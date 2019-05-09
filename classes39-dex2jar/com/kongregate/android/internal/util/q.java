// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import org.json.JSONObject;
import android.content.pm.ProviderInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import com.kongregate.android.internal.sdk.NativeAPI;
import java.util.LinkedList;
import java.security.SecureRandom;
import java.util.Iterator;
import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.crypto.CipherInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import java.util.List;
import android.annotation.TargetApi;
import java.io.File;
import android.content.pm.ApplicationInfo;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import com.kongregate.o.c.d;
import java.util.concurrent.Callable;
import android.content.SharedPreferences;
import java.util.HashMap;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;
import java.util.Map;
import android.content.Context;
import javax.crypto.SecretKey;

public class q
{
    private final SecretKey a;
    private final Context b;
    private final String c;
    private final Map<String, String> d;
    private final Object e;
    
    private q(final Context b, final String s) {
        this.e = new Object();
        if (b == null) {
            throw new IllegalArgumentException("Context is null");
        }
        if (StringUtils.a((CharSequence)s)) {
            throw new IllegalArgumentException("filename can't be empty");
        }
        this.b = b;
        final SharedPreferences a = this.a(b);
        final String string = a.getString("secret", (String)null);
        final String string2 = a.getString("file_postfix", (String)null);
        this.c = s + string2;
        if (!StringUtils.a((CharSequence)string) && !StringUtils.a((CharSequence)string2)) {
            this.a = new SecretKeySpec(Base64.decode(string, 0), "AES");
            j.b("shared filename is: " + this.c);
            this.d = this.e();
            return;
        }
        this.a = null;
        this.d = new HashMap<String, String>(0);
        j.d("unable to obtain a secret key or shared file location");
    }
    
