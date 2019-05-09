// 
// Decompiled by Procyon v0.5.34
// 

package com.onevcat.UniPasteBoard;

import android.content.ClipData;
import android.text.ClipboardManager;
import android.os.Build$VERSION;
import android.app.Activity;
import com.unity3d.player.UnityPlayer;
import android.util.Log;
import android.annotation.SuppressLint;
import android.content.ClipData$Item;
import android.content.Context;

class PasteBoard
{
    private static CharSequence result;
    
    static {
        PasteBoard.result = "";
    }
    
    @SuppressLint({ "NewApi" })
    public static CharSequence coerceToText(final Context p0, final ClipData$Item p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   android/content/ClipData$Item.getText:()Ljava/lang/CharSequence;
        //     4: astore_3       
        //     5: aload_3        
        //     6: ifnull          11
        //     9: aload_3        
        //    10: areturn        
        //    11: aload_1        
        //    12: invokevirtual   android/content/ClipData$Item.getUri:()Landroid/net/Uri;
        //    15: astore          6
        //    17: aload           6
        //    19: ifnull          230
        //    22: aconst_null    
        //    23: astore_1       
        //    24: aconst_null    
        //    25: astore_3       
        //    26: aconst_null    
        //    27: astore          4
        //    29: aload_0        
        //    30: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    33: aload           6
        //    35: ldc             "text/*"
        //    37: aconst_null    
        //    38: invokevirtual   android/content/ContentResolver.openTypedAssetFileDescriptor:(Landroid/net/Uri;Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/res/AssetFileDescriptor;
        //    41: invokevirtual   android/content/res/AssetFileDescriptor.createInputStream:()Ljava/io/FileInputStream;
        //    44: astore_0       
        //    45: aload_0        
        //    46: astore          4
        //    48: aload_0        
        //    49: astore_1       
        //    50: aload_0        
        //    51: astore_3       
        //    52: new             Ljava/io/InputStreamReader;
        //    55: dup            
        //    56: aload_0        
        //    57: ldc             "UTF-8"
        //    59: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    62: astore          5
        //    64: aload_0        
        //    65: astore          4
        //    67: aload_0        
        //    68: astore_1       
        //    69: aload_0        
        //    70: astore_3       
        //    71: new             Ljava/lang/StringBuilder;
        //    74: dup            
        //    75: sipush          128
        //    78: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //    81: astore          7
        //    83: aload_0        
        //    84: astore          4
        //    86: aload_0        
        //    87: astore_1       
        //    88: aload_0        
        //    89: astore_3       
        //    90: sipush          8192
        //    93: newarray        C
        //    95: astore          8
        //    97: aload_0        
        //    98: astore          4
        //   100: aload_0        
        //   101: astore_1       
        //   102: aload_0        
        //   103: astore_3       
        //   104: aload           5
        //   106: aload           8
        //   108: invokevirtual   java/io/InputStreamReader.read:([C)I
        //   111: istore_2       
        //   112: iload_2        
        //   113: ifle            153
        //   116: aload_0        
        //   117: astore          4
        //   119: aload_0        
        //   120: astore_1       
        //   121: aload_0        
        //   122: astore_3       
        //   123: aload           7
        //   125: aload           8
        //   127: iconst_0       
        //   128: iload_2        
        //   129: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //   132: pop            
        //   133: goto            97
        //   136: astore_0       
        //   137: aload           4
        //   139: ifnull          147
        //   142: aload           4
        //   144: invokevirtual   java/io/FileInputStream.close:()V
        //   147: aload           6
        //   149: invokevirtual   android/net/Uri.toString:()Ljava/lang/String;
        //   152: areturn        
        //   153: aload_0        
        //   154: astore          4
        //   156: aload_0        
        //   157: astore_1       
        //   158: aload_0        
        //   159: astore_3       
        //   160: aload           7
        //   162: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   165: astore          5
        //   167: aload           5
        //   169: astore_1       
        //   170: aload_1        
        //   171: astore_3       
        //   172: aload_0        
        //   173: ifnull          9
        //   176: aload_0        
        //   177: invokevirtual   java/io/FileInputStream.close:()V
        //   180: aload_1        
        //   181: areturn        
        //   182: astore_0       
        //   183: aload_1        
        //   184: areturn        
        //   185: astore_0       
        //   186: aload_1        
        //   187: astore_3       
        //   188: ldc             "ClippedData"
        //   190: ldc             "Failure loading text"
        //   192: aload_0        
        //   193: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   196: pop            
        //   197: aload_1        
        //   198: astore_3       
        //   199: aload_0        
        //   200: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   203: astore_0       
        //   204: aload_0        
        //   205: astore_3       
        //   206: aload_1        
        //   207: ifnull          9
        //   210: aload_1        
        //   211: invokevirtual   java/io/FileInputStream.close:()V
        //   214: aload_0        
        //   215: areturn        
        //   216: astore_1       
        //   217: aload_0        
        //   218: areturn        
        //   219: astore_0       
        //   220: aload_3        
        //   221: ifnull          228
        //   224: aload_3        
        //   225: invokevirtual   java/io/FileInputStream.close:()V
        //   228: aload_0        
        //   229: athrow         
        //   230: aload_1        
        //   231: invokevirtual   android/content/ClipData$Item.getIntent:()Landroid/content/Intent;
        //   234: astore_0       
        //   235: aload_0        
        //   236: ifnull          245
        //   239: aload_0        
        //   240: iconst_1       
        //   241: invokevirtual   android/content/Intent.toUri:(I)Ljava/lang/String;
        //   244: areturn        
        //   245: ldc             ""
        //   247: areturn        
        //   248: astore_0       
        //   249: goto            147
        //   252: astore_1       
        //   253: goto            228
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  29     45     136    153    Ljava/io/FileNotFoundException;
        //  29     45     185    219    Ljava/io/IOException;
        //  29     45     219    230    Any
        //  52     64     136    153    Ljava/io/FileNotFoundException;
        //  52     64     185    219    Ljava/io/IOException;
        //  52     64     219    230    Any
        //  71     83     136    153    Ljava/io/FileNotFoundException;
        //  71     83     185    219    Ljava/io/IOException;
        //  71     83     219    230    Any
        //  90     97     136    153    Ljava/io/FileNotFoundException;
        //  90     97     185    219    Ljava/io/IOException;
        //  90     97     219    230    Any
        //  104    112    136    153    Ljava/io/FileNotFoundException;
        //  104    112    185    219    Ljava/io/IOException;
        //  104    112    219    230    Any
        //  123    133    136    153    Ljava/io/FileNotFoundException;
        //  123    133    185    219    Ljava/io/IOException;
        //  123    133    219    230    Any
        //  142    147    248    252    Ljava/io/IOException;
        //  160    167    136    153    Ljava/io/FileNotFoundException;
        //  160    167    185    219    Ljava/io/IOException;
        //  160    167    219    230    Any
        //  176    180    182    185    Ljava/io/IOException;
        //  188    197    219    230    Any
        //  199    204    219    230    Any
        //  210    214    216    219    Ljava/io/IOException;
        //  224    228    252    256    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 159, Size: 159
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
    
    @SuppressLint({ "NewApi" })
    public static String getClipBoardString() throws InterruptedException {
        PasteBoard.result = "";
        Log.d("UniPasteBoard", "Begin");
        final Activity currentActivity = UnityPlayer.currentActivity;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Label_0039: {
                    if (Build$VERSION.SDK_INT >= 11) {
                        break Label_0039;
                    }
                    PasteBoard.result = ((ClipboardManager)currentActivity.getSystemService("clipboard")).getText().toString();
                Block_4_Outer:
                    while (true) {
                        synchronized (this) {
                            this.notify();
                            return;
                            while (true) {
                                CharSequence string = null;
                                final ClipData primaryClip;
                                final ClipData$Item item = primaryClip.getItemAt(0);
                                string = PasteBoard.coerceToText((Context)currentActivity, item).toString();
                                Label_0093: {
                                    PasteBoard.result = string;
                                }
                                continue Block_4_Outer;
                                final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager)currentActivity.getSystemService("clipboard");
                                currentActivity.getContentResolver();
                                primaryClip = clipboardManager.getPrimaryClip();
                                continue;
                            }
                        }
                        // iftrue(Label_0093:, false)
                        // iftrue(Label_0030:, primaryClip == null)
                    }
                }
            }
        };
        synchronized (runnable) {
            currentActivity.runOnUiThread((Runnable)runnable);
            runnable.wait();
            return PasteBoard.result.toString();
        }
    }
    
    public static void setClipBoardString(final String s) {
        Log.d("UniPasteBoard", "Copy Begin");
        final Activity currentActivity = UnityPlayer.currentActivity;
        currentActivity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                if (Build$VERSION.SDK_INT >= 11) {
                    Log.d("UniPasteBoard", "Miao>11");
                    final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager)currentActivity.getSystemService("clipboard");
                    if (clipboardManager != null) {
                        clipboardManager.setPrimaryClip(ClipData.newPlainText((CharSequence)"", (CharSequence)s));
                        Log.d("UniPasteBoard", "Copy OK");
                    }
                }
                else {
                    final ClipboardManager clipboardManager2 = (ClipboardManager)currentActivity.getSystemService("clipboard");
                    if (clipboardManager2 != null) {
                        clipboardManager2.setText((CharSequence)s);
                    }
                }
            }
        });
    }
}
