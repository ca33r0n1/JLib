package com.j0ach1mmall3.jlib.storage.serialization;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 28/12/15
 */
public final class SerializedSet implements SerializedCollection {
    private final Set<String> set;
    private String string;

    /**
     * Constructs a new SerializedSet
     *
     * @param set The Set
     */
    public SerializedSet(Set<String> set) {
        this.set = set == null ? new HashSet<String>() : set;
        this.string = "";
        for (String setString : this.set) {
            this.string = this.string + setString + "|";
        }
    }

    /**
     * Constructs a new SerializedSet
     *
     * @param string The String
     */
    public SerializedSet(String string) {
        this.set = new HashSet<>();
        Collections.addAll(this.set, string.split("\\|"));
        this.string = string;
    }

    /**
     * Returns the Set
     *
     * @return The Set
     */
    public Set<String> getCollection() {
        return this.set;
    }

    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return this.string;
    }
}