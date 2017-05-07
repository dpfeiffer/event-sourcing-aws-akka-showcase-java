package aggregates;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.UUID;

public interface Event extends Serializable {
    UUID getId();
    DateTime getDateTime();
    String getEventType();
}
