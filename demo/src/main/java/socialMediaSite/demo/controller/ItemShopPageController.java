package socialMediaSite.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemShopPageController {

    @GetMapping("/itemShop")
    public String showShopPage() {
        return "itemShop"; 
    }
}
