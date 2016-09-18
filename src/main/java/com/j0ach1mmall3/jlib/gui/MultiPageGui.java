package com.j0ach1mmall3.jlib.gui;

import com.j0ach1mmall3.jlib.gui.events.GuiClickEvent;
import com.j0ach1mmall3.jlib.inventory.JLibItem;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public class MultiPageGui extends Gui {
    private JLibItem previousItem;
    private JLibItem nextItem;

    /**
     * Constructs a new MultiPageGui
     * @param guiPages The GuiPages in this MultiPageGui
     */
    public MultiPageGui(GuiPage... guiPages) {
        super(guiPages);
    }

    /**
     * Constructs a new MultiPageGui
     * @param previousItem The PreviousItem
     * @param nextItem The NextItem
     * @param guiPages The GuiPages in this MultiPageGui
     */
    public MultiPageGui(JLibItem previousItem, JLibItem nextItem, GuiPage... guiPages) {
        super(guiPages);
        this.setPreviousItem(previousItem);
        this.setNextItem(nextItem);
    }

    /**
     *
     * @return
     */
    public JLibItem getPreviousItem() {
        return this.previousItem;
    }

    public void setPreviousItem(JLibItem previousItem) {
        this.previousItem = previousItem;
        final CallbackHandler<GuiClickEvent> guiClickHandler = this.previousItem.getGuiClickHandler();
        this.previousItem.setGuiClickHandler(new CallbackHandler<GuiClickEvent>() {
            @Override
            public void callback(GuiClickEvent o) {
                MultiPageGui.this.open(o.getPlayer(), MultiPageGui.this.getPreviousPage(o.getGuiPage()));
                if(guiClickHandler != null) guiClickHandler.callback(o);
            }
        });
    }

    public JLibItem getNextItem() {
        return this.nextItem;
    }

    public void setNextItem(JLibItem nextItem) {
        this.nextItem = nextItem;
        final CallbackHandler<GuiClickEvent> guiClickHandler = this.nextItem.getGuiClickHandler();
        this.nextItem.setGuiClickHandler(new CallbackHandler<GuiClickEvent>() {
            @Override
            public void callback(GuiClickEvent o) {
                MultiPageGui.this.open(o.getPlayer(), MultiPageGui.this.getNextPage(o.getGuiPage()));
                if(guiClickHandler != null) guiClickHandler.callback(o);
            }
        });
    }

    @Override
    public void open(Player player, int page) {
        GuiPage guiPage = this.guiPages.get(page);
        if(this.previousItem != null) guiPage.addItem(this.previousItem);
        if(this.nextItem != null) guiPage.addItem(this.nextItem);
        super.open(player, page);
    }
}
