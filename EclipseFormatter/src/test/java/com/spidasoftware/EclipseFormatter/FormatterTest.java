/*
 * Copyright (C) 2013 SpidaWeb LLC, http://www.spidasoftware.com
 *
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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.io.FileUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * tests for GroovyFormat and JavaFormat classes
 *
 * @author Nicholas Joodi
 */
public class FormatterTest extends TestCase {
	private static Logger log = Logger.getLogger(FormatterTest.class);
	private File readIn;
	private String fileName;
	private File backUp;
	private String nameWithDate;
	private File javaFile;
	private File groovyFile;
	private File groovyScript;

	public void setUp() throws Exception {
		super.setUp();
		fileName = "backUp";
		backUp = new File(fileName);
		javaFile = new File("javaEclipseFormatterTest13243546.java");
		groovyFile = new File("groovyEclipseFormatterTest13243546.groovy");
		groovyScript = new File("groovyEclipseFormatterTest13243546");
	}

	public void tearDown() {
		if (backUp.exists())
			backUp.delete();
		if (javaFile.exists())
			javaFile.delete();
		if (groovyFile.exists())
			groovyFile.delete();
		if (groovyScript.exists())
			groovyScript.delete();
		if (nameWithDate != null) {
			readIn = new File(nameWithDate);
			if (readIn.exists())
				readIn.delete();
		}

	}

