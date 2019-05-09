package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

@KeepForSdk
public class ProcessUtils {
    private static String zzhf = null;
    private static int zzhg = 0;

    private ProcessUtils() {
    }

    @KeepForSdk
    @Nullable
    public static String getMyProcessName() {
        if (zzhf == null) {
            if (zzhg == 0) {
                zzhg = Process.myPid();
            }
            zzhf = zzd(zzhg);
        }
        return zzhf;
    }

    @Nullable
    private static String zzd(int i) {
        Closeable zzk;
        Throwable th;
        String str = null;
        if (i > 0) {
            try {
                zzk = zzk("/proc/" + i + "/cmdline");
                try {
                    str = zzk.readLine().trim();
                    IOUtils.closeQuietly(zzk);
                } catch (IOException e) {
                    IOUtils.closeQuietly(zzk);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.closeQuietly(zzk);
                    throw th;
                }
            } catch (IOException e2) {
                zzk = str;
                IOUtils.closeQuietly(zzk);
                return str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                zzk = str;
                th = th4;
                IOUtils.closeQuietly(zzk);
                throw th;
            }
        }
        return str;
    }

    private static BufferedReader zzk(String str) throws IOException {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            return bufferedReader;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
