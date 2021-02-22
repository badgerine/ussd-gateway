package za.co.mamamoney.sbu.ussdGateway.exceptionHandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(InvalidSessionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ApiExceptionWrapper>
    handleInvalidSessionException(InvalidSessionException e) {
        LOGGER.error(String.format("Invalid Session ID passed | %s", e.getMessage()));
        return new ResponseEntity<>(
                ApiExceptionWrapper.builder().status(HttpStatus.BAD_REQUEST).timestamp(LocalDateTime.now()).message(e.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEntryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ApiExceptionWrapper>
    handleInvalidEntryException(InvalidEntryException e) {
        LOGGER.error(String.format("Invalid user entry passed | %s", e.getMessage()));
        return new ResponseEntity<>(
                ApiExceptionWrapper.builder().status(HttpStatus.BAD_REQUEST).timestamp(LocalDateTime.now()).message(e.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionWrapper> handleException(Exception e) {
        String reference = UUID.randomUUID().toString();
        LOGGER.error(String.format("Unexpected exception= %s | %s | reference=%s", e.getMessage(), e.getCause().getLocalizedMessage(), reference), e.getCause());
        return new ResponseEntity<>(
                ApiExceptionWrapper.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).timestamp(LocalDateTime.now())
                        .message(String.format("Unexpected Exception. Reference=%s", reference)).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
