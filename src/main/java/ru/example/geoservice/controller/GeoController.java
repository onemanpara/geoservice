package ru.example.geoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.geoservice.data.entity.GeoEntity;
import ru.example.geoservice.domain.GeoJson;
import ru.example.geoservice.domain.UpdateGeoRequest;
import ru.example.geoservice.service.GeoService;

import java.util.List;
import java.util.UUID;

@RestController
public class GeoController {

    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/geo")
    public List<GeoJson> getAllGeo() {
        return geoService.getAllGeo();
    }

    @GetMapping("/geo/{id}")
    public GeoJson getGeoById(@PathVariable UUID id) {
        return GeoJson.fromEntity(geoService.getGeoById(id));
    }

    @PostMapping("/add-geo")
    public GeoJson addGeo(@RequestBody GeoJson geoJson) {
        return geoService.addGeo(geoJson);
    }

    @PatchMapping("/update-geo/{id}")
    public GeoJson updateCountry(
            @PathVariable UUID id,
            @RequestBody UpdateGeoRequest updateGeoRequest
    ) {
        GeoEntity entity = geoService.getGeoById(id);
        if (entity != null) {
            return geoService.updateGeo(entity, updateGeoRequest);
        } else throw new IllegalArgumentException("Can't find country with id" + id);
    }

}
