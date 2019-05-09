package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.a */
public final class C0238a {
    /* renamed from: b */
    private static CameraManager f161b = null;
    /* renamed from: c */
    private static String[] f162c = null;
    /* renamed from: e */
    private static Semaphore f163e = new Semaphore(1);
    /* renamed from: a */
    private C0198d f164a = null;
    /* renamed from: d */
    private CameraDevice f165d;
    /* renamed from: f */
    private HandlerThread f166f;
    /* renamed from: g */
    private Handler f167g;
    /* renamed from: h */
    private Rect f168h;
    /* renamed from: i */
    private Range f169i;
    /* renamed from: j */
    private ImageReader f170j;
    /* renamed from: k */
    private Builder f171k;
    /* renamed from: l */
    private CameraCaptureSession f172l;
    /* renamed from: m */
    private final StateCallback f173m = new C02352(this);
    /* renamed from: n */
    private final OnImageAvailableListener f174n = new C02363(this);
    /* renamed from: o */
    private CaptureCallback f175o = new C02374(this);

    /* renamed from: com.unity3d.player.a$1 */
    class C02341 extends CameraCaptureSession.StateCallback {
        /* renamed from: a */
        final /* synthetic */ C0238a f157a;

        C02341(C0238a c0238a) {
            this.f157a = c0238a;
        }

        public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            C0243g.Log(6, "Camera2: CaptureSession configuration failed.");
        }

