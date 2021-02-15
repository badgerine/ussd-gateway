package za.co.mamamoney.sbu.ussdGateway.exceptionHandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ControllerAdvice
public class ApiExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value={InvalidSessionException.class})
    public ResponseEntity<ApiExceptionWrapper> handleInvalidSessionException(InvalidSessionException e){
        LOGGER.error(String.format("Invalid Session ID passed | %s", e.getMessage()));
        return new ResponseEntity<ApiExceptionWrapper>(new ApiExceptionWrapper(HttpStatus.BAD_REQUEST, e.getMessage(), e),HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value={InvalidEntryException.class})
    public ResponseEntity<ApiExceptionWrapper> handleInvalidEntryException(InvalidEntryException e){
        LOGGER.error(String.format("Invalid user entry passed | %s", e.getMessage()));
        return new ResponseEntity<>(new ApiExceptionWrapper(HttpStatus.BAD_REQUEST, e.getMessage(), e),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ApiExceptionWrapper> handleException(Exception e){
        String reference = UUID.randomUUID().toString();
        LOGGER.error(String.format("Unexpected exception= %s | %s | reference=%s", e.getMessage(), e.getCause().getLocalizedMessage(), reference),e.getCause());
        return new ResponseEntity<ApiExceptionWrapper>(new ApiExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Exception", String.format("Reference=%s",reference)),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
