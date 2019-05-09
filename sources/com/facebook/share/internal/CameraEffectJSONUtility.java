package com.facebook.share.internal;

import com.facebook.share.model.CameraEffectArguments;
import com.facebook.share.model.CameraEffectArguments.Builder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraEffectJSONUtility {
    private static final Map<Class<?>, Setter> SETTERS = new HashMap();

    public interface Setter {
        void setOnArgumentsBuilder(Builder builder, String str, Object obj) throws JSONException;

        void setOnJSON(JSONObject jSONObject, String str, Object obj) throws JSONException;
    }

    /* renamed from: com.facebook.share.internal.CameraEffectJSONUtility$1 */
    static class C03371 implements Setter {
        C03371() {
        }

        public void setOnArgumentsBuilder(Builder builder, String key, Object value) throws JSONException {
            builder.putArgument(key, (String) value);
        }

        public void setOnJSON(JSONObject json, String key, Object value) throws JSONException {
            json.put(key, value);
        }
    }

    /* renamed from: com.facebook.share.internal.CameraEffectJSONUtility$2 */
    static class C03382 implements Setter {
        C03382() {
        }

        public void setOnArgumentsBuilder(Builder builder, String key, Object value) throws JSONException {
            throw new IllegalArgumentException("Unexpected type from JSON");
        }

        public void setOnJSON(JSONObject json, String key, Object value) throws JSONException {
            JSONArray jsonArray = new JSONArray();
            for (String stringValue : (String[]) value) {
                jsonArray.put(stringValue);
            }
            json.put(key, jsonArray);
        }
    }

    /* renamed from: com.facebook.share.internal.CameraEffectJSONUtility$3 */
    static class C03393 implements Setter {
        C03393() {
        }

        public void setOnArgumentsBuilder(Builder builder, String key, Object value) throws JSONException {
            JSONArray jsonArray = (JSONArray) value;
            String[] argsArray = new String[jsonArray.length()];
            int i = 0;
            while (i < jsonArray.length()) {
                Object current = jsonArray.get(i);
                if (current instanceof String) {
                    argsArray[i] = (String) current;
                    i++;
                } else {
                    throw new IllegalArgumentException("Unexpected type in an array: " + current.getClass());
                }
            }
            builder.putArgument(key, argsArray);
        }

        public void setOnJSON(JSONObject json, String key, Object value) throws JSONException {
            throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
        }
    }

    static {
        SETTERS.put(String.class, new C03371());
        SETTERS.put(String[].class, new C03382());
        SETTERS.put(JSONArray.class, new C03393());
    }

    public static JSONObject convertToJSON(CameraEffectArguments arguments) throws JSONException {
        if (arguments == null) {
            return null;
        }
        JSONObject json = new JSONObject();
        for (String key : arguments.keySet()) {
            Object value = arguments.get(key);
            if (value != null) {
                Setter setter = (Setter) SETTERS.get(value.getClass());
                if (setter == null) {
                    throw new IllegalArgumentException("Unsupported type: " + value.getClass());
                }
                setter.setOnJSON(json, key, value);
            }
        }
        return json;
    }

    public static CameraEffectArguments convertToCameraEffectArguments(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        Builder builder = new Builder();
        Iterator<String> jsonIterator = jsonObject.keys();
        while (jsonIterator.hasNext()) {
            String key = (String) jsonIterator.next();
            Object value = jsonObject.get(key);
            if (!(value == null || value == JSONObject.NULL)) {
                Setter setter = (Setter) SETTERS.get(value.getClass());
                if (setter == null) {
                    throw new IllegalArgumentException("Unsupported type: " + value.getClass());
                }
                setter.setOnArgumentsBuilder(builder, key, value);
            }
        }
        return builder.build();
    }
}
