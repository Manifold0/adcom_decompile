// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import com.facebook.FacebookRequestError;
import java.util.HashSet;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Set;
import java.util.Map;

public final class FacebookRequestErrorClassification
{
    public static final int EC_APP_NOT_INSTALLED = 412;
    public static final int EC_APP_TOO_MANY_CALLS = 4;
    public static final int EC_INVALID_SESSION = 102;
    public static final int EC_INVALID_TOKEN = 190;
    public static final int EC_RATE = 9;
    public static final int EC_SERVICE_UNAVAILABLE = 2;
    public static final int EC_TOO_MANY_USER_ACTION_CALLS = 341;
    public static final int EC_USER_TOO_MANY_CALLS = 17;
    public static final int ESC_APP_INACTIVE = 493;
    public static final int ESC_APP_NOT_INSTALLED = 458;
    public static final String KEY_LOGIN_RECOVERABLE = "login_recoverable";
    public static final String KEY_NAME = "name";
    public static final String KEY_OTHER = "other";
    public static final String KEY_RECOVERY_MESSAGE = "recovery_message";
    public static final String KEY_TRANSIENT = "transient";
    private static FacebookRequestErrorClassification defaultInstance;
    private final Map<Integer, Set<Integer>> loginRecoverableErrors;
    private final String loginRecoverableRecoveryMessage;
    private final Map<Integer, Set<Integer>> otherErrors;
    private final String otherRecoveryMessage;
    private final Map<Integer, Set<Integer>> transientErrors;
    private final String transientRecoveryMessage;
    
    FacebookRequestErrorClassification(final Map<Integer, Set<Integer>> otherErrors, final Map<Integer, Set<Integer>> transientErrors, final Map<Integer, Set<Integer>> loginRecoverableErrors, final String otherRecoveryMessage, final String transientRecoveryMessage, final String loginRecoverableRecoveryMessage) {
        this.otherErrors = otherErrors;
        this.transientErrors = transientErrors;
        this.loginRecoverableErrors = loginRecoverableErrors;
        this.otherRecoveryMessage = otherRecoveryMessage;
        this.transientRecoveryMessage = transientRecoveryMessage;
        this.loginRecoverableRecoveryMessage = loginRecoverableRecoveryMessage;
    }
    
