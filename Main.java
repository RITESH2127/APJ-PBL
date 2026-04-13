import gui.LoginFrame;
import util.DataStore;
import util.FileHandler;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Load data from file, or initialize new if not exists
        DataStore dataStore = FileHandler.loadData();
        
        // Add shutdown hook to ensure data is saved when application exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nSaving data...");
            FileHandler.saveData(dataStore);
            System.out.println("Data saved successfully.");
        }));

        // Launch GUI
        SwingUtilities.invokeLater(() -> {
            new LoginFrame(dataStore).setVisible(true);
        });
    }
}
