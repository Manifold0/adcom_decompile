// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.text.TextUtils;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import java.net.URLEncoder;
import java.util.List;
import android.os.Bundle;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.Iterator;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import com.applovin.sdk.AppLovinAd;
import java.io.File;
import android.graphics.Bitmap;
import android.view.ViewParent;
import android.app.Activity;
import android.view.View;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONObject;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.Context;
import com.applovin.sdk.AppLovinSdkUtils;

public class gd extends AppLovinSdkUtils
{
    private static final char[] a;
    private static final char[] b;
    
    static {
        a = "0123456789abcdef".toCharArray();
        b = "-'".toCharArray();
    }
    
    public static double a(final long n) {
        return n / 1000.0;
    }
    
    public static float a(final float n) {
        return 1000.0f * n;
    }
    
    public static int a(final Context context) {
        if (context != null) {
            final Resources resources = context.getResources();
            if (resources != null) {
                final Configuration configuration = resources.getConfiguration();
                if (configuration != null) {
                    return configuration.orientation;
                }
            }
        }
        return 0;
    }
    
    public static int a(final String s, int int1) {
        if (d(s)) {
            int1 = Integer.parseInt(s);
        }
        return int1;
    }
    
    public static int a(final JSONObject jsonObject) {
        final int a = bu.a(jsonObject, "video_completion_percent", -1, null);
        if (a >= 0 && a <= 100) {
            return a;
        }
        return 95;
    }
    
