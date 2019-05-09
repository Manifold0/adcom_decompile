package com.unity3d.services.ads.adunit;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.unity3d.services.ads.api.VideoPlayer;
import com.unity3d.services.ads.video.VideoPlayerView;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.ViewUtilities;

public class VideoPlayerHandler implements IAdUnitViewHandler {
    private RelativeLayout _videoContainer;
    private VideoPlayerView _videoView;

    public boolean create(AdUnitActivity activity) {
        DeviceLog.entered();
        if (this._videoContainer == null) {
            this._videoContainer = new RelativeLayout(activity);
        }
        if (this._videoView == null) {
            this._videoView = new VideoPlayerView(activity);
            LayoutParams params = new LayoutParams(-1, -1);
            params.addRule(13);
            this._videoView.setLayoutParams(params);
            this._videoContainer.addView(this._videoView);
            VideoPlayer.setVideoPlayerView(this._videoView);
        }
        return true;
    }

    public boolean destroy() {
        DeviceLog.entered();
        if (this._videoView != null) {
            this._videoView.stopVideoProgressTimer();
            this._videoView.stopPlayback();
            ViewUtilities.removeViewFromParent(this._videoView);
            if (this._videoView.equals(VideoPlayer.getVideoPlayerView())) {
                VideoPlayer.setVideoPlayerView(null);
            }
            this._videoView = null;
        }
        if (this._videoContainer != null) {
            ViewUtilities.removeViewFromParent(this._videoContainer);
            this._videoContainer = null;
        }
        return true;
    }

    public View getView() {
        return this._videoContainer;
    }

    public void onCreate(AdUnitActivity activity, Bundle savedInstanceState) {
        create(activity);
    }

    public void onStart(AdUnitActivity activity) {
    }

    public void onStop(AdUnitActivity activity) {
    }

    public void onResume(AdUnitActivity activity) {
    }

    public void onPause(AdUnitActivity activity) {
        destroy();
    }

    public void onDestroy(AdUnitActivity activity) {
    }
}
