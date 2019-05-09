package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Class(creator = "VideoConfigurationCreator")
@Reserved({1000})
public final class VideoConfiguration extends AbstractSafeParcelable {
    public static final int CAPTURE_MODE_FILE = 0;
    public static final int CAPTURE_MODE_STREAM = 1;
    public static final int CAPTURE_MODE_UNKNOWN = -1;
    public static final Creator<VideoConfiguration> CREATOR = new zzb();
    public static final int NUM_CAPTURE_MODE = 2;
    public static final int NUM_QUALITY_LEVEL = 4;
    public static final int QUALITY_LEVEL_FULLHD = 3;
    public static final int QUALITY_LEVEL_HD = 1;
    public static final int QUALITY_LEVEL_SD = 0;
    public static final int QUALITY_LEVEL_UNKNOWN = -1;
    public static final int QUALITY_LEVEL_XHD = 2;
    @Field(getter = "getCaptureMode", id = 2)
    private final int zzrq;
    @Field(getter = "getQualityLevel", id = 1)
    private final int zzrz;
    @Field(getter = "shouldShowToastAfterRecording", id = 7)
    private final boolean zzsa;

    public static final class Builder {
        private int zzrq;
        private int zzrz;
        private boolean zzsa = true;

        public Builder(int i, int i2) {
            this.zzrz = i;
            this.zzrq = i2;
        }

        public final VideoConfiguration build() {
            return new VideoConfiguration(this.zzrz, this.zzrq, this.zzsa);
        }

        public final Builder setCaptureMode(int i) {
            this.zzrq = i;
            return this;
        }

        public final Builder setQualityLevel(int i) {
            this.zzrz = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ValidCaptureModes {
    }

    @Constructor
    public VideoConfiguration(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 7) boolean z) {
        Preconditions.checkArgument(isValidQualityLevel(i, false));
        Preconditions.checkArgument(isValidCaptureMode(i2, false));
        this.zzrz = i;
        this.zzrq = i2;
        this.zzsa = z;
    }

    public static boolean isValidCaptureMode(int i, boolean z) {
        switch (i) {
            case -1:
            case 1:
                return z;
            case 0:
                return true;
            default:
                return false;
        }
    }

    public static boolean isValidQualityLevel(int i, boolean z) {
        switch (i) {
            case -1:
                return z;
            case 0:
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    public final int getCaptureMode() {
        return this.zzrq;
    }

    public final int getQualityLevel() {
        return this.zzrz;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getQualityLevel());
        SafeParcelWriter.writeInt(parcel, 2, getCaptureMode());
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzsa);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
