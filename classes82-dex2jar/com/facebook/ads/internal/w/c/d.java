// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.c;

import java.util.concurrent.Callable;
import android.graphics.Bitmap;

class d implements com.facebook.ads.internal.w.c.a
{
    private static final short[] a;
    private static final byte[] b;
    
    static {
        a = new short[] { 512, 512, 456, 512, 328, 456, 335, 512, 405, 328, 271, 456, 388, 335, 292, 512, 454, 405, 364, 328, 298, 271, 496, 456, 420, 388, 360, 335, 312, 292, 273, 512, 482, 454, 428, 405, 383, 364, 345, 328, 312, 298, 284, 271, 259, 496, 475, 456, 437, 420, 404, 388, 374, 360, 347, 335, 323, 312, 302, 292, 282, 273, 265, 512, 497, 482, 468, 454, 441, 428, 417, 405, 394, 383, 373, 364, 354, 345, 337, 328, 320, 312, 305, 298, 291, 284, 278, 271, 265, 259, 507, 496, 485, 475, 465, 456, 446, 437, 428, 420, 412, 404, 396, 388, 381, 374, 367, 360, 354, 347, 341, 335, 329, 323, 318, 312, 307, 302, 297, 292, 287, 282, 278, 273, 269, 265, 261, 512, 505, 497, 489, 482, 475, 468, 461, 454, 447, 441, 435, 428, 422, 417, 411, 405, 399, 394, 389, 383, 378, 373, 368, 364, 359, 354, 350, 345, 341, 337, 332, 328, 324, 320, 316, 312, 309, 305, 301, 298, 294, 291, 287, 284, 281, 278, 274, 271, 268, 265, 262, 259, 257, 507, 501, 496, 491, 485, 480, 475, 470, 465, 460, 456, 451, 446, 442, 437, 433, 428, 424, 420, 416, 412, 408, 404, 400, 396, 392, 388, 385, 381, 377, 374, 370, 367, 363, 360, 357, 354, 350, 347, 344, 341, 338, 335, 332, 329, 326, 323, 320, 318, 315, 312, 310, 307, 304, 302, 299, 297, 294, 292, 289, 287, 285, 282, 280, 278, 275, 273, 271, 269, 267, 265, 263, 261, 259 };
        b = new byte[] { 9, 11, 12, 13, 13, 14, 14, 15, 15, 15, 15, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24 };
    }
    
