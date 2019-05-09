// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.Nullable;
import java.util.Locale;

public final class zzk
{
    private String zzis;
    
    private zzk(final String s) {
        this.zzis = s.toLowerCase(Locale.US);
    }
    
    @Nullable
    public static zzk zze(@Nullable final String s) {
        Preconditions.checkArgument(s == null || !s.isEmpty());
        if (s == null) {
            return null;
        }
        return new zzk(s);
    }
    
    @Override
    public final boolean equals(final Object o) {
        return o == this || (o != null && o.getClass() == this.getClass() && this.zzis.equals(((zzk)o).zzis));
    }
    
    @Override
    public final int hashCode() {
        return this.zzis.hashCode();
    }
    
    public final boolean isFolder() {
        return this.zzis.equals("application/vnd.google-apps.folder");
    }
    
    @Override
    public final String toString() {
        return this.zzis;
    }
    
    public final boolean zzaz() {
        return this.zzis.startsWith("application/vnd.google-apps");
    }
}
