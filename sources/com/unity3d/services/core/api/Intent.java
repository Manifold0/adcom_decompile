package com.unity3d.services.core.api;

import android.app.Activity;
import android.net.Uri;
import com.facebook.share.internal.ShareConstants;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Intent {
    private static WeakReference<Activity> _activeActivity;

    public enum IntentError {
        COULDNT_PARSE_EXTRAS,
        COULDNT_PARSE_CATEGORIES,
        INTENT_WAS_NULL,
        JSON_EXCEPTION,
        ACTIVITY_WAS_NULL
    }

    private static class IntentException extends Exception {
        private IntentError error;
        private Object field;

        public IntentException(IntentError error, Object field) {
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

    @WebViewExposed
    public static void launch(JSONObject intentData, WebViewCallback callback) {
        android.content.Intent intent;
        String className = (String) intentData.opt("className");
        String packageName = (String) intentData.opt("packageName");
        String action = (String) intentData.opt("action");
        String uri = (String) intentData.opt(ShareConstants.MEDIA_URI);
        String mimeType = (String) intentData.opt("mimeType");
        JSONArray categories = (JSONArray) intentData.opt("categories");
        Integer flags = (Integer) intentData.opt("flags");
        JSONArray extras = (JSONArray) intentData.opt("extras");
        if (packageName != null && className == null && action == null && mimeType == null) {
            intent = ClientProperties.getApplicationContext().getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null && flags.intValue() > -1) {
                intent.addFlags(flags.intValue());
            }
        } else {
            intent = new android.content.Intent();
            if (!(className == null || packageName == null)) {
                intent.setClassName(packageName, className);
            }
            if (action != null) {
                intent.setAction(action);
            }
            if (uri != null) {
                intent.setData(Uri.parse(uri));
            }
            if (mimeType != null) {
                intent.setType(mimeType);
            }
            if (flags != null && flags.intValue() > -1) {
                intent.setFlags(flags.intValue());
            }
            if (!setCategories(intent, categories)) {
                callback.error(IntentError.COULDNT_PARSE_CATEGORIES, categories);
            }
            if (!setExtras(intent, extras)) {
                callback.error(IntentError.COULDNT_PARSE_EXTRAS, extras);
            }
        }
        if (intent == null) {
            callback.error(IntentError.INTENT_WAS_NULL, new Object[0]);
        } else if (getStartingActivity() != null) {
            getStartingActivity().startActivity(intent);
            callback.invoke(new Object[0]);
        } else {
            callback.error(IntentError.ACTIVITY_WAS_NULL, new Object[0]);
        }
    }

    private static boolean setCategories(android.content.Intent intent, JSONArray categories) {
        if (categories != null && categories.length() > 0) {
            int i = 0;
            while (i < categories.length()) {
                try {
                    intent.addCategory(categories.getString(i));
                    i++;
                } catch (Exception e) {
                    DeviceLog.exception("Couldn't parse categories for intent", e);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean setExtras(android.content.Intent intent, JSONArray extras) {
        if (extras != null) {
            int i = 0;
            while (i < extras.length()) {
                try {
                    JSONObject item = extras.getJSONObject(i);
                    if (!setExtra(intent, item.getString(ParametersKeys.KEY), item.get("value"))) {
                        return false;
                    }
                    i++;
                } catch (Exception e) {
                    DeviceLog.exception("Couldn't parse extras", e);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean setExtra(android.content.Intent intent, String key, Object value) {
        if (value instanceof String) {
            intent.putExtra(key, (String) value);
        } else if (value instanceof Integer) {
            intent.putExtra(key, ((Integer) value).intValue());
        } else if (value instanceof Double) {
            intent.putExtra(key, ((Double) value).doubleValue());
        } else if (value instanceof Boolean) {
            intent.putExtra(key, ((Boolean) value).booleanValue());
        } else {
            DeviceLog.error("Unable to parse launch intent extra " + key);
            return false;
        }
        return true;
    }

    private static Activity getStartingActivity() {
        if (_activeActivity != null && _activeActivity.get() != null) {
            return (Activity) _activeActivity.get();
        }
        if (ClientProperties.getActivity() != null) {
            return ClientProperties.getActivity();
        }
        return null;
    }

    public static void setActiveActivity(Activity activity) {
        if (activity == null) {
            _activeActivity = null;
        } else {
            _activeActivity = new WeakReference(activity);
        }
    }

    public static void removeActiveActivity(Activity activity) {
        if (_activeActivity != null && _activeActivity.get() != null && activity != null && activity.equals(_activeActivity.get())) {
            _activeActivity = null;
        }
    }

    @WebViewExposed
    public static void canOpenIntent(JSONObject intentData, WebViewCallback callback) {
        try {
            callback.invoke(Boolean.valueOf(checkIntentResolvable(intentFromMetadata(intentData))));
        } catch (IntentException e) {
            DeviceLog.exception("Couldn't resolve intent", e);
            callback.error(e.getError(), e.getField());
        }
    }

    @WebViewExposed
    public static void canOpenIntents(JSONArray intents, WebViewCallback callback) {
        JSONObject results = new JSONObject();
        int len = intents.length();
        int i = 0;
        while (i < len) {
            JSONObject intentData = intents.optJSONObject(i);
            try {
                results.put(intentData.optString("id"), checkIntentResolvable(intentFromMetadata(intentData)));
                i++;
            } catch (IntentException e) {
                DeviceLog.exception("Exception parsing intent", e);
                callback.error(e.getError(), e.getField());
                return;
            } catch (JSONException e2) {
                callback.error(IntentError.JSON_EXCEPTION, e2.getMessage());
                return;
            }
        }
        callback.invoke(results);
    }

    private static boolean checkIntentResolvable(android.content.Intent intent) {
        if (ClientProperties.getApplicationContext().getPackageManager().resolveActivity(intent, 0) != null) {
            return true;
        }
        return false;
    }

    private static android.content.Intent intentFromMetadata(JSONObject json) throws IntentException {
        android.content.Intent intent;
        String className = (String) json.opt("className");
        String packageName = (String) json.opt("packageName");
        String action = (String) json.opt("action");
        String uri = (String) json.opt(ShareConstants.MEDIA_URI);
        String mimeType = (String) json.opt("mimeType");
        JSONArray categories = (JSONArray) json.opt("categories");
        Integer flags = (Integer) json.opt("flags");
        JSONArray extras = (JSONArray) json.opt("extras");
        if (packageName != null && className == null && action == null && mimeType == null) {
            intent = ClientProperties.getApplicationContext().getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null && flags.intValue() > -1) {
                intent.addFlags(flags.intValue());
            }
        } else {
            intent = new android.content.Intent();
            if (!(className == null || packageName == null)) {
                intent.setClassName(packageName, className);
            }
            if (action != null) {
                intent.setAction(action);
            }
            if (uri != null) {
                intent.setData(Uri.parse(uri));
            }
            if (mimeType != null) {
                intent.setType(mimeType);
            }
            if (flags != null && flags.intValue() > -1) {
                intent.setFlags(flags.intValue());
            }
            if (!setCategories(intent, categories)) {
                throw new IntentException(IntentError.COULDNT_PARSE_CATEGORIES, categories);
            } else if (!setExtras(intent, extras)) {
                throw new IntentException(IntentError.COULDNT_PARSE_EXTRAS, extras);
            }
        }
        return intent;
    }
}
