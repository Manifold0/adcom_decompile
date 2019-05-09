// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;
import android.os.RemoteException;
import android.view.View;
import java.lang.ref.WeakReference;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.ads.internal.gmsg.zzv;
import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import android.view.View$OnClickListener;

@zzadh
@ParametersAreNonnullByDefault
public final class zzok implements View$OnClickListener
{
    private final zzacm zzaad;
    @Nullable
    private zzro zzbhm;
    @Nullable
    private zzv zzbhn;
    @Nullable
    @VisibleForTesting
    String zzbho;
    @Nullable
    @VisibleForTesting
    Long zzbhp;
    @Nullable
    @VisibleForTesting
    WeakReference<View> zzbhq;
    
    public zzok(final zzacm zzaad) {
        this.zzaad = zzaad;
    }
    
    private final void zzjx() {
        this.zzbho = null;
        this.zzbhp = null;
        if (this.zzbhq != null) {
            final View view = this.zzbhq.get();
            this.zzbhq = null;
            if (view != null) {
                view.setClickable(false);
                view.setOnClickListener((View$OnClickListener)null);
            }
        }
    }
    
    public final void cancelUnconfirmedClick() {
        if (this.zzbhm == null || this.zzbhp == null) {
            return;
        }
        this.zzjx();
        try {
            this.zzbhm.onUnconfirmedClickCancelled();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final void onClick(final View view) {
        if (this.zzbhq == null || this.zzbhq.get() != view) {
            return;
        }
        if (this.zzbho != null && this.zzbhp != null) {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id", (Object)this.zzbho);
                jsonObject.put("time_interval", zzbv.zzer().currentTimeMillis() - this.zzbhp);
                jsonObject.put("messageType", (Object)"onePointFiveClick");
                this.zzaad.zza("sendMessageToNativeJs", jsonObject);
            }
            catch (JSONException ex) {
                zzakb.zzb("Unable to dispatch sendMessageToNativeJs event", (Throwable)ex);
            }
        }
        this.zzjx();
    }
    
    public final void zza(final zzro zzbhm) {
        this.zzbhm = zzbhm;
        if (this.zzbhn != null) {
            this.zzaad.zzb("/unconfirmedClick", this.zzbhn);
        }
        this.zzbhn = new zzol(this);
        this.zzaad.zza("/unconfirmedClick", this.zzbhn);
    }
    
    @Nullable
    public final zzro zzjw() {
        return this.zzbhm;
    }
}
