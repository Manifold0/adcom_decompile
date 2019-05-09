// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import android.net.Uri;
import com.unity3d.services.core.properties.ClientProperties;
import org.json.JSONException;
import org.json.JSONArray;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import org.json.JSONObject;
import android.app.Activity;
import java.lang.ref.WeakReference;

public class Intent
{
    private static WeakReference<Activity> _activeActivity;
    
    @WebViewExposed
    public static void canOpenIntent(final JSONObject jsonObject, final WebViewCallback webViewCallback) {
        try {
            webViewCallback.invoke(checkIntentResolvable(intentFromMetadata(jsonObject)));
        }
        catch (IntentException ex) {
            DeviceLog.exception("Couldn't resolve intent", ex);
            webViewCallback.error(ex.getError(), ex.getField());
        }
    }
    
    @WebViewExposed
    public static void canOpenIntents(final JSONArray jsonArray, final WebViewCallback webViewCallback) {
        final JSONObject jsonObject = new JSONObject();
        final int length = jsonArray.length();
        int i = 0;
        while (i < length) {
            final JSONObject optJSONObject = jsonArray.optJSONObject(i);
            final String optString = optJSONObject.optString("id");
            try {
                jsonObject.put(optString, checkIntentResolvable(intentFromMetadata(optJSONObject)));
                ++i;
                continue;
            }
            catch (IntentException ex) {
                DeviceLog.exception("Exception parsing intent", ex);
                webViewCallback.error(ex.getError(), ex.getField());
                return;
            }
            catch (JSONException ex2) {
                webViewCallback.error(IntentError.JSON_EXCEPTION, ex2.getMessage());
                return;
            }
            break;
        }
        webViewCallback.invoke(jsonObject);
    }
    
    private static boolean checkIntentResolvable(final android.content.Intent intent) {
        boolean b = false;
        if (ClientProperties.getApplicationContext().getPackageManager().resolveActivity(intent, 0) != null) {
            b = true;
        }
        return b;
    }
    
    private static Activity getStartingActivity() {
        Activity activity = null;
        if (Intent._activeActivity != null && Intent._activeActivity.get() != null) {
            activity = Intent._activeActivity.get();
        }
        else if (ClientProperties.getActivity() != null) {
            return ClientProperties.getActivity();
        }
        return activity;
    }
    
    private static android.content.Intent intentFromMetadata(final JSONObject jsonObject) throws IntentException {
        final String s = (String)jsonObject.opt("className");
        final String s2 = (String)jsonObject.opt("packageName");
        final String action = (String)jsonObject.opt("action");
        final String s3 = (String)jsonObject.opt("uri");
        final String type = (String)jsonObject.opt("mimeType");
        final JSONArray jsonArray = (JSONArray)jsonObject.opt("categories");
        final Integer n = (Integer)jsonObject.opt("flags");
        final JSONArray jsonArray2 = (JSONArray)jsonObject.opt("extras");
        android.content.Intent intent;
        if (s2 != null && s == null && action == null && type == null) {
            final android.content.Intent launchIntentForPackage = ClientProperties.getApplicationContext().getPackageManager().getLaunchIntentForPackage(s2);
            if ((intent = launchIntentForPackage) != null) {
                intent = launchIntentForPackage;
                if (n > -1) {
                    launchIntentForPackage.addFlags((int)n);
                    intent = launchIntentForPackage;
                }
            }
        }
        else {
            final android.content.Intent intent2 = new android.content.Intent();
            if (s != null && s2 != null) {
                intent2.setClassName(s2, s);
            }
            if (action != null) {
                intent2.setAction(action);
            }
            if (s3 != null) {
                intent2.setData(Uri.parse(s3));
            }
            if (type != null) {
                intent2.setType(type);
            }
            if (n != null && n > -1) {
                intent2.setFlags((int)n);
            }
            if (!setCategories(intent2, jsonArray)) {
                throw new IntentException(IntentError.COULDNT_PARSE_CATEGORIES, jsonArray);
            }
            intent = intent2;
            if (!setExtras(intent2, jsonArray2)) {
                throw new IntentException(IntentError.COULDNT_PARSE_EXTRAS, jsonArray2);
            }
        }
        return intent;
    }
    
