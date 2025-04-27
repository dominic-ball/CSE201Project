package socialMediaSite.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import socialMediaSite.demo.model.Post;

import java.util.List;
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUsername(String username);
}
