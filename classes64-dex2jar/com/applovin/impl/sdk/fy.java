// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinSdkUtils;
import org.json.JSONObject;
import java.util.Map;
import com.applovin.sdk.AppLovinAd;
import java.util.HashMap;
import com.applovin.sdk.AppLovinAdRewardListener;

class fy extends dv
{
    private final an a;
    private final AppLovinAdRewardListener b;
    private final Object h;
    private volatile boolean i;
    
    public fy(final an a, final AppLovinAdRewardListener b, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskValidateReward", appLovinSdkImpl);
        this.h = new Object();
        this.i = false;
        this.a = a;
        this.b = b;
    }
    
    private void a(final int n) {
        if (this.b()) {
            return;
        }
        String s = "network_timeout";
        if (n >= 400 && n <= 500) {
            this.b.userRewardRejected(this.a, new HashMap<String, String>(0));
            s = "rejected";
        }
        else {
            this.b.validationRequestFailed(this.a, n);
        }
        dn.a().a(this.a, s);
    }
    
    private void a(final String s, final Map<String, String> map) {
        if (this.b()) {
            return;
        }
        final dn a = dn.a();
        a.a(this.a, s);
        a.a(this.a, map);
        if (s.equals("accepted")) {
            this.b.userRewardVerified(this.a, map);
            return;
        }
        if (s.equals("quota_exceeded")) {
            this.b.userOverQuota(this.a, map);
            return;
        }
        if (s.equals("rejected")) {
            this.b.userRewardRejected(this.a, map);
            return;
        }
        this.b.validationRequestFailed(this.a, -400);
    }
    
    private void a(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/applovin/impl/sdk/fy.b:()Z
        //     4: ifeq            8
        //     7: return         
        //     8: aload_1        
        //     9: invokestatic    com/applovin/impl/sdk/ag.a:(Lorg/json/JSONObject;)Lorg/json/JSONObject;
        //    12: astore_2       
        //    13: aload_2        
        //    14: aload_0        
        //    15: getfield        com/applovin/impl/sdk/fy.d:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    18: invokestatic    com/applovin/impl/sdk/ag.a:(Lorg/json/JSONObject;Lcom/applovin/impl/sdk/AppLovinSdkImpl;)V
        //    21: aload_2        
        //    22: ldc             "params"
        //    24: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
        //    27: checkcast       Lorg/json/JSONObject;
        //    30: invokestatic    com/applovin/impl/sdk/bu.a:(Lorg/json/JSONObject;)Ljava/util/Map;
        //    33: astore_1       
        //    34: aload_2        
        //    35: ldc             "result"
        //    37: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    40: astore_2       
        //    41: aload_0        
        //    42: aload_2        
        //    43: aload_1        
        //    44: invokespecial   com/applovin/impl/sdk/fy.a:(Ljava/lang/String;Ljava/util/Map;)V
        //    47: return         
        //    48: astore_1       
        //    49: aload_0        
        //    50: getfield        com/applovin/impl/sdk/fy.e:Lcom/applovin/sdk/AppLovinLogger;
        //    53: aload_0        
        //    54: getfield        com/applovin/impl/sdk/fy.c:Ljava/lang/String;
        //    57: ldc             "Unable to parse API response"
        //    59: aload_1        
        //    60: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //    65: return         
        //    66: astore_1       
        //    67: new             Ljava/util/HashMap;
        //    70: dup            
        //    71: iconst_0       
        //    72: invokespecial   java/util/HashMap.<init>:(I)V
        //    75: astore_1       
        //    76: goto            34
        //    79: astore_2       
        //    80: ldc             "network_timeout"
        //    82: astore_2       
        //    83: goto            41
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  8      21     48     66     Lorg/json/JSONException;
        //  21     34     66     79     Ljava/lang/Throwable;
        //  21     34     48     66     Lorg/json/JSONException;
        //  34     41     79     86     Ljava/lang/Throwable;
        //  34     41     48     66     Lorg/json/JSONException;
        //  41     47     48     66     Lorg/json/JSONException;
        //  67     76     48     66     Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 46, Size: 46
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
    
    public void a(final boolean i) {
        synchronized (this.h) {
            this.i = i;
        }
    }
    
    boolean b() {
        synchronized (this.h) {
            return this.i;
        }
    }
    
    @Override
    public void run() {
        final String userIdentifier = this.d.getUserIdentifier();
        final String n = this.a.n();
        final HashMap<String, String> hashMap = new HashMap<String, String>(3);
        hashMap.put("zone_id", this.a.t().a());
        if (AppLovinSdkUtils.isValidString(n)) {
            hashMap.put("clcode", n);
        }
        else {
            hashMap.put("clcode", "NO_CLCODE");
        }
        if (!TextUtils.isEmpty((CharSequence)userIdentifier)) {
            hashMap.put("user_id", userIdentifier);
        }
        this.a("vr", new JSONObject((Map)hashMap), new fz(this));
    }
}
