// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.g;

import android.content.ComponentName;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.content.ServiceConnection;
import android.content.Intent;
import java.lang.reflect.Method;
import android.text.TextUtils;
import android.os.Looper;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.Context;

public class a
{
    public static final String a;
    private final String b;
    private final boolean c;
    private final c d;
    
    static {
        a = a.class.getSimpleName();
    }
    
    private a(final String b, final boolean c, final c d) {
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    private static a a(final Context context) {
        try {
            final AdvertisingIdClient$Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                return new a(advertisingIdInfo.getId(), advertisingIdInfo.isLimitAdTrackingEnabled(), c.c);
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    public static a a(final Context context, final com.facebook.ads.internal.g.c.a a) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot get advertising info on main thread.");
        }
        a a2;
        if (a.a() && a.b("idfa_override")) {
            a2 = new a(a.a("idfa_override"), false, c.c);
        }
        else {
            if (a != null && !TextUtils.isEmpty((CharSequence)a.b)) {
                return new a(a.b, a.c, c.b);
            }
            final a a3 = a(context);
            a a4 = null;
            Label_0129: {
                if (a3 != null) {
                    a4 = a3;
                    if (!TextUtils.isEmpty((CharSequence)a3.a())) {
                        break Label_0129;
                    }
                }
                final Method a5 = d.a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
                if (a5 == null) {
                    a4 = null;
                }
                else {
                    final Object a6 = d.a(null, a5, context);
                    if (a6 == null || (int)a6 != 0) {
                        a4 = null;
                    }
                    else {
                        final Method a7 = d.a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
                        if (a7 == null) {
                            a4 = null;
                        }
                        else {
                            final Object a8 = d.a(null, a7, context);
                            if (a8 == null) {
                                a4 = null;
                            }
                            else {
                                final Method a9 = d.a(a8.getClass(), "getId", (Class<?>[])new Class[0]);
                                final Method a10 = d.a(a8.getClass(), "isLimitAdTrackingEnabled", (Class<?>[])new Class[0]);
                                if (a9 == null || a10 == null) {
                                    a4 = null;
                                }
                                else {
                                    a4 = new a((String)d.a(a8, a9, new Object[0]), (boolean)d.a(a8, a10, new Object[0]), c.d);
                                }
                            }
                        }
                    }
                }
            }
            if (a4 != null) {
                a2 = a4;
                if (!TextUtils.isEmpty((CharSequence)a4.a())) {
                    return a2;
                }
            }
            return b(context);
        }
        return a2;
    }
    
    private static a b(final Context context) {
        final b b = new b();
        final Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        Label_0080: {
            if (!context.bindService(intent, (ServiceConnection)b, 1)) {
                break Label_0080;
            }
            try {
                final a a = new a(b.a());
                return new a(a.a(), a.b(), c.e);
            }
            catch (Exception ex) {
                return null;
            }
            finally {
                context.unbindService((ServiceConnection)b);
            }
        }
    }
    
    public String a() {
        return this.b;
    }
    
    public boolean b() {
        return this.c;
    }
    
    public c c() {
        return this.d;
    }
    
    private static final class a implements IInterface
    {
        private IBinder a;
        
        a(final IBinder a) {
            this.a = a;
        }
        
        public String a() {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
        
        public IBinder asBinder() {
            return this.a;
        }
        
        public boolean b() {
            boolean b = true;
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    b = false;
                }
                return b;
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
    
    private static final class b implements ServiceConnection
    {
        private AtomicBoolean a;
        private final BlockingQueue<IBinder> b;
        
        private b() {
            this.a = new AtomicBoolean(false);
            this.b = new LinkedBlockingDeque<IBinder>();
        }
        
        public IBinder a() {
            if (this.a.compareAndSet(true, true)) {
                throw new IllegalStateException("Binder already consumed");
            }
            return this.b.take();
        }
        
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            try {
                this.b.put(binder);
            }
            catch (InterruptedException ex) {}
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
        }
    }
    
    public enum c
    {
        a, 
        b, 
        c, 
        d, 
        e;
    }
}
