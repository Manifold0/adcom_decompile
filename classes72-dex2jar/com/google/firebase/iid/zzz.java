// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import com.google.android.gms.common.internal.Objects;
import android.util.Base64;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;

final class zzz
{
    private final KeyPair zzbr;
    private final long zzbs;
    
    @VisibleForTesting
    zzz(final KeyPair zzbr, final long zzbs) {
        this.zzbr = zzbr;
        this.zzbs = zzbs;
    }
    
    private final String zzv() {
        return Base64.encodeToString(this.zzbr.getPublic().getEncoded(), 11);
    }
    
    private final String zzw() {
        return Base64.encodeToString(this.zzbr.getPrivate().getEncoded(), 11);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof zzz) {
            final zzz zzz = (zzz)o;
            if (this.zzbs == zzz.zzbs && this.zzbr.getPublic().equals(zzz.zzbr.getPublic()) && this.zzbr.getPrivate().equals(zzz.zzbr.getPrivate())) {
                return true;
            }
        }
        return false;
    }
    
    final long getCreationTime() {
        return this.zzbs;
    }
    
    final KeyPair getKeyPair() {
        return this.zzbr;
    }
    
    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzbr.getPublic(), this.zzbr.getPrivate(), this.zzbs });
    }
}
