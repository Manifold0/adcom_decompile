// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.device;

import android.provider.Settings$Global;
import android.content.pm.PackageManager$NameNotFoundException;
import android.net.NetworkInfo;
import java.util.UUID;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.provider.Settings$System;
import android.media.AudioManager;
import android.annotation.TargetApi;
import java.util.Collection;
import java.util.Arrays;
import android.telephony.TelephonyManager;
import android.net.ConnectivityManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.HashMap;
import android.content.pm.PackageInfo;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import android.content.pm.ConfigurationInfo;
import android.app.ActivityManager;
import java.io.File;
import android.os.SystemClock;
import android.content.pm.Signature;
import android.content.pm.PackageManager;
import com.unity3d.services.core.misc.Utilities;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.MessageDigest;
import android.os.Build;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Build$VERSION;
import android.annotation.SuppressLint;
import com.unity3d.services.core.log.DeviceLog;
import android.provider.Settings$Secure;
import com.unity3d.services.core.properties.ClientProperties;

public class Device
{
    public static String getAdvertisingTrackingId() {
        return AdvertisingId.getAdvertisingTrackingId();
    }
    
    @SuppressLint({ "DefaultLocale" })
    public static String getAndroidId() {
        try {
            return Settings$Secure.getString(ClientProperties.getApplicationContext().getContentResolver(), "android_id");
        }
        catch (Exception ex) {
            DeviceLog.exception("Problems fetching androidId", ex);
            return null;
        }
    }
    
    public static int getApiLevel() {
        return Build$VERSION.SDK_INT;
    }
    
