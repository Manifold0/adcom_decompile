// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.wrappers.Wrappers;
import android.util.Log;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
@KeepForSdk
@ShowFirstParty
public class GoogleSignatureVerifier
{
    private static GoogleSignatureVerifier zzam;
    private final Context mContext;
    private volatile String zzan;
    
    private GoogleSignatureVerifier(final Context context) {
        this.mContext = context.getApplicationContext();
    }
    
    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(final Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (GoogleSignatureVerifier.zzam == null) {
                zzc.zza(context);
                GoogleSignatureVerifier.zzam = new GoogleSignatureVerifier(context);
            }
            return GoogleSignatureVerifier.zzam;
        }
    }
    
    private static zze zza(final PackageInfo packageInfo, final zze... array) {
        int i = 0;
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        final zzf zzf = new zzf(packageInfo.signatures[0].toByteArray());
        while (i < array.length) {
            if (array[i].equals(zzf)) {
                return array[i];
            }
            ++i;
        }
        return null;
    }
    
    private final zzm zza(String s, final int n) {
        zzm zza2;
        try {
            final PackageInfo zza = Wrappers.packageManager(this.mContext).zza(s, 64, n);
            final boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
            if (zza == null) {
                return zzm.zzb("null pkg");
            }
            if (zza.signatures.length != 1) {
                return zzm.zzb("single cert required");
            }
            final zzf zzf = new zzf(zza.signatures[0].toByteArray());
            final String packageName = zza.packageName;
            zza2 = zzc.zza(packageName, zzf, honorsDebugCertificates, false);
            if (zza2.zzad && zza.applicationInfo != null && (zza.applicationInfo.flags & 0x2) != 0x0 && zzc.zza(packageName, zzf, false, true).zzad) {
                return zzm.zzb("debuggable release cert app rejected");
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            s = String.valueOf(s);
            if (s.length() != 0) {
                s = "no pkg ".concat(s);
            }
            else {
                s = new String("no pkg ");
            }
            return zzm.zzb(s);
        }
        return zza2;
    }
    
    public static boolean zza(final PackageInfo packageInfo, final boolean b) {
        if (packageInfo != null && packageInfo.signatures != null) {
            zze zze;
            if (b) {
                zze = zza(packageInfo, zzh.zzx);
            }
            else {
                zze = zza(packageInfo, zzh.zzx[0]);
            }
            if (zze != null) {
                return true;
            }
        }
        return false;
    }
    
    private final zzm zzc(String zzan) {
        zzm zzb;
        if (zzan == null) {
            zzb = zzm.zzb("null pkg");
        }
        else {
            if (zzan.equals(this.zzan)) {
                return zzm.zze();
            }
            while (true) {
                while (true) {
                    PackageInfo packageInfo;
                    boolean honorsDebugCertificates;
                    try {
                        packageInfo = Wrappers.packageManager(this.mContext).getPackageInfo(zzan, 64);
                        honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext);
                        if (packageInfo == null) {
                            final zzm zzm = com.google.android.gms.common.zzm.zzb("null pkg");
                            zzb = zzm;
                            if (zzm.zzad) {
                                this.zzan = zzan;
                                return zzm;
                            }
                            break;
                        }
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        zzan = String.valueOf(zzan);
                        if (zzan.length() != 0) {
                            zzan = "no pkg ".concat(zzan);
                        }
                        else {
                            zzan = new String("no pkg ");
                        }
                        return zzm.zzb(zzan);
                    }
                    if (packageInfo.signatures.length != 1) {
                        final zzm zzm = com.google.android.gms.common.zzm.zzb("single cert required");
                        continue;
                    }
                    final zzf zzf = new zzf(packageInfo.signatures[0].toByteArray());
                    final String packageName = packageInfo.packageName;
                    zzm zzm;
                    final zzm zzm2 = zzm = zzc.zza(packageName, zzf, honorsDebugCertificates, (boolean)(0 != 0));
                    if (!zzm2.zzad) {
                        continue;
                    }
                    zzm = zzm2;
                    if (packageInfo.applicationInfo == null) {
                        continue;
                    }
                    zzm = zzm2;
                    if ((packageInfo.applicationInfo.flags & 0x2) == 0x0) {
                        continue;
                    }
                    zzm = zzm2;
                    if (zzc.zza(packageName, zzf, false, true).zzad) {
                        zzm = com.google.android.gms.common.zzm.zzb("debuggable release cert app rejected");
                        continue;
                    }
                    continue;
                }
            }
        }
        return zzb;
    }
    
    @KeepForSdk
    public boolean isGooglePublicSignedPackage(final PackageInfo packageInfo) {
        if (packageInfo != null) {
            if (zza(packageInfo, false)) {
                return true;
            }
            if (zza(packageInfo, true)) {
                if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                    return true;
                }
                Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
                return false;
            }
        }
        return false;
    }
    
    @KeepForSdk
    @ShowFirstParty
    public boolean isPackageGoogleSigned(final String s) {
        final zzm zzc = this.zzc(s);
        zzc.zzf();
        return zzc.zzad;
    }
    
    @KeepForSdk
    @ShowFirstParty
    public boolean isUidGoogleSigned(final int n) {
        final String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(n);
        zzm zzm;
        if (packagesForUid == null || packagesForUid.length == 0) {
            zzm = com.google.android.gms.common.zzm.zzb("no pkgs");
        }
        else {
            final int length = packagesForUid.length;
            zzm = null;
            zzm zzm2;
            for (int i = 0; i < length; ++i, zzm = zzm2) {
                zzm2 = (zzm = this.zza(packagesForUid[i], n));
                if (zzm2.zzad) {
                    break;
                }
            }
        }
        zzm.zzf();
        return zzm.zzad;
    }
}
