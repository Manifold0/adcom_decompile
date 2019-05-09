// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ar.view;

import com.unity3d.services.ar.ARUtils;
import javax.microedition.khronos.egl.EGLConfig;
import com.google.ar.core.Camera;
import com.google.ar.core.Frame;
import com.google.ar.core.exceptions.NotYetAvailableException;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import android.opengl.GLES20;
import javax.microedition.khronos.opengles.GL10;
import com.google.ar.core.Pose;
import org.json.JSONException;
import com.unity3d.services.core.log.DeviceLog;
import java.util.Collection;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.ar.AREvent;
import android.os.Build$VERSION;
import java.util.HashMap;
import android.content.Context;
import com.google.ar.core.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.ar.core.Plane;
import android.util.SparseArray;
import com.google.ar.core.Anchor;
import java.util.Map;
import android.annotation.TargetApi;
import android.opengl.GLSurfaceView$Renderer;
import android.opengl.GLSurfaceView;

@TargetApi(19)
public class ARView extends GLSurfaceView implements GLSurfaceView$Renderer
{
    private static final long FRAME_UPDATE_TIMEOUT = 500L;
    private Map<String, Anchor> _anchors;
    private float _arFar;
    private float _arNear;
    private BackgroundRenderer _backgroundRenderer;
    private SparseArray<Plane> _detectedPlanes;
    private DisplayRotationHelper _displayRotationHelper;
    private boolean _drawNextCameraFrame;
    JSONObject _frameInfo;
    JSONObject _lightEstimate;
    JSONArray _orientation;
    float[] _orientationArray;
    float[] _planeMatrix;
    float[] _planeVertices;
    JSONArray _position;
    JSONArray _projectionMatrix;
    float[] _projectionMatrixArray;
    private Session _session;
    private boolean _sessionRunning;
    private boolean _sessionWasRunning;
    private boolean _shouldSendResize;
    private boolean _showCameraFrame;
    private long _timeOfLastDrawnCameraFrame;
    JSONArray _viewMatrix;
    float[] _viewMatrixArray;
    
    public ARView(final Context context) {
        super(context);
        this._session = null;
        this._arNear = 0.01f;
        this._arFar = 10000.0f;
        this._backgroundRenderer = new BackgroundRenderer();
        this._detectedPlanes = (SparseArray<Plane>)new SparseArray();
        this._anchors = new HashMap<String, Anchor>();
        this._shouldSendResize = false;
        this._projectionMatrixArray = new float[16];
        this._viewMatrixArray = new float[16];
        this._orientationArray = new float[4];
        this._planeVertices = new float[12];
        this._frameInfo = new JSONObject();
        this._position = new JSONArray();
        this._orientation = new JSONArray();
        this._viewMatrix = new JSONArray();
        this._projectionMatrix = new JSONArray();
        this._lightEstimate = new JSONObject();
        this._planeMatrix = new float[16];
        if (Build$VERSION.SDK_INT > 11) {
            this.setPreserveEGLContextOnPause(true);
        }
        this.setEGLContextClientVersion(2);
        this.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.setRenderer((GLSurfaceView$Renderer)this);
        this.setRenderMode(1);
        this._displayRotationHelper = new DisplayRotationHelper(context);
    }
    
    private static void getPlaneVertices(final Plane plane, final float[] array) {
        array[0] = plane.getExtentX() / 2.0f;
        array[1] = 0.0f;
        array[2] = plane.getExtentZ() / 2.0f;
        array[3] = -plane.getExtentX() / 2.0f;
        array[4] = 0.0f;
        array[5] = plane.getExtentZ() / 2.0f;
        array[6] = -plane.getExtentX() / 2.0f;
        array[7] = 0.0f;
        array[8] = -plane.getExtentZ() / 2.0f;
        array[9] = plane.getExtentX() / 2.0f;
        array[10] = 0.0f;
        array[11] = -plane.getExtentZ() / 2.0f;
    }
    
