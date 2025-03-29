/**
 * WebConfig
 * 
 * Configures Spring to serve uploaded profile pictures from a folder.
 *
 * Maps requests to /uploaded-pfps/** to the file system directory /uploaded-pfps/
 */

package socialMediaSite.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@Configuration
public class WebConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploaded-pfps/**")
                .addResourceLocations("file:uploaded-pfps/");
    }
}
