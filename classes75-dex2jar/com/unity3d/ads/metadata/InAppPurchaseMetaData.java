// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.ads.metadata;

import android.content.Context;

public class InAppPurchaseMetaData extends MetaData
{
    public static final String IAP_KEY = "iap";
    public static final String KEY_CURRENCY = "currency";
    public static final String KEY_PRICE = "price";
    public static final String KEY_PRODUCT_ID = "productId";
    public static final String KEY_RECEIPT_PURCHASE_DATA = "receiptPurchaseData";
    public static final String KEY_SIGNATURE = "signature";
    
    public InAppPurchaseMetaData(final Context context) {
        super(context);
    }
    
    @Override
    public void commit() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/unity3d/ads/metadata/InAppPurchaseMetaData._context:Landroid/content/Context;
        //     4: invokestatic    com/unity3d/services/core/device/StorageManager.init:(Landroid/content/Context;)Z
        //     7: ifeq            131
        //    10: getstatic       com/unity3d/services/core/device/StorageManager$StorageType.PUBLIC:Lcom/unity3d/services/core/device/StorageManager$StorageType;
        //    13: invokestatic    com/unity3d/services/core/device/StorageManager.getStorage:(Lcom/unity3d/services/core/device/StorageManager$StorageType;)Lcom/unity3d/services/core/device/Storage;
        //    16: astore_3       
        //    17: aload_0        
        //    18: invokevirtual   com/unity3d/ads/metadata/InAppPurchaseMetaData.getData:()Lorg/json/JSONObject;
        //    21: ifnull          112
        //    24: aload_3        
        //    25: ifnull          112
        //    28: aload_3        
        //    29: ldc             "iap.purchases"
        //    31: invokevirtual   com/unity3d/services/core/device/Storage.get:(Ljava/lang/String;)Ljava/lang/Object;
        //    34: astore          4
        //    36: aconst_null    
        //    37: astore_2       
        //    38: aload_2        
        //    39: astore_1       
        //    40: aload           4
        //    42: ifnull          51
        //    45: aload           4
        //    47: checkcast       Lorg/json/JSONArray;
        //    50: astore_1       
        //    51: aload_1        
        //    52: astore_2       
        //    53: aload_1        
        //    54: ifnonnull       65
        //    57: new             Lorg/json/JSONArray;
        //    60: dup            
        //    61: invokespecial   org/json/JSONArray.<init>:()V
        //    64: astore_2       
        //    65: aload_0        
        //    66: invokevirtual   com/unity3d/ads/metadata/InAppPurchaseMetaData.getData:()Lorg/json/JSONObject;
        //    69: astore_1       
        //    70: aload_1        
        //    71: ldc             "ts"
        //    73: invokestatic    java/lang/System.currentTimeMillis:()J
        //    76: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //    79: pop            
        //    80: aload_2        
        //    81: aload_1        
        //    82: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    85: pop            
        //    86: aload_3        
        //    87: ldc             "iap.purchases"
        //    89: aload_2        
        //    90: invokevirtual   com/unity3d/services/core/device/Storage.set:(Ljava/lang/String;Ljava/lang/Object;)Z
        //    93: pop            
        //    94: aload_3        
        //    95: invokevirtual   com/unity3d/services/core/device/Storage.writeStorage:()Z
        //    98: pop            
        //    99: aload_3        
        //   100: getstatic       com/unity3d/services/core/device/StorageEvent.SET:Lcom/unity3d/services/core/device/StorageEvent;
        //   103: aload_3        
        //   104: ldc             "iap.purchases"
        //   106: invokevirtual   com/unity3d/services/core/device/Storage.get:(Ljava/lang/String;)Ljava/lang/Object;
        //   109: invokevirtual   com/unity3d/services/core/device/Storage.sendEvent:(Lcom/unity3d/services/core/device/StorageEvent;Ljava/lang/Object;)V
        //   112: return         
        //   113: astore_1       
        //   114: ldc             "Invalid object type for purchases"
        //   116: invokestatic    com/unity3d/services/core/log/DeviceLog.error:(Ljava/lang/String;)V
        //   119: aload_2        
        //   120: astore_1       
        //   121: goto            51
        //   124: astore_1       
        //   125: ldc             "Error constructing purchase object"
        //   127: invokestatic    com/unity3d/services/core/log/DeviceLog.error:(Ljava/lang/String;)V
        //   130: return         
        //   131: ldc             "Unity Ads could not commit metadata due to storage error or the data is null"
        //   133: invokestatic    com/unity3d/services/core/log/DeviceLog.error:(Ljava/lang/String;)V
        //   136: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  45     51     113    124    Ljava/lang/Exception;
        //  70     80     124    131    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
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
    
    @Override
    public boolean set(final String s, final Object o) {
        synchronized (this) {
            return this.setRaw(s, o);
        }
    }
    
    public void setCurrency(final String s) {
        this.set("currency", s);
    }
    
    public void setPrice(final Double n) {
        this.set("price", n);
    }
    
    public void setProductId(final String s) {
        this.set("productId", s);
    }
    
    public void setReceiptPurchaseData(final String s) {
        this.set("receiptPurchaseData", s);
    }
    
    public void setSignature(final String s) {
        this.set("signature", s);
    }
}
