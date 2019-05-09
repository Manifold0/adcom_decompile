// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

final class zzol implements zzv<Object>
{
    private final /* synthetic */ zzok zzbhr;
    
    zzol(final zzok zzbhr) {
        this.zzbhr = zzbhr;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        String s;
        while (true) {
            try {
                this.zzbhr.zzbhp = Long.parseLong(map.get("timestamp"));
                this.zzbhr.zzbho = map.get("id");
                s = map.get("asset_id");
                if (this.zzbhr.zzbhm == null) {
                    zzakb.zzck("Received unconfirmed click but UnconfirmedClickListener is null.");
                    return;
                }
            }
            catch (NumberFormatException ex2) {
                zzakb.e("Failed to call parse unconfirmedClickTimestamp.");
                continue;
            }
            break;
        }
        try {
            this.zzbhr.zzbhm.onUnconfirmedClickReceived(s);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
