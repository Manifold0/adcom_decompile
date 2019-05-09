package com.applovin.impl.adview;

import android.content.Context;
import android.view.View;
import com.applovin.sdk.AppLovinSdk;

public abstract class ak extends View {
    /* renamed from: a */
    protected final AppLovinSdk f1761a;
    /* renamed from: b */
    protected final Context f1762b;

    ak(AppLovinSdk appLovinSdk, Context context) {
        super(context);
        this.f1762b = context;
        this.f1761a = appLovinSdk;
    }

    /* renamed from: a */
    public static ak m2007a(AppLovinSdk appLovinSdk, Context context, al alVar) {
        return alVar.equals(al.Invisible) ? new ch(appLovinSdk, context) : alVar.equals(al.WhiteXOnTransparentGrey) ? new cj(appLovinSdk, context) : new cq(appLovinSdk, context);
    }

    /* renamed from: a */
    public abstract void mo4047a(int i);
}
