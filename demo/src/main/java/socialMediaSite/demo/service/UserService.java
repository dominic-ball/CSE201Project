/*
 * handles user registration, login validation and profile updates.
 */

package socialMediaSite.demo.service;

import socialMediaSite.demo.model.User;
import socialMediaSite.demo.repository.UserRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace(); // logs the actual issue to console
            return null;
        }
    }
    

    public User login(String username, String password) {
        return userRepository.findByUsername(username)
            .filter(u -> u.getPassword().equals(password))
            .orElse(null);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
}
