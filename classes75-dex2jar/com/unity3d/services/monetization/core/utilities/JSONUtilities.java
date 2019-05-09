// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.core.utilities;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONException;
import com.unity3d.services.core.log.DeviceLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class JSONUtilities
{
    public static List<Object> jsonArrayToList(final JSONArray jsonArray) {
        final ArrayList<Object> list = new ArrayList<Object>(jsonArray.length());
        int i = 0;
    Label_0037_Outer:
        while (i < jsonArray.length()) {
            while (true) {
                try {
                    list.add(mapTypeFromJSON(jsonArray.get(i)));
                    ++i;
                    continue Label_0037_Outer;
                }
                catch (JSONException ex) {
                    DeviceLog.error("Could not put value into list: %s", ex.getMessage());
                    continue;
                }
                break;
            }
            break;
        }
        return list;
    }
    
    public static Map<String, Object> jsonObjectToMap(final JSONObject jsonObject) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>(jsonObject.length());
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            try {
                hashMap.put(s, mapTypeFromJSON(jsonObject.get(s)));
            }
            catch (JSONException ex) {
                DeviceLog.error("Could not put value in map: %s, %s", s, ex.getMessage());
            }
        }
        return hashMap;
    }
    
    public static JSONObject mapToJsonObject(Map<String, Object> iterator) {
        final JSONObject jsonObject = new JSONObject();
        iterator = ((Map<Object, Object>)iterator).entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            try {
                jsonObject.put((String)entry.getKey(), wrap(entry.getValue()));
            }
            catch (JSONException ex) {
                DeviceLog.error("Could not map entry to object: %s, %s", entry.getKey(), entry.getValue());
            }
        }
        return jsonObject;
    }
    
    private static Object mapTypeFromJSON(final Object o) {
        Object jsonObjectToMap;
        if (o instanceof JSONObject) {
            jsonObjectToMap = jsonObjectToMap((JSONObject)o);
        }
        else {
            jsonObjectToMap = o;
            if (o instanceof JSONArray) {
                return jsonArrayToList((JSONArray)o);
            }
        }
        return jsonObjectToMap;
    }
    
    public static Object wrap(final Object o) {
        Object o2;
        if (o == null) {
            o2 = null;
        }
        else {
            o2 = o;
            if (!(o instanceof JSONArray)) {
                o2 = o;
                if (!(o instanceof JSONObject)) {
                    o2 = o;
                    if (!o.equals(JSONObject.NULL)) {
                        try {
                            if (o instanceof Collection) {
                                return new JSONArray((Collection)o);
                            }
                            if (o.getClass().isArray()) {
                                return new JSONArray((Collection)Arrays.asList(o));
                            }
                            if (o instanceof Map) {
                                return new JSONObject((Map)o);
                            }
                            o2 = o;
                            if (o instanceof Boolean) {
                                return o2;
                            }
                            o2 = o;
                            if (o instanceof Byte) {
                                return o2;
                            }
                            o2 = o;
                            if (o instanceof Character) {
                                return o2;
                            }
                            o2 = o;
                            if (o instanceof Double) {
                                return o2;
                            }
                            o2 = o;
                            if (o instanceof Float) {
                                return o2;
                            }
                            o2 = o;
                            if (o instanceof Integer) {
                                return o2;
                            }
                            o2 = o;
                            if (o instanceof Long) {
                                return o2;
                            }
                            o2 = o;
                            if (o instanceof Short) {
                                return o2;
                            }
                            o2 = o;
                            if (o instanceof String) {
                                return o2;
                            }
                            if (o instanceof Enum) {
                                return o.toString();
                            }
                            if (o.getClass().getPackage().getName().startsWith("java.")) {
                                return o.toString();
                            }
                        }
                        catch (Exception ex) {}
                        return null;
                    }
                }
            }
        }
        return o2;
    }
}
