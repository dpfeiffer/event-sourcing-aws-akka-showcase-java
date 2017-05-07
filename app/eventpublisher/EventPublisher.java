package eventpublisher;

import aggregates.Event;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.OverflowStrategy;
import akka.stream.alpakka.sns.javadsl.SnsPublisher;
import akka.stream.javadsl.Source;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.libs.Json;

public class EventPublisher {

    @Inject
    public EventPublisher(ActorSystem system) {

        final AmazonSNSAsync client = AmazonSNSAsyncClientBuilder.standard()
            .withRegion(Regions.EU_WEST_1)
            .build();

        final ActorRef ref = Source.<Event>actorRef(100, OverflowStrategy.dropNew())
            .map(events -> {
                final JsonNode json = Json.toJson(events);
                return Json.stringify(json);
            })
            .to(SnsPublisher.createSink("arn:aws:sns:eu-west-1:929580149227:event-sourcing-aws-akka-showcase", client))
            .run(ActorMaterializer.create(system));

        system.eventStream().subscribe(ref, Event.class);

    }


}
