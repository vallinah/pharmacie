package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AnnotationAttr {
    public abstract String nameInBase();
    public abstract boolean inc() default false;
    public abstract boolean insert() default true;
    public abstract boolean textarea() default false;
    public abstract boolean show() default true;
    public abstract boolean id() default false;
}