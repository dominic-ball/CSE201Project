/**
 * Endpoints:
 * - /api/users/register : Registers a new user
 * - /api/users/login    : Logs in an existing user
 * - /api/users/setup    : Updates user profile (bio + profile picture via form)
 * - /api/users/update   : Updates user profile (bio + pfp via JSON)
 * - /api/users/{username} : Fetches a user's public profile info
 */

 package socialMediaSite.demo.controller;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
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
 
     // Handles multipart/form-data submissions (setup.html form)
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
                 // Save to uploads/ folder relative to project root
                 String uploadDir = "uploads/";
                 File dir = new File(uploadDir);
                 if (!dir.exists() && !dir.mkdirs()) {
                     throw new IOException("Failed to create upload directory at " + dir.getAbsolutePath());
                 }
 
                 String originalFilename = profilePic.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
                 String fileName = username + "_" + originalFilename;
                 File destination = new File(dir, fileName);
 
                 profilePic.transferTo(destination);
 
                 if (destination.exists()) {
                     System.out.println("File saved successfully at: " + destination.getAbsolutePath());
                 } else {
                     System.out.println("File not saved!");
                 }
 
                 // Save relative path into database
                 user.setProfilePicPath("/uploads/" + fileName);
 
             } catch (IOException e) {
                 e.printStackTrace();
                 return "Failed to save profile picture.";
             }
         }
 
         userService.register(user);
         return "Profile updated successfully!";
     }
 
     // Handles JSON submissions (setup.html JavaScript)
     @PostMapping("/update")
     public ResponseEntity<String> updateProfile(@RequestBody User updatedUser) {
         Optional<User> userOpt = userService.findByUsername(updatedUser.getUsername());
 
         if (userOpt.isPresent()) {
             User user = userOpt.get();
             user.setBio(updatedUser.getBio());
             user.setProfilePicPath(updatedUser.getProfilePicPath()); // base64 string or relative path
             userService.register(user);
             return ResponseEntity.ok("Profile updated");
         } else {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
         }
     }
 
     @GetMapping("/{username}")
     public User getUser(@PathVariable String username) {
         Optional<User> opt = userService.findByUsername(username);
         opt.ifPresent(user -> System.out.println("Serving profilePicPath: " + user.getProfilePicPath()));
         return opt.orElse(null);
     }
 }
 