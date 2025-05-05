package socialMediaSite.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import socialMediaSite.demo.model.User;
import socialMediaSite.demo.service.UserService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/shop")
public class ShopItemController {

    @Autowired
    private UserService userService;

    // Hardcoded item costs 
    private static final Map<String, Integer> ITEM_COSTS = Map.of(
        "level1_pfp.png", 0,
        "level5_pfp.png", 610,
        "level10_pfp.png", 1592,
        "level20_pfp.png", 5727
    );

   
    @PostMapping("/buy")
    public String buyProfilePic(@RequestBody PurchaseRequest request) {
        User user = userService.getUserByUsername(request.getUsername());

        if (user == null) {
            return "User not found";
        }

        String filename = request.getFilename();
        if (!ITEM_COSTS.containsKey(filename)) {
            return "Invalid item";
        }

        int actualCost = ITEM_COSTS.get(filename);
        if (user.getSpendableXp() < actualCost) {
            return "Not enough XP";
        }

        // Deduct XP and assign new profile pic
        user.setSpendableXp(user.getSpendableXp() - actualCost);
        user.setProfilePicPath(filename);
        userService.save(user);

        return "Success";
    }

    
    public static class PurchaseRequest {
        private String username;
        private String filename;
        private int cost;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getFilename() { return filename; }
        public void setFilename(String filename) { this.filename = filename; }

        public int getCost() { return cost; }
        public void setCost(int cost) { this.cost = cost; }
    }
}
