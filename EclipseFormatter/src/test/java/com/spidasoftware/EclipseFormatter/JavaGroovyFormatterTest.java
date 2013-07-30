/*
 * Copyright (C) 2013 Nicholas Joodi
 *
 * SpidaWeb LLC
 * 560 Officenter Pl., Gahanna, OH 43230
 * http://www.spidasoftware.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spidasoftware.EclipseFormatter;

import com.spidasoftware.EclipseFormatter.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * tests for GroovyFormat and JavaFormat classes
 *
 * @author Nicholas Joodi
 */
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
		log.info("JavaFormat.isFormatted() method is set to false when initialized");
		assertTrue("correctlyFormatted is set to false", javaFormatter.isFormatted() == false);
	}

	/**
	 * Test the GroovyFormat constructor
	 */
	public void testgroovyFormatConstruct() {
		log.info("GroovyFormat.isFormatted() method is set to false when initialized");
		assertTrue("correctlyFormatted is set to false", groovyFormatter.isFormatted() == false);
	}

	/**
	 * Test the JavaFormat with unparsable code
	 */
	public void testunParsableJavaCode() {
		log.info("JavaFormat.isFormatted() method is set to false after JavaFormat.Format(string, string) "
				+ "runs on unparsable java code");
		javaFormatter.format("javaEclipseFormatterTest13243546.java",
				"asfasgfdasgfdagfdsgsafgdasfdsf dsfdsa sdfdsf dsd");
		assertTrue("correctlyFormatted is false", javaFormatter.isFormatted() == false);
	}

	/**
	 * Test the JavaFormat with parsable code
	 */
	public void testparsableJavaCode() {
		log.info("JavaFormat.isFormatted() method is set to true after JavaFormat.Format(string, string) "
				+ "runs on parsable java code");
		javaFormatter.format("javaEclipseFormatterTest13243546.java",
				"package groovyTest;\npublic class genericJavaClass"
						+ "{\npublic static void main(String[] args) {\n// TODO Auto-generated method stub\n}\n}");
		assertTrue("correctlyFormatted is true", javaFormatter.isFormatted() == true);
	}

	/**
	 * Test the GroovyFormat with parsable code
	 */
	public void testparsableGroovyCode() {
		log.info("GroovyFormat.isFormatted() method is set to false after GroovyFormat.Format(string, string) "
				+ "runs on parsable groovy code");
		groovyFormatter.format("groovyEclipseFormatterTest13243546.groovy", "package groovyTest\nclass genericClass "
				+ "{\nstatic main(args) {\n}\n}\n");
		assertTrue("correctlyFormatted is true", groovyFormatter.isFormatted() == true);
	}

	/**
	 * Test the JavaFormat with an empty code string
	 */
	public void testEmptyCodeStringjava() {
		log.info("JavaFormat.isFormatted() method is set to false after javaFormat.Format(string, string) "
				+ "runs on an empty string of code");
		javaFormatter.format("javaEclipseFormatterTest13243546.java", "");
		assertTrue("correctlyFormatted is false", javaFormatter.isFormatted() == false);
	}

	/**
	 * Test the GroovyFormat with an empty code string
	 */
	public void testEmptyCodeStringGroovy() {
		log.info("GroovyFormat.isFormatted() method is set to false after GroovyFormat.Format(string, string) "
				+ "runs on an empty string of code");
		groovyFormatter.format("groovyEclipseFormatterTest13243546.groovy", "");
		assertTrue("correctlyFormatted is false", groovyFormatter.isFormatted() == false);
	}
}

