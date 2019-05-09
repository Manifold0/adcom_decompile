// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import java.util.Map;
import android.os.SystemClock;

public final class gb
{
    final gf a;
    final ga b;
    long c;
    private int d;
    private final ea.a e;
    
    gb(final gf a, final ga b) {
        this.d = 1;
        this.e = new ea.a();
        this.a = a;
        this.b = b;
    }
    
    public final dy.a a(final eb c, final String d) {
        final ee b = this.a.b();
        final dy.a a = new dy.a();
        a.g = gf.a;
        a.c = c;
        a.d = d;
        if (y.c()) {
            a.e = y.b();
            a.f = System.currentTimeMillis();
        }
        else {
            a.e = System.currentTimeMillis();
            a.h = SystemClock.elapsedRealtime();
        }
        a.j = b.d;
        a.k = b.e;
        a.l = b.f;
        return a;
    }
    
    public final void a(final dy.a a) {
        while (true) {
            ga b;
            synchronized (this) {
                if (a.c != eb.USAGES) {
                    a.n = this.d++;
                    if (this.e.c != null) {
                        a.o = this.e.b();
                    }
                    this.e.c = a.c;
                    this.e.d = a.d;
                    this.e.e = a.t;
                }
                while (true) {
                    b = this.b;
                    final dy b2 = a.b();
                    try {
                        final go a2 = b.a;
                        synchronized (a2.a) {
                            while (true) {
                                try {
                                    a2.b.add(b2);
                                    // monitorexit(a2.a)
                                    if (b.b == null) {
                                        break;
                                    }
                                    if (fz.a || b2.n != eb.CUSTOM) {
                                        b.a(true);
                                        return;
                                    }
                                }
                                catch (Exception ex) {
                                    a2.a();
                                    try {
                                        a2.b.add(b2);
                                        continue;
                                    }
                                    catch (Exception a2) {
                                        continue;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    catch (Exception ex2) {
                        return;
                    }
                    b.a(false);
                    return;
                }
            }
            b.a.flush();
        }
    }
    
    public final void a(final String p0, final String p1, final double p2, final String p3, final String p4, final String p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/tapjoy/internal/gb.a:Lcom/tapjoy/internal/gf;
        //     4: astore          13
        //     6: aload           13
        //     8: monitorenter   
        //     9: aload           13
        //    11: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //    14: invokevirtual   com/tapjoy/internal/gm.a:()Landroid/content/SharedPreferences$Editor;
        //    17: astore          14
        //    19: aload_2        
        //    20: aload           13
        //    22: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //    25: getfield        com/tapjoy/internal/gm.l:Lcom/tapjoy/internal/q;
        //    28: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //    31: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    34: ifeq            318
        //    37: aload           13
        //    39: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //    42: getfield        com/tapjoy/internal/gm.m:Lcom/tapjoy/internal/m;
        //    45: invokevirtual   com/tapjoy/internal/m.b:()I
        //    48: iconst_1       
        //    49: iadd           
        //    50: istore          10
        //    52: aload           13
        //    54: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //    57: getfield        com/tapjoy/internal/gm.m:Lcom/tapjoy/internal/m;
        //    60: aload           14
        //    62: iload           10
        //    64: invokevirtual   com/tapjoy/internal/m.a:(Landroid/content/SharedPreferences$Editor;I)Landroid/content/SharedPreferences$Editor;
        //    67: pop            
        //    68: aload           13
        //    70: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //    73: getfield        com/tapjoy/internal/gm.n:Lcom/tapjoy/internal/k;
        //    76: invokevirtual   com/tapjoy/internal/k.a:()D
        //    79: dload_3        
        //    80: dadd           
        //    81: dstore          8
        //    83: aload           13
        //    85: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //    88: getfield        com/tapjoy/internal/gm.n:Lcom/tapjoy/internal/k;
        //    91: aload           14
        //    93: dload           8
        //    95: invokevirtual   com/tapjoy/internal/k.a:(Landroid/content/SharedPreferences$Editor;D)Landroid/content/SharedPreferences$Editor;
        //    98: pop            
        //    99: aload           14
        //   101: invokeinterface android/content/SharedPreferences$Editor.commit:()Z
        //   106: pop            
        //   107: aload           13
        //   109: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   112: iload           10
        //   114: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   117: putfield        com/tapjoy/internal/ek$a.m:Ljava/lang/Integer;
        //   120: aload           13
        //   122: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   125: dload           8
        //   127: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   130: putfield        com/tapjoy/internal/ek$a.n:Ljava/lang/Double;
        //   133: aload           13
        //   135: monitorexit    
        //   136: aload_0        
        //   137: getstatic       com/tapjoy/internal/eb.APP:Lcom/tapjoy/internal/eb;
        //   140: ldc             "purchase"
        //   142: invokevirtual   com/tapjoy/internal/gb.a:(Lcom/tapjoy/internal/eb;Ljava/lang/String;)Lcom/tapjoy/internal/dy$a;
        //   145: astore          13
        //   147: new             Lcom/tapjoy/internal/eg$a;
        //   150: dup            
        //   151: invokespecial   com/tapjoy/internal/eg$a.<init>:()V
        //   154: astore          14
        //   156: aload           14
        //   158: aload_1        
        //   159: putfield        com/tapjoy/internal/eg$a.c:Ljava/lang/String;
        //   162: aload_2        
        //   163: ifnull          172
        //   166: aload           14
        //   168: aload_2        
        //   169: putfield        com/tapjoy/internal/eg$a.f:Ljava/lang/String;
        //   172: aload           14
        //   174: dload_3        
        //   175: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   178: putfield        com/tapjoy/internal/eg$a.e:Ljava/lang/Double;
        //   181: aload           7
        //   183: ifnull          193
        //   186: aload           14
        //   188: aload           7
        //   190: putfield        com/tapjoy/internal/eg$a.m:Ljava/lang/String;
        //   193: aload           5
        //   195: ifnull          205
        //   198: aload           14
        //   200: aload           5
        //   202: putfield        com/tapjoy/internal/eg$a.o:Ljava/lang/String;
        //   205: aload           6
        //   207: ifnull          217
        //   210: aload           14
        //   212: aload           6
        //   214: putfield        com/tapjoy/internal/eg$a.p:Ljava/lang/String;
        //   217: aload           13
        //   219: aload           14
        //   221: invokevirtual   com/tapjoy/internal/eg$a.b:()Lcom/tapjoy/internal/eg;
        //   224: putfield        com/tapjoy/internal/dy$a.p:Lcom/tapjoy/internal/eg;
        //   227: aload_0        
        //   228: aload           13
        //   230: invokevirtual   com/tapjoy/internal/gb.a:(Lcom/tapjoy/internal/dy$a;)V
        //   233: aload_0        
        //   234: getfield        com/tapjoy/internal/gb.a:Lcom/tapjoy/internal/gf;
        //   237: astore_1       
        //   238: aload           13
        //   240: getfield        com/tapjoy/internal/dy$a.e:Ljava/lang/Long;
        //   243: invokevirtual   java/lang/Long.longValue:()J
        //   246: lstore          11
        //   248: aload_1        
        //   249: monitorenter   
        //   250: aload_1        
        //   251: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   254: invokevirtual   com/tapjoy/internal/gm.a:()Landroid/content/SharedPreferences$Editor;
        //   257: astore_2       
        //   258: aload_1        
        //   259: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   262: getfield        com/tapjoy/internal/gm.o:Lcom/tapjoy/internal/n;
        //   265: aload_2        
        //   266: lload           11
        //   268: invokevirtual   com/tapjoy/internal/n.a:(Landroid/content/SharedPreferences$Editor;J)Landroid/content/SharedPreferences$Editor;
        //   271: pop            
        //   272: aload_1        
        //   273: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   276: getfield        com/tapjoy/internal/gm.p:Lcom/tapjoy/internal/k;
        //   279: aload_2        
        //   280: dload_3        
        //   281: invokevirtual   com/tapjoy/internal/k.a:(Landroid/content/SharedPreferences$Editor;D)Landroid/content/SharedPreferences$Editor;
        //   284: pop            
        //   285: aload_2        
        //   286: invokeinterface android/content/SharedPreferences$Editor.commit:()Z
        //   291: pop            
        //   292: aload_1        
        //   293: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   296: lload           11
        //   298: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   301: putfield        com/tapjoy/internal/ek$a.o:Ljava/lang/Long;
        //   304: aload_1        
        //   305: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   308: dload_3        
        //   309: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   312: putfield        com/tapjoy/internal/ek$a.p:Ljava/lang/Double;
        //   315: aload_1        
        //   316: monitorexit    
        //   317: return         
        //   318: aload           13
        //   320: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   323: getfield        com/tapjoy/internal/gm.l:Lcom/tapjoy/internal/q;
        //   326: aload           14
        //   328: aload_2        
        //   329: invokevirtual   com/tapjoy/internal/q.a:(Landroid/content/SharedPreferences$Editor;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
        //   332: pop            
        //   333: aload           13
        //   335: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   338: getfield        com/tapjoy/internal/gm.m:Lcom/tapjoy/internal/m;
        //   341: aload           14
        //   343: iconst_1       
        //   344: invokevirtual   com/tapjoy/internal/m.a:(Landroid/content/SharedPreferences$Editor;I)Landroid/content/SharedPreferences$Editor;
        //   347: pop            
        //   348: aload           13
        //   350: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   353: getfield        com/tapjoy/internal/gm.n:Lcom/tapjoy/internal/k;
        //   356: aload           14
        //   358: dload_3        
        //   359: invokevirtual   com/tapjoy/internal/k.a:(Landroid/content/SharedPreferences$Editor;D)Landroid/content/SharedPreferences$Editor;
        //   362: pop            
        //   363: aload           13
        //   365: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   368: getfield        com/tapjoy/internal/gm.o:Lcom/tapjoy/internal/n;
        //   371: aload           14
        //   373: invokevirtual   com/tapjoy/internal/n.a:(Landroid/content/SharedPreferences$Editor;)Landroid/content/SharedPreferences$Editor;
        //   376: pop            
        //   377: aload           13
        //   379: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   382: getfield        com/tapjoy/internal/gm.p:Lcom/tapjoy/internal/k;
        //   385: aload           14
        //   387: invokevirtual   com/tapjoy/internal/k.a:(Landroid/content/SharedPreferences$Editor;)Landroid/content/SharedPreferences$Editor;
        //   390: pop            
        //   391: aload           14
        //   393: invokeinterface android/content/SharedPreferences$Editor.commit:()Z
        //   398: pop            
        //   399: aload           13
        //   401: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   404: aload_2        
        //   405: putfield        com/tapjoy/internal/ek$a.l:Ljava/lang/String;
        //   408: aload           13
        //   410: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   413: aconst_null    
        //   414: putfield        com/tapjoy/internal/ek$a.o:Ljava/lang/Long;
        //   417: aload           13
        //   419: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
        //   422: aconst_null    
        //   423: putfield        com/tapjoy/internal/ek$a.p:Ljava/lang/Double;
        //   426: iconst_1       
        //   427: istore          10
        //   429: dload_3        
        //   430: dstore          8
        //   432: goto            107
        //   435: astore_1       
        //   436: aload           13
        //   438: monitorexit    
        //   439: aload_1        
        //   440: athrow         
        //   441: astore_2       
        //   442: aload_1        
        //   443: monitorexit    
        //   444: aload_2        
        //   445: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      107    435    441    Any
        //  107    136    435    441    Any
        //  250    317    441    446    Any
        //  318    426    435    441    Any
        //  436    439    435    441    Any
        //  442    444    441    446    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0318:
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
    
    public final void a(final String s, final String x, final int n, final long n2, final long n3, final Map map) {
        final dy.a a = this.a(eb.USAGES, s);
        a.x = x;
        a.y = n;
        a.z = n2;
        a.A = n3;
        if (map != null) {
            for (final Map.Entry<String, V> entry : map.entrySet()) {
                a.w.add(new ec(entry.getKey(), (Long)entry.getValue()));
            }
        }
        this.a(a);
    }
    
    public final void a(final String t, final String s, final String u, final String v, final Map map) {
        final dy.a a = this.a(eb.CUSTOM, s);
        a.t = t;
        a.u = u;
        a.v = v;
        if (map != null) {
            for (final Map.Entry<String, V> entry : map.entrySet()) {
                a.w.add(new ec(entry.getKey(), (Long)entry.getValue()));
            }
        }
        this.a(a);
    }
}
