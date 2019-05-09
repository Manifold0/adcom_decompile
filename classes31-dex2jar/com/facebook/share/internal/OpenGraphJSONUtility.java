// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.share.model.SharePhoto;
import android.support.annotation.Nullable;
import com.facebook.share.model.ShareOpenGraphObject;
import org.json.JSONObject;
import com.facebook.share.model.ShareOpenGraphAction;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.List;

public final class OpenGraphJSONUtility
{
    private OpenGraphJSONUtility() {
    }
    
    private static JSONArray toJSONArray(final List list, final PhotoJSONProcessor photoJSONProcessor) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.put(toJSONValue(iterator.next(), photoJSONProcessor));
        }
        return jsonArray;
    }
    
    public static JSONObject toJSONObject(final ShareOpenGraphAction shareOpenGraphAction, final PhotoJSONProcessor photoJSONProcessor) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : shareOpenGraphAction.keySet()) {
            jsonObject.put(s, toJSONValue(shareOpenGraphAction.get(s), photoJSONProcessor));
        }
        return jsonObject;
    }
    
    private static JSONObject toJSONObject(final ShareOpenGraphObject shareOpenGraphObject, final PhotoJSONProcessor photoJSONProcessor) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : shareOpenGraphObject.keySet()) {
            jsonObject.put(s, toJSONValue(shareOpenGraphObject.get(s), photoJSONProcessor));
        }
        return jsonObject;
    }
    
    public static Object toJSONValue(@Nullable final Object o, final PhotoJSONProcessor photoJSONProcessor) throws JSONException {
        Object null;
        if (o == null) {
            null = JSONObject.NULL;
        }
        else {
            null = o;
            if (!(o instanceof String)) {
                null = o;
                if (!(o instanceof Boolean)) {
                    null = o;
                    if (!(o instanceof Double)) {
                        null = o;
                        if (!(o instanceof Float)) {
                            null = o;
                            if (!(o instanceof Integer)) {
                                null = o;
                                if (!(o instanceof Long)) {
                                    if (o instanceof SharePhoto) {
                                        if (photoJSONProcessor != null) {
                                            return photoJSONProcessor.toJSONObject((SharePhoto)o);
                                        }
                                        return null;
                                    }
                                    else {
                                        if (o instanceof ShareOpenGraphObject) {
                                            return toJSONObject((ShareOpenGraphObject)o, photoJSONProcessor);
                                        }
                                        if (o instanceof List) {
                                            return toJSONArray((List)o, photoJSONProcessor);
                                        }
                                        throw new IllegalArgumentException("Invalid object found for JSON serialization: " + o.toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public interface PhotoJSONProcessor
    {
        JSONObject toJSONObject(final SharePhoto p0);
    }
}
