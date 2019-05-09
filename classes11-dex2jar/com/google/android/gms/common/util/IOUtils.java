// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.Closeable;
import java.io.IOException;
import javax.annotation.Nullable;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;

@Deprecated
@KeepForSdk
@ShowFirstParty
public final class IOUtils
{
    private IOUtils() {
    }
    
    @KeepForSdk
    public static void closeQuietly(@Nullable final ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor == null) {
            return;
        }
        try {
            parcelFileDescriptor.close();
        }
        catch (IOException ex) {}
    }
    
    @KeepForSdk
    public static void closeQuietly(@Nullable final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    @Deprecated
    @KeepForSdk
    public static long copyStream(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        return zza(inputStream, outputStream, false);
    }
    
    @Deprecated
    @KeepForSdk
    public static long copyStream(final InputStream inputStream, final OutputStream outputStream, final boolean b, final int n) throws IOException {
        final byte[] array = new byte[n];
        long n2 = 0L;
        try {
            while (true) {
                final int read = inputStream.read(array, 0, n);
                if (read == -1) {
                    break;
                }
                n2 += read;
                outputStream.write(array, 0, read);
            }
        }
        finally {
            if (b) {
                closeQuietly(inputStream);
                closeQuietly(outputStream);
            }
        }
        if (b) {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }
        return n2;
    }
    
    @KeepForSdk
    public static boolean isGzipByteBuffer(final byte[] array) {
        return array.length > 1 && ((array[0] & 0xFF) | (array[1] & 0xFF) << 8) == 0x8B1F;
    }
    
    @Deprecated
    @KeepForSdk
    public static byte[] readInputStreamFully(final InputStream inputStream) throws IOException {
        return readInputStreamFully(inputStream, true);
    }
    
    @Deprecated
    @KeepForSdk
    public static byte[] readInputStreamFully(final InputStream inputStream, final boolean b) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zza(inputStream, byteArrayOutputStream, b);
        return byteArrayOutputStream.toByteArray();
    }
    
    @Deprecated
    @KeepForSdk
    public static byte[] toByteArray(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(byteArrayOutputStream);
        final byte[] array = new byte[4096];
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(array, 0, read);
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    @Deprecated
    private static long zza(final InputStream inputStream, final OutputStream outputStream, final boolean b) throws IOException {
        return copyStream(inputStream, outputStream, b, 1024);
    }
}
