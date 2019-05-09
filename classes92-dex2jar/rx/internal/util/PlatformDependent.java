// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

public final class PlatformDependent
{
    private static final int ANDROID_API_VERSION;
    public static final int ANDROID_API_VERSION_IS_NOT_ANDROID = 0;
    private static final boolean IS_ANDROID;
    
    static {
        ANDROID_API_VERSION = resolveAndroidApiVersion();
        IS_ANDROID = (PlatformDependent.ANDROID_API_VERSION != 0);
    }
    
    private PlatformDependent() {
        throw new IllegalStateException("No instances!");
    }
    
    public static int getAndroidApiVersion() {
        return PlatformDependent.ANDROID_API_VERSION;
    }
    
    static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new PrivilegedAction<ClassLoader>() {
            @Override
            public ClassLoader run() {
                return ClassLoader.getSystemClassLoader();
            }
        });
    }
    
    public static boolean isAndroid() {
        return PlatformDependent.IS_ANDROID;
    }
    
    private static int resolveAndroidApiVersion() {
        try {
            return (int)Class.forName("android.os.Build$VERSION", true, getSystemClassLoader()).getField("SDK_INT").get(null);
        }
        catch (Exception ex) {
            return 0;
        }
    }
}
