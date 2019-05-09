// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;
import javax.annotation.Nullable;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.Uri$Builder;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzbv;
import android.text.TextUtils;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzalk
{
    private final Object mLock;
    @GuardedBy("mLock")
    private String zzcsm;
    @GuardedBy("mLock")
    private String zzcsn;
    @GuardedBy("mLock")
    private boolean zzcso;
    @VisibleForTesting
    private String zzcsp;
    
    public zzalk() {
        this.mLock = new Object();
        this.zzcsm = "";
        this.zzcsn = "";
        this.zzcso = false;
        this.zzcsp = "";
    }
    
    @VisibleForTesting
    private final void zza(final Context context, final String s, final boolean b, final boolean b2) {
        if (!(context instanceof Activity)) {
            zzakb.zzdj("Can not create dialog without Activity Context");
            return;
        }
        zzakk.zzcrm.post((Runnable)new zzall(this, context, s, b, b2));
    }
    
    private final String zzaz(final Context context) {
        synchronized (this.mLock) {
            if (TextUtils.isEmpty((CharSequence)this.zzcsm)) {
                zzbv.zzek();
                this.zzcsm = zzakk.zzn(context, "debug_signals_id.txt");
                if (TextUtils.isEmpty((CharSequence)this.zzcsm)) {
                    zzbv.zzek();
                    this.zzcsm = zzakk.zzrh();
                    zzbv.zzek();
                    zzakk.zze(context, "debug_signals_id.txt", this.zzcsm);
                }
            }
            return this.zzcsm;
        }
    }
    
    private final Uri zzc(final Context context, final String s, final String s2, final String s3) {
        final Uri$Builder buildUpon = Uri.parse(s).buildUpon();
        buildUpon.appendQueryParameter("linkedDeviceId", this.zzaz(context));
        buildUpon.appendQueryParameter("adSlotPath", s2);
        buildUpon.appendQueryParameter("afmaVersion", s3);
        return buildUpon.build();
    }
    
    @VisibleForTesting
    private final boolean zzh(final Context context, String mLock, final String s) {
        final String zzj = zzj(context, this.zzc(context, (String)zzkb.zzik().zzd(zznk.zzbeg), mLock, s).toString(), s);
        if (TextUtils.isEmpty((CharSequence)zzj)) {
            zzakb.zzck("Not linked for in app preview.");
            return false;
        }
        final String trim = zzj.trim();
        String optString;
        try {
            mLock = (String)new JSONObject(trim);
            optString = ((JSONObject)mLock).optString("gct");
            this.zzcsp = ((JSONObject)mLock).optString("status");
            mLock = (String)this.mLock;
            // monitorenter(mLock)
            final zzalk zzalk = this;
            final String s2 = optString;
            zzalk.zzcsn = s2;
            return true;
        }
        catch (JSONException ex) {
            zzakb.zzc("Fail to get in app preview response json.", (Throwable)ex);
            return false;
        }
        try {
            final zzalk zzalk = this;
            final String s2 = optString;
            zzalk.zzcsn = s2;
            return true;
        }
        finally {
        }
        // monitorexit(mLock)
    }
    
    @VisibleForTesting
    private final boolean zzi(final Context context, final String s, final String s2) {
        final String zzj = zzj(context, this.zzc(context, (String)zzkb.zzik().zzd(zznk.zzbeh), s, s2).toString(), s2);
        if (TextUtils.isEmpty((CharSequence)zzj)) {
            zzakb.zzck("Not linked for debug signals.");
            return false;
        }
        final String trim = zzj.trim();
        try {
            final boolean equals = "1".equals(new JSONObject(trim).optString("debug_mode"));
            synchronized (this.mLock) {
                return this.zzcso = equals;
            }
        }
        catch (JSONException ex) {
            zzakb.zzc("Fail to get debug mode response json.", (Throwable)ex);
            return false;
        }
    }
    
    @VisibleForTesting
    private static String zzj(final Context context, final String s, String zzc) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("User-Agent", zzbv.zzek().zzm(context, zzc));
        zzc = (String)new zzalt(context).zzc(s, hashMap);
        try {
            return ((Future<String>)zzc).get((long)zzkb.zzik().zzd(zznk.zzbej), TimeUnit.MILLISECONDS);
        }
        catch (TimeoutException ex) {
            final String value = String.valueOf(s);
            if (value.length() == 0) {
                goto Label_0107;
            }
            zzakb.zzb("Timeout while retriving a response from: ".concat(value), (Throwable)ex);
            ((Future)zzc).cancel(true);
        }
        catch (InterruptedException ex2) {
            final String value2 = String.valueOf(s);
            String concat;
            if (value2.length() != 0) {
                concat = "Interrupted while retriving a response from: ".concat(value2);
            }
            else {
                concat = new String("Interrupted while retriving a response from: ");
            }
            zzakb.zzb(concat, (Throwable)ex2);
            ((Future)zzc).cancel(true);
            goto Label_0105;
        }
        catch (Exception ex3) {
            final String value3 = String.valueOf(s);
            String concat2;
            if (value3.length() != 0) {
                concat2 = "Error retriving a response from: ".concat(value3);
            }
            else {
                concat2 = new String("Error retriving a response from: ");
            }
            zzakb.zzb(concat2, (Throwable)ex3);
            goto Label_0105;
        }
    }
    
    private final void zzk(final Context context, final String s, final String s2) {
        zzbv.zzek();
        zzakk.zza(context, this.zzc(context, (String)zzkb.zzik().zzd(zznk.zzbef), s, s2));
    }
    
    public final void zza(final Context context, final String s, final String s2, @Nullable final String s3) {
        final boolean zzrx = this.zzrx();
        if (this.zzi(context, s, s2)) {
            if (!zzrx && !TextUtils.isEmpty((CharSequence)s3)) {
                this.zzb(context, s2, s3, s);
            }
            zzakb.zzck("Device is linked for debug signals.");
            this.zza(context, "The device is successfully linked for troubleshooting.", false, true);
            return;
        }
        this.zzk(context, s, s2);
    }
    
    public final void zzb(final Context context, final String s, final String s2, final String s3) {
        final Uri$Builder buildUpon = this.zzc(context, (String)zzkb.zzik().zzd(zznk.zzbei), s3, s).buildUpon();
        buildUpon.appendQueryParameter("debugData", s2);
        zzbv.zzek();
        zzakk.zzd(context, s, buildUpon.build().toString());
    }
    
    public final void zzg(final Context context, final String s, final String s2) {
        if (!this.zzh(context, s, s2)) {
            this.zza(context, "In-app preview failed to load because of a system error. Please try again later.", true, true);
        }
        else {
            if ("2".equals(this.zzcsp)) {
                zzakb.zzck("Creative is not pushed for this device.");
                this.zza(context, "There was no creative pushed from DFP to the device.", false, false);
                return;
            }
            if ("1".equals(this.zzcsp)) {
                zzakb.zzck("The app is not linked for creative preview.");
                this.zzk(context, s, s2);
                return;
            }
            if ("0".equals(this.zzcsp)) {
                zzakb.zzck("Device is linked for in app preview.");
                this.zza(context, "The device is successfully linked for creative preview.", false, true);
            }
        }
    }
    
    public final String zzrw() {
        synchronized (this.mLock) {
            return this.zzcsn;
        }
    }
    
    public final boolean zzrx() {
        synchronized (this.mLock) {
            return this.zzcso;
        }
    }
}
