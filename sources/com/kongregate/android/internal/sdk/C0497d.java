package com.kongregate.android.internal.sdk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.util.Log;
import com.kongregate.android.api.KongregateEvent;
import com.kongregate.android.api.KongregateServices;
import com.kongregate.android.api.StatServices;
import com.kongregate.android.internal.sdk.C0485c.C0482c;
import com.kongregate.android.internal.sdk.C0485c.C0489a;
import com.kongregate.p000o.p001j.C0668c;
import com.tapjoy.TapjoyConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.kongregate.android.internal.sdk.d */
abstract class C0497d extends C0485c implements ServiceConnection {
    /* renamed from: k */
    protected static final int f467k = 10;
    /* renamed from: l */
    protected final AtomicBoolean f468l = new AtomicBoolean(false);
    /* renamed from: m */
    protected final AtomicBoolean f469m = new AtomicBoolean(false);
    /* renamed from: n */
    protected final AtomicBoolean f470n = new AtomicBoolean(false);
    /* renamed from: o */
    protected volatile Messenger f471o;
    /* renamed from: p */
    protected volatile Messenger f472p;
    /* renamed from: q */
    protected final ConcurrentLinkedQueue<Message> f473q = new ConcurrentLinkedQueue();
    /* renamed from: r */
    protected final HandlerThread f474r;
    /* renamed from: s */
    protected volatile Handler f475s;
    /* renamed from: t */
    protected final CountDownLatch f476t = new CountDownLatch(1);
    /* renamed from: u */
    protected final ScheduledExecutorService f477u = Executors.newSingleThreadScheduledExecutor();
    /* renamed from: v */
    protected final Cipher f478v;

    /* renamed from: com.kongregate.android.internal.sdk.d$a */
    private class C0493a implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0497d f463a;

        private C0493a(C0497d c0497d) {
            this.f463a = c0497d;
        }

