// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners.view;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;

public enum BannerPosition
{
    BOTTOM_CENTER(new int[] { 12, 14 }, 81), 
    BOTTOM_LEFT(new int[] { 12, 9 }, 83), 
    BOTTOM_RIGHT(new int[] { 12, 11 }, 85), 
    CENTER(new int[] { 13 }, 17), 
    NONE(new int[0], 0), 
    TOP_CENTER(new int[] { 10, 14 }, 49), 
    TOP_LEFT(new int[] { 10, 9 }, 51), 
    TOP_RIGHT(new int[] { 10, 11 }, 53);
    
    private int _gravity;
    private final int[] _rules;
    
    private BannerPosition(final int[] rules, final int gravity) {
        this._rules = rules;
        this._gravity = gravity;
    }
    
    public static BannerPosition fromString(final String s) {
        if (s == null || s.equals("none")) {
            return BannerPosition.NONE;
        }
        if (s.equals("topleft")) {
            return BannerPosition.TOP_LEFT;
        }
        if (s.equals("topright")) {
            return BannerPosition.TOP_RIGHT;
        }
        if (s.equals("topcenter")) {
            return BannerPosition.TOP_CENTER;
        }
        if (s.equals("bottomleft")) {
            return BannerPosition.BOTTOM_LEFT;
        }
        if (s.equals("bottomright")) {
            return BannerPosition.BOTTOM_RIGHT;
        }
        if (s.equals("bottomcenter")) {
            return BannerPosition.BOTTOM_CENTER;
        }
        if (s.equals("center")) {
            return BannerPosition.CENTER;
        }
        return BannerPosition.NONE;
    }
    
    public ViewGroup$LayoutParams addLayoutRules(final RelativeLayout$LayoutParams relativeLayout$LayoutParams) {
        final int[] rules = this._rules;
        for (int length = rules.length, i = 0; i < length; ++i) {
            relativeLayout$LayoutParams.addRule(rules[i]);
        }
        return (ViewGroup$LayoutParams)relativeLayout$LayoutParams;
    }
    
    public int getGravity() {
        return this._gravity;
    }
}
