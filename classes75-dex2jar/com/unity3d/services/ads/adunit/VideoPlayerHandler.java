// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.adunit;

import android.os.Bundle;
import com.unity3d.services.core.misc.ViewUtilities;
import com.unity3d.services.ads.api.VideoPlayer;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.ads.video.VideoPlayerView;
import android.widget.RelativeLayout;

public class VideoPlayerHandler implements IAdUnitViewHandler
{
    private RelativeLayout _videoContainer;
    private VideoPlayerView _videoView;
    
    @Override
    public boolean create(final AdUnitActivity adUnitActivity) {
        DeviceLog.entered();
        if (this._videoContainer == null) {
            this._videoContainer = new RelativeLayout((Context)adUnitActivity);
        }
        if (this._videoView == null) {
            this._videoView = new VideoPlayerView((Context)adUnitActivity);
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this._videoView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this._videoContainer.addView((View)this._videoView);
            VideoPlayer.setVideoPlayerView(this._videoView);
        }
        return true;
    }
    
    @Override
    public boolean destroy() {
        DeviceLog.entered();
        if (this._videoView != null) {
            this._videoView.stopVideoProgressTimer();
            this._videoView.stopPlayback();
            ViewUtilities.removeViewFromParent((View)this._videoView);
            if (this._videoView.equals(VideoPlayer.getVideoPlayerView())) {
                VideoPlayer.setVideoPlayerView(null);
            }
            this._videoView = null;
        }
        if (this._videoContainer != null) {
            ViewUtilities.removeViewFromParent((View)this._videoContainer);
            this._videoContainer = null;
        }
        return true;
    }
    
    @Override
    public View getView() {
        return (View)this._videoContainer;
    }
    
    @Override
    public void onCreate(final AdUnitActivity adUnitActivity, final Bundle bundle) {
        this.create(adUnitActivity);
    }
    
    @Override
    public void onDestroy(final AdUnitActivity adUnitActivity) {
    }
    
    @Override
    public void onPause(final AdUnitActivity adUnitActivity) {
        this.destroy();
    }
    
    @Override
    public void onResume(final AdUnitActivity adUnitActivity) {
    }
    
    @Override
    public void onStart(final AdUnitActivity adUnitActivity) {
    }
    
    @Override
    public void onStop(final AdUnitActivity adUnitActivity) {
    }
}
