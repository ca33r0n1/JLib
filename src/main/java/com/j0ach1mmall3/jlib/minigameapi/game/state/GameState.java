package com.j0ach1mmall3.jlib.minigameapi.game.state;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 26/03/2016
 */
public final class GameState {
    private final String name;
    private final GameRuleSet gameRuleSet;
    private final GameChatType gameChatType;

    public GameState(String name, GameRuleSet gameRuleSet, GameChatType gameChatType) {
        this.name = name;
        this.gameRuleSet = gameRuleSet;
        this.gameChatType = gameChatType;
    }

    public String getName() {
        return this.name;
    }

    public GameRuleSet getRuleSet() {
        return this.gameRuleSet;
    }

    public GameChatType getChatType() {
        return this.gameChatType;
    }
}
