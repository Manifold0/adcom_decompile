package com.ironsource.mediationsdk.server;

import android.text.TextUtils;
import com.ironsource.mediationsdk.IronSourceObject.IResponseListener;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpFunctions {
    public static final String ERROR_PREFIX = "ERROR:";
    private static final String SERVER_BAD_REQUEST_ERROR = "Bad Request - 400";
    private static final String SERVER_REQUEST_ENCODING = "UTF-8";
    private static final String SERVER_REQUEST_GET_METHOD = "GET";
    private static final String SERVER_REQUEST_POST_METHOD = "POST";
    private static final int SERVER_REQUEST_TIMEOUT = 15000;

    public static String getStringFromURL(String link) throws Exception {
        return getStringFromURL(link, null);
    }

    public static String getStringFromURL(String link, IResponseListener listener) throws Exception {
        Throwable th;
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        try {
            conn = (HttpURLConnection) new URL(link).openConnection();
            conn.setReadTimeout(SERVER_REQUEST_TIMEOUT);
            conn.setConnectTimeout(SERVER_REQUEST_TIMEOUT);
            conn.setRequestMethod(SERVER_REQUEST_GET_METHOD);
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == 400) {
                if (listener != null) {
                    listener.onUnrecoverableError(SERVER_BAD_REQUEST_ERROR);
                }
                if (conn != null) {
                    conn.disconnect();
                }
                if (reader != null) {
                    reader.close();
                }
                return null;
            }
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String line = reader2.readLine();
                    if (line == null) {
                        break;
                    }
                    stringBuilder.append(line);
                }
                String result = stringBuilder.toString();
                if (TextUtils.isEmpty(result)) {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (reader2 != null) {
                        reader2.close();
                    }
                    reader = reader2;
                    return null;
                }
                if (conn != null) {
                    conn.disconnect();
                }
                if (reader2 != null) {
                    reader2.close();
                }
                reader = reader2;
                return result;
            } catch (Exception e) {
                reader = reader2;
                if (conn != null) {
                    conn.disconnect();
                }
                if (reader != null) {
                    reader.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                reader = reader2;
                if (conn != null) {
                    conn.disconnect();
                }
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            if (conn != null) {
                conn.disconnect();
            }
            if (reader != null) {
                reader.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (conn != null) {
                conn.disconnect();
            }
            if (reader != null) {
                reader.close();
            }
            throw th;
        }
    }

    public static boolean getStringFromPostWithAutho(String url, String json, String userName, String password) {
        OutputStream os = null;
        HttpURLConnection conn = null;
        try {
            boolean z;
            URL requestURL = new URL(url);
            String authorizationString = IronSourceUtils.getBase64Auth(userName, password);
            conn = (HttpURLConnection) requestURL.openConnection();
            conn.setReadTimeout(SERVER_REQUEST_TIMEOUT);
            conn.setConnectTimeout(SERVER_REQUEST_TIMEOUT);
            conn.setRequestMethod(SERVER_REQUEST_POST_METHOD);
            conn.setRequestProperty("Authorization", authorizationString);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(json);
            writer.flush();
            writer.close();
            if (conn.getResponseCode() == 200) {
                z = true;
            } else {
                z = false;
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn == null) {
                return z;
            }
            conn.disconnect();
            return z;
        } catch (Exception e2) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
            return false;
        } catch (Throwable th) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
