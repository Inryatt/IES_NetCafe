package ua.ies.group3.netcafe.api.model;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @Lob // I hope to god this works!
//    @Column(name = "map", nullable = false)
//    private byte[] map;

    @Lob
    @Column(name = "map", nullable = false, length = 1000000)
    private String map;

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

    //    public byte[] getMap() {
//        return map;
//    }
//
//    public void setMap(byte[] map) {
//        this.map = map;
//    }
}
