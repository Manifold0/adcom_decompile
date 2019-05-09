// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.ads.metadata;

import android.content.Context;

public class PlayerMetaData extends MetaData
{
    public static final String KEY_SERVER_ID = "server_id";
    
    public PlayerMetaData(final Context context) {
        super(context);
        this.setCategory("player");
    }
    
    public void setServerId(final String s) {
        this.set("server_id", s);
    }
}
