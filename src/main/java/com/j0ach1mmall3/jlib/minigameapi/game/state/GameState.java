package com.j0ach1mmall3.jlib.minigameapi.game.state;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 26/03/2016
 */
public final class GameState {
    private final String name;
    private final GameRuleSet gameRuleSet;
    private final GameChatType gameChatType;

    /**
     * Constructs a new GameState
     *
     * @param name         The name of the GameState
     * @param gameRuleSet  The GameRuleSet of the GameState
     * @param gameChatType The GameChatType of the GameState
     */
    public GameState(String name, GameRuleSet gameRuleSet, GameChatType gameChatType) {
        this.name = name;
        this.gameRuleSet = gameRuleSet;
        this.gameChatType = gameChatType;
    }

    /**
     * Returns the name of the GameState
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the GameRuleSet of the GameState
     *
     * @return The GameRuleSet
     */
    public GameRuleSet getRuleSet() {
        return this.gameRuleSet;
    }

    /**
     * Returnsthe GameChatType of the GameState
     *
     * @return The GameChatType
     */
    public GameChatType getChatType() {
        return this.gameChatType;
    }
}
