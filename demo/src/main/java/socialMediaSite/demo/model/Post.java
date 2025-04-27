package socialMediaSite.demo.model;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import jakarta.persistence.*;
>>>>>>> 4eb063f (Post creation, text only For now)
/**
 * Post class
 * Manage attributes of post
 * 
 */
import jakarta.persistence.Id;

@Entity

public class Post {
<<<<<<< HEAD

    String caption;
    String imagePath;
    int likes;
    ArrayList<String> comments = new ArrayList<>();


    public Post(String imagePath, String caption){
        this.caption = caption;
        this.imagePath = imagePath;
    }

    public void setCaption(String caption){
        this.caption = caption;
    }

    public String getCaption(){
        return this.caption;
    }

    public int getLikes(){
        return this.likes;
    }

    public void addLike(){//increases like count by one
        likes++;
    }

    public void removeLike(){//decreases likes by one ***We will need to implement logic to not allow you to like something twice
        likes--;
    }
    public void setLikes(int likes){
        this.likes = likes;
    }

    public ArrayList<String> getComments(){
        return comments;
    }
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    private String username; //user of who made the post

    private String timestamp;

    private String imagePath; //path to the image

    public Post() {
    }
    
    public Post(String content, String username, String timeStamp) {
        this.content = content;
        this.username = username;
        this.timestamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

>>>>>>> 4eb063f (Post creation, text only For now)
}
