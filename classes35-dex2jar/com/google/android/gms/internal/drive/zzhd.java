// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.drive.DriveFile;

public final class zzhd extends zzhb<DriveFile>
{
    public zzhd(final TaskCompletionSource<DriveFile> taskCompletionSource) {
        super(taskCompletionSource);
    }
    
    @Override
    public final void zza(final zzfh zzfh) throws RemoteException {
        this.zzap().setResult((Object)zzfh.getDriveId().asDriveFile());
    }
}
