package com.tapjoy;

import java.util.List;
import java.util.Map;

public class TapjoyHttpURLResponse {
    public int contentLength;
    public long date;
    public long expires;
    public Map headerFields;
    public String redirectURL;
    public String response;
    public int statusCode;

    public String getHeaderFieldAsString(String headerKey) {
        String str = "";
        if (this.headerFields != null) {
            List list = (List) this.headerFields.get(headerKey);
            if (!(list == null || list.get(0) == null)) {
                return (String) list.get(0);
            }
        }
        return str;
    }
}
