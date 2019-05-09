// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import com.facebook.FacebookActivity;
import android.content.Intent;
import com.facebook.FacebookException;
import android.app.Activity;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import android.content.Context;
import com.facebook.FacebookSdk;
import android.net.Uri;

public class DialogPresenter
{
    public static boolean canPresentNativeDialogWithFeature(final DialogFeature dialogFeature) {
        return getProtocolVersionForNativeDialog(dialogFeature).getProtocolVersion() != -1;
    }
    
    public static boolean canPresentWebFallbackDialogWithFeature(final DialogFeature dialogFeature) {
        return getDialogWebFallbackUri(dialogFeature) != null;
    }
    
    private static Uri getDialogWebFallbackUri(final DialogFeature dialogFeature) {
        final FetchedAppSettings$DialogFeatureConfig dialogFeatureConfig = FetchedAppSettings.getDialogFeatureConfig(FacebookSdk.getApplicationId(), dialogFeature.getAction(), dialogFeature.name());
        Uri fallbackUrl = null;
        if (dialogFeatureConfig != null) {
            fallbackUrl = dialogFeatureConfig.getFallbackUrl();
        }
        return fallbackUrl;
    }
    
    public static NativeProtocol$ProtocolVersionQueryResult getProtocolVersionForNativeDialog(final DialogFeature dialogFeature) {
        final String applicationId = FacebookSdk.getApplicationId();
        final String action = dialogFeature.getAction();
        return NativeProtocol.getLatestAvailableProtocolVersionForAction(action, getVersionSpecForFeature(applicationId, action, dialogFeature));
    }
    
    private static int[] getVersionSpecForFeature(final String s, final String s2, final DialogFeature dialogFeature) {
        final FetchedAppSettings$DialogFeatureConfig dialogFeatureConfig = FetchedAppSettings.getDialogFeatureConfig(s, s2, dialogFeature.name());
        if (dialogFeatureConfig != null) {
            return dialogFeatureConfig.getVersionSpec();
        }
        return new int[] { dialogFeature.getMinVersion() };
    }
    
    public static void logDialogActivity(final Context context, final String s, final String s2) {
        final AppEventsLogger logger = AppEventsLogger.newLogger(context);
        final Bundle bundle = new Bundle();
        bundle.putString("fb_dialog_outcome", s2);
        logger.logSdkEvent(s, (Double)null, bundle);
    }
    
    public static void present(final AppCall appCall, final Activity activity) {
        activity.startActivityForResult(appCall.getRequestIntent(), appCall.getRequestCode());
        appCall.setPending();
    }
    
    public static void present(final AppCall appCall, final FragmentWrapper fragmentWrapper) {
        fragmentWrapper.startActivityForResult(appCall.getRequestIntent(), appCall.getRequestCode());
        appCall.setPending();
    }
    
    public static void setupAppCallForCannotShowError(final AppCall appCall) {
        setupAppCallForValidationError(appCall, new FacebookException("Unable to show the provided content via the web or the installed version of the Facebook app. Some dialogs are only supported starting API 14."));
    }
    
    public static void setupAppCallForErrorResult(final AppCall appCall, final FacebookException ex) {
        if (ex == null) {
            return;
        }
        Validate.hasFacebookActivity(FacebookSdk.getApplicationContext());
        final Intent requestIntent = new Intent();
        requestIntent.setClass(FacebookSdk.getApplicationContext(), (Class)FacebookActivity.class);
        requestIntent.setAction(FacebookActivity.PASS_THROUGH_CANCEL_ACTION);
        NativeProtocol.setupProtocolRequestIntent(requestIntent, appCall.getCallId().toString(), (String)null, NativeProtocol.getLatestKnownVersion(), NativeProtocol.createBundleForException(ex));
        appCall.setRequestIntent(requestIntent);
    }
    
