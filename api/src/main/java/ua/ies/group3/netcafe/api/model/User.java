package ua.ies.group3.netcafe.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Schema(description = "Identifier")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Schema(description = "Real name")
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = "Email address")
    @Column(name = "email", nullable = false)
    private String email;

    @Schema(description = "Birthdate")
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Schema(description = "Registration date")
    @Column(name = "registerDate", nullable = false)
    private Date registerDate;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
