package za.co.mamamoney.sbu.ussdGateway.exceptionHandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiExceptionWrapper {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String detail;

    private ApiExceptionWrapper(){
        this.timestamp = LocalDateTime.now();
    }

    ApiExceptionWrapper(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.detail = ex.getCause().getLocalizedMessage();
    }

    public ApiExceptionWrapper(HttpStatus status, String message, String reference) {
        this();
        this.status = status;
        this.message = message;
        this.detail = reference;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
