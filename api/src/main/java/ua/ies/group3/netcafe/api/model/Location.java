package ua.ies.group3.netcafe.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Schema(description = "Identifier")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Schema(description = "Name")
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = "Blueprint map (image stored as string)")
    @Lob
    @Column(name = "map", nullable = false, length = 1000000)
    private String map;

    @Schema(description = "Current location temperature (ºC)")
    @Column(name = "temperature")
    private Double temperature;

    @Schema(description = "Current location humidity (0 to 1)")
    @Column(name = "humidity")
    private Double humidity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
