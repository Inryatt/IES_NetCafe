package ua.ies.group3.netcafe.api.model;

import javax.persistence.*;

@Entity
@Table(name = "machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Specs
    @Column(name = "cpu", nullable = false)
    private String cpu;

    @Column(name = "gpu", nullable = false)
    private String gpu;

    @Column(name = "ram", nullable = false)
    private String ram;

    @Column(name = "disk", nullable = false)
    private String disk;

    @Column(name = "os", nullable = false)
    private String os;

    @Column(name = "name", nullable = false)
    private String name;

    // Location
    @ManyToOne(optional = false)
    @JoinColumn(name = "locationId", nullable = false)
    private Location location;

    @Column(name = "xCoord", nullable = false)
    private double xCoord;

    @Column(name = "yCoord", nullable = false)
    private double yCoord;

    // Current Usage Information
    @OneToOne
    @JoinColumn(name = "currentUserId")
    private User currentUser;

    @Column(name = "timestamp")
    private long timestamp;


}
