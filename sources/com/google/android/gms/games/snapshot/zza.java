package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.internal.zzd;
import com.google.android.gms.games.internal.zzh;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

@Class(creator = "SnapshotContentsEntityCreator")
@Reserved({1000})
public final class zza extends zzd implements SnapshotContents {
    public static final Creator<zza> CREATOR = new zzb();
    private static final Object zzql = new Object();
    @Field(getter = "getContents", id = 1)
    private Contents zzqm;

    @Constructor
    public zza(@Param(id = 1) Contents contents) {
        this.zzqm = contents;
    }

    private final boolean zza(int i, byte[] bArr, int i2, int i3, boolean z) {
        Preconditions.checkState(!isClosed(), "Must provide a previously opened SnapshotContents");
        synchronized (zzql) {
            OutputStream fileOutputStream = new FileOutputStream(this.zzqm.getParcelFileDescriptor().getFileDescriptor());
            OutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            try {
                FileChannel channel = fileOutputStream.getChannel();
                channel.position((long) i);
                bufferedOutputStream.write(bArr, i2, i3);
                if (z) {
                    channel.truncate((long) bArr.length);
                }
                bufferedOutputStream.flush();
            } catch (Throwable e) {
                zzh.m1662i("SnapshotContentsEntity", "Failed to write snapshot data", e);
                return false;
            }
        }
        return true;
    }

    public final void close() {
        this.zzqm = null;
    }

    public final ParcelFileDescriptor getParcelFileDescriptor() {
        Preconditions.checkState(!isClosed(), "Cannot mutate closed contents!");
        return this.zzqm.getParcelFileDescriptor();
    }

    public final boolean isClosed() {
        return this.zzqm == null;
    }

    public final boolean modifyBytes(int i, byte[] bArr, int i2, int i3) {
        return zza(i, bArr, i2, bArr.length, false);
    }

    public final byte[] readFully() throws IOException {
        byte[] readInputStreamFully;
        boolean z = false;
        if (!isClosed()) {
            z = true;
        }
        Preconditions.checkState(z, "Must provide a previously opened Snapshot");
        synchronized (zzql) {
            InputStream fileInputStream = new FileInputStream(this.zzqm.getParcelFileDescriptor().getFileDescriptor());
            InputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                fileInputStream.getChannel().position(0);
                readInputStreamFully = IOUtils.readInputStreamFully(bufferedInputStream, false);
                fileInputStream.getChannel().position(0);
            } catch (Throwable e) {
                zzh.m1664w("SnapshotContentsEntity", "Failed to read snapshot data", e);
                throw e;
            }
        }
        return readInputStreamFully;
    }

    public final boolean writeBytes(byte[] bArr) {
        return zza(0, bArr, 0, bArr.length, true);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzqm, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final Contents zzcl() {
        return this.zzqm;
    }
}
