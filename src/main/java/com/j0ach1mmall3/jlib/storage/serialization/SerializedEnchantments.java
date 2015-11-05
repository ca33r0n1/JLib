package com.j0ach1mmall3.jlib.storage.serialization;

import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.enchantments.Enchantment;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class SerializedEnchantments implements JLibSerializable {
    private Map<Enchantment, Integer> enchantments;
    private String string;

    /**
     * Constructs a new SerializedEnchantments
     * @param enchantments The Enchantments Map
     * @see Enchantment
     */
    public SerializedEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        this.string = "";
        for(Enchantment enchantment : enchantments.keySet()) {
            string = string + enchantment.getName() + "=" + enchantments.get(enchantment) + "|";
        }
    }

    /**
     * Constructs a new SerializedEnchantments
     * @param string The String
     */
    public SerializedEnchantments(String string) {
        for(String splittedString : string.split("\\|")) {
            enchantments.put(Enchantment.getByName(splittedString.split("=")[0]), Parsing.parseInt(splittedString.split("=")[1]));
        }
        this.string = string;
    }

    /**
     * Returns the Enchantments Map
     * @return The Enchantments Map
     * @see Enchantment
     */
    public Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return string;
    }
}
