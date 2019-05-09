// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import android.widget.RelativeLayout;
import android.os.Bundle;
import com.chartboost.sdk.Model.CBError;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import android.view.SurfaceView;
import com.chartboost.sdk.Libraries.CBLogging;
import android.annotation.TargetApi;
import com.chartboost.sdk.impl.s;
import android.os.Handler;
import com.chartboost.sdk.Tracking.a;
import android.annotation.SuppressLint;
import android.app.Activity;

@SuppressLint({ "Registered" })
public class CBImpressionActivity extends Activity
{
    final a a;
    final Handler b;
    final c c;
    private Activity d;
    
    public CBImpressionActivity() {
        a o;
        if (h.a() != null) {
            o = h.a().o;
        }
        else {
            o = null;
        }
        this.a = o;
        Handler p;
        if (h.a() != null) {
            p = h.a().p;
        }
        else {
            p = null;
        }
        this.b = p;
        c q;
        if (h.a() != null) {
            q = h.a().q;
        }
        else {
            q = null;
        }
        this.c = q;
        this.d = null;
    }
    
    @TargetApi(11)
    private void a() {
        if (s.a().a(11)) {
            this.getWindow().setFlags(16777216, 16777216);
        }
    }
    
    private void b() {
        if (!s.a().a(14)) {
            this.b.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        CBLogging.e("VideoInit", "preparing activity for video surface");
                        CBImpressionActivity.this.addContentView((View)new SurfaceView((Context)CBImpressionActivity.this), new ViewGroup$LayoutParams(0, 0));
                    }
                    catch (Exception ex) {
                        com.chartboost.sdk.Tracking.a.a(CBImpressionActivity.class, "postCreateSurfaceView Runnable.run", ex);
                    }
                }
            });
        }
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        if (this.d != null) {
            return this.d.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }
    
    public void forwardTouchEvents(final Activity d) {
        this.d = d;
    }
    
    public void onAttachedToWindow() {
        try {
            super.onAttachedToWindow();
            if (s.a().a(14) && this.getWindow() != null && this.getWindow().getDecorView() != null && !this.getWindow().getDecorView().isHardwareAccelerated() && this.c != null) {
                CBLogging.b("CBImpressionActivity", "The activity passed down is not hardware accelerated, so Chartboost cannot show ads");
                final com.chartboost.sdk.Model.c d = this.c.d();
                if (d != null) {
                    d.a(CBError.CBImpressionError.HARDWARE_ACCELERATION_DISABLED);
                }
                this.finish();
            }
        }
        catch (Exception ex) {
            com.chartboost.sdk.Tracking.a.a(this.getClass(), "onAttachedToWindow", ex);
        }
    }
    
    public void onBackPressed() {
        try {
            if (this.c == null || !this.c.k()) {
                super.onBackPressed();
            }
        }
        catch (Exception ex) {
            com.chartboost.sdk.Tracking.a.a(this.getClass(), "onBackPressed", ex);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if ((this.getIntent() != null && !this.getIntent().getBooleanExtra("isChartboost", false)) || this.a == null || this.b == null || this.c == null) {
            CBLogging.b("CBImpressionActivity", "This activity cannot be called from outside chartboost SDK");
            this.finish();
            return;
        }
        this.a();
        this.requestWindowFeature(1);
        this.getWindow().setWindowAnimations(0);
        this.c.a(this);
        this.setContentView((View)new RelativeLayout((Context)this));
        this.b();
        CBLogging.a("CBImpressionActivity", "Impression Activity onCreate() called");
    }
    
    protected void onDestroy() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/chartboost/sdk/CBImpressionActivity.c:Lcom/chartboost/sdk/c;
        //     4: ifnull          21
        //     7: getstatic       com/chartboost/sdk/i.s:Z
        //    10: ifne            21
        //    13: aload_0        
        //    14: getfield        com/chartboost/sdk/CBImpressionActivity.c:Lcom/chartboost/sdk/c;
        //    17: aload_0        
        //    18: invokevirtual   com/chartboost/sdk/c.k:(Landroid/app/Activity;)V
        //    21: aload_0        
        //    22: invokespecial   android/app/Activity.onDestroy:()V
        //    25: return         
        //    26: astore_1       
        //    27: aload_0        
        //    28: invokespecial   android/app/Activity.onDestroy:()V
        //    31: aload_1        
        //    32: athrow         
        //    33: astore_1       
        //    34: aload_0        
        //    35: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    38: ldc             "onDestroy"
        //    40: aload_1        
        //    41: invokestatic    com/chartboost/sdk/Tracking/a.a:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Exception;)V
        //    44: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      21     26     33     Any
        //  21     25     33     45     Ljava/lang/Exception;
        //  27     33     33     45     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    protected void onPause() {
        try {
            super.onPause();
            if (this.c != null && !i.s) {
                this.c.a((Activity)this);
                this.c.i();
            }
        }
        catch (Exception ex) {
            com.chartboost.sdk.Tracking.a.a(this.getClass(), "onPause", ex);
        }
    }
    
    protected void onResume() {
        while (true) {
            try {
                super.onResume();
                if (this.c != null && !i.s) {
                    this.c.a((Activity)this);
                    this.c.h();
                }
                Chartboost.setActivityAttrs(this);
            }
            catch (Exception ex) {
                com.chartboost.sdk.Tracking.a.a(this.getClass(), "onResume", ex);
                continue;
            }
            break;
        }
    }
    
    protected void onStart() {
        try {
            super.onStart();
            if (this.c != null && !i.s) {
                this.c.e(this);
            }
        }
        catch (Exception ex) {
            com.chartboost.sdk.Tracking.a.a(this.getClass(), "onStart", ex);
        }
    }
    
    protected void onStop() {
        try {
            super.onStop();
            if (this.c != null && !i.s) {
                this.c.i(this);
            }
        }
        catch (Exception ex) {
            com.chartboost.sdk.Tracking.a.a(this.getClass(), "onStop", ex);
        }
    }
}
