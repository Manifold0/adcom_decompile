// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.graphics;

import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Process;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.InputStream;
import android.support.annotation.RequiresApi;
import java.io.File;
import java.nio.ByteBuffer;
import android.content.res.Resources;
import android.content.Context;
import java.io.IOException;
import java.io.Closeable;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
public class TypefaceCompatUtil
{
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";
    
    private TypefaceCompatUtil() {
    }
    
    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context tempFile, final Resources resources, final int n) {
        tempFile = (Context)getTempFile(tempFile);
        if (tempFile == null) {
            return null;
        }
        try {
            if (!copyToFile((File)tempFile, resources, n)) {
                return null;
            }
            return mmap((File)tempFile);
        }
        finally {
            ((File)tempFile).delete();
        }
    }
    
    public static boolean copyToFile(final File file, final Resources resources, final int n) {
        Closeable openRawResource = null;
        try {
            return copyToFile(file, (InputStream)(openRawResource = resources.openRawResource(n)));
        }
        finally {
            closeQuietly(openRawResource);
        }
    }
    
    public static boolean copyToFile(File file, final InputStream ex) {
        Object o = null;
        final File file2 = null;
        while (true) {
            try {
                try {
                    file = (File)new FileOutputStream(file, false);
                    Label_0079: {
                        try {
                            o = new byte[1024];
                            while (true) {
                                final int read = ((InputStream)ex).read((byte[])o);
                                if (read == -1) {
                                    break Label_0079;
                                }
                                ((FileOutputStream)file).write((byte[])o, 0, read);
                            }
                        }
                        catch (IOException ex2) {
                            o = file;
                            Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + ex.getMessage());
                            closeQuietly((Closeable)file);
                            return false;
                            closeQuietly((Closeable)file);
                            return true;
                            closeQuietly((Closeable)o);
                            throw file;
                        }
                        finally {
                            o = file;
                            final File file3;
                            file = file3;
                        }
                    }
                }
                finally {}
            }
            catch (IOException ex) {
                file = file2;
                continue;
            }
            break;
        }
    }
    
    public static File getTempFile(final Context context) {
        final String string = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; ++i) {
            final File file = new File(context.getCacheDir(), string + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
            }
            catch (IOException ex) {}
        }
        return null;
    }
    
    @RequiresApi(19)
    public static ByteBuffer mmap(final Context p0, final CancellationSignal p1, final Uri p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //     4: astore_0       
        //     5: aload_0        
        //     6: aload_2        
        //     7: ldc             "r"
        //     9: aload_1        
        //    10: invokevirtual   android/content/ContentResolver.openFileDescriptor:(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
        //    13: astore_2       
        //    14: new             Ljava/io/FileInputStream;
        //    17: dup            
        //    18: aload_2        
        //    19: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //    22: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/FileDescriptor;)V
        //    25: astore          5
        //    27: aload           5
        //    29: invokevirtual   java/io/FileInputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //    32: astore_0       
        //    33: aload_0        
        //    34: invokevirtual   java/nio/channels/FileChannel.size:()J
        //    37: lstore_3       
        //    38: aload_0        
        //    39: getstatic       java/nio/channels/FileChannel$MapMode.READ_ONLY:Ljava/nio/channels/FileChannel$MapMode;
        //    42: lconst_0       
        //    43: lload_3        
        //    44: invokevirtual   java/nio/channels/FileChannel.map:(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
        //    47: astore_0       
        //    48: aload           5
        //    50: ifnull          62
        //    53: iconst_0       
        //    54: ifeq            106
        //    57: aload           5
        //    59: invokevirtual   java/io/FileInputStream.close:()V
        //    62: aload_2        
        //    63: ifnull          74
        //    66: iconst_0       
        //    67: ifeq            129
        //    70: aload_2        
        //    71: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //    74: aload_0        
        //    75: areturn        
        //    76: astore_0       
        //    77: new             Ljava/lang/NullPointerException;
        //    80: dup            
        //    81: invokespecial   java/lang/NullPointerException.<init>:()V
        //    84: athrow         
        //    85: astore_1       
        //    86: aload_1        
        //    87: athrow         
        //    88: astore_0       
        //    89: aload_2        
        //    90: ifnull          101
        //    93: aload_1        
        //    94: ifnull          183
        //    97: aload_2        
        //    98: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   101: aload_0        
        //   102: athrow         
        //   103: astore_0       
        //   104: aconst_null    
        //   105: areturn        
        //   106: aload           5
        //   108: invokevirtual   java/io/FileInputStream.close:()V
        //   111: goto            62
        //   114: astore_0       
        //   115: aconst_null    
        //   116: astore_1       
        //   117: goto            89
        //   120: astore_0       
        //   121: new             Ljava/lang/NullPointerException;
        //   124: dup            
        //   125: invokespecial   java/lang/NullPointerException.<init>:()V
        //   128: athrow         
        //   129: aload_2        
        //   130: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   133: aload_0        
        //   134: areturn        
        //   135: astore_1       
        //   136: aload_1        
        //   137: athrow         
        //   138: astore_0       
        //   139: aload           5
        //   141: ifnull          153
        //   144: aload_1        
        //   145: ifnull          166
        //   148: aload           5
        //   150: invokevirtual   java/io/FileInputStream.close:()V
        //   153: aload_0        
        //   154: athrow         
        //   155: astore          5
        //   157: aload_1        
        //   158: aload           5
        //   160: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   163: goto            153
        //   166: aload           5
        //   168: invokevirtual   java/io/FileInputStream.close:()V
        //   171: goto            153
        //   174: astore_2       
        //   175: aload_1        
        //   176: aload_2        
        //   177: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   180: goto            101
        //   183: aload_2        
        //   184: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   187: goto            101
        //   190: astore_0       
        //   191: aconst_null    
        //   192: astore_1       
        //   193: goto            139
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      14     103    106    Ljava/io/IOException;
        //  14     27     85     89     Ljava/lang/Throwable;
        //  14     27     114    120    Any
        //  27     48     135    139    Ljava/lang/Throwable;
        //  27     48     190    196    Any
        //  57     62     76     85     Ljava/lang/Throwable;
        //  57     62     114    120    Any
        //  70     74     120    129    Ljava/lang/Throwable;
        //  70     74     103    106    Ljava/io/IOException;
        //  77     85     85     89     Ljava/lang/Throwable;
        //  77     85     114    120    Any
        //  86     88     88     89     Any
        //  97     101    174    183    Ljava/lang/Throwable;
        //  97     101    103    106    Ljava/io/IOException;
        //  101    103    103    106    Ljava/io/IOException;
        //  106    111    85     89     Ljava/lang/Throwable;
        //  106    111    114    120    Any
        //  121    129    103    106    Ljava/io/IOException;
        //  129    133    103    106    Ljava/io/IOException;
        //  136    138    138    139    Any
        //  148    153    155    166    Ljava/lang/Throwable;
        //  148    153    114    120    Any
        //  153    155    85     89     Ljava/lang/Throwable;
        //  153    155    114    120    Any
        //  157    163    85     89     Ljava/lang/Throwable;
        //  157    163    114    120    Any
        //  166    171    85     89     Ljava/lang/Throwable;
        //  166    171    114    120    Any
        //  175    180    103    106    Ljava/io/IOException;
        //  183    187    103    106    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 109, Size: 109
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
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
    
    @RequiresApi(19)
    private static ByteBuffer mmap(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0        
        //     5: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //     8: astore          4
        //    10: aload           4
        //    12: invokevirtual   java/io/FileInputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //    15: astore_0       
        //    16: aload_0        
        //    17: invokevirtual   java/nio/channels/FileChannel.size:()J
        //    20: lstore_1       
        //    21: aload_0        
        //    22: getstatic       java/nio/channels/FileChannel$MapMode.READ_ONLY:Ljava/nio/channels/FileChannel$MapMode;
        //    25: lconst_0       
        //    26: lload_1        
        //    27: invokevirtual   java/nio/channels/FileChannel.map:(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
        //    30: astore_0       
        //    31: aload           4
        //    33: ifnull          45
        //    36: iconst_0       
        //    37: ifeq            56
        //    40: aload           4
        //    42: invokevirtual   java/io/FileInputStream.close:()V
        //    45: aload_0        
        //    46: areturn        
        //    47: astore_0       
        //    48: new             Ljava/lang/NullPointerException;
        //    51: dup            
        //    52: invokespecial   java/lang/NullPointerException.<init>:()V
        //    55: athrow         
        //    56: aload           4
        //    58: invokevirtual   java/io/FileInputStream.close:()V
        //    61: aload_0        
        //    62: areturn        
        //    63: astore_3       
        //    64: aload_3        
        //    65: athrow         
        //    66: astore_0       
        //    67: aload           4
        //    69: ifnull          81
        //    72: aload_3        
        //    73: ifnull          94
        //    76: aload           4
        //    78: invokevirtual   java/io/FileInputStream.close:()V
        //    81: aload_0        
        //    82: athrow         
        //    83: astore          4
        //    85: aload_3        
        //    86: aload           4
        //    88: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //    91: goto            81
        //    94: aload           4
        //    96: invokevirtual   java/io/FileInputStream.close:()V
        //    99: goto            81
        //   102: astore_0       
        //   103: aconst_null    
        //   104: astore_3       
        //   105: goto            67
        //   108: astore_0       
        //   109: aconst_null    
        //   110: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      10     108    111    Ljava/io/IOException;
        //  10     31     63     67     Ljava/lang/Throwable;
        //  10     31     102    108    Any
        //  40     45     47     56     Ljava/lang/Throwable;
        //  40     45     108    111    Ljava/io/IOException;
        //  48     56     108    111    Ljava/io/IOException;
        //  56     61     108    111    Ljava/io/IOException;
        //  64     66     66     67     Any
        //  76     81     83     94     Ljava/lang/Throwable;
        //  76     81     108    111    Ljava/io/IOException;
        //  81     83     108    111    Ljava/io/IOException;
        //  85     91     108    111    Ljava/io/IOException;
        //  94     99     108    111    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0094:
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