    public static void setupAppCallForNativeDialog(final AppCall appCall, final ParameterProvider parameterProvider, final DialogFeature dialogFeature) {
        final Context applicationContext = FacebookSdk.getApplicationContext();
        final String action = dialogFeature.getAction();
        final NativeProtocol$ProtocolVersionQueryResult protocolVersionForNativeDialog = getProtocolVersionForNativeDialog(dialogFeature);
        final int protocolVersion = protocolVersionForNativeDialog.getProtocolVersion();
        if (protocolVersion == -1) {
            throw new FacebookException("Cannot present this dialog. This likely means that the Facebook app is not installed.");
        }
        Bundle bundle;
        if (NativeProtocol.isVersionCompatibleWithBucketedIntent(protocolVersion)) {
            bundle = parameterProvider.getParameters();
        }
        else {
            bundle = parameterProvider.getLegacyParameters();
        }
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        final Intent platformActivityIntent = NativeProtocol.createPlatformActivityIntent(applicationContext, appCall.getCallId().toString(), action, protocolVersionForNativeDialog, bundle2);
        if (platformActivityIntent == null) {
            throw new FacebookException("Unable to create Intent; this likely means theFacebook app is not installed.");
        }
        appCall.setRequestIntent(platformActivityIntent);
    }
    
    public static void setupAppCallForValidationError(final AppCall appCall, final FacebookException ex) {
        setupAppCallForErrorResult(appCall, ex);
    }
    
    public static void setupAppCallForWebDialog(final AppCall appCall, final String s, final Bundle bundle) {
        Validate.hasFacebookActivity(FacebookSdk.getApplicationContext());
        Validate.hasInternetPermissions(FacebookSdk.getApplicationContext());
        final Bundle bundle2 = new Bundle();
        bundle2.putString("action", s);
        bundle2.putBundle("params", bundle);
        final Intent requestIntent = new Intent();
        NativeProtocol.setupProtocolRequestIntent(requestIntent, appCall.getCallId().toString(), s, NativeProtocol.getLatestKnownVersion(), bundle2);
        requestIntent.setClass(FacebookSdk.getApplicationContext(), (Class)FacebookActivity.class);
        requestIntent.setAction("FacebookDialogFragment");
        appCall.setRequestIntent(requestIntent);
    }
    
    public static void setupAppCallForWebFallbackDialog(final AppCall appCall, Bundle queryParamsForPlatformActivityIntentWebFallback, final DialogFeature dialogFeature) {
        Validate.hasFacebookActivity(FacebookSdk.getApplicationContext());
        Validate.hasInternetPermissions(FacebookSdk.getApplicationContext());
        final String name = dialogFeature.name();
        final Uri dialogWebFallbackUri = getDialogWebFallbackUri(dialogFeature);
        if (dialogWebFallbackUri == null) {
            throw new FacebookException("Unable to fetch the Url for the DialogFeature : '" + name + "'");
        }
        queryParamsForPlatformActivityIntentWebFallback = ServerProtocol.getQueryParamsForPlatformActivityIntentWebFallback(appCall.getCallId().toString(), NativeProtocol.getLatestKnownVersion(), queryParamsForPlatformActivityIntentWebFallback);
        if (queryParamsForPlatformActivityIntentWebFallback == null) {
            throw new FacebookException("Unable to fetch the app's key-hash");
        }
        Uri uri;
        if (dialogWebFallbackUri.isRelative()) {
            uri = Utility.buildUri(ServerProtocol.getDialogAuthority(), dialogWebFallbackUri.toString(), queryParamsForPlatformActivityIntentWebFallback);
        }
        else {
            uri = Utility.buildUri(dialogWebFallbackUri.getAuthority(), dialogWebFallbackUri.getPath(), queryParamsForPlatformActivityIntentWebFallback);
        }
        final Bundle bundle = new Bundle();
        bundle.putString("url", uri.toString());
        bundle.putBoolean("is_fallback", true);
        final Intent requestIntent = new Intent();
        NativeProtocol.setupProtocolRequestIntent(requestIntent, appCall.getCallId().toString(), dialogFeature.getAction(), NativeProtocol.getLatestKnownVersion(), bundle);
        requestIntent.setClass(FacebookSdk.getApplicationContext(), (Class)FacebookActivity.class);
        requestIntent.setAction("FacebookDialogFragment");
        appCall.setRequestIntent(requestIntent);
    }
    
    public interface ParameterProvider
    {
        Bundle getLegacyParameters();
        
        Bundle getParameters();
    }
}
