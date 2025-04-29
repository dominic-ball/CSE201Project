package socialMediaSite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import socialMediaSite.demo.model.Post;
import socialMediaSite.demo.repository.PostRepository; 
import socialMediaSite.demo.service.PostService;        

import java.util.Optional; 

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository; 

    @Autowired
    private PostService postService;         
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "viewPost"; 
        } else {
            return "redirect:/profile"; 
        }
    }
}
