// 
// Decompiled by Procyon v0.5.34
// 

package com.google.gson.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import java.lang.annotation.Annotation;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Expose {
    boolean deserialize() default true;
    
    boolean serialize() default true;
}
