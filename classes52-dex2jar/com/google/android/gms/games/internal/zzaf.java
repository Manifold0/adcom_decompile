// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import android.view.ViewTreeObserver;
import android.content.Context;
import android.app.Activity;
import android.os.IBinder;
import android.view.Display;
import com.google.android.gms.common.util.PlatformVersion;
import android.view.View;
import java.lang.ref.WeakReference;
import android.annotation.TargetApi;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnAttachStateChangeListener;

@TargetApi(12)
final class zzaf extends zzac implements View$OnAttachStateChangeListener, ViewTreeObserver$OnGlobalLayoutListener
{
    private boolean zzfu;
    private WeakReference<View> zzjf;
    
    protected zzaf(final zze zze, final int n) {
        super(zze, n, null);
        this.zzfu = false;
    }
    
    @TargetApi(17)
    private final void zzc(final View view) {
        int displayId = -1;
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            final Display display = view.getDisplay();
            displayId = displayId;
            if (display != null) {
                displayId = display.getDisplayId();
            }
        }
        final IBinder windowToken = view.getWindowToken();
        final int[] array = new int[2];
        view.getLocationInWindow(array);
        final int width = view.getWidth();
        final int height = view.getHeight();
        this.zzjd.zzje = displayId;
        this.zzjd.zzjb = windowToken;
        this.zzjd.left = array[0];
        this.zzjd.top = array[1];
        this.zzjd.right = array[0] + width;
        this.zzjd.bottom = array[1] + height;
        if (this.zzfu) {
            this.zzbj();
        }
    }
    
    public final void onGlobalLayout() {
        if (this.zzjf != null) {
            final View view = this.zzjf.get();
            if (view != null) {
                this.zzc(view);
            }
        }
    }
    
    public final void onViewAttachedToWindow(final View view) {
        this.zzc(view);
    }
    
    public final void onViewDetachedFromWindow(final View view) {
        this.zzjc.zzbd();
        view.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
    }
    
    @TargetApi(16)
    @Override
    public final void zzb(View view) {
        this.zzjc.zzbd();
        if (this.zzjf != null) {
            final View view2 = this.zzjf.get();
            final Context context = this.zzjc.getContext();
            View decorView;
            if ((decorView = view2) == null) {
                decorView = view2;
                if (context instanceof Activity) {
                    decorView = ((Activity)context).getWindow().getDecorView();
                }
            }
            if (decorView != null) {
                decorView.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
                final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
                if (PlatformVersion.isAtLeastJellyBean()) {
                    viewTreeObserver.removeOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                }
                else {
                    viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                }
            }
        }
        this.zzjf = null;
        final Context context2 = this.zzjc.getContext();
        View view3;
        if ((view3 = view) == null) {
            view3 = view;
            if (context2 instanceof Activity) {
                if ((view = ((Activity)context2).findViewById(16908290)) == null) {
                    view = ((Activity)context2).getWindow().getDecorView();
                }
                zzh.w("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view. Note that this may not work as expected in multi-screen environments");
                view3 = view;
            }
        }
        if (view3 != null) {
            this.zzc(view3);
            this.zzjf = new WeakReference<View>(view3);
            view3.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
            view3.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
            return;
        }
        zzh.e("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
    }
    
    @Override
    public final void zzbj() {
        if (this.zzjd.zzjb != null) {
            super.zzbj();
            this.zzfu = false;
            return;
        }
        this.zzfu = true;
    }
    
    @Override
    protected final void zzm(final int n) {
        this.zzjd = new zzae(n, null, null);
    }
}
