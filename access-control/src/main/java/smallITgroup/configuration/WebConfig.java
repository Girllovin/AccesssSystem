package smallITgroup.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this class as a configuration class in Spring context
public class WebConfig implements WebMvcConfigurer {

    // This method is used to configure Cross-Origin Resource Sharing (CORS) settings
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // This allows requests from a specific origin (https://accescontrolsystem.onrender.com)
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("https://accescontrolsystem.onrender.com") // Allow cross-origin requests from this URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these HTTP methods
                .allowedHeaders("*") // Allow any headers in the request
                .exposedHeaders("Authorization") // Expose the Authorization header in the response
                .allowCredentials(true); // Allow credentials (cookies, HTTP authentication) to be sent
    }
}
