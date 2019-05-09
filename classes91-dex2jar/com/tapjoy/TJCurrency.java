// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.content.SharedPreferences$Editor;
import java.util.Map;
import java.util.UUID;
import com.tapjoy.internal.eu;
import org.w3c.dom.Document;
import android.content.Context;

public class TJCurrency
{
    private static TJGetCurrencyBalanceListener d;
    private static TJSpendCurrencyListener e;
    private static TJAwardCurrencyListener f;
    private static TJEarnedCurrencyListener g;
    String a;
    int b;
    Context c;
    
    public TJCurrency(final Context c) {
        this.a = null;
        this.b = 0;
        this.c = c;
    }
    
    private void a(final TapjoyHttpURLResponse tapjoyHttpURLResponse) {
        while (true) {
            while (true) {
                Label_0270: {
                    Label_0250: {
                        Label_0230: {
                            synchronized (this) {
                                if (tapjoyHttpURLResponse.response == null) {
                                    break Label_0270;
                                }
                                final Document buildDocument = TapjoyUtil.buildDocument(tapjoyHttpURLResponse.response);
                                if (buildDocument == null) {
                                    break Label_0206;
                                }
                                final String nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Success"));
                                if (nodeTrimValue == null || !nodeTrimValue.equals("true")) {
                                    break Label_0250;
                                }
                                final String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("TapPoints"));
                                final String nodeTrimValue3 = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("CurrencyName"));
                                if (nodeTrimValue2 == null || nodeTrimValue3 == null) {
                                    break Label_0230;
                                }
                                try {
                                    final int int1 = Integer.parseInt(nodeTrimValue2);
                                    final int localCurrencyBalance = this.getLocalCurrencyBalance();
                                    if (TJCurrency.g != null && localCurrencyBalance != -9999 && int1 > localCurrencyBalance) {
                                        TapjoyLog.i("TJCurrency", "earned: " + (int1 - localCurrencyBalance));
                                        TJCurrency.g.onEarnedCurrency(nodeTrimValue3, int1 - localCurrencyBalance);
                                    }
                                    this.saveCurrencyBalance(int1);
                                    if (TJCurrency.d != null) {
                                        TJCurrency.d.onGetCurrencyBalanceResponse(nodeTrimValue3, int1);
                                    }
                                    return;
                                }
                                catch (Exception ex) {
                                    TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "Error parsing XML and calling listener: " + ex.toString()));
                                }
                                if (TJCurrency.d != null) {
                                    TJCurrency.d.onGetCurrencyBalanceResponseFailure("Failed to get currency balance");
                                }
                                return;
                            }
                        }
                        TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "getCurrencyBalance response is invalid -- missing tags."));
                        continue;
                    }
                    TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "getCurrencyBalance response is invalid -- missing <Success> tag."));
                    continue;
                }
                TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "getCurrencyBalance response is NULL"));
                continue;
            }
        }
    }
    
    private void b(final TapjoyHttpURLResponse tapjoyHttpURLResponse) {
        while (true) {
            // monitorenter(this)
            String nodeTrimValue = "Failed to spend currency";
            while (true) {
                Label_0246: {
                    Object o = null;
                    Label_0162: {
                        try {
                            if (tapjoyHttpURLResponse.response == null) {
                                break Label_0246;
                            }
                            o = TapjoyUtil.buildDocument(tapjoyHttpURLResponse.response);
                            String s = nodeTrimValue;
                            if (o == null) {
                                break Label_0139;
                            }
                            final String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(((Document)o).getElementsByTagName("Success"));
                            if (nodeTrimValue2 == null || !nodeTrimValue2.equals("true")) {
                                break Label_0162;
                            }
                            final String nodeTrimValue3 = TapjoyUtil.getNodeTrimValue(((Document)o).getElementsByTagName("TapPoints"));
                            o = TapjoyUtil.getNodeTrimValue(((Document)o).getElementsByTagName("CurrencyName"));
                            if (nodeTrimValue3 == null || o == null) {
                                TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "spendCurrency response is invalid -- missing tags."));
                                s = nodeTrimValue;
                                break Label_0139;
                            }
                            final int int1 = Integer.parseInt(nodeTrimValue3);
                            this.saveCurrencyBalance(int1);
                            if (TJCurrency.e != null) {
                                TJCurrency.e.onSpendCurrencyResponse((String)o, int1);
                            }
                            return;
                            if (TJCurrency.e != null) {
                                TJCurrency.e.onSpendCurrencyResponseFailure(s);
                            }
                            return;
                        }
                        finally {
                        }
                        // monitorexit(this)
                    }
                    final String s2;
                    if (s2 == null || !s2.endsWith("false")) {
                        TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "spendCurrency response is invalid -- missing <Success> tag."));
                        final String s = nodeTrimValue;
                        continue;
                    }
                    nodeTrimValue = TapjoyUtil.getNodeTrimValue(((Document)o).getElementsByTagName("Message"));
                    TapjoyLog.i("TJCurrency", nodeTrimValue);
                    String s = nodeTrimValue;
                    if ("BalanceTooLowError".equals(TapjoyUtil.getNodeTrimValue(((Document)o).getElementsByTagName("MessageCode")))) {
                        eu.a();
                        s = nodeTrimValue;
                        continue;
                    }
                    continue;
                }
                TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "spendCurrency response is NULL"));
                String s = nodeTrimValue;
                continue;
            }
        }
    }
    
    private void c(final TapjoyHttpURLResponse tapjoyHttpURLResponse) {
        while (true) {
            // monitorenter(this)
            final String s = "Failed to award currency";
            while (true) {
                Label_0219: {
                    Object o = null;
                    Label_0162: {
                        try {
                            if (tapjoyHttpURLResponse.response == null) {
                                break Label_0219;
                            }
                            o = TapjoyUtil.buildDocument(tapjoyHttpURLResponse.response);
                            String nodeTrimValue = s;
                            if (o == null) {
                                break Label_0139;
                            }
                            final String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(((Document)o).getElementsByTagName("Success"));
                            if (nodeTrimValue2 == null || !nodeTrimValue2.equals("true")) {
                                break Label_0162;
                            }
                            final String nodeTrimValue3 = TapjoyUtil.getNodeTrimValue(((Document)o).getElementsByTagName("TapPoints"));
                            o = TapjoyUtil.getNodeTrimValue(((Document)o).getElementsByTagName("CurrencyName"));
                            if (nodeTrimValue3 == null || o == null) {
                                TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "awardCurrency response is invalid -- missing tags."));
                                nodeTrimValue = s;
                                break Label_0139;
                            }
                            final int int1 = Integer.parseInt(nodeTrimValue3);
                            this.saveCurrencyBalance(int1);
                            if (TJCurrency.f != null) {
                                TJCurrency.f.onAwardCurrencyResponse((String)o, int1);
                            }
                            return;
                            if (TJCurrency.f != null) {
                                TJCurrency.f.onAwardCurrencyResponseFailure(nodeTrimValue);
                            }
                            return;
                        }
                        finally {
                        }
                        // monitorexit(this)
                    }
                    final String s2;
                    if (s2 != null && s2.endsWith("false")) {
                        final String nodeTrimValue = TapjoyUtil.getNodeTrimValue(((Document)o).getElementsByTagName("Message"));
                        TapjoyLog.i("TJCurrency", nodeTrimValue);
                        continue;
                    }
                    TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "awardCurrency response is invalid -- missing <Success> tag."));
                    String nodeTrimValue = s;
                    continue;
                }
                TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SERVER_ERROR, "awardCurrency response is NULL"));
                String nodeTrimValue = s;
                continue;
            }
        }
    }
    
    public void awardCurrency(final int b, final TJAwardCurrencyListener f) {
        if (b < 0) {
            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Amount must be a positive number for the awardCurrency API"));
            return;
        }
        this.b = b;
        TJCurrency.f = f;
        final String string = UUID.randomUUID().toString();
        final long n = System.currentTimeMillis() / 1000L;
        final Map genericURLParams = TapjoyConnectCore.getGenericURLParams();
        TapjoyUtil.safePut(genericURLParams, "tap_points", String.valueOf(this.b), true);
        TapjoyUtil.safePut(genericURLParams, "guid", string, true);
        TapjoyUtil.safePut(genericURLParams, "timestamp", String.valueOf(n), true);
        TapjoyUtil.safePut(genericURLParams, "verifier", TapjoyConnectCore.getAwardCurrencyVerifier(n, this.b, string), true);
        new Thread(new Runnable() {
            @Override
            public final void run() {
                TJCurrency.this.c(new TapjoyURLConnection().getResponseFromURL(TapjoyConnectCore.getHostURL() + "points/award?", genericURLParams));
            }
        }).start();
    }
    
    public void getCurrencyBalance(final TJGetCurrencyBalanceListener d) {
        TJCurrency.d = d;
        new Thread(new Runnable() {
            final /* synthetic */ Map a = TapjoyConnectCore.getURLParams();
            
            @Override
            public final void run() {
                TJCurrency.this.a(new TapjoyURLConnection().getResponseFromURL(TapjoyConnectCore.getHostURL() + "get_vg_store_items/user_account?", this.a));
            }
        }).start();
    }
    
    public int getLocalCurrencyBalance() {
        return this.c.getSharedPreferences("tjcPrefrences", 0).getInt("last_currency_balance", -9999);
    }
    
    public void saveCurrencyBalance(final int n) {
        final SharedPreferences$Editor edit = this.c.getSharedPreferences("tjcPrefrences", 0).edit();
        edit.putInt("last_currency_balance", n);
        edit.commit();
    }
    
    public void setEarnedCurrencyListener(final TJEarnedCurrencyListener g) {
        TJCurrency.g = g;
    }
    
    public void spendCurrency(final int n, final TJSpendCurrencyListener e) {
        if (n < 0) {
            TapjoyLog.e("TJCurrency", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Amount must be a positive number for the spendCurrency API"));
            return;
        }
        this.a = String.valueOf(n);
        TJCurrency.e = e;
        final Map urlParams = TapjoyConnectCore.getURLParams();
        TapjoyUtil.safePut(urlParams, "tap_points", this.a, true);
        new Thread(new Runnable() {
            @Override
            public final void run() {
                TJCurrency.this.b(new TapjoyURLConnection().getResponseFromURL(TapjoyConnectCore.getHostURL() + "points/spend?", urlParams));
            }
        }).start();
    }
}