    static /* synthetic */ void a(final int[] array, final int n, int i, final int n2, int j, int k, int l) {
        final int n3 = n - 1;
        final int n4 = i - 1;
        final int n5 = n2 * 2 + 1;
        final short n6 = d.a[n2];
        final byte b = d.b[n2];
        final int[] array2 = new int[n5];
        if (l == 1) {
            l = k * i / j;
            int n7;
            long n8;
            long n9;
            long n10;
            long n11;
            long n12;
            long n13;
            long n14;
            long n15;
            long n16;
            int n17;
            int n18;
            int n19;
            long n20;
            long n21;
            long n22;
            long n23;
            long n24;
            long n25;
            long n26;
            long n27;
            long n28;
            int n29;
            int n30;
            long n31;
            long n32;
            long n33;
            int n34;
            int n35;
            long n36;
            long n37;
            long n38;
            for (n7 = (k + 1) * i / j, j = l; j < n7; ++j) {
                n8 = 0L;
                k = n * j;
                n9 = 0L;
                n10 = 0L;
                n11 = 0L;
                n12 = 0L;
                n13 = 0L;
                i = 0;
                n14 = 0L;
                while (i <= n2) {
                    array2[i] = array[k];
                    n13 += (array[k] >>> 16 & 0xFF) * (i + 1);
                    n12 += (array[k] >>> 8 & 0xFF) * (i + 1);
                    n11 += (array[k] & 0xFF) * (i + 1);
                    n10 += (array[k] >>> 16 & 0xFF);
                    n9 += (array[k] >>> 8 & 0xFF);
                    n14 += (array[k] & 0xFF);
                    ++i;
                }
                n15 = 0L;
                i = 1;
                n16 = 0L;
                while (i <= n2) {
                    l = k;
                    if (i <= n3) {
                        l = k + 1;
                    }
                    array2[i + n2] = array[l];
                    n13 += (array[l] >>> 16 & 0xFF) * (n2 + 1 - i);
                    n12 += (array[l] >>> 8 & 0xFF) * (n2 + 1 - i);
                    n11 += (array[l] & 0xFF) * (n2 + 1 - i);
                    n15 += (array[l] >>> 16 & 0xFF);
                    n16 += (array[l] >>> 8 & 0xFF);
                    n8 += (array[l] & 0xFF);
                    ++i;
                    k = l;
                }
                if (n2 > n3) {
                    i = n3;
                }
                else {
                    i = n2;
                }
                l = n2;
                k = 0;
                n17 = i + j * n;
                n18 = i;
                n19 = j * n;
                n20 = n15;
                n21 = n16;
                n22 = n8;
                i = l;
                n23 = n13;
                n24 = n12;
                n25 = n11;
                n26 = n20;
                n27 = n21;
                n28 = n22;
                for (l = n19; k < n; ++k, ++l, n17 = n34, n18 = n35) {
                    array[l] = (int)((long)(array[l] & 0xFF000000) | (n6 * n23 >>> b & 0xFFL) << 16 | (n6 * n24 >>> b & 0xFFL) << 8 | (n6 * n25 >>> b & 0xFFL));
                    n29 = i + n5 - n2;
                    if ((n30 = n29) >= n5) {
                        n30 = n29 - n5;
                    }
                    n31 = (array2[n30] >>> 16 & 0xFF);
                    n32 = (array2[n30] >>> 8 & 0xFF);
                    n33 = (array2[n30] & 0xFF);
                    n34 = n17;
                    if ((n35 = n18) < n3) {
                        n34 = n17 + 1;
                        n35 = n18 + 1;
                    }
                    array2[n30] = array[n34];
                    n36 = n26 + (array[n34] >>> 16 & 0xFF);
                    n37 = n27 + (array[n34] >>> 8 & 0xFF);
                    n38 = n28 + (array[n34] & 0xFF);
                    n23 = n23 - n10 + n36;
                    n24 = n24 - n9 + n37;
                    n25 = n25 - n14 + n38;
                    if (++i >= n5) {
                        i = 0;
                    }
                    n10 = n10 - n31 + (array2[i] >>> 16 & 0xFF);
                    n9 = n9 - n32 + (array2[i] >>> 8 & 0xFF);
                    n14 = n14 - n33 + (array2[i] & 0xFF);
                    n26 = n36 - (array2[i] >>> 16 & 0xFF);
                    n27 = n37 - (array2[i] >>> 8 & 0xFF);
                    n28 = n38 - (array2[i] & 0xFF);
                }
            }
        }
        else if (l == 2) {
            l = k * n / j;
            int n39;
            long n40;
            long n41;
            long n42;
            long n43;
            long n44;
            long n45;
            long n46;
            long n47;
            long n48;
            long n49;
            long n50;
            long n51;
            long n52;
            long n53;
            long n54;
            int n55;
            int n56;
            int n57;
            int n58;
            int n59;
            int n60;
            int n61;
            long n62;
            long n63;
            long n64;
            int n65;
            int n66;
            long n67;
            long n68;
            long n69;
            for (n39 = (k + 1) * n / j, j = l; j < n39; ++j) {
                n40 = 0L;
                k = 0;
                n41 = 0L;
                n42 = 0L;
                n43 = 0L;
                n44 = 0L;
                n45 = 0L;
                n46 = 0L;
                while (k <= n2) {
                    array2[k] = array[j];
                    n46 += (array[j] >>> 16 & 0xFF) * (k + 1);
                    n45 += (array[j] >>> 8 & 0xFF) * (k + 1);
                    n44 += (array[j] & 0xFF) * (k + 1);
                    n43 += (array[j] >>> 16 & 0xFF);
                    n42 += (array[j] >>> 8 & 0xFF);
                    n41 += (array[j] & 0xFF);
                    ++k;
                }
                n47 = 0L;
                n48 = 0L;
                k = 1;
                l = j;
                n49 = n46;
                n50 = n45;
                n51 = n44;
                n52 = n48;
                n53 = n47;
                n54 = n40;
                while (k <= n2) {
                    n55 = l;
                    if (k <= n4) {
                        n55 = l + n;
                    }
                    array2[k + n2] = array[n55];
                    n49 += (array[n55] >>> 16 & 0xFF) * (n2 + 1 - k);
                    n50 += (array[n55] >>> 8 & 0xFF) * (n2 + 1 - k);
                    n51 += (array[n55] & 0xFF) * (n2 + 1 - k);
                    n52 += (array[n55] >>> 16 & 0xFF);
                    n53 += (array[n55] >>> 8 & 0xFF);
                    n54 += (array[n55] & 0xFF);
                    ++k;
                    l = n55;
                }
                if (n2 > n4) {
                    k = n4;
                }
                else {
                    k = n2;
                }
                n56 = k * n + j;
                l = 0;
                n57 = k;
                n58 = j;
                k = n2;
                n59 = n57;
                while (l < i) {
                    array[n58] = (int)((long)(array[n58] & 0xFF000000) | (n6 * n49 >>> b & 0xFFL) << 16 | (n6 * n50 >>> b & 0xFFL) << 8 | (n6 * n51 >>> b & 0xFFL));
                    n60 = k + n5 - n2;
                    if ((n61 = n60) >= n5) {
                        n61 = n60 - n5;
                    }
                    n62 = (array2[n61] >>> 16 & 0xFF);
                    n63 = (array2[n61] >>> 8 & 0xFF);
                    n64 = (array2[n61] & 0xFF);
                    n65 = n56;
                    if ((n66 = n59) < n4) {
                        n65 = n56 + n;
                        n66 = n59 + 1;
                    }
                    array2[n61] = array[n65];
                    n67 = n52 + (array[n65] >>> 16 & 0xFF);
                    n68 = n53 + (array[n65] >>> 8 & 0xFF);
                    n69 = n54 + (array[n65] & 0xFF);
                    n49 = n49 - n43 + n67;
                    n50 = n50 - n42 + n68;
                    n51 = n51 - n41 + n69;
                    if (++k >= n5) {
                        k = 0;
                    }
                    n43 = n43 - n62 + (array2[k] >>> 16 & 0xFF);
                    n42 = n42 - n63 + (array2[k] >>> 8 & 0xFF);
                    n41 = n41 - n64 + (array2[k] & 0xFF);
                    n52 = n67 - (array2[k] >>> 16 & 0xFF);
                    n53 = n68 - (array2[k] >>> 8 & 0xFF);
                    n54 = n69 - (array2[k] & 0xFF);
                    ++l;
                    n58 += n;
                    n56 = n65;
                    n59 = n66;
                }
            }
        }
    }
    
