package com.tapjoy;

import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.internal.em;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public class TapjoyURLConnection {
    public static final int TYPE_GET = 0;
    public static final int TYPE_POST = 1;

    public TapjoyHttpURLResponse getRedirectFromURL(String url) {
        return getResponseFromURL(url, "", 0, true, null, null, null);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String url, Map params) {
        return getResponseFromURL(url, TapjoyUtil.convertURLParams(params, false), 0);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String url, Map params, int type) {
        return getResponseFromURL(url, TapjoyUtil.convertURLParams(params, false), type);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String url) {
        return getResponseFromURL(url, "", 0);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String url, String params) {
        return getResponseFromURL(url, params, 0);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String url, String params, int type) {
        return getResponseFromURL(url, params, type, false, null, null, null);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String url, Map params, Map headers, Map postParams) {
        return getResponseFromURL(url, params != null ? TapjoyUtil.convertURLParams(params, false) : "", 1, false, headers, "application/x-www-form-urlencoded", TapjoyUtil.convertURLParams(postParams, false));
    }

    public TapjoyHttpURLResponse getResponseFromURL(String url, Map params, Map headers, String postJsonData) {
        return getResponseFromURL(url, params != null ? TapjoyUtil.convertURLParams(params, false) : "", 1, false, headers, "application/json;charset=utf-8", postJsonData);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String url, String params, int type, boolean getRedirectOnly, Map headers, String postContentType, String postContent) {
        Exception exception;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;
        TapjoyHttpURLResponse tapjoyHttpURLResponse = new TapjoyHttpURLResponse();
        HttpURLConnection httpURLConnection = null;
        String str;
        try {
            BufferedReader bufferedReader2;
            str = url + params;
            TapjoyLog.m7129i("TapjoyURLConnection", "http " + (type == 0 ? "get" : "post") + ": " + str);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) em.m7652a(new URL(str));
            if (getRedirectOnly) {
                try {
                    httpURLConnection2.setInstanceFollowRedirects(false);
                } catch (Exception e) {
                    Exception exception2 = e;
                    httpURLConnection = httpURLConnection2;
                    exception = exception2;
                    TapjoyLog.m7128e("TapjoyURLConnection", "Exception: " + exception.toString());
                    tapjoyHttpURLResponse.statusCode = 0;
                    if (httpURLConnection != null) {
                        try {
                            if (tapjoyHttpURLResponse.response == null) {
                                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                                stringBuilder = new StringBuilder();
                                while (true) {
                                    str = bufferedReader.readLine();
                                    if (str != null) {
                                        break;
                                    }
                                    stringBuilder.append(str + '\n');
                                }
                                tapjoyHttpURLResponse.response = stringBuilder.toString();
                            }
                        } catch (Exception exception3) {
                            TapjoyLog.m7128e("TapjoyURLConnection", "Exception trying to get error code/content: " + exception3.toString());
                        }
                    }
                    TapjoyLog.m7129i("TapjoyURLConnection", "--------------------");
                    TapjoyLog.m7129i("TapjoyURLConnection", "response status: " + tapjoyHttpURLResponse.statusCode);
                    TapjoyLog.m7129i("TapjoyURLConnection", "response size: " + tapjoyHttpURLResponse.contentLength);
                    TapjoyLog.m7129i("TapjoyURLConnection", "redirectURL: " + tapjoyHttpURLResponse.redirectURL);
                    TapjoyLog.m7129i("TapjoyURLConnection", "--------------------");
                    return tapjoyHttpURLResponse;
                }
            }
            httpURLConnection2.setConnectTimeout(15000);
            httpURLConnection2.setReadTimeout(30000);
            if (headers != null) {
                for (Entry entry : headers.entrySet()) {
                    httpURLConnection2.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }
            if (type == 1) {
                httpURLConnection2.setRequestMethod("POST");
                if (postContent != null) {
                    TapjoyLog.m7129i("TapjoyURLConnection", "Content-Type: " + postContentType);
                    TapjoyLog.m7129i("TapjoyURLConnection", "Content:");
                    TapjoyLog.m7129i("TapjoyURLConnection", postContent);
                    httpURLConnection2.setRequestProperty("Content-Type", postContentType);
                    httpURLConnection2.setRequestProperty("Connection", String.CLOSE);
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setFixedLengthStreamingMode(postContent.length());
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection2.getOutputStream());
                    outputStreamWriter.write(postContent);
                    outputStreamWriter.close();
                }
            }
            httpURLConnection2.connect();
            tapjoyHttpURLResponse.statusCode = httpURLConnection2.getResponseCode();
            tapjoyHttpURLResponse.headerFields = httpURLConnection2.getHeaderFields();
            tapjoyHttpURLResponse.date = httpURLConnection2.getDate();
            tapjoyHttpURLResponse.expires = httpURLConnection2.getExpiration();
            if (getRedirectOnly) {
                bufferedReader2 = null;
            } else {
                bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
            }
            if (!getRedirectOnly) {
                stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine + '\n');
                }
                tapjoyHttpURLResponse.response = stringBuilder.toString();
            }
            if (tapjoyHttpURLResponse.statusCode == IronSourceConstants.OFFERWALL_AVAILABLE) {
                tapjoyHttpURLResponse.redirectURL = httpURLConnection2.getHeaderField("Location");
            }
            String headerField = httpURLConnection2.getHeaderField("content-length");
            if (headerField != null) {
                try {
                    tapjoyHttpURLResponse.contentLength = Integer.valueOf(headerField).intValue();
                } catch (Exception e2) {
                    TapjoyLog.m7128e("TapjoyURLConnection", "Exception: " + e2.toString());
                }
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
        } catch (Exception e3) {
            exception3 = e3;
            TapjoyLog.m7128e("TapjoyURLConnection", "Exception: " + exception3.toString());
            tapjoyHttpURLResponse.statusCode = 0;
            if (httpURLConnection != null) {
                if (tapjoyHttpURLResponse.response == null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                    stringBuilder = new StringBuilder();
                    while (true) {
                        str = bufferedReader.readLine();
                        if (str != null) {
                            break;
                        }
                        stringBuilder.append(str + '\n');
                    }
                    tapjoyHttpURLResponse.response = stringBuilder.toString();
                }
            }
            TapjoyLog.m7129i("TapjoyURLConnection", "--------------------");
            TapjoyLog.m7129i("TapjoyURLConnection", "response status: " + tapjoyHttpURLResponse.statusCode);
            TapjoyLog.m7129i("TapjoyURLConnection", "response size: " + tapjoyHttpURLResponse.contentLength);
            TapjoyLog.m7129i("TapjoyURLConnection", "redirectURL: " + tapjoyHttpURLResponse.redirectURL);
            TapjoyLog.m7129i("TapjoyURLConnection", "--------------------");
            return tapjoyHttpURLResponse;
        }
        TapjoyLog.m7129i("TapjoyURLConnection", "--------------------");
        TapjoyLog.m7129i("TapjoyURLConnection", "response status: " + tapjoyHttpURLResponse.statusCode);
        TapjoyLog.m7129i("TapjoyURLConnection", "response size: " + tapjoyHttpURLResponse.contentLength);
        if (tapjoyHttpURLResponse.redirectURL != null && tapjoyHttpURLResponse.redirectURL.length() > 0) {
            TapjoyLog.m7129i("TapjoyURLConnection", "redirectURL: " + tapjoyHttpURLResponse.redirectURL);
        }
        TapjoyLog.m7129i("TapjoyURLConnection", "--------------------");
        return tapjoyHttpURLResponse;
    }

    public String getContentLength(String url) {
        String replaceAll;
        try {
            replaceAll = url.replaceAll(" ", "%20");
            TapjoyLog.m7126d("TapjoyURLConnection", "requestURL: " + replaceAll);
            HttpURLConnection httpURLConnection = (HttpURLConnection) em.m7652a(new URL(replaceAll));
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(30000);
            replaceAll = httpURLConnection.getHeaderField("content-length");
        } catch (Exception e) {
            TapjoyLog.m7128e("TapjoyURLConnection", "Exception: " + e.toString());
            replaceAll = null;
        }
        TapjoyLog.m7126d("TapjoyURLConnection", "content-length: " + replaceAll);
        return replaceAll;
    }
}
