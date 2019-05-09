// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import android.os.Parcel;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import android.graphics.Bitmap$Config;
import java.io.InputStream;
import java.io.DataInputStream;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.util.Log;
import java.io.Closeable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.io.File;
import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@ShowFirstParty
@SafeParcelable$Class(creator = "BitmapTeleporterCreator")
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable
{
    @KeepForSdk
    public static final Parcelable$Creator<BitmapTeleporter> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final int mType;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @SafeParcelable$Field(id = 2)
    private ParcelFileDescriptor zalg;
    private Bitmap zalh;
    private boolean zali;
    private File zalj;
    
    static {
        CREATOR = (Parcelable$Creator)new zaa();
    }
    
    @SafeParcelable$Constructor
    BitmapTeleporter(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final ParcelFileDescriptor zalg, @SafeParcelable$Param(id = 3) final int mType) {
        this.zalf = zalf;
        this.zalg = zalg;
        this.mType = mType;
        this.zalh = null;
        this.zali = false;
    }
    
    @KeepForSdk
    public BitmapTeleporter(final Bitmap zalh) {
        this.zalf = 1;
        this.zalg = null;
        this.mType = 0;
        this.zalh = zalh;
        this.zali = true;
    }
    
    private static void zaa(final Closeable closeable) {
        try {
            closeable.close();
        }
        catch (IOException ex) {
            Log.w("BitmapTeleporter", "Could not close stream", (Throwable)ex);
        }
    }
    
    private final FileOutputStream zabz() {
        if (this.zalj == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        File file;
        try {
            final File tempFile;
            file = (tempFile = File.createTempFile("teleporter", ".tmp", this.zalj));
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            final BitmapTeleporter bitmapTeleporter = this;
            final File file2 = file;
            final int n = 268435456;
            final ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file2, n);
            bitmapTeleporter.zalg = parcelFileDescriptor;
            final File file3 = file;
            file3.delete();
            return fileOutputStream;
        }
        catch (IOException ex) {
            throw new IllegalStateException("Could not create temporary file", ex);
        }
        try {
            final File tempFile = file;
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            final BitmapTeleporter bitmapTeleporter = this;
            final File file2 = file;
            final int n = 268435456;
            final ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file2, n);
            bitmapTeleporter.zalg = parcelFileDescriptor;
            final File file3 = file;
            file3.delete();
            return fileOutputStream;
        }
        catch (FileNotFoundException ex2) {
            throw new IllegalStateException("Temporary file is somehow already deleted");
        }
    }
    
    @KeepForSdk
    public Bitmap get() {
        Label_0096: {
            if (this.zali) {
                break Label_0096;
            }
            Object wrap = new DataInputStream((InputStream)new ParcelFileDescriptor$AutoCloseInputStream(this.zalg));
            try {
                final byte[] array = new byte[((DataInputStream)wrap).readInt()];
                final int int1 = ((DataInputStream)wrap).readInt();
                final int int2 = ((DataInputStream)wrap).readInt();
                final Bitmap$Config value = Bitmap$Config.valueOf(((DataInputStream)wrap).readUTF());
                ((DataInputStream)wrap).read(array);
                zaa((Closeable)wrap);
                wrap = ByteBuffer.wrap(array);
                final Bitmap bitmap = Bitmap.createBitmap(int1, int2, value);
                bitmap.copyPixelsFromBuffer((Buffer)wrap);
                this.zalh = bitmap;
                this.zali = true;
                return this.zalh;
            }
            catch (IOException ex) {
                throw new IllegalStateException("Could not read from parcel file descriptor", ex);
            }
            finally {
                zaa((Closeable)wrap);
            }
        }
    }
    
    @KeepForSdk
    public void release() {
        if (this.zali) {
            return;
        }
        try {
            this.zalg.close();
        }
        catch (IOException ex) {
            Log.w("BitmapTeleporter", "Could not close PFD", (Throwable)ex);
        }
    }
    
    @KeepForSdk
    public void setTempDir(final File zalj) {
        if (zalj == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zalj = zalj;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        Label_0116: {
            if (this.zalg != null) {
                break Label_0116;
            }
            final Bitmap zalh = this.zalh;
            final ByteBuffer allocate = ByteBuffer.allocate(zalh.getRowBytes() * zalh.getHeight());
            zalh.copyPixelsToBuffer((Buffer)allocate);
            final byte[] array = allocate.array();
            final DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this.zabz()));
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(zalh.getWidth());
                dataOutputStream.writeInt(zalh.getHeight());
                dataOutputStream.writeUTF(zalh.getConfig().toString());
                dataOutputStream.write(array);
                zaa(dataOutputStream);
                final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
                SafeParcelWriter.writeInt(parcel, 1, this.zalf);
                SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zalg, n | 0x1, false);
                SafeParcelWriter.writeInt(parcel, 3, this.mType);
                SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
                this.zalg = null;
            }
            catch (IOException ex) {
                throw new IllegalStateException("Could not write into unlinked file", ex);
            }
            finally {
                zaa(dataOutputStream);
            }
        }
    }
}
