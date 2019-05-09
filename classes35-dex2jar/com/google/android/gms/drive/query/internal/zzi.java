// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import java.util.Set;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

final class zzi
{
    static MetadataField<?> zza(final MetadataBundle metadataBundle) {
        final Set<MetadataField<?>> zzay = metadataBundle.zzay();
        if (zzay.size() != 1) {
            throw new IllegalArgumentException("bundle should have exactly 1 populated field");
        }
        return zzay.iterator().next();
    }
}
