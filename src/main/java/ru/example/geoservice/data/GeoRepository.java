package ru.example.geoservice.data;

import org.springframework.data.repository.CrudRepository;
import ru.example.geoservice.data.entity.GeoEntity;

import java.util.UUID;

public interface GeoRepository extends CrudRepository<GeoEntity, UUID> {}
