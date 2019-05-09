// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.util.UUID;
import com.google.android.gms.nearby.messages.internal.zzl;

public class IBeaconId
{
    public static final int LENGTH = 20;
    private final zzl zzet;
    
    public IBeaconId(final UUID uuid, final short n, final short n2) {
        this.zzet = new zzl(uuid, n, n2);
    }
    
    private IBeaconId(final byte[] array) {
        Preconditions.checkArgument(array.length == 20, (Object)"iBeacon ID must be a UUID, a major, and a minor (20 total bytes).");
        this.zzet = new zzl(array);
    }
    
    public static IBeaconId from(final Message message) {
        final boolean zzl = message.zzl("__i_beacon_id");
        final String type = message.getType();
        Preconditions.checkArgument(zzl, (Object)new StringBuilder(String.valueOf(type).length() + 55).append("Message type '").append(type).append("' is not Message.MESSAGE_TYPE_I_BEACON_ID").toString());
        return new IBeaconId(message.getContent());
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof IBeaconId && Objects.equal((Object)this.zzet, (Object)((IBeaconId)o).zzet));
    }
    
    public short getMajor() {
        return this.zzet.zzaf();
    }
    
    public short getMinor() {
        return this.zzet.zzag();
    }
    
    public UUID getProximityUuid() {
        return this.zzet.getProximityUuid();
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzet });
    }
    
    @Override
    public String toString() {
        final String value = String.valueOf(this.getProximityUuid());
        return new StringBuilder(String.valueOf(value).length() + 53).append("IBeaconId{proximityUuid=").append(value).append(", major=").append(this.getMajor()).append(", minor=").append(this.getMinor()).append('}').toString();
    }
}
