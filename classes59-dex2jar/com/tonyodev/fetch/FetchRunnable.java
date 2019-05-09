// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch;

import java.io.IOException;
import java.util.Iterator;
import java.net.URL;
import android.content.IntentFilter;
import android.content.Intent;
import java.util.ArrayList;
import android.support.annotation.NonNull;
import java.io.RandomAccessFile;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import com.tonyodev.fetch.request.Header;
import java.util.List;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

final class FetchRunnable implements Runnable
{
    private static final String ACTION_DONE = "com.tonyodev.fetch.action_done";
    private static final String EXTRA_ID = "com.tonyodev.fetch.extra_id";
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    private final DatabaseHelper databaseHelper;
    private long downloadedBytes;
    private final String filePath;
    private long fileSize;
    private final List<Header> headers;
    private HttpURLConnection httpURLConnection;
    private final long id;
    private BufferedInputStream input;
    private volatile boolean interrupted;
    private final boolean loggingEnabled;
    private final long onUpdateInterval;
    private RandomAccessFile output;
    private int progress;
    private final String url;
    
    FetchRunnable(@NonNull final Context context, final long id, @NonNull final String url, @NonNull final String filePath, @NonNull final List<Header> headers, final long fileSize, final boolean b, final long onUpdateInterval) {
        this.interrupted = false;
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        if (url == null) {
            throw new NullPointerException("Url cannot be null");
        }
        if (filePath == null) {
            throw new NullPointerException("FilePath cannot be null");
        }
        if (headers == null) {
            this.headers = new ArrayList<Header>();
        }
        else {
            this.headers = headers;
        }
        this.id = id;
        this.url = url;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.context = context.getApplicationContext();
        this.broadcastManager = LocalBroadcastManager.getInstance(this.context);
        this.databaseHelper = DatabaseHelper.getInstance(this.context);
        this.loggingEnabled = b;
        this.onUpdateInterval = onUpdateInterval;
        this.databaseHelper.setLoggingEnabled(b);
    }
    
    private void broadcastDone() {
        final Intent intent = new Intent("com.tonyodev.fetch.action_done");
        intent.putExtra("com.tonyodev.fetch.extra_id", this.id);
        this.broadcastManager.sendBroadcast(intent);
    }
    
