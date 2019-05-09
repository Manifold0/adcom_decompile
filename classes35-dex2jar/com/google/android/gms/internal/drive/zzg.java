// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.drive.events.ListenerToken;

public final class zzg implements ListenerToken
{
    private final ListenerHolder$ListenerKey zzcw;
    private ICancelToken zzcx;
    
    public zzg(final ListenerHolder$ListenerKey zzcw) {
        this.zzcx = null;
        this.zzcw = zzcw;
    }
    
    public final boolean cancel() {
        if (this.zzcx != null) {
            try {
                this.zzcx.cancel();
                return true;
            }
            catch (RemoteException ex) {}
        }
        return false;
    }
    
    public final void setCancelToken(final ICancelToken zzcx) {
        this.zzcx = zzcx;
    }
    
    public final ListenerHolder$ListenerKey zzac() {
        return this.zzcw;
    }
}
