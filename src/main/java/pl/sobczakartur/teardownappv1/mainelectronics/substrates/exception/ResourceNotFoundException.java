package pl.sobczakartur.teardownappv1.mainelectronics.substrates.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}