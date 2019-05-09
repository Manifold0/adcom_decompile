package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@KeepForSdk
@Class(creator = "ContentsCreator")
@Reserved({1})
public class Contents extends AbstractSafeParcelable {
    public static final Creator<Contents> CREATOR = new zzc();
    @Field(id = 4)
    private final int mode;
    @Field(id = 2)
    private final ParcelFileDescriptor zzi;
    @Field(id = 3)
    final int zzj;
    @Field(id = 5)
    private final DriveId zzk;
    @Field(id = 7)
    private final boolean zzl;
    @Nullable
    @Field(id = 8)
    private final String zzm;

    @Constructor
    public Contents(@Param(id = 2) ParcelFileDescriptor parcelFileDescriptor, @Param(id = 3) int i, @Param(id = 4) int i2, @Param(id = 5) DriveId driveId, @Param(id = 7) boolean z, @Nullable @Param(id = 8) String str) {
        this.zzi = parcelFileDescriptor;
        this.zzj = i;
        this.mode = i2;
        this.zzk = driveId;
        this.zzl = z;
        this.zzm = str;
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

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzi, i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzj);
        SafeParcelWriter.writeInt(parcel, 4, this.mode);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzk, i, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzl);
        SafeParcelWriter.writeString(parcel, 8, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean zza() {
        return this.zzl;
    }
}
