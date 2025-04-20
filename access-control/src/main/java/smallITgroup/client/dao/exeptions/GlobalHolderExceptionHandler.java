package smallITgroup.client.dao.exeptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalHolderExceptionHandler {

    // CardHolder not found
    @ExceptionHandler(CardHolderNotFoundExeption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCardHolderNotFound(CardHolderNotFoundExeption ex) {
        log.warn("CardHolder not found: {}", ex.getMessage());
        return new ErrorResponse("Card holder not found", ex.getMessage());
    }

    // Invalid name input
    @ExceptionHandler(NameNotCorrectExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNameError(NameNotCorrectExeption ex) {
        log.warn("Invalid name input: {}", ex.getMessage());
        return new ErrorResponse("Invalid name input", ex.getMessage());
    }

    //  Validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        log.warn("Validation failed: {}", errors);
        return new ErrorResponse("Validation error", errors.toString());
    }

    // Illegal argument/state errors
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalState(RuntimeException ex) {
        log.warn("Illegal argument/state: {}", ex.getMessage());
        return new ErrorResponse("Illegal request", ex.getMessage());
    }

    // Catch-all for unexpected errors
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleAllUncaught(RuntimeException ex) {
        log.error("Unexpected error occurred", ex);
        return new ResponseEntity<>(
                new ErrorResponse("Internal server error", "Something went wrong"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}