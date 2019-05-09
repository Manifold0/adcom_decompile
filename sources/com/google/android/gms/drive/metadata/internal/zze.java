package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.zzd;
import java.util.Date;

public class zze extends zzd<Date> {
    public zze(String str, int i) {
        super(str, i);
    }

    protected final /* synthetic */ void zza(Bundle bundle, Object obj) {
        bundle.putLong(getName(), ((Date) obj).getTime());
    }

    protected final /* synthetic */ Object zzb(Bundle bundle) {
        return new Date(bundle.getLong(getName()));
    }

    protected final /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return new Date(dataHolder.getLong(getName(), i, i2));
    }
}
