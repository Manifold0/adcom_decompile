package com.unity.purchasing.common;

import org.json.JSONException;
import org.json.JSONObject;

public class SaneJSONObject extends JSONObject {
    public JSONObject put(String name, double value) {
        try {
            return super.put(name, value);
        } catch (JSONException j) {
            throw new RuntimeException(j);
        }
    }

    public JSONObject put(String name, Object value) {
        try {
            return super.put(name, value);
        } catch (JSONException j) {
            throw new RuntimeException(j);
        }
    }

    public JSONObject put(String name, boolean value) {
        try {
            return super.put(name, value);
        } catch (JSONException j) {
            throw new RuntimeException(j);
        }
    }
}
