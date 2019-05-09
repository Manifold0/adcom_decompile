// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.OnCompleteListener;

final class zzay implements OnCompleteListener<Boolean>
{
    private final /* synthetic */ TaskCompletionSource zzic;
    
    zzay(final zzak zzak, final TaskCompletionSource zzic) {
        this.zzic = zzic;
    }
    
    public final void onComplete(@NonNull final Task<Boolean> task) {
        if (task.isSuccessful()) {
            this.zzic.setResult((Object)null);
            return;
        }
        this.zzic.setException(task.getException());
    }
}
