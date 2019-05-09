package com.google.android.gms.games.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.common.util.PlatformVersion;
import java.lang.ref.WeakReference;

@TargetApi(12)
final class zzaf extends zzac implements OnAttachStateChangeListener, OnGlobalLayoutListener {
    private boolean zzfu = false;
    private WeakReference<View> zzjf;

    protected zzaf(zze zze, int i) {
        super(zze, i);
    }

    @TargetApi(17)
    private final void zzc(View view) {
        int i = -1;
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            Display display = view.getDisplay();
            if (display != null) {
                i = display.getDisplayId();
            }
        }
        IBinder windowToken = view.getWindowToken();
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int width = view.getWidth();
        int height = view.getHeight();
        this.zzjd.zzje = i;
        this.zzjd.zzjb = windowToken;
        this.zzjd.left = iArr[0];
        this.zzjd.top = iArr[1];
        this.zzjd.right = iArr[0] + width;
        this.zzjd.bottom = iArr[1] + height;
        if (this.zzfu) {
            zzbj();
        }
    }

    public final void onGlobalLayout() {
        if (this.zzjf != null) {
            View view = (View) this.zzjf.get();
            if (view != null) {
                zzc(view);
            }
        }
    }

    public final void onViewAttachedToWindow(View view) {
        zzc(view);
    }

    public final void onViewDetachedFromWindow(View view) {
        this.zzjc.zzbd();
        view.removeOnAttachStateChangeListener(this);
    }

    @TargetApi(16)
    public final void zzb(View view) {
        View view2;
        Context context;
        this.zzjc.zzbd();
        if (this.zzjf != null) {
            view2 = (View) this.zzjf.get();
            context = this.zzjc.getContext();
            if (view2 == null && (context instanceof Activity)) {
                view2 = ((Activity) context).getWindow().getDecorView();
            }
            if (view2 != null) {
                view2.removeOnAttachStateChangeListener(this);
                ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
                if (PlatformVersion.isAtLeastJellyBean()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                } else {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
            }
        }
        this.zzjf = null;
        context = this.zzjc.getContext();
        if (view == null && (context instanceof Activity)) {
            view2 = ((Activity) context).findViewById(16908290);
            if (view2 == null) {
                view2 = ((Activity) context).getWindow().getDecorView();
            }
            zzh.m1663w("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view. Note that this may not work as expected in multi-screen environments");
            view = view2;
        }
        if (view != null) {
            zzc(view);
            this.zzjf = new WeakReference(view);
            view.addOnAttachStateChangeListener(this);
            view.getViewTreeObserver().addOnGlobalLayoutListener(this);
            return;
        }
        zzh.m1660e("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
    }

    public final void zzbj() {
        if (this.zzjd.zzjb != null) {
            super.zzbj();
            this.zzfu = false;
            return;
        }
        this.zzfu = true;
    }

    protected final void zzm(int i) {
        this.zzjd = new zzae(i, null);
    }
}
