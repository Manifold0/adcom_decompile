// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;

final class zaac implements StatusListener
{
    private final /* synthetic */ BasePendingResult zafm;
    private final /* synthetic */ zaab zafn;
    
    zaac(final zaab zafn, final BasePendingResult zafm) {
        this.zafn = zafn;
        this.zafm = zafm;
    }
    
    @Override
    public final void onComplete(final Status status) {
        this.zafn.zafk.remove(this.zafm);
    }
}
