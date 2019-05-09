package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzd;
import com.vungle.warren.persistence.FilePersistor.Version;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Class(creator = "GameRequestEntityCreator")
@Reserved({1000})
@Deprecated
public final class GameRequestEntity extends zzd implements GameRequest {
    public static final Creator<GameRequestEntity> CREATOR = new zza();
    @Field(getter = "getData", id = 3)
    private final byte[] data;
    @Field(getter = "getStatus", id = 12)
    private final int status;
    @Field(getter = "getType", id = 7)
    private final int type;
    @Field(getter = "getRequestId", id = 4)
    private final String zzht;
    @Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @Field(getter = "getCreationTimestamp", id = 9)
    private final long zzoa;
    @Field(getter = "getSender", id = 2)
    private final PlayerEntity zzqh;
    @Field(getter = "getRecipients", id = 5)
    private final ArrayList<PlayerEntity> zzqi;
    @Field(getter = "getExpirationTimestamp", id = 10)
    private final long zzqj;
    @Field(getter = "getRecipientStatusBundle", id = 11)
    private final Bundle zzqk;

    @Constructor
    GameRequestEntity(@Param(id = 1) GameEntity gameEntity, @Param(id = 2) PlayerEntity playerEntity, @Param(id = 3) byte[] bArr, @Param(id = 4) String str, @Param(id = 5) ArrayList<PlayerEntity> arrayList, @Param(id = 7) int i, @Param(id = 9) long j, @Param(id = 10) long j2, @Param(id = 11) Bundle bundle, @Param(id = 12) int i2) {
        this.zzky = gameEntity;
        this.zzqh = playerEntity;
        this.data = bArr;
        this.zzht = str;
        this.zzqi = arrayList;
        this.type = i;
        this.zzoa = j;
        this.zzqj = j2;
        this.zzqk = bundle;
        this.status = i2;
    }

    public GameRequestEntity(GameRequest gameRequest) {
        this.zzky = new GameEntity(gameRequest.getGame());
        this.zzqh = new PlayerEntity(gameRequest.getSender());
        this.zzht = gameRequest.getRequestId();
        this.type = gameRequest.getType();
        this.zzoa = gameRequest.getCreationTimestamp();
        this.zzqj = gameRequest.getExpirationTimestamp();
        this.status = gameRequest.getStatus();
        Object data = gameRequest.getData();
        if (data == null) {
            this.data = null;
        } else {
            this.data = new byte[data.length];
            System.arraycopy(data, 0, this.data, 0, data.length);
        }
        List recipients = gameRequest.getRecipients();
        int size = recipients.size();
        this.zzqi = new ArrayList(size);
        this.zzqk = new Bundle();
        for (int i = 0; i < size; i++) {
            Player player = (Player) ((Player) recipients.get(i)).freeze();
            String playerId = player.getPlayerId();
            this.zzqi.add((PlayerEntity) player);
            this.zzqk.putInt(playerId, gameRequest.getRecipientStatus(playerId));
        }
    }

    static int zza(GameRequest gameRequest) {
        return (Arrays.hashCode(zzb(gameRequest)) * 31) + Objects.hashCode(new Object[]{gameRequest.getGame(), gameRequest.getRecipients(), gameRequest.getRequestId(), gameRequest.getSender(), Integer.valueOf(gameRequest.getType()), Long.valueOf(gameRequest.getCreationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp())});
    }

    static boolean zza(GameRequest gameRequest, Object obj) {
        if (!(obj instanceof GameRequest)) {
            return false;
        }
        if (gameRequest == obj) {
            return true;
        }
        GameRequest gameRequest2 = (GameRequest) obj;
        return Objects.equal(gameRequest2.getGame(), gameRequest.getGame()) && Objects.equal(gameRequest2.getRecipients(), gameRequest.getRecipients()) && Objects.equal(gameRequest2.getRequestId(), gameRequest.getRequestId()) && Objects.equal(gameRequest2.getSender(), gameRequest.getSender()) && Arrays.equals(zzb(gameRequest2), zzb(gameRequest)) && Objects.equal(Integer.valueOf(gameRequest2.getType()), Integer.valueOf(gameRequest.getType())) && Objects.equal(Long.valueOf(gameRequest2.getCreationTimestamp()), Long.valueOf(gameRequest.getCreationTimestamp())) && Objects.equal(Long.valueOf(gameRequest2.getExpirationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp()));
    }

    private static int[] zzb(GameRequest gameRequest) {
        List recipients = gameRequest.getRecipients();
        int size = recipients.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = gameRequest.getRecipientStatus(((Player) recipients.get(i)).getPlayerId());
        }
        return iArr;
    }

    static String zzc(GameRequest gameRequest) {
        return Objects.toStringHelper(gameRequest).add("Game", gameRequest.getGame()).add("Sender", gameRequest.getSender()).add("Recipients", gameRequest.getRecipients()).add(Version.ID, gameRequest.getData()).add("RequestId", gameRequest.getRequestId()).add("Type", Integer.valueOf(gameRequest.getType())).add("CreationTimestamp", Long.valueOf(gameRequest.getCreationTimestamp())).add("ExpirationTimestamp", Long.valueOf(gameRequest.getExpirationTimestamp())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final GameRequest freeze() {
        return this;
    }

    public final long getCreationTimestamp() {
        return this.zzoa;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final long getExpirationTimestamp() {
        return this.zzqj;
    }

    public final Game getGame() {
        return this.zzky;
    }

    public final int getRecipientStatus(String str) {
        return this.zzqk.getInt(str, 0);
    }

    public final List<Player> getRecipients() {
        return new ArrayList(this.zzqi);
    }

    public final String getRequestId() {
        return this.zzht;
    }

    public final Player getSender() {
        return this.zzqh;
    }

    public final int getStatus() {
        return this.status;
    }

    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isConsumed(String str) {
        return getRecipientStatus(str) == 1;
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzc(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getGame(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getSender(), i, false);
        SafeParcelWriter.writeByteArray(parcel, 3, getData(), false);
        SafeParcelWriter.writeString(parcel, 4, getRequestId(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getRecipients(), false);
        SafeParcelWriter.writeInt(parcel, 7, getType());
        SafeParcelWriter.writeLong(parcel, 9, getCreationTimestamp());
        SafeParcelWriter.writeLong(parcel, 10, getExpirationTimestamp());
        SafeParcelWriter.writeBundle(parcel, 11, this.zzqk, false);
        SafeParcelWriter.writeInt(parcel, 12, getStatus());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
