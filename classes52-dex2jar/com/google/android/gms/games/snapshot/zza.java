// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.util.IOUtils;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import android.os.ParcelFileDescriptor;
import java.nio.channels.FileChannel;
import java.io.IOException;
import com.google.android.gms.games.internal.zzh;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "SnapshotContentsEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zza extends zzd implements SnapshotContents
{
    public static final Parcelable$Creator<zza> CREATOR;
    private static final Object zzql;
    @SafeParcelable$Field(getter = "getContents", id = 1)
    private Contents zzqm;
    
    static {
        zzql = new Object();
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @SafeParcelable$Constructor
    public zza(@SafeParcelable$Param(id = 1) final Contents zzqm) {
        this.zzqm = zzqm;
    }
    
    private final boolean zza(final int n, final byte[] array, final int n2, final int n3, final boolean b) {
        while (true) {
            Label_0123: {
                if (this.isClosed()) {
                    break Label_0123;
                }
                final boolean b2 = true;
                Preconditions.checkState(b2, (Object)"Must provide a previously opened SnapshotContents");
                synchronized (zza.zzql) {
                    final FileOutputStream fileOutputStream = new FileOutputStream(this.zzqm.getParcelFileDescriptor().getFileDescriptor());
                    final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    try {
                        final FileChannel channel = fileOutputStream.getChannel();
                        channel.position(n);
                        bufferedOutputStream.write(array, n2, n3);
                        if (b) {
                            channel.truncate(array.length);
                        }
                        bufferedOutputStream.flush();
                        return true;
                    }
                    catch (IOException ex) {
                        zzh.i("SnapshotContentsEntity", "Failed to write snapshot data", ex);
                        return false;
                    }
                }
            }
            final boolean b2 = false;
            continue;
        }
    }
    
    @Override
    public final void close() {
        this.zzqm = null;
    }
    
    @Override
    public final ParcelFileDescriptor getParcelFileDescriptor() {
        Preconditions.checkState(!this.isClosed(), (Object)"Cannot mutate closed contents!");
        return this.zzqm.getParcelFileDescriptor();
    }
    
    @Override
    public final boolean isClosed() {
        return this.zzqm == null;
    }
    
    @Override
    public final boolean modifyBytes(final int n, final byte[] array, final int n2, final int n3) {
        return this.zza(n, array, n2, array.length, false);
    }
    
    @Override
    public final byte[] readFully() throws IOException {
        boolean b = false;
        if (!this.isClosed()) {
            b = true;
        }
        Preconditions.checkState(b, (Object)"Must provide a previously opened Snapshot");
        synchronized (zza.zzql) {
            final FileInputStream fileInputStream = new FileInputStream(this.zzqm.getParcelFileDescriptor().getFileDescriptor());
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                fileInputStream.getChannel().position(0L);
                final byte[] inputStreamFully = IOUtils.readInputStreamFully((InputStream)bufferedInputStream, false);
                fileInputStream.getChannel().position(0L);
                return inputStreamFully;
            }
            catch (IOException ex) {
                zzh.w("SnapshotContentsEntity", "Failed to read snapshot data", ex);
                throw ex;
            }
        }
    }
    
    @Override
    public final boolean writeBytes(final byte[] array) {
        return this.zza(0, array, 0, array.length, true);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzqm, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Override
    public final Contents zzcl() {
        return this.zzqm;
    }
}
