// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.tasks.runnable;

import android.support.annotation.NonNull;

public abstract class PriorityRunnable implements Comparable, Runnable
{
    @Override
    public int compareTo(@NonNull final Object o) {
        if (o instanceof PriorityRunnable) {
            return ((PriorityRunnable)o).getPriority().compareTo(this.getPriority());
        }
        return -1;
    }
    
    public abstract Integer getPriority();
}
