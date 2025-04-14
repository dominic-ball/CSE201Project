package socialMediaSite.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LandingController {

    @GetMapping(value = "/", produces = "text/html")
    @ResponseBody
    public String landing() {
        return "<html>" +
                 "<head><title>Welcome to Ascend</title></head>" +
                 "<body style='background-color:#2e2e2e;color:white;font-family:sans-serif;display:flex;align-items:center;justify-content:center;height:100vh;margin:0;'>" +
                   "<div>" +
                      "<h1>Welcome to Ascend Social Media!</h1>" +
                      "<p>Use the endpoints for registration, login, and more.</p>" +
                   "</div>" +
                 "</body>" +
               "</html>";
    }
}
