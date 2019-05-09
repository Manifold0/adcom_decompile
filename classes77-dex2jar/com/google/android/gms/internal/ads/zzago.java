// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.support.annotation.NonNull;
import android.content.Context;
import java.util.Iterator;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import com.google.android.gms.ads.internal.gmsg.zzb;
import java.util.Map;
import com.google.android.gms.ads.internal.zzbw;

@zzadh
public final class zzago
{
    private static final zzxm zzcku;
    private final zzxn zzckv;
    private final zzbw zzckw;
    private final Map<String, zzaib> zzckx;
    private final zzahu zzcky;
    private final zzb zzckz;
    private final zzabm zzcla;
    
    static {
        zzcku = new zzxm();
    }
    
    public zzago(final zzbw zzckw, final zzxn zzckv, final zzahu zzcky, final zzb zzckz, final zzabm zzcla) {
        this.zzckx = new HashMap<String, zzaib>();
        this.zzckw = zzckw;
        this.zzckv = zzckv;
        this.zzcky = zzcky;
        this.zzckz = zzckz;
        this.zzcla = zzcla;
    }
    
    public static boolean zza(final zzajh zzajh, final zzajh zzajh2) {
        return true;
    }
    
    public final void destroy() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        for (final String s : this.zzckx.keySet()) {
            try {
                final zzaib zzaib = this.zzckx.get(s);
                if (zzaib == null || zzaib.zzpe() == null) {
                    continue;
                }
                zzaib.zzpe().destroy();
            }
            catch (RemoteException ex) {
                zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
            }
        }
    }
    
    public final void onContextChanged(@NonNull final Context context) {
        for (final zzaib zzaib : this.zzckx.values()) {
            try {
                zzaib.zzpe().zzi(ObjectWrapper.wrap((Object)context));
            }
            catch (RemoteException ex) {
                zzakb.zzb("Unable to call Adapter.onContextChanged.", (Throwable)ex);
            }
        }
    }
    
    public final void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        for (final String s : this.zzckx.keySet()) {
            try {
                final zzaib zzaib = this.zzckx.get(s);
                if (zzaib == null || zzaib.zzpe() == null) {
                    continue;
                }
                zzaib.zzpe().pause();
            }
            catch (RemoteException ex) {
                zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
            }
        }
    }
    
    public final void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        for (final String s : this.zzckx.keySet()) {
            try {
                final zzaib zzaib = this.zzckx.get(s);
                if (zzaib == null || zzaib.zzpe() == null) {
                    continue;
                }
                zzaib.zzpe().resume();
            }
            catch (RemoteException ex) {
                zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
            }
        }
    }
    
    @Nullable
    public final zzaib zzca(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzago.zzckx:Ljava/util/Map;
        //     4: aload_1        
        //     5: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    10: checkcast       Lcom/google/android/gms/internal/ads/zzaib;
        //    13: astore_2       
        //    14: aload_2        
        //    15: astore_3       
        //    16: aload_2        
        //    17: ifnonnull       69
        //    20: aload_0        
        //    21: getfield        com/google/android/gms/internal/ads/zzago.zzckv:Lcom/google/android/gms/internal/ads/zzxn;
        //    24: astore_3       
        //    25: ldc             "com.google.ads.mediation.admob.AdMobAdapter"
        //    27: aload_1        
        //    28: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    31: ifeq            121
        //    34: getstatic       com/google/android/gms/internal/ads/zzago.zzcku:Lcom/google/android/gms/internal/ads/zzxm;
        //    37: astore_3       
        //    38: new             Lcom/google/android/gms/internal/ads/zzaib;
        //    41: dup            
        //    42: aload_3        
        //    43: aload_1        
        //    44: invokeinterface com/google/android/gms/internal/ads/zzxn.zzbm:(Ljava/lang/String;)Lcom/google/android/gms/internal/ads/zzxq;
        //    49: aload_0        
        //    50: getfield        com/google/android/gms/internal/ads/zzago.zzcky:Lcom/google/android/gms/internal/ads/zzahu;
        //    53: invokespecial   com/google/android/gms/internal/ads/zzaib.<init>:(Lcom/google/android/gms/internal/ads/zzxq;Lcom/google/android/gms/internal/ads/zzahu;)V
        //    56: astore_3       
        //    57: aload_0        
        //    58: getfield        com/google/android/gms/internal/ads/zzago.zzckx:Ljava/util/Map;
        //    61: aload_1        
        //    62: aload_3        
        //    63: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    68: pop            
        //    69: aload_3        
        //    70: areturn        
        //    71: astore_3       
        //    72: aload_1        
        //    73: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    76: astore_1       
        //    77: aload_1        
        //    78: invokevirtual   java/lang/String.length:()I
        //    81: ifeq            98
        //    84: ldc             "Fail to instantiate adapter "
        //    86: aload_1        
        //    87: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    90: astore_1       
        //    91: aload_1        
        //    92: aload_3        
        //    93: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    96: aload_2        
        //    97: areturn        
        //    98: new             Ljava/lang/String;
        //   101: dup            
        //   102: ldc             "Fail to instantiate adapter "
        //   104: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   107: astore_1       
        //   108: goto            91
        //   111: astore          4
        //   113: aload_3        
        //   114: astore_2       
        //   115: aload           4
        //   117: astore_3       
        //   118: goto            72
        //   121: goto            38
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  20     38     71     72     Ljava/lang/Exception;
        //  38     57     71     72     Ljava/lang/Exception;
        //  57     69     111    121    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    
    public final zzaig zzd(final zzaig zzaig) {
        zzaig zzaig2 = zzaig;
        if (this.zzckw.zzacw != null) {
            zzaig2 = zzaig;
            if (this.zzckw.zzacw.zzcod != null) {
                zzaig2 = zzaig;
                if (!TextUtils.isEmpty((CharSequence)this.zzckw.zzacw.zzcod.zzbsv)) {
                    zzaig2 = new zzaig(this.zzckw.zzacw.zzcod.zzbsv, this.zzckw.zzacw.zzcod.zzbsw);
                }
            }
        }
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw.zzbtw.zzbsd, this.zzckw.zzadr, zzaig2);
        }
        return zzaig2;
    }
    
    public final zzb zzos() {
        return this.zzckz;
    }
    
    public final zzabm zzot() {
        return this.zzcla;
    }
    
    public final void zzou() {
        this.zzckw.zzadv = 0;
        final zzbw zzckw = this.zzckw;
        zzbv.zzej();
        final zzahx zzacu = new zzahx(this.zzckw.zzrt, this.zzckw.zzacx, this);
        final String value = String.valueOf(zzacu.getClass().getName());
        String concat;
        if (value.length() != 0) {
            concat = "AdRenderer: ".concat(value);
        }
        else {
            concat = new String("AdRenderer: ");
        }
        zzakb.zzck(concat);
        zzacu.zznt();
        zzckw.zzacu = zzacu;
    }
    
    public final void zzov() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbsc);
        }
    }
    
    public final void zzow() {
        if (this.zzckw.zzacw != null && this.zzckw.zzacw.zzbtw != null) {
            zzbv.zzfd();
            zzxg.zza(this.zzckw.zzrt, this.zzckw.zzacr.zzcw, this.zzckw.zzacw, this.zzckw.zzacp, false, this.zzckw.zzacw.zzbtw.zzbse);
        }
    }
    
    public final void zzw(final boolean immersiveMode) {
        final zzaib zzca = this.zzca(this.zzckw.zzacw.zzbty);
        if (zzca == null || zzca.zzpe() == null) {
            return;
        }
        try {
            zzca.zzpe().setImmersiveMode(immersiveMode);
            zzca.zzpe().showVideo();
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
