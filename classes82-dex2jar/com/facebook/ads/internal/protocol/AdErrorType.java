// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.protocol;

public enum AdErrorType
{
    AD_ALREADY_STARTED(7001, "Ad already started", true), 
    AD_REQUEST_FAILED(1111, "Facebook Ads SDK request for ads failed", false), 
    AD_REQUEST_TIMEOUT(1112, "Facebook Ads SDK request for ads timed out", false), 
    BID_IMPRESSION_MISMATCH(4001, "Bid payload does not match placement", true), 
    BID_PAYLOAD_ERROR(4002, "Invalid bid payload", false), 
    BROKEN_MEDIA_ERROR(2100, "Failed to load Media for Native Ad", true), 
    CACHE_FAILURE_ERROR(2002, "Pre Caching failure", true), 
    CLEAR_TEXT_SUPPORT_NOT_ALLOWED(7003, "In order to use cache in Facebook Audience Network SDK you should whitelist 127.0.0.1 in your Network Security Configuration:\n<domain-config cleartextTrafficPermitted=\"true\">\n    <domain includeSubdomains=\"true\">127.0.0.1</domain>\n</domain-config>\nSee more: https://developers.facebook.com/docs/audience-network/android-network-security-config", true), 
    DISABLED_APP(1005, "App is disabled from making ad requests", true), 
    ERROR_MESSAGE(1203, "Facebook Ads SDK delivery response Error message", true), 
    IMAGE_CACHE_ERROR(8001, "Failed to cache image", false), 
    INCORRECT_STATE_ERROR(7004, "You can't call %s for ad in state %s", true), 
    INTERNAL_ERROR(2001, "Internal Error", true), 
    INTERSTITIAL_AD_TIMEOUT(2009, "Timeout loading Interstitial Ad", true), 
    INTERSTITIAL_CONTROLLER_IS_NULL(5003, "Interstitial Controller is null show Ad", false), 
    LOAD_CALLED_WHILE_SHOWING_AD(7002, "Ad cannot be loaded while being displayed", true), 
    LOAD_TOO_FREQUENTLY(1002, "Ad was re-loaded too frequently", true), 
    MEDIATION_ERROR(3001, "Mediation Error", true), 
    MISSING_DEPENDENCIES_ERROR(7005, "Facebook Audience Network SDK doesn't have all required classes. Please, check LogCat output for tag %s. See more: https://developers.facebook.com/docs/audience-network/android/ ", true), 
    NETWORK_ERROR(1000, "Network Error", true), 
    NO_ADAPTER_ON_LOAD(5001, "Adapter is null onLoad Ad", false), 
    NO_ADAPTER_ON_START(5002, "Adapter is null onStart Ad", false), 
    NO_AD_PLACEMENT(1302, "Facebook Ads SDK returned no ad placements", false), 
    NO_FILL(1001, "No Fill", true), 
    NO_ICONVIEW_IN_NATIVEBANNERAD(6002, "IconView is missing in NativeBannerAd", true), 
    NO_MEDIAVIEW_IN_NATIVEAD(6001, "MediaView is missing in NativeAd", true), 
    PARSER_FAILURE(1201, "Failed to parse Facebook Ads SDK delivery response", false), 
    REMOTE_ADS_SERVICE_ERROR(2008, "Ads Service process error", true), 
    SERVER_ERROR(2000, "Server Error", true), 
    START_BEFORE_INIT(2005, "initAd must be called before startAd", true), 
    UNKNOWN_ERROR(-1, "unknown error", false), 
    UNKNOWN_RESPONSE(1202, "Unknown Facebook Ads SDK delivery response type", false), 
    UNSUPPORTED_AD_ASSET_NATIVEAD(6003, "unsupported type of ad assets", true), 
    WEB_VIEW_FAILED_TO_LOAD(5004, "WebView failed to load", false);
    
    private final int a;
    private final String b;
    private final boolean c;
    
    private AdErrorType(final int a, final String b, final boolean c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public static AdErrorType adErrorTypeFromCode(final int n) {
        return adErrorTypeFromCode(n, AdErrorType.UNKNOWN_ERROR);
    }
    
    public static AdErrorType adErrorTypeFromCode(final int n, final AdErrorType adErrorType) {
        final AdErrorType[] values = values();
        final int length = values.length;
        int n2 = 0;
        AdErrorType adErrorType2;
        while (true) {
            adErrorType2 = adErrorType;
            if (n2 >= length) {
                break;
            }
            adErrorType2 = values[n2];
            if (adErrorType2.getErrorCode() == n) {
                break;
            }
            ++n2;
        }
        return adErrorType2;
    }
    
    public String getDefaultErrorMessage() {
        return this.b;
    }
    
    public int getErrorCode() {
        return this.a;
    }
    
    public boolean isPublicError() {
        return this.c;
    }
}
