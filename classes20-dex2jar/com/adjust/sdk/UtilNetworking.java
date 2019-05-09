// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import android.net.Uri$Builder;
import android.net.Uri;
import java.util.Map;

public class UtilNetworking
{
    private static String userAgent;
    
    private static String buildAuthorizationHeader(final Map<String, String> map, String s, final String s2, final String s3) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final Map<String, String> signature = getSignature(map, s3, s);
        final String sha256 = Util.sha256(signature.get("clear_signature"));
        s = signature.get("fields");
        final String formatString = Util.formatString("Signature %s,%s,%s,%s", Util.formatString("secret_id=\"%s\"", s2), Util.formatString("signature=\"%s\"", sha256), Util.formatString("algorithm=\"%s\"", "sha256"), Util.formatString("headers=\"%s\"", s));
        getLogger().verbose("authorizationHeader: %s", formatString);
        return formatString;
    }
    
    private static Uri buildUri(final String s, final Map<String, String> map) {
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("https");
        uri$Builder.authority("app.adjust.com");
        uri$Builder.appendPath(s);
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            uri$Builder.appendQueryParameter((String)entry.getKey(), (String)entry.getValue());
        }
        uri$Builder.appendQueryParameter("sent_at", Util.dateFormatter.format(System.currentTimeMillis()));
        return uri$Builder.build();
    }
    
    public static ResponseData createGETHttpsURLConnection(final ActivityPackage activityPackage) throws Exception {
        try {
            final HashMap<String, String> hashMap = new HashMap<String, String>(activityPackage.getParameters());
            final String appSecret = extractAppSecret(hashMap);
            final String secretId = extractSecretId(hashMap);
            final HttpsURLConnection httpsURLConnection = AdjustFactory.getHttpsURLConnection(new URL(buildUri(activityPackage.getPath(), hashMap).toString()));
            final String buildAuthorizationHeader = buildAuthorizationHeader(hashMap, appSecret, secretId, activityPackage.getActivityKind().toString());
            if (buildAuthorizationHeader != null) {
                httpsURLConnection.setRequestProperty("Authorization", buildAuthorizationHeader);
            }
            setDefaultHttpsUrlConnectionProperties(httpsURLConnection, activityPackage.getClientSdk());
            httpsURLConnection.setRequestMethod("GET");
            return readHttpResponse(httpsURLConnection, activityPackage);
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public static ResponseData createPOSTHttpsURLConnection(final String p0, final ActivityPackage p1, final int p2) throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
        //     3: aconst_null    
        //     4: astore          4
        //     6: aload           5
        //     8: astore_3       
        //     9: new             Ljava/net/URL;
        //    12: dup            
        //    13: aload_0        
        //    14: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    17: invokestatic    com/adjust/sdk/AdjustFactory.getHttpsURLConnection:(Ljava/net/URL;)Ljavax/net/ssl/HttpsURLConnection;
        //    20: astore          6
        //    22: aload           5
        //    24: astore_3       
        //    25: new             Ljava/util/HashMap;
        //    28: dup            
        //    29: aload_1        
        //    30: invokevirtual   com/adjust/sdk/ActivityPackage.getParameters:()Ljava/util/Map;
        //    33: invokespecial   java/util/HashMap.<init>:(Ljava/util/Map;)V
        //    36: astore          7
        //    38: aload           5
        //    40: astore_3       
        //    41: aload           7
        //    43: invokestatic    com/adjust/sdk/UtilNetworking.extractAppSecret:(Ljava/util/Map;)Ljava/lang/String;
        //    46: astore_0       
        //    47: aload           5
        //    49: astore_3       
        //    50: aload           7
        //    52: invokestatic    com/adjust/sdk/UtilNetworking.extractSecretId:(Ljava/util/Map;)Ljava/lang/String;
        //    55: astore          8
        //    57: aload           5
        //    59: astore_3       
        //    60: aload           6
        //    62: aload_1        
        //    63: invokevirtual   com/adjust/sdk/ActivityPackage.getClientSdk:()Ljava/lang/String;
        //    66: invokestatic    com/adjust/sdk/UtilNetworking.setDefaultHttpsUrlConnectionProperties:(Ljavax/net/ssl/HttpsURLConnection;Ljava/lang/String;)V
        //    69: aload           5
        //    71: astore_3       
        //    72: aload           7
        //    74: aload_0        
        //    75: aload           8
        //    77: aload_1        
        //    78: invokevirtual   com/adjust/sdk/ActivityPackage.getActivityKind:()Lcom/adjust/sdk/ActivityKind;
        //    81: invokevirtual   com/adjust/sdk/ActivityKind.toString:()Ljava/lang/String;
        //    84: invokestatic    com/adjust/sdk/UtilNetworking.buildAuthorizationHeader:(Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    87: astore_0       
        //    88: aload_0        
        //    89: ifnull          103
        //    92: aload           5
        //    94: astore_3       
        //    95: aload           6
        //    97: ldc             "Authorization"
        //    99: aload_0        
        //   100: invokevirtual   javax/net/ssl/HttpsURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   103: aload           5
        //   105: astore_3       
        //   106: aload           6
        //   108: ldc             "POST"
        //   110: invokevirtual   javax/net/ssl/HttpsURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //   113: aload           5
        //   115: astore_3       
        //   116: aload           6
        //   118: iconst_0       
        //   119: invokevirtual   javax/net/ssl/HttpsURLConnection.setUseCaches:(Z)V
        //   122: aload           5
        //   124: astore_3       
        //   125: aload           6
        //   127: iconst_1       
        //   128: invokevirtual   javax/net/ssl/HttpsURLConnection.setDoInput:(Z)V
        //   131: aload           5
        //   133: astore_3       
        //   134: aload           6
        //   136: iconst_1       
        //   137: invokevirtual   javax/net/ssl/HttpsURLConnection.setDoOutput:(Z)V
        //   140: aload           5
        //   142: astore_3       
        //   143: new             Ljava/io/DataOutputStream;
        //   146: dup            
        //   147: aload           6
        //   149: invokevirtual   javax/net/ssl/HttpsURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   152: invokespecial   java/io/DataOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   155: astore_0       
        //   156: aload_0        
        //   157: aload           7
        //   159: iload_2        
        //   160: invokestatic    com/adjust/sdk/UtilNetworking.getPostDataString:(Ljava/util/Map;I)Ljava/lang/String;
        //   163: invokevirtual   java/io/DataOutputStream.writeBytes:(Ljava/lang/String;)V
        //   166: aload           6
        //   168: aload_1        
        //   169: invokestatic    com/adjust/sdk/UtilNetworking.readHttpResponse:(Ljavax/net/ssl/HttpsURLConnection;Lcom/adjust/sdk/ActivityPackage;)Lcom/adjust/sdk/ResponseData;
        //   172: astore_1       
        //   173: aload_0        
        //   174: ifnull          185
        //   177: aload_0        
        //   178: invokevirtual   java/io/DataOutputStream.flush:()V
        //   181: aload_0        
        //   182: invokevirtual   java/io/DataOutputStream.close:()V
        //   185: aload_1        
        //   186: areturn        
        //   187: astore_0       
        //   188: aload           4
        //   190: astore_3       
        //   191: aload_0        
        //   192: athrow         
        //   193: astore_0       
        //   194: aload_3        
        //   195: ifnull          206
        //   198: aload_3        
        //   199: invokevirtual   java/io/DataOutputStream.flush:()V
        //   202: aload_3        
        //   203: invokevirtual   java/io/DataOutputStream.close:()V
        //   206: aload_0        
        //   207: athrow         
        //   208: astore_1       
        //   209: goto            206
        //   212: astore_1       
        //   213: aload_0        
        //   214: astore_3       
        //   215: aload_1        
        //   216: astore_0       
        //   217: goto            194
        //   220: astore_1       
        //   221: aload_0        
        //   222: astore_3       
        //   223: aload_1        
        //   224: astore_0       
        //   225: goto            191
        //   228: astore_0       
        //   229: aload_1        
        //   230: areturn        
        //    Exceptions:
        //  throws java.lang.Exception
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      22     187    191    Ljava/lang/Exception;
        //  9      22     193    194    Any
        //  25     38     187    191    Ljava/lang/Exception;
        //  25     38     193    194    Any
        //  41     47     187    191    Ljava/lang/Exception;
        //  41     47     193    194    Any
        //  50     57     187    191    Ljava/lang/Exception;
        //  50     57     193    194    Any
        //  60     69     187    191    Ljava/lang/Exception;
        //  60     69     193    194    Any
        //  72     88     187    191    Ljava/lang/Exception;
        //  72     88     193    194    Any
        //  95     103    187    191    Ljava/lang/Exception;
        //  95     103    193    194    Any
        //  106    113    187    191    Ljava/lang/Exception;
        //  106    113    193    194    Any
        //  116    122    187    191    Ljava/lang/Exception;
        //  116    122    193    194    Any
        //  125    131    187    191    Ljava/lang/Exception;
        //  125    131    193    194    Any
        //  134    140    187    191    Ljava/lang/Exception;
        //  134    140    193    194    Any
        //  143    156    187    191    Ljava/lang/Exception;
        //  143    156    193    194    Any
        //  156    173    220    228    Ljava/lang/Exception;
        //  156    173    212    220    Any
        //  177    185    228    231    Ljava/lang/Exception;
        //  191    193    193    194    Any
        //  198    206    208    212    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 130, Size: 130
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static String extractAppSecret(final Map<String, String> map) {
        return map.remove("app_secret");
    }
    
    private static String extractSecretId(final Map<String, String> map) {
        return map.remove("secret_id");
    }
    
    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }
    
    private static String getPostDataString(final Map<String, String> map, final int n) throws UnsupportedEncodingException {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            final String encode = URLEncoder.encode(entry.getKey(), "UTF-8");
            final String s = entry.getValue();
            String encode2;
            if (s != null) {
                encode2 = URLEncoder.encode(s, "UTF-8");
            }
            else {
                encode2 = "";
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(encode);
            sb.append("=");
            sb.append(encode2);
        }
        final String format = Util.dateFormatter.format(System.currentTimeMillis());
        sb.append("&");
        sb.append(URLEncoder.encode("sent_at", "UTF-8"));
        sb.append("=");
        sb.append(URLEncoder.encode(format, "UTF-8"));
        if (n > 0) {
            sb.append("&");
            sb.append(URLEncoder.encode("queue_size", "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode("" + n, "UTF-8"));
        }
        return sb.toString();
    }
    
    private static Map<String, String> getSignature(final Map<String, String> map, String s, final String s2) {
        final String s3 = map.get("created_at");
        final String validIdentifier = getValidIdentifier(map);
        final String s4 = map.get(validIdentifier);
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("app_secret", s2);
        hashMap.put("created_at", s3);
        hashMap.put("activity_kind", s);
        hashMap.put(validIdentifier, s4);
        s = "";
        String string = "";
        for (final Map.Entry<Object, Object> entry : hashMap.entrySet()) {
            if (entry.getValue() != null) {
                s = s + entry.getKey() + " ";
                string += entry.getValue();
            }
        }
        s = s.substring(0, s.length() - 1);
        final HashMap<String, String> hashMap2 = new HashMap<String, String>();
        hashMap2.put("clear_signature", string);
        hashMap2.put("fields", s);
        return hashMap2;
    }
    
    private static String getValidIdentifier(final Map<String, String> map) {
        if (map.get("gps_adid") != null) {
            return "gps_adid";
        }
        if (map.get("fire_adid") != null) {
            return "fire_adid";
        }
        if (map.get("android_id") != null) {
            return "android_id";
        }
        if (map.get("mac_sha1") != null) {
            return "mac_sha1";
        }
        if (map.get("mac_md5") != null) {
            return "mac_md5";
        }
        if (map.get("android_uuid") != null) {
            return "android_uuid";
        }
        return null;
    }
    
    private static ResponseData readHttpResponse(HttpsURLConnection jsonResponse, final ActivityPackage activityPackage) throws Exception {
        StringBuffer sb = null;
        ILogger logger = null;
        ResponseData buildResponseData = null;
        Integer value = null;
    Label_0127:
        while (true) {
            sb = new StringBuffer();
            logger = getLogger();
            buildResponseData = ResponseData.buildResponseData(activityPackage);
            while (true) {
                try {
                    jsonResponse.connect();
                    value = jsonResponse.getResponseCode();
                    if (value >= 400) {
                        final InputStream inputStream = jsonResponse.getErrorStream();
                        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        while (true) {
                            final String line = bufferedReader.readLine();
                            if (line == null) {
                                break Label_0127;
                            }
                            sb.append(line);
                        }
                    }
                }
                catch (Exception ex) {
                    logger.error("Failed to read response. (%s)", ex.getMessage());
                    throw ex;
                }
                finally {
                    if (jsonResponse != null) {
                        jsonResponse.disconnect();
                    }
                }
                final InputStream inputStream = jsonResponse.getInputStream();
                continue;
            }
        }
        if (jsonResponse != null) {
            jsonResponse.disconnect();
        }
        final String string = sb.toString();
        logger.verbose("Response: %s", string);
        if (string != null && string.length() != 0) {
            jsonResponse = null;
            while (true) {
                try {
                    jsonResponse = (HttpsURLConnection)new JSONObject(string);
                    if (jsonResponse == null) {
                        return buildResponseData;
                    }
                    buildResponseData.jsonResponse = (JSONObject)jsonResponse;
                    final Object optString = ((JSONObject)jsonResponse).optString("message", (String)null);
                    buildResponseData.message = (String)optString;
                    buildResponseData.timestamp = ((JSONObject)jsonResponse).optString("timestamp", (String)null);
                    buildResponseData.adid = ((JSONObject)jsonResponse).optString("adid", (String)null);
                    if ((jsonResponse = (HttpsURLConnection)optString) == null) {
                        jsonResponse = (HttpsURLConnection)"No message found";
                    }
                    if (value != null && value == 200) {
                        logger.info("%s", jsonResponse);
                        buildResponseData.success = true;
                        return buildResponseData;
                    }
                }
                catch (JSONException ex2) {
                    final String formatString = Util.formatString("Failed to parse json response. (%s)", ex2.getMessage());
                    logger.error(formatString, new Object[0]);
                    buildResponseData.message = formatString;
                    continue;
                }
                break;
            }
            logger.error("%s", jsonResponse);
            return buildResponseData;
        }
        return buildResponseData;
    }
    
    private static void setDefaultHttpsUrlConnectionProperties(final HttpsURLConnection httpsURLConnection, final String s) {
        httpsURLConnection.setRequestProperty("Client-SDK", s);
        httpsURLConnection.setConnectTimeout(60000);
        httpsURLConnection.setReadTimeout(60000);
        if (UtilNetworking.userAgent != null) {
            httpsURLConnection.setRequestProperty("User-Agent", UtilNetworking.userAgent);
        }
    }
    
    public static void setUserAgent(final String userAgent) {
        UtilNetworking.userAgent = userAgent;
    }
}
