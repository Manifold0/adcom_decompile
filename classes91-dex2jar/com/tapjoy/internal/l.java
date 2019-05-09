// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class l extends BroadcastReceiver
{
    protected final int a(final Context p0, final Intent p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_3       
        //     2: new             Landroid/content/ComponentName;
        //     5: dup            
        //     6: aload_1        
        //     7: aload_0        
        //     8: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    11: invokespecial   android/content/ComponentName.<init>:(Landroid/content/Context;Ljava/lang/Class;)V
        //    14: astore          4
        //    16: aload_1        
        //    17: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    20: aload           4
        //    22: sipush          128
        //    25: invokevirtual   android/content/pm/PackageManager.getReceiverInfo:(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
        //    28: getfield        android/content/pm/ActivityInfo.metaData:Landroid/os/Bundle;
        //    31: astore          4
        //    33: aload           4
        //    35: ifnull          154
        //    38: aload           4
        //    40: invokevirtual   android/os/Bundle.keySet:()Ljava/util/Set;
        //    43: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    48: astore          5
        //    50: iconst_0       
        //    51: istore_3       
        //    52: aload           5
        //    54: invokeinterface java/util/Iterator.hasNext:()Z
        //    59: ifeq            179
        //    62: aload           4
        //    64: aload           5
        //    66: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    71: checkcast       Ljava/lang/String;
        //    74: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    77: astore          6
        //    79: aload           6
        //    81: ifnull          150
        //    84: aload           6
        //    86: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //    89: invokevirtual   java/lang/Class.newInstance:()Ljava/lang/Object;
        //    92: astore          7
        //    94: aload           7
        //    96: instanceof      Landroid/content/BroadcastReceiver;
        //    99: ifeq            52
        //   102: aload           7
        //   104: checkcast       Landroid/content/BroadcastReceiver;
        //   107: astore          7
        //   109: new             Landroid/content/Intent;
        //   112: dup            
        //   113: aload_2        
        //   114: invokespecial   android/content/Intent.<init>:(Landroid/content/Intent;)V
        //   117: astore          8
        //   119: aload           8
        //   121: new             Landroid/content/ComponentName;
        //   124: dup            
        //   125: aload_1        
        //   126: aload           6
        //   128: invokespecial   android/content/ComponentName.<init>:(Landroid/content/Context;Ljava/lang/String;)V
        //   131: invokevirtual   android/content/Intent.setComponent:(Landroid/content/ComponentName;)Landroid/content/Intent;
        //   134: pop            
        //   135: aload           7
        //   137: aload_1        
        //   138: aload           8
        //   140: invokevirtual   android/content/BroadcastReceiver.onReceive:(Landroid/content/Context;Landroid/content/Intent;)V
        //   143: iload_3        
        //   144: iconst_1       
        //   145: iadd           
        //   146: istore_3       
        //   147: goto            52
        //   150: goto            52
        //   153: astore_1       
        //   154: iload_3        
        //   155: ireturn        
        //   156: astore_1       
        //   157: iconst_0       
        //   158: ireturn        
        //   159: astore          6
        //   161: goto            52
        //   164: astore          6
        //   166: goto            52
        //   169: astore          6
        //   171: goto            52
        //   174: astore          6
        //   176: goto            52
        //   179: iload_3        
        //   180: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  16     33     156    159    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  38     50     156    159    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  52     79     153    154    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  84     109    169    174    Ljava/lang/ClassNotFoundException;
        //  84     109    164    169    Ljava/lang/IllegalAccessException;
        //  84     109    159    164    Ljava/lang/InstantiationException;
        //  84     109    153    154    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  109    143    174    179    Ljava/lang/Throwable;
        //  109    143    169    174    Ljava/lang/ClassNotFoundException;
        //  109    143    164    169    Ljava/lang/IllegalAccessException;
        //  109    143    159    164    Ljava/lang/InstantiationException;
        //  109    143    153    154    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 84, Size: 84
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.a(context, intent);
    }
}
