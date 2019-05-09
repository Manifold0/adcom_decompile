// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.support.annotation.Nullable;
import java.io.File;

public class i
{
    @Nullable
    public static String a(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0        
        //     5: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //     8: astore_0       
        //     9: ldc             "MD5"
        //    11: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //    14: astore_2       
        //    15: sipush          1024
        //    18: newarray        B
        //    20: astore_3       
        //    21: aload_0        
        //    22: aload_3        
        //    23: invokevirtual   java/io/InputStream.read:([B)I
        //    26: istore_1       
        //    27: iload_1        
        //    28: ifle            38
        //    31: aload_2        
        //    32: aload_3        
        //    33: iconst_0       
        //    34: iload_1        
        //    35: invokevirtual   java/security/MessageDigest.update:([BII)V
        //    38: iload_1        
        //    39: iconst_m1      
        //    40: if_icmpne       21
        //    43: aload_2        
        //    44: invokevirtual   java/security/MessageDigest.digest:()[B
        //    47: invokestatic    com/facebook/ads/internal/w/b/i.b:([B)Ljava/lang/String;
        //    50: astore_2       
        //    51: aload_0        
        //    52: invokevirtual   java/io/InputStream.close:()V
        //    55: aload_2        
        //    56: areturn        
        //    57: astore_0       
        //    58: new             Ljava/lang/Exception;
        //    61: dup            
        //    62: ldc             "File not found or not accessible."
        //    64: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //    67: athrow         
        //    68: astore_2       
        //    69: new             Ljava/lang/Exception;
        //    72: dup            
        //    73: ldc             "No such algorithm."
        //    75: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //    78: athrow         
        //    79: astore_2       
        //    80: aload_0        
        //    81: invokevirtual   java/io/InputStream.close:()V
        //    84: aload_2        
        //    85: athrow         
        //    86: astore_2       
        //    87: new             Ljava/lang/Exception;
        //    90: dup            
        //    91: ldc             "IO exception."
        //    93: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //    96: athrow         
        //    97: astore_0       
        //    98: aload_2        
        //    99: areturn        
        //   100: astore_0       
        //   101: goto            84
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  0      9      57     68     Ljava/io/FileNotFoundException;
        //  9      21     68     79     Ljava/security/NoSuchAlgorithmException;
        //  9      21     86     97     Ljava/io/IOException;
        //  9      21     79     86     Any
        //  21     27     68     79     Ljava/security/NoSuchAlgorithmException;
        //  21     27     86     97     Ljava/io/IOException;
        //  21     27     79     86     Any
        //  31     38     68     79     Ljava/security/NoSuchAlgorithmException;
        //  31     38     86     97     Ljava/io/IOException;
        //  31     38     79     86     Any
        //  43     51     68     79     Ljava/security/NoSuchAlgorithmException;
        //  43     51     86     97     Ljava/io/IOException;
        //  43     51     79     86     Any
        //  51     55     97     100    Ljava/io/IOException;
        //  69     79     79     86     Any
        //  80     84     100    104    Ljava/io/IOException;
        //  87     97     79     86     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 61, Size: 61
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    public static String a(final byte[] array) {
        final StringBuilder sb = new StringBuilder();
        for (int length = array.length, i = 0; i < length; ++i) {
            final byte b = array[i];
            int n = b >>> 4 & 0xF;
            int n2 = 0;
            while (true) {
                char c;
                if (n >= 0 && n <= 9) {
                    c = (char)(n + 48);
                }
                else {
                    c = (char)(n - 10 + 97);
                }
                sb.append(c);
                if (n2 >= 1) {
                    break;
                }
                ++n2;
                n = (b & 0xF);
            }
        }
        return sb.toString();
    }
    
    private static String b(final byte[] array) {
        final StringBuilder sb = new StringBuilder();
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(Integer.toString((array[i] & 0xFF) + 256, 16).substring(1));
        }
        return sb.toString();
    }
}
