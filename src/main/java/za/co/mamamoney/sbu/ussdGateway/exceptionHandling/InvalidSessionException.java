package za.co.mamamoney.sbu.ussdGateway.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSessionException extends RuntimeException {
    private String message;

    public InvalidSessionException(){
        super("Invalid Session ID");
    }
    public InvalidSessionException(String message) {
        this();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
