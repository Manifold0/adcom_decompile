// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import android.content.pm.PackageInstaller$SessionInfo;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.UserManager;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.HideFirstParty;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import android.content.res.Resources;
import android.app.PendingIntent;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.internal.Preconditions;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.app.NotificationManager;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@ShowFirstParty
public class GooglePlayServicesUtilLight
{
    @KeepForSdk
    static final int GMS_AVAILABILITY_NOTIFICATION_ID = 10436;
    @KeepForSdk
    static final int GMS_GENERAL_ERROR_NOTIFICATION_ID = 39789;
    @KeepForSdk
    public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";
    @Deprecated
    @KeepForSdk
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    @KeepForSdk
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    @KeepForSdk
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    @KeepForSdk
    @VisibleForTesting
    static final AtomicBoolean sCanceledAvailabilityNotification;
    @VisibleForTesting
    private static boolean zzah;
    @VisibleForTesting
    private static boolean zzai;
    private static boolean zzaj;
    @VisibleForTesting
    private static boolean zzak;
    private static final AtomicBoolean zzal;
    
    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
        GooglePlayServicesUtilLight.zzah = false;
        GooglePlayServicesUtilLight.zzai = false;
        GooglePlayServicesUtilLight.zzaj = false;
        GooglePlayServicesUtilLight.zzak = false;
        sCanceledAvailabilityNotification = new AtomicBoolean();
        zzal = new AtomicBoolean();
    }
    
    @KeepForSdk
    GooglePlayServicesUtilLight() {
    }
    
    @Deprecated
    @KeepForSdk
    public static void cancelAvailabilityErrorNotifications(final Context context) {
        if (!GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.getAndSet(true)) {
            try {
                final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
                if (notificationManager != null) {
                    notificationManager.cancel(10436);
                }
            }
            catch (SecurityException ex) {}
        }
    }
    
    @KeepForSdk
    @ShowFirstParty
    public static void enableUsingApkIndependentContext() {
        GooglePlayServicesUtilLight.zzal.set(true);
    }
    
    @Deprecated
    @KeepForSdk
    public static void ensurePlayServicesAvailable(final Context context, int googlePlayServicesAvailable) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        googlePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, googlePlayServicesAvailable);
        if (googlePlayServicesAvailable == 0) {
            return;
        }
        final Intent errorResolutionIntent = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(context, googlePlayServicesAvailable, "e");
        Log.e("GooglePlayServicesUtil", new StringBuilder(57).append("GooglePlayServices not available due to error ").append(googlePlayServicesAvailable).toString());
        if (errorResolutionIntent == null) {
            throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
        }
        throw new GooglePlayServicesRepairableException(googlePlayServicesAvailable, "Google Play Services not available", errorResolutionIntent);
    }
    
    @Deprecated
    @KeepForSdk
    @ShowFirstParty
    public static int getApkVersion(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }
    
    @Deprecated
    @KeepForSdk
    @ShowFirstParty
    public static int getClientVersion(final Context context) {
        Preconditions.checkState(true);
        return ClientLibraryUtils.getClientVersion(context, context.getPackageName());
    }
    
    @Deprecated
    @KeepForSdk
    public static PendingIntent getErrorPendingIntent(final int n, final Context context, final int n2) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(context, n, n2);
    }
    
    @Deprecated
    @KeepForSdk
    @VisibleForTesting
    public static String getErrorString(final int n) {
        return ConnectionResult.zza(n);
    }
    
    @Deprecated
    @KeepForSdk
    @ShowFirstParty
    public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(final int n) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(null, n, null);
    }
    
    @KeepForSdk
    public static Context getRemoteContext(Context packageContext) {
        try {
            packageContext = packageContext.createPackageContext("com.google.android.gms", 3);
            return packageContext;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    @KeepForSdk
    public static Resources getRemoteResource(final Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    @KeepForSdk
    @ShowFirstParty
    public static boolean honorsDebugCertificates(final Context context) {
        boolean b = false;
        while (true) {
            if (GooglePlayServicesUtilLight.zzak) {
                break Label_0053;
            }
            try {
                final PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.google.android.gms", 64);
                GoogleSignatureVerifier.getInstance(context);
                if (packageInfo != null && !GoogleSignatureVerifier.zza(packageInfo, false) && GoogleSignatureVerifier.zza(packageInfo, true)) {
                    GooglePlayServicesUtilLight.zzaj = true;
                }
                else {
                    GooglePlayServicesUtilLight.zzaj = false;
                }
                GooglePlayServicesUtilLight.zzak = true;
                if (GooglePlayServicesUtilLight.zzaj || !DeviceProperties.isUserBuild()) {
                    b = true;
                }
                return b;
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", (Throwable)ex);
                GooglePlayServicesUtilLight.zzak = true;
                continue;
            }
            finally {
                GooglePlayServicesUtilLight.zzak = true;
            }
            break;
        }
    }
    
    @Deprecated
    @KeepForSdk
    @HideFirstParty
    public static int isGooglePlayServicesAvailable(final Context context) {
        return isGooglePlayServicesAvailable(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }
    
    @Deprecated
    @KeepForSdk
    public static int isGooglePlayServicesAvailable(final Context context, int google_PLAY_SERVICES_VERSION_CODE) {
        int zzd;
        while (true) {
            try {
                context.getResources().getString(R$string.common_google_play_services_unknown_issue);
                if ("com.google.android.gms".equals(context.getPackageName()) || GooglePlayServicesUtilLight.zzal.get()) {
                    return zza(context, !DeviceProperties.isWearableWithoutPlayStore(context) && !DeviceProperties.zzf(context), google_PLAY_SERVICES_VERSION_CODE);
                }
                zzd = zzp.zzd(context);
                if (zzd == 0) {
                    throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
                }
            }
            catch (Throwable t) {
                Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
                continue;
            }
            break;
        }
        if (zzd != GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
            google_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
            throw new IllegalStateException(new StringBuilder(320).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(google_PLAY_SERVICES_VERSION_CODE).append(" but found ").append(zzd).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />").toString());
        }
        return zza(context, !DeviceProperties.isWearableWithoutPlayStore(context) && !DeviceProperties.zzf(context), google_PLAY_SERVICES_VERSION_CODE);
    }
    
    @Deprecated
    @KeepForSdk
    public static boolean isGooglePlayServicesUid(final Context context, final int n) {
        return UidVerifier.isGooglePlayServicesUid(context, n);
    }
    
    @Deprecated
    @KeepForSdk
    @ShowFirstParty
    public static boolean isPlayServicesPossiblyUpdating(final Context context, final int n) {
        return n == 18 || (n == 1 && isUninstalledAppPossiblyUpdating(context, "com.google.android.gms"));
    }
    
    @Deprecated
    @KeepForSdk
    @ShowFirstParty
    public static boolean isPlayStorePossiblyUpdating(final Context context, final int n) {
        return n == 9 && isUninstalledAppPossiblyUpdating(context, "com.android.vending");
    }
    
    @TargetApi(18)
    @KeepForSdk
    public static boolean isRestrictedUserProfile(final Context context) {
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            final Bundle applicationRestrictions = ((UserManager)context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
            if (applicationRestrictions != null && "true".equals(applicationRestrictions.getString("restricted_profile"))) {
                return true;
            }
        }
        return false;
    }
    
    @Deprecated
    @KeepForSdk
    @ShowFirstParty
    @VisibleForTesting
    public static boolean isSidewinderDevice(final Context context) {
        return DeviceProperties.isSidewinder(context);
    }
    
    @TargetApi(21)
    static boolean isUninstalledAppPossiblyUpdating(final Context context, final String s) {
        final boolean equals = s.equals("com.google.android.gms");
        if (PlatformVersion.isAtLeastLollipop()) {
            try {
                final Iterator<PackageInstaller$SessionInfo> iterator = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
                while (iterator.hasNext()) {
                    if (s.equals(iterator.next().getAppPackageName())) {
                        return true;
                    }
                }
            }
            catch (Exception ex) {
                return false;
            }
        }
        final PackageManager packageManager = context.getPackageManager();
        try {
            final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(s, 8192);
            if (equals) {
                return applicationInfo.enabled;
            }
            return applicationInfo.enabled && !isRestrictedUserProfile(context);
        }
        catch (PackageManager$NameNotFoundException ex2) {
            return false;
        }
    }
    
    @Deprecated
    @KeepForSdk
    public static boolean isUserRecoverableError(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 1:
            case 2:
            case 3:
            case 9: {
                return true;
            }
        }
    }
    
    @Deprecated
    @TargetApi(19)
    @KeepForSdk
    public static boolean uidHasPackageName(final Context context, final int n, final String s) {
        return UidVerifier.uidHasPackageName(context, n, s);
    }
    
    @VisibleForTesting
    private static int zza(final Context p0, final boolean p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iflt            74
        //     4: iconst_1       
        //     5: istore          4
        //     7: iload           4
        //     9: invokestatic    com/google/android/gms/common/internal/Preconditions.checkArgument:(Z)V
        //    12: aload_0        
        //    13: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    16: astore          6
        //    18: aconst_null    
        //    19: astore          5
        //    21: iload_1        
        //    22: ifeq            37
        //    25: aload           6
        //    27: ldc             "com.android.vending"
        //    29: sipush          8256
        //    32: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    35: astore          5
        //    37: aload           6
        //    39: ldc             "com.google.android.gms"
        //    41: bipush          64
        //    43: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    46: astore          7
        //    48: aload_0        
        //    49: invokestatic    com/google/android/gms/common/GoogleSignatureVerifier.getInstance:(Landroid/content/Context;)Lcom/google/android/gms/common/GoogleSignatureVerifier;
        //    52: pop            
        //    53: aload           7
        //    55: iconst_1       
        //    56: invokestatic    com/google/android/gms/common/GoogleSignatureVerifier.zza:(Landroid/content/pm/PackageInfo;Z)Z
        //    59: ifne            104
        //    62: ldc             "GooglePlayServicesUtil"
        //    64: ldc_w           "Google Play services signature invalid."
        //    67: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    70: pop            
        //    71: bipush          9
        //    73: ireturn        
        //    74: iconst_0       
        //    75: istore          4
        //    77: goto            7
        //    80: astore_0       
        //    81: ldc             "GooglePlayServicesUtil"
        //    83: ldc_w           "Google Play Store is missing."
        //    86: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    89: pop            
        //    90: bipush          9
        //    92: ireturn        
        //    93: astore_0       
        //    94: ldc             "GooglePlayServicesUtil"
        //    96: ldc             "Google Play services is missing."
        //    98: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   101: pop            
        //   102: iconst_1       
        //   103: ireturn        
        //   104: iload_1        
        //   105: ifeq            149
        //   108: aload           5
        //   110: iconst_1       
        //   111: invokestatic    com/google/android/gms/common/GoogleSignatureVerifier.zza:(Landroid/content/pm/PackageInfo;Z)Z
        //   114: ifeq            137
        //   117: aload           5
        //   119: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //   122: iconst_0       
        //   123: aaload         
        //   124: aload           7
        //   126: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //   129: iconst_0       
        //   130: aaload         
        //   131: invokevirtual   android/content/pm/Signature.equals:(Ljava/lang/Object;)Z
        //   134: ifne            149
        //   137: ldc             "GooglePlayServicesUtil"
        //   139: ldc_w           "Google Play Store signature invalid."
        //   142: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   145: pop            
        //   146: bipush          9
        //   148: ireturn        
        //   149: aload           7
        //   151: getfield        android/content/pm/PackageInfo.versionCode:I
        //   154: invokestatic    com/google/android/gms/common/util/zzb.zzc:(I)I
        //   157: iload_2        
        //   158: invokestatic    com/google/android/gms/common/util/zzb.zzc:(I)I
        //   161: if_icmpge       210
        //   164: aload           7
        //   166: getfield        android/content/pm/PackageInfo.versionCode:I
        //   169: istore_3       
        //   170: ldc             "GooglePlayServicesUtil"
        //   172: new             Ljava/lang/StringBuilder;
        //   175: dup            
        //   176: bipush          77
        //   178: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   181: ldc_w           "Google Play services out of date.  Requires "
        //   184: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   187: iload_2        
        //   188: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   191: ldc_w           " but found "
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: iload_3        
        //   198: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   201: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   204: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   207: pop            
        //   208: iconst_2       
        //   209: ireturn        
        //   210: aload           7
        //   212: getfield        android/content/pm/PackageInfo.applicationInfo:Landroid/content/pm/ApplicationInfo;
        //   215: astore          5
        //   217: aload           5
        //   219: astore_0       
        //   220: aload           5
        //   222: ifnonnull       234
        //   225: aload           6
        //   227: ldc             "com.google.android.gms"
        //   229: iconst_0       
        //   230: invokevirtual   android/content/pm/PackageManager.getApplicationInfo:(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //   233: astore_0       
        //   234: aload_0        
        //   235: getfield        android/content/pm/ApplicationInfo.enabled:Z
        //   238: ifne            256
        //   241: iconst_3       
        //   242: ireturn        
        //   243: astore_0       
        //   244: ldc             "GooglePlayServicesUtil"
        //   246: ldc_w           "Google Play services missing when getting application info."
        //   249: aload_0        
        //   250: invokestatic    android/util/Log.wtf:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   253: pop            
        //   254: iconst_1       
        //   255: ireturn        
        //   256: iconst_0       
        //   257: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  25     37     80     93     Landroid/content/pm/PackageManager$NameNotFoundException;
        //  37     48     93     104    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  225    234    243    256    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0037:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
