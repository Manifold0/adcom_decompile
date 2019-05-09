// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.presenter;

import com.vungle.warren.DirectDownloadAdapter;
import android.support.annotation.Nullable;
import android.widget.VideoView;
import android.webkit.WebViewClient;
import android.os.Bundle;
import com.vungle.warren.ui.AdView;
import com.vungle.warren.ui.JavascriptBridge;

public interface AdvertisementPresenter extends ActionHandler
{
    void attach(final AdView p0);
    
    void generateSaveState(final Bundle p0);
    
    WebViewClient getWebViewClient();
    
    boolean handleExit(final String p0);
    
    void initializeViewabilityTracker(final int p0, final VideoView p1);
    
    void notifyPropertiesChanged();
    
    void onProgressUpdate(final int p0);
    
    void play();
    
    void prepare(final Bundle p0);
    
    void reportAction(final String p0, @Nullable final String p1);
    
    void reportError(final String p0);
    
    void restoreFromSave(final Bundle p0);
    
    void setAdVisibility(final boolean p0);
    
    void setDirectDownloadAdapter(final DirectDownloadAdapter p0);
    
    void setEventListener(final EventListener p0);
    
    void stop(final boolean p0, final boolean p1);
    
    void stopViewabilityTracker();
    
    public interface EventListener
    {
        void onError(final Throwable p0);
        
        void onNext(final String p0, final String p1);
    }
}
