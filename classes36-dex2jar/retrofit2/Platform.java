// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2;

import java.lang.reflect.Constructor;
import java.lang.invoke.MethodHandles;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import android.os.Looper;
import android.os.Handler;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import android.os.Build$VERSION;

class Platform
{
    private static final Platform PLATFORM;
    
    static {
        PLATFORM = findPlatform();
    }
    
    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build$VERSION.SDK_INT != 0) {
                return new Android();
            }
        }
        catch (ClassNotFoundException ex) {}
        try {
            Class.forName("java.util.Optional");
            return new Java8();
        }
        catch (ClassNotFoundException ex2) {
            return new Platform();
        }
    }
    
    static Platform get() {
        return Platform.PLATFORM;
    }
    
    CallAdapter.Factory defaultCallAdapterFactory(final Executor executor) {
        if (executor != null) {
            return new ExecutorCallAdapterFactory(executor);
        }
        return DefaultCallAdapterFactory.INSTANCE;
    }
    
    Executor defaultCallbackExecutor() {
        return null;
    }
    
    Object invokeDefaultMethod(final Method method, final Class<?> clazz, final Object o, final Object... array) throws Throwable {
        throw new UnsupportedOperationException();
    }
    
    boolean isDefaultMethod(final Method method) {
        return false;
    }
    
    static class Android extends Platform
    {
        @Override
        CallAdapter.Factory defaultCallAdapterFactory(final Executor executor) {
            return new ExecutorCallAdapterFactory(executor);
        }
        
        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }
        
        static class MainThreadExecutor implements Executor
        {
            private final Handler handler;
            
            MainThreadExecutor() {
                this.handler = new Handler(Looper.getMainLooper());
            }
            
            @Override
            public void execute(final Runnable runnable) {
                this.handler.post(runnable);
            }
        }
    }
    
    @IgnoreJRERequirement
    static class Java8 extends Platform
    {
        @Override
        Object invokeDefaultMethod(final Method method, final Class<?> clazz, final Object o, final Object... array) throws Throwable {
            final Constructor<MethodHandles.Lookup> declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(clazz, -1).unreflectSpecial(method, clazz).bindTo(o).invokeWithArguments(array);
        }
        
        @Override
        boolean isDefaultMethod(final Method method) {
            return method.isDefault();
        }
    }
}
