// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import org.json.JSONObject;
import java.util.Set;

abstract class UserState
{
    static final int DEVICE_TYPE_ANDROID = 1;
    static final int DEVICE_TYPE_FIREOS = 2;
    private static final String[] LOCATION_FIELDS;
    private static final Set<String> LOCATION_FIELDS_SET;
    static final int PUSH_STATUS_FIREBASE_FCM_ERROR_IOEXCEPTION = -11;
    static final int PUSH_STATUS_FIREBASE_FCM_ERROR_MISC_EXCEPTION = -12;
    static final int PUSH_STATUS_FIREBASE_FCM_ERROR_SERVICE_NOT_AVAILABLE = -9;
    static final int PUSH_STATUS_FIREBASE_FCM_INIT_ERROR = -8;
    static final int PUSH_STATUS_INVALID_FCM_SENDER_ID = -6;
    static final int PUSH_STATUS_MISSING_ANDROID_SUPPORT_LIBRARY = -3;
    static final int PUSH_STATUS_MISSING_FIREBASE_FCM_LIBRARY = -4;
    static final int PUSH_STATUS_NO_PERMISSION = 0;
    static final int PUSH_STATUS_OUTDATED_ANDROID_SUPPORT_LIBRARY = -5;
    static final int PUSH_STATUS_OUTDATED_GOOGLE_PLAY_SERVICES_APP = -7;
    static final int PUSH_STATUS_SUBSCRIBED = 1;
    static final int PUSH_STATUS_UNSUBSCRIBE = -2;
    private static final Object syncLock;
    JSONObject dependValues;
    private String persistKey;
    JSONObject syncValues;
    
    static {
        LOCATION_FIELDS = new String[] { "lat", "long", "loc_acc", "loc_type", "loc_bg", "loc_time_stamp", "ad_id" };
        LOCATION_FIELDS_SET = new HashSet<String>(Arrays.asList(UserState.LOCATION_FIELDS));
        syncLock = new Object() {};
    }
    
    UserState(final String persistKey, final boolean b) {
        this.persistKey = persistKey;
        if (b) {
            this.loadState();
            return;
        }
        this.dependValues = new JSONObject();
        this.syncValues = new JSONObject();
    }
    
    private static JSONObject generateJsonDiff(JSONObject generateJsonDiff, final JSONObject jsonObject, final JSONObject jsonObject2, final Set<String> set) {
        synchronized (UserState.syncLock) {
            generateJsonDiff = JSONUtils.generateJsonDiff(generateJsonDiff, jsonObject, jsonObject2, set);
            return generateJsonDiff;
        }
    }
    
