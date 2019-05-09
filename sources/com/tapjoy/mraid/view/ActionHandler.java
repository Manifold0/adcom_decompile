package com.tapjoy.mraid.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.mraid.controller.Abstract.Dimensions;
import com.tapjoy.mraid.controller.Abstract.PlayerProperties;
import com.tapjoy.mraid.listener.Player;
import com.tapjoy.mraid.util.MraidPlayer;
import com.tapjoy.mraid.util.Utils;
import com.tapjoy.mraid.view.MraidView.Action;
import java.util.HashMap;
import java.util.Map.Entry;

public class ActionHandler extends Activity {
    /* renamed from: a */
    private HashMap f8316a = new HashMap();
    /* renamed from: b */
    private RelativeLayout f8317b;

    /* renamed from: com.tapjoy.mraid.view.ActionHandler$1 */
    class C30081 implements Player {
        /* renamed from: a */
        final /* synthetic */ ActionHandler f8314a;

        C30081(ActionHandler actionHandler) {
            this.f8314a = actionHandler;
        }

        public final void onPrepared() {
        }

        public final void onError() {
            this.f8314a.finish();
        }

        public final void onComplete() {
            this.f8314a.finish();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        Bundle extras = getIntent().getExtras();
        this.f8317b = new RelativeLayout(this);
        this.f8317b.setLayoutParams(new LayoutParams(-1, -1));
        this.f8317b.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        setContentView(this.f8317b);
        String string = extras.getString("action");
        if (string != null) {
            Action valueOf = Action.valueOf(string);
            switch (valueOf) {
                case PLAY_AUDIO:
                    m8244a(extras, valueOf).playAudio();
                    return;
                case PLAY_VIDEO:
                    m8244a(extras, valueOf).playVideo();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private MraidPlayer m8244a(Bundle bundle, Action action) {
        LayoutParams layoutParams;
        PlayerProperties playerProperties = (PlayerProperties) bundle.getParcelable(MraidView.PLAYER_PROPERTIES);
        Dimensions dimensions = (Dimensions) bundle.getParcelable(MraidView.DIMENSIONS);
        View mraidPlayer = new MraidPlayer(this);
        mraidPlayer.setPlayData(playerProperties, Utils.getData(MraidView.EXPAND_URL, bundle));
        if (playerProperties.inline || !playerProperties.startStyle.equals(Abstract.FULL_SCREEN)) {
            layoutParams = new RelativeLayout.LayoutParams(dimensions.width, dimensions.height);
            layoutParams.topMargin = dimensions.f8252y;
            layoutParams.leftMargin = dimensions.f8251x;
        } else {
            getWindow().setFlags(1024, 1024);
            getWindow().setFlags(16777216, 16777216);
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
        }
        mraidPlayer.setLayoutParams(layoutParams);
        this.f8317b.addView(mraidPlayer);
        this.f8316a.put(action, mraidPlayer);
        mraidPlayer.setListener(new C30081(this));
        return mraidPlayer;
    }

    protected void onStop() {
        for (Entry entry : this.f8316a.entrySet()) {
            switch ((Action) entry.getKey()) {
                case PLAY_AUDIO:
                case PLAY_VIDEO:
                    ((MraidPlayer) entry.getValue()).releasePlayer();
                    break;
                default:
                    break;
            }
        }
        super.onStop();
    }
}
