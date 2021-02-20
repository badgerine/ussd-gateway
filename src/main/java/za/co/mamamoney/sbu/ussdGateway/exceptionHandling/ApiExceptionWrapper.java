package za.co.mamamoney.sbu.ussdGateway.exceptionHandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiExceptionWrapper {
    @NonNull
    private HttpStatus status;
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    @NonNull
    private String message;

}
