package aggregates;

import org.joda.time.DateTime;

import java.util.Objects;
import java.util.UUID;

public class TimeEntryDeclined implements Event{
    private final UUID id;
    private final DateTime dateTime;

    public TimeEntryDeclined(UUID id, DateTime dateTime) {
        this.id = id;
        this.dateTime = dateTime;
    }

    public UUID getId() {
        return id;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String getEventType() {
        return "timeentry.declined";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntryDeclined that = (TimeEntryDeclined) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime);
    }

    @Override
    public String toString() {
        return "TimeEntryDeclined{" +
            "id=" + id +
            ", dateTime=" + dateTime +
            '}';
    }
}
