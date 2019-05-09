// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Locale;
import android.view.Window;
import android.app.Activity;
import android.graphics.Rect;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.util.Base64;
import android.os.Bundle;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import java.util.UUID;
import android.content.Context;

public final class gf
{
    public static final String a;
    private static gf d;
    public final ek.a b;
    public final gm c;
    private final ed.a e;
    private final dx.a f;
    private final Context g;
    
    static {
        a = UUID.randomUUID().toString();
    }
    
    private gf(final Context p0, final gm p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: invokestatic    com/tapjoy/internal/gq.a:()V
        //     7: aload_0        
        //     8: new             Lcom/tapjoy/internal/ed$a;
        //    11: dup            
        //    12: invokespecial   com/tapjoy/internal/ed$a.<init>:()V
        //    15: putfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //    18: aload_0        
        //    19: new             Lcom/tapjoy/internal/dx$a;
        //    22: dup            
        //    23: invokespecial   com/tapjoy/internal/dx$a.<init>:()V
        //    26: putfield        com/tapjoy/internal/gf.f:Lcom/tapjoy/internal/dx$a;
        //    29: aload_0        
        //    30: new             Lcom/tapjoy/internal/ek$a;
        //    33: dup            
        //    34: invokespecial   com/tapjoy/internal/ek$a.<init>:()V
        //    37: putfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //    40: aload_0        
        //    41: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //    44: ldc             "11.12.2/Android"
        //    46: putfield        com/tapjoy/internal/ed$a.p:Ljava/lang/String;
        //    49: aload_0        
        //    50: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //    53: ldc             "Android"
        //    55: putfield        com/tapjoy/internal/ed$a.g:Ljava/lang/String;
        //    58: aload_0        
        //    59: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //    62: getstatic       android/os/Build$VERSION.RELEASE:Ljava/lang/String;
        //    65: putfield        com/tapjoy/internal/ed$a.h:Ljava/lang/String;
        //    68: aload_0        
        //    69: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //    72: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //    75: putfield        com/tapjoy/internal/ed$a.e:Ljava/lang/String;
        //    78: aload_0        
        //    79: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //    82: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //    85: putfield        com/tapjoy/internal/ed$a.f:Ljava/lang/String;
        //    88: aload_0        
        //    89: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //    92: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //    95: invokevirtual   java/util/Locale.toString:()Ljava/lang/String;
        //    98: putfield        com/tapjoy/internal/ed$a.l:Ljava/lang/String;
        //   101: aload_0        
        //   102: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //   105: invokestatic    java/util/TimeZone.getDefault:()Ljava/util/TimeZone;
        //   108: invokevirtual   java/util/TimeZone.getID:()Ljava/lang/String;
        //   111: putfield        com/tapjoy/internal/ed$a.m:Ljava/lang/String;
        //   114: aload_1        
        //   115: invokevirtual   android/content/Context.getApplicationContext:()Landroid/content/Context;
        //   118: astore          12
        //   120: aload_0        
        //   121: aload           12
        //   123: putfield        com/tapjoy/internal/gf.g:Landroid/content/Context;
        //   126: aload_0        
        //   127: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //   130: astore          13
        //   132: invokestatic    com/tapjoy/internal/fd.b:()Lcom/tapjoy/internal/fb;
        //   135: ldc             "TJC_OPTION_DISABLE_ANDROID_ID_AS_ANALYTICS_ID"
        //   137: invokevirtual   com/tapjoy/internal/fb.b:(Ljava/lang/String;)Z
        //   140: ifne            1514
        //   143: aload           12
        //   145: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //   148: ldc             "android_id"
        //   150: invokestatic    android/provider/Settings$Secure.getString:(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
        //   153: astore_1       
        //   154: ldc             "9774d56d682e549c"
        //   156: aload_1        
        //   157: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   160: ifne            1414
        //   163: aload_1        
        //   164: invokestatic    com/tapjoy/internal/ct.b:(Ljava/lang/String;)Ljava/lang/String;
        //   167: astore_1       
        //   168: aload_1        
        //   169: astore          11
        //   171: aload_1        
        //   172: ifnonnull       220
        //   175: aload           12
        //   177: invokestatic    com/tapjoy/internal/w.a:(Landroid/content/Context;)Ljava/io/File;
        //   180: astore_1       
        //   181: new             Ljava/io/File;
        //   184: dup            
        //   185: aload           12
        //   187: invokestatic    com/tapjoy/internal/gc.c:(Landroid/content/Context;)Ljava/io/File;
        //   190: ldc             "deviceid"
        //   192: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   195: astore          11
        //   197: aload_1        
        //   198: ifnull          1419
        //   201: new             Ljava/io/File;
        //   204: dup            
        //   205: aload_1        
        //   206: ldc             ".io.5rocks"
        //   208: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   211: astore_1       
        //   212: aload           11
        //   214: aload_1        
        //   215: invokestatic    com/tapjoy/internal/aa.a:(Ljava/io/File;Ljava/io/File;)Ljava/lang/String;
        //   218: astore          11
        //   220: aload           13
        //   222: aload           11
        //   224: putfield        com/tapjoy/internal/ed$a.d:Ljava/lang/String;
        //   227: aload           12
        //   229: invokestatic    com/tapjoy/internal/ab.a:(Landroid/content/Context;)Ljava/lang/String;
        //   232: astore_1       
        //   233: aload_1        
        //   234: ifnull          258
        //   237: aload_0        
        //   238: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //   241: aload_1        
        //   242: ldc             ":"
        //   244: ldc             ""
        //   246: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   249: getstatic       java/util/Locale.US:Ljava/util/Locale;
        //   252: invokevirtual   java/lang/String.toLowerCase:(Ljava/util/Locale;)Ljava/lang/String;
        //   255: putfield        com/tapjoy/internal/ed$a.c:Ljava/lang/String;
        //   258: aload           12
        //   260: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //   263: astore          11
        //   265: aload           12
        //   267: ldc             "phone"
        //   269: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   272: checkcast       Landroid/telephony/TelephonyManager;
        //   275: astore_1       
        //   276: aload_1        
        //   277: ifnull          335
        //   280: aload_1        
        //   281: invokevirtual   android/telephony/TelephonyManager.getSimCountryIso:()Ljava/lang/String;
        //   284: astore          13
        //   286: aload           13
        //   288: invokestatic    com/tapjoy/internal/ct.c:(Ljava/lang/String;)Z
        //   291: ifne            309
        //   294: aload_0        
        //   295: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //   298: aload           13
        //   300: getstatic       java/util/Locale.US:Ljava/util/Locale;
        //   303: invokevirtual   java/lang/String.toUpperCase:(Ljava/util/Locale;)Ljava/lang/String;
        //   306: putfield        com/tapjoy/internal/ed$a.q:Ljava/lang/String;
        //   309: aload_1        
        //   310: invokevirtual   android/telephony/TelephonyManager.getNetworkCountryIso:()Ljava/lang/String;
        //   313: astore_1       
        //   314: aload_1        
        //   315: invokestatic    com/tapjoy/internal/ct.c:(Ljava/lang/String;)Z
        //   318: ifne            335
        //   321: aload_0        
        //   322: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //   325: aload_1        
        //   326: getstatic       java/util/Locale.US:Ljava/util/Locale;
        //   329: invokevirtual   java/lang/String.toUpperCase:(Ljava/util/Locale;)Ljava/lang/String;
        //   332: putfield        com/tapjoy/internal/ed$a.r:Ljava/lang/String;
        //   335: aload           12
        //   337: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   340: astore          12
        //   342: aload_0        
        //   343: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //   346: aload           12
        //   348: putfield        com/tapjoy/internal/ed$a.n:Ljava/lang/String;
        //   351: aload_0        
        //   352: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //   355: astore          13
        //   357: aload           11
        //   359: aload           12
        //   361: invokestatic    com/tapjoy/internal/ae.e:(Landroid/content/pm/PackageManager;Ljava/lang/String;)[Landroid/content/pm/Signature;
        //   364: astore_1       
        //   365: aload_1        
        //   366: ifnull          1424
        //   369: aload_1        
        //   370: arraylength    
        //   371: ifle            1424
        //   374: aload_1        
        //   375: iconst_0       
        //   376: aaload         
        //   377: invokevirtual   android/content/pm/Signature.toByteArray:()[B
        //   380: invokestatic    com/tapjoy/internal/cm.a:([B)[B
        //   383: iconst_2       
        //   384: invokestatic    android/util/Base64.encodeToString:([BI)Ljava/lang/String;
        //   387: astore_1       
        //   388: aload           13
        //   390: aload_1        
        //   391: invokestatic    com/tapjoy/internal/ct.a:(Ljava/lang/String;)Ljava/lang/String;
        //   394: putfield        com/tapjoy/internal/ed$a.o:Ljava/lang/String;
        //   397: aload_0        
        //   398: getfield        com/tapjoy/internal/gf.f:Lcom/tapjoy/internal/dx$a;
        //   401: aload           11
        //   403: aload           12
        //   405: invokestatic    com/tapjoy/internal/ae.a:(Landroid/content/pm/PackageManager;Ljava/lang/String;)Ljava/lang/String;
        //   408: putfield        com/tapjoy/internal/dx$a.c:Ljava/lang/String;
        //   411: aload_0        
        //   412: getfield        com/tapjoy/internal/gf.f:Lcom/tapjoy/internal/dx$a;
        //   415: aload           11
        //   417: aload           12
        //   419: invokestatic    com/tapjoy/internal/ae.b:(Landroid/content/pm/PackageManager;Ljava/lang/String;)I
        //   422: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   425: putfield        com/tapjoy/internal/dx$a.d:Ljava/lang/Integer;
        //   428: aload           11
        //   430: aload           12
        //   432: invokevirtual   android/content/pm/PackageManager.getInstallerPackageName:(Ljava/lang/String;)Ljava/lang/String;
        //   435: astore_1       
        //   436: aload_1        
        //   437: invokestatic    com/tapjoy/internal/ct.c:(Ljava/lang/String;)Z
        //   440: ifne            451
        //   443: aload_0        
        //   444: getfield        com/tapjoy/internal/gf.f:Lcom/tapjoy/internal/dx$a;
        //   447: aload_1        
        //   448: putfield        com/tapjoy/internal/dx$a.f:Ljava/lang/String;
        //   451: aload           11
        //   453: aload           12
        //   455: invokestatic    com/tapjoy/internal/gf.a:(Landroid/content/pm/PackageManager;Ljava/lang/String;)Ljava/lang/String;
        //   458: astore_1       
        //   459: aload_1        
        //   460: invokestatic    com/tapjoy/internal/ct.c:(Ljava/lang/String;)Z
        //   463: ifne            474
        //   466: aload_0        
        //   467: getfield        com/tapjoy/internal/gf.f:Lcom/tapjoy/internal/dx$a;
        //   470: aload_1        
        //   471: putfield        com/tapjoy/internal/dx$a.g:Ljava/lang/String;
        //   474: aload_0        
        //   475: invokevirtual   com/tapjoy/internal/gf.a:()V
        //   478: aload_0        
        //   479: aload_2        
        //   480: putfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   483: aload_0        
        //   484: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   487: getfield        com/tapjoy/internal/gm.c:Lcom/tapjoy/internal/q;
        //   490: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //   493: astore_1       
        //   494: aload_1        
        //   495: ifnull          532
        //   498: aload_1        
        //   499: invokevirtual   java/lang/String.length:()I
        //   502: ifle            532
        //   505: aload_0        
        //   506: getfield        com/tapjoy/internal/gf.e:Lcom/tapjoy/internal/ed$a;
        //   509: new             Ljava/lang/StringBuilder;
        //   512: dup            
        //   513: invokespecial   java/lang/StringBuilder.<init>:()V
        //   516: aload_1        
        //   517: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   520: ldc_w           " 11.12.2/Android"
        //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   529: putfield        com/tapjoy/internal/ed$a.p:Ljava/lang/String;
        //   532: aload_0        
        //   533: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   536: invokevirtual   com/tapjoy/internal/gm.b:()Ljava/lang/String;
        //   539: astore_1       
        //   540: aload_1        
        //   541: ifnull          552
        //   544: aload_0        
        //   545: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   548: aload_1        
        //   549: putfield        com/tapjoy/internal/ek$a.d:Ljava/lang/String;
        //   552: aload_0        
        //   553: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   556: astore_1       
        //   557: aload_0        
        //   558: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   561: astore_2       
        //   562: aload_2        
        //   563: getfield        com/tapjoy/internal/gm.b:Landroid/content/SharedPreferences;
        //   566: ldc_w           "it"
        //   569: lconst_0       
        //   570: invokeinterface android/content/SharedPreferences.getLong:(Ljava/lang/String;J)J
        //   575: lstore          8
        //   577: lload           8
        //   579: lstore          6
        //   581: lload           8
        //   583: lconst_0       
        //   584: lcmp           
        //   585: ifne            715
        //   588: aload_2        
        //   589: getfield        com/tapjoy/internal/gm.a:Landroid/content/Context;
        //   592: astore          11
        //   594: aload           11
        //   596: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //   599: aload           11
        //   601: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   604: invokestatic    com/tapjoy/internal/ae.c:(Landroid/content/pm/PackageManager;Ljava/lang/String;)J
        //   607: lstore          8
        //   609: lload           8
        //   611: lstore          6
        //   613: lload           8
        //   615: lconst_0       
        //   616: lcmp           
        //   617: ifne            690
        //   620: aload_2        
        //   621: getfield        com/tapjoy/internal/gm.a:Landroid/content/Context;
        //   624: invokestatic    com/tapjoy/internal/gc.d:(Landroid/content/Context;)Ljava/io/File;
        //   627: invokevirtual   java/io/File.lastModified:()J
        //   630: lstore          8
        //   632: lload           8
        //   634: lstore          6
        //   636: lload           8
        //   638: lconst_0       
        //   639: lcmp           
        //   640: ifne            690
        //   643: aload_2        
        //   644: getfield        com/tapjoy/internal/gm.a:Landroid/content/Context;
        //   647: astore          11
        //   649: new             Ljava/io/File;
        //   652: dup            
        //   653: aload           11
        //   655: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //   658: aload           11
        //   660: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   663: invokestatic    com/tapjoy/internal/ae.d:(Landroid/content/pm/PackageManager;Ljava/lang/String;)Ljava/lang/String;
        //   666: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   669: invokevirtual   java/io/File.lastModified:()J
        //   672: lstore          8
        //   674: lload           8
        //   676: lstore          6
        //   678: lload           8
        //   680: lconst_0       
        //   681: lcmp           
        //   682: ifne            690
        //   685: invokestatic    java/lang/System.currentTimeMillis:()J
        //   688: lstore          6
        //   690: aload_2        
        //   691: getfield        com/tapjoy/internal/gm.b:Landroid/content/SharedPreferences;
        //   694: invokeinterface android/content/SharedPreferences.edit:()Landroid/content/SharedPreferences$Editor;
        //   699: ldc_w           "it"
        //   702: lload           6
        //   704: invokeinterface android/content/SharedPreferences$Editor.putLong:(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;
        //   709: invokeinterface android/content/SharedPreferences$Editor.commit:()Z
        //   714: pop            
        //   715: aload_1        
        //   716: lload           6
        //   718: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   721: putfield        com/tapjoy/internal/ek$a.c:Ljava/lang/Long;
        //   724: aload_0        
        //   725: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   728: getfield        com/tapjoy/internal/gm.f:Lcom/tapjoy/internal/m;
        //   731: invokevirtual   com/tapjoy/internal/m.b:()I
        //   734: istore          5
        //   736: aload_0        
        //   737: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   740: bipush          7
        //   742: iload           5
        //   744: invokestatic    com/tapjoy/internal/gf.a:(II)I
        //   747: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   750: putfield        com/tapjoy/internal/ek$a.e:Ljava/lang/Integer;
        //   753: aload_0        
        //   754: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   757: bipush          30
        //   759: iload           5
        //   761: invokestatic    com/tapjoy/internal/gf.a:(II)I
        //   764: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   767: putfield        com/tapjoy/internal/ek$a.f:Ljava/lang/Integer;
        //   770: aload_0        
        //   771: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   774: getfield        com/tapjoy/internal/gm.h:Lcom/tapjoy/internal/m;
        //   777: invokevirtual   com/tapjoy/internal/m.b:()I
        //   780: istore          5
        //   782: iload           5
        //   784: ifle            799
        //   787: aload_0        
        //   788: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   791: iload           5
        //   793: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   796: putfield        com/tapjoy/internal/ek$a.h:Ljava/lang/Integer;
        //   799: aload_0        
        //   800: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   803: getfield        com/tapjoy/internal/gm.i:Lcom/tapjoy/internal/n;
        //   806: invokevirtual   com/tapjoy/internal/n.a:()J
        //   809: lstore          6
        //   811: lload           6
        //   813: lconst_0       
        //   814: lcmp           
        //   815: ifle            830
        //   818: aload_0        
        //   819: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   822: lload           6
        //   824: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   827: putfield        com/tapjoy/internal/ek$a.i:Ljava/lang/Long;
        //   830: aload_0        
        //   831: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   834: getfield        com/tapjoy/internal/gm.j:Lcom/tapjoy/internal/n;
        //   837: invokevirtual   com/tapjoy/internal/n.a:()J
        //   840: lstore          6
        //   842: lload           6
        //   844: lconst_0       
        //   845: lcmp           
        //   846: ifle            861
        //   849: aload_0        
        //   850: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   853: lload           6
        //   855: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   858: putfield        com/tapjoy/internal/ek$a.j:Ljava/lang/Long;
        //   861: aload_0        
        //   862: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   865: getfield        com/tapjoy/internal/gm.k:Lcom/tapjoy/internal/n;
        //   868: invokevirtual   com/tapjoy/internal/n.a:()J
        //   871: lstore          6
        //   873: lload           6
        //   875: lconst_0       
        //   876: lcmp           
        //   877: ifle            892
        //   880: aload_0        
        //   881: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   884: lload           6
        //   886: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   889: putfield        com/tapjoy/internal/ek$a.k:Ljava/lang/Long;
        //   892: aload_0        
        //   893: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   896: getfield        com/tapjoy/internal/gm.l:Lcom/tapjoy/internal/q;
        //   899: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //   902: astore_1       
        //   903: aload_1        
        //   904: ifnull          915
        //   907: aload_0        
        //   908: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   911: aload_1        
        //   912: putfield        com/tapjoy/internal/ek$a.l:Ljava/lang/String;
        //   915: aload_0        
        //   916: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   919: getfield        com/tapjoy/internal/gm.m:Lcom/tapjoy/internal/m;
        //   922: invokevirtual   com/tapjoy/internal/m.b:()I
        //   925: istore          5
        //   927: iload           5
        //   929: ifle            944
        //   932: aload_0        
        //   933: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   936: iload           5
        //   938: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   941: putfield        com/tapjoy/internal/ek$a.m:Ljava/lang/Integer;
        //   944: aload_0        
        //   945: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   948: getfield        com/tapjoy/internal/gm.n:Lcom/tapjoy/internal/k;
        //   951: invokevirtual   com/tapjoy/internal/k.a:()D
        //   954: dstore_3       
        //   955: dload_3        
        //   956: dconst_0       
        //   957: dcmpl          
        //   958: ifeq            972
        //   961: aload_0        
        //   962: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   965: dload_3        
        //   966: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   969: putfield        com/tapjoy/internal/ek$a.n:Ljava/lang/Double;
        //   972: aload_0        
        //   973: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   976: getfield        com/tapjoy/internal/gm.o:Lcom/tapjoy/internal/n;
        //   979: invokevirtual   com/tapjoy/internal/n.a:()J
        //   982: lstore          6
        //   984: lload           6
        //   986: lconst_0       
        //   987: lcmp           
        //   988: ifle            1003
        //   991: aload_0        
        //   992: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   995: lload           6
        //   997: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1000: putfield        com/tapjoy/internal/ek$a.o:Ljava/lang/Long;
        //  1003: aload_0        
        //  1004: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1007: getfield        com/tapjoy/internal/gm.p:Lcom/tapjoy/internal/k;
        //  1010: invokevirtual   com/tapjoy/internal/k.a:()D
        //  1013: dstore_3       
        //  1014: dload_3        
        //  1015: dconst_0       
        //  1016: dcmpl          
        //  1017: ifeq            1031
        //  1020: aload_0        
        //  1021: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1024: dload_3        
        //  1025: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //  1028: putfield        com/tapjoy/internal/ek$a.p:Ljava/lang/Double;
        //  1031: aload_0        
        //  1032: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1035: getfield        com/tapjoy/internal/gm.g:Lcom/tapjoy/internal/q;
        //  1038: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1041: astore_1       
        //  1042: aload_1        
        //  1043: ifnull          1092
        //  1046: aload_1        
        //  1047: iconst_2       
        //  1048: invokestatic    android/util/Base64.decode:(Ljava/lang/String;I)[B
        //  1051: astore_1       
        //  1052: getstatic       com/tapjoy/internal/ei.c:Lcom/tapjoy/internal/dn;
        //  1055: aload_1        
        //  1056: invokevirtual   com/tapjoy/internal/dn.a:([B)Ljava/lang/Object;
        //  1059: checkcast       Lcom/tapjoy/internal/ei;
        //  1062: astore_1       
        //  1063: aload_0        
        //  1064: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1067: getfield        com/tapjoy/internal/ek$a.g:Ljava/util/List;
        //  1070: invokeinterface java/util/List.clear:()V
        //  1075: aload_0        
        //  1076: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1079: getfield        com/tapjoy/internal/ek$a.g:Ljava/util/List;
        //  1082: aload_1        
        //  1083: getfield        com/tapjoy/internal/ei.d:Ljava/util/List;
        //  1086: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  1091: pop            
        //  1092: aload_0        
        //  1093: getfield        com/tapjoy/internal/gf.f:Lcom/tapjoy/internal/dx$a;
        //  1096: aload_0        
        //  1097: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1100: getfield        com/tapjoy/internal/gm.q:Lcom/tapjoy/internal/q;
        //  1103: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1106: putfield        com/tapjoy/internal/dx$a.e:Ljava/lang/String;
        //  1109: aload_0        
        //  1110: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1113: aload_0        
        //  1114: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1117: getfield        com/tapjoy/internal/gm.r:Lcom/tapjoy/internal/q;
        //  1120: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1123: putfield        com/tapjoy/internal/ek$a.s:Ljava/lang/String;
        //  1126: aload_0        
        //  1127: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1130: getfield        com/tapjoy/internal/gm.s:Lcom/tapjoy/internal/m;
        //  1133: invokevirtual   com/tapjoy/internal/m.a:()Ljava/lang/Integer;
        //  1136: invokevirtual   java/lang/Integer.intValue:()I
        //  1139: istore          5
        //  1141: aload_0        
        //  1142: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1145: astore_2       
        //  1146: iload           5
        //  1148: iconst_m1      
        //  1149: if_icmpeq       1457
        //  1152: iload           5
        //  1154: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1157: astore_1       
        //  1158: aload_2        
        //  1159: aload_1        
        //  1160: putfield        com/tapjoy/internal/ek$a.t:Ljava/lang/Integer;
        //  1163: aload_0        
        //  1164: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1167: getfield        com/tapjoy/internal/gm.t:Lcom/tapjoy/internal/m;
        //  1170: invokevirtual   com/tapjoy/internal/m.a:()Ljava/lang/Integer;
        //  1173: invokevirtual   java/lang/Integer.intValue:()I
        //  1176: istore          5
        //  1178: aload_0        
        //  1179: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1182: astore_2       
        //  1183: iload           5
        //  1185: iconst_m1      
        //  1186: if_icmpeq       1462
        //  1189: iload           5
        //  1191: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1194: astore_1       
        //  1195: aload_2        
        //  1196: aload_1        
        //  1197: putfield        com/tapjoy/internal/ek$a.u:Ljava/lang/Integer;
        //  1200: aload_0        
        //  1201: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1204: aload_0        
        //  1205: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1208: getfield        com/tapjoy/internal/gm.u:Lcom/tapjoy/internal/q;
        //  1211: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1214: putfield        com/tapjoy/internal/ek$a.v:Ljava/lang/String;
        //  1217: aload_0        
        //  1218: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1221: aload_0        
        //  1222: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1225: getfield        com/tapjoy/internal/gm.v:Lcom/tapjoy/internal/q;
        //  1228: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1231: putfield        com/tapjoy/internal/ek$a.w:Ljava/lang/String;
        //  1234: aload_0        
        //  1235: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1238: aload_0        
        //  1239: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1242: getfield        com/tapjoy/internal/gm.w:Lcom/tapjoy/internal/q;
        //  1245: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1248: putfield        com/tapjoy/internal/ek$a.x:Ljava/lang/String;
        //  1251: aload_0        
        //  1252: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1255: aload_0        
        //  1256: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1259: getfield        com/tapjoy/internal/gm.x:Lcom/tapjoy/internal/q;
        //  1262: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1265: putfield        com/tapjoy/internal/ek$a.y:Ljava/lang/String;
        //  1268: aload_0        
        //  1269: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1272: aload_0        
        //  1273: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1276: getfield        com/tapjoy/internal/gm.y:Lcom/tapjoy/internal/q;
        //  1279: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1282: putfield        com/tapjoy/internal/ek$a.z:Ljava/lang/String;
        //  1285: aload_0        
        //  1286: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1289: getfield        com/tapjoy/internal/gm.z:Lcom/tapjoy/internal/q;
        //  1292: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1295: astore_1       
        //  1296: aload_1        
        //  1297: ifnull          1346
        //  1300: aload_1        
        //  1301: iconst_2       
        //  1302: invokestatic    android/util/Base64.decode:(Ljava/lang/String;I)[B
        //  1305: astore_1       
        //  1306: getstatic       com/tapjoy/internal/ej.c:Lcom/tapjoy/internal/dn;
        //  1309: aload_1        
        //  1310: invokevirtual   com/tapjoy/internal/dn.a:([B)Ljava/lang/Object;
        //  1313: checkcast       Lcom/tapjoy/internal/ej;
        //  1316: astore_1       
        //  1317: aload_0        
        //  1318: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1321: getfield        com/tapjoy/internal/ek$a.A:Ljava/util/List;
        //  1324: invokeinterface java/util/List.clear:()V
        //  1329: aload_0        
        //  1330: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1333: getfield        com/tapjoy/internal/ek$a.A:Ljava/util/List;
        //  1336: aload_1        
        //  1337: getfield        com/tapjoy/internal/ej.d:Ljava/util/List;
        //  1340: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //  1345: pop            
        //  1346: aload_0        
        //  1347: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1350: getfield        com/tapjoy/internal/gm.A:Lcom/tapjoy/internal/q;
        //  1353: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //  1356: astore_1       
        //  1357: aload_0        
        //  1358: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1361: getfield        com/tapjoy/internal/gm.B:Lcom/tapjoy/internal/j;
        //  1364: invokevirtual   com/tapjoy/internal/j.a:()Ljava/lang/Boolean;
        //  1367: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1370: istore          10
        //  1372: aload_1        
        //  1373: ifnull          1495
        //  1376: aload_0        
        //  1377: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1380: aload_1        
        //  1381: putfield        com/tapjoy/internal/ek$a.q:Ljava/lang/String;
        //  1384: aload_0        
        //  1385: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1388: iload           10
        //  1390: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1393: putfield        com/tapjoy/internal/ek$a.r:Ljava/lang/Boolean;
        //  1396: aload_0        
        //  1397: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1400: aload_0        
        //  1401: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1404: getfield        com/tapjoy/internal/gm.C:Lcom/tapjoy/internal/j;
        //  1407: invokevirtual   com/tapjoy/internal/j.a:()Ljava/lang/Boolean;
        //  1410: putfield        com/tapjoy/internal/ek$a.B:Ljava/lang/Boolean;
        //  1413: return         
        //  1414: aconst_null    
        //  1415: astore_1       
        //  1416: goto            168
        //  1419: aconst_null    
        //  1420: astore_1       
        //  1421: goto            212
        //  1424: aconst_null    
        //  1425: astore_1       
        //  1426: goto            388
        //  1429: astore_1       
        //  1430: aload_0        
        //  1431: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1434: getfield        com/tapjoy/internal/gm.g:Lcom/tapjoy/internal/q;
        //  1437: invokevirtual   com/tapjoy/internal/q.c:()V
        //  1440: goto            1092
        //  1443: astore_1       
        //  1444: aload_0        
        //  1445: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1448: getfield        com/tapjoy/internal/gm.g:Lcom/tapjoy/internal/q;
        //  1451: invokevirtual   com/tapjoy/internal/q.c:()V
        //  1454: goto            1092
        //  1457: aconst_null    
        //  1458: astore_1       
        //  1459: goto            1158
        //  1462: aconst_null    
        //  1463: astore_1       
        //  1464: goto            1195
        //  1467: astore_1       
        //  1468: aload_0        
        //  1469: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1472: getfield        com/tapjoy/internal/gm.z:Lcom/tapjoy/internal/q;
        //  1475: invokevirtual   com/tapjoy/internal/q.c:()V
        //  1478: goto            1346
        //  1481: astore_1       
        //  1482: aload_0        
        //  1483: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //  1486: getfield        com/tapjoy/internal/gm.z:Lcom/tapjoy/internal/q;
        //  1489: invokevirtual   com/tapjoy/internal/q.c:()V
        //  1492: goto            1346
        //  1495: aload_0        
        //  1496: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1499: aconst_null    
        //  1500: putfield        com/tapjoy/internal/ek$a.q:Ljava/lang/String;
        //  1503: aload_0        
        //  1504: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //  1507: aconst_null    
        //  1508: putfield        com/tapjoy/internal/ek$a.r:Ljava/lang/Boolean;
        //  1511: goto            1396
        //  1514: aconst_null    
        //  1515: astore_1       
        //  1516: goto            168
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  1046   1092   1429   1443   Ljava/lang/IllegalArgumentException;
        //  1046   1092   1443   1457   Ljava/io/IOException;
        //  1300   1346   1467   1481   Ljava/lang/IllegalArgumentException;
        //  1300   1346   1481   1495   Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_1346:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    private static int a(final int n, final int n2) {
        return Integer.bitCount((1 << n) - 1 & n2);
    }
    
