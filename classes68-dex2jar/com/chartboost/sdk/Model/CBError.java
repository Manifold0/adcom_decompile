// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Model;

public final class CBError
{
    private final a a;
    private final String b;
    private boolean c;
    
    public CBError(final a a, final String b) {
        this.a = a;
        this.b = b;
        this.c = true;
    }
    
    public a a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
    
    public CBImpressionError c() {
        switch (CBError$1.a[this.a.ordinal()]) {
            default: {
                return CBImpressionError.NETWORK_FAILURE;
            }
            case 1:
            case 2:
            case 3: {
                return CBImpressionError.INTERNAL;
            }
            case 4: {
                return CBImpressionError.INTERNET_UNAVAILABLE;
            }
            case 5: {
                return CBImpressionError.NO_AD_FOUND;
            }
        }
    }
    
    public enum CBClickError
    {
        AGE_GATE_FAILURE, 
        INTERNAL, 
        NO_HOST_ACTIVITY, 
        URI_INVALID, 
        URI_UNRECOGNIZED;
    }
    
    public enum CBImpressionError
    {
        ACTIVITY_MISSING_IN_MANIFEST, 
        ASSETS_DOWNLOAD_FAILURE, 
        ASSET_MISSING, 
        ASSET_PREFETCH_IN_PROGRESS, 
        EMPTY_LOCAL_VIDEO_LIST, 
        END_POINT_DISABLED, 
        ERROR_CREATING_VIEW, 
        ERROR_DISPLAYING_VIEW, 
        ERROR_LOADING_WEB_VIEW, 
        ERROR_PLAYING_VIDEO, 
        FIRST_SESSION_INTERSTITIALS_DISABLED, 
        HARDWARE_ACCELERATION_DISABLED, 
        IMPRESSION_ALREADY_VISIBLE, 
        INCOMPATIBLE_API_VERSION, 
        INTERNAL, 
        INTERNET_UNAVAILABLE, 
        INTERNET_UNAVAILABLE_AT_SHOW, 
        INVALID_LOCATION, 
        INVALID_RESPONSE, 
        NETWORK_FAILURE, 
        NO_AD_FOUND, 
        NO_HOST_ACTIVITY, 
        PENDING_IMPRESSION_ERROR, 
        SESSION_NOT_STARTED, 
        TOO_MANY_CONNECTIONS, 
        USER_CANCELLATION, 
        VIDEO_ID_MISSING, 
        VIDEO_UNAVAILABLE, 
        VIDEO_UNAVAILABLE_FOR_CURRENT_ORIENTATION, 
        WEB_VIEW_CLIENT_RECEIVED_ERROR, 
        WEB_VIEW_PAGE_LOAD_TIMEOUT, 
        WRONG_ORIENTATION;
    }
    
    public enum a
    {
        a, 
        b, 
        c, 
        d, 
        e, 
        f, 
        g;
    }
}
