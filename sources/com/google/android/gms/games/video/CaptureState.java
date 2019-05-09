package com.google.android.gms.games.video;

import android.os.Bundle;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

public final class CaptureState {
    private final boolean zzrp;
    private final int zzrq;
    private final int zzrr;
    private final boolean zzrs;
    private final boolean zzrt;

    private CaptureState(boolean z, int i, int i2, boolean z2, boolean z3) {
        Preconditions.checkArgument(VideoConfiguration.isValidCaptureMode(i, true));
        Preconditions.checkArgument(VideoConfiguration.isValidQualityLevel(i2, true));
        this.zzrp = z;
        this.zzrq = i;
        this.zzrr = i2;
        this.zzrs = z2;
        this.zzrt = z3;
    }

    public static CaptureState zzb(Bundle bundle) {
        return (bundle == null || bundle.get("IsCapturing") == null) ? null : new CaptureState(bundle.getBoolean("IsCapturing", false), bundle.getInt("CaptureMode", -1), bundle.getInt("CaptureQuality", -1), bundle.getBoolean("IsOverlayVisible", false), bundle.getBoolean("IsPaused", false));
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

    public final String toString() {
        return Objects.toStringHelper(this).add("IsCapturing", Boolean.valueOf(this.zzrp)).add("CaptureMode", Integer.valueOf(this.zzrq)).add("CaptureQuality", Integer.valueOf(this.zzrr)).add("IsOverlayVisible", Boolean.valueOf(this.zzrs)).add("IsPaused", Boolean.valueOf(this.zzrt)).toString();
    }
}
