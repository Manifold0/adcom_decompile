// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import java.util.Map;
import com.kongregate.o.c.d;
import android.widget.Toast;
import com.kongregate.android.internal.util.j;
import com.kongregate.android.internal.util.n;
import android.content.Context;

public class h
{
    private static final h a;
    
    static {
        a = new h();
    }
    
    public static h a() {
        return h.a;
    }
    
    public static void a(final Context context, final String s, final String s2, final String s3) {
        while (true) {
            try {
                final Class<?> forName = Class.forName("io.keen.client.android.KeenClient");
                n.a(forName, "initialize", new Class[] { Context.class, String.class, String.class, String.class }, context, s, s2, s3);
                final Object a = n.a(forName, "client", new Class[0], new Object[0]);
                if (a == null) {
                    d.b(new Runnable() {
                        @Override
                        public void run() {
                            j.c("KONGREGATE CONFIGURATION WARNING: " + "KeenClient enabled but class not found. Be sure the JAR is enabled and KeenClient class is not obfuscated");
                            Toast.makeText(context, (CharSequence)"KeenClient enabled but class not found. Be sure the JAR is enabled and KeenClient class is not obfuscated", 1).show();
                        }
                    });
                }
            }
            catch (ClassNotFoundException ex) {
                j.c("KeenClient not found", ex);
                final Object a = null;
                continue;
            }
            break;
        }
    }
    
    public static void b() {
        n.a("io.keen.client.android.KeenLogging", "enableLogging", null, new Object[0]);
    }
    
    public void a(final String s, final Map<String, Object> map) {
        if (map == null) {
            j.c("Keen event map may not be null");
            return;
        }
        try {
            final Class<?> forName = Class.forName("io.keen.client.android.KeenClient");
            n.a(forName, "addEvent", n.a(forName, "client", new Class[0], new Object[0]), new Class[] { String.class, Map.class }, s, map);
        }
        catch (ClassNotFoundException ex) {
            j.c("KeenClient class not found, not adding event", ex);
        }
    }
    
    public void c() {
        try {
            final Class<?> forName = Class.forName("io.keen.client.android.KeenClient");
            n.a(forName, "upload", n.a(forName, "client", new Class[0], new Object[0]), new Class[] { Class.forName("io.keen.client.android.UploadFinishedCallback") }, null);
        }
        catch (ClassNotFoundException ex) {
            j.c("KeenClient class not found, not adding event", ex);
        }
    }
}