        public void run() {
            if (!this.f463a.m441j()) {
                this.f463a.mo1182h();
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.d$b */
    private final class C0494b extends Handler {
        /* renamed from: a */
        final /* synthetic */ C0497d f464a;

        public C0494b(C0497d c0497d, Looper looper) {
            this.f464a = c0497d;
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            Log.i(C0498e.f484a, "Incoming message: " + message);
            this.f464a.mo1180b(message);
            if (1 == message.what) {
                this.f464a.m311a(new C0668c(message.getData()));
                this.f464a.m309a(KongregateEvent.READY);
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.d$c */
    protected class C0495c extends C0489a {
        /* renamed from: b */
        final /* synthetic */ C0497d f465b;

        protected C0495c(C0497d c0497d) {
            this.f465b = c0497d;
            super(c0497d);
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.d$d */
    protected class C0496d extends C0482c {
        /* renamed from: b */
        final /* synthetic */ C0497d f466b;

        protected C0496d(C0497d c0497d) {
            this.f466b = c0497d;
            super(c0497d);
        }

        public void submit(String str, long j) {
            Bundle bundle = new Bundle(1);
            bundle.putString("name", str);
            bundle.putLong("value", j);
            Message obtain = Message.obtain(null, 4);
            obtain.setData(bundle);
            this.f466b.m434a(obtain);
        }
    }

    /* renamed from: b */
    protected abstract void mo1180b(Message message);

    /* renamed from: k */
    protected abstract Intent mo1181k();

    protected C0497d(Context context, long j, String str) {
        super(context, j, str);
        this.f478v = m432a(context);
        this.f474r = new HandlerThread(this, "KongSDK-ClientMessageHandler") {
            /* renamed from: a */
            final /* synthetic */ C0497d f462a;

            protected void onLooperPrepared() {
                super.onLooperPrepared();
                Looper looper = getLooper();
                if (looper != null) {
                    this.f462a.f472p = new Messenger(new C0494b(this.f462a, looper));
                    this.f462a.f475s = new Handler(looper);
                    this.f462a.f476t.countDown();
                    return;
                }
                Log.e(C0498e.f484a, "Looper failed to initialize");
            }
        };
        this.f474r.start();
    }

    /* renamed from: f */
    protected void m437f() {
        if (this.f470n.getAndSet(true)) {
            this.f477u.scheduleAtFixedRate(new C0493a(), 0, 10, TimeUnit.SECONDS);
        }
    }

    /* renamed from: g */
    protected synchronized void m438g() {
        if (this.f468l.getAndSet(false)) {
            try {
                this.c.unbindService(this);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    /* renamed from: h */
    protected synchronized void mo1182h() {
        if (!m441j()) {
            m438g();
            this.f468l.set(this.c.bindService(mo1181k(), this, 1));
            Log.i(C0498e.f484a, "connect() - " + this.c.getPackageName() + " - " + this.f468l);
        }
        if (!this.f468l.get()) {
            m309a(KongregateEvent.SERVICE_UNAVAILABLE);
            Log.w(C0498e.f484a, "Failed to bind to service");
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        byte[] digest;
        Log.i(C0498e.f484a, "service connected");
        this.f471o = new Messenger(iBinder);
        this.f469m.set(true);
        try {
            this.f476t.await();
        } catch (Throwable e) {
            Log.w(C0498e.f484a, "exception waiting for SDK to initialize: ", e);
        }
        Bundle bundle = new Bundle(6);
        String packageName = this.c.getPackageName();
        bundle.putInt(C0498e.f496m, 1);
        bundle.putString("version", C0498e.f486c);
        bundle.putString(C0498e.f503t, packageName);
        bundle.putLong("application_id", this.a);
        try {
            bundle.putByteArray(C0498e.f505v, this.f478v.doFinal(MessageDigest.getInstance("SHA256").digest(this.b.getBytes())));
        } catch (Throwable e2) {
            Log.e(C0498e.f484a, "Security exception", e2);
        }
        try {
            digest = MessageDigest.getInstance("SHA1").digest((packageName + "-" + this.a + "-" + this.b).getBytes());
        } catch (NoSuchAlgorithmException e3) {
            Log.w(C0498e.f484a, "unable to sign connect message: " + e3);
            digest = null;
        }
        if (digest != null) {
            bundle.putByteArray("a", digest);
        }
        mo1179a(bundle);
        Message obtain = Message.obtain(null, 1);
        obtain.setData(bundle);
        obtain.replyTo = this.f472p;
        try {
            Log.d(C0498e.f484a, "send: " + obtain.toString());
            this.f471o.send(obtain);
            m440i();
        } catch (RemoteException e4) {
            Log.w(C0498e.f484a, "Exception sending connect: " + e4);
        }
        m309a("KONG_API_EVENT_CONNECT");
    }

    /* renamed from: a */
    public void mo1179a(Bundle bundle) {
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Log.i(C0498e.f484a, "service disconnected");
        this.f469m.set(false);
        this.f468l.set(false);
        m309a("KONG_API_EVENT_DISCONNECT");
    }

    /* renamed from: a */
    protected void m434a(Message message) {
        Object obj;
        message.replyTo = this.f472p;
        if (this.f469m.get()) {
            try {
                this.f471o.send(message);
                obj = 1;
            } catch (Throwable e) {
                Log.w(C0498e.f484a, "Exception sending msg: " + message.toString(), e);
            }
            if (obj == null) {
                Log.d(C0498e.f484a, "adding to pending queue: " + message);
                this.f473q.add(message);
            }
        }
        obj = null;
        if (obj == null) {
            Log.d(C0498e.f484a, "adding to pending queue: " + message);
            this.f473q.add(message);
        }
    }

    /* renamed from: i */
    protected void m440i() {
        Iterator it = this.f473q.iterator();
        while (it.hasNext()) {
            Message message = (Message) it.next();
            try {
                this.f471o.send(message);
                this.f473q.remove(message);
            } catch (Throwable e) {
                Log.w(C0498e.f484a, "Exception sending msg: " + message.toString(), e);
                return;
            }
        }
    }

    /* renamed from: j */
    protected boolean m441j() {
        return this.f469m.get();
    }

    /* renamed from: a */
    protected KongregateServices mo1150a() {
        return new C0495c(this);
    }

    /* renamed from: b */
    protected StatServices mo1151b() {
        return new C0496d(this);
    }

    /* renamed from: a */
    protected Cipher m432a(Context context) {
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, new SecretKeySpec(MessageDigest.getInstance("SHA").digest(("backpack" + Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID)).getBytes()), 0, 16, "AES"));
            return instance;
        } catch (Throwable e) {
            Log.e(C0498e.f484a, "Couldn't find algorithm", e);
            throw new IllegalStateException(e);
        }
    }

    public void onPause(Activity activity) {
    }

    public void onResume(Activity activity) {
    }

    public void onPause(Activity activity, String str) {
        onPause(activity);
    }

    public void onResume(Activity activity, String str) {
        onResume(activity);
    }

    public void onPause(Activity activity, Map<String, Object> map) {
        onPause(activity);
    }

    public void onResume(Activity activity, Map<String, Object> map) {
        onResume(activity);
    }

    public void onCreate(Activity activity, Bundle bundle) {
    }

    public void onDestroy(Activity activity) {
    }

    public void onLowMemory() {
    }

    public void willOpenUrl(Uri uri) {
    }
}
