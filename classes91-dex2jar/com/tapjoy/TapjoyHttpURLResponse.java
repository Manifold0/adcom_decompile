// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import java.util.List;
import java.util.Map;

public class TapjoyHttpURLResponse
{
    public int contentLength;
    public long date;
    public long expires;
    public Map headerFields;
    public String redirectURL;
    public String response;
    public int statusCode;
    
    public String getHeaderFieldAsString(final String s) {
        if (this.headerFields != null) {
            final List<String> list = this.headerFields.get(s);
            if (list != null && list.get(0) != null) {
                return list.get(0);
            }
        }
        return "";
    }
}
