// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.support.annotation.WorkerThread;
import java.io.UnsupportedEncodingException;
import android.text.TextUtils;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import android.util.Base64;
import android.support.v4.content.ContextCompat;
import java.io.RandomAccessFile;
import com.google.android.gms.internal.firebase_messaging.zzc;
import java.io.FileInputStream;
import android.content.SharedPreferences$Editor;
import android.util.Log;
import java.security.KeyPair;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
import java.util.Properties;
import java.nio.channels.FileChannel;
import java.io.IOException;
import java.io.File;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.content.Context;

final class zzy
{
    @Nullable
    private final zzz zza(final Context p0, final String p1, final zzz p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_3       
        //     3: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //     6: ifeq            17
        //     9: ldc             "FirebaseInstanceId"
        //    11: ldc             "Writing key to properties file"
        //    13: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    16: pop            
        //    17: new             Ljava/util/Properties;
        //    20: dup            
        //    21: invokespecial   java/util/Properties.<init>:()V
        //    24: astore          9
        //    26: aload           9
        //    28: ldc             "pub"
        //    30: aload_3        
        //    31: invokestatic    com/google/firebase/iid/zzz.zza:(Lcom/google/firebase/iid/zzz;)Ljava/lang/String;
        //    34: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //    37: pop            
        //    38: aload           9
        //    40: ldc             "pri"
        //    42: aload_3        
        //    43: invokestatic    com/google/firebase/iid/zzz.zzb:(Lcom/google/firebase/iid/zzz;)Ljava/lang/String;
        //    46: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //    49: pop            
        //    50: aload           9
        //    52: ldc             "cre"
        //    54: aload_3        
        //    55: invokestatic    com/google/firebase/iid/zzz.zzc:(Lcom/google/firebase/iid/zzz;)J
        //    58: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    61: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //    64: pop            
        //    65: aload_1        
        //    66: aload_2        
        //    67: invokestatic    com/google/firebase/iid/zzy.zzf:(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
        //    70: astore_1       
        //    71: aload_1        
        //    72: invokevirtual   java/io/File.createNewFile:()Z
        //    75: pop            
        //    76: new             Ljava/io/RandomAccessFile;
        //    79: dup            
        //    80: aload_1        
        //    81: ldc             "rw"
        //    83: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    86: astore          7
        //    88: aload           7
        //    90: invokevirtual   java/io/RandomAccessFile.getChannel:()Ljava/nio/channels/FileChannel;
        //    93: astore          8
        //    95: aload           8
        //    97: invokevirtual   java/nio/channels/FileChannel.lock:()Ljava/nio/channels/FileLock;
        //   100: pop            
        //   101: iload           4
        //   103: ifeq            202
        //   106: aload           8
        //   108: invokevirtual   java/nio/channels/FileChannel.size:()J
        //   111: lstore          5
        //   113: lload           5
        //   115: lconst_0       
        //   116: lcmp           
        //   117: ifle            202
        //   120: aload           8
        //   122: lconst_0       
        //   123: invokevirtual   java/nio/channels/FileChannel.position:(J)Ljava/nio/channels/FileChannel;
        //   126: pop            
        //   127: aload           8
        //   129: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/nio/channels/FileChannel;)Lcom/google/firebase/iid/zzz;
        //   132: astore_1       
        //   133: aload           8
        //   135: ifnull          144
        //   138: aconst_null    
        //   139: aload           8
        //   141: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
        //   144: aconst_null    
        //   145: aload           7
        //   147: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/io/RandomAccessFile;)V
        //   150: aload_1        
        //   151: areturn        
        //   152: astore_1       
        //   153: ldc             "FirebaseInstanceId"
        //   155: iconst_3       
        //   156: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   159: ifeq            202
        //   162: aload_1        
        //   163: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   166: astore_1       
        //   167: ldc             "FirebaseInstanceId"
        //   169: new             Ljava/lang/StringBuilder;
        //   172: dup            
        //   173: aload_1        
        //   174: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   177: invokevirtual   java/lang/String.length:()I
        //   180: bipush          64
        //   182: iadd           
        //   183: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   186: ldc             "Tried reading key pair before writing new one, but failed with: "
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: aload_1        
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   198: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   201: pop            
        //   202: aload           8
        //   204: lconst_0       
        //   205: invokevirtual   java/nio/channels/FileChannel.position:(J)Ljava/nio/channels/FileChannel;
        //   208: pop            
        //   209: aload           9
        //   211: aload           8
        //   213: invokestatic    java/nio/channels/Channels.newOutputStream:(Ljava/nio/channels/WritableByteChannel;)Ljava/io/OutputStream;
        //   216: aconst_null    
        //   217: invokevirtual   java/util/Properties.store:(Ljava/io/OutputStream;Ljava/lang/String;)V
        //   220: aload           8
        //   222: ifnull          231
        //   225: aconst_null    
        //   226: aload           8
        //   228: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
        //   231: aconst_null    
        //   232: aload           7
        //   234: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/io/RandomAccessFile;)V
        //   237: aload_3        
        //   238: areturn        
        //   239: astore_1       
        //   240: aload_1        
        //   241: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   244: astore_1       
        //   245: ldc             "FirebaseInstanceId"
        //   247: new             Ljava/lang/StringBuilder;
        //   250: dup            
        //   251: aload_1        
        //   252: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   255: invokevirtual   java/lang/String.length:()I
        //   258: bipush          21
        //   260: iadd           
        //   261: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   264: ldc             "Failed to write key: "
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: aload_1        
        //   270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   276: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   279: pop            
        //   280: aconst_null    
        //   281: areturn        
        //   282: astore_1       
        //   283: aload_1        
        //   284: athrow         
        //   285: astore_2       
        //   286: aload           8
        //   288: ifnull          297
        //   291: aload_1        
        //   292: aload           8
        //   294: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
        //   297: aload_2        
        //   298: athrow         
        //   299: astore_1       
        //   300: aload_1        
        //   301: athrow         
        //   302: astore_2       
        //   303: aload_1        
        //   304: aload           7
        //   306: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/io/RandomAccessFile;)V
        //   309: aload_2        
        //   310: athrow         
        //   311: astore_2       
        //   312: aconst_null    
        //   313: astore_1       
        //   314: goto            303
        //   317: astore_2       
        //   318: aconst_null    
        //   319: astore_1       
        //   320: goto            286
        //   323: astore_1       
        //   324: goto            153
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                          
        //  -----  -----  -----  -----  ------------------------------
        //  71     88     239    282    Ljava/io/IOException;
        //  88     95     299    303    Ljava/lang/Throwable;
        //  88     95     311    317    Any
        //  95     101    282    286    Ljava/lang/Throwable;
        //  95     101    317    323    Any
        //  106    113    282    286    Ljava/lang/Throwable;
        //  106    113    317    323    Any
        //  120    133    323    327    Ljava/io/IOException;
        //  120    133    152    153    Lcom/google/firebase/iid/zzaa;
        //  120    133    282    286    Ljava/lang/Throwable;
        //  120    133    317    323    Any
        //  138    144    299    303    Ljava/lang/Throwable;
        //  138    144    311    317    Any
        //  144    150    239    282    Ljava/io/IOException;
        //  153    202    282    286    Ljava/lang/Throwable;
        //  153    202    317    323    Any
        //  202    220    282    286    Ljava/lang/Throwable;
        //  202    220    317    323    Any
        //  225    231    299    303    Ljava/lang/Throwable;
        //  225    231    311    317    Any
        //  231    237    239    282    Ljava/io/IOException;
        //  283    285    285    286    Any
        //  291    297    299    303    Ljava/lang/Throwable;
        //  291    297    311    317    Any
        //  297    299    299    303    Ljava/lang/Throwable;
        //  297    299    311    317    Any
        //  300    302    302    303    Any
        //  303    311    239    282    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 171, Size: 171
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
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
    
    @Nullable
    private static zzz zza(final SharedPreferences sharedPreferences, final String s) throws zzaa {
        final String string = sharedPreferences.getString(zzaw.zzd(s, "|P|"), (String)null);
        final String string2 = sharedPreferences.getString(zzaw.zzd(s, "|K|"), (String)null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzz(zzc(string, string2), zzb(sharedPreferences, s));
    }
    
    private final zzz zza(final File p0) throws zzaa, IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: new             Ljava/io/FileInputStream;
        //     5: dup            
        //     6: aload_1        
        //     7: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    10: astore          4
        //    12: aload           4
        //    14: invokevirtual   java/io/FileInputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //    17: astore          5
        //    19: aload           5
        //    21: lconst_0       
        //    22: ldc2_w          9223372036854775807
        //    25: iconst_1       
        //    26: invokevirtual   java/nio/channels/FileChannel.lock:(JJZ)Ljava/nio/channels/FileLock;
        //    29: pop            
        //    30: aload           5
        //    32: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/nio/channels/FileChannel;)Lcom/google/firebase/iid/zzz;
        //    35: astore_1       
        //    36: aload           5
        //    38: ifnull          47
        //    41: aconst_null    
        //    42: aload           5
        //    44: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
        //    47: aconst_null    
        //    48: aload           4
        //    50: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/io/FileInputStream;)V
        //    53: aload_1        
        //    54: areturn        
        //    55: astore_1       
        //    56: aload_1        
        //    57: athrow         
        //    58: astore_2       
        //    59: aload           5
        //    61: ifnull          70
        //    64: aload_1        
        //    65: aload           5
        //    67: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
        //    70: aload_2        
        //    71: athrow         
        //    72: astore_1       
        //    73: aload_1        
        //    74: athrow         
        //    75: astore_2       
        //    76: aload_1        
        //    77: aload           4
        //    79: invokestatic    com/google/firebase/iid/zzy.zza:(Ljava/lang/Throwable;Ljava/io/FileInputStream;)V
        //    82: aload_2        
        //    83: athrow         
        //    84: astore_2       
        //    85: aload_3        
        //    86: astore_1       
        //    87: goto            76
        //    90: astore_2       
        //    91: aconst_null    
        //    92: astore_1       
        //    93: goto            59
        //    Exceptions:
        //  throws com.google.firebase.iid.zzaa
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     19     72     76     Ljava/lang/Throwable;
        //  12     19     84     90     Any
        //  19     36     55     59     Ljava/lang/Throwable;
        //  19     36     90     96     Any
        //  41     47     72     76     Ljava/lang/Throwable;
        //  41     47     84     90     Any
        //  56     58     58     59     Any
        //  64     70     72     76     Ljava/lang/Throwable;
        //  64     70     84     90     Any
        //  70     72     72     76     Ljava/lang/Throwable;
        //  70     72     84     90     Any
        //  73     75     75     76     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 57, Size: 57
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
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
    
    private static zzz zza(final FileChannel fileChannel) throws zzaa, IOException {
        final Properties properties = new Properties();
        properties.load(Channels.newInputStream(fileChannel));
        final String property = properties.getProperty("pub");
        final String property2 = properties.getProperty("pri");
        if (property == null || property2 == null) {
            throw new zzaa("Invalid properties file");
        }
        final KeyPair zzc = zzc(property, property2);
        try {
            return new zzz(zzc, Long.parseLong(properties.getProperty("cre")));
        }
        catch (NumberFormatException ex) {
            throw new zzaa(ex);
        }
    }
    
    static void zza(final Context context) {
        final File[] listFiles = zzb(context).listFiles();
        for (int length = listFiles.length, i = 0; i < length; ++i) {
            final File file = listFiles[i];
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }
    
    private final void zza(Context sharedPreferences, final String s, final zzz zzz) {
        sharedPreferences = (Context)sharedPreferences.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzz.equals(zza((SharedPreferences)sharedPreferences, s))) {
                return;
            }
        }
        catch (zzaa zzaa) {}
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to shared preferences");
        }
        final SharedPreferences$Editor edit = ((SharedPreferences)sharedPreferences).edit();
        edit.putString(zzaw.zzd(s, "|P|"), zzz.zzv());
        edit.putString(zzaw.zzd(s, "|K|"), zzz.zzw());
        edit.putString(zzaw.zzd(s, "cre"), String.valueOf(zzz.zzbs));
        edit.commit();
    }
    
