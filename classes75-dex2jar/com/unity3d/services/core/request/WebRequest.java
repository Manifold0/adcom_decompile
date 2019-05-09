// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.request;

import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class WebRequest
{
    private String _body;
    private boolean _canceled;
    private int _connectTimeout;
    private long _contentLength;
    private Map<String, List<String>> _headers;
    private IWebRequestProgressListener _progressListener;
    private int _readTimeout;
    private String _requestType;
    private int _responseCode;
    private Map<String, List<String>> _responseHeaders;
    private URL _url;
    
    public WebRequest(final String s, final String s2, final Map<String, List<String>> map) throws MalformedURLException {
        this(s, s2, map, 30000, 30000);
    }
    
    public WebRequest(final String s, final String requestType, final Map<String, List<String>> headers, final int connectTimeout, final int readTimeout) throws MalformedURLException {
        this._requestType = RequestType.GET.name();
        this._responseCode = -1;
        this._contentLength = -1L;
        this._canceled = false;
        this._url = new URL(s);
        this._requestType = requestType;
        this._headers = headers;
        this._connectTimeout = connectTimeout;
        this._readTimeout = readTimeout;
    }
    
    private HttpURLConnection getHttpUrlConnectionWithHeaders() throws NetworkIOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/unity3d/services/core/request/WebRequest.getUrl:()Ljava/net/URL;
        //     4: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //     7: ldc             "https://"
        //     9: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    12: ifeq            223
        //    15: aload_0        
        //    16: invokevirtual   com/unity3d/services/core/request/WebRequest.getUrl:()Ljava/net/URL;
        //    19: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    22: checkcast       Ljavax/net/ssl/HttpsURLConnection;
        //    25: astore_1       
        //    26: aload_1        
        //    27: iconst_0       
        //    28: invokevirtual   java/net/HttpURLConnection.setInstanceFollowRedirects:(Z)V
        //    31: aload_1        
        //    32: aload_0        
        //    33: invokevirtual   com/unity3d/services/core/request/WebRequest.getConnectTimeout:()I
        //    36: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //    39: aload_1        
        //    40: aload_0        
        //    41: invokevirtual   com/unity3d/services/core/request/WebRequest.getReadTimeout:()I
        //    44: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //    47: aload_1        
        //    48: aload_0        
        //    49: invokevirtual   com/unity3d/services/core/request/WebRequest.getRequestType:()Ljava/lang/String;
        //    52: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //    55: aload_0        
        //    56: invokevirtual   com/unity3d/services/core/request/WebRequest.getHeaders:()Ljava/util/Map;
        //    59: ifnull          311
        //    62: aload_0        
        //    63: invokevirtual   com/unity3d/services/core/request/WebRequest.getHeaders:()Ljava/util/Map;
        //    66: invokeinterface java/util/Map.size:()I
        //    71: ifle            311
        //    74: aload_0        
        //    75: invokevirtual   com/unity3d/services/core/request/WebRequest.getHeaders:()Ljava/util/Map;
        //    78: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //    83: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    88: astore_2       
        //    89: aload_2        
        //    90: invokeinterface java/util/Iterator.hasNext:()Z
        //    95: ifeq            311
        //    98: aload_2        
        //    99: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   104: checkcast       Ljava/lang/String;
        //   107: astore_3       
        //   108: aload_0        
        //   109: invokevirtual   com/unity3d/services/core/request/WebRequest.getHeaders:()Ljava/util/Map;
        //   112: aload_3        
        //   113: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   118: checkcast       Ljava/util/List;
        //   121: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   126: astore          4
        //   128: aload           4
        //   130: invokeinterface java/util/Iterator.hasNext:()Z
        //   135: ifeq            89
        //   138: aload           4
        //   140: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   145: checkcast       Ljava/lang/String;
        //   148: astore          5
        //   150: new             Ljava/lang/StringBuilder;
        //   153: dup            
        //   154: invokespecial   java/lang/StringBuilder.<init>:()V
        //   157: ldc             "Setting header: "
        //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   162: aload_3        
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: ldc             "="
        //   168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   171: aload           5
        //   173: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   176: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   179: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //   182: aload_1        
        //   183: aload_3        
        //   184: aload           5
        //   186: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   189: goto            128
        //   192: astore_1       
        //   193: new             Lcom/unity3d/services/core/request/NetworkIOException;
        //   196: dup            
        //   197: new             Ljava/lang/StringBuilder;
        //   200: dup            
        //   201: invokespecial   java/lang/StringBuilder.<init>:()V
        //   204: ldc             "Open HTTPS connection: "
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: aload_1        
        //   210: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   219: invokespecial   com/unity3d/services/core/request/NetworkIOException.<init>:(Ljava/lang/String;)V
        //   222: athrow         
        //   223: aload_0        
        //   224: invokevirtual   com/unity3d/services/core/request/WebRequest.getUrl:()Ljava/net/URL;
        //   227: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   230: checkcast       Ljava/net/HttpURLConnection;
        //   233: astore_1       
        //   234: goto            26
        //   237: astore_1       
        //   238: new             Lcom/unity3d/services/core/request/NetworkIOException;
        //   241: dup            
        //   242: new             Ljava/lang/StringBuilder;
        //   245: dup            
        //   246: invokespecial   java/lang/StringBuilder.<init>:()V
        //   249: ldc             "Open HTTP connection: "
        //   251: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   254: aload_1        
        //   255: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   264: invokespecial   com/unity3d/services/core/request/NetworkIOException.<init>:(Ljava/lang/String;)V
        //   267: athrow         
        //   268: astore_1       
        //   269: new             Lcom/unity3d/services/core/request/NetworkIOException;
        //   272: dup            
        //   273: new             Ljava/lang/StringBuilder;
        //   276: dup            
        //   277: invokespecial   java/lang/StringBuilder.<init>:()V
        //   280: ldc             "Set Request Method: "
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: aload_0        
        //   286: invokevirtual   com/unity3d/services/core/request/WebRequest.getRequestType:()Ljava/lang/String;
        //   289: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   292: ldc             ", "
        //   294: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   297: aload_1        
        //   298: invokevirtual   java/net/ProtocolException.getMessage:()Ljava/lang/String;
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   307: invokespecial   com/unity3d/services/core/request/NetworkIOException.<init>:(Ljava/lang/String;)V
        //   310: athrow         
        //   311: aload_1        
        //   312: areturn        
        //    Exceptions:
        //  throws com.unity3d.services.core.request.NetworkIOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  15     26     192    223    Ljava/io/IOException;
        //  47     55     268    311    Ljava/net/ProtocolException;
        //  223    234    237    268    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0089:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public void cancel() {
        this._canceled = true;
    }
    
    public String getBody() {
        return this._body;
    }
    
    public int getConnectTimeout() {
        return this._connectTimeout;
    }
    
    public long getContentLength() {
        return this._contentLength;
    }
    
    public Map<String, List<String>> getHeaders() {
        return this._headers;
    }
    
    public String getQuery() {
        if (this._url != null) {
            return this._url.getQuery();
        }
        return null;
    }
    
    public int getReadTimeout() {
        return this._readTimeout;
    }
    
    public String getRequestType() {
        return this._requestType;
    }
    
    public int getResponseCode() {
        return this._responseCode;
    }
    
    public Map<String, List<String>> getResponseHeaders() {
        return this._responseHeaders;
    }
    
    public URL getUrl() {
        return this._url;
    }
    
    public boolean isCanceled() {
        return this._canceled;
    }
    
    public String makeRequest() throws NetworkIOException, IOException, IllegalStateException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.makeStreamRequest(byteArrayOutputStream);
        return byteArrayOutputStream.toString("UTF-8");
    }
    
    public long makeStreamRequest(final OutputStream p0) throws NetworkIOException, IOException, IllegalStateException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   com/unity3d/services/core/request/WebRequest.getHttpUrlConnectionWithHeaders:()Ljava/net/HttpURLConnection;
        //     4: astore          11
        //     6: aload           11
        //     8: iconst_1       
        //     9: invokevirtual   java/net/HttpURLConnection.setDoInput:(Z)V
        //    12: aload_0        
        //    13: invokevirtual   com/unity3d/services/core/request/WebRequest.getRequestType:()Ljava/lang/String;
        //    16: getstatic       com/unity3d/services/core/request/WebRequest$RequestType.POST:Lcom/unity3d/services/core/request/WebRequest$RequestType;
        //    19: invokevirtual   com/unity3d/services/core/request/WebRequest$RequestType.name:()Ljava/lang/String;
        //    22: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    25: ifeq            95
        //    28: aload           11
        //    30: iconst_1       
        //    31: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //    34: aconst_null    
        //    35: astore          8
        //    37: aconst_null    
        //    38: astore          10
        //    40: new             Ljava/io/PrintWriter;
        //    43: dup            
        //    44: new             Ljava/io/OutputStreamWriter;
        //    47: dup            
        //    48: aload           11
        //    50: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //    53: ldc             "UTF-8"
        //    55: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
        //    58: iconst_1       
        //    59: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/Writer;Z)V
        //    62: astore          9
        //    64: aload_0        
        //    65: invokevirtual   com/unity3d/services/core/request/WebRequest.getBody:()Ljava/lang/String;
        //    68: ifnonnull       281
        //    71: aload           9
        //    73: aload_0        
        //    74: invokevirtual   com/unity3d/services/core/request/WebRequest.getQuery:()Ljava/lang/String;
        //    77: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //    80: aload           9
        //    82: invokevirtual   java/io/PrintWriter.flush:()V
        //    85: aload           9
        //    87: ifnull          95
        //    90: aload           9
        //    92: invokevirtual   java/io/PrintWriter.close:()V
        //    95: aload_0        
        //    96: aload           11
        //    98: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   101: putfield        com/unity3d/services/core/request/WebRequest._responseCode:I
        //   104: aload_0        
        //   105: aload           11
        //   107: invokevirtual   java/net/HttpURLConnection.getContentLength:()I
        //   110: i2l            
        //   111: putfield        com/unity3d/services/core/request/WebRequest._contentLength:J
        //   114: aload           11
        //   116: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   119: ifnull          131
        //   122: aload_0        
        //   123: aload           11
        //   125: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   128: putfield        com/unity3d/services/core/request/WebRequest._responseHeaders:Ljava/util/Map;
        //   131: aload           11
        //   133: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   136: astore          8
        //   138: aload_0        
        //   139: getfield        com/unity3d/services/core/request/WebRequest._progressListener:Lcom/unity3d/services/core/request/IWebRequestProgressListener;
        //   142: ifnull          173
        //   145: aload_0        
        //   146: getfield        com/unity3d/services/core/request/WebRequest._progressListener:Lcom/unity3d/services/core/request/IWebRequestProgressListener;
        //   149: aload_0        
        //   150: invokevirtual   com/unity3d/services/core/request/WebRequest.getUrl:()Ljava/net/URL;
        //   153: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   156: aload_0        
        //   157: getfield        com/unity3d/services/core/request/WebRequest._contentLength:J
        //   160: aload_0        
        //   161: getfield        com/unity3d/services/core/request/WebRequest._responseCode:I
        //   164: aload_0        
        //   165: getfield        com/unity3d/services/core/request/WebRequest._responseHeaders:Ljava/util/Map;
        //   168: invokeinterface com/unity3d/services/core/request/IWebRequestProgressListener.onRequestStart:(Ljava/lang/String;JILjava/util/Map;)V
        //   173: new             Ljava/io/BufferedInputStream;
        //   176: dup            
        //   177: aload           8
        //   179: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   182: astore          8
        //   184: iconst_0       
        //   185: istore_2       
        //   186: lconst_0       
        //   187: lstore          4
        //   189: sipush          4096
        //   192: newarray        B
        //   194: astore          9
        //   196: aload_0        
        //   197: invokevirtual   com/unity3d/services/core/request/WebRequest.isCanceled:()Z
        //   200: ifne            495
        //   203: iload_2        
        //   204: iconst_m1      
        //   205: if_icmpeq       495
        //   208: aload           8
        //   210: aload           9
        //   212: invokevirtual   java/io/BufferedInputStream.read:([B)I
        //   215: istore_3       
        //   216: iload_3        
        //   217: istore_2       
        //   218: iload_3        
        //   219: ifle            196
        //   222: aload_1        
        //   223: aload           9
        //   225: iconst_0       
        //   226: iload_3        
        //   227: invokevirtual   java/io/OutputStream.write:([BII)V
        //   230: lload           4
        //   232: iload_3        
        //   233: i2l            
        //   234: ladd           
        //   235: lstore          6
        //   237: lload           6
        //   239: lstore          4
        //   241: iload_3        
        //   242: istore_2       
        //   243: aload_0        
        //   244: getfield        com/unity3d/services/core/request/WebRequest._progressListener:Lcom/unity3d/services/core/request/IWebRequestProgressListener;
        //   247: ifnull          196
        //   250: aload_0        
        //   251: getfield        com/unity3d/services/core/request/WebRequest._progressListener:Lcom/unity3d/services/core/request/IWebRequestProgressListener;
        //   254: aload_0        
        //   255: invokevirtual   com/unity3d/services/core/request/WebRequest.getUrl:()Ljava/net/URL;
        //   258: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   261: lload           6
        //   263: aload_0        
        //   264: getfield        com/unity3d/services/core/request/WebRequest._contentLength:J
        //   267: invokeinterface com/unity3d/services/core/request/IWebRequestProgressListener.onRequestProgress:(Ljava/lang/String;JJ)V
        //   272: lload           6
        //   274: lstore          4
        //   276: iload_3        
        //   277: istore_2       
        //   278: goto            196
        //   281: aload           9
        //   283: aload_0        
        //   284: invokevirtual   com/unity3d/services/core/request/WebRequest.getBody:()Ljava/lang/String;
        //   287: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
        //   290: goto            80
        //   293: astore          8
        //   295: aload           9
        //   297: astore_1       
        //   298: aload           8
        //   300: astore          9
        //   302: aload_1        
        //   303: astore          8
        //   305: ldc_w           "Error while writing POST params"
        //   308: aload           9
        //   310: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   313: aload_1        
        //   314: astore          8
        //   316: new             Lcom/unity3d/services/core/request/NetworkIOException;
        //   319: dup            
        //   320: new             Ljava/lang/StringBuilder;
        //   323: dup            
        //   324: invokespecial   java/lang/StringBuilder.<init>:()V
        //   327: ldc_w           "Error writing POST params: "
        //   330: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   333: aload           9
        //   335: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   344: invokespecial   com/unity3d/services/core/request/NetworkIOException.<init>:(Ljava/lang/String;)V
        //   347: athrow         
        //   348: astore_1       
        //   349: aload           8
        //   351: ifnull          359
        //   354: aload           8
        //   356: invokevirtual   java/io/PrintWriter.close:()V
        //   359: aload_1        
        //   360: athrow         
        //   361: astore_1       
        //   362: ldc_w           "Error closing writer"
        //   365: aload_1        
        //   366: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   369: aload_1        
        //   370: athrow         
        //   371: astore_1       
        //   372: ldc_w           "Error closing writer"
        //   375: aload_1        
        //   376: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   379: aload_1        
        //   380: athrow         
        //   381: astore_1       
        //   382: new             Lcom/unity3d/services/core/request/NetworkIOException;
        //   385: dup            
        //   386: new             Ljava/lang/StringBuilder;
        //   389: dup            
        //   390: invokespecial   java/lang/StringBuilder.<init>:()V
        //   393: ldc_w           "Response code: "
        //   396: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   399: aload_1        
        //   400: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   403: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   406: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   409: invokespecial   com/unity3d/services/core/request/NetworkIOException.<init>:(Ljava/lang/String;)V
        //   412: athrow         
        //   413: astore          10
        //   415: aload           11
        //   417: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //   420: astore          9
        //   422: aload           9
        //   424: astore          8
        //   426: aload           9
        //   428: ifnonnull       138
        //   431: new             Lcom/unity3d/services/core/request/NetworkIOException;
        //   434: dup            
        //   435: new             Ljava/lang/StringBuilder;
        //   438: dup            
        //   439: invokespecial   java/lang/StringBuilder.<init>:()V
        //   442: ldc_w           "Can't open error stream: "
        //   445: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   448: aload           10
        //   450: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   453: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   456: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   459: invokespecial   com/unity3d/services/core/request/NetworkIOException.<init>:(Ljava/lang/String;)V
        //   462: athrow         
        //   463: astore_1       
        //   464: new             Lcom/unity3d/services/core/request/NetworkIOException;
        //   467: dup            
        //   468: new             Ljava/lang/StringBuilder;
        //   471: dup            
        //   472: invokespecial   java/lang/StringBuilder.<init>:()V
        //   475: ldc_w           "Network exception: "
        //   478: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   481: aload_1        
        //   482: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   485: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   488: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   491: invokespecial   com/unity3d/services/core/request/NetworkIOException.<init>:(Ljava/lang/String;)V
        //   494: athrow         
        //   495: aload           11
        //   497: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   500: aload_1        
        //   501: invokevirtual   java/io/OutputStream.flush:()V
        //   504: lload           4
        //   506: lreturn        
        //   507: astore_1       
        //   508: goto            382
        //   511: astore_1       
        //   512: aload           9
        //   514: astore          8
        //   516: goto            349
        //   519: astore          9
        //   521: aload           10
        //   523: astore_1       
        //   524: goto            302
        //    Exceptions:
        //  throws com.unity3d.services.core.request.NetworkIOException
        //  throws java.io.IOException
        //  throws java.lang.IllegalStateException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  40     64     519    527    Ljava/io/IOException;
        //  40     64     348    349    Any
        //  64     80     293    302    Ljava/io/IOException;
        //  64     80     511    519    Any
        //  80     85     293    302    Ljava/io/IOException;
        //  80     85     511    519    Any
        //  90     95     361    371    Ljava/lang/Exception;
        //  95     104    507    511    Ljava/io/IOException;
        //  95     104    381    382    Ljava/lang/RuntimeException;
        //  131    138    413    463    Ljava/io/IOException;
        //  208    216    463    495    Ljava/io/IOException;
        //  281    290    293    302    Ljava/io/IOException;
        //  281    290    511    519    Any
        //  305    313    348    349    Any
        //  316    348    348    349    Any
        //  354    359    371    381    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 248, Size: 248
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
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
    
    public void setBody(final String body) {
        this._body = body;
    }
    
    public void setConnectTimeout(final int connectTimeout) {
        this._connectTimeout = connectTimeout;
    }
    
    public void setProgressListener(final IWebRequestProgressListener progressListener) {
        this._progressListener = progressListener;
    }
    
    public void setReadTimeout(final int readTimeout) {
        this._readTimeout = readTimeout;
    }
    
    public enum RequestType
    {
        GET, 
        HEAD, 
        POST;
    }
}
