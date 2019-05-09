// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.content.Context;
import android.os.Process;
import java.lang.reflect.Method;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class WorkSourceUtil
{
    private static final int zzhj;
    private static final Method zzhk;
    private static final Method zzhl;
    private static final Method zzhm;
    private static final Method zzhn;
    private static final Method zzho;
    private static final Method zzhp;
    private static final Method zzhq;
    
    static {
        zzhj = Process.myUid();
        zzhk = zzx();
        zzhl = zzy();
        zzhm = zzz();
        zzhn = zzaa();
        zzho = zzab();
        zzhp = zzac();
        zzhq = zzad();
    }
    
    private WorkSourceUtil() {
    }
    
    @Nullable
    @KeepForSdk
    public static WorkSource fromPackage(final Context context, @Nullable final String s) {
        if (context == null || context.getPackageManager() == null || s == null) {
            return null;
        }
        ApplicationInfo applicationInfo;
        while (true) {
            while (true) {
                try {
                    applicationInfo = Wrappers.packageManager(context).getApplicationInfo(s, 0);
                    if (applicationInfo != null) {
                        break;
                    }
                    final String value = String.valueOf(s);
                    if (value.length() != 0) {
                        final String concat = "Could not get applicationInfo from package: ".concat(value);
                        Log.e("WorkSourceUtil", concat);
                        return null;
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    final String value2 = String.valueOf(s);
                    String concat2;
                    if (value2.length() != 0) {
                        concat2 = "Could not find package: ".concat(value2);
                    }
                    else {
                        concat2 = new String("Could not find package: ");
                    }
                    Log.e("WorkSourceUtil", concat2);
                    return null;
                }
                final String concat = new String("Could not get applicationInfo from package: ");
                continue;
            }
        }
        return zza(applicationInfo.uid, s);
    }
    
    @KeepForSdk
    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, final String s, final String s2) {
        if (context == null || context.getPackageManager() == null || s2 == null || s == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
        }
        else {
            final int zzd = zzd(context, s);
            if (zzd >= 0) {
                context = (Context)new WorkSource();
                if (WorkSourceUtil.zzhp == null || WorkSourceUtil.zzhq == null) {
                    zza((WorkSource)context, zzd, s);
                    return (WorkSource)context;
                }
                try {
                    final Object invoke = WorkSourceUtil.zzhp.invoke(context, new Object[0]);
                    if (zzd != WorkSourceUtil.zzhj) {
                        WorkSourceUtil.zzhq.invoke(invoke, zzd, s);
                    }
                    WorkSourceUtil.zzhq.invoke(invoke, WorkSourceUtil.zzhj, s2);
                    return (WorkSource)context;
                }
                catch (Exception ex) {
                    Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", (Throwable)ex);
                    return (WorkSource)context;
                }
            }
        }
        return null;
    }
    
    @KeepForSdk
    public static List<String> getNames(@Nullable final WorkSource workSource) {
        int n = 0;
        int zza;
        if (workSource == null) {
            zza = 0;
        }
        else {
            zza = zza(workSource);
        }
        List<String> emptyList;
        if (zza == 0) {
            emptyList = Collections.emptyList();
        }
        else {
            final ArrayList<String> list = new ArrayList<String>();
            while (true) {
                emptyList = list;
                if (n >= zza) {
                    break;
                }
                final String zza2 = zza(workSource, n);
                if (!Strings.isEmptyOrWhitespace(zza2)) {
                    list.add(zza2);
                }
                ++n;
            }
        }
        return emptyList;
    }
    
    @KeepForSdk
    public static boolean hasWorkSourcePermission(final Context context) {
        return context != null && context.getPackageManager() != null && Wrappers.packageManager(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) == 0;
    }
    
    private static int zza(final WorkSource workSource) {
        if (WorkSourceUtil.zzhm != null) {
            try {
                return (int)WorkSourceUtil.zzhm.invoke(workSource, new Object[0]);
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex);
            }
        }
        return 0;
    }
    
    private static WorkSource zza(final int n, final String s) {
        final WorkSource workSource = new WorkSource();
        zza(workSource, n, s);
        return workSource;
    }
    
    @Nullable
    private static String zza(final WorkSource workSource, final int n) {
        if (WorkSourceUtil.zzho != null) {
            try {
                return (String)WorkSourceUtil.zzho.invoke(workSource, n);
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex);
            }
        }
        return null;
    }
    
    private static void zza(final WorkSource workSource, final int n, @Nullable final String s) {
        Label_0050: {
            if (WorkSourceUtil.zzhl == null) {
                break Label_0050;
            }
            String s2;
            if ((s2 = s) == null) {
                s2 = "";
            }
            try {
                WorkSourceUtil.zzhl.invoke(workSource, n, s2);
                return;
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex);
                return;
            }
        }
        if (WorkSourceUtil.zzhk == null) {
            return;
        }
        try {
            WorkSourceUtil.zzhk.invoke(workSource, n);
        }
        catch (Exception ex2) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex2);
        }
    }
    
    private static Method zzaa() {
        try {
            return WorkSource.class.getMethod("get", Integer.TYPE);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzab() {
        Method method = null;
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return method;
        }
        try {
            method = WorkSource.class.getMethod("getName", Integer.TYPE);
            return method;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static final Method zzac() {
        Method method = null;
        if (!PlatformVersion.isAtLeastP()) {
            return method;
        }
        try {
            method = WorkSource.class.getMethod("createWorkChain", (Class<?>[])new Class[0]);
            return method;
        }
        catch (Exception ex) {
            Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", (Throwable)ex);
            return null;
        }
    }
    
    @SuppressLint({ "PrivateApi" })
    private static final Method zzad() {
        Method method = null;
        if (!PlatformVersion.isAtLeastP()) {
            return method;
        }
        try {
            method = Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", Integer.TYPE, String.class);
            return method;
        }
        catch (Exception ex) {
            Log.w("WorkSourceUtil", "Missing WorkChain class", (Throwable)ex);
            return null;
        }
    }
    
    private static int zzd(final Context context, final String s) {
        ApplicationInfo applicationInfo;
        while (true) {
            while (true) {
                try {
                    applicationInfo = Wrappers.packageManager(context).getApplicationInfo(s, 0);
                    if (applicationInfo != null) {
                        break;
                    }
                    final String value = String.valueOf(s);
                    if (value.length() != 0) {
                        final String concat = "Could not get applicationInfo from package: ".concat(value);
                        Log.e("WorkSourceUtil", concat);
                        return -1;
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    final String value2 = String.valueOf(s);
                    String concat2;
                    if (value2.length() != 0) {
                        concat2 = "Could not find package: ".concat(value2);
                    }
                    else {
                        concat2 = new String("Could not find package: ");
                    }
                    Log.e("WorkSourceUtil", concat2);
                    return -1;
                }
                final String concat = new String("Could not get applicationInfo from package: ");
                continue;
            }
        }
        return applicationInfo.uid;
    }
    
    private static Method zzx() {
        try {
            return WorkSource.class.getMethod("add", Integer.TYPE);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzy() {
        Method method = null;
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return method;
        }
        try {
            method = WorkSource.class.getMethod("add", Integer.TYPE, String.class);
            return method;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzz() {
        try {
            return WorkSource.class.getMethod("size", (Class<?>[])new Class[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
