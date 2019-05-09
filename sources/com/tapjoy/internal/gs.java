package com.tapjoy.internal;

import com.adjust.sdk.Constants;
import com.applovin.sdk.AppLovinEventParameters;
import com.ironsource.sdk.constants.LocationConst;
import com.kongregate.android.internal.sdk.C0525o;
import com.kongregate.p000o.p003a.C0578a;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class gs {
    /* renamed from: a */
    public static String m7994a(ed edVar) {
        bm b = new bm().m7216c().m7210a("sdk").m7215b(edVar.f7528t).m7210a("os_name").m7215b(edVar.f7519k).m7210a("os_ver").m7215b(edVar.f7520l).m7210a("device_id").m7215b(edVar.f7516h).m7210a("device_maker").m7215b(edVar.f7517i).m7210a("device_model").m7215b(edVar.f7518j).m7210a(TapjoyConstants.TJC_PACKAGE_ID).m7215b(edVar.f7526r).m7210a(TapjoyConstants.TJC_PACKAGE_SIGN).m7215b(edVar.f7527s).m7210a("locale").m7215b(edVar.f7524p).m7210a(TapjoyConstants.TJC_DEVICE_TIMEZONE).m7215b(edVar.f7525q);
        if (edVar.f7521m != null) {
            b.m7210a(TapjoyConstants.TJC_DEVICE_DISPLAY_DENSITY).m7209a(edVar.f7521m);
        }
        if (edVar.f7522n != null) {
            b.m7210a(TapjoyConstants.TJC_DEVICE_DISPLAY_WIDTH).m7209a(edVar.f7522n);
        }
        if (edVar.f7523o != null) {
            b.m7210a(TapjoyConstants.TJC_DEVICE_DISPLAY_HEIGHT).m7209a(edVar.f7523o);
        }
        if (edVar.f7515g != null) {
            b.m7210a("mac").m7215b(edVar.f7515g);
        }
        if (edVar.f7529u != null) {
            b.m7210a(TapjoyConstants.TJC_DEVICE_COUNTRY_SIM).m7215b(edVar.f7529u);
        }
        if (edVar.f7530v != null) {
            b.m7210a("country_net").m7215b(edVar.f7530v);
        }
        if (edVar.f7531w != null) {
            b.m7210a("imei").m7215b(edVar.f7531w);
        }
        return b.m7217d().toString();
    }

    /* renamed from: a */
    public static String m7990a(dx dxVar) {
        bm c = new bm().m7216c();
        if (dxVar.f7408e != null) {
            c.m7210a(TapjoyConstants.TJC_PACKAGE_VERSION).m7215b(dxVar.f7408e);
        }
        if (dxVar.f7409f != null) {
            c.m7210a(TapjoyConstants.TJC_PACKAGE_REVISION).m7209a(dxVar.f7409f);
        }
        if (dxVar.f7410g != null) {
            c.m7210a("data_ver").m7215b(dxVar.f7410g);
        }
        if (dxVar.f7411h != null) {
            c.m7210a(TapjoyConstants.TJC_INSTALLER).m7215b(dxVar.f7411h);
        }
        if (dxVar.f7412i != null) {
            c.m7210a("store").m7215b(dxVar.f7412i);
        }
        return c.m7217d().toString();
    }

    /* renamed from: a */
    public static String m7995a(ek ekVar) {
        return m7996a(ekVar, null);
    }

    /* renamed from: a */
    private static String m7996a(ek ekVar, dy dyVar) {
        bm c = new bm().m7216c();
        if (ekVar.f7654s != null) {
            c.m7210a(TapjoyConstants.TJC_INSTALLED).m7209a(ekVar.f7654s);
        }
        if (ekVar.f7655t != null) {
            c.m7210a("referrer").m7215b(ekVar.f7655t);
        }
        if (ekVar.f7642G != null) {
            c.m7210a("idfa").m7215b(ekVar.f7642G);
            if (ekVar.f7643H != null && ekVar.f7643H.booleanValue()) {
                c.m7210a("idfa_optout").m7207a(1);
            }
        } else if (!(dyVar == null || dyVar.f7465r == null || !gf.f7891a.equals(dyVar.f7465r))) {
            String b = gq.m7985b();
            if (b != null) {
                c.m7210a("idfa").m7215b(b);
                if (gq.m7986c()) {
                    c.m7210a("idfa_optout").m7207a(1);
                }
            }
        }
        if (ekVar.f7656u != null) {
            c.m7210a(TapjoyConstants.TJC_USER_WEEKLY_FREQUENCY).m7207a((long) Math.max(ekVar.f7656u.intValue(), 1));
        }
        if (ekVar.f7657v != null) {
            c.m7210a(TapjoyConstants.TJC_USER_MONTHLY_FREQUENCY).m7207a((long) Math.max(ekVar.f7657v.intValue(), 1));
        }
        if (ekVar.f7658w.size() > 0) {
            ArrayList arrayList = new ArrayList(ekVar.f7658w.size());
            for (eh ehVar : ekVar.f7658w) {
                if (ehVar.f7587h != null) {
                    arrayList.add(ehVar.f7585f);
                }
            }
            if (!arrayList.isEmpty()) {
                c.m7210a(Constants.PUSH).m7206a();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    c.m7215b((String) it.next());
                }
                c.m7214b();
            }
        }
        c.m7210a(C0578a.f789b).m7216c();
        if (ekVar.f7659x != null) {
            c.m7210a("total_count").m7209a(ekVar.f7659x);
        }
        if (ekVar.f7660y != null) {
            c.m7210a("total_length").m7209a(ekVar.f7660y);
        }
        if (ekVar.f7661z != null) {
            c.m7210a("last_at").m7209a(ekVar.f7661z);
        }
        if (ekVar.f7636A != null) {
            c.m7210a("last_length").m7209a(ekVar.f7636A);
        }
        c.m7217d();
        c.m7210a("purchase").m7216c();
        if (ekVar.f7637B != null) {
            c.m7210a("currency").m7215b(ekVar.f7637B);
        }
        if (ekVar.f7638C != null) {
            c.m7210a("total_count").m7209a(ekVar.f7638C);
        }
        if (ekVar.f7639D != null) {
            c.m7210a("total_price").m7209a(ekVar.f7639D);
        }
        if (ekVar.f7640E != null) {
            c.m7210a("last_at").m7209a(ekVar.f7640E);
        }
        if (ekVar.f7641F != null) {
            c.m7210a("last_price").m7209a(ekVar.f7641F);
        }
        c.m7217d();
        if (ekVar.f7644I != null) {
            c.m7210a("user_id").m7215b(ekVar.f7644I);
        }
        if (ekVar.f7645J != null) {
            c.m7210a(TapjoyConstants.TJC_USER_LEVEL).m7209a(ekVar.f7645J);
        }
        if (ekVar.f7646K != null) {
            c.m7210a(TapjoyConstants.TJC_USER_FRIEND_COUNT).m7209a(ekVar.f7646K);
        }
        if (ekVar.f7647L != null) {
            c.m7210a(TapjoyConstants.TJC_USER_VARIABLE_1).m7215b(ekVar.f7647L);
        }
        if (ekVar.f7648M != null) {
            c.m7210a(TapjoyConstants.TJC_USER_VARIABLE_2).m7215b(ekVar.f7648M);
        }
        if (ekVar.f7649N != null) {
            c.m7210a(TapjoyConstants.TJC_USER_VARIABLE_3).m7215b(ekVar.f7649N);
        }
        if (ekVar.f7650O != null) {
            c.m7210a(TapjoyConstants.TJC_USER_VARIABLE_4).m7215b(ekVar.f7650O);
        }
        if (ekVar.f7651P != null) {
            c.m7210a(TapjoyConstants.TJC_USER_VARIABLE_5).m7215b(ekVar.f7651P);
        }
        if (ekVar.f7652Q.size() > 0) {
            c.m7210a("tags").m7211a(ekVar.f7652Q);
        }
        if (Boolean.TRUE.equals(ekVar.f7653R)) {
            c.m7210a("push_optout").m7207a(1);
        }
        return c.m7217d().toString();
    }

    /* renamed from: a */
    private static String m7991a(dy dyVar, boolean z, boolean z2, boolean z3) {
        bm b;
        bm b2 = new bm().m7216c().m7210a("type").m7215b(m7993a(dyVar.f7461n)).m7210a("name").m7215b(dyVar.f7462o);
        b2.m7210a(LocationConst.TIME);
        if (dyVar.f7464q != null) {
            b2.m7209a(dyVar.f7463p);
            b2.m7210a("systime").m7209a(dyVar.f7464q);
        } else if (!C2999y.m8234c() || dyVar.f7465r == null || dyVar.f7466s == null || !gf.f7891a.equals(dyVar.f7465r)) {
            b2.m7209a(dyVar.f7463p);
        } else {
            b2.m7207a(C2999y.m8230a(dyVar.f7466s.longValue()));
            b2.m7210a("systime").m7209a(dyVar.f7463p);
        }
        if (dyVar.f7467t != null) {
            b2.m7210a("duration").m7209a(dyVar.f7467t);
        }
        if (!(z || dyVar.f7468u == null)) {
            b2.m7210a(String.VIDEO_INFO).m7208a(new br(m7994a(dyVar.f7468u)));
        }
        if (!(z2 || dyVar.f7469v == null)) {
            b2.m7210a(TapjoyConstants.TJC_APP_PLACEMENT).m7208a(new br(m7990a(dyVar.f7469v)));
        }
        if (!(z3 || dyVar.f7470w == null)) {
            b2.m7210a("user").m7208a(new br(m7996a(dyVar.f7470w, dyVar)));
        }
        if (dyVar.f7472y != null) {
            b2.m7210a("event_seq").m7209a(dyVar.f7472y);
        }
        if (dyVar.f7473z != null) {
            bm a = b2.m7210a("event_prev");
            ea eaVar = dyVar.f7473z;
            b = new bm().m7216c().m7210a("type").m7215b(m7993a(eaVar.f7483e)).m7210a("name").m7215b(eaVar.f7484f);
            if (eaVar.f7485g != null) {
                b.m7210a("category").m7215b(eaVar.f7485g);
            }
            a.m7208a(new br(b.m7217d().toString()));
        }
        if (dyVar.f7449A != null) {
            a = b2.m7210a("purchase");
            eg egVar = dyVar.f7449A;
            b = new bm().m7216c().m7210a(C0525o.f622h).m7215b(egVar.f7565h);
            if (egVar.f7566i != null) {
                b.m7210a("product_quantity").m7209a(egVar.f7566i);
            }
            if (egVar.f7567j != null) {
                b.m7210a("product_price").m7209a(egVar.f7567j);
            }
            if (egVar.f7568k != null) {
                b.m7210a("product_price_currency").m7215b(egVar.f7568k);
            }
            if (egVar.f7576s != null) {
                b.m7210a("currency_price").m7215b(egVar.f7576s);
            }
            if (egVar.f7569l != null) {
                b.m7210a("product_type").m7215b(egVar.f7569l);
            }
            if (egVar.f7570m != null) {
                b.m7210a("product_title").m7215b(egVar.f7570m);
            }
            if (egVar.f7571n != null) {
                b.m7210a("product_description").m7215b(egVar.f7571n);
            }
            if (egVar.f7572o != null) {
                b.m7210a(AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER).m7215b(egVar.f7572o);
            }
            if (egVar.f7573p != null) {
                b.m7210a("transaction_state").m7209a(egVar.f7573p);
            }
            if (egVar.f7574q != null) {
                b.m7210a("transaction_date").m7209a(egVar.f7574q);
            }
            if (egVar.f7575r != null) {
                b.m7210a("campaign_id").m7215b(egVar.f7575r);
            }
            if (egVar.f7577t != null) {
                b.m7210a(TransactionDetailsUtilities.RECEIPT).m7215b(egVar.f7577t);
            }
            if (egVar.f7578u != null) {
                b.m7210a(InAppPurchaseMetaData.KEY_SIGNATURE).m7215b(egVar.f7578u);
            }
            a.m7208a(new br(b.m7217d().toString()));
        }
        if (dyVar.f7450B != null) {
            b2.m7210a("exception").m7215b(dyVar.f7450B);
        }
        try {
            if (dyVar.f7452D != null) {
                Map linkedHashMap = new LinkedHashMap();
                if (dyVar.f7451C != null) {
                    bs.m7244b(dyVar.f7451C).m7252a(linkedHashMap);
                }
                ef efVar = dyVar.f7452D;
                if (efVar.f7543d != null) {
                    linkedHashMap.put("fq7_change", efVar.f7543d);
                }
                if (efVar.f7544e != null) {
                    linkedHashMap.put("fq30_change", efVar.f7544e);
                }
                if (efVar.f7545f != null) {
                    linkedHashMap.put(TJAdUnitConstants.PARAM_PUSH_ID, efVar.f7545f);
                }
                b2.m7210a("meta").m7212a(linkedHashMap);
            } else if (dyVar.f7451C != null) {
                b2.m7210a("meta").m7208a(new br(dyVar.f7451C));
            }
        } catch (IOException e) {
        }
        if (dyVar.f7457I != null) {
            b2.m7210a(String.USAGE_TRACKER_DIMENSIONS).m7208a(new br(dyVar.f7457I));
        }
        if (dyVar.f7458J != null) {
            b2.m7210a(NewHtcHomeBadger.COUNT).m7209a(dyVar.f7458J);
        }
        if (dyVar.f7459K != null) {
            b2.m7210a("first_time").m7209a(dyVar.f7459K);
        }
        if (dyVar.f7460L != null) {
            b2.m7210a("last_time").m7209a(dyVar.f7460L);
        }
        if (dyVar.f7453E != null) {
            b2.m7210a("category").m7215b(dyVar.f7453E);
        }
        if (dyVar.f7454F != null) {
            b2.m7210a("p1").m7215b(dyVar.f7454F);
        }
        if (dyVar.f7455G != null) {
            b2.m7210a("p2").m7215b(dyVar.f7455G);
        }
        if (dyVar.f7456H.size() > 0) {
            b2.m7210a(String.USAGE_TRACKER_VALUES).m7216c();
            for (ec ecVar : dyVar.f7456H) {
                b2.m7210a(ecVar.f7492e).m7209a(ecVar.f7493f);
            }
            b2.m7217d();
        }
        return b2.m7217d().toString();
    }

    /* renamed from: a */
    public static String m7992a(dz dzVar) {
        ek ekVar = null;
        bm a = new bm().m7206a();
        dx dxVar = null;
        ed edVar = null;
        for (dy dyVar : dzVar.f7476d) {
            ed edVar2;
            boolean z;
            dx dxVar2;
            boolean z2;
            ek ekVar2;
            boolean z3;
            if (edVar == null || !edVar.equals(dyVar.f7468u)) {
                edVar2 = dyVar.f7468u;
                z = false;
            } else {
                z = true;
                edVar2 = edVar;
            }
            if (dxVar == null || !dxVar.equals(dyVar.f7469v)) {
                dxVar2 = dyVar.f7469v;
                z2 = false;
            } else {
                z2 = true;
                dxVar2 = dxVar;
            }
            if (ekVar == null || !ekVar.equals(dyVar.f7470w)) {
                ekVar2 = dyVar.f7470w;
                z3 = false;
            } else {
                ekVar2 = ekVar;
                z3 = true;
            }
            a.m7208a(new br(m7991a(dyVar, z, z2, z3)));
            ekVar = ekVar2;
            dxVar = dxVar2;
            edVar = edVar2;
        }
        return a.m7214b().toString();
    }

    /* renamed from: a */
    private static String m7993a(eb ebVar) {
        switch (ebVar) {
            case APP:
                return TapjoyConstants.TJC_APP_PLACEMENT;
            case CAMPAIGN:
                return "campaign";
            case CUSTOM:
                return "custom";
            case USAGES:
                return "usages";
            default:
                throw new RuntimeException();
        }
    }
}
