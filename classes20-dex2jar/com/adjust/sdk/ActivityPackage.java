// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.TreeMap;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.io.ObjectStreamField;
import java.io.Serializable;

public class ActivityPackage implements Serializable
{
    private static final ObjectStreamField[] serialPersistentFields;
    private static final long serialVersionUID = -35935556512024097L;
    private ActivityKind activityKind;
    private Map<String, String> callbackParameters;
    private long clickTimeInMilliseconds;
    private long clickTimeInSeconds;
    private String clientSdk;
    private transient int hashCode;
    private long installBeginTimeInSeconds;
    private Map<String, String> parameters;
    private Map<String, String> partnerParameters;
    private String path;
    private int retries;
    private String suffix;
    
    static {
        serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("path", String.class), new ObjectStreamField("clientSdk", String.class), new ObjectStreamField("parameters", Map.class), new ObjectStreamField("activityKind", ActivityKind.class), new ObjectStreamField("suffix", String.class), new ObjectStreamField("callbackParameters", Map.class), new ObjectStreamField("partnerParameters", Map.class) };
    }
    
    public ActivityPackage(final ActivityKind activityKind) {
        this.activityKind = ActivityKind.UNKNOWN;
        this.activityKind = activityKind;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        final ObjectInputStream.GetField fields = objectInputStream.readFields();
        this.path = Util.readStringField(fields, "path", null);
        this.clientSdk = Util.readStringField(fields, "clientSdk", null);
        this.parameters = Util.readObjectField(fields, "parameters", (Map<String, String>)null);
        this.activityKind = Util.readObjectField(fields, "activityKind", ActivityKind.UNKNOWN);
        this.suffix = Util.readStringField(fields, "suffix", null);
        this.callbackParameters = Util.readObjectField(fields, "callbackParameters", (Map<String, String>)null);
        this.partnerParameters = Util.readObjectField(fields, "partnerParameters", (Map<String, String>)null);
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final ActivityPackage activityPackage = (ActivityPackage)o;
            if (!Util.equalString(this.path, activityPackage.path)) {
                return false;
            }
            if (!Util.equalString(this.clientSdk, activityPackage.clientSdk)) {
                return false;
            }
            if (!Util.equalObject(this.parameters, activityPackage.parameters)) {
                return false;
            }
            if (!Util.equalEnum(this.activityKind, activityPackage.activityKind)) {
                return false;
            }
            if (!Util.equalString(this.suffix, activityPackage.suffix)) {
                return false;
            }
            if (!Util.equalObject(this.callbackParameters, activityPackage.callbackParameters)) {
                return false;
            }
            if (!Util.equalObject(this.partnerParameters, activityPackage.partnerParameters)) {
                return false;
            }
        }
        return true;
    }
    
    public ActivityKind getActivityKind() {
        return this.activityKind;
    }
    
    public Map<String, String> getCallbackParameters() {
        return this.callbackParameters;
    }
    
    public long getClickTimeInMilliseconds() {
        return this.clickTimeInMilliseconds;
    }
    
    public long getClickTimeInSeconds() {
        return this.clickTimeInSeconds;
    }
    
    public String getClientSdk() {
        return this.clientSdk;
    }
    
    public String getExtendedString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(Util.formatString("Path:      %s\n", this.path));
        sb.append(Util.formatString("ClientSdk: %s\n", this.clientSdk));
        if (this.parameters != null) {
            sb.append("Parameters:");
            final TreeMap<String, Object> treeMap = new TreeMap<String, Object>(this.parameters);
            final List<String> list = Arrays.asList("app_secret", "secret_id");
            for (final Map.Entry<String, V> entry : treeMap.entrySet()) {
                final String s = entry.getKey();
                if (!list.contains(s)) {
                    sb.append(Util.formatString("\n\t%-16s %s", s, entry.getValue()));
                }
            }
        }
        return sb.toString();
    }
    
    protected String getFailureMessage() {
        return Util.formatString("Failed to track %s%s", this.activityKind.toString(), this.suffix);
    }
    
    public long getInstallBeginTimeInSeconds() {
        return this.installBeginTimeInSeconds;
    }
    
    public Map<String, String> getParameters() {
        return this.parameters;
    }
    
    public Map<String, String> getPartnerParameters() {
        return this.partnerParameters;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public int getRetries() {
        return this.retries;
    }
    
    public String getSuffix() {
        return this.suffix;
    }
    
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = 17;
            this.hashCode = this.hashCode * 37 + Util.hashString(this.path);
            this.hashCode = this.hashCode * 37 + Util.hashString(this.clientSdk);
            this.hashCode = this.hashCode * 37 + Util.hashObject(this.parameters);
            this.hashCode = this.hashCode * 37 + Util.hashEnum(this.activityKind);
            this.hashCode = this.hashCode * 37 + Util.hashString(this.suffix);
            this.hashCode = this.hashCode * 37 + Util.hashObject(this.callbackParameters);
            this.hashCode = this.hashCode * 37 + Util.hashObject(this.partnerParameters);
        }
        return this.hashCode;
    }
    
    public int increaseRetries() {
        return ++this.retries;
    }
    
    public void setCallbackParameters(final Map<String, String> callbackParameters) {
        this.callbackParameters = callbackParameters;
    }
    
    public void setClickTimeInMilliseconds(final long clickTimeInMilliseconds) {
        this.clickTimeInMilliseconds = clickTimeInMilliseconds;
    }
    
    public void setClickTimeInSeconds(final long clickTimeInSeconds) {
        this.clickTimeInSeconds = clickTimeInSeconds;
    }
    
    public void setClientSdk(final String clientSdk) {
        this.clientSdk = clientSdk;
    }
    
    public void setInstallBeginTimeInSeconds(final long installBeginTimeInSeconds) {
        this.installBeginTimeInSeconds = installBeginTimeInSeconds;
    }
    
    public void setParameters(final Map<String, String> parameters) {
        this.parameters = parameters;
    }
    
    public void setPartnerParameters(final Map<String, String> partnerParameters) {
        this.partnerParameters = partnerParameters;
    }
    
    public void setPath(final String path) {
        this.path = path;
    }
    
    public void setSuffix(final String suffix) {
        this.suffix = suffix;
    }
    
    @Override
    public String toString() {
        return Util.formatString("%s%s", this.activityKind.toString(), this.suffix);
    }
}
