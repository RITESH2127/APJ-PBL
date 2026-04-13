package util;

import java.io.*;

/**
 * Handles saving and loading the DataStore to a file.
 */
public class FileHandler {
    private static final String FILE_NAME = "hospital_data.dat";

    /**
     * Saves the current DataStore to a binary file.
     */
    public static void saveData(DataStore dataStore) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(dataStore);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads the DataStore from a binary file.
     */
    public static DataStore loadData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (DataStore) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading data: " + e.getMessage());
                return new DataStore();
            }
        }
        return new DataStore(); // Return new instance if file doesn't exist
    }
}
