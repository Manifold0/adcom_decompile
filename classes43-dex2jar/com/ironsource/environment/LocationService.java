// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.environment;

import android.annotation.SuppressLint;
import android.os.Looper;
import android.os.Bundle;
import android.location.LocationListener;
import android.location.Criteria;
import android.location.LocationManager;
import android.util.Log;
import android.location.Location;
import android.content.Context;

public class LocationService
{
    private static String TAG;
    
    static {
        LocationService.TAG = LocationService.class.getSimpleName();
    }
    
    public static Location getLastLocation(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          6
        //     3: aconst_null    
        //     4: astore          4
        //     6: aconst_null    
        //     7: astore          5
        //     9: aload_0        
        //    10: ldc             "android.permission.ACCESS_FINE_LOCATION"
        //    12: invokestatic    com/ironsource/environment/ApplicationContext.isPermissionGranted:(Landroid/content/Context;Ljava/lang/String;)Z
        //    15: ifne            38
        //    18: aload_0        
        //    19: ldc             "android.permission.ACCESS_COARSE_LOCATION"
        //    21: invokestatic    com/ironsource/environment/ApplicationContext.isPermissionGranted:(Landroid/content/Context;Ljava/lang/String;)Z
        //    24: ifne            38
        //    27: getstatic       com/ironsource/environment/LocationService.TAG:Ljava/lang/String;
        //    30: ldc             "Location Permission Not Granted (ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION)"
        //    32: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    35: pop            
        //    36: aconst_null    
        //    37: areturn        
        //    38: aload           6
        //    40: astore_3       
        //    41: aload_0        
        //    42: ldc             "location"
        //    44: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //    47: checkcast       Landroid/location/LocationManager;
        //    50: astore          7
        //    52: aload           7
        //    54: ifnull          143
        //    57: aload           6
        //    59: astore_3       
        //    60: aload           7
        //    62: invokevirtual   android/location/LocationManager.getAllProviders:()Ljava/util/List;
        //    65: astore_0       
        //    66: aload_0        
        //    67: ifnull          152
        //    70: aload           6
        //    72: astore_3       
        //    73: aload_0        
        //    74: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    79: astore          6
        //    81: aload           5
        //    83: astore_0       
        //    84: aload_0        
        //    85: astore_3       
        //    86: aload_0        
        //    87: astore          4
        //    89: aload           6
        //    91: invokeinterface java/util/Iterator.hasNext:()Z
        //    96: ifeq            152
        //    99: aload_0        
        //   100: astore_3       
        //   101: aload           6
        //   103: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   108: checkcast       Ljava/lang/String;
        //   111: astore          4
        //   113: aload           7
        //   115: aload           4
        //   117: invokevirtual   android/location/LocationManager.getLastKnownLocation:(Ljava/lang/String;)Landroid/location/Location;
        //   120: astore_3       
        //   121: aload_3        
        //   122: ifnull          84
        //   125: aload_3        
        //   126: invokevirtual   android/location/Location.getTime:()J
        //   129: lstore_1       
        //   130: lload_1        
        //   131: ldc2_w          -9223372036854775808
        //   134: lcmp           
        //   135: ifle            84
        //   138: aload_3        
        //   139: astore_0       
        //   140: goto            84
        //   143: aconst_null    
        //   144: astore_0       
        //   145: goto            66
        //   148: astore_0       
        //   149: aload_3        
        //   150: astore          4
        //   152: aload           4
        //   154: areturn        
        //   155: astore_3       
        //   156: goto            84
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  41     52     148    152    Ljava/lang/Exception;
        //  60     66     148    152    Ljava/lang/Exception;
        //  73     81     148    152    Ljava/lang/Exception;
        //  89     99     148    152    Ljava/lang/Exception;
        //  101    113    148    152    Ljava/lang/Exception;
        //  113    121    155    159    Ljava/lang/Exception;
        //  125    130    155    159    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0143:
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
    
    @SuppressLint({ "MissingPermission" })
    public static void getPreciseLocation(final Context context, final ISLocationListener isLocationListener) {
        LocationManager locationManager = null;
        Label_0030: {
            if (ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION")) {
                break Label_0030;
            }
            Log.d(LocationService.TAG, "Location Permission Not Granted (ACCESS_FINE_LOCATION)");
            if (isLocationListener == null) {
                break Label_0030;
            }
            isLocationListener.onLocationChanged(null);
            return;
            try {
                locationManager = (LocationManager)context.getSystemService("location");
                if (isLocationListener != null && !locationManager.isProviderEnabled("gps")) {
                    Log.d(LocationService.TAG, "GPS Provider is turned off");
                    isLocationListener.onLocationChanged(null);
                    return;
                }
            }
            catch (Exception ex) {
                if (isLocationListener != null) {
                    isLocationListener.onLocationChanged(null);
                }
                return;
            }
        }
        locationManager.requestSingleUpdate(new Criteria(), (LocationListener)new LocationListener() {
            public void onLocationChanged(final Location location) {
                Log.d("LocationService", "onLocationChanged " + location.getProvider());
                if (isLocationListener != null) {
                    isLocationListener.onLocationChanged(location);
                }
            }
            
            public void onProviderDisabled(final String s) {
                Log.d("LocationService", "onProviderDisabled " + s);
            }
            
            public void onProviderEnabled(final String s) {
                Log.d("LocationService", "onProviderEnabled " + s);
            }
            
            public void onStatusChanged(final String s, final int n, final Bundle bundle) {
                Log.d("LocationService", "onStatusChanged " + s);
            }
        }, Looper.myLooper());
    }
    
    public static boolean locationServicesEnabled(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "android.permission.ACCESS_FINE_LOCATION"
        //     3: invokestatic    com/ironsource/environment/ApplicationContext.isPermissionGranted:(Landroid/content/Context;Ljava/lang/String;)Z
        //     6: ifne            29
        //     9: aload_0        
        //    10: ldc             "android.permission.ACCESS_COARSE_LOCATION"
        //    12: invokestatic    com/ironsource/environment/ApplicationContext.isPermissionGranted:(Landroid/content/Context;Ljava/lang/String;)Z
        //    15: ifne            29
        //    18: getstatic       com/ironsource/environment/LocationService.TAG:Ljava/lang/String;
        //    21: ldc             "Location Permission Not Granted (ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION)"
        //    23: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    26: pop            
        //    27: iconst_0       
        //    28: ireturn        
        //    29: aload_0        
        //    30: ldc             "location"
        //    32: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //    35: checkcast       Landroid/location/LocationManager;
        //    38: astore_0       
        //    39: iconst_0       
        //    40: istore_1       
        //    41: iconst_0       
        //    42: istore_2       
        //    43: aload_0        
        //    44: ldc             "gps"
        //    46: invokevirtual   android/location/LocationManager.isProviderEnabled:(Ljava/lang/String;)Z
        //    49: istore_3       
        //    50: iload_3        
        //    51: istore_1       
        //    52: aload_0        
        //    53: ldc             "network"
        //    55: invokevirtual   android/location/LocationManager.isProviderEnabled:(Ljava/lang/String;)Z
        //    58: istore_3       
        //    59: iload_3        
        //    60: istore_2       
        //    61: iload_1        
        //    62: ifne            69
        //    65: iload_2        
        //    66: ifeq            83
        //    69: iconst_1       
        //    70: ireturn        
        //    71: astore_0       
        //    72: iconst_0       
        //    73: ireturn        
        //    74: astore_0       
        //    75: goto            61
        //    78: astore          4
        //    80: goto            52
        //    83: iconst_0       
        //    84: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      27     71     74     Ljava/lang/Exception;
        //  29     39     71     74     Ljava/lang/Exception;
        //  43     50     78     83     Ljava/lang/Exception;
        //  52     59     74     78     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0052:
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
    
    public interface ISLocationListener
    {
        void onLocationChanged(final Location p0);
    }
}
