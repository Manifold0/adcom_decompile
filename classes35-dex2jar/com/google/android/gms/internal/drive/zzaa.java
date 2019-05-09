// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.Metadata;

public final class zzaa extends Metadata
{
    private final MetadataBundle zzdr;
    
    public zzaa(final MetadataBundle zzdr) {
        this.zzdr = zzdr;
    }
    
    public final boolean isDataValid() {
        return this.zzdr != null;
    }
    
    @Override
    public final String toString() {
        final String value = String.valueOf(this.zzdr);
        return new StringBuilder(String.valueOf(value).length() + 17).append("Metadata [mImpl=").append(value).append("]").toString();
    }
    
    @Override
    public final <T> T zza(final MetadataField<T> metadataField) {
        return this.zzdr.zza(metadataField);
    }
}
