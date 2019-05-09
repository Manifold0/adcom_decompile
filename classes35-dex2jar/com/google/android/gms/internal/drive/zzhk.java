// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhk extends zzhb<Boolean>
{
    public zzhk(final TaskCompletionSource<Boolean> taskCompletionSource) {
        super(taskCompletionSource);
    }
    
    @Override
    public final void onSuccess() throws RemoteException {
        this.zzap().setResult((Object)true);
    }
}
