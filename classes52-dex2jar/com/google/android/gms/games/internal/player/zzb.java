// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal.player;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "MostRecentGameInfoEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzb extends zzd implements zza
{
    public static final Parcelable$Creator<zzb> CREATOR;
    @SafeParcelable$Field(getter = "getGameId", id = 1)
    private final String zzlf;
    @SafeParcelable$Field(getter = "getGameName", id = 2)
    private final String zzlg;
    @SafeParcelable$Field(getter = "getActivityTimestampMillis", id = 3)
    private final long zzlh;
    @SafeParcelable$Field(getter = "getGameIconImageUri", id = 4)
    private final Uri zzli;
    @SafeParcelable$Field(getter = "getGameHiResImageUri", id = 5)
    private final Uri zzlj;
    @SafeParcelable$Field(getter = "getGameFeaturedImageUri", id = 6)
    private final Uri zzlk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    public zzb(final zza zza) {
        this.zzlf = zza.zzbt();
        this.zzlg = zza.zzbu();
        this.zzlh = zza.zzbv();
        this.zzli = zza.zzbw();
        this.zzlj = zza.zzbx();
        this.zzlk = zza.zzby();
    }
    
    @SafeParcelable$Constructor
    zzb(@SafeParcelable$Param(id = 1) final String zzlf, @SafeParcelable$Param(id = 2) final String zzlg, @SafeParcelable$Param(id = 3) final long zzlh, @SafeParcelable$Param(id = 4) final Uri zzli, @SafeParcelable$Param(id = 5) final Uri zzlj, @SafeParcelable$Param(id = 6) final Uri zzlk) {
        this.zzlf = zzlf;
        this.zzlg = zzlg;
        this.zzlh = zzlh;
        this.zzli = zzli;
        this.zzlj = zzlj;
        this.zzlk = zzlk;
    }
    
    static int zza(final zza zza) {
        return Objects.hashCode(new Object[] { zza.zzbt(), zza.zzbu(), zza.zzbv(), zza.zzbw(), zza.zzbx(), zza.zzby() });
    }
    
    static boolean zza(final zza zza, final Object o) {
        if (o instanceof zza) {
            if (zza == o) {
                return true;
            }
            final zza zza2 = (zza)o;
            if (Objects.equal((Object)zza2.zzbt(), (Object)zza.zzbt()) && Objects.equal((Object)zza2.zzbu(), (Object)zza.zzbu()) && Objects.equal((Object)zza2.zzbv(), (Object)zza.zzbv()) && Objects.equal((Object)zza2.zzbw(), (Object)zza.zzbw()) && Objects.equal((Object)zza2.zzbx(), (Object)zza.zzbx()) && Objects.equal((Object)zza2.zzby(), (Object)zza.zzby())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final zza zza) {
        return Objects.toStringHelper((Object)zza).add("GameId", (Object)zza.zzbt()).add("GameName", (Object)zza.zzbu()).add("ActivityTimestampMillis", (Object)zza.zzbv()).add("GameIconUri", (Object)zza.zzbw()).add("GameHiResUri", (Object)zza.zzbx()).add("GameFeaturedUri", (Object)zza.zzby()).toString();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzlf, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzlg, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzlh);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzli, n, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzlj, n, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzlk, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Override
    public final String zzbt() {
        return this.zzlf;
    }
    
    @Override
    public final String zzbu() {
        return this.zzlg;
    }
    
    @Override
    public final long zzbv() {
        return this.zzlh;
    }
    
    @Override
    public final Uri zzbw() {
        return this.zzli;
    }
    
    @Override
    public final Uri zzbx() {
        return this.zzlj;
    }
    
    @Override
    public final Uri zzby() {
        return this.zzlk;
    }
}
