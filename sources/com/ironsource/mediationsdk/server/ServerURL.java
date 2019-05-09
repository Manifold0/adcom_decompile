package com.ironsource.mediationsdk.server;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Pair;
import com.ironsource.environment.ApplicationContext;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.utils.IronSourceAES;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Vector;

public class ServerURL {
    private static final String AMPERSAND = "&";
    private static final String ANDROID = "android";
    private static final String APPLICATION_KEY = "applicationKey";
    private static final String APPLICATION_USER_ID = "applicationUserId";
    private static final String APPLICATION_VERSION = "appVer";
    private static String BASE_URL_PREFIX = "https://init.supersonicads.com/sdk/v";
    private static String BASE_URL_SUFFIX = "?request=";
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

    public static String getCPVProvidersURL(Context context, String applicationKey, String applicationUserId, String gaid, String mediationType, Vector<Pair<String, String>> segmentParamVector) throws UnsupportedEncodingException {
        Vector<Pair<String, String>> array = new Vector();
        array.add(new Pair("platform", "android"));
        array.add(new Pair("applicationKey", applicationKey));
        array.add(new Pair("applicationUserId", applicationUserId));
        array.add(new Pair("sdkVersion", IronSourceUtils.getSDKVersion()));
        if (IronSourceUtils.getSerr() == 0) {
            array.add(new Pair(SERR, String.valueOf(IronSourceUtils.getSerr())));
        }
        if (!TextUtils.isEmpty(ConfigFile.getConfigFile().getPluginType())) {
            array.add(new Pair(PLUGIN_TYPE, ConfigFile.getConfigFile().getPluginType()));
        }
        if (!TextUtils.isEmpty(ConfigFile.getConfigFile().getPluginVersion())) {
            array.add(new Pair(PLUGIN_VERSION, ConfigFile.getConfigFile().getPluginVersion()));
        }
        if (!TextUtils.isEmpty(ConfigFile.getConfigFile().getPluginFrameworkVersion())) {
            array.add(new Pair(PLUGIN_FW_VERSION, ConfigFile.getConfigFile().getPluginFrameworkVersion()));
        }
        if (!TextUtils.isEmpty(gaid)) {
            array.add(new Pair(GAID, gaid));
        }
        if (!TextUtils.isEmpty(mediationType)) {
            array.add(new Pair(MEDIATION_TYPE, mediationType));
        }
        String appVersion = ApplicationContext.getPublisherApplicationVersion(context, context.getPackageName());
        if (!TextUtils.isEmpty(appVersion)) {
            array.add(new Pair(APPLICATION_VERSION, appVersion));
        }
        array.add(new Pair(OS_VERSION, VERSION.SDK_INT + ""));
        array.add(new Pair(DEVICE_MAKE, Build.MANUFACTURER));
        array.add(new Pair(DEVICE_MODEL, Build.MODEL));
        String connection = IronSourceUtils.getConnectionType(context);
        if (!TextUtils.isEmpty(connection)) {
            array.add(new Pair(CONNECTION_TYPE, connection));
        }
        if (segmentParamVector != null) {
            array.addAll(segmentParamVector);
        }
        return getBaseUrl(IronSourceUtils.getSDKVersion()) + URLEncoder.encode(IronSourceAES.encode(IronSourceUtils.KEY, createURLParams(array)), "UTF-8");
    }

    public static String getRequestURL(String requestUrl, boolean hit, int placementId) throws UnsupportedEncodingException {
        Vector<Pair<String, String>> array = new Vector();
        array.add(new Pair(IMPRESSION, Boolean.toString(hit)));
        array.add(new Pair("placementId", Integer.toString(placementId)));
        return requestUrl + "&" + createURLParams(array);
    }

    private static String createURLParams(Vector<Pair<String, String>> array) throws UnsupportedEncodingException {
        String str = "";
        Iterator it = array.iterator();
        while (it.hasNext()) {
            Pair<String, String> pair = (Pair) it.next();
            if (str.length() > 0) {
                str = str + "&";
            }
            str = str + ((String) pair.first) + "=" + URLEncoder.encode((String) pair.second, "UTF-8");
        }
        return str;
    }

    private static String getBaseUrl(String sdkVersion) {
        return BASE_URL_PREFIX + sdkVersion + BASE_URL_SUFFIX;
    }
}
