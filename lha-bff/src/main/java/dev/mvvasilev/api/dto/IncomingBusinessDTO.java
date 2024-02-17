package dev.mvvasilev.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IncomingBusinessDTO (
        @JsonProperty("displayed_what")
        String displayedWhat,
        @JsonProperty("displayed_where")
        String displayedWhere,
        @JsonProperty("opening_hours")
        IncomingOpeningHoursDTO openingHours

) {}
