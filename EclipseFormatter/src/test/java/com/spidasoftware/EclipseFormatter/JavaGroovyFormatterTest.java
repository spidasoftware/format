/**
 * tests for GroovyFormat and JavaFormat classes
 * 
 * July of 2013
 * spidasoftware
 * @author Nick Joodi
 */

package com.spidasoftware.EclipseFormatter;

import com.spidasoftware.EclipseFormatter.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class JavaGroovyFormatterTest extends TestCase {
	private JavaFormat javaFormatter;
	private GroovyFormat groovyFormatter;
	private File javaFile;
	private File groovyFile;
	private final static Logger log = Logger.getLogger(GroovyFormat.class);

	public void setUp() throws Exception {
		super.setUp();
		javaFormatter = new JavaFormat();
		groovyFormatter = new GroovyFormat();
		javaFile = new File("javaEclipseFormatterTest13243546.java");
		groovyFile = new File("groovyEclipseFormatterTest13243546.groovy");
	}

	public void tearDown() {
		if (javaFile.exists())
			javaFile.delete();
		if (groovyFile.exists())
			groovyFile.delete();
	}

	/**
	 * Test the JavaFormat constructor
	 */
	public void testjavaFormatConstruct() {
		BasicConfigurator.configure();
		log.info("Test to make sure javaFormat.isFormatted() method is set to false when initialized");
		assertTrue("correctlyFormatted is set to false", javaFormatter.isFormatted() == false);
	}

	/**
	 * Test the GroovyFormat constructor
	 */
	public void testgroovyFormatConstruct() {
		log.info("Test to make sure GroovyFormat.isFormatted() method is set to false when initialized");
		assertTrue("correctlyFormatted is set to false", groovyFormatter.isFormatted() == false);
	}

	/**
	 * Test the JavaFormat with unparsable code
	 */
	public void testunParsableJavaCode() {
		log.info("Test to make sure JavaFormat.isFormatted() method is set to false after JavaFormat.Format(string, string) "
				+ "runs on unparsable java code");
		JavaFormat javaFormatter = new JavaFormat();
		javaFormatter.format("javaEclipseFormatterTest13243546.java",
				"asfasgfdasgfdagfdsgsafgdasfdsf dsfdsa sdfdsf dsd");
		assertTrue("correctlyFormatted is false", javaFormatter.isFormatted() == false);
	}

	/**
	 * Test the JavaFormat with parsable code
	 */
	public void testparsableJavaCode() {
		log.info("Test to make sure JavaFormat.isFormatted() method is set to true after JavaFormat.Format(string, string) "
				+ "runs on parsable java code");
		JavaFormat javaFormatter = new JavaFormat();
		javaFormatter.format("javaEclipseFormatterTest13243546.java",
				"package groovyTest;\npublic class genericJavaClass"
						+ "{\npublic static void main(String[] args) {\n// TODO Auto-generated method stub\n}\n}");
		assertTrue("correctlyFormatted is true", javaFormatter.isFormatted() == true);
	}

	/**
	 * Test the GroovyFormat with parsable code
	 */
	public void testparsableGroovyCode() {
		log.info("Test to make sure GroovyFormat.isFormatted() method is set to false after GroovyFormat.Format(string, string) "
				+ "runs on parsable groovy code");
		GroovyFormat groovyFormatter = new GroovyFormat();
		groovyFormatter.format("groovyEclipseFormatterTest13243546.groovy", "package groovyTest\nclass genericClass "
				+ "{\nstatic main(args) {\n}\n}\n");
		assertTrue("correctlyFormatted is true", groovyFormatter.isFormatted() == true);
	}

	/**
	 * Test the JavaFormat with an empty code string
	 */
	public void testEmptyCodeStringjava() {
		log.info("Test to make sure JavaFormat.isFormatted() method is set to false after javaFormat.Format(string, string) "
				+ "runs on an empty string of code");
		JavaFormat javaFormatter = new JavaFormat();
		javaFormatter.format("javaEclipseFormatterTest13243546.java", "");
		assertTrue("correctlyFormatted is false", javaFormatter.isFormatted() == false);
	}

	/**
	 * Test the GroovyFormat with an empty code string
	 */
	public void testEmptyCodeStringGroovy() {
		log.info("Test to make sure GroovyFormat.isFormatted() method is set to false after GroovyFormat.Format(string, string) "
				+ "runs on an empty string of code");
		GroovyFormat groovyFormatter = new GroovyFormat();
		groovyFormatter.format("groovyEclipseFormatterTest13243546.groovy", "");
		assertTrue("correctlyFormatted is false", groovyFormatter.isFormatted() == false);
	}
}