    private SharedPreferences a(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "kongregate_shared_secret"
        //     3: iconst_0       
        //     4: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //     7: astore          8
        //     9: aload           8
        //    11: ldc             "secret"
        //    13: aconst_null    
        //    14: invokeinterface android/content/SharedPreferences.getString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    19: astore          6
        //    21: aload           8
        //    23: ldc             "file_postfix"
        //    25: aconst_null    
        //    26: invokeinterface android/content/SharedPreferences.getString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    31: astore          5
        //    33: aload           6
        //    35: invokestatic    com/kongregate/android/internal/util/StringUtils.a:(Ljava/lang/CharSequence;)Z
        //    38: ifne            57
        //    41: aload           5
        //    43: invokestatic    com/kongregate/android/internal/util/StringUtils.a:(Ljava/lang/CharSequence;)Z
        //    46: ifne            57
        //    49: ldc             "found secret in prefs"
        //    51: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //    54: aload           8
        //    56: areturn        
        //    57: aload_1        
        //    58: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    61: astore          7
        //    63: aload           7
        //    65: ifnull          532
        //    68: invokestatic    com/kongregate/android/internal/sdk/NativeAPI.g:()Lcom/kongregate/o/d/a;
        //    71: invokevirtual   com/kongregate/o/d/a.d:()Z
        //    74: ifeq            532
        //    77: ldc             "check providers for secret"
        //    79: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //    82: aload           7
        //    84: bipush          8
        //    86: invokevirtual   android/content/pm/PackageManager.getInstalledPackages:(I)Ljava/util/List;
        //    89: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    94: astore          9
        //    96: aload           6
        //    98: astore          4
        //   100: aload           9
        //   102: invokeinterface java/util/Iterator.hasNext:()Z
        //   107: ifeq            429
        //   110: aload           9
        //   112: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   117: checkcast       Landroid/content/pm/PackageInfo;
        //   120: getfield        android/content/pm/PackageInfo.providers:[Landroid/content/pm/ProviderInfo;
        //   123: astore          10
        //   125: aload           10
        //   127: ifnull          585
        //   130: aload           10
        //   132: arraylength    
        //   133: istore_3       
        //   134: iconst_0       
        //   135: istore_2       
        //   136: iload_2        
        //   137: iload_3        
        //   138: if_icmpge       402
        //   141: aload           10
        //   143: iload_2        
        //   144: aaload         
        //   145: astore          6
        //   147: ldc             "com.kongregate.permission.ReadSharedData2"
        //   149: aload           6
        //   151: getfield        android/content/pm/ProviderInfo.readPermission:Ljava/lang/String;
        //   154: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   157: ifeq            379
        //   160: new             Ljava/lang/StringBuilder;
        //   163: dup            
        //   164: invokespecial   java/lang/StringBuilder.<init>:()V
        //   167: ldc             "checking authority: "
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: aload           6
        //   174: getfield        android/content/pm/ProviderInfo.authority:Ljava/lang/String;
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   183: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //   186: aload_1        
        //   187: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //   190: new             Ljava/lang/StringBuilder;
        //   193: dup            
        //   194: invokespecial   java/lang/StringBuilder.<init>:()V
        //   197: ldc             "content://"
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: aload           6
        //   204: getfield        android/content/pm/ProviderInfo.authority:Ljava/lang/String;
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: ldc             "/"
        //   212: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   215: ldc             "SharedSecret"
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   223: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //   226: aconst_null    
        //   227: aconst_null    
        //   228: aconst_null    
        //   229: aconst_null    
        //   230: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   233: astore          6
        //   235: aload           6
        //   237: ifnull          379
        //   240: aload           6
        //   242: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   247: ifeq            570
        //   250: aload           6
        //   252: aload           6
        //   254: ldc             "secret"
        //   256: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   261: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   266: astore          5
        //   268: aload           6
        //   270: aload           6
        //   272: ldc             "file_postfix"
        //   274: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   279: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   284: astore          4
        //   286: aload           6
        //   288: invokeinterface android/database/Cursor.isClosed:()Z
        //   293: ifne            303
        //   296: aload           6
        //   298: invokeinterface android/database/Cursor.close:()V
        //   303: aload           4
        //   305: astore          7
        //   307: aload           5
        //   309: astore          6
        //   311: aload           5
        //   313: invokestatic    com/kongregate/android/internal/util/StringUtils.a:(Ljava/lang/CharSequence;)Z
        //   316: ifne            387
        //   319: aload           4
        //   321: astore          7
        //   323: aload           5
        //   325: astore          6
        //   327: aload           5
        //   329: invokestatic    com/kongregate/android/internal/util/StringUtils.a:(Ljava/lang/CharSequence;)Z
        //   332: ifne            387
        //   335: aload_0        
        //   336: aload           8
        //   338: aload           5
        //   340: aload           4
        //   342: invokespecial   com/kongregate/android/internal/util/q.a:(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences;
        //   345: areturn        
        //   346: astore          6
        //   348: ldc             "Permission denied"
        //   350: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   353: aconst_null    
        //   354: astore          6
        //   356: goto            235
        //   359: astore_1       
        //   360: aload           6
        //   362: invokeinterface android/database/Cursor.isClosed:()Z
        //   367: ifne            377
        //   370: aload           6
        //   372: invokeinterface android/database/Cursor.close:()V
        //   377: aload_1        
        //   378: athrow         
        //   379: aload           4
        //   381: astore          6
        //   383: aload           5
        //   385: astore          7
        //   387: iload_2        
        //   388: iconst_1       
        //   389: iadd           
        //   390: istore_2       
        //   391: aload           7
        //   393: astore          5
        //   395: aload           6
        //   397: astore          4
        //   399: goto            136
        //   402: aload           5
        //   404: astore          6
        //   406: aload           4
        //   408: astore          5
        //   410: aload           6
        //   412: astore          4
        //   414: aload           5
        //   416: astore          6
        //   418: aload           4
        //   420: astore          5
        //   422: aload           6
        //   424: astore          4
        //   426: goto            100
        //   429: aload           5
        //   431: astore_1       
        //   432: aload           4
        //   434: invokestatic    com/kongregate/android/internal/util/StringUtils.a:(Ljava/lang/CharSequence;)Z
        //   437: ifne            450
        //   440: aload_1        
        //   441: astore          5
        //   443: aload_1        
        //   444: invokestatic    com/kongregate/android/internal/util/StringUtils.a:(Ljava/lang/CharSequence;)Z
        //   447: ifeq            521
        //   450: ldc             "AES"
        //   452: invokestatic    javax/crypto/KeyGenerator.getInstance:(Ljava/lang/String;)Ljavax/crypto/KeyGenerator;
        //   455: astore_1       
        //   456: aload_1        
        //   457: sipush          128
        //   460: invokevirtual   javax/crypto/KeyGenerator.init:(I)V
        //   463: aload_1        
        //   464: invokevirtual   javax/crypto/KeyGenerator.generateKey:()Ljavax/crypto/SecretKey;
        //   467: invokeinterface javax/crypto/SecretKey.getEncoded:()[B
        //   472: iconst_0       
        //   473: invokestatic    android/util/Base64.encodeToString:([BI)Ljava/lang/String;
        //   476: astore          4
        //   478: new             Ljava/lang/StringBuilder;
        //   481: dup            
        //   482: invokespecial   java/lang/StringBuilder.<init>:()V
        //   485: new             Ljava/util/Random;
        //   488: dup            
        //   489: invokespecial   java/util/Random.<init>:()V
        //   492: ldc_w           2147483647
        //   495: invokevirtual   java/util/Random.nextInt:(I)I
        //   498: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   501: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   504: ldc_w           ".shared"
        //   507: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   510: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   513: astore          5
        //   515: ldc_w           "generated a secret"
        //   518: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //   521: aload_0        
        //   522: aload           8
        //   524: aload           4
        //   526: aload           5
        //   528: invokespecial   com/kongregate/android/internal/util/q.a:(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences;
        //   531: areturn        
        //   532: aload           5
        //   534: astore_1       
        //   535: aload           6
        //   537: astore          4
        //   539: aload           7
        //   541: ifnonnull       432
        //   544: ldc_w           "PackageManager not present"
        //   547: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;)V
        //   550: aload           5
        //   552: astore_1       
        //   553: aload           6
        //   555: astore          4
        //   557: goto            432
        //   560: astore_1       
        //   561: ldc_w           "unable to find or generate key"
        //   564: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;)V
        //   567: aload           8
        //   569: areturn        
        //   570: aload           4
        //   572: astore          7
        //   574: aload           5
        //   576: astore          4
        //   578: aload           7
        //   580: astore          5
        //   582: goto            286
        //   585: aload           4
        //   587: astore          6
        //   589: aload           5
        //   591: astore          4
        //   593: aload           6
        //   595: astore          5
        //   597: goto            414
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  186    235    346    359    Ljava/lang/SecurityException;
        //  240    286    359    379    Any
        //  450    521    560    570    Ljava/security/NoSuchAlgorithmException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0286:
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
    
    private SharedPreferences a(final SharedPreferences sharedPreferences, final String s, final String s2) {
        sharedPreferences.edit().putString("secret", s).putString("file_postfix", s2).apply();
        return sharedPreferences;
    }
    
    public static q a(final Context context, final String s) {
        final Future<q> a = d.a((Callable<q>)new Callable<q>() {
            public q a() {
                return new q(context, s, null);
            }
        });
        try {
            return a.get();
        }
        catch (InterruptedException ex) {
            j.d("failed to get shared data store: ", ex);
            return null;
        }
        catch (ExecutionException ex2) {
            j.d("failed to get shared data store: ", ex2);
            return null;
        }
    }
    
    @TargetApi(9)
    private File a(final ApplicationInfo applicationInfo) {
        final File file = new File(applicationInfo.dataDir + "/" + "kongregate_shared_datastore");
        if (!file.exists()) {
            file.mkdirs();
            if (!com.kongregate.android.internal.util.a.a(9)) {
                j.c("app dir may not be shareable");
                return file;
            }
            file.setWritable(true, false);
            file.setReadable(true, false);
            file.setExecutable(true, false);
        }
        return file;
    }
    
    private File a(final File file) {
        return new File(file + "/" + this.c);
    }
    
    private Cipher a(final int n, final byte[] array) {
        if (this.a == null) {
            throw new IllegalStateException("no secret key.");
        }
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(array);
        try {
            final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(n, this.a, ivParameterSpec);
            return instance;
        }
        catch (GeneralSecurityException ex) {
            j.c("unable to encrypt json", ex);
            return null;
        }
    }
    
    private Cipher a(final byte[] array) {
        return this.a(2, array);
    }
    
    @TargetApi(9)
    private void a(final File file, final byte[] array, final byte[] array2) {
        j.b("commiting to: " + file);
        try {
            final File tempFile = File.createTempFile(this.c, "tmp", file);
            if (this.b(tempFile, array, array2)) {
                if (com.kongregate.android.internal.util.a.a(9)) {
                    tempFile.setWritable(true, false);
                    tempFile.setReadable(true, false);
                }
                tempFile.renameTo(this.a(file));
            }
        }
        catch (IOException ex) {
            j.c("SharedData Store - error creating temporary file in: " + file + " : " + ex);
        }
    }
    
    private byte[] a(String s, final Cipher cipher) {
        final CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(s.getBytes()), cipher);
        s = (String)new ByteArrayOutputStream();
        try {
            while (true) {
                final int read = cipherInputStream.read();
                if (read < 0) {
                    break;
                }
                ((ByteArrayOutputStream)s).write(read);
            }
        }
        catch (IOException ex) {
            j.c("unable to encrypt", ex);
        }
        return ((ByteArrayOutputStream)s).toByteArray();
    }
    
