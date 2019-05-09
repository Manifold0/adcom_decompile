// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.account.WorkAccountApi;

final class zzo implements AddAccountResult
{
    private final Status mStatus;
    private final Account zzk;
    
    public zzo(final Status mStatus, final Account zzk) {
        this.mStatus = mStatus;
        this.zzk = zzk;
    }
    
    @Override
    public final Account getAccount() {
        return this.zzk;
    }
    
    public final Status getStatus() {
        return this.mStatus;
    }
}
