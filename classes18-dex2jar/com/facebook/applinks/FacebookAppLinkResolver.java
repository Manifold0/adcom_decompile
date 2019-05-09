// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.applinks;

import com.facebook.HttpMethod;
import com.facebook.GraphRequest;
import org.json.JSONArray;
import java.util.Iterator;
import com.facebook.FacebookRequestError;
import com.facebook.GraphResponse;
import bolts.Task$TaskCompletionSource;
import com.facebook.GraphRequest$Callback;
import com.facebook.AccessToken;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Map;
import bolts.Continuation;
import java.util.List;
import java.util.ArrayList;
import bolts.Task;
import org.json.JSONException;
import bolts.AppLink$Target;
import org.json.JSONObject;
import bolts.AppLink;
import android.net.Uri;
import java.util.HashMap;
import bolts.AppLinkResolver;

public class FacebookAppLinkResolver implements AppLinkResolver
{
    private static final String APP_LINK_ANDROID_TARGET_KEY = "android";
    private static final String APP_LINK_KEY = "app_links";
    private static final String APP_LINK_TARGET_APP_NAME_KEY = "app_name";
    private static final String APP_LINK_TARGET_CLASS_KEY = "class";
    private static final String APP_LINK_TARGET_PACKAGE_KEY = "package";
    private static final String APP_LINK_TARGET_SHOULD_FALLBACK_KEY = "should_fallback";
    private static final String APP_LINK_TARGET_URL_KEY = "url";
    private static final String APP_LINK_WEB_TARGET_KEY = "web";
    private final HashMap<Uri, AppLink> cachedAppLinks;
    
    public FacebookAppLinkResolver() {
        this.cachedAppLinks = new HashMap<Uri, AppLink>();
    }
    
    private static AppLink$Target getAndroidTargetFromJson(final JSONObject jsonObject) {
        final String tryGetStringFromJson = tryGetStringFromJson(jsonObject, "package", null);
        if (tryGetStringFromJson == null) {
            return null;
        }
        final String tryGetStringFromJson2 = tryGetStringFromJson(jsonObject, "class", null);
        final String tryGetStringFromJson3 = tryGetStringFromJson(jsonObject, "app_name", null);
        final String tryGetStringFromJson4 = tryGetStringFromJson(jsonObject, "url", null);
        Uri parse = null;
        if (tryGetStringFromJson4 != null) {
            parse = Uri.parse(tryGetStringFromJson4);
        }
        return new AppLink$Target(tryGetStringFromJson, tryGetStringFromJson2, parse, tryGetStringFromJson3);
    }
    
    private static Uri getWebFallbackUriFromJson(final Uri uri, JSONObject jsonObject) {
        Uri uri2;
        try {
            jsonObject = jsonObject.getJSONObject("web");
            if (!tryGetBooleanFromJson(jsonObject, "should_fallback", true)) {
                return null;
            }
            final String tryGetStringFromJson = tryGetStringFromJson(jsonObject, "url", null);
            Uri parse = null;
            if (tryGetStringFromJson != null) {
                parse = Uri.parse(tryGetStringFromJson);
            }
            if ((uri2 = parse) == null) {
                return uri;
            }
        }
        catch (JSONException ex) {
            uri2 = uri;
        }
        return uri2;
    }
    
    private static boolean tryGetBooleanFromJson(final JSONObject jsonObject, final String s, final boolean b) {
        try {
            return jsonObject.getBoolean(s);
        }
        catch (JSONException ex) {
            return b;
        }
    }
    
    private static String tryGetStringFromJson(final JSONObject jsonObject, final String s, final String s2) {
        try {
            return jsonObject.getString(s);
        }
        catch (JSONException ex) {
            return s2;
        }
    }
    
    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        final ArrayList<Uri> list = new ArrayList<Uri>();
        list.add(uri);
        return (Task<AppLink>)this.getAppLinkFromUrlsInBackground(list).onSuccess((Continuation)new Continuation<Map<Uri, AppLink>, AppLink>() {
            public AppLink then(final Task<Map<Uri, AppLink>> task) throws Exception {
                return ((Map)task.getResult()).get(uri);
            }
        });
    }
    
    public Task<Map<Uri, AppLink>> getAppLinkFromUrlsInBackground(final List<Uri> list) {
        final HashMap<Uri, AppLink> hashMap = new HashMap<Uri, AppLink>();
        final HashSet<Uri> set = new HashSet<Uri>();
        final StringBuilder sb = new StringBuilder();
        for (final Uri uri : list) {
            synchronized (this.cachedAppLinks) {
                final AppLink appLink = this.cachedAppLinks.get(uri);
                // monitorexit(this.cachedAppLinks)
                if (appLink != null) {
                    hashMap.put(uri, appLink);
                    continue;
                }
            }
            if (!set.isEmpty()) {
                sb.append(',');
            }
            sb.append(uri.toString());
            set.add(uri);
        }
        if (set.isEmpty()) {
            return (Task<Map<Uri, AppLink>>)Task.forResult((Object)hashMap);
        }
        final Task$TaskCompletionSource create = Task.create();
        final Bundle bundle = new Bundle();
        bundle.putString("ids", sb.toString());
        bundle.putString("fields", String.format("%s.fields(%s,%s)", "app_links", "android", "web"));
        new GraphRequest(AccessToken.getCurrentAccessToken(), "", bundle, (HttpMethod)null, (GraphRequest$Callback)new GraphRequest$Callback() {
            public void onCompleted(GraphResponse jsonObject) {
                final FacebookRequestError error = jsonObject.getError();
                if (error != null) {
                    create.setError((Exception)error.getException());
                    return;
                }
                jsonObject = (GraphResponse)jsonObject.getJSONObject();
                if (jsonObject == null) {
                    create.setResult((Object)hashMap);
                    return;
                }
            Label_0245:
                for (final Uri uri : set) {
                    if (((JSONObject)jsonObject).has(uri.toString())) {
                        while (true) {
                            while (true) {
                                int n = 0;
                                Label_0257: {
                                    try {
                                        final JSONObject jsonObject2 = ((JSONObject)jsonObject).getJSONObject(uri.toString()).getJSONObject("app_links");
                                        final JSONArray jsonArray = jsonObject2.getJSONArray("android");
                                        final int length = jsonArray.length();
                                        final ArrayList list = new ArrayList<AppLink$Target>(length);
                                        n = 0;
                                        if (n < length) {
                                            final AppLink$Target access$000 = getAndroidTargetFromJson(jsonArray.getJSONObject(n));
                                            if (access$000 != null) {
                                                list.add(access$000);
                                            }
                                            break Label_0257;
                                        }
                                        else {
                                            final AppLink appLink = new AppLink(uri, (List)list, getWebFallbackUriFromJson(uri, jsonObject2));
                                            hashMap.put(uri, appLink);
                                            synchronized (FacebookAppLinkResolver.this.cachedAppLinks) {
                                                FacebookAppLinkResolver.this.cachedAppLinks.put(uri, appLink);
                                            }
                                        }
                                    }
                                    catch (JSONException ex) {
                                        break;
                                    }
                                    break Label_0245;
                                }
                                ++n;
                                continue;
                            }
                        }
                    }
                }
                create.setResult((Object)hashMap);
            }
        }).executeAsync();
        return (Task<Map<Uri, AppLink>>)create.getTask();
    }
}
