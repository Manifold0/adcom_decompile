// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.video;

import com.google.android.gms.common.api.Result;
import android.content.Intent;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface Videos
{
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STARTED = 2;
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STOPPED = 3;
    public static final int CAPTURE_OVERLAY_STATE_DISMISSED = 4;
    public static final int CAPTURE_OVERLAY_STATE_SHOWN = 1;
    
    PendingResult<CaptureCapabilitiesResult> getCaptureCapabilities(final GoogleApiClient p0);
    
    Intent getCaptureOverlayIntent(final GoogleApiClient p0);
    
    PendingResult<CaptureStateResult> getCaptureState(final GoogleApiClient p0);
    
    PendingResult<CaptureAvailableResult> isCaptureAvailable(final GoogleApiClient p0, final int p1);
    
    boolean isCaptureSupported(final GoogleApiClient p0);
    
    void registerCaptureOverlayStateChangedListener(final GoogleApiClient p0, final CaptureOverlayStateListener p1);
    
    void unregisterCaptureOverlayStateChangedListener(final GoogleApiClient p0);
    
    @Deprecated
    public interface CaptureAvailableResult extends Result
    {
        boolean isAvailable();
    }
    
    @Deprecated
    public interface CaptureCapabilitiesResult extends Result
    {
        VideoCapabilities getCapabilities();
    }
    
    @Deprecated
    public interface CaptureOverlayStateListener
    {
        void onCaptureOverlayStateChanged(final int p0);
    }
    
    @Deprecated
    public interface CaptureStateResult extends Result
    {
        CaptureState getCaptureState();
    }
}
