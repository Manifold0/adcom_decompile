// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.c;

import android.graphics.drawable.Drawable;
import com.facebook.ads.internal.w.b.x;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import com.facebook.ads.internal.w.b.p;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.content.Context;
import java.lang.ref.WeakReference;
import android.graphics.Bitmap;
import android.os.AsyncTask;

public class d extends AsyncTask<String, Void, Bitmap[]>
{
    private static final String b;
    public boolean a;
    private final WeakReference<Context> c;
    private final int d;
    @Nullable
    private final WeakReference<ImageView> e;
    @Nullable
    private final WeakReference<b> f;
    @Nullable
    private final WeakReference<ViewGroup> g;
    private e h;
    private int i;
    private int j;
    
    static {
        b = d.class.getSimpleName();
    }
    
    public d(final ViewGroup viewGroup, final int d) {
        this.a = false;
        this.i = -1;
        this.j = -1;
        this.c = new WeakReference<Context>(viewGroup.getContext());
        this.f = null;
        this.e = null;
        this.g = new WeakReference<ViewGroup>(viewGroup);
        this.d = d;
    }
    
    public d(final ImageView imageView) {
        this.a = false;
        this.i = -1;
        this.j = -1;
        this.c = new WeakReference<Context>(imageView.getContext());
        this.f = null;
        this.e = new WeakReference<ImageView>(imageView);
        this.g = null;
        this.d = 0;
    }
    
    public d(final b b) {
        this.a = false;
        this.i = -1;
        this.j = -1;
        this.c = new WeakReference<Context>(b.getContext());
        this.f = new WeakReference<b>(b);
        this.e = null;
        this.g = null;
        this.d = 0;
    }
    
    public d a() {
        this.i = -1;
        this.j = -1;
        return this;
    }
    
    public d a(final int i, final int j) {
        this.i = i;
        this.j = j;
        return this;
    }
    
    public d a(final e h) {
        this.h = h;
        return this;
    }
    
    public d a(final boolean a) {
        this.a = a;
        return this;
    }
    
