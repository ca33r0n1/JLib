package com.j0ach1mmall3.jlib.storage.serialization;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/11/15
 */
public class JSerializable<O extends Serializable> {
    private final String s;
    private final O object;

    /**
     * Constructs a new JSerializable
     * @param s The String
     * @throws Exception When an error occurs while decoding the String
     */
    public JSerializable(String s) throws Exception {
        this.s = s;

        byte[] data = Base64.decodeBase64(s);
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));
        Object object  = inputStream.readObject();
        inputStream.close();

        this.object = (O) object;
    }

    /**
     * Constructs a new JSerializable
     * @param object The Object
     * @throws Exception When an error occurs while encoding the String
     */
    public JSerializable(O object) throws Exception {
        this.object = object;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream1 = new ObjectOutputStream(outputStream);
        outputStream1.writeObject(object);
        outputStream1.close();

        this.s = Base64.encodeBase64String(outputStream.toByteArray());
        outputStream.close();
    }

    /**
     * Returns the String
     * @return The String
     */
    public String getString() {
        return this.s;
    }

    /**
     * Returns the Object
     * @return The Object
     */
    public O getObject() {
        return this.object;
    }
}
