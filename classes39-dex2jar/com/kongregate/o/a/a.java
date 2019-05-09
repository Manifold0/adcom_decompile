// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import com.kongregate.o.c.d;
import com.adjust.sdk.Adjust;
import android.net.Uri;
import com.kongregate.android.api.APIBootstrap;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.AdjustConfig;
import java.util.HashMap;
import com.adjust.sdk.OnAttributionChangedListener;
import com.adjust.sdk.AdjustEvent;
import com.kongregate.android.internal.util.StringUtils;
import java.util.Iterator;
import org.json.JSONException;
import com.kongregate.android.internal.util.i;
import org.json.JSONObject;
import android.content.pm.PackageInfo;
import com.kongregate.android.internal.util.j;
import com.kongregate.android.internal.sdk.b;
import java.util.Collection;
import java.util.Arrays;
import com.kongregate.android.api.AnalyticsServices;
import java.util.HashSet;
import java.util.Map;
import android.content.Context;
import java.util.List;
import java.util.Set;

public class a
{
    public static final String a = "sale";
    public static final String b = "session";
    public static final String c = "install";
    static final Set<String> d;
    static List<String> e;
    private Context f;
    private volatile Map<String, String> g;
    private a h;
    
    static {
        (d = new HashSet<String>()).addAll(Arrays.asList(AnalyticsServices.Fields.DEVICE_EVENT_ID.fieldName(), AnalyticsServices.Fields.PLAYER_ID.fieldName(), AnalyticsServices.Fields.SITE_VISITOR_ID.fieldName(), AnalyticsServices.Fields.TOTAL_SPENT_IN_USD.fieldName(), AnalyticsServices.Fields.NUM_PURCHASES.fieldName(), AnalyticsServices.Fields.EVENT_TIME_OFFSET.fieldName(), AnalyticsServices.Fields.EVENT_TIME.fieldName(), AnalyticsServices.Fields.FIRST_PLAY_TIME_OFFSET.fieldName(), AnalyticsServices.Fields.FIRST_PLAY_TIME.fieldName(), AnalyticsServices.Fields.NUM_SESSIONS.fieldName(), AnalyticsServices.Fields.DAYS_RETAINED.fieldName(), AnalyticsServices.Fields.DEVICE_TYPE.fieldName(), AnalyticsServices.Fields.CLIENT_OS_TYPE.fieldName(), AnalyticsServices.Fields.CLIENT_OS_VERSION.fieldName(), AnalyticsServices.Fields.COUNTRY_CODE.fieldName(), AnalyticsServices.Fields.LANG_CODE.fieldName(), AnalyticsServices.Fields.DATA_CONNECTION_TYPE.fieldName(), AnalyticsServices.Fields.IP_ADDRESS.fieldName(), AnalyticsServices.Fields.EXTERNAL_IP_ADDRESS.fieldName(), AnalyticsServices.Fields.KONG_USER_ID.fieldName(), AnalyticsServices.Fields.KONG_USERNAME.fieldName(), AnalyticsServices.Fields.KONG_PLUS.fieldName(), AnalyticsServices.Fields.USD_SPENT_ON_KREDS.fieldName(), AnalyticsServices.Fields.PUR_TIER.fieldName(), AnalyticsServices.Fields.GAMECENTER_ID.fieldName(), AnalyticsServices.Fields.GAMECENTER_ALIAS.fieldName(), AnalyticsServices.Fields.SESSION_ID.fieldName(), AnalyticsServices.Fields.IDFA.fieldName(), AnalyticsServices.Fields.GOOGLE_AD_ID.fieldName(), AnalyticsServices.Fields.AD_TRACKING.fieldName(), AnalyticsServices.Fields.CLIENT_VERSION.fieldName(), AnalyticsServices.Fields.DEV_CLIENT_VERSION.fieldName(), AnalyticsServices.Fields.FIRST_CLIENT_VERSION.fieldName(), AnalyticsServices.Fields.FIRST_SDK_VERSION.fieldName(), AnalyticsServices.Fields.SDK_VERSION.fieldName(), AnalyticsServices.Fields.PKG_SRC.fieldName(), AnalyticsServices.Fields.TIME_SKEW.fieldName(), AnalyticsServices.Fields.BUNDLE_ID.fieldName(), AnalyticsServices.Fields.IS_FROM_BACKGROUND.fieldName(), "iap_id", "local_currency_type", "local_currency_cost", "usd_cost", "product_id", "receipt_id", "utm_source", "utm_medium", "utm_term", "utm_content", "utm_campaign", f.a.a(), f.b.a(), f.c.a(), f.d.a(), f.e.a(), f.f.a(), f.g.a(), f.h.a(), f.i.a(), f.j.a(), f.k.a(), f.l.a(), f.m.a(), f.n.a(), f.o.a(), f.p.a(), f.q.a(), f.r.a(), f.s.a(), f.t.a(), f.u.a(), f.v.a(), f.w.a(), f.x.a(), f.y.a(), f.z.a()));
        com.kongregate.o.a.a.e = Arrays.asList("sale");
    }
    
