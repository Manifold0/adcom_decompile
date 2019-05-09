// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

@zzadh
public final class zzru extends UnifiedNativeAd
{
    private final zzrr zzbkw;
    private final List<NativeAd.Image> zzbkx;
    private final zzpz zzbky;
    private final VideoController zzbkz;
    private final NativeAd.AdChoicesInfo zzbla;
    
    public zzru(final zzrr p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_2       
        //     2: aload_0        
        //     3: invokespecial   com/google/android/gms/ads/formats/UnifiedNativeAd.<init>:()V
        //     6: aload_0        
        //     7: new             Ljava/util/ArrayList;
        //    10: dup            
        //    11: invokespecial   java/util/ArrayList.<init>:()V
        //    14: putfield        com/google/android/gms/internal/ads/zzru.zzbkx:Ljava/util/List;
        //    17: aload_0        
        //    18: new             Lcom/google/android/gms/ads/VideoController;
        //    21: dup            
        //    22: invokespecial   com/google/android/gms/ads/VideoController.<init>:()V
        //    25: putfield        com/google/android/gms/internal/ads/zzru.zzbkz:Lcom/google/android/gms/ads/VideoController;
        //    28: aload_0        
        //    29: aload_1        
        //    30: putfield        com/google/android/gms/internal/ads/zzru.zzbkw:Lcom/google/android/gms/internal/ads/zzrr;
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/internal/ads/zzru.zzbkw:Lcom/google/android/gms/internal/ads/zzrr;
        //    37: invokeinterface com/google/android/gms/internal/ads/zzrr.getImages:()Ljava/util/List;
        //    42: astore_1       
        //    43: aload_1        
        //    44: ifnull          142
        //    47: aload_1        
        //    48: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    53: astore_3       
        //    54: aload_3        
        //    55: invokeinterface java/util/Iterator.hasNext:()Z
        //    60: ifeq            142
        //    63: aload_3        
        //    64: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    69: astore_1       
        //    70: aload_1        
        //    71: instanceof      Landroid/os/IBinder;
        //    74: ifeq            219
        //    77: aload_1        
        //    78: checkcast       Landroid/os/IBinder;
        //    81: astore_1       
        //    82: aload_1        
        //    83: ifnull          219
        //    86: aload_1        
        //    87: ldc             "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
        //    89: invokeinterface android/os/IBinder.queryLocalInterface:(Ljava/lang/String;)Landroid/os/IInterface;
        //    94: astore          4
        //    96: aload           4
        //    98: instanceof      Lcom/google/android/gms/internal/ads/zzpw;
        //   101: ifeq            207
        //   104: aload           4
        //   106: checkcast       Lcom/google/android/gms/internal/ads/zzpw;
        //   109: astore_1       
        //   110: aload_1        
        //   111: ifnull          54
        //   114: aload_0        
        //   115: getfield        com/google/android/gms/internal/ads/zzru.zzbkx:Ljava/util/List;
        //   118: new             Lcom/google/android/gms/internal/ads/zzpz;
        //   121: dup            
        //   122: aload_1        
        //   123: invokespecial   com/google/android/gms/internal/ads/zzpz.<init>:(Lcom/google/android/gms/internal/ads/zzpw;)V
        //   126: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   131: pop            
        //   132: goto            54
        //   135: astore_1       
        //   136: ldc             ""
        //   138: aload_1        
        //   139: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   142: aload_0        
        //   143: getfield        com/google/android/gms/internal/ads/zzru.zzbkw:Lcom/google/android/gms/internal/ads/zzrr;
        //   146: invokeinterface com/google/android/gms/internal/ads/zzrr.zzjz:()Lcom/google/android/gms/internal/ads/zzpw;
        //   151: astore_1       
        //   152: aload_1        
        //   153: ifnull          224
        //   156: new             Lcom/google/android/gms/internal/ads/zzpz;
        //   159: dup            
        //   160: aload_1        
        //   161: invokespecial   com/google/android/gms/internal/ads/zzpz.<init>:(Lcom/google/android/gms/internal/ads/zzpw;)V
        //   164: astore_1       
        //   165: aload_0        
        //   166: aload_1        
        //   167: putfield        com/google/android/gms/internal/ads/zzru.zzbky:Lcom/google/android/gms/internal/ads/zzpz;
        //   170: aload_2        
        //   171: astore_1       
        //   172: aload_0        
        //   173: getfield        com/google/android/gms/internal/ads/zzru.zzbkw:Lcom/google/android/gms/internal/ads/zzrr;
        //   176: invokeinterface com/google/android/gms/internal/ads/zzrr.zzkf:()Lcom/google/android/gms/internal/ads/zzps;
        //   181: ifnull          201
        //   184: new             Lcom/google/android/gms/internal/ads/zzpv;
        //   187: dup            
        //   188: aload_0        
        //   189: getfield        com/google/android/gms/internal/ads/zzru.zzbkw:Lcom/google/android/gms/internal/ads/zzrr;
        //   192: invokeinterface com/google/android/gms/internal/ads/zzrr.zzkf:()Lcom/google/android/gms/internal/ads/zzps;
        //   197: invokespecial   com/google/android/gms/internal/ads/zzpv.<init>:(Lcom/google/android/gms/internal/ads/zzps;)V
        //   200: astore_1       
        //   201: aload_0        
        //   202: aload_1        
        //   203: putfield        com/google/android/gms/internal/ads/zzru.zzbla:Lcom/google/android/gms/ads/formats/NativeAd$AdChoicesInfo;
        //   206: return         
        //   207: new             Lcom/google/android/gms/internal/ads/zzpy;
        //   210: dup            
        //   211: aload_1        
        //   212: invokespecial   com/google/android/gms/internal/ads/zzpy.<init>:(Landroid/os/IBinder;)V
        //   215: astore_1       
        //   216: goto            110
        //   219: aconst_null    
        //   220: astore_1       
        //   221: goto            110
        //   224: aconst_null    
        //   225: astore_1       
        //   226: goto            165
        //   229: astore_1       
        //   230: ldc             ""
        //   232: aload_1        
        //   233: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   236: aconst_null    
        //   237: astore_1       
        //   238: goto            165
        //   241: astore_1       
        //   242: ldc             ""
        //   244: aload_1        
        //   245: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   248: aload_2        
        //   249: astore_1       
        //   250: goto            201
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  33     43     135    142    Landroid/os/RemoteException;
        //  47     54     135    142    Landroid/os/RemoteException;
        //  54     82     135    142    Landroid/os/RemoteException;
        //  86     110    135    142    Landroid/os/RemoteException;
        //  114    132    135    142    Landroid/os/RemoteException;
        //  142    152    229    241    Landroid/os/RemoteException;
        //  156    165    229    241    Landroid/os/RemoteException;
        //  172    201    241    253    Landroid/os/RemoteException;
        //  207    216    135    142    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0201:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    private final IObjectWrapper zzka() {
        try {
            return this.zzbkw.zzka();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final void cancelUnconfirmedClick() {
        try {
            this.zzbkw.cancelUnconfirmedClick();
        }
        catch (RemoteException ex) {
            zzane.zzb("Failed to cancelUnconfirmedClick", (Throwable)ex);
        }
    }
    
    @Override
    public final void destroy() {
        try {
            this.zzbkw.destroy();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
    }
    
    @Override
    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzbla;
    }
    
    @Override
    public final String getAdvertiser() {
        try {
            return this.zzbkw.getAdvertiser();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final String getBody() {
        try {
            return this.zzbkw.getBody();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final String getCallToAction() {
        try {
            return this.zzbkw.getCallToAction();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final Bundle getExtras() {
        try {
            final Bundle extras = this.zzbkw.getExtras();
            if (extras != null) {
                return extras;
            }
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
        return new Bundle();
    }
    
    @Override
    public final String getHeadline() {
        try {
            return this.zzbkw.getHeadline();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final NativeAd.Image getIcon() {
        return this.zzbky;
    }
    
    @Override
    public final List<NativeAd.Image> getImages() {
        return this.zzbkx;
    }
    
    @Override
    public final String getMediationAdapterClassName() {
        try {
            return this.zzbkw.getMediationAdapterClassName();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final String getPrice() {
        try {
            return this.zzbkw.getPrice();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final Double getStarRating() {
        try {
            final double starRating = this.zzbkw.getStarRating();
            if (starRating == -1.0) {
                return null;
            }
            return starRating;
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final String getStore() {
        try {
            return this.zzbkw.getStore();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final VideoController getVideoController() {
        try {
            if (this.zzbkw.getVideoController() != null) {
                this.zzbkz.zza(this.zzbkw.getVideoController());
            }
            return this.zzbkz;
        }
        catch (RemoteException ex) {
            zzane.zzb("Exception occurred while getting video controller", (Throwable)ex);
            return this.zzbkz;
        }
    }
    
    @Override
    public final void performClick(final Bundle bundle) {
        try {
            this.zzbkw.performClick(bundle);
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
    }
    
    @Override
    public final boolean recordImpression(final Bundle bundle) {
        try {
            return this.zzbkw.recordImpression(bundle);
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return false;
        }
    }
    
    @Override
    public final void reportTouchEvent(final Bundle bundle) {
        try {
            this.zzbkw.reportTouchEvent(bundle);
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
    }
    
    @Override
    public final void setUnconfirmedClickListener(final UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zzbkw.zza(new zzse(unconfirmedClickListener));
        }
        catch (RemoteException ex) {
            zzane.zzb("Failed to setUnconfirmedClickListener", (Throwable)ex);
        }
    }
    
    @Override
    public final Object zzbh() {
        try {
            final IObjectWrapper zzke = this.zzbkw.zzke();
            if (zzke != null) {
                return ObjectWrapper.unwrap(zzke);
            }
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
        return null;
    }
}
