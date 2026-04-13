package controller;

import model.*;
import service.*;
import util.DataStore;

import java.util.List;
import java.util.Scanner;

public class MainController {
    private AuthService authService;
    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;
    private ServiceTrackingService serviceTracking;
    private BillingService billingService;
    
    private Scanner scanner;
    private User loggedInUser;

    public MainController(DataStore dataStore) {
        this.authService = new AuthService(dataStore);
        this.patientService = new PatientService(dataStore);
        this.doctorService = new DoctorService(dataStore);
        this.appointmentService = new AppointmentService(dataStore);
        this.serviceTracking = new ServiceTrackingService(dataStore);
        this.billingService = new BillingService(dataStore, serviceTracking);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("==================================================");
        System.out.println(" Welcome to Hospital Management System ");
        System.out.println("==================================================");

        while (loggedInUser == null) {
            handleLogin();
        }

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: managePatients(); break;
                case 2: manageDoctors(); break;
                case 3: manageAppointments(); break;
                case 4: manageServices(); break;
                case 5: manageBilling(); break;
                case 6: 
                    loggedInUser = null; 
                    System.out.println("Logged out successfully.");
                    while (loggedInUser == null) {
                        handleLogin();
                    }
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting System... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleLogin() {
        System.out.println("\n--- User Login ---");
        System.out.print("Username (hint: admin): ");
        String username = scanner.nextLine().trim();
        System.out.print("Password (hint: admin123): ");
        String password = scanner.nextLine().trim();

        loggedInUser = authService.login(username, password);
        if (loggedInUser == null) {
            System.out.println("Error: Invalid credentials. Try again.");
        } else {
            System.out.println("Login successful! Welcome, " + loggedInUser.getUsername());
        }
    }

    private void showMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Patient Management");
        System.out.println("2. Doctor Management");
        System.out.println("3. Appointment System");
        System.out.println("4. Service Tracking");
        System.out.println("5. Billing System");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
    }

