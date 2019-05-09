// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.drive.DriveFile;

final class zzbp implements DownloadProgressListener
{
    private final ListenerHolder<DownloadProgressListener> zzey;
    
    public zzbp(final ListenerHolder<DownloadProgressListener> zzey) {
        this.zzey = zzey;
    }
    
    @Override
    public final void onProgress(final long n, final long n2) {
        this.zzey.notifyListener((ListenerHolder$Notifier)new zzbq(this, n, n2));
    }
}
