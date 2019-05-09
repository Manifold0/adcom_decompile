// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.util.Iterator;
import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import java.util.Arrays;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.zzm;

public final class zzij extends zzm<DriveId>
{
    public static final zzij zzkt;
    
    static {
        zzkt = new zzij();
    }
    
    private zzij() {
        super("driveId", Arrays.asList("sqlId", "resourceId", "mimeType"), Arrays.asList("dbInstanceId"), 4100000);
    }
    
    @Override
    protected final boolean zzb(final DataHolder dataHolder, final int n, final int n2) {
        final Iterator<String> iterator = this.zzar().iterator();
        while (iterator.hasNext()) {
            if (!dataHolder.hasColumn((String)iterator.next())) {
                return false;
            }
        }
        return true;
    }
}
