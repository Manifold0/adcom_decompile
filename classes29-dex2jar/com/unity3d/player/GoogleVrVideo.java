// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.view.Surface;

public interface GoogleVrVideo
{
    void deregisterGoogleVrVideoListener(final GoogleVrVideoCallbacks p0);
    
    void registerGoogleVrVideoListener(final GoogleVrVideoCallbacks p0);
    
    void setVideoLocationTransform(final float[] p0);
    
    public interface GoogleVrVideoCallbacks
    {
        void onFrameAvailable();
        
        void onSurfaceAvailable(final Surface p0);
        
        void onSurfaceUnavailable();
    }
}
