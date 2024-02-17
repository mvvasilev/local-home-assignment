package dev.mvvasilev.api.dto;

import java.util.List;

public record OutgoingBusinessDTO (
    String businessName,
    String businessAddress,
    List<OutgoingOpeningHoursDTO> openingHours,
    boolean closedOnHolidays,
    boolean openByArrangement
)
{}
