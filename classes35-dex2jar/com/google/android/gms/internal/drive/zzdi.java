// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.drive.events.zzi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.events.zzj;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.OnChangeListener;

final class zzdi
{
    private OnChangeListener zzge;
    private zzee zzgf;
    private DriveId zzk;
    
    zzdi(final zzch zzch, final OnChangeListener zzge, final DriveId zzk) {
        Preconditions.checkState(zzj.zza(1, zzk));
        this.zzge = zzge;
        this.zzk = zzk;
        final Looper looper = zzch.getLooper();
        final Context applicationContext = zzch.getApplicationContext();
        zzge.getClass();
        (this.zzgf = new zzee(looper, applicationContext, 1, zzdj.zza(zzge))).zzf(1);
    }
}
