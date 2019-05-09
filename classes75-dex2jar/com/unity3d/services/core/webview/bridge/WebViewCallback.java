// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.webview.bridge;

import com.unity3d.services.core.log.DeviceLog;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class WebViewCallback implements Parcelable
{
    public static final Parcelable$Creator<WebViewCallback> CREATOR;
    private String _callbackId;
    private int _invocationId;
    private boolean _invoked;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<WebViewCallback>() {
            public WebViewCallback createFromParcel(final Parcel parcel) {
                return new WebViewCallback(parcel);
            }
            
            public WebViewCallback[] newArray(final int n) {
                return new WebViewCallback[n];
            }
        };
    }
    
    public WebViewCallback(final Parcel parcel) {
        this._callbackId = parcel.readString();
        this._invoked = (parcel.readByte() != 0);
        this._invocationId = parcel.readInt();
    }
    
    public WebViewCallback(final String callbackId, final int invocationId) {
        this._callbackId = callbackId;
        this._invocationId = invocationId;
    }
    
    private void invoke(final CallbackStatus callbackStatus, final Enum enum1, final Object... array) {
        if (this._invoked || this._callbackId == null || this._callbackId.length() == 0) {
            return;
        }
        this._invoked = true;
        final ArrayList<String> list = new ArrayList<String>();
        list.addAll((Collection<? extends String>)Arrays.asList(array));
        list.add(0, this._callbackId);
        final Invocation invocationById = Invocation.getInvocationById(this._invocationId);
        if (invocationById == null) {
            DeviceLog.error("Couldn't get batch with id: " + this.getInvocationId());
            return;
        }
        invocationById.setInvocationResponse(callbackStatus, enum1, list.toArray());
    }
    
    public int describeContents() {
        return 45678;
    }
    
    public void error(final Enum enum1, final Object... array) {
        this.invoke(CallbackStatus.ERROR, enum1, array);
    }
    
    public String getCallbackId() {
        return this._callbackId;
    }
    
    public int getInvocationId() {
        return this._invocationId;
    }
    
    public void invoke(final Object... array) {
        this.invoke(CallbackStatus.OK, null, array);
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        parcel.writeString(this._callbackId);
        if (this._invoked) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeByte((byte)n);
        parcel.writeInt(this._invocationId);
    }
}
