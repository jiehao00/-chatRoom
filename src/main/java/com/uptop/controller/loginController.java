package com.uptop.controller;


import com.uptop.entity.ImageUrl;
import com.uptop.entity.Users;
import com.uptop.service.ImagesService;
import com.uptop.service.UserService;
import com.uptop.utils.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/user")
public class loginController {

    @Autowired
    private UserService userService;
    @Autowired
    private ImagesService imagesService;

    private Upload upload;

    @RequestMapping("/login")
    public String login(Users users, HttpServletRequest request){
        Users resultUser=userService.login(users);
        if(resultUser==null){
            request.setAttribute("users",users);
            request.setAttribute("errorMsg","用户名或者密码错误");
            return "login";
        }else {
            if (resultUser.getState().equals("在线")){
                request.setAttribute("users",users);
                request.setAttribute("errorMsg","用户已登录");
                return "login";
            }else {
                 userService.updateByin(resultUser.getName());
                HttpSession session=request.getSession();
                session.setAttribute("currentUser",resultUser);
                return "index";
            }
        }
    }
    @RequestMapping("/logout")
    public String logout(String name){
        //System.out.println(name);
        userService.updateByout(name);

        return "redirect:/login.jsp";
    }
    @RequestMapping("/out")
    public void out(String name){
       // System.out.println(name);
        userService.updateByout(name);
    }


    @RequestMapping("/seachName")
    @ResponseBody
    public List<Users> search(){
        List<Users>users=new ArrayList<Users>();
        users=userService.searchName();
        //System.out.println(users);
        return users;
    }

    @RequestMapping("/register")
    public String register(Users users,HttpServletRequest request) throws IOException {
        String sqlPath = null;
        //本地服务器路径
        String dir = request.getSession().getServletContext().getRealPath("")+"/upload/images/";
        //定义 文件名
        String filename=null;
        Users resultUser=userService.exam(users);
        if( resultUser ==null){
            if(!users.getUrl().isEmpty()){
                upload=new Upload();
                filename=upload.uploadImage(users.getUrl(),dir);
            }
            sqlPath = "/upload/images/"+filename;
            users.setImagesUrl(sqlPath);
            userService.insertUser(users);
            return "login";
        }else {
            request.setAttribute("errorMsg","用户名已存在");
            //attributes.addFlashAttribute("errorMsg","用户名已存在");
            return "register";
        }
    }

    @RequestMapping("/images")
    public String uploadImages(ImageUrl images,HttpServletRequest request) throws IOException {
        String sqlPath = null;
        //本地服务器路径
        String dir = request.getSession().getServletContext().getRealPath("")+"/upload/images/";
        //定义 文件名
        String filename=null;
        if(!images.getUrl().isEmpty()){
            upload=new Upload();
           filename= upload.uploadImage(images.getUrl(),dir);
        }
        sqlPath = "/upload/images/"+filename;
        images.setImagesUrl(sqlPath);
        imagesService.insertImages(images);
        return "login";
    }

    @RequestMapping("/seachImage")
    @ResponseBody
    public String seachImages(String userName){
        System.out.println(userName);
        String imgUrl=userService.seachImages(userName);
        System.out.println(imgUrl);
        return imgUrl;
    }



//    @RequestMapping("/login")
//    public ModelAndView login(ModelMap map, Users users){
//        //ModelMap map=new ModelMap();
//        Users user=userService.login(users);
//        List<Users> usersList=new ArrayList<Users>();
//        usersList.add(user);
//        if (user==null){
//            map.addAttribute("users",users);
//            map.addAttribute("errorMsg","用户名或者密码错误");
//            return new ModelAndView("login");
//        }
//        else {
//            map.addAttribute("currentUser",usersList);
//            return new ModelAndView("index");
//        }
//    }

}
