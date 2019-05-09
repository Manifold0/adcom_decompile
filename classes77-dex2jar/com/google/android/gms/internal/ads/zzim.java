// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzim extends zzbfc<zzim>
{
    private Integer zzamf;
    private Integer zzanx;
    
    public zzim() {
        this.zzamf = null;
        this.zzanx = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzim zzo(final zzbez p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/google/android/gms/internal/ads/zzbez.zzabk:()I
        //     4: istore_2       
        //     5: iload_2        
        //     6: lookupswitch {
        //                0: 49
        //                8: 51
        //               16: 132
        //          default: 40
        //        }
        //    40: aload_0        
        //    41: aload_1        
        //    42: iload_2        
        //    43: invokespecial   com/google/android/gms/internal/ads/zzbfc.zza:(Lcom/google/android/gms/internal/ads/zzbez;I)Z
        //    46: ifne            0
        //    49: aload_0        
        //    50: areturn        
        //    51: aload_1        
        //    52: invokevirtual   com/google/android/gms/internal/ads/zzbez.getPosition:()I
        //    55: istore_3       
        //    56: aload_1        
        //    57: invokevirtual   com/google/android/gms/internal/ads/zzbez.zzacc:()I
        //    60: istore          4
        //    62: iload           4
        //    64: iflt            102
        //    67: iload           4
        //    69: iconst_2       
        //    70: if_icmpgt       102
        //    73: aload_0        
        //    74: iload           4
        //    76: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    79: putfield        com/google/android/gms/internal/ads/zzim.zzamf:Ljava/lang/Integer;
        //    82: goto            0
        //    85: astore          5
        //    87: aload_1        
        //    88: iload_3        
        //    89: invokevirtual   com/google/android/gms/internal/ads/zzbez.zzdc:(I)V
        //    92: aload_0        
        //    93: aload_1        
        //    94: iload_2        
        //    95: invokevirtual   com/google/android/gms/internal/ads/zzbfc.zza:(Lcom/google/android/gms/internal/ads/zzbez;I)Z
        //    98: pop            
        //    99: goto            0
        //   102: new             Ljava/lang/IllegalArgumentException;
        //   105: dup            
        //   106: new             Ljava/lang/StringBuilder;
        //   109: dup            
        //   110: bipush          43
        //   112: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   115: iload           4
        //   117: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   120: ldc             " is not a valid enum NetworkType"
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   128: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   131: athrow         
        //   132: aload_1        
        //   133: invokevirtual   com/google/android/gms/internal/ads/zzbez.getPosition:()I
        //   136: istore_3       
        //   137: aload_1        
        //   138: invokevirtual   com/google/android/gms/internal/ads/zzbez.zzacc:()I
        //   141: istore          4
        //   143: iload           4
        //   145: iflt            183
        //   148: iload           4
        //   150: iconst_2       
        //   151: if_icmpgt       183
        //   154: aload_0        
        //   155: iload           4
        //   157: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   160: putfield        com/google/android/gms/internal/ads/zzim.zzanx:Ljava/lang/Integer;
        //   163: goto            0
        //   166: astore          5
        //   168: aload_1        
        //   169: iload_3        
        //   170: invokevirtual   com/google/android/gms/internal/ads/zzbez.zzdc:(I)V
        //   173: aload_0        
        //   174: aload_1        
        //   175: iload_2        
        //   176: invokevirtual   com/google/android/gms/internal/ads/zzbfc.zza:(Lcom/google/android/gms/internal/ads/zzbez;I)Z
        //   179: pop            
        //   180: goto            0
        //   183: iload           4
        //   185: iconst_4       
        //   186: if_icmplt       195
        //   189: iload           4
        //   191: iconst_4       
        //   192: if_icmple       154
        //   195: new             Ljava/lang/IllegalArgumentException;
        //   198: dup            
        //   199: new             Ljava/lang/StringBuilder;
        //   202: dup            
        //   203: bipush          51
        //   205: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   208: iload           4
        //   210: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   213: ldc             " is not a valid enum CellularNetworkType"
        //   215: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   218: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   221: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   224: athrow         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  56     62     85     102    Ljava/lang/IllegalArgumentException;
        //  73     82     85     102    Ljava/lang/IllegalArgumentException;
        //  102    132    85     102    Ljava/lang/IllegalArgumentException;
        //  137    143    166    183    Ljava/lang/IllegalArgumentException;
        //  154    163    166    183    Ljava/lang/IllegalArgumentException;
        //  195    225    166    183    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: logicalor:boolean(cmplt:boolean(var_4_8D:int, ldc:int(4)), cmpgt:boolean(var_4_8D:int, ldc:int(4)))
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.GotoRemoval.traverseGraph(GotoRemoval.java:88)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotos(GotoRemoval.java:52)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:276)
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
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzamf != null) {
            zzbfa.zzm(1, (int)this.zzamf);
        }
        if (this.zzanx != null) {
            zzbfa.zzm(2, (int)this.zzanx);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzamf != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzamf);
        }
        int n2 = zzr;
        if (this.zzanx != null) {
            n2 = zzr + zzbfa.zzq(2, (int)this.zzanx);
        }
        return n2;
    }
}
