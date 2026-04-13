package model;

import java.io.Serializable;

/**
 * Appointment class links patients with doctors for specific dates/times.
 */
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date;
    private String time;
    private String status; // Booked, Completed, Cancelled

    public Appointment(String appointmentId, String patientId, String doctorId, String date, String time, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("[%s] Patient: %s | Doctor: %s | Date: %s | Time: %s | Status: %s", 
                appointmentId, patientId, doctorId, date, time, status);
    }
}
