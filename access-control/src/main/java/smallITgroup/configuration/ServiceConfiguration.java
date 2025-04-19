package smallITgroup.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Marks this class as a configuration class for Spring
@EnableScheduling // Enables Spring's task scheduling, allowing scheduled tasks to be run
public class ServiceConfiguration {

    // Bean definition for ModelMapper to handle object-to-object mapping
    @Bean
    ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper(); // Creating a new ModelMapper instance
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) // Enables field matching by name and type
                .setFieldAccessLevel(AccessLevel.PRIVATE) // Allows private fields to be accessed
                .setMatchingStrategy(MatchingStrategies.STRICT); // Strict matching between source and destination properties
        return modelMapper; // Return the configured ModelMapper instance
    }

    // Bean definition for BCryptPasswordEncoder, which is used to encode passwords
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(); // Return a new BCryptPasswordEncoder instance
    }
}
