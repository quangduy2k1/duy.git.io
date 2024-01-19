package com.project4.admin.user;

import com.project4.common.exception.ModelException;
import com.project4.common.entites.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class UserController {
    private static final String GET_HONE_USER = "/user";
    private static final String VIEW_USER_NEW = "/user/new";
    private static final String CREATE_USER_INFO = "/user/save";
    private static final String EDIT_USER_INFO = "/user/edit/{id}";

    private static final String DELETE_USER_INFO = "/user/delete/{id}";

    private final UserService userService;

    @GetMapping(GET_HONE_USER)
    public String showList(Model model) {
        List<User> list = userService.listAll();
        model.addAttribute("list", list);
        return "admin/user";
    }

    @GetMapping(VIEW_USER_NEW)
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add new User");
        return "admin/new_user";
    }

    @PostMapping(CREATE_USER_INFO)
    public String save(User user, RedirectAttributes ra) throws IOException {
        userService.save(user);
        ra.addFlashAttribute("message", " The User has been saved successfully");
        return "redirect:/user";
    }

    @PutMapping(EDIT_USER_INFO)
    public String showEdit(@PathVariable("id") Integer id,
                           Model model,
                           RedirectAttributes ra) {
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User(ID :" + id + ")");
            return "admin/new_user";
        } catch (ModelException e) {
            ra.addFlashAttribute("message", " The User has been saved successfully");
            return "redirect:/user";
        }

    }

    @DeleteMapping(DELETE_USER_INFO)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            userService.delete(123);
            ra.addFlashAttribute("r", " The User Id " + id + " has been delete");
        } catch (Exception e) {
            ra.addFlashAttribute("r", e.getMessage());
        }
        return "redirect:/user";
    }

}
