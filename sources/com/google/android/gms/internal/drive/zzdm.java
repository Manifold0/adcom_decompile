package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.events.OpenFileCallback;

final /* synthetic */ class zzdm implements zzdg {
    private final zzff zzgl;

    zzdm(zzff zzff) {
        this.zzgl = zzff;
    }

    public final void accept(Object obj) {
        zzff zzff = this.zzgl;
        ((OpenFileCallback) obj).onProgress(zzff.zzhi, zzff.zzhj);
    }
}
