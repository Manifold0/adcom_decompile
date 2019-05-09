// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.content.res.Resources$NotFoundException;
import android.util.Log;
import android.util.TypedValue;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ViewUtils
{
    private ViewUtils() {
    }
    
    @KeepForSdk
    public static String getXmlAttributeString(String attributeValue, final String s, final Context context, AttributeSet string, final boolean b, final boolean b2, final String s2) {
    Label_0123:
        while (true) {
            while (true) {
                Label_0006: {
                    if (string == null) {
                        attributeValue = null;
                        break Label_0006;
                    }
                    Label_0174: {
                        break Label_0174;
                        while (true) {
                            final String substring = attributeValue.substring(8);
                            final String packageName = context.getPackageName();
                            string = (AttributeSet)new TypedValue();
                        Label_0242:
                            while (true) {
                                try {
                                    context.getResources().getValue(new StringBuilder(String.valueOf(packageName).length() + 8 + String.valueOf(substring).length()).append(packageName).append(":string/").append(substring).toString(), (TypedValue)string, true);
                                    if (((TypedValue)string).string != null) {
                                        string = (AttributeSet)((TypedValue)string).string.toString();
                                        if (b2 && string == null) {
                                            Log.w(s2, new StringBuilder(String.valueOf(s).length() + 33).append("Required XML attribute \"").append(s).append("\" missing").toString());
                                        }
                                        return (String)string;
                                    }
                                    break Label_0242;
                                    attributeValue = string.getAttributeValue(attributeValue, s);
                                    break;
                                }
                                catch (Resources$NotFoundException ex) {
                                    Log.w(s2, new StringBuilder(String.valueOf(s).length() + 30 + String.valueOf(attributeValue).length()).append("Could not find resource for ").append(s).append(": ").append(attributeValue).toString());
                                    continue;
                                }
                                break;
                            }
                            final String value = String.valueOf(string);
                            Log.w(s2, new StringBuilder(String.valueOf(s).length() + 28 + String.valueOf(value).length()).append("Resource ").append(s).append(" was not a string: ").append(value).toString());
                            string = (AttributeSet)attributeValue;
                            continue Label_0123;
                        }
                    }
                }
                string = (AttributeSet)attributeValue;
                if (attributeValue == null) {
                    continue Label_0123;
                }
                string = (AttributeSet)attributeValue;
                if (!attributeValue.startsWith("@string/")) {
                    continue Label_0123;
                }
                string = (AttributeSet)attributeValue;
                if (b) {
                    continue;
                }
                break;
            }
            continue Label_0123;
        }
    }
}
