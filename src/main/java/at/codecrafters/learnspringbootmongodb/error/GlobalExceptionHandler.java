package at.codecrafters.learnspringbootmongodb.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<CustomNotFoundException> handleCustomNotFoundException(final CustomNotFoundException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e);
    }
}
