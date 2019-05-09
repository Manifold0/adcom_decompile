// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public class zzbcb
{
    private static final zzbbb zzdph;
    private zzbah zzdvk;
    private volatile zzbcu zzdvl;
    private volatile zzbah zzdvm;
    
    static {
        zzdph = zzbbb.zzacr();
    }
    
    private final zzbcu zzk(final zzbcu p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzbcb.zzdvl:Lcom/google/android/gms/internal/ads/zzbcu;
        //     4: ifnonnull       18
        //     7: aload_0        
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        com/google/android/gms/internal/ads/zzbcb.zzdvl:Lcom/google/android/gms/internal/ads/zzbcu;
        //    13: ifnull          23
        //    16: aload_0        
        //    17: monitorexit    
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/internal/ads/zzbcb.zzdvl:Lcom/google/android/gms/internal/ads/zzbcu;
        //    22: areturn        
        //    23: aload_0        
        //    24: aload_1        
        //    25: putfield        com/google/android/gms/internal/ads/zzbcb.zzdvl:Lcom/google/android/gms/internal/ads/zzbcu;
        //    28: aload_0        
        //    29: getstatic       com/google/android/gms/internal/ads/zzbah.zzdpq:Lcom/google/android/gms/internal/ads/zzbah;
        //    32: putfield        com/google/android/gms/internal/ads/zzbcb.zzdvm:Lcom/google/android/gms/internal/ads/zzbah;
        //    35: aload_0        
        //    36: monitorexit    
        //    37: goto            18
        //    40: astore_1       
        //    41: aload_0        
        //    42: monitorexit    
        //    43: aload_1        
        //    44: athrow         
        //    45: astore_2       
        //    46: aload_0        
        //    47: aload_1        
        //    48: putfield        com/google/android/gms/internal/ads/zzbcb.zzdvl:Lcom/google/android/gms/internal/ads/zzbcu;
        //    51: aload_0        
        //    52: getstatic       com/google/android/gms/internal/ads/zzbah.zzdpq:Lcom/google/android/gms/internal/ads/zzbah;
        //    55: putfield        com/google/android/gms/internal/ads/zzbcb.zzdvm:Lcom/google/android/gms/internal/ads/zzbah;
        //    58: goto            35
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  9      18     40     45     Any
        //  23     35     45     61     Lcom/google/android/gms/internal/ads/zzbbu;
        //  23     35     40     45     Any
        //  35     37     40     45     Any
        //  41     43     40     45     Any
        //  46     58     40     45     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof zzbcb)) {
            return false;
        }
        final zzbcb zzbcb = (zzbcb)o;
        final zzbcu zzdvl = this.zzdvl;
        final zzbcu zzdvl2 = zzbcb.zzdvl;
        if (zzdvl == null && zzdvl2 == null) {
            return this.zzaav().equals(zzbcb.zzaav());
        }
        if (zzdvl != null && zzdvl2 != null) {
            return zzdvl.equals(zzdvl2);
        }
        if (zzdvl != null) {
            return zzdvl.equals(zzbcb.zzk(zzdvl.zzadg()));
        }
        return this.zzk(zzdvl2.zzadg()).equals(zzdvl2);
    }
    
    @Override
    public int hashCode() {
        return 1;
    }
    
    public final zzbah zzaav() {
        if (this.zzdvm != null) {
            return this.zzdvm;
        }
        synchronized (this) {
            if (this.zzdvm != null) {
                return this.zzdvm;
            }
        }
        if (this.zzdvl == null) {
            this.zzdvm = zzbah.zzdpq;
        }
        else {
            this.zzdvm = this.zzdvl.zzaav();
        }
        // monitorexit(this)
        return this.zzdvm;
    }
    
    public final int zzacw() {
        if (this.zzdvm != null) {
            return this.zzdvm.size();
        }
        if (this.zzdvl != null) {
            return this.zzdvl.zzacw();
        }
        return 0;
    }
    
    public final zzbcu zzl(final zzbcu zzdvl) {
        final zzbcu zzdvl2 = this.zzdvl;
        this.zzdvk = null;
        this.zzdvm = null;
        this.zzdvl = zzdvl;
        return zzdvl2;
    }
}
