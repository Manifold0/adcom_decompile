package com.vungle.warren.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtil {
    public static boolean hasNonNull(JsonElement jsonElement, String key) {
        if (jsonElement == null || jsonElement.isJsonNull() || !jsonElement.isJsonObject()) {
            return false;
        }
        JsonObject object = jsonElement.getAsJsonObject();
        if (!object.has(key) || object.get(key) == null || object.get(key).isJsonNull()) {
            return false;
        }
        return true;
    }
}
