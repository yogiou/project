package tech.code.challenge.project.exception;

public class TerminateException extends Exception {
    private final String error;
    public TerminateException(String error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error;
    }
}
