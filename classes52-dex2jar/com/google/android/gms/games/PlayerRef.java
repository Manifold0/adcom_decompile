// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.games.internal.player.zza;
import android.os.Parcel;
import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.player.zzd;
import com.google.android.gms.games.internal.player.zze;
import com.google.android.gms.common.data.DataBufferRef;

public final class PlayerRef extends DataBufferRef implements Player
{
    private final PlayerLevelInfo zzce;
    private final zze zzcw;
    private final zzd zzcx;
    
    public PlayerRef(final DataHolder dataHolder, final int n) {
        this(dataHolder, n, null);
    }
    
    public PlayerRef(final DataHolder dataHolder, int integer, final String s) {
        super(dataHolder, integer);
        this.zzcw = new zze(s);
        this.zzcx = new zzd(dataHolder, integer, this.zzcw);
        if (!this.hasNull(this.zzcw.zzlu) && this.getLong(this.zzcw.zzlu) != -1L) {
            integer = 1;
        }
        else {
            integer = 0;
        }
        if (integer != 0) {
            integer = this.getInteger(this.zzcw.zzlv);
            final int integer2 = this.getInteger(this.zzcw.zzly);
            final PlayerLevel playerLevel = new PlayerLevel(integer, this.getLong(this.zzcw.zzlw), this.getLong(this.zzcw.zzlx));
            PlayerLevel playerLevel2;
            if (integer != integer2) {
                playerLevel2 = new PlayerLevel(integer2, this.getLong(this.zzcw.zzlx), this.getLong(this.zzcw.zzlz));
            }
            else {
                playerLevel2 = playerLevel;
            }
            this.zzce = new PlayerLevelInfo(this.getLong(this.zzcw.zzlu), this.getLong(this.zzcw.zzma), playerLevel, playerLevel2);
            return;
        }
        this.zzce = null;
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return PlayerEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new PlayerEntity(this);
    }
    
    public final Uri getBannerImageLandscapeUri() {
        return this.parseUri(this.zzcw.zzmk);
    }
    
    public final String getBannerImageLandscapeUrl() {
        return this.getString(this.zzcw.zzml);
    }
    
    public final Uri getBannerImagePortraitUri() {
        return this.parseUri(this.zzcw.zzmm);
    }
    
    public final String getBannerImagePortraitUrl() {
        return this.getString(this.zzcw.zzmn);
    }
    
    public final String getDisplayName() {
        return this.getString(this.zzcw.zzlm);
    }
    
    public final void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer(this.zzcw.zzlm, charArrayBuffer);
    }
    
    public final Uri getHiResImageUri() {
        return this.parseUri(this.zzcw.zzlp);
    }
    
    public final String getHiResImageUrl() {
        return this.getString(this.zzcw.zzlq);
    }
    
    public final Uri getIconImageUri() {
        return this.parseUri(this.zzcw.zzln);
    }
    
    public final String getIconImageUrl() {
        return this.getString(this.zzcw.zzlo);
    }
    
    public final long getLastPlayedWithTimestamp() {
        if (!this.hasColumn(this.zzcw.zzlt) || this.hasNull(this.zzcw.zzlt)) {
            return -1L;
        }
        return this.getLong(this.zzcw.zzlt);
    }
    
    public final PlayerLevelInfo getLevelInfo() {
        return this.zzce;
    }
    
    public final String getName() {
        return this.getString(this.zzcw.name);
    }
    
    public final String getPlayerId() {
        return this.getString(this.zzcw.zzll);
    }
    
    public final long getRetrievedTimestamp() {
        return this.getLong(this.zzcw.zzlr);
    }
    
    public final String getTitle() {
        return this.getString(this.zzcw.zzcc);
    }
    
    public final void getTitle(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer(this.zzcw.zzcc, charArrayBuffer);
    }
    
    public final boolean hasHiResImage() {
        return this.getHiResImageUri() != null;
    }
    
    public final boolean hasIconImage() {
        return this.getIconImageUri() != null;
    }
    
    public final int hashCode() {
        return PlayerEntity.zza(this);
    }
    
    public final boolean isMuted() {
        return this.getBoolean(this.zzcw.zzmq);
    }
    
    public final String toString() {
        return PlayerEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((PlayerEntity)this.freeze()).writeToParcel(parcel, n);
    }
    
    public final String zzg() {
        return this.getString(this.zzcw.zzch);
    }
    
    public final boolean zzh() {
        return this.getBoolean(this.zzcw.zzmj);
    }
    
    public final int zzi() {
        return this.getInteger(this.zzcw.zzls);
    }
    
    public final boolean zzj() {
        return this.getBoolean(this.zzcw.zzmc);
    }
    
    public final zza zzk() {
        if (this.hasNull(this.zzcw.zzmd)) {
            return null;
        }
        return this.zzcx;
    }
    
    public final int zzl() {
        return this.getInteger(this.zzcw.zzmo);
    }
    
    public final long zzm() {
        return this.getLong(this.zzcw.zzmp);
    }
}
