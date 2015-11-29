package com.j0ach1mmall3.jlib.storage.serialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class SerializedList implements SerializedCollection {
    private final List<String> list;
    private String string;

    /**
     * Constructs a new SerializedList
     * @param list The List
     */
    public SerializedList(List<String> list) {
        this.list = list==null?new ArrayList<String>():list;
        this.string = "";
        for(String listString : this.list) {
            this.string = this.string + listString + "|";
        }
    }

    /**
     * Constructs a new SerializedList
     * @param string The String
     */
    public SerializedList(String string) {
        this.list = new ArrayList<>();
        Collections.addAll(this.list, string.split("\\|"));
        this.string = string;
    }

    /**
     * Returns the List
     * @return The List
     */
    public List<String> getCollection() {
        return this.list;
    }

    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return this.string;
    }
}
