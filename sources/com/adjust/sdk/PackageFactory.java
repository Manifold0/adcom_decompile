package com.adjust.sdk;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.net.UrlQuerySanitizer.ParameterValuePair;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PackageFactory {
    private static final String ADJUST_PREFIX = "adjust_";

    public static ActivityPackage buildReftagSdkClickPackage(String rawReferrer, long clickTime, ActivityState activityState, AdjustConfig adjustConfig, DeviceInfo deviceInfo, SessionParameters sessionParameters) {
        if (rawReferrer == null || rawReferrer.length() == 0) {
            return null;
        }
        String referrer;
        try {
            referrer = URLDecoder.decode(rawReferrer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            referrer = Constants.MALFORMED;
        }
        AdjustFactory.getLogger().verbose("Referrer to parse (%s)", referrer);
        UrlQuerySanitizer querySanitizer = new UrlQuerySanitizer();
        querySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
        querySanitizer.setAllowUnregisteredParamaters(true);
        querySanitizer.parseQuery(referrer);
        PackageBuilder clickPackageBuilder = queryStringClickPackageBuilder(querySanitizer.getParameterList(), activityState, adjustConfig, deviceInfo, sessionParameters);
        if (clickPackageBuilder == null) {
            return null;
        }
        clickPackageBuilder.referrer = referrer;
        clickPackageBuilder.clickTimeInMilliseconds = clickTime;
        clickPackageBuilder.rawReferrer = rawReferrer;
        return clickPackageBuilder.buildClickPackage(Constants.REFTAG);
    }

    public static ActivityPackage buildDeeplinkSdkClickPackage(Uri url, long clickTime, ActivityState activityState, AdjustConfig adjustConfig, DeviceInfo deviceInfo, SessionParameters sessionParameters) {
        if (url == null) {
            return null;
        }
        String urlString = url.toString();
        if (urlString == null || urlString.length() == 0) {
            return null;
        }
        AdjustFactory.getLogger().verbose("Url to parse (%s)", url);
        UrlQuerySanitizer querySanitizer = new UrlQuerySanitizer();
        querySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
        querySanitizer.setAllowUnregisteredParamaters(true);
        querySanitizer.parseUrl(urlString);
        PackageBuilder clickPackageBuilder = queryStringClickPackageBuilder(querySanitizer.getParameterList(), activityState, adjustConfig, deviceInfo, sessionParameters);
        if (clickPackageBuilder == null) {
            return null;
        }
        clickPackageBuilder.deeplink = url.toString();
        clickPackageBuilder.clickTimeInMilliseconds = clickTime;
        return clickPackageBuilder.buildClickPackage(Constants.DEEPLINK);
    }

    public static ActivityPackage buildInstallReferrerSdkClickPackage(String installReferrer, long clickTimeInSeconds, long installBeginInSeconds, ActivityState activityState, AdjustConfig adjustConfig, DeviceInfo deviceInfo, SessionParameters sessionParameters) {
        if (installReferrer == null || installReferrer.length() == 0) {
            return null;
        }
        PackageBuilder clickPackageBuilder = new PackageBuilder(adjustConfig, deviceInfo, activityState, sessionParameters, System.currentTimeMillis());
        if (clickPackageBuilder == null) {
            return null;
        }
        clickPackageBuilder.referrer = installReferrer;
        clickPackageBuilder.clicktTimeInSeconds = clickTimeInSeconds;
        clickPackageBuilder.installBeginTimeInSeconds = installBeginInSeconds;
        return clickPackageBuilder.buildClickPackage(Constants.INSTALL_REFERRER);
    }

    private static PackageBuilder queryStringClickPackageBuilder(List<ParameterValuePair> queryList, ActivityState activityState, AdjustConfig adjustConfig, DeviceInfo deviceInfo, SessionParameters sessionParameters) {
        if (queryList == null) {
            return null;
        }
        Map<String, String> queryStringParameters = new LinkedHashMap();
        AdjustAttribution queryStringAttribution = new AdjustAttribution();
        for (ParameterValuePair parameterValuePair : queryList) {
            readQueryString(parameterValuePair.mParameter, parameterValuePair.mValue, queryStringParameters, queryStringAttribution);
        }
        long now = System.currentTimeMillis();
        String reftag = (String) queryStringParameters.remove(Constants.REFTAG);
        if (activityState != null) {
            activityState.lastInterval = now - activityState.lastActivity;
        }
        PackageBuilder builder = new PackageBuilder(adjustConfig, deviceInfo, activityState, sessionParameters, now);
        builder.extraParameters = queryStringParameters;
        builder.attribution = queryStringAttribution;
        builder.reftag = reftag;
        return builder;
    }

    private static boolean readQueryString(String key, String value, Map<String, String> extraParameters, AdjustAttribution queryStringAttribution) {
        if (key == null || value == null || !key.startsWith(ADJUST_PREFIX)) {
            return false;
        }
        String keyWOutPrefix = key.substring(ADJUST_PREFIX.length());
        if (keyWOutPrefix.length() == 0 || value.length() == 0) {
            return false;
        }
        if (!tryToSetAttribution(queryStringAttribution, keyWOutPrefix, value)) {
            extraParameters.put(keyWOutPrefix, value);
        }
        return true;
    }

    private static boolean tryToSetAttribution(AdjustAttribution queryStringAttribution, String key, String value) {
        if (key.equals("tracker")) {
            queryStringAttribution.trackerName = value;
            return true;
        } else if (key.equals("campaign")) {
            queryStringAttribution.campaign = value;
            return true;
        } else if (key.equals("adgroup")) {
            queryStringAttribution.adgroup = value;
            return true;
        } else if (!key.equals("creative")) {
            return false;
        } else {
            queryStringAttribution.creative = value;
            return true;
        }
    }
}
