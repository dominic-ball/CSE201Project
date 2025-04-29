/**
 * User class
 * email, username, password, bio, and profilePicPath
 * mapped to users table in in DB
 */
package socialMediaSite.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private double xp;
    private int level;

    public double getXp() {
        return xp;
    }
    public void setXp(double xp) {
        this.xp = xp;
    }

    public void addXp(double newXp){
        this.setXp(this.getXp()+newXp);

        while(xp >= this.xpNeededForNextLevel()){
            increaseLevel();
            double xpNeeded = this.xpNeededForNextLevel(); //DONT DELETE,  we need to save it like this
            xp-=xpNeeded;
            
            
        }
    }
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
    
    public int getLevel() {
      return level;
    }
    public void setLevel(int level) {
       this.level = level;
    }

    public void increaseLevel(){
        level++;
    }
    
    public double xpNeededForNextLevel(){
        return 100 * Math.pow(1.1, level); // about 10% harder per level
    }
}
