// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import android.os.RemoteException;
import android.os.Bundle;
import android.accounts.Account;
import android.os.IInterface;

public interface zze extends IInterface
{
    Bundle zza(final Account p0) throws RemoteException;
    
    Bundle zza(final Account p0, final String p1, final Bundle p2) throws RemoteException;
    
    Bundle zza(final String p0) throws RemoteException;
    
    Bundle zza(final String p0, final Bundle p1) throws RemoteException;
    
    AccountChangeEventsResponse zza(final AccountChangeEventsRequest p0) throws RemoteException;
}
