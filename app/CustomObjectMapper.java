import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import play.libs.Json;

public class CustomObjectMapper {

    public CustomObjectMapper(){
        final ObjectMapper mapper = Json.newDefaultMapper()
            .registerModule(new JodaModule());
        Json.setObjectMapper(mapper);
    }
}
