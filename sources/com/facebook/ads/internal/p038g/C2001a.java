package com.facebook.ads.internal.p038g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p026b.C2565a;
import com.facebook.ads.internal.p038g.C2004c.C2003a;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.internal.g.a */
public class C2001a {
    /* renamed from: a */
    public static final String f4426a = C2001a.class.getSimpleName();
    /* renamed from: b */
    private final String f4427b;
    /* renamed from: c */
    private final boolean f4428c;
    /* renamed from: d */
    private final C2000c f4429d;

    /* renamed from: com.facebook.ads.internal.g.a$a */
    private static final class C1998a implements IInterface {
        /* renamed from: a */
        private IBinder f4417a;

        C1998a(IBinder iBinder) {
            this.f4417a = iBinder;
        }

        /* renamed from: a */
        public String m4819a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f4417a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public IBinder asBinder() {
            return this.f4417a;
        }

        /* renamed from: b */
        public boolean m4820b() {
            boolean z = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.f4417a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.g.a$b */
    private static final class C1999b implements ServiceConnection {
        /* renamed from: a */
        private AtomicBoolean f4418a;
        /* renamed from: b */
        private final BlockingQueue<IBinder> f4419b;

        private C1999b() {
            this.f4418a = new AtomicBoolean(false);
            this.f4419b = new LinkedBlockingDeque();
        }

        /* renamed from: a */
        public IBinder m4821a() {
            if (!this.f4418a.compareAndSet(true, true)) {
                return (IBinder) this.f4419b.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f4419b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* renamed from: com.facebook.ads.internal.g.a$c */
    public enum C2000c {
        SHARED_PREFS,
        FB4A,
        DIRECT,
        REFLECTION,
        SERVICE
    }

    private C2001a(String str, boolean z, C2000c c2000c) {
        this.f4427b = str;
        this.f4428c = z;
        this.f4429d = c2000c;
    }

    /* renamed from: a */
    private static C2001a m4822a(Context context) {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                return new C2001a(advertisingIdInfo.getId(), advertisingIdInfo.isLimitAdTrackingEnabled(), C2000c.DIRECT);
            }
        } catch (Throwable th) {
        }
        return null;
    }

    /* renamed from: a */
    public static C2001a m4823a(Context context, C2003a c2003a) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot get advertising info on main thread.");
        } else if (C2565a.m6616a() && C2565a.m6617b("idfa_override")) {
            return new C2001a(C2565a.m6615a("idfa_override"), false, C2000c.DIRECT);
        } else {
            if (c2003a != null && !TextUtils.isEmpty(c2003a.f4435b)) {
                return new C2001a(c2003a.f4435b, c2003a.f4436c, C2000c.FB4A);
            }
            C2001a a = C2001a.m4822a(context);
            if (a == null || TextUtils.isEmpty(a.m4825a())) {
                Method a2 = C2005d.m4832a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
                if (a2 == null) {
                    a = null;
                } else {
                    Object a3 = C2005d.m4830a(null, a2, context);
                    if (a3 == null || ((Integer) a3).intValue() != 0) {
                        a = null;
                    } else {
                        a2 = C2005d.m4832a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
                        if (a2 == null) {
                            a = null;
                        } else {
                            Object a4 = C2005d.m4830a(null, a2, context);
                            if (a4 == null) {
                                a = null;
                            } else {
                                a2 = C2005d.m4831a(a4.getClass(), "getId", new Class[0]);
                                Method a5 = C2005d.m4831a(a4.getClass(), RequestParameters.isLAT, new Class[0]);
                                a = (a2 == null || a5 == null) ? null : new C2001a((String) C2005d.m4830a(a4, a2, new Object[0]), ((Boolean) C2005d.m4830a(a4, a5, new Object[0])).booleanValue(), C2000c.REFLECTION);
                            }
                        }
                    }
                }
            }
            return (a == null || TextUtils.isEmpty(a.m4825a())) ? C2001a.m4824b(context) : a;
        }
    }

    /* renamed from: b */
    private static C2001a m4824b(Context context) {
        ServiceConnection c1999b = new C1999b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, c1999b, 1)) {
            C2001a c2001a;
            try {
                C1998a c1998a = new C1998a(c1999b.m4821a());
                c2001a = new C2001a(c1998a.m4819a(), c1998a.m4820b(), C2000c.SERVICE);
                return c2001a;
            } catch (Exception e) {
                c2001a = e;
            } finally {
                context.unbindService(c1999b);
            }
        }
        return null;
    }

    /* renamed from: a */
    public String m4825a() {
        return this.f4427b;
    }

    /* renamed from: b */
    public boolean m4826b() {
        return this.f4428c;
    }

    /* renamed from: c */
    public C2000c m4827c() {
        return this.f4429d;
    }
}
