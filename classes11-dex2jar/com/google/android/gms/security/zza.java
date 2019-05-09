// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.security;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import android.content.Context;
import android.os.AsyncTask;

final class zza extends AsyncTask<Void, Void, Integer>
{
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ ProviderInstaller.ProviderInstallListener zzix;
    
    zza(final Context val$context, final ProviderInstaller.ProviderInstallListener zzix) {
        this.val$context = val$context;
        this.zzix = zzix;
    }
    
    private final Integer zza(final Void... array) {
        try {
            ProviderInstaller.installIfNeeded(this.val$context);
            return 0;
        }
        catch (GooglePlayServicesRepairableException ex) {
            return ex.getConnectionStatusCode();
        }
        catch (GooglePlayServicesNotAvailableException ex2) {
            return ex2.errorCode;
        }
    }
}
