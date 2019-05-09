// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import android.net.Uri;
import android.os.IBinder;
import android.content.ComponentName;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import com.kongregate.android.api.StatServices;
import android.os.RemoteException;
import android.os.Bundle;
import java.security.GeneralSecurityException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import android.provider.Settings$Secure;
import java.security.MessageDigest;
import com.kongregate.android.api.KongregateServices;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.Executors;
import android.content.Context;
import javax.crypto.Cipher;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.CountDownLatch;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.ConcurrentLinkedQueue;
import android.os.Messenger;
import java.util.concurrent.atomic.AtomicBoolean;
import android.content.ServiceConnection;

abstract class d extends com.kongregate.android.internal.sdk.c implements ServiceConnection
{
    protected static final int k = 10;
    protected final AtomicBoolean l;
    protected final AtomicBoolean m;
    protected final AtomicBoolean n;
    protected volatile Messenger o;
    protected volatile Messenger p;
    protected final ConcurrentLinkedQueue<Message> q;
    protected final HandlerThread r;
    protected volatile Handler s;
    protected final CountDownLatch t;
    protected final ScheduledExecutorService u;
    protected final Cipher v;
    
    protected d(final Context context, final long n, final String s) {
        super(context, n, s);
        this.l = new AtomicBoolean(false);
        this.m = new AtomicBoolean(false);
        this.n = new AtomicBoolean(false);
        this.q = new ConcurrentLinkedQueue<Message>();
        this.t = new CountDownLatch(1);
        this.u = Executors.newSingleThreadScheduledExecutor();
        this.v = this.a(context);
        (this.r = new HandlerThread("KongSDK-ClientMessageHandler") {
            protected void onLooperPrepared() {
                super.onLooperPrepared();
                final Looper looper = this.getLooper();
                if (looper != null) {
                    d.this.p = new Messenger((Handler)new b(looper));
                    d.this.s = new Handler(looper);
                    d.this.t.countDown();
                    return;
                }
                Log.e("KongSDK", "Looper failed to initialize");
            }
        }).start();
    }
    
    @Override
    protected KongregateServices a() {
        return new c();
    }
    
