package cn.edu.scnu.service;

import cn.edu.scnu.dao.UserDao;
import cn.edu.scnu.model.user.User;
import cn.edu.scnu.util.Password;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserDao, User> {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    private User justNowUser;
    public User getJustNowUser() {
        return justNowUser;
    }

    public String checkLogin(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", username);
        User user = userDao.selectOne(queryWrapper);
        if (user == null) {
            return "账号不存在！";
        }

        String passwordHash = user.getPasswordHash();
        if (!Password.decrypt(password, passwordHash)) {
            return "密码错误！";
        }

        justNowUser = user;
        return null;
    }

    public String saveRegister(String name, String email, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userDao.selectOne(queryWrapper);
        if (user != null) {
            return "该邮箱已被注册！";
        }

        user = new User();
        String passwordHash = Password.encrypt(password);
        user.setPasswordHash(passwordHash);
        user.setEmail(email);
        user.setVip(0);
        if (name != null) {
            user.setName(name);
        }

        userDao.insert(user);
        return null;
    }
}
