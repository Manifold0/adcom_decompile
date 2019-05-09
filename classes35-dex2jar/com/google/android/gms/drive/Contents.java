// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@SafeParcelable$Class(creator = "ContentsCreator")
@SafeParcelable$Reserved({ 1 })
public class Contents extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<Contents> CREATOR;
    @SafeParcelable$Field(id = 4)
    private final int mode;
    @SafeParcelable$Field(id = 2)
    private final ParcelFileDescriptor zzi;
    @SafeParcelable$Field(id = 3)
    final int zzj;
    @SafeParcelable$Field(id = 5)
    private final DriveId zzk;
    @SafeParcelable$Field(id = 7)
    private final boolean zzl;
    @Nullable
    @SafeParcelable$Field(id = 8)
    private final String zzm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    public Contents(@SafeParcelable$Param(id = 2) final ParcelFileDescriptor zzi, @SafeParcelable$Param(id = 3) final int zzj, @SafeParcelable$Param(id = 4) final int mode, @SafeParcelable$Param(id = 5) final DriveId zzk, @SafeParcelable$Param(id = 7) final boolean zzl, @Nullable @SafeParcelable$Param(id = 8) final String zzm) {
        this.zzi = zzi;
        this.zzj = zzj;
        this.mode = mode;
        this.zzk = zzk;
        this.zzl = zzl;
        this.zzm = zzm;
    }
    
    public final DriveId getDriveId() {
        return this.zzk;
    }
    
    public final InputStream getInputStream() {
        return new FileInputStream(this.zzi.getFileDescriptor());
    }
    
    public final int getMode() {
        return this.mode;
    }
    
    public final OutputStream getOutputStream() {
        return new FileOutputStream(this.zzi.getFileDescriptor());
    }
    
    @KeepForSdk
    public ParcelFileDescriptor getParcelFileDescriptor() {
        return this.zzi;
    }
    
    public final int getRequestId() {
        return this.zzj;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzi, n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzj);
        SafeParcelWriter.writeInt(parcel, 4, this.mode);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzk, n, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzl);
        SafeParcelWriter.writeString(parcel, 8, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final boolean zza() {
        return this.zzl;
    }
}
