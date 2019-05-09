package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.player.zza;
import com.google.android.gms.games.internal.player.zzd;
import com.google.android.gms.games.internal.player.zze;

public final class PlayerRef extends DataBufferRef implements Player {
    private final PlayerLevelInfo zzce;
    private final zze zzcw;
    private final zzd zzcx;

    public PlayerRef(DataHolder dataHolder, int i) {
        this(dataHolder, i, null);
    }

    public PlayerRef(DataHolder dataHolder, int i, String str) {
        super(dataHolder, i);
        this.zzcw = new zze(str);
        this.zzcx = new zzd(dataHolder, i, this.zzcw);
        Object obj = (hasNull(this.zzcw.zzlu) || getLong(this.zzcw.zzlu) == -1) ? null : 1;
        if (obj != null) {
            int integer = getInteger(this.zzcw.zzlv);
            int integer2 = getInteger(this.zzcw.zzly);
            PlayerLevel playerLevel = new PlayerLevel(integer, getLong(this.zzcw.zzlw), getLong(this.zzcw.zzlx));
            this.zzce = new PlayerLevelInfo(getLong(this.zzcw.zzlu), getLong(this.zzcw.zzma), playerLevel, integer != integer2 ? new PlayerLevel(integer2, getLong(this.zzcw.zzlx), getLong(this.zzcw.zzlz)) : playerLevel);
            return;
        }
        this.zzce = null;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return PlayerEntity.zza(this, obj);
    }

    public final /* synthetic */ Object freeze() {
        return new PlayerEntity(this);
    }

    public final Uri getBannerImageLandscapeUri() {
        return parseUri(this.zzcw.zzmk);
    }

    public final String getBannerImageLandscapeUrl() {
        return getString(this.zzcw.zzml);
    }

    public final Uri getBannerImagePortraitUri() {
        return parseUri(this.zzcw.zzmm);
    }

    public final String getBannerImagePortraitUrl() {
        return getString(this.zzcw.zzmn);
    }

    public final String getDisplayName() {
        return getString(this.zzcw.zzlm);
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        copyToBuffer(this.zzcw.zzlm, charArrayBuffer);
    }

    public final Uri getHiResImageUri() {
        return parseUri(this.zzcw.zzlp);
    }

    public final String getHiResImageUrl() {
        return getString(this.zzcw.zzlq);
    }

    public final Uri getIconImageUri() {
        return parseUri(this.zzcw.zzln);
    }

    public final String getIconImageUrl() {
        return getString(this.zzcw.zzlo);
    }

    public final long getLastPlayedWithTimestamp() {
        return (!hasColumn(this.zzcw.zzlt) || hasNull(this.zzcw.zzlt)) ? -1 : getLong(this.zzcw.zzlt);
    }

    public final PlayerLevelInfo getLevelInfo() {
        return this.zzce;
    }

    public final String getName() {
        return getString(this.zzcw.name);
    }

    public final String getPlayerId() {
        return getString(this.zzcw.zzll);
    }

    public final long getRetrievedTimestamp() {
        return getLong(this.zzcw.zzlr);
    }

    public final String getTitle() {
        return getString(this.zzcw.zzcc);
    }

    public final void getTitle(CharArrayBuffer charArrayBuffer) {
        copyToBuffer(this.zzcw.zzcc, charArrayBuffer);
    }

    public final boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    public final boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    public final int hashCode() {
        return PlayerEntity.zza((Player) this);
    }

    public final boolean isMuted() {
        return getBoolean(this.zzcw.zzmq);
    }

    public final String toString() {
        return PlayerEntity.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((PlayerEntity) ((Player) freeze())).writeToParcel(parcel, i);
    }

    public final String zzg() {
        return getString(this.zzcw.zzch);
    }

    public final boolean zzh() {
        return getBoolean(this.zzcw.zzmj);
    }

    public final int zzi() {
        return getInteger(this.zzcw.zzls);
    }

    public final boolean zzj() {
        return getBoolean(this.zzcw.zzmc);
    }

    public final zza zzk() {
        return hasNull(this.zzcw.zzmd) ? null : this.zzcx;
    }

    public final int zzl() {
        return getInteger(this.zzcw.zzmo);
    }

    public final long zzm() {
        return getLong(this.zzcw.zzmp);
    }
}
