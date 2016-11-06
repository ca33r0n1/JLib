package com.j0ach1mmall3.jlib.storage.serialization;

import com.j0ach1mmall3.jlib.logging.JLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/15
 * @deprecated {@link JSerializable}
 */
@Deprecated
public final class SerializedList {
    private final List<String> list;
    private String s;

    /**
     * Constructs a new SerializedList
     *
     * @param list The List
     * @deprecated {@link JSerializable}
     */
    @Deprecated
    public SerializedList(List<String> list) {
        new JLogger().deprecation();
        this.list = list == null ? new ArrayList<>() : list;
        this.s = "";
        for (String listString : this.list) {
            this.s = this.s + listString + '|';
        }
    }

    /**
     * Constructs a new SerializedList
     *
     * @param s The String
     * @deprecated {@link JSerializable}
     */
    @Deprecated
    public SerializedList(String s) {
        new JLogger().deprecation();
        this.list = new ArrayList<>();
        Collections.addAll(this.list, s.split("\\|"));
        this.s = s;
    }

    /**
     * Returns the List
     *
     * @return The List
     */
    public List<String> getCollection() {
        return this.list;
    }

    /**
     * Returns the String
     *
     * @return The String
     */
    public String getString() {
        return this.s;
    }
}
