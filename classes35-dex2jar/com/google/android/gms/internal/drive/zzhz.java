// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.zzb;

public final class zzhz extends zzb implements SearchableMetadataField<Boolean>
{
    public zzhz(final String s, final int n) {
        super(s, 4100000);
    }
    
    @Override
    protected final Boolean zze(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.getInteger(this.getName(), n, n2) != 0;
    }
}
