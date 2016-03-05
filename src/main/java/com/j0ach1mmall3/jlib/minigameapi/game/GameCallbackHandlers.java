package com.j0ach1mmall3.jlib.minigameapi.game;

import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public final class GameCallbackHandlers {
    private final CallbackHandler<Integer> countdownCallbackHandler;
    private final CallbackHandler<Integer> gameTickCallbackHandler;

    /**
     * Constructs a new GameCallbackHandlers instance
     * @param countdownCallbackHandler The countdown CallbackHandler
     * @param gameTickCallbackHandler The Game tick CallbackHandler
     */
    public GameCallbackHandlers(CallbackHandler<Integer> countdownCallbackHandler, CallbackHandler<Integer> gameTickCallbackHandler) {
        this.countdownCallbackHandler = countdownCallbackHandler;
        this.gameTickCallbackHandler = gameTickCallbackHandler;
    }

    /**
     * Returns the countdown CallbackHandler
     * @return The countdown CallbackHandler
     */
    public CallbackHandler<Integer> getCountdownCallbackHandler() {
        return this.countdownCallbackHandler;
    }

    /**
     * Returns the Game tick CallbackHandler
     * @return The Game tick CallbackHandler
     */
    public CallbackHandler<Integer> getGameTickCallbackHandler() {
        return this.gameTickCallbackHandler;
    }
}
