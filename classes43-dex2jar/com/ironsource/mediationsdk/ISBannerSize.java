// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

public class ISBannerSize
{
    public static final ISBannerSize BANNER;
    public static final ISBannerSize LARGE;
    public static final ISBannerSize RECTANGLE;
    public static final ISBannerSize SMART;
    private String mDescription;
    private int mHeight;
    private int mWidth;
    
    static {
        BANNER = new ISBannerSize("BANNER");
        LARGE = new ISBannerSize("LARGE");
        RECTANGLE = new ISBannerSize("RECTANGLE");
        SMART = new ISBannerSize("SMART");
    }
    
    public ISBannerSize(final int mWidth, final int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mDescription = "CUSTOM";
    }
    
    public ISBannerSize(final String mDescription) {
        this.mDescription = mDescription;
    }
    
    public String getDescription() {
        return this.mDescription;
    }
    
    public int getHeight() {
        return this.mHeight;
    }
    
    public int getWidth() {
        return this.mWidth;
    }
}
