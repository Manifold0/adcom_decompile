// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.content.Intent;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import com.google.android.gms.internal.common.zze;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.os.Bundle;
import java.util.Map;
import java.lang.ref.WeakReference;
import android.app.Activity;
import java.util.WeakHashMap;
import android.app.Fragment;

public final class zza extends Fragment implements LifecycleFragment
{
    private static WeakHashMap<Activity, WeakReference<zza>> zzbe;
    private Map<String, LifecycleCallback> zzbf;
    private int zzbg;
    private Bundle zzbh;
    
    static {
        zza.zzbe = new WeakHashMap<Activity, WeakReference<zza>>();
    }
    
    public zza() {
        this.zzbf = (Map<String, LifecycleCallback>)new ArrayMap();
        this.zzbg = 0;
    }
    
    public static zza zza(final Activity activity) {
        final WeakReference<zza> weakReference = zza.zzbe.get(activity);
        if (weakReference != null) {
            final zza zza = weakReference.get();
            if (zza != null) {
                return zza;
            }
        }
        try {
            final zza zza2 = (zza)activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
            zza zza3 = null;
            Label_0080: {
                if (zza2 != null) {
                    zza3 = zza2;
                    if (!zza2.isRemoving()) {
                        break Label_0080;
                    }
                }
                zza3 = new zza();
                activity.getFragmentManager().beginTransaction().add((Fragment)zza3, "LifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zza.zzbe.put(activity, new WeakReference<zza>(zza3));
            return zza3;
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", ex);
        }
    }
    
    public final void addCallback(final String s, @NonNull final LifecycleCallback lifecycleCallback) {
        if (!this.zzbf.containsKey(s)) {
            this.zzbf.put(s, lifecycleCallback);
            if (this.zzbg > 0) {
                new zze(Looper.getMainLooper()).post((Runnable)new zzb(this, lifecycleCallback, s));
            }
            return;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(s).length() + 59).append("LifecycleCallback with tag ").append(s).append(" already added to this fragment.").toString());
    }
    
    public final void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        final Iterator<LifecycleCallback> iterator = this.zzbf.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().dump(s, fileDescriptor, printWriter, array);
        }
    }
    
    public final <T extends LifecycleCallback> T getCallbackOrNull(final String s, final Class<T> clazz) {
        return clazz.cast(this.zzbf.get(s));
    }
    
    public final Activity getLifecycleActivity() {
        return this.getActivity();
    }
    
    public final boolean isCreated() {
        return this.zzbg > 0;
    }
    
    public final boolean isStarted() {
        return this.zzbg >= 2;
    }
    
    public final void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        final Iterator<LifecycleCallback> iterator = this.zzbf.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onActivityResult(n, n2, intent);
        }
    }
    
    public final void onCreate(final Bundle zzbh) {
        super.onCreate(zzbh);
        this.zzbg = 1;
        this.zzbh = zzbh;
        for (final Map.Entry<String, LifecycleCallback> entry : this.zzbf.entrySet()) {
            final LifecycleCallback lifecycleCallback = entry.getValue();
            Bundle bundle;
            if (zzbh != null) {
                bundle = zzbh.getBundle((String)entry.getKey());
            }
            else {
                bundle = null;
            }
            lifecycleCallback.onCreate(bundle);
        }
    }
    
    public final void onDestroy() {
        super.onDestroy();
        this.zzbg = 5;
        final Iterator<LifecycleCallback> iterator = this.zzbf.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onDestroy();
        }
    }
    
    public final void onResume() {
        super.onResume();
        this.zzbg = 3;
        final Iterator<LifecycleCallback> iterator = this.zzbf.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onResume();
        }
    }
    
    public final void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (final Map.Entry<String, LifecycleCallback> entry : this.zzbf.entrySet()) {
                final Bundle bundle2 = new Bundle();
                entry.getValue().onSaveInstanceState(bundle2);
                bundle.putBundle((String)entry.getKey(), bundle2);
            }
        }
    }
    
    public final void onStart() {
        super.onStart();
        this.zzbg = 2;
        final Iterator<LifecycleCallback> iterator = this.zzbf.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onStart();
        }
    }
    
    public final void onStop() {
        super.onStop();
        this.zzbg = 4;
        final Iterator<LifecycleCallback> iterator = this.zzbf.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onStop();
        }
    }
}
