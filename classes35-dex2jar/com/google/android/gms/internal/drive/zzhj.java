// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.drive.Metadata;

public final class zzhj extends zzhb<Metadata>
{
    public zzhj(final TaskCompletionSource<Metadata> taskCompletionSource) {
        super(taskCompletionSource);
    }
    
    @Override
    public final void zza(final zzfs zzfs) throws RemoteException {
        this.zzap().setResult((Object)new zzaa(zzfs.zzan()));
    }
}