    public static String getApkDigest() throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokevirtual   android/content/Context.getPackageCodePath:()Ljava/lang/String;
        //     6: astore_0       
        //     7: aconst_null    
        //     8: astore_1       
        //     9: new             Ljava/io/FileInputStream;
        //    12: dup            
        //    13: new             Ljava/io/File;
        //    16: dup            
        //    17: aload_0        
        //    18: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    21: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    24: astore_0       
        //    25: aload_0        
        //    26: invokestatic    com/unity3d/services/core/misc/Utilities.Sha256:(Ljava/io/InputStream;)Ljava/lang/String;
        //    29: astore_1       
        //    30: aload_0        
        //    31: ifnull          38
        //    34: aload_0        
        //    35: invokevirtual   java/io/InputStream.close:()V
        //    38: aload_1        
        //    39: areturn        
        //    40: astore_0       
        //    41: aload_1        
        //    42: ifnull          49
        //    45: aload_1        
        //    46: invokevirtual   java/io/InputStream.close:()V
        //    49: aload_0        
        //    50: athrow         
        //    51: astore_0       
        //    52: aload_1        
        //    53: areturn        
        //    54: astore_1       
        //    55: goto            49
        //    58: astore_2       
        //    59: aload_0        
        //    60: astore_1       
        //    61: aload_2        
        //    62: astore_0       
        //    63: goto            41
        //    Exceptions:
        //  throws java.lang.Exception
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      25     40     41     Any
        //  25     30     58     66     Any
        //  34     38     51     54     Ljava/io/IOException;
        //  45     49     54     58     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    public static float getBatteryLevel() {
        if (ClientProperties.getApplicationContext() != null) {
            final Intent registerReceiver = ClientProperties.getApplicationContext().registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null) {
                return registerReceiver.getIntExtra("level", -1) / (float)registerReceiver.getIntExtra("scale", -1);
            }
        }
        return -1.0f;
    }
    
    public static int getBatteryStatus() {
        int intExtra = -1;
        if (ClientProperties.getApplicationContext() != null) {
            final Intent registerReceiver = ClientProperties.getApplicationContext().registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            intExtra = intExtra;
            if (registerReceiver != null) {
                intExtra = registerReceiver.getIntExtra("status", -1);
            }
        }
        return intExtra;
    }
    
    public static String getBoard() {
        return Build.BOARD;
    }
    
    public static String getBootloader() {
        return Build.BOOTLOADER;
    }
    
    public static String getBrand() {
        return Build.BRAND;
    }
    
    public static String getBuildId() {
        return Build.ID;
    }
    
    public static String getBuildVersionIncremental() {
        return Build$VERSION.INCREMENTAL;
    }
    
    public static long getCPUCount() {
        return Runtime.getRuntime().availableProcessors();
    }
    
    public static String getCertificateFingerprint() {
        final String s = null;
        final PackageManager packageManager = ClientProperties.getApplicationContext().getPackageManager();
        final String packageName = ClientProperties.getApplicationContext().getPackageName();
        try {
            final Signature[] signatures = packageManager.getPackageInfo(packageName, 64).signatures;
            String hexString = s;
            if (signatures != null) {
                hexString = s;
                if (signatures.length >= 1) {
                    hexString = Utilities.toHexString(MessageDigest.getInstance("SHA-1").digest(((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatures[0].toByteArray()))).getEncoded()));
                }
            }
            return hexString;
        }
        catch (Exception ex) {
            DeviceLog.exception("Exception when signing certificate fingerprint", ex);
            return null;
        }
    }
    
    public static String getDevice() {
        return Build.DEVICE;
    }
    
    public static long getElapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
    
    public static String getFingerprint() {
        return Build.FINGERPRINT;
    }
    
    public static long getFreeMemory() {
        return getMemoryInfo(MemoryInfoType.FREE_MEMORY);
    }
    
    public static long getFreeSpace(final File file) {
        if (file != null && file.exists()) {
            return Math.round((float)(file.getFreeSpace() / 1024L));
        }
        return -1L;
    }
    
    public static String getGLVersion() {
        if (ClientProperties.getApplicationContext() != null) {
            final ActivityManager activityManager = (ActivityManager)ClientProperties.getApplicationContext().getSystemService("activity");
            if (activityManager != null) {
                final ConfigurationInfo deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo();
                if (deviceConfigurationInfo != null) {
                    return deviceConfigurationInfo.getGlEsVersion();
                }
            }
        }
        return null;
    }
    
    public static String getHardware() {
        return Build.HARDWARE;
    }
    
    public static String getHost() {
        return Build.HOST;
    }
    
    public static List<Map<String, Object>> getInstalledPackages(final boolean b) {
        final ArrayList<HashMap<String, Long>> list = (ArrayList<HashMap<String, Long>>)new ArrayList<Map<String, Long>>();
        if (ClientProperties.getApplicationContext() != null) {
            final PackageManager packageManager = ClientProperties.getApplicationContext().getPackageManager();
            for (final PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
                final HashMap<String, Object> hashMap = new HashMap<String, Object>();
                if (b) {
                    hashMap.put("name", Utilities.Sha256(packageInfo.packageName));
                }
                else {
                    hashMap.put("name", packageInfo.packageName);
                }
                if (packageInfo.firstInstallTime > 0L) {
                    hashMap.put("time", packageInfo.firstInstallTime);
                }
                final String installerPackageName = packageManager.getInstallerPackageName(packageInfo.packageName);
                if (installerPackageName != null && !installerPackageName.isEmpty()) {
                    hashMap.put("installer", installerPackageName);
                }
                list.add((HashMap<String, Long>)hashMap);
            }
        }
        return (List<Map<String, Object>>)list;
    }
    
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }
    
    private static long getMemoryInfo(final MemoryInfoType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_1       
        //     2: getstatic       com/unity3d/services/core/device/Device$1.$SwitchMap$com$unity3d$services$core$device$Device$MemoryInfoType:[I
        //     5: aload_0        
        //     6: invokevirtual   com/unity3d/services/core/device/Device$MemoryInfoType.ordinal:()I
        //     9: iaload         
        //    10: tableswitch {
        //                2: 198
        //                3: 203
        //          default: 32
        //        }
        //    32: aconst_null    
        //    33: astore          5
        //    35: aconst_null    
        //    36: astore          7
        //    38: aconst_null    
        //    39: astore          8
        //    41: new             Ljava/io/RandomAccessFile;
        //    44: dup            
        //    45: ldc_w           "/proc/meminfo"
        //    48: ldc_w           "r"
        //    51: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    54: astore          6
        //    56: iconst_0       
        //    57: istore_2       
        //    58: aload           8
        //    60: astore          5
        //    62: iload_2        
        //    63: iload_1        
        //    64: if_icmpge       81
        //    67: aload           6
        //    69: invokevirtual   java/io/RandomAccessFile.readLine:()Ljava/lang/String;
        //    72: astore          5
        //    74: iload_2        
        //    75: iconst_1       
        //    76: iadd           
        //    77: istore_2       
        //    78: goto            62
        //    81: aload           5
        //    83: invokestatic    com/unity3d/services/core/device/Device.getMemoryValueFromString:(Ljava/lang/String;)J
        //    86: lstore_3       
        //    87: aload           6
        //    89: invokevirtual   java/io/RandomAccessFile.close:()V
        //    92: lload_3        
        //    93: lreturn        
        //    94: astore_0       
        //    95: ldc_w           "Error closing RandomAccessFile"
        //    98: aload_0        
        //    99: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   102: goto            92
        //   105: astore          5
        //   107: aload           7
        //   109: astore          6
        //   111: aload           5
        //   113: astore          7
        //   115: aload           6
        //   117: astore          5
        //   119: new             Ljava/lang/StringBuilder;
        //   122: dup            
        //   123: invokespecial   java/lang/StringBuilder.<init>:()V
        //   126: ldc_w           "Error while reading memory info: "
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: aload_0        
        //   133: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   136: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   139: aload           7
        //   141: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   144: aload           6
        //   146: invokevirtual   java/io/RandomAccessFile.close:()V
        //   149: ldc2_w          -1
        //   152: lreturn        
        //   153: astore_0       
        //   154: ldc_w           "Error closing RandomAccessFile"
        //   157: aload_0        
        //   158: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   161: goto            149
        //   164: astore_0       
        //   165: aload           5
        //   167: invokevirtual   java/io/RandomAccessFile.close:()V
        //   170: aload_0        
        //   171: athrow         
        //   172: astore          5
        //   174: ldc_w           "Error closing RandomAccessFile"
        //   177: aload           5
        //   179: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   182: goto            170
        //   185: astore_0       
        //   186: aload           6
        //   188: astore          5
        //   190: goto            165
        //   193: astore          7
        //   195: goto            115
        //   198: iconst_1       
        //   199: istore_1       
        //   200: goto            32
        //   203: iconst_2       
        //   204: istore_1       
        //   205: goto            32
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  41     56     105    115    Ljava/io/IOException;
        //  41     56     164    165    Any
        //  67     74     193    198    Ljava/io/IOException;
        //  67     74     185    193    Any
        //  81     87     193    198    Ljava/io/IOException;
        //  81     87     185    193    Any
        //  87     92     94     105    Ljava/io/IOException;
        //  119    144    164    165    Any
        //  144    149    153    164    Ljava/io/IOException;
        //  165    170    172    185    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0081:
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
    
    private static long getMemoryValueFromString(String group) {
        if (group != null) {
            final Matcher matcher = Pattern.compile("(\\d+)").matcher(group);
            group = "";
            while (matcher.find()) {
                group = matcher.group(1);
            }
            return Long.parseLong(group);
        }
        return -1L;
    }
    
    public static String getModel() {
        return Build.MODEL;
    }
    
    public static boolean getNetworkMetered() {
        if (ClientProperties.getApplicationContext() != null && Build$VERSION.SDK_INT >= 16) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager != null) {
                return connectivityManager.isActiveNetworkMetered();
            }
        }
        return false;
    }
    
    public static String getNetworkOperator() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkOperator();
        }
        return "";
    }
    
    public static String getNetworkOperatorName() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkOperatorName();
        }
        return "";
    }
    
    public static int getNetworkType() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkType();
        }
        return -1;
    }
    
    @TargetApi(21)
    private static ArrayList<String> getNewAbiList() {
        final ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(Build.SUPPORTED_ABIS));
        return list;
    }
    
    private static ArrayList<String> getOldAbiList() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(Build.CPU_ABI);
        list.add(Build.CPU_ABI2);
        return list;
    }
    
    public static String getOsVersion() {
        return Build$VERSION.RELEASE;
    }
    
    public static Map<String, String> getProcessInfo() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/HashMap.<init>:()V
        //     7: astore_3       
        //     8: aconst_null    
        //     9: astore_0       
        //    10: aconst_null    
        //    11: astore_2       
        //    12: new             Ljava/io/RandomAccessFile;
        //    15: dup            
        //    16: ldc_w           "/proc/self/stat"
        //    19: ldc_w           "r"
        //    22: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    25: astore_1       
        //    26: aload_3        
        //    27: ldc_w           "stat"
        //    30: aload_1        
        //    31: invokevirtual   java/io/RandomAccessFile.readLine:()Ljava/lang/String;
        //    34: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    37: pop            
        //    38: aload_1        
        //    39: invokevirtual   java/io/RandomAccessFile.close:()V
        //    42: aload_3        
        //    43: areturn        
        //    44: astore_0       
        //    45: ldc_w           "Error closing RandomAccessFile"
        //    48: aload_0        
        //    49: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //    52: aload_3        
        //    53: areturn        
        //    54: astore_0       
        //    55: aload_2        
        //    56: astore_1       
        //    57: aload_0        
        //    58: astore_2       
        //    59: aload_1        
        //    60: astore_0       
        //    61: ldc_w           "Error while reading processor info: "
        //    64: aload_2        
        //    65: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //    68: aload_1        
        //    69: invokevirtual   java/io/RandomAccessFile.close:()V
        //    72: aload_3        
        //    73: areturn        
        //    74: astore_0       
        //    75: ldc_w           "Error closing RandomAccessFile"
        //    78: aload_0        
        //    79: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //    82: aload_3        
        //    83: areturn        
        //    84: astore_1       
        //    85: aload_0        
        //    86: invokevirtual   java/io/RandomAccessFile.close:()V
        //    89: aload_1        
        //    90: athrow         
        //    91: astore_0       
        //    92: ldc_w           "Error closing RandomAccessFile"
        //    95: aload_0        
        //    96: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //    99: goto            89
        //   102: astore_2       
        //   103: aload_1        
        //   104: astore_0       
        //   105: aload_2        
        //   106: astore_1       
        //   107: goto            85
        //   110: astore_2       
        //   111: goto            59
        //    Signature:
        //  ()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     26     54     59     Ljava/io/IOException;
        //  12     26     84     85     Any
        //  26     38     110    114    Ljava/io/IOException;
        //  26     38     102    110    Any
        //  38     42     44     54     Ljava/io/IOException;
        //  61     68     84     85     Any
        //  68     72     74     84     Ljava/io/IOException;
        //  85     89     91     102    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    public static String getProduct() {
        return Build.PRODUCT;
    }
    
    public static int getRingerMode() {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        final AudioManager audioManager = (AudioManager)ClientProperties.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getRingerMode();
        }
        return -2;
    }
    
    public static int getScreenBrightness() {
        int int1 = -1;
        if (ClientProperties.getApplicationContext() != null) {
            int1 = Settings$System.getInt(ClientProperties.getApplicationContext().getContentResolver(), "screen_brightness", -1);
        }
        return int1;
    }
    
    public static int getScreenDensity() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        }
        return -1;
    }
    
    public static int getScreenHeight() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        }
        return -1;
    }
    
    public static int getScreenLayout() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getConfiguration().screenLayout;
        }
        return -1;
    }
    
    public static int getScreenWidth() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        }
        return -1;
    }
    
    public static List<Sensor> getSensorList() {
        if (ClientProperties.getApplicationContext() != null) {
            return (List<Sensor>)((SensorManager)ClientProperties.getApplicationContext().getSystemService("sensor")).getSensorList(-1);
        }
        return null;
    }
    
    public static int getStreamMaxVolume(final int n) {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        final AudioManager audioManager = (AudioManager)ClientProperties.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getStreamMaxVolume(n);
        }
        return -2;
    }
    
    public static int getStreamVolume(final int n) {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        final AudioManager audioManager = (AudioManager)ClientProperties.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getStreamVolume(n);
        }
        return -2;
    }
    
    public static ArrayList<String> getSupportedAbis() {
        if (getApiLevel() < 21) {
            return getOldAbiList();
        }
        return getNewAbiList();
    }
    
    public static String getSystemProperty(final String s, final String s2) {
        if (s2 != null) {
            return System.getProperty(s, s2);
        }
        return System.getProperty(s);
    }
    
    public static long getTotalMemory() {
        return getMemoryInfo(MemoryInfoType.TOTAL_MEMORY);
    }
    
    public static long getTotalSpace(final File file) {
        if (file != null && file.exists()) {
            return Math.round((float)(file.getTotalSpace() / 1024L));
        }
        return -1L;
    }
    
    public static String getUniqueEventId() {
        return UUID.randomUUID().toString();
    }
    
    public static long getUptime() {
        return SystemClock.uptimeMillis();
    }
    
    public static boolean isActiveNetworkConnected() {
        boolean b2;
        final boolean b = b2 = false;
        if (ClientProperties.getApplicationContext() != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
            b2 = b;
            if (connectivityManager != null) {
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                b2 = b;
                if (activeNetworkInfo != null) {
                    b2 = b;
                    if (activeNetworkInfo.isConnected()) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    public static Boolean isAdbEnabled() {
        if (getApiLevel() < 17) {
            return oldAdbStatus();
        }
        return newAdbStatus();
    }
    
    public static boolean isAppInstalled(final String s) {
        boolean b2;
        final boolean b = b2 = false;
        if (ClientProperties.getApplicationContext() == null) {
            return b2;
        }
        final PackageManager packageManager = ClientProperties.getApplicationContext().getPackageManager();
        try {
            final PackageInfo packageInfo = packageManager.getPackageInfo(s, 0);
            b2 = b;
            if (packageInfo != null) {
                b2 = b;
                if (packageInfo.packageName != null) {
                    final boolean equals = s.equals(packageInfo.packageName);
                    b2 = b;
                    if (equals) {
                        b2 = true;
                    }
                }
            }
            return b2;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    public static boolean isLimitAdTrackingEnabled() {
        return AdvertisingId.getLimitedAdTracking();
    }
    
    public static boolean isRooted() {
        try {
            return searchPathForBinary("su");
        }
        catch (Exception ex) {
            DeviceLog.exception("Rooted check failed", ex);
            return false;
        }
    }
    
    public static boolean isUSBConnected() {
        boolean booleanExtra = false;
        if (ClientProperties.getApplicationContext() != null) {
            final Intent registerReceiver = ClientProperties.getApplicationContext().registerReceiver((BroadcastReceiver)null, new IntentFilter("android.hardware.usb.action.USB_STATE"));
            booleanExtra = booleanExtra;
            if (registerReceiver != null) {
                booleanExtra = registerReceiver.getBooleanExtra("connected", false);
            }
        }
        return booleanExtra;
    }
    
    public static boolean isUsingWifi() {
        boolean b = true;
        if (ClientProperties.getApplicationContext() != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager != null) {
                final TelephonyManager telephonyManager = (TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone");
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && connectivityManager.getBackgroundDataSetting() && connectivityManager.getActiveNetworkInfo().isConnected() && telephonyManager != null) {
                    if (activeNetworkInfo.getType() != 1 || !activeNetworkInfo.isConnected()) {
                        b = false;
                    }
                    return b;
                }
            }
        }
        return false;
    }
    
    public static boolean isWiredHeadsetOn() {
        return ClientProperties.getApplicationContext() != null && ((AudioManager)ClientProperties.getApplicationContext().getSystemService("audio")).isWiredHeadsetOn();
    }
    
    @TargetApi(17)
    private static Boolean newAdbStatus() {
        boolean b = true;
        try {
            if (1 != Settings$Global.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0)) {
                b = false;
            }
            return b;
        }
        catch (Exception ex) {
            DeviceLog.exception("Problems fetching adb enabled status", ex);
            return null;
        }
    }
    
    private static Boolean oldAdbStatus() {
        boolean b = true;
        try {
            if (1 != Settings$Secure.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0)) {
                b = false;
            }
            return b;
        }
        catch (Exception ex) {
            DeviceLog.exception("Problems fetching adb enabled status", ex);
            return null;
        }
    }
    
    private static boolean searchPathForBinary(final String s) {
        final boolean b = false;
        final String[] split = System.getenv("PATH").split(":");
        final int length = split.length;
        int n = 0;
        boolean b2 = false;
    Label_0103:
        while (true) {
            b2 = b;
            if (n >= length) {
                break;
            }
            final File file = new File(split[n]);
            if (file.exists() && file.isDirectory()) {
                final File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (int length2 = listFiles.length, i = 0; i < length2; ++i) {
                        if (listFiles[i].getName().equals(s)) {
                            b2 = true;
                            break Label_0103;
                        }
                    }
                }
            }
            ++n;
        }
        return b2;
    }
    
    public enum MemoryInfoType
    {
        FREE_MEMORY, 
        TOTAL_MEMORY;
    }
}
