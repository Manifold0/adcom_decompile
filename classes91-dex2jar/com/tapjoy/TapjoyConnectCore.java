// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import com.tapjoy.internal.gf;
import com.tapjoy.internal.gq;
import com.tapjoy.internal.y;
import com.tapjoy.internal.ev;
import com.tapjoy.internal.fd;
import com.tapjoy.internal.er;
import android.net.Uri;
import android.net.ConnectivityManager;
import com.tapjoy.internal.ek;
import com.tapjoy.internal.dx;
import com.tapjoy.internal.ed;
import com.tapjoy.internal.ee;
import java.util.HashSet;
import com.tapjoy.internal.aq;
import com.tapjoy.internal.fh;
import java.util.Locale;
import java.util.HashMap;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.ct;
import org.w3c.dom.Document;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Iterator;
import android.content.pm.ApplicationInfo;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.Arrays;
import java.util.Vector;
import android.content.Context;
import java.util.Set;
import java.util.Map;
import java.util.Hashtable;
import android.content.pm.PackageManager;

public class TapjoyConnectCore
{
    private static float A = 0.0f;
    private static int B = 0;
    private static String C;
    private static String D;
    public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0f;
    private static String E;
    private static String F;
    private static String G;
    private static String H;
    private static String I;
    private static String J;
    private static String K;
    private static String L;
    private static String M;
    private static String N;
    private static String O;
    private static String P;
    private static float Q;
    private static boolean R;
    private static String S;
    private static String T;
    private static String U;
    private static String V;
    private static String W;
    private static long Z;
    protected static int a;
    private static Integer aA;
    private static Long aB;
    private static Long aC;
    private static Long aD;
    private static String aE;
    private static Integer aF;
    private static Double aG;
    private static Double aH;
    private static Long aI;
    private static Integer aJ;
    private static Integer aK;
    private static Integer aL;
    private static String aM;
    private static String aN;
    private static String aO;
    private static boolean ab;
    private static PackageManager ac;
    private static Hashtable ae;
    private static String af;
    private static Map ag;
    private static String ah;
    private static String ai;
    private static String aj;
    private static String ak;
    private static Integer al;
    private static String am;
    private static String an;
    private static Long ao;
    private static String ap;
    private static Integer aq;
    private static Integer ar;
    private static String as;
    private static String at;
    private static String au;
    private static String av;
    private static String aw;
    private static Set ax;
    private static Integer ay;
    private static Integer az;
    protected static int b;
    protected static String c;
    protected static boolean d;
    protected static String e;
    protected static String f;
    private static Context g;
    private static String h;
    private static TapjoyConnectCore i;
    private static TapjoyURLConnection j;
    private static TJConnectListener k;
    private static TJSetUserIDListener l;
    private static Vector m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static String r;
    private static String s;
    private static String t;
    private static String u;
    private static String v;
    private static String w;
    private static String x;
    private static String y;
    private static int z;
    private long X;
    private boolean Y;
    private boolean aa;
    private TapjoyGpsHelper ad;
    
    static {
        TapjoyConnectCore.g = null;
        TapjoyConnectCore.h = null;
        TapjoyConnectCore.i = null;
        TapjoyConnectCore.j = null;
        TapjoyConnectCore.k = null;
        TapjoyConnectCore.l = null;
        TapjoyConnectCore.m = new Vector((Collection<? extends E>)Arrays.asList(TapjoyConstants.dependencyClassNames));
        TapjoyConnectCore.n = "";
        TapjoyConnectCore.o = "";
        TapjoyConnectCore.p = "";
        TapjoyConnectCore.q = "";
        TapjoyConnectCore.r = "";
        TapjoyConnectCore.s = "";
        TapjoyConnectCore.t = "";
        TapjoyConnectCore.u = "";
        TapjoyConnectCore.v = "";
        TapjoyConnectCore.w = "";
        TapjoyConnectCore.x = "";
        TapjoyConnectCore.y = "";
        TapjoyConnectCore.z = 1;
        TapjoyConnectCore.A = 1.0f;
        TapjoyConnectCore.B = 1;
        TapjoyConnectCore.C = "";
        TapjoyConnectCore.D = "";
        TapjoyConnectCore.E = "";
        TapjoyConnectCore.F = "";
        TapjoyConnectCore.G = "";
        TapjoyConnectCore.H = "";
        TapjoyConnectCore.I = "";
        TapjoyConnectCore.J = "";
        TapjoyConnectCore.K = "";
        TapjoyConnectCore.L = "";
        TapjoyConnectCore.M = "";
        TapjoyConnectCore.N = "native";
        TapjoyConnectCore.O = "";
        TapjoyConnectCore.P = "";
        TapjoyConnectCore.Q = 1.0f;
        TapjoyConnectCore.R = false;
        TapjoyConnectCore.S = "";
        TapjoyConnectCore.T = "";
        TapjoyConnectCore.U = "";
        TapjoyConnectCore.V = "";
        TapjoyConnectCore.W = null;
        TapjoyConnectCore.Z = 0L;
        TapjoyConnectCore.a = 0;
        TapjoyConnectCore.b = 0;
        TapjoyConnectCore.c = "";
        TapjoyConnectCore.e = "";
        TapjoyConnectCore.f = "";
        TapjoyConnectCore.ae = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
        TapjoyConnectCore.af = "";
        TapjoyConnectCore.ag = new ConcurrentHashMap();
    }
    
