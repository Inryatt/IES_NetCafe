package model;

public class Software {
    private long id;
    private String name;
    private SoftwareCategory category;

    public Software(long id, String name, SoftwareCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

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

    public SoftwareCategory getCategory() {
        return category;
    }

    public void setCategory(SoftwareCategory category) {
        this.category = category;
    }
}
