// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.List;
import java.util.concurrent.Executors;
import android.os.Environment;
import java.util.Iterator;
import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.BlockingQueue;
import com.google.android.gms.common.util.VisibleForTesting;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznn
{
    @VisibleForTesting
    private Context mContext;
    @VisibleForTesting
    private String zzaej;
    @VisibleForTesting
    private String zzbfx;
    @VisibleForTesting
    private BlockingQueue<zznx> zzbfz;
    @VisibleForTesting
    private ExecutorService zzbga;
    @VisibleForTesting
    private LinkedHashMap<String, String> zzbgb;
    @VisibleForTesting
    private Map<String, zznr> zzbgc;
    private AtomicBoolean zzbgd;
    private File zzbge;
    
    public zznn() {
        this.zzbfz = new ArrayBlockingQueue<zznx>(100);
        this.zzbgb = new LinkedHashMap<String, String>();
        this.zzbgc = new HashMap<String, zznr>();
    }
    
    private final void zzjf() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zznn.zzbfz:Ljava/util/concurrent/BlockingQueue;
        //     4: invokeinterface java/util/concurrent/BlockingQueue.take:()Ljava/lang/Object;
        //     9: checkcast       Lcom/google/android/gms/internal/ads/zznx;
        //    12: astore_2       
        //    13: aload_2        
        //    14: invokevirtual   com/google/android/gms/internal/ads/zznx.zzjk:()Ljava/lang/String;
        //    17: astore_1       
        //    18: aload_1        
        //    19: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    22: ifne            0
        //    25: aload_0        
        //    26: aload_0        
        //    27: getfield        com/google/android/gms/internal/ads/zznn.zzbgb:Ljava/util/LinkedHashMap;
        //    30: aload_2        
        //    31: invokevirtual   com/google/android/gms/internal/ads/zznx.zzjl:()Ljava/util/Map;
        //    34: invokevirtual   com/google/android/gms/internal/ads/zznn.zza:(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;
        //    37: astore_3       
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/internal/ads/zznn.zzbfx:Ljava/lang/String;
        //    42: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //    45: invokevirtual   android/net/Uri.buildUpon:()Landroid/net/Uri$Builder;
        //    48: astore_2       
        //    49: aload_3        
        //    50: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //    55: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    60: astore_3       
        //    61: aload_3        
        //    62: invokeinterface java/util/Iterator.hasNext:()Z
        //    67: ifeq            117
        //    70: aload_3        
        //    71: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    76: checkcast       Ljava/util/Map$Entry;
        //    79: astore          4
        //    81: aload_2        
        //    82: aload           4
        //    84: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //    89: checkcast       Ljava/lang/String;
        //    92: aload           4
        //    94: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //    99: checkcast       Ljava/lang/String;
        //   102: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   105: pop            
        //   106: goto            61
        //   109: astore_1       
        //   110: ldc             "CsiReporter:reporter interrupted"
        //   112: aload_1        
        //   113: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   116: return         
        //   117: new             Ljava/lang/StringBuilder;
        //   120: dup            
        //   121: aload_2        
        //   122: invokevirtual   android/net/Uri$Builder.build:()Landroid/net/Uri;
        //   125: invokevirtual   android/net/Uri.toString:()Ljava/lang/String;
        //   128: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   131: astore_2       
        //   132: aload_2        
        //   133: ldc             "&it="
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: aload_1        
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: pop            
        //   143: aload_2        
        //   144: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   147: astore_3       
        //   148: aload_0        
        //   149: getfield        com/google/android/gms/internal/ads/zznn.zzbgd:Ljava/util/concurrent/atomic/AtomicBoolean;
        //   152: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
        //   155: ifeq            275
        //   158: aload_0        
        //   159: getfield        com/google/android/gms/internal/ads/zznn.zzbge:Ljava/io/File;
        //   162: astore_1       
        //   163: aload_1        
        //   164: ifnull          267
        //   167: new             Ljava/io/FileOutputStream;
        //   170: dup            
        //   171: aload_1        
        //   172: iconst_1       
        //   173: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;Z)V
        //   176: astore_2       
        //   177: aload_2        
        //   178: astore_1       
        //   179: aload_2        
        //   180: aload_3        
        //   181: invokevirtual   java/lang/String.getBytes:()[B
        //   184: invokevirtual   java/io/FileOutputStream.write:([B)V
        //   187: aload_2        
        //   188: astore_1       
        //   189: aload_2        
        //   190: bipush          10
        //   192: invokevirtual   java/io/FileOutputStream.write:(I)V
        //   195: aload_2        
        //   196: invokevirtual   java/io/FileOutputStream.close:()V
        //   199: goto            0
        //   202: astore_1       
        //   203: ldc             "CsiReporter: Cannot close file: sdk_csi_data.txt."
        //   205: aload_1        
        //   206: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   209: goto            0
        //   212: astore_3       
        //   213: aconst_null    
        //   214: astore_2       
        //   215: aload_2        
        //   216: astore_1       
        //   217: ldc             "CsiReporter: Cannot write to file: sdk_csi_data.txt."
        //   219: aload_3        
        //   220: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   223: aload_2        
        //   224: ifnull          0
        //   227: aload_2        
        //   228: invokevirtual   java/io/FileOutputStream.close:()V
        //   231: goto            0
        //   234: astore_1       
        //   235: ldc             "CsiReporter: Cannot close file: sdk_csi_data.txt."
        //   237: aload_1        
        //   238: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   241: goto            0
        //   244: astore_2       
        //   245: aconst_null    
        //   246: astore_1       
        //   247: aload_1        
        //   248: ifnull          255
        //   251: aload_1        
        //   252: invokevirtual   java/io/FileOutputStream.close:()V
        //   255: aload_2        
        //   256: athrow         
        //   257: astore_1       
        //   258: ldc             "CsiReporter: Cannot close file: sdk_csi_data.txt."
        //   260: aload_1        
        //   261: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   264: goto            255
        //   267: ldc             "CsiReporter: File doesn't exists. Cannot write CSI data to file."
        //   269: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   272: goto            0
        //   275: invokestatic    com/google/android/gms/ads/internal/zzbv.zzek:()Lcom/google/android/gms/internal/ads/zzakk;
        //   278: pop            
        //   279: aload_0        
        //   280: getfield        com/google/android/gms/internal/ads/zznn.mContext:Landroid/content/Context;
        //   283: aload_0        
        //   284: getfield        com/google/android/gms/internal/ads/zznn.zzaej:Ljava/lang/String;
        //   287: aload_3        
        //   288: invokestatic    com/google/android/gms/internal/ads/zzakk.zzd:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
        //   291: goto            0
        //   294: astore_2       
        //   295: goto            247
        //   298: astore_3       
        //   299: goto            215
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      18     109    117    Ljava/lang/InterruptedException;
        //  167    177    212    215    Ljava/io/IOException;
        //  167    177    244    247    Any
        //  179    187    298    302    Ljava/io/IOException;
        //  179    187    294    298    Any
        //  189    195    298    302    Ljava/io/IOException;
        //  189    195    294    298    Any
        //  195    199    202    212    Ljava/io/IOException;
        //  217    223    294    298    Any
        //  227    231    234    244    Ljava/io/IOException;
        //  251    255    257    267    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0215:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    final Map<String, String> zza(final Map<String, String> map, @Nullable final Map<String, String> map2) {
        final LinkedHashMap<Object, String> linkedHashMap = new LinkedHashMap<Object, String>(map);
        if (map2 == null) {
            return (Map<String, String>)linkedHashMap;
        }
        for (final Map.Entry<String, String> entry : map2.entrySet()) {
            final String s = entry.getKey();
            linkedHashMap.put(s, this.zzal(s).zzd(linkedHashMap.get(s), entry.getValue()));
        }
        return (Map<String, String>)linkedHashMap;
    }
    
    public final void zza(final Context mContext, final String zzaej, final String zzbfx, final Map<String, String> map) {
        this.mContext = mContext;
        this.zzaej = zzaej;
        this.zzbfx = zzbfx;
        (this.zzbgd = new AtomicBoolean(false)).set((boolean)zzkb.zzik().zzd(zznk.zzawj));
        if (this.zzbgd.get()) {
            final File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.zzbge = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            this.zzbgb.put(entry.getKey(), entry.getValue());
        }
        (this.zzbga = Executors.newSingleThreadExecutor()).execute(new zzno(this));
        this.zzbgc.put("action", zznr.zzbgh);
        this.zzbgc.put("ad_format", zznr.zzbgh);
        this.zzbgc.put("e", zznr.zzbgi);
    }
    
    public final boolean zza(final zznx zznx) {
        return this.zzbfz.offer(zznx);
    }
    
    public final zznr zzal(final String s) {
        final zznr zznr = this.zzbgc.get(s);
        if (zznr != null) {
            return zznr;
        }
        return com.google.android.gms.internal.ads.zznr.zzbgg;
    }
    
    public final void zzg(@Nullable final List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzbgb.put("e", TextUtils.join((CharSequence)",", (Iterable)list));
        }
    }
}