    protected Cipher a(final Context context) {
        try {
            final Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, new SecretKeySpec(MessageDigest.getInstance("SHA").digest(("backpack" + Settings$Secure.getString(context.getContentResolver(), "android_id")).getBytes()), 0, 16, "AES"));
            return instance;
        }
        catch (GeneralSecurityException ex) {
            Log.e("KongSDK", "Couldn't find algorithm", (Throwable)ex);
            throw new IllegalStateException(ex);
        }
    }
    
    public void a(final Bundle bundle) {
    }
    
    protected void a(final Message message) {
        message.replyTo = this.p;
        while (true) {
            Label_0097: {
                if (!this.m.get()) {
                    break Label_0097;
                }
                try {
                    this.o.send(message);
                    final int n = 1;
                    if (n == 0) {
                        Log.d("KongSDK", "adding to pending queue: " + message);
                        this.q.add(message);
                    }
                    return;
                }
                catch (RemoteException ex) {
                    Log.w("KongSDK", "Exception sending msg: " + message.toString(), (Throwable)ex);
                }
            }
            final int n = 0;
            continue;
        }
    }
    
    @Override
    protected StatServices b() {
        return new d();
    }
    
    protected abstract void b(final Message p0);
    
    protected void f() {
        if (this.n.getAndSet(true)) {
            this.u.scheduleAtFixedRate(new a(), 0L, 10L, TimeUnit.SECONDS);
        }
    }
    
    protected void g() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/kongregate/android/internal/sdk/d.l:Ljava/util/concurrent/atomic/AtomicBoolean;
        //     6: iconst_0       
        //     7: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.getAndSet:(Z)Z
        //    10: istore_1       
        //    11: iload_1        
        //    12: ifeq            23
        //    15: aload_0        
        //    16: getfield        com/kongregate/android/internal/sdk/d.c:Landroid/content/Context;
        //    19: aload_0        
        //    20: invokevirtual   android/content/Context.unbindService:(Landroid/content/ServiceConnection;)V
        //    23: aload_0        
        //    24: monitorexit    
        //    25: return         
        //    26: astore_2       
        //    27: aload_0        
        //    28: monitorexit    
        //    29: aload_2        
        //    30: athrow         
        //    31: astore_2       
        //    32: goto            23
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  2      11     26     31     Any
        //  15     23     31     35     Ljava/lang/IllegalArgumentException;
        //  15     23     26     31     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    
    protected void h() {
        synchronized (this) {
            if (!this.j()) {
                this.g();
                this.l.set(this.c.bindService(this.k(), (ServiceConnection)this, 1));
                Log.i("KongSDK", "connect() - " + this.c.getPackageName() + " - " + this.l);
            }
            if (!this.l.get()) {
                this.a("KONG_API_EVENT_SERVICE_UNAVAILABLE");
                Log.w("KongSDK", "Failed to bind to service");
            }
        }
    }
    
    protected void i() {
        for (final Message message : this.q) {
            try {
                this.o.send(message);
                this.q.remove(message);
                continue;
            }
            catch (RemoteException ex) {
                Log.w("KongSDK", "Exception sending msg: " + message.toString(), (Throwable)ex);
            }
            break;
        }
    }
    
    protected boolean j() {
        return this.m.get();
    }
    
    protected abstract Intent k();
    
    public void onCreate(final Activity activity, final Bundle bundle) {
    }
    
    public void onDestroy(final Activity activity) {
    }
    
    public void onLowMemory() {
    }
    
    public void onPause(final Activity activity) {
    }
    
    public void onPause(final Activity activity, final String s) {
        this.onPause(activity);
    }
    
    public void onPause(final Activity activity, final Map<String, Object> map) {
        this.onPause(activity);
    }
    
    public void onResume(final Activity activity) {
    }
    
    public void onResume(final Activity activity, final String s) {
        this.onResume(activity);
    }
    
    public void onResume(final Activity activity, final Map<String, Object> map) {
        this.onResume(activity);
    }
    
    public void onServiceConnected(final ComponentName p0, final IBinder p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: ldc_w           "service connected"
        //     5: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //     8: pop            
        //     9: aload_0        
        //    10: new             Landroid/os/Messenger;
        //    13: dup            
        //    14: aload_2        
        //    15: invokespecial   android/os/Messenger.<init>:(Landroid/os/IBinder;)V
        //    18: putfield        com/kongregate/android/internal/sdk/d.o:Landroid/os/Messenger;
        //    21: aload_0        
        //    22: getfield        com/kongregate/android/internal/sdk/d.m:Ljava/util/concurrent/atomic/AtomicBoolean;
        //    25: iconst_1       
        //    26: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.set:(Z)V
        //    29: aload_0        
        //    30: getfield        com/kongregate/android/internal/sdk/d.t:Ljava/util/concurrent/CountDownLatch;
        //    33: invokevirtual   java/util/concurrent/CountDownLatch.await:()V
        //    36: new             Landroid/os/Bundle;
        //    39: dup            
        //    40: bipush          6
        //    42: invokespecial   android/os/Bundle.<init>:(I)V
        //    45: astore_2       
        //    46: aload_0        
        //    47: getfield        com/kongregate/android/internal/sdk/d.c:Landroid/content/Context;
        //    50: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    53: astore_1       
        //    54: aload_2        
        //    55: ldc_w           "build"
        //    58: iconst_1       
        //    59: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //    62: aload_2        
        //    63: ldc_w           "version"
        //    66: ldc_w           "0.1"
        //    69: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    72: aload_2        
        //    73: ldc_w           "package"
        //    76: aload_1        
        //    77: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    80: aload_2        
        //    81: ldc_w           "application_id"
        //    84: aload_0        
        //    85: getfield        com/kongregate/android/internal/sdk/d.a:J
        //    88: invokevirtual   android/os/Bundle.putLong:(Ljava/lang/String;J)V
        //    91: aload_2        
        //    92: ldc_w           "api_signature"
        //    95: aload_0        
        //    96: getfield        com/kongregate/android/internal/sdk/d.v:Ljavax/crypto/Cipher;
        //    99: ldc_w           "SHA256"
        //   102: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //   105: aload_0        
        //   106: getfield        com/kongregate/android/internal/sdk/d.b:Ljava/lang/String;
        //   109: invokevirtual   java/lang/String.getBytes:()[B
        //   112: invokevirtual   java/security/MessageDigest.digest:([B)[B
        //   115: invokevirtual   javax/crypto/Cipher.doFinal:([B)[B
        //   118: invokevirtual   android/os/Bundle.putByteArray:(Ljava/lang/String;[B)V
        //   121: ldc_w           "SHA1"
        //   124: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //   127: new             Ljava/lang/StringBuilder;
        //   130: dup            
        //   131: invokespecial   java/lang/StringBuilder.<init>:()V
        //   134: aload_1        
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: ldc_w           "-"
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: aload_0        
        //   145: getfield        com/kongregate/android/internal/sdk/d.a:J
        //   148: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   151: ldc_w           "-"
        //   154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   157: aload_0        
        //   158: getfield        com/kongregate/android/internal/sdk/d.b:Ljava/lang/String;
        //   161: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   164: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   167: invokevirtual   java/lang/String.getBytes:()[B
        //   170: invokevirtual   java/security/MessageDigest.digest:([B)[B
        //   173: astore_1       
        //   174: aload_1        
        //   175: ifnull          186
        //   178: aload_2        
        //   179: ldc_w           "a"
        //   182: aload_1        
        //   183: invokevirtual   android/os/Bundle.putByteArray:(Ljava/lang/String;[B)V
        //   186: aload_0        
        //   187: aload_2        
        //   188: invokevirtual   com/kongregate/android/internal/sdk/d.a:(Landroid/os/Bundle;)V
        //   191: aconst_null    
        //   192: iconst_1       
        //   193: invokestatic    android/os/Message.obtain:(Landroid/os/Handler;I)Landroid/os/Message;
        //   196: astore_1       
        //   197: aload_1        
        //   198: aload_2        
        //   199: invokevirtual   android/os/Message.setData:(Landroid/os/Bundle;)V
        //   202: aload_1        
        //   203: aload_0        
        //   204: getfield        com/kongregate/android/internal/sdk/d.p:Landroid/os/Messenger;
        //   207: putfield        android/os/Message.replyTo:Landroid/os/Messenger;
        //   210: ldc             "KongSDK"
        //   212: new             Ljava/lang/StringBuilder;
        //   215: dup            
        //   216: invokespecial   java/lang/StringBuilder.<init>:()V
        //   219: ldc_w           "send: "
        //   222: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: aload_1        
        //   226: invokevirtual   android/os/Message.toString:()Ljava/lang/String;
        //   229: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   235: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   238: pop            
        //   239: aload_0        
        //   240: getfield        com/kongregate/android/internal/sdk/d.o:Landroid/os/Messenger;
        //   243: aload_1        
        //   244: invokevirtual   android/os/Messenger.send:(Landroid/os/Message;)V
        //   247: aload_0        
        //   248: invokevirtual   com/kongregate/android/internal/sdk/d.i:()V
        //   251: aload_0        
        //   252: ldc_w           "KONG_API_EVENT_CONNECT"
        //   255: invokevirtual   com/kongregate/android/internal/sdk/d.a:(Ljava/lang/String;)V
        //   258: return         
        //   259: astore_1       
        //   260: ldc             "KongSDK"
        //   262: ldc_w           "exception waiting for SDK to initialize: "
        //   265: aload_1        
        //   266: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   269: pop            
        //   270: goto            36
        //   273: astore_3       
        //   274: ldc             "KongSDK"
        //   276: ldc_w           "Security exception"
        //   279: aload_3        
        //   280: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   283: pop            
        //   284: goto            121
        //   287: astore_1       
        //   288: ldc             "KongSDK"
        //   290: new             Ljava/lang/StringBuilder;
        //   293: dup            
        //   294: invokespecial   java/lang/StringBuilder.<init>:()V
        //   297: ldc_w           "unable to sign connect message: "
        //   300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: aload_1        
        //   304: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   307: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   310: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   313: pop            
        //   314: aconst_null    
        //   315: astore_1       
        //   316: goto            174
        //   319: astore_1       
        //   320: ldc             "KongSDK"
        //   322: new             Ljava/lang/StringBuilder;
        //   325: dup            
        //   326: invokespecial   java/lang/StringBuilder.<init>:()V
        //   329: ldc_w           "Exception sending connect: "
        //   332: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: aload_1        
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   339: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   342: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   345: pop            
        //   346: goto            251
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  29     36     259    273    Ljava/lang/InterruptedException;
        //  91     121    273    287    Ljava/security/GeneralSecurityException;
        //  121    174    287    319    Ljava/security/NoSuchAlgorithmException;
        //  210    251    319    349    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 160, Size: 160
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
    
    public void onServiceDisconnected(final ComponentName componentName) {
        Log.i("KongSDK", "service disconnected");
        this.m.set(false);
        this.l.set(false);
        this.a("KONG_API_EVENT_DISCONNECT");
    }
    
    public void willOpenUrl(final Uri uri) {
    }
    
    private class a implements Runnable
    {
        @Override
        public void run() {
            if (!d.this.j()) {
                d.this.h();
            }
        }
    }
    
    private final class b extends Handler
    {
        public b(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            super.handleMessage(message);
            Log.i("KongSDK", "Incoming message: " + message);
            d.this.b(message);
            if (1 == message.what) {
                d.this.a(new com.kongregate.o.j.c(message.getData()));
                d.this.a("KONG_API_EVENT_READY");
            }
        }
    }
    
    protected class c extends com.kongregate.android.internal.sdk.c.a
    {
    }
    
    protected class d extends c
    {
        @Override
        public void submit(final String s, final long n) {
            final Bundle data = new Bundle(1);
            data.putString("name", s);
            data.putLong("value", n);
            final Message obtain = Message.obtain((Handler)null, 4);
            obtain.setData(data);
            com.kongregate.android.internal.sdk.d.this.a(obtain);
        }
    }
}
