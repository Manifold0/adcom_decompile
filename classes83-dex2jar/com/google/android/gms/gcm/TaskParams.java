// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.net.Uri;
import java.util.List;
import android.os.Bundle;

public class TaskParams
{
    private final Bundle extras;
    private final String tag;
    private final List<Uri> zziaq;
    
    public TaskParams(final String s) {
        this(s, null, null);
    }
    
    public TaskParams(final String s, final Bundle bundle) {
        this(s, bundle, null);
    }
    
    public TaskParams(final String tag, final Bundle extras, final List<Uri> zziaq) {
        this.tag = tag;
        this.extras = extras;
        this.zziaq = zziaq;
    }
    
    public Bundle getExtras() {
        return this.extras;
    }
    
    public String getTag() {
        return this.tag;
    }
}
