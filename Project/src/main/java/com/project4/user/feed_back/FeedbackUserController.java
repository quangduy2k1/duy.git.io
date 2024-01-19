package com.project4.user.feed_back;

import com.project4.common.entites.*;
import com.project4.user.authen.UserAuthenService;
import com.project4.user.brach.UserBranchService;
import com.project4.user.category.UserCategoryService;
import com.project4.user.product.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/v1/user")
public record FeedbackUserController(FeedbackUserService feedbackUserService ,
                                     UserProductService userProductService,
                                     UserBranchService userBranchService ,
                                     UserCategoryService userCategoryService,
                                     UserAuthenService userAuthenService) {
    private static final String GET_VIEW_NEW_FEED_BACK = "/feedback/new";
    private static final String CREATE_FEED_BACK = "/feedback/save";
    private static final String HOME_REGISTRATION ="/homepage/user/registration";
    private static final String GET_VIEW_FEED_BACK ="/homepage/contact";

    @GetMapping(GET_VIEW_NEW_FEED_BACK)
    public String showForm(Model model) {
        model.addAttribute("feedback", new FeedBack());
        model.addAttribute("pageTitle", "Add new Feedback");
        return "admin/new_user";
    }

    @PostMapping(CREATE_FEED_BACK)
    public String save(FeedBack feedBack, RedirectAttributes ra) throws IOException {
        feedbackUserService.save(feedBack);
        ra.addFlashAttribute("message", " The Feedback has been saved successfully");
        return "redirect:/admin/user";
    }
    @GetMapping(HOME_REGISTRATION)
    public String show(Model model) {
        List<Product> list1 = userProductService.listAll();
        model.addAttribute("list1", list1);
        List<Brand> list = userBranchService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = userCategoryService.listAll();
        model.addAttribute("listt", listt);
        model.addAttribute("users", new User());
        return "Homepage/registration";
    }


    @GetMapping(GET_VIEW_FEED_BACK)
    public String contact(Model model) {
        List<Product> list1 = userProductService.listAll();
        model.addAttribute("list1", list1);
        List<User> listu = userAuthenService.listAll();
        model.addAttribute("listu", listu);
        List<Brand> list = userBranchService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = userCategoryService.listAll();
        model.addAttribute("listt", listt);
        model.addAttribute("feedback", new FeedBack());
        return "homepage/contact";
    }

}
