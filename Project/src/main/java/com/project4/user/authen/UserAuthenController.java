package com.project4.user.authen;

import com.project4.common.entites.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserAuthenController {
    private final UserAuthenService userAuthenService;

    private static final String LOGIN_USER = "/homepage/user/login";
    private static final String CREATE_USER = "/homepage/user/save";

    @PostMapping(LOGIN_USER)
    public String checklogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        if (userAuthenService.check(email, password)) {
            session.setAttribute("UserNames", email);
            return "redirect:/homepage";
        } else {
            model.addAttribute("message", " incorrect password or incorrect email");
            session.setAttribute("UserNames", email);
        }
        return "homepage/login";
    }

    @PostMapping(CREATE_USER)
    public String savee(User user, RedirectAttributes ra, @RequestParam("email") String email, HttpSession session) {
        userAuthenService.save(user);
        ra.addFlashAttribute("message", " The User has been saved successfully");
        session.setAttribute("UserNames", email);
        return "redirect:/homepage";
    }
}