    public static FacebookRequestErrorClassification createFromJSON(final JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        }
        Map<Integer, Set<Integer>> map = null;
        Map<Integer, Set<Integer>> map2 = null;
        Map<Integer, Set<Integer>> map3 = null;
        String s = null;
        String s2 = null;
        String s3 = null;
        String optString;
        String optString2;
        String optString3;
        Map<Integer, Set<Integer>> jsonDefinition;
        Map<Integer, Set<Integer>> jsonDefinition2;
        Map<Integer, Set<Integer>> jsonDefinition3;
        for (int i = 0; i < jsonArray.length(); ++i, map = jsonDefinition3, map2 = jsonDefinition2, map3 = jsonDefinition, s = optString3, s2 = optString2, s3 = optString) {
            final JSONObject optJSONObject = jsonArray.optJSONObject(i);
            if (optJSONObject == null) {
                optString = s3;
                optString2 = s2;
                optString3 = s;
                jsonDefinition = map3;
                jsonDefinition2 = map2;
                jsonDefinition3 = map;
            }
            else {
                final String optString4 = optJSONObject.optString("name");
                jsonDefinition3 = map;
                jsonDefinition2 = map2;
                jsonDefinition = map3;
                optString3 = s;
                optString2 = s2;
                optString = s3;
                if (optString4 != null) {
                    if (optString4.equalsIgnoreCase("other")) {
                        optString3 = optJSONObject.optString("recovery_message", (String)null);
                        jsonDefinition3 = parseJSONDefinition(optJSONObject);
                        jsonDefinition2 = map2;
                        jsonDefinition = map3;
                        optString2 = s2;
                        optString = s3;
                    }
                    else if (optString4.equalsIgnoreCase("transient")) {
                        optString2 = optJSONObject.optString("recovery_message", (String)null);
                        jsonDefinition2 = parseJSONDefinition(optJSONObject);
                        jsonDefinition3 = map;
                        jsonDefinition = map3;
                        optString3 = s;
                        optString = s3;
                    }
                    else {
                        jsonDefinition3 = map;
                        jsonDefinition2 = map2;
                        jsonDefinition = map3;
                        optString3 = s;
                        optString2 = s2;
                        optString = s3;
                        if (optString4.equalsIgnoreCase("login_recoverable")) {
                            optString = optJSONObject.optString("recovery_message", (String)null);
                            jsonDefinition = parseJSONDefinition(optJSONObject);
                            jsonDefinition3 = map;
                            jsonDefinition2 = map2;
                            optString3 = s;
                            optString2 = s2;
                        }
                    }
                }
            }
        }
        return new FacebookRequestErrorClassification(map, map2, map3, s, s2, s3);
    }
    
    public static FacebookRequestErrorClassification getDefaultErrorClassification() {
        synchronized (FacebookRequestErrorClassification.class) {
            if (FacebookRequestErrorClassification.defaultInstance == null) {
                FacebookRequestErrorClassification.defaultInstance = getDefaultErrorClassificationImpl();
            }
            return FacebookRequestErrorClassification.defaultInstance;
        }
    }
    
    private static FacebookRequestErrorClassification getDefaultErrorClassificationImpl() {
        return new FacebookRequestErrorClassification(null, new HashMap<Integer, Set<Integer>>() {
            {
                this.put(2, null);
                this.put(4, null);
                this.put(9, null);
                this.put(17, null);
                this.put(341, null);
            }
        }, new HashMap<Integer, Set<Integer>>() {
            {
                this.put(102, null);
                this.put(190, null);
                this.put(412, null);
            }
        }, null, null, null);
    }
    
    private static Map<Integer, Set<Integer>> parseJSONDefinition(JSONObject optJSONObject) {
        final JSONArray optJSONArray = optJSONObject.optJSONArray("items");
        Map<Integer, Set<Integer>> map;
        if (optJSONArray.length() == 0) {
            map = null;
        }
        else {
            final HashMap<Integer, Set<Integer>> hashMap = new HashMap<Integer, Set<Integer>>();
            int n = 0;
            while (true) {
                map = hashMap;
                if (n >= optJSONArray.length()) {
                    break;
                }
                optJSONObject = optJSONArray.optJSONObject(n);
                if (optJSONObject != null) {
                    final int optInt = optJSONObject.optInt("code");
                    if (optInt != 0) {
                        final Set<Integer> set = null;
                        final JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                        Set<Integer> set2 = set;
                        if (optJSONArray2 != null) {
                            set2 = set;
                            if (optJSONArray2.length() > 0) {
                                final HashSet<Integer> set3 = new HashSet<Integer>();
                                int n2 = 0;
                                while (true) {
                                    set2 = set3;
                                    if (n2 >= optJSONArray2.length()) {
                                        break;
                                    }
                                    final int optInt2 = optJSONArray2.optInt(n2);
                                    if (optInt2 != 0) {
                                        set3.add(optInt2);
                                    }
                                    ++n2;
                                }
                            }
                        }
                        hashMap.put(optInt, set2);
                    }
                }
                ++n;
            }
        }
        return map;
    }
    
    public FacebookRequestError.Category classify(final int n, final int n2, final boolean b) {
        if (b) {
            return FacebookRequestError.Category.TRANSIENT;
        }
        if (this.otherErrors != null && this.otherErrors.containsKey(n)) {
            final Set<Integer> set = this.otherErrors.get(n);
            if (set == null || set.contains(n2)) {
                return FacebookRequestError.Category.OTHER;
            }
        }
        if (this.loginRecoverableErrors != null && this.loginRecoverableErrors.containsKey(n)) {
            final Set<Integer> set2 = this.loginRecoverableErrors.get(n);
            if (set2 == null || set2.contains(n2)) {
                return FacebookRequestError.Category.LOGIN_RECOVERABLE;
            }
        }
        if (this.transientErrors != null && this.transientErrors.containsKey(n)) {
            final Set<Integer> set3 = this.transientErrors.get(n);
            if (set3 == null || set3.contains(n2)) {
                return FacebookRequestError.Category.TRANSIENT;
            }
        }
        return FacebookRequestError.Category.OTHER;
    }
    
    public Map<Integer, Set<Integer>> getLoginRecoverableErrors() {
        return this.loginRecoverableErrors;
    }
    
    public Map<Integer, Set<Integer>> getOtherErrors() {
        return this.otherErrors;
    }
    
    public String getRecoveryMessage(final FacebookRequestError.Category category) {
        switch (category) {
            default: {
                return null;
            }
            case OTHER: {
                return this.otherRecoveryMessage;
            }
            case LOGIN_RECOVERABLE: {
                return this.loginRecoverableRecoveryMessage;
            }
            case TRANSIENT: {
                return this.transientRecoveryMessage;
            }
        }
    }
    
    public Map<Integer, Set<Integer>> getTransientErrors() {
        return this.transientErrors;
    }
}
