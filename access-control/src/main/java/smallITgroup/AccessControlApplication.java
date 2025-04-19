package smallITgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The @SpringBootApplication annotation is a convenience annotation that combines:
// - @Configuration: Marks the class as a source of bean definitions for the application context.
// - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration feature.
// - @ComponentScan: Tells Spring to scan the current package and its sub-packages for components (e.g., controllers, services).
@SpringBootApplication
public class AccessControlApplication {

    // The main method is the entry point for the Spring Boot application.
    // SpringApplication.run() boots up the application context and launches the embedded web server.
	public static void main(String[] args) {
		// Running the Spring Boot application
		SpringApplication.run(AccessControlApplication.class, args);
	}
}