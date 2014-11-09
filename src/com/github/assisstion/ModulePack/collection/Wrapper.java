package com.github.assisstion.ModulePack.collection;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.lang.model.SourceVersion;

import com.github.assisstion.ModulePack.annotation.CompileVersion;

@Documented
@Target(ElementType.TYPE)
@CompileVersion(SourceVersion.RELEASE_5) // Annotation
public @interface Wrapper{

}
