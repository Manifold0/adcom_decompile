package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.drive.TransferPreferencesBuilder;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhg extends zzhb<TransferPreferences> {
    public zzhg(TaskCompletionSource<TransferPreferences> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzfd zzfd) throws RemoteException {
        zzap().setResult(new TransferPreferencesBuilder(zzfd.zzaj()).build());
    }

    public final void zza(zzfu zzfu) throws RemoteException {
        zzap().setResult(zzfu.zzao());
    }
}
