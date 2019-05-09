// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.net.Uri;
import android.content.Context;
import android.graphics.BitmapFactory$Options;

public class ImageUtils
{
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 0;
    
    private static int calculateInSampleSize(final BitmapFactory$Options bitmapFactory$Options, final int n, final int n2) {
        final int outHeight = bitmapFactory$Options.outHeight;
        final int outWidth = bitmapFactory$Options.outWidth;
        int n3 = 1;
        int n4 = 1;
        if (outHeight > n2 || outWidth > n) {
            final int n5 = outHeight / 2;
            final int n6 = outWidth / 2;
            while (true) {
                n3 = n4;
                if (n5 / n4 <= n2) {
                    break;
                }
                n3 = n4;
                if (n6 / n4 <= n) {
                    break;
                }
                n4 *= 2;
            }
        }
        return n3;
    }
    
    public static Bitmap decodeSampledBitmap(final Context context, final Uri uri, final int n, final int n2) throws IOException {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), (Rect)null, bitmapFactory$Options);
        bitmapFactory$Options.inSampleSize = calculateInSampleSize(bitmapFactory$Options, n, n2);
        bitmapFactory$Options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), (Rect)null, bitmapFactory$Options);
    }
    
    public static Bitmap decodeSampledBitmap(final File file, final int n, final int n2) throws IOException {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapFactory$Options);
        bitmapFactory$Options.inSampleSize = calculateInSampleSize(bitmapFactory$Options, n, n2);
        bitmapFactory$Options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapFactory$Options);
    }
    
    public static int determineOrientation(final Context context, final Uri uri) throws IOException {
        InputStream openInputStream = null;
        try {
            return determineOrientation(openInputStream = context.getContentResolver().openInputStream(uri));
        }
        finally {
            if (openInputStream != null) {
                openInputStream.close();
            }
        }
    }
    
    public static int determineOrientation(final File p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: new             Ljava/io/FileInputStream;
        //     5: dup            
        //     6: aload_0        
        //     7: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    10: astore_0       
        //    11: aload_0        
        //    12: invokestatic    net/hockeyapp/android/utils/ImageUtils.determineOrientation:(Ljava/io/InputStream;)I
        //    15: istore_1       
        //    16: aload_0        
        //    17: ifnull          24
        //    20: aload_0        
        //    21: invokevirtual   java/io/InputStream.close:()V
        //    24: iload_1        
        //    25: ireturn        
        //    26: astore_2       
        //    27: aload_3        
        //    28: astore_0       
        //    29: aload_0        
        //    30: ifnull          37
        //    33: aload_0        
        //    34: invokevirtual   java/io/InputStream.close:()V
        //    37: aload_2        
        //    38: athrow         
        //    39: astore_2       
        //    40: goto            29
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      11     26     29     Any
        //  11     16     39     43     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    public static int determineOrientation(final InputStream inputStream) {
        int n = 1;
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, (Rect)null, bitmapFactory$Options);
        if (bitmapFactory$Options.outWidth == -1 || bitmapFactory$Options.outHeight == -1) {
            n = 0;
        }
        else if (bitmapFactory$Options.outWidth / (float)bitmapFactory$Options.outHeight <= 1.0f) {
            return 0;
        }
        return n;
    }
}