    private static void matrix4x4ToQuaternion(final float[] array, final float[] array2) {
        float n;
        if (array[10] < 0.0f) {
            if (array[0] > array[5]) {
                n = 1.0f + array[0] - array[5] - array[10];
                array2[0] = n;
                array2[1] = array[1] + array[4];
                array2[2] = array[8] + array[2];
                array2[3] = array[6] - array[9];
            }
            else {
                n = 1.0f - array[0] + array[5] - array[10];
                array2[0] = array[1] + array[4];
                array2[1] = n;
                array2[2] = array[6] + array[9];
                array2[3] = array[8] - array[2];
            }
        }
        else if (array[0] < -array[5]) {
            n = 1.0f - array[0] - array[5] + array[10];
            array2[0] = array[8] + array[2];
            array2[1] = array[6] + array[9];
            array2[2] = n;
            array2[3] = array[1] - array[4];
        }
        else {
            n = 1.0f + array[0] + array[5] + array[10];
            array2[0] = array[6] - array[9];
            array2[1] = array[8] - array[2];
            array2[2] = array[1] - array[4];
            array2[3] = n;
        }
        array2[0] *= (float)(0.5 / Math.sqrt(n));
        array2[1] *= (float)(0.5 / Math.sqrt(n));
        array2[2] *= (float)(0.5 / Math.sqrt(n));
        array2[3] *= (float)(0.5 / Math.sqrt(n));
    }
    
    private static void matrix4x4ToTranslation(final float[] array, final float[] array2) {
        array2[0] = array[3];
        array2[1] = array[7];
        array2[2] = array[11];
    }
    
    private void sendToWebView(final AREvent arEvent, final Object... array) {
        final WebViewApp currentApp = WebViewApp.getCurrentApp();
        if (currentApp == null || !currentApp.isWebAppLoaded()) {
            return;
        }
        currentApp.sendEvent(WebViewEventCategory.AR, arEvent, array);
    }
    
    private void updatePlanes(Collection<Plane> iterator) {
        if (!((Collection)iterator).isEmpty()) {
            final JSONArray jsonArray = new JSONArray();
            final JSONArray jsonArray2 = new JSONArray();
            final JSONArray jsonArray3 = new JSONArray();
            iterator = ((Collection<Plane>)iterator).iterator();
            while (iterator.hasNext()) {
                final Plane plane = iterator.next();
                JSONObject jsonObject = null;
                Label_0285: {
                    try {
                        jsonObject = new JSONObject();
                        plane.getCenterPose().toMatrix(this._planeMatrix, 0);
                        jsonObject.put("modelMatrix", (Object)new JSONArray((Object)this._planeMatrix));
                        jsonObject.put("identifier", (Object)Integer.toHexString(plane.hashCode()));
                        final JSONArray jsonArray4 = new JSONArray();
                        jsonArray4.put((double)plane.getExtentX());
                        jsonArray4.put((double)plane.getExtentZ());
                        jsonObject.put("extent", (Object)jsonArray4);
                        getPlaneVertices(plane, this._planeVertices);
                        jsonObject.put("vertices", (Object)new JSONArray((Object)this._planeVertices));
                        jsonObject.put("alignment", plane.getType().ordinal());
                        if (plane.getSubsumedBy() != null || this._detectedPlanes.get(plane.hashCode()) != null) {
                            break Label_0285;
                        }
                        this._detectedPlanes.append(plane.hashCode(), (Object)plane);
                        jsonArray.put((Object)jsonObject);
                    }
                    catch (JSONException ex) {
                        DeviceLog.error("Error updating AR planes: " + ex.getMessage());
                    }
                    continue;
                }
                if (plane.getSubsumedBy() != null) {
                    this._detectedPlanes.delete(plane.hashCode());
                    jsonArray3.put((Object)plane);
                }
                else {
                    jsonArray2.put((Object)jsonObject);
                }
            }
            if (jsonArray.length() > 0) {
                this.sendToWebView(AREvent.AR_PLANES_ADDED, jsonArray.toString());
            }
            if (jsonArray2.length() > 0) {
                this.sendToWebView(AREvent.AR_PLANES_UPDATED, jsonArray2.toString());
            }
            if (jsonArray3.length() > 0) {
                this.sendToWebView(AREvent.AR_PLANES_REMOVED, jsonArray3.toString());
            }
        }
    }
    
