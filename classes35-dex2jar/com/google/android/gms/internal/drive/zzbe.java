// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzbe extends TaskApiCall<zzaw, Void>
{
    private final /* synthetic */ TransferPreferences zzen;
    
    zzbe(final zzbb zzbb, final TransferPreferences zzen) {
        this.zzen = zzen;
    }
}
