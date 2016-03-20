package com.j0ach1mmall3.jlib.minigameapi.classes;

import com.j0ach1mmall3.jlib.inventory.GUI;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 20/03/16
 */
public final class ClassSelectGUI {
    private final GUI gui;
    private final Map<Integer, Class> classs;

    /**
     * Constructs a new ClassSelectGUI
     * @param gui The GUI
     * @param classs The inventory position Class mapping
     */
    public ClassSelectGUI(GUI gui, Map<Integer, Class> classs) {
        this.gui = gui;
        this.classs = classs;
    }

    /**
     * Returns the GUI
     * @return The GUI
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Returns a Class from a clicked slot
     * @param slot The clicked slot
     * @return The Class
     */
    public Class getClass(int slot) {
        return this.classs.get(slot);
    }
}
