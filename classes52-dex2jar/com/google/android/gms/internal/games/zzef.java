// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.Videos;

final class zzef implements CaptureCapabilitiesResult
{
    private final /* synthetic */ Status zzbc;
    
    zzef(final zzee zzee, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final VideoCapabilities getCapabilities() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