    // ---------------------------------------------------------
    // PATIENT MANAGEMENT
    // ---------------------------------------------------------
    private void managePatients() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- Patient Management --");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. View Patient by ID");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Back to Main Menu");
            
            int choice = readInt("Select an option: ");
            switch (choice) {
                case 1:
                    System.out.print("Name: "); String name = scanner.nextLine();
                    int age = readInt("Age: ");
                    System.out.print("Gender: "); String gender = scanner.nextLine();
                    System.out.print("Contact: "); String contact = scanner.nextLine();
                    System.out.print("Address: "); String address = scanner.nextLine();
                    System.out.print("Medical History: "); String history = scanner.nextLine();
                    String id = patientService.addPatient(name, age, gender, contact, address, history);
                    System.out.println("Patient added successfully. ID: " + id);
                    break;
                case 2:
                    System.out.println("--- All Patients ---");
                    for (Patient p : patientService.getAllPatients()) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    System.out.print("Enter Patient ID: "); String sId = scanner.nextLine();
                    Patient p = patientService.getPatient(sId);
                    if (p != null) System.out.println(p);
                    else System.out.println("Patient not found.");
                    break;
                case 4:
                    System.out.print("Enter Patient ID to Update: "); String uId = scanner.nextLine();
                    if (patientService.getPatient(uId) == null) {
                        System.out.println("Patient not found."); break;
                    }
                    System.out.println("Enter new details (leave blank to keep current):");
                    System.out.print("Name: "); String uName = scanner.nextLine();
                    System.out.print("Age (enter 0 to skip): "); 
                    String ageInput = scanner.nextLine();
                    int uAge = ageInput.isEmpty() ? 0 : Integer.parseInt(ageInput);
                    System.out.print("Gender: "); String uGender = scanner.nextLine();
                    System.out.print("Contact: "); String uContact = scanner.nextLine();
                    System.out.print("Address: "); String uAddress = scanner.nextLine();
                    System.out.print("Medical History: "); String uHistory = scanner.nextLine();
                    
                    if (patientService.updatePatient(uId, uName, uAge, uGender, uContact, uAddress, uHistory)) {
                        System.out.println("Patient updated successfully.");
                    } else {
                        System.out.println("Update failed.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Patient ID to Delete: "); String dId = scanner.nextLine();
                    if (patientService.deletePatient(dId)) {
                        System.out.println("Patient deleted successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 6: back = true; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // ---------------------------------------------------------
    // DOCTOR MANAGEMENT
    // ---------------------------------------------------------
    private void manageDoctors() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- Doctor Management --");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Update Availability");
            System.out.println("4. Back to Main Menu");
            
            int choice = readInt("Select an option: ");
            switch (choice) {
                case 1:
                    System.out.print("Name: "); String name = scanner.nextLine();
                    System.out.print("Specialization: "); String spec = scanner.nextLine();
                    String id = doctorService.addDoctor(name, spec);
                    System.out.println("Doctor added successfully. ID: " + id);
                    break;
                case 2:
                    System.out.println("--- All Doctors ---");
                    for (Doctor d : doctorService.getAllDoctors()) {
                        System.out.println(d);
                    }
                    break;
                case 3:
                    System.out.print("Enter Doctor ID: "); String dId = scanner.nextLine();
                    System.out.print("Is Available? (true/false): "); boolean avail = Boolean.parseBoolean(scanner.nextLine());
                    if (doctorService.updateAvailability(dId, avail)) {
                        System.out.println("Availability updated.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 4: back = true; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // ---------------------------------------------------------
    // APPOINTMENT SYSTEM
    // ---------------------------------------------------------
    private void manageAppointments() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- Appointment System --");
            System.out.println("1. Book Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. View Patient's Appointments");
            System.out.println("4. Update Appointment Status");
            System.out.println("5. Back");
            
            int choice = readInt("Select an option: ");
            switch (choice) {
                case 1:
                    System.out.print("Patient ID: "); String pId = scanner.nextLine();
                    System.out.print("Doctor ID: "); String dId = scanner.nextLine();
                    System.out.print("Date (YYYY-MM-DD): "); String date = scanner.nextLine();
                    System.out.print("Time (HH:MM): "); String time = scanner.nextLine();
                    String aptId = appointmentService.bookAppointment(pId, dId, date, time);
                    if (aptId != null) {
                        System.out.println("Appointment booked. ID: " + aptId);
                    }
                    break;
                case 2:
                    System.out.println("--- All Appointments ---");
                    for (Appointment a : appointmentService.getAllAppointments()) {
                        System.out.println(a);
                    }
                    break;
                case 3:
                    System.out.print("Enter Patient ID: "); String spId = scanner.nextLine();
                    List<Appointment> apts = appointmentService.getAppointmentsByPatient(spId);
                    for (Appointment a : apts) System.out.println(a);
                    if (apts.isEmpty()) System.out.println("No appointments found.");
                    break;
                case 4:
                    System.out.print("Appointment ID: "); String aId = scanner.nextLine();
                    System.out.print("New Status (Booked/Completed/Cancelled): "); String status = scanner.nextLine();
                    if (appointmentService.updateStatus(aId, status)) {
                        System.out.println("Status updated.");
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 5: back = true; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // ---------------------------------------------------------
    // SERVICE TRACKING
    // ---------------------------------------------------------
    private void manageServices() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- Service Tracking --");
            System.out.println("1. Add Service (Lab Test, X-Ray, etc.)");
            System.out.println("2. View Services by Patient");
            System.out.println("3. Update Service Status");
            System.out.println("4. Back");
            
            int choice = readInt("Select an option: ");
            switch (choice) {
                case 1:
                    System.out.print("Patient ID: "); String pId = scanner.nextLine();
                    System.out.print("Service Name: "); String name = scanner.nextLine();
                    double cost = readDouble("Cost: ");
                    String sId = serviceTracking.addService(pId, name, cost);
                    if (sId != null) {
                        System.out.println("Service added. ID: " + sId);
                    }
                    break;
                case 2:
                    System.out.print("Patient ID: "); String spId = scanner.nextLine();
                    List<Service> srvs = serviceTracking.getServicesByPatient(spId);
                    for (Service s : srvs) System.out.println(s);
                    if (srvs.isEmpty()) System.out.println("No services found.");
                    break;
                case 3:
                    System.out.print("Service ID: "); String uId = scanner.nextLine();
                    System.out.print("New Status (Pending/Completed): "); String status = scanner.nextLine();
                    if (serviceTracking.updateServiceStatus(uId, status)) {
                        System.out.println("Service status updated.");
                    } else {
                        System.out.println("Service not found.");
                    }
                    break;
                case 4: back = true; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // ---------------------------------------------------------
    // BILLING SYSTEM
    // ---------------------------------------------------------
    private void manageBilling() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- Billing System --");
            System.out.println("1. Generate Bill for Patient");
            System.out.println("2. Pay Bill");
            System.out.println("3. View All Bills");
            System.out.println("4. Back");
            
            int choice = readInt("Select an option: ");
            switch (choice) {
                case 1:
                    System.out.print("Patient ID: "); String pId = scanner.nextLine();
                    Billing bill = billingService.generateBill(pId);
                    if (bill != null) {
                        System.out.println("Bill generated successfully.");
                        System.out.println(bill);
                    }
                    break;
                case 2:
                    System.out.print("Bill ID to pay: "); String bId = scanner.nextLine();
                    if (billingService.payBill(bId)) {
                        System.out.println("Bill marked as Paid.");
                    } else {
                        System.out.println("Bill not found.");
                    }
                    break;
                case 3:
                    System.out.println("--- All Bills ---");
                    for (Billing b : billingService.getAllBills()) {
                        System.out.println(b);
                    }
                    break;
                case 4: back = true; break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // Helper methods for robust input
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = Double.parseDouble(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }
}