    public a(final Context context) {
        this.f = context.getApplicationContext();
        this.h = new a();
    }
    
    private String a(final Map<String, Object> map) {
        final String a = com.kongregate.android.internal.sdk.b.a(map, "adjust.environment");
        if ("sandbox".equals(a)) {
            return "sandbox";
        }
        if ("production".equals(a)) {
            return "production";
        }
        j.c("Adjust initialization - unable to find AdjustEnvironment " + a + ". Not initializing Adjust (expected " + "production" + " or " + "sandbox" + ").");
        return null;
    }
    
    public static boolean a(final Context p0, final PackageInfo p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          4
        //     3: iconst_0       
        //     4: istore          5
        //     6: aload_1        
        //     7: getfield        android/content/pm/PackageInfo.receivers:[Landroid/content/pm/ActivityInfo;
        //    10: ifnonnull       22
        //    13: ldc_w           "AdjustWrapper - no receivers specified"
        //    16: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //    19: iload           5
        //    21: ireturn        
        //    22: aload_1        
        //    23: getfield        android/content/pm/PackageInfo.receivers:[Landroid/content/pm/ActivityInfo;
        //    26: astore_0       
        //    27: aload_0        
        //    28: arraylength    
        //    29: istore_3       
        //    30: iconst_0       
        //    31: istore_2       
        //    32: iload           4
        //    34: istore          5
        //    36: iload_2        
        //    37: iload_3        
        //    38: if_icmpge       19
        //    41: aload_0        
        //    42: iload_2        
        //    43: aaload         
        //    44: astore_1       
        //    45: iload           4
        //    47: istore          5
        //    49: ldc_w           "com.kongregate.android.api.receivers.InstallReceiver"
        //    52: aload_1        
        //    53: getfield        android/content/pm/ActivityInfo.name:Ljava/lang/String;
        //    56: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    59: ifeq            210
        //    62: new             Ljava/lang/StringBuilder;
        //    65: dup            
        //    66: invokespecial   java/lang/StringBuilder.<init>:()V
        //    69: ldc_w           "AdjustWrapper - receiver found: "
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: aload_1        
        //    76: getfield        android/content/pm/ActivityInfo.name:Ljava/lang/String;
        //    79: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    82: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    85: invokestatic    com/kongregate/android/internal/util/j.a:(Ljava/lang/String;)V
        //    88: iload           4
        //    90: istore          5
        //    92: aload_1        
        //    93: getfield        android/content/pm/ActivityInfo.metaData:Landroid/os/Bundle;
        //    96: ifnull          210
        //    99: aload_1        
        //   100: getfield        android/content/pm/ActivityInfo.metaData:Landroid/os/Bundle;
        //   103: invokevirtual   android/os/Bundle.keySet:()Ljava/util/Set;
        //   106: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   111: astore          6
        //   113: aload           6
        //   115: invokeinterface java/util/Iterator.hasNext:()Z
        //   120: ifeq            206
        //   123: aload           6
        //   125: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   130: checkcast       Ljava/lang/String;
        //   133: astore          7
        //   135: aload_1        
        //   136: getfield        android/content/pm/ActivityInfo.metaData:Landroid/os/Bundle;
        //   139: aload           7
        //   141: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   144: astore          7
        //   146: ldc_w           "com.adjust.sdk.AdjustReferrerReceiver"
        //   149: aload           7
        //   151: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   154: ifeq            203
        //   157: new             Ljava/lang/StringBuilder;
        //   160: dup            
        //   161: invokespecial   java/lang/StringBuilder.<init>:()V
        //   164: ldc_w           "AdjustWrapper - forward found: "
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: aload           7
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   178: invokestatic    com/kongregate/android/internal/util/j.a:(Ljava/lang/String;)V
        //   181: aload           7
        //   183: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   186: pop            
        //   187: iconst_1       
        //   188: istore          4
        //   190: goto            113
        //   193: astore          7
        //   195: ldc_w           "AdjustWrapper - referral receiver class not found: "
        //   198: aload           7
        //   200: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   203: goto            190
        //   206: iload           4
        //   208: istore          5
        //   210: iload_2        
        //   211: iconst_1       
        //   212: iadd           
        //   213: istore_2       
        //   214: iload           5
        //   216: istore          4
        //   218: goto            32
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  181    187    193    203    Ljava/lang/ClassNotFoundException;
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
    
    private String b(final Map<String, Object> map) {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : map.keySet()) {
            if (com.kongregate.o.a.a.d.contains(s) && i.a(map.get(s))) {
                try {
                    jsonObject.put(s, map.get(s));
                }
                catch (JSONException ex) {
                    j.c("Exception building custom data json: attempting to add field: " + s, (Throwable)ex);
                }
            }
        }
        return jsonObject.toString();
    }
    
    public void a() {
        if (!this.d()) {
            j.c("Adjust - onPause - not initialized");
            return;
        }
        j.a("Adjust onPause");
        this.e().a();
    }
    
