package com.github.assisstion.ModulePack.annotation;

/**
 * A Java Language Version
 *
 * @author Markus Feng
 */
@CompileVersion(Version.V1_5) // Enum
public enum Version{

	/**
	 * Created Java
	 */
	V1_0 (1,0),

	/**
	 * AWT Event Model
	 * Inner classes
	 * JavaBeans
	 * JDBC
	 * RMI
	 * Reflection
	 */
	V1_1 (1,1),

	/**
	 * Swing
	 * Collections
	 */
	V1_2 (1,2),

	/**
	 * JNDI
	 * JPDA
	 * JavaSound
	 */
	V1_3 (1,3),

	/**
	 * assert
	 * regex
	 * Exception Chaining
	 * IPv6
	 * NIO
	 * logging
	 * ImageIO
	 * JAXP (XML)
	 * Security/Crypto (JCE, JSSE, JAAS)
	 * Preferences
	 */
	V1_4 (1,4),

	/**
	 * Generics
	 * Annotations
	 * Autoboxing
	 * Enum
	 * Varargs
	 * for :
	 * import static
	 * concurrency utilites
	 * Scanner
	 */
	V1_5 (1,5),

	/**
	 * SwingWorker
	 */
	V1_6 (1,6),

	/**
	 * switch Strings
	 * try with resources
	 * <>
	 * binary literals
	 * underscore in literals
	 * try with multi-catch
	 * File NIO
	 */
	V1_7 (1,7),

	/**
	 * Lambada expressions
	 * Closures
	 * default methods
	 * Date and Time
	 */
	V1_8 (1,8);

	/**
	 * The major version
	 */
	protected final int major;

	/**
	 * The minor version
	 */
	protected final int minor;

	private Version(int major, int minor){
		this.major = major;
		this.minor = minor;
	}

	/**
	 * Returns the major version
	 * @return the major version
	 */
	public int getMajor(){
		return major;
	}

	/**
	 * Returns the minor version
	 * @return the minor version
	 */
	public int getMinor(){
		return minor;
	}
}
