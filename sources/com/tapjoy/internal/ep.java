package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.fi.C2926a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ep extends et {
    /* renamed from: c */
    private static final String f7677c = ep.class.getSimpleName();

    public ep(String str, String str2) {
        super(str, str2, "ad");
    }

    /* renamed from: a */
    public final C2926a m7668a(String str, JSONObject jSONObject) {
        return m7662a(str, m7666a(jSONObject), m7667b(jSONObject));
    }

    /* renamed from: b */
    public final C2926a m7669b(String str, JSONObject jSONObject) {
        return m7665b(str, m7666a(jSONObject), m7667b(jSONObject));
    }

    /* renamed from: a */
    public static Map m7666a(JSONObject jSONObject) {
        Map hashMap = new HashMap();
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(String.USAGE_TRACKER_DIMENSIONS);
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    hashMap.put(str, jSONObject2.get(str));
                }
            } catch (JSONException e) {
                TapjoyLog.m7126d(f7677c, "Unable to getAdUnitDimensions. Invalid params: " + e);
            }
        }
        return hashMap;
    }

    /* renamed from: b */
    public static Map m7667b(JSONObject jSONObject) {
        Map hashMap = new HashMap();
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(String.USAGE_TRACKER_VALUES);
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    hashMap.put(str, Long.valueOf(jSONObject2.getLong(str)));
                }
            } catch (JSONException e) {
                TapjoyLog.m7126d(f7677c, "Unable to getAdUnitValues. Invalid params: " + e);
            }
        }
        return hashMap;
    }
}
