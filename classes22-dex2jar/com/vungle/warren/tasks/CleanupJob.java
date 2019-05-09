// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.vungle.warren.Storage;
import com.vungle.warren.persistence.Designer;

public class CleanupJob implements Job
{
    static final String TAG;
    private Designer designer;
    private Storage storage;
    
    static {
        TAG = CleanupJob.class.getCanonicalName();
    }
    
    CleanupJob(@NonNull final Designer designer, @NonNull final Storage storage) {
        this.designer = designer;
        this.storage = storage;
    }
    
    public static JobInfo makeJobInfo() {
        return new JobInfo(CleanupJob.TAG).setPriority(0).setUpdateCurrent(true);
    }
    
    @Override
    public int onRunJob(final Bundle p0, final JobRunner p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/vungle/warren/tasks/CleanupJob.designer:Lcom/vungle/warren/persistence/Designer;
        //     4: ifnull          14
        //     7: aload_0        
        //     8: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //    11: ifnonnull       16
        //    14: iconst_1       
        //    15: ireturn        
        //    16: getstatic       com/vungle/warren/tasks/CleanupJob.TAG:Ljava/lang/String;
        //    19: ldc             "CleanupJob: Current directory snapshot"
        //    21: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    24: pop            
        //    25: aload_0        
        //    26: getfield        com/vungle/warren/tasks/CleanupJob.designer:Lcom/vungle/warren/persistence/Designer;
        //    29: invokeinterface com/vungle/warren/persistence/Designer.getCacheDirectory:()Ljava/io/File;
        //    34: invokestatic    com/vungle/warren/utility/FileUtility.printDirectoryTree:(Ljava/io/File;)V
        //    37: aload_0        
        //    38: getfield        com/vungle/warren/tasks/CleanupJob.designer:Lcom/vungle/warren/persistence/Designer;
        //    41: invokeinterface com/vungle/warren/persistence/Designer.getCacheDirectory:()Ljava/io/File;
        //    46: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    49: astore_1       
        //    50: aload_0        
        //    51: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //    54: ldc             Lcom/vungle/warren/model/Advertisement;.class
        //    56: invokevirtual   com/vungle/warren/Storage.loadAll:(Ljava/lang/Class;)Ljava/util/List;
        //    59: astore          6
        //    61: aload_0        
        //    62: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //    65: ldc             Lcom/vungle/warren/model/Placement;.class
        //    67: invokevirtual   com/vungle/warren/Storage.loadAll:(Ljava/lang/Class;)Ljava/util/List;
        //    70: astore          8
        //    72: aload           8
        //    74: invokeinterface java/util/List.size:()I
        //    79: ifne            84
        //    82: iconst_0       
        //    83: ireturn        
        //    84: aload_0        
        //    85: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //    88: invokevirtual   com/vungle/warren/Storage.loadValidPlacements:()Ljava/util/Collection;
        //    91: astore          7
        //    93: new             Ljava/util/HashSet;
        //    96: dup            
        //    97: invokespecial   java/util/HashSet.<init>:()V
        //   100: astore          5
        //   102: aload           8
        //   104: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   109: astore          8
        //   111: aload           8
        //   113: invokeinterface java/util/Iterator.hasNext:()Z
        //   118: ifeq            469
        //   121: aload           8
        //   123: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   128: checkcast       Lcom/vungle/warren/model/Placement;
        //   131: astore          9
        //   133: aload           7
        //   135: invokeinterface java/util/Collection.isEmpty:()Z
        //   140: ifne            194
        //   143: aload           7
        //   145: aload           9
        //   147: invokeinterface java/util/Collection.contains:(Ljava/lang/Object;)Z
        //   152: ifne            194
        //   155: getstatic       com/vungle/warren/tasks/CleanupJob.TAG:Ljava/lang/String;
        //   158: getstatic       java/util/Locale.ENGLISH:Ljava/util/Locale;
        //   161: ldc             "Placement %s is no longer valid, deleting it and its advertisement"
        //   163: iconst_1       
        //   164: anewarray       Ljava/lang/Object;
        //   167: dup            
        //   168: iconst_0       
        //   169: aload           9
        //   171: invokevirtual   com/vungle/warren/model/Placement.getId:()Ljava/lang/String;
        //   174: aastore        
        //   175: invokestatic    java/lang/String.format:(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   178: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   181: pop            
        //   182: aload_0        
        //   183: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //   186: aload           9
        //   188: invokevirtual   com/vungle/warren/Storage.delete:(Lcom/vungle/warren/persistence/Memorable;)V
        //   191: goto            111
        //   194: aload           9
        //   196: invokevirtual   com/vungle/warren/model/Placement.getAdvertisementIDs:()Ljava/util/List;
        //   199: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   204: astore          10
        //   206: aload           10
        //   208: invokeinterface java/util/Iterator.hasNext:()Z
        //   213: ifeq            111
        //   216: aload           10
        //   218: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   223: checkcast       Ljava/lang/String;
        //   226: astore          12
        //   228: aload_0        
        //   229: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //   232: aload           12
        //   234: ldc             Lcom/vungle/warren/model/Advertisement;.class
        //   236: invokevirtual   com/vungle/warren/Storage.load:(Ljava/lang/String;Ljava/lang/Class;)Lcom/vungle/warren/persistence/Memorable;
        //   239: checkcast       Lcom/vungle/warren/model/Advertisement;
        //   242: astore          11
        //   244: aload           11
        //   246: ifnull          412
        //   249: aload           11
        //   251: invokevirtual   com/vungle/warren/model/Advertisement.getExpireTime:()J
        //   254: invokestatic    java/lang/System.currentTimeMillis:()J
        //   257: lcmp           
        //   258: ifgt            356
        //   261: aload           11
        //   263: invokevirtual   com/vungle/warren/model/Advertisement.getState:()I
        //   266: iconst_2       
        //   267: if_icmpeq       356
        //   270: aload_0        
        //   271: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //   274: aload           9
        //   276: invokevirtual   com/vungle/warren/model/Placement.getId:()Ljava/lang/String;
        //   279: aload           12
        //   281: invokevirtual   com/vungle/warren/Storage.removeAdvertisementFromPlacement:(Ljava/lang/String;Ljava/lang/String;)V
        //   284: aload_0        
        //   285: getfield        com/vungle/warren/tasks/CleanupJob.designer:Lcom/vungle/warren/persistence/Designer;
        //   288: aload           11
        //   290: invokevirtual   com/vungle/warren/model/Advertisement.getId:()Ljava/lang/String;
        //   293: invokeinterface com/vungle/warren/persistence/Designer.deleteAssets:(Ljava/lang/String;)V
        //   298: aload_0        
        //   299: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //   302: aload           11
        //   304: invokevirtual   com/vungle/warren/Storage.delete:(Lcom/vungle/warren/persistence/Memorable;)V
        //   307: aload           9
        //   309: invokevirtual   com/vungle/warren/model/Placement.isAutoCached:()Z
        //   312: ifeq            206
        //   315: aload_2        
        //   316: aload           9
        //   318: invokevirtual   com/vungle/warren/model/Placement.getId:()Ljava/lang/String;
        //   321: iconst_1       
        //   322: invokestatic    com/vungle/warren/tasks/DownloadJob.makeJobInfo:(Ljava/lang/String;Z)Lcom/vungle/warren/tasks/JobInfo;
        //   325: ldc2_w          1000
        //   328: invokevirtual   com/vungle/warren/tasks/JobInfo.setDelay:(J)Lcom/vungle/warren/tasks/JobInfo;
        //   331: invokeinterface com/vungle/warren/tasks/JobRunner.execute:(Lcom/vungle/warren/tasks/JobInfo;)V
        //   336: goto            206
        //   339: astore          12
        //   341: getstatic       com/vungle/warren/tasks/CleanupJob.TAG:Ljava/lang/String;
        //   344: aload           12
        //   346: invokestatic    android/util/Log.getStackTraceString:(Ljava/lang/Throwable;)Ljava/lang/String;
        //   349: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   352: pop            
        //   353: goto            298
        //   356: aload           5
        //   358: aload           11
        //   360: invokevirtual   com/vungle/warren/model/Advertisement.getId:()Ljava/lang/String;
        //   363: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   368: pop            
        //   369: getstatic       com/vungle/warren/tasks/CleanupJob.TAG:Ljava/lang/String;
        //   372: new             Ljava/lang/StringBuilder;
        //   375: dup            
        //   376: invokespecial   java/lang/StringBuilder.<init>:()V
        //   379: ldc             "setting valid adv "
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: aload           12
        //   386: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   389: ldc             " for placement "
        //   391: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   394: aload           9
        //   396: invokevirtual   com/vungle/warren/model/Placement.getId:()Ljava/lang/String;
        //   399: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   402: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   405: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   408: pop            
        //   409: goto            206
        //   412: getstatic       com/vungle/warren/tasks/CleanupJob.TAG:Ljava/lang/String;
        //   415: new             Ljava/lang/StringBuilder;
        //   418: dup            
        //   419: invokespecial   java/lang/StringBuilder.<init>:()V
        //   422: ldc             "removing adv "
        //   424: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   427: aload           12
        //   429: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   432: ldc             " from placement "
        //   434: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   437: aload           9
        //   439: invokevirtual   com/vungle/warren/model/Placement.getId:()Ljava/lang/String;
        //   442: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   445: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   448: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   451: pop            
        //   452: aload_0        
        //   453: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //   456: aload           9
        //   458: invokevirtual   com/vungle/warren/model/Placement.getId:()Ljava/lang/String;
        //   461: aload           12
        //   463: invokevirtual   com/vungle/warren/Storage.removeAdvertisementFromPlacement:(Ljava/lang/String;Ljava/lang/String;)V
        //   466: goto            206
        //   469: aload           6
        //   471: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   476: astore_2       
        //   477: aload_2        
        //   478: invokeinterface java/util/Iterator.hasNext:()Z
        //   483: ifeq            609
        //   486: aload_2        
        //   487: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   492: checkcast       Lcom/vungle/warren/model/Advertisement;
        //   495: astore          6
        //   497: aload           6
        //   499: invokevirtual   com/vungle/warren/model/Advertisement.getState:()I
        //   502: iconst_2       
        //   503: if_icmpne       552
        //   506: aload           5
        //   508: aload           6
        //   510: invokevirtual   com/vungle/warren/model/Advertisement.getId:()Ljava/lang/String;
        //   513: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   518: pop            
        //   519: getstatic       com/vungle/warren/tasks/CleanupJob.TAG:Ljava/lang/String;
        //   522: new             Ljava/lang/StringBuilder;
        //   525: dup            
        //   526: invokespecial   java/lang/StringBuilder.<init>:()V
        //   529: ldc             "found adv in viewing state "
        //   531: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   534: aload           6
        //   536: invokevirtual   com/vungle/warren/model/Advertisement.getId:()Ljava/lang/String;
        //   539: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   542: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   545: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   548: pop            
        //   549: goto            477
        //   552: aload           5
        //   554: aload           6
        //   556: invokevirtual   com/vungle/warren/model/Advertisement.getId:()Ljava/lang/String;
        //   559: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   564: ifne            477
        //   567: getstatic       com/vungle/warren/tasks/CleanupJob.TAG:Ljava/lang/String;
        //   570: new             Ljava/lang/StringBuilder;
        //   573: dup            
        //   574: invokespecial   java/lang/StringBuilder.<init>:()V
        //   577: ldc             "delete ad "
        //   579: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   582: aload           6
        //   584: invokevirtual   com/vungle/warren/model/Advertisement.getId:()Ljava/lang/String;
        //   587: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   590: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   593: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   596: pop            
        //   597: aload_0        
        //   598: getfield        com/vungle/warren/tasks/CleanupJob.storage:Lcom/vungle/warren/Storage;
        //   601: aload           6
        //   603: invokevirtual   com/vungle/warren/Storage.delete:(Lcom/vungle/warren/persistence/Memorable;)V
        //   606: goto            477
        //   609: aload_1        
        //   610: arraylength    
        //   611: istore          4
        //   613: iconst_0       
        //   614: istore_3       
        //   615: iload_3        
        //   616: iload           4
        //   618: if_icmpge       690
        //   621: aload_1        
        //   622: iload_3        
        //   623: aaload         
        //   624: astore_2       
        //   625: aload           5
        //   627: aload_2        
        //   628: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   631: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   636: ifne            669
        //   639: getstatic       com/vungle/warren/tasks/CleanupJob.TAG:Ljava/lang/String;
        //   642: getstatic       java/util/Locale.ENGLISH:Ljava/util/Locale;
        //   645: ldc             "Deleting assets under directory %s"
        //   647: iconst_1       
        //   648: anewarray       Ljava/lang/Object;
        //   651: dup            
        //   652: iconst_0       
        //   653: aload_2        
        //   654: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   657: aastore        
        //   658: invokestatic    java/lang/String.format:(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   661: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   664: pop            
        //   665: aload_2        
        //   666: invokestatic    com/vungle/warren/utility/FileUtility.delete:(Ljava/io/File;)V
        //   669: iload_3        
        //   670: iconst_1       
        //   671: iadd           
        //   672: istore_3       
        //   673: goto            615
        //   676: astore_2       
        //   677: getstatic       com/vungle/warren/tasks/CleanupJob.TAG:Ljava/lang/String;
        //   680: ldc             "Failed to delete asset directory!"
        //   682: aload_2        
        //   683: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   686: pop            
        //   687: goto            669
        //   690: iconst_0       
        //   691: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  284    298    339    356    Ljava/io/IOException;
        //  665    669    676    690    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
