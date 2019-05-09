// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

public final class zzyi extends zzlp
{
    private final Object mLock;
    private volatile zzlr zzbuq;
    
    public zzyi() {
        this.mLock = new Object();
    }
    
    public final float getAspectRatio() throws RemoteException {
        throw new RemoteException();
    }
    
    public final int getPlaybackState() throws RemoteException {
        throw new RemoteException();
    }
    
    public final boolean isClickToExpandEnabled() throws RemoteException {
        throw new RemoteException();
    }
    
    public final boolean isCustomControlsEnabled() throws RemoteException {
        throw new RemoteException();
    }
    
    public final boolean isMuted() throws RemoteException {
        throw new RemoteException();
    }
    
    public final void mute(final boolean b) throws RemoteException {
        throw new RemoteException();
    }
    
    public final void pause() throws RemoteException {
        throw new RemoteException();
    }
    
    public final void play() throws RemoteException {
        throw new RemoteException();
    }
    
    public final void zza(final zzlr zzbuq) throws RemoteException {
        synchronized (this.mLock) {
            this.zzbuq = zzbuq;
        }
    }
    
    public final float zzim() throws RemoteException {
        throw new RemoteException();
    }
    
    public final float zzin() throws RemoteException {
        throw new RemoteException();
    }
    
    public final zzlr zzio() throws RemoteException {
        synchronized (this.mLock) {
            return this.zzbuq;
        }
    }
}
