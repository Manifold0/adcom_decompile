// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.app.DownloadManager$Request;
import android.annotation.TargetApi;

@TargetApi(9)
public class zzaks extends zzakq
{
    public zzaks() {
        super(null);
    }
    
    @Override
    public boolean zza(final DownloadManager$Request downloadManager$Request) {
        downloadManager$Request.setShowRunningNotification(true);
        return true;
    }
    
    @Override
    public final int zzrl() {
        return 6;
    }
    
    @Override
    public final int zzrm() {
        return 7;
    }
}
