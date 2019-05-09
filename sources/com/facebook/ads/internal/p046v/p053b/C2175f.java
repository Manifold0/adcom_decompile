package com.facebook.ads.internal.p046v.p053b;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.ads.internal.p046v.p053b.p054a.C2153a;
import com.facebook.ads.internal.p046v.p053b.p054a.C2156c;
import com.facebook.ads.internal.p046v.p053b.p054a.C2162f;
import com.facebook.ads.internal.p046v.p053b.p054a.C2163g;
import com.google.android.gms.nearby.messages.Strategy;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: com.facebook.ads.internal.v.b.f */
public class C2175f {
    /* renamed from: a */
    private final Object f5018a;
    /* renamed from: b */
    private final ExecutorService f5019b;
    /* renamed from: c */
    private final Map<String, C2177g> f5020c;
    /* renamed from: d */
    private final ServerSocket f5021d;
    /* renamed from: e */
    private final int f5022e;
    /* renamed from: f */
    private final Thread f5023f;
    /* renamed from: g */
    private final C2165c f5024g;
    /* renamed from: h */
    private boolean f5025h;

    /* renamed from: com.facebook.ads.internal.v.b.f$a */
    public static final class C2170a {
        /* renamed from: a */
        private File f5008a;
        /* renamed from: b */
        private C2156c f5009b = new C2162f();
        /* renamed from: c */
        private C2153a f5010c = new C2163g(67108864);

        public C2170a(Context context) {
            this.f5008a = C2186o.m5597a(context);
        }
    }

    /* renamed from: com.facebook.ads.internal.v.b.f$b */
    private class C2171b implements Callable<Boolean> {
        /* renamed from: a */
        final /* synthetic */ C2175f f5011a;

        private C2171b(C2175f c2175f) {
            this.f5011a = c2175f;
        }

        /* renamed from: a */
        public Boolean m5557a() {
            return Boolean.valueOf(this.f5011a.m5566c());
        }

        public /* synthetic */ Object call() {
            return m5557a();
        }
    }

    /* renamed from: com.facebook.ads.internal.v.b.f$c */
    private class C2172c implements Callable<Boolean> {
        /* renamed from: a */
        final /* synthetic */ C2175f f5012a;
        /* renamed from: b */
        private final String f5013b;

        public C2172c(C2175f c2175f, String str) {
            this.f5012a = c2175f;
            this.f5013b = str;
        }

        /* renamed from: a */
        public Boolean m5558a() {
            return Boolean.valueOf(this.f5012a.m5567c(this.f5013b));
        }

        public /* synthetic */ Object call() {
            return m5558a();
        }
    }

    /* renamed from: com.facebook.ads.internal.v.b.f$d */
    private final class C2173d implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2175f f5014a;
        /* renamed from: b */
        private final Socket f5015b;

        public C2173d(C2175f c2175f, Socket socket) {
            this.f5014a = c2175f;
            this.f5015b = socket;
        }

