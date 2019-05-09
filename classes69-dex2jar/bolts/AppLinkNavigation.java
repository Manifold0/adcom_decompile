// 
// Decompiled by Procyon v0.5.34
// 

package bolts;

import android.content.pm.PackageManager;
import java.util.Map;
import java.util.HashMap;
import android.content.Intent;
import java.net.URL;
import android.net.Uri;
import android.util.SparseArray;
import org.json.JSONArray;
import java.util.List;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONObject;
import android.content.pm.ApplicationInfo;
import android.content.Context;
import android.os.Bundle;

public class AppLinkNavigation
{
    private static final String KEY_NAME_REFERER_APP_LINK = "referer_app_link";
    private static final String KEY_NAME_REFERER_APP_LINK_APP_NAME = "app_name";
    private static final String KEY_NAME_REFERER_APP_LINK_PACKAGE = "package";
    private static final String KEY_NAME_USER_AGENT = "user_agent";
    private static final String KEY_NAME_VERSION = "version";
    private static final String VERSION = "1.0";
    private static AppLinkResolver defaultResolver;
    private final AppLink appLink;
    private final Bundle appLinkData;
    private final Bundle extras;
    
    public AppLinkNavigation(final AppLink appLink, Bundle appLinkData, final Bundle bundle) {
        if (appLink == null) {
            throw new IllegalArgumentException("appLink must not be null.");
        }
        Object extras;
        if ((extras = appLinkData) == null) {
            extras = new Bundle();
        }
        if ((appLinkData = bundle) == null) {
            appLinkData = new Bundle();
        }
        this.appLink = appLink;
        this.extras = (Bundle)extras;
        this.appLinkData = (Bundle)appLinkData;
    }
    
