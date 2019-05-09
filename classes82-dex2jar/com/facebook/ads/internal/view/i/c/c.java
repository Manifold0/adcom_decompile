// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import com.facebook.ads.internal.view.i.a;
import android.view.View;
import com.facebook.ads.internal.o.d;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.o.f;
import android.widget.TextView;

public class c extends com.facebook.ads.internal.view.i.a.c
{
    private final TextView a;
    private final String b;
    private final f<o> c;
    
    public c(final Context context, final String b) {
        super(context);
        this.c = new f<o>() {
            @Override
            public Class<o> a() {
                return o.class;
            }
            
            @Override
            public void a(final o o) {
                if (com.facebook.ads.internal.view.i.a.c.this.getVideoView() == null) {
                    return;
                }
                com.facebook.ads.internal.view.i.c.c.this.a.setText((CharSequence)com.facebook.ads.internal.view.i.c.c.a(com.facebook.ads.internal.view.i.c.c.this, com.facebook.ads.internal.view.i.a.c.this.getVideoView().getDuration() - com.facebook.ads.internal.view.i.a.c.this.getVideoView().getCurrentPositionInMillis()));
            }
        };
        this.a = new TextView(context);
        this.b = b;
        this.addView((View)this.a);
    }
    
    static /* synthetic */ String a(final c c, long seconds) {
        if (seconds <= 0L) {
            return "00:00";
        }
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(seconds);
        seconds = TimeUnit.MILLISECONDS.toSeconds(seconds % 60000L);
        if (c.b.isEmpty()) {
            return String.format(Locale.US, "%02d:%02d", minutes, seconds);
        }
        return c.b.replace("{{REMAINING_TIME}}", String.format(Locale.US, "%02d:%02d", minutes, seconds));
    }
    
    @Override
    protected void a() {
        super.a();
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().a(this.c);
        }
    }
    
    @Override
    protected void b() {
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().b(this.c);
        }
        super.b();
    }
    
    public void setCountdownTextColor(final int textColor) {
        this.a.setTextColor(textColor);
    }
}
