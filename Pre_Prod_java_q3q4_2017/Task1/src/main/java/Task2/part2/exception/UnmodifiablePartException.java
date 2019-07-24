package Task2.part2.exception;

public class UnmodifiablePartException extends IllegalArgumentException {
    public UnmodifiablePartException(String message) {
        super(message);
    }
}
