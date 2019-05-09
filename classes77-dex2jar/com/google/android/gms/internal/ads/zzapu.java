// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.opengl.GLUtils;
import java.nio.Buffer;
import javax.microedition.khronos.egl.EGLConfig;
import com.google.android.gms.common.util.VisibleForTesting;
import android.util.Log;
import android.opengl.GLES20;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import android.content.Context;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGL10;
import java.util.concurrent.CountDownLatch;
import java.nio.FloatBuffer;
import android.graphics.SurfaceTexture;
import android.annotation.TargetApi;
import android.graphics.SurfaceTexture$OnFrameAvailableListener;

@zzadh
@TargetApi(14)
public final class zzapu extends Thread implements SurfaceTexture$OnFrameAvailableListener, zzapt
{
    private static final float[] zzcyv;
    private final float[] zzcys;
    private final zzapr zzcyw;
    private final float[] zzcyx;
    private final float[] zzcyy;
    private final float[] zzcyz;
    private final float[] zzcza;
    private final float[] zzczb;
    private final float[] zzczc;
    private float zzczd;
    private float zzcze;
    private float zzczf;
    private SurfaceTexture zzczg;
    private SurfaceTexture zzczh;
    private int zzczi;
    private int zzczj;
    private int zzczk;
    private FloatBuffer zzczl;
    private final CountDownLatch zzczm;
    private final Object zzczn;
    private EGL10 zzczo;
    private EGLDisplay zzczp;
    private EGLContext zzczq;
    private EGLSurface zzczr;
    private volatile boolean zzczs;
    private volatile boolean zzczt;
    private int zzuq;
    private int zzur;
    
    static {
        zzcyv = new float[] { -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f };
    }
    
