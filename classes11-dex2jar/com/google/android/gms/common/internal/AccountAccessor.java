// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import android.os.RemoteException;
import android.util.Log;
import android.os.Binder;
import android.accounts.Account;

public class AccountAccessor extends Stub
{
    @KeepForSdk
    public static Account getAccountBinderSafe(final IAccountAccessor accountAccessor) {
        Account account = null;
        if (accountAccessor == null) {
            return account;
        }
        final long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            account = accountAccessor.getAccount();
            return account;
        }
        catch (RemoteException ex) {
            Log.w("AccountAccessor", "Remote account accessor probably died");
            return null;
        }
        finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
    
    public boolean equals(final Object o) {
        throw new NoSuchMethodError();
    }
    
    @Override
    public final Account getAccount() {
        throw new NoSuchMethodError();
    }
}
