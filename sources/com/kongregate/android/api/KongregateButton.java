package com.kongregate.android.api;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0562j;

public class KongregateButton extends ImageView {
    private boolean mInitialized = true;

    /* renamed from: com.kongregate.android.api.KongregateButton$1 */
    class C04351 implements OnClickListener {
        C04351() {
        }

        public void onClick(View view) {
            KongregateAPI instance = APIBootstrap.getInstance();
            if (instance != null) {
                instance.mobile().openKongregateWindow(KongregateButton.this.getContext());
            } else {
                C0562j.m759c("Kongregate API not initialized");
            }
        }
    }

    public KongregateButton(Context context) {
        super(context);
        setup(context, null);
    }

    public KongregateButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setup(context, attributeSet);
    }

    public KongregateButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setup(context, attributeSet);
    }

    private void setup(Context context, AttributeSet attributeSet) {
        setOnClickListener(new C04351());
        setScaleType(ScaleType.FIT_CENTER);
        if (!isInEditMode()) {
            setImageResource(C0558g.m664a("kongregate_button", "drawable", context));
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        if (this.mInitialized) {
            throw new IllegalStateException("Don't call setOnClickListener for KongregateButton");
        }
        super.setOnClickListener(onClickListener);
    }
}
