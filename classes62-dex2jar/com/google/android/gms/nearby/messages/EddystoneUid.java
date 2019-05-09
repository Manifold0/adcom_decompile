// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import java.util.Arrays;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.nearby.messages.internal.zzc;
import com.google.android.gms.nearby.messages.internal.zzg;

public class EddystoneUid
{
    public static final int INSTANCE_LENGTH = 6;
    public static final int LENGTH = 16;
    public static final int NAMESPACE_LENGTH = 10;
    private final zzg zzes;
    
    public EddystoneUid(final String s) {
        this(zzc.zzm(s));
    }
    
    public EddystoneUid(final String s, final String s2) {
        this.zzes = new zzg(s, s2);
    }
    
    private EddystoneUid(final byte[] array) {
        Preconditions.checkArgument(array.length == 16, (Object)"Bytes must be a namespace plus instance (16 bytes).");
        this.zzes = new zzg(array);
    }
    
    public static EddystoneUid from(final Message message) {
        final boolean zzl = message.zzl("__eddystone_uid");
        final String type = message.getType();
        Preconditions.checkArgument(zzl, (Object)new StringBuilder(String.valueOf(type).length() + 58).append("Message type '").append(type).append("' is not Message.MESSAGE_TYPE_EDDYSTONE_UID.").toString());
        return new EddystoneUid(message.getContent());
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof EddystoneUid && Objects.equal((Object)this.zzes, (Object)((EddystoneUid)o).zzes));
    }
    
    public String getHex() {
        return this.zzes.getHex();
    }
    
    public String getInstance() {
        final byte[] bytes = this.zzes.getBytes();
        if (bytes.length < 16) {
            return null;
        }
        return zzc.zzf(Arrays.copyOfRange(bytes, 10, 16));
    }
    
    public String getNamespace() {
        return zzc.zzf(Arrays.copyOfRange(this.zzes.getBytes(), 0, 10));
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzes });
    }
    
    @Override
    public String toString() {
        final String hex = this.getHex();
        return new StringBuilder(String.valueOf(hex).length() + 17).append("EddystoneUid{id=").append(hex).append('}').toString();
    }
}
