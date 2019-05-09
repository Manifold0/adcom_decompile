// 
// Decompiled by Procyon v0.5.34
// 

package de.greenrobot.event.util;

import java.util.Iterator;
import android.util.Log;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import java.util.Map;

public class ExceptionToResourceMapping
{
    public final Map<Class<? extends Throwable>, Integer> throwableToMsgIdMap;
    
    public ExceptionToResourceMapping() {
        this.throwableToMsgIdMap = new HashMap<Class<? extends Throwable>, Integer>();
    }
    
    public ExceptionToResourceMapping addMapping(final Class<? extends Throwable> clazz, final int n) {
        this.throwableToMsgIdMap.put(clazz, n);
        return this;
    }
    
    public Integer mapThrowable(final Throwable t) {
        Throwable t2 = t;
        int n = 20;
        Throwable cause;
        do {
            final Integer mapThrowableFlat = this.mapThrowableFlat(t2);
            if (mapThrowableFlat != null) {
                return mapThrowableFlat;
            }
            cause = t2.getCause();
            --n;
        } while (n > 0 && cause != t && (t2 = cause) != null);
        Log.d(EventBus.TAG, "No specific message ressource ID found for " + t);
        return null;
    }
    
    protected Integer mapThrowableFlat(final Throwable t) {
        final Class<? extends Throwable> class1 = t.getClass();
        Integer n2;
        Integer n = n2 = this.throwableToMsgIdMap.get(class1);
        if (n == null) {
            Class clazz = null;
            final Iterator<Map.Entry<Class<? extends Throwable>, Integer>> iterator = this.throwableToMsgIdMap.entrySet().iterator();
            while (true) {
                n2 = n;
                if (!iterator.hasNext()) {
                    break;
                }
                final Map.Entry<Class<? extends Throwable>, Integer> entry = iterator.next();
                final Class<? extends Throwable> clazz2 = entry.getKey();
                if (!clazz2.isAssignableFrom(class1) || (clazz != null && !clazz.isAssignableFrom(clazz2))) {
                    continue;
                }
                clazz = clazz2;
                n = entry.getValue();
            }
        }
        return n2;
    }
}
