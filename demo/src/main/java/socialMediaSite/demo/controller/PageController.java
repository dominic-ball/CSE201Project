package socialMediaSite.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import socialMediaSite.demo.model.Post;
import socialMediaSite.demo.model.User;
import socialMediaSite.demo.service.PostService;
import socialMediaSite.demo.service.UserService;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/profile")
public String profilePage(@RequestParam("username") String username, Model model) {
    System.out.println("=== Entered /profile endpoint ===");
    System.out.println("Looking up user: " + username);

    User user = userService.getUserByUsername(username);

    if (user == null) {
        System.out.println("User not found for username: " + username);
        return "error"; // or redirect back to login/home
    }

    model.addAttribute("xpNeeded", user.xpNeededForNextLevel());

    System.out.println("Found user: " + user.getUsername());

    List<Post> userPosts = postService.getPostsByUsername(username);

    System.out.println("Number of posts loaded: " + (userPosts != null ? userPosts.size() : "null"));

    model.addAttribute("user", user);
    model.addAttribute("posts", userPosts);

    System.out.println("=== Finished /profile endpoint ===");

    return "profile";
}



    @GetMapping("/dashboard")
    public String dashboardPage(@RequestParam("username") String username, Model model) {
        model.addAttribute("username", username);
        return "dashboard";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password) {
        
        System.out.println("=== Entered /signup endpoint ===");
        System.out.println("Reqyested user signup for: " + username);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setBio("");
        newUser.setXp(0.0);
        
        userService.register(newUser);

        System.out.println("User registered successfully: " + username);
        //redirects to profile page 
        return "redirect:/profile?username=" + username;
    }
}

