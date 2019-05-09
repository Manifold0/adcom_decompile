// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Context;
import android.content.Intent;
import java.io.IOException;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import android.os.PowerManager;
import android.os.PowerManager$WakeLock;

final class zzay implements Runnable
{
    private final zzan zzan;
    private final zzba zzaq;
    private final long zzdh;
    private final PowerManager$WakeLock zzdi;
    private final FirebaseInstanceId zzdj;
    
    @VisibleForTesting
    zzay(final FirebaseInstanceId zzdj, final zzan zzan, final zzba zzaq, final long zzdh) {
        this.zzdj = zzdj;
        this.zzan = zzan;
        this.zzaq = zzaq;
        this.zzdh = zzdh;
        (this.zzdi = ((PowerManager)this.getContext().getSystemService("power")).newWakeLock(1, "fiid-sync")).setReferenceCounted(false);
    }
    
    @VisibleForTesting
    private final boolean zzam() {
        try {
            if (!this.zzdj.zzo()) {
                this.zzdj.zzp();
            }
            return true;
        }
        catch (IOException ex) {
            final String value = String.valueOf(ex.getMessage());
            String concat;
            if (value.length() != 0) {
                concat = "Build channel failed: ".concat(value);
            }
            else {
                concat = new String("Build channel failed: ");
            }
            Log.e("FirebaseInstanceId", concat);
            return false;
        }
    }
    
    @VisibleForTesting
    private final boolean zzan() {
        final zzax zzj = this.zzdj.zzj();
        if (zzj == null || zzj.zzj(this.zzan.zzad())) {
            try {
                final String zzk = this.zzdj.zzk();
                if (zzk == null) {
                    Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                    return false;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    Log.d("FirebaseInstanceId", "Token successfully retrieved");
                }
                if (zzj == null || (zzj != null && !zzk.equals(zzj.zzbq))) {
                    final Context context = this.getContext();
                    final Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
                    intent.putExtra("token", zzk);
                    zzav.zzc(context, intent);
                    zzav.zzb(context, new Intent("com.google.firebase.iid.TOKEN_REFRESH"));
                    return true;
                }
            }
            catch (IOException ex) {}
            catch (SecurityException zzk) {
                goto Label_0130;
            }
        }
        return true;
    }
    
    final Context getContext() {
        return this.zzdj.zzh().getApplicationContext();
    }
    
    @Override
    public final void run() {
        this.zzdi.acquire();
        try {
            this.zzdj.zza(true);
            if (!this.zzdj.zzn()) {
                this.zzdj.zza(false);
                return;
            }
            if (!this.zzao()) {
                new zzaz(this).zzap();
                return;
            }
            if (this.zzam() && this.zzan() && this.zzaq.zzc(this.zzdj)) {
                this.zzdj.zza(false);
            }
            else {
                this.zzdj.zza(this.zzdh);
            }
        }
        finally {
            this.zzdi.release();
        }
    }
    
    final boolean zzao() {
        final ConnectivityManager connectivityManager = (ConnectivityManager)this.getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        else {
            activeNetworkInfo = null;
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
