package gui;

import model.*;
import service.*;
import util.DataStore;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {
    private DataStore dataStore;
    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;
    private ServiceTrackingService serviceTracking;
    private BillingService billingService;

    public DashboardFrame(DataStore dataStore) {
        this.dataStore = dataStore;
        this.patientService = new PatientService(dataStore);
        this.doctorService = new DoctorService(dataStore);
        this.appointmentService = new AppointmentService(dataStore);
        this.serviceTracking = new ServiceTrackingService(dataStore);
        this.billingService = new BillingService(dataStore, serviceTracking);

        setTitle("Hospital Management System - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Patients", createPatientPanel());
        tabbedPane.addTab("Doctors", createDoctorPanel());
        tabbedPane.addTab("Appointments", createAppointmentPanel());
        tabbedPane.addTab("Services", createServicePanel());
        tabbedPane.addTab("Billing", createBillingPanel());

        add(tabbedPane);
    }

    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Input Form
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Patient"));
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField historyField = new JTextField();

        formPanel.add(new JLabel("Name:")); formPanel.add(nameField);
        formPanel.add(new JLabel("Age:")); formPanel.add(ageField);
        formPanel.add(new JLabel("Gender:")); formPanel.add(genderField);
        formPanel.add(new JLabel("Contact:")); formPanel.add(contactField);
        formPanel.add(new JLabel("Address:")); formPanel.add(addressField);
        formPanel.add(new JLabel("History:")); formPanel.add(historyField);

        // Display Area
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Patient");
        JButton viewButton = new JButton("View All Patients");

        addButton.addActionListener(e -> {
            try {
                String id = patientService.addPatient(nameField.getText(), Integer.parseInt(ageField.getText()), 
                            genderField.getText(), contactField.getText(), addressField.getText(), historyField.getText());
                JOptionPane.showMessageDialog(this, "Added successfully! ID: " + id);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error in input. Check age number.");
            }
        });

        viewButton.addActionListener(e -> {
            List<Patient> list = patientService.getAllPatients();
            StringBuilder sb = new StringBuilder();
            for (Patient p : list) sb.append(p.toString()).append("\n");
            displayArea.setText(sb.toString());
        });

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Doctor"));
        JTextField nameField = new JTextField();
        JTextField specField = new JTextField();

        formPanel.add(new JLabel("Name:")); formPanel.add(nameField);
        formPanel.add(new JLabel("Specialization:")); formPanel.add(specField);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        JButton addButton = new JButton("Add Doctor");
        JButton viewButton = new JButton("View All Doctors");

        addButton.addActionListener(e -> {
            String id = doctorService.addDoctor(nameField.getText(), specField.getText());
            JOptionPane.showMessageDialog(this, "Added successfully! ID: " + id);
        });

        viewButton.addActionListener(e -> {
            List<Doctor> list = doctorService.getAllDoctors();
            StringBuilder sb = new StringBuilder();
            for (Doctor d : list) sb.append(d.toString()).append("\n");
            displayArea.setText(sb.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Book Appointment"));
        JTextField patField = new JTextField();
        JTextField docField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();

        formPanel.add(new JLabel("Patient ID:")); formPanel.add(patField);
        formPanel.add(new JLabel("Doctor ID:")); formPanel.add(docField);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):")); formPanel.add(dateField);
        formPanel.add(new JLabel("Time:")); formPanel.add(timeField);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        JButton addButton = new JButton("Book Appointment");
        JButton viewButton = new JButton("View Appointments");

        addButton.addActionListener(e -> {
            String id = appointmentService.bookAppointment(patField.getText(), docField.getText(), dateField.getText(), timeField.getText());
            if (id != null) JOptionPane.showMessageDialog(this, "Booked! ID: " + id);
            else JOptionPane.showMessageDialog(this, "Error booking appointment.");
        });

        viewButton.addActionListener(e -> {
            List<Appointment> list = appointmentService.getAllAppointments();
            StringBuilder sb = new StringBuilder();
            for (Appointment a : list) sb.append(a.toString()).append("\n");
            displayArea.setText(sb.toString());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createServicePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Service for Patient"));
        JTextField patField = new JTextField();
        JTextField srvField = new JTextField();
        JTextField costField = new JTextField();

        formPanel.add(new JLabel("Patient ID:")); formPanel.add(patField);
        formPanel.add(new JLabel("Service Name:")); formPanel.add(srvField);
        formPanel.add(new JLabel("Cost:")); formPanel.add(costField);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        JButton addButton = new JButton("Request Service");
        JButton completeButton = new JButton("Mark Service Completed");

        addButton.addActionListener(e -> {
            try {
                String id = serviceTracking.addService(patField.getText(), srvField.getText(), Double.parseDouble(costField.getText()));
                if (id != null) JOptionPane.showMessageDialog(this, "Service Requested! ID: " + id);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Cost");
            }
        });

        completeButton.addActionListener(e -> {
            String sId = JOptionPane.showInputDialog("Enter Service ID to mark as Completed:");
            if (sId != null && serviceTracking.updateServiceStatus(sId, "Completed")) {
                JOptionPane.showMessageDialog(this, "Status updated to Completed.");
            } else {
                JOptionPane.showMessageDialog(this, "Service not found.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createBillingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        JTextField patField = new JTextField(15);
        JButton genButton = new JButton("Generate Bill");
        JButton payButton = new JButton("Pay Bill");
        
        topPanel.add(new JLabel("Patient ID:"));
        topPanel.add(patField);
        topPanel.add(genButton);
        topPanel.add(payButton);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        genButton.addActionListener(e -> {
            Billing b = billingService.generateBill(patField.getText());
            if (b != null) {
                displayArea.setText(b.toString() + "\nBill Generated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Patient not found.");
            }
        });

        payButton.addActionListener(e -> {
            String bId = JOptionPane.showInputDialog("Enter Bill ID to Pay:");
            if (bId != null && billingService.payBill(bId)) {
                JOptionPane.showMessageDialog(this, "Bill Paid!");
            } else {
                JOptionPane.showMessageDialog(this, "Bill not found.");
            }
        });

        JButton viewAll = new JButton("View All Bills");
        viewAll.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Billing b : billingService.getAllBills()) {
                sb.append(b.toString()).append("\n");
            }
            displayArea.setText(sb.toString());
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(viewAll);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }
}
