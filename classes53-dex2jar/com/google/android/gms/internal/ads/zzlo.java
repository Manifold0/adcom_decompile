// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IInterface;

public interface zzlo extends IInterface
{
    float getAspectRatio() throws RemoteException;
    
    int getPlaybackState() throws RemoteException;
    
    boolean isClickToExpandEnabled() throws RemoteException;
    
    boolean isCustomControlsEnabled() throws RemoteException;
    
    boolean isMuted() throws RemoteException;
    
    void mute(final boolean p0) throws RemoteException;
    
    void pause() throws RemoteException;
    
    void play() throws RemoteException;
    
    void zza(final zzlr p0) throws RemoteException;
    
    float zzim() throws RemoteException;
    
    float zzin() throws RemoteException;
    
    zzlr zzio() throws RemoteException;
}
