package tech.code.challenge.project.exception;

public class BaseException extends Exception {
    private final String error;
    public BaseException(String error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error;
    }
}
