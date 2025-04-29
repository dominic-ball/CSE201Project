package socialMediaSite.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "socialMediaSite.demo")
@EnableJpaRepositories(basePackages = "socialMediaSite.demo.repository")
@EntityScan(basePackages = "socialMediaSite.demo.model")
public class SocialMediaSiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialMediaSiteApplication.class, args);
        System.out.println("[Debug] Social Media Site Application is running!");
        System.out.println("[Debug] You can access the application at: http://localhost:8080");
    }
}
