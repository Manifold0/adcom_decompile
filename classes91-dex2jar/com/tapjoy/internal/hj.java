// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.nio.ByteBuffer;
import android.os.Looper;
import android.content.Context;
import android.os.Handler;
import android.graphics.Bitmap;
import android.widget.ImageView;

public final class hj extends ImageView implements Runnable
{
    private hf a;
    private Bitmap b;
    private final Handler c;
    private boolean d;
    private boolean e;
    private boolean f;
    private Thread g;
    private b h;
    private long i;
    private a j;
    private final Runnable k;
    private final Runnable l;
    
    public hj(final Context context) {
        super(context);
        this.c = new Handler(Looper.getMainLooper());
        this.h = null;
        this.i = -1L;
        this.j = null;
        this.k = new Runnable() {
            @Override
            public final void run() {
                if (hj.this.b != null && !hj.this.b.isRecycled()) {
                    hj.this.setImageBitmap(hj.this.b);
                }
            }
        };
        this.l = new Runnable() {
            @Override
            public final void run() {
                hj.this.b = null;
                hj.this.a = null;
                hj.this.g = null;
                hj.this.f = false;
            }
        };
    }
    
    private void d() {
        if (this.a.a != 0) {
            final hf a = this.a;
            boolean b;
            if (-1 >= a.c.c) {
                b = false;
            }
            else {
                a.a = -1;
                b = true;
            }
            if (b && !this.d) {
                this.e = true;
                this.e();
            }
        }
    }
    
