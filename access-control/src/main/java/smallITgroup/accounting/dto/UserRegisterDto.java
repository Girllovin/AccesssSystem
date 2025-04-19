package smallITgroup.accounting.dto;

import lombok.Getter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// @Getter annotation automatically generates getter methods for all fields in the class.
@Getter
public class UserRegisterDto {

    // @NotBlank ensures that the email field cannot be null or empty. If it is, a validation message will be shown.
    // @Email ensures that the email is in a valid email format.
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Unavailable email")
    String email;
    
    // @NotBlank ensures that the password field cannot be null or empty.
    // @Size ensures that the password is at least 6 characters long. If it's not, a validation message will be shown.
    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, message = "Password have to consist of 6 symbols minimum")
    String password;

}
