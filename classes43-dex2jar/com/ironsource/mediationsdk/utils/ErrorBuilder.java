// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.utils;

import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceError;

public class ErrorBuilder
{
    public static IronSourceError buildCappedPerPlacementError(final String s, final String s2) {
        return new IronSourceError(524, s + " Show Fail - " + s2);
    }
    
    public static IronSourceError buildCappedPerSessionError(final String s) {
        return new IronSourceError(526, s + " Show Fail - Networks have reached their cap per session");
    }
    
    public static IronSourceError buildGenericError(final String s) {
        String s2 = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            s2 = "An error occurred";
        }
        return new IronSourceError(510, s2);
    }
    
    public static IronSourceError buildInitFailedError(String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            s = s2 + " init failed due to an unknown error";
        }
        else {
            s = s2 + " - " + s;
        }
        return new IronSourceError(508, s);
    }
    
    public static IronSourceError buildInvalidConfigurationError(final String s) {
        return new IronSourceError(501, "" + s + " Init Fail - Configurations from the server are not valid");
    }
    
    public static IronSourceError buildInvalidCredentialsError(String string, final String s, final String s2) {
        final StringBuilder append = new StringBuilder().append("Init Fail - ").append(string).append(" value ").append(s).append(" is not valid");
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            string = " - " + s2;
        }
        else {
            string = "";
        }
        return new IronSourceError(506, append.append(string).toString());
    }
    
    public static IronSourceError buildInvalidKeyValueError(String string, final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)string) || TextUtils.isEmpty((CharSequence)s)) {
            return getGenericErrorForMissingParams();
        }
        final StringBuilder append = new StringBuilder().append("Mediation - ").append(string).append(" value is not valid for ").append(s);
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            string = " - " + s2;
        }
        else {
            string = "";
        }
        return new IronSourceError(506, append.append(string).toString());
    }
    
    public static IronSourceError buildKeyNotSetError(final String s, final String s2, final String s3) {
        if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
            return getGenericErrorForMissingParams();
        }
        return new IronSourceError(505, s3 + " Mediation - " + s + " is not set for " + s2);
    }
    
    public static IronSourceError buildLoadFailedError(String string) {
        if (TextUtils.isEmpty((CharSequence)string)) {
            string = "Load failed due to an unknown error";
        }
        else {
            string = "Load failed - " + string;
        }
        return new IronSourceError(510, string);
    }
    
    public static IronSourceError buildLoadFailedError(String string, String string2, final String s) {
        final StringBuilder append = new StringBuilder().append("").append(string).append(" Load Fail");
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            string = " " + string2;
        }
        else {
            string = "";
        }
        string2 = append.append(string).append(" - ").toString();
        string = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            string = "unknown error";
        }
        return new IronSourceError(510, string2 + string);
    }
    
    public static IronSourceError buildNoAdsToShowError(final String s) {
        return new IronSourceError(509, s + " Show Fail - No ads to show");
    }
    
    public static IronSourceError buildNoConfigurationAvailableError(final String s) {
        return new IronSourceError(501, "" + s + " Init Fail - Unable to retrieve configurations from the server");
    }
    
    public static IronSourceError buildNoInternetConnectionInitFailError(final String s) {
        return new IronSourceError(520, "" + s + " Init Fail - No Internet connection");
    }
    
    public static IronSourceError buildNoInternetConnectionLoadFailError(final String s) {
        return new IronSourceError(520, "" + s + " Load Fail - No Internet connection");
    }
    
    public static IronSourceError buildNoInternetConnectionShowFailError(final String s) {
        return new IronSourceError(520, "" + s + " Show Fail - No Internet connection");
    }
    
    public static IronSourceError buildNonExistentInstanceError(final String s) {
        return new IronSourceError(527, s + " The requested instance does not exist");
    }
    
    public static IronSourceError buildShowFailedError(final String s, final String s2) {
        return new IronSourceError(509, s + " Show Fail - " + s2);
    }
    
    public static IronSourceError buildUsingCachedConfigurationError(final String s, final String s2) {
        return new IronSourceError(502, "Mediation - Unable to retrieve configurations from IronSource server, using cached configurations with appKey:" + s + " and userId:" + s2);
    }
    
    private static IronSourceError getGenericErrorForMissingParams() {
        return buildGenericError("Mediation - wrong configuration");
    }
    
    public static IronSourceError unsupportedBannerSize(final String s) {
        return new IronSourceError(616, s + " unsupported banner size");
    }
}
