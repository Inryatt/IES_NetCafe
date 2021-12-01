package model;

import java.time.LocalDate;
import java.util.Date;

public class User {
    private long id;
    private String email;
    private String name;
    private LocalDate birthdate;

    public User(long id, String email, String name, LocalDate birthdate) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
