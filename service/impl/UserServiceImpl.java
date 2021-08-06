package com.atgui.service.impl;

import com.atgui.dao.UserDao;
import com.atgui.dao.impl.UserDaoImpl;
import com.atgui.pojo.User;
import com.atgui.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void regisUser(User user) {
        userDao.savaUser(user);


    }

    @Override
    public User loginUser(User user) {
        return userDao.queryUserByUsernameAndpassword(user.getUsername(), user.getPassword());

    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            //说明用户名可用
            return false;
        }
        return true;

    }

}
