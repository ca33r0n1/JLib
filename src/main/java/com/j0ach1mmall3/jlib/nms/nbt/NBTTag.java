package com.j0ach1mmall3.jlib.nms.nbt;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 7/09/2016
 */
public final class NBTTag {
    public static final byte END = 0;
    public static final byte BYTE = 1;
    public static final byte SHORT = 2;
    public static final byte INT = 3;
    public static final byte LONG = 4;
    public static final byte FLOAT = 5;
    public static final byte DOUBLE = 6;
    public static final byte BYTE_ARRAY = 7;
    public static final byte STRING = 8;
    public static final byte LIST = 9;
    public static final byte COMPOUND = 10;
    public static final byte INT_ARRAY = 11;

    private final Object nbtTag;

    /**
     * Constructs a new NBTTag
     *
     * @param nbtTag The NMS NBTTag
     */
    public NBTTag(Object nbtTag) {
        this.nbtTag = nbtTag;
    }

    /**
     * Constructs a new NBTTag
     *
     * @param typeId The Type id
     * @throws Exception if an exception occurs
     */
    public NBTTag(int typeId) throws Exception {
        this.nbtTag = ReflectionAPI.invokeMethod(ReflectionAPI.getNmsClass("NBTBase"), null, "createTag", new Class[]{byte.class}, (byte) typeId);
    }

    /**
     * Constructs a new NBTTag and sets the data
     *
     * @param nbtTag The NMS NBTTag
     * @param data   The data
     * @throws Exception if an exception occurs
     */
    public NBTTag(Object nbtTag, Object data) throws Exception {
        this(nbtTag);
        this.setData(data);
    }

    /**
     * Constructs a new NBTTag and sets the data
     *
     * @param typeId The Type id
     * @param data   The data
     * @throws Exception if an exception occurs
     */
    public NBTTag(int typeId, Object data) throws Exception {
        this(typeId);
        System.out.println(typeId);
        this.setData(data);
    }

    /**
     * Constructs a new NBTTag and sets NBTTags (Only applies to NBTTagList)
     *
     * @param nbtTag The NMS NBTTag
     * @param list   The NBTTags
     * @throws Exception if an exception occurs
     */
    public NBTTag(Object nbtTag, List<NBTTag> list) throws Exception {
        this(nbtTag);
        this.setList(list);
    }

    /**
     * Constructs a new NBTTag and sets NBTTags (Only applies to NBTTagList)
     *
     * @param list The NBTTags
     * @throws Exception if an exception occurs
     */
    public NBTTag(List<NBTTag> list) throws Exception {
        this(LIST);
        this.setList(list);
    }

    /**
     * Constructs a new NBTTag and sets the mapped NBTTags (Only applies to NBTTagCompound)
     *
     * @param nbtTag The NMS NBTTag
     * @param map    The mapped NBTTags
     * @throws Exception if an exception occurs
     */
    public NBTTag(Object nbtTag, Map<String, NBTTag> map) throws Exception {
        this(nbtTag);
        this.setMap(map);
    }

    /**
     * Constructs a new NBTTag and sets the mapped NBTTags (Only applies to NBTTagCompound)
     *
     * @param map The mapped NBTTags
     * @throws Exception if an exception occurs
     */
    public NBTTag(Map<String, NBTTag> map) throws Exception {
        this(COMPOUND);
        this.setMap(map);
    }

    /**
     * Returns the NMS NBTTag
     *
     * @return The NMSG NBTTag
     */
    public Object getNbtTag() {
        return this.nbtTag;
    }

    /**
     * Returns the Type id of this NBTTag
     *
     * @return The Type id
     * @throws Exception if an exception occurs
     */
    public byte getTypeId() throws Exception {
        return (byte) this.nbtTag.getClass().getMethod("getTypeId").invoke(this.nbtTag);
    }

    /**
     * Returns the data of this NBTTag
     *
     * @return The data
     * @throws Exception if an exception occurs
     */
    public Object getData() throws Exception {
        return ReflectionAPI.getField(this.nbtTag, "data");
    }

    /**
     * Sets the data of this NBTTag
     *
     * @param data The data
     * @throws Exception if an exception occurs
     */
    public void setData(Object data) throws Exception {
        ReflectionAPI.setField(this.nbtTag, "data", data);
    }

    /**
     * Returns the NBTTags for this NBTTag (Only applies to NBTTagList)
     *
     * @return The NBTTags
     * @throws Exception if an exception
     */
    public List<NBTTag> getList() throws Exception {
        List<NBTTag> list = new ArrayList<>();
        for (Object o : (Iterable) ReflectionAPI.getField(this.nbtTag, "list")) {
            list.add(new NBTTag(o));
        }
        return list;
    }

    /**
     * Sets the NBTTags for this NBTTag (Only applies to NBTTagList)
     *
     * @param list The NBTTags
     * @throws Exception if an exception
     */
    public void setList(List<NBTTag> list) throws Exception {
        List<Object> l = (List<Object>) ReflectionAPI.getField(this.nbtTag, "list");
        l.clear();
        l.addAll(list.stream().map(nbtTag -> nbtTag.nbtTag).collect(Collectors.toList()));
        ReflectionAPI.setField(this.nbtTag, "list", l);
    }

    /**
     * Returns the mapped NBTTags for this NBTTag (Only applies to NBTTagCompound)
     *
     * @return The mapped NBTTags
     * @throws Exception if an exception occurs
     */
    public Map<String, NBTTag> getMap() throws Exception {
        Map<String, NBTTag> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : ((Map<String, Object>) ReflectionAPI.getField(this.nbtTag, "map")).entrySet()) {
            map.put(entry.getKey(), new NBTTag(entry.getValue()));
        }
        return map;
    }

    /**
     * Sets the mapped NBTTags for this NBTTag (Only applies to NBTTagCompound)
     *
     * @param map The mapped NBTTags
     * @throws Exception if an exception occurs
     */
    public void setMap(Map<String, NBTTag> map) throws Exception {
        Map<String, Object> m = (Map<String, Object>) ReflectionAPI.getField(this.nbtTag, "map");
        m.clear();
        for (Map.Entry<String, NBTTag> entry : map.entrySet()) {
            m.put(entry.getKey(), entry.getValue().nbtTag);
        }
        ReflectionAPI.setField(this.nbtTag, "map", m);
    }

    @Override
    public String toString() {
        try {
            int typeId = this.getTypeId();
            if (typeId == 0) return "";
            if (typeId == 9) return this.getList().toString();
            if (typeId == 10) return this.getMap().toString();
            return '\"' + this.getData().toString() + '\"';
            /*
            int typeId = this.getTypeId();
            if (typeId == 0) return "NBTTag{typeId=0}";
            if (typeId == 9) return "NBTTag{typeId=9, list=" + this.getList() + '}';
            if (typeId == 10) return "NBTTag{typeId=10, map=" + this.getMap() + '}';
            return "NBTTag{typeId=" + typeId + ", data=" + this.getData() + '}';*/
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
