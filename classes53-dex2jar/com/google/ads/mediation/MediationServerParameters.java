// 
// Decompiled by Procyon v0.5.34
// 

package com.google.ads.mediation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import com.google.android.gms.internal.ads.zzane;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class MediationServerParameters
{
    public void load(Map<String, String> iterator) throws MappingException {
        final HashMap<Object, Field> hashMap = new HashMap<Object, Field>();
        final Field[] fields = this.getClass().getFields();
        for (int length = fields.length, i = 0; i < length; ++i) {
            final Field field = fields[i];
            final Parameter parameter = field.getAnnotation(Parameter.class);
            if (parameter != null) {
                hashMap.put(parameter.name(), field);
            }
        }
        if (hashMap.isEmpty()) {
            zzane.zzdk("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
        }
        iterator = ((Map<Object, Object>)iterator).entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            final Field field2 = hashMap.remove(entry.getKey());
            if (field2 != null) {
                try {
                    field2.set(this, entry.getValue());
                }
                catch (IllegalAccessException ex) {
                    final String s = entry.getKey();
                    zzane.zzdk(new StringBuilder(String.valueOf(s).length() + 49).append("Server option \"").append(s).append("\" could not be set: Illegal Access").toString());
                }
                catch (IllegalArgumentException ex2) {
                    final String s2 = entry.getKey();
                    zzane.zzdk(new StringBuilder(String.valueOf(s2).length() + 43).append("Server option \"").append(s2).append("\" could not be set: Bad Type").toString());
                }
            }
            else {
                final String s3 = entry.getKey();
                final String s4 = entry.getValue();
                zzane.zzck(new StringBuilder(String.valueOf(s3).length() + 31 + String.valueOf(s4).length()).append("Unexpected server option: ").append(s3).append(" = \"").append(s4).append("\"").toString());
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (final Field field3 : hashMap.values()) {
            if (field3.getAnnotation(Parameter.class).required()) {
                final String value = String.valueOf(field3.getAnnotation(Parameter.class).name());
                String concat;
                if (value.length() != 0) {
                    concat = "Required server option missing: ".concat(value);
                }
                else {
                    concat = new String("Required server option missing: ");
                }
                zzane.zzdk(concat);
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(field3.getAnnotation(Parameter.class).name());
            }
        }
        if (sb.length() > 0) {
            final String value2 = String.valueOf(sb.toString());
            String concat2;
            if (value2.length() != 0) {
                concat2 = "Required server option(s) missing: ".concat(value2);
            }
            else {
                concat2 = new String("Required server option(s) missing: ");
            }
            throw new MappingException(concat2);
        }
    }
    
    public static final class MappingException extends Exception
    {
        public MappingException(final String s) {
            super(s);
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.FIELD })
    public @interface Parameter {
        String name();
        
        boolean required() default true;
    }
}
