// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.net.UnknownHostException;
import java.net.ConnectException;
import java.io.InputStream;
import java.util.Scanner;
import java.net.URL;
import java.net.HttpURLConnection;
import org.json.JSONObject;

class OneSignalRestClient
{
    private static final String BASE_URL = "https://onesignal.com/api/v1/";
    private static final int GET_TIMEOUT = 60000;
    private static final int TIMEOUT = 120000;
    
    private static Thread callResponseHandlerOnFailure(final ResponseHandler responseHandler, final int n, final String s, final Throwable t) {
        if (responseHandler == null) {
            return null;
        }
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                responseHandler.onFailure(n, s, t);
            }
        });
        thread.start();
        return thread;
    }
    
    private static Thread callResponseHandlerOnSuccess(final ResponseHandler responseHandler, final String s) {
        if (responseHandler == null) {
            return null;
        }
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                responseHandler.onSuccess(s);
            }
        });
        thread.start();
        return thread;
    }
    
    static void get(final String s, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                makeRequest(s, null, null, responseHandler, 60000);
            }
        }).start();
    }
    
    static void getSync(final String s, final ResponseHandler responseHandler) {
        makeRequest(s, null, null, responseHandler, 60000);
    }
    
    private static int getThreadTimeout(final int n) {
        return n + 5000;
    }
    
    private static void makeRequest(final String s, final String s2, final JSONObject jsonObject, final ResponseHandler responseHandler, final int n) {
        if (s2 == null || !OneSignal.shouldLogUserPrivacyConsentErrorMessageForMethodName(null)) {
            final Thread[] array = { null };
            final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    array[0] = startHTTPConnection(s, s2, jsonObject, responseHandler, n);
                }
            }, "OS_HTTPConnection");
            thread.start();
            try {
                thread.join(getThreadTimeout(n));
                if (thread.getState() != Thread.State.TERMINATED) {
                    thread.interrupt();
                }
                if (array[0] != null) {
                    array[0].join();
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    static void post(final String s, final JSONObject jsonObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                makeRequest(s, "POST", jsonObject, responseHandler, 120000);
            }
        }).start();
    }
    
    static void postSync(final String s, final JSONObject jsonObject, final ResponseHandler responseHandler) {
        makeRequest(s, "POST", jsonObject, responseHandler, 120000);
    }
    
    static void put(final String s, final JSONObject jsonObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                makeRequest(s, "PUT", jsonObject, responseHandler, 120000);
            }
        }).start();
    }
    
    static void putSync(final String s, final JSONObject jsonObject, final ResponseHandler responseHandler) {
        makeRequest(s, "PUT", jsonObject, responseHandler, 120000);
    }
    
    private static Thread startHTTPConnection(String s, String callResponseHandlerOnFailure, JSONObject jsonObject, final ResponseHandler responseHandler, int responseCode) {
        while (true) {
            final HttpURLConnection httpURLConnection = null;
            HttpURLConnection httpURLConnection2 = null;
            final int n = -1;
            final String s2 = null;
            HttpURLConnection httpURLConnection3 = httpURLConnection2;
            int n2 = n;
            HttpURLConnection httpURLConnection4 = httpURLConnection;
            while (true) {
                Label_1059: {
                    try {
                        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignalRestClient: Making request to: https://onesignal.com/api/v1/" + s);
                        httpURLConnection3 = httpURLConnection2;
                        n2 = n;
                        httpURLConnection4 = httpURLConnection;
                        httpURLConnection2 = (httpURLConnection3 = (HttpURLConnection)new URL("https://onesignal.com/api/v1/" + s).openConnection());
                        n2 = n;
                        httpURLConnection4 = httpURLConnection2;
                        httpURLConnection2.setUseCaches(false);
                        httpURLConnection3 = httpURLConnection2;
                        n2 = n;
                        httpURLConnection4 = httpURLConnection2;
                        httpURLConnection2.setConnectTimeout(responseCode);
                        httpURLConnection3 = httpURLConnection2;
                        n2 = n;
                        httpURLConnection4 = httpURLConnection2;
                        httpURLConnection2.setReadTimeout(responseCode);
                        if (jsonObject != null) {
                            httpURLConnection3 = httpURLConnection2;
                            n2 = n;
                            httpURLConnection4 = httpURLConnection2;
                            httpURLConnection2.setDoInput(true);
                        }
                        if (callResponseHandlerOnFailure != null) {
                            httpURLConnection3 = httpURLConnection2;
                            n2 = n;
                            httpURLConnection4 = httpURLConnection2;
                            httpURLConnection2.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                            httpURLConnection3 = httpURLConnection2;
                            n2 = n;
                            httpURLConnection4 = httpURLConnection2;
                            httpURLConnection2.setRequestMethod((String)callResponseHandlerOnFailure);
                            httpURLConnection3 = httpURLConnection2;
                            n2 = n;
                            httpURLConnection4 = httpURLConnection2;
                            httpURLConnection2.setDoOutput(true);
                        }
                        if (jsonObject != null) {
                            httpURLConnection3 = httpURLConnection2;
                            n2 = n;
                            httpURLConnection4 = httpURLConnection2;
                            jsonObject = (JSONObject)jsonObject.toString();
                            httpURLConnection3 = httpURLConnection2;
                            n2 = n;
                            httpURLConnection4 = httpURLConnection2;
                            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignalRestClient: " + (String)callResponseHandlerOnFailure + " SEND JSON: " + (String)jsonObject);
                            httpURLConnection3 = httpURLConnection2;
                            n2 = n;
                            httpURLConnection4 = httpURLConnection2;
                            jsonObject = (JSONObject)(Object)((String)jsonObject).getBytes("UTF-8");
                            httpURLConnection3 = httpURLConnection2;
                            n2 = n;
                            httpURLConnection4 = httpURLConnection2;
                            httpURLConnection2.setFixedLengthStreamingMode(jsonObject.length);
                            httpURLConnection3 = httpURLConnection2;
                            n2 = n;
                            httpURLConnection4 = httpURLConnection2;
                            httpURLConnection2.getOutputStream().write((byte[])(Object)jsonObject);
                        }
                        httpURLConnection3 = httpURLConnection2;
                        n2 = n;
                        httpURLConnection4 = httpURLConnection2;
                        responseCode = httpURLConnection2.getResponseCode();
                        httpURLConnection3 = httpURLConnection2;
                        n2 = responseCode;
                        httpURLConnection4 = httpURLConnection2;
                        OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "OneSignalRestClient: After con.getResponseCode  to: https://onesignal.com/api/v1/" + s);
                        Thread thread;
                        if (responseCode == 200) {
                            httpURLConnection3 = httpURLConnection2;
                            n2 = responseCode;
                            httpURLConnection4 = httpURLConnection2;
                            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignalRestClient: Successfully finished request to: https://onesignal.com/api/v1/" + s);
                            httpURLConnection3 = httpURLConnection2;
                            n2 = responseCode;
                            httpURLConnection4 = httpURLConnection2;
                            jsonObject = (JSONObject)new Scanner(httpURLConnection2.getInputStream(), "UTF-8");
                            httpURLConnection3 = httpURLConnection2;
                            n2 = responseCode;
                            httpURLConnection4 = httpURLConnection2;
                            if (((Scanner)jsonObject).useDelimiter("\\A").hasNext()) {
                                httpURLConnection3 = httpURLConnection2;
                                n2 = responseCode;
                                httpURLConnection4 = httpURLConnection2;
                                s = ((Scanner)jsonObject).next();
                            }
                            else {
                                s = "";
                            }
                            httpURLConnection3 = httpURLConnection2;
                            n2 = responseCode;
                            httpURLConnection4 = httpURLConnection2;
                            ((Scanner)jsonObject).close();
                            httpURLConnection3 = httpURLConnection2;
                            n2 = responseCode;
                            httpURLConnection4 = httpURLConnection2;
                            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, (String)callResponseHandlerOnFailure + " RECEIVED JSON: " + s);
                            httpURLConnection3 = httpURLConnection2;
                            n2 = responseCode;
                            httpURLConnection4 = httpURLConnection2;
                            thread = callResponseHandlerOnSuccess(responseHandler, s);
                        }
                        else {
                            httpURLConnection3 = httpURLConnection2;
                            n2 = responseCode;
                            httpURLConnection4 = httpURLConnection2;
                            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "OneSignalRestClient: Failed request to: https://onesignal.com/api/v1/" + s);
                            httpURLConnection3 = httpURLConnection2;
                            n2 = responseCode;
                            httpURLConnection4 = httpURLConnection2;
                            jsonObject = (JSONObject)httpURLConnection2.getErrorStream();
                            Object inputStream;
                            if ((inputStream = jsonObject) == null) {
                                httpURLConnection3 = httpURLConnection2;
                                n2 = responseCode;
                                httpURLConnection4 = httpURLConnection2;
                                inputStream = httpURLConnection2.getInputStream();
                            }
                            if (inputStream != null) {
                                httpURLConnection3 = httpURLConnection2;
                                n2 = responseCode;
                                httpURLConnection4 = httpURLConnection2;
                                jsonObject = (JSONObject)new Scanner((InputStream)inputStream, "UTF-8");
                                httpURLConnection3 = httpURLConnection2;
                                n2 = responseCode;
                                httpURLConnection4 = httpURLConnection2;
                                if (!((Scanner)jsonObject).useDelimiter("\\A").hasNext()) {
                                    break Label_1059;
                                }
                                httpURLConnection3 = httpURLConnection2;
                                n2 = responseCode;
                                httpURLConnection4 = httpURLConnection2;
                                s = ((Scanner)jsonObject).next();
                                httpURLConnection3 = httpURLConnection2;
                                n2 = responseCode;
                                httpURLConnection4 = httpURLConnection2;
                                ((Scanner)jsonObject).close();
                                httpURLConnection3 = httpURLConnection2;
                                n2 = responseCode;
                                httpURLConnection4 = httpURLConnection2;
                                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "OneSignalRestClient: " + (String)callResponseHandlerOnFailure + " RECEIVED JSON: " + s);
                            }
                            else {
                                httpURLConnection3 = httpURLConnection2;
                                n2 = responseCode;
                                httpURLConnection4 = httpURLConnection2;
                                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "OneSignalRestClient: " + (String)callResponseHandlerOnFailure + " HTTP Code: " + responseCode + " No response body!");
                                s = s2;
                            }
                            httpURLConnection3 = httpURLConnection2;
                            n2 = responseCode;
                            httpURLConnection4 = httpURLConnection2;
                            thread = callResponseHandlerOnFailure(responseHandler, responseCode, s, null);
                        }
                        callResponseHandlerOnFailure = thread;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                            callResponseHandlerOnFailure = thread;
                        }
                        return (Thread)callResponseHandlerOnFailure;
                    }
                    catch (Throwable t) {
                        httpURLConnection4 = httpURLConnection3;
                        Label_0980: {
                            if (!(t instanceof ConnectException)) {
                                httpURLConnection4 = httpURLConnection3;
                                if (!(t instanceof UnknownHostException)) {
                                    httpURLConnection4 = httpURLConnection3;
                                    OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "OneSignalRestClient: " + (String)callResponseHandlerOnFailure + " Error thrown from network stack. ", t);
                                    break Label_0980;
                                }
                            }
                            httpURLConnection4 = httpURLConnection3;
                            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "OneSignalRestClient: Could not send last request, device is offline. Throwable: " + t.getClass().getName());
                        }
                        httpURLConnection4 = httpURLConnection3;
                        return (Thread)(callResponseHandlerOnFailure = callResponseHandlerOnFailure(responseHandler, n2, null, t));
                    }
                    finally {
                        if (httpURLConnection4 != null) {
                            httpURLConnection4.disconnect();
                        }
                    }
                }
                s = "";
                continue;
            }
        }
    }
    
    static class ResponseHandler
    {
        void onFailure(final int n, final String s, final Throwable t) {
        }
        
        void onSuccess(final String s) {
        }
    }
}
