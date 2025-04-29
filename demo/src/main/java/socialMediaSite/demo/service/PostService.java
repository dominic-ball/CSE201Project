package socialMediaSite.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialMediaSite.demo.model.Post;
import socialMediaSite.demo.model.User;
import socialMediaSite.demo.repository.PostRepository;
import socialMediaSite.demo.repository.UserRepository;
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService; 

    @Autowired
    private UserRepository userRepository; 

    public Post createPost(Post post) {
    if (post.getTitle() == null || post.getTitle().isEmpty()) {
        post.setTitle(post.getUsername() + "'s post");
    }

    Post savedPost = postRepository.save(post);

    User user = userService.getUserByUsername(post.getUsername());
    if (user != null) {
        System.out.println("User found");
        user.addXp(40);
        userRepository.save(user);
    }

    return savedPost;
}
    

    public List<Post> getPostsByUsername(String username) {
        return postRepository.findByUsername(username);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
    
}
