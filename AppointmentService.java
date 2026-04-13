package service;

import model.Appointment;
import util.DataStore;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private DataStore dataStore;

    public AppointmentService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public String bookAppointment(String patientId, String doctorId, String date, String time) {
        if (!dataStore.patients.containsKey(patientId)) {
            System.out.println("Error: Patient ID not found.");
            return null;
        }
        if (!dataStore.doctors.containsKey(doctorId) || !dataStore.doctors.get(doctorId).isAvailable()) {
            System.out.println("Error: Doctor not found or unavailable.");
            return null;
        }

        String id = dataStore.idGenerator.getNextId("APT");
        Appointment appointment = new Appointment(id, patientId, doctorId, date, time, "Booked");
        dataStore.appointments.put(id, appointment);
        return id;
    }

    public boolean updateStatus(String appointmentId, String newStatus) {
        if (dataStore.appointments.containsKey(appointmentId)) {
            dataStore.appointments.get(appointmentId).setStatus(newStatus);
            return true;
        }
        return false;
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(dataStore.appointments.values());
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> list = new ArrayList<>();
        for (Appointment apt : dataStore.appointments.values()) {
            if (apt.getPatientId().equals(patientId)) {
                list.add(apt);
            }
        }
        return list;
    }
}
