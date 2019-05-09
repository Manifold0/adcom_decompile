// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import com.google.android.gms.ads.internal.zzbv;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.content.Context;

@zzadh
public final class zzapx
{
    private final Context mContext;
    private final String zzchp;
    @Nullable
    private final zznx zzcxo;
    private boolean zzcxs;
    @Nullable
    private final zznv zzdad;
    private final zzalp zzdae;
    private final long[] zzdaf;
    private final String[] zzdag;
    private boolean zzdah;
    private boolean zzdai;
    private boolean zzdaj;
    private boolean zzdak;
    private zzapg zzdal;
    private boolean zzdam;
    private boolean zzdan;
    private long zzdao;
    private final zzang zzzw;
    
    public zzapx(final Context p0, final zzang p1, final String p2, @Nullable final zznx p3, @Nullable final zznv p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: new             Lcom/google/android/gms/internal/ads/zzals;
        //     8: dup            
        //     9: invokespecial   com/google/android/gms/internal/ads/zzals.<init>:()V
        //    12: ldc             "min_1"
        //    14: ldc2_w          4.9E-324
        //    17: dconst_1       
        //    18: invokevirtual   com/google/android/gms/internal/ads/zzals.zza:(Ljava/lang/String;DD)Lcom/google/android/gms/internal/ads/zzals;
        //    21: ldc             "1_5"
        //    23: dconst_1       
        //    24: ldc2_w          5.0
        //    27: invokevirtual   com/google/android/gms/internal/ads/zzals.zza:(Ljava/lang/String;DD)Lcom/google/android/gms/internal/ads/zzals;
        //    30: ldc             "5_10"
        //    32: ldc2_w          5.0
        //    35: ldc2_w          10.0
        //    38: invokevirtual   com/google/android/gms/internal/ads/zzals.zza:(Ljava/lang/String;DD)Lcom/google/android/gms/internal/ads/zzals;
        //    41: ldc             "10_20"
        //    43: ldc2_w          10.0
        //    46: ldc2_w          20.0
        //    49: invokevirtual   com/google/android/gms/internal/ads/zzals.zza:(Ljava/lang/String;DD)Lcom/google/android/gms/internal/ads/zzals;
        //    52: ldc             "20_30"
        //    54: ldc2_w          20.0
        //    57: ldc2_w          30.0
        //    60: invokevirtual   com/google/android/gms/internal/ads/zzals.zza:(Ljava/lang/String;DD)Lcom/google/android/gms/internal/ads/zzals;
        //    63: ldc             "30_max"
        //    65: ldc2_w          30.0
        //    68: ldc2_w          1.7976931348623157E308
        //    71: invokevirtual   com/google/android/gms/internal/ads/zzals.zza:(Ljava/lang/String;DD)Lcom/google/android/gms/internal/ads/zzals;
        //    74: invokevirtual   com/google/android/gms/internal/ads/zzals.zzrz:()Lcom/google/android/gms/internal/ads/zzalp;
        //    77: putfield        com/google/android/gms/internal/ads/zzapx.zzdae:Lcom/google/android/gms/internal/ads/zzalp;
        //    80: aload_0        
        //    81: iconst_0       
        //    82: putfield        com/google/android/gms/internal/ads/zzapx.zzdah:Z
        //    85: aload_0        
        //    86: iconst_0       
        //    87: putfield        com/google/android/gms/internal/ads/zzapx.zzdai:Z
        //    90: aload_0        
        //    91: iconst_0       
        //    92: putfield        com/google/android/gms/internal/ads/zzapx.zzdaj:Z
        //    95: aload_0        
        //    96: iconst_0       
        //    97: putfield        com/google/android/gms/internal/ads/zzapx.zzdak:Z
        //   100: aload_0        
        //   101: ldc2_w          -1
        //   104: putfield        com/google/android/gms/internal/ads/zzapx.zzdao:J
        //   107: aload_0        
        //   108: aload_1        
        //   109: putfield        com/google/android/gms/internal/ads/zzapx.mContext:Landroid/content/Context;
        //   112: aload_0        
        //   113: aload_2        
        //   114: putfield        com/google/android/gms/internal/ads/zzapx.zzzw:Lcom/google/android/gms/internal/ads/zzang;
        //   117: aload_0        
        //   118: aload_3        
        //   119: putfield        com/google/android/gms/internal/ads/zzapx.zzchp:Ljava/lang/String;
        //   122: aload_0        
        //   123: aload           4
        //   125: putfield        com/google/android/gms/internal/ads/zzapx.zzcxo:Lcom/google/android/gms/internal/ads/zznx;
        //   128: aload_0        
        //   129: aload           5
        //   131: putfield        com/google/android/gms/internal/ads/zzapx.zzdad:Lcom/google/android/gms/internal/ads/zznv;
        //   134: getstatic       com/google/android/gms/internal/ads/zznk.zzave:Lcom/google/android/gms/internal/ads/zzna;
        //   137: astore_1       
        //   138: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //   141: aload_1        
        //   142: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //   145: checkcast       Ljava/lang/String;
        //   148: astore_1       
        //   149: aload_1        
        //   150: ifnonnull       169
        //   153: aload_0        
        //   154: iconst_0       
        //   155: anewarray       Ljava/lang/String;
        //   158: putfield        com/google/android/gms/internal/ads/zzapx.zzdag:[Ljava/lang/String;
        //   161: aload_0        
        //   162: iconst_0       
        //   163: newarray        J
        //   165: putfield        com/google/android/gms/internal/ads/zzapx.zzdaf:[J
        //   168: return         
        //   169: aload_1        
        //   170: ldc             ","
        //   172: invokestatic    android/text/TextUtils.split:(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;
        //   175: astore_1       
        //   176: aload_0        
        //   177: aload_1        
        //   178: arraylength    
        //   179: anewarray       Ljava/lang/String;
        //   182: putfield        com/google/android/gms/internal/ads/zzapx.zzdag:[Ljava/lang/String;
        //   185: aload_0        
        //   186: aload_1        
        //   187: arraylength    
        //   188: newarray        J
        //   190: putfield        com/google/android/gms/internal/ads/zzapx.zzdaf:[J
        //   193: iconst_0       
        //   194: istore          6
        //   196: iload           6
        //   198: aload_1        
        //   199: arraylength    
        //   200: if_icmpge       168
        //   203: aload_0        
        //   204: getfield        com/google/android/gms/internal/ads/zzapx.zzdaf:[J
        //   207: iload           6
        //   209: aload_1        
        //   210: iload           6
        //   212: aaload         
        //   213: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   216: lastore        
        //   217: iload           6
        //   219: iconst_1       
        //   220: iadd           
        //   221: istore          6
        //   223: goto            196
        //   226: astore_2       
        //   227: ldc             "Unable to parse frame hash target time number."
        //   229: aload_2        
        //   230: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   233: aload_0        
        //   234: getfield        com/google/android/gms/internal/ads/zzapx.zzdaf:[J
        //   237: iload           6
        //   239: ldc2_w          -1
        //   242: lastore        
        //   243: goto            217
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  203    217    226    246    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
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
    
    public final void onStop() {
        if ((boolean)zzkb.zzik().zzd(zznk.zzavd) && !this.zzdam) {
            final Bundle bundle = new Bundle();
            bundle.putString("type", "native-player-metrics");
            bundle.putString("request", this.zzchp);
            bundle.putString("player", this.zzdal.zzsp());
            for (final zzalr zzalr : this.zzdae.zzry()) {
                final String value = String.valueOf("fps_c_");
                final String value2 = String.valueOf(zzalr.name);
                String concat;
                if (value2.length() != 0) {
                    concat = value.concat(value2);
                }
                else {
                    concat = new String(value);
                }
                bundle.putString(concat, Integer.toString(zzalr.count));
                final String value3 = String.valueOf("fps_p_");
                final String value4 = String.valueOf(zzalr.name);
                String concat2;
                if (value4.length() != 0) {
                    concat2 = value3.concat(value4);
                }
                else {
                    concat2 = new String(value3);
                }
                bundle.putString(concat2, Double.toString(zzalr.zzctb));
            }
            for (int i = 0; i < this.zzdaf.length; ++i) {
                final String s = this.zzdag[i];
                if (s != null) {
                    final String value5 = String.valueOf((Object)this.zzdaf[i]);
                    bundle.putString(new StringBuilder(String.valueOf(value5).length() + 3).append("fh_").append(value5).toString(), s);
                }
            }
            zzbv.zzek().zza(this.mContext, this.zzzw.zzcw, "gmob-apps", bundle, true);
            this.zzdam = true;
        }
    }
    
    public final void zzb(final zzapg zzdal) {
        zznq.zza(this.zzcxo, this.zzdad, "vpc2");
        this.zzdah = true;
        if (this.zzcxo != null) {
            this.zzcxo.zze("vpn", zzdal.zzsp());
        }
        this.zzdal = zzdal;
    }
    
    public final void zzc(final zzapg zzapg) {
        if (this.zzdaj && !this.zzdak) {
            if (zzakb.zzqp() && !this.zzdak) {
                zzakb.v("VideoMetricsMixin first frame");
            }
            zznq.zza(this.zzcxo, this.zzdad, "vff2");
            this.zzdak = true;
        }
        final long nanoTime = zzbv.zzer().nanoTime();
        if (this.zzcxs && this.zzdan && this.zzdao != -1L) {
            this.zzdae.zza(TimeUnit.SECONDS.toNanos(1L) / (double)(nanoTime - this.zzdao));
        }
        this.zzdan = this.zzcxs;
        this.zzdao = nanoTime;
        final long longValue = (long)zzkb.zzik().zzd(zznk.zzavf);
        final long n = zzapg.getCurrentPosition();
        for (int i = 0; i < this.zzdag.length; ++i) {
            if (this.zzdag[i] == null && longValue > Math.abs(n - this.zzdaf[i])) {
                final String[] zzdag = this.zzdag;
                final Bitmap bitmap = zzapg.getBitmap(8, 8);
                long n2 = 0L;
                long n3 = 63L;
                long n5;
                long n6;
                long n8;
                for (int j = 0; j < 8; ++j, n8 = n5, n3 = n6, n2 = n8) {
                    int k = 0;
                    final long n4 = n3;
                    n5 = n2;
                    for (n6 = n4; k < 8; ++k, --n6) {
                        final int pixel = bitmap.getPixel(k, j);
                        long n7;
                        if (Color.green(pixel) + (Color.blue(pixel) + Color.red(pixel)) > 128) {
                            n7 = 1L;
                        }
                        else {
                            n7 = 0L;
                        }
                        n5 |= n7 << (int)n6;
                    }
                }
                zzdag[i] = String.format("%016X", n2);
                break;
            }
        }
    }
    
    public final void zzsv() {
        if (!this.zzdah || this.zzdai) {
            return;
        }
        zznq.zza(this.zzcxo, this.zzdad, "vfr2");
        this.zzdai = true;
    }
    
    public final void zztt() {
        this.zzcxs = true;
        if (this.zzdai && !this.zzdaj) {
            zznq.zza(this.zzcxo, this.zzdad, "vfp2");
            this.zzdaj = true;
        }
    }
    
    public final void zztu() {
        this.zzcxs = false;
    }
}
