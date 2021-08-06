package com.atgui.dao;

import com.atgui.pojo.User;

public interface UserDao {
    /*根据用户名查询信息
     *username 用户名
     * 如果返回null，则没有此用户，反之亦然
     * */
    public User queryUserByUsername(String username);


    /*
     * 用用户名和密码来查询用户信息
     * 如果返回null，则是用户名或密码错误，反之亦然
     * */
    public User  queryUserByUsernameAndpassword(String username,String password);

    /*
     *保存用户信息
     * 返回-1则失败，反之成功
     * */

    public  int savaUser(User  user);
}
