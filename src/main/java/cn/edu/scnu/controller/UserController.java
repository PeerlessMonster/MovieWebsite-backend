package cn.edu.scnu.controller;

import cn.edu.scnu.model.ErrorResponse;
import cn.edu.scnu.model.LoginRequest;
import cn.edu.scnu.model.RegisterRequest;
import cn.edu.scnu.model.User;
import cn.edu.scnu.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ErrorResponse register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();

        String message = userService.saveRegister(name, email, password);
        if (message != null) {
            response.setStatus(401);
            return new ErrorResponse(message);
        }
        return new ErrorResponse("");
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("user");
    }

    @PostMapping("/login")
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
        user.setPasswordHash("");
        session.setAttribute("user", user);
        if (isRemember) {
            session.setMaxInactiveInterval(604800);
            // 604800 seconds = 7 days
        }
        return user;
    }

    @GetMapping("/user")
    public User getAllInfo(HttpSession session, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.setStatus(401);
        }
        return user;
    }
}
