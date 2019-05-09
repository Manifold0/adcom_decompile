package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
public class LibraryVersion {
    private static final GmsLogger zzel = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzem = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzen = new ConcurrentHashMap();

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzem;
    }

    @VisibleForTesting
    protected LibraryVersion() {
    }

    @KeepForSdk
    public String getVersion(@NonNull String str) {
        String str2;
        String str3;
        Throwable th;
        GmsLogger gmsLogger;
        String str4;
        String str5;
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzen.containsKey(str)) {
            return (String) this.zzen.get(str);
        }
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", new Object[]{str}));
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                String property = properties.getProperty("version", null);
                try {
                    zzel.m7v("LibraryVersion", new StringBuilder((String.valueOf(str).length() + 12) + String.valueOf(property).length()).append(str).append(" version is ").append(property).toString());
                    str2 = property;
                } catch (Throwable e) {
                    str3 = property;
                    th = e;
                    gmsLogger = zzel;
                    str4 = "LibraryVersion";
                    str5 = "Failed to get app version for libraryName: ";
                    str2 = String.valueOf(str);
                    gmsLogger.m4e(str4, str2.length() == 0 ? str5.concat(str2) : new String(str5), th);
                    str2 = str3;
                    if (str2 == null) {
                        str2 = "UNKNOWN";
                        zzel.m1d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
                    }
                    this.zzen.put(str, str2);
                    return str2;
                }
            }
            GmsLogger gmsLogger2 = zzel;
            String str6 = "LibraryVersion";
            str4 = "Failed to get app version for libraryName: ";
            str2 = String.valueOf(str);
            gmsLogger2.m3e(str6, str2.length() != 0 ? str4.concat(str2) : new String(str4));
            str2 = null;
        } catch (Throwable e2) {
            str3 = null;
            th = e2;
            gmsLogger = zzel;
            str4 = "LibraryVersion";
            str5 = "Failed to get app version for libraryName: ";
            str2 = String.valueOf(str);
            if (str2.length() == 0) {
            }
            gmsLogger.m4e(str4, str2.length() == 0 ? str5.concat(str2) : new String(str5), th);
            str2 = str3;
            if (str2 == null) {
                str2 = "UNKNOWN";
                zzel.m1d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
            }
            this.zzen.put(str, str2);
            return str2;
        }
        if (str2 == null) {
            str2 = "UNKNOWN";
            zzel.m1d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
        }
        this.zzen.put(str, str2);
        return str2;
    }
}
