package com.github.assisstion.ModulePack.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * A class marked as immutable to all public members.
 * This means that this class's values should not be changed
 * at any time, regardless of reason. Subclasses, however,
 * are not necessarily required to inherit this immutability.
 * For classes that require each field of its class to be
 * immutable, a final keyword should be used.
 *
 * This class should not have any public fields or public
 * methods that modify its fields. Also, all instances of
 * fields this class has should be effectively immutable,
 * which is defined as having the same values for public fields
 * and returning the same values for public methods at any
 * given access after the first possible instance access was
 * granted by the class.
 *
 * An example of a system Immutable object is java.lang.String.
 * Each of its "modification" methods returns a new String
 * instead of changing the original.
 *
 * Lastly, this class can have transient non-public-accessible
 * members that can change over time. These values should
 * not affect any public-facing fields or methods.
 *
 * All immutable classes are by definition, effectively immutable.
 *
 * @author Markus Feng
 *
 */
@Documented
@Target(ElementType.TYPE)
@CompileVersion(JavaVersion.V1_5) // Annotation
public @interface Immutable{

}
