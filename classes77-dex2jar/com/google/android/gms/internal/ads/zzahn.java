// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.gmsg.zzb;
import java.util.concurrent.Future;
import android.content.Context;

@zzadh
public final class zzahn extends zzajx implements zzaht, zzahw, zzaia
{
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock;
    public final String zzbth;
    private final zzaji zzbze;
    private final zzaib zzcll;
    private final zzahw zzclm;
    private final String zzcln;
    private final zzwx zzclo;
    private final long zzclp;
    private int zzclq;
    private zzahq zzclr;
    private Future zzcls;
    private volatile zzb zzclt;
    
    public zzahn(final Context mContext, final String zzbth, final String zzcln, final zzwx zzclo, final zzaji zzbze, final zzaib zzcll, final zzahw zzclm, final long zzclp) {
        this.zzclq = 0;
        this.mErrorCode = 3;
        this.mContext = mContext;
        this.zzbth = zzbth;
        this.zzcln = zzcln;
        this.zzclo = zzclo;
        this.zzbze = zzbze;
        this.zzcll = zzcll;
        this.mLock = new Object();
        this.zzclm = zzclm;
        this.zzclp = zzclp;
    }
    
    private final void zza(final zzjj zzjj, final zzxq zzxq) {
        this.zzcll.zzpf().zza((zzahw)this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbth)) {
                zzxq.zza(zzjj, this.zzcln, this.zzclo.zzbrr);
                return;
            }
            zzxq.zzc(zzjj, this.zzcln);
        }
        catch (RemoteException ex) {
            zzakb.zzc("Fail to load ad from adapter.", (Throwable)ex);
            this.zza(this.zzbth, 0);
        }
    }
    
    private final boolean zzf(long n) {
        n = this.zzclp - (zzbv.zzer().elapsedRealtime() - n);
        if (n <= 0L) {
            this.mErrorCode = 4;
            return false;
        }
        try {
            this.mLock.wait(n);
            return true;
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            this.mErrorCode = 5;
            return false;
        }
    }
    
    @Override
    public final void onStop() {
    }
    
    public final void zza(final zzb zzclt) {
        this.zzclt = zzclt;
    }
    
    @Override
    public final void zza(final String s, final int mErrorCode) {
        synchronized (this.mLock) {
            this.zzclq = 2;
            this.mErrorCode = mErrorCode;
            this.mLock.notify();
        }
    }
    
    @Override
    public final void zzac(final int n) {
        this.zza(this.zzbth, 0);
    }
    
    @Override
    public final void zzc(final Bundle bundle) {
        final zzb zzclt = this.zzclt;
        if (zzclt != null) {
            zzclt.zza("", bundle);
        }
    }
    
    @Override
    public final void zzcb(final String s) {
        synchronized (this.mLock) {
            this.zzclq = 1;
            this.mLock.notify();
        }
    }
    
    @Override
    public final void zzdn() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzahn.zzcll:Lcom/google/android/gms/internal/ads/zzaib;
        //     4: ifnull          27
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzahn.zzcll:Lcom/google/android/gms/internal/ads/zzaib;
        //    11: invokevirtual   com/google/android/gms/internal/ads/zzaib.zzpf:()Lcom/google/android/gms/internal/ads/zzahv;
        //    14: ifnull          27
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/internal/ads/zzahn.zzcll:Lcom/google/android/gms/internal/ads/zzaib;
        //    21: invokevirtual   com/google/android/gms/internal/ads/zzaib.zzpe:()Lcom/google/android/gms/internal/ads/zzxq;
        //    24: ifnonnull       28
        //    27: return         
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/internal/ads/zzahn.zzcll:Lcom/google/android/gms/internal/ads/zzaib;
        //    32: invokevirtual   com/google/android/gms/internal/ads/zzaib.zzpf:()Lcom/google/android/gms/internal/ads/zzahv;
        //    35: astore          4
        //    37: aload           4
        //    39: aconst_null    
        //    40: invokevirtual   com/google/android/gms/internal/ads/zzahv.zza:(Lcom/google/android/gms/internal/ads/zzahw;)V
        //    43: aload           4
        //    45: aload_0        
        //    46: invokevirtual   com/google/android/gms/internal/ads/zzahv.zza:(Lcom/google/android/gms/internal/ads/zzaht;)V
        //    49: aload           4
        //    51: aload_0        
        //    52: invokevirtual   com/google/android/gms/internal/ads/zzahv.zza:(Lcom/google/android/gms/internal/ads/zzaia;)V
        //    55: aload_0        
        //    56: getfield        com/google/android/gms/internal/ads/zzahn.zzbze:Lcom/google/android/gms/internal/ads/zzaji;
        //    59: getfield        com/google/android/gms/internal/ads/zzaji.zzcgs:Lcom/google/android/gms/internal/ads/zzaef;
        //    62: getfield        com/google/android/gms/internal/ads/zzaef.zzccv:Lcom/google/android/gms/internal/ads/zzjj;
        //    65: astore          5
        //    67: aload_0        
        //    68: getfield        com/google/android/gms/internal/ads/zzahn.zzcll:Lcom/google/android/gms/internal/ads/zzaib;
        //    71: invokevirtual   com/google/android/gms/internal/ads/zzaib.zzpe:()Lcom/google/android/gms/internal/ads/zzxq;
        //    74: astore          6
        //    76: aload           6
        //    78: invokeinterface com/google/android/gms/internal/ads/zzxq.isInitialized:()Z
        //    83: ifeq            230
        //    86: getstatic       com/google/android/gms/internal/ads/zzamu.zzsy:Landroid/os/Handler;
        //    89: new             Lcom/google/android/gms/internal/ads/zzaho;
        //    92: dup            
        //    93: aload_0        
        //    94: aload           5
        //    96: aload           6
        //    98: invokespecial   com/google/android/gms/internal/ads/zzaho.<init>:(Lcom/google/android/gms/internal/ads/zzahn;Lcom/google/android/gms/internal/ads/zzjj;Lcom/google/android/gms/internal/ads/zzxq;)V
        //   101: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   104: pop            
        //   105: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //   108: invokeinterface com/google/android/gms/common/util/Clock.elapsedRealtime:()J
        //   113: lstore_2       
        //   114: aload_0        
        //   115: getfield        com/google/android/gms/internal/ads/zzahn.mLock:Ljava/lang/Object;
        //   118: astore          5
        //   120: aload           5
        //   122: monitorenter   
        //   123: aload_0        
        //   124: getfield        com/google/android/gms/internal/ads/zzahn.zzclq:I
        //   127: ifeq            283
        //   130: new             Lcom/google/android/gms/internal/ads/zzahs;
        //   133: dup            
        //   134: invokespecial   com/google/android/gms/internal/ads/zzahs.<init>:()V
        //   137: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //   140: invokeinterface com/google/android/gms/common/util/Clock.elapsedRealtime:()J
        //   145: lload_2        
        //   146: lsub           
        //   147: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzg:(J)Lcom/google/android/gms/internal/ads/zzahs;
        //   150: astore          6
        //   152: iconst_1       
        //   153: aload_0        
        //   154: getfield        com/google/android/gms/internal/ads/zzahn.zzclq:I
        //   157: if_icmpne       275
        //   160: bipush          6
        //   162: istore_1       
        //   163: aload_0        
        //   164: aload           6
        //   166: iload_1        
        //   167: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzad:(I)Lcom/google/android/gms/internal/ads/zzahs;
        //   170: aload_0        
        //   171: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   174: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzcc:(Ljava/lang/String;)Lcom/google/android/gms/internal/ads/zzahs;
        //   177: aload_0        
        //   178: getfield        com/google/android/gms/internal/ads/zzahn.zzclo:Lcom/google/android/gms/internal/ads/zzwx;
        //   181: getfield        com/google/android/gms/internal/ads/zzwx.zzbru:Ljava/lang/String;
        //   184: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzcd:(Ljava/lang/String;)Lcom/google/android/gms/internal/ads/zzahs;
        //   187: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzpd:()Lcom/google/android/gms/internal/ads/zzahq;
        //   190: putfield        com/google/android/gms/internal/ads/zzahn.zzclr:Lcom/google/android/gms/internal/ads/zzahq;
        //   193: aload           5
        //   195: monitorexit    
        //   196: aload           4
        //   198: aconst_null    
        //   199: invokevirtual   com/google/android/gms/internal/ads/zzahv.zza:(Lcom/google/android/gms/internal/ads/zzahw;)V
        //   202: aload           4
        //   204: aconst_null    
        //   205: invokevirtual   com/google/android/gms/internal/ads/zzahv.zza:(Lcom/google/android/gms/internal/ads/zzaht;)V
        //   208: aload_0        
        //   209: getfield        com/google/android/gms/internal/ads/zzahn.zzclq:I
        //   212: iconst_1       
        //   213: if_icmpne       362
        //   216: aload_0        
        //   217: getfield        com/google/android/gms/internal/ads/zzahn.zzclm:Lcom/google/android/gms/internal/ads/zzahw;
        //   220: aload_0        
        //   221: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   224: invokeinterface com/google/android/gms/internal/ads/zzahw.zzcb:(Ljava/lang/String;)V
        //   229: return         
        //   230: getstatic       com/google/android/gms/internal/ads/zzamu.zzsy:Landroid/os/Handler;
        //   233: new             Lcom/google/android/gms/internal/ads/zzahp;
        //   236: dup            
        //   237: aload_0        
        //   238: aload           6
        //   240: aload           5
        //   242: aload           4
        //   244: invokespecial   com/google/android/gms/internal/ads/zzahp.<init>:(Lcom/google/android/gms/internal/ads/zzahn;Lcom/google/android/gms/internal/ads/zzxq;Lcom/google/android/gms/internal/ads/zzjj;Lcom/google/android/gms/internal/ads/zzahv;)V
        //   247: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   250: pop            
        //   251: goto            105
        //   254: astore          5
        //   256: ldc             "Fail to check if adapter is initialized."
        //   258: aload           5
        //   260: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   263: aload_0        
        //   264: aload_0        
        //   265: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   268: iconst_0       
        //   269: invokevirtual   com/google/android/gms/internal/ads/zzahn.zza:(Ljava/lang/String;I)V
        //   272: goto            105
        //   275: aload_0        
        //   276: getfield        com/google/android/gms/internal/ads/zzahn.mErrorCode:I
        //   279: istore_1       
        //   280: goto            163
        //   283: aload_0        
        //   284: lload_2        
        //   285: invokespecial   com/google/android/gms/internal/ads/zzahn.zzf:(J)Z
        //   288: ifne            356
        //   291: aload_0        
        //   292: new             Lcom/google/android/gms/internal/ads/zzahs;
        //   295: dup            
        //   296: invokespecial   com/google/android/gms/internal/ads/zzahs.<init>:()V
        //   299: aload_0        
        //   300: getfield        com/google/android/gms/internal/ads/zzahn.mErrorCode:I
        //   303: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzad:(I)Lcom/google/android/gms/internal/ads/zzahs;
        //   306: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //   309: invokeinterface com/google/android/gms/common/util/Clock.elapsedRealtime:()J
        //   314: lload_2        
        //   315: lsub           
        //   316: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzg:(J)Lcom/google/android/gms/internal/ads/zzahs;
        //   319: aload_0        
        //   320: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   323: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzcc:(Ljava/lang/String;)Lcom/google/android/gms/internal/ads/zzahs;
        //   326: aload_0        
        //   327: getfield        com/google/android/gms/internal/ads/zzahn.zzclo:Lcom/google/android/gms/internal/ads/zzwx;
        //   330: getfield        com/google/android/gms/internal/ads/zzwx.zzbru:Ljava/lang/String;
        //   333: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzcd:(Ljava/lang/String;)Lcom/google/android/gms/internal/ads/zzahs;
        //   336: invokevirtual   com/google/android/gms/internal/ads/zzahs.zzpd:()Lcom/google/android/gms/internal/ads/zzahq;
        //   339: putfield        com/google/android/gms/internal/ads/zzahn.zzclr:Lcom/google/android/gms/internal/ads/zzahq;
        //   342: aload           5
        //   344: monitorexit    
        //   345: goto            196
        //   348: astore          4
        //   350: aload           5
        //   352: monitorexit    
        //   353: aload           4
        //   355: athrow         
        //   356: aload           5
        //   358: monitorexit    
        //   359: goto            114
        //   362: aload_0        
        //   363: getfield        com/google/android/gms/internal/ads/zzahn.zzclm:Lcom/google/android/gms/internal/ads/zzahw;
        //   366: aload_0        
        //   367: getfield        com/google/android/gms/internal/ads/zzahn.zzbth:Ljava/lang/String;
        //   370: aload_0        
        //   371: getfield        com/google/android/gms/internal/ads/zzahn.mErrorCode:I
        //   374: invokeinterface com/google/android/gms/internal/ads/zzahw.zza:(Ljava/lang/String;I)V
        //   379: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  76     105    254    275    Landroid/os/RemoteException;
        //  123    160    348    356    Any
        //  163    196    348    356    Any
        //  230    251    254    275    Landroid/os/RemoteException;
        //  275    280    348    356    Any
        //  283    345    348    356    Any
        //  350    353    348    356    Any
        //  356    359    348    356    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163:
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
    
    public final Future zzoz() {
        if (this.zzcls != null) {
            return this.zzcls;
        }
        return this.zzcls = (zzanz)this.zznt();
    }
    
    public final zzahq zzpa() {
        synchronized (this.mLock) {
            return this.zzclr;
        }
    }
    
    public final zzwx zzpb() {
        return this.zzclo;
    }
    
    @Override
    public final void zzpc() {
        this.zza(this.zzbze.zzcgs.zzccv, this.zzcll.zzpe());
    }
}
