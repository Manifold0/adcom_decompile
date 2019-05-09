// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.messaging;

import java.util.Locale;

public final class SendException extends Exception
{
    public static final int ERROR_INVALID_PARAMETERS = 1;
    public static final int ERROR_SIZE = 2;
    public static final int ERROR_TOO_MANY_MESSAGES = 4;
    public static final int ERROR_TTL_EXCEEDED = 3;
    public static final int ERROR_UNKNOWN = 0;
    private final int errorCode;
    
    SendException(String lowerCase) {
        int errorCode = 1;
        super(lowerCase);
        Label_0114: {
            if (lowerCase != null) {
                lowerCase = lowerCase.toLowerCase(Locale.US);
                switch (lowerCase) {
                    case "toomanymessages": {
                        errorCode = 4;
                        break Label_0114;
                    }
                    case "service_not_available": {
                        errorCode = 3;
                        break Label_0114;
                    }
                    case "messagetoobig": {
                        errorCode = 2;
                    }
                    case "invalid_parameters":
                    case "missing_to": {
                        break Label_0114;
                    }
                }
            }
            errorCode = 0;
        }
        this.errorCode = errorCode;
    }
    
    public final int getErrorCode() {
        return this.errorCode;
    }
}
