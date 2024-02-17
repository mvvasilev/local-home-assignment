package dev.mvvasilev.api.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.mvvasilev.api.configuration.WeekdayDeserializer;

@JsonDeserialize(using = WeekdayDeserializer.class)
public enum Weekday {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    final private String name;

    Weekday(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
