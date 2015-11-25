package com.j0ach1mmall3.jlib.integration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class NameFetcher {
    private final UUID uuid;

    public NameFetcher(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + this.uuid.toString().replace("-", "")).openConnection();
        JSONObject response = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
        return (String) response.get("name");
    }

    public static String getNameOf(UUID uuid) throws Exception {
        return new NameFetcher(uuid).getName();
    }
}
