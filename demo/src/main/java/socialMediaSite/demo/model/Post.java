package socialMediaSite.demo.model;

import java.util.ArrayList;
import jakarta.persistence.*;

/**
 * Post class
 * Manage attributes of post
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;
    private String title;
    private String content;
    private String username; // user who made the post
    private String timestamp; // time of post creation
    private String imagePath; // path to the image
    private int likes;
    private ArrayList<String> comments = new ArrayList<>();

    // --- Constructors ---
    public Post() {
    }

    public Post(String imagePath, String caption) {
        this.imagePath = imagePath;
        this.caption = caption;
    }

    public Post(String content, String username, String timestamp) {
        this.content = content;
        this.username = username;
        this.timestamp = timestamp;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void addLike() {
        likes++;
    }

    public void removeLike() {
        likes--;
    }

    public ArrayList<String> getComments() {
        return comments;
    }
}
