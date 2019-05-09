// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ConcurrentHashMap;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class LibraryVersion
{
    private static final GmsLogger zzel;
    private static LibraryVersion zzem;
    private ConcurrentHashMap<String, String> zzen;
    
    static {
        zzel = new GmsLogger("LibraryVersion", "");
        LibraryVersion.zzem = new LibraryVersion();
    }
    
    @VisibleForTesting
    protected LibraryVersion() {
        this.zzen = new ConcurrentHashMap<String, String>();
    }
    
    @KeepForSdk
    public static LibraryVersion getInstance() {
        return LibraryVersion.zzem;
    }
    
    @KeepForSdk
    public String getVersion(@NonNull final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "Please provide a valid libraryName"
        //     3: invokestatic    com/google/android/gms/common/internal/Preconditions.checkNotEmpty:(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;
        //     6: pop            
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/common/internal/LibraryVersion.zzen:Ljava/util/concurrent/ConcurrentHashMap;
        //    11: aload_1        
        //    12: invokevirtual   java/util/concurrent/ConcurrentHashMap.containsKey:(Ljava/lang/Object;)Z
        //    15: ifeq            30
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/common/internal/LibraryVersion.zzen:Ljava/util/concurrent/ConcurrentHashMap;
        //    22: aload_1        
        //    23: invokevirtual   java/util/concurrent/ConcurrentHashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    26: checkcast       Ljava/lang/String;
        //    29: areturn        
        //    30: new             Ljava/util/Properties;
        //    33: dup            
        //    34: invokespecial   java/util/Properties.<init>:()V
        //    37: astore_2       
        //    38: ldc             Lcom/google/android/gms/common/internal/LibraryVersion;.class
        //    40: ldc             "/%s.properties"
        //    42: iconst_1       
        //    43: anewarray       Ljava/lang/Object;
        //    46: dup            
        //    47: iconst_0       
        //    48: aload_1        
        //    49: aastore        
        //    50: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    53: invokevirtual   java/lang/Class.getResourceAsStream:(Ljava/lang/String;)Ljava/io/InputStream;
        //    56: astore_3       
        //    57: aload_3        
        //    58: ifnull          154
        //    61: aload_2        
        //    62: aload_3        
        //    63: invokevirtual   java/util/Properties.load:(Ljava/io/InputStream;)V
        //    66: aload_2        
        //    67: ldc             "version"
        //    69: aconst_null    
        //    70: invokevirtual   java/util/Properties.getProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    73: astore_2       
        //    74: getstatic       com/google/android/gms/common/internal/LibraryVersion.zzel:Lcom/google/android/gms/common/internal/GmsLogger;
        //    77: ldc             "LibraryVersion"
        //    79: new             Ljava/lang/StringBuilder;
        //    82: dup            
        //    83: aload_1        
        //    84: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    87: invokevirtual   java/lang/String.length:()I
        //    90: bipush          12
        //    92: iadd           
        //    93: aload_2        
        //    94: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    97: invokevirtual   java/lang/String.length:()I
        //   100: iadd           
        //   101: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   104: aload_1        
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: ldc             " version is "
        //   110: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   113: aload_2        
        //   114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   117: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   120: invokevirtual   com/google/android/gms/common/internal/GmsLogger.v:(Ljava/lang/String;Ljava/lang/String;)V
        //   123: aload_2        
        //   124: astore_3       
        //   125: aload_2        
        //   126: ifnonnull       142
        //   129: ldc             "UNKNOWN"
        //   131: astore_3       
        //   132: getstatic       com/google/android/gms/common/internal/LibraryVersion.zzel:Lcom/google/android/gms/common/internal/GmsLogger;
        //   135: ldc             "LibraryVersion"
        //   137: ldc             ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used"
        //   139: invokevirtual   com/google/android/gms/common/internal/GmsLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   142: aload_0        
        //   143: getfield        com/google/android/gms/common/internal/LibraryVersion.zzen:Ljava/util/concurrent/ConcurrentHashMap;
        //   146: aload_1        
        //   147: aload_3        
        //   148: invokevirtual   java/util/concurrent/ConcurrentHashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   151: pop            
        //   152: aload_3        
        //   153: areturn        
        //   154: getstatic       com/google/android/gms/common/internal/LibraryVersion.zzel:Lcom/google/android/gms/common/internal/GmsLogger;
        //   157: astore_3       
        //   158: aload_1        
        //   159: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   162: astore_2       
        //   163: aload_2        
        //   164: invokevirtual   java/lang/String.length:()I
        //   167: ifeq            189
        //   170: ldc             "Failed to get app version for libraryName: "
        //   172: aload_2        
        //   173: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   176: astore_2       
        //   177: aload_3        
        //   178: ldc             "LibraryVersion"
        //   180: aload_2        
        //   181: invokevirtual   com/google/android/gms/common/internal/GmsLogger.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   184: aconst_null    
        //   185: astore_2       
        //   186: goto            123
        //   189: new             Ljava/lang/String;
        //   192: dup            
        //   193: ldc             "Failed to get app version for libraryName: "
        //   195: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   198: astore_2       
        //   199: goto            177
        //   202: astore_3       
        //   203: aconst_null    
        //   204: astore_2       
        //   205: getstatic       com/google/android/gms/common/internal/LibraryVersion.zzel:Lcom/google/android/gms/common/internal/GmsLogger;
        //   208: astore          5
        //   210: aload_1        
        //   211: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   214: astore          4
        //   216: aload           4
        //   218: invokevirtual   java/lang/String.length:()I
        //   221: ifeq            246
        //   224: ldc             "Failed to get app version for libraryName: "
        //   226: aload           4
        //   228: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   231: astore          4
        //   233: aload           5
        //   235: ldc             "LibraryVersion"
        //   237: aload           4
        //   239: aload_3        
        //   240: invokevirtual   com/google/android/gms/common/internal/GmsLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   243: goto            123
        //   246: new             Ljava/lang/String;
        //   249: dup            
        //   250: ldc             "Failed to get app version for libraryName: "
        //   252: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   255: astore          4
        //   257: goto            233
        //   260: astore_3       
        //   261: goto            205
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  38     57     202    205    Ljava/io/IOException;
        //  61     74     202    205    Ljava/io/IOException;
        //  74     123    260    264    Ljava/io/IOException;
        //  154    177    202    205    Ljava/io/IOException;
        //  177    184    202    205    Ljava/io/IOException;
        //  189    199    202    205    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0123:
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
