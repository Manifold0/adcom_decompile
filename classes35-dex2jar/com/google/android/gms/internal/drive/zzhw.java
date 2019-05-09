// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.util.ArrayList;
import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.metadata.internal.zzl;

public final class zzhw extends zzl<DriveSpace>
{
    public zzhw(final int n) {
        super("spaces", Arrays.asList("inDriveSpace", "isAppData", "inGooglePhotosSpace"), Collections.emptySet(), 7000000);
    }
    
    @Override
    protected final Collection<DriveSpace> zzd(final DataHolder dataHolder, final int n, final int n2) {
        final ArrayList<DriveSpace> list = new ArrayList<DriveSpace>();
        if (dataHolder.getBoolean("inDriveSpace", n, n2)) {
            list.add(DriveSpace.zzaf);
        }
        if (dataHolder.getBoolean("isAppData", n, n2)) {
            list.add(DriveSpace.zzag);
        }
        if (dataHolder.getBoolean("inGooglePhotosSpace", n, n2)) {
            list.add(DriveSpace.zzah);
        }
        return list;
    }
}
