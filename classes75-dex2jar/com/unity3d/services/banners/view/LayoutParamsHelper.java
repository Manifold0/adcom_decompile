// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners.view;

import android.widget.RelativeLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;

class LayoutParamsHelper
{
    private static ViewGroup$LayoutParams updateFrameLayoutParamsForPosition(final FrameLayout$LayoutParams frameLayout$LayoutParams, final BannerPosition bannerPosition) {
        frameLayout$LayoutParams.gravity = bannerPosition.getGravity();
        return (ViewGroup$LayoutParams)frameLayout$LayoutParams;
    }
    
    static ViewGroup$LayoutParams updateLayoutParamsForPosition(final ViewGroup$LayoutParams viewGroup$LayoutParams, final BannerPosition bannerPosition) {
        ViewGroup$LayoutParams updateFrameLayoutParamsForPosition;
        if (viewGroup$LayoutParams instanceof FrameLayout$LayoutParams) {
            updateFrameLayoutParamsForPosition = updateFrameLayoutParamsForPosition((FrameLayout$LayoutParams)viewGroup$LayoutParams, bannerPosition);
        }
        else {
            updateFrameLayoutParamsForPosition = viewGroup$LayoutParams;
            if (viewGroup$LayoutParams instanceof RelativeLayout$LayoutParams) {
                return updateRelativeLayoutParamsForPosition((RelativeLayout$LayoutParams)viewGroup$LayoutParams, bannerPosition);
            }
        }
        return updateFrameLayoutParamsForPosition;
    }
    
    private static ViewGroup$LayoutParams updateRelativeLayoutParamsForPosition(final RelativeLayout$LayoutParams relativeLayout$LayoutParams, final BannerPosition bannerPosition) {
        return bannerPosition.addLayoutRules(relativeLayout$LayoutParams);
    }
}
