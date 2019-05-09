// 
// Decompiled by Procyon v0.5.34
// 

package bolts;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AggregateException extends Exception
{
    private static final String DEFAULT_MESSAGE = "There were multiple errors.";
    private static final long serialVersionUID = 1L;
    private List<Throwable> innerThrowables;
    
    public AggregateException(final String s, final List<? extends Throwable> list) {
        Throwable t;
        if (list != null && list.size() > 0) {
            t = (Throwable)list.get(0);
        }
        else {
            t = null;
        }
        super(s, t);
        this.innerThrowables = Collections.unmodifiableList(list);
    }
    
    public AggregateException(final String s, final Throwable[] array) {
        this(s, Arrays.asList(array));
    }
    
    public AggregateException(final List<? extends Throwable> list) {
        this("There were multiple errors.", list);
    }
    
    @Deprecated
    public Throwable[] getCauses() {
        return this.innerThrowables.toArray(new Throwable[this.innerThrowables.size()]);
    }
    
    @Deprecated
    public List<Exception> getErrors() {
        final ArrayList<Exception> list = new ArrayList<Exception>();
        if (this.innerThrowables != null) {
            for (final Throwable t : this.innerThrowables) {
                if (t instanceof Exception) {
                    list.add((Exception)t);
                }
                else {
                    list.add(new Exception(t));
                }
            }
        }
        return list;
    }
    
    public List<Throwable> getInnerThrowables() {
        return this.innerThrowables;
    }
    
    @Override
    public void printStackTrace(final PrintStream printStream) {
        super.printStackTrace(printStream);
        int n = -1;
        for (final Throwable t : this.innerThrowables) {
            printStream.append("\n");
            printStream.append("  Inner throwable #");
            ++n;
            printStream.append(Integer.toString(n));
            printStream.append(": ");
            t.printStackTrace(printStream);
            printStream.append("\n");
        }
    }
    
    @Override
    public void printStackTrace(final PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        int n = -1;
        for (final Throwable t : this.innerThrowables) {
            printWriter.append("\n");
            printWriter.append("  Inner throwable #");
            ++n;
            printWriter.append(Integer.toString(n));
            printWriter.append(": ");
            t.printStackTrace(printWriter);
            printWriter.append("\n");
        }
    }
}
