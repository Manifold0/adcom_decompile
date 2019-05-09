// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.drive.DriveId;

public final class zzhf extends zzhb<DriveId>
{
    public zzhf(final TaskCompletionSource<DriveId> taskCompletionSource) {
        super(taskCompletionSource);
    }
    
    @Override
    public final void zza(final zzfh zzfh) throws RemoteException {
        this.zzap().setResult((Object)zzfh.getDriveId());
    }
    
    @Override
    public final void zza(final zzfs zzfs) throws RemoteException {
        this.zzap().setResult((Object)new zzaa(zzfs.zzan()).getDriveId());
    }
}
