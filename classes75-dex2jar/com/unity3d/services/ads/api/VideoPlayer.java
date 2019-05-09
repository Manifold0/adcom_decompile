// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.api;

import com.unity3d.services.ads.video.VideoPlayerEvent;
import com.unity3d.services.core.webview.WebViewEventCategory;
import android.os.Build$VERSION;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.ads.video.VideoPlayerError;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.ads.video.VideoPlayerView;

public class VideoPlayer
{
    private static VideoPlayerView _videoPlayerView;
    
    @WebViewExposed
    public static void getCurrentPosition(final WebViewCallback webViewCallback) {
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(getVideoPlayerView().getCurrentPosition());
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void getProgressEventInterval(final WebViewCallback webViewCallback) {
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(getVideoPlayerView().getProgressEventInterval());
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    public static VideoPlayerView getVideoPlayerView() {
        return VideoPlayer._videoPlayerView;
    }
    
    @WebViewExposed
    public static void getVolume(final WebViewCallback webViewCallback) {
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(getVideoPlayerView().getVolume());
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void pause(final WebViewCallback webViewCallback) {
        DeviceLog.debug("Pausing current video");
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (VideoPlayer.getVideoPlayerView() != null) {
                    VideoPlayer.getVideoPlayerView().pause();
                }
            }
        });
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void play(final WebViewCallback webViewCallback) {
        DeviceLog.debug("Starting playback of prepared video");
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (VideoPlayer.getVideoPlayerView() != null) {
                    VideoPlayer.getVideoPlayerView().play();
                }
            }
        });
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void prepare(final String s, final Double n, final WebViewCallback webViewCallback) {
        prepare(s, n, 0, webViewCallback);
    }
    
    @WebViewExposed
    public static void prepare(final String s, final Double n, final Integer n2, final WebViewCallback webViewCallback) {
        DeviceLog.debug("Preparing video for playback: " + s);
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (VideoPlayer.getVideoPlayerView() != null) {
                    VideoPlayer.getVideoPlayerView().prepare(s, n.floatValue(), n2);
                }
            }
        });
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(s);
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void seekTo(final Integer n, final WebViewCallback webViewCallback) {
        DeviceLog.debug("Seeking video to time: " + n);
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (VideoPlayer.getVideoPlayerView() != null) {
                    VideoPlayer.getVideoPlayerView().seekTo(n);
                }
            }
        });
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setInfoListenerEnabled(final boolean infoListenerEnabled, final WebViewCallback webViewCallback) {
        if (Build$VERSION.SDK_INT <= 16) {
            webViewCallback.error(VideoPlayerError.API_LEVEL_ERROR, Build$VERSION.SDK_INT, infoListenerEnabled);
            return;
        }
        if (getVideoPlayerView() != null) {
            getVideoPlayerView().setInfoListenerEnabled(infoListenerEnabled);
            webViewCallback.invoke(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.INFO, infoListenerEnabled);
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setProgressEventInterval(final Integer n, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (VideoPlayer.getVideoPlayerView() != null) {
                    VideoPlayer.getVideoPlayerView().setProgressEventInterval(n);
                }
            }
        });
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    public static void setVideoPlayerView(final VideoPlayerView videoPlayerView) {
        VideoPlayer._videoPlayerView = videoPlayerView;
    }
    
    @WebViewExposed
    public static void setVolume(final Double n, final WebViewCallback webViewCallback) {
        DeviceLog.debug("Setting video volume: " + n);
        if (getVideoPlayerView() != null) {
            getVideoPlayerView().setVolume(n.floatValue());
            webViewCallback.invoke(n);
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void stop(final WebViewCallback webViewCallback) {
        DeviceLog.debug("Stopping current video");
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (VideoPlayer.getVideoPlayerView() != null) {
                    VideoPlayer.getVideoPlayerView().stop();
                }
            }
        });
        if (getVideoPlayerView() != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }
}
