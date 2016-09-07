package com.j0ach1mmall3.jlib.integration.pinger;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/02/16
 */
public final class PingResponse {
    private Description description;
    private Players players;
    private Version version;
    private String favicon;

    /**
     * Returns the description of the server
     * @return The description
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Returns the Players of the server
     * @return The Players
     */
    public Players getPlayers() {
        return this.players;
    }

    /**
     * Returns the Version of the Server
     * @return The Version
     */
    public Version getVersion() {
        return this.version;
    }

    /**
     * Returns the Favicon of the server (Base64)
     * @return The Favicon
     */
    public String getFavicon() {
        return this.favicon;
    }

    public static final class Description {
        private String text;

        /**
         * Returns the text of this Description
         * @return The text
         */
        public String getText() {
            return this.text;
        }
    }

    public static final class Players {
        private int max;
        private int online;
        private List<Player> sample;

        /**
         * Returns the maximum playercount of the server
         * @return The maximum playercount
         */
        public int getMax() {
            return this.max;
        }

        /**
         * Returns the online playercount of the server
         * @return The online playercount
         */
        public int getOnline() {
            return this.online;
        }

        /**
         * Returns the PlayerSample of the server
         * @return The PlayerSample
         */
        public List<Player> getSample() {
            return this.sample;
        }

        public static final class Player {
            private String name;
            private String id;

            /**
             * Returns the name of the Player
             * @return The name
             */
            public String getName() {
                return this.name;
            }

            /**
             * Returns the id of the Player
             * @return The id
             */
            public String getId() {
                return this.id;
            }
        }
    }

    public static final class Version {
        private String name;
        private String protocol;

        /**
         * Returns the name of the Version
         * @return The name
         */
        public String getName() {
            return this.name;
        }

        /**
         * Returns the protocol number of the Version
         * @return The protocol number
         */
        public String getProtocol() {
            return this.protocol;
        }
    }
}
