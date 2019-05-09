// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IInterface;

public interface zzlr extends IInterface
{
    void onVideoEnd() throws RemoteException;
    
    void onVideoMute(final boolean p0) throws RemoteException;
    
    void onVideoPause() throws RemoteException;
    
    void onVideoPlay() throws RemoteException;
    
    void onVideoStart() throws RemoteException;
}
