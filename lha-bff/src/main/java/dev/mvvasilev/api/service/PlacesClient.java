package dev.mvvasilev.api.service;

import dev.mvvasilev.api.dto.IncomingBusinessDTO;
import dev.mvvasilev.api.dto.IncomingOpenHoursDTO;
import dev.mvvasilev.api.dto.OutgoingBusinessDTO;
import dev.mvvasilev.api.dto.OutgoingOpeningHoursDTO;
import dev.mvvasilev.api.enums.Weekday;
import dev.mvvasilev.api.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class PlacesClient {

    private static String FETCH_BUSINESS_INFO_ENDPOINT = "/coding-session-rest-api/{id}";

    @Value("${remote.business-ids}")
    private String[] businessIds;

    final private RestTemplate googleApisTemplate;

    @Autowired
    public PlacesClient(RestTemplate googleApisTemplate) {
        this.googleApisTemplate = googleApisTemplate;
    }

    public List<OutgoingBusinessDTO> fetchBusinesses() {
        return Stream.of(businessIds).parallel()
                .map(id -> {
                    var result = googleApisTemplate.exchange(
                            FETCH_BUSINESS_INFO_ENDPOINT,
                            HttpMethod.GET,
                            null,
                            IncomingBusinessDTO.class,
                            Map.of("id", id)
                    );

                    if (!result.getStatusCode().is2xxSuccessful() || result.getBody() == null) {
                        throw new ApiException("Unable to fetch business information, remote request was unsuccessful or body was empty.");
                    }

                    return result.getBody();
                })
                .map(this::mapIncomingBusinessToOutgoingBusiness)
                .toList();
    }

    protected OutgoingBusinessDTO mapIncomingBusinessToOutgoingBusiness(IncomingBusinessDTO incoming) {
        var openHoursMap = new LinkedMultiValueMap<IncomingOpenHoursDTO, Weekday>();

        incoming.openingHours().days().forEach((key, value) -> value.forEach(ee -> {
            openHoursMap.add(ee, key);
        }));

        var openHours = openHoursMap.entrySet()
                .stream()
                .map(e -> {
                    e.getValue().sort(Comparator.comparingInt(Enum::ordinal));

                    var name = switch (e.getValue().size()) {
                        case 1 -> e.getValue().get(0).getName();
                        default -> e.getValue().get(0).getName() + "-" + e.getValue().get(e.getValue().size() - 1).getName();
                    };

                    return new OutgoingOpeningHoursDTO(
                            name,
                            e.getKey().start(),
                            e.getKey().end(),
                            e.getKey().type()
                    );
                })
                .toList();

        return new OutgoingBusinessDTO(
                incoming.displayedWhat(),
                incoming.displayedWhere(),
                openHours,
                incoming.openingHours().closedOnHolidays(),
                incoming.openingHours().openByArrangement()
        );
    }
}
