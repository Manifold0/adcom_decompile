package com.unity3d.services.core.properties;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import com.unity3d.services.core.log.DeviceLog;
import java.io.ByteArrayInputStream;
import java.lang.ref.WeakReference;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

public class ClientProperties {
    private static final X500Principal DEBUG_CERT = new X500Principal("CN=Android Debug,O=Android,C=US");
    private static WeakReference<Activity> _activity;
    private static Application _application;
    private static Context _applicationContext;
    private static String _gameId;

    public static Activity getActivity() {
        return (Activity) _activity.get();
    }

    public static void setActivity(Activity activity) {
        _activity = new WeakReference(activity);
    }

    public static Context getApplicationContext() {
        return _applicationContext;
    }

    public static void setApplicationContext(Context context) {
        _applicationContext = context;
    }

    public static Application getApplication() {
        return _application;
    }

    public static void setApplication(Application application) {
        _application = application;
    }

    public static String getGameId() {
        return _gameId;
    }

    public static void setGameId(String gameId) {
        _gameId = gameId;
    }

    public static String getAppName() {
        return _applicationContext.getPackageName();
    }

    public static String getAppVersion() {
        try {
            return getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            DeviceLog.exception("Error getting package info", e);
            return null;
        }
    }

    public static boolean isAppDebuggable() {
        int i = 0;
        boolean debuggable = false;
        boolean couldNotGetApplicationInfo = false;
        if (getApplicationContext() == null) {
            return false;
        }
        PackageManager pm = getApplicationContext().getPackageManager();
        String pkgName = getApplicationContext().getPackageName();
        try {
            int i2;
            ApplicationInfo appinfo = pm.getApplicationInfo(pkgName, 0);
            i2 = appinfo.flags & 2;
            appinfo.flags = i2;
            if (i2 != 0) {
                debuggable = true;
            }
        } catch (NameNotFoundException e) {
            DeviceLog.exception("Could not find name", e);
            couldNotGetApplicationInfo = true;
        }
        if (!couldNotGetApplicationInfo) {
            return debuggable;
        }
        try {
            Signature[] signatures = pm.getPackageInfo(pkgName, 64).signatures;
            i2 = signatures.length;
            while (i < i2) {
                debuggable = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatures[i].toByteArray()))).getSubjectX500Principal().equals(DEBUG_CERT);
                if (debuggable) {
                    return debuggable;
                }
                i++;
            }
            return debuggable;
        } catch (NameNotFoundException e2) {
            DeviceLog.exception("Could not find name", e2);
            return debuggable;
        } catch (CertificateException e3) {
            DeviceLog.exception("Certificate exception", e3);
            return debuggable;
        }
    }
}
