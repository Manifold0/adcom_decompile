// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics;

import java.util.concurrent.RejectedExecutionException;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import android.os.AsyncTask;
import java.util.Locale;
import java.io.File;
import java.util.Arrays;
import android.annotation.TargetApi;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;
import android.os.Build$VERSION;
import java.net.URL;
import java.io.Writer;
import java.io.IOException;
import net.hockeyapp.android.utils.HockeyLog;
import java.net.HttpURLConnection;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

public class Sender
{
    static final String DEFAULT_ENDPOINT_URL = "https://gate.hockeyapp.net/v2/track";
    static final int DEFAULT_SENDER_CONNECT_TIMEOUT = 15000;
    static final int DEFAULT_SENDER_READ_TIMEOUT = 10000;
    static final int MAX_REQUEST_COUNT = 10;
    private static final String TAG = "HockeyApp-Metrics";
    private String mCustomServerURL;
    private AtomicInteger mRequestCount;
    protected WeakReference<Persistence> mWeakPersistence;
    
    protected Sender() {
        this.mRequestCount = new AtomicInteger(0);
    }
    
    private void logRequest(final HttpURLConnection httpURLConnection, final String s) {
        final Writer writer = null;
        final Writer writer2 = null;
        Writer writer4;
        Writer writer3 = writer4 = null;
        Label_0125: {
            if (httpURLConnection == null) {
                break Label_0125;
            }
            writer4 = writer3;
            if (s == null) {
                break Label_0125;
            }
            writer3 = writer;
            writer4 = writer2;
            try {
                HockeyLog.debug("HockeyApp-Metrics", "Sending payload:\n" + s);
                writer3 = writer;
                writer4 = writer2;
                HockeyLog.debug("HockeyApp-Metrics", "Using URL:" + httpURLConnection.getURL().toString());
                writer3 = writer;
                writer4 = writer2;
                final Writer writer5 = writer4 = (writer3 = this.getWriter(httpURLConnection));
                writer5.write(s);
                writer3 = writer5;
                writer4 = writer5;
                writer5.flush();
                writer4 = writer5;
                if (writer4 == null) {
                    return;
                }
                try {
                    writer4.close();
                }
                catch (IOException ex) {
                    HockeyLog.error("HockeyApp-Metrics", "Couldn't close writer with: " + ex.toString());
                }
            }
            catch (IOException ex2) {
                writer4 = writer3;
                HockeyLog.debug("HockeyApp-Metrics", "Couldn't log data with: " + ex2.toString());
                if (writer3 == null) {
                    return;
                }
                try {
                    writer3.close();
                }
                catch (IOException ex3) {
                    HockeyLog.error("HockeyApp-Metrics", "Couldn't close writer with: " + ex3.toString());
                }
            }
            finally {
                Label_0243: {
                    if (writer4 == null) {
                        break Label_0243;
                    }
                    try {
                        writer4.close();
                    }
                    catch (IOException ex4) {
                        HockeyLog.error("HockeyApp-Metrics", "Couldn't close writer with: " + ex4.toString());
                    }
                }
            }
        }
    }
    
