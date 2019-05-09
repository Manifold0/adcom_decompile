// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Preconditions;
import android.os.Looper;
import android.os.HandlerThread;
import android.os.Handler;

@zzadh
public final class zzamg
{
    private Handler mHandler;
    private final Object mLock;
    private HandlerThread zzcts;
    private int zzctt;
    
    public zzamg() {
        this.zzcts = null;
        this.mHandler = null;
        this.zzctt = 0;
        this.mLock = new Object();
    }
    
    public final Handler getHandler() {
        return this.mHandler;
    }
    
    public final Looper zzsa() {
        while (true) {
            while (true) {
                synchronized (this.mLock) {
                    if (this.zzctt == 0) {
                        if (this.zzcts == null) {
                            zzakb.v("Starting the looper thread.");
                            (this.zzcts = new HandlerThread("LooperProvider")).start();
                            this.mHandler = new Handler(this.zzcts.getLooper());
                            zzakb.v("Looper thread started.");
                        }
                        else {
                            zzakb.v("Resuming the looper thread");
                            this.mLock.notifyAll();
                        }
                        ++this.zzctt;
                        return this.zzcts.getLooper();
                    }
                }
                Preconditions.checkNotNull((Object)this.zzcts, (Object)"Invalid state: mHandlerThread should already been initialized.");
                continue;
            }
        }
    }
}
