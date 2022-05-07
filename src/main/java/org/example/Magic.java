package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // Interface, Class, Enum
@Retention(RetentionPolicy.SOURCE)// 컴파일 시에만 필요하고 런타임에는 필요가 없다
public @interface Magic {
}
