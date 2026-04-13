package service;

import model.User;
import util.DataStore;

public class AuthService {
    private DataStore dataStore;

    public AuthService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public User login(String username, String password) {
        if (dataStore.users.containsKey(username)) {
            User user = dataStore.users.get(username);
            if (user.getPassword().equals(password)) {
                return user; // Successful login
            }
        }
        return null; // Failed login
    }
}
