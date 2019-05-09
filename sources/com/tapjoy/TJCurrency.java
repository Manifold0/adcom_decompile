package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.eu;
import java.util.Map;
import java.util.UUID;
import org.w3c.dom.Document;

public class TJCurrency {
    /* renamed from: d */
    private static TJGetCurrencyBalanceListener f6943d;
    /* renamed from: e */
    private static TJSpendCurrencyListener f6944e;
    /* renamed from: f */
    private static TJAwardCurrencyListener f6945f;
    /* renamed from: g */
    private static TJEarnedCurrencyListener f6946g;
    /* renamed from: a */
    String f6947a = null;
    /* renamed from: b */
    int f6948b = 0;
    /* renamed from: c */
    Context f6949c;

    public TJCurrency(Context applicationContext) {
        this.f6949c = applicationContext;
    }

    public void saveCurrencyBalance(int balance) {
        Editor edit = this.f6949c.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        edit.putInt(TapjoyConstants.PREF_LAST_CURRENCY_BALANCE, balance);
        edit.commit();
    }

    public int getLocalCurrencyBalance() {
        return this.f6949c.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).getInt(TapjoyConstants.PREF_LAST_CURRENCY_BALANCE, -9999);
    }

    public void getCurrencyBalance(TJGetCurrencyBalanceListener listener) {
        f6943d = listener;
        final Map uRLParams = TapjoyConnectCore.getURLParams();
        new Thread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ TJCurrency f6938b;

            public final void run() {
                this.f6938b.m7069a(new TapjoyURLConnection().getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_GET_CURRENCY_BALANCE_URL_PATH, uRLParams));
            }
        }).start();
    }

    public void spendCurrency(int amount, TJSpendCurrencyListener listener) {
        if (amount < 0) {
            TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Amount must be a positive number for the spendCurrency API"));
            return;
        }
        this.f6947a = String.valueOf(amount);
        f6944e = listener;
        final Map uRLParams = TapjoyConnectCore.getURLParams();
        TapjoyUtil.safePut(uRLParams, TapjoyConstants.TJC_CURRENCY, this.f6947a, true);
        new Thread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ TJCurrency f6940b;

            public final void run() {
                this.f6940b.m7071b(new TapjoyURLConnection().getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_SPEND_CURRENCY_URL_PATH, uRLParams));
            }
        }).start();
    }

    public void awardCurrency(int amount, TJAwardCurrencyListener listener) {
        if (amount < 0) {
            TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Amount must be a positive number for the awardCurrency API"));
            return;
        }
        this.f6948b = amount;
        f6945f = listener;
        String uuid = UUID.randomUUID().toString();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        final Map genericURLParams = TapjoyConnectCore.getGenericURLParams();
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_CURRENCY, String.valueOf(this.f6948b), true);
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_GUID, uuid, true);
        TapjoyUtil.safePut(genericURLParams, "timestamp", String.valueOf(currentTimeMillis), true);
        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_VERIFIER, TapjoyConnectCore.getAwardCurrencyVerifier(currentTimeMillis, this.f6948b, uuid), true);
        new Thread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ TJCurrency f6942b;

            public final void run() {
                this.f6942b.m7073c(new TapjoyURLConnection().getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_AWARD_CURRENCY_URL_PATH, genericURLParams));
            }
        }).start();
    }

    public void setEarnedCurrencyListener(TJEarnedCurrencyListener listener) {
        f6946g = listener;
    }

    /* renamed from: a */
    private synchronized void m7069a(TapjoyHttpURLResponse tapjoyHttpURLResponse) {
        if (tapjoyHttpURLResponse.response != null) {
            Document buildDocument = TapjoyUtil.buildDocument(tapjoyHttpURLResponse.response);
            if (buildDocument != null) {
                String nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Success"));
                if (nodeTrimValue == null || !nodeTrimValue.equals("true")) {
                    TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "getCurrencyBalance response is invalid -- missing <Success> tag."));
                } else {
                    nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("TapPoints"));
                    String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("CurrencyName"));
                    if (nodeTrimValue == null || nodeTrimValue2 == null) {
                        TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "getCurrencyBalance response is invalid -- missing tags."));
                    } else {
                        try {
                            int parseInt = Integer.parseInt(nodeTrimValue);
                            int localCurrencyBalance = getLocalCurrencyBalance();
                            if (!(f6946g == null || localCurrencyBalance == -9999 || parseInt <= localCurrencyBalance)) {
                                TapjoyLog.m7129i("TJCurrency", "earned: " + (parseInt - localCurrencyBalance));
                                f6946g.onEarnedCurrency(nodeTrimValue2, parseInt - localCurrencyBalance);
                            }
                            saveCurrencyBalance(parseInt);
                            if (f6943d != null) {
                                f6943d.onGetCurrencyBalanceResponse(nodeTrimValue2, parseInt);
                            }
                        } catch (Exception e) {
                            TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "Error parsing XML and calling listener: " + e.toString()));
                        }
                    }
                }
            }
            if (f6943d != null) {
                f6943d.onGetCurrencyBalanceResponseFailure("Failed to get currency balance");
            }
        } else {
            TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "getCurrencyBalance response is NULL"));
            if (f6943d != null) {
                f6943d.onGetCurrencyBalanceResponseFailure("Failed to get currency balance");
            }
        }
    }

    /* renamed from: b */
    private synchronized void m7071b(TapjoyHttpURLResponse tapjoyHttpURLResponse) {
        String str = "Failed to spend currency";
        if (tapjoyHttpURLResponse.response != null) {
            Document buildDocument = TapjoyUtil.buildDocument(tapjoyHttpURLResponse.response);
            if (buildDocument != null) {
                String nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Success"));
                if (nodeTrimValue == null || !nodeTrimValue.equals("true")) {
                    if (nodeTrimValue != null) {
                        if (nodeTrimValue.endsWith("false")) {
                            str = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Message"));
                            TapjoyLog.m7129i("TJCurrency", str);
                            if ("BalanceTooLowError".equals(TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("MessageCode")))) {
                                eu.m7688a();
                            }
                        }
                    }
                    TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "spendCurrency response is invalid -- missing <Success> tag."));
                } else {
                    nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("TapPoints"));
                    String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("CurrencyName"));
                    if (nodeTrimValue == null || nodeTrimValue2 == null) {
                        TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "spendCurrency response is invalid -- missing tags."));
                    } else {
                        int parseInt = Integer.parseInt(nodeTrimValue);
                        saveCurrencyBalance(parseInt);
                        if (f6944e != null) {
                            f6944e.onSpendCurrencyResponse(nodeTrimValue2, parseInt);
                        }
                    }
                }
            }
            if (f6944e != null) {
                f6944e.onSpendCurrencyResponseFailure(str);
            }
        } else {
            TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "spendCurrency response is NULL"));
            if (f6944e != null) {
                f6944e.onSpendCurrencyResponseFailure(str);
            }
        }
    }

    /* renamed from: c */
    private synchronized void m7073c(TapjoyHttpURLResponse tapjoyHttpURLResponse) {
        String str = "Failed to award currency";
        if (tapjoyHttpURLResponse.response != null) {
            Document buildDocument = TapjoyUtil.buildDocument(tapjoyHttpURLResponse.response);
            if (buildDocument != null) {
                String nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Success"));
                if (nodeTrimValue == null || !nodeTrimValue.equals("true")) {
                    if (nodeTrimValue != null) {
                        if (nodeTrimValue.endsWith("false")) {
                            str = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Message"));
                            TapjoyLog.m7129i("TJCurrency", str);
                        }
                    }
                    TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "awardCurrency response is invalid -- missing <Success> tag."));
                } else {
                    nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("TapPoints"));
                    String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("CurrencyName"));
                    if (nodeTrimValue == null || nodeTrimValue2 == null) {
                        TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "awardCurrency response is invalid -- missing tags."));
                    } else {
                        int parseInt = Integer.parseInt(nodeTrimValue);
                        saveCurrencyBalance(parseInt);
                        if (f6945f != null) {
                            f6945f.onAwardCurrencyResponse(nodeTrimValue2, parseInt);
                        }
                    }
                }
            }
            if (f6945f != null) {
                f6945f.onAwardCurrencyResponseFailure(str);
            }
        } else {
            TapjoyLog.m7127e("TJCurrency", new TapjoyErrorMessage(ErrorType.SERVER_ERROR, "awardCurrency response is NULL"));
            if (f6945f != null) {
                f6945f.onAwardCurrencyResponseFailure(str);
            }
        }
    }
}