    public void addAnchor(final String s, final String s2) {
        if (this._session == null) {
            DeviceLog.warning("Session is null. Not adding anchor.");
            return;
        }
        final String[] split = s2.split(",");
        if (split.length != 16) {
            DeviceLog.warning("Matrix doesn't have 16 elements. Not adding anchor.");
            return;
        }
        final float[] array = new float[16];
        int i = 0;
        while (i < 16) {
            try {
                array[i] = Float.parseFloat(split[i]);
                ++i;
                continue;
            }
            catch (NumberFormatException ex) {
                DeviceLog.warning("Cannot parse matrix. Not adding anchor.");
                return;
            }
            break;
        }
        final float[] array2 = new float[4];
        matrix4x4ToQuaternion(array, array2);
        final float[] array3 = new float[3];
        matrix4x4ToTranslation(array, array3);
        this._anchors.put(s, this._session.createAnchor(new Pose(array3, array2)));
    }
    
    public float getArFar() {
        return this._arFar;
    }
    
    public float getArNear() {
        return this._arNear;
    }
    
    public boolean getShowCameraFrame() {
        return this._showCameraFrame;
    }
    
    public void onDrawFrame(GL10 update) {
        GLES20.glClear(16384);
        if (this._shouldSendResize) {
            final WebViewApp currentApp = WebViewApp.getCurrentApp();
            if (currentApp != null) {
                final float n = (float)currentApp.getWebView().getWidth();
                final float n2 = (float)currentApp.getWebView().getHeight();
                if (n > 0.0f && n2 > 0.0f) {
                    this.sendToWebView(AREvent.AR_WINDOW_RESIZED, n, n2);
                }
            }
            this._shouldSendResize = false;
        }
        if (!this._sessionRunning) {
            return;
        }
        Camera camera;
        long currentTimeMillis;
        while (true) {
            this._session.setCameraTextureName(this._backgroundRenderer.getTextureId());
            this._displayRotationHelper.updateSessionIfNeeded(this._session);
            try {
                update = (GL10)this._session.update();
                camera = ((Frame)update).getCamera();
                if (!this._showCameraFrame) {
                    return;
                }
            }
            catch (CameraNotAvailableException ex) {
                this.sendToWebView(AREvent.AR_ERROR, ex.getMessage());
                return;
            }
            catch (NotYetAvailableException ex2) {
                this.sendToWebView(AREvent.AR_ERROR, ex2.getMessage());
                return;
            }
            this._backgroundRenderer.draw((Frame)update);
            currentTimeMillis = System.currentTimeMillis();
            if (this._timeOfLastDrawnCameraFrame == 0L) {
                this._timeOfLastDrawnCameraFrame = currentTimeMillis;
            }
            if (currentTimeMillis - this._timeOfLastDrawnCameraFrame >= 500L || this._drawNextCameraFrame) {
                break;
            }
            return;
        }
        this._timeOfLastDrawnCameraFrame = currentTimeMillis;
        this._drawNextCameraFrame = false;
        camera.getProjectionMatrix(this._projectionMatrixArray, 0, this._arNear, this._arFar);
        camera.getViewMatrix(this._viewMatrixArray, 0);
        final float pixelIntensity = ((Frame)update).getLightEstimate().getPixelIntensity();
        final Pose displayOrientedPose = camera.getDisplayOrientedPose();
        displayOrientedPose.getRotationQuaternion(this._orientationArray, 0);
        while (true) {
            try {
                this._position.put(0, (double)displayOrientedPose.tx());
                this._position.put(1, (double)displayOrientedPose.ty());
                this._position.put(2, (double)displayOrientedPose.tz());
                this._frameInfo.put("position", (Object)this._position);
                for (int i = 0; i < 4; ++i) {
                    this._orientation.put(i, (double)this._orientationArray[i]);
                }
                this._frameInfo.put("orientation", (Object)this._orientation);
                for (int j = 0; j < 16; ++j) {
                    this._viewMatrix.put(j, (double)this._viewMatrixArray[j]);
                }
                this._frameInfo.put("viewMatrix", (Object)this._viewMatrix);
                for (int k = 0; k < 16; ++k) {
                    this._projectionMatrix.put(k, (double)this._projectionMatrixArray[k]);
                }
                this._frameInfo.put("projectionMatrix", (Object)this._projectionMatrix);
                this._lightEstimate.put("ambientIntensity", (double)pixelIntensity);
                this._lightEstimate.put("state", (Object)((Frame)update).getLightEstimate().getState().toString());
                this._frameInfo.put("lightEstimate", (Object)this._lightEstimate);
                this.sendToWebView(AREvent.AR_FRAME_UPDATED, this._frameInfo.toString());
                this.updatePlanes(((Frame)update).getUpdatedTrackables((Class)Plane.class));
                DeviceLog.debug(update.toString());
            }
            catch (JSONException ex3) {
                continue;
            }
            break;
        }
    }
    
