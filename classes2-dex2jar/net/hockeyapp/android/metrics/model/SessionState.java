// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

public enum SessionState
{
    END(1), 
    START(0);
    
    private final int value;
    
    private SessionState(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