    private static long zzb(final SharedPreferences sharedPreferences, final String s) {
        final String string = sharedPreferences.getString(zzaw.zzd(s, "cre"), (String)null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            }
            catch (NumberFormatException ex) {}
        }
        return 0L;
    }
    
    private static File zzb(final Context context) {
        final File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }
    
    private static KeyPair zzc(String decode, final String s) throws zzaa {
        byte[] decode2;
        try {
            decode = (IllegalArgumentException)(Object)Base64.decode((String)decode, 8);
            decode2 = Base64.decode(s, 8);
            final String s2 = "RSA";
            final KeyFactory instance = KeyFactory.getInstance(s2);
            final KeyFactory instance2 = instance;
            final IllegalArgumentException ex = decode;
            final X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec((byte[])(Object)ex);
            final PublicKey publicKey = instance2.generatePublic(x509EncodedKeySpec);
            final KeyFactory keyFactory = instance;
            final byte[] array = decode2;
            final PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(array);
            final PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            final KeyPair keyPair = new KeyPair(publicKey, privateKey);
            final KeyPair keyPair2;
            decode = (IllegalArgumentException)(keyPair2 = keyPair);
            return keyPair2;
        }
        catch (IllegalArgumentException decode) {
            throw new zzaa(decode);
        }
        try {
            final String s2 = "RSA";
            final KeyFactory instance2;
            final KeyFactory instance = instance2 = KeyFactory.getInstance(s2);
            final IllegalArgumentException ex = decode;
            final X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec((byte[])(Object)ex);
            final PublicKey publicKey = instance2.generatePublic(x509EncodedKeySpec);
            final KeyFactory keyFactory = instance;
            final byte[] array = decode2;
            final PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(array);
            final PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            final KeyPair keyPair = new KeyPair(publicKey, privateKey);
            final KeyPair keyPair2;
            decode = (IllegalArgumentException)(keyPair2 = keyPair);
            return keyPair2;
        }
        catch (NoSuchAlgorithmException ex2) {}
        catch (InvalidKeySpecException decode) {
            goto Label_0066;
        }
    }
    
    @Nullable
    private final zzz zzd(Context context, final String s) throws zzaa {
    Label_0054_Outer:
        while (true) {
            while (true) {
                Context context2 = null;
            Label_0064:
                while (true) {
                    try {
                        final zzz zze = this.zze(context, s);
                        if (zze != null) {
                            this.zza(context, s, zze);
                            return zze;
                        }
                        context2 = null;
                        try {
                            final zzz zza = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), s);
                            if (zza != null) {
                                this.zza(context, s, zza, false);
                                return zza;
                            }
                            break Label_0064;
                        }
                        catch (zzaa zzaa) {}
                        if (context != null) {
                            throw context;
                        }
                        break;
                    }
                    catch (zzaa context2) {
                        continue Label_0054_Outer;
                    }
                    break;
                }
                context = context2;
                continue;
            }
        }
        return null;
    }
    
    @Nullable
    private final zzz zze(Context zza, String zzf) throws zzaa {
        zzf = (String)zzf((Context)zza, zzf);
        if (!((File)zzf).exists()) {
            return null;
        }
        try {
            zza = (IOException)this.zza((File)zzf);
            return (zzz)zza;
        }
        catch (zzaa zzaa) {}
        catch (IOException zza) {
            goto Label_0024;
        }
        try {
            return this.zza((File)zzf);
        }
        catch (IOException ex) {}
    }
    
    private static File zzf(final Context context, String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            s = "com.google.InstanceId.properties";
        }
        else {
            try {
                s = Base64.encodeToString(s.getBytes("UTF-8"), 11);
                s = new StringBuilder(String.valueOf(s).length() + 33).append("com.google.InstanceId_").append(s).append(".properties").toString();
            }
            catch (UnsupportedEncodingException ex) {
                throw new AssertionError((Object)ex);
            }
        }
        return new File(zzb(context), s);
    }
    
    @WorkerThread
    final zzz zzb(final Context context, final String s) throws zzaa {
        final zzz zzd = this.zzd(context, s);
        if (zzd != null) {
            return zzd;
        }
        return this.zzc(context, s);
    }
    
    @WorkerThread
    final zzz zzc(final Context context, final String s) {
        final zzz zzz = new zzz(zza.zzb(), System.currentTimeMillis());
        final zzz zza = this.zza(context, s, zzz, true);
        if (zza != null && !zza.equals(zzz)) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
            }
            return zza;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Generated new key");
        }
        this.zza(context, s, zzz);
        return zzz;
    }
}
