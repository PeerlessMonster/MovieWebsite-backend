package cn.edu.scnu.service;

import cn.edu.scnu.DAO.UserDAO;
import cn.edu.scnu.DTO.ErrorResponse;
import cn.edu.scnu.entity.User;
import cn.edu.scnu.util.Password;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserDAO, User> {
    private User selectUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return this.getOne(queryWrapper);
    }


    public User getUser(String username) {
        return selectUserByEmail(username);
    }

    public ErrorResponse verifyPassword(int userId, String password) {
        User user = this.getById(userId);

        String passwordHash = user.getPasswordHash();
        if (!Password.decrypt(password, passwordHash)) {
            return ErrorResponse.PASSWORD_WRONG;
        }
        return ErrorResponse.NONE;
    }

    public ErrorResponse verify(String username, String password) {
        User user = selectUserByEmail(username);
        if (user == null) {
            return ErrorResponse.USERNAME_NOT_EXIST;
        }

        String passwordHash = user.getPasswordHash();
        if (!Password.decrypt(password, passwordHash)) {
            return ErrorResponse.PASSWORD_WRONG;
        }
        return ErrorResponse.NONE;
    }

    public ErrorResponse create(String name, String email, String password) {
        User user = selectUserByEmail(email);
        if (user != null) {
            return ErrorResponse.EMAIL_EXIST;
        }
        user = new User();

        String passwordHash = Password.encrypt(password);
        user.setPasswordHash(passwordHash);
        user.setEmail(email);
        user.setVip(0);
        if (name != null) {
            user.setName(name);
        }

        this.save(user);
        return ErrorResponse.NONE;
    }

    public ErrorResponse update(int userId, String name, String email) {
        User user = selectUserByEmail(email);
        if (user != null) {
            return ErrorResponse.EMAIL_EXIST;
        }

        user = this.getById(userId);
        user.setName(name);
        user.setEmail(email);
        this.updateById(user);
        return ErrorResponse.NONE;
    }

    public ErrorResponse updatePassword(int userId, String password) {
        User user = this.getById(userId);

        String passwordHash = user.getPasswordHash();
        if (Password.decrypt(password, passwordHash)) {
            return ErrorResponse.PASSWORD_SAME;
        }

        passwordHash = Password.encrypt(password);
        user.setPasswordHash(passwordHash);
        this.updateById(user);
        return ErrorResponse.NONE;
    }
}
