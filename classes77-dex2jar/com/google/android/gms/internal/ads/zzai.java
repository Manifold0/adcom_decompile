// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.InputStream;
import java.util.Iterator;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import java.util.ArrayList;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.ProtocolVersion;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.util.Map;

public abstract class zzai implements zzar
{
    public abstract zzaq zza(final zzr<?> p0, final Map<String, String> p1) throws IOException, zza;
    
    @Deprecated
    @Override
    public final HttpResponse zzb(final zzr<?> zzr, final Map<String, String> map) throws IOException, zza {
        final zzaq zza = this.zza(zzr, map);
        final BasicHttpResponse basicHttpResponse = new BasicHttpResponse((StatusLine)new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), zza.getStatusCode(), ""));
        final ArrayList<BasicHeader> list = new ArrayList<BasicHeader>();
        for (final zzl zzl : zza.zzq()) {
            list.add(new BasicHeader(zzl.getName(), zzl.getValue()));
        }
        basicHttpResponse.setHeaders((Header[])list.toArray(new Header[list.size()]));
        final InputStream content = zza.getContent();
        if (content != null) {
            final BasicHttpEntity entity = new BasicHttpEntity();
            entity.setContent(content);
            entity.setContentLength((long)zza.getContentLength());
            basicHttpResponse.setEntity((HttpEntity)entity);
        }
        return (HttpResponse)basicHttpResponse;
    }
}
