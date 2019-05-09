// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;

final class zzbq implements ListenerHolder$Notifier<DriveFile.DownloadProgressListener>
{
    private final /* synthetic */ long zzez;
    private final /* synthetic */ long zzfa;
    
    zzbq(final zzbp zzbp, final long zzez, final long zzfa) {
        this.zzez = zzez;
        this.zzfa = zzfa;
    }
    
    public final void onNotifyListenerFailed() {
    }
}
