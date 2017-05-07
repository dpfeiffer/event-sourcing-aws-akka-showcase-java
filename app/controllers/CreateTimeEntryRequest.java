package controllers;

import org.joda.time.DateTime;

import java.util.UUID;

public class CreateTimeEntryRequest {

    private DateTime begin;
    private DateTime end;
    private UUID userId;

    public CreateTimeEntryRequest() {
    }

    public CreateTimeEntryRequest(DateTime begin, DateTime end, UUID userId) {
        this.begin = begin;
        this.end = end;
        this.userId = userId;
    }

    public DateTime getBegin() {
        return begin;
    }

    public void setBegin(DateTime begin) {
        this.begin = begin;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
