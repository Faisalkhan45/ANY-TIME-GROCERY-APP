package org.checkerframework.common.value.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf({UnknownVal.class})
@Documented
@Target({})
@Retention(RetentionPolicy.SOURCE)
public @interface IntRangeFromNonNegative {
}
