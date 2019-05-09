// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import java.util.List;
import android.hardware.camera2.CameraCaptureSession$StateCallback;
import java.util.Arrays;
import android.view.Surface;
import android.hardware.camera2.params.StreamConfigurationMap;
import java.util.concurrent.TimeUnit;
import android.util.Size;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.content.Context;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.CaptureRequest;
import android.media.Image$Plane;
import android.media.Image;
import android.hardware.camera2.CameraCaptureSession$CaptureCallback;
import android.media.ImageReader$OnImageAvailableListener;
import android.hardware.camera2.CameraDevice$StateCallback;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest$Builder;
import android.media.ImageReader;
import android.util.Range;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.hardware.camera2.CameraDevice;
import java.util.concurrent.Semaphore;
import android.hardware.camera2.CameraManager;

public final class a
{
    private static CameraManager b;
    private static String[] c;
    private static Semaphore e;
    private d a;
    private CameraDevice d;
    private HandlerThread f;
    private Handler g;
    private Rect h;
    private Range i;
    private ImageReader j;
    private CaptureRequest$Builder k;
    private CameraCaptureSession l;
    private final CameraDevice$StateCallback m;
    private final ImageReader$OnImageAvailableListener n;
    private CameraCaptureSession$CaptureCallback o;
    
    static {
        a.b = null;
        a.c = null;
        a.e = new Semaphore(1);
    }
    
    protected a(final d a) {
        this.a = null;
        this.m = new CameraDevice$StateCallback() {
            public final void onClosed(final CameraDevice cameraDevice) {
                com.unity3d.player.g.Log(4, "Camera2: CameraDevice closed.");
                com.unity3d.player.a.e.release();
            }
            
            public final void onDisconnected(final CameraDevice cameraDevice) {
                cameraDevice.close();
                com.unity3d.player.a.this.d = null;
                com.unity3d.player.g.Log(5, "Camera2: CameraDevice disconnected.");
                com.unity3d.player.a.e.release();
            }
            
            public final void onError(final CameraDevice cameraDevice, final int n) {
                cameraDevice.close();
                com.unity3d.player.a.this.d = null;
                com.unity3d.player.g.Log(6, "Camera2: Error opeining CameraDevice " + n);
                com.unity3d.player.a.e.release();
            }
            
            public final void onOpened(final CameraDevice cameraDevice) {
                com.unity3d.player.a.this.d = cameraDevice;
                com.unity3d.player.g.Log(4, "Camera2: CameraDevice opened.");
                com.unity3d.player.a.e.release();
            }
        };
        this.n = (ImageReader$OnImageAvailableListener)new ImageReader$OnImageAvailableListener() {
            public final void onImageAvailable(final ImageReader imageReader) {
                if (com.unity3d.player.a.e.tryAcquire()) {
                    final Image acquireLatestImage = imageReader.acquireLatestImage();
                    if (acquireLatestImage != null) {
                        final Image$Plane[] planes = acquireLatestImage.getPlanes();
                        if (acquireLatestImage.getFormat() == 35 && planes != null && planes.length == 3) {
                            com.unity3d.player.a.this.a.a(planes[0].getBuffer(), planes[1].getBuffer(), planes[2].getBuffer(), planes[0].getRowStride(), planes[1].getRowStride(), planes[1].getPixelStride());
                        }
                        else {
                            com.unity3d.player.g.Log(6, "Camera2: Wrong image format.");
                        }
                        acquireLatestImage.close();
                    }
                    com.unity3d.player.a.e.release();
                }
            }
        };
        this.o = new CameraCaptureSession$CaptureCallback() {
            public final void onCaptureCompleted(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final TotalCaptureResult totalCaptureResult) {
            }
            
            public final void onCaptureFailed(final CameraCaptureSession cameraCaptureSession, final CaptureRequest captureRequest, final CaptureFailure captureFailure) {
                com.unity3d.player.g.Log(5, "Camera2: Capture session failed " + captureFailure.getReason());
            }
            
            public final void onCaptureSequenceAborted(final CameraCaptureSession cameraCaptureSession, final int n) {
                com.unity3d.player.g.Log(4, "Camera2: Capture sequence aborted.");
            }
            
            public final void onCaptureSequenceCompleted(final CameraCaptureSession cameraCaptureSession, final int n, final long n2) {
                com.unity3d.player.g.Log(4, "Camera2: Capture sequence completed.");
            }
        };
        this.a = a;
        this.f();
    }
    
    public static int a(final Context context) {
        return c(context).length;
    }
    
    public static int a(final Context context, int intValue) {
        try {
            intValue = (int)b(context).getCameraCharacteristics(c(context)[intValue]).get(CameraCharacteristics.SENSOR_ORIENTATION);
            return intValue;
        }
        catch (CameraAccessException ex) {
            g.Log(6, "Camera2: CameraAccessException " + ex);
            return 0;
        }
    }
    
