// 
// Decompiled by Procyon v0.5.34
// 

package dagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import java.lang.annotation.Annotation;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Provides {
    Type type() default Type.UNIQUE;
    
    public enum Type
    {
        MAP, 
        SET, 
        SET_VALUES, 
        UNIQUE;
    }
}
