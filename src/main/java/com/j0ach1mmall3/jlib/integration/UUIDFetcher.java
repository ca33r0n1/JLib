package com.j0ach1mmall3.jlib.integration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public final class UUIDFetcher {
    private final String name;

    public UUIDFetcher(String name) {
        this.name = name;
    }

    public UUID getUniqueId() {
        try {
            HttpURLConnection connection = this.createConnection();
            OutputStream stream = connection.getOutputStream();
            stream.write(this.name.getBytes());
            stream.flush();
            stream.close();
            JSONArray array = (JSONArray) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
            return this.getUUID((String) ((JSONObject) array.get(0)).get("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection createConnection() throws Exception {
        URL url = new URL("https://api.mojang.com/profiles/minecraft");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        return connection;
    }

    private UUID getUUID(String id) {
        return UUID.fromString(id.substring(0, 8) + '-' + id.substring(8, 12) + '-' + id.substring(12, 16) + '-' + id.substring(16, 20) + '-' +id.substring(20, 32));
    }

    public static UUID getUUIDOf(String name) {
        return new UUIDFetcher(name).getUniqueId();
    }
}