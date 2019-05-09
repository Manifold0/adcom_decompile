// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.drive.MetadataBuffer;

public final class zzhh extends zzhb<MetadataBuffer>
{
    public zzhh(final TaskCompletionSource<MetadataBuffer> taskCompletionSource) {
        super(taskCompletionSource);
    }
    
    @Override
    public final void zza(final zzfn zzfn) throws RemoteException {
        this.zzap().setResult((Object)new MetadataBuffer(zzfn.zzal()));
    }
}
