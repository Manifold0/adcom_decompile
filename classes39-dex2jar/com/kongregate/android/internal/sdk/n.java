// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import org.json.JSONArray;
import java.util.Collection;
import com.kongregate.android.internal.util.d;
import java.util.LinkedList;
import com.kongregate.o.g.b;
import com.kongregate.android.internal.util.j;
import com.kongregate.android.api.MicrotransactionServices;
import java.io.Serializable;
import org.json.JSONObject;
import com.kongregate.o.h.a;

public class n extends b
{
    private final NativeAPI b;
    private com.kongregate.o.h.a c;
    
    public n(final NativeAPI b) {
        this.c = null;
        this.b = b;
    }
    
    public String a(final String s) {
        if (this.c == null) {
            return null;
        }
        return this.c.b(s);
    }
    
    protected String a(final JSONObject jsonObject) {
        final StringBuilder append = new StringBuilder().append(jsonObject.optString("identifier", "null") + jsonObject.optLong("id") + jsonObject.optString("data", "null"));
        Serializable value;
        if (jsonObject.isNull("remaining_uses")) {
            value = "null";
        }
        else {
            value = jsonObject.optLong("remaining_uses");
        }
        return append.append(value).toString();
    }
    
    protected void a() {
        this.b.a("KONG_API_EVENT_USER_INVENTORY");
    }
    
    @Override
    public ReceiptVerificationStatus receiptVerificationStatus(final String s) {
        if (this.c == null) {
            com.kongregate.android.internal.util.j.c("Attempting to retrieve verification status, but verifyTransaction API has not been used");
            return ReceiptVerificationStatus.UNKNOWN;
        }
        return this.c.a(s);
    }
    
    @Override
    public void requestUserItemList() {
        if (this.b.services().isGuest()) {
            com.kongregate.android.internal.util.j.c("Not requesting user item list for guest");
            this.a();
            return;
        }
        final long userId = this.b.services().getUserId();
        com.kongregate.o.g.b.a().a(com.kongregate.o.g.a.a("/api/user_items.json?game_auth_token=" + this.b.services().getGameAuthToken() + "&game_id=" + String.valueOf(this.b.getApplicationId()) + "&user_id=" + String.valueOf(userId), true).toString(), (com.kongregate.o.g.b.a)new com.kongregate.o.g.b.a() {
            final /* synthetic */ String a = n.this.b.services().getUsername().toLowerCase();
            
            @Override
            public void a(final com.kongregate.o.g.c c) {
                super.a(c);
            }
            
            @Override
            public void a(final com.kongregate.o.g.c c, final JSONObject jsonObject) {
                final boolean b = false;
                final JSONArray optJSONArray = jsonObject.optJSONArray("items");
                final boolean optBoolean = jsonObject.optBoolean("success", false);
                final LinkedList<com.kongregate.o.h.c> list = new LinkedList<com.kongregate.o.h.c>();
                String a = this.a;
                String s;
                if (optJSONArray != null) {
                    int n = 0;
                    while (true) {
                        s = a;
                        if (n >= optJSONArray.length()) {
                            break;
                        }
                        final JSONObject optJSONObject = optJSONArray.optJSONObject(n);
                        String string = a;
                        if (optJSONObject != null) {
                            string = a + com.kongregate.android.internal.sdk.n.this.a(optJSONObject);
                            list.add(new com.kongregate.o.h.c(optJSONObject));
                        }
                        ++n;
                        a = string;
                    }
                }
                else {
                    s = a;
                }
                int n2 = b ? 1 : 0;
                if (optBoolean) {
                    n2 = (b ? 1 : 0);
                    if (d.a(s, jsonObject.optString("s", ""))) {
                        n2 = 1;
                    }
                }
                if (n2 != 0) {
                    n.this.a.put(userId, new com.kongregate.o.h.b(list));
                }
                n.this.a();
            }
            
            @Override
            public void b(final com.kongregate.o.g.c c, final JSONObject jsonObject) {
                super.b(c, jsonObject);
            }
        });
    }
    
    @Override
    public void verifyTransaction(final String s, final String s2) {
        this.verifyTransaction(s, s2, null);
    }
    
    @Override
    public void verifyTransaction(final String s, final String s2, final ReceiptVerificationListener receiptVerificationListener) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                if (n.this.c == null) {
                    n.this.c = new com.kongregate.o.h.a(n.this.b.getApplicationContext());
                }
                n.this.c.a(s, s2, new ReceiptVerificationListener() {
                    @Override
                    public void receiptVerificationComplete(final boolean b) {
                        if (receiptVerificationListener != null) {
                            receiptVerificationListener.receiptVerificationComplete(b);
                        }
                        n.this.b.a("KONG_API_EVENT_RECEIPT_VERIFICATION_COMPLETED");
                    }
                });
            }
        });
    }
}
