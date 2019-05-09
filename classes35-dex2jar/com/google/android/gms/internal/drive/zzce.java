// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzce extends zzl
{
    private final BaseImplementation$ResultHolder<DrivePreferencesApi.FileUploadPreferencesResult> zzdv;
    private final /* synthetic */ zzcb zzfi;
    
    private zzce(final zzcb zzfi, final BaseImplementation$ResultHolder<DrivePreferencesApi.FileUploadPreferencesResult> zzdv) {
        this.zzfi = zzfi;
        this.zzdv = zzdv;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zzdv.setResult((Object)new zzcf(this.zzfi, status, null, null));
    }
    
    @Override
    public final void zza(final zzfd zzfd) throws RemoteException {
        this.zzdv.setResult((Object)new zzcf(this.zzfi, Status.RESULT_SUCCESS, zzfd.zzhg, null));
    }
}
