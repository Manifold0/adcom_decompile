// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import java.io.InputStreamReader;
import java.io.InputStream;
import com.moat.analytics.mobile.vng.a.b.a;

class q
{
    static a<String> a(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore          4
        //     5: aload           4
        //     7: astore_1       
        //     8: aload_3        
        //     9: astore_2       
        //    10: new             Ljava/net/URL;
        //    13: dup            
        //    14: aload_0        
        //    15: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    18: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    21: checkcast       Ljava/net/HttpURLConnection;
        //    24: astore_0       
        //    25: aload           4
        //    27: astore_1       
        //    28: aload_3        
        //    29: astore_2       
        //    30: aload_0        
        //    31: iconst_0       
        //    32: invokevirtual   java/net/HttpURLConnection.setUseCaches:(Z)V
        //    35: aload           4
        //    37: astore_1       
        //    38: aload_3        
        //    39: astore_2       
        //    40: aload_0        
        //    41: sipush          10000
        //    44: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //    47: aload           4
        //    49: astore_1       
        //    50: aload_3        
        //    51: astore_2       
        //    52: aload_0        
        //    53: sipush          15000
        //    56: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //    59: aload           4
        //    61: astore_1       
        //    62: aload_3        
        //    63: astore_2       
        //    64: aload_0        
        //    65: ldc             "GET"
        //    67: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //    70: aload           4
        //    72: astore_1       
        //    73: aload_3        
        //    74: astore_2       
        //    75: aload_0        
        //    76: iconst_1       
        //    77: invokevirtual   java/net/HttpURLConnection.setDoInput:(Z)V
        //    80: aload           4
        //    82: astore_1       
        //    83: aload_3        
        //    84: astore_2       
        //    85: aload_0        
        //    86: invokevirtual   java/net/HttpURLConnection.connect:()V
        //    89: aload           4
        //    91: astore_1       
        //    92: aload_3        
        //    93: astore_2       
        //    94: aload_0        
        //    95: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //    98: sipush          400
        //   101: if_icmplt       131
        //   104: aload           4
        //   106: astore_1       
        //   107: aload_3        
        //   108: astore_2       
        //   109: invokestatic    com/moat/analytics/mobile/vng/a/b/a.a:()Lcom/moat/analytics/mobile/vng/a/b/a;
        //   112: astore_0       
        //   113: aload_0        
        //   114: astore_1       
        //   115: aload_1        
        //   116: astore_0       
        //   117: iconst_0       
        //   118: ifeq            129
        //   121: new             Ljava/lang/NullPointerException;
        //   124: dup            
        //   125: invokespecial   java/lang/NullPointerException.<init>:()V
        //   128: athrow         
        //   129: aload_0        
        //   130: areturn        
        //   131: aload           4
        //   133: astore_1       
        //   134: aload_3        
        //   135: astore_2       
        //   136: aload_0        
        //   137: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   140: astore_3       
        //   141: aload_3        
        //   142: astore_1       
        //   143: aload_3        
        //   144: astore_2       
        //   145: aload_3        
        //   146: invokestatic    com/moat/analytics/mobile/vng/q.a:(Ljava/io/InputStream;)Ljava/lang/String;
        //   149: invokestatic    com/moat/analytics/mobile/vng/a/b/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/vng/a/b/a;
        //   152: astore_0       
        //   153: aload_0        
        //   154: astore_1       
        //   155: aload_1        
        //   156: astore_0       
        //   157: aload_3        
        //   158: ifnull          129
        //   161: aload_3        
        //   162: invokevirtual   java/io/InputStream.close:()V
        //   165: aload_1        
        //   166: areturn        
        //   167: astore_0       
        //   168: aload_1        
        //   169: areturn        
        //   170: astore_0       
        //   171: aload_1        
        //   172: astore_2       
        //   173: invokestatic    com/moat/analytics/mobile/vng/a/b/a.a:()Lcom/moat/analytics/mobile/vng/a/b/a;
        //   176: astore_0       
        //   177: aload_0        
        //   178: astore_2       
        //   179: aload_2        
        //   180: astore_0       
        //   181: aload_1        
        //   182: ifnull          129
        //   185: aload_1        
        //   186: invokevirtual   java/io/InputStream.close:()V
        //   189: aload_2        
        //   190: areturn        
        //   191: astore_0       
        //   192: aload_2        
        //   193: areturn        
        //   194: astore_0       
        //   195: aload_2        
        //   196: ifnull          203
        //   199: aload_2        
        //   200: invokevirtual   java/io/InputStream.close:()V
        //   203: aload_0        
        //   204: athrow         
        //   205: astore_0       
        //   206: aload_1        
        //   207: areturn        
        //   208: astore_1       
        //   209: goto            203
        //    Signature:
        //  (Ljava/lang/String;)Lcom/moat/analytics/mobile/vng/a/b/a<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     25     170    194    Ljava/io/IOException;
        //  10     25     194    205    Any
        //  30     35     170    194    Ljava/io/IOException;
        //  30     35     194    205    Any
        //  40     47     170    194    Ljava/io/IOException;
        //  40     47     194    205    Any
        //  52     59     170    194    Ljava/io/IOException;
        //  52     59     194    205    Any
        //  64     70     170    194    Ljava/io/IOException;
        //  64     70     194    205    Any
        //  75     80     170    194    Ljava/io/IOException;
        //  75     80     194    205    Any
        //  85     89     170    194    Ljava/io/IOException;
        //  85     89     194    205    Any
        //  94     104    170    194    Ljava/io/IOException;
        //  94     104    194    205    Any
        //  109    113    170    194    Ljava/io/IOException;
        //  109    113    194    205    Any
        //  121    129    205    208    Ljava/io/IOException;
        //  136    141    170    194    Ljava/io/IOException;
        //  136    141    194    205    Any
        //  145    153    170    194    Ljava/io/IOException;
        //  145    153    194    205    Any
        //  161    165    167    170    Ljava/io/IOException;
        //  173    177    194    205    Any
        //  185    189    191    194    Ljava/io/IOException;
        //  199    203    208    212    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 140, Size: 140
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
    
    private static String a(final InputStream inputStream) {
        final char[] array = new char[256];
        final StringBuilder sb = new StringBuilder();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int n = 0;
        int n2;
        do {
            final int read = inputStreamReader.read(array, 0, array.length);
            if (read <= 0) {
                break;
            }
            n2 = n + read;
            sb.append(array, 0, read);
        } while ((n = n2) < 1024);
        return sb.toString();
    }
}
