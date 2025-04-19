package smallITgroup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Marks this class as a configuration class for Spring context
@EnableMethodSecurity // Enables method-level security annotations (like @PreAuthorize)
public class AuthorizationConfiguration {

    // Configures the HTTP security settings
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // Use HTTP Basic authentication for simplicity (could be replaced with JWT or other methods)
        http.httpBasic(withDefaults());
        
        // Disables CSRF protection (important for stateless APIs like those using JWT)
        http.csrf(csrf -> csrf.disable());

        // Configures the session management to be stateless (no server-side session)
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        // Enables CORS and configures request authorization rules
        http.cors(withDefaults()) // Enables Cross-Origin Resource Sharing
            .csrf(csrf -> csrf.disable()) // Ensures CSRF is disabled again here for safety
            .authorizeHttpRequests(
                authorize -> authorize
                    // Permit unauthenticated access to these endpoints (e.g., registration and password recovery)
                    .requestMatchers("/account/register", "/account/recovery/**").permitAll()
                    // All other requests must be authenticated
                    .anyRequest().authenticated()
            );

        // Return the built security filter chain with the applied settings
        return http.build();
    }

}