    private static Rect a(final Size[] array, final double n, final double n2) {
        int n3 = 0;
        int n4 = 0;
        double n5 = Double.MAX_VALUE;
        double n7;
        for (int i = 0; i < array.length; ++i, n5 = n7) {
            final int width = array[i].getWidth();
            final int height = array[i].getHeight();
            final double n6 = Math.abs(Math.log(n / width)) + Math.abs(Math.log(n2 / height));
            n7 = n5;
            if (n6 < n5) {
                n7 = n6;
                n4 = height;
                n3 = width;
            }
            g.Log(4, "Camera2: FrameSize " + width + " x " + height + " [" + n6 + "]");
        }
        return new Rect(0, 0, n3, n4);
    }
    
    private static Range a(final Range[] array, final double n) {
        double n2 = Double.MAX_VALUE;
        int n3 = -1;
        double n5;
        for (int i = 0; i < array.length; ++i, n2 = n5) {
            final int intValue = (int)array[i].getLower();
            final int intValue2 = (int)array[i].getUpper();
            final double n4 = Math.abs(Math.log(n / intValue)) + Math.abs(Math.log(n / intValue2));
            n5 = n2;
            if (n4 < n2) {
                n5 = n4;
                n3 = i;
            }
            g.Log(4, "Camera2: Frame rate[" + i + "] = " + intValue + "-" + intValue2 + " [" + n4 + "]");
        }
        return array[n3];
    }
    
    private static CameraManager b(final Context context) {
        if (a.b == null) {
            a.b = (CameraManager)context.getSystemService("camera");
        }
        return a.b;
    }
    
    public static boolean b(final Context context, int intValue) {
        try {
            intValue = (int)b(context).getCameraCharacteristics(c(context)[intValue]).get(CameraCharacteristics.LENS_FACING);
            return intValue == 0;
        }
        catch (CameraAccessException ex) {
            g.Log(6, "Camera2: CameraAccessException " + ex);
            return false;
        }
    }
    
    private static String[] c(final Context context) {
        Label_0016: {
            if (a.c != null) {
                break Label_0016;
            }
            try {
                a.c = b(context).getCameraIdList();
                return a.c;
            }
            catch (CameraAccessException ex) {
                g.Log(6, "Camera2: CameraAccessException " + ex);
                a.c = new String[0];
                return a.c;
            }
        }
    }
    
    private void f() {
        (this.f = new HandlerThread("CameraBackground")).start();
        this.g = new Handler(this.f.getLooper());
    }
    
    private void g() {
        this.f.quit();
        try {
            this.f.join(4000L);
            this.f = null;
            this.g = null;
        }
        catch (InterruptedException ex) {
            this.f.interrupt();
            com.unity3d.player.g.Log(6, "Camera2: Interrupted while waiting for the background thread to finish " + ex);
        }
    }
    
    private void h() {
        try {
            if (!com.unity3d.player.a.e.tryAcquire(4L, TimeUnit.SECONDS)) {
                com.unity3d.player.g.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
                return;
            }
        }
        catch (InterruptedException ex) {
            com.unity3d.player.g.Log(6, "Camera2: Interrupted while trying to lock camera for closing " + ex);
            return;
        }
        this.d.close();
        while (true) {
            try {
                if (!com.unity3d.player.a.e.tryAcquire(4L, TimeUnit.SECONDS)) {
                    com.unity3d.player.g.Log(5, "Camera2: Timeout waiting to close camera.");
                }
                com.unity3d.player.a.e.release();
            }
            catch (InterruptedException ex2) {
                com.unity3d.player.g.Log(6, "Camera2: Interrupted while waiting to close camera " + ex2);
                continue;
            }
            break;
        }
    }
    
    public final Rect a() {
        return this.h;
    }
    
