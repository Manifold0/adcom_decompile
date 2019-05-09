// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.logger;

public class IronSourceError
{
    public static final int ERROR_BN_INIT_FAILED_AFTER_LOAD = 602;
    public static final int ERROR_BN_INSTANCE_INIT_ERROR = 612;
    public static final int ERROR_BN_INSTANCE_INIT_TIMEOUT = 607;
    public static final int ERROR_BN_INSTANCE_LOAD_EMPTY_ADAPTER = 611;
    public static final int ERROR_BN_INSTANCE_LOAD_EMPTY_BANNER = 610;
    public static final int ERROR_BN_INSTANCE_LOAD_TIMEOUT = 608;
    public static final int ERROR_BN_INSTANCE_RELOAD_TIMEOUT = 609;
    public static final int ERROR_BN_LOAD_AFTER_INIT_FAILED = 600;
    public static final int ERROR_BN_LOAD_AFTER_LONG_INITIATION = 601;
    public static final int ERROR_BN_LOAD_EXCEPTION = 605;
    public static final int ERROR_BN_LOAD_NO_CONFIG = 615;
    public static final int ERROR_BN_LOAD_NO_FILL = 606;
    public static final int ERROR_BN_LOAD_PLACEMENT_CAPPED = 604;
    public static final int ERROR_BN_LOAD_WHILE_LONG_INITIATION = 603;
    public static final int ERROR_BN_RELOAD_SKIP_BACKGROUND = 614;
    public static final int ERROR_BN_RELOAD_SKIP_INVISIBLE = 613;
    public static final int ERROR_BN_UNSUPPORTED_SIZE = 616;
    public static final int ERROR_CAPPED_PER_SESSION = 526;
    public static final int ERROR_CODE_GENERIC = 510;
    public static final int ERROR_CODE_INIT_FAILED = 508;
    public static final int ERROR_CODE_INVALID_KEY_VALUE = 506;
    public static final int ERROR_CODE_KEY_NOT_SET = 505;
    public static final int ERROR_CODE_NO_ADS_TO_SHOW = 509;
    public static final int ERROR_CODE_NO_CONFIGURATION_AVAILABLE = 501;
    public static final int ERROR_CODE_USING_CACHED_CONFIGURATION = 502;
    public static final int ERROR_NON_EXISTENT_INSTANCE = 527;
    public static final int ERROR_NO_INTERNET_CONNECTION = 520;
    public static final int ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT = 524;
    private int mErrorCode;
    private String mErrorMsg;
    
    public IronSourceError(final int mErrorCode, final String s) {
        this.mErrorCode = mErrorCode;
        String mErrorMsg = s;
        if (s == null) {
            mErrorMsg = "";
        }
        this.mErrorMsg = mErrorMsg;
    }
    
    public int getErrorCode() {
        return this.mErrorCode;
    }
    
    public String getErrorMessage() {
        return this.mErrorMsg;
    }
    
    @Override
    public String toString() {
        return "errorCode:" + this.mErrorCode + ", errorMessage:" + this.mErrorMsg;
    }
}
