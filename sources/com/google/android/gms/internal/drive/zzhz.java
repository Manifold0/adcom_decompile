package com.google.android.gms.internal.drive;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.zzb;

public final class zzhz extends zzb implements SearchableMetadataField<Boolean> {
    public zzhz(String str, int i) {
        super(str, GmsVersion.VERSION_HALLOUMI);
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return zze(dataHolder, i, i2);
    }

    protected final Boolean zze(DataHolder dataHolder, int i, int i2) {
        return Boolean.valueOf(dataHolder.getInteger(getName(), i, i2) != 0);
    }
}
