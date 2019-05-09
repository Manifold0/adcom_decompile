// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;

public enum MessageDialogFeature implements DialogFeature
{
    MESSAGE_DIALOG(20140204), 
    MESSENGER_GENERIC_TEMPLATE(20171115), 
    MESSENGER_MEDIA_TEMPLATE(20171115), 
    MESSENGER_OPEN_GRAPH_MUSIC_TEMPLATE(20171115), 
    PHOTOS(20140324), 
    VIDEO(20141218);
    
    private int minVersion;
    
    private MessageDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    public String getAction() {
        return "com.facebook.platform.action.request.MESSAGE_DIALOG";
    }
    
    public int getMinVersion() {
        return this.minVersion;
    }
}
