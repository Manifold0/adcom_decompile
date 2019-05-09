// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.objects;

public enum CrashManagerUserInput
{
    CrashManagerUserInputAlwaysSend(2), 
    CrashManagerUserInputDontSend(0), 
    CrashManagerUserInputSend(1);
    
    private final int mValue;
    
    private CrashManagerUserInput(final int mValue) {
        this.mValue = mValue;
    }
    
    public int getValue() {
        return this.mValue;
    }
}
