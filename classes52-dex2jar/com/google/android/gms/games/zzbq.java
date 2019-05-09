// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Continuation;

final class zzbq implements Continuation<String, Task<Boolean>>
{
    private final /* synthetic */ ListenerHolder zzdp;
    private final /* synthetic */ RealTimeMultiplayerClient zzdq;
    
    zzbq(final RealTimeMultiplayerClient zzdq, final ListenerHolder zzdp) {
        this.zzdq = zzdq;
        this.zzdp = zzdp;
    }
}
