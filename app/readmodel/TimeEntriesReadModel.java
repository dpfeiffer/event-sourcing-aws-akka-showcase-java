package readmodel;


import aggregates.Event;
import aggregates.TimeEntryApproved;
import aggregates.TimeEntryCreated;
import aggregates.TimeEntryDeclined;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.OverflowStrategy;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.google.inject.Inject;
import model.TimeEntry;
import model.TimeEntryStatus;

import java.util.*;
import java.util.stream.Collectors;

public class TimeEntriesReadModel {

    private final Map<UUID, TimeEntry> database = new LinkedHashMap<>();

    @Inject
    public TimeEntriesReadModel(ActorSystem system) {

        final ActorRef ref = Source.actorRef(100, OverflowStrategy.dropNew()).to(Sink.foreach(o -> {
            if (o instanceof TimeEntryCreated) {
                created((TimeEntryCreated) o);
            } else if (o instanceof TimeEntryApproved) {
                approved((TimeEntryApproved) o);
            } else if (o instanceof TimeEntryDeclined) {
                declined((TimeEntryDeclined) o);
            }
        })).run(ActorMaterializer.create(system));

        system.eventStream().subscribe(ref, Event.class);
    }

    public void created(TimeEntryCreated event) {
        final TimeEntry entry = new TimeEntry(event.getId(), event.getBegin(), event.getEnd(), event.getUserId(), TimeEntryStatus.OPEN);
        database.put(event.getId(), entry);
    }

    public void approved(TimeEntryApproved event) {
        final TimeEntry existing = database.get(event.getId());
        final TimeEntry updated = TimeEntry.newBuilder(existing)
            .status(TimeEntryStatus.APPROVED)
            .build();
        database.put(event.getId(), updated);
    }

    public void declined(TimeEntryDeclined event) {
        final TimeEntry existing = database.get(event.getId());
        final TimeEntry updated = TimeEntry.newBuilder(existing)
            .status(TimeEntryStatus.DECLINED)
            .build();
        database.put(event.getId(), updated);
    }

    public List<TimeEntry> list() {
        return database.values()
            .stream()
            .sorted(Comparator.comparing(TimeEntry::getBegin))
            .collect(Collectors.toList());
    }

}