    private Bundle buildAppLinkDataForNavigation(final Context context) {
        final Bundle bundle = new Bundle();
        final Bundle bundle2 = new Bundle();
        if (context != null) {
            final String packageName = context.getPackageName();
            if (packageName != null) {
                bundle2.putString("package", packageName);
            }
            final ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo != null) {
                final String string = context.getString(applicationInfo.labelRes);
                if (string != null) {
                    bundle2.putString("app_name", string);
                }
            }
        }
        bundle.putAll(this.getAppLinkData());
        bundle.putString("target_url", this.getAppLink().getSourceUrl().toString());
        bundle.putString("version", "1.0");
        bundle.putString("user_agent", "Bolts Android 1.4.0");
        bundle.putBundle("referer_app_link", bundle2);
        bundle.putBundle("extras", this.getExtras());
        return bundle;
    }
    
    public static AppLinkResolver getDefaultResolver() {
        return AppLinkNavigation.defaultResolver;
    }
    
    private JSONObject getJSONForBundle(final Bundle bundle) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            jsonObject.put(s, this.getJSONValue(bundle.get(s)));
        }
        return jsonObject;
    }
    
    private Object getJSONValue(final Object o) throws JSONException {
        JSONObject jsonForBundle;
        if (o instanceof Bundle) {
            jsonForBundle = this.getJSONForBundle((Bundle)o);
        }
        else {
            if (o instanceof CharSequence) {
                return o.toString();
            }
            if (o instanceof List) {
                final JSONArray jsonArray = new JSONArray();
                final Iterator iterator = ((List)o).iterator();
                while (true) {
                    jsonForBundle = (JSONObject)jsonArray;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    jsonArray.put(this.getJSONValue(iterator.next()));
                }
            }
            else if (o instanceof SparseArray) {
                final JSONArray jsonArray2 = new JSONArray();
                final SparseArray sparseArray = (SparseArray)o;
                int n = 0;
                while (true) {
                    jsonForBundle = (JSONObject)jsonArray2;
                    if (n >= sparseArray.size()) {
                        break;
                    }
                    jsonArray2.put(sparseArray.keyAt(n), this.getJSONValue(sparseArray.valueAt(n)));
                    ++n;
                }
            }
            else {
                if (o instanceof Character) {
                    return o.toString();
                }
                if (o instanceof Boolean) {
                    return o;
                }
                if (o instanceof Number) {
                    if (o instanceof Double || o instanceof Float) {
                        return ((Number)o).doubleValue();
                    }
                    return ((Number)o).longValue();
                }
                else if (o instanceof boolean[]) {
                    final JSONArray jsonArray3 = new JSONArray();
                    final boolean[] array = (boolean[])o;
                    final int length = array.length;
                    int n2 = 0;
                    while (true) {
                        jsonForBundle = (JSONObject)jsonArray3;
                        if (n2 >= length) {
                            break;
                        }
                        jsonArray3.put(this.getJSONValue(array[n2]));
                        ++n2;
                    }
                }
                else if (o instanceof char[]) {
                    final JSONArray jsonArray4 = new JSONArray();
                    final char[] array2 = (char[])o;
                    final int length2 = array2.length;
                    int n3 = 0;
                    while (true) {
                        jsonForBundle = (JSONObject)jsonArray4;
                        if (n3 >= length2) {
                            break;
                        }
                        jsonArray4.put(this.getJSONValue(array2[n3]));
                        ++n3;
                    }
                }
                else if (o instanceof CharSequence[]) {
                    final JSONArray jsonArray5 = new JSONArray();
                    final CharSequence[] array3 = (CharSequence[])o;
                    final int length3 = array3.length;
                    int n4 = 0;
                    while (true) {
                        jsonForBundle = (JSONObject)jsonArray5;
                        if (n4 >= length3) {
                            break;
                        }
                        jsonArray5.put(this.getJSONValue(array3[n4]));
                        ++n4;
                    }
                }
                else if (o instanceof double[]) {
                    final JSONArray jsonArray6 = new JSONArray();
                    final double[] array4 = (double[])o;
                    final int length4 = array4.length;
                    int n5 = 0;
                    while (true) {
                        jsonForBundle = (JSONObject)jsonArray6;
                        if (n5 >= length4) {
                            break;
                        }
                        jsonArray6.put(this.getJSONValue(array4[n5]));
                        ++n5;
                    }
                }
                else if (o instanceof float[]) {
                    final JSONArray jsonArray7 = new JSONArray();
                    final float[] array5 = (float[])o;
                    final int length5 = array5.length;
                    int n6 = 0;
                    while (true) {
                        jsonForBundle = (JSONObject)jsonArray7;
                        if (n6 >= length5) {
                            break;
                        }
                        jsonArray7.put(this.getJSONValue(array5[n6]));
                        ++n6;
                    }
                }
                else if (o instanceof int[]) {
                    final JSONArray jsonArray8 = new JSONArray();
                    final int[] array6 = (int[])o;
                    final int length6 = array6.length;
                    int n7 = 0;
                    while (true) {
                        jsonForBundle = (JSONObject)jsonArray8;
                        if (n7 >= length6) {
                            break;
                        }
                        jsonArray8.put(this.getJSONValue(array6[n7]));
                        ++n7;
                    }
                }
                else if (o instanceof long[]) {
                    final JSONArray jsonArray9 = new JSONArray();
                    final long[] array7 = (long[])o;
                    final int length7 = array7.length;
                    int n8 = 0;
                    while (true) {
                        jsonForBundle = (JSONObject)jsonArray9;
                        if (n8 >= length7) {
                            break;
                        }
                        jsonArray9.put(this.getJSONValue(array7[n8]));
                        ++n8;
                    }
                }
                else if (o instanceof short[]) {
                    final JSONArray jsonArray10 = new JSONArray();
                    final short[] array8 = (short[])o;
                    final int length8 = array8.length;
                    int n9 = 0;
                    while (true) {
                        jsonForBundle = (JSONObject)jsonArray10;
                        if (n9 >= length8) {
                            break;
                        }
                        jsonArray10.put(this.getJSONValue(array8[n9]));
                        ++n9;
                    }
                }
                else {
                    if (!(o instanceof String[])) {
                        return null;
                    }
                    final JSONArray jsonArray11 = new JSONArray();
                    final String[] array9 = (String[])o;
                    final int length9 = array9.length;
                    int n10 = 0;
                    while (true) {
                        jsonForBundle = (JSONObject)jsonArray11;
                        if (n10 >= length9) {
                            break;
                        }
                        jsonArray11.put(this.getJSONValue(array9[n10]));
                        ++n10;
                    }
                }
            }
        }
        return jsonForBundle;
    }
    
    private static AppLinkResolver getResolver(final Context context) {
        if (getDefaultResolver() != null) {
            return getDefaultResolver();
        }
        return new WebViewAppLinkResolver(context);
    }
    
    public static NavigationResult navigate(final Context context, final AppLink appLink) {
        return new AppLinkNavigation(appLink, null, null).navigate(context);
    }
    
    public static Task<NavigationResult> navigateInBackground(final Context context, final Uri uri) {
        return navigateInBackground(context, uri, getResolver(context));
    }
    
    public static Task<NavigationResult> navigateInBackground(final Context context, final Uri uri, final AppLinkResolver appLinkResolver) {
        return (Task<NavigationResult>)appLinkResolver.getAppLinkFromUrlInBackground(uri).onSuccess((Continuation)new Continuation<AppLink, NavigationResult>() {
            public NavigationResult then(final Task<AppLink> task) throws Exception {
                return AppLinkNavigation.navigate(context, (AppLink)task.getResult());
            }
        }, Task.UI_THREAD_EXECUTOR);
    }
    
    public static Task<NavigationResult> navigateInBackground(final Context context, final String s) {
        return navigateInBackground(context, s, getResolver(context));
    }
    
    public static Task<NavigationResult> navigateInBackground(final Context context, final String s, final AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(s), appLinkResolver);
    }
    
    public static Task<NavigationResult> navigateInBackground(final Context context, final URL url) {
        return navigateInBackground(context, url, getResolver(context));
    }
    
    public static Task<NavigationResult> navigateInBackground(final Context context, final URL url, final AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(url.toString()), appLinkResolver);
    }
    
    private void sendAppLinkNavigateEventBroadcast(final Context context, final Intent intent, final NavigationResult navigationResult, final JSONException ex) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (ex != null) {
            hashMap.put("error", ex.getLocalizedMessage());
        }
        String s;
        if (navigationResult.isSucceeded()) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("success", s);
        hashMap.put("type", navigationResult.getCode());
        MeasurementEvent.sendBroadcastEvent(context, "al_nav_out", intent, hashMap);
    }
    
    public static void setDefaultResolver(final AppLinkResolver defaultResolver) {
        AppLinkNavigation.defaultResolver = defaultResolver;
    }
    
    public AppLink getAppLink() {
        return this.appLink;
    }
    
    public Bundle getAppLinkData() {
        return this.appLinkData;
    }
    
    public Bundle getExtras() {
        return this.extras;
    }
    
    public NavigationResult navigate(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        final Bundle buildAppLinkDataForNavigation = this.buildAppLinkDataForNavigation(context);
        final Intent intent = null;
        final Iterator<AppLink.Target> iterator = this.getAppLink().getTargets().iterator();
        Intent intent2;
        do {
            intent2 = intent;
            if (!iterator.hasNext()) {
                break;
            }
            final AppLink.Target target = iterator.next();
            intent2 = new Intent("android.intent.action.VIEW");
            if (target.getUrl() != null) {
                intent2.setData(target.getUrl());
            }
            else {
                intent2.setData(this.appLink.getSourceUrl());
            }
            intent2.setPackage(target.getPackageName());
            if (target.getClassName() != null) {
                intent2.setClassName(target.getPackageName(), target.getClassName());
            }
            intent2.putExtra("al_applink_data", buildAppLinkDataForNavigation);
        } while (packageManager.resolveActivity(intent2, 65536) == null);
        Intent intent3 = null;
        NavigationResult navigationResult = NavigationResult.FAILED;
        if (intent2 != null) {
            navigationResult = NavigationResult.APP;
            intent3 = intent2;
        }
        else {
            final Uri webUrl = this.getAppLink().getWebUrl();
            if (webUrl != null) {
                try {
                    intent3 = new Intent("android.intent.action.VIEW", webUrl.buildUpon().appendQueryParameter("al_applink_data", this.getJSONForBundle(buildAppLinkDataForNavigation).toString()).build());
                    navigationResult = NavigationResult.WEB;
                }
                catch (JSONException ex) {
                    this.sendAppLinkNavigateEventBroadcast(context, intent2, NavigationResult.FAILED, ex);
                    throw new RuntimeException((Throwable)ex);
                }
            }
        }
        this.sendAppLinkNavigateEventBroadcast(context, intent3, navigationResult, null);
        if (intent3 != null) {
            context.startActivity(intent3);
        }
        return navigationResult;
    }
    
    public enum NavigationResult
    {
        APP("app", true), 
        FAILED("failed", false), 
        WEB("web", true);
        
        private String code;
        private boolean succeeded;
        
        private NavigationResult(final String code, final boolean succeeded) {
            this.code = code;
            this.succeeded = succeeded;
        }
        
        public String getCode() {
            return this.code;
        }
        
        public boolean isSucceeded() {
            return this.succeeded;
        }
    }
}
