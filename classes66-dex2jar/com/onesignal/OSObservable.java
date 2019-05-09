// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class OSObservable<ObserverType, StateType>
{
    private boolean fireOnMainThread;
    private String methodName;
    private List<Object> observers;
    
    OSObservable(final String methodName, final boolean fireOnMainThread) {
        this.methodName = methodName;
        this.fireOnMainThread = fireOnMainThread;
        this.observers = new ArrayList<Object>();
    }
    
    void addObserver(final ObserverType observerType) {
        this.observers.add(new WeakReference(observerType));
    }
    
    void addObserverStrong(final ObserverType observerType) {
        this.observers.add(observerType);
    }
    
    boolean notifyChange(final StateType stateType) {
        boolean b = false;
        for (Object o : this.observers) {
            if (o instanceof WeakReference) {
                o = ((WeakReference<Object>)o).get();
            }
            if (o != null) {
                Label_0133: {
                    try {
                        final Method declaredMethod = ((WeakReference<Object>)o).getClass().getDeclaredMethod(this.methodName, stateType.getClass());
                        declaredMethod.setAccessible(true);
                        if (this.fireOnMainThread) {
                            OSUtils.runOnMainUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        declaredMethod.invoke(o, stateType);
                                    }
                                    catch (Throwable t) {
                                        t.printStackTrace();
                                    }
                                }
                            });
                            break Label_0133;
                        }
                        declaredMethod.invoke(o, stateType);
                        break Label_0133;
                    }
                    catch (Throwable t) {
                        t.printStackTrace();
                        continue;
                    }
                    break;
                }
                b = true;
            }
        }
        return b;
    }
    
    void removeObserver(final ObserverType observerType) {
        for (int i = 0; i < this.observers.size(); ++i) {
            if (((WeakReference<Object>)this.observers.get(i)).get().equals(observerType)) {
                this.observers.remove(i);
                break;
            }
        }
    }
}
