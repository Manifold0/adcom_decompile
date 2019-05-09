package com.unity3d.services.core.sensorinfo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.ClientProperties;
import org.json.JSONException;
import org.json.JSONObject;

public class SensorInfoListener implements SensorEventListener {
    private static SensorInfoListener _accelerometerListener = null;
    private static Sensor _accelerometerSensor = null;
    private static SensorEvent _latestAccelerometerEvent = null;

    public static boolean startAccelerometerListener(int sensorDelay) {
        if (_accelerometerListener == null) {
            _accelerometerListener = new SensorInfoListener();
        }
        SensorManager sensorManager = (SensorManager) ClientProperties.getApplicationContext().getSystemService("sensor");
        _accelerometerSensor = sensorManager.getDefaultSensor(1);
        return sensorManager.registerListener(_accelerometerListener, _accelerometerSensor, sensorDelay);
    }

    public static void stopAccelerometerListener() {
        if (_accelerometerListener != null) {
            ((SensorManager) ClientProperties.getApplicationContext().getSystemService("sensor")).unregisterListener(_accelerometerListener);
            _accelerometerListener = null;
        }
    }

    public static boolean isAccelerometerListenerActive() {
        return _accelerometerListener != null;
    }

    public static JSONObject getAccelerometerData() {
        JSONObject accelerometerData = null;
        if (_latestAccelerometerEvent != null) {
            accelerometerData = new JSONObject();
            try {
                accelerometerData.put("x", (double) _latestAccelerometerEvent.values[0]);
                accelerometerData.put("y", (double) _latestAccelerometerEvent.values[1]);
                accelerometerData.put("z", (double) _latestAccelerometerEvent.values[2]);
            } catch (JSONException e) {
                DeviceLog.exception("JSON error while constructing accelerometer data", e);
            }
        }
        return accelerometerData;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            _latestAccelerometerEvent = sensorEvent;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
