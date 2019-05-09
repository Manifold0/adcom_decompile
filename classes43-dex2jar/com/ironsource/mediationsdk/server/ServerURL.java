// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.server;

import com.ironsource.mediationsdk.utils.IronSourceAES;
import java.util.Collection;
import android.os.Build;
import android.os.Build$VERSION;
import com.ironsource.environment.ApplicationContext;
import android.text.TextUtils;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.net.URLEncoder;
import android.util.Pair;
import java.util.Vector;

public class ServerURL
{
    private static final String AMPERSAND = "&";
    private static final String ANDROID = "android";
    private static final String APPLICATION_KEY = "applicationKey";
    private static final String APPLICATION_USER_ID = "applicationUserId";
    private static final String APPLICATION_VERSION = "appVer";
    private static String BASE_URL_PREFIX;
    private static String BASE_URL_SUFFIX;
    private static final String CONNECTION_TYPE = "connType";
    private static final String DEVICE_MAKE = "devMake";
    private static final String DEVICE_MODEL = "devModel";
    private static final String EQUAL = "=";
    private static final String GAID = "advId";
    private static final String IMPRESSION = "impression";
    private static final String MEDIATION_TYPE = "mt";
    private static final String OS_VERSION = "osVer";
    private static final String PLACEMENT = "placementId";
    private static final String PLATFORM_KEY = "platform";
    private static final String PLUGIN_FW_VERSION = "plugin_fw_v";
    private static final String PLUGIN_TYPE = "pluginType";
    private static final String PLUGIN_VERSION = "pluginVersion";
    private static final String SDK_VERSION = "sdkVersion";
    private static final String SERR = "serr";
    
    static {
        ServerURL.BASE_URL_PREFIX = "https://init.supersonicads.com/sdk/v";
        ServerURL.BASE_URL_SUFFIX = "?request=";
    }
    
    private static String createURLParams(final Vector<Pair<String, String>> vector) throws UnsupportedEncodingException {
        final String s = "";
        final Iterator<Pair<String, String>> iterator = vector.iterator();
        String string = s;
        while (iterator.hasNext()) {
            final Pair<String, String> pair = iterator.next();
            String string2 = string;
            if (string.length() > 0) {
                string2 = string + "&";
            }
            string = string2 + (String)pair.first + "=" + URLEncoder.encode((String)pair.second, "UTF-8");
        }
        return string;
    }
    
    private static String getBaseUrl(final String s) {
        return ServerURL.BASE_URL_PREFIX + s + ServerURL.BASE_URL_SUFFIX;
    }
    
    public static String getCPVProvidersURL(final Context context, String publisherApplicationVersion, final String s, final String s2, final String s3, final Vector<Pair<String, String>> vector) throws UnsupportedEncodingException {
        final Vector<Pair<String, String>> vector2 = new Vector<Pair<String, String>>();
        vector2.add((Pair<String, String>)new Pair((Object)"platform", (Object)"android"));
        vector2.add((Pair<String, String>)new Pair((Object)"applicationKey", (Object)publisherApplicationVersion));
        vector2.add((Pair<String, String>)new Pair((Object)"applicationUserId", (Object)s));
        vector2.add((Pair<String, String>)new Pair((Object)"sdkVersion", (Object)IronSourceUtils.getSDKVersion()));
        if (IronSourceUtils.getSerr() == 0) {
            vector2.add((Pair<String, String>)new Pair((Object)"serr", (Object)String.valueOf(IronSourceUtils.getSerr())));
        }
        if (!TextUtils.isEmpty((CharSequence)ConfigFile.getConfigFile().getPluginType())) {
            vector2.add((Pair<String, String>)new Pair((Object)"pluginType", (Object)ConfigFile.getConfigFile().getPluginType()));
        }
        if (!TextUtils.isEmpty((CharSequence)ConfigFile.getConfigFile().getPluginVersion())) {
            vector2.add((Pair<String, String>)new Pair((Object)"pluginVersion", (Object)ConfigFile.getConfigFile().getPluginVersion()));
        }
        if (!TextUtils.isEmpty((CharSequence)ConfigFile.getConfigFile().getPluginFrameworkVersion())) {
            vector2.add((Pair<String, String>)new Pair((Object)"plugin_fw_v", (Object)ConfigFile.getConfigFile().getPluginFrameworkVersion()));
        }
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            vector2.add((Pair<String, String>)new Pair((Object)"advId", (Object)s2));
        }
        if (!TextUtils.isEmpty((CharSequence)s3)) {
            vector2.add((Pair<String, String>)new Pair((Object)"mt", (Object)s3));
        }
        publisherApplicationVersion = ApplicationContext.getPublisherApplicationVersion(context, context.getPackageName());
        if (!TextUtils.isEmpty((CharSequence)publisherApplicationVersion)) {
            vector2.add((Pair<String, String>)new Pair((Object)"appVer", (Object)publisherApplicationVersion));
        }
        vector2.add((Pair<String, String>)new Pair((Object)"osVer", (Object)(Build$VERSION.SDK_INT + "")));
        vector2.add((Pair<String, String>)new Pair((Object)"devMake", (Object)Build.MANUFACTURER));
        vector2.add((Pair<String, String>)new Pair((Object)"devModel", (Object)Build.MODEL));
        final String connectionType = IronSourceUtils.getConnectionType(context);
        if (!TextUtils.isEmpty((CharSequence)connectionType)) {
            vector2.add((Pair<String, String>)new Pair((Object)"connType", (Object)connectionType));
        }
        if (vector != null) {
            vector2.addAll(vector);
        }
        return getBaseUrl(IronSourceUtils.getSDKVersion()) + URLEncoder.encode(IronSourceAES.encode("C38FB23A402222A0C17D34A92F971D1F", createURLParams(vector2)), "UTF-8");
    }
    
    public static String getRequestURL(final String s, final boolean b, final int n) throws UnsupportedEncodingException {
        final Vector<Pair<String, String>> vector = new Vector<Pair<String, String>>();
        vector.add((Pair<String, String>)new Pair((Object)"impression", (Object)Boolean.toString(b)));
        vector.add((Pair<String, String>)new Pair((Object)"placementId", (Object)Integer.toString(n)));
        return s + "&" + createURLParams(vector);
    }
}
