package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.metadata.internal.zzl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public final class zzhw extends zzl<DriveSpace> {
    public zzhw(int i) {
        super("spaces", Arrays.asList(new String[]{"inDriveSpace", "isAppData", "inGooglePhotosSpace"}), Collections.emptySet(), GmsVersion.VERSION_ORLA);
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return zzd(dataHolder, i, i2);
    }

    protected final Collection<DriveSpace> zzd(DataHolder dataHolder, int i, int i2) {
        Collection arrayList = new ArrayList();
        if (dataHolder.getBoolean("inDriveSpace", i, i2)) {
            arrayList.add(DriveSpace.zzaf);
        }
        if (dataHolder.getBoolean("isAppData", i, i2)) {
            arrayList.add(DriveSpace.zzag);
        }
        if (dataHolder.getBoolean("inGooglePhotosSpace", i, i2)) {
            arrayList.add(DriveSpace.zzah);
        }
        return arrayList;
    }
}
