// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.video;

import com.google.android.gms.common.internal.Objects;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

public final class CaptureState
{
    private final boolean zzrp;
    private final int zzrq;
    private final int zzrr;
    private final boolean zzrs;
    private final boolean zzrt;
    
    private CaptureState(final boolean zzrp, final int zzrq, final int zzrr, final boolean zzrs, final boolean zzrt) {
        Preconditions.checkArgument(VideoConfiguration.isValidCaptureMode(zzrq, true));
        Preconditions.checkArgument(VideoConfiguration.isValidQualityLevel(zzrr, true));
        this.zzrp = zzrp;
        this.zzrq = zzrq;
        this.zzrr = zzrr;
        this.zzrs = zzrs;
        this.zzrt = zzrt;
    }
    
    public static CaptureState zzb(final Bundle bundle) {
        if (bundle == null || bundle.get("IsCapturing") == null) {
            return null;
        }
        return new CaptureState(bundle.getBoolean("IsCapturing", false), bundle.getInt("CaptureMode", -1), bundle.getInt("CaptureQuality", -1), bundle.getBoolean("IsOverlayVisible", false), bundle.getBoolean("IsPaused", false));
    }
    
    public final int getCaptureMode() {
        return this.zzrq;
    }
    
    public final int getCaptureQuality() {
        return this.zzrr;
    }
    
    public final boolean isCapturing() {
        return this.zzrp;
    }
    
    public final boolean isOverlayVisible() {
        return this.zzrs;
    }
    
    public final boolean isPaused() {
        return this.zzrt;
    }
    
    @Override
    public final String toString() {
        return Objects.toStringHelper((Object)this).add("IsCapturing", (Object)this.zzrp).add("CaptureMode", (Object)this.zzrq).add("CaptureQuality", (Object)this.zzrr).add("IsOverlayVisible", (Object)this.zzrs).add("IsPaused", (Object)this.zzrt).toString();
    }
}
