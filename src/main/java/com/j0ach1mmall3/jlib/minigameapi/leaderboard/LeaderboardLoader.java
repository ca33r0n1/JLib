package com.j0ach1mmall3.jlib.minigameapi.leaderboard;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 11/03/2016
 */
public interface LeaderboardLoader {
    Leaderboard loadLeaderboard();

    void saveLeaderboard(Leaderboard leaderboard);
}
