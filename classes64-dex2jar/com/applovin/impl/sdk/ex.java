// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.HashMap;
import com.applovin.mediation.AppLovinMediationAdapterStats;
import java.util.Iterator;
import java.util.Collection;
import android.text.TextUtils;
import com.applovin.mediation.AppLovinMediationAdapterStatus;
import android.graphics.Point;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;
import com.applovin.adview.AppLovinInterstitialActivity;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;
import com.applovin.mediation.AppLovinMediationAdapterInfo;
import com.applovin.sdk.AppLovinAdLoadListener;

class ex extends dx
{
    private final n a;
    private final AppLovinAdLoadListener b;
    private boolean h;
    
    ex(final n a, final AppLovinAdLoadListener b, final AppLovinSdkImpl appLovinSdkImpl) {
        super("FetchNextAd", appLovinSdkImpl);
        this.h = false;
        this.a = a;
        this.b = b;
    }
    
    private String a(final AppLovinMediationAdapterInfo appLovinMediationAdapterInfo) {
        if (appLovinMediationAdapterInfo == null) {
            return null;
        }
        return appLovinMediationAdapterInfo.getVersion();
    }
    
    private void a(final aw aw) {
        if (System.currentTimeMillis() - aw.b("ad_session_start") > TimeUnit.MINUTES.toMillis(this.d.get(ea.z))) {
            aw.b("ad_session_start", System.currentTimeMillis());
            aw.c("ad_imp_session");
        }
    }
    
    private String b() {
        String string = "custom_size,launch_app";
        if (ab.c()) {
            string = string;
            if (ab.a(AppLovinInterstitialActivity.class, this.f)) {
                string = "custom_size,launch_app" + ",video";
            }
        }
        return string;
    }
    
    private void b(final int n) {
        this.e.e(this.c, "Unable to fetch " + this.a + " ad: server returned " + n);
        while (true) {
            try {
                this.a(n);
                ag.b(n, this.d);
            }
            catch (Throwable t) {
                this.e.userError(this.c, "Unable process a failure to recieve an ad", t);
                continue;
            }
            break;
        }
    }
    
    private void b(final JSONObject jsonObject) {
        ag.a(jsonObject, this.d);
        this.d.i();
        final dx a = this.a(jsonObject);
        if (this.d.get(ea.cC)) {
            this.d.getTaskManager().a(a);
        }
        else {
            this.d.getTaskManager().a(a, fe.a);
        }
        ag.b(jsonObject, this.d);
    }
    
    private void f(final Map<String, String> map) {
        if (this.d.getSettings().isTestAdsEnabled()) {
            map.put("test_ads", Boolean.toString(true));
        }
        map.put("api_did", this.d.get(ea.f));
        map.put("sdk_key", this.d.getSdkKey());
        map.put("sdk_version", "8.0.1");
        map.put("app_version", gd.c(this.d.getDataCollector().d().b));
        map.put("build", Integer.toString(89));
        final String s = this.d.get(ea.I);
        if (AppLovinSdkUtils.isValidString(s)) {
            map.put("plugin_version", s);
        }
        final String mediationProvider = this.d.getMediationProvider();
        if (AppLovinSdkUtils.isValidString(mediationProvider)) {
            map.put("mediation_provider", gd.c(mediationProvider));
        }
        map.put("accept", this.b());
        map.put("v1", Boolean.toString(ab.a("android.permission.WRITE_EXTERNAL_STORAGE", this.f)));
        map.put("v2", Boolean.toString(ab.a(AppLovinInterstitialActivity.class, this.f)));
        map.put("v3", Boolean.toString(ab.a(this.f)));
        map.put("v4", Boolean.toString(ab.b(this.f)));
        map.put("preloading", String.valueOf(this.h));
        map.put("format", "json");
        final ah dataCollector = this.d.getDataCollector();
        map.put("ia", Long.toString(dataCollector.d().e));
        map.put("installer_name", dataCollector.d().d);
    }
    
    private void g(final Map<String, String> map) {
        if (this.d.get(ea.R)) {
            final aw a = this.d.a();
            map.put("li", String.valueOf(a.b("ad_imp")));
            map.put("si", String.valueOf(a.b("ad_imp_session")));
        }
        map.put("sc", this.d.get(ea.A));
    }
    
    private void h(final Map<String, String> map) {
        if (this.d.isSessionTrackingEnabled()) {
            map.put("pnr", Boolean.toString(this.d.j()));
        }
    }
    
