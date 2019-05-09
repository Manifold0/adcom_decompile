// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.content.Context;
import org.json.JSONObject;
import com.chartboost.sdk.Libraries.i;
import android.content.SharedPreferences;
import com.chartboost.sdk.Model.e;
import java.util.concurrent.atomic.AtomicReference;
import com.chartboost.sdk.Libraries.d;

public class ap
{
    final d a;
    final ai b;
    final AtomicReference<e> c;
    final SharedPreferences d;
    final i e;
    final String f;
    final String g;
    final String h;
    final String i;
    String j;
    String k;
    final String l;
    final Integer m;
    final Integer n;
    final Integer o;
    final Integer p;
    final String q;
    final Float r;
    final String s;
    final String t;
    final String u;
    final JSONObject v;
    final boolean w;
    final String x;
    final Integer y;
    
    public ap(final Context p0, final String p1, final d p2, final ai p3, final AtomicReference<e> p4, final SharedPreferences p5, final i p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: aload_3        
        //     6: putfield        com/chartboost/sdk/impl/ap.a:Lcom/chartboost/sdk/Libraries/d;
        //     9: aload_0        
        //    10: aload           4
        //    12: putfield        com/chartboost/sdk/impl/ap.b:Lcom/chartboost/sdk/impl/ai;
        //    15: aload_0        
        //    16: aload           5
        //    18: putfield        com/chartboost/sdk/impl/ap.c:Ljava/util/concurrent/atomic/AtomicReference;
        //    21: aload_0        
        //    22: aload           6
        //    24: putfield        com/chartboost/sdk/impl/ap.d:Landroid/content/SharedPreferences;
        //    27: aload_0        
        //    28: aload           7
        //    30: putfield        com/chartboost/sdk/impl/ap.e:Lcom/chartboost/sdk/Libraries/i;
        //    33: aload_0        
        //    34: aload_2        
        //    35: putfield        com/chartboost/sdk/impl/ap.s:Ljava/lang/String;
        //    38: ldc             "sdk"
        //    40: getstatic       android/os/Build.PRODUCT:Ljava/lang/String;
        //    43: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    46: ifne            77
        //    49: ldc             "google_sdk"
        //    51: getstatic       android/os/Build.PRODUCT:Ljava/lang/String;
        //    54: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    57: ifne            77
        //    60: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //    63: ifnull          649
        //    66: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //    69: ldc             "Genymotion"
        //    71: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    74: ifeq            649
        //    77: aload_0        
        //    78: ldc             "Android Simulator"
        //    80: putfield        com/chartboost/sdk/impl/ap.f:Ljava/lang/String;
        //    83: aload_0        
        //    84: new             Ljava/lang/StringBuilder;
        //    87: dup            
        //    88: invokespecial   java/lang/StringBuilder.<init>:()V
        //    91: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    97: ldc             " "
        //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   102: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   111: putfield        com/chartboost/sdk/impl/ap.t:Ljava/lang/String;
        //   114: aload_0        
        //   115: aload_1        
        //   116: invokestatic    com/chartboost/sdk/impl/ar.a:(Landroid/content/Context;)Ljava/lang/String;
        //   119: putfield        com/chartboost/sdk/impl/ap.u:Ljava/lang/String;
        //   122: aload_0        
        //   123: new             Ljava/lang/StringBuilder;
        //   126: dup            
        //   127: invokespecial   java/lang/StringBuilder.<init>:()V
        //   130: ldc             "Android "
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: getstatic       android/os/Build$VERSION.RELEASE:Ljava/lang/String;
        //   138: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   141: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   144: putfield        com/chartboost/sdk/impl/ap.g:Ljava/lang/String;
        //   147: aload_0        
        //   148: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //   151: invokevirtual   java/util/Locale.getCountry:()Ljava/lang/String;
        //   154: putfield        com/chartboost/sdk/impl/ap.h:Ljava/lang/String;
        //   157: aload_0        
        //   158: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //   161: invokevirtual   java/util/Locale.getLanguage:()Ljava/lang/String;
        //   164: putfield        com/chartboost/sdk/impl/ap.i:Ljava/lang/String;
        //   167: aload_0        
        //   168: ldc             "7.3.0"
        //   170: putfield        com/chartboost/sdk/impl/ap.l:Ljava/lang/String;
        //   173: aload_0        
        //   174: aload_1        
        //   175: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //   178: invokevirtual   android/content/res/Resources.getDisplayMetrics:()Landroid/util/DisplayMetrics;
        //   181: getfield        android/util/DisplayMetrics.density:F
        //   184: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //   187: putfield        com/chartboost/sdk/impl/ap.r:Ljava/lang/Float;
        //   190: aload_1        
        //   191: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   194: astore_2       
        //   195: aload_0        
        //   196: aload_1        
        //   197: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //   200: aload_2        
        //   201: sipush          128
        //   204: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   207: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //   210: putfield        com/chartboost/sdk/impl/ap.j:Ljava/lang/String;
        //   213: aload_0        
        //   214: aload_2        
        //   215: putfield        com/chartboost/sdk/impl/ap.k:Ljava/lang/String;
        //   218: aload_1        
        //   219: ldc             "phone"
        //   221: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   224: checkcast       Landroid/telephony/TelephonyManager;
        //   227: astore          7
        //   229: aload           7
        //   231: ifnull          689
        //   234: aload           7
        //   236: invokevirtual   android/telephony/TelephonyManager.getPhoneType:()I
        //   239: ifeq            689
        //   242: aload           7
        //   244: invokevirtual   android/telephony/TelephonyManager.getSimState:()I
        //   247: iconst_5       
        //   248: if_icmpne       689
        //   251: aload           7
        //   253: invokevirtual   android/telephony/TelephonyManager.getSimOperator:()Ljava/lang/String;
        //   256: astore_2       
        //   257: aconst_null    
        //   258: astore          5
        //   260: aconst_null    
        //   261: astore          6
        //   263: aload           6
        //   265: astore          4
        //   267: aload           5
        //   269: astore_3       
        //   270: aload_2        
        //   271: ifnull          302
        //   274: aload           6
        //   276: astore          4
        //   278: aload           5
        //   280: astore_3       
        //   281: aload_2        
        //   282: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   285: ifne            302
        //   288: aload_2        
        //   289: iconst_0       
        //   290: iconst_3       
        //   291: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   294: astore_3       
        //   295: aload_2        
        //   296: iconst_3       
        //   297: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   300: astore          4
        //   302: iconst_5       
        //   303: anewarray       Lcom/chartboost/sdk/Libraries/e$a;
        //   306: dup            
        //   307: iconst_0       
        //   308: ldc             "carrier-name"
        //   310: aload           7
        //   312: invokevirtual   android/telephony/TelephonyManager.getNetworkOperatorName:()Ljava/lang/String;
        //   315: invokestatic    com/chartboost/sdk/Libraries/e.a:(Ljava/lang/String;Ljava/lang/Object;)Lcom/chartboost/sdk/Libraries/e$a;
        //   318: aastore        
        //   319: dup            
        //   320: iconst_1       
        //   321: ldc             "mobile-country-code"
        //   323: aload_3        
        //   324: invokestatic    com/chartboost/sdk/Libraries/e.a:(Ljava/lang/String;Ljava/lang/Object;)Lcom/chartboost/sdk/Libraries/e$a;
        //   327: aastore        
        //   328: dup            
        //   329: iconst_2       
        //   330: ldc             "mobile-network-code"
        //   332: aload           4
        //   334: invokestatic    com/chartboost/sdk/Libraries/e.a:(Ljava/lang/String;Ljava/lang/Object;)Lcom/chartboost/sdk/Libraries/e$a;
        //   337: aastore        
        //   338: dup            
        //   339: iconst_3       
        //   340: ldc             "iso-country-code"
        //   342: aload           7
        //   344: invokevirtual   android/telephony/TelephonyManager.getNetworkCountryIso:()Ljava/lang/String;
        //   347: invokestatic    com/chartboost/sdk/Libraries/e.a:(Ljava/lang/String;Ljava/lang/Object;)Lcom/chartboost/sdk/Libraries/e$a;
        //   350: aastore        
        //   351: dup            
        //   352: iconst_4       
        //   353: ldc             "phone-type"
        //   355: aload           7
        //   357: invokevirtual   android/telephony/TelephonyManager.getPhoneType:()I
        //   360: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   363: invokestatic    com/chartboost/sdk/Libraries/e.a:(Ljava/lang/String;Ljava/lang/Object;)Lcom/chartboost/sdk/Libraries/e$a;
        //   366: aastore        
        //   367: invokestatic    com/chartboost/sdk/Libraries/e.a:([Lcom/chartboost/sdk/Libraries/e$a;)Lorg/json/JSONObject;
        //   370: astore_2       
        //   371: aload_0        
        //   372: aload_2        
        //   373: putfield        com/chartboost/sdk/impl/ap.v:Lorg/json/JSONObject;
        //   376: aload_0        
        //   377: invokestatic    com/chartboost/sdk/Libraries/CBUtility.c:()Z
        //   380: putfield        com/chartboost/sdk/impl/ap.w:Z
        //   383: aload_0        
        //   384: invokestatic    com/chartboost/sdk/Libraries/CBUtility.d:()Ljava/lang/String;
        //   387: putfield        com/chartboost/sdk/impl/ap.x:Ljava/lang/String;
        //   390: aload_0        
        //   391: aload_1        
        //   392: invokestatic    com/chartboost/sdk/impl/ai.d:(Landroid/content/Context;)Ljava/lang/Integer;
        //   395: putfield        com/chartboost/sdk/impl/ap.y:Ljava/lang/Integer;
        //   398: iconst_0       
        //   399: istore          9
        //   401: iload           9
        //   403: istore          8
        //   405: aload_1        
        //   406: instanceof      Landroid/app/Activity;
        //   409: ifeq            739
        //   412: iload           9
        //   414: istore          8
        //   416: aload_1        
        //   417: checkcast       Landroid/app/Activity;
        //   420: astore_2       
        //   421: iload           9
        //   423: istore          8
        //   425: new             Landroid/graphics/Rect;
        //   428: dup            
        //   429: invokespecial   android/graphics/Rect.<init>:()V
        //   432: astore_3       
        //   433: iload           9
        //   435: istore          8
        //   437: aload_2        
        //   438: invokevirtual   android/app/Activity.getWindow:()Landroid/view/Window;
        //   441: invokevirtual   android/view/Window.getDecorView:()Landroid/view/View;
        //   444: aload_3        
        //   445: invokevirtual   android/view/View.getWindowVisibleDisplayFrame:(Landroid/graphics/Rect;)V
        //   448: iload           9
        //   450: istore          8
        //   452: aload_3        
        //   453: invokevirtual   android/graphics/Rect.width:()I
        //   456: istore          9
        //   458: iload           9
        //   460: istore          8
        //   462: aload_3        
        //   463: invokevirtual   android/graphics/Rect.height:()I
        //   466: istore          10
        //   468: iload           9
        //   470: istore          8
        //   472: iload           10
        //   474: istore          9
        //   476: iload           9
        //   478: istore          10
        //   480: iload           8
        //   482: istore          9
        //   484: iload           10
        //   486: istore          8
        //   488: invokestatic    com/chartboost/sdk/g.a:()Lcom/chartboost/sdk/g;
        //   491: new             Landroid/util/DisplayMetrics;
        //   494: dup            
        //   495: invokespecial   android/util/DisplayMetrics.<init>:()V
        //   498: invokevirtual   com/chartboost/sdk/g.a:(Ljava/lang/Object;)Ljava/lang/Object;
        //   501: checkcast       Landroid/util/DisplayMetrics;
        //   504: astore_2       
        //   505: aload_2        
        //   506: aload_1        
        //   507: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //   510: invokevirtual   android/content/res/Resources.getDisplayMetrics:()Landroid/util/DisplayMetrics;
        //   513: invokevirtual   android/util/DisplayMetrics.setTo:(Landroid/util/DisplayMetrics;)V
        //   516: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   519: bipush          17
        //   521: if_icmplt       549
        //   524: aload_1        
        //   525: ldc_w           "window"
        //   528: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   531: checkcast       Landroid/view/WindowManager;
        //   534: astore_1       
        //   535: aload_1        
        //   536: ifnull          549
        //   539: aload_1        
        //   540: invokeinterface android/view/WindowManager.getDefaultDisplay:()Landroid/view/Display;
        //   545: aload_2        
        //   546: invokevirtual   android/view/Display.getRealMetrics:(Landroid/util/DisplayMetrics;)V
        //   549: aload_2        
        //   550: getfield        android/util/DisplayMetrics.widthPixels:I
        //   553: istore          11
        //   555: aload_2        
        //   556: getfield        android/util/DisplayMetrics.heightPixels:I
        //   559: istore          10
        //   561: aload_0        
        //   562: iload           11
        //   564: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   567: putfield        com/chartboost/sdk/impl/ap.o:Ljava/lang/Integer;
        //   570: aload_0        
        //   571: iload           10
        //   573: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   576: putfield        com/chartboost/sdk/impl/ap.p:Ljava/lang/Integer;
        //   579: aload_0        
        //   580: new             Ljava/lang/StringBuilder;
        //   583: dup            
        //   584: invokespecial   java/lang/StringBuilder.<init>:()V
        //   587: ldc_w           ""
        //   590: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   593: aload_2        
        //   594: getfield        android/util/DisplayMetrics.densityDpi:I
        //   597: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   600: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   603: putfield        com/chartboost/sdk/impl/ap.q:Ljava/lang/String;
        //   606: iload           9
        //   608: ifle            725
        //   611: iload           9
        //   613: iload           11
        //   615: if_icmpgt       725
        //   618: iload           8
        //   620: ifle            732
        //   623: iload           8
        //   625: iload           10
        //   627: if_icmpgt       732
        //   630: aload_0        
        //   631: iload           9
        //   633: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   636: putfield        com/chartboost/sdk/impl/ap.m:Ljava/lang/Integer;
        //   639: aload_0        
        //   640: iload           8
        //   642: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   645: putfield        com/chartboost/sdk/impl/ap.n:Ljava/lang/Integer;
        //   648: return         
        //   649: aload_0        
        //   650: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //   653: putfield        com/chartboost/sdk/impl/ap.f:Ljava/lang/String;
        //   656: goto            83
        //   659: astore_2       
        //   660: ldc_w           "RequestBody"
        //   663: ldc_w           "Exception raised getting package mager object"
        //   666: aload_2        
        //   667: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   670: goto            218
        //   673: astore_2       
        //   674: ldc_w           Lcom/chartboost/sdk/Chartboost;.class
        //   677: ldc_w           "Unable to retrieve sim operator information"
        //   680: aload_2        
        //   681: invokestatic    com/chartboost/sdk/Tracking/a.a:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Exception;)V
        //   684: aconst_null    
        //   685: astore_2       
        //   686: goto            257
        //   689: new             Lorg/json/JSONObject;
        //   692: dup            
        //   693: invokespecial   org/json/JSONObject.<init>:()V
        //   696: astore_2       
        //   697: goto            371
        //   700: astore_2       
        //   701: ldc_w           "RequestBody"
        //   704: ldc_w           "Exception getting activity size"
        //   707: aload_2        
        //   708: invokestatic    com/chartboost/sdk/Libraries/CBLogging.b:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   711: iconst_0       
        //   712: istore          10
        //   714: iload           8
        //   716: istore          9
        //   718: iload           10
        //   720: istore          8
        //   722: goto            488
        //   725: iload           11
        //   727: istore          9
        //   729: goto            618
        //   732: iload           10
        //   734: istore          8
        //   736: goto            630
        //   739: iconst_0       
        //   740: istore          9
        //   742: iconst_0       
        //   743: istore          8
        //   745: goto            476
        //    Signature:
        //  (Landroid/content/Context;Ljava/lang/String;Lcom/chartboost/sdk/Libraries/d;Lcom/chartboost/sdk/impl/ai;Ljava/util/concurrent/atomic/AtomicReference<Lcom/chartboost/sdk/Model/e;>;Landroid/content/SharedPreferences;Lcom/chartboost/sdk/Libraries/i;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  190    218    659    673    Ljava/lang/Exception;
        //  251    257    673    689    Ljava/lang/Exception;
        //  405    412    700    725    Ljava/lang/Exception;
        //  416    421    700    725    Ljava/lang/Exception;
        //  425    433    700    725    Ljava/lang/Exception;
        //  437    448    700    725    Ljava/lang/Exception;
        //  452    458    700    725    Ljava/lang/Exception;
        //  462    468    700    725    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 350, Size: 350
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
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
