package service;

import model.Doctor;
import util.DataStore;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private DataStore dataStore;

    public DoctorService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public String addDoctor(String name, String specialization) {
        String id = dataStore.idGenerator.getNextId("DOC");
        Doctor doctor = new Doctor(id, name, specialization, true);
        dataStore.doctors.put(id, doctor);
        return id;
    }

    public Doctor getDoctor(String id) {
        return dataStore.doctors.get(id);
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(dataStore.doctors.values());
    }

    public boolean updateAvailability(String id, boolean isAvailable) {
        Doctor d = getDoctor(id);
        if (d != null) {
            d.setAvailable(isAvailable);
            return true;
        }
        return false;
    }
}
