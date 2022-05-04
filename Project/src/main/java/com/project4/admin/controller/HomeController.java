package com.project4.admin.controller;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.Admin;
import com.project4.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AdminService adminService;

    @GetMapping("/admin")
    public String show(HttpSession session) {
        session.removeAttribute("UserName");
        return "admin/login";
    }

    @GetMapping("/index")
    public String shows() {
        return "admin/index";
    }

    @GetMapping("/admin/dashboard")
    public String showDashboard() {
        return "admin/home";
    }

    @PostMapping("/admin/home")
    public String checkLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        if (adminService.check(email, password)) {
            session.setAttribute("UserName", email);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("message", " incorrect password or incorrect email");
            session.setAttribute("UserName", email);
        }
        return "admin/login";
    }

    @GetMapping("/listAdmin")
    public String showAdmin(Model model) {
        List<Admin> list = adminService.listAll();
        model.addAttribute("list", list);
        return "admin/listAdmin";
    }

    @GetMapping("/listAdmin/new")
    public String showForm(Model model) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("pageTitle", "Add new Admin");
        return "admin/listAdmin_new";
    }

    @PostMapping("/listAdmin/save")
    public String saveAdmin(Admin admin, RedirectAttributes ra) throws IOException {
        adminService.save(admin);
        ra.addFlashAttribute("message", " The Admin has been saved successfully");
        return "redirect:/listAdmin";
    }

    @GetMapping("/listAdmin/edit/{id}")
    public String showEdit(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Admin admin = adminService.get(id);
            model.addAttribute("admin", admin);
            model.addAttribute("pageTitle", "Edit Admin(ID :" + id + ")");
            return "admin/listAdmin_new";
        } catch (ModelException e) {
            ra.addFlashAttribute("message", " The Category has been saved successfully");
            return "redirect:/listAdmin";
        }
    }

    @DeleteMapping("/listAdmin/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            adminService.delete(id);
            ra.addFlashAttribute("r", " The Admin Id " + id + " has been delete");
        } catch (Exception e) {
            ra.addFlashAttribute("r", e.getMessage());
        }
        return "redirect:/listAdmin";
    }

}

