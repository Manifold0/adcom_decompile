// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.images;

import com.google.android.gms.common.internal.Objects;
import android.net.Uri;

final class zab
{
    public final Uri uri;
    
    public zab(final Uri uri) {
        this.uri = uri;
    }
    
    @Override
    public final boolean equals(final Object o) {
        return o instanceof zab && (this == o || Objects.equal((Object)((zab)o).uri, (Object)this.uri));
    }
    
    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.uri });
    }
}
