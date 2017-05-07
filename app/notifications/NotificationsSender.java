package notifications;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.alpakka.sqs.Ack;
import akka.stream.alpakka.sqs.MessageAction;
import akka.stream.alpakka.sqs.javadsl.SqsAckSink;
import akka.stream.alpakka.sqs.javadsl.SqsSource;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import scala.Tuple2;

public class NotificationsSender {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public NotificationsSender(ActorSystem system) {

        final AmazonSQSAsync client = AmazonSQSAsyncClientBuilder.defaultClient();
        SqsSource.create("https://sqs.eu-west-1.amazonaws.com/929580149227/event-sourcing-aws-akka-showcase", client)
            .map(this::handleEvent)
            .to(SqsAckSink.create("https://sqs.eu-west-1.amazonaws.com/929580149227/event-sourcing-aws-akka-showcase", client))
            .run(ActorMaterializer.create(system));

    }

    public Tuple2<Message, MessageAction> handleEvent(Message message){
        final SnsEnvelope envelope = Json.fromJson(Json.parse(message.getBody()), SnsEnvelope.class);
        final JsonNode json = Json.parse(envelope.getMessage());
        final String eventType = json.get("eventType").asText();
        if(eventType.equals("timeentry.created")){
            logger.info("sending timeentry.created notification");
        }else if(eventType.equals("timeentry.approved")){
            logger.info("sending timeentry.approved notification");
        }else if(eventType.equals("timeentry.declined")){
            logger.info("sending timeentry.declined notification");
        }
        return new Tuple2<Message, MessageAction>(message, new Ack());

    }
}
