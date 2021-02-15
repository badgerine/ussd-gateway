package za.co.mamamoney.sbu.ussdGateway.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEntryException extends RuntimeException {
    private String message;

    public InvalidEntryException(){
        super("Invalid User Entry");
    }

    public InvalidEntryException(String message) {
        this();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
