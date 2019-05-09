// 
// Decompiled by Procyon v0.5.34
// 

package android.arch.lifecycle;

import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.lang.reflect.Constructor;
import java.util.Map;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
class Lifecycling
{
    private static Map<Class, Constructor<? extends GenericLifecycleObserver>> sCallbackCache;
    private static Constructor<? extends GenericLifecycleObserver> sREFLECTIVE;
    
    static {
        while (true) {
            try {
                Lifecycling.sREFLECTIVE = ReflectiveGenericLifecycleObserver.class.getDeclaredConstructor(Object.class);
                Lifecycling.sCallbackCache = new HashMap<Class, Constructor<? extends GenericLifecycleObserver>>();
            }
            catch (NoSuchMethodException ex) {
                continue;
            }
            break;
        }
    }
    
    static String getAdapterName(final String s) {
        return s.replace(".", "_") + "_LifecycleAdapter";
    }
    
    @NonNull
    static GenericLifecycleObserver getCallback(final Object o) {
        if (o instanceof GenericLifecycleObserver) {
            return (GenericLifecycleObserver)o;
        }
        try {
            final Class<?> class1 = o.getClass();
            final Constructor<? extends GenericLifecycleObserver> constructor = Lifecycling.sCallbackCache.get(class1);
            if (constructor != null) {
                return (GenericLifecycleObserver)constructor.newInstance(o);
            }
            final Constructor<? extends GenericLifecycleObserver> generatedAdapterConstructor = getGeneratedAdapterConstructor(class1);
            Constructor<? extends GenericLifecycleObserver> sreflective;
            if (generatedAdapterConstructor != null) {
                sreflective = generatedAdapterConstructor;
                if (!generatedAdapterConstructor.isAccessible()) {
                    generatedAdapterConstructor.setAccessible(true);
                    sreflective = generatedAdapterConstructor;
                }
            }
            else {
                sreflective = Lifecycling.sREFLECTIVE;
            }
            Lifecycling.sCallbackCache.put(class1, sreflective);
            return (GenericLifecycleObserver)sreflective.newInstance(o);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        catch (InstantiationException ex2) {
            throw new RuntimeException(ex2);
        }
        catch (InvocationTargetException ex3) {
            throw new RuntimeException(ex3);
        }
    }
    
    @Nullable
    private static Constructor<? extends GenericLifecycleObserver> getGeneratedAdapterConstructor(final Class<?> clazz) {
        final Package package1 = clazz.getPackage();
        String name;
        if (package1 != null) {
            name = package1.getName();
        }
        else {
            name = "";
        }
        String s = clazz.getCanonicalName();
        if (s != null) {
            Label_0070: {
                if (!name.isEmpty()) {
                    break Label_0070;
                }
                while (true) {
                    final String adapterName = getAdapterName(s);
                    try {
                        String string;
                        if (name.isEmpty()) {
                            string = adapterName;
                        }
                        else {
                            string = name + "." + adapterName;
                        }
                        return (Constructor<? extends GenericLifecycleObserver>)Class.forName(string).getDeclaredConstructor(clazz);
                        s = s.substring(name.length() + 1);
                    }
                    catch (ClassNotFoundException ex2) {
                        final Class<?> superclass = clazz.getSuperclass();
                        if (superclass != null) {
                            return getGeneratedAdapterConstructor(superclass);
                        }
                        break;
                    }
                    catch (NoSuchMethodException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
        return null;
    }
}
