import com.google.inject.AbstractModule;
import eventpublisher.EventPublisher;
import notifications.NotificationsSender;
import offices.TimeEntryOffice;
import play.libs.akka.AkkaGuiceSupport;
import readmodel.TimeEntriesReadModel;

public class Module extends AbstractModule implements AkkaGuiceSupport {
    @Override
    protected void configure() {
        bindActor(TimeEntryOffice.class, "time-entry-office");
        bind(TimeEntriesReadModel.class).asEagerSingleton();
        bind(CustomObjectMapper.class).asEagerSingleton();
        bind(EventPublisher.class).asEagerSingleton();
        bind(NotificationsSender.class).asEagerSingleton();
    }
}
