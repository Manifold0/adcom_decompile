// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

public class JsonUtil
{
    public static boolean hasNonNull(final JsonElement jsonElement, final String s) {
        if (jsonElement != null && !jsonElement.isJsonNull() && jsonElement.isJsonObject()) {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject.has(s) && asJsonObject.get(s) != null && !asJsonObject.get(s).isJsonNull()) {
                return true;
            }
        }
        return false;
    }
}
