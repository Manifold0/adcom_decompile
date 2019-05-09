// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import com.google.android.gms.drive.metadata.internal.zzb;

final class zzhq extends zzb
{
    zzhq(final String s, final Collection collection, final Collection collection2, final int n) {
        super(s, collection, collection2, 7000000);
    }
    
    @Override
    protected final Boolean zze(final DataHolder dataHolder, final int n, final int n2) {
        return dataHolder.getInteger("trashed", n, n2) == 2;
    }
}
