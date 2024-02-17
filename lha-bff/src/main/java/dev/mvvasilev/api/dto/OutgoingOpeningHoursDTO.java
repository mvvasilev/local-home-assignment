package dev.mvvasilev.api.dto;

import dev.mvvasilev.api.enums.OpenHoursType;

public record OutgoingOpeningHoursDTO (
        String name,
        String start,
        String end,
        OpenHoursType type
) {}
