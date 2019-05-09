// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.drive.MetadataBuffer;

public final class zzhi extends zzhb<MetadataBuffer>
{
    public zzhi(final TaskCompletionSource<MetadataBuffer> taskCompletionSource) {
        super(taskCompletionSource);
    }
    
    @Override
    public final void zza(final zzfp zzfp) throws RemoteException {
        this.zzap().setResult((Object)new MetadataBuffer(zzfp.zzam()));
    }
}
