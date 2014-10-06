package com.github.assisstion.ModulePack.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Gives the lowest version of Java this program will compile on.
 * Not always evaluated to full accuracy.
 *
 * @author Markus Feng
 */
@Documented
@Target(ElementType.TYPE)
@CompileVersion(JavaVersion.V1_5) // Annotation
public @interface CompileVersion{
	JavaVersion value();
}
