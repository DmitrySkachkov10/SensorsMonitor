package by.dmitryskachkov.exception.exceptions;

import org.springframework.http.HttpStatusCode;

public class ValidationException extends RuntimeException {

    private HttpStatusCode httpStatusCode;

    public ValidationException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public ValidationException(String message) {
        super(message);
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
