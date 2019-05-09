// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import java.net.URLConnection;
import java.util.Iterator;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import com.tapjoy.internal.em;
import java.net.URL;
import java.net.HttpURLConnection;

public class TapjoyURLConnection
{
    public static final int TYPE_GET = 0;
    public static final int TYPE_POST = 1;
    
    public String getContentLength(String s) {
        while (true) {
            try {
                s = s.replaceAll(" ", "%20");
                TapjoyLog.d("TapjoyURLConnection", "requestURL: " + s);
                final HttpURLConnection httpURLConnection = (HttpURLConnection)em.a(new URL(s));
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(30000);
                s = httpURLConnection.getHeaderField("content-length");
                TapjoyLog.d("TapjoyURLConnection", "content-length: " + s);
                return s;
            }
            catch (Exception ex) {
                TapjoyLog.e("TapjoyURLConnection", "Exception: " + ex.toString());
                s = null;
                continue;
            }
            break;
        }
    }
    
    public TapjoyHttpURLResponse getRedirectFromURL(final String s) {
        return this.getResponseFromURL(s, "", 0, true, null, null, null);
    }
    
    public TapjoyHttpURLResponse getResponseFromURL(final String s) {
        return this.getResponseFromURL(s, "", 0);
    }
    
    public TapjoyHttpURLResponse getResponseFromURL(final String s, final String s2) {
        return this.getResponseFromURL(s, s2, 0);
    }
    
    public TapjoyHttpURLResponse getResponseFromURL(final String s, final String s2, final int n) {
        return this.getResponseFromURL(s, s2, n, false, null, null, null);
    }
    
