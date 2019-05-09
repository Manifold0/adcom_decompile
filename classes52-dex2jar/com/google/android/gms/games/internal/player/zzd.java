// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBufferRef;

public final class zzd extends DataBufferRef implements zza
{
    private final zze zzcw;
    
    public zzd(final DataHolder dataHolder, final int n, final zze zzcw) {
        super(dataHolder, n);
        this.zzcw = zzcw;
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return zzb.zza(this, o);
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
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((zzb)this.freeze()).writeToParcel(parcel, n);
    }
    
    public final String zzbt() {
        return this.getString(this.zzcw.zzmd);
    }
    
    public final String zzbu() {
        return this.getString(this.zzcw.zzme);
    }
    
    public final long zzbv() {
        return this.getLong(this.zzcw.zzmf);
    }
    
    public final Uri zzbw() {
        return this.parseUri(this.zzcw.zzmg);
    }
    
    public final Uri zzbx() {
        return this.parseUri(this.zzcw.zzmh);
    }
    
    public final Uri zzby() {
        return this.parseUri(this.zzcw.zzmi);
    }
}
