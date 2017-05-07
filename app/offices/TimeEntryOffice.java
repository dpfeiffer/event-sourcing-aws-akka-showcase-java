package offices;

import aggregates.*;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;
import static akka.pattern.PatternsCS.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class TimeEntryOffice extends AbstractActor {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);
    private final Map<UUID, ActorRef> aggregates = new HashMap<>();

    public TimeEntryOffice() {
        final PartialFunction<Object, BoxedUnit> behavior = ReceiveBuilder.create()
            .match(Command.class, this::forwardCommand)
            .build();
        receive(behavior);
    }

    private void forwardCommand(Command c){
        final ActorRef aggregate = aggregate(c.getId());
        final CompletableFuture<Object> future = ask(aggregate, c, 9000).toCompletableFuture();
        future.thenAccept(o -> {
            if(o instanceof Success){
                final Event event = ((Success) o).getEvent();
                context().system().eventStream().publish(event);
                logger.info("Command {} processed successfully returning event {}", c, event);
            }else if (o instanceof Failure){
                logger.info("Command {} processing failed because {}", c, ((Failure) o).getMessage());
            }else{
                logger.error("Unexpected response. {}", o);
            }
        });
        pipe(future, context().dispatcher()).to(sender());
    }

    private ActorRef aggregate(UUID id){
        if(aggregates.containsKey(id)){
            return aggregates.get(id);
        }else{
            return createAggregate(id);
        }
    }

    private ActorRef createAggregate(UUID id){
        final ActorRef actor = context().actorOf(TimeEntryAggregate.props(id));
        aggregates.put(id, actor);
        return actor;
    }
}
