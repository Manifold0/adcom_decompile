// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.o;

import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class e<T extends f, E extends d>
{
    private final Map<Class<E>, List<WeakReference<T>>> a;
    private final Queue<E> b;
    
    public e() {
        this.a = new HashMap<Class<E>, List<WeakReference<T>>>();
        this.b = new ArrayDeque<E>();
    }
    
    private void a(final List<WeakReference<T>> list) {
        if (list != null) {
            int i = 0;
            int n = 0;
            while (i < list.size()) {
                final WeakReference<T> weakReference = list.get(i);
                int n2 = n;
                if (weakReference.get() != null) {
                    list.set(n, weakReference);
                    n2 = n + 1;
                }
                ++i;
                n = n2;
            }
            for (int j = list.size() - 1; j >= n; --j) {
                list.remove(j);
            }
        }
    }
    
    public void a(final E e) {
        while (true) {
            final d d2;
        Label_0169:
            while (true) {
                Label_0075: {
                    synchronized (this) {
                        if (this.b.isEmpty()) {
                            this.b.add(e);
                            while (!this.b.isEmpty()) {
                                final d d = this.b.peek();
                                if (this.a != null) {
                                    break Label_0075;
                                }
                                this.b.remove();
                            }
                            break;
                        }
                        break Label_0169;
                    }
                }
                final List<WeakReference<T>> list = this.a.get(((Throwable)d2).getClass());
                if (list == null) {
                    continue;
                }
                this.a(list);
                if (!list.isEmpty()) {
                    final Iterator<Object> iterator = new ArrayList<Object>(list).iterator();
                    while (iterator.hasNext()) {
                        final f<d> f = iterator.next().get();
                        if (f != null && f.b(d2)) {
                            f.a(d2);
                        }
                    }
                    continue;
                }
                continue;
            }
            this.b.add((E)d2);
            break;
        }
    }
    // monitorexit(this)
    
    public void a(final T... array) {
        // monitorenter(this)
        if (array != null) {
            try {
                for (int length = array.length, i = 0; i < length; ++i) {
                    this.a(array[i]);
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    // monitorexit(this)
    
    public boolean a(final T t) {
        // monitorenter(this)
        boolean add;
        if (t == null) {
            add = false;
        }
        else {
            while (true) {
                while (true) {
                    int n;
                    try {
                        Object a = t.a();
                        if (this.a.get(a) == null) {
                            this.a.put((Class<E>)a, new ArrayList<WeakReference<T>>());
                        }
                        a = this.a.get(a);
                        this.a((List<WeakReference<T>>)a);
                        final int size = ((List)a).size();
                        n = 0;
                        if (n >= size) {
                            add = ((List<WeakReference>)a).add(new WeakReference<T>(t));
                            break;
                        }
                        if (((List<WeakReference<Object>>)a).get(n).get() == t) {
                            add = false;
                            break;
                        }
                    }
                    finally {
                    }
                    // monitorexit(this)
                    ++n;
                    continue;
                }
            }
        }
        // monitorexit(this)
        return add;
    }
    
    public void b(final T... array) {
        // monitorenter(this)
        if (array != null) {
            try {
                for (int length = array.length, i = 0; i < length; ++i) {
                    this.b(array[i]);
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    // monitorexit(this)
    
    public boolean b(@Nullable final T t) {
        // monitorenter(this)
        boolean b = false;
        Label_0009: {
            if (t == null) {
                b = false;
            }
            else {
                try {
                    final List<WeakReference<T>> list = this.a.get(t.a());
                    if (list == null) {
                        b = false;
                    }
                    else {
                        for (int size = list.size(), i = 0; i < size; ++i) {
                            if (list.get(i).get() == t) {
                                list.get(i).clear();
                                b = true;
                                break Label_0009;
                            }
                        }
                        b = false;
                    }
                }
                finally {
                }
                // monitorexit(this)
            }
        }
        // monitorexit(this)
        return b;
    }
}
