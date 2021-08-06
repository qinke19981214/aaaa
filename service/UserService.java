package com.atgui.service;

import com.atgui.pojo.User;

public interface UserService {

    /*
     *注册用户
     * user
     * */
    public void regisUser(User user);

    /*
     * 登入用户
     * user
     * */
    public  User  loginUser(User user);

    /*
     * 检查用户名是否存在
     * 返回true则用户名存在，返回false表示用户名可用
     * */
    public boolean existsUsername(String username);
}
