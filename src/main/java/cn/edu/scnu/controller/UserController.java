package cn.edu.scnu.controller;

import cn.edu.scnu.DTO.ErrorResponse;
import cn.edu.scnu.VO.ErrorVO;
import cn.edu.scnu.DTO.VerifyUserRequest;
import cn.edu.scnu.DTO.UserInfoRequest;
import cn.edu.scnu.VO.UserVO;
import cn.edu.scnu.config.SessionConfig;
import cn.edu.scnu.entity.User;
import cn.edu.scnu.service.UserService;
import cn.edu.scnu.util.ExpireTime;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ErrorVO register(@RequestBody UserInfoRequest request, HttpServletResponse response) {
        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();

        ErrorResponse errorType = userService.create(name, email, password);
        if (errorType != ErrorResponse.NONE) {
            response.setStatus(400);
        } else {
            response.setStatus(201);
        }
        return new ErrorVO(errorType);
    }

    @DeleteMapping("/users/sessions")
    public ErrorVO logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute("user");

        response.setStatus(204);
        return new ErrorVO(ErrorResponse.NONE);
    }

    @PostMapping("/users/sessions")
    public Object login(@RequestBody VerifyUserRequest request, HttpSession session, HttpServletResponse response) {
        String username = request.getUsername();
        String password = request.getPassword();
        boolean isRemember = request.isRemember();

        ErrorResponse errorType = userService.verify(username, password);
        if (errorType != ErrorResponse.NONE) {
            response.setStatus(401);
            return new ErrorVO(errorType);
        }

        User user = userService.getUser(username);
        user.setPasswordHash(null);
        session.setAttribute("user", user);
        int maxInactiveIntervalS = isRemember ? SessionConfig.EXPIRE_REMEMBER : SessionConfig.EXPIRE;
        session.setMaxInactiveInterval(maxInactiveIntervalS);

        response.setStatus(201);
        long expireMillis = maxInactiveIntervalS * 1000;
        return new UserVO(user, expireMillis);
    }

    @PostMapping("/users/passwords")
    public Object checkPassword(@RequestBody VerifyUserRequest request, HttpSession session, HttpServletResponse response) {
        String password = request.getPassword();

        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        ErrorResponse errorType = userService.verifyPassword(userId, password);
        if (errorType != ErrorResponse.NONE) {
            response.setStatus(401);
        } else {
            response.setStatus(201);
        }
        return new ErrorVO(errorType);
    }

    @GetMapping("/users")
    public Object getInformation(HttpSession session, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            return new ErrorVO(ErrorResponse.SESSION_NOT_EXIST);
        }
        response.setStatus(200);

        int maxInactiveIntervalS = session.getMaxInactiveInterval();
        /* 不勾选“记住我”，session的ttl为30分钟，相当于每执行一次该方法（刷新）就重置回30分钟 */
        long expireMillis = maxInactiveIntervalS * 1000;
        /* “记住我”，session的ttl为7天 */
        if (maxInactiveIntervalS == SessionConfig.EXPIRE_REMEMBER) {
            long createTimeMillis = session.getCreationTime();
            expireMillis = ExpireTime.calculate(maxInactiveIntervalS, createTimeMillis);
            /* 访问session就会重置其ttl为maxInactiveInterval，故重新设置 */
            maxInactiveIntervalS = (int) (expireMillis / 1000);
            session.setMaxInactiveInterval(maxInactiveIntervalS);
        }
        return new UserVO(user, expireMillis);
    }

    @PatchMapping("/users")
    public ErrorVO changeInformation(@RequestBody UserInfoRequest request, HttpSession session, HttpServletResponse response) {
        String name = request.getName();
        if (name == null) {
            name = "";
        }
        String email = request.getEmail();

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        ErrorResponse errorType = userService.update(userId, name, email);
        if (errorType == ErrorResponse.NONE) {
            user.setName(name);
            user.setEmail(email);
            session.setAttribute("user", user);

            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
        return new ErrorVO(errorType);
    }

    @PutMapping("/users/passwords")
    public ErrorVO changePassword(@RequestBody UserInfoRequest request, HttpSession session, HttpServletResponse response) {
        String password = request.getPassword();

        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        ErrorResponse errorType = userService.updatePassword(userId, password);
        if (errorType != ErrorResponse.NONE) {
            response.setStatus(400);
        } else {
            response.setStatus(200);
        }
        return new ErrorVO(errorType);
    }

    @PutMapping("/users/vips")
    public ErrorVO upgradeVip(HttpSession session, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        user.setVip(user.getVip() + 1);
        userService.updateById(user);

        session.setAttribute("user", user);
        response.setStatus(200);
        return new ErrorVO(ErrorResponse.NONE);
    }
}
