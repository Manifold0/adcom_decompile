// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.RemoteException;
import android.content.Intent;
import android.os.IInterface;

public interface zzaap extends IInterface
{
    void onActivityResult(final int p0, final int p1, final Intent p2) throws RemoteException;
    
    void onBackPressed() throws RemoteException;
    
    void onCreate(final Bundle p0) throws RemoteException;
    
    void onDestroy() throws RemoteException;
    
    void onPause() throws RemoteException;
    
    void onRestart() throws RemoteException;
    
    void onResume() throws RemoteException;
    
    void onSaveInstanceState(final Bundle p0) throws RemoteException;
    
    void onStart() throws RemoteException;
    
    void onStop() throws RemoteException;
    
    void zzax() throws RemoteException;
    
    boolean zznj() throws RemoteException;
    
    void zzo(final IObjectWrapper p0) throws RemoteException;
}
