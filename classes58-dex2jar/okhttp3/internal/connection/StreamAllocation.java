// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.connection;

import java.lang.ref.WeakReference;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.OkHttpClient;
import java.lang.ref.Reference;
import okhttp3.internal.Util;
import java.io.IOException;
import okhttp3.internal.Internal;
import java.net.Socket;
import okhttp3.Route;
import okhttp3.ConnectionPool;
import okhttp3.internal.http.HttpCodec;
import okhttp3.Address;

public final class StreamAllocation
{
    public final Address address;
    private final Object callStackTrace;
    private boolean canceled;
    private HttpCodec codec;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    private int refusedStreamCount;
    private boolean released;
    private Route route;
    private final RouteSelector routeSelector;
    
    public StreamAllocation(final ConnectionPool connectionPool, final Address address, final Object callStackTrace) {
        this.connectionPool = connectionPool;
        this.address = address;
        this.routeSelector = new RouteSelector(address, this.routeDatabase());
        this.callStackTrace = callStackTrace;
    }
    
    private Socket deallocate(final boolean b, final boolean b2, final boolean b3) {
        assert Thread.holdsLock(this.connectionPool);
        if (b3) {
            this.codec = null;
        }
        if (b2) {
            this.released = true;
        }
        final Socket socket = null;
        final Socket socket2 = null;
        Socket socket3 = socket;
        if (this.connection != null) {
            if (b) {
                this.connection.noNewStreams = true;
            }
            socket3 = socket;
            if (this.codec == null) {
                if (!this.released) {
                    socket3 = socket;
                    if (!this.connection.noNewStreams) {
                        return socket3;
                    }
                }
                this.release(this.connection);
                socket3 = socket2;
                if (this.connection.allocations.isEmpty()) {
                    this.connection.idleAtNanos = System.nanoTime();
                    socket3 = socket2;
                    if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                        socket3 = this.connection.socket();
                    }
                }
                this.connection = null;
            }
        }
        return socket3;
    }
    
    private RealConnection findConnection(final int n, final int n2, final int n3, final boolean b) throws IOException {
        synchronized (this.connectionPool) {
            if (this.released) {
                throw new IllegalStateException("released");
            }
        }
        if (this.codec != null) {
            throw new IllegalStateException("codec != null");
        }
        if (this.canceled) {
            throw new IOException("Canceled");
        }
        final RealConnection connection = this.connection;
        if (connection != null && !connection.noNewStreams) {
            // monitorexit(connectionPool)
            return connection;
        }
        Internal.instance.get(this.connectionPool, this.address, this);
        if (this.connection != null) {
            // monitorexit(connectionPool)
            return this.connection;
        }
        final Route route = this.route;
        // monitorexit(connectionPool)
        Route next;
        if ((next = route) == null) {
            next = this.routeSelector.next();
        }
        final RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.route = next;
            this.refusedStreamCount = 0;
            realConnection = new RealConnection(this.connectionPool, next);
            this.acquire(realConnection);
            if (this.canceled) {
                throw new IOException("Canceled");
            }
        }
        // monitorexit(connectionPool2)
        realConnection.connect(n, n2, n3, b);
        this.routeDatabase().connected(realConnection.route());
        Socket deduplicate = null;
        synchronized (this.connectionPool) {
            Internal.instance.put(this.connectionPool, realConnection);
            RealConnection connection3 = realConnection;
            if (realConnection.isMultiplexed()) {
                deduplicate = Internal.instance.deduplicate(this.connectionPool, this.address, this);
                connection3 = this.connection;
            }
            // monitorexit(this.connectionPool)
            Util.closeQuietly(deduplicate);
            return connection3;
        }
    }
    
    private RealConnection findHealthyConnection(final int n, final int n2, final int n3, final boolean b, final boolean b2) throws IOException {
        while (true) {
            final RealConnection connection = this.findConnection(n, n2, n3, b);
            synchronized (this.connectionPool) {
                if (connection.successCount == 0) {
                    return connection;
                }
                // monitorexit(this.connectionPool)
                if (!connection.isHealthy(b2)) {
                    this.noNewStreams();
                    continue;
                }
            }
            break;
        }
        return;
    }
    
    private void release(final RealConnection realConnection) {
        for (int i = 0; i < realConnection.allocations.size(); ++i) {
            if (realConnection.allocations.get(i).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }
    
    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }
    
    public void acquire(final RealConnection connection) {
        assert Thread.holdsLock(this.connectionPool);
        if (this.connection != null) {
            throw new IllegalStateException();
        }
        this.connection = connection;
        connection.allocations.add(new StreamAllocationReference(this, this.callStackTrace));
    }
    
    public void cancel() {
        RealConnection connection;
        while (true) {
            synchronized (this.connectionPool) {
                this.canceled = true;
                final HttpCodec codec = this.codec;
                connection = this.connection;
                // monitorexit(this.connectionPool)
                if (codec != null) {
                    codec.cancel();
                    return;
                }
            }
            if (connection != null) {
                break;
            }
            return;
        }
        connection.cancel();
    }
    
    public HttpCodec codec() {
        synchronized (this.connectionPool) {
            return this.codec;
        }
    }
    
    public RealConnection connection() {
        synchronized (this) {
            return this.connection;
        }
    }
    
    public boolean hasMoreRoutes() {
        return this.route != null || this.routeSelector.hasNext();
    }
    
    public HttpCodec newStream(final OkHttpClient okHttpClient, final boolean b) {
        final int connectTimeoutMillis = okHttpClient.connectTimeoutMillis();
        final int timeoutMillis = okHttpClient.readTimeoutMillis();
        final int writeTimeoutMillis = okHttpClient.writeTimeoutMillis();
        final boolean retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure();
        try {
            final HttpCodec codec = this.findHealthyConnection(connectTimeoutMillis, timeoutMillis, writeTimeoutMillis, retryOnConnectionFailure, b).newCodec(okHttpClient, this);
            synchronized (this.connectionPool) {
                return this.codec = codec;
            }
        }
        catch (IOException ex) {
            throw new RouteException(ex);
        }
    }
    
    public void noNewStreams() {
        synchronized (this.connectionPool) {
            final Socket deallocate = this.deallocate(true, false, false);
            // monitorexit(this.connectionPool)
            Util.closeQuietly(deallocate);
        }
    }
    
    public void release() {
        synchronized (this.connectionPool) {
            final Socket deallocate = this.deallocate(false, true, false);
            // monitorexit(this.connectionPool)
            Util.closeQuietly(deallocate);
        }
    }
    
    public Socket releaseAndAcquire(final RealConnection connection) {
        assert Thread.holdsLock(this.connectionPool);
        if (this.codec != null || this.connection.allocations.size() != 1) {
            throw new IllegalStateException();
        }
        final Reference<StreamAllocation> reference = this.connection.allocations.get(0);
        final Socket deallocate = this.deallocate(true, false, false);
        this.connection = connection;
        connection.allocations.add(reference);
        return deallocate;
    }
    
    public void streamFailed(final IOException ex) {
        final boolean b = false;
        synchronized (this.connectionPool) {
            boolean b2 = false;
            Label_0070: {
                if (ex instanceof StreamResetException) {
                    final StreamResetException ex2 = (StreamResetException)ex;
                    if (ex2.errorCode == ErrorCode.REFUSED_STREAM) {
                        ++this.refusedStreamCount;
                    }
                    if (ex2.errorCode == ErrorCode.REFUSED_STREAM) {
                        b2 = b;
                        if (this.refusedStreamCount <= 1) {
                            break Label_0070;
                        }
                    }
                    b2 = true;
                    this.route = null;
                }
                else {
                    b2 = b;
                    if (this.connection != null) {
                        if (this.connection.isMultiplexed()) {
                            b2 = b;
                            if (!(ex instanceof ConnectionShutdownException)) {
                                break Label_0070;
                            }
                        }
                        b2 = true;
                        if (this.connection.successCount == 0) {
                            if (this.route != null && ex != null) {
                                this.routeSelector.connectFailed(this.route, ex);
                            }
                            this.route = null;
                            b2 = b2;
                        }
                    }
                }
            }
            final Socket deallocate = this.deallocate(b2, false, true);
            // monitorexit(this.connectionPool)
            Util.closeQuietly(deallocate);
        }
    }
    
    public void streamFinished(final boolean b, final HttpCodec httpCodec) {
        final ConnectionPool connectionPool = this.connectionPool;
        // monitorenter(connectionPool)
        while (true) {
            if (httpCodec != null) {
                try {
                    if (httpCodec != this.codec) {
                        throw new IllegalStateException("expected " + this.codec + " but was " + httpCodec);
                    }
                }
                finally {
                }
                // monitorexit(connectionPool)
                if (!b) {
                    final RealConnection connection = this.connection;
                    ++connection.successCount;
                }
                final Socket deallocate = this.deallocate(b, false, true);
                // monitorexit(connectionPool)
                Util.closeQuietly(deallocate);
                return;
            }
            continue;
        }
    }
    
    @Override
    public String toString() {
        final RealConnection connection = this.connection();
        if (connection != null) {
            return connection.toString();
        }
        return this.address.toString();
    }
    
    public static final class StreamAllocationReference extends WeakReference<StreamAllocation>
    {
        public final Object callStackTrace;
        
        StreamAllocationReference(final StreamAllocation streamAllocation, final Object callStackTrace) {
            super(streamAllocation);
            this.callStackTrace = callStackTrace;
        }
    }
}
