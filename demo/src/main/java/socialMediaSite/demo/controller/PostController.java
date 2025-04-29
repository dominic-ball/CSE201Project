package socialMediaSite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/posts/{id}/like")
    public String likePost(@PathVariable Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setLikes(post.getLikes() + 1);
            postRepository.save(post);
        }

        return "redirect:/posts/" + id; 
    }

    @PostMapping("/posts/{id}/comment")
    public String commentOnPost(@PathVariable Long id, String comment) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.getComments().add(comment);
            postRepository.save(post);
        }

        return "redirect:/posts/" + id; 
    }
}
