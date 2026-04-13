# PROJECT REPORT
# Integrated Hospital Management System

**Course Code / Subject:** Object-Oriented Programming (Java) PBL  
**Academic Year:** 2025 - 2026

---

## 1. ABSTRACT
The healthcare sector handles vast amounts of sensitive records daily. Administrative inefficiencies in managing doctor availability, tracking lab tests, and calculating final invoices can bottleneck patient care. The **Integrated Hospital Management System (IHMS)** is a comprehensive desktop application developed using modern Java technologies. It replaces manual paperwork by providing a centralized platform. Powered by the Model-View-Controller (MVC) architecture, it offers strict data encapsulation, exception handling, and robust data persistence using Object Serialization mechanisms—packaged cleanly into both an efficient CLI and a rich Graphical User Interface (Java Swing).

## 2. PROBLEM STATEMENT
Traditional hospital management relies heavily on disconnected systems where patient records, appointment registries, and billing calculators exist in silos. This fragmentation frequently leads to data mismatches, lost service trails, and significant delays at the billing counters. An automated, unified architectural framework is required to trace a patient's lifecycle from the reception desk up until hospital discharge, mapping entities intelligently.

## 3. OBJECTIVES
1. To design a modular, highly cohesive, and loosely coupled Hospital architecture utilizing pure Object-Oriented Programming principles (Encapsulation, Polymorphism, Inheritance).
2. To provide an intuitive Graphical User Interface separating administrative logic from visual panels.
3. To execute seamless multi-entity tracking (Linking patients $\rightarrow$ doctors $\rightarrow$ appointments $\rightarrow$ services $\rightarrow$ billing).
4. To establish stable memory persistence guaranteeing information recovery without introducing bulky database engine dependencies.

## 4. SYSTEM ARCHITECTURE & DESIGN (MVC)
The project strictly observes the **MVC framework**.
- **Model:** Found within the `model/` package. Comprises Plain Old Java Objects (POJOs) matching real-world counterparts. Uses Encapsulation with private state attributes accessed heavily via standard Getters and Setters.
- **Controller/GUI (View):** The system orchestrates inputs through two paradigms: `controller/MainController.java` handles robust interactive terminal I/O streams, whereas `gui/` features `JFrames` and listeners mapping GUI interactions.
- **Service layer:** Rather than models bleeding logic, individual services (`BillingService`, `AppointmentService`) exist in the `service/` package acting as intermediaries, verifying rules before updating the `util.DataStore`.

### Tech Stack Utilized
- **Programming Language:** Java (JDK Core)
- **Frameworks:** JFC/Swing (`java.awt`, `javax.swing`)
- **Data Persistence Strategy:** `java.io.ObjectOutputStream`, `java.io.ObjectInputStream` (Binary Serialization `hospital_data.dat`).

## 5. MODULE DESCRIPTIONS

### 5.1 Authentication & Registration Module
A localized security gateway built via `AuthService` handling administrative user objects (`admin/admin123`). It secures both CLI operations and acts as the blocking `LoginFrame` within the GUI application preventing unauthorized memory injections.

### 5.2 Patient & Doctor Logistics
Implements `PatientService.class` and `DoctorService.class`. Generates automated alphanumeric keys (e.g., `PAT001`, `DOC001`) via a centralized `IdGenerator`. Attributes handle boolean state flags representing specialist availability.

### 5.3 Appointment Scheduler
The bridge bridging Patient IDs with Doctor IDs mapping real-world schedule collisions. Includes mechanisms classifying schedules by lifecycle states: `Booked`, `Completed`, or `Cancelled`.

### 5.4 Medical Service Tracking
In a modern facility, tracking distinct procedures (MRI, X-Ray) independently before consolidating is paramount. This engine accepts cost values and a status queue. Only services flagged explicitly as `Completed` reflect in the patient's final ledger. 

### 5.5 Automated Financial Billing
The crowning algorithm in the suite. The system aggregates universal hospital Consultation configurations with all individual auxiliary charges generated over the patient’s lifecycle to issue a comprehensive runtime generated bill formatted cleanly on-screen.

## 6. DATA FLOW AND PERSISTENCE
MySQL dependencies inherently affect initial execution probability in academic presentations due to driver and credential mismatching. This application employs advanced JDK lifecycle hooks:
`Runtime.getRuntime().addShutdownHook(...)`
This ensures whenever the software safely shuts down operations, it serializes its active, memory-resident Java Collections Framework topologies (`HashMap`, `ArrayList`) into `.dat` binary logs. Upon initialization, it recursively loads state, assuring immediate integrity.

## 7. SCREENSHOTS & TESTING (Implementation Plan)
**Unit validation points tested successfully:**
- Correct compilation flags routing into `\bin` repositories.
- Negative tests (Incorrect passwords blocked at main screen gateway).
- Data type parsing tests (Decimal costs in Services successfully captured).
- Verification of Billing Algorithm scaling exactly proportionally to services injected.

## 8. CONCLUSION
The **Integrated Hospital Management System** stands as an optimal academic demonstration of Enterprise Java structuring patterns applied accurately. By dividing code logic appropriately amongst Models, Services, and views, and layering a clean Java Swing user interface overlay, the project meets all primary requisites of a dynamic, modern problem-solving application ready for scalability.

---
**Prepared For:** Object Oriented Programming Submission / Java PBL  
**Date of Submission:** April 2026