    public TapjoyConnectCore(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: lconst_0       
        //     6: putfield        com/tapjoy/TapjoyConnectCore.X:J
        //     9: aload_0        
        //    10: iconst_0       
        //    11: putfield        com/tapjoy/TapjoyConnectCore.Y:Z
        //    14: aload_0        
        //    15: iconst_0       
        //    16: putfield        com/tapjoy/TapjoyConnectCore.aa:Z
        //    19: aload_1        
        //    20: putstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //    23: invokestatic    com/tapjoy/internal/fh.a:()Lcom/tapjoy/internal/fh;
        //    26: aload_1        
        //    27: invokevirtual   com/tapjoy/internal/fh.a:(Landroid/content/Context;)V
        //    30: invokestatic    com/tapjoy/internal/fd.a:()Lcom/tapjoy/internal/fd;
        //    33: aload_1        
        //    34: invokevirtual   com/tapjoy/internal/fd.a:(Landroid/content/Context;)V
        //    37: new             Lcom/tapjoy/TapjoyURLConnection;
        //    40: dup            
        //    41: invokespecial   com/tapjoy/TapjoyURLConnection.<init>:()V
        //    44: putstatic       com/tapjoy/TapjoyConnectCore.j:Lcom/tapjoy/TapjoyURLConnection;
        //    47: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //    50: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    53: putstatic       com/tapjoy/TapjoyConnectCore.ac:Landroid/content/pm/PackageManager;
        //    56: aload_0        
        //    57: new             Lcom/tapjoy/TapjoyGpsHelper;
        //    60: dup            
        //    61: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //    64: invokespecial   com/tapjoy/TapjoyGpsHelper.<init>:(Landroid/content/Context;)V
        //    67: putfield        com/tapjoy/TapjoyConnectCore.ad:Lcom/tapjoy/TapjoyGpsHelper;
        //    70: getstatic       com/tapjoy/TapjoyConnectCore.ae:Ljava/util/Hashtable;
        //    73: ifnonnull       86
        //    76: new             Ljava/util/Hashtable;
        //    79: dup            
        //    80: invokespecial   java/util/Hashtable.<init>:()V
        //    83: putstatic       com/tapjoy/TapjoyConnectCore.ae:Ljava/util/Hashtable;
        //    86: invokestatic    com/tapjoy/TapjoyConnectCore.i:()V
        //    89: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //    92: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    95: ldc_w           "raw/tapjoy_config"
        //    98: aconst_null    
        //    99: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   102: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   105: invokevirtual   android/content/res/Resources.getIdentifier:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
        //   108: istore_2       
        //   109: new             Ljava/util/Properties;
        //   112: dup            
        //   113: invokespecial   java/util/Properties.<init>:()V
        //   116: astore_1       
        //   117: aload_1        
        //   118: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   121: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //   124: iload_2        
        //   125: invokevirtual   android/content/res/Resources.openRawResource:(I)Ljava/io/InputStream;
        //   128: invokevirtual   java/util/Properties.load:(Ljava/io/InputStream;)V
        //   131: aload_1        
        //   132: invokestatic    com/tapjoy/TapjoyConnectCore.a:(Ljava/util/Properties;)V
        //   135: ldc_w           "unit_test_mode"
        //   138: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   141: invokestatic    com/tapjoy/internal/ct.c:(Ljava/lang/String;)Z
        //   144: ifeq            151
        //   147: aload_0        
        //   148: invokespecial   com/tapjoy/TapjoyConnectCore.j:()V
        //   151: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   154: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //   157: ldc_w           "android_id"
        //   160: invokestatic    android/provider/Settings$Secure.getString:(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
        //   163: astore_1       
        //   164: aload_1        
        //   165: putstatic       com/tapjoy/TapjoyConnectCore.n:Ljava/lang/String;
        //   168: aload_1        
        //   169: ifnull          181
        //   172: getstatic       com/tapjoy/TapjoyConnectCore.n:Ljava/lang/String;
        //   175: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   178: putstatic       com/tapjoy/TapjoyConnectCore.n:Ljava/lang/String;
        //   181: getstatic       com/tapjoy/TapjoyConnectCore.ac:Landroid/content/pm/PackageManager;
        //   184: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   187: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   190: iconst_0       
        //   191: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   194: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //   197: putstatic       com/tapjoy/TapjoyConnectCore.w:Ljava/lang/String;
        //   200: ldc_w           "android"
        //   203: putstatic       com/tapjoy/TapjoyConnectCore.t:Ljava/lang/String;
        //   206: ldc_w           "android"
        //   209: putstatic       com/tapjoy/TapjoyConnectCore.D:Ljava/lang/String;
        //   212: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //   215: putstatic       com/tapjoy/TapjoyConnectCore.r:Ljava/lang/String;
        //   218: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //   221: putstatic       com/tapjoy/TapjoyConnectCore.s:Ljava/lang/String;
        //   224: getstatic       android/os/Build$VERSION.RELEASE:Ljava/lang/String;
        //   227: putstatic       com/tapjoy/TapjoyConnectCore.u:Ljava/lang/String;
        //   230: ldc_w           "11.12.2"
        //   233: putstatic       com/tapjoy/TapjoyConnectCore.x:Ljava/lang/String;
        //   236: ldc_w           "1.0.11"
        //   239: putstatic       com/tapjoy/TapjoyConnectCore.y:Ljava/lang/String;
        //   242: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   245: iconst_3       
        //   246: if_icmple       281
        //   249: new             Lcom/tapjoy/TapjoyDisplayMetricsUtil;
        //   252: dup            
        //   253: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   256: invokespecial   com/tapjoy/TapjoyDisplayMetricsUtil.<init>:(Landroid/content/Context;)V
        //   259: astore_1       
        //   260: aload_1        
        //   261: invokevirtual   com/tapjoy/TapjoyDisplayMetricsUtil.getScreenDensityDPI:()I
        //   264: putstatic       com/tapjoy/TapjoyConnectCore.z:I
        //   267: aload_1        
        //   268: invokevirtual   com/tapjoy/TapjoyDisplayMetricsUtil.getScreenDensityScale:()F
        //   271: putstatic       com/tapjoy/TapjoyConnectCore.A:F
        //   274: aload_1        
        //   275: invokevirtual   com/tapjoy/TapjoyDisplayMetricsUtil.getScreenLayoutSize:()I
        //   278: putstatic       com/tapjoy/TapjoyConnectCore.B:I
        //   281: ldc_w           "android.permission.ACCESS_WIFI_STATE"
        //   284: invokestatic    com/tapjoy/TapjoyConnectCore.e:(Ljava/lang/String;)Z
        //   287: istore_3       
        //   288: iload_3        
        //   289: ifeq            954
        //   292: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   295: ldc_w           "wifi"
        //   298: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   301: checkcast       Landroid/net/wifi/WifiManager;
        //   304: astore_1       
        //   305: aload_1        
        //   306: ifnull          348
        //   309: aload_1        
        //   310: invokevirtual   android/net/wifi/WifiManager.getConnectionInfo:()Landroid/net/wifi/WifiInfo;
        //   313: astore_1       
        //   314: aload_1        
        //   315: ifnull          348
        //   318: aload_1        
        //   319: invokevirtual   android/net/wifi/WifiInfo.getMacAddress:()Ljava/lang/String;
        //   322: astore_1       
        //   323: aload_1        
        //   324: putstatic       com/tapjoy/TapjoyConnectCore.p:Ljava/lang/String;
        //   327: aload_1        
        //   328: ifnull          348
        //   331: getstatic       com/tapjoy/TapjoyConnectCore.p:Ljava/lang/String;
        //   334: ldc_w           ":"
        //   337: ldc             ""
        //   339: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   342: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   345: putstatic       com/tapjoy/TapjoyConnectCore.p:Ljava/lang/String;
        //   348: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   351: ldc_w           "phone"
        //   354: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   357: checkcast       Landroid/telephony/TelephonyManager;
        //   360: astore_1       
        //   361: aload_1        
        //   362: ifnull          422
        //   365: aload_1        
        //   366: invokevirtual   android/telephony/TelephonyManager.getNetworkOperatorName:()Ljava/lang/String;
        //   369: putstatic       com/tapjoy/TapjoyConnectCore.E:Ljava/lang/String;
        //   372: aload_1        
        //   373: invokevirtual   android/telephony/TelephonyManager.getNetworkCountryIso:()Ljava/lang/String;
        //   376: putstatic       com/tapjoy/TapjoyConnectCore.F:Ljava/lang/String;
        //   379: aload_1        
        //   380: invokevirtual   android/telephony/TelephonyManager.getNetworkOperator:()Ljava/lang/String;
        //   383: astore_1       
        //   384: aload_1        
        //   385: ifnull          422
        //   388: aload_1        
        //   389: invokevirtual   java/lang/String.length:()I
        //   392: iconst_5       
        //   393: if_icmpeq       405
        //   396: aload_1        
        //   397: invokevirtual   java/lang/String.length:()I
        //   400: bipush          6
        //   402: if_icmpne       422
        //   405: aload_1        
        //   406: iconst_0       
        //   407: iconst_3       
        //   408: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   411: putstatic       com/tapjoy/TapjoyConnectCore.G:Ljava/lang/String;
        //   414: aload_1        
        //   415: iconst_3       
        //   416: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   419: putstatic       com/tapjoy/TapjoyConnectCore.H:Ljava/lang/String;
        //   422: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   425: ldc_w           "tjcPrefrences"
        //   428: iconst_0       
        //   429: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //   432: astore_1       
        //   433: aload_1        
        //   434: ldc_w           "tapjoyInstallId"
        //   437: ldc             ""
        //   439: invokeinterface android/content/SharedPreferences.getString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   444: astore          4
        //   446: aload           4
        //   448: putstatic       com/tapjoy/TapjoyConnectCore.q:Ljava/lang/String;
        //   451: aload           4
        //   453: ifnull          467
        //   456: getstatic       com/tapjoy/TapjoyConnectCore.q:Ljava/lang/String;
        //   459: invokevirtual   java/lang/String.length:()I
        //   462: istore_2       
        //   463: iload_2        
        //   464: ifne            525
        //   467: new             Ljava/lang/StringBuilder;
        //   470: dup            
        //   471: invokespecial   java/lang/StringBuilder.<init>:()V
        //   474: invokestatic    java/util/UUID.randomUUID:()Ljava/util/UUID;
        //   477: invokevirtual   java/util/UUID.toString:()Ljava/lang/String;
        //   480: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   483: invokestatic    java/lang/System.currentTimeMillis:()J
        //   486: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   489: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   492: invokestatic    com/tapjoy/TapjoyUtil.SHA256:(Ljava/lang/String;)Ljava/lang/String;
        //   495: putstatic       com/tapjoy/TapjoyConnectCore.q:Ljava/lang/String;
        //   498: aload_1        
        //   499: invokeinterface android/content/SharedPreferences.edit:()Landroid/content/SharedPreferences$Editor;
        //   504: astore_1       
        //   505: aload_1        
        //   506: ldc_w           "tapjoyInstallId"
        //   509: getstatic       com/tapjoy/TapjoyConnectCore.q:Ljava/lang/String;
        //   512: invokeinterface android/content/SharedPreferences$Editor.putString:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
        //   517: pop            
        //   518: aload_1        
        //   519: invokeinterface android/content/SharedPreferences$Editor.commit:()Z
        //   524: pop            
        //   525: ldc_w           "TJC_OPTION_STORE_NAME"
        //   528: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   531: ifnull          602
        //   534: ldc_w           "TJC_OPTION_STORE_NAME"
        //   537: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   540: invokevirtual   java/lang/String.length:()I
        //   543: ifle            602
        //   546: ldc_w           "TJC_OPTION_STORE_NAME"
        //   549: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   552: putstatic       com/tapjoy/TapjoyConnectCore.K:Ljava/lang/String;
        //   555: new             Ljava/util/ArrayList;
        //   558: dup            
        //   559: getstatic       com/tapjoy/TapjoyConnectFlag.STORE_ARRAY:[Ljava/lang/String;
        //   562: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   565: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //   568: getstatic       com/tapjoy/TapjoyConnectCore.K:Ljava/lang/String;
        //   571: invokevirtual   java/util/ArrayList.contains:(Ljava/lang/Object;)Z
        //   574: ifne            602
        //   577: ldc_w           "TapjoyConnect"
        //   580: new             Ljava/lang/StringBuilder;
        //   583: dup            
        //   584: ldc_w           "Warning -- undefined STORE_NAME: "
        //   587: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   590: getstatic       com/tapjoy/TapjoyConnectCore.K:Ljava/lang/String;
        //   593: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   596: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   599: invokestatic    com/tapjoy/TapjoyLog.w:(Ljava/lang/String;Ljava/lang/String;)V
        //   602: getstatic       com/tapjoy/TapjoyConnectCore.K:Ljava/lang/String;
        //   605: astore_1       
        //   606: new             Landroid/content/Intent;
        //   609: dup            
        //   610: ldc_w           "android.intent.action.VIEW"
        //   613: invokespecial   android/content/Intent.<init>:(Ljava/lang/String;)V
        //   616: astore          4
        //   618: aload_1        
        //   619: invokevirtual   java/lang/String.length:()I
        //   622: ifgt            996
        //   625: aload           4
        //   627: ldc_w           "market://details"
        //   630: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //   633: invokevirtual   android/content/Intent.setData:(Landroid/net/Uri;)Landroid/content/Intent;
        //   636: pop            
        //   637: getstatic       com/tapjoy/TapjoyConnectCore.ac:Landroid/content/pm/PackageManager;
        //   640: aload           4
        //   642: iconst_0       
        //   643: invokevirtual   android/content/pm/PackageManager.queryIntentActivities:(Landroid/content/Intent;I)Ljava/util/List;
        //   646: invokeinterface java/util/List.size:()I
        //   651: ifle            1070
        //   654: iconst_1       
        //   655: istore_3       
        //   656: iload_3        
        //   657: putstatic       com/tapjoy/TapjoyConnectCore.R:Z
        //   660: invokestatic    com/tapjoy/TapjoyConnectCore.g:()V
        //   663: ldc_w           "TJC_OPTION_DISABLE_PERSISTENT_IDS"
        //   666: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   669: ifnull          693
        //   672: ldc_w           "TJC_OPTION_DISABLE_PERSISTENT_IDS"
        //   675: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   678: invokevirtual   java/lang/String.length:()I
        //   681: ifle            693
        //   684: ldc_w           "TJC_OPTION_DISABLE_PERSISTENT_IDS"
        //   687: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   690: putstatic       com/tapjoy/TapjoyConnectCore.f:Ljava/lang/String;
        //   693: ldc_w           "TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK"
        //   696: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   699: ifnull          723
        //   702: ldc_w           "TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK"
        //   705: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   708: invokevirtual   java/lang/String.length:()I
        //   711: ifle            723
        //   714: ldc_w           "TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK"
        //   717: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   720: putstatic       com/tapjoy/TapjoyConnectCore.e:Ljava/lang/String;
        //   723: ldc_w           "TJC_OPTION_USER_ID"
        //   726: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   729: ifnull          782
        //   732: ldc_w           "TJC_OPTION_USER_ID"
        //   735: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   738: invokevirtual   java/lang/String.length:()I
        //   741: ifle            782
        //   744: ldc_w           "TapjoyConnect"
        //   747: new             Ljava/lang/StringBuilder;
        //   750: dup            
        //   751: ldc_w           "Setting userID to: "
        //   754: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   757: ldc_w           "TJC_OPTION_USER_ID"
        //   760: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   763: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   766: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   769: invokestatic    com/tapjoy/TapjoyLog.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   772: ldc_w           "TJC_OPTION_USER_ID"
        //   775: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   778: aconst_null    
        //   779: invokestatic    com/tapjoy/TapjoyConnectCore.setUserID:(Ljava/lang/String;Lcom/tapjoy/TJSetUserIDListener;)V
        //   782: ldc_w           "TJC_OPTION_SERVICE_URL"
        //   785: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   788: invokestatic    com/tapjoy/TapjoyUtil.getRedirectDomain:(Ljava/lang/String;)Ljava/lang/String;
        //   791: putstatic       com/tapjoy/TapjoyConnectCore.P:Ljava/lang/String;
        //   794: getstatic       com/tapjoy/TapjoyConnectCore.ae:Ljava/util/Hashtable;
        //   797: ifnull          803
        //   800: invokestatic    com/tapjoy/TapjoyConnectCore.h:()V
        //   803: aload_0        
        //   804: invokevirtual   com/tapjoy/TapjoyConnectCore.callConnect:()V
        //   807: aload_0        
        //   808: iconst_1       
        //   809: putfield        com/tapjoy/TapjoyConnectCore.aa:Z
        //   812: return         
        //   813: astore_1       
        //   814: new             Lcom/tapjoy/TapjoyException;
        //   817: dup            
        //   818: aload_1        
        //   819: invokevirtual   android/content/pm/PackageManager$NameNotFoundException.getMessage:()Ljava/lang/String;
        //   822: invokespecial   com/tapjoy/TapjoyException.<init>:(Ljava/lang/String;)V
        //   825: athrow         
        //   826: astore_1       
        //   827: ldc_w           "TapjoyConnect"
        //   830: new             Lcom/tapjoy/TapjoyErrorMessage;
        //   833: dup            
        //   834: getstatic       com/tapjoy/TapjoyErrorMessage$ErrorType.INTEGRATION_ERROR:Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
        //   837: aload_1        
        //   838: invokevirtual   com/tapjoy/TapjoyIntegrationException.getMessage:()Ljava/lang/String;
        //   841: invokespecial   com/tapjoy/TapjoyErrorMessage.<init>:(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
        //   844: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
        //   847: invokestatic    com/tapjoy/TapjoyConnectCore.d:()V
        //   850: getstatic       com/tapjoy/internal/ev.b:Lcom/tapjoy/internal/ev$a;
        //   853: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   856: invokevirtual   com/tapjoy/internal/ev$a.notifyObservers:(Ljava/lang/Object;)V
        //   859: return         
        //   860: astore_1       
        //   861: ldc_w           "TapjoyConnect"
        //   864: new             Ljava/lang/StringBuilder;
        //   867: dup            
        //   868: ldc_w           "Error getting screen density/dimensions/layout: "
        //   871: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   874: aload_1        
        //   875: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   878: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   881: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   884: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   887: goto            281
        //   890: astore_1       
        //   891: ldc_w           "TapjoyConnect"
        //   894: new             Lcom/tapjoy/TapjoyErrorMessage;
        //   897: dup            
        //   898: getstatic       com/tapjoy/TapjoyErrorMessage$ErrorType.SDK_ERROR:Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
        //   901: aload_1        
        //   902: invokevirtual   com/tapjoy/TapjoyException.getMessage:()Ljava/lang/String;
        //   905: invokespecial   com/tapjoy/TapjoyErrorMessage.<init>:(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
        //   908: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
        //   911: invokestatic    com/tapjoy/TapjoyConnectCore.d:()V
        //   914: getstatic       com/tapjoy/internal/ev.b:Lcom/tapjoy/internal/ev$a;
        //   917: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   920: invokevirtual   com/tapjoy/internal/ev$a.notifyObservers:(Ljava/lang/Object;)V
        //   923: return         
        //   924: astore_1       
        //   925: ldc_w           "TapjoyConnect"
        //   928: new             Ljava/lang/StringBuilder;
        //   931: dup            
        //   932: ldc_w           "Error getting device mac address: "
        //   935: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   938: aload_1        
        //   939: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   942: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   945: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   948: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   951: goto            348
        //   954: ldc_w           "TapjoyConnect"
        //   957: ldc_w           "*** ignore macAddress"
        //   960: invokestatic    com/tapjoy/TapjoyLog.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   963: goto            348
        //   966: astore_1       
        //   967: ldc_w           "TapjoyConnect"
        //   970: new             Ljava/lang/StringBuilder;
        //   973: dup            
        //   974: ldc_w           "Error generating install id: "
        //   977: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   980: aload_1        
        //   981: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   984: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   987: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   990: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   993: goto            525
        //   996: aload_1        
        //   997: ldc_w           "gfan"
        //  1000: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1003: ifeq            1016
        //  1006: ldc_w           "com.mappn.gfan"
        //  1009: invokestatic    com/tapjoy/TapjoyConnectCore.d:(Ljava/lang/String;)Z
        //  1012: istore_3       
        //  1013: goto            656
        //  1016: aload_1        
        //  1017: ldc_w           "skt"
        //  1020: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1023: ifeq            1070
        //  1026: ldc_w           "com.skt.skaf.TSCINSTALL"
        //  1029: invokestatic    com/tapjoy/TapjoyConnectCore.d:(Ljava/lang/String;)Z
        //  1032: istore_3       
        //  1033: goto            656
        //  1036: astore_1       
        //  1037: ldc_w           "TapjoyConnect"
        //  1040: new             Ljava/lang/StringBuilder;
        //  1043: dup            
        //  1044: ldc_w           "Error trying to detect store intent on devicee: "
        //  1047: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //  1050: aload_1        
        //  1051: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //  1054: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1057: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1060: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Ljava/lang/String;)V
        //  1063: goto            660
        //  1066: astore_1       
        //  1067: goto            135
        //  1070: iconst_0       
        //  1071: istore_3       
        //  1072: goto            656
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  70     86     826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  70     86     890    924    Lcom/tapjoy/TapjoyException;
        //  86     117    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  86     117    890    924    Lcom/tapjoy/TapjoyException;
        //  117    135    1066   1070   Ljava/lang/Exception;
        //  117    135    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  117    135    890    924    Lcom/tapjoy/TapjoyException;
        //  135    151    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  135    151    890    924    Lcom/tapjoy/TapjoyException;
        //  151    168    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  151    168    890    924    Lcom/tapjoy/TapjoyException;
        //  172    181    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  172    181    890    924    Lcom/tapjoy/TapjoyException;
        //  181    200    813    826    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  181    200    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  181    200    890    924    Lcom/tapjoy/TapjoyException;
        //  200    242    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  200    242    890    924    Lcom/tapjoy/TapjoyException;
        //  242    281    860    890    Ljava/lang/Exception;
        //  242    281    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  242    281    890    924    Lcom/tapjoy/TapjoyException;
        //  281    288    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  281    288    890    924    Lcom/tapjoy/TapjoyException;
        //  292    305    924    954    Ljava/lang/Exception;
        //  292    305    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  292    305    890    924    Lcom/tapjoy/TapjoyException;
        //  309    314    924    954    Ljava/lang/Exception;
        //  309    314    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  309    314    890    924    Lcom/tapjoy/TapjoyException;
        //  318    327    924    954    Ljava/lang/Exception;
        //  318    327    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  318    327    890    924    Lcom/tapjoy/TapjoyException;
        //  331    348    924    954    Ljava/lang/Exception;
        //  331    348    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  331    348    890    924    Lcom/tapjoy/TapjoyException;
        //  348    361    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  348    361    890    924    Lcom/tapjoy/TapjoyException;
        //  365    384    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  365    384    890    924    Lcom/tapjoy/TapjoyException;
        //  388    405    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  388    405    890    924    Lcom/tapjoy/TapjoyException;
        //  405    422    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  405    422    890    924    Lcom/tapjoy/TapjoyException;
        //  422    451    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  422    451    890    924    Lcom/tapjoy/TapjoyException;
        //  456    463    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  456    463    890    924    Lcom/tapjoy/TapjoyException;
        //  467    525    966    996    Ljava/lang/Exception;
        //  467    525    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  467    525    890    924    Lcom/tapjoy/TapjoyException;
        //  525    602    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  525    602    890    924    Lcom/tapjoy/TapjoyException;
        //  602    654    1036   1066   Ljava/lang/Exception;
        //  602    654    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  602    654    890    924    Lcom/tapjoy/TapjoyException;
        //  656    660    1036   1066   Ljava/lang/Exception;
        //  656    660    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  656    660    890    924    Lcom/tapjoy/TapjoyException;
        //  660    693    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  660    693    890    924    Lcom/tapjoy/TapjoyException;
        //  693    723    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  693    723    890    924    Lcom/tapjoy/TapjoyException;
        //  723    782    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  723    782    890    924    Lcom/tapjoy/TapjoyException;
        //  782    803    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  782    803    890    924    Lcom/tapjoy/TapjoyException;
        //  803    812    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  803    812    890    924    Lcom/tapjoy/TapjoyException;
        //  814    826    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  814    826    890    924    Lcom/tapjoy/TapjoyException;
        //  861    887    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  861    887    890    924    Lcom/tapjoy/TapjoyException;
        //  925    951    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  925    951    890    924    Lcom/tapjoy/TapjoyException;
        //  954    963    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  954    963    890    924    Lcom/tapjoy/TapjoyException;
        //  967    993    826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  967    993    890    924    Lcom/tapjoy/TapjoyException;
        //  996    1013   1036   1066   Ljava/lang/Exception;
        //  996    1013   826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  996    1013   890    924    Lcom/tapjoy/TapjoyException;
        //  1016   1033   1036   1066   Ljava/lang/Exception;
        //  1016   1033   826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  1016   1033   890    924    Lcom/tapjoy/TapjoyException;
        //  1037   1063   826    860    Lcom/tapjoy/TapjoyIntegrationException;
        //  1037   1063   890    924    Lcom/tapjoy/TapjoyException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 436, Size: 436
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
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
    
    private static String a(final long n) {
        try {
            return TapjoyUtil.SHA256(TapjoyConnectCore.v + ":" + o() + ":" + n + ":" + TapjoyConnectCore.L);
        }
        catch (Exception ex) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing verifier value -- " + ex.toString()));
            return "";
        }
    }
    