    public final boolean a(final Context context, final int n, final int n2, final int n3, final int n4) {
        CameraCharacteristics cameraCharacteristics;
        try {
            cameraCharacteristics = com.unity3d.player.a.b.getCameraCharacteristics(c(context)[n]);
            com.unity3d.player.g.Log(4, "Camera2: Hardware level: " + cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL));
            if ((int)cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL) == 2) {
                com.unity3d.player.g.Log(5, "Camera2: only LEGACY hardware level is supported.");
                return false;
            }
        }
        catch (CameraAccessException ex) {
            com.unity3d.player.g.Log(6, "Camera2: CameraAccessException " + ex);
            return false;
        }
        final StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap)cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            com.unity3d.player.g.Log(6, "Camera2: configuration map is not available.");
            return false;
        }
        final Size[] outputSizes = streamConfigurationMap.getOutputSizes(35);
        if (outputSizes == null || outputSizes.length == 0) {
            com.unity3d.player.g.Log(6, "Camera2: output sizes for YUV_420_888 format are not avialable.");
            return false;
        }
        this.h = a(outputSizes, n2, n3);
        final Range[] array = (Range[])cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        if (array == null || array.length == 0) {
            com.unity3d.player.g.Log(6, "Camera2: target FPS ranges are not avialable.");
            return false;
        }
        this.i = a(array, n4);
        try {
            if (!com.unity3d.player.a.e.tryAcquire(4L, TimeUnit.SECONDS)) {
                com.unity3d.player.g.Log(5, "Camera2: Timeout waiting to lock camera for opening.");
                return false;
            }
        }
        catch (InterruptedException ex2) {
            com.unity3d.player.g.Log(6, "Camera2: Interrupted while trying to lock camera for opening " + ex2);
            return false;
        }
        Label_0334: {
            try {
                com.unity3d.player.a.b.openCamera(c(context)[n], this.m, this.g);
                final Semaphore semaphore = com.unity3d.player.a.e;
                final long n5 = 4L;
                final TimeUnit timeUnit = TimeUnit.SECONDS;
                final boolean b = semaphore.tryAcquire(n5, timeUnit);
                if (!b) {
                    final int n6 = 5;
                    final String s = "Camera2: Timeout waiting to open camera.";
                    com.unity3d.player.g.Log(n6, s);
                    return false;
                }
                break Label_0334;
            }
            catch (CameraAccessException ex3) {
                com.unity3d.player.g.Log(6, "Camera2: CameraAccessException " + ex3);
                com.unity3d.player.a.e.release();
                return false;
            }
            while (true) {
                try {
                    final Semaphore semaphore = com.unity3d.player.a.e;
                    final long n5 = 4L;
                    final TimeUnit timeUnit = TimeUnit.SECONDS;
                    final boolean b = semaphore.tryAcquire(n5, timeUnit);
                    if (!b) {
                        final int n6 = 5;
                        final String s = "Camera2: Timeout waiting to open camera.";
                        com.unity3d.player.g.Log(n6, s);
                        return false;
                    }
                    com.unity3d.player.a.e.release();
                    if (this.d != null) {
                        return true;
                    }
                }
                catch (InterruptedException ex4) {
                    com.unity3d.player.g.Log(6, "Camera2: Interrupted while waiting to open camera " + ex4);
                    continue;
                }
                break;
            }
        }
        return false;
    }
    
    public final void b() {
        com.unity3d.player.g.Log(4, "Camera2: Close.");
        if (this.d != null) {
            this.d();
            this.h();
            this.d = null;
            this.j.close();
            this.j = null;
        }
        this.g();
    }
    
    public final void c() {
        com.unity3d.player.g.Log(4, "Camera2: Start preview.");
        if (this.j == null) {
            (this.j = ImageReader.newInstance(this.h.width(), this.h.height(), 35, 2)).setOnImageAvailableListener(this.n, this.g);
        }
        try {
            this.d.createCaptureSession((List)Arrays.asList(this.j.getSurface()), (CameraCaptureSession$StateCallback)new CameraCaptureSession$StateCallback() {
                public final void onConfigureFailed(final CameraCaptureSession cameraCaptureSession) {
                    com.unity3d.player.g.Log(6, "Camera2: CaptureSession configuration failed.");
                }
                
                public final void onConfigured(final CameraCaptureSession cameraCaptureSession) {
                    com.unity3d.player.g.Log(4, "Camera2: CaptureSession is configured.");
                    if (com.unity3d.player.a.this.d == null) {
                        return;
                    }
                    com.unity3d.player.a.this.l = cameraCaptureSession;
                    try {
                        com.unity3d.player.a.this.k = com.unity3d.player.a.this.d.createCaptureRequest(1);
                        com.unity3d.player.a.this.k.addTarget(com.unity3d.player.a.this.j.getSurface());
                        com.unity3d.player.a.this.k.set(CaptureRequest.CONTROL_AF_MODE, (Object)4);
                        com.unity3d.player.a.this.k.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, (Object)com.unity3d.player.a.this.i);
                        com.unity3d.player.a.this.l.setRepeatingRequest(com.unity3d.player.a.this.k.build(), com.unity3d.player.a.this.o, com.unity3d.player.a.this.g);
                    }
                    catch (CameraAccessException ex) {
                        com.unity3d.player.g.Log(6, "Camera2: CameraAccessException " + ex);
                    }
                }
            }, this.g);
        }
        catch (CameraAccessException ex) {
            com.unity3d.player.g.Log(6, "Camera2: CameraAccessException " + ex);
        }
    }
    
    public final void d() {
        com.unity3d.player.g.Log(4, "Camera2: Stop preview.");
        if (this.l == null) {
            return;
        }
        while (true) {
            try {
                this.l.abortCaptures();
                this.l.close();
                this.l = null;
            }
            catch (CameraAccessException ex) {
                com.unity3d.player.g.Log(6, "Camera2: CameraAccessException " + ex);
                continue;
            }
            break;
        }
    }
}
