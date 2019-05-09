// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.wrappers;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import com.google.android.gms.common.util.PlatformVersion;
import android.os.Process;
import android.os.Binder;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.ApplicationInfo;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class PackageManagerWrapper
{
    private final Context zzhx;
    
    public PackageManagerWrapper(final Context zzhx) {
        this.zzhx = zzhx;
    }
    
    @KeepForSdk
    public int checkCallingOrSelfPermission(final String s) {
        return this.zzhx.checkCallingOrSelfPermission(s);
    }
    
    @KeepForSdk
    public int checkPermission(final String s, final String s2) {
        return this.zzhx.getPackageManager().checkPermission(s, s2);
    }
    
    @KeepForSdk
    public ApplicationInfo getApplicationInfo(final String s, final int n) throws PackageManager$NameNotFoundException {
        return this.zzhx.getPackageManager().getApplicationInfo(s, n);
    }
    
    @KeepForSdk
    public CharSequence getApplicationLabel(final String s) throws PackageManager$NameNotFoundException {
        return this.zzhx.getPackageManager().getApplicationLabel(this.zzhx.getPackageManager().getApplicationInfo(s, 0));
    }
    
    @KeepForSdk
    public PackageInfo getPackageInfo(final String s, final int n) throws PackageManager$NameNotFoundException {
        return this.zzhx.getPackageManager().getPackageInfo(s, n);
    }
    
    public final String[] getPackagesForUid(final int n) {
        return this.zzhx.getPackageManager().getPackagesForUid(n);
    }
    
    @KeepForSdk
    public boolean isCallerInstantApp() {
        if (Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.zzhx);
        }
        if (PlatformVersion.isAtLeastO()) {
            final String nameForUid = this.zzhx.getPackageManager().getNameForUid(Binder.getCallingUid());
            if (nameForUid != null) {
                return this.zzhx.getPackageManager().isInstantApp(nameForUid);
            }
        }
        return false;
    }
    
    public final PackageInfo zza(final String s, final int n, final int n2) throws PackageManager$NameNotFoundException {
        return this.zzhx.getPackageManager().getPackageInfo(s, 64);
    }
    
    @TargetApi(19)
    public final boolean zzb(int n, final String s) {
        final boolean b = false;
        Label_0030: {
            if (!PlatformVersion.isAtLeastKitKat()) {
                break Label_0030;
            }
            try {
                ((AppOpsManager)this.zzhx.getSystemService("appops")).checkPackage(n, s);
                boolean b2 = true;
                Label_0028: {
                    return b2;
                }
                final String[] packagesForUid = this.zzhx.getPackageManager().getPackagesForUid(n);
                b2 = b;
                // iftrue(Label_0028:, s == null)
                b2 = b;
                // iftrue(Label_0028:, packagesForUid == null)
                n = 0;
                // iftrue(Label_0028:, n >= packagesForUid.length)
            Block_5:
                while (true) {
                    b2 = b;
                    break Block_5;
                    Label_0083:
                    ++n;
                    continue;
                }
                // iftrue(Label_0083:, !s.equals((Object)packagesForUid[n]))
                return true;
            }
            catch (SecurityException ex) {
                return false;
            }
        }
    }
}
