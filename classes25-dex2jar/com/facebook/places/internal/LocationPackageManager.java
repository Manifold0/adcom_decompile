// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.internal;

import java.util.concurrent.Callable;
import android.util.Log;
import com.facebook.FacebookSdk;
import java.util.concurrent.FutureTask;

public class LocationPackageManager
{
    private static final String TAG = "LocationPackageManager";
    
    private static void logException(final String s, final Throwable t) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e("LocationPackageManager", s, t);
        }
    }
    
    private static FutureTask<LocationPackage> newBluetoothScanFuture(final LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<LocationPackage>(new Callable<LocationPackage>() {
            @Override
            public LocationPackage call() throws Exception {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     3: dup            
                //     4: invokespecial   com/facebook/places/internal/LocationPackage.<init>:()V
                //     7: astore_2       
                //     8: invokestatic    com/facebook/FacebookSdk.getApplicationContext:()Landroid/content/Context;
                //    11: aload_0        
                //    12: getfield        com/facebook/places/internal/LocationPackageManager$3.val$requestParams:Lcom/facebook/places/internal/LocationPackageRequestParams;
                //    15: invokestatic    com/facebook/places/internal/ScannerFactory.newBleScanner:(Landroid/content/Context;Lcom/facebook/places/internal/LocationPackageRequestParams;)Lcom/facebook/places/internal/BleScanner;
                //    18: astore_3       
                //    19: aload_3        
                //    20: invokeinterface com/facebook/places/internal/BleScanner.initAndCheckEligibility:()V
                //    25: aload_3        
                //    26: invokeinterface com/facebook/places/internal/BleScanner.startScanning:()V
                //    31: aload_0        
                //    32: getfield        com/facebook/places/internal/LocationPackageManager$3.val$requestParams:Lcom/facebook/places/internal/LocationPackageRequestParams;
                //    35: invokevirtual   com/facebook/places/internal/LocationPackageRequestParams.getBluetoothScanDurationMs:()J
                //    38: invokestatic    java/lang/Thread.sleep:(J)V
                //    41: aload_3        
                //    42: invokeinterface com/facebook/places/internal/BleScanner.stopScanning:()V
                //    47: aload_3        
                //    48: invokeinterface com/facebook/places/internal/BleScanner.getErrorCode:()I
                //    53: istore_1       
                //    54: iload_1        
                //    55: ifne            100
                //    58: aload_2        
                //    59: aload_3        
                //    60: invokeinterface com/facebook/places/internal/BleScanner.getScanResults:()Ljava/util/List;
                //    65: putfield        com/facebook/places/internal/LocationPackage.ambientBluetoothLe:Ljava/util/List;
                //    68: aload_2        
                //    69: iconst_1       
                //    70: putfield        com/facebook/places/internal/LocationPackage.isBluetoothScanningEnabled:Z
                //    73: aload_2        
                //    74: areturn        
                //    75: astore          4
                //    77: aload_3        
                //    78: invokeinterface com/facebook/places/internal/BleScanner.stopScanning:()V
                //    83: aload           4
                //    85: athrow         
                //    86: astore_3       
                //    87: ldc             "Exception scanning for bluetooth beacons"
                //    89: aload_3        
                //    90: invokestatic    com/facebook/places/internal/LocationPackageManager.access$300:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //    93: aload_2        
                //    94: iconst_0       
                //    95: putfield        com/facebook/places/internal/LocationPackage.isBluetoothScanningEnabled:Z
                //    98: aload_2        
                //    99: areturn        
                //   100: invokestatic    com/facebook/FacebookSdk.isDebugEnabled:()Z
                //   103: ifeq            128
                //   106: ldc             "LocationPackageManager"
                //   108: ldc             "Bluetooth LE scan failed with error: %d"
                //   110: iconst_1       
                //   111: anewarray       Ljava/lang/Object;
                //   114: dup            
                //   115: iconst_0       
                //   116: iload_1        
                //   117: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
                //   120: aastore        
                //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //   124: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   127: pop            
                //   128: aload_2        
                //   129: iconst_0       
                //   130: putfield        com/facebook/places/internal/LocationPackage.isBluetoothScanningEnabled:Z
                //   133: aload_2        
                //   134: areturn        
                //   135: astore          4
                //   137: goto            41
                //    Exceptions:
                //  throws java.lang.Exception
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  8      25     86     100    Ljava/lang/Exception;
                //  25     31     75     86     Any
                //  31     41     135    140    Ljava/lang/Exception;
                //  31     41     75     86     Any
                //  41     54     86     100    Ljava/lang/Exception;
                //  58     73     86     100    Ljava/lang/Exception;
                //  77     86     86     100    Ljava/lang/Exception;
                //  100    128    86     100    Ljava/lang/Exception;
                //  128    133    86     100    Ljava/lang/Exception;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    private static FutureTask<LocationPackage> newLocationScanFuture(final LocationScanner locationScanner, final LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<LocationPackage>(new Callable<LocationPackage>() {
            @Override
            public LocationPackage call() throws Exception {
                final LocationPackage locationPackage = new LocationPackage();
                try {
                    locationPackage.location = locationScanner.getLocation();
                    return locationPackage;
                }
                catch (ScannerException ex) {
                    locationPackage.locationError = ex.type;
                    logException("Exception while getting location", ex);
                    return locationPackage;
                }
                catch (Exception ex2) {
                    locationPackage.locationError = ScannerException.Type.UNKNOWN_ERROR;
                    return locationPackage;
                }
            }
        });
    }
    
    private static FutureTask<LocationPackage> newWifiScanFuture(final LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<LocationPackage>(new Callable<LocationPackage>() {
            @Override
            public LocationPackage call() throws Exception {
                final LocationPackage locationPackage = new LocationPackage();
                try {
                    final WifiScanner wifiScanner = ScannerFactory.newWifiScanner(FacebookSdk.getApplicationContext(), locationPackageRequestParams);
                    wifiScanner.initAndCheckEligibility();
                    locationPackage.connectedWifi = wifiScanner.getConnectedWifi();
                    locationPackage.isWifiScanningEnabled = wifiScanner.isWifiScanningEnabled();
                    if (locationPackage.isWifiScanningEnabled) {
                        locationPackage.ambientWifi = wifiScanner.getWifiScans();
                    }
                    return locationPackage;
                }
                catch (Exception ex) {
                    logException("Exception scanning for wifi access points", ex);
                    locationPackage.isWifiScanningEnabled = false;
                    return locationPackage;
                }
            }
        });
    }
    
    public static void requestLocationPackage(final LocationPackageRequestParams locationPackageRequestParams, final Listener listener) {
        FacebookSdk.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     3: dup            
                //     4: invokespecial   com/facebook/places/internal/LocationPackage.<init>:()V
                //     7: astore          4
                //     9: aconst_null    
                //    10: astore_1       
                //    11: aconst_null    
                //    12: astore_2       
                //    13: aconst_null    
                //    14: astore_3       
                //    15: aload_0        
                //    16: getfield        com/facebook/places/internal/LocationPackageManager$1.val$requestParams:Lcom/facebook/places/internal/LocationPackageRequestParams;
                //    19: invokevirtual   com/facebook/places/internal/LocationPackageRequestParams.isLocationScanEnabled:()Z
                //    22: ifeq            60
                //    25: invokestatic    com/facebook/FacebookSdk.getApplicationContext:()Landroid/content/Context;
                //    28: aload_0        
                //    29: getfield        com/facebook/places/internal/LocationPackageManager$1.val$requestParams:Lcom/facebook/places/internal/LocationPackageRequestParams;
                //    32: invokestatic    com/facebook/places/internal/ScannerFactory.newLocationScanner:(Landroid/content/Context;Lcom/facebook/places/internal/LocationPackageRequestParams;)Lcom/facebook/places/internal/LocationScanner;
                //    35: astore_1       
                //    36: aload_1        
                //    37: invokeinterface com/facebook/places/internal/LocationScanner.initAndCheckEligibility:()V
                //    42: aload_1        
                //    43: aload_0        
                //    44: getfield        com/facebook/places/internal/LocationPackageManager$1.val$requestParams:Lcom/facebook/places/internal/LocationPackageRequestParams;
                //    47: invokestatic    com/facebook/places/internal/LocationPackageManager.access$000:(Lcom/facebook/places/internal/LocationScanner;Lcom/facebook/places/internal/LocationPackageRequestParams;)Ljava/util/concurrent/FutureTask;
                //    50: astore_1       
                //    51: invokestatic    com/facebook/FacebookSdk.getExecutor:()Ljava/util/concurrent/Executor;
                //    54: aload_1        
                //    55: invokeinterface java/util/concurrent/Executor.execute:(Ljava/lang/Runnable;)V
                //    60: aload_0        
                //    61: getfield        com/facebook/places/internal/LocationPackageManager$1.val$requestParams:Lcom/facebook/places/internal/LocationPackageRequestParams;
                //    64: invokevirtual   com/facebook/places/internal/LocationPackageRequestParams.isWifiScanEnabled:()Z
                //    67: ifeq            87
                //    70: aload_0        
                //    71: getfield        com/facebook/places/internal/LocationPackageManager$1.val$requestParams:Lcom/facebook/places/internal/LocationPackageRequestParams;
                //    74: invokestatic    com/facebook/places/internal/LocationPackageManager.access$100:(Lcom/facebook/places/internal/LocationPackageRequestParams;)Ljava/util/concurrent/FutureTask;
                //    77: astore_2       
                //    78: invokestatic    com/facebook/FacebookSdk.getExecutor:()Ljava/util/concurrent/Executor;
                //    81: aload_2        
                //    82: invokeinterface java/util/concurrent/Executor.execute:(Ljava/lang/Runnable;)V
                //    87: aload_0        
                //    88: getfield        com/facebook/places/internal/LocationPackageManager$1.val$requestParams:Lcom/facebook/places/internal/LocationPackageRequestParams;
                //    91: invokevirtual   com/facebook/places/internal/LocationPackageRequestParams.isBluetoothScanEnabled:()Z
                //    94: ifeq            114
                //    97: aload_0        
                //    98: getfield        com/facebook/places/internal/LocationPackageManager$1.val$requestParams:Lcom/facebook/places/internal/LocationPackageRequestParams;
                //   101: invokestatic    com/facebook/places/internal/LocationPackageManager.access$200:(Lcom/facebook/places/internal/LocationPackageRequestParams;)Ljava/util/concurrent/FutureTask;
                //   104: astore_3       
                //   105: invokestatic    com/facebook/FacebookSdk.getExecutor:()Ljava/util/concurrent/Executor;
                //   108: aload_3        
                //   109: invokeinterface java/util/concurrent/Executor.execute:(Ljava/lang/Runnable;)V
                //   114: aload_3        
                //   115: ifnull          144
                //   118: aload_3        
                //   119: invokevirtual   java/util/concurrent/FutureTask.get:()Ljava/lang/Object;
                //   122: checkcast       Lcom/facebook/places/internal/LocationPackage;
                //   125: astore_3       
                //   126: aload           4
                //   128: aload_3        
                //   129: getfield        com/facebook/places/internal/LocationPackage.ambientBluetoothLe:Ljava/util/List;
                //   132: putfield        com/facebook/places/internal/LocationPackage.ambientBluetoothLe:Ljava/util/List;
                //   135: aload           4
                //   137: aload_3        
                //   138: getfield        com/facebook/places/internal/LocationPackage.isBluetoothScanningEnabled:Z
                //   141: putfield        com/facebook/places/internal/LocationPackage.isBluetoothScanningEnabled:Z
                //   144: aload_2        
                //   145: ifnull          183
                //   148: aload_2        
                //   149: invokevirtual   java/util/concurrent/FutureTask.get:()Ljava/lang/Object;
                //   152: checkcast       Lcom/facebook/places/internal/LocationPackage;
                //   155: astore_2       
                //   156: aload           4
                //   158: aload_2        
                //   159: getfield        com/facebook/places/internal/LocationPackage.isWifiScanningEnabled:Z
                //   162: putfield        com/facebook/places/internal/LocationPackage.isWifiScanningEnabled:Z
                //   165: aload           4
                //   167: aload_2        
                //   168: getfield        com/facebook/places/internal/LocationPackage.connectedWifi:Lcom/facebook/places/internal/WifiScanResult;
                //   171: putfield        com/facebook/places/internal/LocationPackage.connectedWifi:Lcom/facebook/places/internal/WifiScanResult;
                //   174: aload           4
                //   176: aload_2        
                //   177: getfield        com/facebook/places/internal/LocationPackage.ambientWifi:Ljava/util/List;
                //   180: putfield        com/facebook/places/internal/LocationPackage.ambientWifi:Ljava/util/List;
                //   183: aload_1        
                //   184: ifnull          213
                //   187: aload_1        
                //   188: invokevirtual   java/util/concurrent/FutureTask.get:()Ljava/lang/Object;
                //   191: checkcast       Lcom/facebook/places/internal/LocationPackage;
                //   194: astore_1       
                //   195: aload           4
                //   197: aload_1        
                //   198: getfield        com/facebook/places/internal/LocationPackage.locationError:Lcom/facebook/places/internal/ScannerException$Type;
                //   201: putfield        com/facebook/places/internal/LocationPackage.locationError:Lcom/facebook/places/internal/ScannerException$Type;
                //   204: aload           4
                //   206: aload_1        
                //   207: getfield        com/facebook/places/internal/LocationPackage.location:Landroid/location/Location;
                //   210: putfield        com/facebook/places/internal/LocationPackage.location:Landroid/location/Location;
                //   213: aload_0        
                //   214: getfield        com/facebook/places/internal/LocationPackageManager$1.val$listener:Lcom/facebook/places/internal/LocationPackageManager$Listener;
                //   217: aload           4
                //   219: invokeinterface com/facebook/places/internal/LocationPackageManager$Listener.onLocationPackage:(Lcom/facebook/places/internal/LocationPackage;)V
                //   224: return         
                //   225: astore_3       
                //   226: ldc             "Exception scanning for bluetooth beacons"
                //   228: aload_3        
                //   229: invokestatic    com/facebook/places/internal/LocationPackageManager.access$300:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   232: goto            144
                //   235: astore_1       
                //   236: ldc             "Exception scanning for locations"
                //   238: aload_1        
                //   239: invokestatic    com/facebook/places/internal/LocationPackageManager.access$300:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   242: aload           4
                //   244: aload_1        
                //   245: getfield        com/facebook/places/internal/ScannerException.type:Lcom/facebook/places/internal/ScannerException$Type;
                //   248: putfield        com/facebook/places/internal/LocationPackage.locationError:Lcom/facebook/places/internal/ScannerException$Type;
                //   251: goto            213
                //   254: astore_2       
                //   255: ldc             "Exception scanning for wifi access points"
                //   257: aload_2        
                //   258: invokestatic    com/facebook/places/internal/LocationPackageManager.access$300:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   261: goto            183
                //   264: astore_1       
                //   265: ldc             "Exception requesting a location package"
                //   267: aload_1        
                //   268: invokestatic    com/facebook/places/internal/LocationPackageManager.access$300:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   271: goto            213
                //   274: astore_1       
                //   275: ldc             "Exception getting location"
                //   277: aload_1        
                //   278: invokestatic    com/facebook/places/internal/LocationPackageManager.access$300:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //   281: goto            213
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                           
                //  -----  -----  -----  -----  -----------------------------------------------
                //  15     60     235    254    Lcom/facebook/places/internal/ScannerException;
                //  15     60     264    274    Ljava/lang/Exception;
                //  60     87     235    254    Lcom/facebook/places/internal/ScannerException;
                //  60     87     264    274    Ljava/lang/Exception;
                //  87     114    235    254    Lcom/facebook/places/internal/ScannerException;
                //  87     114    264    274    Ljava/lang/Exception;
                //  118    144    225    235    Ljava/lang/Exception;
                //  118    144    235    254    Lcom/facebook/places/internal/ScannerException;
                //  148    183    254    264    Ljava/lang/Exception;
                //  148    183    235    254    Lcom/facebook/places/internal/ScannerException;
                //  187    213    274    284    Ljava/lang/Exception;
                //  187    213    235    254    Lcom/facebook/places/internal/ScannerException;
                //  226    232    235    254    Lcom/facebook/places/internal/ScannerException;
                //  226    232    264    274    Ljava/lang/Exception;
                //  255    261    235    254    Lcom/facebook/places/internal/ScannerException;
                //  255    261    264    274    Ljava/lang/Exception;
                //  275    281    235    254    Lcom/facebook/places/internal/ScannerException;
                //  275    281    264    274    Ljava/lang/Exception;
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 131, Size: 131
                //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
                //     at java.util.ArrayList.get(ArrayList.java:429)
                //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
                //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public interface Listener
    {
        void onLocationPackage(final LocationPackage p0);
    }
}
