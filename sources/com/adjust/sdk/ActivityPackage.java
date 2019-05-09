package com.adjust.sdk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class ActivityPackage implements Serializable {
    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[]{new ObjectStreamField("path", String.class), new ObjectStreamField("clientSdk", String.class), new ObjectStreamField("parameters", Map.class), new ObjectStreamField("activityKind", ActivityKind.class), new ObjectStreamField("suffix", String.class), new ObjectStreamField("callbackParameters", Map.class), new ObjectStreamField("partnerParameters", Map.class)};
    private static final long serialVersionUID = -35935556512024097L;
    private ActivityKind activityKind = ActivityKind.UNKNOWN;
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

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClientSdk() {
        return this.clientSdk;
    }

    public void setClientSdk(String clientSdk) {
        this.clientSdk = clientSdk;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void setCallbackParameters(Map<String, String> callbackParameters) {
        this.callbackParameters = callbackParameters;
    }

    public void setPartnerParameters(Map<String, String> partnerParameters) {
        this.partnerParameters = partnerParameters;
    }

    public ActivityKind getActivityKind() {
        return this.activityKind;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getRetries() {
        return this.retries;
    }

    public int increaseRetries() {
        this.retries++;
        return this.retries;
    }

    public long getClickTimeInMilliseconds() {
        return this.clickTimeInMilliseconds;
    }

    public void setClickTimeInMilliseconds(long clickTimeInMilliseconds) {
        this.clickTimeInMilliseconds = clickTimeInMilliseconds;
    }

    public long getClickTimeInSeconds() {
        return this.clickTimeInSeconds;
    }

    public void setClickTimeInSeconds(long clickTimeInSeconds) {
        this.clickTimeInSeconds = clickTimeInSeconds;
    }

    public long getInstallBeginTimeInSeconds() {
        return this.installBeginTimeInSeconds;
    }

    public void setInstallBeginTimeInSeconds(long installBeginTimeInSeconds) {
        this.installBeginTimeInSeconds = installBeginTimeInSeconds;
    }

    public Map<String, String> getCallbackParameters() {
        return this.callbackParameters;
    }

    public Map<String, String> getPartnerParameters() {
        return this.partnerParameters;
    }

    public ActivityPackage(ActivityKind activityKind) {
        this.activityKind = activityKind;
    }

    public String toString() {
        return Util.formatString("%s%s", this.activityKind.toString(), this.suffix);
    }

    public String getExtendedString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Util.formatString("Path:      %s\n", this.path));
        builder.append(Util.formatString("ClientSdk: %s\n", this.clientSdk));
        if (this.parameters != null) {
            builder.append("Parameters:");
            SortedMap<String, String> sortedParameters = new TreeMap(this.parameters);
            List<String> stringsToExclude = Arrays.asList(new String[]{"app_secret", "secret_id"});
            for (Entry<String, String> entry : sortedParameters.entrySet()) {
                if (!stringsToExclude.contains((String) entry.getKey())) {
                    builder.append(Util.formatString("\n\t%-16s %s", (String) entry.getKey(), ((Entry) r5.next()).getValue()));
                }
            }
        }
        return builder.toString();
    }

    protected String getFailureMessage() {
        return Util.formatString("Failed to track %s%s", this.activityKind.toString(), this.suffix);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        GetField fields = stream.readFields();
        this.path = Util.readStringField(fields, "path", null);
        this.clientSdk = Util.readStringField(fields, "clientSdk", null);
        this.parameters = (Map) Util.readObjectField(fields, "parameters", null);
        this.activityKind = (ActivityKind) Util.readObjectField(fields, "activityKind", ActivityKind.UNKNOWN);
        this.suffix = Util.readStringField(fields, "suffix", null);
        this.callbackParameters = (Map) Util.readObjectField(fields, "callbackParameters", null);
        this.partnerParameters = (Map) Util.readObjectField(fields, "partnerParameters", null);
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        ActivityPackage otherActivityPackage = (ActivityPackage) other;
        if (!Util.equalString(this.path, otherActivityPackage.path)) {
            return false;
        }
        if (!Util.equalString(this.clientSdk, otherActivityPackage.clientSdk)) {
            return false;
        }
        if (!Util.equalObject(this.parameters, otherActivityPackage.parameters)) {
            return false;
        }
        if (!Util.equalEnum(this.activityKind, otherActivityPackage.activityKind)) {
            return false;
        }
        if (!Util.equalString(this.suffix, otherActivityPackage.suffix)) {
            return false;
        }
        if (!Util.equalObject(this.callbackParameters, otherActivityPackage.callbackParameters)) {
            return false;
        }
        if (Util.equalObject(this.partnerParameters, otherActivityPackage.partnerParameters)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = 17;
            this.hashCode = (this.hashCode * 37) + Util.hashString(this.path);
            this.hashCode = (this.hashCode * 37) + Util.hashString(this.clientSdk);
            this.hashCode = (this.hashCode * 37) + Util.hashObject(this.parameters);
            this.hashCode = (this.hashCode * 37) + Util.hashEnum(this.activityKind);
            this.hashCode = (this.hashCode * 37) + Util.hashString(this.suffix);
            this.hashCode = (this.hashCode * 37) + Util.hashObject(this.callbackParameters);
            this.hashCode = (this.hashCode * 37) + Util.hashObject(this.partnerParameters);
        }
        return this.hashCode;
    }
}
