package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "MostRecentGameInfoEntityCreator")
@Reserved({1000})
public final class zzb extends zzd implements zza {
    public static final Creator<zzb> CREATOR = new zzc();
    @Field(getter = "getGameId", id = 1)
    private final String zzlf;
    @Field(getter = "getGameName", id = 2)
    private final String zzlg;
    @Field(getter = "getActivityTimestampMillis", id = 3)
    private final long zzlh;
    @Field(getter = "getGameIconImageUri", id = 4)
    private final Uri zzli;
    @Field(getter = "getGameHiResImageUri", id = 5)
    private final Uri zzlj;
    @Field(getter = "getGameFeaturedImageUri", id = 6)
    private final Uri zzlk;

    public zzb(zza zza) {
        this.zzlf = zza.zzbt();
        this.zzlg = zza.zzbu();
        this.zzlh = zza.zzbv();
        this.zzli = zza.zzbw();
        this.zzlj = zza.zzbx();
        this.zzlk = zza.zzby();
    }

    @Constructor
    zzb(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) long j, @Param(id = 4) Uri uri, @Param(id = 5) Uri uri2, @Param(id = 6) Uri uri3) {
        this.zzlf = str;
        this.zzlg = str2;
        this.zzlh = j;
        this.zzli = uri;
        this.zzlj = uri2;
        this.zzlk = uri3;
    }

    static int zza(zza zza) {
        return Objects.hashCode(new Object[]{zza.zzbt(), zza.zzbu(), Long.valueOf(zza.zzbv()), zza.zzbw(), zza.zzbx(), zza.zzby()});
    }

    static boolean zza(zza zza, Object obj) {
        if (!(obj instanceof zza)) {
            return false;
        }
        if (zza == obj) {
            return true;
        }
        zza zza2 = (zza) obj;
        return Objects.equal(zza2.zzbt(), zza.zzbt()) && Objects.equal(zza2.zzbu(), zza.zzbu()) && Objects.equal(Long.valueOf(zza2.zzbv()), Long.valueOf(zza.zzbv())) && Objects.equal(zza2.zzbw(), zza.zzbw()) && Objects.equal(zza2.zzbx(), zza.zzbx()) && Objects.equal(zza2.zzby(), zza.zzby());
    }

    static String zzb(zza zza) {
        return Objects.toStringHelper(zza).add("GameId", zza.zzbt()).add("GameName", zza.zzbu()).add("ActivityTimestampMillis", Long.valueOf(zza.zzbv())).add("GameIconUri", zza.zzbw()).add("GameHiResUri", zza.zzbx()).add("GameFeaturedUri", zza.zzby()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        if (this != null) {
            return this;
        }
        throw null;
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

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzlf, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzlg, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzlh);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzli, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzlj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzlk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzbt() {
        return this.zzlf;
    }

    public final String zzbu() {
        return this.zzlg;
    }

    public final long zzbv() {
        return this.zzlh;
    }

    public final Uri zzbw() {
        return this.zzli;
    }

    public final Uri zzbx() {
        return this.zzlj;
    }

    public final Uri zzby() {
        return this.zzlk;
    }
}