    protected HttpURLConnection createConnection() {
        HttpURLConnection httpURLConnection2;
        final HttpURLConnection httpURLConnection = httpURLConnection2 = null;
        try {
            URL url;
            if (this.getCustomServerURL() == null) {
                httpURLConnection2 = httpURLConnection;
                url = new URL("https://gate.hockeyapp.net/v2/track");
            }
            else {
                httpURLConnection2 = httpURLConnection;
                if ((url = new URL(this.mCustomServerURL)) == null) {
                    httpURLConnection2 = httpURLConnection;
                    url = new URL("https://gate.hockeyapp.net/v2/track");
                }
            }
            httpURLConnection2 = httpURLConnection;
            final HttpURLConnection httpURLConnection3 = httpURLConnection2 = (HttpURLConnection)url.openConnection();
            httpURLConnection3.setReadTimeout(10000);
            httpURLConnection2 = httpURLConnection3;
            httpURLConnection3.setConnectTimeout(15000);
            httpURLConnection2 = httpURLConnection3;
            httpURLConnection3.setRequestMethod("POST");
            httpURLConnection2 = httpURLConnection3;
            httpURLConnection3.setRequestProperty("Content-Type", "application/x-json-stream");
            httpURLConnection2 = httpURLConnection3;
            httpURLConnection3.setDoOutput(true);
            httpURLConnection2 = httpURLConnection3;
            httpURLConnection3.setDoInput(true);
            httpURLConnection2 = httpURLConnection3;
            httpURLConnection3.setUseCaches(false);
            return httpURLConnection3;
        }
        catch (IOException ex) {
            HockeyLog.error("HockeyApp-Metrics", "Could not open connection for provided URL with exception: ", ex);
            return httpURLConnection2;
        }
    }
    
    protected String getCustomServerURL() {
        return this.mCustomServerURL;
    }
    
    protected Persistence getPersistence() {
        Persistence persistence = null;
        if (this.mWeakPersistence != null) {
            persistence = this.mWeakPersistence.get();
        }
        return persistence;
    }
    
    @TargetApi(19)
    protected Writer getWriter(final HttpURLConnection httpURLConnection) throws IOException {
        if (Build$VERSION.SDK_INT >= 19) {
            httpURLConnection.addRequestProperty("Content-Encoding", "gzip");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-json-stream");
            return new OutputStreamWriter(new GZIPOutputStream(httpURLConnection.getOutputStream(), true), "UTF-8");
        }
        return new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
    }
    
    protected boolean isExpected(final int n) {
        return 200 <= n && n <= 203;
    }
    
    protected boolean isRecoverableError(final int n) {
        return Arrays.asList(408, 429, 500, 503, 511).contains(n);
    }
    
    protected String loadData(final File file) {
        String s = null;
        if (this.getPersistence() != null) {
            s = s;
            if (file != null) {
                final String load = this.getPersistence().load(file);
                if ((s = load) != null) {
                    s = load;
                    if (load.isEmpty()) {
                        this.getPersistence().deleteFile(file);
                        s = load;
                    }
                }
            }
        }
        return s;
    }
    
    protected void onResponse(final HttpURLConnection httpURLConnection, final int n, final String s, final File file) {
        this.mRequestCount.getAndDecrement();
        HockeyLog.debug("HockeyApp-Metrics", "response code " + Integer.toString(n));
        if (this.isRecoverableError(n)) {
            HockeyLog.debug("HockeyApp-Metrics", "Recoverable error (probably a server error), persisting data:\n" + s);
            if (this.getPersistence() != null) {
                this.getPersistence().makeAvailable(file);
            }
            return;
        }
        if (this.getPersistence() != null) {
            this.getPersistence().deleteFile(file);
        }
        final StringBuilder sb = new StringBuilder();
        if (this.isExpected(n)) {
            this.triggerSending();
            return;
        }
        this.onUnexpected(httpURLConnection, n, sb);
    }
    
    protected void onUnexpected(final HttpURLConnection httpURLConnection, final int n, final StringBuilder sb) {
        final String format = String.format(Locale.ROOT, "Unexpected response code: %d", n);
        sb.append(format);
        sb.append("\n");
        HockeyLog.error("HockeyApp-Metrics", format);
        this.readResponse(httpURLConnection, sb);
    }
    