    @Override
    public Bitmap a(final Bitmap p0, final float p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   android/graphics/Bitmap.getWidth:()I
        //     4: istore          4
        //     6: aload_1        
        //     7: invokevirtual   android/graphics/Bitmap.getHeight:()I
        //    10: istore          5
        //    12: iload           4
        //    14: iload           5
        //    16: imul           
        //    17: newarray        I
        //    19: astore          7
        //    21: aload_1        
        //    22: aload           7
        //    24: iconst_0       
        //    25: iload           4
        //    27: iconst_0       
        //    28: iconst_0       
        //    29: iload           4
        //    31: iload           5
        //    33: invokevirtual   android/graphics/Bitmap.getPixels:([IIIIIII)V
        //    36: getstatic       com/facebook/ads/internal/w/c/f.a:I
        //    39: istore          6
        //    41: new             Ljava/util/ArrayList;
        //    44: dup            
        //    45: iload           6
        //    47: invokespecial   java/util/ArrayList.<init>:(I)V
        //    50: astore_1       
        //    51: new             Ljava/util/ArrayList;
        //    54: dup            
        //    55: iload           6
        //    57: invokespecial   java/util/ArrayList.<init>:(I)V
        //    60: astore          8
        //    62: iconst_0       
        //    63: istore_3       
        //    64: iload_3        
        //    65: iload           6
        //    67: if_icmpge       129
        //    70: aload_1        
        //    71: new             Lcom/facebook/ads/internal/w/c/d$a;
        //    74: dup            
        //    75: aload           7
        //    77: iload           4
        //    79: iload           5
        //    81: fload_2        
        //    82: f2i            
        //    83: iload           6
        //    85: iload_3        
        //    86: iconst_1       
        //    87: invokespecial   com/facebook/ads/internal/w/c/d$a.<init>:([IIIIIII)V
        //    90: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //    93: pop            
        //    94: aload           8
        //    96: new             Lcom/facebook/ads/internal/w/c/d$a;
        //    99: dup            
        //   100: aload           7
        //   102: iload           4
        //   104: iload           5
        //   106: fload_2        
        //   107: f2i            
        //   108: iload           6
        //   110: iload_3        
        //   111: iconst_2       
        //   112: invokespecial   com/facebook/ads/internal/w/c/d$a.<init>:([IIIIIII)V
        //   115: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   118: pop            
        //   119: iload_3        
        //   120: iconst_1       
        //   121: iadd           
        //   122: istore_3       
        //   123: goto            64
        //   126: astore_1       
        //   127: aconst_null    
        //   128: areturn        
        //   129: getstatic       com/facebook/ads/internal/w/c/f.b:Ljava/util/concurrent/ExecutorService;
        //   132: aload_1        
        //   133: invokeinterface java/util/concurrent/ExecutorService.invokeAll:(Ljava/util/Collection;)Ljava/util/List;
        //   138: pop            
        //   139: getstatic       com/facebook/ads/internal/w/c/f.b:Ljava/util/concurrent/ExecutorService;
        //   142: aload           8
        //   144: invokeinterface java/util/concurrent/ExecutorService.invokeAll:(Ljava/util/Collection;)Ljava/util/List;
        //   149: pop            
        //   150: aload           7
        //   152: iload           4
        //   154: iload           5
        //   156: getstatic       android/graphics/Bitmap$Config.ARGB_8888:Landroid/graphics/Bitmap$Config;
        //   159: invokestatic    android/graphics/Bitmap.createBitmap:([IIILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
        //   162: astore_1       
        //   163: aload_1        
        //   164: areturn        
        //   165: astore_1       
        //   166: aconst_null    
        //   167: areturn        
        //   168: astore_1       
        //   169: aconst_null    
        //   170: areturn        
        //   171: astore_1       
        //   172: aconst_null    
        //   173: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  12     21     126    129    Ljava/lang/OutOfMemoryError;
        //  129    139    165    168    Ljava/lang/InterruptedException;
        //  139    150    168    171    Ljava/lang/InterruptedException;
        //  150    163    171    174    Ljava/lang/OutOfMemoryError;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 98, Size: 98
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
    
    private static class a implements Callable<Void>
    {
        private final int[] a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;
        private final int f;
        private final int g;
        
        public a(final int[] a, final int b, final int c, final int d, final int e, final int f, final int g) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
            this.g = g;
        }
        
        public Void a() {
            com.facebook.ads.internal.w.c.d.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
            return null;
        }
    }
}
