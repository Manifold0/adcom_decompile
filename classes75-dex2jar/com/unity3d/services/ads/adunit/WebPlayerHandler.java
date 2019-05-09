// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.adunit;

import android.os.Bundle;
import android.view.View;
import com.unity3d.services.core.misc.ViewUtilities;
import android.content.Context;
import com.unity3d.services.ads.webplayer.WebPlayer;

public class WebPlayerHandler implements IAdUnitViewHandler
{
    private WebPlayer _webPlayer;
    
    @Override
    public boolean create(final AdUnitActivity adUnitActivity) {
        if (this._webPlayer == null) {
            (this._webPlayer = new WebPlayer((Context)adUnitActivity, "webplayer", com.unity3d.services.ads.api.WebPlayer.getWebSettings(), com.unity3d.services.ads.api.WebPlayer.getWebPlayerSettings())).setEventSettings(com.unity3d.services.ads.api.WebPlayer.getWebPlayerEventSettings());
        }
        return true;
    }
    
    @Override
    public boolean destroy() {
        if (this._webPlayer != null) {
            ViewUtilities.removeViewFromParent((View)this._webPlayer);
        }
        this._webPlayer = null;
        return true;
    }
    
    @Override
    public View getView() {
        return (View)this._webPlayer;
    }
    
    @Override
    public void onCreate(final AdUnitActivity adUnitActivity, final Bundle bundle) {
        this.create(adUnitActivity);
    }
    
    @Override
    public void onDestroy(final AdUnitActivity adUnitActivity) {
        if (adUnitActivity.isFinishing()) {
            this.destroy();
        }
    }
    
    @Override
    public void onPause(final AdUnitActivity adUnitActivity) {
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
