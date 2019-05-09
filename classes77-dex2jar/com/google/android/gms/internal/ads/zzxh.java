// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;

@zzadh
public final class zzxh implements zzww
{
    private final Context mContext;
    private final Object mLock;
    private final long mStartTime;
    private final boolean zzael;
    private final zzwy zzbtj;
    private final boolean zzbtn;
    private final boolean zzbto;
    private final zzaef zzbuc;
    private final long zzbud;
    private final int zzbue;
    private boolean zzbuf;
    private final Map<zzanz<zzxe>, zzxb> zzbug;
    private final String zzbuh;
    private List<zzxe> zzbui;
    private final zzxn zzwh;
    
    public zzxh(final Context mContext, final zzaef zzbuc, final zzxn zzwh, final zzwy zzbtj, final boolean zzael, final boolean zzbtn, final String zzbuh, final long mStartTime, final long zzbud, final int n, final boolean zzbto) {
        this.mLock = new Object();
        this.zzbuf = false;
        this.zzbug = new HashMap<zzanz<zzxe>, zzxb>();
        this.zzbui = new ArrayList<zzxe>();
        this.mContext = mContext;
        this.zzbuc = zzbuc;
        this.zzwh = zzwh;
        this.zzbtj = zzbtj;
        this.zzael = zzael;
        this.zzbtn = zzbtn;
        this.zzbuh = zzbuh;
        this.mStartTime = mStartTime;
        this.zzbud = zzbud;
        this.zzbue = 2;
        this.zzbto = zzbto;
    }
    
    private final void zza(final zzanz<zzxe> zzanz) {
        zzakk.zzcrm.post((Runnable)new zzxj(this, zzanz));
    }
    
    private final zzxe zzi(List<zzanz<zzxe>> zzanz) {
        Object o = this.mLock;
        // monitorenter(o)
        try {
            if (this.zzbuf) {
                return new zzxe(-1);
            }
            // monitorexit(o)
            o = ((List<zzanz<zzxe>>)zzanz).iterator();
            while (((Iterator)o).hasNext()) {
                zzanz = ((Iterator<zzanz<zzxe>>)o).next();
                try {
                    final zzxe zzxe = zzanz.get();
                    this.zzbui.add(zzxe);
                    if (zzxe != null && zzxe.zzbtv == 0) {
                        this.zza(zzanz);
                        return zzxe;
                    }
                    continue;
                }
                catch (InterruptedException ex) {}
                catch (ExecutionException zzanz) {}
            }
            goto Label_0109;
        }
        finally {}
    }
    
