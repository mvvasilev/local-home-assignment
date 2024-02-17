package dev.mvvasilev.api.dto;

import dev.mvvasilev.api.enums.OpenHoursType;
import dev.mvvasilev.api.enums.Weekday;

import java.util.List;
import java.util.Map;

public record IncomingOpeningHoursDTO (
        boolean closedOnHolidays,
        boolean openByArrangement,
        Map<Weekday, List<IncomingOpenHoursDTO>> days
) {
}
