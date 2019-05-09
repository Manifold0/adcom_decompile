// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.OnCompleteListener;

final class zaad implements OnCompleteListener<Object>
{
    private final /* synthetic */ zaab zafn;
    private final /* synthetic */ TaskCompletionSource zafo;
    
    zaad(final zaab zafn, final TaskCompletionSource zafo) {
        this.zafn = zafn;
        this.zafo = zafo;
    }
    
    public final void onComplete(@NonNull final Task<Object> task) {
        this.zafn.zafl.remove(this.zafo);
    }
}
