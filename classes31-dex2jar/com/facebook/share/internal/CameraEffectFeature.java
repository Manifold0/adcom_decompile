// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;

public enum CameraEffectFeature implements DialogFeature
{
    SHARE_CAMERA_EFFECT(20170417);
    
    private int minVersion;
    
    private CameraEffectFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.CAMERA_EFFECT";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
