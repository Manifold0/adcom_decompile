// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ar.view;

import android.os.Bundle;
import android.view.View;
import com.unity3d.services.core.misc.ViewUtilities;
import android.content.Context;
import com.unity3d.services.ar.ARCheck;
import com.unity3d.services.ads.adunit.AdUnitActivity;
import com.unity3d.services.ads.adunit.IAdUnitViewHandler;

public class ARViewHandler implements IAdUnitViewHandler
{
    private ARView _arView;
    
    @Override
    public boolean create(final AdUnitActivity adUnitActivity) {
        if (ARCheck.isFrameworkPresent() && this._arView == null) {
            this._arView = new ARView((Context)adUnitActivity);
            return true;
        }
        return true;
    }
    
    @Override
    public boolean destroy() {
        if (this._arView != null) {
            ViewUtilities.removeViewFromParent((View)this._arView);
        }
        this._arView = null;
        return true;
    }
    
    @Override
    public View getView() {
        return (View)this._arView;
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
        if (this._arView != null) {
            this._arView.onPause();
        }
    }
    
    @Override
    public void onResume(final AdUnitActivity adUnitActivity) {
        if (this._arView != null) {
            this._arView.onResume();
        }
    }
    
    @Override
    public void onStart(final AdUnitActivity adUnitActivity) {
    }
    
    @Override
    public void onStop(final AdUnitActivity adUnitActivity) {
    }
}
