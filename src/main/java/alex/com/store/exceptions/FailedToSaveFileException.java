package alex.com.store.exceptions;

public class FailedToSaveFileException extends RuntimeException {
    public FailedToSaveFileException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}
