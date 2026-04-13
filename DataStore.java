package util;

import model.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Central runtime data store. Holds all collections.
 */
public class DataStore implements Serializable {
    private static final long serialVersionUID = 1L;

    public Map<String, User> users = new HashMap<>();
    public Map<String, Patient> patients = new HashMap<>();
    public Map<String, Doctor> doctors = new HashMap<>();
    public Map<String, Appointment> appointments = new HashMap<>();
    public Map<String, Billing> billings = new HashMap<>();
    public Map<String, Service> services = new HashMap<>();
    public IdGenerator idGenerator = new IdGenerator();

    public DataStore() {
        // Initialize default admin user if not tracking separately
        users.put("admin", new User("admin", "admin123", "Admin"));
    }
}
