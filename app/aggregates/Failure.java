package aggregates;

import java.util.Objects;

public class Failure {

    private final String message;

    public Failure(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Failure failure = (Failure) o;
        return Objects.equals(message, failure.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "Failure{" +
            "message='" + message + '\'' +
            '}';
    }
}
