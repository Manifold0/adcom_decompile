// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzyc extends IInterface
{
    String getAdvertiser() throws RemoteException;
    
    String getBody() throws RemoteException;
    
    String getCallToAction() throws RemoteException;
    
    Bundle getExtras() throws RemoteException;
    
    String getHeadline() throws RemoteException;
    
    List getImages() throws RemoteException;
    
    boolean getOverrideClickHandling() throws RemoteException;
    
    boolean getOverrideImpressionRecording() throws RemoteException;
    
    zzlo getVideoController() throws RemoteException;
    
    void recordImpression() throws RemoteException;
    
    void zzb(final IObjectWrapper p0, final IObjectWrapper p1, final IObjectWrapper p2) throws RemoteException;
    
    void zzj(final IObjectWrapper p0) throws RemoteException;
    
    void zzk(final IObjectWrapper p0) throws RemoteException;
    
    IObjectWrapper zzke() throws RemoteException;
    
    zzps zzkf() throws RemoteException;
    
    zzpw zzkg() throws RemoteException;
    
    void zzl(final IObjectWrapper p0) throws RemoteException;
    
    IObjectWrapper zzmv() throws RemoteException;
    
    IObjectWrapper zzmw() throws RemoteException;
}
