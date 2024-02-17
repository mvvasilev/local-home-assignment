package dev.mvvasilev.api.dto;

import dev.mvvasilev.api.enums.OpenHoursType;

import java.util.Objects;

public record IncomingOpenHoursDTO(
        String start,
        String end,
        OpenHoursType type
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingOpenHoursDTO that = (IncomingOpenHoursDTO) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, type);
    }
}
