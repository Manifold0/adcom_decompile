package com.unity3d.services.monetization.core.utilities;

import com.unity3d.services.core.log.DeviceLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtilities {
    public static Map<String, Object> jsonObjectToMap(JSONObject object) {
        Map<String, Object> map = new HashMap(object.length());
        Iterator<String> it = object.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            try {
                map.put(key, mapTypeFromJSON(object.get(key)));
            } catch (JSONException e) {
                DeviceLog.error("Could not put value in map: %s, %s", key, e.getMessage());
            }
        }
        return map;
    }

    public static List<Object> jsonArrayToList(JSONArray array) {
        List<Object> values = new ArrayList(array.length());
        for (int i = 0; i < array.length(); i++) {
            try {
                values.add(mapTypeFromJSON(array.get(i)));
            } catch (JSONException e) {
                DeviceLog.error("Could not put value into list: %s", e.getMessage());
            }
        }
        return values;
    }

    private static Object mapTypeFromJSON(Object value) {
        if (value instanceof JSONObject) {
            return jsonObjectToMap((JSONObject) value);
        }
        if (value instanceof JSONArray) {
            return jsonArrayToList((JSONArray) value);
        }
        return value;
    }

    public static JSONObject mapToJsonObject(Map<String, Object> extras) {
        JSONObject object = new JSONObject();
        for (Entry<String, Object> entry : extras.entrySet()) {
            try {
                object.put((String) entry.getKey(), wrap(entry.getValue()));
            } catch (JSONException e) {
                DeviceLog.error("Could not map entry to object: %s, %s", entry.getKey(), entry.getValue());
            }
        }
        return object;
    }

    public static Object wrap(Object o) {
        if (o == null) {
            return null;
        }
        if ((o instanceof JSONArray) || (o instanceof JSONObject) || o.equals(JSONObject.NULL)) {
            return o;
        }
        try {
            if (o instanceof Collection) {
                return new JSONArray((Collection) o);
            }
            if (o.getClass().isArray()) {
                return new JSONArray(Arrays.asList(new Object[]{o}));
            } else if (o instanceof Map) {
                return new JSONObject((Map) o);
            } else {
                if ((o instanceof Boolean) || (o instanceof Byte) || (o instanceof Character) || (o instanceof Double) || (o instanceof Float) || (o instanceof Integer) || (o instanceof Long) || (o instanceof Short) || (o instanceof String)) {
                    return o;
                }
                if (o instanceof Enum) {
                    return o.toString();
                }
                if (o.getClass().getPackage().getName().startsWith("java.")) {
                    return o.toString();
                }
                return null;
            }
        } catch (Exception e) {
        }
    }
}