    public static Activity a(View view, final AppLovinSdk appLovinSdk) {
        if (view == null) {
            return null;
        }
        int i = 0;
        while (i < 1000) {
            try {
                final Context context = view.getContext();
                if (context instanceof Activity) {
                    return (Activity)context;
                }
                final ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    view = (View)parent;
                    ++i;
                    continue;
                }
                return null;
            }
            catch (Throwable t) {
                appLovinSdk.getLogger().e("AppLovinUtils", "Encountered error while retrieving activity from view", t);
            }
            break;
        }
        return null;
    }
    
    public static Bitmap a(final Context p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_3       
        //     2: new             Landroid/graphics/BitmapFactory$Options;
        //     5: dup            
        //     6: invokespecial   android/graphics/BitmapFactory$Options.<init>:()V
        //     9: astore          4
        //    11: aload           4
        //    13: iconst_1       
        //    14: putfield        android/graphics/BitmapFactory$Options.inJustDecodeBounds:Z
        //    17: aload_0        
        //    18: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    21: iload_1        
        //    22: invokestatic    android/graphics/BitmapFactory.decodeResource:(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;
        //    25: pop            
        //    26: aload           4
        //    28: getfield        android/graphics/BitmapFactory$Options.outHeight:I
        //    31: iload_2        
        //    32: if_icmpgt       44
        //    35: aload           4
        //    37: getfield        android/graphics/BitmapFactory$Options.outWidth:I
        //    40: iload_2        
        //    41: if_icmple       84
        //    44: ldc2_w          2.0
        //    47: iload_2        
        //    48: i2d            
        //    49: aload           4
        //    51: getfield        android/graphics/BitmapFactory$Options.outHeight:I
        //    54: aload           4
        //    56: getfield        android/graphics/BitmapFactory$Options.outWidth:I
        //    59: invokestatic    java/lang/Math.max:(II)I
        //    62: i2d            
        //    63: ddiv           
        //    64: invokestatic    java/lang/Math.log:(D)D
        //    67: ldc2_w          0.5
        //    70: invokestatic    java/lang/Math.log:(D)D
        //    73: ddiv           
        //    74: invokestatic    java/lang/Math.ceil:(D)D
        //    77: d2i            
        //    78: i2d            
        //    79: invokestatic    java/lang/Math.pow:(DD)D
        //    82: d2i            
        //    83: istore_3       
        //    84: new             Landroid/graphics/BitmapFactory$Options;
        //    87: dup            
        //    88: invokespecial   android/graphics/BitmapFactory$Options.<init>:()V
        //    91: iload_3        
        //    92: putfield        android/graphics/BitmapFactory$Options.inSampleSize:I
        //    95: aload_0        
        //    96: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    99: iload_1        
        //   100: invokestatic    android/graphics/BitmapFactory.decodeResource:(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;
        //   103: astore_0       
        //   104: new             Ljava/lang/NullPointerException;
        //   107: dup            
        //   108: invokespecial   java/lang/NullPointerException.<init>:()V
        //   111: athrow         
        //   112: astore_0       
        //   113: new             Ljava/lang/NullPointerException;
        //   116: dup            
        //   117: invokespecial   java/lang/NullPointerException.<init>:()V
        //   120: athrow         
        //   121: astore_0       
        //   122: aconst_null    
        //   123: areturn        
        //   124: astore_0       
        //   125: new             Ljava/lang/NullPointerException;
        //   128: dup            
        //   129: invokespecial   java/lang/NullPointerException.<init>:()V
        //   132: athrow         
        //   133: aload_0        
        //   134: athrow         
        //   135: astore          4
        //   137: goto            133
        //   140: astore          4
        //   142: aload_0        
        //   143: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      35     112    124    Ljava/lang/Exception;
        //  2      35     124    140    Any
        //  35     44     112    124    Ljava/lang/Exception;
        //  35     44     124    140    Any
        //  44     84     112    124    Ljava/lang/Exception;
        //  44     84     124    140    Any
        //  84     104    112    124    Ljava/lang/Exception;
        //  84     104    124    140    Any
        //  104    112    140    144    Ljava/lang/Exception;
        //  113    121    121    124    Ljava/lang/Exception;
        //  125    133    135    140    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0133:
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
    
    public static Bitmap a(final File p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_2       
        //     2: aconst_null    
        //     3: astore          4
        //     5: new             Landroid/graphics/BitmapFactory$Options;
        //     8: dup            
        //     9: invokespecial   android/graphics/BitmapFactory$Options.<init>:()V
        //    12: astore          5
        //    14: aload           5
        //    16: iconst_1       
        //    17: putfield        android/graphics/BitmapFactory$Options.inJustDecodeBounds:Z
        //    20: new             Ljava/io/FileInputStream;
        //    23: dup            
        //    24: aload_0        
        //    25: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    28: astore_3       
        //    29: aload_3        
        //    30: aconst_null    
        //    31: aload           5
        //    33: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
        //    36: pop            
        //    37: aload_3        
        //    38: invokevirtual   java/io/FileInputStream.close:()V
        //    41: aload           5
        //    43: getfield        android/graphics/BitmapFactory$Options.outHeight:I
        //    46: iload_1        
        //    47: if_icmpgt       59
        //    50: aload           5
        //    52: getfield        android/graphics/BitmapFactory$Options.outWidth:I
        //    55: iload_1        
        //    56: if_icmple       99
        //    59: ldc2_w          2.0
        //    62: iload_1        
        //    63: i2d            
        //    64: aload           5
        //    66: getfield        android/graphics/BitmapFactory$Options.outHeight:I
        //    69: aload           5
        //    71: getfield        android/graphics/BitmapFactory$Options.outWidth:I
        //    74: invokestatic    java/lang/Math.max:(II)I
        //    77: i2d            
        //    78: ddiv           
        //    79: invokestatic    java/lang/Math.log:(D)D
        //    82: ldc2_w          0.5
        //    85: invokestatic    java/lang/Math.log:(D)D
        //    88: ddiv           
        //    89: invokestatic    java/lang/Math.ceil:(D)D
        //    92: d2i            
        //    93: i2d            
        //    94: invokestatic    java/lang/Math.pow:(DD)D
        //    97: d2i            
        //    98: istore_2       
        //    99: new             Landroid/graphics/BitmapFactory$Options;
        //   102: dup            
        //   103: invokespecial   android/graphics/BitmapFactory$Options.<init>:()V
        //   106: astore          5
        //   108: aload           5
        //   110: iload_2        
        //   111: putfield        android/graphics/BitmapFactory$Options.inSampleSize:I
        //   114: new             Ljava/io/FileInputStream;
        //   117: dup            
        //   118: aload_0        
        //   119: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   122: astore_0       
        //   123: aload_0        
        //   124: aconst_null    
        //   125: aload           5
        //   127: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
        //   130: astore          4
        //   132: aload_0        
        //   133: invokevirtual   java/io/FileInputStream.close:()V
        //   136: aload_3        
        //   137: invokevirtual   java/io/FileInputStream.close:()V
        //   140: aload_0        
        //   141: invokevirtual   java/io/FileInputStream.close:()V
        //   144: aload           4
        //   146: areturn        
        //   147: astore_0       
        //   148: aconst_null    
        //   149: astore_0       
        //   150: aconst_null    
        //   151: astore_3       
        //   152: aload_3        
        //   153: invokevirtual   java/io/FileInputStream.close:()V
        //   156: aload_0        
        //   157: invokevirtual   java/io/FileInputStream.close:()V
        //   160: aconst_null    
        //   161: areturn        
        //   162: astore_0       
        //   163: aconst_null    
        //   164: astore_3       
        //   165: aload_3        
        //   166: invokevirtual   java/io/FileInputStream.close:()V
        //   169: aload           4
        //   171: invokevirtual   java/io/FileInputStream.close:()V
        //   174: aload_0        
        //   175: athrow         
        //   176: astore_3       
        //   177: goto            174
        //   180: astore_0       
        //   181: goto            165
        //   184: astore          5
        //   186: aload_0        
        //   187: astore          4
        //   189: aload           5
        //   191: astore_0       
        //   192: goto            165
        //   195: astore_0       
        //   196: goto            160
        //   199: astore_0       
        //   200: aconst_null    
        //   201: astore_0       
        //   202: goto            152
        //   205: astore          4
        //   207: goto            152
        //   210: astore_0       
        //   211: aload           4
        //   213: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      29     147    152    Ljava/lang/Exception;
        //  5      29     162    165    Any
        //  29     50     199    205    Ljava/lang/Exception;
        //  29     50     180    184    Any
        //  50     59     199    205    Ljava/lang/Exception;
        //  50     59     180    184    Any
        //  59     99     199    205    Ljava/lang/Exception;
        //  59     99     180    184    Any
        //  99     123    199    205    Ljava/lang/Exception;
        //  99     123    180    184    Any
        //  123    136    205    210    Ljava/lang/Exception;
        //  123    136    184    195    Any
        //  136    144    210    214    Ljava/lang/Exception;
        //  152    160    195    199    Ljava/lang/Exception;
        //  165    174    176    180    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 117, Size: 117
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
    
    public static AppLovinAd a(final AppLovinAd appLovinAd, final AppLovinSdk appLovinSdk) {
        if (appLovinAd instanceof aq) {
            final AppLovinAdServiceImpl appLovinAdServiceImpl = (AppLovinAdServiceImpl)appLovinSdk.getAdService();
            final aq aq = (aq)appLovinAd;
            final AppLovinAd dequeueAd = appLovinAdServiceImpl.dequeueAd(aq.t());
            appLovinSdk.getLogger().d("AppLovinUtils", "Dequeued ad for dummy ad: " + dequeueAd);
            if (dequeueAd != null) {
                aq.a(dequeueAd);
                ((q)dequeueAd).a(aq);
            }
            return dequeueAd;
        }
        return appLovinAd;
    }
    
    public static String a(final String s) {
        if (s != null && s.length() > 4) {
            return s.substring(s.length() - 4);
        }
        return "NOKEY";
    }
    
    public static String a(final String s, final AppLovinSdkImpl appLovinSdkImpl) {
        return a(s, appLovinSdkImpl.get(ea.v), appLovinSdkImpl.get(ea.u));
    }
    
    private static String a(String substring, final Integer n, final String s) {
        if (s == null) {
            throw new IllegalArgumentException("No algorithm specified");
        }
        String a;
        if (substring == null || substring.length() < 1) {
            a = "";
        }
        else {
            a = substring;
            if (s.length() >= 1) {
                a = substring;
                if (!"none".equals(s)) {
                    try {
                        final MessageDigest instance = MessageDigest.getInstance(s);
                        instance.update(substring.getBytes("UTF-8"));
                        substring = (a = a(instance.digest()));
                        if (substring != null) {
                            a = substring;
                            if (n > 0) {
                                substring = substring.substring(0, Math.min(n, substring.length()));
                                return substring;
                            }
                        }
                    }
                    catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException("Unknown algorithm \"" + s + "\"", ex);
                    }
                    catch (UnsupportedEncodingException ex2) {
                        throw new RuntimeException("Programming error: UTF-8 is not know encoding", ex2);
                    }
                }
            }
        }
        return a;
    }
    
    public static String a(String s, final String s2) {
        if (s == null) {
            s = "";
        }
        return s2.replace("{PLACEMENT}", c(s));
    }
    
    static String a(final Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append((Object)entry.getKey()).append('=').append((Object)entry.getValue());
        }
        return sb.toString();
    }
    
    public static String a(final byte[] array) {
        if (array == null) {
            throw new IllegalArgumentException("No data specified");
        }
        final char[] array2 = new char[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            array2[i * 2] = gd.a[(array[i] & 0xF0) >>> 4];
            array2[i * 2 + 1] = gd.a[array[i] & 0xF];
        }
        return new String(array2);
    }
    
    public static void a(final AppLovinAdLoadListener appLovinAdLoadListener, final n n, final int n2, final AppLovinSdk appLovinSdk) {
        if (appLovinAdLoadListener != null) {
            try {
                if (appLovinAdLoadListener instanceof at) {
                    ((at)appLovinAdLoadListener).a(n, n2);
                    return;
                }
                appLovinAdLoadListener.failedToReceiveAd(n2);
            }
            catch (Throwable t) {
                appLovinSdk.getLogger().e("AppLovinUtils", "Unable process a failure to receive an ad", t);
            }
        }
    }
    
    public static boolean a() {
        final boolean b = false;
        final Context staticApplicationContext = AppLovinSdkImpl.getStaticApplicationContext();
        boolean b2 = b;
        if (staticApplicationContext != null) {
            final Bundle d = d(staticApplicationContext);
            b2 = b;
            if (d != null) {
                b2 = b;
                if (d.containsKey("applovin.sdk.verbose_logging")) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static boolean a(final int n, final int n2) {
        return (n & n2) != 0x0;
    }
    
    public static boolean a(final an an, final Context context, final AppLovinSdkImpl appLovinSdkImpl) {
        return an != null && (an.b() || an.d() == null || appLovinSdkImpl.getFileManager().a(an.d().getLastPathSegment(), context));
    }
    
    public static boolean a(final AppLovinAd appLovinAd, final AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinAd == null) {
            appLovinSdkImpl.getLogger().userError("AppLovinUtils", "Failing ad display - ad is null.");
            return false;
        }
        if (((q)appLovinAd).m() == o.b) {
            return true;
        }
        if (!ag.a(appLovinSdkImpl.getApplicationContext(), appLovinSdkImpl) && !appLovinSdkImpl.get(ea.cG)) {
            appLovinSdkImpl.getLogger().userError("AppLovinUtils", "Failing ad display due to no internet connection.");
            return false;
        }
        return true;
    }
    
    public static boolean a(final String s, final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (s.startsWith(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    public static long b(final float n) {
        return Math.round(n);
    }
    
    public static String b(final String s) {
        return a(s, -1, "SHA-1");
    }
    
    public static boolean b(final Context context) {
        final boolean b = false;
        Context staticApplicationContext = context;
        if (context == null) {
            staticApplicationContext = AppLovinSdkImpl.getStaticApplicationContext();
        }
        boolean b2 = b;
        if (staticApplicationContext != null) {
            final Bundle d = d(staticApplicationContext);
            b2 = b;
            if (d != null) {
                b2 = b;
                if (d.getBoolean("applovin.sdk.test_ads", false)) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static long c(final float n) {
        return b(a(n));
    }
    
    public static String c(String encode) {
        if (AppLovinSdkUtils.isValidString(encode)) {
            try {
                encode = URLEncoder.encode(encode, "UTF-8");
                return encode;
            }
            catch (UnsupportedEncodingException ex) {
                throw new UnsupportedOperationException(ex);
            }
        }
        return "";
    }
    
    public static boolean c(final Context context) {
        final boolean b = false;
        Context staticApplicationContext = context;
        if (context == null) {
            staticApplicationContext = AppLovinSdkImpl.getStaticApplicationContext();
        }
        boolean b2 = b;
        if (staticApplicationContext != null) {
            final Bundle d = d(staticApplicationContext);
            b2 = b;
            if (d != null) {
                b2 = b;
                if (d.getBoolean("applovin.sdk.verbose_logging", false)) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static Bundle d(final Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("AppLovinSdk", "Unable to retrieve application metadata", (Throwable)ex);
            return null;
        }
    }
    
    public static boolean d(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final char char1 = s.charAt(0);
            boolean b;
            if (char1 == '-' || char1 == '+') {
                b = true;
            }
            else {
                b = false;
            }
            final int length = s.length();
            int i = b ? 1 : 0;
            if (b) {
                if (length == 1) {
                    return false;
                }
                i = (b ? 1 : 0);
            }
            while (i < length) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
                ++i;
            }
            return true;
        }
        return false;
    }
    
    public static int e(final String s) {
        return a(s, 0);
    }
}
