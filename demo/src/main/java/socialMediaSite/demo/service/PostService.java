package socialMediaSite.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import socialMediaSite.demo.model.Post;
import socialMediaSite.demo.repository.PostRepository;

import java.util.List;
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPostsByUsername(String username) {
        return postRepository.findByUsername(username);
    }
}
