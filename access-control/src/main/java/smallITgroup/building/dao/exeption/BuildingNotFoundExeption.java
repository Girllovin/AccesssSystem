package smallITgroup.building.dao.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// The @ResponseStatus annotation is used to indicate that this exception 
// should trigger an HTTP response with the specified status code (HttpStatus.NOT_FOUND).
// In this case, when this exception is thrown, a 404 NOT_FOUND status is returned.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BuildingNotFoundExeption extends RuntimeException {

    // serialVersionUID is a unique identifier for the Serializable class.
    // It's used during the deserialization process to ensure that a loaded class
    // matches the serialized object.
    private static final long serialVersionUID = 1L;

    // This custom exception extends RuntimeException, meaning it is an unchecked exception.
    // It can be thrown when a requested building is not found in the database.
    // No additional functionality is added to the RuntimeException, but you could
    // extend it with more specific error messages or properties if needed.
}