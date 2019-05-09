package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;

public final class zzd extends DataBufferRef implements zza {
    private final zze zzcw;

    public zzd(DataHolder dataHolder, int i, zze zze) {
        super(dataHolder, i);
        this.zzcw = zze;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return zzb.zza(this, obj);
    }

    public final /* synthetic */ Object freeze() {
        return new zzb(this);
    }

    public final int hashCode() {
        return zzb.zza(this);
    }

    public final String toString() {
        return zzb.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((zzb) ((zza) freeze())).writeToParcel(parcel, i);
    }

    public final String zzbt() {
        return getString(this.zzcw.zzmd);
    }

    public final String zzbu() {
        return getString(this.zzcw.zzme);
    }

    public final long zzbv() {
        return getLong(this.zzcw.zzmf);
    }

    public final Uri zzbw() {
        return parseUri(this.zzcw.zzmg);
    }

    public final Uri zzbx() {
        return parseUri(this.zzcw.zzmh);
    }

    public final Uri zzby() {
        return parseUri(this.zzcw.zzmi);
    }
}
