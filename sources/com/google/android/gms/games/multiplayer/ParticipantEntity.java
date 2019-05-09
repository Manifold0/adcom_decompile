package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
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
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

@RetainForClient
@Class(creator = "ParticipantEntityCreator")
@Reserved({1000})
public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant {
    public static final Creator<ParticipantEntity> CREATOR = new zza();
    @Field(getter = "getStatus", id = 5)
    private final int status;
    @Field(getter = "getIconImageUrl", id = 11)
    private final String zzac;
    @Field(getter = "getHiResImageUrl", id = 12)
    private final String zzad;
    @Field(getter = "getPlayer", id = 8)
    private final PlayerEntity zzfh;
    @Field(getter = "getParticipantId", id = 1)
    private final String zzhl;
    @Field(getter = "getDisplayName", id = 2)
    private final String zzn;
    @Field(getter = "getClientAddress", id = 6)
    private final String zzoh;
    @Field(getter = "isConnectedToRoom", id = 7)
    private final boolean zzoi;
    @Field(getter = "getCapabilities", id = 9)
    private final int zzoj;
    @Field(getter = "getResult", id = 10)
    private final ParticipantResult zzok;
    @Field(getter = "getIconImageUri", id = 3)
    private final Uri zzr;
    @Field(getter = "getHiResImageUri", id = 4)
    private final Uri zzs;

    static final class zza extends zzc {
        zza() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return zze(parcel);
        }

        public final ParticipantEntity zze(Parcel parcel) {
            Object obj = 1;
            if (GamesDowngradeableSafeParcel.zzb(ParticipantEntity.getUnparcelClientVersion()) || ParticipantEntity.canUnparcelSafely(ParticipantEntity.class.getCanonicalName())) {
                return super.zze(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            Uri parse = readString3 == null ? null : Uri.parse(readString3);
            String readString4 = parcel.readString();
            Uri parse2 = readString4 == null ? null : Uri.parse(readString4);
            int readInt = parcel.readInt();
            String readString5 = parcel.readString();
            boolean z = parcel.readInt() > 0;
            if (parcel.readInt() <= 0) {
                obj = null;
            }
            return new ParticipantEntity(readString, readString2, parse, parse2, readInt, readString5, z, obj != null ? (PlayerEntity) PlayerEntity.CREATOR.createFromParcel(parcel) : null, 7, null, null, null);
        }
    }

    public ParticipantEntity(Participant participant) {
        this.zzhl = participant.getParticipantId();
        this.zzn = participant.getDisplayName();
        this.zzr = participant.getIconImageUri();
        this.zzs = participant.getHiResImageUri();
        this.status = participant.getStatus();
        this.zzoh = participant.zzcg();
        this.zzoi = participant.isConnectedToRoom();
        Player player = participant.getPlayer();
        this.zzfh = player == null ? null : new PlayerEntity(player);
        this.zzoj = participant.getCapabilities();
        this.zzok = participant.getResult();
        this.zzac = participant.getIconImageUrl();
        this.zzad = participant.getHiResImageUrl();
    }

    @Constructor
    ParticipantEntity(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) Uri uri, @Param(id = 4) Uri uri2, @Param(id = 5) int i, @Param(id = 6) String str3, @Param(id = 7) boolean z, @Param(id = 8) PlayerEntity playerEntity, @Param(id = 9) int i2, @Param(id = 10) ParticipantResult participantResult, @Param(id = 11) String str4, @Param(id = 12) String str5) {
        this.zzhl = str;
        this.zzn = str2;
        this.zzr = uri;
        this.zzs = uri2;
        this.status = i;
        this.zzoh = str3;
        this.zzoi = z;
        this.zzfh = playerEntity;
        this.zzoj = i2;
        this.zzok = participantResult;
        this.zzac = str4;
        this.zzad = str5;
    }

    static int zza(Participant participant) {
        return Objects.hashCode(new Object[]{participant.getPlayer(), Integer.valueOf(participant.getStatus()), participant.zzcg(), Boolean.valueOf(participant.isConnectedToRoom()), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), Integer.valueOf(participant.getCapabilities()), participant.getResult(), participant.getParticipantId()});
    }

    static boolean zza(Participant participant, Object obj) {
        if (!(obj instanceof Participant)) {
            return false;
        }
        if (participant == obj) {
            return true;
        }
        Participant participant2 = (Participant) obj;
        return Objects.equal(participant2.getPlayer(), participant.getPlayer()) && Objects.equal(Integer.valueOf(participant2.getStatus()), Integer.valueOf(participant.getStatus())) && Objects.equal(participant2.zzcg(), participant.zzcg()) && Objects.equal(Boolean.valueOf(participant2.isConnectedToRoom()), Boolean.valueOf(participant.isConnectedToRoom())) && Objects.equal(participant2.getDisplayName(), participant.getDisplayName()) && Objects.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && Objects.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && Objects.equal(Integer.valueOf(participant2.getCapabilities()), Integer.valueOf(participant.getCapabilities())) && Objects.equal(participant2.getResult(), participant.getResult()) && Objects.equal(participant2.getParticipantId(), participant.getParticipantId());
    }

    static String zzb(Participant participant) {
        return Objects.toStringHelper(participant).add("ParticipantId", participant.getParticipantId()).add("Player", participant.getPlayer()).add("Status", Integer.valueOf(participant.getStatus())).add("ClientAddress", participant.zzcg()).add("ConnectedToRoom", Boolean.valueOf(participant.isConnectedToRoom())).add("DisplayName", participant.getDisplayName()).add("IconImage", participant.getIconImageUri()).add("IconImageUrl", participant.getIconImageUrl()).add("HiResImage", participant.getHiResImageUri()).add("HiResImageUrl", participant.getHiResImageUrl()).add("Capabilities", Integer.valueOf(participant.getCapabilities())).add("Result", participant.getResult()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Participant freeze() {
        return this;
    }

    public final int getCapabilities() {
        return this.zzoj;
    }

    public final String getDisplayName() {
        return this.zzfh == null ? this.zzn : this.zzfh.getDisplayName();
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        if (this.zzfh == null) {
            DataUtils.copyStringToBuffer(this.zzn, charArrayBuffer);
        } else {
            this.zzfh.getDisplayName(charArrayBuffer);
        }
    }

    public final Uri getHiResImageUri() {
        return this.zzfh == null ? this.zzs : this.zzfh.getHiResImageUri();
    }

    public final String getHiResImageUrl() {
        return this.zzfh == null ? this.zzad : this.zzfh.getHiResImageUrl();
    }

    public final Uri getIconImageUri() {
        return this.zzfh == null ? this.zzr : this.zzfh.getIconImageUri();
    }

    public final String getIconImageUrl() {
        return this.zzfh == null ? this.zzac : this.zzfh.getIconImageUrl();
    }

    public final String getParticipantId() {
        return this.zzhl;
    }

    public final Player getPlayer() {
        return this.zzfh;
    }

    public final ParticipantResult getResult() {
        return this.zzok;
    }

    public final int getStatus() {
        return this.status;
    }

    public final int hashCode() {
        return zza((Participant) this);
    }

    public final boolean isConnectedToRoom() {
        return this.zzoi;
    }

    public final boolean isDataValid() {
        return true;
    }

    public final void setShouldDowngrade(boolean z) {
        super.setShouldDowngrade(z);
        if (this.zzfh != null) {
            this.zzfh.setShouldDowngrade(z);
        }
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        String str = null;
        int i2 = 0;
        if (shouldDowngrade()) {
            parcel.writeString(this.zzhl);
            parcel.writeString(this.zzn);
            parcel.writeString(this.zzr == null ? null : this.zzr.toString());
            if (this.zzs != null) {
                str = this.zzs.toString();
            }
            parcel.writeString(str);
            parcel.writeInt(this.status);
            parcel.writeString(this.zzoh);
            parcel.writeInt(this.zzoi ? 1 : 0);
            if (this.zzfh != null) {
                i2 = 1;
            }
            parcel.writeInt(i2);
            if (this.zzfh != null) {
                this.zzfh.writeToParcel(parcel, i);
                return;
            }
            return;
        }
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getParticipantId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getIconImageUri(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getHiResImageUri(), i, false);
        SafeParcelWriter.writeInt(parcel, 5, getStatus());
        SafeParcelWriter.writeString(parcel, 6, this.zzoh, false);
        SafeParcelWriter.writeBoolean(parcel, 7, isConnectedToRoom());
        SafeParcelWriter.writeParcelable(parcel, 8, getPlayer(), i, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzoj);
        SafeParcelWriter.writeParcelable(parcel, 10, getResult(), i, false);
        SafeParcelWriter.writeString(parcel, 11, getIconImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 12, getHiResImageUrl(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzcg() {
        return this.zzoh;
    }
}
