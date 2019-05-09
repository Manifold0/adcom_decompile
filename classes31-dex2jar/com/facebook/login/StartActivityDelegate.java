// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.content.Intent;
import android.app.Activity;

interface StartActivityDelegate
{
    Activity getActivityContext();
    
    void startActivityForResult(final Intent p0, final int p1);
}