    private void e() {
        int n;
        if ((this.d || this.e) && this.a != null && this.g == null) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            (this.g = new Thread(this)).start();
        }
    }
    
    public final void a() {
        this.d = true;
        this.e();
    }
    
    public final void a(final hh hh, final byte[] array) {
        try {
            this.a = new hf((hf.a)new hk(), hh, ByteBuffer.wrap(array));
            if (this.d) {
                this.e();
                return;
            }
        }
        catch (Exception ex) {
            this.a = null;
            return;
        }
        this.d();
    }
    
    public final void b() {
        this.d = false;
        if (this.g != null) {
            this.g.interrupt();
            this.g = null;
        }
    }
    
    public final void c() {
        this.d = false;
        this.e = false;
        this.f = true;
        this.b();
        this.c.post(this.l);
    }
    
    public final long getFramesDisplayDuration() {
        return this.i;
    }
    
    public final int getGifHeight() {
        return this.a.c.g;
    }
    
    public final int getGifWidth() {
        return this.a.c.f;
    }
    
    public final a getOnAnimationStop() {
        return this.j;
    }
    
    public final b getOnFrameAvailable() {
        return this.h;
    }
    
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c();
    }
    
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/tapjoy/internal/hj.d:Z
        //     4: ifne            14
        //     7: aload_0        
        //     8: getfield        com/tapjoy/internal/hj.e:Z
        //    11: ifeq            111
        //    14: aload_0        
        //    15: getfield        com/tapjoy/internal/hj.a:Lcom/tapjoy/internal/hf;
        //    18: astore          4
        //    20: aload           4
        //    22: getfield        com/tapjoy/internal/hf.c:Lcom/tapjoy/internal/hh;
        //    25: getfield        com/tapjoy/internal/hh.c:I
        //    28: ifgt            136
        //    31: iconst_0       
        //    32: istore_1       
        //    33: invokestatic    java/lang/System.nanoTime:()J
        //    36: lstore_2       
        //    37: aload_0        
        //    38: aload_0        
        //    39: getfield        com/tapjoy/internal/hj.a:Lcom/tapjoy/internal/hf;
        //    42: invokevirtual   com/tapjoy/internal/hf.a:()Landroid/graphics/Bitmap;
        //    45: putfield        com/tapjoy/internal/hj.b:Landroid/graphics/Bitmap;
        //    48: aload_0        
        //    49: getfield        com/tapjoy/internal/hj.h:Lcom/tapjoy/internal/hj$b;
        //    52: ifnull          68
        //    55: aload_0        
        //    56: aload_0        
        //    57: getfield        com/tapjoy/internal/hj.h:Lcom/tapjoy/internal/hj$b;
        //    60: invokeinterface com/tapjoy/internal/hj$b.a:()Landroid/graphics/Bitmap;
        //    65: putfield        com/tapjoy/internal/hj.b:Landroid/graphics/Bitmap;
        //    68: invokestatic    java/lang/System.nanoTime:()J
        //    71: lload_2        
        //    72: lsub           
        //    73: ldc2_w          1000000
        //    76: ldiv           
        //    77: lstore_2       
        //    78: aload_0        
        //    79: getfield        com/tapjoy/internal/hj.c:Landroid/os/Handler;
        //    82: aload_0        
        //    83: getfield        com/tapjoy/internal/hj.k:Ljava/lang/Runnable;
        //    86: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //    89: pop            
        //    90: aload_0        
        //    91: iconst_0       
        //    92: putfield        com/tapjoy/internal/hj.e:Z
        //    95: aload_0        
        //    96: getfield        com/tapjoy/internal/hj.d:Z
        //    99: ifeq            106
        //   102: iload_1        
        //   103: ifne            239
        //   106: aload_0        
        //   107: iconst_0       
        //   108: putfield        com/tapjoy/internal/hj.d:Z
        //   111: aload_0        
        //   112: getfield        com/tapjoy/internal/hj.f:Z
        //   115: ifeq            130
        //   118: aload_0        
        //   119: getfield        com/tapjoy/internal/hj.c:Landroid/os/Handler;
        //   122: aload_0        
        //   123: getfield        com/tapjoy/internal/hj.l:Ljava/lang/Runnable;
        //   126: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   129: pop            
        //   130: aload_0        
        //   131: aconst_null    
        //   132: putfield        com/tapjoy/internal/hj.g:Ljava/lang/Thread;
        //   135: return         
        //   136: aload           4
        //   138: getfield        com/tapjoy/internal/hf.a:I
        //   141: aload           4
        //   143: getfield        com/tapjoy/internal/hf.c:Lcom/tapjoy/internal/hh;
        //   146: getfield        com/tapjoy/internal/hh.c:I
        //   149: iconst_1       
        //   150: isub           
        //   151: if_icmpne       166
        //   154: aload           4
        //   156: aload           4
        //   158: getfield        com/tapjoy/internal/hf.b:I
        //   161: iconst_1       
        //   162: iadd           
        //   163: putfield        com/tapjoy/internal/hf.b:I
        //   166: aload           4
        //   168: getfield        com/tapjoy/internal/hf.c:Lcom/tapjoy/internal/hh;
        //   171: getfield        com/tapjoy/internal/hh.m:I
        //   174: iconst_m1      
        //   175: if_icmpeq       199
        //   178: aload           4
        //   180: getfield        com/tapjoy/internal/hf.b:I
        //   183: aload           4
        //   185: getfield        com/tapjoy/internal/hf.c:Lcom/tapjoy/internal/hh;
        //   188: getfield        com/tapjoy/internal/hh.m:I
        //   191: if_icmple       199
        //   194: iconst_0       
        //   195: istore_1       
        //   196: goto            33
        //   199: aload           4
        //   201: aload           4
        //   203: getfield        com/tapjoy/internal/hf.a:I
        //   206: iconst_1       
        //   207: iadd           
        //   208: aload           4
        //   210: getfield        com/tapjoy/internal/hf.c:Lcom/tapjoy/internal/hh;
        //   213: getfield        com/tapjoy/internal/hh.c:I
        //   216: irem           
        //   217: putfield        com/tapjoy/internal/hf.a:I
        //   220: iconst_1       
        //   221: istore_1       
        //   222: goto            33
        //   225: astore          4
        //   227: lconst_0       
        //   228: lstore_2       
        //   229: goto            90
        //   232: astore          4
        //   234: lconst_0       
        //   235: lstore_2       
        //   236: goto            90
        //   239: aload_0        
        //   240: getfield        com/tapjoy/internal/hj.a:Lcom/tapjoy/internal/hf;
        //   243: astore          4
        //   245: aload           4
        //   247: getfield        com/tapjoy/internal/hf.c:Lcom/tapjoy/internal/hh;
        //   250: getfield        com/tapjoy/internal/hh.c:I
        //   253: ifle            377
        //   256: aload           4
        //   258: getfield        com/tapjoy/internal/hf.a:I
        //   261: ifge            305
        //   264: goto            377
        //   267: iload_1        
        //   268: i2l            
        //   269: lload_2        
        //   270: lsub           
        //   271: l2i            
        //   272: istore_1       
        //   273: iload_1        
        //   274: ifle            295
        //   277: aload_0        
        //   278: getfield        com/tapjoy/internal/hj.i:J
        //   281: lconst_0       
        //   282: lcmp           
        //   283: ifle            351
        //   286: aload_0        
        //   287: getfield        com/tapjoy/internal/hj.i:J
        //   290: lstore_2       
        //   291: lload_2        
        //   292: invokestatic    java/lang/Thread.sleep:(J)V
        //   295: aload_0        
        //   296: getfield        com/tapjoy/internal/hj.d:Z
        //   299: ifne            0
        //   302: goto            111
        //   305: aload           4
        //   307: getfield        com/tapjoy/internal/hf.a:I
        //   310: istore_1       
        //   311: iload_1        
        //   312: iflt            372
        //   315: iload_1        
        //   316: aload           4
        //   318: getfield        com/tapjoy/internal/hf.c:Lcom/tapjoy/internal/hh;
        //   321: getfield        com/tapjoy/internal/hh.c:I
        //   324: if_icmpge       372
        //   327: aload           4
        //   329: getfield        com/tapjoy/internal/hf.c:Lcom/tapjoy/internal/hh;
        //   332: getfield        com/tapjoy/internal/hh.e:Ljava/util/List;
        //   335: iload_1        
        //   336: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   341: checkcast       Lcom/tapjoy/internal/hg;
        //   344: getfield        com/tapjoy/internal/hg.i:I
        //   347: istore_1       
        //   348: goto            267
        //   351: iload_1        
        //   352: i2l            
        //   353: lstore_2       
        //   354: goto            291
        //   357: astore          4
        //   359: goto            295
        //   362: astore          4
        //   364: goto            236
        //   367: astore          4
        //   369: goto            229
        //   372: iconst_m1      
        //   373: istore_1       
        //   374: goto            267
        //   377: iconst_0       
        //   378: istore_1       
        //   379: goto            267
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  33     68     225    229    Ljava/lang/ArrayIndexOutOfBoundsException;
        //  33     68     232    236    Ljava/lang/IllegalArgumentException;
        //  68     78     225    229    Ljava/lang/ArrayIndexOutOfBoundsException;
        //  68     78     232    236    Ljava/lang/IllegalArgumentException;
        //  78     90     367    372    Ljava/lang/ArrayIndexOutOfBoundsException;
        //  78     90     362    367    Ljava/lang/IllegalArgumentException;
        //  239    264    357    362    Ljava/lang/InterruptedException;
        //  277    291    357    362    Ljava/lang/InterruptedException;
        //  291    295    357    362    Ljava/lang/InterruptedException;
        //  305    311    357    362    Ljava/lang/InterruptedException;
        //  315    348    357    362    Ljava/lang/InterruptedException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0090:
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
    
    public final void setBytes(final byte[] array) {
        this.a = new hf();
        try {
            this.a.a(array);
            if (this.d) {
                this.e();
                return;
            }
        }
        catch (Exception ex) {
            this.a = null;
            return;
        }
        this.d();
    }
    
    public final void setFramesDisplayDuration(final long i) {
        this.i = i;
    }
    
    public final void setOnAnimationStop(final a j) {
        this.j = j;
    }
    
    public final void setOnFrameAvailable(final b h) {
        this.h = h;
    }
    
    public interface a
    {
    }
    
    public interface b
    {
        Bitmap a();
    }
}
