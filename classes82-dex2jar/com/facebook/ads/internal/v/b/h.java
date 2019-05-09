// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URL;
import android.util.Log;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class h implements n
{
    public final String a;
    private HttpURLConnection b;
    private InputStream c;
    private volatile int d;
    private volatile String e;
    
    public h(final h h) {
        this.d = Integer.MIN_VALUE;
        this.a = h.a;
        this.e = h.e;
        this.d = h.d;
    }
    
    public h(final String s) {
        final MimeTypeMap singleton = MimeTypeMap.getSingleton();
        final String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(s);
        String mimeTypeFromExtension;
        if (TextUtils.isEmpty((CharSequence)fileExtensionFromUrl)) {
            mimeTypeFromExtension = null;
        }
        else {
            mimeTypeFromExtension = singleton.getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        this(s, mimeTypeFromExtension);
    }
    
    public h(final String s, final String e) {
        this.d = Integer.MIN_VALUE;
        this.a = j.a(s);
        this.e = e;
    }
    
    private HttpURLConnection a(final int n, final int n2) {
        String s = this.a;
        int n3 = 0;
        int i;
        HttpURLConnection httpURLConnection;
        do {
            final StringBuilder append = new StringBuilder().append("Open connection ");
            String string;
            if (n > 0) {
                string = " with offset " + n;
            }
            else {
                string = "";
            }
            Log.d("ProxyCache", append.append(string).append(" to ").append(s).toString());
            httpURLConnection = (HttpURLConnection)new URL(s).openConnection();
            if (n > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + n + "-");
            }
            if (n2 > 0) {
                httpURLConnection.setConnectTimeout(n2);
                httpURLConnection.setReadTimeout(n2);
            }
            final int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 301 || responseCode == 302 || responseCode == 303) {
                i = 1;
            }
            else {
                i = 0;
            }
            int n4 = n3;
            if (i != 0) {
                s = httpURLConnection.getHeaderField("Location");
                n4 = n3 + 1;
                httpURLConnection.disconnect();
            }
            if (n4 > 5) {
                throw new l("Too many redirects: " + n4);
            }
            n3 = n4;
        } while (i != 0);
        return httpURLConnection;
    }
    
    private void d() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_1       
        //     2: aconst_null    
        //     3: astore          5
        //     5: aconst_null    
        //     6: astore          6
        //     8: aconst_null    
        //     9: astore          4
        //    11: ldc             "ProxyCache"
        //    13: new             Ljava/lang/StringBuilder;
        //    16: dup            
        //    17: invokespecial   java/lang/StringBuilder.<init>:()V
        //    20: ldc             "Read content info from "
        //    22: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    25: aload_0        
        //    26: getfield        com/facebook/ads/internal/v/b/h.a:Ljava/lang/String;
        //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    32: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    35: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    38: pop            
        //    39: aload_0        
        //    40: iconst_0       
        //    41: sipush          10000
        //    44: invokespecial   com/facebook/ads/internal/v/b/h.a:(II)Ljava/net/HttpURLConnection;
        //    47: astore_3       
        //    48: aload           5
        //    50: astore_1       
        //    51: aload_3        
        //    52: astore_2       
        //    53: aload           6
        //    55: astore          4
        //    57: aload_0        
        //    58: aload_3        
        //    59: invokevirtual   java/net/HttpURLConnection.getContentLength:()I
        //    62: putfield        com/facebook/ads/internal/v/b/h.d:I
        //    65: aload           5
        //    67: astore_1       
        //    68: aload_3        
        //    69: astore_2       
        //    70: aload           6
        //    72: astore          4
        //    74: aload_0        
        //    75: aload_3        
        //    76: invokevirtual   java/net/HttpURLConnection.getContentType:()Ljava/lang/String;
        //    79: putfield        com/facebook/ads/internal/v/b/h.e:Ljava/lang/String;
        //    82: aload           5
        //    84: astore_1       
        //    85: aload_3        
        //    86: astore_2       
        //    87: aload           6
        //    89: astore          4
        //    91: aload_3        
        //    92: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //    95: astore          5
        //    97: aload           5
        //    99: astore_1       
        //   100: aload_3        
        //   101: astore_2       
        //   102: aload           5
        //   104: astore          4
        //   106: ldc             "ProxyCache"
        //   108: new             Ljava/lang/StringBuilder;
        //   111: dup            
        //   112: invokespecial   java/lang/StringBuilder.<init>:()V
        //   115: ldc             "Content info for `"
        //   117: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   120: aload_0        
        //   121: getfield        com/facebook/ads/internal/v/b/h.a:Ljava/lang/String;
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: ldc             "`: mime: "
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: aload_0        
        //   133: getfield        com/facebook/ads/internal/v/b/h.e:Ljava/lang/String;
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: ldc             ", content-length: "
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: aload_0        
        //   145: getfield        com/facebook/ads/internal/v/b/h.d:I
        //   148: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   151: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   154: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   157: pop            
        //   158: aload           5
        //   160: invokestatic    com/facebook/ads/internal/v/b/m.a:(Ljava/io/Closeable;)V
        //   163: aload_3        
        //   164: ifnull          171
        //   167: aload_3        
        //   168: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   171: return         
        //   172: astore          5
        //   174: aconst_null    
        //   175: astore_3       
        //   176: aload           4
        //   178: astore_1       
        //   179: aload_3        
        //   180: astore_2       
        //   181: ldc             "ProxyCache"
        //   183: new             Ljava/lang/StringBuilder;
        //   186: dup            
        //   187: invokespecial   java/lang/StringBuilder.<init>:()V
        //   190: ldc             "Error fetching info from "
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: aload_0        
        //   196: getfield        com/facebook/ads/internal/v/b/h.a:Ljava/lang/String;
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   205: aload           5
        //   207: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   210: pop            
        //   211: aload           4
        //   213: invokestatic    com/facebook/ads/internal/v/b/m.a:(Ljava/io/Closeable;)V
        //   216: aload_3        
        //   217: ifnull          171
        //   220: aload_3        
        //   221: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   224: return         
        //   225: astore_3       
        //   226: aconst_null    
        //   227: astore_2       
        //   228: aload_1        
        //   229: invokestatic    com/facebook/ads/internal/v/b/m.a:(Ljava/io/Closeable;)V
        //   232: aload_2        
        //   233: ifnull          240
        //   236: aload_2        
        //   237: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   240: aload_3        
        //   241: athrow         
        //   242: astore_3       
        //   243: goto            228
        //   246: astore          5
        //   248: goto            176
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  39     48     172    176    Ljava/io/IOException;
        //  39     48     225    228    Any
        //  57     65     246    251    Ljava/io/IOException;
        //  57     65     242    246    Any
        //  74     82     246    251    Ljava/io/IOException;
        //  74     82     242    246    Any
        //  91     97     246    251    Ljava/io/IOException;
        //  91     97     242    246    Any
        //  106    158    246    251    Ljava/io/IOException;
        //  106    158    242    246    Any
        //  181    211    242    246    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0171:
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
    
    @Override
    public int a() {
        synchronized (this) {
            if (this.d == Integer.MIN_VALUE) {
                this.d();
            }
            return this.d;
        }
    }
    
    @Override
    public int a(final byte[] array) {
        if (this.c == null) {
            throw new l("Error reading data from " + this.a + ": connection is absent!");
        }
        try {
            return this.c.read(array, 0, array.length);
        }
        catch (InterruptedIOException ex) {
            throw new i("Reading source " + this.a + " is interrupted", ex);
        }
        catch (IOException ex2) {
            throw new l("Error reading data from " + this.a, ex2);
        }
    }
    
    @Override
    public void a(final int n) {
    Label_0075_Outer:
        while (true) {
            while (true) {
                while (true) {
                    int responseCode = 0;
                    int d = 0;
                    Label_0126: {
                        try {
                            this.b = this.a(n, -1);
                            this.e = this.b.getContentType();
                            this.c = new BufferedInputStream(this.b.getInputStream(), 8192);
                            final HttpURLConnection b = this.b;
                            responseCode = this.b.getResponseCode();
                            d = b.getContentLength();
                            if (responseCode != 200) {
                                break Label_0126;
                            }
                            this.d = d;
                            return;
                            d = this.d;
                            continue Label_0075_Outer;
                        }
                        catch (IOException ex) {
                            throw new l("Error opening connection for " + this.a + " with offset " + n, ex);
                        }
                    }
                    if (responseCode == 206) {
                        d += n;
                        continue Label_0075_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    @Override
    public void b() {
        if (this.b == null) {
            return;
        }
        try {
            this.b.disconnect();
        }
        catch (NullPointerException ex) {
            throw new l("Error disconnecting HttpUrlConnection", ex);
        }
    }
    
    public String c() {
        synchronized (this) {
            if (TextUtils.isEmpty((CharSequence)this.e)) {
                this.d();
            }
            return this.e;
        }
    }
    
    @Override
    public String toString() {
        return "HttpUrlSource{url='" + this.a + "}";
    }
}
