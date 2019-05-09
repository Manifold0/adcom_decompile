// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.nio.ByteBuffer;
import android.support.annotation.Nullable;
import java.util.UUID;

public final class zzl extends zzc
{
    public zzl(final UUID uuid, @Nullable final Short n, @Nullable final Short n2) {
        int n3 = 0;
        int n4;
        if (n == null) {
            n4 = 0;
        }
        else {
            n4 = 2;
        }
        if (n2 != null) {
            n3 = 2;
        }
        final ByteBuffer allocate = ByteBuffer.allocate(n3 + (n4 + 16));
        allocate.putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits());
        if (n != null) {
            allocate.putShort(n);
        }
        if (n2 != null) {
            allocate.putShort(n2);
        }
        this(allocate.array());
    }
    
    public zzl(final byte[] array) {
        Preconditions.checkArgument(array.length == 16 || array.length == 18 || array.length == 20, (Object)"Prefix must be a UUID, a UUID and a major, or a UUID, a major, and a minor.");
        super(array);
    }
    
    public final UUID getProximityUuid() {
        final ByteBuffer wrap = ByteBuffer.wrap(this.getBytes());
        return new UUID(wrap.getLong(), wrap.getLong());
    }
    
    @Override
    public final String toString() {
        final String value = String.valueOf(this.getProximityUuid());
        final String value2 = String.valueOf(this.zzaf());
        final String value3 = String.valueOf(this.zzag());
        return new StringBuilder(String.valueOf(value).length() + 47 + String.valueOf(value2).length() + String.valueOf(value3).length()).append("IBeaconIdPrefix{proximityUuid=").append(value).append(", major=").append(value2).append(", minor=").append(value3).append('}').toString();
    }
    
    public final Short zzaf() {
        final byte[] bytes = this.getBytes();
        if (bytes.length >= 18) {
            return ByteBuffer.wrap(bytes).getShort(16);
        }
        return null;
    }
    
    public final Short zzag() {
        final byte[] bytes = this.getBytes();
        if (bytes.length == 20) {
            return ByteBuffer.wrap(bytes).getShort(18);
        }
        return null;
    }
}
