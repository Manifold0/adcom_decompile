// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.drive.events.ListenerToken;

final class zzdk extends zzl
{
    private final /* synthetic */ zzch zzfu;
    private final ListenerToken zzgh;
    private final ListenerHolder<OpenFileCallback> zzgi;
    
    zzdk(final zzch zzfu, final ListenerToken zzgh, final ListenerHolder<OpenFileCallback> zzgi) {
        this.zzfu = zzfu;
        this.zzgh = zzgh;
        this.zzgi = zzgi;
    }
    
    private final void zza(final zzdg<OpenFileCallback> zzdg) {
        this.zzgi.notifyListener((ListenerHolder$Notifier)new zzdo(this, zzdg));
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zza(new zzdl(this, status));
    }
    
    @Override
    public final void zza(final zzfb zzfb) throws RemoteException {
        this.zza(new zzdn(this, zzfb));
    }
    
    @Override
    public final void zza(final zzff zzff) throws RemoteException {
        this.zza(new zzdm(zzff));
    }
}
