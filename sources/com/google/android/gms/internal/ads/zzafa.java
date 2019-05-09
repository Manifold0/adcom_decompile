package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.google.android.gms.ads.internal.gmsg.zzaa;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.unity.purchasing.googleplay.Consts;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafa extends zzajx {
    private static final Object sLock = new Object();
    @VisibleForTesting
    private static final long zzcgc = TimeUnit.SECONDS.toMillis(10);
    @GuardedBy("sLock")
    @VisibleForTesting
    private static boolean zzcgd = false;
    private static zzvf zzcge = null;
    private static HttpClient zzcgf = null;
    private static zzaa zzcgg = null;
    private static zzv<Object> zzcgh = null;
    private final Context mContext;
    private final Object zzbzh = new Object();
    private final zzadj zzccf;
    private final zzaeg zzccg;
    private zzhx zzcci;
    private zzvs zzcgi;

    public zzafa(Context context, zzaeg zzaeg, zzadj zzadj, zzhx zzhx) {
        super(true);
        this.zzccf = zzadj;
        this.mContext = context;
        this.zzccg = zzaeg;
        this.zzcci = zzhx;
        synchronized (sLock) {
            if (!zzcgd) {
                zzcgg = new zzaa();
                zzcgf = new HttpClient(context.getApplicationContext(), zzaeg.zzacr);
                zzcgh = new zzafi();
                zzcge = new zzvf(this.mContext.getApplicationContext(), this.zzccg.zzacr, (String) zzkb.zzik().zzd(zznk.zzaub), new zzafh(), new zzafg());
                zzcgd = true;
            }
        }
    }

    private final JSONObject zza(zzaef zzaef, String str) {
        Throwable e;
        Object obj;
        Info advertisingIdInfo;
        Map hashMap;
        JSONObject jSONObject = null;
        Bundle bundle = zzaef.zzccv.extras.getBundle("sdk_less_server_data");
        if (bundle != null) {
            zzaga zzaga;
            try {
                zzaga = (zzaga) zzbv.zzev().zzq(this.mContext).get();
            } catch (Throwable e2) {
                zzakb.zzc("Error grabbing device info: ", e2);
                obj = jSONObject;
            }
            Context context = this.mContext;
            zzafl zzafl = new zzafl();
            zzafl.zzcgs = zzaef;
            zzafl.zzcgt = zzaga;
            JSONObject zza = zzafs.zza(context, zzafl);
            if (zza != null) {
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
                } catch (IOException e3) {
                    e2 = e3;
                    zzakb.zzc("Cannot get advertising id info", e2);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put(Consts.INAPP_REQUEST_ID, str);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzbv.zzek().zzn(hashMap);
                    return jSONObject;
                } catch (IllegalStateException e4) {
                    e2 = e4;
                    zzakb.zzc("Cannot get advertising id info", e2);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put(Consts.INAPP_REQUEST_ID, str);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzbv.zzek().zzn(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesNotAvailableException e5) {
                    e2 = e5;
                    zzakb.zzc("Cannot get advertising id info", e2);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put(Consts.INAPP_REQUEST_ID, str);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzbv.zzek().zzn(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesRepairableException e6) {
                    e2 = e6;
                    zzakb.zzc("Cannot get advertising id info", e2);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put(Consts.INAPP_REQUEST_ID, str);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzbv.zzek().zzn(hashMap);
                    return jSONObject;
                }
                hashMap = new HashMap();
                hashMap.put(Consts.INAPP_REQUEST_ID, str);
                hashMap.put("request_param", zza);
                hashMap.put("data", bundle);
                if (advertisingIdInfo != null) {
                    hashMap.put("adid", advertisingIdInfo.getId());
                    if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                    }
                    hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                }
                try {
                    jSONObject = zzbv.zzek().zzn(hashMap);
                } catch (JSONException e7) {
                }
            }
        }
        return jSONObject;
    }

    protected static void zzb(zzuu zzuu) {
        zzuu.zza("/loadAd", zzcgg);
        zzuu.zza("/fetchHttpRequest", zzcgf);
        zzuu.zza("/invalidRequest", zzcgh);
    }

    private final zzaej zzc(zzaef zzaef) {
        zzbv.zzek();
        String zzrh = zzakk.zzrh();
        JSONObject zza = zza(zzaef, zzrh);
        if (zza == null) {
            return new zzaej(0);
        }
        long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        Future zzas = zzcgg.zzas(zzrh);
        zzamu.zzsy.post(new zzafc(this, zza, zzrh));
        try {
            JSONObject jSONObject = (JSONObject) zzas.get(zzcgc - (zzbv.zzer().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new zzaej(-1);
            }
            zzaej zza2 = zzafs.zza(this.mContext, zzaef, jSONObject.toString());
            return (zza2.errorCode == -3 || !TextUtils.isEmpty(zza2.zzceo)) ? zza2 : new zzaej(3);
        } catch (CancellationException e) {
            return new zzaej(-1);
        } catch (InterruptedException e2) {
            return new zzaej(-1);
        } catch (TimeoutException e3) {
            return new zzaej(2);
        } catch (ExecutionException e4) {
            return new zzaej(0);
        }
    }

    protected static void zzc(zzuu zzuu) {
        zzuu.zzb("/loadAd", zzcgg);
        zzuu.zzb("/fetchHttpRequest", zzcgf);
        zzuu.zzb("/invalidRequest", zzcgh);
    }

    public final void onStop() {
        synchronized (this.zzbzh) {
            zzamu.zzsy.post(new zzaff(this));
        }
    }

    public final void zzdn() {
        zzakb.zzck("SdkLessAdLoaderBackgroundTask started.");
        String zzab = zzbv.zzfh().zzab(this.mContext);
        zzaef zzaef = new zzaef(this.zzccg, -1, zzbv.zzfh().zzz(this.mContext), zzbv.zzfh().zzaa(this.mContext), zzab);
        zzbv.zzfh().zzg(this.mContext, zzab);
        zzaej zzc = zzc(zzaef);
        zzaef zzaef2 = zzaef;
        zzwy zzwy = null;
        zzjn zzjn = null;
        zzamu.zzsy.post(new zzafb(this, new zzaji(zzaef2, zzc, zzwy, zzjn, zzc.errorCode, zzbv.zzer().elapsedRealtime(), zzc.zzceu, null, this.zzcci)));
    }
}
