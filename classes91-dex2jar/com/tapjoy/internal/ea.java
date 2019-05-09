// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ea extends dl
{
    public static final dn c;
    public static final eb d;
    public final eb e;
    public final String f;
    public final String g;
    
    static {
        c = new b();
        d = eb.APP;
    }
    
    public ea(final eb e, final String f, final String g, final hx hx) {
        super(ea.c, hx);
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ea)) {
                return false;
            }
            final ea ea = (ea)o;
            if (!this.a().equals(ea.a()) || !this.e.equals(ea.e) || !this.f.equals(ea.f) || !ds.a(this.g, ea.g)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        int b;
        if ((b = super.b) == 0) {
            final int hashCode = this.a().hashCode();
            final int hashCode2 = this.e.hashCode();
            final int hashCode3 = this.f.hashCode();
            int hashCode4;
            if (this.g != null) {
                hashCode4 = this.g.hashCode();
            }
            else {
                hashCode4 = 0;
            }
            b = hashCode4 + ((hashCode * 37 + hashCode2) * 37 + hashCode3) * 37;
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(", type=").append(this.e);
        sb.append(", name=").append(this.f);
        if (this.g != null) {
            sb.append(", category=").append(this.g);
        }
        return sb.replace(0, 2, "EventGroup{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public eb c;
        public String d;
        public String e;
        
        public final ea b() {
            if (this.c == null || this.d == null) {
                throw ds.a(this.c, "type", this.d, "name");
            }
            return new ea(this.c, this.d, this.e, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, ea.class);
        }
        
        private static ea b(final do do1) {
            final ea.a a = new ea.a();
            final long a2 = do1.a();
            while (true) {
                final int b = do1.b();
                if (b == -1) {
                    break;
                }
                switch (b) {
                    default: {
                        final dk c = do1.c();
                        ((dl.a)a).a(b, c, c.a().a(do1));
                        continue;
                    }
                    case 1: {
                        try {
                            a.c = (eb)eb.ADAPTER.a(do1);
                        }
                        catch (dn.a a3) {
                            ((dl.a)a).a(b, dk.a, (long)a3.a);
                        }
                        continue;
                    }
                    case 2: {
                        a.d = (String)dn.p.a(do1);
                        continue;
                    }
                    case 3: {
                        a.e = (String)dn.p.a(do1);
                        continue;
                    }
                }
            }
            do1.a(a2);
            return a.b();
        }
    }
}
