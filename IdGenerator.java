package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility to generate auto-incrementing unique IDs for entities.
 */
public class IdGenerator implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Integer> counters;

    public IdGenerator() {
        counters = new HashMap<>();
    }

    /**
     * Generates a new ID based on prefix (e.g., "P" -> "P001")
     */
    public synchronized String getNextId(String prefix) {
        int count = counters.getOrDefault(prefix, 0) + 1;
        counters.put(prefix, count);
        return String.format("%s%03d", prefix, count);
    }
}
