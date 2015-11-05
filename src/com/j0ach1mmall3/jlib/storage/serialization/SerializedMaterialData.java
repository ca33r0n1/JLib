package com.j0ach1mmall3.jlib.storage.serialization;

import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.material.MaterialData;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class SerializedMaterialData {
    private MaterialData materialData;
    private String string;

    /**
     * Constructs a new SerializedMaterialData
     * @param materialData The MaterialData
     * @see MaterialData
     */
    @SuppressWarnings("deprecation")
    public SerializedMaterialData(MaterialData materialData) {
        this.materialData = materialData;
        this.string = materialData.getItemTypeId() + "|" + materialData.getData();
    }

    /**
     * Constructs a new SerializedMaterialData
     * @param string The String
     */
    @SuppressWarnings("deprecation")
    public SerializedMaterialData(String string) {
        String[] splitted = string.split("\\|");
        this.materialData = new MaterialData(Parsing.parseInt(splitted[0]), Parsing.parseByte(splitted[1]));
        this.string = string;
    }

    /**
     * Returns the MaterialData
     * @return The MaterialData
     * @see MaterialData
     */
    public MaterialData getMaterialData() {
        return materialData;
    }

    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return string;
    }
}
