// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.utils;

import android.os.AsyncTask;

public class IronSourceAsyncHttpRequestTask extends AsyncTask<String, Integer, Integer>
{
    protected Integer doInBackground(final String... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_2       
        //     2: aconst_null    
        //     3: astore          5
        //     5: aconst_null    
        //     6: astore          4
        //     8: aconst_null    
        //     9: astore_3       
        //    10: new             Ljava/net/URL;
        //    13: dup            
        //    14: aload_1        
        //    15: iconst_0       
        //    16: aaload         
        //    17: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    20: astore          6
        //    22: aload           5
        //    24: astore_1       
        //    25: aload           4
        //    27: astore_2       
        //    28: aload           6
        //    30: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    33: checkcast       Ljava/net/HttpURLConnection;
        //    36: astore_3       
        //    37: aload_3        
        //    38: astore_1       
        //    39: aload_3        
        //    40: astore_2       
        //    41: aload_3        
        //    42: sipush          3000
        //    45: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //    48: aload_3        
        //    49: astore_1       
        //    50: aload_3        
        //    51: astore_2       
        //    52: aload_3        
        //    53: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //    56: pop            
        //    57: aload_3        
        //    58: ifnull          115
        //    61: aload_3        
        //    62: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //    65: iconst_1       
        //    66: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    69: areturn        
        //    70: astore_2       
        //    71: aload_3        
        //    72: astore_1       
        //    73: aload_2        
        //    74: astore_3       
        //    75: aload_1        
        //    76: astore_2       
        //    77: aload_3        
        //    78: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    81: aload_1        
        //    82: ifnull          65
        //    85: aload_1        
        //    86: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //    89: goto            65
        //    92: astore_3       
        //    93: aload_2        
        //    94: astore_1       
        //    95: aload_1        
        //    96: ifnull          103
        //    99: aload_1        
        //   100: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   103: aload_3        
        //   104: athrow         
        //   105: astore_3       
        //   106: goto            95
        //   109: astore_3       
        //   110: aload_2        
        //   111: astore_1       
        //   112: goto            75
        //   115: goto            65
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     22     70     75     Ljava/lang/Exception;
        //  10     22     92     95     Any
        //  28     37     109    115    Ljava/lang/Exception;
        //  28     37     105    109    Any
        //  41     48     109    115    Ljava/lang/Exception;
        //  41     48     105    109    Any
        //  52     57     109    115    Ljava/lang/Exception;
        //  52     57     105    109    Any
        //  77     81     92     95     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException
        //     at java.util.Collections$1.remove(Collections.java:4684)
        //     at java.util.AbstractCollection.removeAll(AbstractCollection.java:376)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2968)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
}
