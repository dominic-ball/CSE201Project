/**
 * WebConfig
 * 
 * Configures Spring to serve uploaded profile pictures from a folder.
 *
 * Maps requests to /uploads/** to the file system directory /uploads/
 */

 package socialMediaSite.demo.config;

 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
 @Configuration
 public class WebConfig implements WebMvcConfigurer {
 
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/uploads/**")
                 .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/");
     }
 }
 