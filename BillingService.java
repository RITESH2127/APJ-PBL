package service;

import model.Billing;
import model.Service;
import util.DataStore;
import java.util.ArrayList;
import java.util.List;

public class BillingService {
    private DataStore dataStore;
    private ServiceTrackingService serviceTracking;

    // Consultation fee constant
    private static final double CONSULTATION_FEE = 500.0;

    public BillingService(DataStore dataStore, ServiceTrackingService serviceTracking) {
        this.dataStore = dataStore;
        this.serviceTracking = serviceTracking;
    }

    public Billing generateBill(String patientId) {
        if (!dataStore.patients.containsKey(patientId)) {
            System.out.println("Error: Patient not found.");
            return null;
        }

        List<Service> patientServices = serviceTracking.getServicesByPatient(patientId);
        List<String> serviceNames = new ArrayList<>();
        double totalCost = CONSULTATION_FEE;
        
        serviceNames.add("Consultation");

        for (Service s : patientServices) {
            // Only bill completed services
            if (s.getStatus().equalsIgnoreCase("Completed")) {
                serviceNames.add(s.getServiceName());
                totalCost += s.getCost();
            }
        }

        String billId = dataStore.idGenerator.getNextId("BIL");
        Billing billing = new Billing(billId, patientId, serviceNames, totalCost, "Unpaid");
        dataStore.billings.put(billId, billing);
        return billing;
    }

    public boolean payBill(String billId) {
        if (dataStore.billings.containsKey(billId)) {
            dataStore.billings.get(billId).setPaymentStatus("Paid");
            return true;
        }
        return false;
    }

    public List<Billing> getAllBills() {
        return new ArrayList<>(dataStore.billings.values());
    }
}
