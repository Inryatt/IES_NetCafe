package ua.ies.group3.netcafe.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "softwares")
public class Software {
    @Schema(description = "Identifier")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Schema(description = "Name")
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = "Type")
    @Column(name = "type", nullable = false)
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