    protected void readResponse(final HttpURLConnection p0, final StringBuilder p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuffer.<init>:()V
        //     7: astore          6
        //     9: aconst_null    
        //    10: astore_3       
        //    11: aconst_null    
        //    12: astore_2       
        //    13: aload_1        
        //    14: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //    17: astore          5
        //    19: aload           5
        //    21: astore          4
        //    23: aload           5
        //    25: ifnonnull       40
        //    28: aload           5
        //    30: astore_2       
        //    31: aload           5
        //    33: astore_3       
        //    34: aload_1        
        //    35: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //    38: astore          4
        //    40: aload           4
        //    42: ifnull          182
        //    45: aload           4
        //    47: astore_2       
        //    48: aload           4
        //    50: astore_3       
        //    51: new             Ljava/io/BufferedReader;
        //    54: dup            
        //    55: new             Ljava/io/InputStreamReader;
        //    58: dup            
        //    59: aload           4
        //    61: ldc             "UTF-8"
        //    63: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    66: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    69: astore_1       
        //    70: aload           4
        //    72: astore_2       
        //    73: aload           4
        //    75: astore_3       
        //    76: aload_1        
        //    77: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    80: astore          5
        //    82: aload           5
        //    84: ifnull          125
        //    87: aload           4
        //    89: astore_2       
        //    90: aload           4
        //    92: astore_3       
        //    93: aload           6
        //    95: aload           5
        //    97: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   100: pop            
        //   101: goto            70
        //   104: astore_1       
        //   105: aload_2        
        //   106: astore_3       
        //   107: ldc             "HockeyApp-Metrics"
        //   109: aload_1        
        //   110: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   113: invokestatic    net/hockeyapp/android/utils/HockeyLog.error:(Ljava/lang/String;Ljava/lang/String;)V
        //   116: aload_2        
        //   117: ifnull          124
        //   120: aload_2        
        //   121: invokevirtual   java/io/InputStream.close:()V
        //   124: return         
        //   125: aload           4
        //   127: astore_2       
        //   128: aload           4
        //   130: astore_3       
        //   131: aload           6
        //   133: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   136: astore_1       
        //   137: aload           4
        //   139: astore_2       
        //   140: aload           4
        //   142: astore_3       
        //   143: aload_1        
        //   144: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   147: ifne            196
        //   150: aload           4
        //   152: astore_2       
        //   153: aload           4
        //   155: astore_3       
        //   156: aload_1        
        //   157: invokestatic    net/hockeyapp/android/utils/HockeyLog.verbose:(Ljava/lang/String;)V
        //   160: aload           4
        //   162: ifnull          124
        //   165: aload           4
        //   167: invokevirtual   java/io/InputStream.close:()V
        //   170: return         
        //   171: astore_1       
        //   172: ldc             "HockeyApp-Metrics"
        //   174: aload_1        
        //   175: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   178: invokestatic    net/hockeyapp/android/utils/HockeyLog.error:(Ljava/lang/String;Ljava/lang/String;)V
        //   181: return         
        //   182: aload           4
        //   184: astore_2       
        //   185: aload           4
        //   187: astore_3       
        //   188: aload_1        
        //   189: invokevirtual   java/net/HttpURLConnection.getResponseMessage:()Ljava/lang/String;
        //   192: astore_1       
        //   193: goto            137
        //   196: aload           4
        //   198: astore_2       
        //   199: aload           4
        //   201: astore_3       
        //   202: ldc             "HockeyApp-Metrics"
        //   204: ldc_w           "Couldn't log response, result is null or empty string"
        //   207: invokestatic    net/hockeyapp/android/utils/HockeyLog.verbose:(Ljava/lang/String;Ljava/lang/String;)V
        //   210: goto            160
        //   213: astore_1       
        //   214: aload_3        
        //   215: ifnull          222
        //   218: aload_3        
        //   219: invokevirtual   java/io/InputStream.close:()V
        //   222: aload_1        
        //   223: athrow         
        //   224: astore_1       
        //   225: ldc             "HockeyApp-Metrics"
        //   227: aload_1        
        //   228: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   231: invokestatic    net/hockeyapp/android/utils/HockeyLog.error:(Ljava/lang/String;Ljava/lang/String;)V
        //   234: return         
        //   235: astore_2       
        //   236: ldc             "HockeyApp-Metrics"
        //   238: aload_2        
        //   239: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   242: invokestatic    net/hockeyapp/android/utils/HockeyLog.error:(Ljava/lang/String;Ljava/lang/String;)V
        //   245: goto            222
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  13     19     104    124    Ljava/io/IOException;
        //  13     19     213    224    Any
        //  34     40     104    124    Ljava/io/IOException;
        //  34     40     213    224    Any
        //  51     70     104    124    Ljava/io/IOException;
        //  51     70     213    224    Any
        //  76     82     104    124    Ljava/io/IOException;
        //  76     82     213    224    Any
        //  93     101    104    124    Ljava/io/IOException;
        //  93     101    213    224    Any
        //  107    116    213    224    Any
        //  120    124    224    235    Ljava/io/IOException;
        //  131    137    104    124    Ljava/io/IOException;
        //  131    137    213    224    Any
        //  143    150    104    124    Ljava/io/IOException;
        //  143    150    213    224    Any
        //  156    160    104    124    Ljava/io/IOException;
        //  156    160    213    224    Any
        //  165    170    171    182    Ljava/io/IOException;
        //  188    193    104    124    Ljava/io/IOException;
        //  188    193    213    224    Any
        //  202    210    104    124    Ljava/io/IOException;
        //  202    210    213    224    Any
        //  218    222    235    248    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 133, Size: 133
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
    
    protected int requestCount() {
        return this.mRequestCount.get();
    }
    
    protected void send(final HttpURLConnection httpURLConnection, final File file, final String s) {
        this.logRequest(httpURLConnection, s);
        if (httpURLConnection == null || file == null || s == null) {
            return;
        }
        this.mRequestCount.getAndIncrement();
        try {
            httpURLConnection.connect();
            this.onResponse(httpURLConnection, httpURLConnection.getResponseCode(), s, file);
        }
        catch (IOException ex) {
            HockeyLog.debug("HockeyApp-Metrics", "Couldn't send data with IOException: " + ex.toString());
            this.mRequestCount.getAndDecrement();
            if (this.getPersistence() != null) {
                HockeyLog.debug("HockeyApp-Metrics", "Persisting because of IOException: We're probably offline.");
                this.getPersistence().makeAvailable(file);
            }
        }
    }
    
    protected void sendAvailableFiles() {
        if (this.getPersistence() != null) {
            final File nextAvailableFileInDirectory = this.getPersistence().nextAvailableFileInDirectory();
            final String loadData = this.loadData(nextAvailableFileInDirectory);
            final HttpURLConnection connection = this.createConnection();
            if (loadData != null && connection != null) {
                this.send(connection, nextAvailableFileInDirectory, loadData);
            }
        }
    }
    
    protected void setCustomServerURL(final String mCustomServerURL) {
        this.mCustomServerURL = mCustomServerURL;
    }
    
    protected void setPersistence(final Persistence persistence) {
        this.mWeakPersistence = new WeakReference<Persistence>(persistence);
    }
    
    protected void triggerSending() {
        if (this.requestCount() < 10) {
            try {
                AsyncTaskUtils.execute(new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(final Void... array) {
                        Sender.this.sendAvailableFiles();
                        return null;
                    }
                });
                return;
            }
            catch (RejectedExecutionException ex) {
                HockeyLog.error("Could not send events. Executor rejected async task.", ex);
                return;
            }
        }
        HockeyLog.debug("HockeyApp-Metrics", "We have already 10 pending requests, not sending anything.");
    }
    
    protected void triggerSendingForTesting(final HttpURLConnection httpURLConnection, final File file, final String s) {
        if (this.requestCount() < 10) {
            this.mRequestCount.getAndIncrement();
            AsyncTaskUtils.execute(new AsyncTask<Void, Void, Void>() {
                protected Void doInBackground(final Void... array) {
                    Sender.this.send(httpURLConnection, file, s);
                    return null;
                }
            });
        }
    }
}
