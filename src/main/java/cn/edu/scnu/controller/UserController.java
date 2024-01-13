package cn.edu.scnu.controller;

import cn.edu.scnu.model.ErrorResponse;
import cn.edu.scnu.model.user.LoginRequest;
import cn.edu.scnu.model.user.RegisterRequest;
import cn.edu.scnu.model.user.User;
import cn.edu.scnu.service.UserService;
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
    public ErrorResponse register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();

        String message = userService.saveRegister(name, email, password);
        if (message != null) {
            response.setStatus(401);
            return new ErrorResponse(message);
        }
        response.setStatus(201);
        return new ErrorResponse("");
    }

    @DeleteMapping("/users/sessions")
    public ErrorResponse logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute("user");

        response.setStatus(204);
        return new ErrorResponse("");
    }

    @PostMapping("/users/sessions")
    public Object login(@RequestBody LoginRequest request, HttpSession session, HttpServletResponse response) {
        String username = request.getUsername();
        String password = request.getPassword();
        boolean isRemember = request.isRemember();

        String message = userService.checkLogin(username, password);
        if (message != null) {
            response.setStatus(401);
            return new ErrorResponse(message);
        }

        User user = userService.getJustNowUser();
        user.setPasswordHash(null);
        session.setAttribute("user", user);
        if (isRemember) {
            session.setMaxInactiveInterval(604800);
            // 604800 seconds = 7 days
        }
        response.setStatus(201);
        return user;
    }

    @GetMapping("/users")
    public Object sendSession(HttpSession session, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            return new ErrorResponse("未登录账号！");
        }
        response.setStatus(200);
        return user;
    }
}
