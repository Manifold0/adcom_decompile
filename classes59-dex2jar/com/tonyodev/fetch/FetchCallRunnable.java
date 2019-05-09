// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch;

import com.tonyodev.fetch.exception.DownloadInterruptedException;
import java.util.Iterator;
import com.tonyodev.fetch.request.Header;
import java.net.URL;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import android.os.Looper;
import com.tonyodev.fetch.request.Request;
import java.io.InputStream;
import java.net.HttpURLConnection;
import android.os.Handler;
import com.tonyodev.fetch.callback.FetchCall;
import java.io.BufferedReader;

final class FetchCallRunnable implements Runnable
{
    private BufferedReader bufferedReader;
    private final Callback callback;
    private final FetchCall<String> fetchCall;
    private final Handler handler;
    private HttpURLConnection httpURLConnection;
    private InputStream input;
    private volatile boolean interrupted;
    private final Request request;
    private String response;
    
    FetchCallRunnable(final Request request, final FetchCall<String> fetchCall, final Callback callback) {
        this.handler = new Handler(Looper.getMainLooper());
        this.interrupted = false;
        if (request == null) {
            throw new NullPointerException("Request Cannot be null");
        }
        if (fetchCall == null) {
            throw new NullPointerException("FetchCall cannot be null");
        }
        if (callback == null) {
            throw new NullPointerException("Callback cannot be null");
        }
        this.request = request;
        this.fetchCall = fetchCall;
        this.callback = callback;
    }
    
    private String inputToString() throws IOException {
        final StringBuilder sb = new StringBuilder();
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.input));
        while (true) {
            final String line = this.bufferedReader.readLine();
            if (line == null || this.isInterrupted()) {
                break;
            }
            sb.append(line);
        }
        if (this.isInterrupted()) {
            return null;
        }
        return sb.toString();
    }
    
    private boolean isInterrupted() {
        return this.interrupted;
    }
    
    private void release() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/tonyodev/fetch/FetchCallRunnable.input:Ljava/io/InputStream;
        //     4: ifnull          14
        //     7: aload_0        
        //     8: getfield        com/tonyodev/fetch/FetchCallRunnable.input:Ljava/io/InputStream;
        //    11: invokevirtual   java/io/InputStream.close:()V
        //    14: aload_0        
        //    15: getfield        com/tonyodev/fetch/FetchCallRunnable.bufferedReader:Ljava/io/BufferedReader;
        //    18: ifnull          28
        //    21: aload_0        
        //    22: getfield        com/tonyodev/fetch/FetchCallRunnable.bufferedReader:Ljava/io/BufferedReader;
        //    25: invokevirtual   java/io/BufferedReader.close:()V
        //    28: aload_0        
        //    29: getfield        com/tonyodev/fetch/FetchCallRunnable.httpURLConnection:Ljava/net/HttpURLConnection;
        //    32: ifnull          42
        //    35: aload_0        
        //    36: getfield        com/tonyodev/fetch/FetchCallRunnable.httpURLConnection:Ljava/net/HttpURLConnection;
        //    39: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //    42: return         
        //    43: astore_1       
        //    44: aload_1        
        //    45: invokevirtual   java/io/IOException.printStackTrace:()V
        //    48: goto            14
        //    51: astore_1       
        //    52: aload_1        
        //    53: invokevirtual   java/io/IOException.printStackTrace:()V
        //    56: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      14     43     51     Ljava/io/IOException;
        //  14     28     51     59     Ljava/io/IOException;
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
    
    private void setHttpConnectionPrefs() throws IOException {
        (this.httpURLConnection = (HttpURLConnection)new URL(this.request.getUrl()).openConnection()).setRequestMethod("GET");
        this.httpURLConnection.setReadTimeout(15000);
        this.httpURLConnection.setConnectTimeout(10000);
        this.httpURLConnection.setUseCaches(true);
        this.httpURLConnection.setDefaultUseCaches(true);
        this.httpURLConnection.setInstanceFollowRedirects(true);
        this.httpURLConnection.setDoInput(true);
        for (final Header header : this.request.getHeaders()) {
            this.httpURLConnection.addRequestProperty(header.getHeader(), header.getValue());
        }
    }
    
    public Request getRequest() {
        return this.request;
    }
    
    void interrupt() {
        synchronized (this) {
            this.interrupted = true;
        }
    }
    
    @Override
    public void run() {
        Label_0100: {
            try {
                this.setHttpConnectionPrefs();
                this.httpURLConnection.connect();
                final int n = this.httpURLConnection.getResponseCode();
                if (n != 200) {
                    break Label_0100;
                }
                if (this.isInterrupted()) {
                    throw new DownloadInterruptedException("DIE", -118);
                }
                break Label_0100;
            }
            catch (Exception ex) {
                try {
                    ex.printStackTrace();
                    final int n = ErrorUtils.getCode(ex.getMessage());
                    if (!this.isInterrupted()) {
                        this.handler.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                FetchCallRunnable.this.fetchCall.onError(n, FetchCallRunnable.this.request);
                            }
                        });
                    }
                    return;
                    this.handler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            FetchCallRunnable.this.fetchCall.onSuccess(FetchCallRunnable.this.response, FetchCallRunnable.this.request);
                        }
                    });
                    Label_0142: {
                        this.release();
                    }
                    this.callback.onDone(this.request);
                    return;
                    throw new IllegalStateException("SSRV:" + n);
                    this.input = this.httpURLConnection.getInputStream();
                    this.response = this.inputToString();
                }
                // iftrue(Label_0142:, this.isInterrupted())
                finally {
                    this.release();
                    this.callback.onDone(this.request);
                }
            }
        }
    }
    
    interface Callback
    {
        void onDone(final Request p0);
    }
}
