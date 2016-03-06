package com.j0ach1mmall3.jlib.nbsapi;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class NBSDecoder {
    private final File file;

    public NBSDecoder(File file) {
        this.file = file;
    }

    public Song getSong() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(this.file));
        short length = readShort(dataInputStream);
        readShort(dataInputStream);
        String title = readString(dataInputStream);
        String author = readString(dataInputStream);
        readString(dataInputStream);
        String description = readString(dataInputStream);
        float speed = readShort(dataInputStream) / 100F;
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
        return new Song(ticks, length, title, author, description, speed);
    }

    private static short readShort(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        return (short) (byte1 + (byte2 << 8));
    }

    private static int readInt(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        int byte3 = dis.readUnsignedByte();
        int byte4 = dis.readUnsignedByte();
        return (byte1 + (byte2 << 8) + (byte3 << 16) + (byte4 << 24));
    }

    private static String readString(DataInputStream dis) throws IOException {
        int length = readInt(dis);
        StringBuilder sb = new StringBuilder(length);
        for (; length > 0; --length) {
            char c = (char) dis.readByte();
            if (c == (char) 0x0D) c = ' ';
            sb.append(c);
        }
        return sb.toString();
    }
}
