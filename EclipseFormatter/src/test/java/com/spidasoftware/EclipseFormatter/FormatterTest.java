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

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class FormatterTest extends TestCase {
	private static Logger log = Logger.getLogger(FormatterTest.class);

	public void setUp() throws Exception {
		super.setUp();
	}

	public void tearDown() {

	}

	/**
	 * Test the Formatter.readInFile when contents of file are not empty
	 */
	public void testreadInFileNotEmpty() {
		BasicConfigurator.configure();
		String fileName = "test";
		String contents = "abcdefghijklmnopqrstu\nvwx\nyz\n";
		File test = new File(fileName);
		try {
				PrintWriter out = new PrintWriter(new FileWriter(fileName));
				out.print(contents);
				out.close();

		}catch (IOException e) {
				log.error(e, e);
			}
		assertTrue("readInFile extracts correct file contents", (Formatter.readInFile(fileName)).equals(contents));
		if (test.exists())
			test.delete();


	}

	/**
	 * Test the Formatter.readInFile when contents of file are empty
	 */
	public void testreadInFileEmpty() {
		String fileName = "test";
		String contents = "";
		File test = new File(fileName);
		try {
				PrintWriter out = new PrintWriter(new FileWriter(fileName));
				out.print(contents);
				out.close();
		}catch (IOException e) {
				log.error(e, e);
			}
					
					

		assertTrue("readInFile extracts correct file contents", (Formatter.readInFile(fileName)).equals(contents));
		if (test.exists())
			test.delete();
	}

	/**
	 * Test that Formatter.createBackupFile creates a file
	 */
	public void testcreateBackupFileExists() {
		String fileName = "test";
		String contents = "abcdefghijklmnopqrstu\nvwx\nyz\n";
		String nameWithDate = Formatter.createBackupFile(fileName, contents);
		assertTrue("creatBackupFile creates a file", (new File(nameWithDate).exists()));

	}

	/**
	 * Test that Formatter.createBackupFile creates a file with the same contents
	 */
	public void testcreateBackupFileCorrectContents() {
		String fileName = "test";
		String contents = "abcdefghijklmnopqrstu\nvwx\nyz\n";
		String nameWithDate = Formatter.createBackupFile(fileName, contents);
		File test = new File(nameWithDate);

		BufferedReader inStream = null;
		StringBuilder code = new StringBuilder("");
		String line;
		try {
			FileReader file = new FileReader(nameWithDate);
			inStream = new BufferedReader(file);
			while ((line = inStream.readLine()) != null) {
				code.append(line + "\n");
			}
				code.delete(code.length()-1, code.length());
		} catch (IOException e) {
				log.error(e, e);
			}
			System.out.print(contents+ "----\n" +code.toString()+"----\n"+contents+"----\n"+code.toString());
		assertTrue("creatBackupFile creates a file",code.toString().equals(contents) );
		if (test.exists())
			test.delete();
	}

	/**
	 * Test where Formatter.formatOne returned a backup file when ran on a java file
	 */
	public void testformatOneBackupFileJava() {
		Options options = new Options(); 
		options.addOption("b", false, "create a backup file");
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		try {
			cmd = parser.parse(options, new String[]{"b"});
		} catch (ParseException e) {
			log.error(e, e);
		}
		String nameWithDate = Formatter.formatOne("javaEclipseFormatterTest13243546.java", "package groovyTest;\npublic class genericJavaClass"
						+ "{\npublic static void main(String[] args) {\n// TODO Auto-generated method stub\n}\n}", cmd );
		File test = new File(nameWithDate);
		assertTrue("Formatter.formatOne returned a backup file", test.exists());
		if (test.exists())
			test.delete();
	}

		/**
	 * Test where Formatter.formatOne returned a backup file when ran on a java file
	 */
	public void testformatOneNoBackupFileJava() {
		Options options = new Options(); 
		CommandLine cmd = null;
		CommandLineParser parser = new BasicParser();
		//CommandLine cmd = getOptions({}, options);
		try {
			cmd = parser.parse(options, new String[]{});
		} catch (ParseException e) {
			log.error(e, e);
		}
		String nameWithDate = Formatter.formatOne("javaEclipseFormatterTest13243546.java", "package groovyTest;\npublic class genericJavaClass"
						+ "{\npublic static void main(String[] args) {\n// TODO Auto-generated method stub\n}\n}", cmd );
		File test = new File(nameWithDate);
		assertTrue("Formatter.formatOne returned a backup file", test.exists() == false);

		if (test.exists())
			test.delete();
	}

	/**
	 * Test where Formatter.formatOne returned a backup file when ran on a groovy file
	 */
	public void testformatOneBackupFileGroovy() {
		Options options = new Options(); 
		options.addOption("b", false, "create a backup file");
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, new String[]{"b"});
		} catch (ParseException e) {
			log.error(e, e);
		}
		String nameWithDate = Formatter.formatOne("javaEclipseFormatterTest13243546.groovy", "package groovyTest\nclass genericClass "
				+ "{\nstatic main(args) {\n}\n}\n", cmd );
		File test = new File(nameWithDate);
		assertTrue("Formatter.formatOne returned a backup file", test.exists());
		if (test.exists())
			test.delete();
	}

}