    public void a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            if (this.h != null) {
                this.h.a(false);
            }
            return;
        }
        this.executeOnExecutor(p.a, (Object[])new String[] { s });
    }
    
    protected void a(final Bitmap[] array) {
        if (this.e != null) {
            final ImageView imageView = this.e.get();
            if (imageView != null) {
                imageView.setImageBitmap(array[0]);
            }
        }
        if (this.f != null) {
            final b b = this.f.get();
            if (b != null) {
                b.a(array[0], array[1]);
            }
        }
        if (this.g != null && this.g.get() != null && array[1] != null) {
            x.a(this.g.get(), (Drawable)new BitmapDrawable(this.c.get().getResources(), array[1]));
        }
        if (this.h != null) {
            this.h.a(array[0] != null);
        }
    }
    
    protected Bitmap[] a(final String... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iconst_0       
        //     2: aaload         
        //     3: astore          6
        //     5: aload_0        
        //     6: getfield        com/facebook/ads/internal/view/c/d.c:Ljava/lang/ref/WeakReference;
        //     9: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //    12: checkcast       Landroid/content/Context;
        //    15: astore_1       
        //    16: aload_1        
        //    17: ifnonnull       33
        //    20: iconst_2       
        //    21: anewarray       Landroid/graphics/Bitmap;
        //    24: dup            
        //    25: iconst_0       
        //    26: aconst_null    
        //    27: aastore        
        //    28: dup            
        //    29: iconst_1       
        //    30: aconst_null    
        //    31: aastore        
        //    32: areturn        
        //    33: aload_1        
        //    34: invokestatic    com/facebook/ads/internal/h/d.a:(Landroid/content/Context;)Lcom/facebook/ads/internal/h/d;
        //    37: aload           6
        //    39: aload_0        
        //    40: getfield        com/facebook/ads/internal/view/c/d.i:I
        //    43: aload_0        
        //    44: getfield        com/facebook/ads/internal/view/c/d.j:I
        //    47: invokevirtual   com/facebook/ads/internal/h/d.a:(Ljava/lang/String;II)Landroid/graphics/Bitmap;
        //    50: astore          4
        //    52: aload_0        
        //    53: getfield        com/facebook/ads/internal/view/c/d.f:Ljava/lang/ref/WeakReference;
        //    56: ifnull          152
        //    59: aload_0        
        //    60: getfield        com/facebook/ads/internal/view/c/d.f:Ljava/lang/ref/WeakReference;
        //    63: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //    66: ifnull          152
        //    69: iconst_1       
        //    70: istore_2       
        //    71: aload_0        
        //    72: getfield        com/facebook/ads/internal/view/c/d.g:Ljava/lang/ref/WeakReference;
        //    75: ifnull          157
        //    78: aload_0        
        //    79: getfield        com/facebook/ads/internal/view/c/d.g:Ljava/lang/ref/WeakReference;
        //    82: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //    85: ifnull          157
        //    88: iconst_1       
        //    89: istore_3       
        //    90: goto            245
        //    93: aload           4
        //    95: ifnull          240
        //    98: aload_0        
        //    99: getfield        com/facebook/ads/internal/view/c/d.a:Z
        //   102: ifne            240
        //   105: new             Lcom/facebook/ads/internal/w/c/f;
        //   108: dup            
        //   109: aload           4
        //   111: invokespecial   com/facebook/ads/internal/w/c/f.<init>:(Landroid/graphics/Bitmap;)V
        //   114: astore_1       
        //   115: aload_0        
        //   116: getfield        com/facebook/ads/internal/view/c/d.d:I
        //   119: ifeq            162
        //   122: aload_0        
        //   123: getfield        com/facebook/ads/internal/view/c/d.d:I
        //   126: istore_2       
        //   127: aload_1        
        //   128: iload_2        
        //   129: invokevirtual   com/facebook/ads/internal/w/c/f.a:(I)Landroid/graphics/Bitmap;
        //   132: pop            
        //   133: aload_1        
        //   134: invokevirtual   com/facebook/ads/internal/w/c/f.a:()Landroid/graphics/Bitmap;
        //   137: astore_1       
        //   138: iconst_2       
        //   139: anewarray       Landroid/graphics/Bitmap;
        //   142: dup            
        //   143: iconst_0       
        //   144: aload           4
        //   146: aastore        
        //   147: dup            
        //   148: iconst_1       
        //   149: aload_1        
        //   150: aastore        
        //   151: areturn        
        //   152: iconst_0       
        //   153: istore_2       
        //   154: goto            71
        //   157: iconst_0       
        //   158: istore_3       
        //   159: goto            245
        //   162: aload           4
        //   164: invokevirtual   android/graphics/Bitmap.getWidth:()I
        //   167: i2f            
        //   168: ldc             40.0
        //   170: fdiv           
        //   171: invokestatic    java/lang/Math.round:(F)I
        //   174: istore_2       
        //   175: goto            127
        //   178: astore          4
        //   180: aconst_null    
        //   181: astore_1       
        //   182: getstatic       com/facebook/ads/internal/view/c/d.b:Ljava/lang/String;
        //   185: new             Ljava/lang/StringBuilder;
        //   188: dup            
        //   189: invokespecial   java/lang/StringBuilder.<init>:()V
        //   192: ldc             "Error downloading image: "
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: aload           6
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   205: aload           4
        //   207: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   210: pop            
        //   211: aload           4
        //   213: aconst_null    
        //   214: invokestatic    com/facebook/ads/internal/o/a.a:(Ljava/lang/Throwable;Ljava/lang/String;)Lcom/facebook/ads/internal/o/a;
        //   217: invokestatic    com/facebook/ads/internal/o/b.a:(Lcom/facebook/ads/internal/o/a;)V
        //   220: aload_1        
        //   221: astore          4
        //   223: aconst_null    
        //   224: astore_1       
        //   225: goto            138
        //   228: astore          5
        //   230: aload           4
        //   232: astore_1       
        //   233: aload           5
        //   235: astore          4
        //   237: goto            182
        //   240: aconst_null    
        //   241: astore_1       
        //   242: goto            138
        //   245: iload_2        
        //   246: ifne            93
        //   249: iload_3        
        //   250: ifeq            240
        //   253: goto            93
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  33     52     178    182    Ljava/lang/Throwable;
        //  52     69     228    240    Ljava/lang/Throwable;
        //  71     88     228    240    Ljava/lang/Throwable;
        //  98     127    228    240    Ljava/lang/Throwable;
        //  127    138    228    240    Ljava/lang/Throwable;
        //  162    175    228    240    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
