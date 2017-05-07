package aggregates;

import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.persistence.AbstractPersistentActor;
import org.joda.time.DateTime;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

import java.util.UUID;

public class TimeEntryAggregate extends AbstractPersistentActor {

    private final UUID id;

    public TimeEntryAggregate(UUID id) {
        this.id = id;
    }

    @Override
    public String persistenceId() {
        return "time-entry-" + id;
    }

    @Override
    public PartialFunction<Object, BoxedUnit> receiveRecover() {
        return ReceiveBuilder.create().build();
    }

    @Override
    public PartialFunction<Object, BoxedUnit> receiveCommand() {
        return ReceiveBuilder.create()
            .match(CreateTimeEntry.class, this::create)
            .match(ApproveTimeEntry.class, this::approve)
            .match(DeclineTimeEntry.class, this::decline)
            .build();
    }

    private void create(CreateTimeEntry command) {
        final TimeEntryCreated event = new TimeEntryCreated(command.getId(), command.getBegin(), command.getEnd(), command.getUserId(), DateTime.now());
        persist(event, e -> {
            final Success result = new Success(e);
            sender().tell(result, self());
        });
    }

    private void approve(ApproveTimeEntry command) {
        final TimeEntryApproved event = new TimeEntryApproved(command.getId(), DateTime.now());
        persist(event, e -> {
            final Success result = new Success(e);
            sender().tell(result, self());
        });
    }

    private void decline(DeclineTimeEntry command) {
        final TimeEntryDeclined event = new TimeEntryDeclined(command.getId(), DateTime.now());
        persist(event, e -> {
            final Success result = new Success(e);
            sender().tell(result, self());
        });
    }

    public static Props props(UUID id) {
        return Props.create(TimeEntryAggregate.class, () -> new TimeEntryAggregate(id));
    }

}
