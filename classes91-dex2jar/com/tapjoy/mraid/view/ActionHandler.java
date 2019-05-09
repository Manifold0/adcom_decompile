// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.view;

import java.util.Iterator;
import java.util.Map;
import com.tapjoy.mraid.listener.Player;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.tapjoy.mraid.util.Utils;
import android.content.Context;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.mraid.util.MraidPlayer;
import android.os.Bundle;
import android.widget.RelativeLayout;
import java.util.HashMap;
import android.app.Activity;

public class ActionHandler extends Activity
{
    private HashMap a;
    private RelativeLayout b;
    
    public ActionHandler() {
        this.a = new HashMap();
    }
    
    private MraidPlayer a(final Bundle bundle, final MraidView.Action action) {
        final Abstract.PlayerProperties playerProperties = (Abstract.PlayerProperties)bundle.getParcelable("player_properties");
        final Abstract.Dimensions dimensions = (Abstract.Dimensions)bundle.getParcelable("expand_dimensions");
        final MraidPlayer mraidPlayer = new MraidPlayer((Context)this);
        mraidPlayer.setPlayData(playerProperties, Utils.getData("expand_url", bundle));
        RelativeLayout$LayoutParams layoutParams;
        if (!playerProperties.inline && playerProperties.startStyle.equals("fullscreen")) {
            this.getWindow().setFlags(1024, 1024);
            this.getWindow().setFlags(16777216, 16777216);
            layoutParams = new RelativeLayout$LayoutParams(-1, -1);
            layoutParams.addRule(13);
        }
        else {
            layoutParams = new RelativeLayout$LayoutParams(dimensions.width, dimensions.height);
            layoutParams.topMargin = dimensions.y;
            layoutParams.leftMargin = dimensions.x;
        }
        mraidPlayer.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.b.addView((View)mraidPlayer);
        this.a.put(action, mraidPlayer);
        mraidPlayer.setListener(new Player() {
            @Override
            public final void onComplete() {
                ActionHandler.this.finish();
            }
            
            @Override
            public final void onError() {
                ActionHandler.this.finish();
            }
            
            @Override
            public final void onPrepared() {
            }
        });
        return mraidPlayer;
    }
    
    public void onCreate(Bundle extras) {
        super.onCreate(extras);
        this.requestWindowFeature(1);
        extras = this.getIntent().getExtras();
        (this.b = new RelativeLayout((Context)this)).setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
        this.b.setBackgroundColor(-16777216);
        this.setContentView((View)this.b);
        final String string = extras.getString("action");
        if (string != null) {
            final MraidView.Action value = MraidView.Action.valueOf(string);
            switch (ActionHandler$2.a[value.ordinal()]) {
                case 1: {
                    this.a(extras, value).playAudio();
                }
                case 2: {
                    this.a(extras, value).playVideo();
                }
            }
        }
    }
    
    protected void onStop() {
        for (final Map.Entry<MraidView.Action, V> entry : this.a.entrySet()) {
            switch (ActionHandler$2.a[entry.getKey().ordinal()]) {
                default: {
                    continue;
                }
                case 1:
                case 2: {
                    ((MraidPlayer)entry.getValue()).releasePlayer();
                    continue;
                }
            }
        }
        super.onStop();
    }
}
