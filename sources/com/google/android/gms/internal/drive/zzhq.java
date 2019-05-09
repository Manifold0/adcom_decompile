package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.metadata.internal.zzb;
import java.util.Collection;

final class zzhq extends zzb {
    zzhq(String str, Collection collection, Collection collection2, int i) {
        super(str, collection, collection2, GmsVersion.VERSION_ORLA);
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return zze(dataHolder, i, i2);
    }

    protected final Boolean zze(DataHolder dataHolder, int i, int i2) {
        return Boolean.valueOf(dataHolder.getInteger("trashed", i, i2) == 2);
    }
}
