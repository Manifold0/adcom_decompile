// 
// Decompiled by Procyon v0.5.34
// 

package rx.exceptions;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.HashSet;
import rx.annotations.Beta;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Collection;
import java.util.List;

public final class CompositeException extends RuntimeException
{
    private static final long serialVersionUID = 3026362227162912146L;
    private Throwable cause;
    private final List<Throwable> exceptions;
    private final String message;
    
    @Deprecated
    public CompositeException(final String s, final Collection<? extends Throwable> collection) {
        final LinkedHashSet<NullPointerException> set = new LinkedHashSet<NullPointerException>();
        final ArrayList<Object> list = new ArrayList<Object>();
        if (collection != null) {
            for (final Throwable t : collection) {
                if (t instanceof CompositeException) {
                    set.addAll((Collection<?>)((CompositeException)t).getExceptions());
                }
                else if (t != null) {
                    set.add((NullPointerException)t);
                }
                else {
                    set.add(new NullPointerException());
                }
            }
        }
        else {
            set.add(new NullPointerException());
        }
        list.addAll(set);
        this.exceptions = Collections.unmodifiableList((List<? extends Throwable>)list);
        this.message = this.exceptions.size() + " exceptions occurred. ";
    }
    
    public CompositeException(final Collection<? extends Throwable> collection) {
        this(null, collection);
    }
    
    @Beta
    public CompositeException(final Throwable... array) {
        final LinkedHashSet<NullPointerException> set = new LinkedHashSet<NullPointerException>();
        final ArrayList<Object> list = new ArrayList<Object>();
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                final Throwable t = array[i];
                if (t instanceof CompositeException) {
                    set.addAll((Collection<?>)((CompositeException)t).getExceptions());
                }
                else if (t != null) {
                    set.add((NullPointerException)t);
                }
                else {
                    set.add(new NullPointerException());
                }
            }
        }
        else {
            set.add(new NullPointerException());
        }
        list.addAll(set);
        this.exceptions = Collections.unmodifiableList((List<? extends Throwable>)list);
        this.message = this.exceptions.size() + " exceptions occurred. ";
    }
    
    private void appendStackTrace(final StringBuilder sb, final Throwable t, final String s) {
        sb.append(s).append(t).append('\n');
        final StackTraceElement[] stackTrace = t.getStackTrace();
        for (int length = stackTrace.length, i = 0; i < length; ++i) {
            sb.append("\t\tat ").append(stackTrace[i]).append('\n');
        }
        if (t.getCause() != null) {
            sb.append("\tCaused by: ");
            this.appendStackTrace(sb, t.getCause(), "");
        }
    }
    
    private List<Throwable> getListOfCauses(Throwable cause) {
        final ArrayList<Throwable> list = new ArrayList<Throwable>();
        final Throwable cause2 = cause.getCause();
        Throwable cause3;
        if (cause2 != null && (cause3 = cause2) != cause) {
            Label_0033: {
                break Label_0033;
                do {
                    cause3 = cause3.getCause();
                    list.add(cause3);
                    cause = cause3.getCause();
                    if (cause != null) {
                        continue;
                    }
                    return list;
                } while (cause != cause3);
            }
            return list;
        }
        return list;
    }
    
    private Throwable getRootCause(Throwable cause) {
        final Throwable cause2 = cause.getCause();
        Throwable cause3;
        if (cause2 == null || (cause3 = cause2) == cause) {
            return cause;
        }
        Label_0023: {
            break Label_0023;
            do {
                cause3 = cause3.getCause();
                cause = cause3.getCause();
            } while (cause != null && cause != cause3);
        }
        return cause3;
    }
    
    private void printStackTrace(final PrintStreamOrWriter printStreamOrWriter) {
        final StringBuilder sb = new StringBuilder(128);
        sb.append(this).append('\n');
        final StackTraceElement[] stackTrace = this.getStackTrace();
        for (int length = stackTrace.length, i = 0; i < length; ++i) {
            sb.append("\tat ").append(stackTrace[i]).append('\n');
        }
        int n = 1;
        for (final Throwable t : this.exceptions) {
            sb.append("  ComposedException ").append(n).append(" :\n");
            this.appendStackTrace(sb, t, "\t");
            ++n;
        }
        synchronized (printStreamOrWriter.lock()) {
            printStreamOrWriter.println(sb.toString());
        }
    }
    
    @Override
    public Throwable getCause() {
        while (true) {
        Label_0039_Outer:
            while (true) {
            Label_0163_Outer:
                while (true) {
                    Label_0172: {
                        Throwable t = null;
                        synchronized (this) {
                            if (this.cause != null) {
                                break Label_0177;
                            }
                            final CompositeExceptionCausalChain cause = new CompositeExceptionCausalChain();
                            final HashSet<Throwable> set = new HashSet<Throwable>();
                            final Iterator<Throwable> iterator = this.exceptions.iterator();
                            Block_6: {
                                while (iterator.hasNext()) {
                                    t = iterator.next();
                                    if (!set.contains(t)) {
                                        break Block_6;
                                    }
                                }
                                break Label_0172;
                            }
                            set.add(t);
                            for (final Throwable t2 : this.getListOfCauses(t)) {
                                if (set.contains(t2)) {
                                    t = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                                }
                                else {
                                    set.add(t2);
                                }
                            }
                        }
                        while (true) {
                            try {
                                Throwable t3 = null;
                                t3.initCause(t);
                                t3 = this.getRootCause(t3);
                                continue Label_0163_Outer;
                                t3 = this.cause;
                                // monitorexit(this)
                                return t3;
                                final CompositeExceptionCausalChain cause;
                                this.cause = cause;
                                continue Label_0039_Outer;
                            }
                            catch (Throwable t) {
                                continue;
                            }
                            break;
                        }
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public List<Throwable> getExceptions() {
        return this.exceptions;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
    
    @Override
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }
    
    @Override
    public void printStackTrace(final PrintStream printStream) {
        this.printStackTrace((PrintStreamOrWriter)new WrappedPrintStream(printStream));
    }
    
    @Override
    public void printStackTrace(final PrintWriter printWriter) {
        this.printStackTrace((PrintStreamOrWriter)new WrappedPrintWriter(printWriter));
    }
    
    static final class CompositeExceptionCausalChain extends RuntimeException
    {
        static final String MESSAGE = "Chain of Causes for CompositeException In Order Received =>";
        private static final long serialVersionUID = 3875212506787802066L;
        
        @Override
        public String getMessage() {
            return "Chain of Causes for CompositeException In Order Received =>";
        }
    }
    
    abstract static class PrintStreamOrWriter
    {
        abstract Object lock();
        
        abstract void println(final Object p0);
    }
    
    static final class WrappedPrintStream extends PrintStreamOrWriter
    {
        private final PrintStream printStream;
        
        WrappedPrintStream(final PrintStream printStream) {
            this.printStream = printStream;
        }
        
        @Override
        Object lock() {
            return this.printStream;
        }
        
        @Override
        void println(final Object o) {
            this.printStream.println(o);
        }
    }
    
    static final class WrappedPrintWriter extends PrintStreamOrWriter
    {
        private final PrintWriter printWriter;
        
        WrappedPrintWriter(final PrintWriter printWriter) {
            this.printWriter = printWriter;
        }
        
        @Override
        Object lock() {
            return this.printWriter;
        }
        
        @Override
        void println(final Object o) {
            this.printWriter.println(o);
        }
    }
}
