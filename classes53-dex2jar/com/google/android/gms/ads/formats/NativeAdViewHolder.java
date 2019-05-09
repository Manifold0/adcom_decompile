// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;
import java.lang.ref.WeakReference;
import com.google.android.gms.internal.ads.zzqf;
import android.view.View;
import java.util.WeakHashMap;

public final class NativeAdViewHolder
{
    public static WeakHashMap<View, NativeAdViewHolder> zzvk;
    private zzqf zzvj;
    private WeakReference<View> zzvl;
    
    static {
        NativeAdViewHolder.zzvk = new WeakHashMap<View, NativeAdViewHolder>();
    }
    
    public NativeAdViewHolder(final View view, final Map<String, View> map, final Map<String, View> map2) {
        Preconditions.checkNotNull((Object)view, (Object)"ContainerView must not be null");
        if (view instanceof NativeAdView || view instanceof UnifiedNativeAdView) {
            zzane.e("The provided containerView is of type of NativeAdView, which cannot be usedwith NativeAdViewHolder.");
            return;
        }
        if (NativeAdViewHolder.zzvk.get(view) != null) {
            zzane.e("The provided containerView is already in use with another NativeAdViewHolder.");
            return;
        }
        NativeAdViewHolder.zzvk.put(view, this);
        this.zzvl = new WeakReference<View>(view);
        this.zzvj = zzkb.zzig().zza(view, zzb(map), zzb(map2));
    }
    
    private final void zza(final IObjectWrapper objectWrapper) {
        View view;
        if (this.zzvl != null) {
            view = this.zzvl.get();
        }
        else {
            view = null;
        }
        if (view == null) {
            zzane.zzdk("NativeAdViewHolder.setNativeAd containerView doesn't exist, returning");
        }
        else {
            if (!NativeAdViewHolder.zzvk.containsKey(view)) {
                NativeAdViewHolder.zzvk.put(view, this);
            }
            if (this.zzvj != null) {
                try {
                    this.zzvj.zza(objectWrapper);
                }
                catch (RemoteException ex) {
                    zzane.zzb("Unable to call setNativeAd on delegate", (Throwable)ex);
                }
            }
        }
    }
    
    private static HashMap<String, View> zzb(final Map<String, View> map) {
        if (map == null) {
            return new HashMap<String, View>();
        }
        return new HashMap<String, View>(map);
    }
    
    public final void setClickConfirmingView(final View view) {
        try {
            this.zzvj.zzc(ObjectWrapper.wrap((Object)view));
        }
        catch (RemoteException ex) {
            zzane.zzb("Unable to call setClickConfirmingView on delegate", (Throwable)ex);
        }
    }
    
    public final void setNativeAd(final NativeAd nativeAd) {
        this.zza((IObjectWrapper)nativeAd.zzbe());
    }
    
    public final void setNativeAd(final UnifiedNativeAd unifiedNativeAd) {
        this.zza((IObjectWrapper)unifiedNativeAd.zzbe());
    }
    
    public final void unregisterNativeAd() {
    Label_0016:
        while (true) {
            if (this.zzvj == null) {
                break Label_0016;
            }
            while (true) {
                while (true) {
                    try {
                        this.zzvj.unregisterNativeAd();
                        if (this.zzvl != null) {
                            final View view = this.zzvl.get();
                            if (view != null) {
                                NativeAdViewHolder.zzvk.remove(view);
                            }
                            return;
                        }
                    }
                    catch (RemoteException ex) {
                        zzane.zzb("Unable to call unregisterNativeAd on delegate", (Throwable)ex);
                        continue Label_0016;
                    }
                    final View view = null;
                    continue;
                }
            }
            break;
        }
    }
}
