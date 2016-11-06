package com.j0ach1mmall3.jlib.minigameapi.classes;

import com.j0ach1mmall3.jlib.gui.Gui;
import com.j0ach1mmall3.jlib.gui.GuiPage;
import com.j0ach1mmall3.jlib.inventory.JLibItem;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerSelectClassEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public final class ClassSelectGUI extends Gui {
    private final Game game;
    private final Map<Integer, Class> classes;

    /**
     * Constructs a new ClassSelectGUI
     *
     * @param game     The Game this ClassSelectGUI belongs to
     * @param classes  The inventory position Class mapping
     * @param guiPages The GuiPages in this TeamSelectGUI
     */
    public ClassSelectGUI(Game game, Map<Integer, Class> classes, GuiPage... guiPages) {
        super(guiPages);
        this.game = game;
        this.classes = classes;
    }

    /**
     * Adds a JLibItem to this ClassSelectGui
     *
     * @param page     The page the JLibItem is in
     * @param position The position of the JLibItem
     * @param jLibItem The JLibItem
     */
    public void addItem(int page, int position, JLibItem jLibItem) {
        jLibItem.setGuiClickHandler(o -> {
            Class clazz = this.classes.get(o.getInventoryClickEvent().getSlot());
            Player p = o.getPlayer();
            o.setCancelled(true);
            PlayerSelectClassEvent event = new PlayerSelectClassEvent(this.game, clazz, p);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                p.closeInventory();
                if (this.game.containsPlayer(p)) this.game.setClass(p, event.getClazz());
            }
        });
        this.guiPages.get(page).addItem(position, jLibItem);
    }
}
