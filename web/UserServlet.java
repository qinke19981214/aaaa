package com.atgui.web;

import com.atgui.pojo.User;
import com.atgui.service.UserService;
import com.atgui.service.impl.UserServiceImpl;
import com.atgui.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService =new UserServiceImpl();
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session
        req.getSession().invalidate();
        //重定向首页
        resp.sendRedirect("http://localhost:8080"+req.getContextPath());

    }


    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String username = req.getParameter("username");
        String password = req.getParameter("password");
    User login1= userService.loginUser(new User(null,username,password,null));


        if(login1!=null){
            System.out.println("登入成功");
            HttpSession session = req.getSession();
            session.setAttribute("user",login1);



            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else {
            req.setAttribute("msg","用户或密码错误");
            req.setAttribute("username",username);
            System.out.println("用户名["+username+"]错误"+"或"+"密码["+password+"]错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }

    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        //获取图片中的类容
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.removeAttribute(KAPTCHA_SESSION_KEY);



        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user= BeanUtil.copy(req.getParameterMap(),new User());

        //注册功能

        if (token!=null&&token.equalsIgnoreCase(code)){
            //true 进行下一步
            if(userService.existsUsername(username)){
                req.setAttribute("msg","用户名存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                //返回true则用户名存在，跳转到注册页面去
                System.out.println("用户名["+username+"]已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{

                userService.regisUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);

            }

        }else {
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            //跳回注册页面
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);



        }
    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        try {
//            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
//            declaredMethod.invoke(this,req,resp);
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//    }
}
