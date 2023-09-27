package ru.example.geoservice.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.example.geoservice.domain.GeoJson;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "geo")
public class GeoEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "country_name", unique = true, nullable = false)
    private String countryName;

    @Column(name = "country_code", length = 2, unique = true, nullable = false)
    private String countryCode;

    @Column()
    @JdbcTypeCode(SqlTypes.JSON)
    private List<List<List<List<Double>>>> coordinates;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<List<List<List<Double>>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<List<Double>>>> coordinates) {
        this.coordinates = coordinates;
    }

    public static GeoEntity fromJson(GeoJson geoJson) {
        GeoEntity geoEntity = new GeoEntity();
        geoEntity.setCountryName(geoJson.countryName());
        geoEntity.setCountryCode(geoJson.countryCode());
        geoEntity.setCoordinates(geoJson.coordinates());
        return geoEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoEntity geoEntity = (GeoEntity) o;
        return Objects.equals(id, geoEntity.id) && Objects.equals(countryName, geoEntity.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryName);
    }

}
