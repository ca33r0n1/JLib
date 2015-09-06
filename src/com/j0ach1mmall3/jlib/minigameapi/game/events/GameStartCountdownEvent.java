package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by j0ach1mmall3 on 19:36 4/09/2015 using IntelliJ IDEA.
 */
public class GameStartCountdownEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private Game game;
    private int time;
    private boolean isCancelled;
    public GameStartCountdownEvent(Game game, int time) {
        this.game = game;
        this.time = time;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HandlerList getHandlerList() {
        return HANDLERS;
    }
}
