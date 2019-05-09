// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents.internal;

import com.facebook.internal.Utility;
import android.os.IBinder;
import java.lang.reflect.InvocationTargetException;
import android.util.Log;
import java.util.ArrayList;
import android.os.Bundle;
import android.content.Context;
import java.lang.reflect.Method;
import java.util.HashMap;

public class InAppPurchaseEventManager
{
    private static final String DETAILS_LIST = "DETAILS_LIST";
    private static final String GET_INTERFACE_METHOD = "iap_get_interface";
    private static final String GET_SKU_DETAILS_METHOD = "iap_get_sku_details";
    private static final String IN_APP_BILLING_SERVICE = "com.android.vending.billing.IInAppBillingService";
    private static final String IN_APP_BILLING_SERVICE_STUB = "com.android.vending.billing.IInAppBillingService$Stub";
    private static final String ITEM_ID_LIST = "ITEM_ID_LIST";
    private static final String RESPONSE_CODE = "RESPONSE_CODE";
    private static final String TAG;
    private static final HashMap<String, Class<?>> classMap;
    private static final HashMap<String, Method> methodMap;
    
    static {
        methodMap = new HashMap<String, Method>();
        classMap = new HashMap<String, Class<?>>();
        TAG = InAppPurchaseEventManager.class.getCanonicalName();
    }
    
    public static String getPurchaseDetails(final Context context, final String s, Object cast, final boolean b) {
        if (cast == null || s == "") {
            return "";
        }
        String s3;
        while (true) {
            while (true) {
                Label_0306: {
                    try {
                        Method declaredMethod = InAppPurchaseEventManager.methodMap.get("iap_get_sku_details");
                        final Class<?> clazz = InAppPurchaseEventManager.classMap.get("com.android.vending.billing.IInAppBillingService");
                        Class<?> loadClass;
                        if (declaredMethod == null || (loadClass = clazz) == null) {
                            loadClass = context.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
                            declaredMethod = loadClass.getDeclaredMethod("getSkuDetails", Integer.TYPE, String.class, String.class, Bundle.class);
                            InAppPurchaseEventManager.methodMap.put("iap_get_sku_details", declaredMethod);
                            InAppPurchaseEventManager.classMap.put("com.android.vending.billing.IInAppBillingService", loadClass);
                        }
                        final ArrayList<String> list = new ArrayList<String>();
                        list.add(s);
                        final Bundle bundle = new Bundle();
                        bundle.putStringArrayList("ITEM_ID_LIST", (ArrayList)list);
                        cast = loadClass.cast(cast);
                        final String packageName = context.getPackageName();
                        if (!b) {
                            break Label_0306;
                        }
                        final String s2 = "subs";
                        final Bundle bundle2 = (Bundle)declaredMethod.invoke(cast, 3, packageName, s2, bundle);
                        if (bundle2.getInt("RESPONSE_CODE") != 0) {
                            goto Label_0259;
                        }
                        final ArrayList stringArrayList = bundle2.getStringArrayList("DETAILS_LIST");
                        if (stringArrayList.size() < 1) {
                            s3 = "";
                        }
                        else {
                            s3 = stringArrayList.get(0);
                        }
                    }
                    catch (ClassNotFoundException ex) {
                        Log.e(InAppPurchaseEventManager.TAG, "com.android.vending.billing.IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to the project, and import the IInAppBillingService.aidl file into this package", (Throwable)ex);
                    }
                    catch (NoSuchMethodException ex2) {
                        Log.e(InAppPurchaseEventManager.TAG, "com.android.vending.billing.IInAppBillingService.getSkuDetails method is not available", (Throwable)ex2);
                        goto Label_0259;
                    }
                    catch (InvocationTargetException ex3) {
                        Log.e(InAppPurchaseEventManager.TAG, "Invocation target exception in com.android.vending.billing.IInAppBillingService.getSkuDetails", (Throwable)ex3);
                        goto Label_0259;
                    }
                    catch (IllegalAccessException ex4) {
                        Log.e(InAppPurchaseEventManager.TAG, "Illegal access to method com.android.vending.billing.IInAppBillingService.getSkuDetails", (Throwable)ex4);
                        goto Label_0259;
                    }
                    break;
                }
                final String s2 = "inapp";
                continue;
            }
        }
        return s3;
    }
    
    public static Object getServiceInterface(final Context context, final IBinder binder) {
        try {
            Method declaredMethod;
            if ((declaredMethod = InAppPurchaseEventManager.methodMap.get("iap_get_interface")) == null) {
                declaredMethod = context.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", IBinder.class);
                InAppPurchaseEventManager.methodMap.put("iap_get_interface", declaredMethod);
            }
            Utility.logd(InAppPurchaseEventManager.TAG, "In-app billing service connected");
            return declaredMethod.invoke(null, binder);
        }
        catch (ClassNotFoundException ex) {
            Log.e(InAppPurchaseEventManager.TAG, "com.android.vending.billing.IInAppBillingService$Stub is not available, please add com.android.vending.billing.IInAppBillingService to the project.", (Throwable)ex);
            return null;
        }
        catch (NoSuchMethodException ex2) {
            Log.e(InAppPurchaseEventManager.TAG, "com.android.vending.billing.IInAppBillingService$Stub.asInterface method not found", (Throwable)ex2);
            return null;
        }
        catch (IllegalAccessException ex3) {
            Log.e(InAppPurchaseEventManager.TAG, "Illegal access to method com.android.vending.billing.IInAppBillingService$Stub.asInterface", (Throwable)ex3);
            return null;
        }
        catch (InvocationTargetException ex4) {
            Log.e(InAppPurchaseEventManager.TAG, "Invocation target exception in com.android.vending.billing.IInAppBillingService$Stub.asInterface", (Throwable)ex4);
            return null;
        }
    }
}
