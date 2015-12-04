package com.j0ach1mmall3.jlib.integration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/12/2015
 */
public final class NameFetcher {
    private final UUID uuid;

    /**
     * Creates a new NameFetcher instance with the given UUID
     * @param uuid The UUID
     */
    public NameFetcher(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the name of the UUID this NameFetcher instance is associated with
     * @return The name of the UUID
     * @throws IOException Thrown when we can't connect to the session servers
     * @throws ParseException Thrown when the response can't be parsed to JSON
     */
    public String getName() throws IOException, ParseException {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + this.uuid.toString().replace("-", "")).openConnection();
        JSONObject response = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
        return (String) response.get("name");
    }

    /**
     * Returns the name of a specified UUID
     * @param uuid The UUID
     * @return The name of the UUID
     * @throws IOException Thrown when we can't connect to the session servers
     * @throws ParseException Thrown when the response can't be parsed to JSON
     * @deprecated {@link NameFetcher#getName()}
     */
    @Deprecated
    public static String getNameOf(UUID uuid) throws IOException, ParseException {
        return new NameFetcher(uuid).getName();
    }
}
