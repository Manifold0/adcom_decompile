// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.sensorinfo;

import com.unity3d.services.core.properties.ClientProperties;
import android.hardware.SensorManager;
import org.json.JSONException;
import com.unity3d.services.core.log.DeviceLog;
import org.json.JSONObject;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;

public class SensorInfoListener implements SensorEventListener
{
    private static SensorInfoListener _accelerometerListener;
    private static Sensor _accelerometerSensor;
    private static SensorEvent _latestAccelerometerEvent;
    
    static {
        SensorInfoListener._accelerometerListener = null;
        SensorInfoListener._accelerometerSensor = null;
        SensorInfoListener._latestAccelerometerEvent = null;
    }
    
    public static JSONObject getAccelerometerData() {
        JSONObject jsonObject = null;
        if (SensorInfoListener._latestAccelerometerEvent == null) {
            return jsonObject;
        }
        jsonObject = new JSONObject();
        try {
            jsonObject.put("x", (double)SensorInfoListener._latestAccelerometerEvent.values[0]);
            jsonObject.put("y", (double)SensorInfoListener._latestAccelerometerEvent.values[1]);
            jsonObject.put("z", (double)SensorInfoListener._latestAccelerometerEvent.values[2]);
            return jsonObject;
        }
        catch (JSONException ex) {
            DeviceLog.exception("JSON error while constructing accelerometer data", (Exception)ex);
            return jsonObject;
        }
    }
    
    public static boolean isAccelerometerListenerActive() {
        return SensorInfoListener._accelerometerListener != null;
    }
    
    public static boolean startAccelerometerListener(final int n) {
        if (SensorInfoListener._accelerometerListener == null) {
            SensorInfoListener._accelerometerListener = new SensorInfoListener();
        }
        final SensorManager sensorManager = (SensorManager)ClientProperties.getApplicationContext().getSystemService("sensor");
        SensorInfoListener._accelerometerSensor = sensorManager.getDefaultSensor(1);
        return sensorManager.registerListener((SensorEventListener)SensorInfoListener._accelerometerListener, SensorInfoListener._accelerometerSensor, n);
    }
    
    public static void stopAccelerometerListener() {
        if (SensorInfoListener._accelerometerListener != null) {
            ((SensorManager)ClientProperties.getApplicationContext().getSystemService("sensor")).unregisterListener((SensorEventListener)SensorInfoListener._accelerometerListener);
            SensorInfoListener._accelerometerListener = null;
        }
    }
    
    public void onAccuracyChanged(final Sensor sensor, final int n) {
    }
    
    public void onSensorChanged(final SensorEvent latestAccelerometerEvent) {
        if (latestAccelerometerEvent.sensor.getType() == 1) {
            SensorInfoListener._latestAccelerometerEvent = latestAccelerometerEvent;
        }
    }
}
