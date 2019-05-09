// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.content.Intent;
import android.app.Activity;
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
import android.support.v4.app.FragmentActivity;
import java.util.WeakHashMap;
import android.support.v4.app.Fragment;

public final class zzc extends Fragment implements LifecycleFragment
{
    private static WeakHashMap<FragmentActivity, WeakReference<zzc>> zzbe;
    private Map<String, LifecycleCallback> zzbf;
    private int zzbg;
    private Bundle zzbh;
    
    static {
        zzc.zzbe = new WeakHashMap<FragmentActivity, WeakReference<zzc>>();
    }
    
    public zzc() {
        this.zzbf = (Map<String, LifecycleCallback>)new ArrayMap();
        this.zzbg = 0;
    }
    
    public static zzc zza(final FragmentActivity fragmentActivity) {
        final WeakReference<zzc> weakReference = zzc.zzbe.get(fragmentActivity);
        if (weakReference != null) {
            final zzc zzc = weakReference.get();
            if (zzc != null) {
                return zzc;
            }
        }
        try {
            final zzc zzc2 = (zzc)fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            zzc zzc3 = null;
            Label_0080: {
                if (zzc2 != null) {
                    zzc3 = zzc2;
                    if (!zzc2.isRemoving()) {
                        break Label_0080;
                    }
                }
                zzc3 = new zzc();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment)zzc3, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            zzc.zzbe.put(fragmentActivity, new WeakReference<zzc>(zzc3));
            return zzc3;
        }
        catch (ClassCastException ex) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", ex);
        }
    }
    
    public final void addCallback(final String s, @NonNull final LifecycleCallback lifecycleCallback) {
        if (!this.zzbf.containsKey(s)) {
            this.zzbf.put(s, lifecycleCallback);
            if (this.zzbg > 0) {
                new zze(Looper.getMainLooper()).post((Runnable)new zzd(this, lifecycleCallback, s));
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
