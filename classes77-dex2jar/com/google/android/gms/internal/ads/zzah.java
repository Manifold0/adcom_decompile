// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.util.List;
import org.apache.http.conn.ConnectTimeoutException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Map;

final class zzah extends zzai
{
    private final zzar zzbo;
    
    zzah(final zzar zzbo) {
        this.zzbo = zzbo;
    }
    
    @Override
    public final zzaq zza(final zzr<?> zzr, final Map<String, String> map) throws IOException, zza {
        HttpResponse zzb;
        int statusCode;
        ArrayList list;
        try {
            zzb = this.zzbo.zzb(zzr, map);
            statusCode = zzb.getStatusLine().getStatusCode();
            final Header[] allHeaders = zzb.getAllHeaders();
            list = new ArrayList<zzl>(allHeaders.length);
            for (int length = allHeaders.length, i = 0; i < length; ++i) {
                final Header header = allHeaders[i];
                list.add(new zzl(header.getName(), header.getValue()));
            }
        }
        catch (ConnectTimeoutException ex) {
            throw new SocketTimeoutException(ex.getMessage());
        }
        if (zzb.getEntity() == null) {
            return new zzaq(statusCode, (List<zzl>)list);
        }
        final long contentLength = zzb.getEntity().getContentLength();
        if ((int)contentLength != contentLength) {
            throw new IOException(new StringBuilder(40).append("Response too large: ").append(contentLength).toString());
        }
        return new zzaq(statusCode, (List<zzl>)list, (int)zzb.getEntity().getContentLength(), zzb.getEntity().getContent());
    }
}
