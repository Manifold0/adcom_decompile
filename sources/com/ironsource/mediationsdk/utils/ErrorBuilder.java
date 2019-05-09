package com.ironsource.mediationsdk.utils;

import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceError;

public class ErrorBuilder {
    public static IronSourceError buildNoConfigurationAvailableError(String adUnit) {
        return new IronSourceError(IronSourceError.ERROR_CODE_NO_CONFIGURATION_AVAILABLE, "" + adUnit + " Init Fail - Unable to retrieve configurations from the server");
    }

    public static IronSourceError buildInvalidConfigurationError(String adUnit) {
        return new IronSourceError(IronSourceError.ERROR_CODE_NO_CONFIGURATION_AVAILABLE, "" + adUnit + " Init Fail - Configurations from the server are not valid");
    }

    public static IronSourceError buildUsingCachedConfigurationError(String appKey, String userId) {
        return new IronSourceError(IronSourceError.ERROR_CODE_USING_CACHED_CONFIGURATION, "Mediation - Unable to retrieve configurations from IronSource server, using cached configurations with appKey:" + appKey + " and userId:" + userId);
    }

    public static IronSourceError buildKeyNotSetError(String key, String provider, String adUnit) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(provider)) {
            return getGenericErrorForMissingParams();
        }
        return new IronSourceError(IronSourceError.ERROR_CODE_KEY_NOT_SET, adUnit + " Mediation - " + key + " is not set for " + provider);
    }

    public static IronSourceError buildInvalidKeyValueError(String key, String provider, String optionalReason) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(provider)) {
            return getGenericErrorForMissingParams();
        }
        return new IronSourceError(IronSourceError.ERROR_CODE_INVALID_KEY_VALUE, "Mediation - " + key + " value is not valid for " + provider + (!TextUtils.isEmpty(optionalReason) ? " - " + optionalReason : ""));
    }

    public static IronSourceError buildInvalidCredentialsError(String credentialName, String credentialValue, String errorMessage) {
        return new IronSourceError(IronSourceError.ERROR_CODE_INVALID_KEY_VALUE, "Init Fail - " + credentialName + " value " + credentialValue + " is not valid" + (!TextUtils.isEmpty(errorMessage) ? " - " + errorMessage : ""));
    }

    public static IronSourceError buildInitFailedError(String errorMsg, String adUnit) {
        if (TextUtils.isEmpty(errorMsg)) {
            errorMsg = adUnit + " init failed due to an unknown error";
        } else {
            errorMsg = adUnit + " - " + errorMsg;
        }
        return new IronSourceError(IronSourceError.ERROR_CODE_INIT_FAILED, errorMsg);
    }

    public static IronSourceError buildNoAdsToShowError(String adUnit) {
        return new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, adUnit + " Show Fail - No ads to show");
    }

    public static IronSourceError buildShowFailedError(String adUnit, String error) {
        return new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, adUnit + " Show Fail - " + error);
    }

    public static IronSourceError buildLoadFailedError(String adUnit, String adapterName, String errorMsg) {
        String resultingMessage = "" + adUnit + " Load Fail" + (!TextUtils.isEmpty(adapterName) ? " " + adapterName : "") + " - ";
        if (TextUtils.isEmpty(errorMsg)) {
            errorMsg = "unknown error";
        }
        return new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, resultingMessage + errorMsg);
    }

    public static IronSourceError buildGenericError(String errorMsg) {
        if (TextUtils.isEmpty(errorMsg)) {
            errorMsg = "An error occurred";
        }
        return new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, errorMsg);
    }

    public static IronSourceError buildNoInternetConnectionInitFailError(String adUnit) {
        return new IronSourceError(IronSourceError.ERROR_NO_INTERNET_CONNECTION, "" + adUnit + " Init Fail - No Internet connection");
    }

    public static IronSourceError buildNoInternetConnectionLoadFailError(String adUnit) {
        return new IronSourceError(IronSourceError.ERROR_NO_INTERNET_CONNECTION, "" + adUnit + " Load Fail - No Internet connection");
    }

    public static IronSourceError buildNoInternetConnectionShowFailError(String adUnit) {
        return new IronSourceError(IronSourceError.ERROR_NO_INTERNET_CONNECTION, "" + adUnit + " Show Fail - No Internet connection");
    }

    public static IronSourceError buildCappedPerPlacementError(String adUnit, String error) {
        return new IronSourceError(IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT, adUnit + " Show Fail - " + error);
    }

    public static IronSourceError buildCappedPerSessionError(String adUnit) {
        return new IronSourceError(IronSourceError.ERROR_CAPPED_PER_SESSION, adUnit + " Show Fail - Networks have reached their cap per session");
    }

    public static IronSourceError buildNonExistentInstanceError(String adUnit) {
        return new IronSourceError(IronSourceError.ERROR_NON_EXISTENT_INSTANCE, adUnit + " The requested instance does not exist");
    }

    private static IronSourceError getGenericErrorForMissingParams() {
        return buildGenericError("Mediation - wrong configuration");
    }

    public static IronSourceError buildLoadFailedError(String errorMsg) {
        if (TextUtils.isEmpty(errorMsg)) {
            errorMsg = "Load failed due to an unknown error";
        } else {
            errorMsg = "Load failed - " + errorMsg;
        }
        return new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, errorMsg);
    }

    public static IronSourceError unsupportedBannerSize(String providerName) {
        return new IronSourceError(IronSourceError.ERROR_BN_UNSUPPORTED_SIZE, providerName + " unsupported banner size");
    }
}
