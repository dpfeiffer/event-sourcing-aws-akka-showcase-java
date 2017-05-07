package aggregates;

import org.joda.time.DateTime;

import java.util.Objects;
import java.util.UUID;

public class TimeEntryCreated implements Event {

    private final UUID id;
    private final DateTime begin;
    private final DateTime end;
    private final UUID userId;
    private final DateTime dateTime;

    public TimeEntryCreated(UUID id, DateTime begin, DateTime end, UUID userId, DateTime dateTime) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.userId = userId;
        this.dateTime = dateTime;
    }

    public UUID getId() {
        return id;
    }

    public DateTime getBegin() {
        return begin;
    }

    public DateTime getEnd() {
        return end;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public DateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String getEventType() {
        return "timeentry.created";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntryCreated that = (TimeEntryCreated) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(begin, that.begin) &&
            Objects.equals(end, that.end) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, begin, end, userId, dateTime);
    }

    @Override
    public String toString() {
        return "TimeEntryCreated{" +
            "id=" + id +
            ", begin=" + begin +
            ", end=" + end +
            ", userId=" + userId +
            ", dateTime=" + dateTime +
            '}';
    }
}
