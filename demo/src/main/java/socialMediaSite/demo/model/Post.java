package socialMediaSite.demo.model;
import java.util.ArrayList;
/**
 * Post class
 * Manage attributes of post
 * 
 */
public class Post {

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
}
