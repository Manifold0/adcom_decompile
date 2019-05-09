// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.content.Intent;
import android.app.Activity;
import java.util.HashMap;
import android.view.View;
import android.widget.FrameLayout;
import android.support.annotation.Nullable;
import android.os.IBinder;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public class zzjr
{
    private final Object mLock;
    @GuardedBy("mLock")
    private zzld zzari;
    private final zzjh zzarj;
    private final zzjg zzark;
    private final zzme zzarl;
    private final zzrv zzarm;
    private final zzahi zzarn;
    private final zzaao zzaro;
    private final zzrw zzarp;
    
    public zzjr(final zzjh zzarj, final zzjg zzark, final zzme zzarl, final zzrv zzarm, final zzahi zzarn, final zzaao zzaro, final zzrw zzarp) {
        this.mLock = new Object();
        this.zzarj = zzarj;
        this.zzark = zzark;
        this.zzarl = zzarl;
        this.zzarm = zzarm;
        this.zzarn = zzarn;
        this.zzaro = zzaro;
        this.zzarp = zzarp;
    }
    
    @VisibleForTesting
    static <T> T zza(final Context context, final boolean b, final zza<T> zza) {
        final int n = 1;
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        int n2 = b2 ? 1 : 0;
        if (!b2) {
            zzkb.zzif();
            n2 = (b2 ? 1 : 0);
            if (!zzamu.zzbe(context)) {
                zzane.zzck("Google Play Services is not available");
                n2 = 1;
            }
        }
        zzkb.zzif();
        final int zzbg = zzamu.zzbg(context);
        zzkb.zzif();
        if (zzbg > zzamu.zzbf(context)) {
            n2 = n;
        }
        zznk.initialize(context);
        if (zzkb.zzik().zzd(zznk.zzber)) {
            n2 = 0;
        }
        T t;
        if (n2 != 0) {
            if ((t = zza.zzic()) == null) {
                t = zza.zzid();
            }
        }
        else if ((t = zza.zzid()) == null) {
            return zza.zzic();
        }
        return t;
    }
    
    private static void zza(final Context context, final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", s);
        zzkb.zzif().zza(context, null, "gmob-apps", bundle, true);
    }
    
    @Nullable
    private static zzld zzhz() {
        try {
            final Object instance = zzjr.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (!(instance instanceof IBinder)) {
                zzane.zzdk("ClientApi class is not an instance of IBinder");
                return null;
            }
            return zzle.asInterface((IBinder)instance);
        }
        catch (Exception ex) {
            zzane.zzc("Failed to instantiate ClientApi class.", ex);
            return null;
        }
    }
    
    @Nullable
    private final zzld zzia() {
        synchronized (this.mLock) {
            if (this.zzari == null) {
                this.zzari = zzhz();
            }
            return this.zzari;
        }
    }
    
    public final zzqa zza(final Context context, final FrameLayout frameLayout, final FrameLayout frameLayout2) {
        return zza(context, false, (zza<zzqa>)new zzjx(this, frameLayout, frameLayout2, context));
    }
    
    public final zzqf zza(final View view, final HashMap<String, View> hashMap, final HashMap<String, View> hashMap2) {
        return zza(view.getContext(), false, (zza<zzqf>)new zzjy(this, view, hashMap, hashMap2));
    }
    
    @Nullable
    public final zzaap zzb(final Activity activity) {
        boolean booleanExtra = false;
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            zzane.e("useClientJar flag not found in activity intent extras.");
        }
        else {
            booleanExtra = intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
        return zza((Context)activity, booleanExtra, (zza<zzaap>)new zzka(this, activity));
    }
    
    public final zzkn zzb(final Context context, final String s, final zzxn zzxn) {
        return zza(context, false, (zza<zzkn>)new zzjv(this, context, s, zzxn));
    }
    
    @VisibleForTesting
    abstract class zza<T>
    {
        @Nullable
        protected abstract T zza(final zzld p0) throws RemoteException;
        
        @Nullable
        protected abstract T zzib() throws RemoteException;
        
        @Nullable
        protected final T zzic() {
            final zzld zza = zzjr.this.zzia();
            if (zza == null) {
                zzane.zzdk("ClientApi class cannot be loaded.");
                return null;
            }
            try {
                return this.zza(zza);
            }
            catch (RemoteException ex) {
                zzane.zzc("Cannot invoke local loader using ClientApi class", (Throwable)ex);
                return null;
            }
        }
        
        @Nullable
        protected final T zzid() {
            try {
                return this.zzib();
            }
            catch (RemoteException ex) {
                zzane.zzc("Cannot invoke remote loader", (Throwable)ex);
                return null;
            }
        }
    }
}
