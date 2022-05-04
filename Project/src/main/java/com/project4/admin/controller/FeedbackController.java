package com.project4.admin.controller;

import com.project4.admin.exception.ModelException;
import com.project4.admin.models.FeedBack;
import com.project4.admin.models.User;
import com.project4.admin.service.FeedbackService;
import com.project4.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;
    @GetMapping("/feedback")
    public String showList(Model model){
        List<FeedBack> list=feedbackService.listAll();
        model.addAttribute("list",list);
        List<User> us=userService.listAll();
        model.addAttribute("us",us);
        return "admin/feedback";
    }
    @GetMapping("/feedback/new")
    public String showForm(Model model){
        model.addAttribute("feedback",new FeedBack());
        model.addAttribute("pageTitle","Add new Feedback");
        return "admin/new_user";
    }
    @PostMapping("/feedback/save")
    public String save(FeedBack feedBack, RedirectAttributes ra) throws IOException {
        feedbackService.save(feedBack);
        ra.addFlashAttribute("message"," The Feedback has been saved successfully");
        return "redirect:/admin/user";
    }
    @GetMapping("/feedback/delete/{id}")
    public String delete(@PathVariable("id")Integer id,RedirectAttributes ra ){
        try {
            feedbackService.delete(id);
            ra.addFlashAttribute("r"," The Feedback Id "+ id +" has been delete");
        }catch(Exception e){
            ra.addFlashAttribute("r",e.getMessage());
        }
        return "redirect:/feedback";
    }
}
