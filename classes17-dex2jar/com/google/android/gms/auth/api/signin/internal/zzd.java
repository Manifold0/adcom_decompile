// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.logging.Logger;

public final class zzd implements Runnable
{
    private static final Logger zzbd;
    private final String zzbe;
    private final StatusPendingResult zzbf;
    
    static {
        zzbd = new Logger("RevokeAccessOperation", new String[0]);
    }
    
    private zzd(final String zzbe) {
        Preconditions.checkNotEmpty(zzbe);
        this.zzbe = zzbe;
        this.zzbf = new StatusPendingResult((GoogleApiClient)null);
    }
    
    public static PendingResult<Status> zzc(final String s) {
        if (s == null) {
            return (PendingResult<Status>)PendingResults.immediateFailedResult((Result)new Status(4), (GoogleApiClient)null);
        }
        final zzd zzd = new zzd(s);
        new Thread(zzd).start();
        return (PendingResult<Status>)zzd.zzbf;
    }
    
    @Override
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_2       
        //     4: ldc             "https://accounts.google.com/o/oauth2/revoke?token="
        //     6: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //     9: astore_3       
        //    10: aload_0        
        //    11: getfield        com/google/android/gms/auth/api/signin/internal/zzd.zzbe:Ljava/lang/String;
        //    14: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    17: astore          4
        //    19: aload           4
        //    21: invokevirtual   java/lang/String.length:()I
        //    24: ifeq            115
        //    27: aload_3        
        //    28: aload           4
        //    30: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    33: astore_3       
        //    34: new             Ljava/net/URL;
        //    37: dup            
        //    38: aload_3        
        //    39: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    42: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    45: checkcast       Ljava/net/HttpURLConnection;
        //    48: astore_3       
        //    49: aload_3        
        //    50: ldc             "Content-Type"
        //    52: ldc             "application/x-www-form-urlencoded"
        //    54: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    57: aload_3        
        //    58: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //    61: istore_1       
        //    62: iload_1        
        //    63: sipush          200
        //    66: if_icmpne       168
        //    69: getstatic       com/google/android/gms/common/api/Status.RESULT_SUCCESS:Lcom/google/android/gms/common/api/Status;
        //    72: astore_3       
        //    73: aload_3        
        //    74: astore_2       
        //    75: getstatic       com/google/android/gms/auth/api/signin/internal/zzd.zzbd:Lcom/google/android/gms/common/logging/Logger;
        //    78: new             Ljava/lang/StringBuilder;
        //    81: dup            
        //    82: bipush          26
        //    84: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //    87: ldc             "Response Code: "
        //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    92: iload_1        
        //    93: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    96: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    99: iconst_0       
        //   100: anewarray       Ljava/lang/Object;
        //   103: invokevirtual   com/google/android/gms/common/logging/Logger.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   106: aload_0        
        //   107: getfield        com/google/android/gms/auth/api/signin/internal/zzd.zzbf:Lcom/google/android/gms/common/api/internal/StatusPendingResult;
        //   110: aload_2        
        //   111: invokevirtual   com/google/android/gms/common/api/internal/StatusPendingResult.setResult:(Lcom/google/android/gms/common/api/Result;)V
        //   114: return         
        //   115: new             Ljava/lang/String;
        //   118: dup            
        //   119: aload_3        
        //   120: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   123: astore_3       
        //   124: goto            34
        //   127: astore_3       
        //   128: getstatic       com/google/android/gms/auth/api/signin/internal/zzd.zzbd:Lcom/google/android/gms/common/logging/Logger;
        //   131: astore          4
        //   133: aload_3        
        //   134: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   137: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   140: astore_3       
        //   141: aload_3        
        //   142: invokevirtual   java/lang/String.length:()I
        //   145: ifeq            183
        //   148: ldc             "IOException when revoking access: "
        //   150: aload_3        
        //   151: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   154: astore_3       
        //   155: aload           4
        //   157: aload_3        
        //   158: iconst_0       
        //   159: anewarray       Ljava/lang/Object;
        //   162: invokevirtual   com/google/android/gms/common/logging/Logger.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   165: goto            106
        //   168: getstatic       com/google/android/gms/auth/api/signin/internal/zzd.zzbd:Lcom/google/android/gms/common/logging/Logger;
        //   171: ldc             "Unable to revoke access!"
        //   173: iconst_0       
        //   174: anewarray       Ljava/lang/Object;
        //   177: invokevirtual   com/google/android/gms/common/logging/Logger.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   180: goto            75
        //   183: new             Ljava/lang/String;
        //   186: dup            
        //   187: ldc             "IOException when revoking access: "
        //   189: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   192: astore_3       
        //   193: goto            155
        //   196: astore_3       
        //   197: getstatic       com/google/android/gms/auth/api/signin/internal/zzd.zzbd:Lcom/google/android/gms/common/logging/Logger;
        //   200: astore          4
        //   202: aload_3        
        //   203: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   206: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   209: astore_3       
        //   210: aload_3        
        //   211: invokevirtual   java/lang/String.length:()I
        //   214: ifeq            237
        //   217: ldc             "Exception when revoking access: "
        //   219: aload_3        
        //   220: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   223: astore_3       
        //   224: aload           4
        //   226: aload_3        
        //   227: iconst_0       
        //   228: anewarray       Ljava/lang/Object;
        //   231: invokevirtual   com/google/android/gms/common/logging/Logger.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   234: goto            106
        //   237: new             Ljava/lang/String;
        //   240: dup            
        //   241: ldc             "Exception when revoking access: "
        //   243: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   246: astore_3       
        //   247: goto            224
        //   250: astore_3       
        //   251: goto            197
        //   254: astore_3       
        //   255: goto            128
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      34     127    128    Ljava/io/IOException;
        //  4      34     196    197    Ljava/lang/Exception;
        //  34     62     127    128    Ljava/io/IOException;
        //  34     62     196    197    Ljava/lang/Exception;
        //  69     73     127    128    Ljava/io/IOException;
        //  69     73     196    197    Ljava/lang/Exception;
        //  75     106    254    258    Ljava/io/IOException;
        //  75     106    250    254    Ljava/lang/Exception;
        //  115    124    127    128    Ljava/io/IOException;
        //  115    124    196    197    Ljava/lang/Exception;
        //  168    180    127    128    Ljava/io/IOException;
        //  168    180    196    197    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
}
