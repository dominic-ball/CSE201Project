package socialMediaSite.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/loginpage.html";  // works with static HTML
    }

    @GetMapping("/sign-up")
    public String redirectToSignup() {
        return "redirect:/signuppage.html";  // works with static HTML
    }
}
