// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.Set;
import org.json.JSONObject;

class JSONUtils
{
    static JSONObject generateJsonDiff(final JSONObject jsonObject, final JSONObject jsonObject2, final JSONObject jsonObject3, final Set<String> set) {
        JSONObject jsonObject4;
        if (jsonObject == null) {
            jsonObject4 = null;
        }
        else {
            jsonObject4 = jsonObject3;
            if (jsonObject2 != null) {
                final Iterator keys = jsonObject2.keys();
                JSONObject jsonObject5;
                if (jsonObject3 != null) {
                    jsonObject5 = jsonObject3;
                }
                else {
                    jsonObject5 = new JSONObject();
                }
                while (keys.hasNext()) {
                    String s = null;
                    Object value = null;
                    Label_0326: {
                        Label_0187: {
                            try {
                                s = keys.next();
                                value = jsonObject2.get(s);
                                if (!jsonObject.has(s)) {
                                    break Label_0326;
                                }
                                if (!(value instanceof JSONObject)) {
                                    break Label_0187;
                                }
                                final JSONObject jsonObject6 = jsonObject.getJSONObject(s);
                                JSONObject jsonObject7 = null;
                                if (jsonObject3 != null) {
                                    jsonObject7 = jsonObject7;
                                    if (jsonObject3.has(s)) {
                                        jsonObject7 = jsonObject3.getJSONObject(s);
                                    }
                                }
                                final String string = generateJsonDiff(jsonObject6, (JSONObject)value, jsonObject7, set).toString();
                                if (string.equals("{}")) {
                                    continue;
                                }
                                jsonObject5.put(s, (Object)new JSONObject(string));
                            }
                            catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                            continue;
                        }
                        if (value instanceof JSONArray) {
                            handleJsonArray(s, (JSONArray)value, jsonObject.getJSONArray(s), jsonObject5);
                            continue;
                        }
                        if (set != null && set.contains(s)) {
                            jsonObject5.put(s, value);
                            continue;
                        }
                        final Object value2 = jsonObject.get(s);
                        if (value.equals(value2)) {
                            continue;
                        }
                        if (!(value2 instanceof Integer) || "".equals(value)) {
                            jsonObject5.put(s, value);
                            continue;
                        }
                        if (((Number)value2).doubleValue() != ((Number)value).doubleValue()) {
                            jsonObject5.put(s, value);
                            continue;
                        }
                        continue;
                    }
                    if (value instanceof JSONObject) {
                        jsonObject5.put(s, (Object)new JSONObject(value.toString()));
                    }
                    else if (value instanceof JSONArray) {
                        handleJsonArray(s, (JSONArray)value, null, jsonObject5);
                    }
                    else {
                        jsonObject5.put(s, value);
                    }
                }
                return jsonObject5;
            }
        }
        return jsonObject4;
    }
    
    static JSONObject getJSONObjectWithoutBlankValues(JSONObject jsonObject, String optJSONObject) {
        if (!jsonObject.has(optJSONObject)) {
            jsonObject = null;
        }
        else {
            final JSONObject jsonObject2 = new JSONObject();
            optJSONObject = (String)jsonObject.optJSONObject(optJSONObject);
            final Iterator keys = ((JSONObject)optJSONObject).keys();
            while (true) {
                jsonObject = jsonObject2;
                if (!keys.hasNext()) {
                    break;
                }
                final String s = keys.next();
                try {
                    final Object value = ((JSONObject)optJSONObject).get(s);
                    if ("".equals(value)) {
                        continue;
                    }
                    jsonObject2.put(s, value);
                }
                catch (Throwable t) {}
            }
        }
        return jsonObject;
    }
    
    private static void handleJsonArray(final String s, final JSONArray jsonArray, final JSONArray jsonArray2, final JSONObject jsonObject) throws JSONException {
        if (s.endsWith("_a") || s.endsWith("_d")) {
            jsonObject.put(s, (Object)jsonArray);
        }
        else {
            final String stringNE = toStringNE(jsonArray);
            final JSONArray jsonArray3 = new JSONArray();
            final JSONArray jsonArray4 = new JSONArray();
            String stringNE2;
            if (jsonArray2 == null) {
                stringNE2 = null;
            }
            else {
                stringNE2 = toStringNE(jsonArray2);
            }
            for (int i = 0; i < jsonArray.length(); ++i) {
                final String s2 = (String)jsonArray.get(i);
                if (jsonArray2 == null || !stringNE2.contains(s2)) {
                    jsonArray3.put((Object)s2);
                }
            }
            if (jsonArray2 != null) {
                for (int j = 0; j < jsonArray2.length(); ++j) {
                    final String string = jsonArray2.getString(j);
                    if (!stringNE.contains(string)) {
                        jsonArray4.put((Object)string);
                    }
                }
            }
            if (!jsonArray3.toString().equals("[]")) {
                jsonObject.put(s + "_a", (Object)jsonArray3);
            }
            if (!jsonArray4.toString().equals("[]")) {
                jsonObject.put(s + "_d", (Object)jsonArray4);
            }
        }
    }
    
    static String toStringNE(final JSONArray jsonArray) {
        String string = "[";
        int i = 0;
        try {
            while (i < jsonArray.length()) {
                string = string + "\"" + jsonArray.getString(i) + "\"";
                ++i;
            }
        }
        catch (Throwable t) {}
        return string + "]";
    }
}
