package com.google.android.gms.drive.query.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.zzb;
import com.google.android.gms.drive.query.Filter;
import java.util.List;

public final class zzk implements zzj<Boolean> {
    private Boolean zzlw = Boolean.valueOf(false);

    private zzk() {
    }

    public static boolean zza(@Nullable Filter filter) {
        return filter == null ? false : ((Boolean) filter.zza(new zzk())).booleanValue();
    }

    public final /* synthetic */ Object zza(zzb zzb, Object obj) {
        return this.zzlw;
    }

    public final /* synthetic */ Object zza(zzx zzx, MetadataField metadataField, Object obj) {
        return this.zzlw;
    }

    public final /* synthetic */ Object zza(zzx zzx, List list) {
        return this.zzlw;
    }

    public final /* synthetic */ Object zza(Object obj) {
        return this.zzlw;
    }

    public final /* synthetic */ Object zzbb() {
        return this.zzlw;
    }

    public final /* synthetic */ Object zzbc() {
        return this.zzlw;
    }

    public final /* synthetic */ Object zzc(MetadataField metadataField, Object obj) {
        return this.zzlw;
    }

    public final /* synthetic */ Object zze(MetadataField metadataField) {
        return this.zzlw;
    }

    public final /* synthetic */ Object zzg(String str) {
        if (!str.isEmpty()) {
            this.zzlw = Boolean.valueOf(true);
        }
        return this.zzlw;
    }
}
