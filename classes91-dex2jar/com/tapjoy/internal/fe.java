// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.Intent;
import android.content.BroadcastReceiver;
import android.app.Application;
import java.util.Observable;
import java.util.Observer;
import java.util.Hashtable;
import android.content.Context;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.util.Collection;
import com.tapjoy.TJConnectListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class fe
{
    private final ReentrantLock a;
    volatile int b;
    b c;
    long d;
    a e;
    private final Condition f;
    private final LinkedList g;
    private a h;
    
    public fe() {
        this.a = new ReentrantLock();
        this.f = this.a.newCondition();
        this.b = fe.c.a;
        this.g = new LinkedList();
        this.d = 1000L;
    }
    
    final a a() {
        this.a.lock();
        try {
            if (this.h != null) {
                this.e = this.h;
                this.h = null;
            }
            return this.e;
        }
        finally {
            this.a.unlock();
        }
    }
    
    final void a(final int b) {
        this.a.lock();
        try {
            final int b2 = this.b;
            this.b = b;
        }
        finally {
            this.a.unlock();
        }
    }
    
    final void a(final boolean b) {
        while (true) {
            this.a.lock();
            while (true) {
                TJConnectListener tjConnectListener = null;
                Label_0102: {
                    try {
                        if (this.g.size() != 0) {
                            final ArrayList<TJConnectListener> list = new ArrayList<TJConnectListener>(this.g);
                            this.g.clear();
                            this.a.unlock();
                            final Iterator<TJConnectListener> iterator = list.iterator();
                            while (iterator.hasNext()) {
                                tjConnectListener = iterator.next();
                                if (!b) {
                                    break Label_0102;
                                }
                                tjConnectListener.onConnectSuccess();
                            }
                        }
                        return;
                    }
                    finally {
                        this.a.unlock();
                    }
                }
                tjConnectListener.onConnectFailure();
                continue;
            }
        }
    }
    
    final boolean a(final long n) {
        this.a.lock();
        try {
            final int d = fe.c.d;
            final int c = fe.c.c;
            this.a(d);
            if (this.f.await(n, TimeUnit.MILLISECONDS)) {
                this.d = 1000L;
            }
            return false;
        }
        catch (InterruptedException ex) {
            return false;
        }
        finally {
            final int c2 = fe.c.c;
            final int d2 = fe.c.d;
            this.a(c2);
            this.a.unlock();
        }
    }
    
    public abstract boolean a(final Context p0, final String p1, final Hashtable p2, final TJConnectListener p3);
    
    final void b() {
        this.a.lock();
        try {
            this.d = 1000L;
            this.f.signal();
        }
        finally {
            this.a.unlock();
        }
    }
    
    public final boolean b(final Context context, final String s, final Hashtable hashtable, final TJConnectListener tjConnectListener) {
        this.a.lock();
        Label_0026: {
            if (tjConnectListener == null) {
                break Label_0026;
            }
            while (true) {
                while (true) {
                    Label_0243: {
                        try {
                            this.g.addLast(eq.a(tjConnectListener, TJConnectListener.class));
                            final a h = new a(context, s, hashtable);
                            switch (fe$3.a[this.b - 1]) {
                                case 1: {
                                    this.a(true);
                                    return true;
                                }
                                case 2: {
                                    this.e = h;
                                    ev.b.addObserver(new Observer() {
                                        @Override
                                        public final void update(final Observable observable, final Object o) {
                                            ev.b.deleteObserver(this);
                                            if (!(boolean)Boolean.TRUE.equals(o) && fe.this.e != null && fe.this.e.a != null) {
                                                (fe.this.c = new b((byte)0)).e();
                                            }
                                        }
                                    });
                                    if (this.a(h.a, h.b, h.c, new TJConnectListener() {
                                        @Override
                                        public final void onConnectFailure() {
                                            fe.this.a(false);
                                        }
                                        
                                        @Override
                                        public final void onConnectSuccess() {
                                            final fe a = fe.this;
                                            final int e = fe.c.e;
                                            final int b = fe.c.b;
                                            a.a(e);
                                            fe.this.a(true);
                                        }
                                    })) {
                                        final int b = fe.c.b;
                                        final int a = fe.c.a;
                                        this.a(b);
                                        return true;
                                    }
                                    this.g.clear();
                                    return false;
                                }
                                case 3:
                                case 4: {
                                    this.h = h;
                                    return true;
                                }
                                case 5: {
                                    this.h = h;
                                    this.b();
                                    return true;
                                }
                                default: {
                                    break Label_0243;
                                }
                            }
                            this.a(fe.c.a);
                            return false;
                        }
                        finally {
                            this.a.unlock();
                        }
                    }
                    continue;
                }
            }
        }
    }
    
    final class a
    {
        public final Context a;
        public final String b;
        public final Hashtable c;
        
        public a(Context a, final String b, final Hashtable c) {
            Context applicationContext = null;
            if (a != null) {
                if (a instanceof Application) {
                    applicationContext = a;
                }
                else {
                    applicationContext = a.getApplicationContext();
                }
            }
            if (applicationContext != null) {
                a = applicationContext;
            }
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    
    final class b extends dd
    {
        private boolean b;
        private boolean c;
        private Context d;
        private BroadcastReceiver e;
        
        private b() {
            this.e = new BroadcastReceiver() {
                public final void onReceive(final Context context, final Intent intent) {
                    fe.this.b();
                }
            };
        }
        
        private void h() {
            this.d.unregisterReceiver(this.e);
        }
        
        protected final void a() {
            final fe a = fe.this;
            final int c = fe.c.c;
            final int b = fe.c.b;
            a.a(c);
        }
        
        protected final void b() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: aload_0        
            //     2: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //     5: invokevirtual   com/tapjoy/internal/fe.a:()Lcom/tapjoy/internal/fe$a;
            //     8: getfield        com/tapjoy/internal/fe$a.a:Landroid/content/Context;
            //    11: putfield        com/tapjoy/internal/fe$b.d:Landroid/content/Context;
            //    14: new             Landroid/content/IntentFilter;
            //    17: dup            
            //    18: ldc             "android.net.conn.CONNECTIVITY_CHANGE"
            //    20: invokespecial   android/content/IntentFilter.<init>:(Ljava/lang/String;)V
            //    23: astore          5
            //    25: aload_0        
            //    26: getfield        com/tapjoy/internal/fe$b.d:Landroid/content/Context;
            //    29: aload_0        
            //    30: getfield        com/tapjoy/internal/fe$b.e:Landroid/content/BroadcastReceiver;
            //    33: aload           5
            //    35: invokevirtual   android/content/Context.registerReceiver:(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
            //    38: pop            
            //    39: aload_0        
            //    40: getfield        com/tapjoy/internal/fe$b.b:Z
            //    43: ifne            224
            //    46: new             Ljava/util/concurrent/CountDownLatch;
            //    49: dup            
            //    50: iconst_1       
            //    51: invokespecial   java/util/concurrent/CountDownLatch.<init>:(I)V
            //    54: astore          5
            //    56: getstatic       com/tapjoy/internal/ev.b:Lcom/tapjoy/internal/ev$a;
            //    59: new             Lcom/tapjoy/internal/fe$b$1;
            //    62: dup            
            //    63: aload_0        
            //    64: aload           5
            //    66: invokespecial   com/tapjoy/internal/fe$b$1.<init>:(Lcom/tapjoy/internal/fe$b;Ljava/util/concurrent/CountDownLatch;)V
            //    69: invokevirtual   com/tapjoy/internal/ev$a.addObserver:(Ljava/util/Observer;)V
            //    72: aload_0        
            //    73: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //    76: invokevirtual   com/tapjoy/internal/fe.a:()Lcom/tapjoy/internal/fe$a;
            //    79: astore          6
            //    81: aload_0        
            //    82: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //    85: aload           6
            //    87: getfield        com/tapjoy/internal/fe$a.a:Landroid/content/Context;
            //    90: aload           6
            //    92: getfield        com/tapjoy/internal/fe$a.b:Ljava/lang/String;
            //    95: aload           6
            //    97: getfield        com/tapjoy/internal/fe$a.c:Ljava/util/Hashtable;
            //   100: aconst_null    
            //   101: invokevirtual   com/tapjoy/internal/fe.a:(Landroid/content/Context;Ljava/lang/String;Ljava/util/Hashtable;Lcom/tapjoy/TJConnectListener;)Z
            //   104: ifne            120
            //   107: aload_0        
            //   108: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //   111: iconst_0       
            //   112: invokestatic    com/tapjoy/internal/fe.a:(Lcom/tapjoy/internal/fe;Z)V
            //   115: aload_0        
            //   116: invokespecial   com/tapjoy/internal/fe$b.h:()V
            //   119: return         
            //   120: aload           5
            //   122: invokevirtual   java/util/concurrent/CountDownLatch.await:()V
            //   125: aload_0        
            //   126: getfield        com/tapjoy/internal/fe$b.c:Z
            //   129: ifeq            165
            //   132: aload_0        
            //   133: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //   136: astore          5
            //   138: getstatic       com/tapjoy/internal/fe$c.e:I
            //   141: istore_1       
            //   142: getstatic       com/tapjoy/internal/fe$c.c:I
            //   145: istore_2       
            //   146: aload           5
            //   148: iload_1        
            //   149: invokevirtual   com/tapjoy/internal/fe.a:(I)V
            //   152: aload_0        
            //   153: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //   156: iconst_1       
            //   157: invokestatic    com/tapjoy/internal/fe.a:(Lcom/tapjoy/internal/fe;Z)V
            //   160: aload_0        
            //   161: invokespecial   com/tapjoy/internal/fe$b.h:()V
            //   164: return         
            //   165: aload_0        
            //   166: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //   169: iconst_0       
            //   170: invokestatic    com/tapjoy/internal/fe.a:(Lcom/tapjoy/internal/fe;Z)V
            //   173: aload_0        
            //   174: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //   177: getfield        com/tapjoy/internal/fe.d:J
            //   180: ldc2_w          1000
            //   183: invokestatic    java/lang/Math.max:(JJ)J
            //   186: lstore_3       
            //   187: aload_0        
            //   188: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //   191: lload_3        
            //   192: iconst_2       
            //   193: lshl           
            //   194: ldc2_w          3600000
            //   197: invokestatic    java/lang/Math.min:(JJ)J
            //   200: putfield        com/tapjoy/internal/fe.d:J
            //   203: aload_0        
            //   204: getfield        com/tapjoy/internal/fe$b.a:Lcom/tapjoy/internal/fe;
            //   207: lload_3        
            //   208: invokevirtual   com/tapjoy/internal/fe.a:(J)Z
            //   211: pop            
            //   212: goto            39
            //   215: astore          5
            //   217: aload_0        
            //   218: invokespecial   com/tapjoy/internal/fe$b.h:()V
            //   221: aload           5
            //   223: athrow         
            //   224: aload_0        
            //   225: invokespecial   com/tapjoy/internal/fe$b.h:()V
            //   228: return         
            //   229: astore          5
            //   231: goto            125
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  39     115    215    224    Any
            //  120    125    229    234    Ljava/lang/InterruptedException;
            //  120    125    215    224    Any
            //  125    160    215    224    Any
            //  165    212    215    224    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0120:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        
        protected final void c() {
            if (fe.this.c == this) {
                fe.this.c = null;
            }
            if (fe.this.b == fe.c.c) {
                final fe a = fe.this;
                final int a2 = fe.c.a;
                final int c = fe.c.c;
                a.a(a2);
            }
        }
        
        protected final void d() {
            this.b = true;
            fe.this.b();
        }
    }
    
    enum c
    {
        public static final int a;
        public static final int b;
        public static final int c;
        public static final int d;
        public static final int e;
        private static final /* synthetic */ int[] f;
        
        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = 5;
            f = new int[] { fe.c.a, fe.c.b, fe.c.c, fe.c.d, fe.c.e };
        }
        
        public static int[] a() {
            return fe.c.f.clone();
        }
    }
}
