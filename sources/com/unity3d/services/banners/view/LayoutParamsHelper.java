package com.unity3d.services.banners.view;

import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

class LayoutParamsHelper {
    LayoutParamsHelper() {
    }

    static LayoutParams updateLayoutParamsForPosition(LayoutParams params, BannerPosition position) {
        if (params instanceof FrameLayout.LayoutParams) {
            return updateFrameLayoutParamsForPosition((FrameLayout.LayoutParams) params, position);
        }
        if (params instanceof RelativeLayout.LayoutParams) {
            return updateRelativeLayoutParamsForPosition((RelativeLayout.LayoutParams) params, position);
        }
        return params;
    }

    private static LayoutParams updateRelativeLayoutParamsForPosition(RelativeLayout.LayoutParams params, BannerPosition position) {
        return position.addLayoutRules(params);
    }

    private static LayoutParams updateFrameLayoutParamsForPosition(FrameLayout.LayoutParams params, BannerPosition position) {
        params.gravity = position.getGravity();
        return params;
    }
}
