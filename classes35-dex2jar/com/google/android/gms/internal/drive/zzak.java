// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzak extends zzl
{
    private final BaseImplementation$ResultHolder<DriveApi.DriveContentsResult> zzdv;
    
    zzak(final BaseImplementation$ResultHolder<DriveApi.DriveContentsResult> zzdv) {
        this.zzdv = zzdv;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zzdv.setResult((Object)new zzal(status, null));
    }
    
    @Override
    public final void zza(final zzfb zzfb) throws RemoteException {
        this.zzdv.setResult((Object)new zzal(Status.RESULT_SUCCESS, new zzbi(zzfb.zzeq)));
    }
}
