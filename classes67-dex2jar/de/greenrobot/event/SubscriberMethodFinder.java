// 
// Decompiled by Procyon v0.5.34
// 

package de.greenrobot.event;

import java.lang.reflect.Method;
import android.util.Log;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SubscriberMethodFinder
{
    private static final int MODIFIERS_IGNORE = 1032;
    private static final Map<String, List<SubscriberMethod>> methodCache;
    private static final Map<Class<?>, Class<?>> skipMethodVerificationForClasses;
    
    static {
        methodCache = new HashMap<String, List<SubscriberMethod>>();
        skipMethodVerificationForClasses = new ConcurrentHashMap<Class<?>, Class<?>>();
    }
    
    static void clearCaches() {
        synchronized (SubscriberMethodFinder.methodCache) {
            SubscriberMethodFinder.methodCache.clear();
        }
    }
    
    public static void clearSkipMethodVerifications() {
        SubscriberMethodFinder.skipMethodVerificationForClasses.clear();
    }
    
    static void skipMethodVerificationFor(final Class<?> clazz) {
        if (!SubscriberMethodFinder.methodCache.isEmpty()) {
            throw new IllegalStateException("This method must be called before registering anything");
        }
        SubscriberMethodFinder.skipMethodVerificationForClasses.put(clazz, clazz);
    }
    
    List<SubscriberMethod> findSubscriberMethods(final Class<?> clazz, final String s) {
        final String string = clazz.getName() + '.' + s;
        synchronized (SubscriberMethodFinder.methodCache) {
            final List<SubscriberMethod> list = SubscriberMethodFinder.methodCache.get(string);
            // monitorexit(SubscriberMethodFinder.methodCache)
            if (list != null) {
                return list;
            }
        }
        final ArrayList<SubscriberMethod> list2 = new ArrayList<SubscriberMethod>();
        final Class clazz2;
        Class superclass = clazz2;
        final HashSet<String> set = new HashSet<String>();
        final StringBuilder sb = new StringBuilder();
        while (superclass != null) {
            final String name = superclass.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }
            final Method[] methods = superclass.getMethods();
            for (int length = methods.length, i = 0; i < length; ++i) {
                final Method method = methods[i];
                final String name2 = method.getName();
                if (name2.startsWith(s)) {
                    final int modifiers = method.getModifiers();
                    if ((modifiers & 0x1) != 0x0 && (modifiers & 0x408) == 0x0) {
                        final Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1) {
                            final String substring = name2.substring(s.length());
                            ThreadMode threadMode;
                            if (substring.length() == 0) {
                                threadMode = ThreadMode.PostThread;
                            }
                            else if (substring.equals("MainThread")) {
                                threadMode = ThreadMode.MainThread;
                            }
                            else if (substring.equals("BackgroundThread")) {
                                threadMode = ThreadMode.BackgroundThread;
                            }
                            else if (substring.equals("Async")) {
                                threadMode = ThreadMode.Async;
                            }
                            else {
                                if (!SubscriberMethodFinder.skipMethodVerificationForClasses.containsKey(superclass)) {
                                    throw new EventBusException("Illegal onEvent method, check for typos: " + method);
                                }
                                continue;
                            }
                            final Class<?> clazz3 = parameterTypes[0];
                            sb.setLength(0);
                            sb.append(name2);
                            sb.append('>').append(clazz3.getName());
                            if (set.add(sb.toString())) {
                                list2.add(new SubscriberMethod(method, threadMode, clazz3));
                            }
                        }
                    }
                    else if (!SubscriberMethodFinder.skipMethodVerificationForClasses.containsKey(superclass)) {
                        Log.d(EventBus.TAG, "Skipping method (not public, static or abstract): " + superclass + "." + name2);
                    }
                }
            }
            superclass = superclass.getSuperclass();
        }
        if (list2.isEmpty()) {
            throw new EventBusException("Subscriber " + clazz2 + " has no public methods called " + s);
        }
        synchronized (SubscriberMethodFinder.methodCache) {
            SubscriberMethodFinder.methodCache.put(string, list2);
            return list2;
        }
    }
}
