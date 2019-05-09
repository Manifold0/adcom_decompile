// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.utils;

import java.io.UnsupportedEncodingException;
import org.json.JSONObject;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.Cipher;
import android.util.Base64;
import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;
import android.text.TextUtils;

public class IronSourceAES
{
    private static boolean mDidSendEncryptionFailEventInSession;
    
    static {
        IronSourceAES.mDidSendEncryptionFailEventInSession = false;
    }
    
    public static String decode(String s, final String s2) {
        while (true) {
            synchronized (IronSourceAES.class) {
                if (TextUtils.isEmpty((CharSequence)s)) {
                    s = "";
                }
                else if (TextUtils.isEmpty((CharSequence)s2)) {
                    s = "";
                }
                else {
                    try {
                        final SecretKeySpec key = getKey(s);
                        final byte[] array = new byte[16];
                        Arrays.fill(array, (byte)0);
                        final IvParameterSpec ivParameterSpec = new IvParameterSpec(array);
                        final byte[] decode = Base64.decode(s2, 0);
                        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
                        instance.init(2, key, ivParameterSpec);
                        s = new String(instance.doFinal(decode));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        if (IronSourceAES.mDidSendEncryptionFailEventInSession) {
                            return "";
                        }
                        IronSourceAES.mDidSendEncryptionFailEventInSession = true;
                        final JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
                        try {
                            mediationAdditionalData.put("status", (Object)"false");
                            mediationAdditionalData.put("reason", 1);
                            InterstitialEventsManager.getInstance().sendEventToUrl(new EventData(114, mediationAdditionalData), "https://track.atom-data.io");
                        }
                        catch (JSONException ex2) {
                            ex2.printStackTrace();
                        }
                    }
                }
                return s;
            }
            s = "";
            return s;
        }
    }
    
    public static String encode(String replaceAll, final String s) {
        synchronized (IronSourceAES.class) {
            if (TextUtils.isEmpty((CharSequence)replaceAll)) {
                replaceAll = "";
            }
            else if (TextUtils.isEmpty((CharSequence)s)) {
                replaceAll = "";
            }
            else {
                try {
                    final SecretKeySpec key = getKey(replaceAll);
                    final byte[] bytes = s.getBytes("UTF8");
                    final byte[] array = new byte[16];
                    Arrays.fill(array, (byte)0);
                    final IvParameterSpec ivParameterSpec = new IvParameterSpec(array);
                    final Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
                    instance.init(1, key, ivParameterSpec);
                    replaceAll = Base64.encodeToString(instance.doFinal(bytes), 0).replaceAll(System.getProperty("line.separator"), "");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    replaceAll = "";
                }
            }
            return replaceAll;
        }
    }
    
    private static SecretKeySpec getKey(final String s) throws UnsupportedEncodingException {
        final byte[] array = new byte[32];
        Arrays.fill(array, (byte)0);
        final byte[] bytes = s.getBytes("UTF-8");
        int n;
        if (bytes.length < array.length) {
            n = bytes.length;
        }
        else {
            n = array.length;
        }
        System.arraycopy(bytes, 0, array, 0, n);
        return new SecretKeySpec(array, "AES");
    }
}
