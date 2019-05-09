// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

final class zaaf
{
    private final zai<?> zafq;
    private final TaskCompletionSource<Boolean> zafr;
    
    public zaaf(final zai<?> zafq) {
        this.zafr = (TaskCompletionSource<Boolean>)new TaskCompletionSource();
        this.zafq = zafq;
    }
    
    public final TaskCompletionSource<Boolean> zaal() {
        return this.zafr;
    }
    
    public final zai<?> zak() {
        return this.zafq;
    }
}
