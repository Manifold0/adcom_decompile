// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.games.internal.zzt;

final class zzcz extends zzt<Videos.CaptureOverlayStateListener>
{
    zzcz(final VideosClient videosClient, final ListenerHolder$ListenerKey listenerHolder$ListenerKey) {
        super(listenerHolder$ListenerKey);
    }
    
    @Override
    protected final void zzc(final zze zze, final TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zzbb();
        taskCompletionSource.setResult((Object)true);
    }
}
