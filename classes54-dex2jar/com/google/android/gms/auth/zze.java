// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import android.os.RemoteException;
import com.google.android.gms.common.logging.Logger;
import java.io.IOException;
import com.google.android.gms.internal.auth.zzay;
import android.content.Intent;
import com.google.android.gms.internal.auth.zzf;
import android.os.IBinder;
import android.accounts.Account;
import android.os.Bundle;

final class zze implements zzj<TokenData>
{
    private final /* synthetic */ Bundle val$options;
    private final /* synthetic */ Account zzo;
    private final /* synthetic */ String zzp;
    
    zze(final Account zzo, final String zzp, final Bundle val$options) {
        this.zzo = zzo;
        this.zzp = zzp;
        this.val$options = val$options;
    }
}
