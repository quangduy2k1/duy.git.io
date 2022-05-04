package com.project4.admin.controller;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Admin;
import com.project4.admin.models.Category;
import com.project4.admin.service.AdminService;
import com.project4.admin.service.BrandService;
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
public class HomeController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/admin")
    public String show(Model model,HttpSession session)
    {
        session.removeAttribute("UserName");
        return "admin/login";
    }
    @GetMapping("/index")
    public String shows(Model model){
        return "admin/index";
    }
    @GetMapping("/admin/dashborad")
    public String showDashboard(Model model){
        return "admin/home";
    }
    @PostMapping("/admin/home")
    public String checklogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session){
        if(adminService.chek(email,password)){
            session.setAttribute("UserName",email);
            return "redirect:/admin/dashborad";
        }else {
            model.addAttribute("message"," incorrect password or incorrect email");
            session.setAttribute("UserName",email);
        }
        return "admin/login";
    }
    @GetMapping("/listAdmin")
    public String showAdmin(Model model){
        List<Admin> list=adminService.listAll();
        model.addAttribute("list",list);
        return "admin/listAdmin";
    }
    @GetMapping("/listAdmin/new")
    public String showForm(Model model){
        model.addAttribute("admin",new Admin());
        model.addAttribute("pageTitle","Add new Admin");
        return "admin/listAdmin_new";
    }
    @PostMapping("/listAdmin/save")
    public String saveAdmin(Admin admin,RedirectAttributes ra) throws IOException {
        adminService.save(admin);
        ra.addFlashAttribute("message"," The Admin has been saved successfully");
        return "redirect:/listAdmin";
    }
    @GetMapping("/listAdmin/edit/{id}")
    public String showEdit(@PathVariable("id")Integer id,Model model,RedirectAttributes ra ){
        try {
            Admin admin=adminService.get(id);
            model.addAttribute("admin",admin);
            model.addAttribute("pageTitle","Edit Admin(ID :" +id+")");
            return "admin/listAdmin_new";
        }catch (ModelException e){
            ra.addFlashAttribute("message"," The Category has been saved successfully");
            return "redirect:/listAdmin";
        }
    }
    @GetMapping("/listAdmin/delete/{id}")
    public String delete(@PathVariable("id")Integer id,RedirectAttributes ra ){
        try {
            adminService.delete(123);
            ra.addFlashAttribute("r"," The Admin Id "+ id +" has been delete");
        }catch(Exception e){
            ra.addFlashAttribute("r",e.getMessage());
        }
        return "redirect:/listAdmin";
    }

}

