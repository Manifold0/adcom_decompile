// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.drive.TransferPreferencesBuilder;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.drive.TransferPreferences;

public final class zzhg extends zzhb<TransferPreferences>
{
    public zzhg(final TaskCompletionSource<TransferPreferences> taskCompletionSource) {
        super(taskCompletionSource);
    }
    
    @Override
    public final void zza(final zzfd zzfd) throws RemoteException {
        this.zzap().setResult((Object)new TransferPreferencesBuilder(zzfd.zzaj()).build());
    }
    
    @Override
    public final void zza(final zzfu zzfu) throws RemoteException {
        this.zzap().setResult((Object)zzfu.zzao());
    }
}
