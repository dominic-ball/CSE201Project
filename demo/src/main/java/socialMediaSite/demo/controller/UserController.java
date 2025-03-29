/**
 * Endpoints:
 * - /api/users/register : Registers a new user
 * - /api/users/login    : Logs in an existing user
 * - /api/users/setup    : Updates user profile (bio + profile picture)
 * - /api/users/{username} : Fetches a user's public profile info
 */

package socialMediaSite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import socialMediaSite.demo.model.User;
import socialMediaSite.demo.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginData) {
        User user = userService.login(loginData.getUsername(), loginData.getPassword());
        if (user != null) {
            return "Login successful!";
        } else {
            return "Invalid username or password!";
        }
    }

    @SuppressWarnings("null")
    @PostMapping("/setup")
    public String setupUser(
        @RequestParam("username") String username,
        @RequestParam(value = "bio", required = false) String bio,
        @RequestParam(value = "profile-pic", required = false) MultipartFile profilePic
    ) {
        Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        User user = optionalUser.get();

        if (bio != null) {
            user.setBio(bio);
        }

        if (profilePic != null && !profilePic.isEmpty()) {
            try {
                // Get absolute path to your project directory
                String projectRoot = System.getProperty("user.dir");
                String uploadDir = projectRoot + File.separator + "uploaded-pfps";
        
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();
        
                // Sanitize file name (remove spaces/special characters)
                String originalFilename = profilePic.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
                String fileName = username + "_" + originalFilename;
                String filePath = uploadDir + File.separator + fileName;
        
                // Save the file to disk
                profilePic.transferTo(new File(filePath));
        
                // Set web-accessible path
                user.setProfilePicPath("/uploaded-pfps/" + fileName);
        
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to save profile picture.";
            }
        }
        
        
        

        userService.register(user); // reuse save method
        return "Profile updated successfully!";
    }
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.findByUsername(username).orElse(null);
    }

}
