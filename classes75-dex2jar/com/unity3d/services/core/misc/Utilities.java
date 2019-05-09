// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.misc;

import android.os.Handler;
import android.os.Looper;
import java.io.FileInputStream;
import java.io.File;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONObject;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import com.unity3d.services.core.log.DeviceLog;
import java.security.MessageDigest;
import java.io.InputStream;

public class Utilities
{
    public static String Sha256(final InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        MessageDigest instance;
        try {
            instance = MessageDigest.getInstance("SHA-256");
            final byte[] array = new byte[4096];
            while (true) {
                final int read = inputStream.read(array);
                if (read == -1) {
                    break;
                }
                instance.update(array, 0, read);
            }
        }
        catch (NoSuchAlgorithmException ex) {
            DeviceLog.exception("SHA-256 algorithm not found", ex);
            return null;
        }
        return toHexString(instance.digest());
    }
    
    public static String Sha256(final String s) {
        return Sha256(s.getBytes());
    }
    
    public static String Sha256(final byte[] array) {
        if (array == null) {
            return null;
        }
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(array, 0, array.length);
            return toHexString(instance.digest());
        }
        catch (NoSuchAlgorithmException ex) {
            DeviceLog.exception("SHA-256 algorithm not found", ex);
            return null;
        }
    }
    
    public static JSONObject mergeJsonObjects(final JSONObject jsonObject, final JSONObject jsonObject2) throws JSONException {
        if (jsonObject == null) {
            return jsonObject2;
        }
        if (jsonObject2 == null) {
            return jsonObject;
        }
        final JSONObject jsonObject3 = new JSONObject();
        final Iterator keys = jsonObject2.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            jsonObject3.put(s, jsonObject2.get(s));
        }
        final Iterator keys2 = jsonObject.keys();
        while (keys2.hasNext()) {
            final String s2 = keys2.next();
            if (jsonObject3.has(s2) && jsonObject3.get(s2) instanceof JSONObject && jsonObject.get(s2) instanceof JSONObject) {
                jsonObject3.put(s2, (Object)mergeJsonObjects(jsonObject.getJSONObject(s2), jsonObject3.getJSONObject(s2)));
            }
            else {
                jsonObject3.put(s2, jsonObject.get(s2));
            }
        }
        return jsonObject3;
    }
    
    public static String readFile(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       8
        //     4: aconst_null    
        //     5: astore_2       
        //     6: aload_2        
        //     7: areturn        
        //     8: ldc             ""
        //    10: astore_3       
        //    11: aconst_null    
        //    12: astore_1       
        //    13: aconst_null    
        //    14: astore_2       
        //    15: aload_0        
        //    16: invokevirtual   java/io/File.exists:()Z
        //    19: ifeq            126
        //    22: aload_0        
        //    23: invokevirtual   java/io/File.canRead:()Z
        //    26: ifeq            126
        //    29: new             Ljava/io/FileReader;
        //    32: dup            
        //    33: aload_0        
        //    34: invokespecial   java/io/FileReader.<init>:(Ljava/io/File;)V
        //    37: astore_0       
        //    38: new             Ljava/io/BufferedReader;
        //    41: dup            
        //    42: aload_0        
        //    43: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    46: astore_2       
        //    47: aload_3        
        //    48: astore_1       
        //    49: aload_2        
        //    50: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    53: astore_3       
        //    54: aload_3        
        //    55: ifnull          67
        //    58: aload_1        
        //    59: aload_3        
        //    60: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    63: astore_1       
        //    64: goto            49
        //    67: aload_2        
        //    68: ifnull          75
        //    71: aload_2        
        //    72: invokevirtual   java/io/BufferedReader.close:()V
        //    75: aload_1        
        //    76: astore_2       
        //    77: aload_0        
        //    78: ifnull          6
        //    81: aload_0        
        //    82: invokevirtual   java/io/FileReader.close:()V
        //    85: aload_1        
        //    86: areturn        
        //    87: astore_0       
        //    88: ldc             "Couldn't close FileReader"
        //    90: aload_0        
        //    91: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //    94: aload_1        
        //    95: areturn        
        //    96: astore_3       
        //    97: aload_2        
        //    98: astore_0       
        //    99: aload_3        
        //   100: astore_2       
        //   101: ldc             "Problem reading file"
        //   103: aload_2        
        //   104: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   107: aconst_null    
        //   108: astore_3       
        //   109: aload_1        
        //   110: astore_2       
        //   111: aload_3        
        //   112: astore_1       
        //   113: goto            67
        //   116: astore_2       
        //   117: ldc             "Couldn't close BufferedReader"
        //   119: aload_2        
        //   120: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   123: goto            75
        //   126: ldc             "File did not exist or couldn't be read"
        //   128: invokestatic    com/unity3d/services/core/log/DeviceLog.error:(Ljava/lang/String;)V
        //   131: aconst_null    
        //   132: areturn        
        //   133: astore_2       
        //   134: goto            101
        //   137: astore_3       
        //   138: aload_2        
        //   139: astore_1       
        //   140: aload_3        
        //   141: astore_2       
        //   142: goto            101
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  29     38     96     101    Ljava/lang/Exception;
        //  38     47     133    137    Ljava/lang/Exception;
        //  49     54     137    145    Ljava/lang/Exception;
        //  58     64     137    145    Ljava/lang/Exception;
        //  71     75     116    126    Ljava/lang/Exception;
        //  81     85     87     96     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 90, Size: 90
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
    
    public static byte[] readFileBytes(final File file) throws IOException {
        if (file == null) {
            return null;
        }
        final FileInputStream fileInputStream = new FileInputStream(file);
        final byte[] array = new byte[(int)file.length()];
        int n = 0;
        int n2;
        if (file.length() < 4096) {
            n2 = (int)file.length();
        }
        else {
            n2 = 4096;
        }
        while (true) {
            final int read = fileInputStream.read(array, n, n2);
            if (read <= 0) {
                break;
            }
            final int n3 = n += read;
            if (file.length() - n3 >= 4096) {
                continue;
            }
            n2 = (int)file.length() - n3;
            n = n3;
        }
        fileInputStream.close();
        return array;
    }
    
    public static void runOnUiThread(final Runnable runnable) {
        runOnUiThread(runnable, 0L);
    }
    
    public static void runOnUiThread(final Runnable runnable, final long n) {
        final Handler handler = new Handler(Looper.getMainLooper());
        if (n > 0L) {
            handler.postDelayed(runnable, n);
            return;
        }
        handler.post(runnable);
    }
    
    public static String toHexString(final byte[] array) {
        String string = "";
        for (int length = array.length, i = 0; i < length; ++i) {
            final int n = array[i] & 0xFF;
            String string2 = string;
            if (n <= 15) {
                string2 = string + "0";
            }
            string = string2 + Integer.toHexString(n);
        }
        return string;
    }
    
    public static boolean writeFile(final File p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       8
        //     4: iconst_0       
        //     5: istore_3       
        //     6: iload_3        
        //     7: ireturn        
        //     8: aconst_null    
        //     9: astore          4
        //    11: aconst_null    
        //    12: astore          6
        //    14: iconst_1       
        //    15: istore_2       
        //    16: new             Ljava/io/FileOutputStream;
        //    19: dup            
        //    20: aload_0        
        //    21: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    24: astore          5
        //    26: aload           5
        //    28: aload_1        
        //    29: invokevirtual   java/lang/String.getBytes:()[B
        //    32: invokevirtual   java/io/FileOutputStream.write:([B)V
        //    35: aload           5
        //    37: invokevirtual   java/io/FileOutputStream.flush:()V
        //    40: aload           5
        //    42: ifnull          50
        //    45: aload           5
        //    47: invokevirtual   java/io/FileOutputStream.close:()V
        //    50: iload_2        
        //    51: istore_3       
        //    52: iload_2        
        //    53: ifeq            6
        //    56: new             Ljava/lang/StringBuilder;
        //    59: dup            
        //    60: invokespecial   java/lang/StringBuilder.<init>:()V
        //    63: ldc             "Wrote file: "
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: aload_0        
        //    69: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    78: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //    81: iload_2        
        //    82: ireturn        
        //    83: astore_1       
        //    84: ldc             "Error closing FileOutputStream"
        //    86: aload_1        
        //    87: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //    90: goto            50
        //    93: astore          5
        //    95: aload           6
        //    97: astore_1       
        //    98: iconst_0       
        //    99: istore_3       
        //   100: aload_1        
        //   101: astore          4
        //   103: ldc             "Could not write file"
        //   105: aload           5
        //   107: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   110: iload_3        
        //   111: istore_2       
        //   112: aload_1        
        //   113: ifnull          50
        //   116: aload_1        
        //   117: invokevirtual   java/io/FileOutputStream.close:()V
        //   120: iload_3        
        //   121: istore_2       
        //   122: goto            50
        //   125: astore_1       
        //   126: ldc             "Error closing FileOutputStream"
        //   128: aload_1        
        //   129: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   132: iload_3        
        //   133: istore_2       
        //   134: goto            50
        //   137: astore_0       
        //   138: aload           4
        //   140: ifnull          148
        //   143: aload           4
        //   145: invokevirtual   java/io/FileOutputStream.close:()V
        //   148: aload_0        
        //   149: athrow         
        //   150: astore_1       
        //   151: ldc             "Error closing FileOutputStream"
        //   153: aload_1        
        //   154: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   157: goto            148
        //   160: astore_0       
        //   161: aload           5
        //   163: astore          4
        //   165: goto            138
        //   168: astore          4
        //   170: aload           5
        //   172: astore_1       
        //   173: aload           4
        //   175: astore          5
        //   177: goto            98
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  16     26     93     98     Ljava/lang/Exception;
        //  16     26     137    138    Any
        //  26     40     168    180    Ljava/lang/Exception;
        //  26     40     160    168    Any
        //  45     50     83     93     Ljava/lang/Exception;
        //  103    110    137    138    Any
        //  116    120    125    137    Ljava/lang/Exception;
        //  143    148    150    160    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
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