    private void i(final Map<String, String> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/applovin/impl/sdk/ex.d:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //     4: invokestatic    com/applovin/impl/sdk/a.a:(Lcom/applovin/impl/sdk/AppLovinSdkImpl;)Ljava/util/Map;
        //     7: astore_2       
        //     8: aload_2        
        //     9: invokeinterface java/util/Map.isEmpty:()Z
        //    14: ifeq            30
        //    17: aload_0        
        //    18: aload_2        
        //    19: invokespecial   com/applovin/impl/sdk/ex.j:(Ljava/util/Map;)V
        //    22: aload_2        
        //    23: aload_0        
        //    24: getfield        com/applovin/impl/sdk/ex.d:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    27: invokestatic    com/applovin/impl/sdk/a.a:(Ljava/util/Map;Lcom/applovin/impl/sdk/AppLovinSdkImpl;)V
        //    30: aload_0        
        //    31: aload_2        
        //    32: invokespecial   com/applovin/impl/sdk/ex.k:(Ljava/util/Map;)V
        //    35: aload_1        
        //    36: aload_2        
        //    37: invokeinterface java/util/Map.putAll:(Ljava/util/Map;)V
        //    42: aload_1        
        //    43: ldc_w           "network"
        //    46: aload_0        
        //    47: getfield        com/applovin/impl/sdk/ex.d:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    50: invokestatic    com/applovin/impl/sdk/ag.a:(Lcom/applovin/impl/sdk/AppLovinSdkImpl;)Ljava/lang/String;
        //    53: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    58: pop            
        //    59: aload_0        
        //    60: aload_1        
        //    61: invokespecial   com/applovin/impl/sdk/ex.m:(Ljava/util/Map;)V
        //    64: aload_1        
        //    65: ldc_w           "vz"
        //    68: aload_0        
        //    69: getfield        com/applovin/impl/sdk/ex.d:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    72: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getApplicationContext:()Landroid/content/Context;
        //    75: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    78: aload_0        
        //    79: getfield        com/applovin/impl/sdk/ex.d:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    82: invokestatic    com/applovin/impl/sdk/gd.a:(Ljava/lang/String;Lcom/applovin/impl/sdk/AppLovinSdkImpl;)Ljava/lang/String;
        //    85: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    90: pop            
        //    91: return         
        //    92: astore_3       
        //    93: aload_0        
        //    94: getfield        com/applovin/impl/sdk/ex.e:Lcom/applovin/sdk/AppLovinLogger;
        //    97: aload_0        
        //    98: getfield        com/applovin/impl/sdk/ex.c:Ljava/lang/String;
        //   101: ldc_w           "Unable to populate device information"
        //   104: aload_3        
        //   105: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   110: goto            30
        //   113: astore_3       
        //   114: aload_0        
        //   115: getfield        com/applovin/impl/sdk/ex.e:Lcom/applovin/sdk/AppLovinLogger;
        //   118: aload_0        
        //   119: getfield        com/applovin/impl/sdk/ex.c:Ljava/lang/String;
        //   122: ldc_w           "Unable to populate ephemeral device information"
        //   125: aload_3        
        //   126: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   131: goto            35
        //    Signature:
        //  (Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  17     30     92     113    Ljava/lang/Exception;
        //  30     35     113    134    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    
    private void j(final Map<String, String> map) {
        final am a = this.d.getDataCollector().a();
        map.put("brand", gd.c(a.d));
        map.put("brand_name", gd.c(a.e));
        map.put("hardware", gd.c(a.f));
        map.put("carrier", gd.c(a.j));
        map.put("country_code", gd.c(a.i));
        map.put("locale", gd.c(a.k.toString()));
        map.put("model", gd.c(a.a));
        map.put("os", gd.c(a.b));
        map.put("platform", gd.c(a.c));
        map.put("revision", gd.c(a.g));
        map.put("orientation_lock", a.l);
        map.put("tz_offset", String.valueOf(a.o));
        map.put("wvvc", String.valueOf(a.p));
        map.put("adns", String.valueOf(a.m));
        map.put("adnsd", String.valueOf(a.n));
        String s;
        if (a.u) {
            s = "1";
        }
        else {
            s = "0";
        }
        map.put("sim", s);
        map.put("gy", String.valueOf(a.v));
        this.l(map);
    }
    
    private void k(final Map<String, String> map) {
        final am c = this.d.getDataCollector().c();
        final al r = c.r;
        if (r != null) {
            map.put("act", String.valueOf(r.a));
            map.put("acm", String.valueOf(r.b));
        }
        String s;
        if (c.q) {
            s = "1";
        }
        else {
            s = "0";
        }
        map.put("adr", s);
        map.put("volume", String.valueOf(c.s));
        final String t = c.t;
        if (AppLovinSdkUtils.isValidString(t)) {
            map.put("ua", gd.c(t));
        }
        final Boolean w = c.w;
        if (w != null) {
            map.put("huc", w.toString());
        }
        final Boolean x = c.x;
        if (x != null) {
            map.put("aru", x.toString());
        }
        this.l(map);
        this.n(map);
    }
    
    private void l(final Map<String, String> map) {
        final Point c = ab.c(this.d.getApplicationContext());
        map.put("dx", Integer.toString(c.x));
        map.put("dy", Integer.toString(c.y));
    }
    
    private void m(final Map<String, String> map) {
        final aj e = this.d.getDataCollector().e();
        final String b = e.b;
        if (AppLovinSdkUtils.isValidString(b)) {
            map.put("idfa", b);
        }
        map.put("dnt", Boolean.toString(e.a));
    }
    
    private void n(final Map<String, String> map) {
        final Collection<AppLovinMediationAdapterInfo> adapterInfo = this.d.getMediationService().getAdapterInfo();
        if (adapterInfo != null && !adapterInfo.isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            for (final AppLovinMediationAdapterInfo appLovinMediationAdapterInfo : adapterInfo) {
                if (appLovinMediationAdapterInfo.getStatus() == AppLovinMediationAdapterStatus.READY) {
                    sb.append(appLovinMediationAdapterInfo.getName());
                    final String a = this.a(appLovinMediationAdapterInfo);
                    if (!TextUtils.isEmpty((CharSequence)a)) {
                        sb.append(":");
                        sb.append(a);
                    }
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            if (sb.length() > 0) {
                map.put("aa", sb.toString());
            }
        }
        final AppLovinMediationAdapterStats lastAdapterStats = this.d.getMediationService().getLastAdapterStats();
        if (lastAdapterStats != null) {
            map.put("lman", lastAdapterStats.getAdapterName());
            map.put("lmat", String.valueOf(lastAdapterStats.getLastAdLoadMillis()));
        }
    }
    
    protected dx a(final JSONObject jsonObject) {
        return new fj(jsonObject, this.a, this.b, this.d);
    }
    
    protected void a(final int n) {
        if (this.b != null) {
            if (!(this.b instanceof at)) {
                this.b.failedToReceiveAd(n);
                return;
            }
            ((at)this.b).a(this.a, n);
        }
    }
    
    void a(final Map<String, String> map) {
        map.put("zone_id", gd.c(this.a.a()));
    }
    
    void a(final boolean h) {
        this.h = h;
    }
    
    protected String b(final Map<String, String> map) {
        return ag.b("3.0/ad", map, this.d);
    }
    
    protected String c(final Map<String, String> map) {
        return ag.d("3.0/ad", map, this.d);
    }
    
    protected void d(final Map<String, String> map) {
        this.i(map);
        this.g(map);
        this.f(map);
        this.e(map);
        this.a(map);
        this.h(map);
    }
    
    void e(final Map<String, String> map) {
    }
    
    @Override
    public void run() {
        Label_0180: {
            if (!this.h) {
                break Label_0180;
            }
            this.e.d(this.c, "Preloading next ad of zone: " + this.a);
            while (true) {
                final aw a = this.d.a();
                a.a("ad_req");
                this.a(a);
                try {
                    final ey ey = new ey(this, "GET", new JSONObject(), "RepeatFetchNextAd", this.d);
                    final HashMap<String, String> hashMap = new HashMap<String, String>();
                    this.d(hashMap);
                    ey.a(this.b(hashMap));
                    ey.b(this.c(hashMap));
                    ey.b(this.d.get(ea.x));
                    ey.c(this.d.get(ea.k));
                    ey.a(ea.n);
                    ey.b(ea.r);
                    ey.run();
                    return;
                    this.e.d(this.c, "Fetching next ad of zone: " + this.a);
                }
                catch (Throwable t) {
                    this.e.e(this.c, "Unable to fetch ad " + this.a, t);
                    this.b(0);
                }
            }
        }
    }
}
