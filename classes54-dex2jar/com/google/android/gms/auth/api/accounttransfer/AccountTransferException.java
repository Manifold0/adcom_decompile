// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ApiException;

public class AccountTransferException extends ApiException
{
    public AccountTransferException(@NonNull final Status status) {
        super(status);
    }
}