    private File b(final ApplicationInfo applicationInfo) {
        return this.a(this.a(applicationInfo));
    }
    
    private Map<String, String> b(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aconst_null    
        //     4: astore          5
        //     6: new             Ljava/lang/StringBuilder;
        //     9: dup            
        //    10: invokespecial   java/lang/StringBuilder.<init>:()V
        //    13: ldc_w           "read and decrypt data store: "
        //    16: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    19: aload_1        
        //    20: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    23: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    26: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //    29: new             Ljava/util/HashMap;
        //    32: dup            
        //    33: invokespecial   java/util/HashMap.<init>:()V
        //    36: astore          6
        //    38: new             Ljava/io/BufferedInputStream;
        //    41: dup            
        //    42: new             Ljava/io/FileInputStream;
        //    45: dup            
        //    46: aload_1        
        //    47: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    50: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    53: astore_3       
        //    54: bipush          16
        //    56: newarray        B
        //    58: astore          7
        //    60: aload_3        
        //    61: aload           7
        //    63: iconst_0       
        //    64: bipush          16
        //    66: invokevirtual   java/io/BufferedInputStream.read:([BII)I
        //    69: bipush          16
        //    71: if_icmpeq       91
        //    74: ldc_w           "error unable to read initialization vector"
        //    77: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //    80: aconst_null    
        //    81: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //    84: aload_3        
        //    85: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //    88: aload           6
        //    90: areturn        
        //    91: new             Ljava/io/ByteArrayOutputStream;
        //    94: dup            
        //    95: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    98: astore_1       
        //    99: new             Ljavax/crypto/CipherInputStream;
        //   102: dup            
        //   103: aload_3        
        //   104: aload_0        
        //   105: aload           7
        //   107: invokespecial   com/kongregate/android/internal/util/q.a:([B)Ljavax/crypto/Cipher;
        //   110: invokespecial   javax/crypto/CipherInputStream.<init>:(Ljava/io/InputStream;Ljavax/crypto/Cipher;)V
        //   113: astore          4
        //   115: aload           4
        //   117: invokevirtual   javax/crypto/CipherInputStream.read:()I
        //   120: istore_2       
        //   121: iload_2        
        //   122: iflt            166
        //   125: aload_1        
        //   126: iload_2        
        //   127: invokevirtual   java/io/ByteArrayOutputStream.write:(I)V
        //   130: goto            115
        //   133: astore          5
        //   135: aload_1        
        //   136: astore          4
        //   138: aload_3        
        //   139: astore_1       
        //   140: aload           4
        //   142: astore_3       
        //   143: aload           5
        //   145: astore          4
        //   147: ldc_w           "error reading datastore"
        //   150: aload           4
        //   152: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   155: aload_3        
        //   156: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //   159: aload_1        
        //   160: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //   163: aload           6
        //   165: areturn        
        //   166: aload_1        
        //   167: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //   170: aload_3        
        //   171: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //   174: aload_1        
        //   175: invokevirtual   java/io/ByteArrayOutputStream.toString:()Ljava/lang/String;
        //   178: invokestatic    com/kongregate/android/internal/util/i.c:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   181: astore_1       
        //   182: aload_1        
        //   183: ifnull          242
        //   186: aload_1        
        //   187: invokevirtual   org/json/JSONObject.keys:()Ljava/util/Iterator;
        //   190: astore_3       
        //   191: aload_3        
        //   192: invokeinterface java/util/Iterator.hasNext:()Z
        //   197: ifeq            242
        //   200: aload_3        
        //   201: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   206: checkcast       Ljava/lang/String;
        //   209: astore          4
        //   211: aload           6
        //   213: aload           4
        //   215: aload_1        
        //   216: aload           4
        //   218: invokestatic    com/kongregate/android/internal/util/i.c:(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
        //   221: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   224: pop            
        //   225: goto            191
        //   228: astore_1       
        //   229: aconst_null    
        //   230: astore_3       
        //   231: aload           4
        //   233: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //   236: aload_3        
        //   237: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //   240: aload_1        
        //   241: athrow         
        //   242: aload           6
        //   244: areturn        
        //   245: astore_1       
        //   246: goto            231
        //   249: astore          5
        //   251: aload_1        
        //   252: astore          4
        //   254: aload           5
        //   256: astore_1       
        //   257: goto            231
        //   260: astore          4
        //   262: aload_1        
        //   263: astore          5
        //   265: aload           4
        //   267: astore_1       
        //   268: aload_3        
        //   269: astore          4
        //   271: aload           5
        //   273: astore_3       
        //   274: goto            231
        //   277: astore          4
        //   279: aconst_null    
        //   280: astore_1       
        //   281: aload           5
        //   283: astore_3       
        //   284: goto            147
        //   287: astore          4
        //   289: aload_3        
        //   290: astore_1       
        //   291: aload           5
        //   293: astore_3       
        //   294: goto            147
        //    Signature:
        //  (Ljava/io/File;)Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  38     54     277    287    Ljava/io/IOException;
        //  38     54     228    231    Any
        //  54     80     287    297    Ljava/io/IOException;
        //  54     80     245    249    Any
        //  91     99     287    297    Ljava/io/IOException;
        //  91     99     245    249    Any
        //  99     115    133    147    Ljava/io/IOException;
        //  99     115    249    260    Any
        //  115    121    133    147    Ljava/io/IOException;
        //  115    121    249    260    Any
        //  125    130    133    147    Ljava/io/IOException;
        //  125    130    249    260    Any
        //  147    155    260    277    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 151, Size: 151
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
    
    private boolean b(final File p0, final byte[] p1, final byte[] p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: new             Ljava/io/FileOutputStream;
        //     7: dup            
        //     8: aload_1        
        //     9: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    12: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    15: astore          4
        //    17: aload           4
        //    19: astore_1       
        //    20: aload           4
        //    22: aload_3        
        //    23: invokevirtual   java/io/BufferedOutputStream.write:([B)V
        //    26: aload           4
        //    28: astore_1       
        //    29: aload           4
        //    31: aload_2        
        //    32: invokevirtual   java/io/BufferedOutputStream.write:([B)V
        //    35: aload           4
        //    37: astore_1       
        //    38: aload           4
        //    40: invokevirtual   java/io/BufferedOutputStream.flush:()V
        //    43: aload           4
        //    45: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //    48: iconst_1       
        //    49: ireturn        
        //    50: astore_3       
        //    51: aconst_null    
        //    52: astore_2       
        //    53: aload_2        
        //    54: astore_1       
        //    55: ldc_w           "error saving shared data store"
        //    58: aload_3        
        //    59: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    62: aload_2        
        //    63: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //    66: iconst_0       
        //    67: ireturn        
        //    68: astore_2       
        //    69: aconst_null    
        //    70: astore_1       
        //    71: aload_1        
        //    72: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //    75: aload_2        
        //    76: athrow         
        //    77: astore_2       
        //    78: goto            71
        //    81: astore_3       
        //    82: aload           4
        //    84: astore_2       
        //    85: goto            53
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      17     50     53     Ljava/io/IOException;
        //  0      17     68     71     Any
        //  20     26     81     88     Ljava/io/IOException;
        //  20     26     77     81     Any
        //  29     35     81     88     Ljava/io/IOException;
        //  29     35     77     81     Any
        //  38     43     81     88     Ljava/io/IOException;
        //  38     43     77     81     Any
        //  55     62     77     81     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0053:
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
    
    private File d() {
        final File file = new File(Environment.getExternalStorageDirectory() + "/.kongregate/data");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
    
    private Map<String, String> e() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(0);
        final ApplicationInfo applicationInfo = this.b.getApplicationInfo();
        if (applicationInfo == null) {
            j.c("SharedDataStore - unable to load shared data. no app info");
            return hashMap;
        }
        File file;
        if (g.b(this.b)) {
            file = this.a(this.d());
        }
        else {
            file = this.b(applicationInfo);
        }
        final Iterator<ApplicationInfo> iterator = this.g().iterator();
        while (iterator.hasNext()) {
            final File b = this.b(iterator.next());
            if (b.exists() && b.lastModified() > file.lastModified()) {
                file = b;
            }
        }
        Map<String, String> b2;
        if (file.exists()) {
            b2 = this.b(file);
        }
        else {
            j.b("no data store found");
            b2 = hashMap;
        }
        return b2;
    }
    
    private Cipher f() {
        return this.a(1, SecureRandom.getSeed(16));
    }
    
    private List<ApplicationInfo> g() {
        final PackageManager packageManager = this.b.getPackageManager();
        final LinkedList<ApplicationInfo> list = new LinkedList<ApplicationInfo>();
        if (!NativeAPI.g().d()) {
            list.add(this.b.getApplicationInfo());
            return list;
        }
        if (packageManager == null) {
            j.c("package manager not found. unable to search for kongregate apps");
            return list;
        }
        for (final PackageInfo packageInfo : packageManager.getInstalledPackages(8)) {
            final ProviderInfo[] providers = packageInfo.providers;
            if (providers != null) {
                for (int length = providers.length, i = 0; i < length; ++i) {
                    if ("com.kongregate.permission.ReadSharedData2".equals(providers[i].readPermission)) {
                        list.add(packageInfo.applicationInfo);
                    }
                }
            }
        }
        return list;
    }
    
    public String a(String s) {
        synchronized (this.e) {
            s = this.d.get(s);
            return s;
        }
    }
    
    public void a() {
        synchronized (this.e) {
            this.d.clear();
        }
    }
    
    public void a(final String s, final String s2) {
        synchronized (this.e) {
            this.d.put(s, s2);
        }
    }
    
    public Map<String, Object> b() {
        synchronized (this.e) {
            return new HashMap<String, Object>(this.d);
        }
    }
    
    public void b(final String s) {
        synchronized (this.e) {
            this.d.remove(s);
        }
    }
    
    public void c() {
        if (this.a == null) {
            j.d("no secret key, unable to persist");
            return;
        }
        if (this.b.getApplicationInfo() == null) {
            j.c("SharedDataStore unable to commit. no app info");
            return;
        }
        if (this.b.getPackageManager() == null) {
            j.c("SharedDataStore unable to commit. no package manager");
            return;
        }
        synchronized (this.e) {
            final JSONObject jsonObject = new JSONObject((Map)this.d);
            // monitorexit(this.e)
            com.kongregate.o.c.d.a(new Runnable() {
                final /* synthetic */ String a = jsonObject.toString();
                final /* synthetic */ Cipher b = q.this.f();
                
                @Override
                public void run() {
                    final byte[] a = q.this.a(this.a, this.b);
                    final byte[] iv = this.b.getIV();
                    final Iterator<ApplicationInfo> iterator = q.this.g().iterator();
                    while (iterator.hasNext()) {
                        q.this.a(q.this.a(iterator.next()), a, iv);
                    }
                    if (g.b(q.this.b)) {
                        q.this.a(q.this.d(), a, iv);
                    }
                }
            });
        }
    }
}
