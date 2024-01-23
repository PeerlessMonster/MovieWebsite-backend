package cn.edu.scnu.service;

import cn.edu.scnu.DAO.UserDAO;
import cn.edu.scnu.DTO.ErrorType;
import cn.edu.scnu.VO.ErrorVO;
import cn.edu.scnu.entity.User;
import cn.edu.scnu.util.Password;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserDAO, User> {
    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    private User servingUser;
    private boolean checkEmailExist(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userDao.selectOne(queryWrapper);
        if (user == null) {
            return false;
        }
        servingUser = user;
        return true;
    }

    public ErrorType verifyPassword(int userId, String password) {
        User user = this.getById(userId);

        String passwordHash = user.getPasswordHash();
        if (!Password.decrypt(password, passwordHash)) {
            return ErrorType.PASSWORD_WRONG;
        }
        return ErrorType.NONE;
    }

    public ErrorType verify(String username, String password) {
        if (!checkEmailExist(username)) {
            return ErrorType.USERNAME_NOT_EXIST;
        }

        String passwordHash = servingUser.getPasswordHash();
        if (!Password.decrypt(password, passwordHash)) {
            return ErrorType.PASSWORD_WRONG;
        }
        return ErrorType.NONE;
    }

    public User selectUserByUsername(String username) {
        checkEmailExist(username);
        return servingUser;
    }

    public ErrorType create(String name, String email, String password) {
        if (checkEmailExist(email)) {
            return ErrorType.EMAIL_EXIST;
        }

        User user = new User();
        String passwordHash = Password.encrypt(password);
        user.setPasswordHash(passwordHash);
        user.setEmail(email);
        user.setVip(0);
        if (name != null) {
            user.setName(name);
        }

        userDao.insert(user);
        return ErrorType.NONE;
    }

    public ErrorType update(int userId, String name, String email) {
        if (checkEmailExist(email)) {
            return ErrorType.EMAIL_EXIST;
        }

        User user = this.getById(userId);
        user.setName(name);
        user.setEmail(email);
        userDao.updateById(user);
        return ErrorType.NONE;
    }
}
