package dev.mvvasilev.api.configuration;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.mvvasilev.api.enums.Weekday;

import java.io.IOException;

public class WeekdayDeserializer extends StdDeserializer<Weekday> {
    protected WeekdayDeserializer() {
        super(Weekday.class);
    }

    @Override
    public Weekday deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        var value = deserializationContext.readValue(jsonParser, String.class);

        return switch (value) {
            case "monday" -> Weekday.MONDAY;
            case "tuesday" -> Weekday.TUESDAY;
            case "wednesday" -> Weekday.WEDNESDAY;
            case "thursday" -> Weekday.THURSDAY;
            case "friday" -> Weekday.FRIDAY;
            case "saturday" -> Weekday.SATURDAY;
            case "sunday" -> Weekday.SUNDAY;
            default -> throw deserializationContext.weirdStringException(value, Weekday.class, "Invalid weekday");
        };
    }
}
