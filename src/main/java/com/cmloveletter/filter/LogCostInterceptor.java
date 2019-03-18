package com.cmloveletter.filter;

import com.cmloveletter.entity.User;
import com.cmloveletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
public class LogCostInterceptor implements HandlerInterceptor {
    long start = System.currentTimeMillis();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start = System.currentTimeMillis();
        System.out.println("------------------拦截");
        //前置拦截

//        request.setAttribute("path", request.getRequestURI());
//        String path = request.getRequestURI().substring(1);
//        System.out.println("---"+path);
//        int n = path.indexOf("/");
//        //拿到url路径,一会判断使用
//        String module = n > 0 ? path.substring(0, n) : "index";
//        request.setAttribute("module", module);
//        //第一次访问,会根据cookie判断是否登录,登录以后就放session里面
//        //如果第二次访问,就会有session信息了
//        User user = (User)request.getSession().getAttribute("ADMIN_SESSION");
//        if (user == null) {
//            String adminstr = "";
//            Cookie[] cookies = request.getCookies();
//            if (null != cookies) {
//                for (Cookie cookie : cookies) {
//                    if (user.getUname().equals(cookie.getName())) {
//                        adminstr = cookie.getValue();
//                        if (adminstr != null)
//                            break;
//                    }
//                }
//                if (!StringUtils.isEmpty(adminstr)) {
//                    //根据cookie的value值base65解码得到ID
//                    //int adminId = Integer.parseInt(CryptoUtil.decryptDES(Constants.ADMIN_KEY, adminstr));
//                    //根据ID查询,得到账号的所有信息
//                    //user = service.selectAdminById(adminId);
//                    System.out.println(adminstr);
//                }
//                if (user != null) {
//                    //如果账号存在,就放session里面,以后都省的去Cookie查了
//                    request.getSession().setAttribute("ADMIN_SESSION", user);
//                    //调用下面的方法
//                    return goOn(user, module, request, response);
//                } else {
//                    response.sendRedirect("/login");
//                    return false;
//                }
//            } else {
//                return goOn(user, module, request, response);
//            }
//        }
        HttpSession session=request.getSession();
        User userInfo = (User)session.getAttribute("ADMIN_SESSION");
        if(userInfo==null)
        {
            //没有session，跳转至login
            System.out.println("--No Login--");
            response.sendRedirect("/login");
            //request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }
        return  true;
    }
    // (admin的权限字段).contains(截取的路径)
    // 匹配判断是否能进入程序,return boolean
    private boolean goOn(User u, String module, HttpServletRequest request, HttpServletResponse response){
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("---Interceptor Execute cost="+(System.currentTimeMillis()-start)+"----------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //最终拦截
    }
}
