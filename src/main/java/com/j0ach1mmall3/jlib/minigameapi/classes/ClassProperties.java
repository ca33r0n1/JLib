package com.j0ach1mmall3.jlib.minigameapi.classes;

import com.j0ach1mmall3.jlib.inventory.JLibItem;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 20/03/2016
 */
public final class ClassProperties {
    private final ClassSelectGUI classSelectGUI;
    private final JLibItem classSelectItem;
    private final boolean giveSelectItem;
    private final boolean dropSelectItem;
    private final boolean moveSelectItem;

    /**
     * Constructs a new ClassProperties instance
     * @param classSelectGUI The ClassSelectGUI
     * @param classSelectItem The classSelectItem
     * @param giveSelectItem Whether we should give the SelectItem on join
     * @param dropSelectItem Whether dropping the SelectItem should be allowed
     * @param moveSelectItem Whether moving the SelectItem should be allowed
     */
    public ClassProperties(ClassSelectGUI classSelectGUI, JLibItem classSelectItem, boolean giveSelectItem, boolean dropSelectItem, boolean moveSelectItem) {
        this.classSelectGUI = classSelectGUI;
        this.classSelectItem = classSelectItem;
        this.giveSelectItem = giveSelectItem;
        this.dropSelectItem = dropSelectItem;
        this.moveSelectItem = moveSelectItem;
    }

    /**
     * Returns the ClassSelectGUI
     * @return The ClassSelectGUI
     */
    public ClassSelectGUI getClassSelectGUI() {
        return this.classSelectGUI;
    }

    /**
     * Returns the classSelectItem
     * @return The classSelectItem
     */
    public JLibItem getClassSelectItem() {
        return this.classSelectItem;
    }

    /**
     * Returns whether we should give the SelectItem on join
     * @return Whether we should give the SelectItem on join
     */
    public boolean isGiveSelectItem() {
        return this.giveSelectItem;
    }

    /**
     * Returns whether dropping the SelectItem should be allowed
     * @return Whether dropping the SelectItem should be allowed
     */
    public boolean isDropSelectItem() {
        return this.dropSelectItem;
    }

    /**
     * Returns whether moving the SelectItem should be allowed
     * @return Whether moving the SelectItem should be allowed
     */
    public boolean isMoveSelectItem() {
        return this.moveSelectItem;
    }
}