    @WebViewExposed
    public static void launch(final JSONObject jsonObject, final WebViewCallback webViewCallback) {
        final String s = (String)jsonObject.opt("className");
        final String s2 = (String)jsonObject.opt("packageName");
        final String action = (String)jsonObject.opt("action");
        final String s3 = (String)jsonObject.opt("uri");
        final String type = (String)jsonObject.opt("mimeType");
        final JSONArray jsonArray = (JSONArray)jsonObject.opt("categories");
        final Integer n = (Integer)jsonObject.opt("flags");
        final JSONArray jsonArray2 = (JSONArray)jsonObject.opt("extras");
        android.content.Intent intent;
        if (s2 != null && s == null && action == null && type == null) {
            final android.content.Intent launchIntentForPackage = ClientProperties.getApplicationContext().getPackageManager().getLaunchIntentForPackage(s2);
            if ((intent = launchIntentForPackage) != null) {
                intent = launchIntentForPackage;
                if (n > -1) {
                    launchIntentForPackage.addFlags((int)n);
                    intent = launchIntentForPackage;
                }
            }
        }
        else {
            final android.content.Intent intent2 = new android.content.Intent();
            if (s != null && s2 != null) {
                intent2.setClassName(s2, s);
            }
            if (action != null) {
                intent2.setAction(action);
            }
            if (s3 != null) {
                intent2.setData(Uri.parse(s3));
            }
            if (type != null) {
                intent2.setType(type);
            }
            if (n != null && n > -1) {
                intent2.setFlags((int)n);
            }
            if (!setCategories(intent2, jsonArray)) {
                webViewCallback.error(IntentError.COULDNT_PARSE_CATEGORIES, jsonArray);
            }
            intent = intent2;
            if (!setExtras(intent2, jsonArray2)) {
                webViewCallback.error(IntentError.COULDNT_PARSE_EXTRAS, jsonArray2);
                intent = intent2;
            }
        }
        if (intent == null) {
            webViewCallback.error(IntentError.INTENT_WAS_NULL, new Object[0]);
            return;
        }
        if (getStartingActivity() != null) {
            getStartingActivity().startActivity(intent);
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(IntentError.ACTIVITY_WAS_NULL, new Object[0]);
    }
    
    public static void removeActiveActivity(final Activity activity) {
        if (Intent._activeActivity != null && Intent._activeActivity.get() != null && activity != null && activity.equals(Intent._activeActivity.get())) {
            Intent._activeActivity = null;
        }
    }
    
    public static void setActiveActivity(final Activity activity) {
        if (activity == null) {
            Intent._activeActivity = null;
            return;
        }
        Intent._activeActivity = new WeakReference<Activity>(activity);
    }
    
    private static boolean setCategories(final android.content.Intent intent, final JSONArray jsonArray) {
        if (jsonArray != null && jsonArray.length() > 0) {
            int i = 0;
            while (i < jsonArray.length()) {
                try {
                    intent.addCategory(jsonArray.getString(i));
                    ++i;
                    continue;
                }
                catch (Exception ex) {
                    DeviceLog.exception("Couldn't parse categories for intent", ex);
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    private static boolean setExtra(final android.content.Intent intent, final String s, final Object o) {
        if (o instanceof String) {
            intent.putExtra(s, (String)o);
        }
        else if (o instanceof Integer) {
            intent.putExtra(s, (int)o);
        }
        else if (o instanceof Double) {
            intent.putExtra(s, (double)o);
        }
        else {
            if (!(o instanceof Boolean)) {
                DeviceLog.error("Unable to parse launch intent extra " + s);
                return false;
            }
            intent.putExtra(s, (boolean)o);
        }
        return true;
    }
    
    private static boolean setExtras(final android.content.Intent intent, final JSONArray jsonArray) {
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); ++i) {
                try {
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (!setExtra(intent, jsonObject.getString("key"), jsonObject.get("value"))) {
                        return false;
                    }
                }
                catch (Exception ex) {
                    DeviceLog.exception("Couldn't parse extras", ex);
                    return false;
                }
            }
        }
        return true;
    }
    
    public enum IntentError
    {
        ACTIVITY_WAS_NULL, 
        COULDNT_PARSE_CATEGORIES, 
        COULDNT_PARSE_EXTRAS, 
        INTENT_WAS_NULL, 
        JSON_EXCEPTION;
    }
    
    private static class IntentException extends Exception
    {
        private IntentError error;
        private Object field;
        
        public IntentException(final IntentError error, final Object field) {
            this.error = error;
            this.field = field;
        }
        
        public IntentError getError() {
            return this.error;
        }
        
        public Object getField() {
            return this.field;
        }
    }
}
