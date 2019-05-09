// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.TaskApiCall;

public abstract class zzah<TResult> extends TaskApiCall<zze, TResult>
{
    protected abstract void zza(final zze p0, final TaskCompletionSource<TResult> p1) throws RemoteException;
}
