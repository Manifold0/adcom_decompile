// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.ui;

import android.content.DialogInterface$OnClickListener;
import android.net.Uri;

public interface AdView
{
    void close();
    
    String getWebsiteUrl();
    
    void open(final String p0);
    
    void playVideo(final Uri p0, final boolean p1);
    
    void setOrientation(final int p0);
    
    void setVisibility(final boolean p0);
    
    void showCTAOverlay(final boolean p0, final boolean p1, final int p2);
    
    void showCloseButton();
    
    void showDialog(final String p0, final String p1, final String p2, final String p3, final DialogInterface$OnClickListener p4);
    
    void showWebsite(final String p0);
    
    void updateWindow(final boolean p0);
}
