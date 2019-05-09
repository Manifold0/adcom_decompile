// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2;

import okio.Okio;
import okio.Buffer;
import okio.Source;
import okio.ForwardingSource;
import okio.BufferedSource;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import java.io.IOException;

final class OkHttpCall<T> implements Call<T>
{
    private final Object[] args;
    private volatile boolean canceled;
    private Throwable creationFailure;
    private boolean executed;
    private okhttp3.Call rawCall;
    private final ServiceMethod<T, ?> serviceMethod;
    
    OkHttpCall(final ServiceMethod<T, ?> serviceMethod, final Object[] args) {
        this.serviceMethod = serviceMethod;
        this.args = args;
    }
    
    private okhttp3.Call createRawCall() throws IOException {
        final okhttp3.Call call = this.serviceMethod.callFactory.newCall(this.serviceMethod.toRequest(this.args));
        if (call == null) {
            throw new NullPointerException("Call.Factory returned null.");
        }
        return call;
    }
    
    @Override
    public void cancel() {
        this.canceled = true;
        synchronized (this) {
            final okhttp3.Call rawCall = this.rawCall;
            // monitorexit(this)
            if (rawCall != null) {
                rawCall.cancel();
            }
        }
    }
    
    @Override
    public OkHttpCall<T> clone() {
        return new OkHttpCall<T>(this.serviceMethod, this.args);
    }
    
