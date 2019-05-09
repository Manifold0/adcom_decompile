// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

import java.io.OutputStream;
import com.facebook.ads.internal.v.b.a.a;
import java.io.File;
import java.util.Locale;
import java.util.Iterator;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.net.SocketException;
import java.net.Socket;
import java.io.IOException;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import android.content.Context;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class f
{
    private final Object a;
    private final ExecutorService b;
    private final Map<String, g> c;
    private final ServerSocket d;
    private final int e;
    private final Thread f;
    private final com.facebook.ads.internal.v.b.c g;
    private boolean h;
    
    public f(final Context context) {
        this(new com.facebook.ads.internal.v.b.c(new a(context).a, new a(context).b, new a(context).c));
    }
    
    private f(com.facebook.ads.internal.v.b.c ex) {
        this.a = new Object();
        this.b = Executors.newFixedThreadPool(8);
        this.c = new ConcurrentHashMap<String, g>();
        this.g = j.a((com.facebook.ads.internal.v.b.c)ex);
        try {
            this.d = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.e = this.d.getLocalPort();
            ex = (IOException)new CountDownLatch(1);
            (this.f = new Thread(new e((CountDownLatch)ex))).start();
            ((CountDownLatch)ex).await();
            Log.i("ProxyCache", "Proxy cache server started. Ping it...");
            this.b();
        }
        catch (InterruptedException ex2) {}
        catch (IOException ex) {
            goto Label_0130;
        }
    }
    
    static /* synthetic */ void a(final f f) {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                final Socket accept = f.d.accept();
                Log.d("ProxyCache", "Accept new socket " + accept);
                f.b.submit(f.new d(accept));
            }
        }
        catch (IOException ex) {
            f.a(new l("Error during waiting connection", ex));
        }
    }
    
    static /* synthetic */ void a(final f f, final Socket socket) {
        try {
            Object o = com.facebook.ads.internal.v.b.d.a(socket.getInputStream());
            Log.i("ProxyCache", "Request to cache proxy:" + o);
            final String b = m.b(((com.facebook.ads.internal.v.b.d)o).a);
            if ("ping".equals(b)) {
                o = socket.getOutputStream();
                ((OutputStream)o).write("HTTP/1.1 200 OK\n\n".getBytes());
                ((OutputStream)o).write("ping ok".getBytes());
            }
            else {
                f.e(b).a((com.facebook.ads.internal.v.b.d)o, socket);
            }
        }
        catch (SocketException ex) {
            Log.d("ProxyCache", "Closing socket... Socket is closed by client.");
        }
        catch (l l) {}
        catch (IOException o) {}
    }
    
    private void a(final Throwable t) {
        Log.e("ProxyCache", "HttpProxyCacheServer error", t);
    }
    
    private void a(final Socket p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   java/net/Socket.isInputShutdown:()Z
        //     4: ifne            11
        //     7: aload_1        
        //     8: invokevirtual   java/net/Socket.shutdownInput:()V
        //    11: aload_1        
        //    12: invokevirtual   java/net/Socket.isOutputShutdown:()Z
        //    15: ifeq            22
        //    18: aload_1        
        //    19: invokevirtual   java/net/Socket.shutdownOutput:()V
        //    22: aload_1        
        //    23: invokevirtual   java/net/Socket.isClosed:()Z
        //    26: ifne            33
        //    29: aload_1        
        //    30: invokevirtual   java/net/Socket.close:()V
        //    33: return         
        //    34: astore_2       
        //    35: ldc             "ProxyCache"
        //    37: ldc_w           "Releasing input stream... Socket is closed by client."
        //    40: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    43: pop            
        //    44: goto            11
        //    47: astore_2       
        //    48: aload_0        
        //    49: new             Lcom/facebook/ads/internal/v/b/l;
        //    52: dup            
        //    53: ldc_w           "Error closing socket input stream"
        //    56: aload_2        
        //    57: invokespecial   com/facebook/ads/internal/v/b/l.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    60: invokespecial   com/facebook/ads/internal/v/b/f.a:(Ljava/lang/Throwable;)V
        //    63: goto            11
        //    66: astore_2       
        //    67: aload_0        
        //    68: new             Lcom/facebook/ads/internal/v/b/l;
        //    71: dup            
        //    72: ldc_w           "Error closing socket output stream"
        //    75: aload_2        
        //    76: invokespecial   com/facebook/ads/internal/v/b/l.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    79: invokespecial   com/facebook/ads/internal/v/b/f.a:(Ljava/lang/Throwable;)V
        //    82: goto            22
        //    85: astore_1       
        //    86: aload_0        
        //    87: new             Lcom/facebook/ads/internal/v/b/l;
        //    90: dup            
        //    91: ldc_w           "Error closing socket"
        //    94: aload_1        
        //    95: invokespecial   com/facebook/ads/internal/v/b/l.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    98: invokespecial   com/facebook/ads/internal/v/b/f.a:(Ljava/lang/Throwable;)V
        //   101: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  0      11     34     47     Ljava/net/SocketException;
        //  0      11     47     66     Ljava/io/IOException;
        //  11     22     66     85     Ljava/io/IOException;
        //  22     33     85     102    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 49, Size: 49
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
    
    private void b() {
        int n = 300;
        int i = 0;
        while (i < 3) {
            try {
                this.h = this.b.submit((Callable<Boolean>)new b()).get(n, TimeUnit.MILLISECONDS);
                if (this.h) {
                    return;
                }
                SystemClock.sleep((long)n);
                n *= 2;
                ++i;
            }
            catch (InterruptedException ex) {}
            catch (ExecutionException ex2) {
                goto Label_0074;
            }
            catch (TimeoutException ex2) {
                goto Label_0074;
            }
        }
        goto Label_0120;
    }
    
    private boolean c() {
        final h h = new h(this.d("ping"));
        try {
            final byte[] bytes = "ping ok".getBytes();
            h.a(0);
            final byte[] array = new byte[bytes.length];
            h.a(array);
            final boolean equals = Arrays.equals(bytes, array);
            Log.d("ProxyCache", "Ping response: `" + new String(array) + "`, pinged? " + equals);
            return equals;
        }
        catch (l l) {
            Log.e("ProxyCache", "Error reading ping response", (Throwable)l);
            return false;
        }
        finally {
            h.b();
        }
    }
    
    private boolean c(String s) {
        s = (String)new h(this.d(s));
        try {
            ((h)s).a(0);
            while (((h)s).a(new byte[8192]) != -1) {}
            return true;
        }
        catch (l l) {
            Log.e("ProxyCache", "Error reading url", (Throwable)l);
            return false;
        }
        finally {
            ((h)s).b();
        }
    }
    
    private int d() {
        synchronized (this.a) {
            final Iterator<g> iterator = this.c.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                n += iterator.next().b();
            }
            return n;
        }
    }
    
    private String d(final String s) {
        return String.format(Locale.US, "http://%s:%d/%s", "127.0.0.1", this.e, m.a(s));
    }
    
    private g e(final String s) {
        synchronized (this.a) {
            g g;
            if ((g = this.c.get(s)) == null) {
                g = new g(s, this.g);
                this.c.put(s, g);
            }
            return g;
        }
    }
    
    public void a() {
        Log.i("ProxyCache", "Shutdown proxy server");
        synchronized (this.a) {
            final Iterator<g> iterator = this.c.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().a();
            }
        }
        this.c.clear();
        // monitorexit(o)
        this.f.interrupt();
        try {
            if (!this.d.isClosed()) {
                this.d.close();
            }
        }
        catch (IOException ex) {
            this.a(new l("Error shutting down proxy server", ex));
        }
    }
    
    public boolean a(final String s) {
        int i = 0;
        int n = 300;
        while (i < 3) {
            try {
                if (this.b.submit((Callable<Boolean>)new c(s)).get()) {
                    return true;
                }
                SystemClock.sleep((long)n);
                n *= 2;
                ++i;
            }
            catch (InterruptedException ex) {}
            catch (ExecutionException ex2) {
                goto Label_0063;
            }
        }
        goto Label_0110;
    }
    
    public String b(final String s) {
        if (!this.h) {
            Log.e("ProxyCache", "Proxy server isn't pinged. Caching doesn't work.");
        }
        String d = s;
        if (this.h) {
            d = this.d(s);
        }
        return d;
    }
    
    public static final class a
    {
        private File a;
        private com.facebook.ads.internal.v.b.a.c b;
        private com.facebook.ads.internal.v.b.a.a c;
        
        public a(final Context context) {
            this.a = o.a(context);
            this.c = new com.facebook.ads.internal.v.b.a.g(67108864L);
            this.b = new com.facebook.ads.internal.v.b.a.f();
        }
    }
    
    private class b implements Callable<Boolean>
    {
        public Boolean a() {
            return com.facebook.ads.internal.v.b.f.this.c();
        }
    }
    
    private class c implements Callable<Boolean>
    {
        private final String b;
        
        public c(final String b) {
            this.b = b;
        }
        
        public Boolean a() {
            return com.facebook.ads.internal.v.b.f.this.c(this.b);
        }
    }
    
    private final class d implements Runnable
    {
        private final Socket b;
        
        public d(final Socket b) {
            this.b = b;
        }
        
        @Override
        public void run() {
            com.facebook.ads.internal.v.b.f.a(com.facebook.ads.internal.v.b.f.this, this.b);
        }
    }
    
    private final class e implements Runnable
    {
        private final CountDownLatch b;
        
        public e(final CountDownLatch b) {
            this.b = b;
        }
        
        @Override
        public void run() {
            this.b.countDown();
            com.facebook.ads.internal.v.b.f.a(com.facebook.ads.internal.v.b.f.this);
        }
    }
}
