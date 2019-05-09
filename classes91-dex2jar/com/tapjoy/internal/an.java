// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.view.MotionEvent;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Matrix;

public class an extends ao
{
    private int a;
    private final Matrix b;
    private final float[] c;
    
    public an(final Context context) {
        super(context);
        this.a = 0;
        this.b = new Matrix();
        this.c = new float[2];
    }
    
    protected void dispatchDraw(final Canvas p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/tapjoy/internal/an.a:I
        //     4: ifne            13
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokespecial   com/tapjoy/internal/ao.dispatchDraw:(Landroid/graphics/Canvas;)V
        //    12: return         
        //    13: aload_1        
        //    14: invokevirtual   android/graphics/Canvas.save:()I
        //    17: pop            
        //    18: aload_0        
        //    19: invokevirtual   com/tapjoy/internal/an.getWidth:()I
        //    22: istore_2       
        //    23: aload_0        
        //    24: invokevirtual   com/tapjoy/internal/an.getHeight:()I
        //    27: istore_3       
        //    28: aload_1        
        //    29: fconst_0       
        //    30: fconst_0       
        //    31: iload_2        
        //    32: i2f            
        //    33: iload_3        
        //    34: i2f            
        //    35: getstatic       android/graphics/Region$Op.REPLACE:Landroid/graphics/Region$Op;
        //    38: invokevirtual   android/graphics/Canvas.clipRect:(FFFFLandroid/graphics/Region$Op;)Z
        //    41: pop            
        //    42: aload_0        
        //    43: invokevirtual   com/tapjoy/internal/an.getParent:()Landroid/view/ViewParent;
        //    46: checkcast       Landroid/view/ViewGroup;
        //    49: astore          7
        //    51: aload           7
        //    53: invokevirtual   android/view/View.getParent:()Landroid/view/ViewParent;
        //    56: checkcast       Landroid/view/ViewGroup;
        //    59: astore          8
        //    61: aload           8
        //    63: instanceof      Landroid/widget/ScrollView;
        //    66: ifne            81
        //    69: aload           8
        //    71: instanceof      Landroid/widget/HorizontalScrollView;
        //    74: istore          6
        //    76: iload           6
        //    78: ifeq            85
        //    81: aload           8
        //    83: astore          7
        //    85: aload_0        
        //    86: invokevirtual   com/tapjoy/internal/an.getLeft:()I
        //    89: aload           7
        //    91: invokevirtual   android/view/View.getScrollX:()I
        //    94: isub           
        //    95: istore          4
        //    97: aload_0        
        //    98: invokevirtual   com/tapjoy/internal/an.getTop:()I
        //   101: aload           7
        //   103: invokevirtual   android/view/View.getScrollY:()I
        //   106: isub           
        //   107: istore          5
        //   109: aload_1        
        //   110: iconst_0       
        //   111: iload           4
        //   113: isub           
        //   114: i2f            
        //   115: iconst_0       
        //   116: iload           5
        //   118: isub           
        //   119: i2f            
        //   120: aload           7
        //   122: invokevirtual   android/view/View.getWidth:()I
        //   125: iload           4
        //   127: isub           
        //   128: i2f            
        //   129: aload           7
        //   131: invokevirtual   android/view/View.getHeight:()I
        //   134: iload           5
        //   136: isub           
        //   137: i2f            
        //   138: getstatic       android/graphics/Region$Op.INTERSECT:Landroid/graphics/Region$Op;
        //   141: invokevirtual   android/graphics/Canvas.clipRect:(FFFFLandroid/graphics/Region$Op;)Z
        //   144: pop            
        //   145: aload_1        
        //   146: aload_0        
        //   147: getfield        com/tapjoy/internal/an.a:I
        //   150: bipush          90
        //   152: imul           
        //   153: i2f            
        //   154: invokevirtual   android/graphics/Canvas.rotate:(F)V
        //   157: aload_0        
        //   158: getfield        com/tapjoy/internal/an.a:I
        //   161: tableswitch {
        //                2: 196
        //                3: 256
        //                4: 269
        //          default: 188
        //        }
        //   188: new             Ljava/lang/IllegalStateException;
        //   191: dup            
        //   192: invokespecial   java/lang/IllegalStateException.<init>:()V
        //   195: athrow         
        //   196: aload_1        
        //   197: fconst_0       
        //   198: iload_2        
        //   199: ineg           
        //   200: i2f            
        //   201: invokevirtual   android/graphics/Canvas.translate:(FF)V
        //   204: aload_0        
        //   205: getfield        com/tapjoy/internal/an.b:Landroid/graphics/Matrix;
        //   208: aload_0        
        //   209: getfield        com/tapjoy/internal/an.a:I
        //   212: bipush          -90
        //   214: imul           
        //   215: i2f            
        //   216: invokevirtual   android/graphics/Matrix.setRotate:(F)V
        //   219: aload_0        
        //   220: getfield        com/tapjoy/internal/an.a:I
        //   223: tableswitch {
        //                2: 280
        //                3: 303
        //                4: 322
        //          default: 248
        //        }
        //   248: new             Ljava/lang/IllegalStateException;
        //   251: dup            
        //   252: invokespecial   java/lang/IllegalStateException.<init>:()V
        //   255: athrow         
        //   256: aload_1        
        //   257: iload_2        
        //   258: ineg           
        //   259: i2f            
        //   260: iload_3        
        //   261: ineg           
        //   262: i2f            
        //   263: invokevirtual   android/graphics/Canvas.translate:(FF)V
        //   266: goto            204
        //   269: aload_1        
        //   270: iload_3        
        //   271: ineg           
        //   272: i2f            
        //   273: fconst_0       
        //   274: invokevirtual   android/graphics/Canvas.translate:(FF)V
        //   277: goto            204
        //   280: aload_0        
        //   281: getfield        com/tapjoy/internal/an.b:Landroid/graphics/Matrix;
        //   284: fconst_0       
        //   285: iload_2        
        //   286: iconst_1       
        //   287: isub           
        //   288: i2f            
        //   289: invokevirtual   android/graphics/Matrix.postTranslate:(FF)Z
        //   292: pop            
        //   293: aload_0        
        //   294: aload_1        
        //   295: invokespecial   com/tapjoy/internal/ao.dispatchDraw:(Landroid/graphics/Canvas;)V
        //   298: aload_1        
        //   299: invokevirtual   android/graphics/Canvas.restore:()V
        //   302: return         
        //   303: aload_0        
        //   304: getfield        com/tapjoy/internal/an.b:Landroid/graphics/Matrix;
        //   307: iload_2        
        //   308: iconst_1       
        //   309: isub           
        //   310: i2f            
        //   311: iload_3        
        //   312: iconst_1       
        //   313: isub           
        //   314: i2f            
        //   315: invokevirtual   android/graphics/Matrix.postTranslate:(FF)Z
        //   318: pop            
        //   319: goto            293
        //   322: aload_0        
        //   323: getfield        com/tapjoy/internal/an.b:Landroid/graphics/Matrix;
        //   326: iload_3        
        //   327: iconst_1       
        //   328: isub           
        //   329: i2f            
        //   330: fconst_0       
        //   331: invokevirtual   android/graphics/Matrix.postTranslate:(FF)Z
        //   334: pop            
        //   335: goto            293
        //   338: astore          7
        //   340: goto            145
        //   343: astore          8
        //   345: goto            85
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  42     51     338    343    Ljava/lang/Exception;
        //  51     76     343    348    Ljava/lang/Exception;
        //  85     145    338    343    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0081:
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
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        if (this.a == 0) {
            return super.dispatchTouchEvent(motionEvent);
        }
        final float[] c = this.c;
        c[0] = motionEvent.getX();
        c[1] = motionEvent.getY();
        this.b.mapPoints(c);
        motionEvent.setLocation(c[0], c[1]);
        return super.dispatchTouchEvent(motionEvent);
    }
    
    public int getRotationCount() {
        return this.a;
    }
    
    @Override
    public void onMeasure(final int n, final int n2) {
        if (this.a % 2 == 0) {
            super.onMeasure(n, n2);
            return;
        }
        super.onMeasure(n2, n);
        this.setMeasuredDimension(this.getMeasuredHeight(), this.getMeasuredWidth());
    }
    
    public void setRotationCount(final int n) {
        this.a = (n & 0x3);
    }
}
