package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos.CaptureAvailableResult;
import com.google.android.gms.games.video.Videos.CaptureCapabilitiesResult;
import com.google.android.gms.games.video.Videos.CaptureOverlayStateListener;
import com.google.android.gms.games.video.Videos.CaptureStateResult;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class VideosClient extends zzu {
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STARTED = 2;
    public static final int CAPTURE_OVERLAY_STATE_CAPTURE_STOPPED = 3;
    public static final int CAPTURE_OVERLAY_STATE_DISMISSED = 4;
    public static final int CAPTURE_OVERLAY_STATE_SHOWN = 1;
    private static final ResultConverter<CaptureAvailableResult, Boolean> zzex = new zzda();
    private static final ResultConverter<CaptureStateResult, CaptureState> zzey = new zzdb();
    private static final ResultConverter<CaptureCapabilitiesResult, VideoCapabilities> zzez = new zzdc();

    public interface OnCaptureOverlayStateListener extends CaptureOverlayStateListener {
        void onCaptureOverlayStateChanged(int i);
    }

    VideosClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    VideosClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<VideoCapabilities> getCaptureCapabilities() {
        return zzi.toTask(Games.Videos.getCaptureCapabilities(asGoogleApiClient()), zzez);
    }

    public Task<Intent> getCaptureOverlayIntent() {
        return doRead(new zzcw(this));
    }

    public Task<CaptureState> getCaptureState() {
        return zzi.toTask(Games.Videos.getCaptureState(asGoogleApiClient()), zzey);
    }

    public Task<Boolean> isCaptureAvailable(int i) {
        return zzi.toTask(Games.Videos.isCaptureAvailable(asGoogleApiClient(), i), zzex);
    }

    public Task<Boolean> isCaptureSupported() {
        return doRead(new zzcx(this));
    }

    public Task<Void> registerOnCaptureOverlayStateChangedListener(@NonNull OnCaptureOverlayStateListener onCaptureOverlayStateListener) {
        ListenerHolder registerListener = registerListener(onCaptureOverlayStateListener, OnCaptureOverlayStateListener.class.getSimpleName());
        return doRegisterEventListener(new zzcy(this, registerListener, registerListener), new zzcz(this, registerListener.getListenerKey()));
    }

    public Task<Boolean> unregisterOnCaptureOverlayStateChangedListener(@NonNull OnCaptureOverlayStateListener onCaptureOverlayStateListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(onCaptureOverlayStateListener, OnCaptureOverlayStateListener.class.getSimpleName()));
    }
}
