package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ArrayUtils;

public final class zzg extends zzc {
    public zzg(String str) {
        this(zzc.zzm(str));
    }

    public zzg(String str, String str2) {
        this(zzc.zzm(str), zzc.zzm(str2));
    }

    public zzg(byte[] bArr) {
        boolean z = bArr.length == 10 || bArr.length == 16;
        Preconditions.checkArgument(z, "Bytes must be a namespace (10 bytes), or a namespace plus instance (16 bytes).");
        super(bArr);
    }

    private zzg(byte[] bArr, byte[] bArr2) {
        boolean z = false;
        byte[][] bArr3 = new byte[2][];
        Preconditions.checkArgument(bArr.length == 10, "Namespace length(" + bArr.length + " bytes) must be 10 bytes.");
        bArr3[0] = bArr;
        if (bArr2.length == 6) {
            z = true;
        }
        Preconditions.checkArgument(z, "Instance length(" + bArr2.length + " bytes) must be 6 bytes.");
        bArr3[1] = bArr2;
        this(ArrayUtils.concatByteArrays(bArr3));
    }

    public final String toString() {
        String hex = getHex();
        return new StringBuilder(String.valueOf(hex).length() + 26).append("EddystoneUidPrefix{bytes=").append(hex).append('}').toString();
    }
}
