// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics.model;

import net.hockeyapp.android.metrics.JsonHelper;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.io.Serializable;

public class Device implements IJsonSerializable, Serializable
{
    private String id;
    private String ip;
    private String language;
    private String locale;
    private String machineName;
    private String model;
    private String network;
    private String networkName;
    private String oemName;
    private String os;
    private String osVersion;
    private String roleInstance;
    private String roleName;
    private String screenResolution;
    private String type;
    private String vmName;
    
    public Device() {
        this.InitializeFields();
    }
    
    protected void InitializeFields() {
    }
    
    public void addToHashMap(final Map<String, String> map) {
        if (this.id != null) {
            map.put("ai.device.id", this.id);
        }
        if (this.ip != null) {
            map.put("ai.device.ip", this.ip);
        }
        if (this.language != null) {
            map.put("ai.device.language", this.language);
        }
        if (this.locale != null) {
            map.put("ai.device.locale", this.locale);
        }
        if (this.model != null) {
            map.put("ai.device.model", this.model);
        }
        if (this.network != null) {
            map.put("ai.device.network", this.network);
        }
        if (this.networkName != null) {
            map.put("ai.device.networkName", this.networkName);
        }
        if (this.oemName != null) {
            map.put("ai.device.oemName", this.oemName);
        }
        if (this.os != null) {
            map.put("ai.device.os", this.os);
        }
        if (this.osVersion != null) {
            map.put("ai.device.osVersion", this.osVersion);
        }
        if (this.roleInstance != null) {
            map.put("ai.device.roleInstance", this.roleInstance);
        }
        if (this.roleName != null) {
            map.put("ai.device.roleName", this.roleName);
        }
        if (this.screenResolution != null) {
            map.put("ai.device.screenResolution", this.screenResolution);
        }
        if (this.type != null) {
            map.put("ai.device.type", this.type);
        }
        if (this.machineName != null) {
            map.put("ai.device.machineName", this.machineName);
        }
        if (this.vmName != null) {
            map.put("ai.device.vmName", this.vmName);
        }
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public String getLocale() {
        return this.locale;
    }
    
    public String getMachineName() {
        return this.machineName;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public String getNetwork() {
        return this.network;
    }
    
    public String getNetworkName() {
        return this.networkName;
    }
    
    public String getOemName() {
        return this.oemName;
    }
    
    public String getOs() {
        return this.os;
    }
    
    public String getOsVersion() {
        return this.osVersion;
    }
    
    public String getRoleInstance() {
        return this.roleInstance;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public String getScreenResolution() {
        return this.screenResolution;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getVmName() {
        return this.vmName;
    }
    
    @Override
    public void serialize(final Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("writer");
        }
        writer.write(123);
        this.serializeContent(writer);
        writer.write(125);
    }
    
    protected String serializeContent(final Writer writer) throws IOException {
        String s = "";
        if (this.id != null) {
            writer.write("" + "\"ai.device.id\":");
            writer.write(JsonHelper.convert(this.id));
            s = ",";
        }
        String s2 = s;
        if (this.ip != null) {
            writer.write(s + "\"ai.device.ip\":");
            writer.write(JsonHelper.convert(this.ip));
            s2 = ",";
        }
        String s3 = s2;
        if (this.language != null) {
            writer.write(s2 + "\"ai.device.language\":");
            writer.write(JsonHelper.convert(this.language));
            s3 = ",";
        }
        String s4 = s3;
        if (this.locale != null) {
            writer.write(s3 + "\"ai.device.locale\":");
            writer.write(JsonHelper.convert(this.locale));
            s4 = ",";
        }
        String s5 = s4;
        if (this.model != null) {
            writer.write(s4 + "\"ai.device.model\":");
            writer.write(JsonHelper.convert(this.model));
            s5 = ",";
        }
        String s6 = s5;
        if (this.network != null) {
            writer.write(s5 + "\"ai.device.network\":");
            writer.write(JsonHelper.convert(this.network));
            s6 = ",";
        }
        String s7 = s6;
        if (this.networkName != null) {
            writer.write(s6 + "\"ai.device.networkName\":");
            writer.write(JsonHelper.convert(this.networkName));
            s7 = ",";
        }
        String s8 = s7;
        if (this.oemName != null) {
            writer.write(s7 + "\"ai.device.oemName\":");
            writer.write(JsonHelper.convert(this.oemName));
            s8 = ",";
        }
        String s9 = s8;
        if (this.os != null) {
            writer.write(s8 + "\"ai.device.os\":");
            writer.write(JsonHelper.convert(this.os));
            s9 = ",";
        }
        String s10 = s9;
        if (this.osVersion != null) {
            writer.write(s9 + "\"ai.device.osVersion\":");
            writer.write(JsonHelper.convert(this.osVersion));
            s10 = ",";
        }
        String s11 = s10;
        if (this.roleInstance != null) {
            writer.write(s10 + "\"ai.device.roleInstance\":");
            writer.write(JsonHelper.convert(this.roleInstance));
            s11 = ",";
        }
        String s12 = s11;
        if (this.roleName != null) {
            writer.write(s11 + "\"ai.device.roleName\":");
            writer.write(JsonHelper.convert(this.roleName));
            s12 = ",";
        }
        String s13 = s12;
        if (this.screenResolution != null) {
            writer.write(s12 + "\"ai.device.screenResolution\":");
            writer.write(JsonHelper.convert(this.screenResolution));
            s13 = ",";
        }
        String s14 = s13;
        if (this.type != null) {
            writer.write(s13 + "\"ai.device.type\":");
            writer.write(JsonHelper.convert(this.type));
            s14 = ",";
        }
        String s15 = s14;
        if (this.machineName != null) {
            writer.write(s14 + "\"ai.device.machineName\":");
            writer.write(JsonHelper.convert(this.machineName));
            s15 = ",";
        }
        String s16 = s15;
        if (this.vmName != null) {
            writer.write(s15 + "\"ai.device.vmName\":");
            writer.write(JsonHelper.convert(this.vmName));
            s16 = ",";
        }
        return s16;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
    }
    
    public void setLanguage(final String language) {
        this.language = language;
    }
    
    public void setLocale(final String locale) {
        this.locale = locale;
    }
    
    public void setMachineName(final String machineName) {
        this.machineName = machineName;
    }
    
    public void setModel(final String model) {
        this.model = model;
    }
    
    public void setNetwork(final String network) {
        this.network = network;
    }
    
    public void setNetworkName(final String networkName) {
        this.networkName = networkName;
    }
    
    public void setOemName(final String oemName) {
        this.oemName = oemName;
    }
    
    public void setOs(final String os) {
        this.os = os;
    }
    
    public void setOsVersion(final String osVersion) {
        this.osVersion = osVersion;
    }
    
    public void setRoleInstance(final String roleInstance) {
        this.roleInstance = roleInstance;
    }
    
    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }
    
    public void setScreenResolution(final String screenResolution) {
        this.screenResolution = screenResolution;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setVmName(final String vmName) {
        this.vmName = vmName;
    }
}
