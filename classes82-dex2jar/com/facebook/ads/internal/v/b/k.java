// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;

class k
{
    private final n a;
    private final com.facebook.ads.internal.v.b.a b;
    private final Object c;
    private final Object d;
    private final AtomicInteger e;
    private volatile Thread f;
    private volatile boolean g;
    private volatile int h;
    
    public k(final n n, final com.facebook.ads.internal.v.b.a a) {
        this.c = new Object();
        this.d = new Object();
        this.h = -1;
        this.a = j.a(n);
        this.b = j.a(a);
        this.e = new AtomicInteger();
    }
    
    static /* synthetic */ void a(final k p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_3       
        //     2: iconst_0       
        //     3: istore_1       
        //     4: aload_0        
        //     5: getfield        com/facebook/ads/internal/v/b/k.b:Lcom/facebook/ads/internal/v/b/a;
        //     8: invokeinterface com/facebook/ads/internal/v/b/a.a:()I
        //    13: istore_2       
        //    14: iload_2        
        //    15: istore_1       
        //    16: iload_2        
        //    17: istore_3       
        //    18: aload_0        
        //    19: getfield        com/facebook/ads/internal/v/b/k.a:Lcom/facebook/ads/internal/v/b/n;
        //    22: iload_2        
        //    23: invokeinterface com/facebook/ads/internal/v/b/n.a:(I)V
        //    28: iload_2        
        //    29: istore_1       
        //    30: iload_2        
        //    31: istore_3       
        //    32: aload_0        
        //    33: getfield        com/facebook/ads/internal/v/b/k.a:Lcom/facebook/ads/internal/v/b/n;
        //    36: invokeinterface com/facebook/ads/internal/v/b/n.a:()I
        //    41: istore          5
        //    43: iload_2        
        //    44: istore_3       
        //    45: iload_2        
        //    46: istore_1       
        //    47: iload           5
        //    49: istore          4
        //    51: sipush          8192
        //    54: newarray        B
        //    56: astore          12
        //    58: iload_2        
        //    59: istore_3       
        //    60: iload_2        
        //    61: istore_1       
        //    62: iload           5
        //    64: istore          4
        //    66: aload_0        
        //    67: getfield        com/facebook/ads/internal/v/b/k.a:Lcom/facebook/ads/internal/v/b/n;
        //    70: aload           12
        //    72: invokeinterface com/facebook/ads/internal/v/b/n.a:([B)I
        //    77: istore          6
        //    79: iload           6
        //    81: iconst_m1      
        //    82: if_icmpeq       259
        //    85: iload_2        
        //    86: istore_3       
        //    87: iload_2        
        //    88: istore_1       
        //    89: iload           5
        //    91: istore          4
        //    93: aload_0        
        //    94: getfield        com/facebook/ads/internal/v/b/k.d:Ljava/lang/Object;
        //    97: astore          11
        //    99: iload_2        
        //   100: istore_3       
        //   101: iload_2        
        //   102: istore_1       
        //   103: iload           5
        //   105: istore          4
        //   107: aload           11
        //   109: monitorenter   
        //   110: aload_0        
        //   111: invokespecial   com/facebook/ads/internal/v/b/k.c:()Z
        //   114: ifeq            134
        //   117: aload           11
        //   119: monitorexit    
        //   120: aload_0        
        //   121: invokespecial   com/facebook/ads/internal/v/b/k.d:()V
        //   124: aload_0        
        //   125: iload_2        
        //   126: i2l            
        //   127: iload           5
        //   129: i2l            
        //   130: invokespecial   com/facebook/ads/internal/v/b/k.b:(JJ)V
        //   133: return         
        //   134: aload_0        
        //   135: getfield        com/facebook/ads/internal/v/b/k.b:Lcom/facebook/ads/internal/v/b/a;
        //   138: aload           12
        //   140: iload           6
        //   142: invokeinterface com/facebook/ads/internal/v/b/a.a:([BI)V
        //   147: aload           11
        //   149: monitorexit    
        //   150: iload_2        
        //   151: iload           6
        //   153: iadd           
        //   154: istore_2       
        //   155: iload_2        
        //   156: i2l            
        //   157: lstore          7
        //   159: iload           5
        //   161: i2l            
        //   162: lstore          9
        //   164: iload_2        
        //   165: istore_3       
        //   166: iload_2        
        //   167: istore_1       
        //   168: iload           5
        //   170: istore          4
        //   172: aload_0        
        //   173: lload           7
        //   175: lload           9
        //   177: invokespecial   com/facebook/ads/internal/v/b/k.b:(JJ)V
        //   180: goto            58
        //   183: astore          11
        //   185: iload_3        
        //   186: istore_1       
        //   187: iload           5
        //   189: istore          4
        //   191: aload_0        
        //   192: getfield        com/facebook/ads/internal/v/b/k.e:Ljava/util/concurrent/atomic/AtomicInteger;
        //   195: invokevirtual   java/util/concurrent/atomic/AtomicInteger.incrementAndGet:()I
        //   198: pop            
        //   199: iload_3        
        //   200: istore_1       
        //   201: iload           5
        //   203: istore          4
        //   205: aload_0        
        //   206: aload           11
        //   208: invokevirtual   com/facebook/ads/internal/v/b/k.a:(Ljava/lang/Throwable;)V
        //   211: aload_0        
        //   212: invokespecial   com/facebook/ads/internal/v/b/k.d:()V
        //   215: aload_0        
        //   216: iload_3        
        //   217: i2l            
        //   218: iload           5
        //   220: i2l            
        //   221: invokespecial   com/facebook/ads/internal/v/b/k.b:(JJ)V
        //   224: return         
        //   225: astore          12
        //   227: aload           11
        //   229: monitorexit    
        //   230: iload_2        
        //   231: istore_3       
        //   232: iload_2        
        //   233: istore_1       
        //   234: iload           5
        //   236: istore          4
        //   238: aload           12
        //   240: athrow         
        //   241: astore          11
        //   243: aload_0        
        //   244: invokespecial   com/facebook/ads/internal/v/b/k.d:()V
        //   247: aload_0        
        //   248: iload_1        
        //   249: i2l            
        //   250: iload           4
        //   252: i2l            
        //   253: invokespecial   com/facebook/ads/internal/v/b/k.b:(JJ)V
        //   256: aload           11
        //   258: athrow         
        //   259: iload_2        
        //   260: istore_3       
        //   261: iload_2        
        //   262: istore_1       
        //   263: iload           5
        //   265: istore          4
        //   267: aload_0        
        //   268: getfield        com/facebook/ads/internal/v/b/k.d:Ljava/lang/Object;
        //   271: astore          11
        //   273: iload_2        
        //   274: istore_3       
        //   275: iload_2        
        //   276: istore_1       
        //   277: iload           5
        //   279: istore          4
        //   281: aload           11
        //   283: monitorenter   
        //   284: aload_0        
        //   285: invokespecial   com/facebook/ads/internal/v/b/k.c:()Z
        //   288: ifne            321
        //   291: aload_0        
        //   292: getfield        com/facebook/ads/internal/v/b/k.b:Lcom/facebook/ads/internal/v/b/a;
        //   295: invokeinterface com/facebook/ads/internal/v/b/a.a:()I
        //   300: aload_0        
        //   301: getfield        com/facebook/ads/internal/v/b/k.a:Lcom/facebook/ads/internal/v/b/n;
        //   304: invokeinterface com/facebook/ads/internal/v/b/n.a:()I
        //   309: if_icmpne       321
        //   312: aload_0        
        //   313: getfield        com/facebook/ads/internal/v/b/k.b:Lcom/facebook/ads/internal/v/b/a;
        //   316: invokeinterface com/facebook/ads/internal/v/b/a.c:()V
        //   321: aload           11
        //   323: monitorexit    
        //   324: aload_0        
        //   325: invokespecial   com/facebook/ads/internal/v/b/k.d:()V
        //   328: aload_0        
        //   329: iload_2        
        //   330: i2l            
        //   331: iload           5
        //   333: i2l            
        //   334: invokespecial   com/facebook/ads/internal/v/b/k.b:(JJ)V
        //   337: return         
        //   338: astore          12
        //   340: aload           11
        //   342: monitorexit    
        //   343: iload_2        
        //   344: istore_3       
        //   345: iload_2        
        //   346: istore_1       
        //   347: iload           5
        //   349: istore          4
        //   351: aload           12
        //   353: athrow         
        //   354: astore          11
        //   356: iconst_m1      
        //   357: istore          4
        //   359: goto            243
        //   362: astore          11
        //   364: iconst_m1      
        //   365: istore          5
        //   367: goto            185
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      14     362    370    Ljava/lang/Throwable;
        //  4      14     354    362    Any
        //  18     28     362    370    Ljava/lang/Throwable;
        //  18     28     354    362    Any
        //  32     43     362    370    Ljava/lang/Throwable;
        //  32     43     354    362    Any
        //  51     58     183    185    Ljava/lang/Throwable;
        //  51     58     241    243    Any
        //  66     79     183    185    Ljava/lang/Throwable;
        //  66     79     241    243    Any
        //  93     99     183    185    Ljava/lang/Throwable;
        //  93     99     241    243    Any
        //  107    110    183    185    Ljava/lang/Throwable;
        //  107    110    241    243    Any
        //  110    120    225    241    Any
        //  134    150    225    241    Any
        //  172    180    183    185    Ljava/lang/Throwable;
        //  172    180    241    243    Any
        //  191    199    241    243    Any
        //  205    211    241    243    Any
        //  227    230    225    241    Any
        //  238    241    183    185    Ljava/lang/Throwable;
        //  238    241    241    243    Any
        //  267    273    183    185    Ljava/lang/Throwable;
        //  267    273    241    243    Any
        //  281    284    183    185    Ljava/lang/Throwable;
        //  281    284    241    243    Any
        //  284    321    338    354    Any
        //  321    324    338    354    Any
        //  340    343    338    354    Any
        //  351    354    183    185    Ljava/lang/Throwable;
        //  351    354    241    243    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0134:
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
    
    private void b() {
        synchronized (this) {
            boolean b;
            if (this.f != null && this.f.getState() != Thread.State.TERMINATED) {
                b = true;
            }
            else {
                b = false;
            }
            if (!this.g && !this.b.d() && !b) {
                (this.f = new Thread(new a(), "Source reader for " + this.a)).start();
            }
        }
    }
    
    private void b(final long n, final long n2) {
        this.a(n, n2);
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }
    
    private boolean c() {
        return Thread.currentThread().isInterrupted() || this.g;
    }
    
    private void d() {
        try {
            this.a.b();
        }
        catch (l l) {}
        catch (IllegalArgumentException ex) {
            goto Label_0011;
        }
    }
    
    public int a(final byte[] array, final long n, int a) {
        if (array == null) {
            throw new NullPointerException("Buffer must be not null!");
        }
        // monitorenter(c)
        Label_0172: {
            if (n < 0L) {
                break Label_0172;
            }
            boolean b = true;
        Label_0045_Outer:
            while (true) {
                j.a(b, "Data offset must be positive!");
                Label_0178: {
                    if (a < 0 || a > array.length) {
                        break Label_0178;
                    }
                    b = true;
                Label_0052_Outer:
                    while (true) {
                        j.a(b, "Length must be in range [0..buffer.length]");
                        while (true) {
                            if (this.b.d() || this.b.a() >= a + n || this.g) {
                                break Label_0172;
                            }
                            this.b();
                            final Object c = this.c;
                            try {
                                this.c.wait(1000L);
                                // monitorexit(c)
                                final int value = this.e.get();
                                if (value >= 1) {
                                    this.e.set(0);
                                    throw new l("Error reading source " + value + " times");
                                }
                                continue;
                                b = false;
                                continue Label_0045_Outer;
                                b = false;
                                continue Label_0052_Outer;
                            }
                            catch (InterruptedException ex) {
                                throw new l("Waiting source data is interrupted!", ex);
                                try {}
                                finally {
                                }
                                // monitorexit(c)
                            }
                            break;
                        }
                        break;
                    }
                }
                break;
            }
        }
        a = this.b.a(array, n, a);
        if (this.b.d() && this.h != 100) {
            this.a(this.h = 100);
        }
        return a;
    }
    
    public void a() {
        synchronized (this.d) {
            Log.d("ProxyCache", "Shutdown proxy for " + this.a);
            try {
                this.g = true;
                if (this.f != null) {
                    this.f.interrupt();
                }
                this.b.b();
            }
            catch (l l) {
                this.a(l);
            }
        }
    }
    
    protected void a(final int n) {
    }
    
    protected void a(final long n, final long n2) {
        boolean b = true;
        int n3;
        if (n2 == 0L) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        int h;
        if (n3 != 0) {
            h = 100;
        }
        else {
            h = (int)(100L * n / n2);
        }
        boolean b2;
        if (h != this.h) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (n2 < 0L) {
            b = false;
        }
        if (b && b2) {
            this.a(h);
        }
        this.h = h;
    }
    
    protected final void a(final Throwable t) {
        if (t instanceof i) {
            Log.d("ProxyCache", "ProxyCache is interrupted");
            return;
        }
        Log.e("ProxyCache", "ProxyCache error", t);
    }
    
    private class a implements Runnable
    {
        @Override
        public void run() {
            k.a(k.this);
        }
    }
}