    @Override
    public void enqueue(final Callback<T> callback) {
        if (callback == null) {
            throw new NullPointerException("callback == null");
        }
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
        }
        this.executed = true;
        final okhttp3.Call rawCall = this.rawCall;
        final Throwable creationFailure = this.creationFailure;
        okhttp3.Call rawCall2 = rawCall;
        Throwable creationFailure2 = creationFailure;
        while (true) {
            if (rawCall != null) {
                break Label_0090;
            }
            rawCall2 = rawCall;
            if ((creationFailure2 = creationFailure) != null) {
                break Label_0090;
            }
            try {
                rawCall2 = this.createRawCall();
                this.rawCall = rawCall2;
                creationFailure2 = creationFailure;
                // monitorexit(this)
                if (creationFailure2 != null) {
                    callback.onFailure(this, creationFailure2);
                    return;
                }
            }
            catch (Throwable creationFailure2) {
                this.creationFailure = creationFailure2;
                rawCall2 = rawCall;
                continue;
            }
            break;
        }
        if (this.canceled) {
            rawCall2.cancel();
        }
        rawCall2.enqueue((okhttp3.Callback)new okhttp3.Callback() {
            private void callFailure(final Throwable t) {
                try {
                    callback.onFailure(OkHttpCall.this, t);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            
            private void callSuccess(final Response<T> response) {
                try {
                    callback.onResponse(OkHttpCall.this, response);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            
            public void onFailure(final okhttp3.Call call, final IOException ex) {
                try {
                    callback.onFailure(OkHttpCall.this, ex);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            
            public void onResponse(final okhttp3.Call call, final okhttp3.Response response) throws IOException {
                try {
                    this.callSuccess(OkHttpCall.this.parseResponse(response));
                }
                catch (Throwable t) {
                    this.callFailure(t);
                }
            }
        });
    }
    
    @Override
    public Response<T> execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
        }
        this.executed = true;
        if (this.creationFailure != null) {
            if (this.creationFailure instanceof IOException) {
                throw (IOException)this.creationFailure;
            }
            throw (RuntimeException)this.creationFailure;
        }
        else {
            Label_0083: {
                okhttp3.Call rawCall;
                if ((rawCall = this.rawCall) != null) {
                    break Label_0083;
                }
                try {
                    rawCall = this.createRawCall();
                    this.rawCall = rawCall;
                    // monitorexit(this)
                    if (this.canceled) {
                        rawCall.cancel();
                    }
                    return this.parseResponse(rawCall.execute());
                }
                catch (IOException ex) {}
                catch (RuntimeException rawCall) {
                    goto Label_0110;
                }
            }
        }
    }
    
    @Override
    public boolean isCanceled() {
        boolean b = true;
        if (this.canceled) {
            return true;
        }
        while (true) {
            synchronized (this) {
                if (this.rawCall != null && this.rawCall.isCanceled()) {
                    return b;
                }
            }
            b = false;
            return b;
        }
    }
    
    @Override
    public boolean isExecuted() {
        synchronized (this) {
            return this.executed;
        }
    }
    
    Response<T> parseResponse(okhttp3.Response response) throws IOException {
        final ResponseBody body = response.body();
        final okhttp3.Response build = response.newBuilder().body((ResponseBody)new NoContentResponseBody(body.contentType(), body.contentLength())).build();
        final int code = build.code();
        Label_0075: {
            if (code >= 200) {
                if (code < 300) {
                    break Label_0075;
                }
            }
            try {
                return (Response<T>)Response.error(Utils.buffer(body), build);
            }
            finally {
                body.close();
            }
        }
        if (code == 204 || code == 205) {
            body.close();
            return Response.success((T)null, build);
        }
        response = (okhttp3.Response)new ExceptionCatchingRequestBody(body);
        try {
            return Response.success(this.serviceMethod.toResponse((ResponseBody)response), build);
        }
        catch (RuntimeException ex) {
            ((ExceptionCatchingRequestBody)response).throwIfCaught();
            throw ex;
        }
    }
    
    @Override
    public Request request() {
        while (true) {
            Label_0066: {
                synchronized (this) {
                    final okhttp3.Call rawCall = this.rawCall;
                    if (rawCall != null) {
                        return rawCall.request();
                    }
                    if (this.creationFailure == null) {
                        break Label_0066;
                    }
                    if (this.creationFailure instanceof IOException) {
                        throw new RuntimeException("Unable to create request.", this.creationFailure);
                    }
                }
                break;
                try {
                    final okhttp3.Call rawCall2 = this.createRawCall();
                    this.rawCall = rawCall2;
                    return rawCall2.request();
                }
                catch (RuntimeException creationFailure) {
                    throw this.creationFailure = creationFailure;
                }
                catch (IOException creationFailure2) {
                    this.creationFailure = creationFailure2;
                    throw new RuntimeException("Unable to create request.", creationFailure2);
                }
            }
        }
        throw (RuntimeException)this.creationFailure;
    }
    
    static final class ExceptionCatchingRequestBody extends ResponseBody
    {
        private final ResponseBody delegate;
        IOException thrownException;
        
        ExceptionCatchingRequestBody(final ResponseBody delegate) {
            this.delegate = delegate;
        }
        
        public void close() {
            this.delegate.close();
        }
        
        public long contentLength() {
            return this.delegate.contentLength();
        }
        
        public MediaType contentType() {
            return this.delegate.contentType();
        }
        
        public BufferedSource source() {
            return Okio.buffer((Source)new ForwardingSource(this.delegate.source()) {
                public long read(final Buffer buffer, long read) throws IOException {
                    try {
                        read = super.read(buffer, read);
                        return read;
                    }
                    catch (IOException thrownException) {
                        throw ExceptionCatchingRequestBody.this.thrownException = thrownException;
                    }
                }
            });
        }
        
        void throwIfCaught() throws IOException {
            if (this.thrownException != null) {
                throw this.thrownException;
            }
        }
    }
    
    static final class NoContentResponseBody extends ResponseBody
    {
        private final long contentLength;
        private final MediaType contentType;
        
        NoContentResponseBody(final MediaType contentType, final long contentLength) {
            this.contentType = contentType;
            this.contentLength = contentLength;
        }
        
        public long contentLength() {
            return this.contentLength;
        }
        
        public MediaType contentType() {
            return this.contentType;
        }
        
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }
}
