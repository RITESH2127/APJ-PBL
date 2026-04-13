package model;

import java.io.Serializable;
import java.util.List;

/**
 * Billing represents the final bill for a patient.
 */
public class Billing implements Serializable {
    private static final long serialVersionUID = 1L;

    private String billingId;
    private String patientId;
    private List<String> serviceNames;
    private double totalAmount;
    private String paymentStatus; // Paid, Unpaid

    public Billing(String billingId, String patientId, List<String> serviceNames, double totalAmount, String paymentStatus) {
        this.billingId = billingId;
        this.patientId = patientId;
        this.serviceNames = serviceNames;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public String getBillingId() { return billingId; }
    public void setBillingId(String billingId) { this.billingId = billingId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public List<String> getServiceNames() { return serviceNames; }
    public void setServiceNames(List<String> serviceNames) { this.serviceNames = serviceNames; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    @Override
    public String toString() {
        return String.format("[%s] Patient: %s | Services: %s | Total: %.2f | Status: %s", 
                billingId, patientId, String.join(", ", serviceNames), totalAmount, paymentStatus);
    }
}
