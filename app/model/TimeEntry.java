package model;

import org.joda.time.DateTime;

import java.util.Objects;
import java.util.UUID;

public class TimeEntry {
    private final UUID id;
    private final DateTime begin;
    private final DateTime end;
    private final UUID userId;
    private final TimeEntryStatus status;

    public TimeEntry(UUID id, DateTime begin, DateTime end, UUID userId, TimeEntryStatus status) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.userId = userId;
        this.status = status;
    }

    private TimeEntry(Builder builder) {
        id = builder.id;
        begin = builder.begin;
        end = builder.end;
        userId = builder.userId;
        status = builder.status;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(TimeEntry copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.begin = copy.begin;
        builder.end = copy.end;
        builder.userId = copy.userId;
        builder.status = copy.status;
        return builder;
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

    public TimeEntryStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return Objects.equals(id, timeEntry.id) &&
            Objects.equals(begin, timeEntry.begin) &&
            Objects.equals(end, timeEntry.end) &&
            Objects.equals(userId, timeEntry.userId) &&
            status == timeEntry.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, begin, end, userId, status);
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
            "id=" + id +
            ", begin=" + begin +
            ", end=" + end +
            ", userId=" + userId +
            ", status=" + status +
            '}';
    }


    public static final class Builder {
        private UUID id;
        private DateTime begin;
        private DateTime end;
        private UUID userId;
        private TimeEntryStatus status;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder begin(DateTime val) {
            begin = val;
            return this;
        }

        public Builder end(DateTime val) {
            end = val;
            return this;
        }

        public Builder userId(UUID val) {
            userId = val;
            return this;
        }

        public Builder status(TimeEntryStatus val) {
            status = val;
            return this;
        }

        public TimeEntry build() {
            return new TimeEntry(this);
        }
    }
}
