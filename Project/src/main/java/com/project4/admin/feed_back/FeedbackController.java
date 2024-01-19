package com.project4.admin.feed_back;

import com.project4.common.entites.FeedBack;
import com.project4.common.entites.User;
import com.project4.admin.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
public record FeedbackController(FeedbackService feedbackService, UserService userService) {
    private static final String GET_LIST_FEED_BACK = "/feedback";
    private static final String DELETE_FEED_BACK = "/feedback/delete/{id}";

    @GetMapping(GET_LIST_FEED_BACK)
    public String showList(Model model) {
        List<FeedBack> list = feedbackService.listAll();
        model.addAttribute("list", list);
        List<User> us = userService.listAll();
        model.addAttribute("us", us);
        return "admin/feedback";
    }

    @GetMapping("/feedback/new")
    public String showForm(Model model) {
        model.addAttribute("feedback", new FeedBack());
        model.addAttribute("pageTitle", "Add new Feedback");
        return "admin/new_user";
    }

    @PostMapping("/feedback/save")
    public String save(FeedBack feedBack, RedirectAttributes ra) throws IOException {
        feedbackService.save(feedBack);
        ra.addFlashAttribute("message", " The Feedback has been saved successfully");
        return "redirect:/admin/user";
    }

    @DeleteMapping(DELETE_FEED_BACK)
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            feedbackService.delete(id);
            ra.addFlashAttribute("r", " The Feedback Id " + id + " has been delete");
        } catch (Exception e) {
            ra.addFlashAttribute("r", e.getMessage());
        }
        return "redirect:/feedback";
    }
}
