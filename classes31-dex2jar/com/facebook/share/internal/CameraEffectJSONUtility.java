// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import com.facebook.share.model.CameraEffectArguments;
import java.util.HashMap;
import java.util.Map;

public class CameraEffectJSONUtility
{
    private static final Map<Class<?>, Setter> SETTERS;
    
    static {
        (SETTERS = new HashMap<Class<?>, Setter>()).put(String.class, new Setter() {
            @Override
            public void setOnArgumentsBuilder(final CameraEffectArguments.Builder builder, final String s, final Object o) throws JSONException {
                builder.putArgument(s, (String)o);
            }
            
            @Override
            public void setOnJSON(final JSONObject jsonObject, final String s, final Object o) throws JSONException {
                jsonObject.put(s, o);
            }
        });
        CameraEffectJSONUtility.SETTERS.put(String[].class, (Setter)new Setter() {
            @Override
            public void setOnArgumentsBuilder(final CameraEffectArguments.Builder builder, final String s, final Object o) throws JSONException {
                throw new IllegalArgumentException("Unexpected type from JSON");
            }
            
            @Override
            public void setOnJSON(final JSONObject jsonObject, final String s, final Object o) throws JSONException {
                final JSONArray jsonArray = new JSONArray();
                final String[] array = (String[])o;
                for (int length = array.length, i = 0; i < length; ++i) {
                    jsonArray.put((Object)array[i]);
                }
                jsonObject.put(s, (Object)jsonArray);
            }
        });
        CameraEffectJSONUtility.SETTERS.put(JSONArray.class, (Setter)new Setter() {
            @Override
            public void setOnArgumentsBuilder(final CameraEffectArguments.Builder builder, final String s, final Object o) throws JSONException {
                final JSONArray jsonArray = (JSONArray)o;
                final String[] array = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); ++i) {
                    final Object value = jsonArray.get(i);
                    if (!(value instanceof String)) {
                        throw new IllegalArgumentException("Unexpected type in an array: " + ((String)value).getClass());
                    }
                    array[i] = (String)value;
                }
                builder.putArgument(s, array);
            }
            
            @Override
            public void setOnJSON(final JSONObject jsonObject, final String s, final Object o) throws JSONException {
                throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
            }
        });
    }
    
    public static CameraEffectArguments convertToCameraEffectArguments(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        final CameraEffectArguments.Builder builder = new CameraEffectArguments.Builder();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            final Object value = jsonObject.get(s);
            if (value != null && value != JSONObject.NULL) {
                final Setter setter = CameraEffectJSONUtility.SETTERS.get(value.getClass());
                if (setter == null) {
                    throw new IllegalArgumentException("Unsupported type: " + value.getClass());
                }
                setter.setOnArgumentsBuilder(builder, s, value);
            }
        }
        return builder.build();
    }
    
    public static JSONObject convertToJSON(final CameraEffectArguments cameraEffectArguments) throws JSONException {
        if (cameraEffectArguments != null) {
            final JSONObject jsonObject = new JSONObject();
            final Iterator<String> iterator = cameraEffectArguments.keySet().iterator();
            Object value;
            while (true) {
                final JSONObject jsonObject2 = jsonObject;
                if (!iterator.hasNext()) {
                    return jsonObject2;
                }
                final String s = iterator.next();
                value = cameraEffectArguments.get(s);
                if (value == null) {
                    continue;
                }
                final Setter setter = CameraEffectJSONUtility.SETTERS.get(value.getClass());
                if (setter == null) {
                    break;
                }
                setter.setOnJSON(jsonObject, s, value);
            }
            throw new IllegalArgumentException("Unsupported type: " + value.getClass());
        }
        return null;
    }
    
    public interface Setter
    {
        void setOnArgumentsBuilder(final CameraEffectArguments.Builder p0, final String p1, final Object p2) throws JSONException;
        
        void setOnJSON(final JSONObject p0, final String p1, final Object p2) throws JSONException;
    }
}