    public static gf a(final Context context) {
        synchronized (gf.class) {
            if (gf.d == null) {
                gf.d = new gf(context, gm.a(context));
            }
            return gf.d;
        }
    }
    
    private static String a(final PackageManager packageManager, final String s) {
        try {
            final Bundle metaData = packageManager.getApplicationInfo(s, 128).metaData;
            if (metaData != null) {
                final Object value = metaData.get("com.tapjoy.appstore");
                if (value != null) {
                    return value.toString().trim();
                }
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return null;
    }
    
    private void g() {
        this.c.g.a(Base64.encodeToString(ei.c.b(new ei(this.b.g)), 2));
    }
    
    final void a() {
        // monitorenter(this)
        try {
            try {
                final DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager)this.g.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                final Activity a = fu.a();
                if (a != null) {
                    final Window window = a.getWindow();
                    if (window != null) {
                        final int heightPixels = displayMetrics.heightPixels;
                        final Rect rect = new Rect();
                        window.getDecorView().getWindowVisibleDisplayFrame(rect);
                        displayMetrics.heightPixels = heightPixels - rect.top;
                    }
                }
                this.e.i = displayMetrics.densityDpi;
                this.e.j = displayMetrics.widthPixels;
                this.e.k = displayMetrics.heightPixels;
            }
            finally {
            }
            // monitorexit(this)
        }
        catch (Exception ex) {}
    }
    
    public final boolean a(final int n, final String z) {
        boolean b = false;
        final boolean b2 = false;
        final boolean b3 = false;
        final boolean b4 = false;
        final boolean b5 = false;
        boolean b6 = false;
        // monitorenter(this)
    Block_11_Outer:
        while (true) {
            switch (n) {
                case 1: {
                    Label_0060: {
                        break Label_0060;
                        try {
                            // monitorexit(this)
                            return b6;
                        Block_10_Outer:
                            while (true) {
                                this.b.v = z;
                                b6 = b;
                                continue Block_11_Outer;
                            Label_0088:
                                while (true) {
                                    b = true;
                                    break Label_0088;
                                    this.c.u.a(z);
                                    continue;
                                }
                                b6 = b;
                                continue Block_10_Outer;
                            }
                        }
                        // iftrue(Label_0088:, cr.a((Object)this.b.v, (Object)z))
                        // iftrue(Label_0056:, !b)
                        finally {
                        }
                        // monitorexit(this)
                    }
                }
                case 2: {
                    this.c.v.a(z);
                    boolean b7 = b2;
                    if (!cr.a(this.b.w, z)) {
                        b7 = true;
                    }
                    b6 = b7;
                    if (b7) {
                        this.b.w = z;
                        b6 = b7;
                    }
                    continue;
                }
                case 3: {
                    this.c.w.a(z);
                    boolean b8 = b3;
                    if (!cr.a(this.b.x, z)) {
                        b8 = true;
                    }
                    b6 = b8;
                    if (b8) {
                        this.b.x = z;
                        b6 = b8;
                    }
                    continue;
                }
                case 4: {
                    this.c.x.a(z);
                    boolean b9 = b4;
                    if (!cr.a(this.b.y, z)) {
                        b9 = true;
                    }
                    b6 = b9;
                    if (b9) {
                        this.b.y = z;
                        b6 = b9;
                    }
                    continue;
                }
                case 5: {
                    this.c.y.a(z);
                    boolean b10 = b5;
                    if (!cr.a(this.b.z, z)) {
                        b10 = true;
                    }
                    b6 = b10;
                    if (b10) {
                        this.b.z = z;
                        b6 = b10;
                    }
                    continue;
                }
            }
            break;
        }
    }
    
    public final boolean a(final Integer t) {
        while (true) {
            while (true) {
                synchronized (this) {
                    this.c.s.a(t);
                    if (!cr.a(this.b.t, t)) {
                        final boolean b = true;
                        if (b) {
                            this.b.t = t;
                        }
                        return b;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    public final boolean a(final String e) {
        while (true) {
            final boolean b = true;
            boolean b2 = true;
            while (true) {
                Label_0080: {
                    while (true) {
                        Label_0075: {
                            synchronized (this) {
                                this.c.q.a(e);
                                if (e != null) {
                                    if (cr.a(this.f.e, e)) {
                                        break Label_0080;
                                    }
                                    this.f.e = e;
                                }
                                else {
                                    if (this.f.e == null) {
                                        break Label_0075;
                                    }
                                    b2 = b;
                                    this.f.e = null;
                                }
                                return b2;
                            }
                        }
                        b2 = false;
                        continue;
                    }
                }
                b2 = false;
                continue;
            }
        }
    }
    
    final boolean a(final String s, final long n, final boolean b) {
        while (true) {
            while (true) {
                int n2;
                synchronized (this) {
                    final int size = this.b.g.size();
                    n2 = 0;
                    if (n2 >= size) {
                        this.b.g.add(new eh(s, n));
                        this.g();
                        return true;
                    }
                    final eh eh = this.b.g.get(n2);
                    if (eh.f.equals(s)) {
                        if (b) {
                            final eh.a b2 = eh.b();
                            b2.d = n;
                            this.b.g.set(n2, b2.b());
                            return true;
                        }
                        return false;
                    }
                }
                ++n2;
                continue;
            }
        }
    }
    
    public final boolean a(final boolean b) {
        while (true) {
            while (true) {
                synchronized (this) {
                    this.c.C.a(b);
                    if (b != (boolean)cr.b(this.b.B, ek.r)) {
                        final boolean b2 = true;
                        this.b.B = b;
                        return b2;
                    }
                }
                final boolean b2 = false;
                continue;
            }
        }
    }
    
    public final ee b() {
        while (true) {
            while (true) {
                Label_0145: {
                    synchronized (this) {
                        this.e.l = Locale.getDefault().toString();
                        this.e.m = TimeZone.getDefault().getID();
                        boolean b = false;
                        final long currentTimeMillis = System.currentTimeMillis();
                        final Iterator<eh> iterator = (Iterator<eh>)this.b.g.iterator();
                        if (!iterator.hasNext()) {
                            if (b) {
                                this.g();
                            }
                            return new ee(this.e.b(), this.f.b(), this.b.b());
                        }
                        if (iterator.next().g <= currentTimeMillis - 259200000L) {
                            iterator.remove();
                            b = true;
                            break Label_0145;
                        }
                        break Label_0145;
                    }
                }
                continue;
            }
        }
    }
    
    public final boolean b(final Integer u) {
        while (true) {
            while (true) {
                synchronized (this) {
                    this.c.t.a(u);
                    if (!cr.a(this.b.u, u)) {
                        final boolean b = true;
                        if (b) {
                            this.b.u = u;
                        }
                        return b;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    public final boolean b(final String s) {
        while (true) {
            while (true) {
                synchronized (this) {
                    this.c.r.a(s);
                    if (!cr.a(this.b.s, s)) {
                        final boolean b = true;
                        if (b) {
                            this.b.s = s;
                        }
                        return b;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    final String c() {
        synchronized (this) {
            return this.c.d.a();
        }
    }
    
    public final boolean c(final String s) {
        while (true) {
            while (true) {
                int n;
                synchronized (this) {
                    n = this.b.g.size() - 1;
                    if (n < 0) {
                        return false;
                    }
                    final eh eh = this.b.g.get(n);
                    if (eh.f.equals(s)) {
                        final eh.a b = eh.b();
                        b.e = System.currentTimeMillis();
                        this.b.g.set(n, b.b());
                        this.g();
                        return true;
                    }
                }
                --n;
                continue;
            }
        }
    }
    
    public final ef d() {
        int n;
        Calendar instance;
        int value;
        int n2;
        int n3 = 0;
        ef ef;
        int n4 = 0;
        int a;
        Calendar instance2;
        long n5;
        final Calendar calendar;
        Label_0260_Outer:Label_0271_Outer:
        while (true) {
            n = 1;
            while (true) {
            Label_0529:
                while (true) {
                Label_0428:
                    while (true) {
                    Label_0510:
                        while (true) {
                            Label_0507: {
                                synchronized (this) {
                                    instance = Calendar.getInstance();
                                    value = instance.get(1);
                                    n2 = instance.get(2);
                                    value = instance.get(5) + (value * 10000 + n2 * 100 + 100);
                                    n3 = this.c.e.a();
                                    if (n3 != value) {
                                        if (n3 == 0) {
                                            this.b.e = 1;
                                            this.b.f = 1;
                                            ef = new ef("fq7_0_1", "fq30_0_1", null);
                                        }
                                        else {
                                            n4 = this.c.f.a();
                                            n2 = a(7, n4);
                                            a = a(30, n4);
                                            instance2 = Calendar.getInstance();
                                            instance2.set(n3 / 10000, n3 / 100 % 100 - 1, n3 % 100);
                                            n3 = Integer.signum(instance.get(1) - instance2.get(1));
                                            switch (n3) {
                                                case 0: {
                                                    n = instance.get(6) - instance2.get(6);
                                                    if (Math.abs(n) >= 30) {
                                                        n = 0;
                                                        n |= 0x1;
                                                        n4 = a(7, n);
                                                        n3 = a(30, n);
                                                        this.b.e = n4;
                                                        this.b.f = n3;
                                                        ef = new ef("fq7_" + n2 + "_" + n4, "fq30_" + a + "_" + n3, null);
                                                        break;
                                                    }
                                                    break Label_0529;
                                                }
                                                case -1: {
                                                    ((Calendar)instance2.clone()).set(instance.get(1), instance.get(2), instance.get(5));
                                                    n5 = instance2.getTimeInMillis();
                                                    break Label_0510;
                                                }
                                                case 1: {
                                                    ((Calendar)instance.clone()).set(instance2.get(1), instance2.get(2), instance2.get(5));
                                                    n5 = instance.getTimeInMillis();
                                                    break Label_0510;
                                                }
                                                default: {
                                                    break Label_0507;
                                                }
                                            }
                                        }
                                        this.c.e.a(value);
                                        this.c.f.a(n);
                                        return ef;
                                    }
                                    return null;
                                    // iftrue(Label_0515:, calendar.getTimeInMillis() >= n5)
                                    calendar.add(5, 1);
                                    ++n;
                                    continue Label_0428;
                                }
                            }
                            continue Label_0260_Outer;
                        }
                        n = 0;
                        continue Label_0428;
                    }
                    Label_0515: {
                        if (n3 > 0) {
                            continue Label_0271_Outer;
                        }
                    }
                    n = -n;
                    continue Label_0271_Outer;
                }
                if (n >= 0) {
                    n = n4 << n;
                    continue;
                }
                n = n4 >> -n;
                continue;
            }
        }
    }
    
    public final Set e() {
        synchronized (this) {
            return new HashSet(this.b.A);
        }
    }
    
    public final boolean f() {
        return (boolean)cr.b(this.b.B, ek.r);
    }
}
