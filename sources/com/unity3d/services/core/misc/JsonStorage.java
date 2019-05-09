package com.unity3d.services.core.misc;

import android.text.TextUtils;
import com.unity3d.services.core.log.DeviceLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonStorage {
    private JSONObject _data;

    public synchronized boolean initData() {
        boolean z;
        if (this._data == null) {
            this._data = new JSONObject();
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized void setData(JSONObject data) {
        this._data = data;
    }

    public synchronized JSONObject getData() {
        return this._data;
    }

    public synchronized boolean hasData() {
        boolean z;
        if (this._data == null || this._data.length() <= 0) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public synchronized void clearData() {
        this._data = null;
    }

    public synchronized boolean set(String key, Object value) {
        boolean z = false;
        synchronized (this) {
            if (this._data == null || key == null || key.length() == 0 || value == null) {
                DeviceLog.error("Storage not properly initialized or incorrect parameters:" + this._data + ", " + key + ", " + value);
            } else {
                createObjectTree(getParentObjectTreeFor(key));
                if (findObject(getParentObjectTreeFor(key)) instanceof JSONObject) {
                    JSONObject parentObject = (JSONObject) findObject(getParentObjectTreeFor(key));
                    String[] objects = key.split("\\.");
                    if (parentObject != null) {
                        try {
                            parentObject.put(objects[objects.length - 1], value);
                        } catch (JSONException e) {
                            DeviceLog.exception("Couldn't set value", e);
                        }
                    }
                    z = true;
                } else {
                    DeviceLog.debug("Cannot set subvalue to an object that is not JSONObject");
                }
            }
        }
        return z;
    }

    public synchronized Object get(String key) {
        Object obj = null;
        synchronized (this) {
            if (this._data == null) {
                DeviceLog.error("Data is NULL, readStorage probably not called");
            } else {
                String[] objects = key.split("\\.");
                if (findObject(getParentObjectTreeFor(key)) instanceof JSONObject) {
                    JSONObject parentObject = (JSONObject) findObject(getParentObjectTreeFor(key));
                    if (parentObject != null) {
                        obj = null;
                        try {
                            if (parentObject.has(objects[objects.length - 1])) {
                                obj = parentObject.get(objects[objects.length - 1]);
                            }
                        } catch (Exception e) {
                            DeviceLog.exception("Error getting data", e);
                        }
                    }
                }
            }
        }
        return obj;
    }

    public synchronized List<String> getKeys(String key, boolean recursive) {
        List<String> arrayList;
        if (get(key) instanceof JSONObject) {
            JSONObject parentObject = (JSONObject) get(key);
            arrayList = new ArrayList();
            if (parentObject != null) {
                Iterator<String> i = parentObject.keys();
                while (i.hasNext()) {
                    String currentKey = (String) i.next();
                    List<String> subkeys = null;
                    if (recursive) {
                        subkeys = getKeys(key + "." + currentKey, recursive);
                    }
                    arrayList.add(currentKey);
                    if (subkeys != null) {
                        for (String subkey : subkeys) {
                            arrayList.add(currentKey + "." + subkey);
                        }
                    }
                }
            }
        } else {
            arrayList = null;
        }
        return arrayList;
    }

    public synchronized boolean delete(String key) {
        boolean z = false;
        synchronized (this) {
            if (this._data == null) {
                DeviceLog.error("Data is NULL, readStorage probably not called");
            } else {
                String[] objects = key.split("\\.");
                if (findObject(getParentObjectTreeFor(key)) instanceof JSONObject) {
                    JSONObject parentObject = (JSONObject) findObject(getParentObjectTreeFor(key));
                    if (!(parentObject == null || parentObject.remove(objects[objects.length - 1]) == null)) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    private synchronized Object findObject(String key) {
        Object obj = null;
        synchronized (this) {
            String[] objects = key.split("\\.");
            JSONObject parentObject = this._data;
            if (key.length() == 0) {
                obj = parentObject;
            } else {
                int idx = 0;
                while (idx < objects.length) {
                    if (!parentObject.has(objects[idx])) {
                        break;
                    }
                    try {
                        parentObject = parentObject.getJSONObject(objects[idx]);
                        idx++;
                    } catch (Exception e) {
                        DeviceLog.exception("Couldn't read JSONObject: " + objects[idx], e);
                    }
                }
                JSONObject jSONObject = parentObject;
            }
        }
        return obj;
    }

    private synchronized void createObjectTree(String tree) {
        String[] objects = tree.split("\\.");
        JSONObject parentObject = this._data;
        if (tree.length() != 0) {
            for (int idx = 0; idx < objects.length; idx++) {
                if (parentObject.has(objects[idx])) {
                    try {
                        parentObject = parentObject.getJSONObject(objects[idx]);
                    } catch (Exception e) {
                        DeviceLog.exception("Couldn't get existing JSONObject", e);
                    }
                } else {
                    try {
                        parentObject = parentObject.put(objects[idx], new JSONObject()).getJSONObject(objects[idx]);
                    } catch (Exception e2) {
                        DeviceLog.exception("Couldn't create new JSONObject", e2);
                    }
                }
            }
        }
    }

    private synchronized String getParentObjectTreeFor(String tree) {
        ArrayList<String> tmpObs;
        tmpObs = new ArrayList(Arrays.asList(tree.split("\\.")));
        tmpObs.remove(tmpObs.size() - 1);
        return TextUtils.join(".", tmpObs.toArray());
    }
}
