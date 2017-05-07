package aggregates;

import java.util.Objects;

public class Success {
    private final Event event;

    public Success(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Success success = (Success) o;
        return Objects.equals(event, success.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event);
    }

    @Override
    public String toString() {
        return "Success{" +
            "event=" + event +
            '}';
    }
}
