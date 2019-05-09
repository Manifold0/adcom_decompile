// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import com.facebook.ads.internal.view.a.b;
import com.facebook.ads.internal.o.d;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.Intent;

public interface a
{
    void a(final Intent p0, @Nullable final Bundle p1, final AudienceNetworkActivity p2);
    
    void a(final Bundle p0);
    
    void a_(final boolean p0);
    
    void b(final boolean p0);
    
    void onDestroy();
    
    void setListener(final a p0);
    
    public interface a
    {
        void a(final View p0);
        
        void a(final View p0, final int p1);
        
        void a(final String p0);
        
        void a(final String p0, final d p1);
        
        void a(final String p0, final boolean p1, @Nullable final b p2);
    }
}
