// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.server;

import java.net.URLConnection;
import java.io.Serializable;
import android.text.TextUtils;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.ironsource.mediationsdk.IronSourceObject;
import java.io.OutputStream;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.net.URL;

public class HttpFunctions
{
    public static final String ERROR_PREFIX = "ERROR:";
    private static final String SERVER_BAD_REQUEST_ERROR = "Bad Request - 400";
    private static final String SERVER_REQUEST_ENCODING = "UTF-8";
    private static final String SERVER_REQUEST_GET_METHOD = "GET";
    private static final String SERVER_REQUEST_POST_METHOD = "POST";
    private static final int SERVER_REQUEST_TIMEOUT = 15000;
    
    public static boolean getStringFromPostWithAutho(String s, final String s2, String base64Auth, final String s3) {
        final OutputStream outputStream = null;
        final OutputStream outputStream2 = null;
        final HttpURLConnection httpURLConnection = null;
        Object o2;
        final Object o = o2 = null;
        OutputStream outputStream3 = outputStream2;
        Object o3 = httpURLConnection;
        OutputStream outputStream4 = outputStream;
        try {
            final URL url = new URL(s);
            o2 = o;
            outputStream3 = outputStream2;
            o3 = httpURLConnection;
            outputStream4 = outputStream;
            base64Auth = IronSourceUtils.getBase64Auth(base64Auth, s3);
            o2 = o;
            outputStream3 = outputStream2;
            o3 = httpURLConnection;
            outputStream4 = outputStream;
            s = (String)(o2 = url.openConnection());
            outputStream3 = outputStream2;
            o3 = s;
            outputStream4 = outputStream;
            ((URLConnection)s).setReadTimeout(15000);
            o2 = s;
            outputStream3 = outputStream2;
            o3 = s;
            outputStream4 = outputStream;
            ((URLConnection)s).setConnectTimeout(15000);
            o2 = s;
            outputStream3 = outputStream2;
            o3 = s;
            outputStream4 = outputStream;
            ((HttpURLConnection)s).setRequestMethod("POST");
            o2 = s;
            outputStream3 = outputStream2;
            o3 = s;
            outputStream4 = outputStream;
            ((URLConnection)s).setRequestProperty("Authorization", base64Auth);
            o2 = s;
            outputStream3 = outputStream2;
            o3 = s;
            outputStream4 = outputStream;
            ((URLConnection)s).setDoInput(true);
            o2 = s;
            outputStream3 = outputStream2;
            o3 = s;
            outputStream4 = outputStream;
            ((URLConnection)s).setDoOutput(true);
            o2 = s;
            outputStream3 = outputStream2;
            o3 = s;
            outputStream4 = outputStream;
            final OutputStream outputStream5 = ((URLConnection)s).getOutputStream();
            o2 = s;
            outputStream3 = outputStream5;
            o3 = s;
            outputStream4 = outputStream5;
            final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream5, "UTF-8"));
            o2 = s;
            outputStream3 = outputStream5;
            o3 = s;
            outputStream4 = outputStream5;
            bufferedWriter.write(s2);
            o2 = s;
            outputStream3 = outputStream5;
            o3 = s;
            outputStream4 = outputStream5;
            bufferedWriter.flush();
            o2 = s;
            outputStream3 = outputStream5;
            o3 = s;
            outputStream4 = outputStream5;
            bufferedWriter.close();
            o2 = s;
            outputStream3 = outputStream5;
            o3 = s;
            outputStream4 = outputStream5;
            Label_0350: {
                if (((HttpURLConnection)s).getResponseCode() != 200) {
                    break Label_0350;
                }
                boolean b = true;
                Label_0339: {
                    if (outputStream5 == null) {
                        break Label_0339;
                    }
                    try {
                        outputStream5.close();
                        if (s != null) {
                            ((HttpURLConnection)s).disconnect();
                        }
                        return b;
                        b = false;
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        catch (Exception ex4) {
            Label_0375: {
                if (outputStream3 == null) {
                    break Label_0375;
                }
                try {
                    outputStream3.close();
                    if (o2 != null) {
                        ((HttpURLConnection)o2).disconnect();
                    }
                    return false;
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        finally {
            Label_0406: {
                if (outputStream4 == null) {
                    break Label_0406;
                }
                try {
                    outputStream4.close();
                    if (o3 != null) {
                        ((HttpURLConnection)o3).disconnect();
                    }
                }
                catch (IOException ex3) {
                    ex3.printStackTrace();
                }
            }
        }
    }
    
    public static String getStringFromURL(final String s) throws Exception {
        return getStringFromURL(s, null);
    }
    
    public static String getStringFromURL(String s, IronSourceObject.IResponseListener responseListener) throws Exception {
        Serializable line = null;
        Serializable string = null;
    Block_15_Outer:
        while (true) {
            try {
                s = (String)(line = (string = (Serializable)new URL(s).openConnection()));
                ((URLConnection)s).setReadTimeout(15000);
                string = s;
                line = s;
                ((URLConnection)s).setConnectTimeout(15000);
                string = s;
                line = s;
                ((HttpURLConnection)s).setRequestMethod("GET");
                string = s;
                line = s;
                ((URLConnection)s).setDoInput(true);
                string = s;
                line = s;
                ((URLConnection)s).connect();
                string = s;
                line = s;
                if (((HttpURLConnection)s).getResponseCode() == 400) {
                    if (responseListener != null) {
                        string = s;
                        line = s;
                        responseListener.onUnrecoverableError("Bad Request - 400");
                    }
                    if (s != null) {
                        ((HttpURLConnection)s).disconnect();
                    }
                    if (false) {
                        throw new NullPointerException();
                    }
                    return null;
                }
                else {
                    string = s;
                    line = s;
                    responseListener = (IronSourceObject.IResponseListener)new BufferedReader(new InputStreamReader(((URLConnection)s).getInputStream()));
                    Label_0206: {
                        try {
                            string = new StringBuilder();
                            while (true) {
                                line = ((BufferedReader)responseListener).readLine();
                                if (line == null) {
                                    break Label_0206;
                                }
                                ((StringBuilder)string).append((String)line);
                            }
                        }
                        catch (Exception ex) {
                            if (s != null) {
                                ((HttpURLConnection)s).disconnect();
                            }
                            if (responseListener != null) {
                                ((BufferedReader)responseListener).close();
                            }
                            return null;
                            string = ((StringBuilder)string).toString();
                            // iftrue(Label_0238:, !TextUtils.isEmpty((CharSequence)string))
                            // iftrue(Label_0228:, s == null)
                            // iftrue(Label_0254:, responseListener == null)
                            // iftrue(Label_0268:, string == null)
                            // iftrue(Label_0236:, responseListener == null)
                            // iftrue(Label_0276:, s == null)
                            // iftrue(Label_0246:, s == null)
                            Block_14:Label_0268_Outer:
                            while (true) {
                                while (true) {
                                    Block_18: {
                                        while (true) {
                                            Block_13: {
                                                break Block_13;
                                                Block_16: {
                                                    while (true) {
                                                        break Block_16;
                                                        break Block_18;
                                                        ((HttpURLConnection)s).disconnect();
                                                        continue Block_15_Outer;
                                                    }
                                                    Label_0254: {
                                                        return (String)string;
                                                    }
                                                    break Block_14;
                                                }
                                                ((BufferedReader)responseListener).close();
                                                return (String)string;
                                            }
                                            ((HttpURLConnection)s).disconnect();
                                            continue Label_0268_Outer;
                                        }
                                        ((BufferedReader)s).close();
                                        throw responseListener;
                                    }
                                    ((HttpURLConnection)string).disconnect();
                                    continue;
                                }
                                Label_0276: {
                                    throw responseListener;
                                }
                                Label_0236:
                                return null;
                                Label_0238:
                                continue Label_0268_Outer;
                            }
                            ((BufferedReader)responseListener).close();
                        }
                        finally {
                            string = s;
                            s = (String)responseListener;
                            responseListener = (IronSourceObject.IResponseListener)line;
                        }
                    }
                }
            }
            catch (Exception ex2) {
                continue;
            }
            break;
        }
    }
}
