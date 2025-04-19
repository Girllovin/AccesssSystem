package smallITgroup.client.dao.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// The @ResponseStatus annotation is used to map this exception to a specific HTTP status (BAD_REQUEST in this case)
// This means that when this exception is thrown, the API will return a 400 status code.
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NameNotCorrectExeption extends RuntimeException {

    // Serial version UID for ensuring compatibility during serialization
    private static final long serialVersionUID = 5080455837442128177L;

}
