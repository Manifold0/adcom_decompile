package com.kongregate.android.internal.sdk;

import com.kongregate.android.api.KongregateEvent;
import com.kongregate.android.api.MicrotransactionServices.ReceiptVerificationListener;
import com.kongregate.android.api.MicrotransactionServices.ReceiptVerificationStatus;
import com.kongregate.android.internal.sdk.C0485c.C0490b;
import com.kongregate.android.internal.util.C0546d;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.p000o.p002g.C0640a;
import com.kongregate.p000o.p002g.C0645b;
import com.kongregate.p000o.p002g.C0645b.C0468a;
import com.kongregate.p000o.p002g.C0646c;
import com.kongregate.p000o.p006c.C0626d;
import com.kongregate.p000o.p009h.C0654a;
import com.kongregate.p000o.p009h.C0655b;
import com.kongregate.p000o.p009h.C0656c;
import java.util.Collection;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kongregate.android.internal.sdk.n */
public class C0512n extends C0490b {
    /* renamed from: b */
    private final NativeAPI f571b;
    /* renamed from: c */
    private C0654a f572c = null;

    public /* bridge */ /* synthetic */ boolean hasItem(String str) {
        return super.hasItem(str);
    }

    public C0512n(NativeAPI nativeAPI) {
        this.f571b = nativeAPI;
    }

    public void requestUserItemList() {
        if (this.f571b.services().isGuest()) {
            C0562j.m759c("Not requesting user item list for guest");
            m466a();
            return;
        }
        final long userId = this.f571b.services().getUserId();
        String gameAuthToken = this.f571b.services().getGameAuthToken();
        String valueOf = String.valueOf(this.f571b.getApplicationId());
        final String toLowerCase = this.f571b.services().getUsername().toLowerCase();
        C0645b.m1085a().m1090a(C0640a.m1054a("/api/user_items.json?game_auth_token=" + gameAuthToken + "&game_id=" + valueOf + "&user_id=" + String.valueOf(userId), true).toString(), new C0468a(this) {
            /* renamed from: c */
            final /* synthetic */ C0512n f565c;

            /* renamed from: a */
            public void mo1133a(C0646c c0646c, JSONObject jSONObject) {
                boolean z = false;
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                boolean optBoolean = jSONObject.optBoolean("success", false);
                Collection linkedList = new LinkedList();
                String str = toLowerCase;
                String str2;
                if (optJSONArray != null) {
                    str2 = str;
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            str2 = str2 + this.f565c.m465a(optJSONObject);
                            linkedList.add(new C0656c(optJSONObject));
                        }
                    }
                } else {
                    str2 = str;
                }
                if (optBoolean && C0546d.m633a(r2, jSONObject.optString("s", ""))) {
                    z = true;
                }
                if (z) {
                    this.f565c.a.put(Long.valueOf(userId), new C0655b(linkedList));
                }
                this.f565c.m466a();
            }

            /* renamed from: a */
            public void mo1185a(C0646c c0646c) {
                super.mo1185a(c0646c);
            }

            /* renamed from: b */
            public void mo1186b(C0646c c0646c, JSONObject jSONObject) {
                super.mo1186b(c0646c, jSONObject);
            }
        });
    }

    /* renamed from: a */
    protected String m465a(JSONObject jSONObject) {
        return ((jSONObject.optString("identifier", "null") + jSONObject.optLong("id")) + jSONObject.optString("data", "null")) + (jSONObject.isNull("remaining_uses") ? "null" : Long.valueOf(jSONObject.optLong("remaining_uses")));
    }

    /* renamed from: a */
    protected void m466a() {
        this.f571b.m309a(KongregateEvent.USER_INVENTORY);
    }

    public void verifyTransaction(String str, String str2) {
        verifyTransaction(str, str2, null);
    }

    public void verifyTransaction(final String str, final String str2, final ReceiptVerificationListener receiptVerificationListener) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ C0512n f570d;

            /* renamed from: com.kongregate.android.internal.sdk.n$2$1 */
            class C05101 implements ReceiptVerificationListener {
                /* renamed from: a */
                final /* synthetic */ C05112 f566a;

                C05101(C05112 c05112) {
                    this.f566a = c05112;
                }

                public void receiptVerificationComplete(boolean z) {
                    if (receiptVerificationListener != null) {
                        receiptVerificationListener.receiptVerificationComplete(z);
                    }
                    this.f566a.f570d.f571b.m309a(KongregateEvent.RECEIPT_VERIFICATION_COMPLETE);
                }
            }

            public void run() {
                if (this.f570d.f572c == null) {
                    this.f570d.f572c = new C0654a(this.f570d.f571b.getApplicationContext());
                }
                this.f570d.f572c.m1132a(str, str2, new C05101(this));
            }
        });
    }

    public ReceiptVerificationStatus receiptVerificationStatus(String str) {
        if (this.f572c != null) {
            return this.f572c.m1129a(str);
        }
        C0562j.m759c("Attempting to retrieve verification status, but verifyTransaction API has not been used");
        return ReceiptVerificationStatus.UNKNOWN;
    }

    /* renamed from: a */
    public String m464a(String str) {
        if (this.f572c == null) {
            return null;
        }
        return this.f572c.m1134b(str);
    }
}