    private final zzxe zzj(final List<zzanz<zzxe>> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzxh.mLock:Ljava/lang/Object;
        //     4: astore          8
        //     6: aload           8
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        com/google/android/gms/internal/ads/zzxh.zzbuf:Z
        //    13: ifeq            30
        //    16: new             Lcom/google/android/gms/internal/ads/zzxe;
        //    19: dup            
        //    20: iconst_m1      
        //    21: invokespecial   com/google/android/gms/internal/ads/zzxe.<init>:(I)V
        //    24: astore_1       
        //    25: aload           8
        //    27: monitorexit    
        //    28: aload_1        
        //    29: areturn        
        //    30: aload           8
        //    32: monitorexit    
        //    33: iconst_m1      
        //    34: istore_2       
        //    35: aconst_null    
        //    36: astore          8
        //    38: aconst_null    
        //    39: astore          9
        //    41: aload_0        
        //    42: getfield        com/google/android/gms/internal/ads/zzxh.zzbtj:Lcom/google/android/gms/internal/ads/zzwy;
        //    45: getfield        com/google/android/gms/internal/ads/zzwy.zzbsy:J
        //    48: ldc2_w          -1
        //    51: lcmp           
        //    52: ifeq            222
        //    55: aload_0        
        //    56: getfield        com/google/android/gms/internal/ads/zzxh.zzbtj:Lcom/google/android/gms/internal/ads/zzwy;
        //    59: getfield        com/google/android/gms/internal/ads/zzwy.zzbsy:J
        //    62: lstore          4
        //    64: aload_1        
        //    65: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    70: astore          11
        //    72: aload           11
        //    74: invokeinterface java/util/Iterator.hasNext:()Z
        //    79: ifeq            301
        //    82: aload           11
        //    84: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    89: checkcast       Lcom/google/android/gms/internal/ads/zzanz;
        //    92: astore          10
        //    94: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //    97: invokeinterface com/google/android/gms/common/util/Clock.currentTimeMillis:()J
        //   102: lstore          6
        //   104: lload           4
        //   106: lconst_0       
        //   107: lcmp           
        //   108: ifne            230
        //   111: aload           10
        //   113: invokeinterface com/google/android/gms/internal/ads/zzanz.isDone:()Z
        //   118: ifeq            230
        //   121: aload           10
        //   123: invokeinterface com/google/android/gms/internal/ads/zzanz.get:()Ljava/lang/Object;
        //   128: checkcast       Lcom/google/android/gms/internal/ads/zzxe;
        //   131: astore_1       
        //   132: aload_0        
        //   133: getfield        com/google/android/gms/internal/ads/zzxh.zzbui:Ljava/util/List;
        //   136: aload_1        
        //   137: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   142: pop            
        //   143: aload_1        
        //   144: ifnull          333
        //   147: aload_1        
        //   148: getfield        com/google/android/gms/internal/ads/zzxe.zzbtv:I
        //   151: ifne            333
        //   154: aload_1        
        //   155: getfield        com/google/android/gms/internal/ads/zzxe.zzbua:Lcom/google/android/gms/internal/ads/zzxw;
        //   158: astore          12
        //   160: aload           12
        //   162: ifnull          333
        //   165: aload           12
        //   167: invokeinterface com/google/android/gms/internal/ads/zzxw.zzmm:()I
        //   172: iload_2        
        //   173: if_icmple       333
        //   176: aload           12
        //   178: invokeinterface com/google/android/gms/internal/ads/zzxw.zzmm:()I
        //   183: istore_3       
        //   184: iload_3        
        //   185: istore_2       
        //   186: aload           10
        //   188: astore          8
        //   190: lload           4
        //   192: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //   195: invokeinterface com/google/android/gms/common/util/Clock.currentTimeMillis:()J
        //   200: lload           6
        //   202: lsub           
        //   203: lsub           
        //   204: lconst_0       
        //   205: invokestatic    java/lang/Math.max:(JJ)J
        //   208: lstore          4
        //   210: aload_1        
        //   211: astore          9
        //   213: goto            72
        //   216: astore_1       
        //   217: aload           8
        //   219: monitorexit    
        //   220: aload_1        
        //   221: athrow         
        //   222: ldc2_w          10000
        //   225: lstore          4
        //   227: goto            64
        //   230: aload           10
        //   232: lload           4
        //   234: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //   237: invokeinterface com/google/android/gms/internal/ads/zzanz.get:(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
        //   242: checkcast       Lcom/google/android/gms/internal/ads/zzxe;
        //   245: astore_1       
        //   246: goto            132
        //   249: astore_1       
        //   250: ldc             "Exception while processing an adapter; continuing with other adapters"
        //   252: aload_1        
        //   253: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   256: lload           4
        //   258: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //   261: invokeinterface com/google/android/gms/common/util/Clock.currentTimeMillis:()J
        //   266: lload           6
        //   268: lsub           
        //   269: lsub           
        //   270: lconst_0       
        //   271: invokestatic    java/lang/Math.max:(JJ)J
        //   274: lstore          4
        //   276: goto            72
        //   279: astore_1       
        //   280: lload           4
        //   282: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //   285: invokeinterface com/google/android/gms/common/util/Clock.currentTimeMillis:()J
        //   290: lload           6
        //   292: lsub           
        //   293: lsub           
        //   294: lconst_0       
        //   295: invokestatic    java/lang/Math.max:(JJ)J
        //   298: pop2           
        //   299: aload_1        
        //   300: athrow         
        //   301: aload_0        
        //   302: aload           8
        //   304: invokespecial   com/google/android/gms/internal/ads/zzxh.zza:(Lcom/google/android/gms/internal/ads/zzanz;)V
        //   307: aload           9
        //   309: ifnonnull       339
        //   312: new             Lcom/google/android/gms/internal/ads/zzxe;
        //   315: dup            
        //   316: iconst_1       
        //   317: invokespecial   com/google/android/gms/internal/ads/zzxe.<init>:(I)V
        //   320: areturn        
        //   321: astore_1       
        //   322: goto            250
        //   325: astore_1       
        //   326: goto            250
        //   329: astore_1       
        //   330: goto            250
        //   333: aload           9
        //   335: astore_1       
        //   336: goto            190
        //   339: aload           9
        //   341: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/google/android/gms/internal/ads/zzanz<Lcom/google/android/gms/internal/ads/zzxe;>;>;)Lcom/google/android/gms/internal/ads/zzxe;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  9      28     216    222    Any
        //  30     33     216    222    Any
        //  111    132    321    325    Ljava/lang/InterruptedException;
        //  111    132    325    329    Ljava/util/concurrent/ExecutionException;
        //  111    132    249    250    Landroid/os/RemoteException;
        //  111    132    329    333    Ljava/util/concurrent/TimeoutException;
        //  111    132    279    301    Any
        //  132    143    321    325    Ljava/lang/InterruptedException;
        //  132    143    325    329    Ljava/util/concurrent/ExecutionException;
        //  132    143    249    250    Landroid/os/RemoteException;
        //  132    143    329    333    Ljava/util/concurrent/TimeoutException;
        //  132    143    279    301    Any
        //  147    160    321    325    Ljava/lang/InterruptedException;
        //  147    160    325    329    Ljava/util/concurrent/ExecutionException;
        //  147    160    249    250    Landroid/os/RemoteException;
        //  147    160    329    333    Ljava/util/concurrent/TimeoutException;
        //  147    160    279    301    Any
        //  165    184    321    325    Ljava/lang/InterruptedException;
        //  165    184    325    329    Ljava/util/concurrent/ExecutionException;
        //  165    184    249    250    Landroid/os/RemoteException;
        //  165    184    329    333    Ljava/util/concurrent/TimeoutException;
        //  165    184    279    301    Any
        //  217    220    216    222    Any
        //  230    246    321    325    Ljava/lang/InterruptedException;
        //  230    246    325    329    Ljava/util/concurrent/ExecutionException;
        //  230    246    249    250    Landroid/os/RemoteException;
        //  230    246    329    333    Ljava/util/concurrent/TimeoutException;
        //  230    246    279    301    Any
        //  250    256    279    301    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0132:
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
    
    @Override
    public final void cancel() {
        synchronized (this.mLock) {
            this.zzbuf = true;
            final Iterator<zzxb> iterator = this.zzbug.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().cancel();
            }
        }
    }
    // monitorexit(o)
    
