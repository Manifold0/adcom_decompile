// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Intent;
import android.content.ComponentName;
import java.util.Iterator;
import java.util.Collection;
import java.math.BigDecimal;
import java.util.HashMap;
import android.os.IBinder;
import org.json.JSONObject;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.ArrayList;
import android.content.ServiceConnection;
import java.lang.reflect.Method;
import android.content.Context;

class TrackGooglePurchase
{
    private static Class<?> IInAppBillingServiceClass;
    private static int iapEnabled;
    private Context appContext;
    private Method getPurchasesMethod;
    private Method getSkuDetailsMethod;
    private boolean isWaitingForPurchasesRequest;
    private Object mIInAppBillingService;
    private ServiceConnection mServiceConn;
    private boolean newAsExisting;
    private ArrayList<String> purchaseTokens;
    
    static {
        TrackGooglePurchase.iapEnabled = -99;
    }
    
    TrackGooglePurchase(final Context appContext) {
        boolean newAsExisting = true;
        this.newAsExisting = true;
        this.isWaitingForPurchasesRequest = false;
        this.appContext = appContext;
        this.purchaseTokens = new ArrayList<String>();
        while (true) {
            try {
                final JSONArray jsonArray = new JSONArray(OneSignalPrefs.getString("GTPlayerPurchases", "purchaseTokens", "[]"));
                for (int i = 0; i < jsonArray.length(); ++i) {
                    this.purchaseTokens.add(jsonArray.get(i).toString());
                }
                if (jsonArray.length() != 0) {
                    newAsExisting = false;
                }
                this.newAsExisting = newAsExisting;
                if (this.newAsExisting) {
                    this.newAsExisting = OneSignalPrefs.getBool("GTPlayerPurchases", "ExistingPurchases", true);
                }
                this.trackIAP();
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    static boolean CanTrack(final Context context) {
        boolean b = false;
        if (TrackGooglePurchase.iapEnabled == -99) {
            TrackGooglePurchase.iapEnabled = context.checkCallingOrSelfPermission("com.android.vending.BILLING");
        }
        try {
            if (TrackGooglePurchase.iapEnabled == 0) {
                TrackGooglePurchase.IInAppBillingServiceClass = Class.forName("com.android.vending.billing.IInAppBillingService");
            }
            if (TrackGooglePurchase.iapEnabled == 0) {
                b = true;
            }
            return b;
        }
        catch (Throwable t) {
            TrackGooglePurchase.iapEnabled = 0;
            return false;
        }
    }
    
    private void QueryBoughtItems() {
        if (this.isWaitingForPurchasesRequest) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    TrackGooglePurchase.this.isWaitingForPurchasesRequest = true;
                    while (true) {
                        int n = 0;
                        Label_0295: {
                            while (true) {
                                try {
                                    if (TrackGooglePurchase.this.getPurchasesMethod == null) {
                                        TrackGooglePurchase.this.getPurchasesMethod = getGetPurchasesMethod(TrackGooglePurchase.IInAppBillingServiceClass);
                                        TrackGooglePurchase.this.getPurchasesMethod.setAccessible(true);
                                    }
                                    final Bundle bundle = (Bundle)TrackGooglePurchase.this.getPurchasesMethod.invoke(TrackGooglePurchase.this.mIInAppBillingService, 3, TrackGooglePurchase.this.appContext.getPackageName(), "inapp", null);
                                    if (bundle.getInt("RESPONSE_CODE") == 0) {
                                        final ArrayList<String> list = new ArrayList<String>();
                                        final ArrayList<String> list2 = new ArrayList<String>();
                                        final ArrayList stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                                        final ArrayList stringArrayList2 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                                        n = 0;
                                        if (n < stringArrayList2.size()) {
                                            final String s = stringArrayList2.get(n);
                                            final String s2 = stringArrayList.get(n);
                                            final String string = new JSONObject(s).getString("purchaseToken");
                                            if (!TrackGooglePurchase.this.purchaseTokens.contains(string) && !list2.contains(string)) {
                                                list2.add(string);
                                                list.add(s2);
                                            }
                                            break Label_0295;
                                        }
                                        else if (list.size() > 0) {
                                            TrackGooglePurchase.this.sendPurchases(list, list2);
                                        }
                                        else if (stringArrayList2.size() == 0) {
                                            TrackGooglePurchase.this.newAsExisting = false;
                                            OneSignalPrefs.saveBool("GTPlayerPurchases", "ExistingPurchases", false);
                                        }
                                    }
                                    TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                                    return;
                                }
                                catch (Throwable t) {
                                    t.printStackTrace();
                                    continue;
                                }
                                continue;
                            }
                        }
                        ++n;
                        continue;
                    }
                }
            }
        }).start();
    }
    
    private static Method getAsInterfaceMethod(final Class clazz) {
        final Method[] methods = clazz.getMethods();
        for (int length = methods.length, i = 0; i < length; ++i) {
            final Method method = methods[i];
            final Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0] == IBinder.class) {
                return method;
            }
        }
        return null;
    }
    
    private static Method getGetPurchasesMethod(final Class clazz) {
        final Method[] methods = clazz.getMethods();
        for (int length = methods.length, i = 0; i < length; ++i) {
            final Method method = methods[i];
            final Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 4 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class && parameterTypes[2] == String.class && parameterTypes[3] == String.class) {
                return method;
            }
        }
        return null;
    }
    
    private static Method getGetSkuDetailsMethod(final Class clazz) {
        final Method[] methods = clazz.getMethods();
        for (int length = methods.length, i = 0; i < length; ++i) {
            final Method method = methods[i];
            final Class<?>[] parameterTypes = method.getParameterTypes();
            final Class<?> returnType = method.getReturnType();
            if (parameterTypes.length == 4 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class && parameterTypes[2] == String.class && parameterTypes[3] == Bundle.class && returnType == Bundle.class) {
                return method;
            }
        }
        return null;
    }
    
    private void sendPurchases(final ArrayList<String> list, final ArrayList<String> list2) {
        HashMap<String, JSONObject> hashMap = null;
        Label_0263: {
            try {
                if (this.getSkuDetailsMethod == null) {
                    (this.getSkuDetailsMethod = getGetSkuDetailsMethod(TrackGooglePurchase.IInAppBillingServiceClass)).setAccessible(true);
                }
                final Bundle bundle = new Bundle();
                bundle.putStringArrayList("ITEM_ID_LIST", (ArrayList)list);
                final Bundle bundle2 = (Bundle)this.getSkuDetailsMethod.invoke(this.mIInAppBillingService, 3, this.appContext.getPackageName(), "inapp", bundle);
                if (bundle2.getInt("RESPONSE_CODE") == 0) {
                    final ArrayList stringArrayList = bundle2.getStringArrayList("DETAILS_LIST");
                    hashMap = new HashMap<String, JSONObject>();
                    final Iterator<String> iterator = stringArrayList.iterator();
                    while (iterator.hasNext()) {
                        final JSONObject jsonObject = new JSONObject((String)iterator.next());
                        final String string = jsonObject.getString("productId");
                        final BigDecimal divide = new BigDecimal(jsonObject.getString("price_amount_micros")).divide(new BigDecimal(1000000));
                        final JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("sku", (Object)string);
                        jsonObject2.put("iso", (Object)jsonObject.getString("price_currency_code"));
                        jsonObject2.put("amount", (Object)divide.toString());
                        hashMap.put(string, jsonObject2);
                    }
                    break Label_0263;
                }
            }
            catch (Throwable t) {
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed to track IAP purchases", t);
            }
            return;
        }
        final JSONArray jsonArray = new JSONArray();
        for (final String s : list) {
            if (hashMap.containsKey(s)) {
                jsonArray.put(hashMap.get(s));
            }
        }
        if (jsonArray.length() > 0) {
            OneSignal.sendPurchases(jsonArray, this.newAsExisting, new OneSignalRestClient.ResponseHandler() {
                public void onFailure(final int n, final JSONObject jsonObject, final Throwable t) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "HTTP sendPurchases failed to send.", t);
                    TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                }
                
                public void onSuccess(final String s) {
                    TrackGooglePurchase.this.purchaseTokens.addAll(list2);
                    OneSignalPrefs.saveString("GTPlayerPurchases", "purchaseTokens", TrackGooglePurchase.this.purchaseTokens.toString());
                    OneSignalPrefs.saveBool("GTPlayerPurchases", "ExistingPurchases", true);
                    TrackGooglePurchase.this.newAsExisting = false;
                    TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                }
            });
        }
    }
    
    void trackIAP() {
        if (this.mServiceConn == null) {
            this.mServiceConn = (ServiceConnection)new ServiceConnection() {
                public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                    try {
                        final Method access$200 = getAsInterfaceMethod(Class.forName("com.android.vending.billing.IInAppBillingService$Stub"));
                        access$200.setAccessible(true);
                        TrackGooglePurchase.this.mIInAppBillingService = access$200.invoke(null, binder);
                        TrackGooglePurchase.this.QueryBoughtItems();
                    }
                    catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
                
                public void onServiceDisconnected(final ComponentName componentName) {
                    TrackGooglePurchase.iapEnabled = -99;
                    TrackGooglePurchase.this.mIInAppBillingService = null;
                }
            };
            final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            this.appContext.bindService(intent, this.mServiceConn, 1);
        }
        else if (this.mIInAppBillingService != null) {
            this.QueryBoughtItems();
        }
    }
}
