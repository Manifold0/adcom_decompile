// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.R$string;
import android.content.Context;
import com.google.android.gms.common.api.Status;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.annotation.KeepForSdk;

@Deprecated
@KeepForSdk
public final class GoogleServices
{
    private static final Object sLock;
    @GuardedBy("sLock")
    private static GoogleServices zzay;
    private final String zzaz;
    private final Status zzba;
    private final boolean zzbb;
    private final boolean zzbc;
    
    static {
        sLock = new Object();
    }
    
    @KeepForSdk
    @VisibleForTesting
    GoogleServices(final Context context) {
        boolean zzbb = true;
        boolean zzbc = true;
        final Resources resources = context.getResources();
        final int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R$string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            zzbb = (resources.getInteger(identifier) != 0);
            if (zzbb) {
                zzbc = false;
            }
            this.zzbc = zzbc;
        }
        else {
            this.zzbc = false;
        }
        this.zzbb = zzbb;
        String zzaz;
        if ((zzaz = zzp.zzc(context)) == null) {
            zzaz = new StringResourceValueReader(context).getString("google_app_id");
        }
        if (TextUtils.isEmpty((CharSequence)zzaz)) {
            this.zzba = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzaz = null;
            return;
        }
        this.zzaz = zzaz;
        this.zzba = Status.RESULT_SUCCESS;
    }
    
    @KeepForSdk
    @VisibleForTesting
    GoogleServices(final String zzaz, final boolean zzbb) {
        this.zzaz = zzaz;
        this.zzba = Status.RESULT_SUCCESS;
        this.zzbb = zzbb;
        this.zzbc = !zzbb;
    }
    
    @KeepForSdk
    private static GoogleServices checkInitialized(final String s) {
        synchronized (GoogleServices.sLock) {
            if (GoogleServices.zzay == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(s).length() + 34).append("Initialize must be called before ").append(s).append(".").toString());
            }
        }
        // monitorexit(o)
        return GoogleServices.zzay;
    }
    
    @KeepForSdk
    @VisibleForTesting
    static void clearInstanceForTest() {
        synchronized (GoogleServices.sLock) {
            GoogleServices.zzay = null;
        }
    }
    
    @KeepForSdk
    public static String getGoogleAppId() {
        return checkInitialized("getGoogleAppId").zzaz;
    }
    
    @KeepForSdk
    public static Status initialize(final Context context) {
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (GoogleServices.sLock) {
            if (GoogleServices.zzay == null) {
                GoogleServices.zzay = new GoogleServices(context);
            }
            return GoogleServices.zzay.zzba;
        }
    }
    
    @KeepForSdk
    public static Status initialize(final Context context, final String s, final boolean b) {
        Preconditions.checkNotNull(context, "Context must not be null.");
        Preconditions.checkNotEmpty(s, "App ID must be nonempty.");
        synchronized (GoogleServices.sLock) {
            if (GoogleServices.zzay != null) {
                return GoogleServices.zzay.checkGoogleAppId(s);
            }
            return (GoogleServices.zzay = new GoogleServices(s, b)).zzba;
        }
    }
    
    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        final GoogleServices checkInitialized = checkInitialized("isMeasurementEnabled");
        return checkInitialized.zzba.isSuccess() && checkInitialized.zzbb;
    }
    
    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return checkInitialized("isMeasurementExplicitlyDisabled").zzbc;
    }
    
    @KeepForSdk
    @VisibleForTesting
    final Status checkGoogleAppId(String zzaz) {
        if (this.zzaz != null && !this.zzaz.equals(zzaz)) {
            zzaz = this.zzaz;
            return new Status(10, new StringBuilder(String.valueOf(zzaz).length() + 97).append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '").append(zzaz).append("'.").toString());
        }
        return Status.RESULT_SUCCESS;
    }
}