    @Override
    public final zzxe zzh(final List<zzwx> list) {
        zzakb.zzck("Starting mediation.");
        final ArrayList<zzanz<zzxe>> list2 = new ArrayList<zzanz<zzxe>>();
        zzjn zzacv = this.zzbuc.zzacv;
        final int[] array = new int[2];
        if (zzacv.zzard != null) {
            zzbv.zzfd();
            if (zzxg.zza(this.zzbuh, array)) {
                final int n = array[0];
                final int n2 = array[1];
                final zzjn[] zzard = zzacv.zzard;
                for (int length = zzard.length, i = 0; i < length; ++i) {
                    final zzjn zzjn = zzard[i];
                    if (n == zzjn.width && n2 == zzjn.height) {
                        zzacv = zzjn;
                        break;
                    }
                }
            }
        }
        while (true) {
            for (final zzwx zzwx : list) {
                final String value = String.valueOf(zzwx.zzbrs);
                String concat;
                if (value.length() != 0) {
                    concat = "Trying mediation network: ".concat(value);
                }
                else {
                    concat = new String("Trying mediation network: ");
                }
                zzakb.zzdj(concat);
                final Iterator<String> iterator2 = zzwx.zzbrt.iterator();
                while (iterator2.hasNext()) {
                    final zzxb zzxb = new zzxb(this.mContext, iterator2.next(), this.zzwh, this.zzbtj, zzwx, this.zzbuc.zzccv, zzacv, this.zzbuc.zzacr, this.zzael, this.zzbtn, this.zzbuc.zzadj, this.zzbuc.zzads, this.zzbuc.zzcdk, this.zzbuc.zzcef, this.zzbto);
                    final zzanz<zzxe> zza = zzaki.zza((Callable<zzxe>)new zzxi(this, zzxb));
                    this.zzbug.put(zza, zzxb);
                    list2.add(zza);
                }
            }
            switch (this.zzbue) {
                default: {
                    return this.zzi(list2);
                }
                case 2: {
                    return this.zzj(list2);
                }
            }
            continue;
        }
    }
    
    @Override
    public final List<zzxe> zzme() {
        return this.zzbui;
    }
}
