// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.objects;

public enum FeedbackUserDataElement
{
    DONT_SHOW(0), 
    OPTIONAL(1), 
    REQUIRED(2);
    
    private final int mValue;
    
    private FeedbackUserDataElement(final int mValue) {
        this.mValue = mValue;
    }
    
    public int getValue() {
        return this.mValue;
    }
}
