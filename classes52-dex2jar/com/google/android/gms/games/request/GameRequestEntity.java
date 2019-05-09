// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.request;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;
import java.util.List;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.Bundle;
import java.util.ArrayList;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@Deprecated
@SafeParcelable$Class(creator = "GameRequestEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class GameRequestEntity extends zzd implements GameRequest
{
    public static final Parcelable$Creator<GameRequestEntity> CREATOR;
    @SafeParcelable$Field(getter = "getData", id = 3)
    private final byte[] data;
    @SafeParcelable$Field(getter = "getStatus", id = 12)
    private final int status;
    @SafeParcelable$Field(getter = "getType", id = 7)
    private final int type;
    @SafeParcelable$Field(getter = "getRequestId", id = 4)
    private final String zzht;
    @SafeParcelable$Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @SafeParcelable$Field(getter = "getCreationTimestamp", id = 9)
    private final long zzoa;
    @SafeParcelable$Field(getter = "getSender", id = 2)
    private final PlayerEntity zzqh;
    @SafeParcelable$Field(getter = "getRecipients", id = 5)
    private final ArrayList<PlayerEntity> zzqi;
    @SafeParcelable$Field(getter = "getExpirationTimestamp", id = 10)
    private final long zzqj;
    @SafeParcelable$Field(getter = "getRecipientStatusBundle", id = 11)
    private final Bundle zzqk;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    @SafeParcelable$Constructor
    GameRequestEntity(@SafeParcelable$Param(id = 1) final GameEntity zzky, @SafeParcelable$Param(id = 2) final PlayerEntity zzqh, @SafeParcelable$Param(id = 3) final byte[] data, @SafeParcelable$Param(id = 4) final String zzht, @SafeParcelable$Param(id = 5) final ArrayList<PlayerEntity> zzqi, @SafeParcelable$Param(id = 7) final int type, @SafeParcelable$Param(id = 9) final long zzoa, @SafeParcelable$Param(id = 10) final long zzqj, @SafeParcelable$Param(id = 11) final Bundle zzqk, @SafeParcelable$Param(id = 12) final int status) {
        this.zzky = zzky;
        this.zzqh = zzqh;
        this.data = data;
        this.zzht = zzht;
        this.zzqi = zzqi;
        this.type = type;
        this.zzoa = zzoa;
        this.zzqj = zzqj;
        this.zzqk = zzqk;
        this.status = status;
    }
    
    public GameRequestEntity(final GameRequest gameRequest) {
        this.zzky = new GameEntity(gameRequest.getGame());
        this.zzqh = new PlayerEntity(gameRequest.getSender());
        this.zzht = gameRequest.getRequestId();
        this.type = gameRequest.getType();
        this.zzoa = gameRequest.getCreationTimestamp();
        this.zzqj = gameRequest.getExpirationTimestamp();
        this.status = gameRequest.getStatus();
        final byte[] data = gameRequest.getData();
        if (data == null) {
            this.data = null;
        }
        else {
            System.arraycopy(data, 0, this.data = new byte[data.length], 0, data.length);
        }
        final List<Player> recipients = gameRequest.getRecipients();
        final int size = recipients.size();
        this.zzqi = new ArrayList<PlayerEntity>(size);
        this.zzqk = new Bundle();
        for (int i = 0; i < size; ++i) {
            final Player player = (Player)recipients.get(i).freeze();
            final String playerId = player.getPlayerId();
            this.zzqi.add((PlayerEntity)player);
            this.zzqk.putInt(playerId, gameRequest.getRecipientStatus(playerId));
        }
    }
    
    static int zza(final GameRequest gameRequest) {
        return Arrays.hashCode(zzb(gameRequest)) * 31 + Objects.hashCode(new Object[] { gameRequest.getGame(), gameRequest.getRecipients(), gameRequest.getRequestId(), gameRequest.getSender(), gameRequest.getType(), gameRequest.getCreationTimestamp(), gameRequest.getExpirationTimestamp() });
    }
    
    static boolean zza(final GameRequest gameRequest, final Object o) {
        if (o instanceof GameRequest) {
            if (gameRequest == o) {
                return true;
            }
            final GameRequest gameRequest2 = (GameRequest)o;
            if (Objects.equal((Object)gameRequest2.getGame(), (Object)gameRequest.getGame()) && Objects.equal((Object)gameRequest2.getRecipients(), (Object)gameRequest.getRecipients()) && Objects.equal((Object)gameRequest2.getRequestId(), (Object)gameRequest.getRequestId()) && Objects.equal((Object)gameRequest2.getSender(), (Object)gameRequest.getSender()) && Arrays.equals(zzb(gameRequest2), zzb(gameRequest)) && Objects.equal((Object)gameRequest2.getType(), (Object)gameRequest.getType()) && Objects.equal((Object)gameRequest2.getCreationTimestamp(), (Object)gameRequest.getCreationTimestamp()) && Objects.equal((Object)gameRequest2.getExpirationTimestamp(), (Object)gameRequest.getExpirationTimestamp())) {
                return true;
            }
        }
        return false;
    }
    
    private static int[] zzb(final GameRequest gameRequest) {
        final List<Player> recipients = gameRequest.getRecipients();
        final int size = recipients.size();
        final int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = gameRequest.getRecipientStatus(recipients.get(i).getPlayerId());
        }
        return array;
    }
    
    static String zzc(final GameRequest gameRequest) {
        return Objects.toStringHelper((Object)gameRequest).add("Game", (Object)gameRequest.getGame()).add("Sender", (Object)gameRequest.getSender()).add("Recipients", (Object)gameRequest.getRecipients()).add("Data", (Object)gameRequest.getData()).add("RequestId", (Object)gameRequest.getRequestId()).add("Type", (Object)gameRequest.getType()).add("CreationTimestamp", (Object)gameRequest.getCreationTimestamp()).add("ExpirationTimestamp", (Object)gameRequest.getExpirationTimestamp()).toString();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final GameRequest freeze() {
        return this;
    }
    
    @Override
    public final long getCreationTimestamp() {
        return this.zzoa;
    }
    
    @Override
    public final byte[] getData() {
        return this.data;
    }
    
    @Override
    public final long getExpirationTimestamp() {
        return this.zzqj;
    }
    
    @Override
    public final Game getGame() {
        return this.zzky;
    }
    
    @Override
    public final int getRecipientStatus(final String s) {
        return this.zzqk.getInt(s, 0);
    }
    
    @Override
    public final List<Player> getRecipients() {
        return new ArrayList<Player>(this.zzqi);
    }
    
    @Override
    public final String getRequestId() {
        return this.zzht;
    }
    
    @Override
    public final Player getSender() {
        return this.zzqh;
    }
    
    @Override
    public final int getStatus() {
        return this.status;
    }
    
    @Override
    public final int getType() {
        return this.type;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    @Override
    public final boolean isConsumed(final String s) {
        return this.getRecipientStatus(s) == 1;
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final String toString() {
        return zzc(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getGame(), n, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.getSender(), n, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.getData(), false);
        SafeParcelWriter.writeString(parcel, 4, this.getRequestId(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, (List)this.getRecipients(), false);
        SafeParcelWriter.writeInt(parcel, 7, this.getType());
        SafeParcelWriter.writeLong(parcel, 9, this.getCreationTimestamp());
        SafeParcelWriter.writeLong(parcel, 10, this.getExpirationTimestamp());
        SafeParcelWriter.writeBundle(parcel, 11, this.zzqk, false);
        SafeParcelWriter.writeInt(parcel, 12, this.getStatus());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
