package smallITgroup.client.dao.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// The @ResponseStatus annotation is used to mark the exception
// so that it triggers a specific HTTP status (NOT_FOUND in this case) 
// when this exception is thrown during a REST API call.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardHolderNotFoundExeption extends RuntimeException {

    // Serial version UID for serialization compatibility
    private static final long serialVersionUID = -433184779955377531L;

}
