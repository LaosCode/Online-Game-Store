package alex.com.store.exceptions;

public class FailedToDeleteFileException extends RuntimeException {
    public FailedToDeleteFileException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}
