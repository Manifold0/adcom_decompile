// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.cache;

import okio.Buffer;
import java.io.IOException;
import okio.Sink;
import okio.ForwardingSink;

class FaultHidingSink extends ForwardingSink
{
    private boolean hasErrors;
    
    public FaultHidingSink(final Sink sink) {
        super(sink);
    }
    
    public void close() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.close();
        }
        catch (IOException ex) {
            this.hasErrors = true;
            this.onException(ex);
        }
    }
    
    public void flush() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.flush();
        }
        catch (IOException ex) {
            this.hasErrors = true;
            this.onException(ex);
        }
    }
    
    protected void onException(final IOException ex) {
    }
    
    public void write(final Buffer buffer, final long n) throws IOException {
        if (this.hasErrors) {
            buffer.skip(n);
            return;
        }
        try {
            super.write(buffer, n);
        }
        catch (IOException ex) {
            this.hasErrors = true;
            this.onException(ex);
        }
    }
}
