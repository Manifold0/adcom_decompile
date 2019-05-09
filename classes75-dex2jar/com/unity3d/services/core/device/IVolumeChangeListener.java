// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.device;

public interface IVolumeChangeListener
{
    int getStreamType();
    
    void onVolumeChanged(final int p0);
}
