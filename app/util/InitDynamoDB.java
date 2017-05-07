package util;

import akka.persistence.dynamodb.journal.package$;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import java.util.List;

public class InitDynamoDB {

    public static void main(String[] args) {
        final AmazonDynamoDBAsync client = AmazonDynamoDBAsyncClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://dynamodb.eu-west-1.amazonaws.com", "eu-west-1"))
            .build();

        final CreateTableRequest request = package$.MODULE$.schema().withTableName("event-sourcing-aws-akka-showcase").withProvisionedThroughput(new ProvisionedThroughput(10000L, 10000L));

        final List<String> tables = client.listTables().getTableNames();
        final boolean alreadyExists = tables.contains("event-sourcing-aws-akka-showcase");

        if (!alreadyExists) {
            final CreateTableResult result = client.createTable(request);
            System.out.println(result);
        }

    }
}
