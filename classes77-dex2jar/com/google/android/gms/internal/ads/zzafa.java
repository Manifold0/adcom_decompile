// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.gmsg.zzaa;
import com.google.android.gms.ads.internal.gmsg.HttpClient;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzafa extends zzajx
{
    private static final Object sLock;
    @VisibleForTesting
    private static final long zzcgc;
    @VisibleForTesting
    @GuardedBy("sLock")
    private static boolean zzcgd;
    private static zzvf zzcge;
    private static HttpClient zzcgf;
    private static zzaa zzcgg;
    private static zzv<Object> zzcgh;
    private final Context mContext;
    private final Object zzbzh;
    private final zzadj zzccf;
    private final zzaeg zzccg;
    private zzhx zzcci;
    private zzvs zzcgi;
    
    static {
        zzcgc = TimeUnit.SECONDS.toMillis(10L);
        sLock = new Object();
        zzafa.zzcgd = false;
        zzafa.zzcge = null;
        zzafa.zzcgf = null;
        zzafa.zzcgg = null;
        zzafa.zzcgh = null;
    }
    
    public zzafa(Context applicationContext, final zzaeg zzccg, final zzadj zzccf, final zzhx zzcci) {
        super(true);
        this.zzbzh = new Object();
        this.zzccf = zzccf;
        this.mContext = applicationContext;
        this.zzccg = zzccg;
        this.zzcci = zzcci;
        synchronized (zzafa.sLock) {
            if (!zzafa.zzcgd) {
                zzafa.zzcgg = new zzaa();
                zzafa.zzcgf = new HttpClient(applicationContext.getApplicationContext(), zzccg.zzacr);
                zzafa.zzcgh = new zzafi();
                applicationContext = this.mContext.getApplicationContext();
                zzafa.zzcge = new zzvf(applicationContext, this.zzccg.zzacr, (String)zzkb.zzik().zzd(zznk.zzaub), new zzafh(), new zzafg());
                zzafa.zzcgd = true;
            }
        }
    }
    
    private final JSONObject zza(final zzaef p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzaef.zzccv:Lcom/google/android/gms/internal/ads/zzjj;
        //     4: getfield        com/google/android/gms/internal/ads/zzjj.extras:Landroid/os/Bundle;
        //     7: ldc             "sdk_less_server_data"
        //     9: invokevirtual   android/os/Bundle.getBundle:(Ljava/lang/String;)Landroid/os/Bundle;
        //    12: astore          5
        //    14: aload           5
        //    16: ifnonnull       21
        //    19: aconst_null    
        //    20: areturn        
        //    21: invokestatic    com/google/android/gms/ads/internal/zzbv.zzev:()Lcom/google/android/gms/internal/ads/zzagc;
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/internal/ads/zzafa.mContext:Landroid/content/Context;
        //    28: invokevirtual   com/google/android/gms/internal/ads/zzagc.zzq:(Landroid/content/Context;)Ljava/util/concurrent/Future;
        //    31: invokeinterface java/util/concurrent/Future.get:()Ljava/lang/Object;
        //    36: checkcast       Lcom/google/android/gms/internal/ads/zzaga;
        //    39: astore          4
        //    41: aload_0        
        //    42: getfield        com/google/android/gms/internal/ads/zzafa.mContext:Landroid/content/Context;
        //    45: astore          6
        //    47: new             Lcom/google/android/gms/internal/ads/zzafl;
        //    50: dup            
        //    51: invokespecial   com/google/android/gms/internal/ads/zzafl.<init>:()V
        //    54: astore          7
        //    56: aload           7
        //    58: aload_1        
        //    59: putfield        com/google/android/gms/internal/ads/zzafl.zzcgs:Lcom/google/android/gms/internal/ads/zzaef;
        //    62: aload           7
        //    64: aload           4
        //    66: putfield        com/google/android/gms/internal/ads/zzafl.zzcgt:Lcom/google/android/gms/internal/ads/zzaga;
        //    69: aload           6
        //    71: aload           7
        //    73: invokestatic    com/google/android/gms/internal/ads/zzafs.zza:(Landroid/content/Context;Lcom/google/android/gms/internal/ads/zzafl;)Lorg/json/JSONObject;
        //    76: astore          4
        //    78: aload           4
        //    80: ifnull          19
        //    83: aload_0        
        //    84: getfield        com/google/android/gms/internal/ads/zzafa.mContext:Landroid/content/Context;
        //    87: invokestatic    com/google/android/gms/ads/identifier/AdvertisingIdClient.getAdvertisingIdInfo:(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
        //    90: astore_1       
        //    91: new             Ljava/util/HashMap;
        //    94: dup            
        //    95: invokespecial   java/util/HashMap.<init>:()V
        //    98: astore          6
        //   100: aload           6
        //   102: ldc             "request_id"
        //   104: aload_2        
        //   105: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   108: pop            
        //   109: aload           6
        //   111: ldc             "request_param"
        //   113: aload           4
        //   115: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   118: pop            
        //   119: aload           6
        //   121: ldc             "data"
        //   123: aload           5
        //   125: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   128: pop            
        //   129: aload_1        
        //   130: ifnull          166
        //   133: aload           6
        //   135: ldc             "adid"
        //   137: aload_1        
        //   138: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$Info.getId:()Ljava/lang/String;
        //   141: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   144: pop            
        //   145: aload_1        
        //   146: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$Info.isLimitAdTrackingEnabled:()Z
        //   149: ifeq            206
        //   152: iconst_1       
        //   153: istore_3       
        //   154: aload           6
        //   156: ldc             "lat"
        //   158: iload_3        
        //   159: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   162: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   165: pop            
        //   166: invokestatic    com/google/android/gms/ads/internal/zzbv.zzek:()Lcom/google/android/gms/internal/ads/zzakk;
        //   169: aload           6
        //   171: invokevirtual   com/google/android/gms/internal/ads/zzakk.zzn:(Ljava/util/Map;)Lorg/json/JSONObject;
        //   174: astore_1       
        //   175: aload_1        
        //   176: areturn        
        //   177: astore          4
        //   179: ldc_w           "Error grabbing device info: "
        //   182: aload           4
        //   184: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   187: aconst_null    
        //   188: astore          4
        //   190: goto            41
        //   193: astore_1       
        //   194: ldc_w           "Cannot get advertising id info"
        //   197: aload_1        
        //   198: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   201: aconst_null    
        //   202: astore_1       
        //   203: goto            91
        //   206: iconst_0       
        //   207: istore_3       
        //   208: goto            154
        //   211: astore_1       
        //   212: aconst_null    
        //   213: areturn        
        //   214: astore_1       
        //   215: goto            194
        //   218: astore_1       
        //   219: goto            194
        //   222: astore_1       
        //   223: goto            194
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                   
        //  -----  -----  -----  -----  -----------------------------------------------------------------------
        //  21     41     177    193    Ljava/lang/Exception;
        //  83     91     222    226    Ljava/io/IOException;
        //  83     91     214    218    Ljava/lang/IllegalStateException;
        //  83     91     218    222    Lcom/google/android/gms/common/GooglePlayServicesNotAvailableException;
        //  83     91     193    194    Lcom/google/android/gms/common/GooglePlayServicesRepairableException;
        //  166    175    211    214    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 110, Size: 110
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
    
    protected static void zzb(final zzuu zzuu) {
        zzuu.zza("/loadAd", zzafa.zzcgg);
        zzuu.zza("/fetchHttpRequest", zzafa.zzcgf);
        zzuu.zza("/invalidRequest", zzafa.zzcgh);
    }
    
    private final zzaej zzc(final zzaef zzaef) {
        zzbv.zzek();
        final String zzrh = zzakk.zzrh();
        final JSONObject zza = this.zza(zzaef, zzrh);
        if (zza == null) {
            return new zzaej(0);
        }
        final long elapsedRealtime = zzbv.zzer().elapsedRealtime();
        final Future<JSONObject> zzas = zzafa.zzcgg.zzas(zzrh);
        zzamu.zzsy.post((Runnable)new zzafc(this, zza, zzrh));
        final long zzcgc = zzafa.zzcgc;
        final long elapsedRealtime2 = zzbv.zzer().elapsedRealtime();
        try {
            if (zzas.get(zzcgc - (elapsedRealtime2 - elapsedRealtime), TimeUnit.MILLISECONDS) == null) {
                return new zzaej(-1);
            }
            goto Label_0153;
        }
        catch (InterruptedException ex) {}
        catch (TimeoutException ex2) {
            return new zzaej(2);
        }
        catch (ExecutionException ex3) {
            return new zzaej(0);
        }
        catch (CancellationException ex4) {
            goto Label_0124;
        }
    }
    
    protected static void zzc(final zzuu zzuu) {
        zzuu.zzb("/loadAd", zzafa.zzcgg);
        zzuu.zzb("/fetchHttpRequest", zzafa.zzcgf);
        zzuu.zzb("/invalidRequest", zzafa.zzcgh);
    }
    
    @Override
    public final void onStop() {
        synchronized (this.zzbzh) {
            zzamu.zzsy.post((Runnable)new zzaff(this));
        }
    }
    
    @Override
    public final void zzdn() {
        zzakb.zzck("SdkLessAdLoaderBackgroundTask started.");
        final String zzab = zzbv.zzfh().zzab(this.mContext);
        final zzaef zzaef = new zzaef(this.zzccg, -1L, zzbv.zzfh().zzz(this.mContext), zzbv.zzfh().zzaa(this.mContext), zzab);
        zzbv.zzfh().zzg(this.mContext, zzab);
        final zzaej zzc = this.zzc(zzaef);
        zzamu.zzsy.post((Runnable)new zzafb(this, new zzaji(zzaef, zzc, null, null, zzc.errorCode, zzbv.zzer().elapsedRealtime(), zzc.zzceu, null, this.zzcci)));
    }
}
