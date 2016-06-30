package com.j0ach1mmall3.jlib.integration.dropfile;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/06/2016
 */
public final class DropFile {
    private String name;
    private byte[] data;

    /**
     * Creates a new DropFile file
     * @param name The name of the file
     * @param data The data of the file
     */
    public DropFile(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    /**
     * Returns the name of the file
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the file
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the data of the file
     * @return The data
     */
    public byte[] getData() {
        return this.data;
    }

    /**
     * Sets the data of the file
     * @param data The new data
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}
