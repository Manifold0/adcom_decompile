package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.FrameLayout;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public class zzjr {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private zzld zzari;
    private final zzjh zzarj;
    private final zzjg zzark;
    private final zzme zzarl;
    private final zzrv zzarm;
    private final zzahi zzarn;
    private final zzaao zzaro;
    private final zzrw zzarp;

    @VisibleForTesting
    abstract class zza<T> {
        private final /* synthetic */ zzjr zzart;

        zza(zzjr zzjr) {
            this.zzart = zzjr;
        }

        @Nullable
        protected abstract T zza(zzld zzld) throws RemoteException;

        @Nullable
        protected abstract T zzib() throws RemoteException;

        @Nullable
        protected final T zzic() {
            T t = null;
            zzld zza = this.zzart.zzia();
            if (zza == null) {
                zzane.zzdk("ClientApi class cannot be loaded.");
            } else {
                try {
                    t = zza(zza);
                } catch (Throwable e) {
                    zzane.zzc("Cannot invoke local loader using ClientApi class", e);
                }
            }
            return t;
        }

        @Nullable
        protected final T zzid() {
            try {
                return zzib();
            } catch (Throwable e) {
                zzane.zzc("Cannot invoke remote loader", e);
                return null;
            }
        }
    }

    public zzjr(zzjh zzjh, zzjg zzjg, zzme zzme, zzrv zzrv, zzahi zzahi, zzaao zzaao, zzrw zzrw) {
        this.zzarj = zzjh;
        this.zzark = zzjg;
        this.zzarl = zzme;
        this.zzarm = zzrv;
        this.zzarn = zzahi;
        this.zzaro = zzaao;
        this.zzarp = zzrw;
    }

    @VisibleForTesting
    static <T> T zza(Context context, boolean z, zza<T> zza) {
        Object obj = 1;
        Object obj2 = z ? 1 : null;
        if (obj2 == null) {
            zzkb.zzif();
            if (!zzamu.zzbe(context)) {
                zzane.zzck("Google Play Services is not available");
                obj2 = 1;
            }
        }
        zzkb.zzif();
        int zzbg = zzamu.zzbg(context);
        zzkb.zzif();
        if (zzbg <= zzamu.zzbf(context)) {
            obj = obj2;
        }
        zznk.initialize(context);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzber)).booleanValue()) {
            obj = null;
        }
        T zzic;
        if (obj != null) {
            zzic = zza.zzic();
            return zzic == null ? zza.zzid() : zzic;
        } else {
            zzic = zza.zzid();
            return zzic == null ? zza.zzic() : zzic;
        }
    }

    private static void zza(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", str);
        zzkb.zzif().zza(context, null, "gmob-apps", bundle, true);
    }

    @Nullable
    private static zzld zzhz() {
        try {
            Object newInstance = zzjr.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zzle.asInterface((IBinder) newInstance);
            }
            zzane.zzdk("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Throwable e) {
            zzane.zzc("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    @Nullable
    private final zzld zzia() {
        zzld zzld;
        synchronized (this.mLock) {
            if (this.zzari == null) {
                this.zzari = zzhz();
            }
            zzld = this.zzari;
        }
        return zzld;
    }

    public final zzqa zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzqa) zza(context, false, new zzjx(this, frameLayout, frameLayout2, context));
    }

    public final zzqf zza(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        return (zzqf) zza(view.getContext(), false, new zzjy(this, view, hashMap, hashMap2));
    }

    @Nullable
    public final zzaap zzb(Activity activity) {
        boolean z = false;
        String str = "com.google.android.gms.ads.internal.overlay.useClientJar";
        Intent intent = activity.getIntent();
        if (intent.hasExtra(str)) {
            z = intent.getBooleanExtra(str, false);
        } else {
            zzane.m1665e("useClientJar flag not found in activity intent extras.");
        }
        return (zzaap) zza((Context) activity, z, new zzka(this, activity));
    }

    public final zzkn zzb(Context context, String str, zzxn zzxn) {
        return (zzkn) zza(context, false, new zzjv(this, context, str, zzxn));
    }
}
