package com.kongregate.p000o.p009h;

import android.content.Context;
import android.content.SharedPreferences;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.api.MicrotransactionServices.ReceiptVerificationListener;
import com.kongregate.android.api.MicrotransactionServices.ReceiptVerificationStatus;
import com.kongregate.android.internal.sdk.C0525o;
import com.kongregate.android.internal.util.C0546d;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p002g.C0640a;
import com.kongregate.p000o.p002g.C0645b;
import com.kongregate.p000o.p002g.C0645b.C0468a;
import com.kongregate.p000o.p002g.C0646c;
import com.kongregate.p000o.p006c.C0626d;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.h.a */
public class C0654a {
    /* renamed from: a */
    private final SharedPreferences f1056a;

    /* renamed from: com.kongregate.o.h.a$a */
    static class C0653a {
        /* renamed from: e */
        static final C0653a f1051e = new C0653a(ReceiptVerificationStatus.INVALID, "receipt_json_invalid");
        /* renamed from: a */
        String f1052a;
        /* renamed from: b */
        ReceiptVerificationStatus f1053b;
        /* renamed from: c */
        String f1054c;
        /* renamed from: d */
        String f1055d;

        C0653a() {
        }

        C0653a(ReceiptVerificationStatus receiptVerificationStatus, String str) {
            this.f1053b = receiptVerificationStatus;
            this.f1055d = str;
        }
    }

    public C0654a(Context context) {
        this.f1056a = context.getSharedPreferences("kongregate_purchases", 0);
    }

    /* renamed from: a */
    public void m1132a(String str, String str2, final ReceiptVerificationListener receiptVerificationListener) {
        JSONObject c = C0561i.m749c(str);
        if (c == null) {
            C0562j.m759c("IAP FLOW STEP: verifyTransaction() - not json: " + str);
            m1125a(C0653a.f1051e, receiptVerificationListener);
            return;
        }
        String c2 = C0561i.m748c(c, "productId");
        String c3 = C0561i.m748c(c, "orderId");
        if (c2 == null || c3 == null) {
            C0562j.m759c("IAP FLOW STEP: verifyTransaction() - missing orderId[" + c3 + "] and/or ProductId[" + c2 + RequestParameters.RIGHT_BRACKETS);
            m1125a(C0653a.f1051e, receiptVerificationListener);
            return;
        }
        C0562j.m756b("IAP FLOW STEP: verifyTransaction() - " + c2);
        final C0653a c4 = m1135c(c3);
        c4.f1052a = c3;
        c4.f1054c = c2;
        c4.f1053b = ReceiptVerificationStatus.PROCESSING;
        c4.f1055d = null;
        m1131a(c4);
        Map hashMap = new HashMap();
        hashMap.put(InAppPurchaseMetaData.KEY_SIGNATURE, str2);
        hashMap.put(TransactionDetailsUtilities.RECEIPT, str);
        hashMap.put("game_id", Long.toString(m1133b().getApplicationId()));
        m1130a().m1091a(C0640a.m1054a("/mobile_receipt/validate_google", true).toString(), hashMap, new C0468a(this) {
            /* renamed from: c */
            final /* synthetic */ C0654a f1047c;

            /* renamed from: a */
            public void mo1133a(C0646c c0646c, JSONObject jSONObject) {
                boolean optBoolean = jSONObject.optBoolean("valid", true);
                if (!this.f1047c.m1128a(c4.f1052a, jSONObject)) {
                    C0562j.m753a("validate iap: invalid response");
                    c4.f1053b = ReceiptVerificationStatus.INVALID;
                    c4.f1055d = "response_signature_invalid";
                } else if (optBoolean) {
                    C0562j.m753a("validate iap: valid");
                    c4.f1053b = ReceiptVerificationStatus.VALID;
                    c4.f1055d = jSONObject.optString("reason", "receipt_valid");
                } else {
                    C0562j.m753a("validate iap: invalid receipt");
                    c4.f1053b = ReceiptVerificationStatus.INVALID;
                    c4.f1055d = jSONObject.optString("reason", "receipt_signature_invalid");
                }
                this.f1047c.m1125a(c4, receiptVerificationListener);
            }

            /* renamed from: a */
            public void mo1185a(C0646c c0646c) {
                C0562j.m753a("validate iap: http errror: " + c0646c.m1098e());
                super.mo1185a(c0646c);
                c4.f1053b = ReceiptVerificationStatus.VALID;
                c4.f1055d = "connection_error";
                this.f1047c.m1125a(c4, receiptVerificationListener);
            }

            /* renamed from: b */
            public void mo1186b(C0646c c0646c, JSONObject jSONObject) {
                C0562j.m753a("validate iap: request error: " + c0646c.m1098e());
                super.mo1186b(c0646c, jSONObject);
                c4.f1053b = ReceiptVerificationStatus.VALID;
                c4.f1055d = "server_error";
                this.f1047c.m1125a(c4, receiptVerificationListener);
            }
        });
    }

    /* renamed from: a */
    C0645b m1130a() {
        return C0645b.m1085a();
    }

    /* renamed from: b */
    KongregateAPI m1133b() {
        return APIBootstrap.getInstance();
    }

    /* renamed from: a */
    private boolean m1128a(String str, JSONObject jSONObject) {
        return C0546d.m633a(Long.toString(m1133b().getApplicationId()) + jSONObject.optString("valid") + str, jSONObject.optString(InAppPurchaseMetaData.KEY_SIGNATURE));
    }

    /* renamed from: a */
    private void m1125a(final C0653a c0653a, final ReceiptVerificationListener receiptVerificationListener) {
        if (c0653a != null) {
            m1131a(c0653a);
        }
        if (receiptVerificationListener != null) {
            C0626d.m968b(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C0654a f1050c;

                public void run() {
                    receiptVerificationListener.receiptVerificationComplete(c0653a.f1053b == ReceiptVerificationStatus.VALID);
                }
            });
        }
    }

    /* renamed from: a */
    public ReceiptVerificationStatus m1129a(String str) {
        return m1135c(str).f1053b;
    }

    /* renamed from: b */
    public String m1134b(String str) {
        if (StringUtils.m580a((CharSequence) str)) {
            return null;
        }
        C0653a c = m1135c(str);
        this.f1056a.edit().remove(str).apply();
        return c.f1055d;
    }

    /* renamed from: c */
    public C0653a m1135c(String str) {
        C0653a c0653a = new C0653a();
        JSONObject c = C0561i.m749c(this.f1056a.getString(str, null));
        if (c != null) {
            c0653a.f1052a = str;
            c0653a.f1053b = ReceiptVerificationStatus.valueOf(c.optString("result"));
            c0653a.f1054c = c.optString(C0525o.f622h);
            c0653a.f1055d = c.optString(C0525o.f629o);
        } else {
            C0562j.m759c("Purchase state not found for purchaseToken");
            c0653a.f1053b = ReceiptVerificationStatus.UNKNOWN;
        }
        return c0653a;
    }

    /* renamed from: a */
    void m1131a(C0653a c0653a) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("order_id", c0653a.f1052a);
            jSONObject.put("result", c0653a.f1053b);
            jSONObject.put(C0525o.f622h, c0653a.f1054c);
            jSONObject.put(C0525o.f629o, c0653a.f1055d);
        } catch (Throwable e) {
            C0562j.m760c("problem writting verification state to json", e);
        }
        this.f1056a.edit().putString(c0653a.f1052a, jSONObject.toString()).apply();
    }
}
