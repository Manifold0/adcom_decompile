// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.HandlerThread;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.view.WindowManager;
import android.content.Context;
import android.os.Handler;
import javax.annotation.concurrent.GuardedBy;
import android.view.Display;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;

@zzadh
final class zzapr implements SensorEventListener
{
    private final SensorManager zzcyn;
    private final Object zzcyo;
    private final Display zzcyp;
    private final float[] zzcyq;
    private final float[] zzcyr;
    @GuardedBy("mSensorThreadLock")
    private float[] zzcys;
    private Handler zzcyt;
    private zzapt zzcyu;
    
    zzapr(final Context context) {
        this.zzcyn = (SensorManager)context.getSystemService("sensor");
        this.zzcyp = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        this.zzcyq = new float[9];
        this.zzcyr = new float[9];
        this.zzcyo = new Object();
    }
    
    private final void zzg(final int n, final int n2) {
        final float n3 = this.zzcyr[n];
        this.zzcyr[n] = this.zzcyr[n2];
        this.zzcyr[n2] = n3;
    }
    
    public final void onAccuracyChanged(final Sensor sensor, final int n) {
    }
    
    public final void onSensorChanged(final SensorEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        android/hardware/SensorEvent.values:[F
        //     4: astore_2       
        //     5: aload_2        
        //     6: iconst_0       
        //     7: faload         
        //     8: fconst_0       
        //     9: fcmpl          
        //    10: ifne            29
        //    13: aload_2        
        //    14: iconst_1       
        //    15: faload         
        //    16: fconst_0       
        //    17: fcmpl          
        //    18: ifne            29
        //    21: aload_2        
        //    22: iconst_2       
        //    23: faload         
        //    24: fconst_0       
        //    25: fcmpl          
        //    26: ifeq            171
        //    29: aload_0        
        //    30: getfield        com/google/android/gms/internal/ads/zzapr.zzcyo:Ljava/lang/Object;
        //    33: astore_1       
        //    34: aload_1        
        //    35: monitorenter   
        //    36: aload_0        
        //    37: getfield        com/google/android/gms/internal/ads/zzapr.zzcys:[F
        //    40: ifnonnull       51
        //    43: aload_0        
        //    44: bipush          9
        //    46: newarray        F
        //    48: putfield        com/google/android/gms/internal/ads/zzapr.zzcys:[F
        //    51: aload_1        
        //    52: monitorexit    
        //    53: aload_0        
        //    54: getfield        com/google/android/gms/internal/ads/zzapr.zzcyq:[F
        //    57: aload_2        
        //    58: invokestatic    android/hardware/SensorManager.getRotationMatrixFromVector:([F[F)V
        //    61: aload_0        
        //    62: getfield        com/google/android/gms/internal/ads/zzapr.zzcyp:Landroid/view/Display;
        //    65: invokevirtual   android/view/Display.getRotation:()I
        //    68: tableswitch {
        //                2: 177
        //                3: 196
        //                4: 217
        //          default: 96
        //        }
        //    96: aload_0        
        //    97: getfield        com/google/android/gms/internal/ads/zzapr.zzcyq:[F
        //   100: iconst_0       
        //   101: aload_0        
        //   102: getfield        com/google/android/gms/internal/ads/zzapr.zzcyr:[F
        //   105: iconst_0       
        //   106: bipush          9
        //   108: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   111: aload_0        
        //   112: iconst_1       
        //   113: iconst_3       
        //   114: invokespecial   com/google/android/gms/internal/ads/zzapr.zzg:(II)V
        //   117: aload_0        
        //   118: iconst_2       
        //   119: bipush          6
        //   121: invokespecial   com/google/android/gms/internal/ads/zzapr.zzg:(II)V
        //   124: aload_0        
        //   125: iconst_5       
        //   126: bipush          7
        //   128: invokespecial   com/google/android/gms/internal/ads/zzapr.zzg:(II)V
        //   131: aload_0        
        //   132: getfield        com/google/android/gms/internal/ads/zzapr.zzcyo:Ljava/lang/Object;
        //   135: astore_1       
        //   136: aload_1        
        //   137: monitorenter   
        //   138: aload_0        
        //   139: getfield        com/google/android/gms/internal/ads/zzapr.zzcyr:[F
        //   142: iconst_0       
        //   143: aload_0        
        //   144: getfield        com/google/android/gms/internal/ads/zzapr.zzcys:[F
        //   147: iconst_0       
        //   148: bipush          9
        //   150: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   153: aload_1        
        //   154: monitorexit    
        //   155: aload_0        
        //   156: getfield        com/google/android/gms/internal/ads/zzapr.zzcyu:Lcom/google/android/gms/internal/ads/zzapt;
        //   159: ifnull          171
        //   162: aload_0        
        //   163: getfield        com/google/android/gms/internal/ads/zzapr.zzcyu:Lcom/google/android/gms/internal/ads/zzapt;
        //   166: invokeinterface com/google/android/gms/internal/ads/zzapt.zznn:()V
        //   171: return         
        //   172: astore_2       
        //   173: aload_1        
        //   174: monitorexit    
        //   175: aload_2        
        //   176: athrow         
        //   177: aload_0        
        //   178: getfield        com/google/android/gms/internal/ads/zzapr.zzcyq:[F
        //   181: iconst_2       
        //   182: sipush          129
        //   185: aload_0        
        //   186: getfield        com/google/android/gms/internal/ads/zzapr.zzcyr:[F
        //   189: invokestatic    android/hardware/SensorManager.remapCoordinateSystem:([FII[F)Z
        //   192: pop            
        //   193: goto            111
        //   196: aload_0        
        //   197: getfield        com/google/android/gms/internal/ads/zzapr.zzcyq:[F
        //   200: sipush          129
        //   203: sipush          130
        //   206: aload_0        
        //   207: getfield        com/google/android/gms/internal/ads/zzapr.zzcyr:[F
        //   210: invokestatic    android/hardware/SensorManager.remapCoordinateSystem:([FII[F)Z
        //   213: pop            
        //   214: goto            111
        //   217: aload_0        
        //   218: getfield        com/google/android/gms/internal/ads/zzapr.zzcyq:[F
        //   221: sipush          130
        //   224: iconst_1       
        //   225: aload_0        
        //   226: getfield        com/google/android/gms/internal/ads/zzapr.zzcyr:[F
        //   229: invokestatic    android/hardware/SensorManager.remapCoordinateSystem:([FII[F)Z
        //   232: pop            
        //   233: goto            111
        //   236: astore_2       
        //   237: aload_1        
        //   238: monitorexit    
        //   239: aload_2        
        //   240: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  36     51     172    177    Any
        //  51     53     172    177    Any
        //  138    155    236    241    Any
        //  173    175    172    177    Any
        //  237    239    236    241    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0171:
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
    
    final void start() {
        if (this.zzcyt == null) {
            final Sensor defaultSensor = this.zzcyn.getDefaultSensor(11);
            if (defaultSensor == null) {
                zzakb.e("No Sensor of TYPE_ROTATION_VECTOR");
                return;
            }
            final HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
            handlerThread.start();
            this.zzcyt = new Handler(handlerThread.getLooper());
            if (!this.zzcyn.registerListener((SensorEventListener)this, defaultSensor, 0, this.zzcyt)) {
                zzakb.e("SensorManager.registerListener failed.");
                this.stop();
            }
        }
    }
    
    final void stop() {
        if (this.zzcyt == null) {
            return;
        }
        this.zzcyn.unregisterListener((SensorEventListener)this);
        this.zzcyt.post((Runnable)new zzaps(this));
        this.zzcyt = null;
    }
    
    final void zza(final zzapt zzcyu) {
        this.zzcyu = zzcyu;
    }
    
    final boolean zza(final float[] array) {
        synchronized (this.zzcyo) {
            if (this.zzcys == null) {
                return false;
            }
            System.arraycopy(this.zzcys, 0, array, 0, this.zzcys.length);
            return true;
        }
    }
}
