package com.github.assisstion.ModulePack.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.lang.model.SourceVersion;

/**
 * Lists all classes outside of its package that is not in the
 * Java Standard Library and is required to compile this class.
 * Not always evaluated to full accuracy.
 *
 * @author Markus Feng
 */
@Documented
@Target(ElementType.TYPE)
@CompileVersion(SourceVersion.RELEASE_5) // Annotation
public @interface Dependency {
	Class<?>[] value();
}
