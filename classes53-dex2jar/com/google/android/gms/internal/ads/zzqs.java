// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzqs extends IInterface
{
    void destroy() throws RemoteException;
    
    List<String> getAvailableAssetNames() throws RemoteException;
    
    String getCustomTemplateId() throws RemoteException;
    
    zzlo getVideoController() throws RemoteException;
    
    void performClick(final String p0) throws RemoteException;
    
    void recordImpression() throws RemoteException;
    
    String zzao(final String p0) throws RemoteException;
    
    zzpw zzap(final String p0) throws RemoteException;
    
    boolean zzh(final IObjectWrapper p0) throws RemoteException;
    
    IObjectWrapper zzka() throws RemoteException;
    
    IObjectWrapper zzkh() throws RemoteException;
}
