// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class au
{
    public static final class a implements ax
    {
        private final av a;
        
        public a(final av a) {
            this.a = a;
        }
        
        @Override
        public final Object a(Object a) {
            final av a2 = this.a;
            // monitorenter(a2)
            try {
                a = this.a.a(a, false);
                // monitorexit(a2)
                if (a != null) {
                    // monitorenter(a)
                    final Object o = a;
                    final Object a3 = ((at)o).a();
                    return a3;
                }
                return null;
            }
            finally {
                final Object o2;
                a = o2;
            }
            // monitorexit(a2)
            try {
                final Object o = a;
                final Object a4;
                final Object a3 = a4 = ((at)o).a();
                return a4;
            }
            finally {
            }
            // monitorexit(a)
            return null;
        }
        
        @Override
        public final void a(Object a, final Object o) {
            final av a2 = this.a;
            // monitorenter(a2)
            try {
                a = this.a.a(a, true);
                // monitorexit(a2)
                // monitorenter(a)
                final Object o2 = a;
                final Object o3 = o;
                ((at)o2).a(o3);
                return;
            }
            finally {
                final Object o4;
                a = o4;
            }
            // monitorexit(a2)
            try {
                final Object o2 = a;
                final Object o3 = o;
                ((at)o2).a(o3);
            }
            finally {
            }
            // monitorexit(a)
        }
    }
}
