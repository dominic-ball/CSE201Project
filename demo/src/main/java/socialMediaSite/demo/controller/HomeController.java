package socialMediaSite.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String sayHello() {
        return "<script>window.location.href = '/loginpage.html';</script>";
    }   


    @GetMapping("/sign-up")
    public String loginClicked() {
        return "sign up placeholder";
    }
}