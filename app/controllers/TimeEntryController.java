package controllers;

import aggregates.*;
import akka.actor.ActorRef;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import readmodel.TimeEntriesReadModel;

import javax.inject.Named;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import static akka.pattern.PatternsCS.ask;

public class TimeEntryController extends Controller {

    private final ActorRef timeEntryOffice;
    private TimeEntriesReadModel readModel;

    @Inject
    public TimeEntryController(@Named("time-entry-office") ActorRef timeEntryOffice, TimeEntriesReadModel readModel) {
        this.timeEntryOffice = timeEntryOffice;
        this.readModel = readModel;
    }

    public Result list() {
        return ok(Json.toJson(readModel.list()));
    }

    public CompletionStage<Result> create() {
        final JsonNode json = request().body().asJson();
        final CreateTimeEntryRequest request = Json.fromJson(json, CreateTimeEntryRequest.class);
        final CreateTimeEntry command = new CreateTimeEntry(UUID.randomUUID(), request.getBegin(), request.getEnd(), request.getUserId());
        final CompletionStage<Object> future = ask(timeEntryOffice, command, 10000);

        return future.thenApply(this::mapToResult);
    }

    public CompletionStage<Result> approve(UUID id) {
        final ApproveTimeEntry command = new ApproveTimeEntry(id);
        final CompletionStage<Object> future = ask(timeEntryOffice, command, 10000);
        return future.thenApply(this::mapToResult);
    }

    public CompletionStage<Result> decline(UUID id) {
        final DeclineTimeEntry command = new DeclineTimeEntry(id);
        final CompletionStage<Object> future = ask(timeEntryOffice, command, 10000);
        return future.thenApply(this::mapToResult);
    }

    private Result mapToResult(Object o) {
        if (o instanceof Success) {
            return ok();
        } else if (o instanceof Failure) {
            return badRequest();
        } else {
            return internalServerError();
        }
    }

}
