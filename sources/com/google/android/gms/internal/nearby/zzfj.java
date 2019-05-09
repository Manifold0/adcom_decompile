package com.google.android.gms.internal.nearby;

import android.os.ParcelFileDescriptor;

public final class zzfj {
    private final zzfh zzdz = new zzfh();

    public final zzfj zzb(long j) {
        this.zzdz.id = j;
        return this;
    }

    public final zzfj zzb(byte[] bArr) {
        this.zzdz.zzy = bArr;
        return this;
    }

    public final zzfj zzc(long j) {
        this.zzdz.zzdx = j;
        return this;
    }

    public final zzfj zzc(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzdz.zzdv = parcelFileDescriptor;
        return this;
    }

    public final zzfj zzd(int i) {
        this.zzdz.type = i;
        return this;
    }

    public final zzfj zzd(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzdz.zzdy = parcelFileDescriptor;
        return this;
    }

    public final zzfj zze(String str) {
        this.zzdz.zzdw = str;
        return this;
    }

    public final zzfh zzr() {
        return this.zzdz;
    }
}
