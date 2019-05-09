// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import android.accounts.Account;

final class zzk extends zzn
{
    private final /* synthetic */ zzj zzaf;
    
    zzk(final zzj zzaf) {
        this.zzaf = zzaf;
    }
    
    @Override
    public final void zzc(final Account account) {
        final zzj zzaf = this.zzaf;
        Status status;
        if (account != null) {
            status = Status.RESULT_SUCCESS;
        }
        else {
            status = zzh.zzad;
        }
        zzaf.setResult((Result)new zzo(status, account));
    }
}