    public TapjoyHttpURLResponse getResponseFromURL(String o, String string, final int n, final boolean b, final Map map, String line, final String s) {
        TapjoyHttpURLResponse tapjoyHttpURLResponse;
        HttpURLConnection httpURLConnection;
        StringBuilder sb;
        Object o2;
        Exception ex;
        BufferedReader bufferedReader;
        StringBuilder sb2 = null;
        String line2;
        OutputStreamWriter outputStreamWriter;
        StringBuilder sb3;
        String headerField;
        Label_0183_Outer:Label_0601_Outer:
        while (true) {
            tapjoyHttpURLResponse = new TapjoyHttpURLResponse();
            httpURLConnection = null;
            while (true) {
            Label_0774:
                while (true) {
                    try {
                        string = (String)o + (String)string;
                        sb = new StringBuilder("http ");
                        if (n == 0) {
                            o = "get";
                        }
                        else {
                            o = "post";
                        }
                        TapjoyLog.i("TapjoyURLConnection", sb.append((String)o).append(": ").append((String)string).toString());
                        string = em.a(new URL((String)string));
                        while (true) {
                            if (b) {
                                while (true) {
                                    Label_0755: {
                                        Label_0433: {
                                            try {
                                                ((HttpURLConnection)string).setInstanceFollowRedirects(false);
                                                ((URLConnection)string).setConnectTimeout(15000);
                                                ((URLConnection)string).setReadTimeout(30000);
                                                if (map != null) {
                                                    for (final Map.Entry<String, V> entry : map.entrySet()) {
                                                        ((URLConnection)string).setRequestProperty(entry.getKey(), (String)entry.getValue());
                                                    }
                                                }
                                                break Label_0433;
                                            }
                                            catch (Exception ex2) {
                                                o2 = string;
                                                ex = ex2;
                                            }
                                            TapjoyLog.e("TapjoyURLConnection", "Exception: " + ex.toString());
                                            tapjoyHttpURLResponse.statusCode = 0;
                                            if (o2 != null) {
                                                try {
                                                    if (tapjoyHttpURLResponse.response == null) {
                                                        bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection)o2).getErrorStream()));
                                                        sb2 = new StringBuilder();
                                                        while (true) {
                                                            line2 = bufferedReader.readLine();
                                                            if (line2 == null) {
                                                                break Label_0755;
                                                            }
                                                            sb2.append(line2 + '\n');
                                                        }
                                                    }
                                                }
                                                catch (Exception ex3) {
                                                    TapjoyLog.e("TapjoyURLConnection", "Exception trying to get error code/content: " + ex3.toString());
                                                }
                                            }
                                            TapjoyLog.i("TapjoyURLConnection", "--------------------");
                                            TapjoyLog.i("TapjoyURLConnection", "response status: " + tapjoyHttpURLResponse.statusCode);
                                            TapjoyLog.i("TapjoyURLConnection", "response size: " + tapjoyHttpURLResponse.contentLength);
                                            if (tapjoyHttpURLResponse.redirectURL != null && tapjoyHttpURLResponse.redirectURL.length() > 0) {
                                                TapjoyLog.i("TapjoyURLConnection", "redirectURL: " + tapjoyHttpURLResponse.redirectURL);
                                            }
                                            TapjoyLog.i("TapjoyURLConnection", "--------------------");
                                            return tapjoyHttpURLResponse;
                                        }
                                        if (n == 1) {
                                            ((HttpURLConnection)string).setRequestMethod("POST");
                                            if (s != null) {
                                                TapjoyLog.i("TapjoyURLConnection", "Content-Type: " + line);
                                                TapjoyLog.i("TapjoyURLConnection", "Content:");
                                                TapjoyLog.i("TapjoyURLConnection", s);
                                                ((URLConnection)string).setRequestProperty("Content-Type", line);
                                                ((URLConnection)string).setRequestProperty("Connection", "close");
                                                ((URLConnection)string).setDoOutput(true);
                                                ((HttpURLConnection)string).setFixedLengthStreamingMode(s.length());
                                                outputStreamWriter = new OutputStreamWriter(((URLConnection)string).getOutputStream());
                                                outputStreamWriter.write(s);
                                                outputStreamWriter.close();
                                            }
                                        }
                                        ((URLConnection)string).connect();
                                        tapjoyHttpURLResponse.statusCode = ((HttpURLConnection)string).getResponseCode();
                                        tapjoyHttpURLResponse.headerFields = ((URLConnection)string).getHeaderFields();
                                        tapjoyHttpURLResponse.date = ((URLConnection)string).getDate();
                                        tapjoyHttpURLResponse.expires = ((URLConnection)string).getExpiration();
                                        if (b) {
                                            break Label_0774;
                                        }
                                        o = new BufferedReader(new InputStreamReader(((URLConnection)string).getInputStream()));
                                        if (!b) {
                                            sb3 = new StringBuilder();
                                            while (true) {
                                                line = ((BufferedReader)o).readLine();
                                                if (line == null) {
                                                    break;
                                                }
                                                sb3.append(line + '\n');
                                            }
                                            tapjoyHttpURLResponse.response = sb3.toString();
                                        }
                                        if (tapjoyHttpURLResponse.statusCode == 302) {
                                            tapjoyHttpURLResponse.redirectURL = ((URLConnection)string).getHeaderField("Location");
                                        }
                                        headerField = ((URLConnection)string).getHeaderField("content-length");
                                        while (true) {
                                            if (headerField == null) {
                                                break Label_0714;
                                            }
                                            try {
                                                tapjoyHttpURLResponse.contentLength = Integer.valueOf(headerField);
                                                if (o != null) {
                                                    ((BufferedReader)o).close();
                                                }
                                                continue Label_0601_Outer;
                                            }
                                            catch (Exception ex4) {
                                                TapjoyLog.e("TapjoyURLConnection", "Exception: " + ex4.toString());
                                                continue;
                                            }
                                            break;
                                        }
                                    }
                                    tapjoyHttpURLResponse.response = sb2.toString();
                                    continue Label_0601_Outer;
                                }
                            }
                            continue Label_0183_Outer;
                        }
                    }
                    catch (Exception ex) {
                        o2 = httpURLConnection;
                        continue Label_0601_Outer;
                    }
                    break;
                }
                o = null;
                continue;
            }
        }
    }
    
    public TapjoyHttpURLResponse getResponseFromURL(final String s, final Map map) {
        return this.getResponseFromURL(s, TapjoyUtil.convertURLParams(map, false), 0);
    }
    
    public TapjoyHttpURLResponse getResponseFromURL(final String s, final Map map, final int n) {
        return this.getResponseFromURL(s, TapjoyUtil.convertURLParams(map, false), n);
    }
    
    public TapjoyHttpURLResponse getResponseFromURL(final String s, final Map map, final Map map2, final String s2) {
        String convertURLParams;
        if (map != null) {
            convertURLParams = TapjoyUtil.convertURLParams(map, false);
        }
        else {
            convertURLParams = "";
        }
        return this.getResponseFromURL(s, convertURLParams, 1, false, map2, "application/json;charset=utf-8", s2);
    }
    
    public TapjoyHttpURLResponse getResponseFromURL(final String s, final Map map, final Map map2, final Map map3) {
        String convertURLParams;
        if (map != null) {
            convertURLParams = TapjoyUtil.convertURLParams(map, false);
        }
        else {
            convertURLParams = "";
        }
        return this.getResponseFromURL(s, convertURLParams, 1, false, map2, "application/x-www-form-urlencoded", TapjoyUtil.convertURLParams(map3, false));
    }
}
