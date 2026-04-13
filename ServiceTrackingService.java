package service;

import model.Service;
import util.DataStore;
import java.util.ArrayList;
import java.util.List;

public class ServiceTrackingService {
    private DataStore dataStore;

    public ServiceTrackingService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public String addService(String patientId, String serviceName, double cost) {
        if (!dataStore.patients.containsKey(patientId)) {
            System.out.println("Error: Patient not found.");
            return null;
        }
        String id = dataStore.idGenerator.getNextId("SRV");
        Service service = new Service(id, patientId, serviceName, cost, "Pending");
        dataStore.services.put(id, service);
        return id;
    }

    public boolean updateServiceStatus(String serviceId, String status) {
        if (dataStore.services.containsKey(serviceId)) {
            dataStore.services.get(serviceId).setStatus(status);
            return true;
        }
        return false;
    }

    public List<Service> getServicesByPatient(String patientId) {
        List<Service> list = new ArrayList<>();
        for (Service s : dataStore.services.values()) {
            if (s.getPatientId().equals(patientId)) {
                list.add(s);
            }
        }
        return list;
    }
}
