// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.http2;

public enum ErrorCode
{
    CANCEL(8), 
    FLOW_CONTROL_ERROR(3), 
    INTERNAL_ERROR(2), 
    NO_ERROR(0), 
    PROTOCOL_ERROR(1), 
    REFUSED_STREAM(7);
    
    public final int httpCode;
    
    private ErrorCode(final int httpCode) {
        this.httpCode = httpCode;
    }
    
    public static ErrorCode fromHttp2(final int n) {
        final ErrorCode[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final ErrorCode errorCode = values[i];
            if (errorCode.httpCode == n) {
                return errorCode;
            }
        }
        return null;
    }
}
