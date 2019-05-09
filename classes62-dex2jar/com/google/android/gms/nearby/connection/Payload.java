// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import java.lang.annotation.Annotation;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.File;
import android.os.ParcelFileDescriptor;
import java.util.UUID;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Payload
{
    private final long id;
    @Type
    private final int type;
    @Nullable
    private final Stream zzaa;
    @Nullable
    private final byte[] zzy;
    @Nullable
    private final File zzz;
    
    private Payload(final long id, final int type, @Nullable final byte[] zzy, @Nullable final File zzz, @Nullable final Stream zzaa) {
        this.id = id;
        this.type = type;
        this.zzy = zzy;
        this.zzz = zzz;
        this.zzaa = zzaa;
    }
    
    @NonNull
    public static Payload fromBytes(@NonNull final byte[] array) {
        Preconditions.checkNotNull((Object)array, (Object)"Cannot create a Payload from null bytes.");
        return zza(array, UUID.randomUUID().getLeastSignificantBits());
    }
    
    @NonNull
    public static Payload fromFile(@NonNull final ParcelFileDescriptor parcelFileDescriptor) {
        return zza(File.zza(parcelFileDescriptor), UUID.randomUUID().getLeastSignificantBits());
    }
    
    @NonNull
    public static Payload fromFile(@NonNull final java.io.File file) throws FileNotFoundException {
        return zza(File.zza(file, file.length()), UUID.randomUUID().getLeastSignificantBits());
    }
    
    @NonNull
    public static Payload fromStream(@NonNull final ParcelFileDescriptor parcelFileDescriptor) {
        return zza(Stream.zzb(parcelFileDescriptor), UUID.randomUUID().getLeastSignificantBits());
    }
    
    @NonNull
    public static Payload fromStream(@NonNull final InputStream inputStream) {
        return zza(Stream.zza(inputStream), UUID.randomUUID().getLeastSignificantBits());
    }
    
    public static Payload zza(final File file, final long n) {
        return new Payload(n, 2, null, file, null);
    }
    
    public static Payload zza(final Stream stream, final long n) {
        return new Payload(n, 3, null, null, stream);
    }
    
    public static Payload zza(final byte[] array, final long n) {
        return new Payload(n, 1, array, null, null);
    }
    
    @Nullable
    public byte[] asBytes() {
        return this.zzy;
    }
    
    @Nullable
    public File asFile() {
        return this.zzz;
    }
    
    @Nullable
    public Stream asStream() {
        return this.zzaa;
    }
    
    public long getId() {
        return this.id;
    }
    
    @Type
    public int getType() {
        return this.type;
    }
    
    public static class File
    {
        @Nullable
        private final java.io.File zzab;
        private final ParcelFileDescriptor zzac;
        private final long zzad;
        
        private File(@Nullable final java.io.File zzab, final ParcelFileDescriptor zzac, final long zzad) {
            this.zzab = zzab;
            this.zzac = zzac;
            this.zzad = zzad;
        }
        
        public static File zza(final ParcelFileDescriptor parcelFileDescriptor) {
            return new File(null, (ParcelFileDescriptor)Preconditions.checkNotNull((Object)parcelFileDescriptor, (Object)"Cannot create Payload.File from null ParcelFileDescriptor."), parcelFileDescriptor.getStatSize());
        }
        
        public static File zza(final java.io.File file, final long n) throws FileNotFoundException {
            return new File((java.io.File)Preconditions.checkNotNull((Object)file, (Object)"Cannot create Payload.File from null java.io.File."), ParcelFileDescriptor.open(file, 268435456), n);
        }
        
        @Nullable
        public java.io.File asJavaFile() {
            return this.zzab;
        }
        
        @NonNull
        public ParcelFileDescriptor asParcelFileDescriptor() {
            return this.zzac;
        }
        
        public long getSize() {
            return this.zzad;
        }
    }
    
    public static class Stream
    {
        @Nullable
        private final ParcelFileDescriptor zzac;
        @Nullable
        private InputStream zzae;
        
        private Stream(@Nullable final ParcelFileDescriptor zzac, @Nullable final InputStream zzae) {
            this.zzac = zzac;
            this.zzae = zzae;
        }
        
        public static Stream zza(final InputStream inputStream) {
            Preconditions.checkNotNull((Object)inputStream, (Object)"Cannot create Payload.Stream from null InputStream.");
            return new Stream(null, inputStream);
        }
        
        public static Stream zzb(final ParcelFileDescriptor parcelFileDescriptor) {
            Preconditions.checkNotNull((Object)parcelFileDescriptor, (Object)"Cannot create Payload.Stream from null ParcelFileDescriptor.");
            return new Stream(parcelFileDescriptor, null);
        }
        
        @NonNull
        public InputStream asInputStream() {
            if (this.zzae == null) {
                this.zzae = (InputStream)new ParcelFileDescriptor$AutoCloseInputStream(this.zzac);
            }
            return this.zzae;
        }
        
        @Nullable
        public ParcelFileDescriptor asParcelFileDescriptor() {
            return this.zzac;
        }
    }
    
    public @interface Type {
        public static final int BYTES = 1;
        public static final int FILE = 2;
        public static final int STREAM = 3;
    }
}
