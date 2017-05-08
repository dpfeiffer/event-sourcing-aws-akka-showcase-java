import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.typesafe.config.ConfigBeanFactory;
import eventpublisher.EventPublisher;
import notifications.NotificationsSender;
import offices.TimeEntryOffice;
import play.Configuration;
import play.libs.akka.AkkaGuiceSupport;
import readmodel.TimeEntriesReadModel;
import settings.Settings;

public class Module extends AbstractModule implements AkkaGuiceSupport {
    @Override
    protected void configure() {
        bindActor(TimeEntryOffice.class, "time-entry-office");
        bind(TimeEntriesReadModel.class).asEagerSingleton();
        bind(CustomObjectMapper.class).asEagerSingleton();
        bind(EventPublisher.class).asEagerSingleton();
        bind(NotificationsSender.class).asEagerSingleton();
    }

    @Provides
    public Settings provideSettings(Configuration config) {
        return ConfigBeanFactory.create(config.underlying().getConfig("showcase"), Settings.class);
    }
}