        public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
            C0243g.Log(4, "Camera2: CaptureSession is configured.");
            if (this.f157a.f165d != null) {
                this.f157a.f172l = cameraCaptureSession;
                try {
                    this.f157a.f171k = this.f157a.f165d.createCaptureRequest(1);
                    this.f157a.f171k.addTarget(this.f157a.f170j.getSurface());
                    this.f157a.f171k.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(4));
                    this.f157a.f171k.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, this.f157a.f169i);
                    this.f157a.f172l.setRepeatingRequest(this.f157a.f171k.build(), this.f157a.f175o, this.f157a.f167g);
                } catch (CameraAccessException e) {
                    C0243g.Log(6, "Camera2: CameraAccessException " + e);
                }
            }
        }
    }

    /* renamed from: com.unity3d.player.a$2 */
    class C02352 extends StateCallback {
        /* renamed from: a */
        final /* synthetic */ C0238a f158a;

        C02352(C0238a c0238a) {
            this.f158a = c0238a;
        }

        public final void onClosed(CameraDevice cameraDevice) {
            C0243g.Log(4, "Camera2: CameraDevice closed.");
            C0238a.f163e.release();
        }

        public final void onDisconnected(CameraDevice cameraDevice) {
            cameraDevice.close();
            this.f158a.f165d = null;
            C0243g.Log(5, "Camera2: CameraDevice disconnected.");
            C0238a.f163e.release();
        }

        public final void onError(CameraDevice cameraDevice, int i) {
            cameraDevice.close();
            this.f158a.f165d = null;
            C0243g.Log(6, "Camera2: Error opeining CameraDevice " + i);
            C0238a.f163e.release();
        }

        public final void onOpened(CameraDevice cameraDevice) {
            this.f158a.f165d = cameraDevice;
            C0243g.Log(4, "Camera2: CameraDevice opened.");
            C0238a.f163e.release();
        }
    }

    /* renamed from: com.unity3d.player.a$3 */
    class C02363 implements OnImageAvailableListener {
        /* renamed from: a */
        final /* synthetic */ C0238a f159a;

        C02363(C0238a c0238a) {
            this.f159a = c0238a;
        }

        public final void onImageAvailable(ImageReader imageReader) {
            if (C0238a.f163e.tryAcquire()) {
                Image acquireLatestImage = imageReader.acquireLatestImage();
                if (acquireLatestImage != null) {
                    Plane[] planes = acquireLatestImage.getPlanes();
                    if (acquireLatestImage.getFormat() == 35 && planes != null && planes.length == 3) {
                        this.f159a.f164a.mo683a(planes[0].getBuffer(), planes[1].getBuffer(), planes[2].getBuffer(), planes[0].getRowStride(), planes[1].getRowStride(), planes[1].getPixelStride());
                    } else {
                        C0243g.Log(6, "Camera2: Wrong image format.");
                    }
                    acquireLatestImage.close();
                }
                C0238a.f163e.release();
            }
        }
    }

    /* renamed from: com.unity3d.player.a$4 */
    class C02374 extends CaptureCallback {
        /* renamed from: a */
        final /* synthetic */ C0238a f160a;

        C02374(C0238a c0238a) {
            this.f160a = c0238a;
        }

        public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
        }

        public final void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            C0243g.Log(5, "Camera2: Capture session failed " + captureFailure.getReason());
        }

        public final void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
            C0243g.Log(4, "Camera2: Capture sequence aborted.");
        }

        public final void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
            C0243g.Log(4, "Camera2: Capture sequence completed.");
        }
    }

    protected C0238a(C0198d c0198d) {
        this.f164a = c0198d;
        m137f();
    }

    /* renamed from: a */
    public static int m120a(Context context) {
        return C0238a.m132c(context).length;
    }

    /* renamed from: a */
    public static int m121a(Context context, int i) {
        try {
            return ((Integer) C0238a.m128b(context).getCameraCharacteristics(C0238a.m132c(context)[i]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        } catch (CameraAccessException e) {
            C0243g.Log(6, "Camera2: CameraAccessException " + e);
            return 0;
        }
    }

    /* renamed from: a */
    private static Rect m122a(Size[] sizeArr, double d, double d2) {
        int i = 0;
        int i2 = 0;
        double d3 = Double.MAX_VALUE;
        for (int i3 = 0; i3 < sizeArr.length; i3++) {
            int width = sizeArr[i3].getWidth();
            int height = sizeArr[i3].getHeight();
            double abs = Math.abs(Math.log(d / ((double) width))) + Math.abs(Math.log(d2 / ((double) height)));
            if (abs < d3) {
                d3 = abs;
                i2 = height;
                i = width;
            }
            C0243g.Log(4, "Camera2: FrameSize " + width + " x " + height + " [" + abs + RequestParameters.RIGHT_BRACKETS);
        }
        return new Rect(0, 0, i, i2);
    }

    /* renamed from: a */
    private static Range m127a(Range[] rangeArr, double d) {
        double d2 = Double.MAX_VALUE;
        int i = -1;
        for (int i2 = 0; i2 < rangeArr.length; i2++) {
            int intValue = ((Integer) rangeArr[i2].getLower()).intValue();
            int intValue2 = ((Integer) rangeArr[i2].getUpper()).intValue();
            double abs = Math.abs(Math.log(d / ((double) intValue))) + Math.abs(Math.log(d / ((double) intValue2)));
            if (abs < d2) {
                d2 = abs;
                i = i2;
            }
            C0243g.Log(4, "Camera2: Frame rate[" + i2 + "] = " + intValue + "-" + intValue2 + " [" + abs + RequestParameters.RIGHT_BRACKETS);
        }
        return rangeArr[i];
    }

    /* renamed from: b */
    private static CameraManager m128b(Context context) {
        if (f161b == null) {
            f161b = (CameraManager) context.getSystemService("camera");
        }
        return f161b;
    }

    /* renamed from: b */
    public static boolean m130b(Context context, int i) {
        try {
            return ((Integer) C0238a.m128b(context).getCameraCharacteristics(C0238a.m132c(context)[i]).get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
        } catch (CameraAccessException e) {
            C0243g.Log(6, "Camera2: CameraAccessException " + e);
            return false;
        }
    }

    /* renamed from: c */
    private static String[] m132c(Context context) {
        if (f162c == null) {
            try {
                f162c = C0238a.m128b(context).getCameraIdList();
            } catch (CameraAccessException e) {
                C0243g.Log(6, "Camera2: CameraAccessException " + e);
                f162c = new String[0];
            }
        }
        return f162c;
    }

    /* renamed from: f */
    private void m137f() {
        this.f166f = new HandlerThread("CameraBackground");
        this.f166f.start();
        this.f167g = new Handler(this.f166f.getLooper());
    }

    /* renamed from: g */
    private void m139g() {
        this.f166f.quit();
        try {
            this.f166f.join(4000);
            this.f166f = null;
            this.f167g = null;
        } catch (InterruptedException e) {
            this.f166f.interrupt();
            C0243g.Log(6, "Camera2: Interrupted while waiting for the background thread to finish " + e);
        }
    }

    /* renamed from: h */
    private void m141h() {
        try {
            if (f163e.tryAcquire(4, TimeUnit.SECONDS)) {
                this.f165d.close();
                try {
                    if (!f163e.tryAcquire(4, TimeUnit.SECONDS)) {
                        C0243g.Log(5, "Camera2: Timeout waiting to close camera.");
                    }
                } catch (InterruptedException e) {
                    C0243g.Log(6, "Camera2: Interrupted while waiting to close camera " + e);
                }
                f163e.release();
                return;
            }
            C0243g.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
        } catch (InterruptedException e2) {
            C0243g.Log(6, "Camera2: Interrupted while trying to lock camera for closing " + e2);
        }
    }

    /* renamed from: a */
    public final Rect m142a() {
        return this.f168h;
    }

    /* renamed from: a */
    public final boolean m143a(Context context, int i, int i2, int i3, int i4) {
        try {
            CameraCharacteristics cameraCharacteristics = f161b.getCameraCharacteristics(C0238a.m132c(context)[i]);
            C0243g.Log(4, "Camera2: Hardware level: " + cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL));
            if (((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                C0243g.Log(5, "Camera2: only LEGACY hardware level is supported.");
                return false;
            }
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if (streamConfigurationMap == null) {
                C0243g.Log(6, "Camera2: configuration map is not available.");
                return false;
            }
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(35);
            if (outputSizes == null || outputSizes.length == 0) {
                C0243g.Log(6, "Camera2: output sizes for YUV_420_888 format are not avialable.");
                return false;
            }
            this.f168h = C0238a.m122a(outputSizes, (double) i2, (double) i3);
            Range[] rangeArr = (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
            if (rangeArr == null || rangeArr.length == 0) {
                C0243g.Log(6, "Camera2: target FPS ranges are not avialable.");
                return false;
            }
            this.f169i = C0238a.m127a(rangeArr, (double) i4);
            try {
                if (f163e.tryAcquire(4, TimeUnit.SECONDS)) {
                    try {
                        f161b.openCamera(C0238a.m132c(context)[i], this.f173m, this.f167g);
                        try {
                            if (f163e.tryAcquire(4, TimeUnit.SECONDS)) {
                                f163e.release();
                                return this.f165d != null;
                            } else {
                                C0243g.Log(5, "Camera2: Timeout waiting to open camera.");
                                return false;
                            }
                        } catch (InterruptedException e) {
                            C0243g.Log(6, "Camera2: Interrupted while waiting to open camera " + e);
                        }
                    } catch (CameraAccessException e2) {
                        C0243g.Log(6, "Camera2: CameraAccessException " + e2);
                        f163e.release();
                        return false;
                    }
                }
                C0243g.Log(5, "Camera2: Timeout waiting to lock camera for opening.");
                return false;
            } catch (InterruptedException e3) {
                C0243g.Log(6, "Camera2: Interrupted while trying to lock camera for opening " + e3);
                return false;
            }
        } catch (CameraAccessException e22) {
            C0243g.Log(6, "Camera2: CameraAccessException " + e22);
            return false;
        }
    }

    /* renamed from: b */
    public final void m144b() {
        C0243g.Log(4, "Camera2: Close.");
        if (this.f165d != null) {
            m146d();
            m141h();
            this.f165d = null;
            this.f170j.close();
            this.f170j = null;
        }
        m139g();
    }

    /* renamed from: c */
    public final void m145c() {
        C0243g.Log(4, "Camera2: Start preview.");
        if (this.f170j == null) {
            this.f170j = ImageReader.newInstance(this.f168h.width(), this.f168h.height(), 35, 2);
            this.f170j.setOnImageAvailableListener(this.f174n, this.f167g);
        }
        try {
            this.f165d.createCaptureSession(Arrays.asList(new Surface[]{this.f170j.getSurface()}), new C02341(this), this.f167g);
        } catch (CameraAccessException e) {
            C0243g.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: d */
    public final void m146d() {
        C0243g.Log(4, "Camera2: Stop preview.");
        if (this.f172l != null) {
            try {
                this.f172l.abortCaptures();
            } catch (CameraAccessException e) {
                C0243g.Log(6, "Camera2: CameraAccessException " + e);
            }
            this.f172l.close();
            this.f172l = null;
        }
    }
}
