package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.drive.metadata.zza;

public class zzi extends zza<Long> {
    public zzi(String str, int i) {
        super(str, GmsVersion.VERSION_JARLSBERG);
    }

    protected final /* synthetic */ void zza(Bundle bundle, Object obj) {
        bundle.putLong(getName(), ((Long) obj).longValue());
    }

    protected final /* synthetic */ Object zzb(Bundle bundle) {
        return Long.valueOf(bundle.getLong(getName()));
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return Long.valueOf(dataHolder.getLong(getName(), i, i2));
    }
}
