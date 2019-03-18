package com.cmloveletter.controller;

import com.cmloveletter.entity.User;
import com.cmloveletter.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * 控制器
 */
@Controller
@Repository(value = "user") //表明该类是用来执行与数据库相关的操作
public class Contoller {

    @Autowired
    UserService user;

    @RequestMapping("/login")
    public String login(){
        System.out.println("--login");
        return "login";
    }

    @RequestMapping("/")
    public String def(HttpSession session){
        return "index";
    }


    /**
     * 跳转到应用列表页面
     * @param pageNo 要显示第几页内容
     * @param pageSize 一页显示多少条
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="5")int pageSize, Model model){
        PageInfo<User> page = user.getAllUserByPage(pageNo,pageSize);
        model.addAttribute("pageInfo", page);
        System.out.println(page);
        return "index";
    }


//    @RequestMapping("/index")
//    public String index(HttpSession session,int currentPage,int pageSize){
//        System.out.println("--index");
//        List<User> list = user.getAllUserByPage(currentPage,pageSize);
//        session.setAttribute("userList",list);
//        return "index";
//    }

    @RequestMapping("/loginUser")
    @ResponseBody
    public Map<String,Object> loginUser(String username, String userpwd, HttpSession session){
        System.out.println("--loginUser");
        userpwd = creatMD5(userpwd);
//        System.out.println("--login userName:"+username+" userPwd:"+userpwd);
        Map<String,Object> map = new HashMap();
        boolean flag = false;
        User u = user.loginUser(username,userpwd);
        if (u == null) {
            map.put("loginInf","用户名或密码错误！");
        }
        else {
            map.put("loginInf","登录成功！");
            flag = true;
            session.setAttribute("ADMIN_SESSION",u);
        }
        map.put("flag",flag);
        return map;
    }
    //MD5加密
    private String creatMD5(String str){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return new BigInteger(1, md.digest()).toString(16);
    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession session){
        session.setAttribute("ADMIN_SESSION",null);
        return "login";
    }


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) { return "上传失败，请选择文件"; }
        String fileName = file.getOriginalFilename();
        String filePath = "F:\\TEST\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.warning(e.toString());
        }
        return "上传失败！";
    }

}
