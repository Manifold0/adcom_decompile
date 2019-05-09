package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class eq {

    /* renamed from: com.tapjoy.internal.eq$a */
    static class C2908a implements InvocationHandler {
        /* renamed from: a */
        private final Object f7681a;
        /* renamed from: b */
        private final Thread f7682b;
        /* renamed from: c */
        private final Looper f7683c;

        public C2908a(Object obj, Thread thread, Looper looper) {
            this.f7681a = obj;
            this.f7682b = thread;
            this.f7683c = looper;
        }

        public final Object invoke(Object proxy, final Method method, final Object[] args) {
            if (this.f7682b == Thread.currentThread()) {
                return method.invoke(this.f7681a, args);
            }
            if (method.getReturnType().equals(Void.TYPE)) {
                Runnable c29071 = new Runnable(this) {
                    /* renamed from: c */
                    final /* synthetic */ C2908a f7680c;

                    public final void run() {
                        try {
                            method.invoke(this.f7680c.f7681a, args);
                        } catch (Throwable e) {
                            throw cu.m7340a(e);
                        } catch (Throwable e2) {
                            throw cu.m7340a(e2);
                        } catch (Throwable e22) {
                            throw cu.m7340a(e22);
                        }
                    }
                };
                if (this.f7683c != null && new Handler(this.f7683c).post(c29071)) {
                    return null;
                }
                if (this.f7682b == fu.m7790b() && fu.f7795a.mo6287a(c29071)) {
                    return null;
                }
                Looper mainLooper = Looper.getMainLooper();
                if (mainLooper == null || !new Handler(mainLooper).post(c29071)) {
                    return method.invoke(this.f7681a, args);
                }
                return null;
            }
            throw new UnsupportedOperationException("method not return void: " + method.getName());
        }
    }

    /* renamed from: a */
    public static Object m7671a(Object obj, Class cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C2908a(obj, Thread.currentThread(), Looper.myLooper()));
    }
}
