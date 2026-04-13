<div align="center">
  
# 🏥 Integrated Hospital Management System

**A robust, scalable, and fully Object-Oriented Java Application with a Swing GUI.**

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![Swing](https://img.shields.io/badge/Java_Swing-007396?style=for-the-badge&logo=java&logoColor=white)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![Architecture](https://img.shields.io/badge/Architecture-MVC-brightgreen?style=for-the-badge)](#)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

</div>

---

## 📖 Overview

The **Integrated Hospital Management System (IHMS)** is a comprehensive desktop application designed to streamline the administrative and clinical workflows of a modern healthcare facility. Built purely in Java, this system implements the Model-View-Controller (MVC) design pattern and transitions seamlessly between robust backend computing and an elegant graphical user interface (GUI).

Whether managing patient records, charting doctor availability, scheduling critical appointments, tracking lab services, or automatically computing final bills, this system ensures 100% data integrity without requiring external database installations via intelligent File Serialization. 

## ✨ Key Features

- **🔐 Secure Authentication:** Role-based login gateway protecting sensitive hospital data.
- **👨‍⚕️ Staff & Doctor Management:** Efficiently onboard medical professionals and toggle their real-time availability.
- **🧑‍🦽 Patient Registry:** Maintain detailed demographic and medical history profiles.
- **📅 Smart Appointment System:** Link patients with available specialists and monitor appointment statuses (Booked/Completed/Cancelled).
- **🔬 Service Tracking Engine:** A specialized module for logging auxiliary services (like MRIs, Blood Tests, and X-Rays) performed on patients.
- **📄 Automated Billing:** Instantly generate consolidated invoices tallying consultation fees and completed lab services.
- **💾 State Persistence:** Data is reliably saved and restored utilizing binary Object Serialization, protecting against accidental closures.
- **🖥️ Dual Interface:** Features both a blazing-fast command-line mode and a strictly organized, multi-tab **Java Swing interface**.

## 🛠️ Technology Stack

| Component | Technology Used |
| :--- | :--- |
| **Language** | Java (JDK 8+) |
| **User Interface** | Java Swing Toolkit (`JFrame`, `JTabbedPane`) |
| **Storage Mechanism** | Java File I/O (`ObjectInputStream` & Object Serialization) |
| **Design Pattern** | Model-View-Controller (MVC) |

## 📂 Project Structure

```text
📦 JAVA PBL
 ┣ 📂 model/          # Core entities (Patient, Doctor, Service, Billing, User)
 ┣ 📂 service/        # Business logic managers enforcing rules and workflows
 ┣ 📂 util/           # DataStore, auto-incrementing ID generators, and FileHandlers
 ┣ 📂 controller/     # Command-line interface orchestration engine
 ┣ 📂 gui/            # Advanced Swing-based Graphical User Interface components
 ┗ 📜 Main.java       # System bootstrap and Entry Point
```

## 🚀 Getting Started

### 1️⃣ Prerequisites
Ensure you have the Java Development Kit (JDK) installed on your machine.
- Verify installation by opening the terminal and typing: `java -version` and `javac -version`

### 2️⃣ Compilation
Navigate to the root directory of the project in your terminal window and run:

```bash
# Create a binary output directory
mkdir bin

# Compile architecture 
javac -d bin gui/*.java model/*.java util/*.java service/*.java controller/*.java Main.java
```

### 3️⃣ Execution
Start the graphical dashboard by executing the compiled application:

```bash
java -cp bin Main
```

## 🎮 Usage Guide

Once the system boots up:
1. **Login:** Use the default Administrator credentials:
   - **Username:** `admin`
   - **Password:** `admin123`
2. **Navigate Flow:**
   - **Add a Doctor:** Go to the "Doctors" tab and register a specialist.
   - **Add a Patient:** Go to the "Patients" tab. 
   - **Book Appointment:** Go to "Appointments" and link the generated `PAT001` ID with `DOC001`.
   - **Bill Generation:** After tracking services via the "Services" tab, go to "Billing", enter the Patient ID, and print their final hospital statement!

## 🧠 Architecture Notes
This system leverages a **JVM Shutdown Hook** to write the current memory maps concurrently to a physical `hospital_data.dat` document upon exiting. Under no circumstances will standard, graceful application closure result in data loss. 

---
<div align="center">
  <i>Developed with precision for Academic Excellence in Software Engineering principles.</i>
</div>
