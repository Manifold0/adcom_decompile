// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.security;

import android.content.Intent;
import android.content.res.Resources$NotFoundException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import android.support.annotation.Nullable;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;
import java.lang.reflect.Method;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

public class ProviderInstaller
{
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final Object lock;
    private static final GoogleApiAvailabilityLight zziv;
    private static Method zziw;
    
    static {
        zziv = GoogleApiAvailabilityLight.getInstance();
        lock = new Object();
        ProviderInstaller.zziw = null;
    }
    
    public static void installIfNeeded(final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Preconditions.checkNotNull(context, "Context must not be null");
        ProviderInstaller.zziv.verifyGooglePlayServicesIsAvailable(context, 11925000);
        Context context3;
        final Context context2 = context3 = zzk(context);
        if (context2 == null) {
            context3 = zzl(context);
        }
        if (context3 == null) {
            Log.e("ProviderInstaller", "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        Object lock;
        Throwable cause = null;
        String s;
        String value;
        String concat;
        final Context context4;
        Object o;
        Label_0158_Outer:Label_0168_Outer:
        while (true) {
            lock = ProviderInstaller.lock;
            // monitorenter(lock)
            while (true) {
                Label_0216: {
                    while (true) {
                    Label_0198:
                        while (true) {
                            try {
                                if (ProviderInstaller.zziw == null) {
                                    ProviderInstaller.zziw = context3.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", Context.class);
                                }
                                ProviderInstaller.zziw.invoke(null, context3);
                                return;
                            }
                            catch (Exception context2) {
                                cause = ((Throwable)context2).getCause();
                                if (!Log.isLoggable("ProviderInstaller", 6)) {
                                    break Label_0216;
                                }
                                if (cause != null) {}
                                s = ((Throwable)context2).getMessage();
                                value = String.valueOf(s);
                                if (value.length() != 0) {
                                    concat = "Failed to install provider: ".concat(value);
                                    Log.e("ProviderInstaller", concat);
                                    break Label_0216;
                                }
                                break Label_0198;
                                try {}
                                finally {
                                }
                                // monitorexit(lock)
                                CrashUtils.addDynamiteErrorToDropBox(context4, (Throwable)o);
                                throw new GooglePlayServicesNotAvailableException(8);
                            }
                            s = cause.getMessage();
                            continue Label_0158_Outer;
                        }
                        concat = new String("Failed to install provider: ");
                        continue Label_0168_Outer;
                    }
                }
                if (cause == null) {
                    o = context2;
                    continue;
                }
                o = cause;
                continue;
            }
        }
    }
    
    public static void installIfNeededAsync(final Context context, final ProviderInstallListener providerInstallListener) {
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkNotNull(providerInstallListener, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new zza(context, providerInstallListener).execute((Object[])new Void[0]);
    }
    
    @Nullable
    private static Context zzk(Context moduleContext) {
        try {
            moduleContext = DynamiteModule.load(moduleContext, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "providerinstaller").getModuleContext();
            return moduleContext;
        }
        catch (DynamiteModule.LoadingException ex) {
            final String value = String.valueOf(ex.getMessage());
            String concat;
            if (value.length() != 0) {
                concat = "Failed to load providerinstaller module: ".concat(value);
            }
            else {
                concat = new String("Failed to load providerinstaller module: ");
            }
            Log.w("ProviderInstaller", concat);
            return null;
        }
    }
    
    @Nullable
    private static Context zzl(final Context context) {
        try {
            return GooglePlayServicesUtilLight.getRemoteContext(context);
        }
        catch (Resources$NotFoundException ex) {
            final String value = String.valueOf(ex.getMessage());
            String concat;
            if (value.length() != 0) {
                concat = "Failed to load GMS Core context for providerinstaller: ".concat(value);
            }
            else {
                concat = new String("Failed to load GMS Core context for providerinstaller: ");
            }
            Log.w("ProviderInstaller", concat);
            CrashUtils.addDynamiteErrorToDropBox(context, (Throwable)ex);
            return null;
        }
    }
    
    public interface ProviderInstallListener
    {
        void onProviderInstallFailed(final int p0, final Intent p1);
        
        void onProviderInstalled();
    }
}
