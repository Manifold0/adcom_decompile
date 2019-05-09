// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.adunit;

public class AdUnitMotionEvent
{
    private int _action;
    private int _deviceId;
    private long _eventTime;
    private boolean _isObscured;
    private float _pressure;
    private float _size;
    private int _source;
    private int _toolType;
    private float _x;
    private float _y;
    
    public AdUnitMotionEvent(final int action, final boolean isObscured, final int toolType, final int source, final int deviceId, final float x, final float y, final long eventTime, final float pressure, final float size) {
        this._action = action;
        this._isObscured = isObscured;
        this._toolType = toolType;
        this._source = source;
        this._deviceId = deviceId;
        this._x = x;
        this._y = y;
        this._eventTime = eventTime;
        this._pressure = pressure;
        this._size = size;
    }
    
    public int getAction() {
        return this._action;
    }
    
    public int getDeviceId() {
        return this._deviceId;
    }
    
    public long getEventTime() {
        return this._eventTime;
    }
    
    public float getPressure() {
        return this._pressure;
    }
    
    public float getSize() {
        return this._size;
    }
    
    public int getSource() {
        return this._source;
    }
    
    public int getToolType() {
        return this._toolType;
    }
    
    public float getX() {
        return this._x;
    }
    
    public float getY() {
        return this._y;
    }
    
    public boolean isObscured() {
        return this._isObscured;
    }
}
