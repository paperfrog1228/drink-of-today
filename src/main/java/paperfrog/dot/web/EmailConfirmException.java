package paperfrog.dot.web;

public class EmailConfirmException extends RuntimeException {
    public EmailConfirmException(String message) {
        super(message);
    }
    public EmailConfirmException() {
        super();
    }
}
