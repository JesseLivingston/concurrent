/**
 * 
 */
package com.aries.tiger.concurrent.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jesse
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {

}
