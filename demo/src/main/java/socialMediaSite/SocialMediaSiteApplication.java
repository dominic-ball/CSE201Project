/**
 * main spring boot application class
 * launches backend server 
 */

package socialMediaSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "socialMediaSiteApplication")
@EnableJpaRepositories(basePackages = "socialMediaSite.repository")
@EntityScan(basePackages = "socialMediaSite.demo.model")

public class SocialMediaSiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialMediaSiteApplication.class, args);
    }
}




