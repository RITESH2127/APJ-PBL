package model;

import java.io.Serializable;

/**
 * Patient class strictly encapsulates patient details.
 */
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private String address;
    private String medicalHistory;

    public Patient(String id, String name, int age, String gender, String contact, String address, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
        this.medicalHistory = medicalHistory;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }

    @Override
    public String toString() {
        return String.format("[%s] %s | %d yrs | %s | %s | %s | %s",
                id, name, age, gender, contact, address, medicalHistory);
    }
}
