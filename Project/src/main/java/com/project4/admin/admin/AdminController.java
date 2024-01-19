package com.project4.admin.admin;

import com.project4.common.exception.ModelException;
import com.project4.common.entites.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
public record AdminController(AdminService adminService) {
    private static final String GET_HOME_ADMIN_LOGIN = "/get";
    private static final String GET_HOME_ADMIN_INDEX = "/index";
    private static final String GET_HOME_ADMIN_DASHBOARD = "/dashboard";
    private static final String GET_HOME_ADMIN = "/home";
    private static final String GET_LIST_ADMIN = "/list-admin";
    private static final String GET_LIST_ADMIN_NEW = "/list-admin/new";
    private static final String GET_LIST_ADMIN_CREATE = "/list-admin/save";
    private static final String EDIT_ADMIN_INFO = "/list-admin/edit/{id}";
    private static final String DELETE_ADMIN_INFO = "/list-admin/delete/{id}";

    @GetMapping(GET_HOME_ADMIN_LOGIN)
    public String show(HttpSession session) {
        session.removeAttribute("UserName");
        return "admin/login";
    }

    @GetMapping(GET_HOME_ADMIN_INDEX)
    public String shows() {
        return "admin/index";
    }

    @GetMapping(GET_HOME_ADMIN_DASHBOARD)
    public String showDashboard() {
        return "admin/home";
    }

    @PostMapping(GET_HOME_ADMIN)
    public String checkLogin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model, HttpSession session) {
        if (adminService.check(email, password)) {
            session.setAttribute("UserName", email);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("message", " incorrect password or incorrect email");
            session.setAttribute("UserName", email);
        }
        return "admin/login";
    }

    @GetMapping(GET_LIST_ADMIN)
    public String showAdmin(Model model) {
        List<Admin> list = adminService.listAll();
        model.addAttribute("list", list);
        return "admin/listAdmin";
    }

    @GetMapping(GET_LIST_ADMIN_NEW)
    public String showForm(Model model) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("pageTitle", "Add new Admin");
        return "admin/listAdmin_new";
    }

    @PostMapping(GET_LIST_ADMIN_CREATE)
    public String saveAdmin(Admin admin, RedirectAttributes ra) {
        adminService.save(admin);
        ra.addFlashAttribute("message", " The Admin has been saved successfully");
        return "redirect:/listAdmin";
    }

    @PutMapping(EDIT_ADMIN_INFO)
    public String showEdit(@PathVariable("id") Integer id,
                           Model model,
                           RedirectAttributes ra) {
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

    @DeleteMapping(DELETE_ADMIN_INFO)
    public String delete(@PathVariable("id") Integer id,
                         RedirectAttributes ra) {
        try {
            adminService.delete(id);
            ra.addFlashAttribute("r", " The Admin Id " + id + " has been delete");
        } catch (Exception e) {
            ra.addFlashAttribute("r", e.getMessage());
        }
        return "redirect:/listAdmin";
    }

}

