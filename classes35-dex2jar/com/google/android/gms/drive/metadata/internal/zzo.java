// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.HashSet;
import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import java.util.Arrays;
import java.util.Collections;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.DriveId;

public final class zzo extends zzl<DriveId> implements SearchableCollectionMetadataField<DriveId>
{
    public static final zzg zziu;
    
    static {
        zziu = new zzp();
    }
    
    public zzo(final int n) {
        super("parents", Collections.emptySet(), Arrays.asList("parentsExtra", "dbInstanceId", "parentsExtraHolder"), 4100000);
    }
    
    private static void zzc(final DataHolder dataHolder) {
        final Bundle metadata = dataHolder.getMetadata();
        if (metadata == null) {
            return;
        }
        synchronized (dataHolder) {
            final DataHolder dataHolder2 = (DataHolder)metadata.getParcelable("parentsExtraHolder");
            if (dataHolder2 != null) {
                dataHolder2.close();
                metadata.remove("parentsExtraHolder");
            }
        }
    }
    
    @Override
    protected final Collection<DriveId> zzc(final Bundle bundle) {
        final Collection<? extends DriveId> zzc = super.zzc(bundle);
        if (zzc == null) {
            return null;
        }
        return new HashSet<DriveId>(zzc);
    }
    
    @Override
    protected final Collection<DriveId> zzd(final DataHolder p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //     4: astore          10
        //     6: aload           10
        //     8: ldc             "parentsExtra"
        //    10: invokevirtual   android/os/Bundle.getParcelableArrayList:(Ljava/lang/String;)Ljava/util/ArrayList;
        //    13: astore          8
        //    15: aload           8
        //    17: astore          9
        //    19: aload           8
        //    21: ifnonnull       354
        //    24: aload           10
        //    26: ldc             "parentsExtraHolder"
        //    28: invokevirtual   android/os/Bundle.getParcelable:(Ljava/lang/String;)Landroid/os/Parcelable;
        //    31: ifnull          66
        //    34: aload_1        
        //    35: monitorenter   
        //    36: aload_1        
        //    37: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //    40: ldc             "parentsExtraHolder"
        //    42: invokevirtual   android/os/Bundle.getParcelable:(Ljava/lang/String;)Landroid/os/Parcelable;
        //    45: checkcast       Lcom/google/android/gms/common/data/DataHolder;
        //    48: astore          8
        //    50: aload           8
        //    52: ifnonnull       77
        //    55: aload_1        
        //    56: monitorexit    
        //    57: aload           10
        //    59: ldc             "parentsExtra"
        //    61: invokevirtual   android/os/Bundle.getParcelableArrayList:(Ljava/lang/String;)Ljava/util/ArrayList;
        //    64: astore          8
        //    66: aload           8
        //    68: astore          9
        //    70: aload           8
        //    72: ifnonnull       354
        //    75: aconst_null    
        //    76: areturn        
        //    77: aload_1        
        //    78: invokevirtual   com/google/android/gms/common/data/DataHolder.getCount:()I
        //    81: istore          4
        //    83: new             Ljava/util/ArrayList;
        //    86: dup            
        //    87: iload           4
        //    89: invokespecial   java/util/ArrayList.<init>:(I)V
        //    92: astore          9
        //    94: new             Ljava/util/HashMap;
        //    97: dup            
        //    98: iload           4
        //   100: invokespecial   java/util/HashMap.<init>:(I)V
        //   103: astore          11
        //   105: iconst_0       
        //   106: istore_3       
        //   107: iload_3        
        //   108: iload           4
        //   110: if_icmpge       166
        //   113: aload_1        
        //   114: iload_3        
        //   115: invokevirtual   com/google/android/gms/common/data/DataHolder.getWindowIndex:(I)I
        //   118: istore          5
        //   120: new             Lcom/google/android/gms/drive/metadata/internal/ParentDriveIdSet;
        //   123: dup            
        //   124: invokespecial   com/google/android/gms/drive/metadata/internal/ParentDriveIdSet.<init>:()V
        //   127: astore          12
        //   129: aload           9
        //   131: aload           12
        //   133: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   136: pop            
        //   137: aload           11
        //   139: aload_1        
        //   140: ldc             "sqlId"
        //   142: iload_3        
        //   143: iload           5
        //   145: invokevirtual   com/google/android/gms/common/data/DataHolder.getLong:(Ljava/lang/String;II)J
        //   148: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   151: aload           12
        //   153: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   158: pop            
        //   159: iload_3        
        //   160: iconst_1       
        //   161: iadd           
        //   162: istore_3       
        //   163: goto            107
        //   166: aload           8
        //   168: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //   171: astore          14
        //   173: aload           14
        //   175: ldc             "childSqlIdColumn"
        //   177: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   180: astore          12
        //   182: aload           14
        //   184: ldc             "parentSqlIdColumn"
        //   186: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   189: astore          13
        //   191: aload           14
        //   193: ldc             "parentResIdColumn"
        //   195: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   198: astore          14
        //   200: aload           8
        //   202: invokevirtual   com/google/android/gms/common/data/DataHolder.getCount:()I
        //   205: istore          4
        //   207: iconst_0       
        //   208: istore_3       
        //   209: iload_3        
        //   210: iload           4
        //   212: if_icmpge       298
        //   215: aload           8
        //   217: iload_3        
        //   218: invokevirtual   com/google/android/gms/common/data/DataHolder.getWindowIndex:(I)I
        //   221: istore          5
        //   223: aload           11
        //   225: aload           8
        //   227: aload           12
        //   229: iload_3        
        //   230: iload           5
        //   232: invokevirtual   com/google/android/gms/common/data/DataHolder.getLong:(Ljava/lang/String;II)J
        //   235: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   238: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   243: checkcast       Lcom/google/android/gms/drive/metadata/internal/ParentDriveIdSet;
        //   246: astore          15
        //   248: new             Lcom/google/android/gms/drive/metadata/internal/zzq;
        //   251: dup            
        //   252: aload           8
        //   254: aload           14
        //   256: iload_3        
        //   257: iload           5
        //   259: invokevirtual   com/google/android/gms/common/data/DataHolder.getString:(Ljava/lang/String;II)Ljava/lang/String;
        //   262: aload           8
        //   264: aload           13
        //   266: iload_3        
        //   267: iload           5
        //   269: invokevirtual   com/google/android/gms/common/data/DataHolder.getLong:(Ljava/lang/String;II)J
        //   272: iconst_1       
        //   273: invokespecial   com/google/android/gms/drive/metadata/internal/zzq.<init>:(Ljava/lang/String;JI)V
        //   276: astore          16
        //   278: aload           15
        //   280: getfield        com/google/android/gms/drive/metadata/internal/ParentDriveIdSet.zzit:Ljava/util/List;
        //   283: aload           16
        //   285: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   290: pop            
        //   291: iload_3        
        //   292: iconst_1       
        //   293: iadd           
        //   294: istore_3       
        //   295: goto            209
        //   298: aload_1        
        //   299: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //   302: ldc             "parentsExtra"
        //   304: aload           9
        //   306: invokevirtual   android/os/Bundle.putParcelableArrayList:(Ljava/lang/String;Ljava/util/ArrayList;)V
        //   309: aload           8
        //   311: invokevirtual   com/google/android/gms/common/data/DataHolder.close:()V
        //   314: aload_1        
        //   315: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //   318: ldc             "parentsExtraHolder"
        //   320: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //   323: aload_1        
        //   324: monitorexit    
        //   325: goto            57
        //   328: astore          8
        //   330: aload_1        
        //   331: monitorexit    
        //   332: aload           8
        //   334: athrow         
        //   335: astore          9
        //   337: aload           8
        //   339: invokevirtual   com/google/android/gms/common/data/DataHolder.close:()V
        //   342: aload_1        
        //   343: invokevirtual   com/google/android/gms/common/data/DataHolder.getMetadata:()Landroid/os/Bundle;
        //   346: ldc             "parentsExtraHolder"
        //   348: invokevirtual   android/os/Bundle.remove:(Ljava/lang/String;)V
        //   351: aload           9
        //   353: athrow         
        //   354: aload           10
        //   356: ldc             "dbInstanceId"
        //   358: invokevirtual   android/os/Bundle.getLong:(Ljava/lang/String;)J
        //   361: lstore          6
        //   363: aload           9
        //   365: iload_2        
        //   366: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   371: checkcast       Lcom/google/android/gms/drive/metadata/internal/ParentDriveIdSet;
        //   374: astore          8
        //   376: new             Ljava/util/HashSet;
        //   379: dup            
        //   380: invokespecial   java/util/HashSet.<init>:()V
        //   383: astore_1       
        //   384: aload           8
        //   386: getfield        com/google/android/gms/drive/metadata/internal/ParentDriveIdSet.zzit:Ljava/util/List;
        //   389: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   394: astore          8
        //   396: aload           8
        //   398: invokeinterface java/util/Iterator.hasNext:()Z
        //   403: ifeq            452
        //   406: aload           8
        //   408: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   413: checkcast       Lcom/google/android/gms/drive/metadata/internal/zzq;
        //   416: astore          9
        //   418: aload_1        
        //   419: new             Lcom/google/android/gms/drive/DriveId;
        //   422: dup            
        //   423: aload           9
        //   425: getfield        com/google/android/gms/drive/metadata/internal/zzq.zzab:Ljava/lang/String;
        //   428: aload           9
        //   430: getfield        com/google/android/gms/drive/metadata/internal/zzq.zzac:J
        //   433: lload           6
        //   435: aload           9
        //   437: getfield        com/google/android/gms/drive/metadata/internal/zzq.zzad:I
        //   440: invokespecial   com/google/android/gms/drive/DriveId.<init>:(Ljava/lang/String;JJI)V
        //   443: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   448: pop            
        //   449: goto            396
        //   452: aload_1        
        //   453: areturn        
        //    Signature:
        //  (Lcom/google/android/gms/common/data/DataHolder;II)Ljava/util/Collection<Lcom/google/android/gms/drive/DriveId;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  36     50     328    335    Any
        //  55     57     328    335    Any
        //  77     105    335    354    Any
        //  113    159    335    354    Any
        //  166    207    335    354    Any
        //  215    291    335    354    Any
        //  298    309    335    354    Any
        //  309    325    328    335    Any
        //  330    332    328    335    Any
        //  337    354    328    335    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
