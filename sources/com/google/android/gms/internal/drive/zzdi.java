package com.google.android.gms.internal.drive;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.OnChangeListener;
import com.google.android.gms.drive.events.zzj;

final class zzdi {
    private OnChangeListener zzge;
    private zzee zzgf;
    private DriveId zzk;

    zzdi(zzch zzch, OnChangeListener onChangeListener, DriveId driveId) {
        Preconditions.checkState(zzj.zza(1, driveId));
        this.zzge = onChangeListener;
        this.zzk = driveId;
        Looper looper = zzch.getLooper();
        Context applicationContext = zzch.getApplicationContext();
        onChangeListener.getClass();
        this.zzgf = new zzee(looper, applicationContext, 1, zzdj.zza(onChangeListener));
        this.zzgf.zzf(1);
    }
}
