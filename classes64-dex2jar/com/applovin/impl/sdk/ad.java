// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.xml.sax.SAXException;
import com.applovin.sdk.AppLovinSdk;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import org.json.JSONException;
import java.util.Locale;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.JSONObject;
import com.applovin.sdk.AppLovinLogger;

class ad
{
    private static final Object a;
    private final AppLovinSdkImpl b;
    private final AppLovinLogger c;
    
    static {
        a = new JSONObject();
    }
    
    ad(final AppLovinSdkImpl b) {
        this.b = b;
        this.c = b.getLogger();
    }
    
    private int a(final Throwable t) {
        if (t instanceof UnknownHostException) {
            return -103;
        }
        if (t instanceof SocketTimeoutException) {
            return -102;
        }
        if (t instanceof IOException) {
            final String message = t.getMessage();
            if (message != null && message.toLowerCase(Locale.ENGLISH).contains("authentication challenge")) {
                return 401;
            }
            return -100;
        }
        else {
            if (t instanceof JSONException) {
                return -104;
            }
            return -1;
        }
    }
    
    private HttpURLConnection a(final String s, final String requestMethod, final int n) throws IOException {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
        httpURLConnection.setRequestMethod(requestMethod);
        int intValue;
        if (n < 0) {
            intValue = this.b.get(ea.w);
        }
        else {
            intValue = n;
        }
        httpURLConnection.setConnectTimeout(intValue);
        int intValue2 = n;
        if (n < 0) {
            intValue2 = this.b.get(ea.y);
        }
        httpURLConnection.setReadTimeout(intValue2);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }
    
