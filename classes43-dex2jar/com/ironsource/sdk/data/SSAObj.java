// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.data;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

public class SSAObj
{
    private JSONObject mJsonObject;
    
    public SSAObj() {
        this.mJsonObject = new JSONObject();
    }
    
    public SSAObj(final String jsonObject) {
        this.setJsonObject(jsonObject);
    }
    
    private Object fromJson(final Object o) throws JSONException {
        Object o2;
        if (o == JSONObject.NULL) {
            o2 = null;
        }
        else {
            if (o instanceof JSONObject) {
                return this.toMap((JSONObject)o);
            }
            o2 = o;
            if (o instanceof JSONArray) {
                return this.toList((JSONArray)o);
            }
        }
        return o2;
    }
    
    private Map<String, Object> getMap(final JSONObject jsonObject, final String s) throws JSONException {
        return this.toMap(jsonObject.getJSONObject(s));
    }
    
    public static boolean isEmptyObject(final JSONObject jsonObject) {
        return jsonObject.names() == null;
    }
    
    private void setJsonObject(final String s) {
        try {
            this.mJsonObject = new JSONObject(s);
        }
        catch (Exception ex) {
            this.mJsonObject = new JSONObject();
        }
    }
    
    public static Object toJSON(Object next) throws JSONException {
        if (next instanceof Map) {
            final JSONObject jsonObject = new JSONObject();
            final Map map = (Map)next;
            final Iterator<Object> iterator = map.keySet().iterator();
            while (true) {
                next = jsonObject;
                if (!iterator.hasNext()) {
                    break;
                }
                next = iterator.next();
                if (next == null) {
                    continue;
                }
                jsonObject.put(next.toString(), toJSON(map.get(next)));
            }
        }
        else if (next instanceof Iterable) {
            final JSONArray jsonArray = new JSONArray();
            final Iterator<Object> iterator2 = (Iterator<Object>)((Iterable)next).iterator();
            while (true) {
                next = jsonArray;
                if (!iterator2.hasNext()) {
                    break;
                }
                jsonArray.put(iterator2.next());
            }
        }
        return next;
    }
    
    private Map<String, Object> toMap(final JSONObject jsonObject) throws JSONException {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            hashMap.put(s, this.fromJson(jsonObject.get(s)));
        }
        return hashMap;
    }
    
    public boolean containsKey(final String s) {
        return this.getJsonObject().has(s);
    }
    
    public Object get(final String s) {
        try {
            return this.getJsonObject().get(s);
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    public boolean getBoolean(final String s) {
        try {
            return this.mJsonObject.getBoolean(s);
        }
        catch (JSONException ex) {
            return false;
        }
    }
    
    public JSONObject getJsonObject() {
        return this.mJsonObject;
    }
    
    public String getString(String string) {
        try {
            string = this.mJsonObject.getString(string);
            return string;
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    public String getString(final String s, final String s2) {
        return this.mJsonObject.optString(s, s2);
    }
    
    public boolean isNull(final String s) {
        return this.getJsonObject().isNull(s);
    }
    
    public void put(final String s, final String s2) {
        try {
            this.mJsonObject.put(s, (Object)s2);
        }
        catch (Exception ex) {}
    }
    
    public void put(final String s, final JSONObject jsonObject) {
        try {
            this.mJsonObject.put(s, (Object)jsonObject);
        }
        catch (Exception ex) {}
    }
    
    public List toList(final JSONArray jsonArray) throws JSONException {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(this.fromJson(jsonArray.get(i)));
        }
        return list;
    }
    
    @Override
    public String toString() {
        if (this.mJsonObject == null) {
            return "";
        }
        return this.mJsonObject.toString();
    }
}
