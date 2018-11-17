package com.baizhan.controller;

import com.baizhan.pojo.Users;
import com.baizhan.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *  实现控制层
 */
@Controller
@RequestMapping("/users")//所有访问UserController的方法都加一个前缀/user
public class UsersController {
    @Autowired //自动注入
    private UsersService usersService;//业务层对象
    /**
     * 页面跳转
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public String addUser(Users users){
        System.out.println("进入controller:" + users.getName());
        this.usersService.addUser(users);
        return "ok";
    }

    /**
     *  查询所有用户
     */
    @RequestMapping("selectUserAll")
    public String selectUserAll(Model model){
        List<Users> list = this.usersService.selectUserAll();
        model.addAttribute("list",list);
        return "showUser";
    }

    /**
     *  根据id查询用户
     */
    @RequestMapping("/selectUsersById")
    public String selectUsersById(Integer id,Model model){
        Users user = this.usersService.selectUsersById(id);
        model.addAttribute("user",user);
        return "updateUser";
    }

    /**
     * 更新用户
     */
    @RequestMapping("/editUser")
    public String updateUser(Users users){
        this.usersService.updateUser(users);
        return "ok";
    }

    /**
     *  根据id删除用户
     */
    @RequestMapping("/delUser")
    public String deleteUserById(Integer id){
        this.usersService.deleteUserById(id);
        //return "ok";// 返回成功页面
        return "redirect:/users/selectUserAll";// 重定向会原来页面
    }

    /**
     * demo echarts
     */
    @RequestMapping("/json")
    @ResponseBody
    public List<Users> json(){
        List<Users> list = new ArrayList<Users>();
        list.add(new Users(20,"22",11));
        list.add(new Users(22,"21",14));
        System.out.println(list.toArray().toString());
        return list;
    }
    @RequestMapping("/json1")
    @ResponseBody
    public List<Users> json1(){
        System.out.println("---------------");
        List<Users> list = new ArrayList<Users>();
        list.add(new Users(20,"name",11));
        list.add(new Users(22,"value",14));
        for (Users u:list) {
            System.out.println(u.toString());
        }
        return list;
    }

    /**
     *  echarts
     */
    @RequestMapping("/echarts")
    public String selectUsersById(Model model){
        return "echarts";
    }
}
