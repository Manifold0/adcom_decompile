package com.google.android.gms.nearby.connection;

import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveFile;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class Payload {
    private final long id;
    @Type
    private final int type;
    @Nullable
    private final Stream zzaa;
    @Nullable
    private final byte[] zzy;
    @Nullable
    private final File zzz;

    public static class File {
        @Nullable
        private final java.io.File zzab;
        private final ParcelFileDescriptor zzac;
        private final long zzad;

        private File(@Nullable java.io.File file, ParcelFileDescriptor parcelFileDescriptor, long j) {
            this.zzab = file;
            this.zzac = parcelFileDescriptor;
            this.zzad = j;
        }

        public static File zza(ParcelFileDescriptor parcelFileDescriptor) {
            return new File(null, (ParcelFileDescriptor) Preconditions.checkNotNull(parcelFileDescriptor, "Cannot create Payload.File from null ParcelFileDescriptor."), parcelFileDescriptor.getStatSize());
        }

        public static File zza(java.io.File file, long j) throws FileNotFoundException {
            return new File((java.io.File) Preconditions.checkNotNull(file, "Cannot create Payload.File from null java.io.File."), ParcelFileDescriptor.open(file, DriveFile.MODE_READ_ONLY), j);
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

    public static class Stream {
        @Nullable
        private final ParcelFileDescriptor zzac;
        @Nullable
        private InputStream zzae;

        private Stream(@Nullable ParcelFileDescriptor parcelFileDescriptor, @Nullable InputStream inputStream) {
            this.zzac = parcelFileDescriptor;
            this.zzae = inputStream;
        }

        public static Stream zza(InputStream inputStream) {
            Preconditions.checkNotNull(inputStream, "Cannot create Payload.Stream from null InputStream.");
            return new Stream(null, inputStream);
        }

        public static Stream zzb(ParcelFileDescriptor parcelFileDescriptor) {
            Preconditions.checkNotNull(parcelFileDescriptor, "Cannot create Payload.Stream from null ParcelFileDescriptor.");
            return new Stream(parcelFileDescriptor, null);
        }

        @NonNull
        public InputStream asInputStream() {
            if (this.zzae == null) {
                this.zzae = new AutoCloseInputStream(this.zzac);
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

    private Payload(long j, int i, @Nullable byte[] bArr, @Nullable File file, @Nullable Stream stream) {
        this.id = j;
        this.type = i;
        this.zzy = bArr;
        this.zzz = file;
        this.zzaa = stream;
    }

    @NonNull
    public static Payload fromBytes(@NonNull byte[] bArr) {
        Preconditions.checkNotNull(bArr, "Cannot create a Payload from null bytes.");
        return zza(bArr, UUID.randomUUID().getLeastSignificantBits());
    }

    @NonNull
    public static Payload fromFile(@NonNull ParcelFileDescriptor parcelFileDescriptor) {
        return zza(File.zza(parcelFileDescriptor), UUID.randomUUID().getLeastSignificantBits());
    }

    @NonNull
    public static Payload fromFile(@NonNull java.io.File file) throws FileNotFoundException {
        return zza(File.zza(file, file.length()), UUID.randomUUID().getLeastSignificantBits());
    }

    @NonNull
    public static Payload fromStream(@NonNull ParcelFileDescriptor parcelFileDescriptor) {
        return zza(Stream.zzb(parcelFileDescriptor), UUID.randomUUID().getLeastSignificantBits());
    }

    @NonNull
    public static Payload fromStream(@NonNull InputStream inputStream) {
        return zza(Stream.zza(inputStream), UUID.randomUUID().getLeastSignificantBits());
    }

    public static Payload zza(File file, long j) {
        return new Payload(j, 2, null, file, null);
    }

    public static Payload zza(Stream stream, long j) {
        return new Payload(j, 3, null, null, stream);
    }

    public static Payload zza(byte[] bArr, long j) {
        return new Payload(j, 1, bArr, null, null);
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
}
