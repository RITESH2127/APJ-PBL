package model;

import java.io.Serializable;

/**
 * Service class tracks services given to a patient (e.g., Lab tests, X-Ray) for billing and tracking.
 */
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;

    private String serviceId;
    private String patientId;
    private String serviceName;
    private double cost;
    private String status; // Pending, Completed

    public Service(String serviceId, String patientId, String serviceName, double cost, String status) {
        this.serviceId = serviceId;
        this.patientId = patientId;
        this.serviceName = serviceName;
        this.cost = cost;
        this.status = status;
    }

    public String getServiceId() { return serviceId; }
    public void setServiceId(String serviceId) { this.serviceId = serviceId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("[%s] %s for Patient: %s | Cost: %.2f | Status: %s", 
                serviceId, serviceName, patientId, cost, status);
    }
}
