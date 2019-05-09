// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.drive.DriveContents;

public final class zzhc extends zzhb<DriveContents>
{
    public zzhc(final TaskCompletionSource<DriveContents> taskCompletionSource) {
        super(taskCompletionSource);
    }
    
    @Override
    public final void zza(final zzfb zzfb) throws RemoteException {
        this.zzap().setResult((Object)new zzbi(zzfb.zzai()));
    }
}
