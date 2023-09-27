package ru.example.geoservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.example.geoservice.data.entity.GeoEntity;

import java.util.List;

public record GeoJson(@JsonProperty("country_name") String countryName,
                      @JsonProperty("country_code") String countryCode,
                      List<List<List<List<Double>>>> coordinates) {

    public static GeoJson fromEntity(GeoEntity entity) {
        return new GeoJson(entity.getCountryName(), entity.getCountryCode(), entity.getCoordinates());
    }

}