    private static String a(final long n, String sha256) {
        try {
            sha256 = TapjoyUtil.SHA256(TapjoyConnectCore.v + ":" + o() + ":" + n + ":" + TapjoyConnectCore.L + ":" + sha256);
            return sha256;
        }
        catch (Exception ex) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing packageNamesVerifier -- " + ex.toString()));
            return "";
        }
    }
    
    private static void a(final String s, final String s2) {
        String string = null;
        Label_0055: {
            if (!s.equals("TJC_OPTION_SERVICE_URL")) {
                string = s2;
                if (!s.equals("TJC_OPTION_PLACEMENT_SERVICE_URL")) {
                    break Label_0055;
                }
            }
            string = s2;
            if (!s2.endsWith("/")) {
                string = s2 + "/";
            }
        }
        TapjoyConnectCore.ae.put(s, string);
    }
    
    private static void a(final List list) {
        synchronized (TapjoyConnectCore.class) {
            TapjoyConnectCore.af = "";
            for (final ApplicationInfo applicationInfo : TapjoyConnectCore.ac.getInstalledApplications(0)) {
                if ((applicationInfo.flags & 0x1) != 0x1 && list.contains(applicationInfo.packageName)) {
                    TapjoyLog.d("TapjoyConnect", "MATCH: installed packageName: " + applicationInfo.packageName);
                    if (TapjoyConnectCore.af.length() > 0) {
                        TapjoyConnectCore.af += ",";
                    }
                    TapjoyConnectCore.af += applicationInfo.packageName;
                }
            }
        }
    }
    // monitorexit(TapjoyConnectCore.class)
    
    private static void a(final Properties properties) {
        final Enumeration<String> keys = ((Hashtable<String, V>)properties).keys();
        while (keys.hasMoreElements()) {
            try {
                final String s = keys.nextElement();
                a(s, (String)properties.get(s));
            }
            catch (ClassCastException ex) {
                TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
            }
        }
    }
    
    static /* synthetic */ void a(final boolean b) {
        if (b) {
            TapjoyLog.i("TapjoyConnect", "Set userID is successful");
            if (TapjoyConnectCore.l != null) {
                TapjoyConnectCore.l.onSetUserIDSuccess();
            }
        }
        else {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Failed to set userID"));
            if (TapjoyConnectCore.l != null) {
                TapjoyConnectCore.l.onSetUserIDFailure("Failed to set userID");
            }
        }
    }
    
    static /* synthetic */ boolean a(String nodeTrimValue) {
        final Document buildDocument = TapjoyUtil.buildDocument(nodeTrimValue);
        if (buildDocument != null) {
            final String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("PackageNames"));
            if (nodeTrimValue2 != null && nodeTrimValue2.length() > 0) {
                final Vector<String> vector = new Vector<String>();
                int n = 0;
                while (true) {
                    final int index = nodeTrimValue2.indexOf(44, n);
                    if (index == -1) {
                        break;
                    }
                    TapjoyLog.d("TapjoyConnect", "parse: " + nodeTrimValue2.substring(n, index).trim());
                    vector.add(nodeTrimValue2.substring(n, index).trim());
                    n = index + 1;
                }
                TapjoyLog.d("TapjoyConnect", "parse: " + nodeTrimValue2.substring(n).trim());
                vector.add(nodeTrimValue2.substring(n).trim());
                a(vector);
            }
            nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Success"));
            if (nodeTrimValue == null || !nodeTrimValue.equals("true")) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean a(final String p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          7
        //     3: aload_0        
        //     4: invokestatic    com/tapjoy/internal/bs.b:(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
        //     7: astore          6
        //     9: aload           6
        //    11: astore          7
        //    13: aload           6
        //    15: invokevirtual   com/tapjoy/internal/bs.d:()Ljava/util/Map;
        //    18: astore          10
        //    20: aload           6
        //    22: astore          7
        //    24: aload           10
        //    26: ldc_w           "app_group_id"
        //    29: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    34: checkcast       Ljava/lang/String;
        //    37: invokestatic    com/tapjoy/internal/ct.a:(Ljava/lang/String;)Ljava/lang/String;
        //    40: astore          8
        //    42: aload           6
        //    44: astore          7
        //    46: aload           10
        //    48: ldc_w           "store"
        //    51: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    56: checkcast       Ljava/lang/String;
        //    59: invokestatic    com/tapjoy/internal/ct.a:(Ljava/lang/String;)Ljava/lang/String;
        //    62: astore          13
        //    64: aload           6
        //    66: astore          7
        //    68: aload           10
        //    70: ldc_w           "analytics_api_key"
        //    73: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    78: checkcast       Ljava/lang/String;
        //    81: invokestatic    com/tapjoy/internal/ct.a:(Ljava/lang/String;)Ljava/lang/String;
        //    84: astore          14
        //    86: aload           6
        //    88: astore          7
        //    90: aload           10
        //    92: ldc_w           "managed_device_id"
        //    95: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   100: checkcast       Ljava/lang/String;
        //   103: invokestatic    com/tapjoy/internal/ct.a:(Ljava/lang/String;)Ljava/lang/String;
        //   106: astore          15
        //   108: aload           6
        //   110: astore          7
        //   112: aload           10
        //   114: ldc_w           "package_names"
        //   117: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   122: checkcast       Ljava/lang/String;
        //   125: invokestatic    com/tapjoy/internal/ct.a:(Ljava/lang/String;)Ljava/lang/String;
        //   128: astore          12
        //   130: aload           6
        //   132: astore          7
        //   134: aload           10
        //   136: ldc_w           "cache_max_age"
        //   139: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   144: astore          11
        //   146: aload           6
        //   148: astore          7
        //   150: new             Lcom/tapjoy/internal/er;
        //   153: dup            
        //   154: aload           14
        //   156: invokespecial   com/tapjoy/internal/er.<init>:(Ljava/lang/String;)V
        //   159: astore          16
        //   161: aload           6
        //   163: astore          7
        //   165: aload           16
        //   167: getfield        com/tapjoy/internal/er.a:Lcom/tapjoy/internal/er$a;
        //   170: getstatic       com/tapjoy/internal/er$a.RPC_ANALYTICS:Lcom/tapjoy/internal/er$a;
        //   173: if_acmpeq       217
        //   176: aload           6
        //   178: astore          7
        //   180: new             Ljava/io/IOException;
        //   183: dup            
        //   184: ldc_w           "Invalid analytics_api_key"
        //   187: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   190: athrow         
        //   191: astore          7
        //   193: aload           6
        //   195: astore_0       
        //   196: aload           7
        //   198: astore          6
        //   200: ldc_w           "TapjoyConnect"
        //   203: aload           6
        //   205: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   208: invokestatic    com/tapjoy/TapjoyLog.v:(Ljava/lang/String;Ljava/lang/String;)V
        //   211: aload_0        
        //   212: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   215: iconst_0       
        //   216: ireturn        
        //   217: aload           6
        //   219: astore          7
        //   221: aload           16
        //   223: getfield        com/tapjoy/internal/er.b:Ljava/lang/String;
        //   226: astore          9
        //   228: aload           6
        //   230: astore          7
        //   232: aload           9
        //   234: bipush          13
        //   236: ldc_w           "-8000-8000-"
        //   239: iconst_0       
        //   240: bipush          11
        //   242: invokevirtual   java/lang/String.regionMatches:(ILjava/lang/String;II)Z
        //   245: ifeq            479
        //   248: aload           6
        //   250: astore          7
        //   252: new             Ljava/lang/StringBuffer;
        //   255: dup            
        //   256: invokespecial   java/lang/StringBuffer.<init>:()V
        //   259: aload           9
        //   261: iconst_0       
        //   262: bipush          8
        //   264: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   267: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   270: aload           9
        //   272: bipush          24
        //   274: bipush          30
        //   276: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   279: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   282: aload           9
        //   284: bipush          9
        //   286: bipush          13
        //   288: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   291: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   294: aload           9
        //   296: bipush          30
        //   298: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   301: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   304: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   307: astore          9
        //   309: aload           6
        //   311: astore          7
        //   313: aload           16
        //   315: getfield        com/tapjoy/internal/er.c:Ljava/lang/String;
        //   318: astore          16
        //   320: aload           8
        //   322: ifnonnull       827
        //   325: aload           9
        //   327: astore          8
        //   329: aload           6
        //   331: astore          7
        //   333: invokestatic    com/tapjoy/internal/gc.a:()Lcom/tapjoy/internal/gc;
        //   336: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   339: aload           14
        //   341: ldc_w           "11.12.2"
        //   344: ldc_w           "https://rpc.tapjoy.com/"
        //   347: aload           9
        //   349: aload           16
        //   351: invokevirtual   com/tapjoy/internal/gc.a:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   354: aload           6
        //   356: astore          7
        //   358: aload           8
        //   360: putstatic       com/tapjoy/TapjoyConnectCore.S:Ljava/lang/String;
        //   363: aload           6
        //   365: astore          7
        //   367: aload           13
        //   369: putstatic       com/tapjoy/TapjoyConnectCore.T:Ljava/lang/String;
        //   372: aload           6
        //   374: astore          7
        //   376: aload           14
        //   378: putstatic       com/tapjoy/TapjoyConnectCore.U:Ljava/lang/String;
        //   381: aload           6
        //   383: astore          7
        //   385: aload           15
        //   387: putstatic       com/tapjoy/TapjoyConnectCore.V:Ljava/lang/String;
        //   390: aload           6
        //   392: astore          7
        //   394: new             Ljava/util/ArrayList;
        //   397: dup            
        //   398: invokespecial   java/util/ArrayList.<init>:()V
        //   401: astore          8
        //   403: aload           12
        //   405: ifnull          517
        //   408: aload           6
        //   410: astore          7
        //   412: aload           12
        //   414: ldc_w           ","
        //   417: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   420: astore          9
        //   422: aload           6
        //   424: astore          7
        //   426: aload           9
        //   428: arraylength    
        //   429: istore_3       
        //   430: iconst_0       
        //   431: istore_2       
        //   432: iload_2        
        //   433: iload_3        
        //   434: if_icmpge       517
        //   437: aload           6
        //   439: astore          7
        //   441: aload           9
        //   443: iload_2        
        //   444: aaload         
        //   445: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   448: astore          12
        //   450: aload           6
        //   452: astore          7
        //   454: aload           12
        //   456: invokevirtual   java/lang/String.length:()I
        //   459: ifle            830
        //   462: aload           6
        //   464: astore          7
        //   466: aload           8
        //   468: aload           12
        //   470: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   475: pop            
        //   476: goto            830
        //   479: aload           6
        //   481: astore          7
        //   483: new             Ljava/lang/IllegalArgumentException;
        //   486: dup            
        //   487: ldc_w           "The given UUID did not come from 5Rocks."
        //   490: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   493: athrow         
        //   494: astore_0       
        //   495: aload           6
        //   497: astore          7
        //   499: ldc_w           "TapjoyConnect"
        //   502: aload_0        
        //   503: invokevirtual   java/lang/RuntimeException.getMessage:()Ljava/lang/String;
        //   506: invokestatic    com/tapjoy/TapjoyLog.v:(Ljava/lang/String;Ljava/lang/String;)V
        //   509: aload           6
        //   511: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   514: goto            215
        //   517: aload           6
        //   519: astore          7
        //   521: aload           8
        //   523: invokeinterface java/util/List.isEmpty:()Z
        //   528: ifne            540
        //   531: aload           6
        //   533: astore          7
        //   535: aload           8
        //   537: invokestatic    com/tapjoy/TapjoyConnectCore.a:(Ljava/util/List;)V
        //   540: aload           6
        //   542: astore          7
        //   544: aload           6
        //   546: invokevirtual   com/tapjoy/internal/bs.close:()V
        //   549: iload_1        
        //   550: ifne            658
        //   553: aload           11
        //   555: instanceof      Ljava/lang/String;
        //   558: istore_1       
        //   559: iload_1        
        //   560: ifeq            672
        //   563: aload           11
        //   565: checkcast       Ljava/lang/String;
        //   568: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   571: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   574: lstore          4
        //   576: lload           4
        //   578: lconst_0       
        //   579: lcmp           
        //   580: ifgt            703
        //   583: invokestatic    com/tapjoy/TapjoyAppSettings.getInstance:()Lcom/tapjoy/TapjoyAppSettings;
        //   586: invokevirtual   com/tapjoy/TapjoyAppSettings.removeConnectResult:()V
        //   589: invokestatic    com/tapjoy/internal/fd.a:()Lcom/tapjoy/internal/fd;
        //   592: astore_0       
        //   593: aload           10
        //   595: ldc_w           "configurations"
        //   598: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   603: astore          6
        //   605: aload           6
        //   607: instanceof      Ljava/util/Map;
        //   610: istore_1       
        //   611: iload_1        
        //   612: ifeq            726
        //   615: aload_0        
        //   616: getfield        com/tapjoy/internal/fd.a:Lcom/tapjoy/internal/fb;
        //   619: aload           6
        //   621: checkcast       Ljava/util/Map;
        //   624: invokevirtual   com/tapjoy/internal/fb.a:(Ljava/util/Map;)V
        //   627: aload           6
        //   629: invokestatic    com/tapjoy/internal/bm.a:(Ljava/lang/Object;)Ljava/lang/String;
        //   632: astore          6
        //   634: aload_0        
        //   635: invokevirtual   com/tapjoy/internal/fd.c:()Landroid/content/SharedPreferences;
        //   638: invokeinterface android/content/SharedPreferences.edit:()Landroid/content/SharedPreferences$Editor;
        //   643: ldc_w           "configurations"
        //   646: aload           6
        //   648: invokeinterface android/content/SharedPreferences$Editor.putString:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
        //   653: invokeinterface android/content/SharedPreferences$Editor.apply:()V
        //   658: aconst_null    
        //   659: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   662: iconst_1       
        //   663: ireturn        
        //   664: astore          6
        //   666: lconst_0       
        //   667: lstore          4
        //   669: goto            576
        //   672: aload           11
        //   674: instanceof      Ljava/lang/Number;
        //   677: istore_1       
        //   678: iload_1        
        //   679: ifeq            697
        //   682: aload           11
        //   684: checkcast       Ljava/lang/Number;
        //   687: invokevirtual   java/lang/Number.longValue:()J
        //   690: lstore          4
        //   692: goto            576
        //   695: astore          6
        //   697: lconst_0       
        //   698: lstore          4
        //   700: goto            576
        //   703: invokestatic    com/tapjoy/TapjoyAppSettings.getInstance:()Lcom/tapjoy/TapjoyAppSettings;
        //   706: aload_0        
        //   707: invokestatic    com/tapjoy/TapjoyConnectCore.p:()Ljava/lang/String;
        //   710: lload           4
        //   712: ldc2_w          1000
        //   715: lmul           
        //   716: invokestatic    com/tapjoy/internal/y.b:()J
        //   719: ladd           
        //   720: invokevirtual   com/tapjoy/TapjoyAppSettings.saveConnectResultAndParams:(Ljava/lang/String;Ljava/lang/String;J)V
        //   723: goto            589
        //   726: aload           6
        //   728: ifnonnull       658
        //   731: aload_0        
        //   732: getfield        com/tapjoy/internal/fd.a:Lcom/tapjoy/internal/fb;
        //   735: aconst_null    
        //   736: invokevirtual   com/tapjoy/internal/fb.a:(Ljava/util/Map;)V
        //   739: aload_0        
        //   740: invokevirtual   com/tapjoy/internal/fd.c:()Landroid/content/SharedPreferences;
        //   743: invokeinterface android/content/SharedPreferences.edit:()Landroid/content/SharedPreferences$Editor;
        //   748: ldc_w           "configurations"
        //   751: invokeinterface android/content/SharedPreferences$Editor.remove:(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
        //   756: invokeinterface android/content/SharedPreferences$Editor.apply:()V
        //   761: goto            658
        //   764: astore_0       
        //   765: aconst_null    
        //   766: astore          6
        //   768: goto            495
        //   771: astore_0       
        //   772: aconst_null    
        //   773: astore          6
        //   775: aload           6
        //   777: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   780: aload_0        
        //   781: athrow         
        //   782: astore_0       
        //   783: aload           7
        //   785: astore          6
        //   787: goto            775
        //   790: astore_0       
        //   791: aconst_null    
        //   792: astore          6
        //   794: goto            775
        //   797: astore          7
        //   799: aload_0        
        //   800: astore          6
        //   802: aload           7
        //   804: astore_0       
        //   805: goto            775
        //   808: astore_0       
        //   809: aconst_null    
        //   810: astore          6
        //   812: goto            495
        //   815: astore          6
        //   817: aload           7
        //   819: astore_0       
        //   820: goto            200
        //   823: astore_0       
        //   824: goto            658
        //   827: goto            329
        //   830: iload_2        
        //   831: iconst_1       
        //   832: iadd           
        //   833: istore_2       
        //   834: goto            432
        //   837: astore          6
        //   839: aconst_null    
        //   840: astore_0       
        //   841: goto            200
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  3      9      815    823    Ljava/io/IOException;
        //  3      9      808    815    Ljava/lang/RuntimeException;
        //  3      9      771    775    Any
        //  13     20     191    200    Ljava/io/IOException;
        //  13     20     494    495    Ljava/lang/RuntimeException;
        //  13     20     782    790    Any
        //  24     42     191    200    Ljava/io/IOException;
        //  24     42     494    495    Ljava/lang/RuntimeException;
        //  24     42     782    790    Any
        //  46     64     191    200    Ljava/io/IOException;
        //  46     64     494    495    Ljava/lang/RuntimeException;
        //  46     64     782    790    Any
        //  68     86     191    200    Ljava/io/IOException;
        //  68     86     494    495    Ljava/lang/RuntimeException;
        //  68     86     782    790    Any
        //  90     108    191    200    Ljava/io/IOException;
        //  90     108    494    495    Ljava/lang/RuntimeException;
        //  90     108    782    790    Any
        //  112    130    191    200    Ljava/io/IOException;
        //  112    130    494    495    Ljava/lang/RuntimeException;
        //  112    130    782    790    Any
        //  134    146    191    200    Ljava/io/IOException;
        //  134    146    494    495    Ljava/lang/RuntimeException;
        //  134    146    782    790    Any
        //  150    161    191    200    Ljava/io/IOException;
        //  150    161    494    495    Ljava/lang/RuntimeException;
        //  150    161    782    790    Any
        //  165    176    191    200    Ljava/io/IOException;
        //  165    176    494    495    Ljava/lang/RuntimeException;
        //  165    176    782    790    Any
        //  180    191    191    200    Ljava/io/IOException;
        //  180    191    494    495    Ljava/lang/RuntimeException;
        //  180    191    782    790    Any
        //  200    211    797    808    Any
        //  221    228    191    200    Ljava/io/IOException;
        //  221    228    494    495    Ljava/lang/RuntimeException;
        //  221    228    782    790    Any
        //  232    248    191    200    Ljava/io/IOException;
        //  232    248    494    495    Ljava/lang/RuntimeException;
        //  232    248    782    790    Any
        //  252    309    191    200    Ljava/io/IOException;
        //  252    309    494    495    Ljava/lang/RuntimeException;
        //  252    309    782    790    Any
        //  313    320    191    200    Ljava/io/IOException;
        //  313    320    494    495    Ljava/lang/RuntimeException;
        //  313    320    782    790    Any
        //  333    354    191    200    Ljava/io/IOException;
        //  333    354    494    495    Ljava/lang/RuntimeException;
        //  333    354    782    790    Any
        //  358    363    191    200    Ljava/io/IOException;
        //  358    363    494    495    Ljava/lang/RuntimeException;
        //  358    363    782    790    Any
        //  367    372    191    200    Ljava/io/IOException;
        //  367    372    494    495    Ljava/lang/RuntimeException;
        //  367    372    782    790    Any
        //  376    381    191    200    Ljava/io/IOException;
        //  376    381    494    495    Ljava/lang/RuntimeException;
        //  376    381    782    790    Any
        //  385    390    191    200    Ljava/io/IOException;
        //  385    390    494    495    Ljava/lang/RuntimeException;
        //  385    390    782    790    Any
        //  394    403    191    200    Ljava/io/IOException;
        //  394    403    494    495    Ljava/lang/RuntimeException;
        //  394    403    782    790    Any
        //  412    422    191    200    Ljava/io/IOException;
        //  412    422    494    495    Ljava/lang/RuntimeException;
        //  412    422    782    790    Any
        //  426    430    191    200    Ljava/io/IOException;
        //  426    430    494    495    Ljava/lang/RuntimeException;
        //  426    430    782    790    Any
        //  441    450    191    200    Ljava/io/IOException;
        //  441    450    494    495    Ljava/lang/RuntimeException;
        //  441    450    782    790    Any
        //  454    462    191    200    Ljava/io/IOException;
        //  454    462    494    495    Ljava/lang/RuntimeException;
        //  454    462    782    790    Any
        //  466    476    191    200    Ljava/io/IOException;
        //  466    476    494    495    Ljava/lang/RuntimeException;
        //  466    476    782    790    Any
        //  483    494    191    200    Ljava/io/IOException;
        //  483    494    494    495    Ljava/lang/RuntimeException;
        //  483    494    782    790    Any
        //  499    509    782    790    Any
        //  521    531    191    200    Ljava/io/IOException;
        //  521    531    494    495    Ljava/lang/RuntimeException;
        //  521    531    782    790    Any
        //  535    540    191    200    Ljava/io/IOException;
        //  535    540    494    495    Ljava/lang/RuntimeException;
        //  535    540    782    790    Any
        //  544    549    191    200    Ljava/io/IOException;
        //  544    549    494    495    Ljava/lang/RuntimeException;
        //  544    549    782    790    Any
        //  553    559    837    844    Ljava/io/IOException;
        //  553    559    764    771    Ljava/lang/RuntimeException;
        //  553    559    790    797    Any
        //  563    576    664    672    Ljava/lang/NumberFormatException;
        //  563    576    837    844    Ljava/io/IOException;
        //  563    576    764    771    Ljava/lang/RuntimeException;
        //  563    576    790    797    Any
        //  583    589    837    844    Ljava/io/IOException;
        //  583    589    764    771    Ljava/lang/RuntimeException;
        //  583    589    790    797    Any
        //  589    611    837    844    Ljava/io/IOException;
        //  589    611    764    771    Ljava/lang/RuntimeException;
        //  589    611    790    797    Any
        //  615    658    823    827    Ljava/lang/Exception;
        //  615    658    837    844    Ljava/io/IOException;
        //  615    658    764    771    Ljava/lang/RuntimeException;
        //  615    658    790    797    Any
        //  672    678    837    844    Ljava/io/IOException;
        //  672    678    764    771    Ljava/lang/RuntimeException;
        //  672    678    790    797    Any
        //  682    692    695    697    Ljava/lang/NumberFormatException;
        //  682    692    837    844    Ljava/io/IOException;
        //  682    692    764    771    Ljava/lang/RuntimeException;
        //  682    692    790    797    Any
        //  703    723    837    844    Ljava/io/IOException;
        //  703    723    764    771    Ljava/lang/RuntimeException;
        //  703    723    790    797    Any
        //  731    761    837    844    Ljava/io/IOException;
        //  731    761    764    771    Ljava/lang/RuntimeException;
        //  731    761    790    797    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
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
    
    private static boolean c(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    com/tapjoy/internal/bs.b:(Ljava/lang/String;)Lcom/tapjoy/internal/bs;
        //     4: astore_1       
        //     5: aload_1        
        //     6: astore_0       
        //     7: aload_1        
        //     8: invokevirtual   com/tapjoy/internal/bs.a:()Z
        //    11: ifeq            43
        //    14: aload_1        
        //    15: astore_0       
        //    16: aload_1        
        //    17: invokevirtual   com/tapjoy/internal/bs.s:()V
        //    20: aload_1        
        //    21: astore_0       
        //    22: ldc_w           "TapjoyConnect"
        //    25: ldc_w           "Successfully sent completed Pay-Per-Action to Tapjoy server."
        //    28: invokestatic    com/tapjoy/TapjoyLog.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    31: aload_1        
        //    32: astore_0       
        //    33: aload_1        
        //    34: invokevirtual   com/tapjoy/internal/bs.close:()V
        //    37: aconst_null    
        //    38: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //    41: iconst_1       
        //    42: ireturn        
        //    43: aload_1        
        //    44: astore_0       
        //    45: aload_1        
        //    46: invokevirtual   com/tapjoy/internal/bs.close:()V
        //    49: aconst_null    
        //    50: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //    53: ldc_w           "TapjoyConnect"
        //    56: new             Lcom/tapjoy/TapjoyErrorMessage;
        //    59: dup            
        //    60: getstatic       com/tapjoy/TapjoyErrorMessage$ErrorType.SDK_ERROR:Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
        //    63: ldc_w           "Completed Pay-Per-Action call failed."
        //    66: invokespecial   com/tapjoy/TapjoyErrorMessage.<init>:(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
        //    69: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
        //    72: iconst_0       
        //    73: ireturn        
        //    74: astore_2       
        //    75: aconst_null    
        //    76: astore_1       
        //    77: aload_1        
        //    78: astore_0       
        //    79: ldc_w           "TapjoyConnect"
        //    82: aload_2        
        //    83: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //    86: invokestatic    com/tapjoy/TapjoyLog.v:(Ljava/lang/String;Ljava/lang/String;)V
        //    89: aload_1        
        //    90: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //    93: goto            53
        //    96: astore_2       
        //    97: aconst_null    
        //    98: astore_1       
        //    99: aload_1        
        //   100: astore_0       
        //   101: ldc_w           "TapjoyConnect"
        //   104: aload_2        
        //   105: invokevirtual   java/lang/RuntimeException.getMessage:()Ljava/lang/String;
        //   108: invokestatic    com/tapjoy/TapjoyLog.v:(Ljava/lang/String;Ljava/lang/String;)V
        //   111: aload_1        
        //   112: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   115: goto            53
        //   118: astore_1       
        //   119: aconst_null    
        //   120: astore_0       
        //   121: aload_0        
        //   122: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   125: aload_1        
        //   126: athrow         
        //   127: astore_1       
        //   128: goto            121
        //   131: astore_2       
        //   132: goto            99
        //   135: astore_2       
        //   136: goto            77
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      5      74     77     Ljava/io/IOException;
        //  0      5      96     99     Ljava/lang/RuntimeException;
        //  0      5      118    121    Any
        //  7      14     135    139    Ljava/io/IOException;
        //  7      14     131    135    Ljava/lang/RuntimeException;
        //  7      14     127    131    Any
        //  16     20     135    139    Ljava/io/IOException;
        //  16     20     131    135    Ljava/lang/RuntimeException;
        //  16     20     127    131    Any
        //  22     31     135    139    Ljava/io/IOException;
        //  22     31     131    135    Ljava/lang/RuntimeException;
        //  22     31     127    131    Any
        //  33     37     135    139    Ljava/io/IOException;
        //  33     37     131    135    Ljava/lang/RuntimeException;
        //  33     37     127    131    Any
        //  45     49     135    139    Ljava/io/IOException;
        //  45     49     131    135    Ljava/lang/RuntimeException;
        //  45     49     127    131    Any
        //  79     89     127    131    Any
        //  101    111    127    131    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
    
    private static void d() {
        if (!ct.c(TapjoyConnectCore.M)) {
            gc.a().a(TapjoyConnectCore.g, TapjoyConnectCore.h, "11.12.2", "https://rpc.tapjoy.com/", TapjoyConnectCore.M, TapjoyConnectCore.L);
        }
        if (TapjoyConnectCore.k != null) {
            TapjoyConnectCore.k.onConnectFailure();
        }
    }
    
    private static boolean d(final String s) {
        final Iterator<ApplicationInfo> iterator = TapjoyConnectCore.ac.getInstalledApplications(0).iterator();
        while (iterator.hasNext()) {
            if (iterator.next().packageName.startsWith(s)) {
                return true;
            }
        }
        return false;
    }
    
    private static Map e() {
        final HashMap hashMap = new HashMap();
        final HashMap hashMap2 = new HashMap();
        final HashMap hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, "plugin", TapjoyConnectCore.N, true);
        TapjoyUtil.safePut(hashMap3, "sdk_type", TapjoyConnectCore.O, true);
        TapjoyUtil.safePut(hashMap3, "app_id", TapjoyConnectCore.v, true);
        TapjoyUtil.safePut(hashMap3, "library_version", TapjoyConnectCore.x, true);
        TapjoyUtil.safePut(hashMap3, "library_revision", "0859594bd", true);
        TapjoyUtil.safePut(hashMap3, "bridge_version", TapjoyConnectCore.y, true);
        TapjoyUtil.safePut(hashMap3, "app_version", TapjoyConnectCore.w, true);
        hashMap2.putAll(hashMap3);
        final HashMap hashMap4 = new HashMap();
        TapjoyUtil.safePut(hashMap4, "device_name", TapjoyConnectCore.r, true);
        TapjoyUtil.safePut(hashMap4, "platform", TapjoyConnectCore.D, true);
        TapjoyUtil.safePut(hashMap4, "os_version", TapjoyConnectCore.u, true);
        TapjoyUtil.safePut(hashMap4, "device_manufacturer", TapjoyConnectCore.s, true);
        TapjoyUtil.safePut(hashMap4, "device_type", TapjoyConnectCore.t, true);
        TapjoyUtil.safePut(hashMap4, "screen_layout_size", new StringBuilder().append(TapjoyConnectCore.B).toString(), true);
        TapjoyUtil.safePut(hashMap4, "store_name", TapjoyConnectCore.K, true);
        TapjoyUtil.safePut(hashMap4, "store_view", String.valueOf(TapjoyConnectCore.R), true);
        TapjoyUtil.safePut(hashMap4, "carrier_name", TapjoyConnectCore.E, true);
        TapjoyUtil.safePut(hashMap4, "carrier_country_code", TapjoyConnectCore.F, true);
        TapjoyUtil.safePut(hashMap4, "mobile_network_code", TapjoyConnectCore.H, true);
        TapjoyUtil.safePut(hashMap4, "mobile_country_code", TapjoyConnectCore.G, true);
        TapjoyUtil.safePut(hashMap4, "country_code", Locale.getDefault().getCountry(), true);
        TapjoyUtil.safePut(hashMap4, "language_code", Locale.getDefault().getLanguage(), true);
        TapjoyUtil.safePut(hashMap4, "connection_type", TapjoyConnectCore.I = getConnectionType(), true);
        TapjoyUtil.safePut(hashMap4, "connection_subtype", TapjoyConnectCore.J = getConnectionSubType(), true);
        TapjoyUtil.safePut(hashMap4, "screen_density", new StringBuilder().append(TapjoyConnectCore.z).toString(), true);
        hashMap2.putAll(hashMap4);
        final HashMap hashMap5 = new HashMap();
        if (l()) {
            TapjoyUtil.safePut(hashMap5, "advertising_id", TapjoyConnectCore.c, true);
            TapjoyUtil.safePut(hashMap5, "ad_tracking_enabled", String.valueOf(TapjoyConnectCore.d), true);
        }
        if (!m()) {
            TapjoyUtil.safePut(hashMap5, "android_id", TapjoyConnectCore.n, true);
            TapjoyUtil.safePut(hashMap5, "mac_address", TapjoyConnectCore.p, true);
        }
        TapjoyUtil.safePut(hashMap5, "install_id", TapjoyConnectCore.q, true);
        TapjoyUtil.safePut(hashMap5, "publisher_user_id", TapjoyConnectCore.C, true);
        TapjoyUtil.safePut(hashMap5, "ad_id_check_disabled", TapjoyConnectCore.e, true);
        TapjoyUtil.safePut(hashMap5, "persistent_ids_disabled", TapjoyConnectCore.f, true);
        if (TapjoyConnectCore.a != 0) {
            TapjoyUtil.safePut(hashMap5, "packaged_gps_version", Integer.toString(TapjoyConnectCore.a), true);
        }
        if (TapjoyConnectCore.b != 0) {
            TapjoyUtil.safePut(hashMap5, "device_gps_version", Integer.toString(TapjoyConnectCore.b), true);
        }
        if (TapjoyConnectCore.o == null || TapjoyConnectCore.o.length() == 0 || System.currentTimeMillis() - TapjoyConnectCore.Z > 1800000L) {
            TapjoyConnectCore.o = n();
        }
        else {
            TapjoyConnectCore.Z = System.currentTimeMillis();
        }
        TapjoyUtil.safePut(hashMap5, "session_id", TapjoyConnectCore.o, true);
        hashMap2.putAll(hashMap5);
        final HashMap hashMap6 = new HashMap();
        TapjoyUtil.safePut(hashMap6, "app_group_id", TapjoyConnectCore.S, true);
        TapjoyUtil.safePut(hashMap6, "store", TapjoyConnectCore.T, true);
        TapjoyUtil.safePut(hashMap6, "analytics_api_key", TapjoyConnectCore.U, true);
        TapjoyUtil.safePut(hashMap6, "managed_device_id", TapjoyConnectCore.V, true);
        hashMap2.putAll(hashMap6);
        final fh a = fh.a();
        final HashMap hashMap7 = new HashMap();
        if (a.a != null) {
            String s;
            if (a.a) {
                s = "1";
            }
            else {
                s = "0";
            }
            TapjoyUtil.safePut(hashMap7, "gdpr", s, true);
        }
        if (!com.tapjoy.internal.aq.a(a.b)) {
            TapjoyUtil.safePut(hashMap7, "cgdpr", a.b, true);
        }
        hashMap2.putAll(hashMap7);
        if (TapjoyCache.getInstance() != null && TapjoyCache.getInstance().getCachedOfferIDs() != null && TapjoyCache.getInstance().getCachedOfferIDs().length() > 0) {
            TapjoyUtil.safePut(hashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
        }
        TapjoyUtil.safePut(hashMap2, "display_multiplier", Float.toString(TapjoyConnectCore.Q), true);
        hashMap.putAll(hashMap2);
        final HashMap hashMap8 = new HashMap();
        g();
        final HashMap hashMap9 = new HashMap();
        TapjoyUtil.safePut(hashMap9, "analytics_id", TapjoyConnectCore.ah, true);
        TapjoyUtil.safePut(hashMap9, "pkg_id", TapjoyConnectCore.ai, true);
        TapjoyUtil.safePut(hashMap9, "pkg_sign", TapjoyConnectCore.aj, true);
        TapjoyUtil.safePut(hashMap9, "display_d", TapjoyConnectCore.aJ);
        TapjoyUtil.safePut(hashMap9, "display_w", TapjoyConnectCore.aK);
        TapjoyUtil.safePut(hashMap9, "display_h", TapjoyConnectCore.aL);
        TapjoyUtil.safePut(hashMap9, "country_sim", TapjoyConnectCore.aM, true);
        TapjoyUtil.safePut(hashMap9, "timezone", TapjoyConnectCore.aN, true);
        hashMap8.putAll(hashMap9);
        final HashMap hashMap10 = new HashMap();
        TapjoyUtil.safePut(hashMap10, "pkg_ver", TapjoyConnectCore.ak, true);
        TapjoyUtil.safePut(hashMap10, "pkg_rev", TapjoyConnectCore.al);
        TapjoyUtil.safePut(hashMap10, "pkg_data_ver", TapjoyConnectCore.am, true);
        TapjoyUtil.safePut(hashMap10, "installer", TapjoyConnectCore.an, true);
        if (ct.c(TapjoyConnectCore.K)) {
            TapjoyUtil.safePut(hashMap10, "store_name", TapjoyConnectCore.aO, true);
        }
        hashMap8.putAll(hashMap10);
        hashMap8.putAll(f());
        hashMap.putAll(hashMap8);
        return hashMap;
    }
    
    private static boolean e(final String s) {
        return TapjoyConnectCore.ac.checkPermission(s, TapjoyConnectCore.g.getPackageName()) == 0;
    }
    
    private static Map f() {
        final HashMap hashMap = new HashMap();
        TapjoyUtil.safePut(hashMap, "installed", TapjoyConnectCore.ao);
        TapjoyUtil.safePut(hashMap, "referrer", TapjoyConnectCore.ap, true);
        TapjoyUtil.safePut(hashMap, "user_level", TapjoyConnectCore.aq);
        TapjoyUtil.safePut(hashMap, "friend_count", TapjoyConnectCore.ar);
        TapjoyUtil.safePut(hashMap, "uv1", TapjoyConnectCore.as, true);
        TapjoyUtil.safePut(hashMap, "uv2", TapjoyConnectCore.at, true);
        TapjoyUtil.safePut(hashMap, "uv3", TapjoyConnectCore.au, true);
        TapjoyUtil.safePut(hashMap, "uv4", TapjoyConnectCore.av, true);
        TapjoyUtil.safePut(hashMap, "uv5", TapjoyConnectCore.aw, true);
        final Iterator<String> iterator = TapjoyConnectCore.ax.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            TapjoyUtil.safePut(hashMap, "user_tags[" + n + "]", iterator.next(), true);
            ++n;
        }
        TapjoyUtil.safePut(hashMap, "fq7", TapjoyConnectCore.ay);
        TapjoyUtil.safePut(hashMap, "fq30", TapjoyConnectCore.az);
        TapjoyUtil.safePut(hashMap, "session_total_count", TapjoyConnectCore.aA);
        TapjoyUtil.safePut(hashMap, "session_total_length", TapjoyConnectCore.aB);
        TapjoyUtil.safePut(hashMap, "session_last_at", TapjoyConnectCore.aC);
        TapjoyUtil.safePut(hashMap, "session_last_length", TapjoyConnectCore.aD);
        TapjoyUtil.safePut(hashMap, "purchase_currency", TapjoyConnectCore.aE, true);
        TapjoyUtil.safePut(hashMap, "purchase_total_count", TapjoyConnectCore.aF);
        TapjoyUtil.safePut(hashMap, "purchase_total_price", TapjoyConnectCore.aG);
        TapjoyUtil.safePut(hashMap, "purchase_last_price", TapjoyConnectCore.aH);
        TapjoyUtil.safePut(hashMap, "purchase_last_at", TapjoyConnectCore.aI);
        return hashMap;
    }
    
    private static void g() {
        final ee a = gc.a(TapjoyConnectCore.g).a(true);
        final ed d = a.d;
        TapjoyConnectCore.ah = d.h;
        TapjoyConnectCore.ai = d.r;
        TapjoyConnectCore.aj = d.s;
        TapjoyConnectCore.aJ = d.m;
        TapjoyConnectCore.aK = d.n;
        TapjoyConnectCore.aL = d.o;
        TapjoyConnectCore.aM = d.u;
        TapjoyConnectCore.aN = d.q;
        final dx e = a.e;
        TapjoyConnectCore.ak = e.e;
        TapjoyConnectCore.al = e.f;
        TapjoyConnectCore.am = e.g;
        TapjoyConnectCore.an = e.h;
        TapjoyConnectCore.aO = e.i;
        final ek f = a.f;
        TapjoyConnectCore.ao = f.s;
        TapjoyConnectCore.ap = f.t;
        TapjoyConnectCore.aq = f.J;
        TapjoyConnectCore.ar = f.K;
        TapjoyConnectCore.as = f.L;
        TapjoyConnectCore.at = f.M;
        TapjoyConnectCore.au = f.N;
        TapjoyConnectCore.av = f.O;
        TapjoyConnectCore.aw = f.P;
        TapjoyConnectCore.ax = new HashSet(f.Q);
        TapjoyConnectCore.ay = f.u;
        TapjoyConnectCore.az = f.v;
        TapjoyConnectCore.aA = f.x;
        TapjoyConnectCore.aB = f.y;
        TapjoyConnectCore.aC = f.z;
        TapjoyConnectCore.aD = f.A;
        TapjoyConnectCore.aE = f.B;
        TapjoyConnectCore.aF = f.C;
        TapjoyConnectCore.aG = f.D;
        TapjoyConnectCore.aH = f.F;
        TapjoyConnectCore.aI = f.E;
    }
    
    public static String getAndroidID() {
        return TapjoyConnectCore.n;
    }
    
    public static String getAppID() {
        return TapjoyConnectCore.v;
    }
    
    public static String getAwardCurrencyVerifier(final long n, final int n2, String sha256) {
        try {
            sha256 = TapjoyUtil.SHA256(TapjoyConnectCore.v + ":" + o() + ":" + n + ":" + TapjoyConnectCore.L + ":" + n2 + ":" + sha256);
            return sha256;
        }
        catch (Exception ex) {
            TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error in computing awardCurrencyVerifier -- " + ex.toString()));
            return "";
        }
    }
    
    public static String getCarrierName() {
        return TapjoyConnectCore.E;
    }
    
    public static String getConnectFlagValue(final String s) {
        String string = "";
        if (TapjoyConnectCore.ae != null) {
            string = string;
            if (TapjoyConnectCore.ae.get(s) != null) {
                string = TapjoyConnectCore.ae.get(s).toString();
            }
        }
        return string;
    }
    
    public static String getConnectURL() {
        return "https://connect.tapjoy.com/";
    }
    
    public static String getConnectionSubType() {
        Object subtypeName;
        try {
            subtypeName = TapjoyConnectCore.g.getSystemService("connectivity");
            if (subtypeName != null) {
                subtypeName = ((ConnectivityManager)subtypeName).getActiveNetworkInfo().getSubtypeName();
                final String s = "TapjoyConnect";
                final String s2 = "connection_sub_type: ";
                final StringBuilder sb = new StringBuilder(s2);
                final ConnectivityManager connectivityManager = (ConnectivityManager)subtypeName;
                final StringBuilder sb2 = sb.append((String)connectivityManager);
                final String s3 = sb2.toString();
                TapjoyLog.d(s, s3);
                final ConnectivityManager connectivityManager2 = (ConnectivityManager)subtypeName;
                return (String)connectivityManager2;
            }
            return "";
        }
        catch (Exception ex) {
            subtypeName = "";
        }
        while (true) {
            try {
                final String s = "TapjoyConnect";
                final String s2 = "connection_sub_type: ";
                final StringBuilder sb = new StringBuilder(s2);
                final ConnectivityManager connectivityManager = (ConnectivityManager)subtypeName;
                final StringBuilder sb2 = sb.append((String)connectivityManager);
                final String s3 = sb2.toString();
                TapjoyLog.d(s, s3);
                final ConnectivityManager connectivityManager2 = (ConnectivityManager)subtypeName;
                return (String)connectivityManager2;
                final Exception ex;
                TapjoyLog.e("TapjoyConnect", "getConnectionSubType error: " + ex.toString());
                return (String)subtypeName;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
        return "";
    }
    
    public static String getConnectionType() {
        String s3 = null;
    Label_0176:
        while (true) {
            String s2;
            final String s = s2 = "";
            while (true) {
                try {
                    final ConnectivityManager connectivityManager = (ConnectivityManager)TapjoyConnectCore.g.getSystemService("connectivity");
                    s3 = s;
                    if (connectivityManager == null) {
                        break;
                    }
                    s2 = s;
                    s3 = s;
                    if (connectivityManager.getActiveNetworkInfo() == null) {
                        break;
                    }
                    s2 = s;
                    switch (connectivityManager.getActiveNetworkInfo().getType()) {
                        case 1:
                        case 6: {
                            final String s4 = "wifi";
                            s2 = s4;
                            TapjoyLog.d("TapjoyConnect", "connectivity: " + connectivityManager.getActiveNetworkInfo().getType());
                            s2 = s4;
                            TapjoyLog.d("TapjoyConnect", "connection_type: " + s4);
                            s3 = s4;
                            break Label_0176;
                        }
                    }
                }
                catch (Exception ex) {
                    TapjoyLog.e("TapjoyConnect", "getConnectionType error: " + ex.toString());
                    return s2;
                }
                final String s4 = "mobile";
                continue;
            }
        }
        return s3;
    }
    
    public static Context getContext() {
        return TapjoyConnectCore.g;
    }
    
    public static float getDeviceScreenDensityScale() {
        return TapjoyConnectCore.A;
    }
    
    public static Map getGenericURLParams() {
        final Map e = e();
        TapjoyUtil.safePut(e, "app_id", TapjoyConnectCore.v, true);
        return e;
    }
    
    public static String getHostURL() {
        return getConnectFlagValue("TJC_OPTION_SERVICE_URL");
    }
    
    public static TapjoyConnectCore getInstance() {
        return TapjoyConnectCore.i;
    }
    
    public static String getMacAddress() {
        return TapjoyConnectCore.p;
    }
    
    public static String getPlacementURL() {
        return getConnectFlagValue("TJC_OPTION_PLACEMENT_SERVICE_URL");
    }
    
    public static String getRedirectDomain() {
        return TapjoyConnectCore.P;
    }
    
    public static String getSecretKey() {
        return TapjoyConnectCore.L;
    }
    
    public static String getSha1MacAddress() {
        try {
            return TapjoyUtil.SHA1(TapjoyConnectCore.p);
        }
        catch (Exception ex) {
            TapjoyLog.e("TapjoyConnect", "Error generating sha1 of macAddress: " + ex.toString());
            return null;
        }
    }
    
    public static String getSupportURL(final String s) {
        String v = s;
        if (s == null) {
            v = TapjoyConnectCore.v;
        }
        return getHostURL() + "support_requests/new?currency_id=" + v + "&app_id=" + TapjoyConnectCore.v + "&udid=" + TapjoyConnectCore.V + "&language_code=" + Locale.getDefault().getLanguage();
    }
    
    public static Map getTimeStampAndVerifierParams() {
        final long n = System.currentTimeMillis() / 1000L;
        final String a = a(n);
        final HashMap hashMap = new HashMap();
        TapjoyUtil.safePut(hashMap, "timestamp", String.valueOf(n), true);
        TapjoyUtil.safePut(hashMap, "verifier", a, true);
        return hashMap;
    }
    
    public static Map getURLParams() {
        final Map genericURLParams = getGenericURLParams();
        genericURLParams.putAll(getTimeStampAndVerifierParams());
        return genericURLParams;
    }
    
    public static String getUserID() {
        return TapjoyConnectCore.C;
    }
    
    private static void h() {
        TapjoyLog.i("TapjoyConnect", "Connect Flags:");
        TapjoyLog.i("TapjoyConnect", "--------------------");
        for (final Map.Entry<String, V> entry : TapjoyConnectCore.ae.entrySet()) {
            TapjoyLog.i("TapjoyConnect", "key: " + entry.getKey() + ", value: " + Uri.encode(entry.getValue().toString()));
        }
        TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue("TJC_OPTION_SERVICE_URL") + "]");
        TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + TapjoyConnectCore.P + "]");
        TapjoyLog.i("TapjoyConnect", "--------------------");
    }
    
    private static void i() {
        while (true) {
            while (true) {
                int n;
                try {
                    if (TapjoyConnectCore.ac == null) {
                        break;
                    }
                    final ApplicationInfo applicationInfo = TapjoyConnectCore.ac.getApplicationInfo(TapjoyConnectCore.g.getPackageName(), 128);
                    if (applicationInfo == null || applicationInfo.metaData == null) {
                        TapjoyLog.d("TapjoyConnect", "No metadata present.");
                        return;
                    }
                    final String[] flag_ARRAY = TapjoyConnectFlag.FLAG_ARRAY;
                    final int length = flag_ARRAY.length;
                    n = 0;
                    if (n >= length) {
                        TapjoyLog.d("TapjoyConnect", "Metadata successfully loaded");
                        return;
                    }
                    final String s = flag_ARRAY[n];
                    final String string = applicationInfo.metaData.getString("tapjoy." + s);
                    if (string != null) {
                        TapjoyLog.d("TapjoyConnect", "Found manifest flag: " + s + ", " + string);
                        a(s, string);
                    }
                }
                catch (Exception ex) {
                    TapjoyLog.e("TapjoyConnect", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error reading manifest meta-data -- " + ex.toString()));
                    return;
                }
                ++n;
                continue;
            }
        }
    }
    
    public static boolean isConnected() {
        return TapjoyConnectCore.ab;
    }
    
    public static boolean isFullScreenViewOpen() {
        final Iterator<Integer> iterator = TapjoyConnectCore.ag.values().iterator();
        while (iterator.hasNext()) {
            switch (iterator.next()) {
                default: {
                    continue;
                }
                case 1:
                case 2: {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isUnitTestMode() {
        return getConnectFlagValue("unit_test_mode") == "true";
    }
    
    public static boolean isViewOpen() {
        TapjoyLog.d("TapjoyConnect", "isViewOpen: " + TapjoyConnectCore.ag.size());
        return !TapjoyConnectCore.ag.isEmpty();
    }
    
    private void j() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //     6: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //     9: iconst_1       
        //    10: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    13: getfield        android/content/pm/PackageInfo.activities:[Landroid/content/pm/ActivityInfo;
        //    16: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //    19: astore_2       
        //    20: aload_2        
        //    21: ifnull          429
        //    24: aload_2        
        //    25: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    30: astore_2       
        //    31: aload_2        
        //    32: invokeinterface java/util/Iterator.hasNext:()Z
        //    37: ifeq            429
        //    40: aload_2        
        //    41: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    46: checkcast       Landroid/content/pm/ActivityInfo;
        //    49: astore_3       
        //    50: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //    53: aload_3        
        //    54: getfield        android/content/pm/ActivityInfo.name:Ljava/lang/String;
        //    57: invokevirtual   java/util/Vector.contains:(Ljava/lang/Object;)Z
        //    60: ifeq            31
        //    63: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //    66: aload_3        
        //    67: getfield        android/content/pm/ActivityInfo.name:Ljava/lang/String;
        //    70: invokevirtual   java/util/Vector.indexOf:(Ljava/lang/Object;)I
        //    73: istore_1       
        //    74: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //    77: iload_1        
        //    78: invokevirtual   java/util/Vector.get:(I)Ljava/lang/Object;
        //    81: checkcast       Ljava/lang/String;
        //    84: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //    87: pop            
        //    88: new             Ljava/util/Vector;
        //    91: dup            
        //    92: invokespecial   java/util/Vector.<init>:()V
        //    95: astore          4
        //    97: aload_3        
        //    98: getfield        android/content/pm/ActivityInfo.configChanges:I
        //   101: sipush          128
        //   104: iand           
        //   105: sipush          128
        //   108: if_icmpeq       120
        //   111: aload           4
        //   113: ldc_w           "orientation"
        //   116: invokevirtual   java/util/Vector.add:(Ljava/lang/Object;)Z
        //   119: pop            
        //   120: aload_3        
        //   121: getfield        android/content/pm/ActivityInfo.configChanges:I
        //   124: bipush          32
        //   126: iand           
        //   127: bipush          32
        //   129: if_icmpeq       141
        //   132: aload           4
        //   134: ldc_w           "keyboardHidden"
        //   137: invokevirtual   java/util/Vector.add:(Ljava/lang/Object;)Z
        //   140: pop            
        //   141: aload           4
        //   143: invokevirtual   java/util/Vector.size:()I
        //   146: ifeq            295
        //   149: aload           4
        //   151: invokevirtual   java/util/Vector.size:()I
        //   154: iconst_1       
        //   155: if_icmpne       250
        //   158: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   161: dup            
        //   162: new             Ljava/lang/StringBuilder;
        //   165: dup            
        //   166: invokespecial   java/lang/StringBuilder.<init>:()V
        //   169: aload           4
        //   171: invokevirtual   java/util/Vector.toString:()Ljava/lang/String;
        //   174: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   177: ldc_w           " property is not specified in manifest configChanges for "
        //   180: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   186: iload_1        
        //   187: invokevirtual   java/util/Vector.get:(I)Ljava/lang/Object;
        //   190: checkcast       Ljava/lang/String;
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   199: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   202: athrow         
        //   203: astore_2       
        //   204: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   207: dup            
        //   208: new             Ljava/lang/StringBuilder;
        //   211: dup            
        //   212: ldc_w           "[ClassNotFoundException] Could not find dependency class "
        //   215: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   218: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   221: iload_1        
        //   222: invokevirtual   java/util/Vector.get:(I)Ljava/lang/Object;
        //   225: checkcast       Ljava/lang/String;
        //   228: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   231: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   234: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   237: athrow         
        //   238: astore_2       
        //   239: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   242: dup            
        //   243: ldc_w           "NameNotFoundException: Could not find package."
        //   246: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   249: athrow         
        //   250: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   253: dup            
        //   254: new             Ljava/lang/StringBuilder;
        //   257: dup            
        //   258: invokespecial   java/lang/StringBuilder.<init>:()V
        //   261: aload           4
        //   263: invokevirtual   java/util/Vector.toString:()Ljava/lang/String;
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: ldc_w           " properties are not specified in manifest configChanges for "
        //   272: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   278: iload_1        
        //   279: invokevirtual   java/util/Vector.get:(I)Ljava/lang/Object;
        //   282: checkcast       Ljava/lang/String;
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   288: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   291: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   294: athrow         
        //   295: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   298: bipush          13
        //   300: if_icmplt       349
        //   303: aload_3        
        //   304: getfield        android/content/pm/ActivityInfo.configChanges:I
        //   307: sipush          1024
        //   310: iand           
        //   311: sipush          1024
        //   314: if_icmpeq       349
        //   317: ldc_w           "TapjoyConnect"
        //   320: new             Ljava/lang/StringBuilder;
        //   323: dup            
        //   324: ldc_w           "WARNING -- screenSize property is not specified in manifest configChanges for "
        //   327: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   330: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   333: iload_1        
        //   334: invokevirtual   java/util/Vector.get:(I)Ljava/lang/Object;
        //   337: checkcast       Ljava/lang/String;
        //   340: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   343: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   346: invokestatic    com/tapjoy/TapjoyLog.w:(Ljava/lang/String;Ljava/lang/String;)V
        //   349: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   352: bipush          11
        //   354: if_icmplt       418
        //   357: aload_3        
        //   358: getfield        android/content/pm/ActivityInfo.name:Ljava/lang/String;
        //   361: ldc_w           "com.tapjoy.TJAdUnitActivity"
        //   364: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   367: ifeq            418
        //   370: aload_3        
        //   371: getfield        android/content/pm/ActivityInfo.flags:I
        //   374: sipush          512
        //   377: iand           
        //   378: sipush          512
        //   381: if_icmpeq       418
        //   384: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   387: dup            
        //   388: new             Ljava/lang/StringBuilder;
        //   391: dup            
        //   392: ldc_w           "'hardwareAccelerated' property not specified in manifest for "
        //   395: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   398: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   401: iload_1        
        //   402: invokevirtual   java/util/Vector.get:(I)Ljava/lang/Object;
        //   405: checkcast       Ljava/lang/String;
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   414: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   417: athrow         
        //   418: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   421: iload_1        
        //   422: invokevirtual   java/util/Vector.remove:(I)Ljava/lang/Object;
        //   425: pop            
        //   426: goto            31
        //   429: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   432: invokevirtual   java/util/Vector.size:()I
        //   435: ifeq            538
        //   438: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   441: invokevirtual   java/util/Vector.size:()I
        //   444: iconst_1       
        //   445: if_icmpne       493
        //   448: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   451: dup            
        //   452: new             Ljava/lang/StringBuilder;
        //   455: dup            
        //   456: ldc_w           "Missing "
        //   459: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   462: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   465: invokevirtual   java/util/Vector.size:()I
        //   468: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   471: ldc_w           " dependency class in manifest: "
        //   474: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   477: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   480: invokevirtual   java/util/Vector.toString:()Ljava/lang/String;
        //   483: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   486: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   489: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   492: athrow         
        //   493: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   496: dup            
        //   497: new             Ljava/lang/StringBuilder;
        //   500: dup            
        //   501: ldc_w           "Missing "
        //   504: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   507: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   510: invokevirtual   java/util/Vector.size:()I
        //   513: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   516: ldc_w           " dependency classes in manifest: "
        //   519: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   522: getstatic       com/tapjoy/TapjoyConnectCore.m:Ljava/util/Vector;
        //   525: invokevirtual   java/util/Vector.toString:()Ljava/lang/String;
        //   528: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   531: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   534: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   537: athrow         
        //   538: invokestatic    com/tapjoy/TapjoyConnectCore.k:()V
        //   541: ldc_w           "com.tapjoy.TJAdUnitJSBridge"
        //   544: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   547: astore_2       
        //   548: aload_2        
        //   549: ldc_w           "closeRequested"
        //   552: iconst_1       
        //   553: anewarray       Ljava/lang/Class;
        //   556: dup            
        //   557: iconst_0       
        //   558: ldc_w           Ljava/lang/Boolean;.class
        //   561: aastore        
        //   562: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   565: pop            
        //   566: ldc_w           "mraid.js"
        //   569: invokestatic    com/tapjoy/TapjoyUtil.getResource:(Ljava/lang/String;)Ljava/lang/Object;
        //   572: checkcast       Ljava/lang/String;
        //   575: astore_2       
        //   576: aload_2        
        //   577: ifnonnull       755
        //   580: ldc_w           "js/mraid.js"
        //   583: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   586: invokestatic    com/tapjoy/TapjoyUtil.copyTextFromJarIntoString:(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
        //   589: astore_2       
        //   590: aload_2        
        //   591: ifnonnull       752
        //   594: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   597: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //   600: ldc_w           "mraid"
        //   603: ldc_w           "raw"
        //   606: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   609: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   612: invokevirtual   android/content/res/Resources.getIdentifier:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
        //   615: istore_1       
        //   616: getstatic       com/tapjoy/TapjoyConnectCore.g:Landroid/content/Context;
        //   619: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //   622: iload_1        
        //   623: invokevirtual   android/content/res/Resources.openRawResource:(I)Ljava/io/InputStream;
        //   626: astore_3       
        //   627: aload_3        
        //   628: invokevirtual   java/io/InputStream.available:()I
        //   631: newarray        B
        //   633: astore          4
        //   635: aload_3        
        //   636: aload           4
        //   638: invokevirtual   java/io/InputStream.read:([B)I
        //   641: pop            
        //   642: new             Ljava/lang/String;
        //   645: dup            
        //   646: aload           4
        //   648: invokespecial   java/lang/String.<init>:([B)V
        //   651: astore_3       
        //   652: ldc_w           "mraid.js"
        //   655: aload_3        
        //   656: invokestatic    com/tapjoy/TapjoyUtil.setResource:(Ljava/lang/String;Ljava/lang/Object;)V
        //   659: aload_3        
        //   660: astore_2       
        //   661: aload_2        
        //   662: ifnonnull       700
        //   665: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   668: dup            
        //   669: ldc_w           "ClassNotFoundException: mraid.js was not found."
        //   672: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   675: athrow         
        //   676: astore_2       
        //   677: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   680: dup            
        //   681: ldc_w           "ClassNotFoundException: com.tapjoy.TJAdUnitJSBridge was not found."
        //   684: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   687: athrow         
        //   688: astore_2       
        //   689: new             Lcom/tapjoy/TapjoyIntegrationException;
        //   692: dup            
        //   693: ldc_w           "Try configuring Proguard or other code obfuscators to ignore com.tapjoy classes. Visit http://dev.tapjoy.comfor more information."
        //   696: invokespecial   com/tapjoy/TapjoyIntegrationException.<init>:(Ljava/lang/String;)V
        //   699: athrow         
        //   700: ldc_w           "TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK"
        //   703: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   706: ifnull          734
        //   709: ldc_w           "TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK"
        //   712: invokestatic    com/tapjoy/TapjoyConnectCore.getConnectFlagValue:(Ljava/lang/String;)Ljava/lang/String;
        //   715: ldc_w           "true"
        //   718: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   721: ifeq            734
        //   724: ldc_w           "TapjoyConnect"
        //   727: ldc_w           "Skipping integration check for Google Play Services and Advertising ID. Do this only if you do not have access to Google Play Services."
        //   730: invokestatic    com/tapjoy/TapjoyLog.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   733: return         
        //   734: aload_0        
        //   735: getfield        com/tapjoy/TapjoyConnectCore.ad:Lcom/tapjoy/TapjoyGpsHelper;
        //   738: invokevirtual   com/tapjoy/TapjoyGpsHelper.checkGooglePlayIntegration:()V
        //   741: return         
        //   742: astore_3       
        //   743: goto            661
        //   746: astore_2       
        //   747: aload_3        
        //   748: astore_2       
        //   749: goto            661
        //   752: goto            661
        //   755: goto            590
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  0      20     238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  24     31     238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  31     74     238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  74     120    203    238    Ljava/lang/ClassNotFoundException;
        //  74     120    238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  120    141    203    238    Ljava/lang/ClassNotFoundException;
        //  120    141    238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  141    203    203    238    Ljava/lang/ClassNotFoundException;
        //  141    203    238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  204    238    238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  250    295    203    238    Ljava/lang/ClassNotFoundException;
        //  250    295    238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  295    349    203    238    Ljava/lang/ClassNotFoundException;
        //  295    349    238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  349    418    203    238    Ljava/lang/ClassNotFoundException;
        //  349    418    238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  418    426    203    238    Ljava/lang/ClassNotFoundException;
        //  418    426    238    250    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  541    548    676    688    Ljava/lang/ClassNotFoundException;
        //  548    566    688    700    Ljava/lang/NoSuchMethodException;
        //  594    652    742    746    Ljava/lang/Exception;
        //  652    659    746    752    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 319, Size: 319
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
    
    private static void k() {
        final int n = 0;
        final Vector<String> vector = new Vector<String>();
        final String[] dependencyPermissions = TapjoyConstants.dependencyPermissions;
        for (int length = dependencyPermissions.length, i = 0; i < length; ++i) {
            final String s = dependencyPermissions[i];
            if (!e(s)) {
                vector.add(s);
            }
        }
        if (vector.size() == 0) {
            final Vector<String> vector2 = new Vector<String>();
            final String[] optionalPermissions = TapjoyConstants.optionalPermissions;
            for (int length2 = optionalPermissions.length, j = n; j < length2; ++j) {
                final String s2 = optionalPermissions[j];
                if (!e(s2)) {
                    vector2.add(s2);
                }
            }
            if (vector2.size() != 0) {
                if (vector2.size() != 1) {
                    TapjoyLog.w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permissions were not found in manifest. The exclusion of these permissions could cause problems.");
                    return;
                }
                TapjoyLog.w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permission was not found in manifest. The exclusion of this permission could cause problems.");
            }
            return;
        }
        if (vector.size() == 1) {
            throw new TapjoyIntegrationException("Missing 1 permission in manifest: " + vector.toString());
        }
        throw new TapjoyIntegrationException("Missing " + vector.size() + " permissions in manifest: " + vector.toString());
    }
    
    private static boolean l() {
        return TapjoyConnectCore.c != null && TapjoyConnectCore.c.length() > 0;
    }
    
    private static boolean m() {
        return l() && getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS") != null && getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS").equals("true");
    }
    
    private static String n() {
        TapjoyLog.i("TapjoyConnect", "generating sessionID...");
        String sha256;
        try {
            sha256 = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + TapjoyConnectCore.v);
            TapjoyConnectCore.Z = System.currentTimeMillis();
            return sha256;
        }
        catch (Exception ex) {
            sha256 = null;
        }
        while (true) {
            try {
                TapjoyConnectCore.Z = System.currentTimeMillis();
                return sha256;
                final Exception ex;
                TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + ex.toString());
                return sha256;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    private static String o() {
        final int n = 1;
        if (m()) {
            return TapjoyConnectCore.c;
        }
        int n2;
        if (TapjoyConnectCore.p != null && TapjoyConnectCore.p.length() > 0) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (n2 != 0) {
            return TapjoyConnectCore.p;
        }
        if (l()) {
            return TapjoyConnectCore.c;
        }
        int n3;
        if (TapjoyConnectCore.n != null && TapjoyConnectCore.n.length() > 0) {
            n3 = n;
        }
        else {
            n3 = 0;
        }
        if (n3 != 0) {
            return TapjoyConnectCore.n;
        }
        TapjoyLog.e("TapjoyConnect", "Error -- no valid device identifier");
        return null;
    }
    
    private static String p() {
        final String string = TapjoyConnectCore.v + TapjoyConnectCore.w + TapjoyConnectCore.x + TapjoyConnectCore.c + TapjoyConnectCore.q;
        try {
            return TapjoyUtil.SHA1(string);
        }
        catch (Exception ex) {
            return string;
        }
    }
    
    public static void requestTapjoyConnect(final Context context, final String s) {
        requestTapjoyConnect(context, s, null);
    }
    
    public static void requestTapjoyConnect(final Context context, final String s, final Hashtable hashtable) {
        requestTapjoyConnect(context, s, hashtable, null);
    }
    
    public static void requestTapjoyConnect(final Context context, final String s, final Hashtable hashtable, final TJConnectListener k) {
        er er;
        try {
            er = new er(s);
            if (er.a != com.tapjoy.internal.er.a.SDK_ANDROID) {
                throw new IllegalArgumentException("The given API key was not for Android.");
            }
        }
        catch (IllegalArgumentException ex) {
            throw new TapjoyIntegrationException(ex.getMessage());
        }
        TapjoyConnectCore.h = s;
        TapjoyConnectCore.v = er.b;
        TapjoyConnectCore.L = er.c;
        TapjoyConnectCore.M = er.d;
        if (hashtable != null) {
            TapjoyConnectCore.ae.putAll(hashtable);
            fd.b().a(hashtable);
        }
        gc.a(context).j = s;
        TapjoyConnectCore.k = k;
        TapjoyConnectCore.i = new TapjoyConnectCore(context);
    }
    
    public static void setPlugin(final String n) {
        TapjoyConnectCore.N = n;
    }
    
    public static void setSDKType(final String o) {
        TapjoyConnectCore.O = o;
    }
    
    public static void setUserID(final String c, final TJSetUserIDListener l) {
        TapjoyConnectCore.C = c;
        TapjoyConnectCore.l = l;
        TapjoyLog.d("TapjoyConnect", "URL parameters: " + getURLParams());
        new Thread(new Runnable() {
            @Override
            public final void run() {
                TapjoyLog.i("TapjoyConnect", "Setting userID to " + TapjoyConnectCore.C);
                final TapjoyHttpURLResponse responseFromURL = TapjoyConnectCore.j.getResponseFromURL(TapjoyConnectCore.getHostURL() + "set_publisher_user_id?", TapjoyConnectCore.getURLParams());
                boolean a = false;
                if (responseFromURL.response != null) {
                    a = TapjoyConnectCore.a(responseFromURL.response);
                }
                TapjoyConnectCore.a(a);
            }
        }).start();
    }
    
    public static void setViewShowing(final boolean b) {
        if (b) {
            TapjoyConnectCore.ag.put("", 1);
            return;
        }
        TapjoyConnectCore.ag.clear();
    }
    
    public static void viewDidClose(final String s) {
        TapjoyLog.d("TapjoyConnect", "viewDidClose: " + s);
        TapjoyConnectCore.ag.remove(s);
        ev.e.notifyObservers();
    }
    
    public static void viewWillOpen(final String s, final int n) {
        TapjoyLog.d("TapjoyConnect", "viewWillOpen: " + s);
        TapjoyConnectCore.ag.put(s, n);
    }
    
    public void actionComplete(final String s) {
        TapjoyLog.i("TapjoyConnect", "actionComplete: " + s);
        final Map e = e();
        TapjoyUtil.safePut(e, "app_id", s, true);
        e.putAll(getTimeStampAndVerifierParams());
        TapjoyLog.d("TapjoyConnect", "PPA URL parameters: " + e);
        new Thread(new PPAThread(e)).start();
    }
    
    public void appPause() {
        this.Y = true;
    }
    
    public void appResume() {
        if (this.Y) {
            n();
            this.Y = false;
        }
    }
    
    public void callConnect() {
        this.fetchAdvertisingID();
    }
    
    public void completeConnectCall() {
        TapjoyLog.d("TapjoyConnect", "starting connect call...");
        String hostURL = "https://connect.tapjoy.com/";
        if (getHostURL() != "https://ws.tapjoyads.com/") {
            hostURL = getHostURL();
        }
        while (true) {
            Label_0458: {
                if (isConnected()) {
                    break Label_0458;
                }
                final String connectResult = TapjoyAppSettings.getInstance().getConnectResult(p(), com.tapjoy.internal.y.b());
                if (connectResult == null || !a(connectResult, true)) {
                    break Label_0458;
                }
                TapjoyLog.i("TapjoyConnect", "Connect using stored connect result");
                TapjoyConnectCore.ab = true;
                if (TapjoyConnectCore.k != null) {
                    TapjoyConnectCore.k.onConnectSuccess();
                }
                ev.a.notifyObservers();
                final int n = 1;
                final TapjoyHttpURLResponse responseFromURL = TapjoyConnectCore.j.getResponseFromURL(hostURL + "api/connect/v3.json?", null, null, getURLParams());
                if (responseFromURL != null && responseFromURL.statusCode == 200) {
                    if (a(responseFromURL.response, false)) {
                        TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
                        TapjoyConnectCore.ab = true;
                        for (final Map.Entry<String, V> entry : getGenericURLParams().entrySet()) {
                            TapjoyLog.d("TapjoyConnect", entry.getKey() + ": " + (String)entry.getValue());
                        }
                        if (n == 0) {
                            if (TapjoyConnectCore.k != null) {
                                TapjoyConnectCore.k.onConnectSuccess();
                            }
                            ev.a.notifyObservers();
                        }
                        ev.b.notifyObservers(Boolean.TRUE);
                    }
                    else {
                        if (n == 0) {
                            d();
                        }
                        ev.b.notifyObservers(Boolean.FALSE);
                    }
                    if (TapjoyConnectCore.af.length() > 0) {
                        final Map genericURLParams = getGenericURLParams();
                        TapjoyUtil.safePut(genericURLParams, "package_names", TapjoyConnectCore.af, true);
                        final long n2 = System.currentTimeMillis() / 1000L;
                        final String a = a(n2, TapjoyConnectCore.af);
                        TapjoyUtil.safePut(genericURLParams, "timestamp", String.valueOf(n2), true);
                        TapjoyUtil.safePut(genericURLParams, "verifier", a, true);
                        final TapjoyHttpURLResponse responseFromURL2 = new TapjoyURLConnection().getResponseFromURL(getHostURL() + "apps_installed?", genericURLParams);
                        if (responseFromURL2 != null && responseFromURL2.statusCode == 200) {
                            TapjoyLog.d("TapjoyConnect", "Successfully pinged sdkless api.");
                        }
                    }
                    return;
                }
                if (n == 0) {
                    d();
                }
                ev.b.notifyObservers(Boolean.FALSE);
                return;
            }
            final int n = 0;
            continue;
        }
    }
    
    public void fetchAdvertisingID() {
        new Thread(new Runnable() {
            @Override
            public final void run() {
                TapjoyConnectCore.this.ad.loadAdvertisingId();
                if (TapjoyConnectCore.this.ad.isGooglePlayServicesAvailable() && TapjoyConnectCore.this.ad.isGooglePlayManifestConfigured()) {
                    TapjoyConnectCore.b = TapjoyConnectCore.this.ad.getDeviceGooglePlayServicesVersion();
                    TapjoyConnectCore.a = TapjoyConnectCore.this.ad.getPackagedGooglePlayServicesVersion();
                }
                if (TapjoyConnectCore.this.ad.isAdIdAvailable()) {
                    TapjoyConnectCore.d = TapjoyConnectCore.this.ad.isAdTrackingEnabled();
                    TapjoyConnectCore.c = TapjoyConnectCore.this.ad.getAdvertisingId();
                    final gc a = gc.a();
                    final String c = TapjoyConnectCore.c;
                    final boolean b = !TapjoyConnectCore.d;
                    final gf f = a.f;
                    final String a2 = f.c.A.a();
                    f.b.q = c;
                    f.b.r = b;
                    f.c.A.a(c);
                    f.c.B.a(b);
                    gq.a(c, b);
                    if (!ct.c(a2) && !c.equals(a2)) {
                        f.c.a(false);
                    }
                }
                if (m()) {
                    TapjoyLog.i("TapjoyConnect", "Disabling persistent IDs. Do this only if you are not using Tapjoy to manage currency.");
                }
                TapjoyConnectCore.this.completeConnectCall();
            }
        }).start();
    }
    
    public float getCurrencyMultiplier() {
        return TapjoyConnectCore.Q;
    }
    
    public boolean isInitialized() {
        return this.aa;
    }
    
    public void release() {
        TapjoyConnectCore.i = null;
        TapjoyConnectCore.j = null;
        TapjoyLog.d("TapjoyConnect", "Releasing core static instance.");
    }
    
    public void setCurrencyMultiplier(final float q) {
        TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + q);
        TapjoyConnectCore.Q = q;
    }
    
    public class PPAThread implements Runnable
    {
        private Map b;
        
        public PPAThread(final Map b) {
            this.b = b;
        }
        
        @Override
        public void run() {
            final TapjoyHttpURLResponse responseFromURL = TapjoyConnectCore.j.getResponseFromURL(TapjoyConnectCore.getHostURL() + "api/connect/v3.json?", null, null, this.b);
            if (responseFromURL.response != null) {
                c(responseFromURL.response);
            }
        }
    }
}
