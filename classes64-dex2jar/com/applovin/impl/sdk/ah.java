// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.concurrent.TimeUnit;
import android.os.Handler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.io.File;
import android.os.Build;
import java.util.Date;
import java.util.TimeZone;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.ContentResolver;
import android.provider.Settings$SettingNotFoundException;
import android.provider.Settings$Secure;
import java.util.Iterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import com.applovin.sdk.AppLovinLogger;

class ah
{
    private static String e;
    private static String f;
    private static int g;
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;
    private final Map<Class, Object> d;
    
    ah(final AppLovinSdkImpl a) {
        if (a == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = a;
        this.b = a.getLogger();
        this.c = a.getApplicationContext();
        this.d = (Map<Class, Object>)Collections.synchronizedMap(new HashMap<Class, Object>());
    }
    
    private am a(final am p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: aload_1        
        //     3: ifnonnull       14
        //     6: new             Lcom/applovin/impl/sdk/am;
        //     9: dup            
        //    10: invokespecial   com/applovin/impl/sdk/am.<init>:()V
        //    13: astore_3       
        //    14: aload_3        
        //    15: aload_0        
        //    16: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //    19: invokestatic    com/applovin/impl/sdk/ac.a:(Landroid/content/Context;)Ljava/lang/Boolean;
        //    22: putfield        com/applovin/impl/sdk/am.w:Ljava/lang/Boolean;
        //    25: aload_3        
        //    26: aload_0        
        //    27: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //    30: invokestatic    com/applovin/impl/sdk/ac.b:(Landroid/content/Context;)Ljava/lang/Boolean;
        //    33: putfield        com/applovin/impl/sdk/am.x:Ljava/lang/Boolean;
        //    36: aload_0        
        //    37: getfield        com/applovin/impl/sdk/ah.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    40: getstatic       com/applovin/impl/sdk/ea.cp:Lcom/applovin/impl/sdk/ec;
        //    43: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ec;)Ljava/lang/Object;
        //    46: checkcast       Ljava/lang/Boolean;
        //    49: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    52: ifeq            261
        //    55: aload_3        
        //    56: aload_0        
        //    57: invokespecial   com/applovin/impl/sdk/ah.i:()Lcom/applovin/impl/sdk/al;
        //    60: putfield        com/applovin/impl/sdk/am.r:Lcom/applovin/impl/sdk/al;
        //    63: aload_0        
        //    64: getfield        com/applovin/impl/sdk/ah.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    67: getstatic       com/applovin/impl/sdk/ea.co:Lcom/applovin/impl/sdk/ec;
        //    70: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ec;)Ljava/lang/Object;
        //    73: checkcast       Ljava/lang/Boolean;
        //    76: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    79: ifeq            90
        //    82: aload_3        
        //    83: aload_0        
        //    84: invokespecial   com/applovin/impl/sdk/ah.k:()Z
        //    87: putfield        com/applovin/impl/sdk/am.q:Z
        //    90: aload_0        
        //    91: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //    94: ldc             "audio"
        //    96: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //    99: checkcast       Landroid/media/AudioManager;
        //   102: astore_1       
        //   103: aload_1        
        //   104: ifnull          137
        //   107: aload_0        
        //   108: getfield        com/applovin/impl/sdk/ah.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   111: getstatic       com/applovin/impl/sdk/ea.cv:Lcom/applovin/impl/sdk/ec;
        //   114: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ec;)Ljava/lang/Object;
        //   117: checkcast       Ljava/lang/Float;
        //   120: invokevirtual   java/lang/Float.floatValue:()F
        //   123: fstore_2       
        //   124: aload_3        
        //   125: aload_1        
        //   126: iconst_3       
        //   127: invokevirtual   android/media/AudioManager.getStreamVolume:(I)I
        //   130: i2f            
        //   131: fload_2        
        //   132: fmul           
        //   133: f2i            
        //   134: putfield        com/applovin/impl/sdk/am.s:I
        //   137: aload_0        
        //   138: getfield        com/applovin/impl/sdk/ah.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   141: getstatic       com/applovin/impl/sdk/ea.cy:Lcom/applovin/impl/sdk/ec;
        //   144: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ec;)Ljava/lang/Object;
        //   147: checkcast       Ljava/lang/Boolean;
        //   150: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   153: ifeq            194
        //   156: getstatic       com/applovin/impl/sdk/ah.e:Ljava/lang/String;
        //   159: ifnonnull       178
        //   162: aload_0        
        //   163: invokespecial   com/applovin/impl/sdk/ah.o:()Ljava/lang/String;
        //   166: astore_1       
        //   167: aload_1        
        //   168: invokestatic    com/applovin/impl/sdk/gd.isValidString:(Ljava/lang/String;)Z
        //   171: ifeq            287
        //   174: aload_1        
        //   175: putstatic       com/applovin/impl/sdk/ah.e:Ljava/lang/String;
        //   178: getstatic       com/applovin/impl/sdk/ah.e:Ljava/lang/String;
        //   181: invokestatic    com/applovin/impl/sdk/gd.isValidString:(Ljava/lang/String;)Z
        //   184: ifeq            194
        //   187: aload_3        
        //   188: getstatic       com/applovin/impl/sdk/ah.e:Ljava/lang/String;
        //   191: putfield        com/applovin/impl/sdk/am.t:Ljava/lang/String;
        //   194: aload_0        
        //   195: getfield        com/applovin/impl/sdk/ah.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   198: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getSettingsManager:()Lcom/applovin/impl/sdk/ed;
        //   201: getstatic       com/applovin/impl/sdk/ea.cm:Lcom/applovin/impl/sdk/ec;
        //   204: invokevirtual   com/applovin/impl/sdk/ed.a:(Lcom/applovin/impl/sdk/ec;)Ljava/lang/Object;
        //   207: checkcast       Ljava/lang/String;
        //   210: astore_1       
        //   211: getstatic       com/applovin/impl/sdk/ah.f:Ljava/lang/String;
        //   214: ifnull          227
        //   217: aload_1        
        //   218: getstatic       com/applovin/impl/sdk/ah.f:Ljava/lang/String;
        //   221: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   224: ifne            302
        //   227: aload_1        
        //   228: putstatic       com/applovin/impl/sdk/ah.f:Ljava/lang/String;
        //   231: aload_0        
        //   232: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //   235: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //   238: aload_1        
        //   239: iconst_0       
        //   240: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   243: astore_1       
        //   244: aload_3        
        //   245: aload_1        
        //   246: getfield        android/content/pm/PackageInfo.versionCode:I
        //   249: putfield        com/applovin/impl/sdk/am.p:I
        //   252: aload_1        
        //   253: getfield        android/content/pm/PackageInfo.versionCode:I
        //   256: putstatic       com/applovin/impl/sdk/ah.g:I
        //   259: aload_3        
        //   260: areturn        
        //   261: aload_3        
        //   262: aconst_null    
        //   263: putfield        com/applovin/impl/sdk/am.r:Lcom/applovin/impl/sdk/al;
        //   266: goto            63
        //   269: astore_1       
        //   270: aload_0        
        //   271: getfield        com/applovin/impl/sdk/ah.b:Lcom/applovin/sdk/AppLovinLogger;
        //   274: ldc             "DataCollector"
        //   276: ldc             "Unable to collect volume"
        //   278: aload_1        
        //   279: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   284: goto            137
        //   287: ldc             ""
        //   289: putstatic       com/applovin/impl/sdk/ah.e:Ljava/lang/String;
        //   292: goto            178
        //   295: astore_1       
        //   296: iconst_0       
        //   297: putstatic       com/applovin/impl/sdk/ah.g:I
        //   300: aload_3        
        //   301: areturn        
        //   302: aload_3        
        //   303: getstatic       com/applovin/impl/sdk/ah.g:I
        //   306: putfield        com/applovin/impl/sdk/am.p:I
        //   309: aload_3        
        //   310: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  90     103    269    287    Ljava/lang/Throwable;
        //  107    137    269    287    Ljava/lang/Throwable;
        //  227    259    295    302    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0227:
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
    
    static boolean a(final String s, final Context context) {
        if (s == null) {
            throw new IllegalArgumentException("No permission name specified");
        }
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        return context.getPackageManager().checkPermission(s, context.getPackageName()) == 0;
    }
    
    private boolean a(final String s, final ec<String> ec) {
        final Iterator<String> iterator = aa.a(this.a.get(ec)).iterator();
        while (iterator.hasNext()) {
            if (s.startsWith(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    private String b(final String s) {
        final int length = s.length();
        final int[] array2;
        final int[] array = array2 = new int[10];
        array2[0] = 11;
        array2[1] = 12;
        array2[2] = 10;
        array2[3] = 3;
        array2[4] = 2;
        array2[5] = 1;
        array2[6] = 15;
        array2[7] = 10;
        array2[8] = 15;
        array2[9] = 14;
        final int length2 = array.length;
        final char[] array3 = new char[length];
        for (int i = 0; i < length; ++i) {
            array3[i] = s.charAt(i);
            for (int j = length2 - 1; j >= 0; --j) {
                array3[i] ^= (char)array[j];
            }
        }
        final String s2 = new String(array3);
        if (s2 != null) {
            return s2;
        }
        return "";
    }
    
    private String f() {
        try {
            final int a = gd.a(this.c);
            if (a == 1) {
                return "portrait";
            }
            if (a == 2) {
                return "landscape";
            }
        }
        catch (Throwable t) {
            this.a.getLogger().e("DataCollector", "Encountered error while attempting to collect application orientation", t);
        }
        return "none";
    }
    
    private aj g() {
        try {
            final ContentResolver contentResolver = this.c.getContentResolver();
            String string = Settings$Secure.getString(contentResolver, "advertising_id");
            final aj aj = new aj();
            if (string == null) {
                string = "";
            }
            aj.b = string;
            aj.a = (Settings$Secure.getInt(contentResolver, "limit_ad_tracking") != 0);
            return aj;
        }
        catch (Settings$SettingNotFoundException ex) {
            this.b.e("DataCollector", "Unable to determine if FireOS limited ad tracking is turned on", (Throwable)ex);
        }
        catch (Throwable t) {
            this.b.e("DataCollector", "Unable to collect FireOS IDFA", t);
            goto Label_0083;
        }
    }
    
    private aj h() {
        while (true) {
            while (true) {
                Label_0171: {
                    try {
                        final Class<?> forName = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                        if (forName == null) {
                            goto Label_0144;
                        }
                        final Object invoke = forName.getMethod("getAdvertisingIdInfo", Context.class).invoke(null, this.c);
                        if (invoke == null) {
                            goto Label_0144;
                        }
                        final Class<?> class1 = invoke.getClass();
                        final Object invoke2 = class1.getMethod("isLimitAdTrackingEnabled", (Class<?>[])null).invoke(invoke, (Object[])null);
                        String b = (String)class1.getMethod("getId", (Class<?>[])null).invoke(invoke, (Object[])null);
                        if (b == null) {
                            b = "";
                            final aj aj = new aj();
                            aj.a = (boolean)invoke2;
                            aj.b = b;
                            return aj;
                        }
                        break Label_0171;
                    }
                    catch (ClassNotFoundException ex) {
                        this.b.userError("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", ex);
                    }
                    catch (Throwable t) {
                        this.b.e("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", t);
                        goto Label_0144;
                    }
                }
                continue;
            }
        }
    }
    
    private al i() {
    Label_0065_Outer:
        while (true) {
            final int n = -1;
            while (true) {
            Label_0148:
                while (true) {
                    try {
                        final al al = new al();
                        final Intent registerReceiver = this.c.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                        if (registerReceiver != null) {
                            final int intExtra = registerReceiver.getIntExtra("level", -1);
                            if (registerReceiver != null) {
                                final int intExtra2 = registerReceiver.getIntExtra("scale", -1);
                                if (intExtra > 0 && intExtra2 > 0) {
                                    al.b = (int)(intExtra / (float)intExtra2 * 100.0f);
                                }
                                else {
                                    al.b = -1;
                                }
                                int intExtra3 = n;
                                if (registerReceiver != null) {
                                    intExtra3 = registerReceiver.getIntExtra("status", -1);
                                }
                                al.a = intExtra3;
                                return al;
                            }
                            break Label_0148;
                        }
                    }
                    catch (Throwable t) {
                        this.b.e("DataCollector", "Unable to collect battery info", t);
                        return null;
                    }
                    final int intExtra = -1;
                    continue Label_0065_Outer;
                }
                final int intExtra2 = -1;
                continue;
            }
        }
    }
    
    private double j() {
        return Math.round(TimeZone.getDefault().getOffset(new Date().getTime()) * 10.0 / 3600000.0) / 10.0;
    }
    
    private boolean k() {
        boolean b = false;
        try {
            if (this.l() || this.m()) {
                b = true;
            }
            return b;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    private boolean l() {
        final String tags = Build.TAGS;
        return tags != null && tags.contains(this.b("lz}$blpz"));
    }
    
    private boolean m() {
        final boolean b = false;
        final String[] array = { "&zpz}ld&hyy&Z|yl{|zl{'hyb", "&zk`g&z|", "&zpz}ld&k`g&z|", "&zpz}ld&qk`g&z|", "&mh}h&efjhe&qk`g&z|", "&mh}h&efjhe&k`g&z|", "&zpz}ld&zm&qk`g&z|", "&zpz}ld&k`g&oh`ezhol&z|", "&mh}h&efjhe&z|" };
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= array.length) {
                break;
            }
            if (new File(this.b(array[n])).exists()) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    private boolean n() {
        return this.a(Build.DEVICE, ea.cr) || this.a(Build.HARDWARE, ea.cq) || this.a(Build.MANUFACTURER, ea.cs) || this.a(Build.MODEL, ea.ct);
    }
    
    private String o() {
        final AtomicReference<String> atomicReference = new AtomicReference<String>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Handler(this.c.getMainLooper()).post((Runnable)new ai(this, atomicReference, countDownLatch));
        try {
            countDownLatch.await(this.a.get(ea.cz), TimeUnit.MILLISECONDS);
            return atomicReference.get();
        }
        catch (Throwable t) {
            return atomicReference.get();
        }
    }
    
    am a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: ldc             Lcom/applovin/impl/sdk/am;.class
        //     6: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    11: astore_2       
        //    12: aload_2        
        //    13: ifnull          25
        //    16: aload_0        
        //    17: aload_2        
        //    18: checkcast       Lcom/applovin/impl/sdk/am;
        //    21: invokespecial   com/applovin/impl/sdk/ah.a:(Lcom/applovin/impl/sdk/am;)Lcom/applovin/impl/sdk/am;
        //    24: areturn        
        //    25: new             Lcom/applovin/impl/sdk/am;
        //    28: dup            
        //    29: invokespecial   com/applovin/impl/sdk/am.<init>:()V
        //    32: astore_2       
        //    33: aload_2        
        //    34: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //    37: putfield        com/applovin/impl/sdk/am.k:Ljava/util/Locale;
        //    40: aload_2        
        //    41: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //    44: putfield        com/applovin/impl/sdk/am.a:Ljava/lang/String;
        //    47: aload_2        
        //    48: getstatic       android/os/Build$VERSION.RELEASE:Ljava/lang/String;
        //    51: putfield        com/applovin/impl/sdk/am.b:Ljava/lang/String;
        //    54: aload_2        
        //    55: aload_0        
        //    56: invokevirtual   com/applovin/impl/sdk/ah.b:()Ljava/lang/String;
        //    59: putfield        com/applovin/impl/sdk/am.c:Ljava/lang/String;
        //    62: aload_2        
        //    63: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //    66: putfield        com/applovin/impl/sdk/am.d:Ljava/lang/String;
        //    69: aload_2        
        //    70: getstatic       android/os/Build.BRAND:Ljava/lang/String;
        //    73: putfield        com/applovin/impl/sdk/am.e:Ljava/lang/String;
        //    76: aload_2        
        //    77: getstatic       android/os/Build.HARDWARE:Ljava/lang/String;
        //    80: putfield        com/applovin/impl/sdk/am.f:Ljava/lang/String;
        //    83: aload_2        
        //    84: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    87: putfield        com/applovin/impl/sdk/am.h:I
        //    90: aload_2        
        //    91: getstatic       android/os/Build.DEVICE:Ljava/lang/String;
        //    94: putfield        com/applovin/impl/sdk/am.g:Ljava/lang/String;
        //    97: aload_2        
        //    98: aload_0        
        //    99: invokespecial   com/applovin/impl/sdk/ah.f:()Ljava/lang/String;
        //   102: putfield        com/applovin/impl/sdk/am.l:Ljava/lang/String;
        //   105: aload_2        
        //   106: aload_0        
        //   107: invokespecial   com/applovin/impl/sdk/ah.j:()D
        //   110: putfield        com/applovin/impl/sdk/am.o:D
        //   113: aload_2        
        //   114: aload_0        
        //   115: invokespecial   com/applovin/impl/sdk/ah.n:()Z
        //   118: putfield        com/applovin/impl/sdk/am.u:Z
        //   121: aload_0        
        //   122: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //   125: ldc_w           "sensor"
        //   128: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   131: checkcast       Landroid/hardware/SensorManager;
        //   134: iconst_4       
        //   135: invokevirtual   android/hardware/SensorManager.getDefaultSensor:(I)Landroid/hardware/Sensor;
        //   138: ifnull          252
        //   141: iconst_1       
        //   142: istore_1       
        //   143: aload_2        
        //   144: iload_1        
        //   145: putfield        com/applovin/impl/sdk/am.v:Z
        //   148: aload_0        
        //   149: ldc_w           "android.permission.READ_PHONE_STATE"
        //   152: invokevirtual   com/applovin/impl/sdk/ah.a:(Ljava/lang/String;)Z
        //   155: ifeq            206
        //   158: aload_0        
        //   159: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //   162: ldc_w           "phone"
        //   165: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   168: checkcast       Landroid/telephony/TelephonyManager;
        //   171: astore_3       
        //   172: aload_3        
        //   173: ifnull          206
        //   176: aload_2        
        //   177: aload_3        
        //   178: invokevirtual   android/telephony/TelephonyManager.getSimCountryIso:()Ljava/lang/String;
        //   181: getstatic       java/util/Locale.ENGLISH:Ljava/util/Locale;
        //   184: invokevirtual   java/lang/String.toUpperCase:(Ljava/util/Locale;)Ljava/lang/String;
        //   187: putfield        com/applovin/impl/sdk/am.i:Ljava/lang/String;
        //   190: aload_3        
        //   191: invokevirtual   android/telephony/TelephonyManager.getNetworkOperatorName:()Ljava/lang/String;
        //   194: astore_3       
        //   195: aload_2        
        //   196: aload_3        
        //   197: ldc_w           "UTF-8"
        //   200: invokestatic    java/net/URLEncoder.encode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   203: putfield        com/applovin/impl/sdk/am.j:Ljava/lang/String;
        //   206: aload_0        
        //   207: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //   210: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //   213: invokevirtual   android/content/res/Resources.getDisplayMetrics:()Landroid/util/DisplayMetrics;
        //   216: astore_3       
        //   217: aload_2        
        //   218: aload_3        
        //   219: getfield        android/util/DisplayMetrics.density:F
        //   222: putfield        com/applovin/impl/sdk/am.m:F
        //   225: aload_2        
        //   226: aload_3        
        //   227: getfield        android/util/DisplayMetrics.densityDpi:I
        //   230: putfield        com/applovin/impl/sdk/am.n:I
        //   233: aload_0        
        //   234: getfield        com/applovin/impl/sdk/ah.d:Ljava/util/Map;
        //   237: ldc             Lcom/applovin/impl/sdk/am;.class
        //   239: aload_2        
        //   240: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   245: pop            
        //   246: aload_0        
        //   247: aload_2        
        //   248: invokespecial   com/applovin/impl/sdk/ah.a:(Lcom/applovin/impl/sdk/am;)Lcom/applovin/impl/sdk/am;
        //   251: areturn        
        //   252: iconst_0       
        //   253: istore_1       
        //   254: goto            143
        //   257: astore_3       
        //   258: aload_0        
        //   259: getfield        com/applovin/impl/sdk/ah.b:Lcom/applovin/sdk/AppLovinLogger;
        //   262: ldc             "DataCollector"
        //   264: ldc_w           "Unable to retrieve gyroscope availability"
        //   267: aload_3        
        //   268: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   273: goto            148
        //   276: astore          4
        //   278: aload_2        
        //   279: aload_3        
        //   280: putfield        com/applovin/impl/sdk/am.j:Ljava/lang/String;
        //   283: goto            206
        //   286: astore_3       
        //   287: goto            233
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  121    141    257    276    Ljava/lang/Throwable;
        //  143    148    257    276    Ljava/lang/Throwable;
        //  195    206    276    286    Ljava/io/UnsupportedEncodingException;
        //  206    233    286    290    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 136, Size: 136
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
    
    boolean a(final String s) {
        return a(s, this.c);
    }
    
    String b() {
        if (this.a.isFireOS()) {
            return "fireos";
        }
        return "android";
    }
    
    am c() {
        return this.a((am)null);
    }
    
    ak d() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: ldc_w           Lcom/applovin/impl/sdk/ak;.class
        //     7: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: astore_3       
        //    13: aload_3        
        //    14: ifnull          22
        //    17: aload_3        
        //    18: checkcast       Lcom/applovin/impl/sdk/ak;
        //    21: areturn        
        //    22: aload_0        
        //    23: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //    26: invokevirtual   android/content/Context.getApplicationInfo:()Landroid/content/pm/ApplicationInfo;
        //    29: astore          6
        //    31: new             Ljava/io/File;
        //    34: dup            
        //    35: aload           6
        //    37: getfield        android/content/pm/ApplicationInfo.sourceDir:Ljava/lang/String;
        //    40: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    43: invokevirtual   java/io/File.lastModified:()J
        //    46: lstore_1       
        //    47: aload_0        
        //    48: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //    51: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    54: astore          7
        //    56: aload           7
        //    58: aload_0        
        //    59: getfield        com/applovin/impl/sdk/ah.c:Landroid/content/Context;
        //    62: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    65: iconst_0       
        //    66: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    69: astore_3       
        //    70: aload           7
        //    72: aload           6
        //    74: getfield        android/content/pm/ApplicationInfo.packageName:Ljava/lang/String;
        //    77: invokevirtual   android/content/pm/PackageManager.getInstallerPackageName:(Ljava/lang/String;)Ljava/lang/String;
        //    80: astore          5
        //    82: aload_3        
        //    83: astore          4
        //    85: aload           5
        //    87: astore_3       
        //    88: new             Lcom/applovin/impl/sdk/ak;
        //    91: dup            
        //    92: invokespecial   com/applovin/impl/sdk/ak.<init>:()V
        //    95: astore          5
        //    97: aload           5
        //    99: aload           6
        //   101: getfield        android/content/pm/ApplicationInfo.packageName:Ljava/lang/String;
        //   104: putfield        com/applovin/impl/sdk/ak.c:Ljava/lang/String;
        //   107: aload_3        
        //   108: ifnull          184
        //   111: aload           5
        //   113: aload_3        
        //   114: putfield        com/applovin/impl/sdk/ak.d:Ljava/lang/String;
        //   117: aload           5
        //   119: lload_1        
        //   120: putfield        com/applovin/impl/sdk/ak.e:J
        //   123: aload           5
        //   125: aload           7
        //   127: aload           6
        //   129: invokevirtual   android/content/pm/PackageManager.getApplicationLabel:(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
        //   132: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   135: putfield        com/applovin/impl/sdk/ak.a:Ljava/lang/String;
        //   138: aload           4
        //   140: ifnull          190
        //   143: aload           4
        //   145: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //   148: astore_3       
        //   149: aload           5
        //   151: aload_3        
        //   152: putfield        com/applovin/impl/sdk/ak.b:Ljava/lang/String;
        //   155: aload_0        
        //   156: getfield        com/applovin/impl/sdk/ah.d:Ljava/util/Map;
        //   159: ldc_w           Lcom/applovin/impl/sdk/ak;.class
        //   162: aload           5
        //   164: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   169: pop            
        //   170: aload           5
        //   172: areturn        
        //   173: astore_3       
        //   174: aconst_null    
        //   175: astore_3       
        //   176: aload_3        
        //   177: astore          4
        //   179: aconst_null    
        //   180: astore_3       
        //   181: goto            88
        //   184: ldc             ""
        //   186: astore_3       
        //   187: goto            111
        //   190: ldc             ""
        //   192: astore_3       
        //   193: goto            149
        //   196: astore          4
        //   198: goto            176
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  56     70     173    176    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  70     82     196    201    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
    
    aj e() {
        aj aj;
        if (this.a.isFireOS()) {
            aj = this.g();
            if (aj == null) {
                if (this.a.get(ea.ch)) {
                    aj = this.h();
                }
                else {
                    aj = new aj();
                }
            }
        }
        else {
            aj = this.h();
        }
        if (this.a.get(ea.bL)) {
            if (aj.a && !this.a.get(ea.bK)) {
                aj.b = "";
            }
            return aj;
        }
        return new aj();
    }
}
