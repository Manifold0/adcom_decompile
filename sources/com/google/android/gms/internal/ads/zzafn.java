package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.zzbv;
import com.unity.purchasing.googleplay.Consts;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafn extends zzaeo {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static zzafn zzchh;
    private final Context mContext;
    private final zzafm zzchi;
    private final ScheduledExecutorService zzchj = Executors.newSingleThreadScheduledExecutor();

    private zzafn(Context context, zzafm zzafm) {
        this.mContext = context;
        this.zzchi = zzafm;
    }

    private static zzaej zza(Context context, zzafm zzafm, zzaef zzaef, ScheduledExecutorService scheduledExecutorService) {
        Future zza;
        zzakb.zzck("Starting ad request from service using: google.afma.request.getAdDictionary");
        zznx zznx = new zznx(((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue(), "load_ad", zzaef.zzacv.zzarb);
        if (zzaef.versionCode > 10 && zzaef.zzcdl != -1) {
            zznx.zza(zznx.zzd(zzaef.zzcdl), "cts");
        }
        zznv zzjj = zznx.zzjj();
        Future zza2 = zzano.zza(zzafm.zzche.zzk(context), ((Long) zzkb.zzik().zzd(zznk.zzbdf)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        Future zza3 = zzano.zza(zzafm.zzchd.zzr(context), ((Long) zzkb.zzik().zzd(zznk.zzbah)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        Future zzcl = zzafm.zzcgy.zzcl(zzaef.zzccw.packageName);
        Future zzcm = zzafm.zzcgy.zzcm(zzaef.zzccw.packageName);
        Future zza4 = zzafm.zzchf.zza(zzaef.zzccx, zzaef.zzccw);
        Future zzq = zzbv.zzev().zzq(context);
        zzanz zzi = zzano.zzi(null);
        Bundle bundle = zzaef.zzccv.extras;
        Object obj = (bundle == null || bundle.getString("_ad") == null) ? null : 1;
        if (zzaef.zzcdr && obj == null) {
            zzi = zzafm.zzchb.zza(zzaef.applicationInfo);
        }
        zzanz zza5 = zzano.zza(zzi, ((Long) zzkb.zzik().zzd(zznk.zzbco)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        zzany zzi2 = zzano.zzi(null);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayj)).booleanValue()) {
            zza = zzano.zza(zzafm.zzchf.zzae(context), ((Long) zzkb.zzik().zzd(zznk.zzayk)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        } else {
            Object obj2 = zzi2;
        }
        Bundle bundle2 = (zzaef.versionCode < 4 || zzaef.zzcdc == null) ? null : zzaef.zzcdc;
        ((Boolean) zzkb.zzik().zzd(zznk.zzawx)).booleanValue();
        zzbv.zzek();
        if (zzakk.zzl(context, "android.permission.ACCESS_NETWORK_STATE")) {
            if (((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
                zzakb.zzck("Device is offline.");
            }
        }
        String uuid = zzaef.versionCode >= 7 ? zzaef.zzcdi : UUID.randomUUID().toString();
        zzaft zzaft = new zzaft(context, uuid, zzaef.applicationInfo.packageName);
        if (zzaef.zzccv.extras != null) {
            String string = zzaef.zzccv.extras.getString("_ad");
            if (string != null) {
                return zzafs.zza(context, zzaef, string);
            }
        }
        List zzf = zzafm.zzcgz.zzf(zzaef.zzcdj);
        bundle = (Bundle) zzano.zza(zza2, null, ((Long) zzkb.zzik().zzd(zznk.zzbdf)).longValue(), TimeUnit.MILLISECONDS);
        zzagk zzagk = (zzagk) zzano.zza(zza3, null);
        Location location = (Location) zzano.zza((Future) zza5, null);
        Info info = (Info) zzano.zza(zza, null);
        String str = (String) zzano.zza(zza4, null);
        String str2 = (String) zzano.zza(zzcl, null);
        String str3 = (String) zzano.zza(zzcm, null);
        zzaga zzaga = (zzaga) zzano.zza(zzq, null);
        if (zzaga == null) {
            zzakb.zzdk("Error fetching device info. This is not recoverable.");
            return new zzaej(0);
        }
        zzafl zzafl = new zzafl();
        zzafl.zzcgs = zzaef;
        zzafl.zzcgt = zzaga;
        zzafl.zzcgo = zzagk;
        zzafl.zzaqe = location;
        zzafl.zzcgn = bundle;
        zzafl.zzccx = str;
        zzafl.zzcgr = info;
        if (zzf == null) {
            zzafl.zzcdj.clear();
        }
        zzafl.zzcdj = zzf;
        zzafl.zzcdc = bundle2;
        zzafl.zzcgp = str2;
        zzafl.zzcgq = str3;
        zzafl.zzcgu = zzafm.zzcgx.zze(context);
        zzafl.zzcgv = zzafm.zzcgv;
        JSONObject zza6 = zzafs.zza(context, zzafl);
        if (zza6 == null) {
            return new zzaej(0);
        }
        if (zzaef.versionCode < 7) {
            try {
                zza6.put(Consts.INAPP_REQUEST_ID, uuid);
            } catch (JSONException e) {
            }
        }
        zznx.zza(zzjj, "arc");
        zznx.zzjj();
        Future zza7 = zzano.zza(zzano.zza(zzafm.zzchg.zzof().zzf(zza6), zzafo.zzxn, (Executor) scheduledExecutorService), 10, TimeUnit.SECONDS, scheduledExecutorService);
        zzi = zzafm.zzcha.zzop();
        if (zzi != null) {
            zzanm.zza(zzi, "AdRequestServiceImpl.loadAd.flags");
        }
        zzafz zzafz = (zzafz) zzano.zza(zza7, null);
        if (zzafz == null) {
            return new zzaej(0);
        }
        if (zzafz.getErrorCode() != -2) {
            return new zzaej(zzafz.getErrorCode());
        }
        zznx.zzjm();
        zzaej zzaej = null;
        if (!TextUtils.isEmpty(zzafz.zzom())) {
            zzaej = zzafs.zza(context, zzaef, zzafz.zzom());
        }
        if (zzaej == null && !TextUtils.isEmpty(zzafz.getUrl())) {
            zzaej = zza(zzaef, context, zzaef.zzacr.zzcw, zzafz.getUrl(), str2, str3, zzafz, zznx, zzafm);
        }
        if (zzaej == null) {
            zzaej = new zzaej(0);
        }
        zznx.zza(zzjj, "tts");
        zzaej.zzcfd = zznx.zzjk();
        return zzaej;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzaej zza(com.google.android.gms.internal.ads.zzaef r14, android.content.Context r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, com.google.android.gms.internal.ads.zzafz r20, com.google.android.gms.internal.ads.zznx r21, com.google.android.gms.internal.ads.zzafm r22) {
        /*
        if (r21 == 0) goto L_0x0110;
    L_0x0002:
        r2 = r21.zzjj();
        r4 = r2;
    L_0x0007:
        r9 = new com.google.android.gms.internal.ads.zzafx;	 Catch:{ IOException -> 0x011b }
        r2 = r20.zzoi();	 Catch:{ IOException -> 0x011b }
        r9.<init>(r14, r2);	 Catch:{ IOException -> 0x011b }
        r3 = "AdRequestServiceImpl: Sending request: ";
        r2 = java.lang.String.valueOf(r17);	 Catch:{ IOException -> 0x011b }
        r5 = r2.length();	 Catch:{ IOException -> 0x011b }
        if (r5 == 0) goto L_0x0114;
    L_0x001c:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x011b }
    L_0x0020:
        com.google.android.gms.internal.ads.zzakb.zzck(r2);	 Catch:{ IOException -> 0x011b }
        r3 = new java.net.URL;	 Catch:{ IOException -> 0x011b }
        r0 = r17;
        r3.<init>(r0);	 Catch:{ IOException -> 0x011b }
        r2 = 0;
        r5 = com.google.android.gms.ads.internal.zzbv.zzer();	 Catch:{ IOException -> 0x011b }
        r10 = r5.elapsedRealtime();	 Catch:{ IOException -> 0x011b }
        r7 = r2;
        r8 = r3;
    L_0x0035:
        if (r22 == 0) goto L_0x003e;
    L_0x0037:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x011b }
        r2.zzoq();	 Catch:{ IOException -> 0x011b }
    L_0x003e:
        r2 = r8.openConnection();	 Catch:{ IOException -> 0x011b }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x011b }
        r3 = com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0140 }
        r5 = 0;
        r0 = r16;
        r3.zza(r15, r0, r5, r2);	 Catch:{ all -> 0x0140 }
        r3 = r20.zzok();	 Catch:{ all -> 0x0140 }
        if (r3 == 0) goto L_0x006e;
    L_0x0054:
        r3 = android.text.TextUtils.isEmpty(r18);	 Catch:{ all -> 0x0140 }
        if (r3 != 0) goto L_0x0061;
    L_0x005a:
        r3 = "x-afma-drt-cookie";
        r0 = r18;
        r2.addRequestProperty(r3, r0);	 Catch:{ all -> 0x0140 }
    L_0x0061:
        r3 = android.text.TextUtils.isEmpty(r19);	 Catch:{ all -> 0x0140 }
        if (r3 != 0) goto L_0x006e;
    L_0x0067:
        r3 = "x-afma-drt-v2-cookie";
        r0 = r19;
        r2.addRequestProperty(r3, r0);	 Catch:{ all -> 0x0140 }
    L_0x006e:
        r3 = r14.zzcds;	 Catch:{ all -> 0x0140 }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x0140 }
        if (r5 != 0) goto L_0x0080;
    L_0x0076:
        r5 = "Sending webview cookie in ad request header.";
        com.google.android.gms.internal.ads.zzakb.zzck(r5);	 Catch:{ all -> 0x0140 }
        r5 = "Cookie";
        r2.addRequestProperty(r5, r3);	 Catch:{ all -> 0x0140 }
    L_0x0080:
        r3 = 0;
        if (r20 == 0) goto L_0x00ad;
    L_0x0083:
        r5 = r20.zzoj();	 Catch:{ all -> 0x0140 }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0140 }
        if (r5 != 0) goto L_0x00ad;
    L_0x008d:
        r3 = 1;
        r2.setDoOutput(r3);	 Catch:{ all -> 0x0140 }
        r3 = r20.zzoj();	 Catch:{ all -> 0x0140 }
        r3 = r3.getBytes();	 Catch:{ all -> 0x0140 }
        r5 = r3.length;	 Catch:{ all -> 0x0140 }
        r2.setFixedLengthStreamingMode(r5);	 Catch:{ all -> 0x0140 }
        r6 = 0;
        r5 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x013a }
        r12 = r2.getOutputStream();	 Catch:{ all -> 0x013a }
        r5.<init>(r12);	 Catch:{ all -> 0x013a }
        r5.write(r3);	 Catch:{ all -> 0x020b }
        com.google.android.gms.common.util.IOUtils.closeQuietly(r5);	 Catch:{ all -> 0x0140 }
    L_0x00ad:
        r12 = new com.google.android.gms.internal.ads.zzamy;	 Catch:{ all -> 0x0140 }
        r5 = r14.zzcdi;	 Catch:{ all -> 0x0140 }
        r12.<init>(r5);	 Catch:{ all -> 0x0140 }
        r12.zza(r2, r3);	 Catch:{ all -> 0x0140 }
        r3 = r2.getResponseCode();	 Catch:{ all -> 0x0140 }
        r13 = r2.getHeaderFields();	 Catch:{ all -> 0x0140 }
        r12.zza(r2, r3);	 Catch:{ all -> 0x0140 }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 < r5) goto L_0x0154;
    L_0x00c6:
        r5 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r3 >= r5) goto L_0x0154;
    L_0x00ca:
        r7 = r8.toString();	 Catch:{ all -> 0x0140 }
        r6 = 0;
        r5 = new java.io.InputStreamReader;	 Catch:{ all -> 0x014e }
        r8 = r2.getInputStream();	 Catch:{ all -> 0x014e }
        r5.<init>(r8);	 Catch:{ all -> 0x014e }
        com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0207 }
        r6 = com.google.android.gms.internal.ads.zzakk.zza(r5);	 Catch:{ all -> 0x0207 }
        com.google.android.gms.common.util.IOUtils.closeQuietly(r5);	 Catch:{ all -> 0x0140 }
        r12.zzdg(r6);	 Catch:{ all -> 0x0140 }
        zza(r7, r13, r6, r3);	 Catch:{ all -> 0x0140 }
        r9.zza(r7, r13, r6);	 Catch:{ all -> 0x0140 }
        if (r21 == 0) goto L_0x00fa;
    L_0x00ed:
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ all -> 0x0140 }
        r5 = 0;
        r6 = "ufe";
        r3[r5] = r6;	 Catch:{ all -> 0x0140 }
        r0 = r21;
        r0.zza(r4, r3);	 Catch:{ all -> 0x0140 }
    L_0x00fa:
        r3 = r20.zzon();	 Catch:{ all -> 0x0140 }
        r3 = r9.zza(r10, r3);	 Catch:{ all -> 0x0140 }
        r2.disconnect();	 Catch:{ IOException -> 0x011b }
        if (r22 == 0) goto L_0x010e;
    L_0x0107:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x011b }
        r2.zzor();	 Catch:{ IOException -> 0x011b }
    L_0x010e:
        r2 = r3;
    L_0x010f:
        return r2;
    L_0x0110:
        r2 = 0;
        r4 = r2;
        goto L_0x0007;
    L_0x0114:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x011b }
        r2.<init>(r3);	 Catch:{ IOException -> 0x011b }
        goto L_0x0020;
    L_0x011b:
        r2 = move-exception;
        r3 = "Error while connecting to ad server: ";
        r2 = r2.getMessage();
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x0200;
    L_0x012c:
        r2 = r3.concat(r2);
    L_0x0130:
        com.google.android.gms.internal.ads.zzakb.zzdk(r2);
        r2 = new com.google.android.gms.internal.ads.zzaej;
        r3 = 2;
        r2.<init>(r3);
        goto L_0x010f;
    L_0x013a:
        r3 = move-exception;
        r4 = r6;
    L_0x013c:
        com.google.android.gms.common.util.IOUtils.closeQuietly(r4);	 Catch:{ all -> 0x0140 }
        throw r3;	 Catch:{ all -> 0x0140 }
    L_0x0140:
        r3 = move-exception;
        r2.disconnect();	 Catch:{ IOException -> 0x011b }
        if (r22 == 0) goto L_0x014d;
    L_0x0146:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x011b }
        r2.zzor();	 Catch:{ IOException -> 0x011b }
    L_0x014d:
        throw r3;	 Catch:{ IOException -> 0x011b }
    L_0x014e:
        r3 = move-exception;
        r4 = r6;
    L_0x0150:
        com.google.android.gms.common.util.IOUtils.closeQuietly(r4);	 Catch:{ all -> 0x0140 }
        throw r3;	 Catch:{ all -> 0x0140 }
    L_0x0154:
        r5 = r8.toString();	 Catch:{ all -> 0x0140 }
        r6 = 0;
        zza(r5, r13, r6, r3);	 Catch:{ all -> 0x0140 }
        r5 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r3 < r5) goto L_0x01bc;
    L_0x0160:
        r5 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r3 >= r5) goto L_0x01bc;
    L_0x0164:
        r3 = "Location";
        r3 = r2.getHeaderField(r3);	 Catch:{ all -> 0x0140 }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x0140 }
        if (r5 == 0) goto L_0x0189;
    L_0x0170:
        r3 = "No location header to follow redirect.";
        com.google.android.gms.internal.ads.zzakb.zzdk(r3);	 Catch:{ all -> 0x0140 }
        r3 = new com.google.android.gms.internal.ads.zzaej;	 Catch:{ all -> 0x0140 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0140 }
        r2.disconnect();	 Catch:{ IOException -> 0x011b }
        if (r22 == 0) goto L_0x0187;
    L_0x0180:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x011b }
        r2.zzor();	 Catch:{ IOException -> 0x011b }
    L_0x0187:
        r2 = r3;
        goto L_0x010f;
    L_0x0189:
        r6 = new java.net.URL;	 Catch:{ all -> 0x0140 }
        r6.<init>(r3);	 Catch:{ all -> 0x0140 }
        r5 = r7 + 1;
        r3 = com.google.android.gms.internal.ads.zznk.zzbes;	 Catch:{ all -> 0x0140 }
        r7 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0140 }
        r3 = r7.zzd(r3);	 Catch:{ all -> 0x0140 }
        r3 = (java.lang.Integer) r3;	 Catch:{ all -> 0x0140 }
        r3 = r3.intValue();	 Catch:{ all -> 0x0140 }
        if (r5 <= r3) goto L_0x01e9;
    L_0x01a2:
        r3 = "Too many redirects.";
        com.google.android.gms.internal.ads.zzakb.zzdk(r3);	 Catch:{ all -> 0x0140 }
        r3 = new com.google.android.gms.internal.ads.zzaej;	 Catch:{ all -> 0x0140 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0140 }
        r2.disconnect();	 Catch:{ IOException -> 0x011b }
        if (r22 == 0) goto L_0x01b9;
    L_0x01b2:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x011b }
        r2.zzor();	 Catch:{ IOException -> 0x011b }
    L_0x01b9:
        r2 = r3;
        goto L_0x010f;
    L_0x01bc:
        r4 = 46;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0140 }
        r5.<init>(r4);	 Catch:{ all -> 0x0140 }
        r4 = "Received error HTTP response code: ";
        r4 = r5.append(r4);	 Catch:{ all -> 0x0140 }
        r3 = r4.append(r3);	 Catch:{ all -> 0x0140 }
        r3 = r3.toString();	 Catch:{ all -> 0x0140 }
        com.google.android.gms.internal.ads.zzakb.zzdk(r3);	 Catch:{ all -> 0x0140 }
        r3 = new com.google.android.gms.internal.ads.zzaej;	 Catch:{ all -> 0x0140 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0140 }
        r2.disconnect();	 Catch:{ IOException -> 0x011b }
        if (r22 == 0) goto L_0x01e6;
    L_0x01df:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x011b }
        r2.zzor();	 Catch:{ IOException -> 0x011b }
    L_0x01e6:
        r2 = r3;
        goto L_0x010f;
    L_0x01e9:
        r9.zzl(r13);	 Catch:{ all -> 0x0140 }
        r2.disconnect();	 Catch:{ IOException -> 0x011b }
        if (r22 == 0) goto L_0x01fc;
    L_0x01f1:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x011b }
        r2.zzor();	 Catch:{ IOException -> 0x011b }
        r7 = r5;
        r8 = r6;
        goto L_0x0035;
    L_0x01fc:
        r7 = r5;
        r8 = r6;
        goto L_0x0035;
    L_0x0200:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x0130;
    L_0x0207:
        r3 = move-exception;
        r4 = r5;
        goto L_0x0150;
    L_0x020b:
        r3 = move-exception;
        r4 = r5;
        goto L_0x013c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafn.zza(com.google.android.gms.internal.ads.zzaef, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.ads.zzafz, com.google.android.gms.internal.ads.zznx, com.google.android.gms.internal.ads.zzafm):com.google.android.gms.internal.ads.zzaej");
    }

    public static zzafn zza(Context context, zzafm zzafm) {
        zzafn zzafn;
        synchronized (sLock) {
            if (zzchh == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zznk.initialize(context);
                zzchh = new zzafn(context, zzafm);
                if (context.getApplicationContext() != null) {
                    zzbv.zzek().zzal(context);
                }
                zzajz.zzai(context);
            }
            zzafn = zzchh;
        }
        return zzafn;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzakb.isLoggable(2)) {
            zzakb.m3915v(new StringBuilder(String.valueOf(str).length() + 39).append("Http Response: {\n  URL:\n    ").append(str).append("\n  Headers:").toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    String str32;
                    zzakb.m3915v(new StringBuilder(String.valueOf(str32).length() + 5).append("    ").append(str32).append(":").toString());
                    for (String str322 : (List) map.get(str322)) {
                        String str4 = "      ";
                        str322 = String.valueOf(str322);
                        zzakb.m3915v(str322.length() != 0 ? str4.concat(str322) : new String(str4));
                    }
                }
            }
            zzakb.m3915v("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += 1000) {
                    zzakb.m3915v(str2.substring(i2, Math.min(str2.length(), i2 + 1000)));
                }
            } else {
                zzakb.m3915v("    null");
            }
            zzakb.m3915v("  Response Code:\n    " + i + "\n}");
        }
    }

    public final void zza(zzaef zzaef, zzaeq zzaeq) {
        zzbv.zzeo().zzd(this.mContext, zzaef.zzacr);
        Future zzb = zzaki.zzb(new zzafp(this, zzaef, zzaeq));
        zzbv.zzez().zzsa();
        zzbv.zzez().getHandler().postDelayed(new zzafq(this, zzb), 60000);
    }

    public final void zza(zzaey zzaey, zzaet zzaet) {
        zzakb.m3915v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    public final zzaej zzb(zzaef zzaef) {
        return zza(this.mContext, this.zzchi, zzaef, this.zzchj);
    }

    public final void zzb(zzaey zzaey, zzaet zzaet) {
        zzakb.m3915v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }
}
