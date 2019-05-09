// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.video;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "VideoConfigurationCreator")
@SafeParcelable$Reserved({ 1000 })
public final class VideoConfiguration extends AbstractSafeParcelable
{
    public static final int CAPTURE_MODE_FILE = 0;
    public static final int CAPTURE_MODE_STREAM = 1;
    public static final int CAPTURE_MODE_UNKNOWN = -1;
    public static final Parcelable$Creator<VideoConfiguration> CREATOR;
    public static final int NUM_CAPTURE_MODE = 2;
    public static final int NUM_QUALITY_LEVEL = 4;
    public static final int QUALITY_LEVEL_FULLHD = 3;
    public static final int QUALITY_LEVEL_HD = 1;
    public static final int QUALITY_LEVEL_SD = 0;
    public static final int QUALITY_LEVEL_UNKNOWN = -1;
    public static final int QUALITY_LEVEL_XHD = 2;
    @SafeParcelable$Field(getter = "getCaptureMode", id = 2)
    private final int zzrq;
    @SafeParcelable$Field(getter = "getQualityLevel", id = 1)
    private final int zzrz;
    @SafeParcelable$Field(getter = "shouldShowToastAfterRecording", id = 7)
    private final boolean zzsa;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @SafeParcelable$Constructor
    public VideoConfiguration(@SafeParcelable$Param(id = 1) final int zzrz, @SafeParcelable$Param(id = 2) final int zzrq, @SafeParcelable$Param(id = 7) final boolean zzsa) {
        Preconditions.checkArgument(isValidQualityLevel(zzrz, false));
        Preconditions.checkArgument(isValidCaptureMode(zzrq, false));
        this.zzrz = zzrz;
        this.zzrq = zzrq;
        this.zzsa = zzsa;
    }
    
    public static boolean isValidCaptureMode(final int n, boolean b) {
        switch (n) {
            default: {
                b = false;
                return b;
            }
            case -1:
            case 1: {
                return b;
            }
            case 0: {
                return true;
            }
        }
    }
    
    public static boolean isValidQualityLevel(final int n, boolean b) {
        switch (n) {
            default: {
                b = false;
                return b;
            }
            case -1: {
                return b;
            }
            case 0:
            case 1:
            case 2:
            case 3: {
                return true;
            }
        }
    }
    
    public final int getCaptureMode() {
        return this.zzrq;
    }
    
    public final int getQualityLevel() {
        return this.zzrz;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.getQualityLevel());
        SafeParcelWriter.writeInt(parcel, 2, this.getCaptureMode());
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzsa);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static final class Builder
    {
        private int zzrq;
        private int zzrz;
        private boolean zzsa;
        
        public Builder(final int zzrz, final int zzrq) {
            this.zzrz = zzrz;
            this.zzrq = zzrq;
            this.zzsa = true;
        }
        
        public final VideoConfiguration build() {
            return new VideoConfiguration(this.zzrz, this.zzrq, this.zzsa);
        }
        
        public final Builder setCaptureMode(final int zzrq) {
            this.zzrq = zzrq;
            return this;
        }
        
        public final Builder setQualityLevel(final int zzrz) {
            this.zzrz = zzrz;
            return this;
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface ValidCaptureModes {
    }
}
