package com.facebook.appevents;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.Constants;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

class AppEvent implements Serializable {
    private static final long serialVersionUID = 1;
    private static final HashSet<String> validatedIdentifiers = new HashSet();
    private final String checksum;
    private final boolean isImplicit;
    private final JSONObject jsonObject;
    private final String name;

    static class SerializationProxyV1 implements Serializable {
        private static final long serialVersionUID = -2488473066578201069L;
        private final boolean isImplicit;
        private final String jsonString;

        private SerializationProxyV1(String jsonString, boolean isImplicit) {
            this.jsonString = jsonString;
            this.isImplicit = isImplicit;
        }

        private Object readResolve() throws JSONException {
            return new AppEvent(this.jsonString, this.isImplicit, null);
        }
    }

    static class SerializationProxyV2 implements Serializable {
        private static final long serialVersionUID = 20160803001L;
        private final String checksum;
        private final boolean isImplicit;
        private final String jsonString;

        private SerializationProxyV2(String jsonString, boolean isImplicit, String checksum) {
            this.jsonString = jsonString;
            this.isImplicit = isImplicit;
            this.checksum = checksum;
        }

        private Object readResolve() throws JSONException {
            return new AppEvent(this.jsonString, this.isImplicit, this.checksum);
        }
    }

    public AppEvent(String contextName, String eventName, Double valueToSum, Bundle parameters, boolean isImplicitlyLogged, @Nullable UUID currentSessionId) throws JSONException, FacebookException {
        this.jsonObject = getJSONObjectForAppEvent(contextName, eventName, valueToSum, parameters, isImplicitlyLogged, currentSessionId);
        this.isImplicit = isImplicitlyLogged;
        this.name = eventName;
        this.checksum = calculateChecksum();
    }

    public String getName() {
        return this.name;
    }

    private AppEvent(String jsonString, boolean isImplicit, String checksum) throws JSONException {
        this.jsonObject = new JSONObject(jsonString);
        this.isImplicit = isImplicit;
        this.name = this.jsonObject.optString(Constants.EVENT_NAME_EVENT_KEY);
        this.checksum = checksum;
    }

    public boolean getIsImplicit() {
        return this.isImplicit;
    }

    public JSONObject getJSONObject() {
        return this.jsonObject;
    }

    public boolean isChecksumValid() {
        if (this.checksum == null) {
            return true;
        }
        return calculateChecksum().equals(this.checksum);
    }

    private static void validateIdentifier(String identifier) throws FacebookException {
        String regex = "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$";
        if (identifier == null || identifier.length() == 0 || identifier.length() > 40) {
            if (identifier == null) {
                identifier = "<None Provided>";
            }
            throw new FacebookException(String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", new Object[]{identifier, Integer.valueOf(40)}));
        }
        synchronized (validatedIdentifiers) {
            boolean alreadyValidated = validatedIdentifiers.contains(identifier);
        }
        if (!alreadyValidated) {
            if (identifier.matches("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$")) {
                synchronized (validatedIdentifiers) {
                    validatedIdentifiers.add(identifier);
                }
                return;
            }
            throw new FacebookException(String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", new Object[]{identifier}));
        }
    }

    private static JSONObject getJSONObjectForAppEvent(String contextName, String eventName, Double valueToSum, Bundle parameters, boolean isImplicitlyLogged, @Nullable UUID currentSessionId) throws FacebookException, JSONException {
        validateIdentifier(eventName);
        JSONObject eventObject = new JSONObject();
        eventObject.put(Constants.EVENT_NAME_EVENT_KEY, eventName);
        eventObject.put(Constants.EVENT_NAME_MD5_EVENT_KEY, md5Checksum(eventName));
        eventObject.put(Constants.LOG_TIME_APP_EVENT_KEY, System.currentTimeMillis() / 1000);
        eventObject.put("_ui", contextName);
        if (currentSessionId != null) {
            eventObject.put("_session_id", currentSessionId);
        }
        if (valueToSum != null) {
            eventObject.put("_valueToSum", valueToSum.doubleValue());
        }
        if (isImplicitlyLogged) {
            eventObject.put("_implicitlyLogged", "1");
        }
        if (parameters != null) {
            for (String key : parameters.keySet()) {
                validateIdentifier(key);
                Object value = parameters.get(key);
                if ((value instanceof String) || (value instanceof Number)) {
                    eventObject.put(key, value.toString());
                } else {
                    throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", new Object[]{value, key}));
                }
            }
        }
        if (!isImplicitlyLogged) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", eventObject.toString());
        }
        return eventObject;
    }

    private Object writeReplace() {
        return new SerializationProxyV2(this.jsonObject.toString(), this.isImplicit, this.checksum);
    }

    public String toString() {
        return String.format("\"%s\", implicit: %b, json: %s", new Object[]{this.jsonObject.optString(Constants.EVENT_NAME_EVENT_KEY), Boolean.valueOf(this.isImplicit), this.jsonObject.toString()});
    }

    private String calculateChecksum() {
        if (VERSION.SDK_INT > 19) {
            return md5Checksum(this.jsonObject.toString());
        }
        ArrayList<String> keys = new ArrayList();
        Iterator<String> iterator = this.jsonObject.keys();
        while (iterator.hasNext()) {
            keys.add(iterator.next());
        }
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            sb.append(key).append(" = ").append(this.jsonObject.optString(key)).append('\n');
        }
        return md5Checksum(sb.toString());
    }

    private static String md5Checksum(String toHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance(com.adjust.sdk.Constants.MD5);
            byte[] bytes = toHash.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            return bytesToHex(digest.digest());
        } catch (Exception e) {
            Utility.logd("Failed to generate checksum: ", e);
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        } catch (Exception e2) {
            Utility.logd("Failed to generate checksum: ", e2);
            return "1";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(bytes[i])}));
        }
        return sb.toString();
    }
}
