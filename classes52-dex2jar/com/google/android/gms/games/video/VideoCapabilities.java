// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.video;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "VideoCapabilitiesCreator")
@SafeParcelable$Reserved({ 1000 })
public final class VideoCapabilities extends zzd
{
    public static final Parcelable$Creator<VideoCapabilities> CREATOR;
    @SafeParcelable$Field(getter = "isCameraSupported", id = 1)
    private final boolean zzru;
    @SafeParcelable$Field(getter = "isMicSupported", id = 2)
    private final boolean zzrv;
    @SafeParcelable$Field(getter = "isWriteStorageSupported", id = 3)
    private final boolean zzrw;
    @SafeParcelable$Field(getter = "getSupportedCaptureModes", id = 4)
    private final boolean[] zzrx;
    @SafeParcelable$Field(getter = "getSupportedQualityLevels", id = 5)
    private final boolean[] zzry;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    @SafeParcelable$Constructor
    public VideoCapabilities(@SafeParcelable$Param(id = 1) final boolean zzru, @SafeParcelable$Param(id = 2) final boolean zzrv, @SafeParcelable$Param(id = 3) final boolean zzrw, @SafeParcelable$Param(id = 4) final boolean[] zzrx, @SafeParcelable$Param(id = 5) final boolean[] zzry) {
        this.zzru = zzru;
        this.zzrv = zzrv;
        this.zzrw = zzrw;
        this.zzrx = zzrx;
        this.zzry = zzry;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof VideoCapabilities) {
            if (this == o) {
                return true;
            }
            final VideoCapabilities videoCapabilities = (VideoCapabilities)o;
            if (Objects.equal((Object)videoCapabilities.getSupportedCaptureModes(), (Object)this.getSupportedCaptureModes()) && Objects.equal((Object)videoCapabilities.getSupportedQualityLevels(), (Object)this.getSupportedQualityLevels()) && Objects.equal((Object)videoCapabilities.isCameraSupported(), (Object)this.isCameraSupported()) && Objects.equal((Object)videoCapabilities.isMicSupported(), (Object)this.isMicSupported()) && Objects.equal((Object)videoCapabilities.isWriteStorageSupported(), (Object)this.isWriteStorageSupported())) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean[] getSupportedCaptureModes() {
        return this.zzrx;
    }
    
    public final boolean[] getSupportedQualityLevels() {
        return this.zzry;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.getSupportedCaptureModes(), this.getSupportedQualityLevels(), this.isCameraSupported(), this.isMicSupported(), this.isWriteStorageSupported() });
    }
    
    public final boolean isCameraSupported() {
        return this.zzru;
    }
    
    public final boolean isFullySupported(final int n, final int n2) {
        return this.zzru && this.zzrv && this.zzrw && this.supportsCaptureMode(n) && this.supportsQualityLevel(n2);
    }
    
    public final boolean isMicSupported() {
        return this.zzrv;
    }
    
    public final boolean isWriteStorageSupported() {
        return this.zzrw;
    }
    
    public final boolean supportsCaptureMode(final int n) {
        Preconditions.checkState(VideoConfiguration.isValidCaptureMode(n, false));
        return this.zzrx[n];
    }
    
    public final boolean supportsQualityLevel(final int n) {
        Preconditions.checkState(VideoConfiguration.isValidQualityLevel(n, false));
        return this.zzry[n];
    }
    
    public final String toString() {
        return Objects.toStringHelper((Object)this).add("SupportedCaptureModes", (Object)this.getSupportedCaptureModes()).add("SupportedQualityLevels", (Object)this.getSupportedQualityLevels()).add("CameraSupported", (Object)this.isCameraSupported()).add("MicSupported", (Object)this.isMicSupported()).add("StorageWriteSupported", (Object)this.isWriteStorageSupported()).toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.isCameraSupported());
        SafeParcelWriter.writeBoolean(parcel, 2, this.isMicSupported());
        SafeParcelWriter.writeBoolean(parcel, 3, this.isWriteStorageSupported());
        SafeParcelWriter.writeBooleanArray(parcel, 4, this.getSupportedCaptureModes(), false);
        SafeParcelWriter.writeBooleanArray(parcel, 5, this.getSupportedQualityLevels(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
