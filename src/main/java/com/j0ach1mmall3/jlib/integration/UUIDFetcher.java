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
public final class UUIDFetcher {
    private final String name;

    /**
     * Creates a new UUIDFetcher with the given name
     * @param name The name
     */
    public UUIDFetcher(String name) {
        this.name = name;
    }

    /**
     * Returns the UUID of the name this UUIDFetcher instance is associated with
     * @return The UUID of the name
     * @throws IOException Thrown when we can't connect to the api servers
     * @throws ParseException Thrown when the response can't be parsed to JSON
     */
    public UUID getUniqueId() throws IOException, ParseException {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + this.name).openConnection();
        return this.getUUID((String) ((JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()))).get("id"));
    }

    private UUID getUUID(String id) {
        return UUID.fromString(id.substring(0, 8) + '-' + id.substring(8, 12) + '-' + id.substring(12, 16) + '-' + id.substring(16, 20) + '-' +id.substring(20, 32));
    }

    /**
     * Returns the UUID of a specified name
     * @param name The UUID
     * @return The UUID of the name
     * @throws IOException Thrown when we can't connect to the api servers
     * @deprecated {@link UUIDFetcher#getUniqueId()}
     */
    @Deprecated
    public static UUID getUUIDOf(String name) throws IOException, ParseException {
        return new UUIDFetcher(name).getUniqueId();
    }
}