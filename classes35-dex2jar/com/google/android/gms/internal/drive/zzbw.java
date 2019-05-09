// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzbw extends zzl
{
    private final BaseImplementation$ResultHolder<DriveFolder.DriveFolderResult> zzdv;
    
    public zzbw(final BaseImplementation$ResultHolder<DriveFolder.DriveFolderResult> zzdv) {
        this.zzdv = zzdv;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zzdv.setResult((Object)new zzbz(status, null));
    }
    
    @Override
    public final void zza(final zzfh zzfh) throws RemoteException {
        this.zzdv.setResult((Object)new zzbz(Status.RESULT_SUCCESS, new zzbs(zzfh.zzdb)));
    }
}