    public zzapu(final Context context) {
        super("SphericalVideoProcessor");
        this.zzczl = ByteBuffer.allocateDirect(zzapu.zzcyv.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.zzczl.put(zzapu.zzcyv).position(0);
        this.zzcys = new float[9];
        this.zzcyx = new float[9];
        this.zzcyy = new float[9];
        this.zzcyz = new float[9];
        this.zzcza = new float[9];
        this.zzczb = new float[9];
        this.zzczc = new float[9];
        this.zzczd = Float.NaN;
        (this.zzcyw = new zzapr(context)).zza(this);
        this.zzczm = new CountDownLatch(1);
        this.zzczn = new Object();
    }
    
    private static void zza(final float[] array, final float n) {
        array[0] = 1.0f;
        array[1] = 0.0f;
        array[3] = (array[2] = 0.0f);
        array[4] = (float)Math.cos(n);
        array[5] = (float)(-Math.sin(n));
        array[6] = 0.0f;
        array[7] = (float)Math.sin(n);
        array[8] = (float)Math.cos(n);
    }
    
    private static void zza(final float[] array, final float[] array2, final float[] array3) {
        array[0] = array2[0] * array3[0] + array2[1] * array3[3] + array2[2] * array3[6];
        array[1] = array2[0] * array3[1] + array2[1] * array3[4] + array2[2] * array3[7];
        array[2] = array2[0] * array3[2] + array2[1] * array3[5] + array2[2] * array3[8];
        array[3] = array2[3] * array3[0] + array2[4] * array3[3] + array2[5] * array3[6];
        array[4] = array2[3] * array3[1] + array2[4] * array3[4] + array2[5] * array3[7];
        array[5] = array2[3] * array3[2] + array2[4] * array3[5] + array2[5] * array3[8];
        array[6] = array2[6] * array3[0] + array2[7] * array3[3] + array2[8] * array3[6];
        array[7] = array2[6] * array3[1] + array2[7] * array3[4] + array2[8] * array3[7];
        array[8] = array2[6] * array3[2] + array2[7] * array3[5] + array2[8] * array3[8];
    }
    
    private static void zzb(final float[] array, final float n) {
        array[0] = (float)Math.cos(n);
        array[1] = (float)(-Math.sin(n));
        array[2] = 0.0f;
        array[3] = (float)Math.sin(n);
        array[4] = (float)Math.cos(n);
        array[5] = 0.0f;
        array[7] = (array[6] = 0.0f);
        array[8] = 1.0f;
    }
    
    private static int zzd(final int n, final String s) {
        final int glCreateShader = GLES20.glCreateShader(n);
        zzdo("createShader");
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, s);
            zzdo("shaderSource");
            GLES20.glCompileShader(glCreateShader);
            zzdo("compileShader");
            final int[] array = { 0 };
            GLES20.glGetShaderiv(glCreateShader, 35713, array, 0);
            zzdo("getShaderiv");
            if (array[0] == 0) {
                Log.e("SphericalVideoRenderer", new StringBuilder(37).append("Could not compile shader ").append(n).append(":").toString());
                Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
                GLES20.glDeleteShader(glCreateShader);
                zzdo("deleteShader");
                return 0;
            }
        }
        return glCreateShader;
    }
    
    private static void zzdo(final String s) {
        final int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("SphericalVideoRenderer", new StringBuilder(String.valueOf(s).length() + 21).append(s).append(": glError ").append(glGetError).toString());
        }
    }
    
    @VisibleForTesting
    private final boolean zztk() {
        int n2;
        final int n = n2 = 0;
        if (this.zzczr != null) {
            n2 = n;
            if (this.zzczr != EGL10.EGL_NO_SURFACE) {
                n2 = ((this.zzczo.eglMakeCurrent(this.zzczp, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | false | this.zzczo.eglDestroySurface(this.zzczp, this.zzczr)) ? 1 : 0);
                this.zzczr = null;
            }
        }
        int n3 = n2;
        if (this.zzczq != null) {
            n3 = (n2 | (this.zzczo.eglDestroyContext(this.zzczp, this.zzczq) ? 1 : 0));
            this.zzczq = null;
        }
        int n4 = n3;
        if (this.zzczp != null) {
            n4 = (n3 | (this.zzczo.eglTerminate(this.zzczp) ? 1 : 0));
            this.zzczp = null;
        }
        return n4 != 0;
    }
    
    public final void onFrameAvailable(final SurfaceTexture surfaceTexture) {
        ++this.zzczk;
        synchronized (this.zzczn) {
            this.zzczn.notifyAll();
        }
    }
    
    @Override
    public final void run() {
        final int n = 1;
        if (this.zzczh == null) {
            zzakb.e("SphericalVideoProcessor started with no output texture.");
            this.zzczm.countDown();
            return;
        }
        this.zzczo = (EGL10)EGLContext.getEGL();
        this.zzczp = this.zzczo.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        int glGetUniformLocation;
        if (this.zzczp == EGL10.EGL_NO_DISPLAY) {
            glGetUniformLocation = 0;
        }
        else if (!this.zzczo.eglInitialize(this.zzczp, new int[2])) {
            glGetUniformLocation = 0;
        }
        else {
            final int[] array = { 0 };
            final EGLConfig[] array2 = { null };
            EGLConfig eglConfig;
            if (this.zzczo.eglChooseConfig(this.zzczp, new int[] { 12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344 }, array2, 1, array) && array[0] > 0) {
                eglConfig = array2[0];
            }
            else {
                eglConfig = null;
            }
            if (eglConfig == null) {
                glGetUniformLocation = 0;
            }
            else {
                this.zzczq = this.zzczo.eglCreateContext(this.zzczp, eglConfig, EGL10.EGL_NO_CONTEXT, new int[] { 12440, 2, 12344 });
                if (this.zzczq == null || this.zzczq == EGL10.EGL_NO_CONTEXT) {
                    glGetUniformLocation = 0;
                }
                else {
                    this.zzczr = this.zzczo.eglCreateWindowSurface(this.zzczp, eglConfig, (Object)this.zzczh, (int[])null);
                    if (this.zzczr == null || this.zzczr == EGL10.EGL_NO_SURFACE) {
                        glGetUniformLocation = 0;
                    }
                    else if (!this.zzczo.eglMakeCurrent(this.zzczp, this.zzczr, this.zzczr, this.zzczq)) {
                        glGetUniformLocation = 0;
                    }
                    else {
                        glGetUniformLocation = 1;
                    }
                }
            }
        }
        final zzna zzazp = zznk.zzazp;
        String s;
        if (!((String)zzkb.zzik().zzd(zzazp)).equals(zzazp.zzja())) {
            s = (String)zzkb.zzik().zzd(zzazp);
        }
        else {
            s = "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}";
        }
        final int zzd = zzd(35633, s);
        int zzczi;
        if (zzd == 0) {
            zzczi = 0;
        }
        else {
            final zzna zzazq = zznk.zzazq;
            String s2;
            if (!((String)zzkb.zzik().zzd(zzazq)).equals(zzazq.zzja())) {
                s2 = (String)zzkb.zzik().zzd(zzazq);
            }
            else {
                s2 = "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}";
            }
            final int zzd2 = zzd(35632, s2);
            if (zzd2 == 0) {
                zzczi = 0;
            }
            else {
                final int glCreateProgram = GLES20.glCreateProgram();
                zzdo("createProgram");
                if ((zzczi = glCreateProgram) != 0) {
                    GLES20.glAttachShader(glCreateProgram, zzd);
                    zzdo("attachShader");
                    GLES20.glAttachShader(glCreateProgram, zzd2);
                    zzdo("attachShader");
                    GLES20.glLinkProgram(glCreateProgram);
                    zzdo("linkProgram");
                    final int[] array3 = { 0 };
                    GLES20.glGetProgramiv(glCreateProgram, 35714, array3, 0);
                    zzdo("getProgramiv");
                    if (array3[0] != 1) {
                        Log.e("SphericalVideoRenderer", "Could not link program: ");
                        Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(glCreateProgram));
                        GLES20.glDeleteProgram(glCreateProgram);
                        zzdo("deleteProgram");
                        zzczi = 0;
                    }
                    else {
                        GLES20.glValidateProgram(glCreateProgram);
                        zzdo("validateProgram");
                        zzczi = glCreateProgram;
                    }
                }
            }
        }
        GLES20.glUseProgram(this.zzczi = zzczi);
        zzdo("useProgram");
        final int glGetAttribLocation = GLES20.glGetAttribLocation(this.zzczi, "aPosition");
        GLES20.glVertexAttribPointer(glGetAttribLocation, 3, 5126, false, 12, (Buffer)this.zzczl);
        zzdo("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        zzdo("enableVertexAttribArray");
        final int[] array4 = { 0 };
        GLES20.glGenTextures(1, array4, 0);
        zzdo("genTextures");
        final int n2 = array4[0];
        GLES20.glBindTexture(36197, n2);
        zzdo("bindTextures");
        GLES20.glTexParameteri(36197, 10240, 9729);
        zzdo("texParameteri");
        GLES20.glTexParameteri(36197, 10241, 9729);
        zzdo("texParameteri");
        GLES20.glTexParameteri(36197, 10242, 33071);
        zzdo("texParameteri");
        GLES20.glTexParameteri(36197, 10243, 33071);
        zzdo("texParameteri");
        GLES20.glUniformMatrix3fv(this.zzczj = GLES20.glGetUniformLocation(this.zzczi, "uVMat"), 1, false, new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f }, 0);
        int glGetUniformLocation2;
        if (this.zzczi != 0) {
            glGetUniformLocation2 = n;
        }
        else {
            glGetUniformLocation2 = 0;
        }
        if (glGetUniformLocation == 0 || glGetUniformLocation2 == 0) {
            final String value = String.valueOf(GLUtils.getEGLErrorString(this.zzczo.eglGetError()));
            String concat;
            if (value.length() != 0) {
                concat = "EGL initialization failed: ".concat(value);
            }
            else {
                concat = new String("EGL initialization failed: ");
            }
            zzakb.e(concat);
            zzbv.zzeo().zza(new Throwable(concat), "SphericalVideoProcessor.run.1");
            this.zztk();
            this.zzczm.countDown();
            return;
        }
        (this.zzczg = new SurfaceTexture(n2)).setOnFrameAvailableListener((SurfaceTexture$OnFrameAvailableListener)this);
        this.zzczm.countDown();
        this.zzcyw.start();
        float[] zzcys;
        float[] array6;
        float[] array5;
        float[] array7;
        Block_23_Outer:Label_1521_Outer:
        while (true) {
            Label_1521:Label_1582_Outer:
            while (true) {
                try {
                    this.zzczs = true;
                    if (!this.zzczt) {
                        while (this.zzczk > 0) {
                            this.zzczg.updateTexImage();
                            --this.zzczk;
                        }
                        break Label_1521;
                    }
                }
                catch (IllegalStateException ex) {
                    zzakb.zzdk("SphericalVideoProcessor halted unexpectedly.");
                    return;
                    // iftrue(Label_1303:, !Float.isNaN(this.zzczd))
                    // iftrue(Label_1653:, this.zzuq <= this.zzur)
                    // iftrue(Label_1526:, !this.zzczs)
                    // iftrue(Label_1582:, !this.zzcyw.zza(this.zzcys))
                    while (true) {
                        Block_27:Label_1319_Outer:
                        while (true) {
                        Label_1319:
                            while (true) {
                                while (true) {
                                    while (true) {
                                        Block_24: {
                                            break Block_24;
                                            this.zzczs = false;
                                            break Block_27;
                                            zzb(this.zzczb, this.zzczd + this.zzcze);
                                            break Label_1319;
                                            GLES20.glViewport(0, 0, this.zzuq, this.zzur);
                                            zzdo("viewport");
                                            glGetUniformLocation = GLES20.glGetUniformLocation(this.zzczi, "uFOVx");
                                            glGetUniformLocation2 = GLES20.glGetUniformLocation(this.zzczi, "uFOVy");
                                            GLES20.glUniform1f(glGetUniformLocation, 0.87266463f);
                                            GLES20.glUniform1f(glGetUniformLocation2, 0.87266463f * this.zzur / this.zzuq);
                                            continue Label_1521;
                                        }
                                        zzcys = this.zzcys;
                                        array5 = (array6 = new float[3]);
                                        array6[0] = 0.0f;
                                        array6[1] = 1.0f;
                                        array6[2] = 0.0f;
                                        array7 = new float[] { zzcys[0] * array5[0] + zzcys[1] * array5[1] + zzcys[2] * array5[2], zzcys[3] * array5[0] + zzcys[4] * array5[1] + zzcys[5] * array5[2], zzcys[8] * array5[2] + (zzcys[6] * array5[0] + zzcys[7] * array5[1]) };
                                        this.zzczd = -((float)Math.atan2(array7[1], array7[0]) - 1.5707964f);
                                        continue Label_1319_Outer;
                                    }
                                    zza(this.zzcyx, 1.5707964f);
                                    zza(this.zzcyy, this.zzczb, this.zzcyx);
                                    zza(this.zzcyz, this.zzcys, this.zzcyy);
                                    zza(this.zzcza, this.zzczf);
                                    zza(this.zzczc, this.zzcza, this.zzcyz);
                                    GLES20.glUniformMatrix3fv(this.zzczj, 1, false, this.zzczc, 0);
                                    GLES20.glDrawArrays(5, 0, 4);
                                    zzdo("drawArrays");
                                    GLES20.glFinish();
                                    this.zzczo.eglSwapBuffers(this.zzczp, this.zzczr);
                                    continue Label_1582_Outer;
                                }
                                zza(this.zzcys, -1.5707964f);
                                zzb(this.zzczb, this.zzcze);
                                continue Label_1319;
                            }
                            continue Label_1521_Outer;
                        }
                        try {
                            Label_1526: {
                                synchronized (this.zzczn) {
                                    if (!this.zzczt && !this.zzczs && this.zzczk == 0) {
                                        this.zzczn.wait();
                                    }
                                }
                            }
                        }
                        catch (InterruptedException ex2) {
                            continue Block_23_Outer;
                        }
                        continue;
                    }
                }
                catch (Throwable t) {
                    zzakb.zzb("SphericalVideoProcessor died.", t);
                    zzbv.zzeo().zza(t, "SphericalVideoProcessor.run.2");
                    return;
                    Label_1653: {
                        GLES20.glUniform1f(glGetUniformLocation, 0.87266463f * this.zzuq / this.zzur);
                    }
                    GLES20.glUniform1f(glGetUniformLocation2, 0.87266463f);
                    continue Label_1521;
                }
                finally {
                    this.zzcyw.stop();
                    this.zzczg.setOnFrameAvailableListener((SurfaceTexture$OnFrameAvailableListener)null);
                    this.zzczg = null;
                    this.zztk();
                }
                break;
            }
            break;
        }
        this.zzcyw.stop();
        this.zzczg.setOnFrameAvailableListener((SurfaceTexture$OnFrameAvailableListener)null);
        this.zzczg = null;
        this.zztk();
    }
    
    public final void zza(final SurfaceTexture zzczh, final int zzuq, final int zzur) {
        this.zzuq = zzuq;
        this.zzur = zzur;
        this.zzczh = zzczh;
    }
    
    public final void zzb(float n, float n2) {
        if (this.zzuq > this.zzur) {
            n = 1.7453293f * n / this.zzuq;
            final float n3 = 1.7453293f * n2 / this.zzuq;
            n2 = n;
            n = n3;
        }
        else {
            final float n4 = 1.7453293f * n / this.zzur;
            n = 1.7453293f * n2 / this.zzur;
            n2 = n4;
        }
        this.zzcze -= n2;
        this.zzczf -= n;
        if (this.zzczf < -1.5707964f) {
            this.zzczf = -1.5707964f;
        }
        if (this.zzczf > 1.5707964f) {
            this.zzczf = 1.5707964f;
        }
    }
    
    public final void zzh(final int zzuq, final int zzur) {
        synchronized (this.zzczn) {
            this.zzuq = zzuq;
            this.zzur = zzur;
            this.zzczs = true;
            this.zzczn.notifyAll();
        }
    }
    
    public final void zznn() {
        synchronized (this.zzczn) {
            this.zzczn.notifyAll();
        }
    }
    
    public final void zzti() {
        synchronized (this.zzczn) {
            this.zzczt = true;
            this.zzczh = null;
            this.zzczn.notifyAll();
        }
    }
    
    public final SurfaceTexture zztj() {
        if (this.zzczh == null) {
            return null;
        }
        try {
            this.zzczm.await();
            return this.zzczg;
        }
        catch (InterruptedException ex) {
            return this.zzczg;
        }
    }
}
