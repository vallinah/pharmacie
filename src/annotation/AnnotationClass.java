package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AnnotationClass {
    public abstract String nameInBase();
    public abstract String sequence() default "";
    public abstract String prefix() default "";
    public abstract String page() default "";
}