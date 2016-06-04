package com.j0ach1mmall3.jlib.integration.dropfile;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/06/2016
 */
public final class DropFile {
    private String name;
    private byte[] data;

    public DropFile(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
