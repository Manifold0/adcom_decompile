// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

public class FacebookGraphResponseException extends FacebookException
{
    private final GraphResponse graphResponse;
    
    public FacebookGraphResponseException(final GraphResponse graphResponse, final String s) {
        super(s);
        this.graphResponse = graphResponse;
    }
    
    public final GraphResponse getGraphResponse() {
        return this.graphResponse;
    }
    
    @Override
    public final String toString() {
        FacebookRequestError error;
        if (this.graphResponse != null) {
            error = this.graphResponse.getError();
        }
        else {
            error = null;
        }
        final StringBuilder append = new StringBuilder().append("{FacebookGraphResponseException: ");
        final String message = this.getMessage();
        if (message != null) {
            append.append(message);
            append.append(" ");
        }
        if (error != null) {
            append.append("httpResponseCode: ").append(error.getRequestStatusCode()).append(", facebookErrorCode: ").append(error.getErrorCode()).append(", facebookErrorType: ").append(error.getErrorType()).append(", message: ").append(error.getErrorMessage()).append("}");
        }
        return append.toString();
    }
}
