package model;

import java.io.Serializable;

/**
 * Doctor class encapsulates details about available doctors.
 */
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String specialization;
    private boolean isAvailable;

    public Doctor(String id, String name, String specialization, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.isAvailable = isAvailable;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return String.format("[%s] Dr. %s - %s [Available: %b]", 
                id, name, specialization, isAvailable);
    }
}
