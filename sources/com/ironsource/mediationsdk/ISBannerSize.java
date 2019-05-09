package com.ironsource.mediationsdk;

public class ISBannerSize {
    public static final ISBannerSize BANNER = new ISBannerSize("BANNER");
    public static final ISBannerSize LARGE = new ISBannerSize("LARGE");
    public static final ISBannerSize RECTANGLE = new ISBannerSize("RECTANGLE");
    public static final ISBannerSize SMART = new ISBannerSize("SMART");
    private String mDescription;
    private int mHeight;
    private int mWidth;

    public ISBannerSize(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        this.mDescription = "CUSTOM";
    }

    public ISBannerSize(String description) {
        this.mDescription = description;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }
}
