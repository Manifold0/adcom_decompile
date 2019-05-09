// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;

public final class gs
{
    public static String a(final dx dx) {
        final bm c = new bm().c();
        if (dx.e != null) {
            c.a("pkg_ver").b(dx.e);
        }
        if (dx.f != null) {
            c.a("pkg_rev").a(dx.f);
        }
        if (dx.g != null) {
            c.a("data_ver").b(dx.g);
        }
        if (dx.h != null) {
            c.a("installer").b(dx.h);
        }
        if (dx.i != null) {
            c.a("store").b(dx.i);
        }
        return c.d().toString();
    }
    
    private static String a(dy iterator, final boolean b, final boolean b2, final boolean b3) {
        final bm b4 = new bm().c().a("type").b(a(iterator.n)).a("name").b(iterator.o);
        b4.a("time");
        while (true) {
            Label_0079: {
                if (iterator.q != null) {
                    b4.a(iterator.p);
                    b4.a("systime").a(iterator.q);
                    break Label_0079;
                }
                Label_1111: {
                    break Label_1111;
                    while (true) {
                        while (true) {
                            try {
                                if (iterator.D != null) {
                                    final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
                                    if (iterator.C != null) {
                                        bs.b(iterator.C).a(linkedHashMap);
                                    }
                                    final ef d = iterator.D;
                                    if (d.d != null) {
                                        linkedHashMap.put("fq7_change", d.d);
                                    }
                                    if (d.e != null) {
                                        linkedHashMap.put("fq30_change", d.e);
                                    }
                                    if (d.f != null) {
                                        linkedHashMap.put("push_id", d.f);
                                    }
                                    b4.a("meta").a(linkedHashMap);
                                }
                                else if (iterator.C != null) {
                                    b4.a("meta").a(new br(iterator.C));
                                }
                                if (iterator.I != null) {
                                    b4.a("dimensions").a(new br(iterator.I));
                                }
                                if (iterator.J != null) {
                                    b4.a("count").a(iterator.J);
                                }
                                if (iterator.K != null) {
                                    b4.a("first_time").a(iterator.K);
                                }
                                if (iterator.L != null) {
                                    b4.a("last_time").a(iterator.L);
                                }
                                if (iterator.E != null) {
                                    b4.a("category").b(iterator.E);
                                }
                                if (iterator.F != null) {
                                    b4.a("p1").b(iterator.F);
                                }
                                if (iterator.G != null) {
                                    b4.a("p2").b(iterator.G);
                                }
                                if (iterator.H.size() > 0) {
                                    b4.a("values").c();
                                    iterator = (dy)iterator.H.iterator();
                                    while (((Iterator)iterator).hasNext()) {
                                        final ec ec = ((Iterator<ec>)iterator).next();
                                        b4.a(ec.e).a(ec.f);
                                    }
                                    break;
                                }
                                return b4.d().toString();
                                // iftrue(Label_1178:, !y.c() || iterator.r == null || iterator.s == null || !gf.a.equals((Object)iterator.r))
                                while (true) {
                                    b4.a(y.a(iterator.s));
                                    b4.a("systime").a(iterator.p);
                                    break Label_0079;
                                    continue;
                                }
                                Label_1178: {
                                    b4.a(iterator.p);
                                }
                                break Label_0079;
                            }
                            catch (IOException ex) {
                                continue;
                            }
                            continue;
                        }
                    }
                }
                b4.d();
                return b4.d().toString();
            }
            if (iterator.t != null) {
                b4.a("duration").a(iterator.t);
            }
            if (!b && iterator.u != null) {
                b4.a("info").a(new br(a(iterator.u)));
            }
            if (!b2 && iterator.v != null) {
                b4.a("app").a(new br(a(iterator.v)));
            }
            if (!b3 && iterator.w != null) {
                b4.a("user").a(new br(a(iterator.w, iterator)));
            }
            if (iterator.y != null) {
                b4.a("event_seq").a(iterator.y);
            }
            if (iterator.z != null) {
                final bm a = b4.a("event_prev");
                final ea z = iterator.z;
                final bm b5 = new bm().c().a("type").b(a(z.e)).a("name").b(z.f);
                if (z.g != null) {
                    b5.a("category").b(z.g);
                }
                a.a(new br(b5.d().toString()));
            }
            if (iterator.A != null) {
                final bm a2 = b4.a("purchase");
                final eg a3 = iterator.A;
                final bm b6 = new bm().c().a("product_id").b(a3.h);
                if (a3.i != null) {
                    b6.a("product_quantity").a(a3.i);
                }
                if (a3.j != null) {
                    b6.a("product_price").a(a3.j);
                }
                if (a3.k != null) {
                    b6.a("product_price_currency").b(a3.k);
                }
                if (a3.s != null) {
                    b6.a("currency_price").b(a3.s);
                }
                if (a3.l != null) {
                    b6.a("product_type").b(a3.l);
                }
                if (a3.m != null) {
                    b6.a("product_title").b(a3.m);
                }
                if (a3.n != null) {
                    b6.a("product_description").b(a3.n);
                }
                if (a3.o != null) {
                    b6.a("transaction_id").b(a3.o);
                }
                if (a3.p != null) {
                    b6.a("transaction_state").a(a3.p);
                }
                if (a3.q != null) {
                    b6.a("transaction_date").a(a3.q);
                }
                if (a3.r != null) {
                    b6.a("campaign_id").b(a3.r);
                }
                if (a3.t != null) {
                    b6.a("receipt").b(a3.t);
                }
                if (a3.u != null) {
                    b6.a("signature").b(a3.u);
                }
                a2.a(new br(b6.d().toString()));
            }
            if (iterator.B != null) {
                b4.a("exception").b(iterator.B);
            }
            continue;
        }
    }
    
