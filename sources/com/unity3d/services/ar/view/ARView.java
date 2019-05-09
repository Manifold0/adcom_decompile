package com.unity3d.services.ar.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Build.VERSION;
import android.util.SparseArray;
import com.google.ar.core.Anchor;
import com.google.ar.core.Camera;
import com.google.ar.core.Frame;
import com.google.ar.core.Plane;
import com.google.ar.core.Pose;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.NotYetAvailableException;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.unity3d.services.ar.AREvent;
import com.unity3d.services.ar.ARUtils;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(19)
public class ARView extends GLSurfaceView implements Renderer {
    private static final long FRAME_UPDATE_TIMEOUT = 500;
    private Map<String, Anchor> _anchors = new HashMap();
    private float _arFar = 10000.0f;
    private float _arNear = 0.01f;
    private BackgroundRenderer _backgroundRenderer = new BackgroundRenderer();
    private SparseArray<Plane> _detectedPlanes = new SparseArray();
    private DisplayRotationHelper _displayRotationHelper;
    private boolean _drawNextCameraFrame;
    JSONObject _frameInfo = new JSONObject();
    JSONObject _lightEstimate = new JSONObject();
    JSONArray _orientation = new JSONArray();
    float[] _orientationArray = new float[4];
    float[] _planeMatrix = new float[16];
    float[] _planeVertices = new float[12];
    JSONArray _position = new JSONArray();
    JSONArray _projectionMatrix = new JSONArray();
    float[] _projectionMatrixArray = new float[16];
    private Session _session = null;
    private boolean _sessionRunning;
    private boolean _sessionWasRunning;
    private boolean _shouldSendResize = false;
    private boolean _showCameraFrame;
    private long _timeOfLastDrawnCameraFrame;
    JSONArray _viewMatrix = new JSONArray();
    float[] _viewMatrixArray = new float[16];

    public ARView(Context context) {
        super(context);
        if (VERSION.SDK_INT > 11) {
            setPreserveEGLContextOnPause(true);
        }
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        setRenderer(this);
        setRenderMode(1);
        this._displayRotationHelper = new DisplayRotationHelper(context);
    }

    public void onResume() {
        super.onResume();
        if (this._session != null && this._sessionWasRunning) {
            try {
                this._session.resume();
                this._sessionRunning = true;
            } catch (Exception e) {
                DeviceLog.error("Error resuming AR session: " + e.getMessage());
            }
        }
        this._displayRotationHelper.onResume();
    }

    public void onPause() {
        super.onPause();
        if (this._session != null && this._sessionRunning) {
            try {
                this._sessionWasRunning = true;
                this._sessionRunning = false;
                this._session.pause();
            } catch (Exception e) {
                DeviceLog.error("Error pausing AR session: " + e.getMessage());
            }
        }
        this._displayRotationHelper.onPause();
    }

