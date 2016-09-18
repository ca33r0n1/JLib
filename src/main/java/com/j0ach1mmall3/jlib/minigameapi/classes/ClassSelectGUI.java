package com.j0ach1mmall3.jlib.minigameapi.classes;

import com.j0ach1mmall3.jlib.gui.Gui;
import com.j0ach1mmall3.jlib.gui.GuiPage;
import com.j0ach1mmall3.jlib.gui.events.GuiClickEvent;
import com.j0ach1mmall3.jlib.inventory.JLibItem;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerSelectClassEvent;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
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
     * @param game The Game this ClassSelectGUI belongs to
     * @param classes The inventory position Class mapping
     * @param guiPages The GuiPages in this TeamSelectGUI
     */
    public ClassSelectGUI(Game game, Map<Integer, Class> classes, GuiPage... guiPages) {
        this.game = game;
        this.classes = classes;
    }

    public void addItem(int page, int position, JLibItem jLibItem) {
        jLibItem.setGuiClickHandler(new CallbackHandler<GuiClickEvent>() {
            @Override
            public void callback(GuiClickEvent o) {
                Class clazz = ClassSelectGUI.this.classes.get(o.getInventoryClickEvent().getSlot());
                Player p = o.getPlayer();
                o.setCancelled(true);
                PlayerSelectClassEvent event = new PlayerSelectClassEvent(ClassSelectGUI.this.game, clazz, p);
                Bukkit.getPluginManager().callEvent(event);
                if(!event.isCancelled()) {
                    p.closeInventory();
                    if(ClassSelectGUI.this.game.containsPlayer(p)) ClassSelectGUI.this.game.setClass(p, event.getClazz());
                }
            }
        });
        this.guiPages.get(page).addItem(jLibItem);
    }
}