    public static String a(final dz dz) {
        final ek ek = null;
        final bm a = new bm().a();
        final Iterator<dy> iterator = (Iterator<dy>)dz.d.iterator();
        dx v = null;
        ed u = null;
        ek w = ek;
        while (iterator.hasNext()) {
            final dy dy = iterator.next();
            boolean b;
            if (u == null || !u.equals(dy.u)) {
                u = dy.u;
                b = false;
            }
            else {
                b = true;
            }
            boolean b2;
            if (v == null || !v.equals(dy.v)) {
                v = dy.v;
                b2 = false;
            }
            else {
                b2 = true;
            }
            boolean b3;
            if (w == null || !w.equals(dy.w)) {
                w = dy.w;
                b3 = false;
            }
            else {
                b3 = true;
            }
            a.a(new br(a(dy, b, b2, b3)));
        }
        return a.b().toString();
    }
    
    private static String a(final eb eb) {
        switch (gs$1.a[eb.ordinal()]) {
            default: {
                throw new RuntimeException();
            }
            case 1: {
                return "app";
            }
            case 2: {
                return "campaign";
            }
            case 3: {
                return "custom";
            }
            case 4: {
                return "usages";
            }
        }
    }
    
    public static String a(final ed ed) {
        final bm b = new bm().c().a("sdk").b(ed.t).a("os_name").b(ed.k).a("os_ver").b(ed.l).a("device_id").b(ed.h).a("device_maker").b(ed.i).a("device_model").b(ed.j).a("pkg_id").b(ed.r).a("pkg_sign").b(ed.s).a("locale").b(ed.p).a("timezone").b(ed.q);
        if (ed.m != null) {
            b.a("display_d").a(ed.m);
        }
        if (ed.n != null) {
            b.a("display_w").a(ed.n);
        }
        if (ed.o != null) {
            b.a("display_h").a(ed.o);
        }
        if (ed.g != null) {
            b.a("mac").b(ed.g);
        }
        if (ed.u != null) {
            b.a("country_sim").b(ed.u);
        }
        if (ed.v != null) {
            b.a("country_net").b(ed.v);
        }
        if (ed.w != null) {
            b.a("imei").b(ed.w);
        }
        return b.d().toString();
    }
    
    public static String a(final ek ek) {
        return a(ek, null);
    }
    
    private static String a(final ek ek, final dy dy) {
        final bm c = new bm().c();
        if (ek.s != null) {
            c.a("installed").a(ek.s);
        }
        if (ek.t != null) {
            c.a("referrer").b(ek.t);
        }
        if (ek.G != null) {
            c.a("idfa").b(ek.G);
            if (ek.H != null && ek.H) {
                c.a("idfa_optout").a(1L);
            }
        }
        else if (dy != null && dy.r != null && gf.a.equals(dy.r)) {
            final String b = gq.b();
            if (b != null) {
                c.a("idfa").b(b);
                if (gq.c()) {
                    c.a("idfa_optout").a(1L);
                }
            }
        }
        if (ek.u != null) {
            c.a("fq7").a(Math.max(ek.u, 1));
        }
        if (ek.v != null) {
            c.a("fq30").a(Math.max(ek.v, 1));
        }
        if (ek.w.size() > 0) {
            final ArrayList<String> list = new ArrayList<String>(ek.w.size());
            for (final eh eh : ek.w) {
                if (eh.h != null) {
                    list.add(eh.f);
                }
            }
            if (!list.isEmpty()) {
                c.a("push").a();
                final Iterator<String> iterator2 = list.iterator();
                while (iterator2.hasNext()) {
                    c.b(iterator2.next());
                }
                c.b();
            }
        }
        c.a("session").c();
        if (ek.x != null) {
            c.a("total_count").a(ek.x);
        }
        if (ek.y != null) {
            c.a("total_length").a(ek.y);
        }
        if (ek.z != null) {
            c.a("last_at").a(ek.z);
        }
        if (ek.A != null) {
            c.a("last_length").a(ek.A);
        }
        c.d();
        c.a("purchase").c();
        if (ek.B != null) {
            c.a("currency").b(ek.B);
        }
        if (ek.C != null) {
            c.a("total_count").a(ek.C);
        }
        if (ek.D != null) {
            c.a("total_price").a(ek.D);
        }
        if (ek.E != null) {
            c.a("last_at").a(ek.E);
        }
        if (ek.F != null) {
            c.a("last_price").a(ek.F);
        }
        c.d();
        if (ek.I != null) {
            c.a("user_id").b(ek.I);
        }
        if (ek.J != null) {
            c.a("user_level").a(ek.J);
        }
        if (ek.K != null) {
            c.a("friend_count").a(ek.K);
        }
        if (ek.L != null) {
            c.a("uv1").b(ek.L);
        }
        if (ek.M != null) {
            c.a("uv2").b(ek.M);
        }
        if (ek.N != null) {
            c.a("uv3").b(ek.N);
        }
        if (ek.O != null) {
            c.a("uv4").b(ek.O);
        }
        if (ek.P != null) {
            c.a("uv5").b(ek.P);
        }
        if (ek.Q.size() > 0) {
            c.a("tags").a(ek.Q);
        }
        if (Boolean.TRUE.equals(ek.R)) {
            c.a("push_optout").a(1L);
        }
        return c.d().toString();
    }
}
