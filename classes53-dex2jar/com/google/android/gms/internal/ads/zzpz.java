// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.graphics.drawable.Drawable;
import com.google.android.gms.ads.formats.NativeAd;

@zzadh
public final class zzpz extends Image
{
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzbhv;
    private final zzpw zzbkm;
    
    public zzpz(final zzpw p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          6
        //     3: aload_0        
        //     4: invokespecial   com/google/android/gms/ads/formats/NativeAd$Image.<init>:()V
        //     7: aload_0        
        //     8: aload_1        
        //     9: putfield        com/google/android/gms/internal/ads/zzpz.zzbkm:Lcom/google/android/gms/internal/ads/zzpw;
        //    12: aload_0        
        //    13: getfield        com/google/android/gms/internal/ads/zzpz.zzbkm:Lcom/google/android/gms/internal/ads/zzpw;
        //    16: invokeinterface com/google/android/gms/internal/ads/zzpw.zzjy:()Lcom/google/android/gms/dynamic/IObjectWrapper;
        //    21: astore_1       
        //    22: aload_1        
        //    23: ifnull          83
        //    26: aload_1        
        //    27: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.unwrap:(Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
        //    30: checkcast       Landroid/graphics/drawable/Drawable;
        //    33: astore_1       
        //    34: aload_0        
        //    35: aload_1        
        //    36: putfield        com/google/android/gms/internal/ads/zzpz.mDrawable:Landroid/graphics/drawable/Drawable;
        //    39: aload_0        
        //    40: getfield        com/google/android/gms/internal/ads/zzpz.zzbkm:Lcom/google/android/gms/internal/ads/zzpw;
        //    43: invokeinterface com/google/android/gms/internal/ads/zzpw.getUri:()Landroid/net/Uri;
        //    48: astore_1       
        //    49: aload_0        
        //    50: aload_1        
        //    51: putfield        com/google/android/gms/internal/ads/zzpz.mUri:Landroid/net/Uri;
        //    54: dconst_1       
        //    55: dstore_2       
        //    56: aload_0        
        //    57: getfield        com/google/android/gms/internal/ads/zzpz.zzbkm:Lcom/google/android/gms/internal/ads/zzpw;
        //    60: invokeinterface com/google/android/gms/internal/ads/zzpw.getScale:()D
        //    65: dstore          4
        //    67: dload           4
        //    69: dstore_2       
        //    70: aload_0        
        //    71: dload_2        
        //    72: putfield        com/google/android/gms/internal/ads/zzpz.zzbhv:D
        //    75: return         
        //    76: astore_1       
        //    77: ldc             ""
        //    79: aload_1        
        //    80: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    83: aconst_null    
        //    84: astore_1       
        //    85: goto            34
        //    88: astore_1       
        //    89: ldc             ""
        //    91: aload_1        
        //    92: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    95: aload           6
        //    97: astore_1       
        //    98: goto            49
        //   101: astore_1       
        //   102: ldc             ""
        //   104: aload_1        
        //   105: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   108: goto            70
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  12     22     76     83     Landroid/os/RemoteException;
        //  26     34     76     83     Landroid/os/RemoteException;
        //  39     49     88     101    Landroid/os/RemoteException;
        //  56     67     101    111    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 58, Size: 58
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
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
    
    @Override
    public final Drawable getDrawable() {
        return this.mDrawable;
    }
    
    @Override
    public final double getScale() {
        return this.zzbhv;
    }
    
    @Override
    public final Uri getUri() {
        return this.mUri;
    }
}
