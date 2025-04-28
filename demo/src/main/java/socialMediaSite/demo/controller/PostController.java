package socialMediaSite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  // ✅ USE THIS
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import socialMediaSite.demo.model.Post;
import socialMediaSite.demo.service.PostService;

@Controller  // ✅ CHANGE TO @Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return "error"; 
        }
        model.addAttribute("post", post);
        return "viewPost"; 
    }
}
