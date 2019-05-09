// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.games.Player;
import android.net.Uri;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

@SafeParcelable$Class(creator = "ParticipantEntityCreator")
@SafeParcelable$Reserved({ 1000 })
@RetainForClient
public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant
{
    public static final Parcelable$Creator<ParticipantEntity> CREATOR;
    @SafeParcelable$Field(getter = "getStatus", id = 5)
    private final int status;
    @SafeParcelable$Field(getter = "getIconImageUrl", id = 11)
    private final String zzac;
    @SafeParcelable$Field(getter = "getHiResImageUrl", id = 12)
    private final String zzad;
    @SafeParcelable$Field(getter = "getPlayer", id = 8)
    private final PlayerEntity zzfh;
    @SafeParcelable$Field(getter = "getParticipantId", id = 1)
    private final String zzhl;
    @SafeParcelable$Field(getter = "getDisplayName", id = 2)
    private final String zzn;
    @SafeParcelable$Field(getter = "getClientAddress", id = 6)
    private final String zzoh;
    @SafeParcelable$Field(getter = "isConnectedToRoom", id = 7)
    private final boolean zzoi;
    @SafeParcelable$Field(getter = "getCapabilities", id = 9)
    private final int zzoj;
    @SafeParcelable$Field(getter = "getResult", id = 10)
    private final ParticipantResult zzok;
    @SafeParcelable$Field(getter = "getIconImageUri", id = 3)
    private final Uri zzr;
    @SafeParcelable$Field(getter = "getHiResImageUri", id = 4)
    private final Uri zzs;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public ParticipantEntity(final Participant participant) {
        this.zzhl = participant.getParticipantId();
        this.zzn = participant.getDisplayName();
        this.zzr = participant.getIconImageUri();
        this.zzs = participant.getHiResImageUri();
        this.status = participant.getStatus();
        this.zzoh = participant.zzcg();
        this.zzoi = participant.isConnectedToRoom();
        final Player player = participant.getPlayer();
        PlayerEntity zzfh;
        if (player == null) {
            zzfh = null;
        }
        else {
            zzfh = new PlayerEntity(player);
        }
        this.zzfh = zzfh;
        this.zzoj = participant.getCapabilities();
        this.zzok = participant.getResult();
        this.zzac = participant.getIconImageUrl();
        this.zzad = participant.getHiResImageUrl();
    }
    
    @SafeParcelable$Constructor
    ParticipantEntity(@SafeParcelable$Param(id = 1) final String zzhl, @SafeParcelable$Param(id = 2) final String zzn, @SafeParcelable$Param(id = 3) final Uri zzr, @SafeParcelable$Param(id = 4) final Uri zzs, @SafeParcelable$Param(id = 5) final int status, @SafeParcelable$Param(id = 6) final String zzoh, @SafeParcelable$Param(id = 7) final boolean zzoi, @SafeParcelable$Param(id = 8) final PlayerEntity zzfh, @SafeParcelable$Param(id = 9) final int zzoj, @SafeParcelable$Param(id = 10) final ParticipantResult zzok, @SafeParcelable$Param(id = 11) final String zzac, @SafeParcelable$Param(id = 12) final String zzad) {
        this.zzhl = zzhl;
        this.zzn = zzn;
        this.zzr = zzr;
        this.zzs = zzs;
        this.status = status;
        this.zzoh = zzoh;
        this.zzoi = zzoi;
        this.zzfh = zzfh;
        this.zzoj = zzoj;
        this.zzok = zzok;
        this.zzac = zzac;
        this.zzad = zzad;
    }
    
    static int zza(final Participant participant) {
        return Objects.hashCode(new Object[] { participant.getPlayer(), participant.getStatus(), participant.zzcg(), participant.isConnectedToRoom(), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), participant.getCapabilities(), participant.getResult(), participant.getParticipantId() });
    }
    
    static boolean zza(final Participant participant, final Object o) {
        if (o instanceof Participant) {
            if (participant == o) {
                return true;
            }
            final Participant participant2 = (Participant)o;
            if (Objects.equal((Object)participant2.getPlayer(), (Object)participant.getPlayer()) && Objects.equal((Object)participant2.getStatus(), (Object)participant.getStatus()) && Objects.equal((Object)participant2.zzcg(), (Object)participant.zzcg()) && Objects.equal((Object)participant2.isConnectedToRoom(), (Object)participant.isConnectedToRoom()) && Objects.equal((Object)participant2.getDisplayName(), (Object)participant.getDisplayName()) && Objects.equal((Object)participant2.getIconImageUri(), (Object)participant.getIconImageUri()) && Objects.equal((Object)participant2.getHiResImageUri(), (Object)participant.getHiResImageUri()) && Objects.equal((Object)participant2.getCapabilities(), (Object)participant.getCapabilities()) && Objects.equal((Object)participant2.getResult(), (Object)participant.getResult()) && Objects.equal((Object)participant2.getParticipantId(), (Object)participant.getParticipantId())) {
                return true;
            }
        }
        return false;
    }
    
    static /* synthetic */ boolean zza(final String s) {
        return canUnparcelSafely(s);
    }
    
    static String zzb(final Participant participant) {
        return Objects.toStringHelper((Object)participant).add("ParticipantId", (Object)participant.getParticipantId()).add("Player", (Object)participant.getPlayer()).add("Status", (Object)participant.getStatus()).add("ClientAddress", (Object)participant.zzcg()).add("ConnectedToRoom", (Object)participant.isConnectedToRoom()).add("DisplayName", (Object)participant.getDisplayName()).add("IconImage", (Object)participant.getIconImageUri()).add("IconImageUrl", (Object)participant.getIconImageUrl()).add("HiResImage", (Object)participant.getHiResImageUri()).add("HiResImageUrl", (Object)participant.getHiResImageUrl()).add("Capabilities", (Object)participant.getCapabilities()).add("Result", (Object)participant.getResult()).toString();
    }
    
    static /* synthetic */ Integer zze() {
        return getUnparcelClientVersion();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final Participant freeze() {
        return this;
    }
    
    @Override
    public final int getCapabilities() {
        return this.zzoj;
    }
    
    @Override
    public final String getDisplayName() {
        if (this.zzfh == null) {
            return this.zzn;
        }
        return this.zzfh.getDisplayName();
    }
    
    @Override
    public final void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.zzfh == null) {
            DataUtils.copyStringToBuffer(this.zzn, charArrayBuffer);
            return;
        }
        this.zzfh.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public final Uri getHiResImageUri() {
        if (this.zzfh == null) {
            return this.zzs;
        }
        return this.zzfh.getHiResImageUri();
    }
    
    @Override
    public final String getHiResImageUrl() {
        if (this.zzfh == null) {
            return this.zzad;
        }
        return this.zzfh.getHiResImageUrl();
    }
    
    @Override
    public final Uri getIconImageUri() {
        if (this.zzfh == null) {
            return this.zzr;
        }
        return this.zzfh.getIconImageUri();
    }
    
    @Override
    public final String getIconImageUrl() {
        if (this.zzfh == null) {
            return this.zzac;
        }
        return this.zzfh.getIconImageUrl();
    }
    
    @Override
    public final String getParticipantId() {
        return this.zzhl;
    }
    
    @Override
    public final Player getPlayer() {
        return this.zzfh;
    }
    
    @Override
    public final ParticipantResult getResult() {
        return this.zzok;
    }
    
    @Override
    public final int getStatus() {
        return this.status;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    @Override
    public final boolean isConnectedToRoom() {
        return this.zzoi;
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final void setShouldDowngrade(final boolean b) {
        super.setShouldDowngrade(b);
        if (this.zzfh != null) {
            this.zzfh.setShouldDowngrade(b);
        }
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final String s = null;
        final int n2 = 0;
        if (!this.shouldDowngrade()) {
            final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, this.getParticipantId(), false);
            SafeParcelWriter.writeString(parcel, 2, this.getDisplayName(), false);
            SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.getIconImageUri(), n, false);
            SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.getHiResImageUri(), n, false);
            SafeParcelWriter.writeInt(parcel, 5, this.getStatus());
            SafeParcelWriter.writeString(parcel, 6, this.zzoh, false);
            SafeParcelWriter.writeBoolean(parcel, 7, this.isConnectedToRoom());
            SafeParcelWriter.writeParcelable(parcel, 8, (Parcelable)this.getPlayer(), n, false);
            SafeParcelWriter.writeInt(parcel, 9, this.zzoj);
            SafeParcelWriter.writeParcelable(parcel, 10, (Parcelable)this.getResult(), n, false);
            SafeParcelWriter.writeString(parcel, 11, this.getIconImageUrl(), false);
            SafeParcelWriter.writeString(parcel, 12, this.getHiResImageUrl(), false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
        else {
            parcel.writeString(this.zzhl);
            parcel.writeString(this.zzn);
            String string;
            if (this.zzr == null) {
                string = null;
            }
            else {
                string = this.zzr.toString();
            }
            parcel.writeString(string);
            String string2;
            if (this.zzs == null) {
                string2 = s;
            }
            else {
                string2 = this.zzs.toString();
            }
            parcel.writeString(string2);
            parcel.writeInt(this.status);
            parcel.writeString(this.zzoh);
            int n3;
            if (this.zzoi) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            parcel.writeInt(n3);
            int n4;
            if (this.zzfh == null) {
                n4 = n2;
            }
            else {
                n4 = 1;
            }
            parcel.writeInt(n4);
            if (this.zzfh != null) {
                this.zzfh.writeToParcel(parcel, n);
            }
        }
    }
    
    @Override
    public final String zzcg() {
        return this.zzoh;
    }
    
    static final class zza extends zzc
    {
        @Override
        public final ParticipantEntity zze(final Parcel parcel) {
            int n = 1;
            if (GamesDowngradeableSafeParcel.zzb(ParticipantEntity.zze()) || ParticipantEntity.zza(ParticipantEntity.class.getCanonicalName())) {
                return super.zze(parcel);
            }
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final String string3 = parcel.readString();
            Uri parse;
            if (string3 == null) {
                parse = null;
            }
            else {
                parse = Uri.parse(string3);
            }
            final String string4 = parcel.readString();
            Uri parse2;
            if (string4 == null) {
                parse2 = null;
            }
            else {
                parse2 = Uri.parse(string4);
            }
            final int int1 = parcel.readInt();
            final String string5 = parcel.readString();
            final boolean b = parcel.readInt() > 0;
            if (parcel.readInt() <= 0) {
                n = 0;
            }
            PlayerEntity playerEntity;
            if (n != 0) {
                playerEntity = (PlayerEntity)PlayerEntity.CREATOR.createFromParcel(parcel);
            }
            else {
                playerEntity = null;
            }
            return new ParticipantEntity(string, string2, parse, parse2, int1, string5, b, playerEntity, 7, null, null, null);
        }
    }
}
