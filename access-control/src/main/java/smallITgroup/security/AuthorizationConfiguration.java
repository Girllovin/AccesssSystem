package smallITgroup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AuthorizationConfiguration {

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.httpBasic(withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
        .csrf().disable()
        .cors() 
        .and()
        .authorizeHttpRequests()
            .anyRequest().permitAll();
        
//        http.authorizeHttpRequests(authorize -> authorize
//        		.requestMatchers("/account/register", "/account/recovery/**")
//        			.permitAll()
//        		.mvcMatchers("/account/user/{login}/role/{role}")
//        			.hasRole("ADMINISTRATOR")
//        		.mvcMatchers(HttpMethod.PUT, "/account/user/{login}")
//        			.access("#login == authentication.name")
//        		.mvcMatchers(HttpMethod.DELETE, "/account/user/{login}")
//        			.access("#login == authentication.name or hasRole('ADMINISTRATOR')")
//        		.mvcMatchers(HttpMethod.POST, "/forum/post/{author}")
//        			.access("#author == authentication.name")
//        		.mvcMatchers(HttpMethod.PUT, "/forum/post/{id}/comment/{author}")
//        			.access("#author == authentication.name")
//        		.mvcMatchers(HttpMethod.PUT, "/forum/post/{id}")
//        			.access("@customSecurity.checkPostAuthor(#id, authentication.name)")
//        		.mvcMatchers(HttpMethod.DELETE, "/forum/post/{id}")
//        			.access("@customSecurity.checkPostAuthor(#id, authentication.name) or hasRole('MODERATOR')")
//        		.anyRequest()
//        			.authenticated()
//        );
//		
		return http.build();
	}
}
