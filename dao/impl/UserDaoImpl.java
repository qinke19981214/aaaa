package com.atgui.dao.impl;

import com.atgui.dao.UserDao;
import com.atgui.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String  sql="select id,username,password,email from t_user1 where username=?";

        return queryforone(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndpassword(String username, String password) {
        String  sql="select id ,username , password,email from t_user1 where username=? and password=?";

        return queryforone(User.class,sql,username,password);
    }

    @Override
    public int savaUser(User user) {
        String sql="insert into t_user1(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}