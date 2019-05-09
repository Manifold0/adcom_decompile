// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Message;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;
import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class ListenerHolder<L>
{
    private final zaa zajj;
    private volatile L zajk;
    private final ListenerKey<L> zajl;
    
    @KeepForSdk
    ListenerHolder(@NonNull final Looper looper, @NonNull final L l, @NonNull final String s) {
        this.zajj = new zaa(looper);
        this.zajk = (L)Preconditions.checkNotNull((Object)l, (Object)"Listener must not be null");
        this.zajl = new ListenerKey<L>(l, Preconditions.checkNotEmpty(s));
    }
    
    @KeepForSdk
    public final void clear() {
        this.zajk = null;
    }
    
    @NonNull
    @KeepForSdk
    public final ListenerKey<L> getListenerKey() {
        return this.zajl;
    }
    
    @KeepForSdk
    public final boolean hasListener() {
        return this.zajk != null;
    }
    
    @KeepForSdk
    public final void notifyListener(final Notifier<? super L> notifier) {
        Preconditions.checkNotNull((Object)notifier, (Object)"Notifier must not be null");
        this.zajj.sendMessage(this.zajj.obtainMessage(1, (Object)notifier));
    }
    
    @KeepForSdk
    final void notifyListenerInternal(final Notifier<? super L> notifier) {
        final L zajk = this.zajk;
        if (zajk == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(zajk);
        }
        catch (RuntimeException ex) {
            notifier.onNotifyListenerFailed();
            throw ex;
        }
    }
    
    @KeepForSdk
    public static final class ListenerKey<L>
    {
        private final L zajk;
        private final String zajn;
        
        @KeepForSdk
        ListenerKey(final L zajk, final String zajn) {
            this.zajk = zajk;
            this.zajn = zajn;
        }
        
        @Override
        public final boolean equals(final Object o) {
            if (this != o) {
                if (!(o instanceof ListenerKey)) {
                    return false;
                }
                final ListenerKey listenerKey = (ListenerKey)o;
                if (this.zajk != listenerKey.zajk || !this.zajn.equals(listenerKey.zajn)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public final int hashCode() {
            return System.identityHashCode(this.zajk) * 31 + this.zajn.hashCode();
        }
    }
    
    @KeepForSdk
    public interface Notifier<L>
    {
        @KeepForSdk
        void notifyListener(final L p0);
        
        @KeepForSdk
        void onNotifyListenerFailed();
    }
    
    private final class zaa extends zap
    {
        public zaa(final Looper looper) {
            super(looper);
        }
        
        public final void handleMessage(final Message message) {
            boolean b = true;
            if (message.what != 1) {
                b = false;
            }
            Preconditions.checkArgument(b);
            ListenerHolder.this.notifyListenerInternal((Notifier<? super L>)message.obj);
        }
    }
}