    public void a(final String s, final Map<String, Object> map) {
        if (!this.d()) {
            j.c("Adjust - not initialized. Not sending event: " + s);
            return;
        }
        final String s2 = this.g.get(s);
        if (StringUtils.c((CharSequence)s2)) {
            j.c("Adjust - no event token for event: " + s);
            return;
        }
        j.a("firing Adjust event: " + s + " token: " + s2);
        final AdjustEvent adjustEvent = new AdjustEvent(s2);
    Label_0180:
        while (true) {
            if (!com.kongregate.o.a.a.e.contains(s)) {
                break Label_0180;
            }
            while (true) {
                Double n;
                while (true) {
                    try {
                        n = map.get("usd_cost");
                        if (n != null) {
                            j.a("Adding revenue to adjust event: " + n);
                            adjustEvent.setRevenue((double)n, "USD");
                            adjustEvent.addCallbackParameter("kong_props", this.b(map));
                            this.e().a(adjustEvent);
                            return;
                        }
                    }
                    catch (ClassCastException ex) {
                        j.c("unable to cast USD_COST to double: " + map.get("usd_cost"));
                        n = null;
                        continue;
                    }
                    break;
                }
                j.c("Unable to set usd cost: " + n);
                continue Label_0180;
            }
            break;
        }
    }
    
    public void a(final Map<String, Object> map, final OnAttributionChangedListener onAttributionChangedListener) {
        j.a("Adjust initialize");
        final String a = com.kongregate.android.internal.sdk.b.a(map, "adjust.app_token");
        if (StringUtils.a((CharSequence)a)) {
            j.c("Adjust Initialization - unable to find AdjustAppToken. Not initializing Adjust.");
        }
        else {
            final String a2 = this.a(map);
            if (a2 != null) {
                (this.g = new HashMap<String, String>()).put("sale", com.kongregate.android.internal.sdk.b.a(map, "adjust.sale"));
                this.g.put("session", com.kongregate.android.internal.sdk.b.a(map, "adjust.session"));
                this.g.put("install", com.kongregate.android.internal.sdk.b.a(map, "adjust.install"));
                for (final String s : this.g.keySet()) {
                    if (StringUtils.a((CharSequence)this.g.get(s))) {
                        j.c("KONGREGATE CONFIGURATION WARNING: missing Adjust event token for event: " + s);
                    }
                }
                j.a("Adjust initializing: " + a + " : " + a2);
                final AdjustConfig adjustConfig = new AdjustConfig(this.f, a, a2);
                LogLevel logLevel;
                if (AdjustConfig.class.equals(a2)) {
                    logLevel = LogLevel.DEBUG;
                }
                else {
                    logLevel = LogLevel.INFO;
                }
                adjustConfig.setLogLevel(logLevel);
                if (onAttributionChangedListener != null) {
                    adjustConfig.setOnAttributionChangedListener(onAttributionChangedListener);
                }
                this.e().a(adjustConfig);
                this.c();
            }
        }
    }
    
    public void b() {
        if (!this.d()) {
            j.c("Adjust - onResume - not initialized");
            return;
        }
        j.a("Adjust - onResume");
        this.e().b();
    }
    
    public void b(final String s, final Map<String, Object> map) {
        if (!this.d()) {
            j.c("Adjust - not initialized. Not sending event: " + s);
            return;
        }
        final AdjustEvent adjustEvent = new AdjustEvent(s);
        adjustEvent.addCallbackParameter("kong_props", this.b(map));
        this.e().a(adjustEvent);
    }
    
    public void c() {
        final Uri openURL = APIBootstrap.getInstance().mobile().getOpenURL();
        if (this.d() && openURL != null) {
            j.a("notify adjust of deep link launch: " + openURL);
            this.e().a(openURL);
        }
    }
    
    protected boolean d() {
        return this.g != null;
    }
    
    protected a e() {
        return this.h;
    }
    
    protected class a
    {
        protected void a() {
            com.kongregate.o.c.d.c(new Runnable() {
                @Override
                public void run() {
                    Adjust.onPause();
                }
            });
        }
        
        protected void a(final Uri uri) {
            com.kongregate.o.c.d.c(new Runnable() {
                @Override
                public void run() {
                    Adjust.appWillOpenUrl(uri);
                }
            });
        }
        
        protected void a(final AdjustConfig adjustConfig) {
            com.kongregate.o.c.d.c(new Runnable() {
                @Override
                public void run() {
                    Adjust.onCreate(adjustConfig);
                }
            });
        }
        
        protected void a(final AdjustEvent adjustEvent) {
            com.kongregate.o.c.d.c(new Runnable() {
                @Override
                public void run() {
                    Adjust.trackEvent(adjustEvent);
                }
            });
        }
        
        protected void b() {
            com.kongregate.o.c.d.c(new Runnable() {
                @Override
                public void run() {
                    Adjust.onResume();
                }
            });
        }
    }
}
