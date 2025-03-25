/**
 * User class
 * email, username, password, bio, and profilePicPath
 * mapped to users table in in DB
 */
package socialMediaSite.demo.model;

import jakarta.persistence.*;

@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    @Column(unique = true)
    private String username;
    private String password; 
    private String bio;
    private String profilePicture;
    private String profilePicPath;


    //getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {  
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public String getProfilePicture() {
        return profilePicture;
    }
    
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePicPath() {
        return profilePicPath;
    }
    
    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }
    
}
