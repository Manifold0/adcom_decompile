package com.unity3d.services.banners.view;

import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.ironsource.sdk.constants.Constants.ParametersKeys;

public enum BannerPosition {
    TOP_LEFT(new int[]{10, 9}, 51),
    TOP_CENTER(new int[]{10, 14}, 49),
    TOP_RIGHT(new int[]{10, 11}, 53),
    BOTTOM_LEFT(new int[]{12, 9}, 83),
    BOTTOM_CENTER(new int[]{12, 14}, 81),
    BOTTOM_RIGHT(new int[]{12, 11}, 85),
    CENTER(new int[]{13}, 17),
    NONE(new int[0], 0);
    
    private int _gravity;
    private final int[] _rules;

    private BannerPosition(int[] rules, int gravity) {
        this._rules = rules;
        this._gravity = gravity;
    }

    public static BannerPosition fromString(String in) {
        if (in == null || in.equals(ParametersKeys.ORIENTATION_NONE)) {
            return NONE;
        }
        if (in.equals("topleft")) {
            return TOP_LEFT;
        }
        if (in.equals("topright")) {
            return TOP_RIGHT;
        }
        if (in.equals("topcenter")) {
            return TOP_CENTER;
        }
        if (in.equals("bottomleft")) {
            return BOTTOM_LEFT;
        }
        if (in.equals("bottomright")) {
            return BOTTOM_RIGHT;
        }
        if (in.equals("bottomcenter")) {
            return BOTTOM_CENTER;
        }
        if (in.equals("center")) {
            return CENTER;
        }
        return NONE;
    }

    public LayoutParams addLayoutRules(RelativeLayout.LayoutParams params) {
        for (int rule : this._rules) {
            params.addRule(rule);
        }
        return params;
    }

    public int getGravity() {
        return this._gravity;
    }
}
