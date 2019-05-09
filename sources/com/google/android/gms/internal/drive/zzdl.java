package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.events.OpenFileCallback;

final /* synthetic */ class zzdl implements zzdg {
    private final zzdk zzgj;
    private final Status zzgk;

    zzdl(zzdk zzdk, Status status) {
        this.zzgj = zzdk;
        this.zzgk = status;
    }

    public final void accept(Object obj) {
        this.zzgj.zza(this.zzgk, (OpenFileCallback) obj);
    }
}
