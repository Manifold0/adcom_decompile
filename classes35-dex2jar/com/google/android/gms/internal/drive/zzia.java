// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import java.util.Arrays;
import com.google.android.gms.drive.metadata.internal.zzg;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.zzm;

public class zzia extends zzm<AppVisibleCustomProperties>
{
    public static final zzg zzkm;
    
    static {
        zzkm = new zzib();
    }
    
    public zzia(final int n) {
        super("customProperties", Arrays.asList("hasCustomProperties", "sqlId"), Arrays.asList("customPropertiesExtra", "customPropertiesExtraHolder"), 5000000);
    }
    
    private static void zzc(final DataHolder dataHolder) {
        final Bundle metadata = dataHolder.getMetadata();
        if (metadata == null) {
            return;
        }
        synchronized (dataHolder) {
            final DataHolder dataHolder2 = (DataHolder)metadata.getParcelable("customPropertiesExtraHolder");
            if (dataHolder2 != null) {
                dataHolder2.close();
                metadata.remove("customPropertiesExtraHolder");
            }
        }
    }
    
    private static AppVisibleCustomProperties zzf(final DataHolder p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //     4: astore          9
        //     6: aload           9
        //     8: ldc             "customPropertiesExtra"
        //    10: invokevirtual   android/os/Bundle.getSparseParcelableArray:(Ljava/lang/String;)Landroid/util/SparseArray;
        //    13: astore          7
        //    15: aload           7
        //    17: astore          8
        //    19: aload           7
        //    21: ifnonnull       385
        //    24: aload           9
        //    26: ldc             "customPropertiesExtraHolder"
        //    28: invokevirtual   android/os/Bundle.getParcelable:(Ljava/lang/String;)Landroid/os/Parcelable;
        //    31: ifnull          66
        //    34: aload_0        
        //    35: monitorenter   
        //    36: aload_0        
        //    37: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //    40: ldc             "customPropertiesExtraHolder"
        //    42: invokevirtual   android/os/Bundle.getParcelable:(Ljava/lang/String;)Landroid/os/Parcelable;
        //    45: checkcast       Lcom/google/android/gms/common/data/DataHolder;
        //    48: astore          10
        //    50: aload           10
        //    52: ifnonnull       79
        //    55: aload_0        
        //    56: monitorexit    
        //    57: aload           9
        //    59: ldc             "customPropertiesExtra"
        //    61: invokevirtual   android/os/Bundle.getSparseParcelableArray:(Ljava/lang/String;)Landroid/util/SparseArray;
        //    64: astore          7
        //    66: aload           7
        //    68: astore          8
        //    70: aload           7
        //    72: ifnonnull       385
        //    75: getstatic       com/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties.zzil:Lcom/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties;
        //    78: areturn        
        //    79: aload           10
        //    81: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //    84: astore          7
        //    86: aload           7
        //    88: ldc             "entryIdColumn"
        //    90: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    93: astore          12
        //    95: aload           7
        //    97: ldc             "keyColumn"
        //    99: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   102: astore          13
        //   104: aload           7
        //   106: ldc             "visibilityColumn"
        //   108: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   111: astore          14
        //   113: aload           7
        //   115: ldc             "valueColumn"
        //   117: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   120: astore          15
        //   122: new             Landroid/support/v4/util/LongSparseArray;
        //   125: dup            
        //   126: invokespecial   android/support/v4/util/LongSparseArray.<init>:()V
        //   129: astore          11
        //   131: iconst_0       
        //   132: istore_2       
        //   133: iload_2        
        //   134: aload           10
        //   136: invokevirtual   com/google/android/gms/common/data/DataHolder.getCount:()I
        //   139: if_icmpge       269
        //   142: aload           10
        //   144: iload_2        
        //   145: invokevirtual   com/google/android/gms/common/data/DataHolder.getWindowIndex:(I)I
        //   148: istore_3       
        //   149: aload           10
        //   151: aload           12
        //   153: iload_2        
        //   154: iload_3        
        //   155: invokevirtual   com/google/android/gms/common/data/DataHolder.getLong:(Ljava/lang/String;II)J
        //   158: lstore          5
        //   160: aload           10
        //   162: aload           13
        //   164: iload_2        
        //   165: iload_3        
        //   166: invokevirtual   com/google/android/gms/common/data/DataHolder.getString:(Ljava/lang/String;II)Ljava/lang/String;
        //   169: astore          7
        //   171: aload           10
        //   173: aload           14
        //   175: iload_2        
        //   176: iload_3        
        //   177: invokevirtual   com/google/android/gms/common/data/DataHolder.getInteger:(Ljava/lang/String;II)I
        //   180: istore          4
        //   182: aload           10
        //   184: aload           15
        //   186: iload_2        
        //   187: iload_3        
        //   188: invokevirtual   com/google/android/gms/common/data/DataHolder.getString:(Ljava/lang/String;II)Ljava/lang/String;
        //   191: astore          8
        //   193: new             Lcom/google/android/gms/drive/metadata/internal/zzc;
        //   196: dup            
        //   197: new             Lcom/google/android/gms/drive/metadata/CustomPropertyKey;
        //   200: dup            
        //   201: aload           7
        //   203: iload           4
        //   205: invokespecial   com/google/android/gms/drive/metadata/CustomPropertyKey.<init>:(Ljava/lang/String;I)V
        //   208: aload           8
        //   210: invokespecial   com/google/android/gms/drive/metadata/internal/zzc.<init>:(Lcom/google/android/gms/drive/metadata/CustomPropertyKey;Ljava/lang/String;)V
        //   213: astore          16
        //   215: aload           11
        //   217: lload           5
        //   219: invokevirtual   android/support/v4/util/LongSparseArray.get:(J)Ljava/lang/Object;
        //   222: checkcast       Lcom/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties$zza;
        //   225: astore          8
        //   227: aload           8
        //   229: astore          7
        //   231: aload           8
        //   233: ifnonnull       254
        //   236: new             Lcom/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties$zza;
        //   239: dup            
        //   240: invokespecial   com/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties$zza.<init>:()V
        //   243: astore          7
        //   245: aload           11
        //   247: lload           5
        //   249: aload           7
        //   251: invokevirtual   android/support/v4/util/LongSparseArray.put:(JLjava/lang/Object;)V
        //   254: aload           7
        //   256: aload           16
        //   258: invokevirtual   com/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties$zza.zza:(Lcom/google/android/gms/drive/metadata/internal/zzc;)Lcom/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties$zza;
        //   261: pop            
        //   262: iload_2        
        //   263: iconst_1       
        //   264: iadd           
        //   265: istore_2       
        //   266: goto            133
        //   269: new             Landroid/util/SparseArray;
        //   272: dup            
        //   273: invokespecial   android/util/SparseArray.<init>:()V
        //   276: astore          7
        //   278: iconst_0       
        //   279: istore_2       
        //   280: iload_2        
        //   281: aload_0        
        //   282: invokevirtual   com/google/android/gms/common/data/DataHolder.getCount:()I
        //   285: if_icmpge       329
        //   288: aload           11
        //   290: aload_0        
        //   291: ldc             "sqlId"
        //   293: iload_2        
        //   294: aload_0        
        //   295: iload_2        
        //   296: invokevirtual   com/google/android/gms/common/data/DataHolder.getWindowIndex:(I)I
        //   299: invokevirtual   com/google/android/gms/common/data/DataHolder.getLong:(Ljava/lang/String;II)J
        //   302: invokevirtual   android/support/v4/util/LongSparseArray.get:(J)Ljava/lang/Object;
        //   305: checkcast       Lcom/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties$zza;
        //   308: astore          8
        //   310: aload           8
        //   312: ifnull          398
        //   315: aload           7
        //   317: iload_2        
        //   318: aload           8
        //   320: invokevirtual   com/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties$zza.zzat:()Lcom/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties;
        //   323: invokevirtual   android/util/SparseArray.append:(ILjava/lang/Object;)V
        //   326: goto            398
        //   329: aload_0        
        //   330: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //   333: ldc             "customPropertiesExtra"
        //   335: aload           7
        //   337: invokevirtual   android/os/Bundle.putSparseParcelableArray:(Ljava/lang/String;Landroid/util/SparseArray;)V
        //   340: aload           10
        //   342: invokevirtual   com/google/android/gms/common/data/DataHolder.close:()V
        //   345: aload_0        
        //   346: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //   349: ldc             "customPropertiesExtraHolder"
        //   351: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //   354: aload_0        
        //   355: monitorexit    
        //   356: goto            57
        //   359: astore          7
        //   361: aload_0        
        //   362: monitorexit    
        //   363: aload           7
        //   365: athrow         
        //   366: astore          7
        //   368: aload           10
        //   370: invokevirtual   com/google/android/gms/common/data/DataHolder.close:()V
        //   373: aload_0        
        //   374: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //   377: ldc             "customPropertiesExtraHolder"
        //   379: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //   382: aload           7
        //   384: athrow         
        //   385: aload           8
        //   387: iload_1        
        //   388: getstatic       com/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties.zzil:Lcom/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties;
        //   391: invokevirtual   android/util/SparseArray.get:(ILjava/lang/Object;)Ljava/lang/Object;
        //   394: checkcast       Lcom/google/android/gms/drive/metadata/internal/AppVisibleCustomProperties;
        //   397: areturn        
        //   398: iload_2        
        //   399: iconst_1       
        //   400: iadd           
        //   401: istore_2       
        //   402: goto            280
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  36     50     359    366    Any
        //  55     57     359    366    Any
        //  79     131    366    385    Any
        //  133    227    366    385    Any
        //  236    254    366    385    Any
        //  254    262    366    385    Any
        //  269    278    366    385    Any
        //  280    310    366    385    Any
        //  315    326    366    385    Any
        //  329    340    366    385    Any
        //  340    356    359    366    Any
        //  361    363    359    366    Any
        //  368    385    359    366    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0079:
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
