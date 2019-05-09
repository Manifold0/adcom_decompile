// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.TaskApiCall;
import android.content.Intent;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.internal.games.zzu;

public class VideosClient extends zzu
{
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STARTED = 2;
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STOPPED = 3;
    public static final int CAPTURE_OVERLAY_STATE_DISMISSED = 4;
    public static final int CAPTURE_OVERLAY_STATE_SHOWN = 1;
    private static final PendingResultUtil$ResultConverter<Videos.CaptureAvailableResult, Boolean> zzex;
    private static final PendingResultUtil$ResultConverter<Videos.CaptureStateResult, CaptureState> zzey;
    private static final PendingResultUtil$ResultConverter<Videos.CaptureCapabilitiesResult, VideoCapabilities> zzez;
    
    static {
        zzex = (PendingResultUtil$ResultConverter)new zzda();
        zzey = (PendingResultUtil$ResultConverter)new zzdb();
        zzez = (PendingResultUtil$ResultConverter)new zzdc();
    }
    
    VideosClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    VideosClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    public Task<VideoCapabilities> getCaptureCapabilities() {
        return zzi.toTask(Games.Videos.getCaptureCapabilities(this.asGoogleApiClient()), VideosClient.zzez);
    }
    
    public Task<Intent> getCaptureOverlayIntent() {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzcw(this));
    }
    
    public Task<CaptureState> getCaptureState() {
        return zzi.toTask(Games.Videos.getCaptureState(this.asGoogleApiClient()), VideosClient.zzey);
    }
    
    public Task<Boolean> isCaptureAvailable(final int n) {
        return zzi.toTask(Games.Videos.isCaptureAvailable(this.asGoogleApiClient(), n), VideosClient.zzex);
    }
    
    public Task<Boolean> isCaptureSupported() {
        return (Task<Boolean>)this.doRead((TaskApiCall)new zzcx(this));
    }
    
    public Task<Void> registerOnCaptureOverlayStateChangedListener(@NonNull final OnCaptureOverlayStateListener onCaptureOverlayStateListener) {
        final ListenerHolder registerListener = this.registerListener((Object)onCaptureOverlayStateListener, OnCaptureOverlayStateListener.class.getSimpleName());
        return (Task<Void>)this.doRegisterEventListener((RegisterListenerMethod)new zzcy(this, registerListener, registerListener), (UnregisterListenerMethod)new zzcz(this, registerListener.getListenerKey()));
    }
    
    public Task<Boolean> unregisterOnCaptureOverlayStateChangedListener(@NonNull final OnCaptureOverlayStateListener onCaptureOverlayStateListener) {
        return (Task<Boolean>)this.doUnregisterEventListener(ListenerHolders.createListenerKey((Object)onCaptureOverlayStateListener, OnCaptureOverlayStateListener.class.getSimpleName()));
    }
    
    public interface OnCaptureOverlayStateListener extends CaptureOverlayStateListener
    {
        void onCaptureOverlayStateChanged(final int p0);
    }
}