    private Set<String> getGroupChangeFields(final UserState userState) {
        try {
            if (this.dependValues.optLong("loc_time_stamp") != userState.dependValues.getLong("loc_time_stamp")) {
                userState.syncValues.put("loc_bg", userState.dependValues.opt("loc_bg"));
                userState.syncValues.put("loc_time_stamp", userState.dependValues.opt("loc_time_stamp"));
                return UserState.LOCATION_FIELDS_SET;
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    private void loadState() {
        final String string = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, null);
        Label_0180: {
            if (string != null) {
                break Label_0180;
            }
        Label_0096_Outer:
            while (true) {
                this.dependValues = new JSONObject();
                boolean b = true;
                while (true) {
                    int n = 0;
                Label_0096:
                    while (true) {
                        try {
                            if (this.persistKey.equals("CURRENT_STATE")) {
                                n = OneSignalPrefs.getInt(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SUBSCRIPTION", 1);
                                break Label_0096;
                            }
                            Label_0167: {
                                break Label_0167;
                            Label_0206_Outer:
                                while (true) {
                                    while (true) {
                                        try {
                                            (this.syncValues = new JSONObject()).put("identifier", (Object)OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_REGISTRATION_ID", null));
                                            return;
                                            final String string2;
                                            this.syncValues = new JSONObject(string2);
                                            return;
                                            try {
                                                this.dependValues = new JSONObject(string);
                                            }
                                            catch (JSONException ex) {
                                                ex.printStackTrace();
                                            }
                                            break Label_0096;
                                            n = OneSignalPrefs.getInt(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SYNCED_SUBSCRIPTION", 1);
                                        }
                                        catch (JSONException ex2) {
                                            ex2.printStackTrace();
                                            return;
                                        }
                                        final String string2 = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, null);
                                        if (string2 == null) {
                                            continue Label_0206_Outer;
                                        }
                                        continue Label_0096_Outer;
                                    }
                                }
                            }
                            final int n2;
                            this.dependValues.put("subscribableStatus", n2);
                            this.dependValues.put("userSubscribePref", b);
                            continue Label_0096;
                        }
                        catch (JSONException ex3) {
                            continue Label_0096;
                        }
                        break;
                    }
                    int n2 = n;
                    if (n == -2) {
                        n2 = 1;
                        b = false;
                        continue;
                    }
                    continue;
                }
            }
        }
    }
    
    private void modifySyncValuesJsonArray(final String s) {
        while (true) {
            while (true) {
                JSONArray jsonArray;
                try {
                    if (this.syncValues.has(s)) {
                        jsonArray = this.syncValues.getJSONArray(s);
                    }
                    else {
                        jsonArray = new JSONArray();
                    }
                    final JSONArray jsonArray2 = new JSONArray();
                    if (this.syncValues.has(s + "_d")) {
                        final String stringNE = JSONUtils.toStringNE(this.syncValues.getJSONArray(s + "_d"));
                        int n = 0;
                        JSONArray jsonArray3;
                        while (true) {
                            jsonArray3 = jsonArray2;
                            if (n >= jsonArray.length()) {
                                break;
                            }
                            if (!stringNE.contains(jsonArray.getString(n))) {
                                jsonArray2.put(jsonArray.get(n));
                            }
                            ++n;
                        }
                        if (this.syncValues.has(s + "_a")) {
                            final JSONArray jsonArray4 = this.syncValues.getJSONArray(s + "_a");
                            for (int i = 0; i < jsonArray4.length(); ++i) {
                                jsonArray3.put(jsonArray4.get(i));
                            }
                        }
                        this.syncValues.put(s, (Object)jsonArray3);
                        this.syncValues.remove(s + "_a");
                        this.syncValues.remove(s + "_d");
                        return;
                    }
                }
                catch (Throwable t) {
                    return;
                }
                JSONArray jsonArray3 = jsonArray;
                continue;
            }
        }
    }
    
    protected abstract void addDependFields();
    
    void clearLocation() {
        try {
            this.syncValues.put("lat", (Object)null);
            this.syncValues.put("long", (Object)null);
            this.syncValues.put("loc_acc", (Object)null);
            this.syncValues.put("loc_type", (Object)null);
            this.syncValues.put("loc_bg", (Object)null);
            this.syncValues.put("loc_time_stamp", (Object)null);
            this.dependValues.put("loc_bg", (Object)null);
            this.dependValues.put("loc_time_stamp", (Object)null);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    UserState deepClone(String instance) {
        instance = (String)this.newInstance(instance);
        try {
            ((UserState)instance).dependValues = new JSONObject(this.dependValues.toString());
            ((UserState)instance).syncValues = new JSONObject(this.syncValues.toString());
            return (UserState)instance;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return (UserState)instance;
        }
    }
    
    JSONObject generateJsonDiff(final UserState userState, final boolean b) {
        this.addDependFields();
        userState.addDependFields();
        final JSONObject generateJsonDiff = generateJsonDiff(this.syncValues, userState.syncValues, null, this.getGroupChangeFields(userState));
        JSONObject jsonObject;
        if (!b && generateJsonDiff.toString().equals("{}")) {
            jsonObject = null;
        }
        else {
            try {
                if (!generateJsonDiff.has("app_id")) {
                    generateJsonDiff.put("app_id", (Object)this.syncValues.optString("app_id"));
                }
                jsonObject = generateJsonDiff;
                if (this.syncValues.has("email_auth_hash")) {
                    generateJsonDiff.put("email_auth_hash", (Object)this.syncValues.optString("email_auth_hash"));
                    return generateJsonDiff;
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                return generateJsonDiff;
            }
        }
        return jsonObject;
    }
    
    abstract boolean isSubscribed();
    
    void mergeTags(JSONObject optJSONObject, final JSONObject jsonObject) {
        while (true) {
            JSONObject jsonObject2 = null;
            Label_0181: {
                synchronized (UserState.syncLock) {
                    if (!((JSONObject)optJSONObject).has("tags")) {
                        return;
                    }
                Label_0052:
                    while (true) {
                        Label_0133: {
                            if (!this.syncValues.has("tags")) {
                                break Label_0133;
                            }
                            while (true) {
                                while (true) {
                                    String s = null;
                                    Label_0145: {
                                        try {
                                            jsonObject2 = new JSONObject(this.syncValues.optString("tags"));
                                            optJSONObject = ((JSONObject)optJSONObject).optJSONObject("tags");
                                            final Iterator keys = ((JSONObject)optJSONObject).keys();
                                            try {
                                                while (keys.hasNext()) {
                                                    s = keys.next();
                                                    if (!"".equals(((JSONObject)optJSONObject).optString(s))) {
                                                        break Label_0145;
                                                    }
                                                    jsonObject2.remove(s);
                                                }
                                                break Label_0181;
                                            }
                                            catch (Throwable t) {}
                                            return;
                                        }
                                        catch (JSONException jsonObject2) {
                                            jsonObject2 = new JSONObject();
                                            continue Label_0052;
                                        }
                                        break;
                                    }
                                    if (jsonObject == null || !jsonObject.has(s)) {
                                        jsonObject2.put(s, (Object)((JSONObject)optJSONObject).optString(s));
                                        continue;
                                    }
                                    continue;
                                }
                            }
                        }
                        jsonObject2 = new JSONObject();
                        continue Label_0052;
                    }
                }
            }
            if (jsonObject2.toString().equals("{}")) {
                this.syncValues.remove("tags");
                return;
            }
            this.syncValues.put("tags", (Object)jsonObject2);
        }
    }
    
    abstract UserState newInstance(final String p0);
    
    void persistState() {
        synchronized (UserState.syncLock) {
            this.modifySyncValuesJsonArray("pkgs");
            OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USERSTATE_SYNCVALYES_" + this.persistKey, this.syncValues.toString());
            OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USERSTATE_DEPENDVALYES_" + this.persistKey, this.dependValues.toString());
        }
    }
    
    void persistStateAfterSync(final JSONObject jsonObject, final JSONObject jsonObject2) {
        if (jsonObject != null) {
            generateJsonDiff(this.dependValues, jsonObject, this.dependValues, null);
        }
        if (jsonObject2 != null) {
            generateJsonDiff(this.syncValues, jsonObject2, this.syncValues, null);
            this.mergeTags(jsonObject2, null);
        }
        if (jsonObject != null || jsonObject2 != null) {
            this.persistState();
        }
    }
    
    void set(final String s, final Object o) {
        try {
            this.syncValues.put(s, o);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    void setLocation(final LocationGMS.LocationPoint locationPoint) {
        try {
            this.syncValues.put("lat", (Object)locationPoint.lat);
            this.syncValues.put("long", (Object)locationPoint.log);
            this.syncValues.put("loc_acc", (Object)locationPoint.accuracy);
            this.syncValues.put("loc_type", (Object)locationPoint.type);
            this.dependValues.put("loc_bg", (Object)locationPoint.bg);
            this.dependValues.put("loc_time_stamp", (Object)locationPoint.timeStamp);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
