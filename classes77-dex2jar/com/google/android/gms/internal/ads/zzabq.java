// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.Window;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import android.app.Activity;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;
import android.widget.PopupWindow;
import android.annotation.TargetApi;

@zzadh
@TargetApi(19)
public final class zzabq extends zzabn
{
    private Object zzbzn;
    @GuardedBy("mPopupWindowLock")
    private PopupWindow zzbzo;
    @GuardedBy("mPopupWindowLock")
    private boolean zzbzp;
    
    zzabq(final Context context, final zzaji zzaji, final zzaqw zzaqw, final zzabm zzabm) {
        super(context, zzaji, zzaqw, zzabm);
        this.zzbzn = new Object();
        this.zzbzp = false;
    }
    
    private final void zznv() {
        synchronized (this.zzbzn) {
            this.zzbzp = true;
            if (this.mContext instanceof Activity && ((Activity)this.mContext).isDestroyed()) {
                this.zzbzo = null;
            }
            if (this.zzbzo != null) {
                if (this.zzbzo.isShowing()) {
                    this.zzbzo.dismiss();
                }
                this.zzbzo = null;
            }
        }
    }
    
    @Override
    public final void cancel() {
        this.zznv();
        super.cancel();
    }
    
    @Override
    protected final void zznu() {
        while (true) {
            Label_0170: {
                if (!(this.mContext instanceof Activity)) {
                    break Label_0170;
                }
                final Window window = ((Activity)this.mContext).getWindow();
                if (window == null || window.getDecorView() == null || ((Activity)this.mContext).isDestroyed()) {
                    return;
                }
                final FrameLayout frameLayout = new FrameLayout(this.mContext);
                frameLayout.setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
                frameLayout.addView(this.zzbnd.getView(), -1, -1);
                synchronized (this.zzbzn) {
                    if (this.zzbzp) {
                        return;
                    }
                }
                (this.zzbzo = new PopupWindow((View)frameLayout, 1, 1, false)).setOutsideTouchable(true);
                this.zzbzo.setClippingEnabled(false);
                zzakb.zzck("Displaying the 1x1 popup off the screen.");
                while (true) {
                    try {
                        final Window window2;
                        this.zzbzo.showAtLocation(window2.getDecorView(), 0, -1, -1);
                        // monitorexit(o)
                        return;
                    }
                    catch (Exception ex) {
                        this.zzbzo = null;
                        continue;
                    }
                    break;
                }
            }
            final Window window = null;
            continue;
        }
    }
    
    @Override
    protected final void zzz(final int n) {
        this.zznv();
        super.zzz(n);
    }
}
