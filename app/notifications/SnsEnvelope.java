package notifications;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SnsEnvelope {

    private final String subject;
    private final String message;

    @JsonCreator
    public SnsEnvelope(@JsonProperty("Subject") String subject, @JsonProperty("Message") String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnsEnvelope that = (SnsEnvelope) o;
        return Objects.equals(subject, that.subject) &&
            Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, message);
    }

    @Override
    public String toString() {
        return "SnsEnvelope{" +
            "subject='" + subject + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
