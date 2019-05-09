// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.pm.ApplicationInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.mediation.AppLovinMediationAdapterConfig;

class cm implements AppLovinMediationAdapterConfig
{
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final String c;
    private final String d;
    private final Object e;
    private Map<String, String> f;
    private final Set<String> g;
    private final Map<String, String> h;
    
    cm(final String s, final AppLovinSdkImpl a) {
        this.e = new Object();
        this.g = new HashSet<String>(5);
        this.h = new HashMap<String, String>(5);
        if (a == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (s == null) {
            throw new IllegalArgumentException("No classname specified");
        }
        this.a = a;
        this.b = a.getLogger();
        this.c = s.toLowerCase();
        this.d = "applovin.mediation." + s + ":config";
    }
    
    private Map<String, String> a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/applovin/impl/sdk/cm.d:Ljava/lang/String;
        //     8: ldc             Ljava/lang/String;.class
        //    10: invokespecial   com/applovin/impl/sdk/ef.<init>:(Ljava/lang/String;Ljava/lang/Class;)V
        //    13: astore_1       
        //    14: aload_0        
        //    15: getfield        com/applovin/impl/sdk/cm.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    18: aload_1        
        //    19: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ef;)Ljava/lang/Object;
        //    22: checkcast       Ljava/lang/String;
        //    25: astore_1       
        //    26: aload_1        
        //    27: ifnull          93
        //    30: aload_1        
        //    31: invokevirtual   java/lang/String.isEmpty:()Z
        //    34: ifne            93
        //    37: new             Lorg/json/JSONObject;
        //    40: dup            
        //    41: aload_1        
        //    42: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    45: invokestatic    com/applovin/impl/sdk/bu.a:(Lorg/json/JSONObject;)Ljava/util/Map;
        //    48: astore_1       
        //    49: aload_0        
        //    50: getfield        com/applovin/impl/sdk/cm.b:Lcom/applovin/sdk/AppLovinLogger;
        //    53: ldc             "MediationAdapterConfigWrapper"
        //    55: new             Ljava/lang/StringBuilder;
        //    58: dup            
        //    59: invokespecial   java/lang/StringBuilder.<init>:()V
        //    62: ldc             "Last known config for '"
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    67: aload_0        
        //    68: getfield        com/applovin/impl/sdk/cm.c:Ljava/lang/String;
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: ldc             "' is: "
        //    76: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    79: aload_1        
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    83: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    86: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    91: aload_1        
        //    92: areturn        
        //    93: aload_0        
        //    94: getfield        com/applovin/impl/sdk/cm.b:Lcom/applovin/sdk/AppLovinLogger;
        //    97: ldc             "MediationAdapterConfigWrapper"
        //    99: new             Ljava/lang/StringBuilder;
        //   102: dup            
        //   103: invokespecial   java/lang/StringBuilder.<init>:()V
        //   106: ldc             "Last known config for '"
        //   108: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   111: aload_0        
        //   112: getfield        com/applovin/impl/sdk/cm.c:Ljava/lang/String;
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: ldc             "' is missing"
        //   120: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   123: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   126: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   131: aconst_null    
        //   132: areturn        
        //   133: astore_2       
        //   134: aconst_null    
        //   135: astore_1       
        //   136: aload_0        
        //   137: getfield        com/applovin/impl/sdk/cm.b:Lcom/applovin/sdk/AppLovinLogger;
        //   140: ldc             "MediationAdapterConfigWrapper"
        //   142: new             Ljava/lang/StringBuilder;
        //   145: dup            
        //   146: invokespecial   java/lang/StringBuilder.<init>:()V
        //   149: ldc             "Unable to load the last known configuration for "
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: aload_0        
        //   155: getfield        com/applovin/impl/sdk/cm.c:Ljava/lang/String;
        //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   161: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   164: aload_2        
        //   165: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   170: aload_1        
        //   171: areturn        
        //   172: astore_2       
        //   173: goto            136
        //    Signature:
        //  ()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      26     133    136    Ljava/lang/Throwable;
        //  30     49     133    136    Ljava/lang/Throwable;
        //  49     91     172    176    Ljava/lang/Throwable;
        //  93     131    133    136    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0093:
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
    
    void a(final Map<String, String> f) {
        final Object e = this.e;
        // monitorenter(e)
        Label_0033: {
            if (f == null) {
                break Label_0033;
            }
            try {
                if (!f.isEmpty()) {
                    this.b(this.f = f);
                }
                else {
                    this.f = this.a();
                }
            }
            finally {
            }
            // monitorexit(e)
        }
    }
    
    void b(final Map<String, String> f) {
        if (f == null) {
            return;
        }
        try {
            this.a.put(new ef<String>(this.d, String.class), bu.a(f).toString());
            synchronized (this.e) {
                this.f = f;
            }
        }
        catch (Throwable t) {
            this.b.e("MediationAdapterConfigWrapper", "Unable to save the last known configuration for " + this.c, t);
        }
    }
    
    @Override
    public Boolean getBoolean(String string, Boolean value) {
        if (string == null) {
            throw new IllegalArgumentException("No key specified");
        }
        string = this.getString(string, null);
        if (string != null) {
            value = Boolean.parseBoolean(string);
        }
        return value;
    }
    
    @Override
    public boolean getBoolean(final String s) {
        return this.getBoolean(s, false);
    }
    
    @Override
    public int getInt(final String s, final int n) {
        if (s == null) {
            throw new IllegalArgumentException("No key specified");
        }
        final String string = this.getString(s, null);
        int int1 = n;
        if (string == null) {
            return int1;
        }
        int1 = n;
        if (!gd.d(string)) {
            return int1;
        }
        try {
            int1 = Integer.parseInt(string);
            return int1;
        }
        catch (NumberFormatException ex) {
            this.b.w("MediationAdapterConfigWrapper", "Unable to parse int for " + s, ex);
            return n;
        }
    }
    
    @Override
    public long getLong(final String s, final long n) {
        if (s == null) {
            throw new IllegalArgumentException("No key specified");
        }
        final String string = this.getString(s, null);
        long long1 = n;
        if (string == null) {
            return long1;
        }
        try {
            final String trim = string.replace("L", "").trim();
            long1 = n;
            if (gd.d(trim)) {
                long1 = Long.parseLong(trim);
            }
            return long1;
        }
        catch (NumberFormatException ex) {
            this.b.w("MediationAdapterConfigWrapper", "Unable to parse long for " + s, ex);
            return n;
        }
    }
    
    @Override
    public String getString(String s, final String s2) {
        if (s == null) {
            throw new IllegalArgumentException("No key specified");
        }
        final String string;
        synchronized (this.e) {
            if (this.f != null && this.f.containsKey(s)) {
                s = this.f.get(s);
                return (String)s;
            }
            string = "applovin.mediation." + this.c + ":" + (String)s;
            if (this.h.containsKey(string)) {
                s = this.h.get(string);
                return (String)s;
            }
        }
        if (this.g.contains(string)) {
            // monitorexit(o)
            return s2;
        }
        while (true) {
            try {
                final ApplicationInfo applicationInfo = this.a.getApplicationContext().getPackageManager().getApplicationInfo(this.a.getApplicationContext().getPackageName(), 128);
                if (applicationInfo.metaData != null) {
                    final String value = String.valueOf(applicationInfo.metaData.get(string));
                    if (value != null) {
                        this.h.put(string, value);
                        // monitorexit(o)
                        return value;
                    }
                    this.g.add(string);
                }
                // monitorexit(o)
                return s2;
            }
            catch (Throwable t) {
                this.b.e("MediationAdapterConfigWrapper", "Unable to load " + (String)s + "from AndroidManifest.xml", t);
                continue;
            }
            break;
        }
    }
}
