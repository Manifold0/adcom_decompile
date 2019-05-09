// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzan extends zzl
{
    private final BaseImplementation$ResultHolder<DriveApi.DriveIdResult> zzdv;
    
    public zzan(final BaseImplementation$ResultHolder<DriveApi.DriveIdResult> zzdv) {
        this.zzdv = zzdv;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zzdv.setResult((Object)new zzao(status, null));
    }
    
    @Override
    public final void zza(final zzfh zzfh) throws RemoteException {
        this.zzdv.setResult((Object)new zzao(Status.RESULT_SUCCESS, zzfh.zzdb));
    }
    
    @Override
    public final void zza(final zzfs zzfs) throws RemoteException {
        this.zzdv.setResult((Object)new zzao(Status.RESULT_SUCCESS, new zzaa(zzfs.zzdl).getDriveId()));
    }
}
