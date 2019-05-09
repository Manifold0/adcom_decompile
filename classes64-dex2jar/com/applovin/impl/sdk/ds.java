// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;

abstract class ds implements at, AppLovinNativeAdLoadListener
{
    protected final AppLovinSdkImpl a;
    protected final AppLovinLogger b;
    protected final Object c;
    protected final Map<n, dt> d;
    protected final Map<n, dt> e;
    protected final Map<n, Object> f;
    protected final Set<n> g;
    
    ds(final AppLovinSdkImpl a) {
        this.a = a;
        this.b = a.getLogger();
        this.c = new Object();
        this.d = new HashMap<n, dt>();
        this.e = new HashMap<n, dt>();
        this.f = new HashMap<n, Object>();
        this.g = new HashSet<n>();
        this.a();
    }
    
    private dt l(final n n) {
        return this.d.get(n);
    }
    
    private dt m(final n n) {
        return this.e.get(n);
    }
    
    private dt n(final n n) {
        synchronized (this.c) {
            final dt m = this.m(n);
            if (m != null && m.a() > 0) {
                return m;
            }
            return this.l(n);
        }
    }
    
    abstract dx a(final n p0);
    
    abstract n a(final cj p0);
    
    abstract void a();
    
    abstract void a(final Object p0, final cj p1);
    
    abstract void a(final Object p0, final n p1, final int p2);
    
    public void a(final LinkedHashSet<n> set) {
        if (this.f == null || this.f.isEmpty()) {
            return;
        }
        synchronized (this.c) {
            final Iterator<n> iterator = this.f.keySet().iterator();
            while (iterator.hasNext()) {
                final n n = iterator.next();
                if (!n.m() && !set.contains(n)) {
                    final Object value = this.f.get(n);
                    iterator.remove();
                    this.b.userError("AppLovinAdService", "Failed to load ad for zone (" + n.a() + "). Please check that the zone has been added to your AppLovin account.");
                    this.a(value, n, -7);
                }
            }
        }
    }
    // monitorexit(o)
    
    public boolean a(final n n, final Object o) {
        while (true) {
            synchronized (this.c) {
                if (!this.k(n)) {
                    this.b(n, o);
                    return true;
                }
            }
            return false;
        }
    }
    
    void b(final cj cj) {
        this.j(this.a(cj));
    }
    
