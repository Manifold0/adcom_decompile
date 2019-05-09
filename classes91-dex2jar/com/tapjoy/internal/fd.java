// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.SharedPreferences;
import java.util.Map;
import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import android.content.Context;

public final class fd
{
    private static final fd b;
    private static fd c;
    public final fb a;
    private Context d;
    
    static {
        fd.c = (b = new fd());
    }
    
    fd() {
        this.a = new fb();
    }
    
    public static fd a() {
        return fd.c;
    }
    
    public static fb b() {
        return fd.c.a;
    }
    
    public final void a(Context c) {
        // monitorenter(this)
        if (c == null) {
            return;
        }
        try {
            if (this.d != null) {
                return;
            }
            this.d = c;
            c = (Context)this.c();
            Object o = this.c().getString("configurations", (String)null);
            Label_0062: {
                if (o == null) {
                    break Label_0062;
                }
                try {
                    o = bs.b((String)o);
                    try {
                        final Map d = ((bs)o).d();
                        ((bs)o).close();
                        this.a.a(d);
                        c = (Context)new Observer() {
                            @Override
                            public final void update(final Observable observable, Object a) {
                                fi.a(fd.this.a.a("usage_tracking_enabled", false));
                                final Iterator<fm.a> iterator = fd.this.a.b.iterator();
                                while (true) {
                                    while (iterator.hasNext()) {
                                        a = iterator.next().a("usage_tracking_exclude");
                                        if (a != null && List.class.isInstance(a)) {
                                            final Collection cast = List.class.cast(a);
                                            fi.a(cast);
                                            return;
                                        }
                                    }
                                    final Collection cast = null;
                                    continue;
                                }
                            }
                        };
                        this.a.addObserver((Observer)c);
                        ((Observer)c).update(this.a, null);
                    }
                    finally {
                        ((bs)o).close();
                    }
                }
                catch (Exception o) {
                    ((SharedPreferences)c).edit().remove("configurations").commit();
                }
            }
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public final SharedPreferences c() {
        return this.d.getSharedPreferences("tjcPrefrences", 0);
    }
}
