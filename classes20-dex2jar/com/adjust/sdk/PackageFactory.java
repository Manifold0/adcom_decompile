// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.Iterator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import android.net.UrlQuerySanitizer$ParameterValuePair;
import java.util.List;
import android.net.UrlQuerySanitizer;
import android.net.Uri;

public class PackageFactory
{
    private static final String ADJUST_PREFIX = "adjust_";
    
    public static ActivityPackage buildDeeplinkSdkClickPackage(final Uri uri, final long clickTimeInMilliseconds, final ActivityState activityState, final AdjustConfig adjustConfig, final DeviceInfo deviceInfo, final SessionParameters sessionParameters) {
        if (uri != null) {
            final String string = uri.toString();
            if (string != null && string.length() != 0) {
                AdjustFactory.getLogger().verbose("Url to parse (%s)", uri);
                final UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
                urlQuerySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
                urlQuerySanitizer.setAllowUnregisteredParamaters(true);
                urlQuerySanitizer.parseUrl(string);
                final PackageBuilder queryStringClickPackageBuilder = queryStringClickPackageBuilder(urlQuerySanitizer.getParameterList(), activityState, adjustConfig, deviceInfo, sessionParameters);
                if (queryStringClickPackageBuilder != null) {
                    queryStringClickPackageBuilder.deeplink = uri.toString();
                    queryStringClickPackageBuilder.clickTimeInMilliseconds = clickTimeInMilliseconds;
                    return queryStringClickPackageBuilder.buildClickPackage("deeplink");
                }
            }
        }
        return null;
    }
    
    public static ActivityPackage buildInstallReferrerSdkClickPackage(final String referrer, final long clicktTimeInSeconds, final long installBeginTimeInSeconds, final ActivityState activityState, final AdjustConfig adjustConfig, final DeviceInfo deviceInfo, final SessionParameters sessionParameters) {
        if (referrer == null || referrer.length() == 0) {
            return null;
        }
        final PackageBuilder packageBuilder = new PackageBuilder(adjustConfig, deviceInfo, activityState, sessionParameters, System.currentTimeMillis());
        if (packageBuilder == null) {
            return null;
        }
        packageBuilder.referrer = referrer;
        packageBuilder.clicktTimeInSeconds = clicktTimeInSeconds;
        packageBuilder.installBeginTimeInSeconds = installBeginTimeInSeconds;
        return packageBuilder.buildClickPackage("install_referrer");
    }
    
    public static ActivityPackage buildReftagSdkClickPackage(final String rawReferrer, final long clickTimeInMilliseconds, ActivityState queryStringClickPackageBuilder, final AdjustConfig adjustConfig, final DeviceInfo deviceInfo, final SessionParameters sessionParameters) {
        if (rawReferrer == null || rawReferrer.length() == 0) {
            return null;
        }
        String decode;
        while (true) {
            try {
                decode = URLDecoder.decode(rawReferrer, "UTF-8");
                AdjustFactory.getLogger().verbose("Referrer to parse (%s)", decode);
                final UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
                urlQuerySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
                urlQuerySanitizer.setAllowUnregisteredParamaters(true);
                urlQuerySanitizer.parseQuery(decode);
                queryStringClickPackageBuilder = (ActivityState)queryStringClickPackageBuilder(urlQuerySanitizer.getParameterList(), queryStringClickPackageBuilder, adjustConfig, deviceInfo, sessionParameters);
                if (queryStringClickPackageBuilder == null) {
                    return null;
                }
            }
            catch (UnsupportedEncodingException ex) {
                decode = "malformed";
                continue;
            }
            break;
        }
        ((PackageBuilder)queryStringClickPackageBuilder).referrer = decode;
        ((PackageBuilder)queryStringClickPackageBuilder).clickTimeInMilliseconds = clickTimeInMilliseconds;
        ((PackageBuilder)queryStringClickPackageBuilder).rawReferrer = rawReferrer;
        return ((PackageBuilder)queryStringClickPackageBuilder).buildClickPackage("reftag");
    }
    
    private static PackageBuilder queryStringClickPackageBuilder(final List<UrlQuerySanitizer$ParameterValuePair> list, final ActivityState activityState, final AdjustConfig adjustConfig, final DeviceInfo deviceInfo, final SessionParameters sessionParameters) {
        if (list == null) {
            return null;
        }
        final LinkedHashMap<Object, String> extraParameters = new LinkedHashMap<Object, String>();
        final AdjustAttribution attribution = new AdjustAttribution();
        for (final UrlQuerySanitizer$ParameterValuePair urlQuerySanitizer$ParameterValuePair : list) {
            readQueryString(urlQuerySanitizer$ParameterValuePair.mParameter, urlQuerySanitizer$ParameterValuePair.mValue, (Map<String, String>)extraParameters, attribution);
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final String reftag = extraParameters.remove("reftag");
        if (activityState != null) {
            activityState.lastInterval = currentTimeMillis - activityState.lastActivity;
        }
        final PackageBuilder packageBuilder = new PackageBuilder(adjustConfig, deviceInfo, activityState, sessionParameters, currentTimeMillis);
        packageBuilder.extraParameters = (Map<String, String>)extraParameters;
        packageBuilder.attribution = attribution;
        packageBuilder.reftag = reftag;
        return packageBuilder;
    }
    
    private static boolean readQueryString(String substring, final String s, final Map<String, String> map, final AdjustAttribution adjustAttribution) {
        if (substring != null && s != null && substring.startsWith("adjust_")) {
            substring = substring.substring("adjust_".length());
            if (substring.length() != 0 && s.length() != 0) {
                if (!tryToSetAttribution(adjustAttribution, substring, s)) {
                    map.put(substring, s);
                }
                return true;
            }
        }
        return false;
    }
    
    private static boolean tryToSetAttribution(final AdjustAttribution adjustAttribution, final String s, final String s2) {
        if (s.equals("tracker")) {
            adjustAttribution.trackerName = s2;
            return true;
        }
        if (s.equals("campaign")) {
            adjustAttribution.campaign = s2;
            return true;
        }
        if (s.equals("adgroup")) {
            adjustAttribution.adgroup = s2;
            return true;
        }
        if (s.equals("creative")) {
            adjustAttribution.creative = s2;
            return true;
        }
        return false;
    }
}
