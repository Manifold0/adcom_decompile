// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.objects;

import java.io.Serializable;

public class ErrorObject implements Serializable
{
    private static final long serialVersionUID = 1508110658372169868L;
    private int mCode;
    private String mMessage;
    
    public int getCode() {
        return this.mCode;
    }
    
    public String getMessage() {
        return this.mMessage;
    }
    
    public void setCode(final int mCode) {
        this.mCode = mCode;
    }
    
    public void setMessage(final String mMessage) {
        this.mMessage = mMessage;
    }
}
