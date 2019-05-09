// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.h;

import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.api.KongregateAPI;
import java.util.Map;
import com.kongregate.o.g.c;
import java.util.HashMap;
import com.kongregate.android.internal.util.i;
import org.json.JSONException;
import com.kongregate.android.internal.util.j;
import com.kongregate.o.g.b;
import org.json.JSONObject;
import com.kongregate.o.c.d;
import com.kongregate.android.api.MicrotransactionServices;
import android.content.Context;
import android.content.SharedPreferences;

public class a
{
    private final SharedPreferences a;
    
    public a(final Context context) {
        this.a = context.getSharedPreferences("kongregate_purchases", 0);
    }
    
    private void a(final a a, final MicrotransactionServices.ReceiptVerificationListener receiptVerificationListener) {
        if (a != null) {
            this.a(a);
        }
        if (receiptVerificationListener != null) {
            d.b(new Runnable() {
                @Override
                public void run() {
                    receiptVerificationListener.receiptVerificationComplete(a.b == MicrotransactionServices.ReceiptVerificationStatus.VALID);
                }
            });
        }
    }
    
    private boolean a(final String s, final JSONObject jsonObject) {
        return com.kongregate.android.internal.util.d.a(Long.toString(this.b().getApplicationId()) + jsonObject.optString("valid") + s, jsonObject.optString("signature"));
    }
    
    public MicrotransactionServices.ReceiptVerificationStatus a(final String s) {
        return this.c(s).b;
    }
    
    b a() {
        return b.a();
    }
    
    void a(final a a) {
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("order_id", (Object)a.a);
                jsonObject.put("result", (Object)a.b);
                jsonObject.put("product_id", (Object)a.c);
                jsonObject.put("fail_reason", (Object)a.d);
                this.a.edit().putString(a.a, jsonObject.toString()).apply();
            }
            catch (JSONException ex) {
                j.c("problem writting verification state to json", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public void a(final String s, final String s2, final MicrotransactionServices.ReceiptVerificationListener receiptVerificationListener) {
        final JSONObject c = i.c(s);
        if (c == null) {
            j.c("IAP FLOW STEP: verifyTransaction() - not json: " + s);
            this.a(com.kongregate.o.h.a.a.e, receiptVerificationListener);
            return;
        }
        final String c2 = i.c(c, "productId");
        final String c3 = i.c(c, "orderId");
        if (c2 == null || c3 == null) {
            j.c("IAP FLOW STEP: verifyTransaction() - missing orderId[" + c3 + "] and/or ProductId[" + c2 + "]");
            this.a(com.kongregate.o.h.a.a.e, receiptVerificationListener);
            return;
        }
        j.b("IAP FLOW STEP: verifyTransaction() - " + c2);
        final a c4 = this.c(c3);
        c4.a = c3;
        c4.c = c2;
        c4.b = MicrotransactionServices.ReceiptVerificationStatus.PROCESSING;
        c4.d = null;
        this.a(c4);
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("signature", s2);
        hashMap.put("receipt", s);
        hashMap.put("game_id", Long.toString(this.b().getApplicationId()));
        this.a().a(com.kongregate.o.g.a.a("/mobile_receipt/validate_google", true).toString(), hashMap, (b.a)new b.a() {
            @Override
            public void a(final c c) {
                j.a("validate iap: http errror: " + c.e());
                super.a(c);
                c4.b = MicrotransactionServices.ReceiptVerificationStatus.VALID;
                c4.d = "connection_error";
                com.kongregate.o.h.a.this.a(c4, receiptVerificationListener);
            }
            
            @Override
            public void a(final c c, final JSONObject jsonObject) {
                final boolean optBoolean = jsonObject.optBoolean("valid", true);
                if (!com.kongregate.o.h.a.this.a(c4.a, jsonObject)) {
                    j.a("validate iap: invalid response");
                    c4.b = MicrotransactionServices.ReceiptVerificationStatus.INVALID;
                    c4.d = "response_signature_invalid";
                }
                else if (!optBoolean) {
                    j.a("validate iap: invalid receipt");
                    c4.b = MicrotransactionServices.ReceiptVerificationStatus.INVALID;
                    c4.d = jsonObject.optString("reason", "receipt_signature_invalid");
                }
                else {
                    j.a("validate iap: valid");
                    c4.b = MicrotransactionServices.ReceiptVerificationStatus.VALID;
                    c4.d = jsonObject.optString("reason", "receipt_valid");
                }
                com.kongregate.o.h.a.this.a(c4, receiptVerificationListener);
            }
            
            @Override
            public void b(final c c, final JSONObject jsonObject) {
                j.a("validate iap: request error: " + c.e());
                super.b(c, jsonObject);
                c4.b = MicrotransactionServices.ReceiptVerificationStatus.VALID;
                c4.d = "server_error";
                com.kongregate.o.h.a.this.a(c4, receiptVerificationListener);
            }
        });
    }
    
    KongregateAPI b() {
        return APIBootstrap.getInstance();
    }
    
    public String b(final String s) {
        if (StringUtils.a((CharSequence)s)) {
            return null;
        }
        final a c = this.c(s);
        this.a.edit().remove(s).apply();
        return c.d;
    }
    
    public a c(final String a) {
        final a a2 = new a();
        final JSONObject c = i.c(this.a.getString(a, (String)null));
        if (c != null) {
            a2.a = a;
            a2.b = MicrotransactionServices.ReceiptVerificationStatus.valueOf(c.optString("result"));
            a2.c = c.optString("product_id");
            a2.d = c.optString("fail_reason");
            return a2;
        }
        j.c("Purchase state not found for purchaseToken");
        a2.b = MicrotransactionServices.ReceiptVerificationStatus.UNKNOWN;
        return a2;
    }
    
    static class a
    {
        static final a e;
        String a;
        MicrotransactionServices.ReceiptVerificationStatus b;
        String c;
        String d;
        
        static {
            e = new a(MicrotransactionServices.ReceiptVerificationStatus.INVALID, "receipt_json_invalid");
        }
        
        a() {
        }
        
        a(final MicrotransactionServices.ReceiptVerificationStatus b, final String d) {
            this.b = b;
            this.d = d;
        }
    }
}
