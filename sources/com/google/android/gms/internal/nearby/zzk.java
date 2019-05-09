package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.Task;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzk {
    private static zzk zzal;
    private final Map<String, Set<ListenerKey<?>>> zzam = new HashMap();
    private final Set<ListenerKey<?>> zzan = new HashSet();
    private final Map<String, ListenerHolder<String>> zzao = new HashMap();

    private zzk() {
    }

    public static synchronized zzk zza() {
        zzk zzk;
        synchronized (zzk.class) {
            if (zzal == null) {
                zzal = new zzk();
            }
            zzk = zzal;
        }
        return zzk;
    }

    private final void zza(String str, ListenerKey<?> listenerKey) {
        Set set = (Set) this.zzam.get(str);
        if (set == null) {
            set = new HashSet();
            this.zzam.put(str, set);
        }
        set.add(listenerKey);
    }

    public final synchronized <T> ListenerHolder<T> zza(GoogleApi googleApi, T t, String str) {
        ListenerHolder<T> registerListener;
        registerListener = googleApi.registerListener(t, str);
        zza(str, registerListener.getListenerKey());
        return registerListener;
    }

    public final synchronized ListenerHolder<String> zza(GoogleApi googleApi, String str, String str2) {
        ListenerHolder<String> listenerHolder;
        if (this.zzao.containsKey(str) && ((ListenerHolder) this.zzao.get(str)).hasListener()) {
            listenerHolder = (ListenerHolder) this.zzao.get(str);
        } else {
            listenerHolder = googleApi.registerListener(str, str2);
            zza(str2, listenerHolder.getListenerKey());
            this.zzao.put(str, listenerHolder);
        }
        return listenerHolder;
    }

    public final synchronized Task<Boolean> zza(GoogleApi googleApi, ListenerKey<?> listenerKey) {
        this.zzan.remove(listenerKey);
        return googleApi.doUnregisterEventListener(listenerKey);
    }

    public final synchronized Task<Void> zza(GoogleApi googleApi, RegisterListenerMethod registerListenerMethod, UnregisterListenerMethod unregisterListenerMethod) {
        this.zzan.add(registerListenerMethod.getListenerKey());
        return googleApi.doRegisterEventListener(registerListenerMethod, unregisterListenerMethod).addOnFailureListener(new zzl(this, registerListenerMethod));
    }

    public final synchronized void zza(GoogleApi googleApi, String str) {
        Set<ListenerKey> set = (Set) this.zzam.get(str);
        if (set != null) {
            for (ListenerKey listenerKey : set) {
                if (this.zzan.contains(listenerKey)) {
                    zza(googleApi, listenerKey);
                }
            }
            this.zzam.remove(str);
        }
    }

    public final synchronized <T> ListenerKey<T> zzb(GoogleApi googleApi, T t, String str) {
        return t instanceof String ? zza(googleApi, (String) t, str).getListenerKey() : ListenerHolders.createListenerKey(t, str);
    }
}
