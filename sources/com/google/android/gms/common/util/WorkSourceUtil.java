package com.google.android.gms.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.wrappers.Wrappers;
import com.kongregate.p000o.p003a.C0583e;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@KeepForSdk
public class WorkSourceUtil {
    private static final int zzhj = Process.myUid();
    private static final Method zzhk = zzx();
    private static final Method zzhl = zzy();
    private static final Method zzhm = zzz();
    private static final Method zzhn = zzaa();
    private static final Method zzho = zzab();
    private static final Method zzhp = zzac();
    private static final Method zzhq = zzad();

    private WorkSourceUtil() {
    }

    private static WorkSource zza(int i, String str) {
        WorkSource workSource = new WorkSource();
        zza(workSource, i, str);
        return workSource;
    }

    @Nullable
    @KeepForSdk
    public static WorkSource fromPackage(Context context, @Nullable String str) {
        if (context == null || context.getPackageManager() == null || str == null) {
            return null;
        }
        String str2;
        String str3;
        String valueOf;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return zza(applicationInfo.uid, str);
            }
            str2 = "WorkSourceUtil";
            str3 = "Could not get applicationInfo from package: ";
            valueOf = String.valueOf(str);
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        } catch (NameNotFoundException e) {
            str2 = "WorkSourceUtil";
            str3 = "Could not find package: ";
            valueOf = String.valueOf(str);
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
    }

    private static void zza(WorkSource workSource, int i, @Nullable String str) {
        if (zzhl != null) {
            if (str == null) {
                str = "";
            }
            try {
                zzhl.invoke(workSource, new Object[]{Integer.valueOf(i), str});
            } catch (Throwable e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        } else if (zzhk != null) {
            try {
                zzhk.invoke(workSource, new Object[]{Integer.valueOf(i)});
            } catch (Throwable e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            }
        }
    }

    @KeepForSdk
    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, String str, String str2) {
        WorkSource workSource = null;
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
        } else {
            int zzd = zzd(context, str);
            if (zzd >= 0) {
                workSource = new WorkSource();
                if (zzhp == null || zzhq == null) {
                    zza(workSource, zzd, str);
                } else {
                    try {
                        Object invoke = zzhp.invoke(workSource, new Object[0]);
                        if (zzd != zzhj) {
                            zzhq.invoke(invoke, new Object[]{Integer.valueOf(zzd), str});
                        }
                        zzhq.invoke(invoke, new Object[]{Integer.valueOf(zzhj), str2});
                    } catch (Throwable e) {
                        Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e);
                    }
                }
            }
        }
        return workSource;
    }

    private static int zza(WorkSource workSource) {
        if (zzhm != null) {
            try {
                return ((Integer) zzhm.invoke(workSource, new Object[0])).intValue();
            } catch (Throwable e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }

    @Nullable
    private static String zza(WorkSource workSource, int i) {
        if (zzho != null) {
            try {
                return (String) zzho.invoke(workSource, new Object[]{Integer.valueOf(i)});
            } catch (Throwable e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return null;
    }

    @KeepForSdk
    public static List<String> getNames(@Nullable WorkSource workSource) {
        int i = 0;
        int zza = workSource == null ? 0 : zza(workSource);
        if (zza == 0) {
            return Collections.emptyList();
        }
        List<String> arrayList = new ArrayList();
        while (i < zza) {
            String zza2 = zza(workSource, i);
            if (!Strings.isEmptyOrWhitespace(zza2)) {
                arrayList.add(zza2);
            }
            i++;
        }
        return arrayList;
    }

    @KeepForSdk
    public static boolean hasWorkSourcePermission(Context context) {
        if (context == null || context.getPackageManager() == null || Wrappers.packageManager(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    private static int zzd(Context context, String str) {
        String str2;
        String str3;
        String valueOf;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            str2 = "WorkSourceUtil";
            str3 = "Could not get applicationInfo from package: ";
            valueOf = String.valueOf(str);
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return -1;
        } catch (NameNotFoundException e) {
            str2 = "WorkSourceUtil";
            str3 = "Could not find package: ";
            valueOf = String.valueOf(str);
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return -1;
        }
    }

    private static Method zzx() {
        Method method = null;
        try {
            method = WorkSource.class.getMethod(C0583e.f802a, new Class[]{Integer.TYPE});
        } catch (Exception e) {
        }
        return method;
    }

    private static Method zzy() {
        Method method = null;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                method = WorkSource.class.getMethod(C0583e.f802a, new Class[]{Integer.TYPE, String.class});
            } catch (Exception e) {
            }
        }
        return method;
    }

    private static Method zzz() {
        Method method = null;
        try {
            method = WorkSource.class.getMethod("size", new Class[0]);
        } catch (Exception e) {
        }
        return method;
    }

    private static Method zzaa() {
        Method method = null;
        try {
            method = WorkSource.class.getMethod("get", new Class[]{Integer.TYPE});
        } catch (Exception e) {
        }
        return method;
    }

    private static Method zzab() {
        Method method = null;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                method = WorkSource.class.getMethod("getName", new Class[]{Integer.TYPE});
            } catch (Exception e) {
            }
        }
        return method;
    }

    private static final Method zzac() {
        Method method = null;
        if (PlatformVersion.isAtLeastP()) {
            try {
                method = WorkSource.class.getMethod("createWorkChain", new Class[0]);
            } catch (Throwable e) {
                Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", e);
            }
        }
        return method;
    }

    @SuppressLint({"PrivateApi"})
    private static final Method zzad() {
        Method method = null;
        if (PlatformVersion.isAtLeastP()) {
            try {
                method = Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", new Class[]{Integer.TYPE, String.class});
            } catch (Throwable e) {
                Log.w("WorkSourceUtil", "Missing WorkChain class", e);
            }
        }
        return method;
    }
}
