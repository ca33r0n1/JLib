package com.j0ach1mmall3.jlib.nbsapi;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public final class NBSDecoder {
    private final File file;

    /**
     * Constructs a new NBSDecoder of a File
     *
     * @param file The File
     */
    public NBSDecoder(File file) {
        this.file = file;
    }

    /**
     * Gets the Song of this File
     *
     * @return The Song
     * @throws IOException When an IOException occurs
     */
    public Song getSong() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(this.file));
        short length = readShort(dataInputStream);
        readShort(dataInputStream);
        String title = readString(dataInputStream);
        String author = readString(dataInputStream);
        readString(dataInputStream);
        String description = readString(dataInputStream);
        float speed = readShort(dataInputStream) / 100.0F;
        dataInputStream.readBoolean();
        dataInputStream.readByte();
        dataInputStream.readByte();
        readInt(dataInputStream);
        readInt(dataInputStream);
        readInt(dataInputStream);
        readInt(dataInputStream);
        readInt(dataInputStream);
        readString(dataInputStream);
        Map<Integer, Tick> ticks = new HashMap<>();
        int tick = -1;
        while (true) {
            short jumpTicks = readShort(dataInputStream);
            if (jumpTicks == 0) break;
            tick += jumpTicks;
            Tick t = new Tick();
            while (readShort(dataInputStream) != 0) {
                t.addNote(new Note(dataInputStream.readByte(), dataInputStream.readByte() - 33));
            }
            ticks.put(tick, t);
        }
        dataInputStream.close();
        return new Song(ticks, length, title, author, description, speed);
    }

    /**
     * Reads a short from a DataInputStream
     *
     * @param dataInputStream The DataInputStream
     * @return The short
     * @throws IOException When an IOException occurs
     */
    private static short readShort(DataInputStream dataInputStream) throws IOException {
        int byte1 = dataInputStream.readUnsignedByte();
        int byte2 = dataInputStream.readUnsignedByte();
        return (short) (byte1 + (byte2 << 8));
    }

    /**
     * Reads an int from a DataInputStream
     *
     * @param dataInputStream The DataInputStream
     * @return The int
     * @throws IOException When an IOException occurs
     */
    private static int readInt(DataInputStream dataInputStream) throws IOException {
        int byte1 = dataInputStream.readUnsignedByte();
        int byte2 = dataInputStream.readUnsignedByte();
        int byte3 = dataInputStream.readUnsignedByte();
        int byte4 = dataInputStream.readUnsignedByte();
        return byte1 + (byte2 << 8) + (byte3 << 16) + (byte4 << 24);
    }

    /**
     * Reads a String from a DataInputStream
     *
     * @param dataInputStream The DataInputStream
     * @return The String
     * @throws IOException When an IOException occurs
     */
    private static String readString(DataInputStream dataInputStream) throws IOException {
        int length = readInt(dataInputStream);
        StringBuilder sb = new StringBuilder(length);
        while (length > 0) {
            --length;
            char c = (char) dataInputStream.readByte();
            if (c == (char) 0x0D) c = ' ';
            sb.append(c);
        }
        return sb.toString();
    }
}
