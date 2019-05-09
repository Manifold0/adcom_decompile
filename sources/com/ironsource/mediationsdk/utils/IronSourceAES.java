package com.ironsource.mediationsdk.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

public class IronSourceAES {
    private static boolean mDidSendEncryptionFailEventInSession = false;

    public static synchronized String encode(String keyString, String stringToEncode) {
        String str;
        synchronized (IronSourceAES.class) {
            if (TextUtils.isEmpty(keyString)) {
                str = "";
            } else if (TextUtils.isEmpty(stringToEncode)) {
                str = "";
            } else {
                try {
                    SecretKeySpec skeySpec = getKey(keyString);
                    byte[] clearText = stringToEncode.getBytes("UTF8");
                    byte[] iv = new byte[16];
                    Arrays.fill(iv, (byte) 0);
                    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                    cipher.init(1, skeySpec, ivParameterSpec);
                    str = Base64.encodeToString(cipher.doFinal(clearText), 0).replaceAll(System.getProperty("line.separator"), "");
                } catch (Exception e) {
                    e.printStackTrace();
                    str = "";
                }
            }
        }
        return str;
    }

    public static synchronized String decode(String keyString, String stringToDecode) {
        String str;
        synchronized (IronSourceAES.class) {
            if (TextUtils.isEmpty(keyString)) {
                str = "";
            } else if (TextUtils.isEmpty(stringToDecode)) {
                str = "";
            } else {
                try {
                    SecretKey key = getKey(keyString);
                    byte[] iv = new byte[16];
                    Arrays.fill(iv, (byte) 0);
                    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
                    byte[] encrypedPwdBytes = Base64.decode(stringToDecode, 0);
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                    cipher.init(2, key, ivParameterSpec);
                    str = new String(cipher.doFinal(encrypedPwdBytes));
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!mDidSendEncryptionFailEventInSession) {
                        mDidSendEncryptionFailEventInSession = true;
                        JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
                        try {
                            data.put("status", "false");
                            data.put("reason", 1);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        InterstitialEventsManager.getInstance().sendEventToUrl(new EventData(114, data), "https://track.atom-data.io");
                    }
                    str = "";
                }
            }
        }
        return str;
    }

    private static SecretKeySpec getKey(String key) throws UnsupportedEncodingException {
        byte[] keyBytes = new byte[32];
        Arrays.fill(keyBytes, (byte) 0);
        byte[] passwordBytes = key.getBytes("UTF-8");
        System.arraycopy(passwordBytes, 0, keyBytes, 0, passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length);
        return new SecretKeySpec(keyBytes, "AES");
    }
}
