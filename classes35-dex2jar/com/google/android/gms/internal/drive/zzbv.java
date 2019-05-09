// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzbv extends zzl
{
    private final BaseImplementation$ResultHolder<DriveFolder.DriveFileResult> zzdv;
    
    public zzbv(final BaseImplementation$ResultHolder<DriveFolder.DriveFileResult> zzdv) {
        this.zzdv = zzdv;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zzdv.setResult((Object)new zzbx(status, null));
    }
    
    @Override
    public final void zza(final zzfh zzfh) throws RemoteException {
        this.zzdv.setResult((Object)new zzbx(Status.RESULT_SUCCESS, new zzbn(zzfh.zzdb)));
    }
}
