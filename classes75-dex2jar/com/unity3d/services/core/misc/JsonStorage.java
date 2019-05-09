// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.misc;

import org.json.JSONException;
import java.util.Iterator;
import java.util.List;
import android.text.TextUtils;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.unity3d.services.core.log.DeviceLog;
import org.json.JSONObject;

public class JsonStorage
{
    private JSONObject _data;
    
    private void createObjectTree(String s) {
        while (true) {
            while (true) {
                Label_0116: {
                    final String[] split;
                    Object data;
                    int i;
                    synchronized (this) {
                        split = s.split("\\.");
                        data = this._data;
                        i = s.length();
                        if (i != 0) {
                            i = 0;
                            while (i < split.length) {
                                if (((JSONObject)data).has(split[i])) {
                                    break Label_0116;
                                }
                                s = (String)data;
                                try {
                                    s = (String)((JSONObject)(s = (String)((JSONObject)data).put(split[i], (Object)new JSONObject()))).getJSONObject(split[i]);
                                    ++i;
                                    data = s;
                                }
                                catch (Exception data) {
                                    DeviceLog.exception("Couldn't create new JSONObject", (Exception)data);
                                }
                            }
                        }
                        return;
                    }
                    try {
                        s = (String)((JSONObject)data).getJSONObject(split[i]);
                        continue;
                    }
                    catch (Exception ex) {
                        DeviceLog.exception("Couldn't get existing JSONObject", ex);
                        s = (String)data;
                        continue;
                    }
                }
                continue;
            }
        }
    }
    
    private Object findObject(final String s) {
        while (true) {
            final JSONObject jsonObject = null;
            JSONObject jsonObject2;
            synchronized (this) {
                final String[] split = s.split("\\.");
                jsonObject2 = this._data;
                if (s.length() != 0) {
                    int i = 0;
                    while (i < split.length) {
                        final boolean has = jsonObject2.has(split[i]);
                        JSONObject jsonObject3 = jsonObject;
                        if (!has) {
                            return jsonObject3;
                        }
                        try {
                            jsonObject2 = jsonObject2.getJSONObject(split[i]);
                            ++i;
                        }
                        catch (Exception ex) {
                            DeviceLog.exception("Couldn't read JSONObject: " + split[i], ex);
                            jsonObject3 = jsonObject;
                        }
                    }
                    return jsonObject2;
                }
                return jsonObject2;
            }
            return jsonObject2;
        }
    }
    
    private String getParentObjectTreeFor(String join) {
        synchronized (this) {
            final ArrayList list = new ArrayList((Collection<? extends E>)Arrays.asList(join.split("\\.")));
            list.remove(list.size() - 1);
            join = TextUtils.join((CharSequence)".", list.toArray());
            return join;
        }
    }
    
    public void clearData() {
        synchronized (this) {
            this._data = null;
        }
    }
    
    public boolean delete(final String s) {
        final boolean b = false;
        synchronized (this) {
            boolean b2;
            if (this._data == null) {
                DeviceLog.error("Data is NULL, readStorage probably not called");
                b2 = b;
            }
            else {
                final String[] split = s.split("\\.");
                b2 = b;
                if (this.findObject(this.getParentObjectTreeFor(s)) instanceof JSONObject) {
                    final JSONObject jsonObject = (JSONObject)this.findObject(this.getParentObjectTreeFor(s));
                    b2 = b;
                    if (jsonObject != null) {
                        final Object remove = jsonObject.remove(split[split.length - 1]);
                        b2 = b;
                        if (remove != null) {
                            b2 = true;
                        }
                    }
                }
            }
            return b2;
        }
    }
    
    public Object get(String s) {
        final Object o = null;
        synchronized (this) {
            Object value;
            if (this._data == null) {
                DeviceLog.error("Data is NULL, readStorage probably not called");
                value = o;
            }
            else {
                final String[] split = s.split("\\.");
                value = o;
                if (this.findObject(this.getParentObjectTreeFor(s)) instanceof JSONObject) {
                    final JSONObject jsonObject = (JSONObject)this.findObject(this.getParentObjectTreeFor(s));
                    value = o;
                    if (jsonObject != null) {
                        s = (String)(value = null);
                        try {
                            if (jsonObject.has(split[split.length - 1])) {
                                value = jsonObject.get(split[split.length - 1]);
                            }
                        }
                        catch (Exception ex) {
                            DeviceLog.exception("Error getting data", ex);
                            value = s;
                        }
                    }
                }
            }
            return value;
        }
    }
    
    public JSONObject getData() {
        synchronized (this) {
            return this._data;
        }
    }
    
    public List<String> getKeys(final String s, final boolean b) {
        Object o = null;
        Label_0192: {
            synchronized (this) {
                if (this.get(s) instanceof JSONObject) {
                    final JSONObject jsonObject = (JSONObject)this.get(s);
                    final ArrayList list = (ArrayList)(o = new ArrayList<String>());
                    if (jsonObject == null) {
                        break Label_0192;
                    }
                    final Iterator keys = jsonObject.keys();
                    while (true) {
                        o = list;
                        if (!keys.hasNext()) {
                            break Label_0192;
                        }
                        final String s2 = keys.next();
                        o = null;
                        if (b) {
                            o = this.getKeys(s + "." + s2, b);
                        }
                        list.add(s2);
                        if (o == null) {
                            continue;
                        }
                        o = ((List<String>)o).iterator();
                        while (((Iterator)o).hasNext()) {
                            list.add(s2 + "." + ((Iterator<String>)o).next());
                        }
                    }
                }
            }
            o = null;
        }
        // monitorexit(this)
        return (List<String>)o;
    }
    
    public boolean hasData() {
        synchronized (this) {
            return this._data != null && this._data.length() > 0;
        }
    }
    
    public boolean initData() {
        synchronized (this) {
            boolean b;
            if (this._data == null) {
                this._data = new JSONObject();
                b = true;
            }
            else {
                b = false;
            }
            return b;
        }
    }
    
    public boolean set(final String s, final Object o) {
        while (true) {
            boolean b = false;
            Label_0156: {
                synchronized (this) {
                    if (this._data == null || s == null || s.length() == 0 || o == null) {
                        DeviceLog.error("Storage not properly initialized or incorrect parameters:" + this._data + ", " + s + ", " + o);
                    }
                    else {
                        this.createObjectTree(this.getParentObjectTreeFor(s));
                        if (!(this.findObject(this.getParentObjectTreeFor(s)) instanceof JSONObject)) {
                            break Label_0156;
                        }
                        final JSONObject jsonObject = (JSONObject)this.findObject(this.getParentObjectTreeFor(s));
                        final String[] split = s.split("\\.");
                        Label_0136: {
                            if (jsonObject == null) {
                                break Label_0136;
                            }
                            try {
                                jsonObject.put(split[split.length - 1], o);
                                b = true;
                            }
                            catch (JSONException ex) {
                                DeviceLog.exception("Couldn't set value", (Exception)ex);
                            }
                        }
                    }
                    return b;
                }
            }
            DeviceLog.debug("Cannot set subvalue to an object that is not JSONObject");
            return b;
        }
    }
    
    public void setData(final JSONObject data) {
        synchronized (this) {
            this._data = data;
        }
    }
}
