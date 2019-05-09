// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

class a
{
    private static final Object a;
    private static final Map<String, Map<String, String>> b;
    
    static {
        a = new Object();
        b = new HashMap<String, Map<String, String>>();
    }
    
    static Map<String, String> a(final AppLovinSdkImpl appLovinSdkImpl) {
        synchronized (com.applovin.impl.sdk.a.a) {
            appLovinSdkImpl.getLogger().d("AdDataCache", "Reading cached device data...");
            return c(appLovinSdkImpl);
        }
    }
    
    private static void a(final String s, final Map<String, String> map) {
        final List<String> a = aa.a(s, "=");
        if (a.size() == 2) {
            map.put(a.get(0), a.get(1));
        }
    }
    
    static void a(final Map<String, String> map, final AppLovinSdkImpl appLovinSdkImpl) {
        b(map, appLovinSdkImpl);
    }
    
    static void b(final AppLovinSdkImpl appLovinSdkImpl) {
        synchronized (com.applovin.impl.sdk.a.a) {
            appLovinSdkImpl.getLogger().d("AdDataCache", "Clearing old device data cache...");
            a(new HashMap<String, String>(0), appLovinSdkImpl);
        }
    }
    
    private static void b(final Map<String, String> map, final AppLovinSdkImpl appLovinSdkImpl) {
        if (map == null) {
            throw new IllegalArgumentException("No ad aata specified");
        }
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        try {
            synchronized (com.applovin.impl.sdk.a.b) {
                Map<String, String> map2;
                if ((map2 = com.applovin.impl.sdk.a.b.get("ad_data_cache")) == null) {
                    map2 = new HashMap<String, String>();
                }
                map2.clear();
                map2.putAll(map);
                com.applovin.impl.sdk.a.b.put("ad_data_cache", map2);
                // monitorexit(a.b)
                appLovinSdkImpl.put(ef.d, gd.a(map));
                appLovinSdkImpl.getLogger().d("AdDataCache", map.size() + " " + "ad_data_cache" + " entries saved in cache");
            }
        }
        catch (Exception ex) {
            appLovinSdkImpl.getLogger().e("AdDataCache", "Unable to save ad data entries", ex);
        }
    }
    
    private static Map<String, String> c(final AppLovinSdkImpl p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1       
        //     4: aload_1        
        //     5: monitorenter   
        //     6: getstatic       com/applovin/impl/sdk/a.b:Ljava/util/Map;
        //     9: ldc             "ad_data_cache"
        //    11: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    16: checkcast       Ljava/util/Map;
        //    19: astore_2       
        //    20: aload_1        
        //    21: monitorexit    
        //    22: aload_2        
        //    23: ifnonnull       222
        //    26: aload_0        
        //    27: getstatic       com/applovin/impl/sdk/ef.d:Lcom/applovin/impl/sdk/ef;
        //    30: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ef;)Ljava/lang/Object;
        //    33: checkcast       Ljava/lang/String;
        //    36: ldc             "&"
        //    38: invokestatic    com/applovin/impl/sdk/aa.a:(Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //    41: astore_3       
        //    42: aload_3        
        //    43: invokeinterface java/util/List.isEmpty:()Z
        //    48: ifne            222
        //    51: new             Ljava/util/HashMap;
        //    54: dup            
        //    55: invokespecial   java/util/HashMap.<init>:()V
        //    58: astore_1       
        //    59: aload_3        
        //    60: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    65: astore_2       
        //    66: aload_2        
        //    67: invokeinterface java/util/Iterator.hasNext:()Z
        //    72: ifeq            133
        //    75: aload_2        
        //    76: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    81: checkcast       Ljava/lang/String;
        //    84: aload_1        
        //    85: invokestatic    com/applovin/impl/sdk/a.a:(Ljava/lang/String;Ljava/util/Map;)V
        //    88: goto            66
        //    91: astore_2       
        //    92: aload_0        
        //    93: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getLogger:()Lcom/applovin/sdk/AppLovinLogger;
        //    96: ldc             "AdDataCache"
        //    98: ldc             "Unable to load ad data"
        //   100: aload_2        
        //   101: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   106: aload_0        
        //   107: getstatic       com/applovin/impl/sdk/ef.d:Lcom/applovin/impl/sdk/ef;
        //   110: ldc             ""
        //   112: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.put:(Lcom/applovin/impl/sdk/ef;Ljava/lang/Object;)V
        //   115: aload_1        
        //   116: ifnull          206
        //   119: new             Ljava/util/HashMap;
        //   122: dup            
        //   123: aload_1        
        //   124: invokespecial   java/util/HashMap.<init>:(Ljava/util/Map;)V
        //   127: areturn        
        //   128: astore_0       
        //   129: aload_1        
        //   130: monitorexit    
        //   131: aload_0        
        //   132: athrow         
        //   133: getstatic       com/applovin/impl/sdk/a.b:Ljava/util/Map;
        //   136: astore_2       
        //   137: aload_2        
        //   138: monitorenter   
        //   139: getstatic       com/applovin/impl/sdk/a.b:Ljava/util/Map;
        //   142: ldc             "ad_data_cache"
        //   144: aload_1        
        //   145: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   150: pop            
        //   151: aload_2        
        //   152: monitorexit    
        //   153: aload_0        
        //   154: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getLogger:()Lcom/applovin/sdk/AppLovinLogger;
        //   157: ldc             "AdDataCache"
        //   159: new             Ljava/lang/StringBuilder;
        //   162: dup            
        //   163: invokespecial   java/lang/StringBuilder.<init>:()V
        //   166: aload_1        
        //   167: invokeinterface java/util/Map.size:()I
        //   172: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   175: ldc             " "
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: ldc             "ad_data_cache"
        //   182: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   185: ldc             " entries loaded from cache"
        //   187: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   190: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   193: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   198: goto            115
        //   201: astore_3       
        //   202: aload_2        
        //   203: monitorexit    
        //   204: aload_3        
        //   205: athrow         
        //   206: new             Ljava/util/HashMap;
        //   209: dup            
        //   210: invokespecial   java/util/HashMap.<init>:()V
        //   213: areturn        
        //   214: astore_3       
        //   215: aload_2        
        //   216: astore_1       
        //   217: aload_3        
        //   218: astore_2       
        //   219: goto            92
        //   222: aload_2        
        //   223: astore_1       
        //   224: goto            115
        //    Signature:
        //  (Lcom/applovin/impl/sdk/AppLovinSdkImpl;)Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      22     128    133    Any
        //  51     59     214    222    Ljava/lang/Exception;
        //  59     66     91     92     Ljava/lang/Exception;
        //  66     88     91     92     Ljava/lang/Exception;
        //  129    131    128    133    Any
        //  133    139    91     92     Ljava/lang/Exception;
        //  139    153    201    206    Any
        //  153    198    91     92     Ljava/lang/Exception;
        //  202    204    201    206    Any
        //  204    206    91     92     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
}
