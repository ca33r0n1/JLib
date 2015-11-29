package com.j0ach1mmall3.jlib.integration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public final class UUIDFetcher {
    private final String name;

    public UUIDFetcher(String name) {
        this.name = name;
    }

    public UUID getUniqueId() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + this.name).openConnection();
        return this.getUUID((String) ((JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()))).get("id"));
    }

    private UUID getUUID(String id) {
        return UUID.fromString(id.substring(0, 8) + '-' + id.substring(8, 12) + '-' + id.substring(12, 16) + '-' + id.substring(16, 20) + '-' +id.substring(20, 32));
    }

    public static UUID getUUIDOf(String name) throws Exception {
        return new UUIDFetcher(name).getUniqueId();
    }
}