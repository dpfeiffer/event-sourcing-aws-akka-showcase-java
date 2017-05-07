package aggregates;

import org.joda.time.DateTime;

import java.util.UUID;

public class CreateTimeEntry implements Command{

    private final UUID id;
    private final DateTime begin;
    private final DateTime end;
    private final UUID userId;

    public CreateTimeEntry(UUID id, DateTime begin, DateTime end, UUID userId) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.userId = userId;
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
}
