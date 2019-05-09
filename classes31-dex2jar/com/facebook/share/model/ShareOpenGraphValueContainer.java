// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import java.util.Set;
import java.util.Iterator;
import android.os.Parcelable;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import android.os.Parcel;
import android.os.Bundle;

public abstract class ShareOpenGraphValueContainer<P extends ShareOpenGraphValueContainer, E extends Builder> implements ShareModel
{
    private final Bundle bundle;
    
    ShareOpenGraphValueContainer(final Parcel parcel) {
        this.bundle = parcel.readBundle(Builder.class.getClassLoader());
    }
    
    protected ShareOpenGraphValueContainer(final Builder<P, E> builder) {
        this.bundle = (Bundle)((Builder<ShareOpenGraphValueContainer, Builder>)builder).bundle.clone();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public Object get(final String s) {
        return this.bundle.get(s);
    }
    
    public boolean getBoolean(final String s, final boolean b) {
        return this.bundle.getBoolean(s, b);
    }
    
    @Nullable
    public boolean[] getBooleanArray(final String s) {
        return this.bundle.getBooleanArray(s);
    }
    
    public Bundle getBundle() {
        return (Bundle)this.bundle.clone();
    }
    
    public double getDouble(final String s, final double n) {
        return this.bundle.getDouble(s, n);
    }
    
    @Nullable
    public double[] getDoubleArray(final String s) {
        return this.bundle.getDoubleArray(s);
    }
    
    public int getInt(final String s, final int n) {
        return this.bundle.getInt(s, n);
    }
    
    @Nullable
    public int[] getIntArray(final String s) {
        return this.bundle.getIntArray(s);
    }
    
    public long getLong(final String s, final long n) {
        return this.bundle.getLong(s, n);
    }
    
    @Nullable
    public long[] getLongArray(final String s) {
        return this.bundle.getLongArray(s);
    }
    
    public ShareOpenGraphObject getObject(final String s) {
        final Object value = this.bundle.get(s);
        if (value instanceof ShareOpenGraphObject) {
            return (ShareOpenGraphObject)value;
        }
        return null;
    }
    
    @Nullable
    public ArrayList<ShareOpenGraphObject> getObjectArrayList(final String s) {
        final ArrayList parcelableArrayList = this.bundle.getParcelableArrayList(s);
        ArrayList<ShareOpenGraphObject> list;
        if (parcelableArrayList == null) {
            list = null;
        }
        else {
            final ArrayList<ShareOpenGraphObject> list2 = new ArrayList<ShareOpenGraphObject>();
            final Iterator<Parcelable> iterator = parcelableArrayList.iterator();
            while (true) {
                list = list2;
                if (!iterator.hasNext()) {
                    break;
                }
                final Parcelable parcelable = iterator.next();
                if (!(parcelable instanceof ShareOpenGraphObject)) {
                    continue;
                }
                list2.add((ShareOpenGraphObject)parcelable);
            }
        }
        return list;
    }
    
    @Nullable
    public SharePhoto getPhoto(final String s) {
        final Parcelable parcelable = this.bundle.getParcelable(s);
        if (parcelable instanceof SharePhoto) {
            return (SharePhoto)parcelable;
        }
        return null;
    }
    
    @Nullable
    public ArrayList<SharePhoto> getPhotoArrayList(final String s) {
        final ArrayList parcelableArrayList = this.bundle.getParcelableArrayList(s);
        ArrayList<SharePhoto> list;
        if (parcelableArrayList == null) {
            list = null;
        }
        else {
            final ArrayList<SharePhoto> list2 = new ArrayList<SharePhoto>();
            final Iterator<Parcelable> iterator = parcelableArrayList.iterator();
            while (true) {
                list = list2;
                if (!iterator.hasNext()) {
                    break;
                }
                final Parcelable parcelable = iterator.next();
                if (!(parcelable instanceof SharePhoto)) {
                    continue;
                }
                list2.add((SharePhoto)parcelable);
            }
        }
        return list;
    }
    
    @Nullable
    public String getString(final String s) {
        return this.bundle.getString(s);
    }
    
    @Nullable
    public ArrayList<String> getStringArrayList(final String s) {
        return (ArrayList<String>)this.bundle.getStringArrayList(s);
    }
    
    public Set<String> keySet() {
        return (Set<String>)this.bundle.keySet();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeBundle(this.bundle);
    }
    
    public abstract static class Builder<P extends ShareOpenGraphValueContainer, E extends Builder> implements ShareModelBuilder<P, E>
    {
        private Bundle bundle;
        
        public Builder() {
            this.bundle = new Bundle();
        }
        
        public E putBoolean(final String s, final boolean b) {
            this.bundle.putBoolean(s, b);
            return (E)this;
        }
        
        public E putBooleanArray(final String s, @Nullable final boolean[] array) {
            this.bundle.putBooleanArray(s, array);
            return (E)this;
        }
        
        public E putDouble(final String s, final double n) {
            this.bundle.putDouble(s, n);
            return (E)this;
        }
        
        public E putDoubleArray(final String s, @Nullable final double[] array) {
            this.bundle.putDoubleArray(s, array);
            return (E)this;
        }
        
        public E putInt(final String s, final int n) {
            this.bundle.putInt(s, n);
            return (E)this;
        }
        
        public E putIntArray(final String s, @Nullable final int[] array) {
            this.bundle.putIntArray(s, array);
            return (E)this;
        }
        
        public E putLong(final String s, final long n) {
            this.bundle.putLong(s, n);
            return (E)this;
        }
        
        public E putLongArray(final String s, @Nullable final long[] array) {
            this.bundle.putLongArray(s, array);
            return (E)this;
        }
        
        public E putObject(final String s, @Nullable final ShareOpenGraphObject shareOpenGraphObject) {
            this.bundle.putParcelable(s, (Parcelable)shareOpenGraphObject);
            return (E)this;
        }
        
        public E putObjectArrayList(final String s, @Nullable final ArrayList<ShareOpenGraphObject> list) {
            this.bundle.putParcelableArrayList(s, (ArrayList)list);
            return (E)this;
        }
        
        public E putPhoto(final String s, @Nullable final SharePhoto sharePhoto) {
            this.bundle.putParcelable(s, (Parcelable)sharePhoto);
            return (E)this;
        }
        
        public E putPhotoArrayList(final String s, @Nullable final ArrayList<SharePhoto> list) {
            this.bundle.putParcelableArrayList(s, (ArrayList)list);
            return (E)this;
        }
        
        public E putString(final String s, @Nullable final String s2) {
            this.bundle.putString(s, s2);
            return (E)this;
        }
        
        public E putStringArrayList(final String s, @Nullable final ArrayList<String> list) {
            this.bundle.putStringArrayList(s, (ArrayList)list);
            return (E)this;
        }
        
        @Override
        public E readFrom(final P p) {
            if (p != null) {
                this.bundle.putAll(p.getBundle());
            }
            return (E)this;
        }
    }
}