    private void sendToWebView(AREvent eventType, Object... params) {
        WebViewApp webViewApp = WebViewApp.getCurrentApp();
        if (webViewApp != null && webViewApp.isWebAppLoaded()) {
            webViewApp.sendEvent(WebViewEventCategory.AR, eventType, params);
        }
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        this._backgroundRenderer.createOnGlThread();
        GLES20.glClear(16384);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        this._displayRotationHelper.onSurfaceChanged(width, height);
        sendToWebView(AREvent.AR_WINDOW_RESIZED, Float.valueOf((float) width), Float.valueOf((float) height));
    }

    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(16384);
        if (this._shouldSendResize) {
            WebViewApp webViewApp = WebViewApp.getCurrentApp();
            if (webViewApp != null) {
                float height = (float) webViewApp.getWebView().getHeight();
                if (((float) webViewApp.getWebView().getWidth()) > 0.0f && height > 0.0f) {
                    sendToWebView(AREvent.AR_WINDOW_RESIZED, Float.valueOf(width), Float.valueOf(height));
                }
            }
            this._shouldSendResize = false;
        }
        if (this._sessionRunning) {
            this._session.setCameraTextureName(this._backgroundRenderer.getTextureId());
            this._displayRotationHelper.updateSessionIfNeeded(this._session);
            try {
                Frame frame = this._session.update();
                Camera camera = frame.getCamera();
                if (this._showCameraFrame) {
                    this._backgroundRenderer.draw(frame);
                    long currentTime = System.currentTimeMillis();
                    if (this._timeOfLastDrawnCameraFrame == 0) {
                        this._timeOfLastDrawnCameraFrame = currentTime;
                    }
                    if (currentTime - this._timeOfLastDrawnCameraFrame >= FRAME_UPDATE_TIMEOUT || this._drawNextCameraFrame) {
                        this._timeOfLastDrawnCameraFrame = currentTime;
                        this._drawNextCameraFrame = false;
                        camera.getProjectionMatrix(this._projectionMatrixArray, 0, this._arNear, this._arFar);
                        camera.getViewMatrix(this._viewMatrixArray, 0);
                        float lightIntensity = frame.getLightEstimate().getPixelIntensity();
                        Pose pose = camera.getDisplayOrientedPose();
                        pose.getRotationQuaternion(this._orientationArray, 0);
                        try {
                            int i;
                            this._position.put(0, (double) pose.tx());
                            this._position.put(1, (double) pose.ty());
                            this._position.put(2, (double) pose.tz());
                            this._frameInfo.put(ParametersKeys.POSITION, this._position);
                            for (i = 0; i < 4; i++) {
                                this._orientation.put(i, (double) this._orientationArray[i]);
                            }
                            this._frameInfo.put("orientation", this._orientation);
                            for (i = 0; i < 16; i++) {
                                this._viewMatrix.put(i, (double) this._viewMatrixArray[i]);
                            }
                            this._frameInfo.put("viewMatrix", this._viewMatrix);
                            for (i = 0; i < 16; i++) {
                                this._projectionMatrix.put(i, (double) this._projectionMatrixArray[i]);
                            }
                            this._frameInfo.put("projectionMatrix", this._projectionMatrix);
                            this._lightEstimate.put("ambientIntensity", (double) lightIntensity);
                            this._lightEstimate.put("state", frame.getLightEstimate().getState().toString());
                            this._frameInfo.put("lightEstimate", this._lightEstimate);
                        } catch (JSONException e) {
                        }
                        sendToWebView(AREvent.AR_FRAME_UPDATED, this._frameInfo.toString());
                        updatePlanes(frame.getUpdatedTrackables(Plane.class));
                        DeviceLog.debug(frame.toString());
                    }
                }
            } catch (CameraNotAvailableException e2) {
                sendToWebView(AREvent.AR_ERROR, e2.getMessage());
            } catch (NotYetAvailableException e3) {
                sendToWebView(AREvent.AR_ERROR, e3.getMessage());
            }
        }
    }

    private void updatePlanes(Collection<Plane> planes) {
        if (!planes.isEmpty()) {
            JSONArray addedPlanesArray = new JSONArray();
            JSONArray updatedPlanesArray = new JSONArray();
            JSONArray removedPlanesArray = new JSONArray();
            for (Plane plane : planes) {
                try {
                    JSONObject p = new JSONObject();
                    plane.getCenterPose().toMatrix(this._planeMatrix, 0);
                    p.put("modelMatrix", new JSONArray(this._planeMatrix));
                    p.put("identifier", Integer.toHexString(plane.hashCode()));
                    JSONArray planeExtentArray = new JSONArray();
                    planeExtentArray.put((double) plane.getExtentX());
                    planeExtentArray.put((double) plane.getExtentZ());
                    p.put("extent", planeExtentArray);
                    getPlaneVertices(plane, this._planeVertices);
                    p.put("vertices", new JSONArray(this._planeVertices));
                    p.put("alignment", plane.getType().ordinal());
                    if (plane.getSubsumedBy() == null && this._detectedPlanes.get(plane.hashCode()) == null) {
                        this._detectedPlanes.append(plane.hashCode(), plane);
                        addedPlanesArray.put(p);
                    } else if (plane.getSubsumedBy() != null) {
                        this._detectedPlanes.delete(plane.hashCode());
                        removedPlanesArray.put(plane);
                    } else {
                        updatedPlanesArray.put(p);
                    }
                } catch (JSONException e) {
                    DeviceLog.error("Error updating AR planes: " + e.getMessage());
                }
            }
            if (addedPlanesArray.length() > 0) {
                sendToWebView(AREvent.AR_PLANES_ADDED, addedPlanesArray.toString());
            }
            if (updatedPlanesArray.length() > 0) {
                sendToWebView(AREvent.AR_PLANES_UPDATED, updatedPlanesArray.toString());
            }
            if (removedPlanesArray.length() > 0) {
                sendToWebView(AREvent.AR_PLANES_REMOVED, removedPlanesArray.toString());
            }
        }
    }

    private static void getPlaneVertices(Plane plane, float[] planeVertices) {
        planeVertices[0] = plane.getExtentX() / 2.0f;
        planeVertices[1] = 0.0f;
        planeVertices[2] = plane.getExtentZ() / 2.0f;
        planeVertices[3] = (-plane.getExtentX()) / 2.0f;
        planeVertices[4] = 0.0f;
        planeVertices[5] = plane.getExtentZ() / 2.0f;
        planeVertices[6] = (-plane.getExtentX()) / 2.0f;
        planeVertices[7] = 0.0f;
        planeVertices[8] = (-plane.getExtentZ()) / 2.0f;
        planeVertices[9] = plane.getExtentX() / 2.0f;
        planeVertices[10] = 0.0f;
        planeVertices[11] = (-plane.getExtentZ()) / 2.0f;
    }

    public void restartSession(JSONObject properties) throws JSONException {
        if (this._session == null) {
            try {
                this._session = new Session(getContext());
                this._shouldSendResize = true;
            } catch (Exception e) {
                DeviceLog.debug("Error creating ARCore session");
                return;
            }
        }
        this._session.configure(ARUtils.createConfiguration(properties.getJSONObject("configuration"), this._session));
        this._session.resume();
        this._sessionRunning = true;
        this._displayRotationHelper.onResume();
    }

    public void pauseSession() {
        if (this._sessionRunning) {
            this._session.pause();
        }
    }

    public void setDrawNextCameraFrame() {
        this._drawNextCameraFrame = true;
    }

    public boolean getShowCameraFrame() {
        return this._showCameraFrame;
    }

    public void setShowCameraFrame(boolean showCameraFrame) {
        this._showCameraFrame = showCameraFrame;
    }

    public float getArNear() {
        return this._arNear;
    }

    public void setArNear(float arNear) {
        this._arNear = arNear;
    }

    public float getArFar() {
        return this._arFar;
    }

    public void setArFar(float arFar) {
        this._arFar = arFar;
    }

    public void addAnchor(String identifier, String matrix) {
        if (this._session == null) {
            DeviceLog.warning("Session is null. Not adding anchor.");
            return;
        }
        String[] floats = matrix.split(",");
        if (floats.length != 16) {
            DeviceLog.warning("Matrix doesn't have 16 elements. Not adding anchor.");
            return;
        }
        float[] anchorMatrix = new float[16];
        int i = 0;
        while (i < 16) {
            try {
                anchorMatrix[i] = Float.parseFloat(floats[i]);
                i++;
            } catch (NumberFormatException e) {
                DeviceLog.warning("Cannot parse matrix. Not adding anchor.");
                return;
            }
        }
        float[] quaternion = new float[4];
        matrix4x4ToQuaternion(anchorMatrix, quaternion);
        float[] translation = new float[3];
        matrix4x4ToTranslation(anchorMatrix, translation);
        this._anchors.put(identifier, this._session.createAnchor(new Pose(translation, quaternion)));
    }

    public void removeAnchor(String identifier) {
        if (this._anchors.containsKey(identifier)) {
            ((Anchor) this._anchors.get(identifier)).detach();
            this._anchors.remove(identifier);
            return;
        }
        DeviceLog.warning("Anchor with identifier: " + identifier + " doesn't exist.");
    }

    private static void matrix4x4ToQuaternion(float[] m, float[] q) {
        float t;
        if (m[10] < 0.0f) {
            if (m[0] > m[5]) {
                t = ((1.0f + m[0]) - m[5]) - m[10];
                q[0] = t;
                q[1] = m[1] + m[4];
                q[2] = m[8] + m[2];
                q[3] = m[6] - m[9];
            } else {
                t = ((1.0f - m[0]) + m[5]) - m[10];
                q[0] = m[1] + m[4];
                q[1] = t;
                q[2] = m[6] + m[9];
                q[3] = m[8] - m[2];
            }
        } else if (m[0] < (-m[5])) {
            t = ((1.0f - m[0]) - m[5]) + m[10];
            q[0] = m[8] + m[2];
            q[1] = m[6] + m[9];
            q[2] = t;
            q[3] = m[1] - m[4];
        } else {
            t = ((1.0f + m[0]) + m[5]) + m[10];
            q[0] = m[6] - m[9];
            q[1] = m[8] - m[2];
            q[2] = m[1] - m[4];
            q[3] = t;
        }
        q[0] = (float) (((double) q[0]) * (0.5d / Math.sqrt((double) t)));
        q[1] = (float) (((double) q[1]) * (0.5d / Math.sqrt((double) t)));
        q[2] = (float) (((double) q[2]) * (0.5d / Math.sqrt((double) t)));
        q[3] = (float) (((double) q[3]) * (0.5d / Math.sqrt((double) t)));
    }

    private static void matrix4x4ToTranslation(float[] m, float[] t) {
        t[0] = m[3];
        t[1] = m[7];
        t[2] = m[11];
    }
}
