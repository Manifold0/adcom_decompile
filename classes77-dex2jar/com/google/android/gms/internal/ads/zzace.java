// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.zzbo;
import org.json.JSONObject;
import android.view.View;
import java.lang.ref.WeakReference;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.zzbc;
import android.util.DisplayMetrics;
import javax.annotation.concurrent.GuardedBy;
import android.content.Context;

@zzadh
public final class zzace
{
    private final Context mContext;
    private final Object mLock;
    @GuardedBy("mLock")
    private int zzadx;
    @GuardedBy("mLock")
    private int zzady;
    private zzamj zzadz;
    private final DisplayMetrics zzagj;
    private final zzci zzbjc;
    private final zzaji zzbze;
    private final zzbc zzcbc;
    private ViewTreeObserver$OnGlobalLayoutListener zzcbd;
    private ViewTreeObserver$OnScrollChangedListener zzcbe;
    private final zznx zzvr;
    
    public zzace(final Context mContext, final zzci zzbjc, final zzaji zzbze, final zznx zzvr, final zzbc zzcbc) {
        this.mLock = new Object();
        this.zzadx = -1;
        this.zzady = -1;
        this.mContext = mContext;
        this.zzbjc = zzbjc;
        this.zzbze = zzbze;
        this.zzvr = zzvr;
        this.zzcbc = zzcbc;
        this.zzadz = new zzamj(200L);
        zzbv.zzek();
        this.zzagj = zzakk.zza((WindowManager)mContext.getSystemService("window"));
    }
    
    private final void zza(final WeakReference<zzaqw> weakReference, final boolean b) {
        if (weakReference != null) {
            final zzaqw zzaqw = weakReference.get();
            if (zzaqw != null && zzaqw.getView() != null && (!b || this.zzadz.tryAcquire())) {
                while (true) {
                    final View view = zzaqw.getView();
                    final int[] array = new int[2];
                    view.getLocationOnScreen(array);
                    zzkb.zzif();
                    int zzadx = zzamu.zzb(this.zzagj, array[0]);
                    zzkb.zzif();
                    int zzady = zzamu.zzb(this.zzagj, array[1]);
                    while (true) {
                        Label_0174: {
                            synchronized (this.mLock) {
                                if (this.zzadx != zzadx || this.zzady != zzady) {
                                    this.zzadx = zzadx;
                                    this.zzady = zzady;
                                    final zzasc zzuf = zzaqw.zzuf();
                                    zzadx = this.zzadx;
                                    zzady = this.zzady;
                                    if (b) {
                                        break Label_0174;
                                    }
                                    final boolean b2 = true;
                                    zzuf.zza(zzadx, zzady, b2);
                                }
                                return;
                            }
                        }
                        final boolean b2 = false;
                        continue;
                    }
                }
            }
        }
    }
}
