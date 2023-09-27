package ru.example.geoservice.service;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.geoservice.data.GeoRepository;
import ru.example.geoservice.data.entity.GeoEntity;
import ru.example.geoservice.domain.GeoJson;
import ru.example.geoservice.domain.UpdateGeoRequest;

import java.util.List;
import java.util.UUID;

@Service
public class GeoService {

    private final GeoRepository geoRepository;

    @Autowired
    public GeoService(GeoRepository geoRepository) {
        this.geoRepository = geoRepository;
    }

    public List<GeoJson> getAllGeo() {
        return IteratorUtils.toList(geoRepository.findAll().iterator())
                .stream()
                .map(GeoJson::fromEntity)
                .toList();
    }

    public GeoJson addGeo(GeoJson geoJson) {
        GeoEntity entity = GeoEntity.fromJson(geoJson);
        geoRepository.save(entity);
        return geoJson;
    }

    public GeoJson updateGeo(GeoEntity entity, UpdateGeoRequest updateGeoRequest) {
        if (updateGeoRequest.getCountryName() != null) {
            entity.setCountryName(updateGeoRequest.getCountryName());
        }
        if (updateGeoRequest.getCountryCode() != null) {
            entity.setCountryCode(updateGeoRequest.getCountryCode());
        }
        geoRepository.save(entity);
        return GeoJson.fromEntity(entity);
    }

    public GeoEntity getGeoById(UUID id) {
        return geoRepository.findById(id).orElse(null);
    }

}
