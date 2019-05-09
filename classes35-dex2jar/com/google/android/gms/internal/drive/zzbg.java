// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.drive.CreateFileActivityOptions;
import android.content.IntentSender;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzbg extends TaskApiCall<zzaw, IntentSender>
{
    private final /* synthetic */ CreateFileActivityOptions zzep;
    
    zzbg(final zzbb zzbb, final CreateFileActivityOptions zzep) {
        this.zzep = zzep;
    }
}
