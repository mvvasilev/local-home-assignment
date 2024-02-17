package dev.mvvasilev.api.controller;

import dev.mvvasilev.api.dto.OutgoingBusinessDTO;
import dev.mvvasilev.api.service.PlacesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessesController {

    private PlacesClient placesClient;

    @Autowired
    public BusinessesController(PlacesClient placesClient) {
        this.placesClient = placesClient;
    }

    @GetMapping
    public ResponseEntity<List<OutgoingBusinessDTO>> fetchBusinesses() {
        return ResponseEntity.ofNullable(placesClient.fetchBusinesses());
    }

}
