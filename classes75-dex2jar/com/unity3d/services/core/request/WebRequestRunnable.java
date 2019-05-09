// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.request;

import com.unity3d.services.core.log.DeviceLog;
import java.net.MalformedURLException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import java.util.List;
import java.util.Map;

public class WebRequestRunnable implements Runnable
{
    private final String _body;
    private boolean _canceled;
    private final int _connectTimeout;
    private WebRequest _currentRequest;
    private final Map<String, List<String>> _headers;
    private final IWebRequestListener _listener;
    private final int _readTimeout;
    private final String _type;
    private final String _url;
    
    public WebRequestRunnable(final String url, final String type, final String body, final int connectTimeout, final int readTimeout, final Map<String, List<String>> headers, final IWebRequestListener listener) {
        this._canceled = false;
        this._url = url;
        this._type = type;
        this._body = body;
        this._connectTimeout = connectTimeout;
        this._readTimeout = readTimeout;
        this._headers = headers;
        this._listener = listener;
    }
    
    private Map<String, List<String>> getResponseHeaders(final Bundle bundle) {
        Object o = null;
        if (bundle.size() > 0) {
            final HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
            final Iterator iterator = bundle.keySet().iterator();
            while (true) {
                o = hashMap;
                if (!iterator.hasNext()) {
                    break;
                }
                final String s = iterator.next();
                final String[] stringArray = bundle.getStringArray(s);
                if (stringArray == null) {
                    continue;
                }
                hashMap.put(s, new ArrayList<String>(Arrays.asList(stringArray)));
            }
        }
        return (Map<String, List<String>>)o;
    }
    
    private void makeRequest(String request, final String s, final Map<String, List<String>> map, String body, int i, final int n) throws MalformedURLException {
        if (!this._canceled) {
            this._currentRequest = new WebRequest((String)request, s, map, i, n);
            if (body != null) {
                this._currentRequest.setBody(body);
            }
            try {
                request = this._currentRequest.makeRequest();
                if (!this._currentRequest.isCanceled()) {
                    final Bundle bundle = new Bundle();
                    final Iterator<String> iterator = this._currentRequest.getResponseHeaders().keySet().iterator();
                    Block_6: {
                        while (iterator.hasNext()) {
                            body = iterator.next();
                            if (body != null && !body.contentEquals("null")) {
                                break Block_6;
                            }
                        }
                        goto Label_0272;
                    }
                    final String[] array = new String[this._currentRequest.getResponseHeaders().get(body).size()];
                    for (i = 0; i < this._currentRequest.getResponseHeaders().get(body).size(); ++i) {
                        array[i] = this._currentRequest.getResponseHeaders().get(body).get(i);
                    }
                    goto Label_0261;
                }
            }
            catch (IllegalStateException ex) {}
            catch (IOException request) {
                goto Label_0218;
            }
            catch (NetworkIOException request) {
                goto Label_0218;
            }
        }
    }
    
    private void onFailed(final String s) {
        this._listener.onFailed(this._url, s);
    }
    
    private void onSucceed(final String s, final int n, final Map<String, List<String>> map) {
        this._listener.onComplete(this._url, s, n, map);
    }
    
    @Override
    public void run() {
        DeviceLog.debug("Handling request message: " + this._url + " type=" + this._type);
        try {
            this.makeRequest(this._url, this._type, this._headers, this._body, this._connectTimeout, this._readTimeout);
        }
        catch (MalformedURLException ex) {
            DeviceLog.exception("Malformed URL", ex);
            this.onFailed("Malformed URL");
        }
    }
    
    public void setCancelStatus(final boolean canceled) {
        this._canceled = canceled;
        if (this._canceled && this._currentRequest != null) {
            this._currentRequest.cancel();
        }
    }
}