    private static void a(final InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        }
        catch (Exception ex) {}
    }
    
    private <T> void a(String a, final int n, final String s, final String s2, final T t, final af<T> af) throws JSONException {
        this.c.d("ConnectionManager", n + " received from from \"" + s2 + "\": " + a);
        if (n >= 200 && n < 300) {
            Label_0115: {
                if (a == null || a.length() <= 2) {
                    break Label_0115;
                }
                int n2 = 1;
            Label_0105_Outer:
                while (true) {
                    Object o = t;
                    while (true) {
                        if (n == 204) {
                            break Label_0105;
                        }
                        o = t;
                        if (n2 == 0) {
                            break Label_0105;
                        }
                        try {
                            if (!(t instanceof String)) {
                                if (t instanceof gf) {
                                    a = (String)gg.a(a, this.b);
                                }
                                else if (t instanceof JSONObject) {
                                    a = (String)new JSONObject(a);
                                }
                                else {
                                    this.c.e("ConnectionManager", "Unable to handle '" + t.getClass().getName() + "'");
                                    a = (String)t;
                                }
                            }
                            o = a;
                            af.a((T)o, n);
                            return;
                            n2 = 0;
                            continue Label_0105_Outer;
                        }
                        catch (JSONException ex) {
                            this.c.e("ConnectionManager", "Invalid JSON returned from \"" + s2 + "\"", (Throwable)ex);
                            o = t;
                            continue;
                        }
                        catch (SAXException ex2) {
                            this.c.e("ConnectionManager", "Invalid XML returned from \"" + s2 + "\"", ex2);
                            o = t;
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
        }
        this.c.e("ConnectionManager", n + " error received from \"" + s2 + "\"");
        af.a(n);
    }
    
    private void a(final String s, final String s2, final int n, final long n2) {
        this.c.i("ConnectionManager", "Successful " + s + " returned " + n + " in " + (System.currentTimeMillis() - n2) / 1000.0f + " s over " + ag.a(this.b) + " to \"" + s2 + "\"");
    }
    
    private void a(final String s, final String s2, final int n, final long n2, final Throwable t) {
        this.c.e("ConnectionManager", "Failed " + s + " returned " + n + " in " + (System.currentTimeMillis() - n2) / 1000.0f + " s over " + ag.a(this.b) + " to \"" + s2 + "\"", t);
    }
    
    private static void a(final HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null) {
            return;
        }
        try {
            httpURLConnection.disconnect();
        }
        catch (Exception ex) {}
    }
    
    void a(final String s, final af<String> af) {
        this.a(s, "GET", -1, null, "", true, af);
    }
    
     <T> void a(final String p0, final String p1, final int p2, final JSONObject p3, final T p4, final boolean p5, final ae p6, final af<T> p7) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc_w           "No endpoint specified"
        //    11: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    14: athrow         
        //    15: aload_2        
        //    16: ifnonnull       30
        //    19: new             Ljava/lang/IllegalArgumentException;
        //    22: dup            
        //    23: ldc_w           "No method specified"
        //    26: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    29: athrow         
        //    30: aload           8
        //    32: ifnonnull       46
        //    35: new             Ljava/lang/IllegalArgumentException;
        //    38: dup            
        //    39: ldc_w           "No callback specified"
        //    42: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    45: athrow         
        //    46: aload_1        
        //    47: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //    50: ldc_w           "http"
        //    53: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    56: ifne            107
        //    59: aload_0        
        //    60: getfield        com/applovin/impl/sdk/ad.c:Lcom/applovin/sdk/AppLovinLogger;
        //    63: ldc             "ConnectionManager"
        //    65: new             Ljava/lang/StringBuilder;
        //    68: dup            
        //    69: invokespecial   java/lang/StringBuilder.<init>:()V
        //    72: ldc_w           "Requested postback submission to non HTTP endpoint "
        //    75: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    78: aload_1        
        //    79: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    82: ldc_w           "; skipping..."
        //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    88: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    91: invokeinterface com/applovin/sdk/AppLovinLogger.userError:(Ljava/lang/String;Ljava/lang/String;)V
        //    96: aload           8
        //    98: sipush          -900
        //   101: invokeinterface com/applovin/impl/sdk/af.a:(I)V
        //   106: return         
        //   107: aload_0        
        //   108: getfield        com/applovin/impl/sdk/ad.b:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   111: getstatic       com/applovin/impl/sdk/ea.bS:Lcom/applovin/impl/sdk/ec;
        //   114: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.get:(Lcom/applovin/impl/sdk/ec;)Ljava/lang/Object;
        //   117: checkcast       Ljava/lang/Boolean;
        //   120: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   123: ifeq            716
        //   126: aload_1        
        //   127: ldc_w           "https://"
        //   130: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   133: ifne            716
        //   136: aload_0        
        //   137: getfield        com/applovin/impl/sdk/ad.b:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   140: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getLogger:()Lcom/applovin/sdk/AppLovinLogger;
        //   143: ldc             "ConnectionManager"
        //   145: ldc_w           "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting..."
        //   148: invokeinterface com/applovin/sdk/AppLovinLogger.w:(Ljava/lang/String;Ljava/lang/String;)V
        //   153: aload_1        
        //   154: ldc_w           "http://"
        //   157: ldc_w           "https://"
        //   160: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   163: astore          13
        //   165: invokestatic    java/lang/System.currentTimeMillis:()J
        //   168: lstore          9
        //   170: aconst_null    
        //   171: astore          14
        //   173: aconst_null    
        //   174: astore          12
        //   176: aload_0        
        //   177: getfield        com/applovin/impl/sdk/ad.c:Lcom/applovin/sdk/AppLovinLogger;
        //   180: ldc             "ConnectionManager"
        //   182: new             Ljava/lang/StringBuilder;
        //   185: dup            
        //   186: invokespecial   java/lang/StringBuilder.<init>:()V
        //   189: ldc_w           "Sending "
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: aload_2        
        //   196: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   199: ldc_w           " request to \""
        //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: aload           13
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: ldc_w           "\"..."
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   219: invokeinterface com/applovin/sdk/AppLovinLogger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   224: aload_0        
        //   225: aload           13
        //   227: aload_2        
        //   228: iload_3        
        //   229: invokespecial   com/applovin/impl/sdk/ad.a:(Ljava/lang/String;Ljava/lang/String;I)Ljava/net/HttpURLConnection;
        //   232: astore          11
        //   234: aload           4
        //   236: ifnull          355
        //   239: aload           4
        //   241: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   244: astore_1       
        //   245: aload_0        
        //   246: getfield        com/applovin/impl/sdk/ad.c:Lcom/applovin/sdk/AppLovinLogger;
        //   249: ldc             "ConnectionManager"
        //   251: new             Ljava/lang/StringBuilder;
        //   254: dup            
        //   255: invokespecial   java/lang/StringBuilder.<init>:()V
        //   258: ldc_w           "Request to \""
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: aload           13
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: ldc_w           "\" is "
        //   272: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: aload_1        
        //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   282: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   287: aload           11
        //   289: ldc_w           "Content-Type"
        //   292: ldc_w           "application/json; charset=utf-8"
        //   295: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   298: aload           11
        //   300: iconst_1       
        //   301: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   304: aload           11
        //   306: aload_1        
        //   307: ldc_w           "UTF-8"
        //   310: invokestatic    java/nio/charset/Charset.forName:(Ljava/lang/String;)Ljava/nio/charset/Charset;
        //   313: invokevirtual   java/lang/String.getBytes:(Ljava/nio/charset/Charset;)[B
        //   316: arraylength    
        //   317: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   320: new             Ljava/io/PrintWriter;
        //   323: dup            
        //   324: new             Ljava/io/OutputStreamWriter;
        //   327: dup            
        //   328: aload           11
        //   330: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   333: ldc_w           "UTF8"
        //   336: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
        //   339: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/Writer;)V
        //   342: astore          4
        //   344: aload           4
        //   346: aload_1        
        //   347: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   350: aload           4
        //   352: invokevirtual   java/io/PrintWriter.close:()V
        //   355: aload           11
        //   357: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   360: istore_3       
        //   361: aload           11
        //   363: invokevirtual   java/net/HttpURLConnection.getContentType:()Ljava/lang/String;
        //   366: astore          15
        //   368: iload_3        
        //   369: ifle            597
        //   372: aload_0        
        //   373: aload_2        
        //   374: aload           13
        //   376: iload_3        
        //   377: lload           9
        //   379: invokespecial   com/applovin/impl/sdk/ad.a:(Ljava/lang/String;Ljava/lang/String;IJ)V
        //   382: aload           11
        //   384: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   387: astore_1       
        //   388: iload           6
        //   390: ifeq            488
        //   393: aload_1        
        //   394: astore          4
        //   396: aload_1        
        //   397: astore          12
        //   399: aload_1        
        //   400: aload_0        
        //   401: getfield        com/applovin/impl/sdk/ad.b:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   404: invokestatic    com/applovin/impl/sdk/ag.a:(Ljava/io/InputStream;Lcom/applovin/impl/sdk/AppLovinSdkImpl;)Ljava/lang/String;
        //   407: astore          14
        //   409: aload           7
        //   411: ifnull          453
        //   414: aload           14
        //   416: ifnull          436
        //   419: aload_1        
        //   420: astore          4
        //   422: aload_1        
        //   423: astore          12
        //   425: aload           7
        //   427: aload           14
        //   429: invokevirtual   java/lang/String.length:()I
        //   432: i2l            
        //   433: invokestatic    com/applovin/impl/sdk/ae.a:(Lcom/applovin/impl/sdk/ae;J)V
        //   436: aload_1        
        //   437: astore          4
        //   439: aload_1        
        //   440: astore          12
        //   442: aload           7
        //   444: invokestatic    java/lang/System.currentTimeMillis:()J
        //   447: lload           9
        //   449: lsub           
        //   450: invokestatic    com/applovin/impl/sdk/ae.b:(Lcom/applovin/impl/sdk/ae;J)V
        //   453: aload_1        
        //   454: astore          4
        //   456: aload_1        
        //   457: astore          12
        //   459: aload_0        
        //   460: aload           14
        //   462: aload           11
        //   464: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   467: aload           15
        //   469: aload           13
        //   471: aload           5
        //   473: aload           8
        //   475: invokespecial   com/applovin/impl/sdk/ad.a:(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Object;Lcom/applovin/impl/sdk/af;)V
        //   478: aload_1        
        //   479: invokestatic    com/applovin/impl/sdk/ad.a:(Ljava/io/InputStream;)V
        //   482: aload           11
        //   484: invokestatic    com/applovin/impl/sdk/ad.a:(Ljava/net/HttpURLConnection;)V
        //   487: return         
        //   488: aload           7
        //   490: ifnull          510
        //   493: aload_1        
        //   494: astore          4
        //   496: aload_1        
        //   497: astore          12
        //   499: aload           7
        //   501: invokestatic    java/lang/System.currentTimeMillis:()J
        //   504: lload           9
        //   506: lsub           
        //   507: invokestatic    com/applovin/impl/sdk/ae.b:(Lcom/applovin/impl/sdk/ae;J)V
        //   510: aload_1        
        //   511: astore          4
        //   513: aload_1        
        //   514: astore          12
        //   516: aload           8
        //   518: aload           5
        //   520: iload_3        
        //   521: invokeinterface com/applovin/impl/sdk/af.a:(Ljava/lang/Object;I)V
        //   526: goto            478
        //   529: astore          4
        //   531: iload           6
        //   533: ifeq            621
        //   536: aload_1        
        //   537: astore          4
        //   539: aload_1        
        //   540: astore          12
        //   542: aload           8
        //   544: sipush          -901
        //   547: invokeinterface com/applovin/impl/sdk/af.a:(I)V
        //   552: goto            478
        //   555: astore          5
        //   557: aload           11
        //   559: astore_1       
        //   560: aload_0        
        //   561: aload           5
        //   563: invokespecial   com/applovin/impl/sdk/ad.a:(Ljava/lang/Throwable;)I
        //   566: istore_3       
        //   567: aload_0        
        //   568: aload_2        
        //   569: aload           13
        //   571: iload_3        
        //   572: lload           9
        //   574: aload           5
        //   576: invokespecial   com/applovin/impl/sdk/ad.a:(Ljava/lang/String;Ljava/lang/String;IJLjava/lang/Throwable;)V
        //   579: aload           8
        //   581: iload_3        
        //   582: invokeinterface com/applovin/impl/sdk/af.a:(I)V
        //   587: aload           4
        //   589: invokestatic    com/applovin/impl/sdk/ad.a:(Ljava/io/InputStream;)V
        //   592: aload_1        
        //   593: invokestatic    com/applovin/impl/sdk/ad.a:(Ljava/net/HttpURLConnection;)V
        //   596: return         
        //   597: aload_0        
        //   598: aload_2        
        //   599: aload           13
        //   601: iload_3        
        //   602: lload           9
        //   604: aconst_null    
        //   605: invokespecial   com/applovin/impl/sdk/ad.a:(Ljava/lang/String;Ljava/lang/String;IJLjava/lang/Throwable;)V
        //   608: aload           8
        //   610: iload_3        
        //   611: invokeinterface com/applovin/impl/sdk/af.a:(I)V
        //   616: aconst_null    
        //   617: astore_1       
        //   618: goto            478
        //   621: aload_1        
        //   622: astore          4
        //   624: aload_1        
        //   625: astore          12
        //   627: aload           8
        //   629: aload           5
        //   631: sipush          -901
        //   634: invokeinterface com/applovin/impl/sdk/af.a:(Ljava/lang/Object;I)V
        //   639: goto            478
        //   642: astore_1       
        //   643: aload           12
        //   645: astore          4
        //   647: aload           4
        //   649: invokestatic    com/applovin/impl/sdk/ad.a:(Ljava/io/InputStream;)V
        //   652: aload           11
        //   654: invokestatic    com/applovin/impl/sdk/ad.a:(Ljava/net/HttpURLConnection;)V
        //   657: aload_1        
        //   658: athrow         
        //   659: astore_1       
        //   660: aconst_null    
        //   661: astore          11
        //   663: aload           14
        //   665: astore          4
        //   667: goto            647
        //   670: astore_1       
        //   671: aload           14
        //   673: astore          4
        //   675: goto            647
        //   678: astore_2       
        //   679: aload_1        
        //   680: astore          11
        //   682: aload_2        
        //   683: astore_1       
        //   684: goto            647
        //   687: astore          5
        //   689: aconst_null    
        //   690: astore_1       
        //   691: aload           12
        //   693: astore          4
        //   695: goto            560
        //   698: astore          5
        //   700: aload           11
        //   702: astore_1       
        //   703: aload           12
        //   705: astore          4
        //   707: goto            560
        //   710: astore_1       
        //   711: aconst_null    
        //   712: astore_1       
        //   713: goto            531
        //   716: aload_1        
        //   717: astore          13
        //   719: goto            165
        //    Signature:
        //  <T:Ljava/lang/Object;>(Ljava/lang/String;Ljava/lang/String;ILorg/json/JSONObject;TT;ZLcom/applovin/impl/sdk/ae;Lcom/applovin/impl/sdk/af<TT;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  176    234    687    698    Ljava/lang/Throwable;
        //  176    234    659    670    Any
        //  239    355    698    710    Ljava/lang/Throwable;
        //  239    355    670    678    Any
        //  355    368    710    716    Ljava/net/MalformedURLException;
        //  355    368    698    710    Ljava/lang/Throwable;
        //  355    368    670    678    Any
        //  372    388    710    716    Ljava/net/MalformedURLException;
        //  372    388    698    710    Ljava/lang/Throwable;
        //  372    388    670    678    Any
        //  399    409    529    531    Ljava/net/MalformedURLException;
        //  399    409    555    560    Ljava/lang/Throwable;
        //  399    409    642    647    Any
        //  425    436    529    531    Ljava/net/MalformedURLException;
        //  425    436    555    560    Ljava/lang/Throwable;
        //  425    436    642    647    Any
        //  442    453    529    531    Ljava/net/MalformedURLException;
        //  442    453    555    560    Ljava/lang/Throwable;
        //  442    453    642    647    Any
        //  459    478    529    531    Ljava/net/MalformedURLException;
        //  459    478    555    560    Ljava/lang/Throwable;
        //  459    478    642    647    Any
        //  499    510    529    531    Ljava/net/MalformedURLException;
        //  499    510    555    560    Ljava/lang/Throwable;
        //  499    510    642    647    Any
        //  516    526    529    531    Ljava/net/MalformedURLException;
        //  516    526    555    560    Ljava/lang/Throwable;
        //  516    526    642    647    Any
        //  542    552    555    560    Ljava/lang/Throwable;
        //  542    552    642    647    Any
        //  560    587    678    687    Any
        //  597    616    710    716    Ljava/net/MalformedURLException;
        //  597    616    698    710    Ljava/lang/Throwable;
        //  597    616    670    678    Any
        //  627    639    555    560    Ljava/lang/Throwable;
        //  627    639    642    647    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 333, Size: 333
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
    
     <T> void a(final String s, final String s2, final int n, final JSONObject jsonObject, final T t, final boolean b, final af<T> af) {
        this.a(s, s2, n, jsonObject, t, b, null, af);
    }
}
