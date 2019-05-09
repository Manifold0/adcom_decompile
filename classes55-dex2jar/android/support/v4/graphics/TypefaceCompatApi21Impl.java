// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.graphics;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.provider.FontsContractCompat;
import android.os.CancellationSignal;
import android.content.Context;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.system.Os;
import java.io.File;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
@RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl
{
    private static final String TAG = "TypefaceCompatApi21Impl";
    
    private File getFile(final ParcelFileDescriptor parcelFileDescriptor) {
        try {
            final String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
            return null;
        }
        catch (ErrnoException ex) {
            return null;
        }
    }
    
    @Override
    public Typeface createFromFontInfo(final Context p0, final CancellationSignal p1, @NonNull final FontsContractCompat.FontInfo[] p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: arraylength    
        //     2: iconst_1       
        //     3: if_icmpge       10
        //     6: aconst_null    
        //     7: astore_1       
        //     8: aload_1        
        //     9: areturn        
        //    10: aload_0        
        //    11: aload_3        
        //    12: iload           4
        //    14: invokevirtual   android/support/v4/graphics/TypefaceCompatApi21Impl.findBestInfo:([Landroid/support/v4/provider/FontsContractCompat$FontInfo;I)Landroid/support/v4/provider/FontsContractCompat$FontInfo;
        //    17: astore_3       
        //    18: aload_1        
        //    19: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    22: astore          5
        //    24: aload           5
        //    26: aload_3        
        //    27: invokevirtual   android/support/v4/provider/FontsContractCompat$FontInfo.getUri:()Landroid/net/Uri;
        //    30: ldc             "r"
        //    32: aload_2        
        //    33: invokevirtual   android/content/ContentResolver.openFileDescriptor:(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
        //    36: astore_3       
        //    37: aload_0        
        //    38: aload_3        
        //    39: invokespecial   android/support/v4/graphics/TypefaceCompatApi21Impl.getFile:(Landroid/os/ParcelFileDescriptor;)Ljava/io/File;
        //    42: astore_2       
        //    43: aload_2        
        //    44: ifnull          54
        //    47: aload_2        
        //    48: invokevirtual   java/io/File.canRead:()Z
        //    51: ifne            209
        //    54: new             Ljava/io/FileInputStream;
        //    57: dup            
        //    58: aload_3        
        //    59: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //    62: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/FileDescriptor;)V
        //    65: astore          5
        //    67: aconst_null    
        //    68: astore_2       
        //    69: aload_0        
        //    70: aload_1        
        //    71: aload           5
        //    73: invokespecial   android/support/v4/graphics/TypefaceCompatBaseImpl.createFromInputStream:(Landroid/content/Context;Ljava/io/InputStream;)Landroid/graphics/Typeface;
        //    76: astore_1       
        //    77: aload_1        
        //    78: astore_2       
        //    79: aload           5
        //    81: ifnull          93
        //    84: iconst_0       
        //    85: ifeq            148
        //    88: aload           5
        //    90: invokevirtual   java/io/FileInputStream.close:()V
        //    93: aload_2        
        //    94: astore_1       
        //    95: aload_3        
        //    96: ifnull          8
        //    99: iconst_0       
        //   100: ifeq            162
        //   103: aload_3        
        //   104: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   107: aload_2        
        //   108: areturn        
        //   109: astore_1       
        //   110: new             Ljava/lang/NullPointerException;
        //   113: dup            
        //   114: invokespecial   java/lang/NullPointerException.<init>:()V
        //   117: athrow         
        //   118: astore_1       
        //   119: aconst_null    
        //   120: areturn        
        //   121: astore_1       
        //   122: new             Ljava/lang/NullPointerException;
        //   125: dup            
        //   126: invokespecial   java/lang/NullPointerException.<init>:()V
        //   129: athrow         
        //   130: astore_2       
        //   131: aload_2        
        //   132: athrow         
        //   133: astore_1       
        //   134: aload_3        
        //   135: ifnull          146
        //   138: aload_2        
        //   139: ifnull          254
        //   142: aload_3        
        //   143: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   146: aload_1        
        //   147: athrow         
        //   148: aload           5
        //   150: invokevirtual   java/io/FileInputStream.close:()V
        //   153: goto            93
        //   156: astore_1       
        //   157: aconst_null    
        //   158: astore_2       
        //   159: goto            134
        //   162: aload_3        
        //   163: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   166: aload_2        
        //   167: areturn        
        //   168: astore_1       
        //   169: aload_1        
        //   170: astore_2       
        //   171: aload_1        
        //   172: athrow         
        //   173: astore_1       
        //   174: aload           5
        //   176: ifnull          188
        //   179: aload_2        
        //   180: ifnull          201
        //   183: aload           5
        //   185: invokevirtual   java/io/FileInputStream.close:()V
        //   188: aload_1        
        //   189: athrow         
        //   190: astore          5
        //   192: aload_2        
        //   193: aload           5
        //   195: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   198: goto            188
        //   201: aload           5
        //   203: invokevirtual   java/io/FileInputStream.close:()V
        //   206: goto            188
        //   209: aload_2        
        //   210: invokestatic    android/graphics/Typeface.createFromFile:(Ljava/io/File;)Landroid/graphics/Typeface;
        //   213: astore_2       
        //   214: aload_2        
        //   215: astore_1       
        //   216: aload_3        
        //   217: ifnull          8
        //   220: iconst_0       
        //   221: ifeq            239
        //   224: aload_3        
        //   225: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   228: aload_2        
        //   229: areturn        
        //   230: astore_1       
        //   231: new             Ljava/lang/NullPointerException;
        //   234: dup            
        //   235: invokespecial   java/lang/NullPointerException.<init>:()V
        //   238: athrow         
        //   239: aload_3        
        //   240: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   243: aload_2        
        //   244: areturn        
        //   245: astore_3       
        //   246: aload_2        
        //   247: aload_3        
        //   248: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   251: goto            146
        //   254: aload_3        
        //   255: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   258: goto            146
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  24     37     118    121    Ljava/io/IOException;
        //  37     43     130    134    Ljava/lang/Throwable;
        //  37     43     156    162    Any
        //  47     54     130    134    Ljava/lang/Throwable;
        //  47     54     156    162    Any
        //  54     67     130    134    Ljava/lang/Throwable;
        //  54     67     156    162    Any
        //  69     77     168    173    Ljava/lang/Throwable;
        //  69     77     173    209    Any
        //  88     93     121    130    Ljava/lang/Throwable;
        //  88     93     156    162    Any
        //  103    107    109    118    Ljava/lang/Throwable;
        //  103    107    118    121    Ljava/io/IOException;
        //  110    118    118    121    Ljava/io/IOException;
        //  122    130    130    134    Ljava/lang/Throwable;
        //  122    130    156    162    Any
        //  131    133    133    134    Any
        //  142    146    245    254    Ljava/lang/Throwable;
        //  142    146    118    121    Ljava/io/IOException;
        //  146    148    118    121    Ljava/io/IOException;
        //  148    153    130    134    Ljava/lang/Throwable;
        //  148    153    156    162    Any
        //  162    166    118    121    Ljava/io/IOException;
        //  171    173    173    209    Any
        //  183    188    190    201    Ljava/lang/Throwable;
        //  183    188    156    162    Any
        //  188    190    130    134    Ljava/lang/Throwable;
        //  188    190    156    162    Any
        //  192    198    130    134    Ljava/lang/Throwable;
        //  192    198    156    162    Any
        //  201    206    130    134    Ljava/lang/Throwable;
        //  201    206    156    162    Any
        //  209    214    130    134    Ljava/lang/Throwable;
        //  209    214    156    162    Any
        //  224    228    230    239    Ljava/lang/Throwable;
        //  224    228    118    121    Ljava/io/IOException;
        //  231    239    118    121    Ljava/io/IOException;
        //  239    243    118    121    Ljava/io/IOException;
        //  246    251    118    121    Ljava/io/IOException;
        //  254    258    118    121    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 151, Size: 151
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
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
}
