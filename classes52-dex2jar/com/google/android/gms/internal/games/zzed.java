// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.Videos;

final class zzed implements CaptureAvailableResult
{
    private final /* synthetic */ Status zzbc;
    
    zzed(final zzec zzec, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    @Override
    public final boolean isAvailable() {
        return false;
    }
}
