// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import java.util.List;
import org.json.JSONException;
import android.text.TextUtils;
import java.util.concurrent.Executor;
import org.json.JSONObject;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.location.Location;
import java.util.concurrent.Future;
import android.os.Bundle;
import java.util.UUID;
import android.net.ConnectivityManager;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzafn extends zzaeo
{
    private static final Object sLock;
    @GuardedBy("sLock")
    private static zzafn zzchh;
    private final Context mContext;
    private final zzafm zzchi;
    private final ScheduledExecutorService zzchj;
    
    static {
        sLock = new Object();
    }
    
    private zzafn(final Context mContext, final zzafm zzchi) {
        this.zzchj = Executors.newSingleThreadScheduledExecutor();
        this.mContext = mContext;
        this.zzchi = zzchi;
    }
    
    private static zzaej zza(Context context, final zzafm zzafm, final zzaef zzcgs, ScheduledExecutorService scheduledExecutorService) {
        zzakb.zzck("Starting ad request from service using: google.afma.request.getAdDictionary");
        final zznx zznx = new zznx((boolean)zzkb.zzik().zzd(zznk.zzawh), "load_ad", zzcgs.zzacv.zzarb);
        if (zzcgs.versionCode > 10 && zzcgs.zzcdl != -1L) {
            zznx.zza(zznx.zzd(zzcgs.zzcdl), "cts");
        }
        final zznv zzjj = zznx.zzjj();
        final zzanz<T> zza = zzano.zza((zzanz<T>)zzafm.zzche.zzk(context), (long)zzkb.zzik().zzd(zznk.zzbdf), TimeUnit.MILLISECONDS, scheduledExecutorService);
        final zzanz<T> zza2 = zzano.zza((zzanz<T>)zzafm.zzchd.zzr(context), (long)zzkb.zzik().zzd(zznk.zzbah), TimeUnit.MILLISECONDS, scheduledExecutorService);
        final zzanz<String> zzcl = zzafm.zzcgy.zzcl(zzcgs.zzccw.packageName);
        final zzanz<String> zzcm = zzafm.zzcgy.zzcm(zzcgs.zzccw.packageName);
        final zzanz<String> zza3 = zzafm.zzchf.zza(zzcgs.zzccx, zzcgs.zzccw);
        final Future<zzaga> zzq = zzbv.zzev().zzq(context);
        final zzany<V> zzi = zzano.zzi((V)null);
        final Bundle extras = zzcgs.zzccv.extras;
        int n;
        if (extras != null && extras.getString("_ad") != null) {
            n = 1;
        }
        else {
            n = 0;
        }
        Object zza4 = zzi;
        if (zzcgs.zzcdr) {
            zza4 = zzi;
            if (n == 0) {
                zza4 = zzafm.zzchb.zza(zzcgs.applicationInfo);
            }
        }
        final zzanz<T> zza5 = zzano.zza((zzanz<T>)zza4, (long)zzkb.zzik().zzd(zznk.zzbco), TimeUnit.MILLISECONDS, scheduledExecutorService);
        zzanz<T> zzanz = zzano.zzi((T)null);
        if (zzkb.zzik().zzd(zznk.zzayj)) {
            zzanz = zzano.zza((zzanz<T>)zzafm.zzchf.zzae(context), (long)zzkb.zzik().zzd(zznk.zzayk), TimeUnit.MILLISECONDS, scheduledExecutorService);
        }
        while (true) {
            Label_1141: {
                if (zzcgs.versionCode < 4 || zzcgs.zzcdc == null) {
                    break Label_1141;
                }
                final Bundle zzcdc = zzcgs.zzcdc;
                (boolean)zzkb.zzik().zzd(zznk.zzawx);
                zzbv.zzek();
                if (zzakk.zzl(context, "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
                    zzakb.zzck("Device is offline.");
                }
                String s;
                if (zzcgs.versionCode >= 7) {
                    s = zzcgs.zzcdi;
                }
                else {
                    s = UUID.randomUUID().toString();
                }
                new zzaft(context, s, zzcgs.applicationInfo.packageName);
                if (zzcgs.zzccv.extras != null) {
                    final String string = zzcgs.zzccv.extras.getString("_ad");
                    if (string != null) {
                        return zzafs.zza(context, zzcgs, string);
                    }
                }
                final List<String> zzf = zzafm.zzcgz.zzf(zzcgs.zzcdj);
                final Bundle zzcgn = zzano.zza((Future<Bundle>)zza, null, (long)zzkb.zzik().zzd(zznk.zzbdf), TimeUnit.MILLISECONDS);
                final zzagk zzcgo = zzano.zza((Future<zzagk>)zza2, null);
                final Location zzaqe = zzano.zza((Future<Location>)zza5, null);
                final AdvertisingIdClient$Info zzcgr = zzano.zza((Future<AdvertisingIdClient$Info>)zzanz, null);
                final String zzccx = zzano.zza(zza3, (String)null);
                final String zzcgp = zzano.zza(zzcl, (String)null);
                final String zzcgq = zzano.zza(zzcm, (String)null);
                final zzaga zzcgt = zzano.zza(zzq, null);
                if (zzcgt == null) {
                    zzakb.zzdk("Error fetching device info. This is not recoverable.");
                    return new zzaej(0);
                }
                final zzafl zzafl = new zzafl();
                zzafl.zzcgs = zzcgs;
                zzafl.zzcgt = zzcgt;
                zzafl.zzcgo = zzcgo;
                zzafl.zzaqe = zzaqe;
                zzafl.zzcgn = zzcgn;
                zzafl.zzccx = zzccx;
                zzafl.zzcgr = zzcgr;
                if (zzf == null) {
                    zzafl.zzcdj.clear();
                }
                zzafl.zzcdj = zzf;
                zzafl.zzcdc = zzcdc;
                zzafl.zzcgp = zzcgp;
                zzafl.zzcgq = zzcgq;
                zzafl.zzcgu = zzafm.zzcgx.zze(context);
                zzafl.zzcgv = zzafm.zzcgv;
                Object o = zzafs.zza(context, zzafl);
                if (o == null) {
                    return new zzaej(0);
                }
                while (true) {
                    if (zzcgs.versionCode >= 7) {
                        break Label_0884;
                    }
                    try {
                        ((JSONObject)o).put("request_id", (Object)s);
                        zznx.zza(zzjj, "arc");
                        zznx.zzjj();
                        scheduledExecutorService = (ScheduledExecutorService)zzano.zza(zzano.zza(zzafm.zzchg.zzof().zzf((JSONObject)o), (zzanj<? super JSONObject, ?>)zzafo.zzxn, (Executor)scheduledExecutorService), 10L, TimeUnit.SECONDS, scheduledExecutorService);
                        o = zzafm.zzcha.zzop();
                        if (o != null) {
                            zzanm.zza((zzanz)o, "AdRequestServiceImpl.loadAd.flags");
                        }
                        final zzafz zzafz = zzano.zza((Future<zzafz>)scheduledExecutorService, null);
                        if (zzafz == null) {
                            return new zzaej(0);
                        }
                        if (zzafz.getErrorCode() != -2) {
                            return new zzaej(zzafz.getErrorCode());
                        }
                        zznx.zzjm();
                        o = null;
                        if (!TextUtils.isEmpty((CharSequence)zzafz.zzom())) {
                            o = zzafs.zza(context, zzcgs, zzafz.zzom());
                        }
                        if ((scheduledExecutorService = (ScheduledExecutorService)o) == null) {
                            scheduledExecutorService = (ScheduledExecutorService)o;
                            if (!TextUtils.isEmpty((CharSequence)zzafz.getUrl())) {
                                scheduledExecutorService = (ScheduledExecutorService)zza(zzcgs, context, zzcgs.zzacr.zzcw, zzafz.getUrl(), zzcgp, zzcgq, zzafz, zznx, zzafm);
                            }
                        }
                        if ((context = (Context)scheduledExecutorService) == null) {
                            context = (Context)new zzaej(0);
                        }
                        zznx.zza(zzjj, "tts");
                        ((zzaej)context).zzcfd = zznx.zzjk();
                        return (zzaej)context;
                    }
                    catch (JSONException ex) {
                        continue;
                    }
                    break;
                }
            }
            final Bundle zzcdc = null;
            continue;
        }
    }
    
    public static zzaej zza(final zzaef p0, final Context p1, final String p2, final String p3, final String p4, final String p5, final zzafz p6, final zznx p7, final zzafm p8) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: ifnull          810
        //     5: aload           7
        //     7: invokevirtual   com/google/android/gms/internal/ads/zznx.zzjj:()Lcom/google/android/gms/internal/ads/zznv;
        //    10: astore          13
        //    12: new             Lcom/google/android/gms/internal/ads/zzafx;
        //    15: dup            
        //    16: aload_0        
        //    17: aload           6
        //    19: invokevirtual   com/google/android/gms/internal/ads/zzafz.zzoi:()Ljava/lang/String;
        //    22: invokespecial   com/google/android/gms/internal/ads/zzafx.<init>:(Lcom/google/android/gms/internal/ads/zzaef;Ljava/lang/String;)V
        //    25: astore          17
        //    27: aload_3        
        //    28: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    31: astore          14
        //    33: aload           14
        //    35: invokevirtual   java/lang/String.length:()I
        //    38: ifeq            443
        //    41: ldc_w           "AdRequestServiceImpl: Sending request: "
        //    44: aload           14
        //    46: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    49: astore          14
        //    51: aload           14
        //    53: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //    56: new             Ljava/net/URL;
        //    59: dup            
        //    60: aload_3        
        //    61: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    64: astore_3       
        //    65: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //    68: invokeinterface com/google/android/gms/common/util/Clock.elapsedRealtime:()J
        //    73: lstore          11
        //    75: iconst_0       
        //    76: istore          9
        //    78: aload           8
        //    80: ifnull          93
        //    83: aload           8
        //    85: getfield        com/google/android/gms/internal/ads/zzafm.zzchc:Lcom/google/android/gms/internal/ads/zzagi;
        //    88: invokeinterface com/google/android/gms/internal/ads/zzagi.zzoq:()V
        //    93: aload_3        
        //    94: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    97: checkcast       Ljava/net/HttpURLConnection;
        //   100: astore          16
        //   102: invokestatic    com/google/android/gms/ads/internal/zzbv.zzek:()Lcom/google/android/gms/internal/ads/zzakk;
        //   105: aload_1        
        //   106: aload_2        
        //   107: iconst_0       
        //   108: aload           16
        //   110: invokevirtual   com/google/android/gms/internal/ads/zzakk.zza:(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
        //   113: aload           6
        //   115: invokevirtual   com/google/android/gms/internal/ads/zzafz.zzok:()Z
        //   118: ifeq            157
        //   121: aload           4
        //   123: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   126: ifne            139
        //   129: aload           16
        //   131: ldc_w           "x-afma-drt-cookie"
        //   134: aload           4
        //   136: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   139: aload           5
        //   141: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   144: ifne            157
        //   147: aload           16
        //   149: ldc_w           "x-afma-drt-v2-cookie"
        //   152: aload           5
        //   154: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   157: aload_0        
        //   158: getfield        com/google/android/gms/internal/ads/zzaef.zzcds:Ljava/lang/String;
        //   161: astore          14
        //   163: aload           14
        //   165: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   168: ifne            187
        //   171: ldc_w           "Sending webview cookie in ad request header."
        //   174: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //   177: aload           16
        //   179: ldc_w           "Cookie"
        //   182: aload           14
        //   184: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   187: aconst_null    
        //   188: astore          15
        //   190: aload           15
        //   192: astore          14
        //   194: aload           6
        //   196: ifnull          268
        //   199: aload           15
        //   201: astore          14
        //   203: aload           6
        //   205: invokevirtual   com/google/android/gms/internal/ads/zzafz.zzoj:()Ljava/lang/String;
        //   208: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   211: ifne            268
        //   214: aload           16
        //   216: iconst_1       
        //   217: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   220: aload           6
        //   222: invokevirtual   com/google/android/gms/internal/ads/zzafz.zzoj:()Ljava/lang/String;
        //   225: invokevirtual   java/lang/String.getBytes:()[B
        //   228: astore          15
        //   230: aload           16
        //   232: aload           15
        //   234: arraylength    
        //   235: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   238: new             Ljava/io/BufferedOutputStream;
        //   241: dup            
        //   242: aload           16
        //   244: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   247: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   250: astore          14
        //   252: aload           14
        //   254: aload           15
        //   256: invokevirtual   java/io/BufferedOutputStream.write:([B)V
        //   259: aload           14
        //   261: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //   264: aload           15
        //   266: astore          14
        //   268: new             Lcom/google/android/gms/internal/ads/zzamy;
        //   271: dup            
        //   272: aload_0        
        //   273: getfield        com/google/android/gms/internal/ads/zzaef.zzcdi:Ljava/lang/String;
        //   276: invokespecial   com/google/android/gms/internal/ads/zzamy.<init>:(Ljava/lang/String;)V
        //   279: astore          15
        //   281: aload           15
        //   283: aload           16
        //   285: aload           14
        //   287: invokevirtual   com/google/android/gms/internal/ads/zzamy.zza:(Ljava/net/HttpURLConnection;[B)V
        //   290: aload           16
        //   292: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   295: istore          10
        //   297: aload           16
        //   299: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   302: astore          14
        //   304: aload           15
        //   306: aload           16
        //   308: iload           10
        //   310: invokevirtual   com/google/android/gms/internal/ads/zzamy.zza:(Ljava/net/HttpURLConnection;I)V
        //   313: iload           10
        //   315: sipush          200
        //   318: if_icmplt       536
        //   321: iload           10
        //   323: sipush          300
        //   326: if_icmpge       536
        //   329: aload_3        
        //   330: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   333: astore_0       
        //   334: new             Ljava/io/InputStreamReader;
        //   337: dup            
        //   338: aload           16
        //   340: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   343: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //   346: astore_1       
        //   347: invokestatic    com/google/android/gms/ads/internal/zzbv.zzek:()Lcom/google/android/gms/internal/ads/zzakk;
        //   350: pop            
        //   351: aload_1        
        //   352: invokestatic    com/google/android/gms/internal/ads/zzakk.zza:(Ljava/io/InputStreamReader;)Ljava/lang/String;
        //   355: astore_2       
        //   356: aload_1        
        //   357: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //   360: aload           15
        //   362: aload_2        
        //   363: invokevirtual   com/google/android/gms/internal/ads/zzamy.zzdg:(Ljava/lang/String;)V
        //   366: aload_0        
        //   367: aload           14
        //   369: aload_2        
        //   370: iload           10
        //   372: invokestatic    com/google/android/gms/internal/ads/zzafn.zza:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //   375: aload           17
        //   377: aload_0        
        //   378: aload           14
        //   380: aload_2        
        //   381: invokevirtual   com/google/android/gms/internal/ads/zzafx.zza:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V
        //   384: aload           7
        //   386: ifnull          407
        //   389: aload           7
        //   391: aload           13
        //   393: iconst_1       
        //   394: anewarray       Ljava/lang/String;
        //   397: dup            
        //   398: iconst_0       
        //   399: ldc_w           "ufe"
        //   402: aastore        
        //   403: invokevirtual   com/google/android/gms/internal/ads/zznx.zza:(Lcom/google/android/gms/internal/ads/zznv;[Ljava/lang/String;)Z
        //   406: pop            
        //   407: aload           17
        //   409: lload           11
        //   411: aload           6
        //   413: invokevirtual   com/google/android/gms/internal/ads/zzafz.zzon:()Z
        //   416: invokevirtual   com/google/android/gms/internal/ads/zzafx.zza:(JZ)Lcom/google/android/gms/internal/ads/zzaej;
        //   419: astore_0       
        //   420: aload           16
        //   422: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   425: aload           8
        //   427: ifnull          808
        //   430: aload           8
        //   432: getfield        com/google/android/gms/internal/ads/zzafm.zzchc:Lcom/google/android/gms/internal/ads/zzagi;
        //   435: invokeinterface com/google/android/gms/internal/ads/zzagi.zzor:()V
        //   440: goto            808
        //   443: new             Ljava/lang/String;
        //   446: dup            
        //   447: ldc_w           "AdRequestServiceImpl: Sending request: "
        //   450: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   453: astore          14
        //   455: goto            51
        //   458: astore_0       
        //   459: aload_0        
        //   460: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   463: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   466: astore_0       
        //   467: aload_0        
        //   468: invokevirtual   java/lang/String.length:()I
        //   471: ifeq            783
        //   474: ldc_w           "Error while connecting to ad server: "
        //   477: aload_0        
        //   478: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   481: astore_0       
        //   482: aload_0        
        //   483: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   486: new             Lcom/google/android/gms/internal/ads/zzaej;
        //   489: dup            
        //   490: iconst_2       
        //   491: invokespecial   com/google/android/gms/internal/ads/zzaej.<init>:(I)V
        //   494: areturn        
        //   495: astore_0       
        //   496: aconst_null    
        //   497: astore_1       
        //   498: aload_1        
        //   499: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //   502: aload_0        
        //   503: athrow         
        //   504: astore_0       
        //   505: aload           16
        //   507: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   510: aload           8
        //   512: ifnull          525
        //   515: aload           8
        //   517: getfield        com/google/android/gms/internal/ads/zzafm.zzchc:Lcom/google/android/gms/internal/ads/zzagi;
        //   520: invokeinterface com/google/android/gms/internal/ads/zzagi.zzor:()V
        //   525: aload_0        
        //   526: athrow         
        //   527: astore_0       
        //   528: aconst_null    
        //   529: astore_1       
        //   530: aload_1        
        //   531: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //   534: aload_0        
        //   535: athrow         
        //   536: aload_3        
        //   537: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   540: aload           14
        //   542: aconst_null    
        //   543: iload           10
        //   545: invokestatic    com/google/android/gms/internal/ads/zzafn.zza:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //   548: iload           10
        //   550: sipush          300
        //   553: if_icmplt       693
        //   556: iload           10
        //   558: sipush          400
        //   561: if_icmpge       693
        //   564: aload           16
        //   566: ldc_w           "Location"
        //   569: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   572: astore_3       
        //   573: aload_3        
        //   574: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   577: ifeq            617
        //   580: ldc_w           "No location header to follow redirect."
        //   583: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   586: new             Lcom/google/android/gms/internal/ads/zzaej;
        //   589: dup            
        //   590: iconst_0       
        //   591: invokespecial   com/google/android/gms/internal/ads/zzaej.<init>:(I)V
        //   594: astore_0       
        //   595: aload           16
        //   597: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   600: aload           8
        //   602: ifnull          615
        //   605: aload           8
        //   607: getfield        com/google/android/gms/internal/ads/zzafm.zzchc:Lcom/google/android/gms/internal/ads/zzagi;
        //   610: invokeinterface com/google/android/gms/internal/ads/zzagi.zzor:()V
        //   615: aload_0        
        //   616: areturn        
        //   617: new             Ljava/net/URL;
        //   620: dup            
        //   621: aload_3        
        //   622: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   625: astore_3       
        //   626: iload           9
        //   628: iconst_1       
        //   629: iadd           
        //   630: istore          9
        //   632: getstatic       com/google/android/gms/internal/ads/zznk.zzbes:Lcom/google/android/gms/internal/ads/zzna;
        //   635: astore          15
        //   637: iload           9
        //   639: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //   642: aload           15
        //   644: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //   647: checkcast       Ljava/lang/Integer;
        //   650: invokevirtual   java/lang/Integer.intValue:()I
        //   653: if_icmple       750
        //   656: ldc_w           "Too many redirects."
        //   659: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   662: new             Lcom/google/android/gms/internal/ads/zzaej;
        //   665: dup            
        //   666: iconst_0       
        //   667: invokespecial   com/google/android/gms/internal/ads/zzaej.<init>:(I)V
        //   670: astore_0       
        //   671: aload           16
        //   673: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   676: aload           8
        //   678: ifnull          691
        //   681: aload           8
        //   683: getfield        com/google/android/gms/internal/ads/zzafm.zzchc:Lcom/google/android/gms/internal/ads/zzagi;
        //   686: invokeinterface com/google/android/gms/internal/ads/zzagi.zzor:()V
        //   691: aload_0        
        //   692: areturn        
        //   693: new             Ljava/lang/StringBuilder;
        //   696: dup            
        //   697: bipush          46
        //   699: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   702: ldc_w           "Received error HTTP response code: "
        //   705: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   708: iload           10
        //   710: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   713: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   716: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   719: new             Lcom/google/android/gms/internal/ads/zzaej;
        //   722: dup            
        //   723: iconst_0       
        //   724: invokespecial   com/google/android/gms/internal/ads/zzaej.<init>:(I)V
        //   727: astore_0       
        //   728: aload           16
        //   730: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   733: aload           8
        //   735: ifnull          748
        //   738: aload           8
        //   740: getfield        com/google/android/gms/internal/ads/zzafm.zzchc:Lcom/google/android/gms/internal/ads/zzagi;
        //   743: invokeinterface com/google/android/gms/internal/ads/zzagi.zzor:()V
        //   748: aload_0        
        //   749: areturn        
        //   750: aload           17
        //   752: aload           14
        //   754: invokevirtual   com/google/android/gms/internal/ads/zzafx.zzl:(Ljava/util/Map;)V
        //   757: aload           16
        //   759: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   762: aload           8
        //   764: ifnull          780
        //   767: aload           8
        //   769: getfield        com/google/android/gms/internal/ads/zzafm.zzchc:Lcom/google/android/gms/internal/ads/zzagi;
        //   772: invokeinterface com/google/android/gms/internal/ads/zzagi.zzor:()V
        //   777: goto            78
        //   780: goto            78
        //   783: new             Ljava/lang/String;
        //   786: dup            
        //   787: ldc_w           "Error while connecting to ad server: "
        //   790: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   793: astore_0       
        //   794: goto            482
        //   797: astore_0       
        //   798: goto            530
        //   801: astore_0       
        //   802: aload           14
        //   804: astore_1       
        //   805: goto            498
        //   808: aload_0        
        //   809: areturn        
        //   810: aconst_null    
        //   811: astore          13
        //   813: goto            12
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     51     458    495    Ljava/io/IOException;
        //  51     75     458    495    Ljava/io/IOException;
        //  83     93     458    495    Ljava/io/IOException;
        //  93     102    458    495    Ljava/io/IOException;
        //  102    139    504    527    Any
        //  139    157    504    527    Any
        //  157    187    504    527    Any
        //  203    238    504    527    Any
        //  238    252    495    498    Any
        //  252    259    801    808    Any
        //  259    264    504    527    Any
        //  268    313    504    527    Any
        //  329    334    504    527    Any
        //  334    347    527    530    Any
        //  347    356    797    801    Any
        //  356    384    504    527    Any
        //  389    407    504    527    Any
        //  407    420    504    527    Any
        //  420    425    458    495    Ljava/io/IOException;
        //  430    440    458    495    Ljava/io/IOException;
        //  443    455    458    495    Ljava/io/IOException;
        //  498    504    504    527    Any
        //  505    510    458    495    Ljava/io/IOException;
        //  515    525    458    495    Ljava/io/IOException;
        //  525    527    458    495    Ljava/io/IOException;
        //  530    536    504    527    Any
        //  536    548    504    527    Any
        //  564    595    504    527    Any
        //  595    600    458    495    Ljava/io/IOException;
        //  605    615    458    495    Ljava/io/IOException;
        //  617    626    504    527    Any
        //  632    671    504    527    Any
        //  671    676    458    495    Ljava/io/IOException;
        //  681    691    458    495    Ljava/io/IOException;
        //  693    728    504    527    Any
        //  728    733    458    495    Ljava/io/IOException;
        //  738    748    458    495    Ljava/io/IOException;
        //  750    757    504    527    Any
        //  757    762    458    495    Ljava/io/IOException;
        //  767    777    458    495    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 366, Size: 366
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static zzafn zza(final Context context, final zzafm zzafm) {
        synchronized (zzafn.sLock) {
            if (zzafn.zzchh == null) {
                Context applicationContext = context;
                if (context.getApplicationContext() != null) {
                    applicationContext = context.getApplicationContext();
                }
                zznk.initialize(applicationContext);
                zzafn.zzchh = new zzafn(applicationContext, zzafm);
                if (applicationContext.getApplicationContext() != null) {
                    zzbv.zzek().zzal(applicationContext);
                }
                zzajz.zzai(applicationContext);
            }
            return zzafn.zzchh;
        }
    }
    
    private static void zza(String s, final Map<String, List<String>> map, final String s2, final int n) {
        if (zzakb.isLoggable(2)) {
            zzakb.v(new StringBuilder(String.valueOf(s).length() + 39).append("Http Response: {\n  URL:\n    ").append(s).append("\n  Headers:").toString());
            if (map != null) {
                final Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    s = iterator.next();
                    zzakb.v(new StringBuilder(String.valueOf(s).length() + 5).append("    ").append(s).append(":").toString());
                    final Iterator<String> iterator2 = map.get(s).iterator();
                    while (iterator2.hasNext()) {
                        s = String.valueOf(iterator2.next());
                        if (s.length() != 0) {
                            s = "      ".concat(s);
                        }
                        else {
                            s = new String("      ");
                        }
                        zzakb.v(s);
                    }
                }
            }
            zzakb.v("  Body:");
            if (s2 != null) {
                for (int i = 0; i < Math.min(s2.length(), 100000); i += 1000) {
                    zzakb.v(s2.substring(i, Math.min(s2.length(), i + 1000)));
                }
            }
            else {
                zzakb.v("    null");
            }
            zzakb.v(new StringBuilder(34).append("  Response Code:\n    ").append(n).append("\n}").toString());
        }
    }
    
    public final void zza(final zzaef zzaef, final zzaeq zzaeq) {
        zzbv.zzeo().zzd(this.mContext, zzaef.zzacr);
        final zzanz<?> zzb = zzaki.zzb(new zzafp(this, zzaef, zzaeq));
        zzbv.zzez().zzsa();
        zzbv.zzez().getHandler().postDelayed((Runnable)new zzafq(this, zzb), 60000L);
    }
    
    public final void zza(final zzaey zzaey, final zzaet zzaet) {
        zzakb.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }
    
    public final zzaej zzb(final zzaef zzaef) {
        return zza(this.mContext, this.zzchi, zzaef, this.zzchj);
    }
    
    public final void zzb(final zzaey zzaey, final zzaet zzaet) {
        zzakb.v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }
}
