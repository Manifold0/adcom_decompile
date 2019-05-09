// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzqo extends IInterface
{
    void destroy() throws RemoteException;
    
    String getAdvertiser() throws RemoteException;
    
    String getBody() throws RemoteException;
    
    String getCallToAction() throws RemoteException;
    
    Bundle getExtras() throws RemoteException;
    
    String getHeadline() throws RemoteException;
    
    List getImages() throws RemoteException;
    
    String getMediationAdapterClassName() throws RemoteException;
    
    zzlo getVideoController() throws RemoteException;
    
    void performClick(final Bundle p0) throws RemoteException;
    
    boolean recordImpression(final Bundle p0) throws RemoteException;
    
    void reportTouchEvent(final Bundle p0) throws RemoteException;
    
    IObjectWrapper zzka() throws RemoteException;
    
    IObjectWrapper zzke() throws RemoteException;
    
    zzps zzkf() throws RemoteException;
    
    zzpw zzkg() throws RemoteException;
}
