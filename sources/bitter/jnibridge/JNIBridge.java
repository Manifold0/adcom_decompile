package bitter.jnibridge;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JNIBridge {

    /* renamed from: bitter.jnibridge.JNIBridge$a */
    private static class C0197a implements InvocationHandler {
        /* renamed from: a */
        private Object f0a = new Object[0];
        /* renamed from: b */
        private long f1b;
        /* renamed from: c */
        private Constructor f2c;

        public C0197a(long j) {
            this.f1b = j;
            try {
                this.f2c = Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
                this.f2c.setAccessible(true);
            } catch (NoClassDefFoundError e) {
                this.f2c = null;
            } catch (NoSuchMethodException e2) {
                this.f2c = null;
            }
        }

        /* renamed from: a */
        private Object m18a(Object obj, Method method, Object[] objArr) {
            if (objArr == null) {
                objArr = new Object[0];
            }
            Class declaringClass = method.getDeclaringClass();
            return ((Lookup) this.f2c.newInstance(new Object[]{declaringClass, Integer.valueOf(2)})).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
        }

        /* renamed from: a */
        public final void m19a() {
            synchronized (this.f0a) {
                this.f1b = 0;
            }
        }

        public final void finalize() {
            synchronized (this.f0a) {
                if (this.f1b == 0) {
                    return;
                }
                JNIBridge.delete(this.f1b);
            }
        }

        public final Object invoke(Object obj, Method method, Object[] objArr) {
            Object obj2;
            synchronized (this.f0a) {
                if (this.f1b == 0) {
                    obj2 = null;
                } else {
                    try {
                        obj2 = JNIBridge.invoke(this.f1b, method.getDeclaringClass(), method, objArr);
                    } catch (NoSuchMethodError e) {
                        if (this.f2c == null) {
                            System.err.println("JNIBridge error: Java interface default methods are only supported since Android Oreo");
                            throw e;
                        } else if ((method.getModifiers() & 1024) == 0) {
                            obj2 = m18a(obj, method, objArr);
                        } else {
                            throw e;
                        }
                    }
                }
            }
            return obj2;
        }
    }

    static native void delete(long j);

    static void disableInterfaceProxy(Object obj) {
        if (obj != null) {
            ((C0197a) Proxy.getInvocationHandler(obj)).m19a();
        }
    }

    static native Object invoke(long j, Class cls, Method method, Object[] objArr);

    static Object newInterfaceProxy(long j, Class[] clsArr) {
        return Proxy.newProxyInstance(JNIBridge.class.getClassLoader(), clsArr, new C0197a(j));
    }
}