    private boolean canRetry(final int n) {
        if (Utils.isNetworkAvailable(this.context)) {
            switch (n) {
                case -118:
                case -104:
                case -103: {
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }
    
    @NonNull
    static IntentFilter getDoneFilter() {
        return new IntentFilter("com.tonyodev.fetch.action_done");
    }
    
    static long getIdFromIntent(final Intent intent) {
        if (intent == null) {
            return -1L;
        }
        return intent.getLongExtra("com.tonyodev.fetch.extra_id", -1L);
    }
    
    private boolean isInterrupted() {
        return this.interrupted;
    }
    
    private boolean isResponseOk(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 200:
            case 202:
            case 206: {
                return true;
            }
        }
    }
    
    private void release() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/tonyodev/fetch/FetchRunnable.input:Ljava/io/BufferedInputStream;
        //     4: ifnull          14
        //     7: aload_0        
        //     8: getfield        com/tonyodev/fetch/FetchRunnable.input:Ljava/io/BufferedInputStream;
        //    11: invokevirtual   java/io/BufferedInputStream.close:()V
        //    14: aload_0        
        //    15: getfield        com/tonyodev/fetch/FetchRunnable.output:Ljava/io/RandomAccessFile;
        //    18: ifnull          28
        //    21: aload_0        
        //    22: getfield        com/tonyodev/fetch/FetchRunnable.output:Ljava/io/RandomAccessFile;
        //    25: invokevirtual   java/io/RandomAccessFile.close:()V
        //    28: aload_0        
        //    29: getfield        com/tonyodev/fetch/FetchRunnable.httpURLConnection:Ljava/net/HttpURLConnection;
        //    32: ifnull          42
        //    35: aload_0        
        //    36: getfield        com/tonyodev/fetch/FetchRunnable.httpURLConnection:Ljava/net/HttpURLConnection;
        //    39: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //    42: return         
        //    43: astore_1       
        //    44: aload_0        
        //    45: getfield        com/tonyodev/fetch/FetchRunnable.loggingEnabled:Z
        //    48: ifeq            14
        //    51: aload_1        
        //    52: invokevirtual   java/io/IOException.printStackTrace:()V
        //    55: goto            14
        //    58: astore_1       
        //    59: aload_0        
        //    60: getfield        com/tonyodev/fetch/FetchRunnable.loggingEnabled:Z
        //    63: ifeq            28
        //    66: aload_1        
        //    67: invokevirtual   java/io/IOException.printStackTrace:()V
        //    70: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      14     43     58     Ljava/io/IOException;
        //  14     28     58     73     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0014:
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
    
    private void setContentLength() {
        try {
            this.fileSize = this.downloadedBytes + Long.valueOf(this.httpURLConnection.getHeaderField("Content-Length"));
        }
        catch (Exception ex) {
            this.fileSize = -1L;
        }
    }
    
    private void setHttpConnectionPrefs() throws IOException {
        (this.httpURLConnection = (HttpURLConnection)new URL(this.url).openConnection()).setRequestMethod("GET");
        this.httpURLConnection.setReadTimeout(20000);
        this.httpURLConnection.setConnectTimeout(15000);
        this.httpURLConnection.setUseCaches(false);
        this.httpURLConnection.setDefaultUseCaches(false);
        this.httpURLConnection.setInstanceFollowRedirects(true);
        this.httpURLConnection.setDoInput(true);
        for (final Header header : this.headers) {
            this.httpURLConnection.addRequestProperty(header.getHeader(), header.getValue());
        }
    }
    
    private void writeToFileAndPost() throws IOException {
        final byte[] array = new byte[1024];
        long n = System.nanoTime();
        while (true) {
            final int read = this.input.read(array, 0, 1024);
            if (read == -1 || this.isInterrupted()) {
                break;
            }
            this.output.write(array, 0, read);
            this.downloadedBytes += read;
            if (!Utils.hasIntervalElapsed(n, System.nanoTime(), this.onUpdateInterval) || this.isInterrupted()) {
                continue;
            }
            this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
            Utils.sendEventUpdate(this.broadcastManager, this.id, 901, this.progress, this.downloadedBytes, this.fileSize, -1);
            this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
            n = System.nanoTime();
        }
    }
    
    long getId() {
        synchronized (this) {
            return this.id;
        }
    }
    
    void interrupt() {
        synchronized (this) {
            this.interrupted = true;
        }
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   com/tonyodev/fetch/FetchRunnable.setHttpConnectionPrefs:()V
        //     4: aload_0        
        //     5: getfield        com/tonyodev/fetch/FetchRunnable.filePath:Ljava/lang/String;
        //     8: invokestatic    com/tonyodev/fetch/Utils.createFileOrThrow:(Ljava/lang/String;)V
        //    11: aload_0        
        //    12: aload_0        
        //    13: getfield        com/tonyodev/fetch/FetchRunnable.filePath:Ljava/lang/String;
        //    16: invokestatic    com/tonyodev/fetch/Utils.getFileSize:(Ljava/lang/String;)J
        //    19: putfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //    22: aload_0        
        //    23: aload_0        
        //    24: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //    27: aload_0        
        //    28: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //    31: invokestatic    com/tonyodev/fetch/Utils.getProgress:(JJ)I
        //    34: putfield        com/tonyodev/fetch/FetchRunnable.progress:I
        //    37: aload_0        
        //    38: getfield        com/tonyodev/fetch/FetchRunnable.databaseHelper:Lcom/tonyodev/fetch/DatabaseHelper;
        //    41: aload_0        
        //    42: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //    45: aload_0        
        //    46: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //    49: aload_0        
        //    50: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //    53: invokevirtual   com/tonyodev/fetch/DatabaseHelper.updateFileBytes:(JJJ)Z
        //    56: pop            
        //    57: aload_0        
        //    58: getfield        com/tonyodev/fetch/FetchRunnable.httpURLConnection:Ljava/net/HttpURLConnection;
        //    61: ldc_w           "Range"
        //    64: new             Ljava/lang/StringBuilder;
        //    67: dup            
        //    68: invokespecial   java/lang/StringBuilder.<init>:()V
        //    71: ldc_w           "bytes="
        //    74: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    77: aload_0        
        //    78: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //    81: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    84: ldc_w           "-"
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    96: aload_0        
        //    97: invokespecial   com/tonyodev/fetch/FetchRunnable.isInterrupted:()Z
        //   100: ifeq            198
        //   103: new             Lcom/tonyodev/fetch/exception/DownloadInterruptedException;
        //   106: dup            
        //   107: ldc_w           "DIE"
        //   110: bipush          -118
        //   112: invokespecial   com/tonyodev/fetch/exception/DownloadInterruptedException.<init>:(Ljava/lang/String;I)V
        //   115: athrow         
        //   116: astore_2       
        //   117: aload_0        
        //   118: getfield        com/tonyodev/fetch/FetchRunnable.loggingEnabled:Z
        //   121: ifeq            128
        //   124: aload_2        
        //   125: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   128: aload_2        
        //   129: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   132: invokestatic    com/tonyodev/fetch/ErrorUtils.getCode:(Ljava/lang/String;)I
        //   135: istore_1       
        //   136: aload_0        
        //   137: iload_1        
        //   138: invokespecial   com/tonyodev/fetch/FetchRunnable.canRetry:(I)Z
        //   141: ifeq            583
        //   144: aload_0        
        //   145: getfield        com/tonyodev/fetch/FetchRunnable.databaseHelper:Lcom/tonyodev/fetch/DatabaseHelper;
        //   148: aload_0        
        //   149: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //   152: sipush          900
        //   155: iconst_m1      
        //   156: invokevirtual   com/tonyodev/fetch/DatabaseHelper.updateStatus:(JII)Z
        //   159: ifeq            189
        //   162: aload_0        
        //   163: getfield        com/tonyodev/fetch/FetchRunnable.broadcastManager:Landroid/support/v4/content/LocalBroadcastManager;
        //   166: aload_0        
        //   167: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //   170: sipush          900
        //   173: aload_0        
        //   174: getfield        com/tonyodev/fetch/FetchRunnable.progress:I
        //   177: aload_0        
        //   178: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   181: aload_0        
        //   182: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   185: iconst_m1      
        //   186: invokestatic    com/tonyodev/fetch/Utils.sendEventUpdate:(Landroid/support/v4/content/LocalBroadcastManager;JIIJJI)V
        //   189: aload_0        
        //   190: invokespecial   com/tonyodev/fetch/FetchRunnable.release:()V
        //   193: aload_0        
        //   194: invokespecial   com/tonyodev/fetch/FetchRunnable.broadcastDone:()V
        //   197: return         
        //   198: aload_0        
        //   199: getfield        com/tonyodev/fetch/FetchRunnable.httpURLConnection:Ljava/net/HttpURLConnection;
        //   202: invokevirtual   java/net/HttpURLConnection.connect:()V
        //   205: aload_0        
        //   206: getfield        com/tonyodev/fetch/FetchRunnable.httpURLConnection:Ljava/net/HttpURLConnection;
        //   209: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   212: istore_1       
        //   213: aload_0        
        //   214: iload_1        
        //   215: invokespecial   com/tonyodev/fetch/FetchRunnable.isResponseOk:(I)Z
        //   218: ifeq            555
        //   221: aload_0        
        //   222: invokespecial   com/tonyodev/fetch/FetchRunnable.isInterrupted:()Z
        //   225: ifeq            252
        //   228: new             Lcom/tonyodev/fetch/exception/DownloadInterruptedException;
        //   231: dup            
        //   232: ldc_w           "DIE"
        //   235: bipush          -118
        //   237: invokespecial   com/tonyodev/fetch/exception/DownloadInterruptedException.<init>:(Ljava/lang/String;I)V
        //   240: athrow         
        //   241: astore_2       
        //   242: aload_0        
        //   243: invokespecial   com/tonyodev/fetch/FetchRunnable.release:()V
        //   246: aload_0        
        //   247: invokespecial   com/tonyodev/fetch/FetchRunnable.broadcastDone:()V
        //   250: aload_2        
        //   251: athrow         
        //   252: aload_0        
        //   253: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   256: lconst_1       
        //   257: lcmp           
        //   258: ifge            300
        //   261: aload_0        
        //   262: invokespecial   com/tonyodev/fetch/FetchRunnable.setContentLength:()V
        //   265: aload_0        
        //   266: getfield        com/tonyodev/fetch/FetchRunnable.databaseHelper:Lcom/tonyodev/fetch/DatabaseHelper;
        //   269: aload_0        
        //   270: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //   273: aload_0        
        //   274: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   277: aload_0        
        //   278: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   281: invokevirtual   com/tonyodev/fetch/DatabaseHelper.updateFileBytes:(JJJ)Z
        //   284: pop            
        //   285: aload_0        
        //   286: aload_0        
        //   287: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   290: aload_0        
        //   291: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   294: invokestatic    com/tonyodev/fetch/Utils.getProgress:(JJ)I
        //   297: putfield        com/tonyodev/fetch/FetchRunnable.progress:I
        //   300: aload_0        
        //   301: new             Ljava/io/RandomAccessFile;
        //   304: dup            
        //   305: aload_0        
        //   306: getfield        com/tonyodev/fetch/FetchRunnable.filePath:Ljava/lang/String;
        //   309: ldc_w           "rw"
        //   312: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   315: putfield        com/tonyodev/fetch/FetchRunnable.output:Ljava/io/RandomAccessFile;
        //   318: iload_1        
        //   319: sipush          206
        //   322: if_icmpne       398
        //   325: aload_0        
        //   326: getfield        com/tonyodev/fetch/FetchRunnable.output:Ljava/io/RandomAccessFile;
        //   329: aload_0        
        //   330: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   333: invokevirtual   java/io/RandomAccessFile.seek:(J)V
        //   336: aload_0        
        //   337: new             Ljava/io/BufferedInputStream;
        //   340: dup            
        //   341: aload_0        
        //   342: getfield        com/tonyodev/fetch/FetchRunnable.httpURLConnection:Ljava/net/HttpURLConnection;
        //   345: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   348: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   351: putfield        com/tonyodev/fetch/FetchRunnable.input:Ljava/io/BufferedInputStream;
        //   354: aload_0        
        //   355: invokespecial   com/tonyodev/fetch/FetchRunnable.writeToFileAndPost:()V
        //   358: aload_0        
        //   359: getfield        com/tonyodev/fetch/FetchRunnable.databaseHelper:Lcom/tonyodev/fetch/DatabaseHelper;
        //   362: aload_0        
        //   363: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //   366: aload_0        
        //   367: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   370: aload_0        
        //   371: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   374: invokevirtual   com/tonyodev/fetch/DatabaseHelper.updateFileBytes:(JJJ)Z
        //   377: pop            
        //   378: aload_0        
        //   379: invokespecial   com/tonyodev/fetch/FetchRunnable.isInterrupted:()Z
        //   382: ifeq            409
        //   385: new             Lcom/tonyodev/fetch/exception/DownloadInterruptedException;
        //   388: dup            
        //   389: ldc_w           "DIE"
        //   392: bipush          -118
        //   394: invokespecial   com/tonyodev/fetch/exception/DownloadInterruptedException.<init>:(Ljava/lang/String;I)V
        //   397: athrow         
        //   398: aload_0        
        //   399: getfield        com/tonyodev/fetch/FetchRunnable.output:Ljava/io/RandomAccessFile;
        //   402: lconst_0       
        //   403: invokevirtual   java/io/RandomAccessFile.seek:(J)V
        //   406: goto            336
        //   409: aload_0        
        //   410: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   413: aload_0        
        //   414: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   417: lcmp           
        //   418: iflt            528
        //   421: aload_0        
        //   422: invokespecial   com/tonyodev/fetch/FetchRunnable.isInterrupted:()Z
        //   425: ifne            528
        //   428: aload_0        
        //   429: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   432: lconst_1       
        //   433: lcmp           
        //   434: ifge            537
        //   437: aload_0        
        //   438: aload_0        
        //   439: getfield        com/tonyodev/fetch/FetchRunnable.filePath:Ljava/lang/String;
        //   442: invokestatic    com/tonyodev/fetch/Utils.getFileSize:(Ljava/lang/String;)J
        //   445: putfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   448: aload_0        
        //   449: getfield        com/tonyodev/fetch/FetchRunnable.databaseHelper:Lcom/tonyodev/fetch/DatabaseHelper;
        //   452: aload_0        
        //   453: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //   456: aload_0        
        //   457: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   460: aload_0        
        //   461: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   464: invokevirtual   com/tonyodev/fetch/DatabaseHelper.updateFileBytes:(JJJ)Z
        //   467: pop            
        //   468: aload_0        
        //   469: aload_0        
        //   470: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   473: aload_0        
        //   474: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   477: invokestatic    com/tonyodev/fetch/Utils.getProgress:(JJ)I
        //   480: putfield        com/tonyodev/fetch/FetchRunnable.progress:I
        //   483: aload_0        
        //   484: getfield        com/tonyodev/fetch/FetchRunnable.databaseHelper:Lcom/tonyodev/fetch/DatabaseHelper;
        //   487: aload_0        
        //   488: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //   491: sipush          903
        //   494: iconst_m1      
        //   495: invokevirtual   com/tonyodev/fetch/DatabaseHelper.updateStatus:(JII)Z
        //   498: ifeq            528
        //   501: aload_0        
        //   502: getfield        com/tonyodev/fetch/FetchRunnable.broadcastManager:Landroid/support/v4/content/LocalBroadcastManager;
        //   505: aload_0        
        //   506: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //   509: sipush          903
        //   512: aload_0        
        //   513: getfield        com/tonyodev/fetch/FetchRunnable.progress:I
        //   516: aload_0        
        //   517: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   520: aload_0        
        //   521: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   524: iconst_m1      
        //   525: invokestatic    com/tonyodev/fetch/Utils.sendEventUpdate:(Landroid/support/v4/content/LocalBroadcastManager;JIIJJI)V
        //   528: aload_0        
        //   529: invokespecial   com/tonyodev/fetch/FetchRunnable.release:()V
        //   532: aload_0        
        //   533: invokespecial   com/tonyodev/fetch/FetchRunnable.broadcastDone:()V
        //   536: return         
        //   537: aload_0        
        //   538: aload_0        
        //   539: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   542: aload_0        
        //   543: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   546: invokestatic    com/tonyodev/fetch/Utils.getProgress:(JJ)I
        //   549: putfield        com/tonyodev/fetch/FetchRunnable.progress:I
        //   552: goto            483
        //   555: new             Ljava/lang/IllegalStateException;
        //   558: dup            
        //   559: new             Ljava/lang/StringBuilder;
        //   562: dup            
        //   563: invokespecial   java/lang/StringBuilder.<init>:()V
        //   566: ldc_w           "SSRV:"
        //   569: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   572: iload_1        
        //   573: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   576: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   579: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   582: athrow         
        //   583: aload_0        
        //   584: getfield        com/tonyodev/fetch/FetchRunnable.databaseHelper:Lcom/tonyodev/fetch/DatabaseHelper;
        //   587: aload_0        
        //   588: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //   591: sipush          904
        //   594: iload_1        
        //   595: invokevirtual   com/tonyodev/fetch/DatabaseHelper.updateStatus:(JII)Z
        //   598: ifeq            189
        //   601: aload_0        
        //   602: getfield        com/tonyodev/fetch/FetchRunnable.broadcastManager:Landroid/support/v4/content/LocalBroadcastManager;
        //   605: aload_0        
        //   606: getfield        com/tonyodev/fetch/FetchRunnable.id:J
        //   609: sipush          904
        //   612: aload_0        
        //   613: getfield        com/tonyodev/fetch/FetchRunnable.progress:I
        //   616: aload_0        
        //   617: getfield        com/tonyodev/fetch/FetchRunnable.downloadedBytes:J
        //   620: aload_0        
        //   621: getfield        com/tonyodev/fetch/FetchRunnable.fileSize:J
        //   624: iload_1        
        //   625: invokestatic    com/tonyodev/fetch/Utils.sendEventUpdate:(Landroid/support/v4/content/LocalBroadcastManager;JIIJJI)V
        //   628: goto            189
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      116    116    631    Ljava/lang/Exception;
        //  0      116    241    252    Any
        //  117    128    241    252    Any
        //  128    189    241    252    Any
        //  198    241    116    631    Ljava/lang/Exception;
        //  198    241    241    252    Any
        //  252    300    116    631    Ljava/lang/Exception;
        //  252    300    241    252    Any
        //  300    318    116    631    Ljava/lang/Exception;
        //  300    318    241    252    Any
        //  325    336    116    631    Ljava/lang/Exception;
        //  325    336    241    252    Any
        //  336    398    116    631    Ljava/lang/Exception;
        //  336    398    241    252    Any
        //  398    406    116    631    Ljava/lang/Exception;
        //  398    406    241    252    Any
        //  409    483    116    631    Ljava/lang/Exception;
        //  409    483    241    252    Any
        //  483    528    116    631    Ljava/lang/Exception;
        //  483    528    241    252    Any
        //  537    552    116    631    Ljava/lang/Exception;
        //  537    552    241    252    Any
        //  555    583    116    631    Ljava/lang/Exception;
        //  555    583    241    252    Any
        //  583    628    241    252    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 296, Size: 296
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
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
}
