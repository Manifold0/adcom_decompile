// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.Videos;

final class zzeh implements CaptureStateResult
{
    private final /* synthetic */ Status zzbc;
    
    zzeh(final zzeg zzeg, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final CaptureState getCaptureState() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