        public void run() {
            C2175f.m5560a(this.f5014a, this.f5015b);
        }
    }

    /* renamed from: com.facebook.ads.internal.v.b.f$e */
    private final class C2174e implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2175f f5016a;
        /* renamed from: b */
        private final CountDownLatch f5017b;

        public C2174e(C2175f c2175f, CountDownLatch countDownLatch) {
            this.f5016a = c2175f;
            this.f5017b = countDownLatch;
        }

        public void run() {
            this.f5017b.countDown();
            C2175f.m5559a(this.f5016a);
        }
    }

    public C2175f(Context context) {
        this(new C2165c(new C2170a(context).f5008a, new C2170a(context).f5009b, new C2170a(context).f5010c));
    }

    private C2175f(C2165c c2165c) {
        Throwable e;
        this.f5018a = new Object();
        this.f5019b = Executors.newFixedThreadPool(8);
        this.f5020c = new ConcurrentHashMap();
        this.f5024g = (C2165c) C2182j.m5591a(c2165c);
        try {
            this.f5021d = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.f5022e = this.f5021d.getLocalPort();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.f5023f = new Thread(new C2174e(this, countDownLatch));
            this.f5023f.start();
            countDownLatch.await();
            Log.i("ProxyCache", "Proxy cache server started. Ping it...");
            m5564b();
        } catch (IOException e2) {
            e = e2;
            this.f5019b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        } catch (InterruptedException e3) {
            e = e3;
            this.f5019b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m5559a(C2175f c2175f) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = c2175f.f5021d.accept();
                Log.d("ProxyCache", "Accept new socket " + accept);
                c2175f.f5019b.submit(new C2173d(c2175f, accept));
            } catch (Throwable e) {
                c2175f.m5561a(new C2180l("Error during waiting connection", e));
                return;
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m5560a(C2175f c2175f, Socket socket) {
        Throwable e;
        try {
            C2166d a = C2166d.m5540a(socket.getInputStream());
            Log.i("ProxyCache", "Request to cache proxy:" + a);
            String b = C2185m.m5595b(a.f4994a);
            if ("ping".equals(b)) {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
                outputStream.write("ping ok".getBytes());
            } else {
                c2175f.m5570e(b).m5578a(a, socket);
            }
            c2175f.m5562a(socket);
            Log.d("ProxyCache", "Opened connections: " + c2175f.m5568d());
        } catch (SocketException e2) {
            Log.d("ProxyCache", "Closing socket... Socket is closed by client.");
            c2175f.m5562a(socket);
            Log.d("ProxyCache", "Opened connections: " + c2175f.m5568d());
        } catch (C2180l e3) {
            e = e3;
            c2175f.m5561a(new C2180l("Error processing request", e));
            c2175f.m5562a(socket);
            Log.d("ProxyCache", "Opened connections: " + c2175f.m5568d());
        } catch (IOException e4) {
            e = e4;
            c2175f.m5561a(new C2180l("Error processing request", e));
            c2175f.m5562a(socket);
            Log.d("ProxyCache", "Opened connections: " + c2175f.m5568d());
        } catch (Throwable th) {
            c2175f.m5562a(socket);
            Log.d("ProxyCache", "Opened connections: " + c2175f.m5568d());
        }
    }

    /* renamed from: a */
    private void m5561a(Throwable th) {
        Log.e("ProxyCache", "HttpProxyCacheServer error", th);
    }

    /* renamed from: a */
    private void m5562a(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException e) {
            Log.d("ProxyCache", "Releasing input stream... Socket is closed by client.");
        } catch (Throwable e2) {
            m5561a(new C2180l("Error closing socket input stream", e2));
        }
        try {
            if (socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (Throwable e22) {
            m5561a(new C2180l("Error closing socket output stream", e22));
        }
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (Throwable e222) {
            m5561a(new C2180l("Error closing socket", e222));
        }
    }

    /* renamed from: b */
    private void m5564b() {
        int i = Strategy.TTL_SECONDS_DEFAULT;
        int i2 = 0;
        while (i2 < 3) {
            try {
                this.f5025h = ((Boolean) this.f5019b.submit(new C2171b()).get((long) i, TimeUnit.MILLISECONDS)).booleanValue();
                if (!this.f5025h) {
                    SystemClock.sleep((long) i);
                    i *= 2;
                    i2++;
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                Throwable e2 = e;
                Log.e("ProxyCache", "Error pinging server [attempt: " + i2 + ", timeout: " + i + "]. ", e2);
                i *= 2;
                i2++;
            } catch (ExecutionException e3) {
                e2 = e3;
                Log.e("ProxyCache", "Error pinging server [attempt: " + i2 + ", timeout: " + i + "]. ", e2);
                i *= 2;
                i2++;
            } catch (TimeoutException e4) {
                e2 = e4;
                Log.e("ProxyCache", "Error pinging server [attempt: " + i2 + ", timeout: " + i + "]. ", e2);
                i *= 2;
                i2++;
            }
        }
        Log.e("ProxyCache", "Shutdown server... Error pinging server [attempts: " + i2 + ", max timeout: " + (i / 2) + "].");
        m5571a();
    }

    /* renamed from: c */
    private boolean m5566c() {
        boolean equals;
        C2179h c2179h = new C2179h(m5569d("ping"));
        try {
            byte[] bytes = "ping ok".getBytes();
            c2179h.mo5533a(0);
            byte[] bArr = new byte[bytes.length];
            c2179h.mo5532a(bArr);
            equals = Arrays.equals(bytes, bArr);
            Log.d("ProxyCache", "Ping response: `" + new String(bArr) + "`, pinged? " + equals);
            return equals;
        } catch (C2180l e) {
            equals = e;
            Log.e("ProxyCache", "Error reading ping response", equals);
            return false;
        } finally {
            c2179h.mo5534b();
        }
    }

    /* renamed from: c */
    private boolean m5567c(String str) {
        C2179h c2179h = new C2179h(m5569d(str));
        try {
            c2179h.mo5533a(0);
            while (true) {
                if (c2179h.mo5532a(new byte[8192]) == -1) {
                    break;
                }
            }
            return true;
        } catch (Throwable e) {
            Log.e("ProxyCache", "Error reading url", e);
            return false;
        } finally {
            c2179h.mo5534b();
        }
    }

    /* renamed from: d */
    private int m5568d() {
        int i;
        synchronized (this.f5018a) {
            i = 0;
            for (C2177g b : this.f5020c.values()) {
                i = b.m5579b() + i;
            }
        }
        return i;
    }

    /* renamed from: d */
    private String m5569d(String str) {
        return String.format(Locale.US, "http://%s:%d/%s", new Object[]{"127.0.0.1", Integer.valueOf(this.f5022e), C2185m.m5593a(str)});
    }

    /* renamed from: e */
    private C2177g m5570e(String str) {
        C2177g c2177g;
        synchronized (this.f5018a) {
            c2177g = (C2177g) this.f5020c.get(str);
            if (c2177g == null) {
                c2177g = new C2177g(str, this.f5024g);
                this.f5020c.put(str, c2177g);
            }
        }
        return c2177g;
    }

    /* renamed from: a */
    public void m5571a() {
        Log.i("ProxyCache", "Shutdown proxy server");
        synchronized (this.f5018a) {
            for (C2177g a : this.f5020c.values()) {
                a.m5577a();
            }
            this.f5020c.clear();
        }
        this.f5023f.interrupt();
        try {
            if (!this.f5021d.isClosed()) {
                this.f5021d.close();
            }
        } catch (Throwable e) {
            m5561a(new C2180l("Error shutting down proxy server", e));
        }
    }

    /* renamed from: a */
    public boolean m5572a(String str) {
        int i = 0;
        int i2 = Strategy.TTL_SECONDS_DEFAULT;
        while (i < 3) {
            try {
                if (((Boolean) this.f5019b.submit(new C2172c(this, str)).get()).booleanValue()) {
                    return true;
                }
                SystemClock.sleep((long) i2);
                i2 *= 2;
                i++;
            } catch (InterruptedException e) {
                Throwable e2 = e;
                Log.e("ProxyCache", "Error precaching url [attempt: " + i + ", url: " + str + "]. ", e2);
                i2 *= 2;
                i++;
            } catch (ExecutionException e3) {
                e2 = e3;
                Log.e("ProxyCache", "Error precaching url [attempt: " + i + ", url: " + str + "]. ", e2);
                i2 *= 2;
                i++;
            }
        }
        Log.e("ProxyCache", "Shutdown server... Error precaching url [attempts: " + i + ", url: " + str + "].");
        m5571a();
        return false;
    }

    /* renamed from: b */
    public String m5573b(String str) {
        if (!this.f5025h) {
            Log.e("ProxyCache", "Proxy server isn't pinged. Caching doesn't work.");
        }
        return this.f5025h ? m5569d(str) : str;
    }
}
