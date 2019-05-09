package com.ironsource.sdk.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SSAObj {
    private JSONObject mJsonObject;

    public SSAObj() {
        this.mJsonObject = new JSONObject();
    }

    public SSAObj(String value) {
        setJsonObject(value);
    }

    private void setJsonObject(String value) {
        try {
            this.mJsonObject = new JSONObject(value);
        } catch (Exception e) {
            this.mJsonObject = new JSONObject();
        }
    }

    public JSONObject getJsonObject() {
        return this.mJsonObject;
    }

    public boolean containsKey(String key) {
        return getJsonObject().has(key);
    }

    public boolean isNull(String key) {
        return getJsonObject().isNull(key);
    }

    public Object get(String key) {
        try {
            return getJsonObject().get(key);
        } catch (JSONException e) {
            return null;
        }
    }

    public String getString(String key) {
        try {
            return this.mJsonObject.getString(key);
        } catch (JSONException e) {
            return null;
        }
    }

    public String getString(String key, String fallback) {
        return this.mJsonObject.optString(key, fallback);
    }

    public boolean getBoolean(String key) {
        try {
            return this.mJsonObject.getBoolean(key);
        } catch (JSONException e) {
            return false;
        }
    }

    public static Object toJSON(Object object) throws JSONException {
        Object jSONObject;
        if (object instanceof Map) {
            jSONObject = new JSONObject();
            Map map = (Map) object;
            for (Object key : map.keySet()) {
                if (key != null) {
                    jSONObject.put(key.toString(), toJSON(map.get(key)));
                }
            }
            return jSONObject;
        } else if (!(object instanceof Iterable)) {
            return object;
        } else {
            jSONObject = new JSONArray();
            for (Object value : (Iterable) object) {
                jSONObject.put(value);
            }
            return jSONObject;
        }
    }

    public static boolean isEmptyObject(JSONObject object) {
        return object.names() == null;
    }

    public List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }

    public void put(String name, String value) {
        try {
            this.mJsonObject.put(name, value);
        } catch (Exception e) {
        }
    }

    public void put(String name, JSONObject value) {
        try {
            this.mJsonObject.put(name, value);
        } catch (Exception e) {
        }
    }

    public String toString() {
        if (this.mJsonObject == null) {
            return "";
        }
        return this.mJsonObject.toString();
    }

    private Map<String, Object> getMap(JSONObject object, String key) throws JSONException {
        return toMap(object.getJSONObject(key));
    }

    private Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap();
        Iterator keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }

    private Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        }
        if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        }
        if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        }
        return json;
    }
}
