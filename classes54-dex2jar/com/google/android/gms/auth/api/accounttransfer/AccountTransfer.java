// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.internal.auth.zzt;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.internal.auth.zzu;
import com.google.android.gms.common.api.Api$ClientKey;

public final class AccountTransfer
{
    public static final String ACTION_ACCOUNT_EXPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_EXPORT_DATA_AVAILABLE";
    public static final String ACTION_ACCOUNT_IMPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_IMPORT_DATA_AVAILABLE";
    public static final String ACTION_START_ACCOUNT_EXPORT = "com.google.android.gms.auth.START_ACCOUNT_EXPORT";
    public static final String KEY_EXTRA_ACCOUNT_TYPE = "key_extra_account_type";
    private static final Api$ClientKey<zzu> zzaj;
    private static final Api$AbstractClientBuilder<zzu, zzn> zzak;
    private static final Api<zzn> zzal;
    @Deprecated
    private static final zzb zzam;
    private static final zzq zzan;
    
    static {
        zzaj = new Api$ClientKey();
        zzak = new zza();
        zzal = new Api("AccountTransfer.ACCOUNT_TRANSFER_API", (Api$AbstractClientBuilder)AccountTransfer.zzak, (Api$ClientKey)AccountTransfer.zzaj);
        zzam = (zzb)new zzt();
        zzan = (zzq)new zzt();
    }
    
    private AccountTransfer() {
    }
    
    public static AccountTransferClient getAccountTransferClient(@NonNull final Activity activity) {
        return new AccountTransferClient(activity);
    }
    
    public static AccountTransferClient getAccountTransferClient(@NonNull final Context context) {
        return new AccountTransferClient(context);
    }
}
