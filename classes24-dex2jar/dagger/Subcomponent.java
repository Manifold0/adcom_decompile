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
@Target({ ElementType.TYPE })
public @interface Subcomponent {
    Class<?>[] modules() default {};
    
    @Documented
    @Target({ ElementType.TYPE })
    public @interface Builder {
    }
}
