package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.games.GamesStatusCodes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzabv implements Callable<zzajh> {
    @VisibleForTesting
    private static long zzbzx = 10;
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock = new Object();
    private final zzacm zzaad;
    private final zzci zzbjc;
    private final zzaji zzbze;
    private final zzalt zzbzy;
    private final zzbc zzbzz;
    private boolean zzcaa;
    private List<String> zzcab;
    private JSONObject zzcac;
    private String zzcad;
    @Nullable
    private String zzcae;
    private final zznx zzvr;

    public zzabv(Context context, zzbc zzbc, zzalt zzalt, zzci zzci, zzaji zzaji, zznx zznx) {
        this.mContext = context;
        this.zzbzz = zzbc;
        this.zzbzy = zzalt;
        this.zzbze = zzaji;
        this.zzbjc = zzci;
        this.zzvr = zznx;
        this.zzaad = zzbc.zzdr();
        this.zzcaa = false;
        this.mErrorCode = -2;
        this.zzcab = null;
        this.zzcad = null;
        this.zzcae = null;
    }

    private final zzajh zza(zzpb zzpb, boolean z) {
        int i;
        synchronized (this.mLock) {
            i = this.mErrorCode;
            if (zzpb == null && this.mErrorCode == -2) {
                i = 0;
            }
        }
        return new zzajh(this.zzbze.zzcgs.zzccv, null, this.zzbze.zzcos.zzbsn, i, this.zzbze.zzcos.zzbso, this.zzcab, this.zzbze.zzcos.orientation, this.zzbze.zzcos.zzbsu, this.zzbze.zzcgs.zzccy, false, null, null, null, null, null, 0, this.zzbze.zzacv, this.zzbze.zzcos.zzcep, this.zzbze.zzcoh, this.zzbze.zzcoi, this.zzbze.zzcos.zzcev, this.zzcac, i != -2 ? null : zzpb, null, null, null, this.zzbze.zzcos.zzcfh, this.zzbze.zzcos.zzcfi, null, this.zzbze.zzcos.zzbsr, this.zzcad, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, z, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzcae);
    }

    private final zzanz<zzon> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString("url") : jSONObject.optString("url");
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? zzano.zzi(new zzon(null, Uri.parse(string), optDouble)) : this.zzbzy.zza(string, new zzacb(this, z, optDouble, optBoolean, string));
        } else {
            zzd(0, z);
            return zzano.zzi(null);
        }
    }

    private final void zzab(int i) {
        synchronized (this.mLock) {
            this.zzcaa = true;
            this.mErrorCode = i;
        }
    }

    private static zzaqw zzb(zzanz<zzaqw> zzanz) {
        Throwable e;
        try {
            return (zzaqw) zzanz.get((long) ((Integer) zzkb.zzik().zzd(zznk.zzbby)).intValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e2) {
            zzane.zzc("", e2);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e3) {
            e2 = e3;
            zzane.zzc("", e2);
        } catch (TimeoutException e4) {
            e2 = e4;
            zzane.zzc("", e2);
        } catch (CancellationException e5) {
            e2 = e5;
            zzane.zzc("", e2);
        }
        return null;
    }

    private static Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException e) {
            return null;
        }
    }

    static zzaqw zzc(zzanz<zzaqw> zzanz) {
        Throwable e;
        try {
            return (zzaqw) zzanz.get((long) ((Integer) zzkb.zzik().zzd(zznk.zzbbx)).intValue(), TimeUnit.SECONDS);
        } catch (Throwable e2) {
            zzakb.zzc("InterruptedException occurred while waiting for video to load", e2);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e3) {
            e2 = e3;
            zzakb.zzc("Exception occurred while waiting for video to load", e2);
        } catch (TimeoutException e4) {
            e2 = e4;
            zzakb.zzc("Exception occurred while waiting for video to load", e2);
        } catch (CancellationException e5) {
            e2 = e5;
            zzakb.zzc("Exception occurred while waiting for video to load", e2);
        }
        return null;
    }

    private final void zzc(zzqs zzqs, String str) {
        try {
            zzrc zzr = this.zzbzz.zzr(zzqs.getCustomTemplateId());
            if (zzr != null) {
                zzr.zzb(zzqs, str);
            }
        } catch (Throwable e) {
            zzakb.zzc(new StringBuilder(String.valueOf(str).length() + 40).append("Failed to call onCustomClick for asset ").append(str).append(".").toString(), e);
        }
    }

    private static <V> List<V> zzk(List<zzanz<V>> list) throws ExecutionException, InterruptedException {
        List<V> arrayList = new ArrayList();
        for (zzanz zzanz : list) {
            Object obj = zzanz.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzajh zznw() {
        /*
        r15 = this;
        r12 = 0;
        r11 = 0;
        r2 = r15.zzbzz;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r10 = r2.getUuid();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r15.zznx();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 != 0) goto L_0x00b3;
    L_0x000e:
        r3 = new org.json.JSONObject;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r15.zzbze;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzcos;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzceo;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3.<init>(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = new org.json.JSONObject;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r15.zzbze;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r4.zzcos;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r4.zzceo;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2.<init>(r4);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r2.length();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r4 == 0) goto L_0x003f;
    L_0x002a:
        r4 = "ads";
        r2 = r2.optJSONArray(r4);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 == 0) goto L_0x00b1;
    L_0x0032:
        r4 = 0;
        r2 = r2.optJSONObject(r4);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
    L_0x0037:
        if (r2 == 0) goto L_0x003f;
    L_0x0039:
        r2 = r2.length();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 != 0) goto L_0x0043;
    L_0x003f:
        r2 = 3;
        r15.zzab(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
    L_0x0043:
        r2 = r15.zzaad;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzh(r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = zzbzx;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.get(r4, r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = (org.json.JSONObject) r2;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = "success";
        r4 = 0;
        r3 = r2.optBoolean(r3, r4);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r3 == 0) goto L_0x00b3;
    L_0x005c:
        r3 = "json";
        r2 = r2.getJSONObject(r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = "ads";
        r2 = r2.optJSONArray(r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = 0;
        r7 = r2.getJSONObject(r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
    L_0x006d:
        r2 = "enable_omid";
        r14 = r7.optBoolean(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r14 != 0) goto L_0x00b5;
    L_0x0075:
        r2 = 0;
        r2 = com.google.android.gms.internal.ads.zzano.zzi(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r13 = r2;
    L_0x007b:
        r2 = r15.zznx();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 != 0) goto L_0x0083;
    L_0x0081:
        if (r7 != 0) goto L_0x00e8;
    L_0x0083:
        r4 = r11;
    L_0x0084:
        r2 = r15.zznx();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 != 0) goto L_0x008e;
    L_0x008a:
        if (r4 == 0) goto L_0x008e;
    L_0x008c:
        if (r7 != 0) goto L_0x01a7;
    L_0x008e:
        r3 = r11;
    L_0x008f:
        r2 = r3 instanceof com.google.android.gms.internal.ads.zzos;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 == 0) goto L_0x00a3;
    L_0x0093:
        r0 = r3;
        r0 = (com.google.android.gms.internal.ads.zzos) r0;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r0;
        r4 = new com.google.android.gms.internal.ads.zzabz;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4.<init>(r15, r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r15.zzaad;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5 = "/nativeAdCustomClick";
        r2.zza(r5, r4);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
    L_0x00a3:
        r2 = r15.zza(r3, r14);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = r15.zzbzz;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = zzb(r13);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3.zzg(r4);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
    L_0x00b0:
        return r2;
    L_0x00b1:
        r2 = r11;
        goto L_0x0037;
    L_0x00b3:
        r7 = r11;
        goto L_0x006d;
    L_0x00b5:
        r2 = "omid_settings";
        r2 = r7.optJSONObject(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 != 0) goto L_0x00c4;
    L_0x00bd:
        r2 = 0;
        r2 = com.google.android.gms.internal.ads.zzano.zzi(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r13 = r2;
        goto L_0x007b;
    L_0x00c4:
        r3 = "omid_html";
        r3 = r2.optString(r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = android.text.TextUtils.isEmpty(r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 == 0) goto L_0x00d7;
    L_0x00d0:
        r2 = 0;
        r2 = com.google.android.gms.internal.ads.zzano.zzi(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r13 = r2;
        goto L_0x007b;
    L_0x00d7:
        r2 = new com.google.android.gms.internal.ads.zzaoj;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2.<init>();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = com.google.android.gms.internal.ads.zzaoe.zzcvy;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5 = new com.google.android.gms.internal.ads.zzabw;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5.<init>(r15, r2, r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4.execute(r5);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r13 = r2;
        goto L_0x007b;
    L_0x00e8:
        r2 = "template_id";
        r5 = r7.getString(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r15.zzbze;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzcgs;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzadj;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 == 0) goto L_0x0124;
    L_0x00f6:
        r2 = r15.zzbze;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzcgs;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzadj;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzbjn;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r2;
    L_0x00ff:
        r2 = r15.zzbze;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzcgs;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzadj;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 == 0) goto L_0x0126;
    L_0x0107:
        r2 = r15.zzbze;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzcgs;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzadj;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r2.zzbjp;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = r2;
    L_0x0110:
        r2 = "2";
        r2 = r2.equals(r5);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 == 0) goto L_0x0128;
    L_0x0118:
        r2 = new com.google.android.gms.internal.ads.zzacn;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5 = r15.zzbze;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5 = r5.zzcor;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2.<init>(r4, r3, r5);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r2;
        goto L_0x0084;
    L_0x0124:
        r4 = r12;
        goto L_0x00ff;
    L_0x0126:
        r3 = r12;
        goto L_0x0110;
    L_0x0128:
        r2 = "1";
        r2 = r2.equals(r5);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 == 0) goto L_0x013c;
    L_0x0130:
        r2 = new com.google.android.gms.internal.ads.zzaco;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5 = r15.zzbze;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5 = r5.zzcor;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2.<init>(r4, r3, r5);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r2;
        goto L_0x0084;
    L_0x013c:
        r2 = "3";
        r2 = r2.equals(r5);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 == 0) goto L_0x01a0;
    L_0x0144:
        r2 = "custom_template_id";
        r2 = r7.getString(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = new com.google.android.gms.internal.ads.zzaoj;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3.<init>();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5 = com.google.android.gms.internal.ads.zzakk.zzcrm;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r6 = new com.google.android.gms.internal.ads.zzaby;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r6.<init>(r15, r3, r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5.post(r6);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r8 = zzbzx;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = r3.get(r8, r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r2 == 0) goto L_0x016b;
    L_0x0163:
        r2 = new com.google.android.gms.internal.ads.zzacp;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2.<init>(r4);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r2;
        goto L_0x0084;
    L_0x016b:
        r3 = "No handler for custom template: ";
        r2 = "custom_template_id";
        r2 = r7.getString(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r2.length();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r4 == 0) goto L_0x0187;
    L_0x017d:
        r2 = r3.concat(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
    L_0x0181:
        com.google.android.gms.internal.ads.zzakb.e(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
    L_0x0184:
        r4 = r11;
        goto L_0x0084;
    L_0x0187:
        r2 = new java.lang.String;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2.<init>(r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        goto L_0x0181;
    L_0x018d:
        r2 = move-exception;
    L_0x018e:
        r3 = "Malformed native JSON response.";
        com.google.android.gms.internal.ads.zzakb.zzc(r3, r2);
    L_0x0193:
        r2 = r15.zzcaa;
        if (r2 != 0) goto L_0x019a;
    L_0x0197:
        r15.zzab(r12);
    L_0x019a:
        r2 = r15.zza(r11, r12);
        goto L_0x00b0;
    L_0x01a0:
        r2 = 0;
        r15.zzab(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        goto L_0x0184;
    L_0x01a5:
        r2 = move-exception;
        goto L_0x018e;
    L_0x01a7:
        r2 = "tracking_urls_and_actions";
        r5 = r7.getJSONObject(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = "impression_tracking_urls";
        r6 = r5.optJSONArray(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r6 != 0) goto L_0x01f0;
    L_0x01b5:
        r2 = r11;
    L_0x01b6:
        if (r2 != 0) goto L_0x0206;
    L_0x01b8:
        r2 = r11;
    L_0x01b9:
        r15.zzcab = r2;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = "active_view";
        r2 = r5.optJSONObject(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r15.zzcac = r2;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = "debug_signals";
        r2 = r7.optString(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r15.zzcad = r2;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = "omid_settings";
        r2 = r7.optString(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r15.zzcae = r2;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r8 = r4.zza(r15, r7);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = new com.google.android.gms.internal.ads.zzpd;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = r15.mContext;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r4 = r15.zzbzz;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r5 = r15.zzaad;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r6 = r15.zzbjc;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r9 = r15.zzbze;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r9 = r9.zzcgs;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r9 = r9.zzacr;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r8.zzb(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = r8;
        goto L_0x008f;
    L_0x01f0:
        r2 = r6.length();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2 = new java.lang.String[r2];	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = r12;
    L_0x01f7:
        r8 = r6.length();	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        if (r3 >= r8) goto L_0x01b6;
    L_0x01fd:
        r8 = r6.getString(r3);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r2[r3] = r8;	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        r3 = r3 + 1;
        goto L_0x01f7;
    L_0x0206:
        r2 = java.util.Arrays.asList(r2);	 Catch:{ CancellationException -> 0x018d, ExecutionException -> 0x01a5, InterruptedException -> 0x021a, JSONException -> 0x021d, TimeoutException -> 0x020b, Exception -> 0x0212 }
        goto L_0x01b9;
    L_0x020b:
        r2 = move-exception;
        r3 = "Timeout when loading native ad.";
        com.google.android.gms.internal.ads.zzakb.zzc(r3, r2);
        goto L_0x0193;
    L_0x0212:
        r2 = move-exception;
        r3 = "Error occured while doing native ads initialization.";
        com.google.android.gms.internal.ads.zzakb.zzc(r3, r2);
        goto L_0x0193;
    L_0x021a:
        r2 = move-exception;
        goto L_0x018e;
    L_0x021d:
        r2 = move-exception;
        goto L_0x018e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabv.zznw():com.google.android.gms.internal.ads.zzajh");
    }

    private final boolean zznx() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcaa;
        }
        return z;
    }

    public final /* synthetic */ Object call() throws Exception {
        return zznw();
    }

    public final zzanz<zzon> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public final List<zzanz<zzon>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        List<zzanz<zzon>> arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            zzd(0, false);
            return arrayList;
        }
        int length = z3 ? optJSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, false, z2));
        }
        return arrayList;
    }

    public final Future<zzon> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    final /* synthetic */ void zza(zzaoj zzaoj, String str) {
        try {
            zzbv.zzel();
            zzaqw zza = zzarc.zza(this.mContext, zzasi.zzvq(), "native-omid", false, false, this.zzbjc, this.zzbze.zzcgs.zzacr, this.zzvr, null, this.zzbzz.zzbi(), this.zzbze.zzcoq);
            zza.zzuf().zza(new zzabx(zzaoj, zza));
            zza.loadData(str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
        } catch (Throwable e) {
            zzaoj.set(null);
            zzane.zzc("", e);
        }
    }

    public final zzanz<zzaqw> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return zzano.zzi(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            zzakb.zzdk("Required field 'vast_xml' is missing");
            return zzano.zzi(null);
        }
        zzace zzace = new zzace(this.mContext, this.zzbjc, this.zzbze, this.zzvr, this.zzbzz);
        zzaoj zzaoj = new zzaoj();
        zzaoe.zzcvy.execute(new zzacf(zzace, optJSONObject, zzaoj));
        return zzaoj;
    }

    public final void zzd(int i, boolean z) {
        if (z) {
            zzab(i);
        }
    }

    public final zzanz<zzoj> zzg(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return zzano.zzi(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND);
        int i = (this.zzbze.zzcgs.zzadj == null || this.zzbze.zzcgs.zzadj.versionCode < 2) ? 1 : this.zzbze.zzcgs.zzadj.zzbjq;
        boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List arrayList = new ArrayList();
        List zza;
        if (optJSONObject.optJSONArray("images") != null) {
            zza = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, MessengerShareContentUtility.MEDIA_IMAGE, false, false));
            zza = arrayList;
        }
        zzanz zzaoj = new zzaoj();
        int size = r2.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzanz zza2 : r2) {
            zza2.zza(new zzacc(atomicInteger, size, zzaoj, r2), zzaki.zzcrj);
        }
        return zzano.zza(zzaoj, new zzaca(this, optString, zzb2, zzb, optInt, optInt3, optInt2, i, optBoolean), zzaki.zzcrj);
    }
}
