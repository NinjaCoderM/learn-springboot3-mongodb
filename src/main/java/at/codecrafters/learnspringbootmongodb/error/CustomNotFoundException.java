package at.codecrafters.learnspringbootmongodb.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.NonNull;
import org.springframework.web.ErrorResponse;

public class CustomNotFoundException extends RuntimeException implements ErrorResponse {

    private final HttpStatus httpStatus;

    public CustomNotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    @Override
    @NonNull
    public HttpStatusCode getStatusCode() {
        return httpStatus;
    }

    @Override
    @NonNull
    public ProblemDetail getBody() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, getMessage());
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());
        problemDetail.setProperty("errorCode", "STUDENT_NOT_FOUND");
        return problemDetail;
    }
}
