// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzci;
import android.os.AsyncTask;

final class zzbt extends AsyncTask<Void, Void, String>
{
    private final /* synthetic */ zzbp zzaba;
    
    private zzbt(final zzbp zzaba) {
        this.zzaba = zzaba;
    }
    
    private final String zza(Void... zzaba) {
        try {
            zzaba = (ExecutionException)this.zzaba;
            ((zzbp)zzaba).zzaay = this.zzaba.zzaav.get((long)zzkb.zzik().zzd(zznk.zzbdb), TimeUnit.MILLISECONDS);
            return this.zzaba.zzea();
        }
        catch (InterruptedException ex) {}
        catch (ExecutionException zzaba) {
            goto Label_0056;
        }
        catch (TimeoutException zzaba) {
            goto Label_0056;
        }
    }
}
