package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.OnChangeListener;

final /* synthetic */ class zzdj implements ChangeListener {
    private final OnChangeListener zzgg;

    private zzdj(OnChangeListener onChangeListener) {
        this.zzgg = onChangeListener;
    }

    static ChangeListener zza(OnChangeListener onChangeListener) {
        return new zzdj(onChangeListener);
    }

    public final void onChange(ChangeEvent changeEvent) {
        this.zzgg.onChange(changeEvent);
    }
}
