package service;

import model.Patient;
import util.DataStore;
import java.util.ArrayList;
import java.util.List;

public class PatientService {
    private DataStore dataStore;

    public PatientService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public String addPatient(String name, int age, String gender, String contact, String address, String medicalHistory) {
        String id = dataStore.idGenerator.getNextId("PAT");
        Patient patient = new Patient(id, name, age, gender, contact, address, medicalHistory);
        dataStore.patients.put(id, patient);
        return id;
    }

    public Patient getPatient(String id) {
        return dataStore.patients.get(id);
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(dataStore.patients.values());
    }

    public boolean updatePatient(String id, String name, int age, String gender, String contact, String address, String medicalHistory) {
        Patient p = getPatient(id);
        if (p != null) {
            if (!name.isEmpty()) p.setName(name);
            if (age > 0) p.setAge(age);
            if (!gender.isEmpty()) p.setGender(gender);
            if (!contact.isEmpty()) p.setContact(contact);
            if (!address.isEmpty()) p.setAddress(address);
            if (!medicalHistory.isEmpty()) p.setMedicalHistory(medicalHistory);
            return true;
        }
        return false;
    }

    public boolean deletePatient(String id) {
        if (dataStore.patients.containsKey(id)) {
            dataStore.patients.remove(id);
            return true;
        }
        return false;
    }
}
