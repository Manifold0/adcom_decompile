// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.internal.Preconditions;

public final class zzg extends zzc
{
    public zzg(final String s) {
        this(zzc.zzm(s));
    }
    
    public zzg(final String s, final String s2) {
        this(zzc.zzm(s), zzc.zzm(s2));
    }
    
    public zzg(final byte[] array) {
        Preconditions.checkArgument(array.length == 10 || array.length == 16, (Object)"Bytes must be a namespace (10 bytes), or a namespace plus instance (16 bytes).");
        super(array);
    }
    
    private zzg(final byte[] array, final byte[] array2) {
        final boolean b = false;
        Preconditions.checkArgument(array.length == 10, (Object)new StringBuilder(53).append("Namespace length(").append(array.length).append(" bytes) must be 10 bytes.").toString());
        boolean b2 = b;
        if (array2.length == 6) {
            b2 = true;
        }
        Preconditions.checkArgument(b2, (Object)new StringBuilder(51).append("Instance length(").append(array2.length).append(" bytes) must be 6 bytes.").toString());
        this(ArrayUtils.concatByteArrays(new byte[][] { array, array2 }));
    }
    
    @Override
    public final String toString() {
        final String hex = this.getHex();
        return new StringBuilder(String.valueOf(hex).length() + 26).append("EddystoneUidPrefix{bytes=").append(hex).append('}').toString();
    }
}
