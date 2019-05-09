// 
// Decompiled by Procyon v0.5.34
// 

package android.arch.lifecycle;

public interface GenericLifecycleObserver extends LifecycleObserver
{
    void onStateChanged(final LifecycleOwner p0, final Lifecycle.Event p1);
}