    public void b(final n n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            this.j(n);
        }
    }
    
    public void b(final n n, final Object o) {
        synchronized (this.c) {
            if (this.f.containsKey(n)) {
                this.b.w("PreloadManager", "Possibly missing prior registered preload callback.");
            }
            this.f.put(n, o);
        }
    }
    
    public boolean b(final n n) {
        return this.f.containsKey(n);
    }
    
    public cj c(final n n) {
        while (true) {
            synchronized (this.c) {
                final dt n2 = this.n(n);
                if (n2 != null) {
                    return n2.f();
                }
            }
            return null;
        }
    }
    
    protected void c(final cj p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: invokevirtual   com/applovin/impl/sdk/ds.a:(Lcom/applovin/impl/sdk/cj;)Lcom/applovin/impl/sdk/n;
        //     5: astore          4
        //     7: aload           4
        //     9: invokevirtual   com/applovin/impl/sdk/n.l:()Z
        //    12: istore_2       
        //    13: aload_0        
        //    14: getfield        com/applovin/impl/sdk/ds.c:Ljava/lang/Object;
        //    17: astore_3       
        //    18: aload_3        
        //    19: monitorenter   
        //    20: aload_0        
        //    21: getfield        com/applovin/impl/sdk/ds.f:Ljava/util/Map;
        //    24: aload           4
        //    26: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    31: astore          5
        //    33: aload_0        
        //    34: getfield        com/applovin/impl/sdk/ds.f:Ljava/util/Map;
        //    37: aload           4
        //    39: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    44: pop            
        //    45: aload_0        
        //    46: getfield        com/applovin/impl/sdk/ds.g:Ljava/util/Set;
        //    49: aload           4
        //    51: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    56: pop            
        //    57: aload           5
        //    59: ifnull          66
        //    62: iload_2        
        //    63: ifeq            197
        //    66: aload_0        
        //    67: aload           4
        //    69: invokespecial   com/applovin/impl/sdk/ds.l:(Lcom/applovin/impl/sdk/n;)Lcom/applovin/impl/sdk/dt;
        //    72: aload_1        
        //    73: invokevirtual   com/applovin/impl/sdk/dt.a:(Lcom/applovin/impl/sdk/cj;)V
        //    76: aload_0        
        //    77: getfield        com/applovin/impl/sdk/ds.b:Lcom/applovin/sdk/AppLovinLogger;
        //    80: ldc             "PreloadManager"
        //    82: new             Ljava/lang/StringBuilder;
        //    85: dup            
        //    86: invokespecial   java/lang/StringBuilder.<init>:()V
        //    89: ldc             "Ad enqueued: "
        //    91: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    94: aload_1        
        //    95: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    98: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   101: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   106: aload_3        
        //   107: monitorexit    
        //   108: aload           5
        //   110: ifnull          166
        //   113: aload_0        
        //   114: getfield        com/applovin/impl/sdk/ds.b:Lcom/applovin/sdk/AppLovinLogger;
        //   117: ldc             "PreloadManager"
        //   119: new             Ljava/lang/StringBuilder;
        //   122: dup            
        //   123: invokespecial   java/lang/StringBuilder.<init>:()V
        //   126: ldc             "Called additional callback regarding "
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: aload_1        
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   135: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   138: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   143: iload_2        
        //   144: ifeq            218
        //   147: aload_0        
        //   148: aload           5
        //   150: new             Lcom/applovin/impl/sdk/aq;
        //   153: dup            
        //   154: aload           4
        //   156: aload_0        
        //   157: getfield        com/applovin/impl/sdk/ds.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   160: invokespecial   com/applovin/impl/sdk/aq.<init>:(Lcom/applovin/impl/sdk/n;Lcom/applovin/impl/sdk/AppLovinSdkImpl;)V
        //   163: invokevirtual   com/applovin/impl/sdk/ds.a:(Ljava/lang/Object;Lcom/applovin/impl/sdk/cj;)V
        //   166: aload_0        
        //   167: getfield        com/applovin/impl/sdk/ds.b:Lcom/applovin/sdk/AppLovinLogger;
        //   170: ldc             "PreloadManager"
        //   172: new             Ljava/lang/StringBuilder;
        //   175: dup            
        //   176: invokespecial   java/lang/StringBuilder.<init>:()V
        //   179: ldc             "Pulled ad from network and saved to preload cache: "
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: aload_1        
        //   185: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   188: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   191: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   196: return         
        //   197: aload_0        
        //   198: getfield        com/applovin/impl/sdk/ds.b:Lcom/applovin/sdk/AppLovinLogger;
        //   201: ldc             "PreloadManager"
        //   203: ldc             "Additional callback found or dummy ads are enabled; skipping enqueue..."
        //   205: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   210: goto            106
        //   213: astore_1       
        //   214: aload_3        
        //   215: monitorexit    
        //   216: aload_1        
        //   217: athrow         
        //   218: aload_0        
        //   219: aload           5
        //   221: aload_1        
        //   222: invokevirtual   com/applovin/impl/sdk/ds.a:(Ljava/lang/Object;Lcom/applovin/impl/sdk/cj;)V
        //   225: aload_0        
        //   226: aload_1        
        //   227: invokevirtual   com/applovin/impl/sdk/ds.b:(Lcom/applovin/impl/sdk/cj;)V
        //   230: goto            166
        //   233: astore_3       
        //   234: aload_0        
        //   235: getfield        com/applovin/impl/sdk/ds.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   238: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getLogger:()Lcom/applovin/sdk/AppLovinLogger;
        //   241: ldc             "PreloadManager"
        //   243: ldc             "Encountered throwable while notifying user callback"
        //   245: aload_3        
        //   246: invokeinterface com/applovin/sdk/AppLovinLogger.userError:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   251: goto            166
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  20     57     213    218    Any
        //  66     106    213    218    Any
        //  106    108    213    218    Any
        //  147    166    233    254    Ljava/lang/Throwable;
        //  197    210    213    218    Any
        //  214    216    213    218    Any
        //  218    230    233    254    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0166:
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
    
    protected void c(final n p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/applovin/impl/sdk/ds.b:Lcom/applovin/sdk/AppLovinLogger;
        //     4: ldc             "PreloadManager"
        //     6: new             Ljava/lang/StringBuilder;
        //     9: dup            
        //    10: invokespecial   java/lang/StringBuilder.<init>:()V
        //    13: ldc             "Failed to pre-load an ad of zone "
        //    15: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    18: aload_1        
        //    19: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    22: ldc             ", error code "
        //    24: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    27: iload_2        
        //    28: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    31: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    34: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    39: aload_0        
        //    40: getfield        com/applovin/impl/sdk/ds.c:Ljava/lang/Object;
        //    43: astore_3       
        //    44: aload_3        
        //    45: monitorenter   
        //    46: aload_0        
        //    47: getfield        com/applovin/impl/sdk/ds.f:Ljava/util/Map;
        //    50: aload_1        
        //    51: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    56: astore          4
        //    58: aload_0        
        //    59: getfield        com/applovin/impl/sdk/ds.g:Ljava/util/Set;
        //    62: aload_1        
        //    63: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    68: pop            
        //    69: aload_3        
        //    70: monitorexit    
        //    71: aload           4
        //    73: ifnull          84
        //    76: aload_0        
        //    77: aload           4
        //    79: aload_1        
        //    80: iload_2        
        //    81: invokevirtual   com/applovin/impl/sdk/ds.a:(Ljava/lang/Object;Lcom/applovin/impl/sdk/n;I)V
        //    84: return         
        //    85: astore_1       
        //    86: aload_3        
        //    87: monitorexit    
        //    88: aload_1        
        //    89: athrow         
        //    90: astore_1       
        //    91: aload_0        
        //    92: getfield        com/applovin/impl/sdk/ds.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    95: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getLogger:()Lcom/applovin/sdk/AppLovinLogger;
        //    98: ldc             "PreloadManager"
        //   100: ldc             "Encountered exception while invoking user callback"
        //   102: aload_1        
        //   103: invokeinterface com/applovin/sdk/AppLovinLogger.userError:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   108: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  46     71     85     90     Any
        //  76     84     90     109    Ljava/lang/Throwable;
        //  86     88     85     90     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0084:
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
    
    public cj d(final n n) {
        while (true) {
            synchronized (this.c) {
                final dt n2 = this.n(n);
                if (n2 != null) {
                    return n2.e();
                }
            }
            return null;
        }
    }
    
    public cj e(final n n) {
        Object o = null;
        while (true) {
            while (true) {
                Label_0183: {
                    Label_0175: {
                        final dt m;
                        Label_0132: {
                            synchronized (this.c) {
                                o = this.l(n);
                                if (o == null) {
                                    break Label_0183;
                                }
                                if (!n.l()) {
                                    break Label_0175;
                                }
                                m = this.m(n);
                                if (m.c()) {
                                    o = new aq(n, this.a);
                                }
                                else {
                                    if (((dt)o).a() <= 0) {
                                        break Label_0132;
                                    }
                                    m.a(((dt)o).e());
                                    o = new aq(n, this.a);
                                }
                                // monitorexit(this.c)
                                if (o != null) {
                                    this.b.d("PreloadManager", "Retrieved ad of zone " + n + "...");
                                    return (cj)o;
                                }
                                break;
                            }
                        }
                        if (m.a() > 0 && this.a.get(ea.cV)) {
                            o = new aq(n, this.a);
                            continue;
                        }
                        o = null;
                        continue;
                    }
                    o = ((dt)o).e();
                    continue;
                }
                o = null;
                continue;
            }
        }
        this.b.d("PreloadManager", "Unable to retrieve ad of zone " + n + "...");
        return (cj)o;
    }
    
    public boolean f(final n n) {
        while (true) {
            synchronized (this.c) {
                final dt l = this.l(n);
                if (l != null) {
                    return l.c();
                }
            }
            return false;
        }
    }
    
    public void g(final n n) {
        if (n == null) {
            return;
        }
        int n2 = 0;
        synchronized (this.c) {
            final dt l = this.l(n);
            if (l != null) {
                n2 = l.b() - l.a();
            }
            // monitorexit(this.c)
            this.b(n, n2);
        }
    }
    
    public boolean h(final n n) {
        while (true) {
            synchronized (this.c) {
                final dt m = this.m(n);
                if (this.a.get(ea.cW) && m != null && m.a() > 0) {
                    return true;
                }
                final dt l = this.l(n);
                if (l == null) {
                    return false;
                }
                if (!l.d()) {
                    return true;
                }
            }
            return false;
            b = false;
            return b;
        }
    }
    
    public void i(final n n) {
        while (true) {
            synchronized (this.c) {
                final dt l = this.l(n);
                if (l != null) {
                    l.a(n.f());
                }
                else {
                    this.d.put(n, new dt(n.f()));
                }
                final dt m = this.m(n);
                if (m != null) {
                    m.a(n.g());
                    return;
                }
            }
            final n n2;
            this.e.put(n2, new dt(n2.g()));
        }
    }
    
    public void j(final n n) {
        if (this.a.get(ea.J) && !this.f(n)) {
            this.b.d("PreloadManager", "Preloading ad for zone " + n + "...");
            this.a.getTaskManager().a(this.a(n), fe.a, 500L);
        }
    }
    
    boolean k(final n n) {
        synchronized (this.c) {
            return this.g.contains(n);
        }
    }
}