    public void onPause() {
        super.onPause();
        while (true) {
            if (this._session == null || !this._sessionRunning) {
                break Label_0035;
            }
            try {
                this._sessionWasRunning = true;
                this._sessionRunning = false;
                this._session.pause();
                this._displayRotationHelper.onPause();
            }
            catch (Exception ex) {
                DeviceLog.error("Error pausing AR session: " + ex.getMessage());
                continue;
            }
            break;
        }
    }
    
    public void onResume() {
        super.onResume();
        while (true) {
            if (this._session == null || !this._sessionWasRunning) {
                break Label_0030;
            }
            try {
                this._session.resume();
                this._sessionRunning = true;
                this._displayRotationHelper.onResume();
            }
            catch (Exception ex) {
                DeviceLog.error("Error resuming AR session: " + ex.getMessage());
                continue;
            }
            break;
        }
    }
    
    public void onSurfaceChanged(final GL10 gl10, final int n, final int n2) {
        GLES20.glViewport(0, 0, n, n2);
        this._displayRotationHelper.onSurfaceChanged(n, n2);
        this.sendToWebView(AREvent.AR_WINDOW_RESIZED, Float.valueOf(n), Float.valueOf(n2));
    }
    
    public void onSurfaceCreated(final GL10 gl10, final EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this._backgroundRenderer.createOnGlThread();
        GLES20.glClear(16384);
    }
    
    public void pauseSession() {
        if (this._sessionRunning) {
            this._session.pause();
        }
    }
    
    public void removeAnchor(final String s) {
        if (this._anchors.containsKey(s)) {
            this._anchors.get(s).detach();
            this._anchors.remove(s);
            return;
        }
        DeviceLog.warning("Anchor with identifier: " + s + " doesn't exist.");
    }
    
    public void restartSession(final JSONObject jsonObject) throws JSONException {
        Label_0027: {
            if (this._session != null) {
                break Label_0027;
            }
            try {
                this._session = new Session(this.getContext());
                this._shouldSendResize = true;
                this._session.configure(ARUtils.createConfiguration(jsonObject.getJSONObject("configuration"), this._session));
                this._session.resume();
                this._sessionRunning = true;
                this._displayRotationHelper.onResume();
            }
            catch (Exception ex) {
                DeviceLog.debug("Error creating ARCore session");
            }
        }
    }
    
    public void setArFar(final float arFar) {
        this._arFar = arFar;
    }
    
    public void setArNear(final float arNear) {
        this._arNear = arNear;
    }
    
    public void setDrawNextCameraFrame() {
        this._drawNextCameraFrame = true;
    }
    
    public void setShowCameraFrame(final boolean showCameraFrame) {
        this._showCameraFrame = showCameraFrame;
    }
}
