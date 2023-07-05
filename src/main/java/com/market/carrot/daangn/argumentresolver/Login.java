package com.market.carrot.daangn.argumentresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)//파라미터만 사용한다
@Retention(RetentionPolicy.RUNTIME)//대체로 이거 사용
public @interface Login {
}
