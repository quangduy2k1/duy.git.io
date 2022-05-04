package com.project4.admin.controller;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.User;
import com.project4.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public String showList(Model model){
        List<User> list=userService.listAll();
        model.addAttribute("list",list);
        return "admin/user";
    }
    @GetMapping("/user/new")
    public String showForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add new User");
        return "admin/new_user";
    }
    @PostMapping("/user/save")
    public String save(User user, RedirectAttributes ra) throws IOException {
        userService.save(user);
        ra.addFlashAttribute("message"," The User has been saved successfully");
        return "redirect:/user";
    }
    @GetMapping("/user/edit/{id}")
    public String showEdit(@PathVariable("id")Integer id, Model model, RedirectAttributes ra ){
        try {
            User user=userService.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle","Edit User(ID :" +id+")");
            return "admin/new_user";
        }catch (ModelException e){
            ra.addFlashAttribute("message"," The User has been saved successfully");
            return "redirect:/user";
        }

    }
    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable("id")Integer id,RedirectAttributes ra ){
        try {
            userService.delete(123);
            ra.addFlashAttribute("r"," The User Id "+ id +" has been delete");
        }catch(Exception e){
            ra.addFlashAttribute("r",e.getMessage());
        }
        return "redirect:/user";
    }

}