	/**
	 * Test the Formatter.readInFile when contents of file are not empty
	 */
	public void testreadInFileNotEmpty() {
		BasicConfigurator.configure();
		log.info("Formatter.readInFile reads in correctly from a non-empty file");
		String contents = "abcdefghijklmnopqrstu\nvwx\nyz\n";
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileName));
			out.print(contents);
			out.close();
		} catch (IOException e) {
			log.error(e, e);
		}
		assertTrue("readInFile extracts correct file contents", (Formatter.readInFile(fileName)).equals(contents));
	}

	/**
	 * Test the Formatter.readInFile when contents of file are empty
	 */
	public void testreadInFileEmpty() {
		log.info("Formatter.readInFile reads in correctly from a empty");
		String contents = "";
		File backUp = new File(fileName);
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileName));
			out.print(contents);
			out.close();
		} catch (IOException e) {
			log.error(e, e);
		}
		assertTrue("readInFile extracts correct file contents", (Formatter.readInFile(fileName)).equals(contents));
	}

	/**
	 * Test that Formatter.createBackupFile creates a file
	 */
	public void testcreateBackupFileExists() {
		log.info("Formatter.createBackupFile creates a file");
		String contents = "abcdefghijklmnopqrstu\nvwx\nyz\n";
		nameWithDate = Formatter.createBackupFile(fileName, contents);
		assertTrue("creatBackupFile creates a file", (new File(nameWithDate).exists()));
	}

	/**
	 * Test that Formatter.createBackupFile creates a file with the same contents
	 */
	public void testcreateBackupFileCorrectContents() {
		log.info("Formatter.createBackupFile creates a file with the same contents");
		String contents = "abcdefghijklmnopqrstu\nvwx\nyz\n";
		nameWithDate = Formatter.createBackupFile(fileName, contents);
		backUp = new File(nameWithDate);
		String code = null;
		try {
			code = FileUtils.readFileToString(backUp, null);
		} catch (IOException e) {
			log.error("Error occured when opening " + fileName);
		}

		assertTrue("creatBackupFile creates a file", code.equals(contents));
	}

	/**
	 * Test where Formatter.formatOne returned a backup file when ran on a java file
	 */
	public void testformatOneBackupFileJava() {
		log.info("Formatter.formatOne returned a backup file when ran on a java file");
		Options options = new Options();
		options.addOption("b", false, "create a backup file");
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[] { "-b" });
		} catch (ParseException e) {
			log.error(e, e);
		}
		nameWithDate = Formatter.formatOne("javaEclipseFormatterTest13243546.java",
				"package groovyTest;\npublic class genericJavaClass"
						+ "{\npublic static void main(String[] args) {\n// TODO Auto-generated method stub\n}\n}", cmd);
		assertTrue("Formatter.formatOne returned a backup file", nameWithDate != null);
	}

	/**
	 * Test where Formatter.formatOne does not return a backup file when ran on a java file
	 */
	public void testformatOneNoBackupFileJava() {
		log.info("Formatter.formatOne returns no backup file when ran on a java file when the option is not set");
		Options options = new Options();
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[] {});
		} catch (ParseException e) {
			log.error(e, e);
		}
		nameWithDate = Formatter.formatOne("javaEclipseFormatterTest13243546.java",
				"package groovyTest;\npublic class genericJavaClass"
						+ "{\npublic static void main(String[] args) {\n// TODO Auto-generated method stub\n}\n}", cmd);
		assertTrue("Formatter.formatOne did not return a backup file", nameWithDate == null);
	}

	/**
	 * Test where Formatter.formatOne returned a backup file when ran on a groovy file
	 */
	public void testformatOneBackupFileGroovy() {
		log.info("Formatter.formatOne returned a backup file when ran on a groovy file");
		Options options = new Options();
		options.addOption("b", false, "create a backup file");
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, new String[] { "-b" });
		} catch (ParseException e) {
			log.error(e, e);
		}
		nameWithDate = Formatter.formatOne("groovyEclipseFormatterTest13243546.groovy",
				"package groovyTest\nclass genericClass " + "{\nstatic main(args) {\n}\n}\n", cmd);
		assertTrue("Formatter.formatOne returned a backup file", nameWithDate != null);

	}

	/**
	 * Test where Formatter.formatOne does not return a backup file when ran on a groovy file
	 */
	public void testformatOneNoBackupFileGroovy() {
		log.info("Formatter.formatOne returns no backup file when ran on a groovy file when the option is not set");
		Options options = new Options();
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[] {});
		} catch (ParseException e) {
			log.error(e, e);
		}
		nameWithDate = Formatter.formatOne("groovyEclipseFormatterTest13243546.groovy",
				"package groovyTest\nclass genericClass " + "{\nstatic main(args) {\n}\n}\n", cmd);
		assertTrue("Formatter.formatOne did not return a backup file", nameWithDate == null);
	}

	/**
	 * Test where groovy flag is set true
	 */
	public void testgroovyFlagSetTrue() {
		log.info("groovy flag set true");
		Options options = new Options();
		options.addOption("java", false, "only format java files");
		options.addOption("groovy", false, "only format groovy files");
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[] { "-groovy" });
		} catch (ParseException e) {
			log.error(e, e);
		}
		assertTrue("groovy flag set true", Formatter.groovyFormatting(cmd) == true);
	}

	/**
	 * Test where groovy flag is set false
	 */
	public void testgroovyFlagSetfalse() {
		log.info("groovy flag set false");
		Options options = new Options();
		options.addOption("java", false, "only format java files");
		options.addOption("groovy", false, "only format groovy files");
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[] { "-java" });
		} catch (ParseException e) {
			log.error(e, e);
		}
		assertTrue("groovy flag set false", Formatter.groovyFormatting(cmd) == false);
	}

	/**
	 * Test where java flag is set true
	 */
	public void testjavaFlagSetTrue() {
		log.info("java flag set true");
		Options options = new Options();
		options.addOption("java", false, "only format java files");
		options.addOption("groovy", false, "only format groovy files");
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[] { "-java" });
		} catch (ParseException e) {
			log.error(e, e);
		}
		assertTrue("java flag set true", Formatter.javaFormatting(cmd) == true);
	}

	/**
	 * Test where java flag is set false
	 */
	public void testjavaFlagSetFalse() {
		log.info("java flag set false");
		Options options = new Options();
		options.addOption("java", false, "only format java files");
		options.addOption("groovy", false, "only format groovy files");
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[] { "-groovy" });
		} catch (ParseException e) {
			log.error(e, e);
		}
		assertTrue("java flag set false", Formatter.javaFormatting(cmd) == false);
	}

	/**
	 * Test is groovy script True
	 */
	public void testisgroovyScriptTrue() {
		log.info("Is a groovy script");
		Options options = new Options();
		options.addOption("b", false, "create a backup file");
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[] { "-b" });
		} catch (ParseException e) {
			log.error(e, e);
		}
		nameWithDate = Formatter.formatOne("groovyEclipseFormatterTest13243546",
				"#!/usr/bin/env groovy\npackage groovyTest\nclass genericClass " + "{\nstatic main(args) {\n}\n}\n",
				cmd);
		assertTrue("Formatter.formatOne did not return a backup file", nameWithDate != null);
	}

	/**
	 * Test is groovy script False
	 */
	public void testisgroovyScriptFalse() {
		log.info("Is not a groovy script");
		Options options = new Options();
		options.addOption("b", false, "create a backup file");
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[] { "-b" });
		} catch (ParseException e) {
			log.error(e, e);
		}
		nameWithDate = Formatter.formatOne("groovyEclipseFormatterTest13243546",
				"package groovyTest\nclass genericClass " + "{\nstatic main(args) {\n}\n}\n", cmd);
		assertTrue("Formatter.formatOne did not return a backup file", nameWithDate == null);
	}

}

