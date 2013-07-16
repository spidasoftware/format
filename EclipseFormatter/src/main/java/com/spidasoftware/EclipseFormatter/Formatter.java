/**
 * 
 * Extracted various classes from eclipse to create a command line automatic java 
 * and groovy formatter
 * 
 * SpidaSoftware
 * @author Nicholas Joodi
 * 
 */

package com.spidasoftware.EclipseFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class Formatter {

	/**
	 * This main method will take in one command line argument, a file, 
	 * and return a formatted verion of that file, as well as a backup file. 
	 * Only files with the extension of .groovy or .java will be formatted.
	 */
	public static void main(String[] args) {
		
		String code = readInFile(args[0]);
		String extension = args[0].substring(args[0].lastIndexOf(".") + 1, args[0].length());
		if (code != null && extension.equals("java")) {
			JavaFormat javaFormatter = new JavaFormat();
			javaFormatter.format(args[0], code);
			if (javaFormatter.isFormatted()) {
				createBackupFile(args[0], code);
			}

		} else if (code != null && extension.equals("groovy")) {
			GroovyFormat groovyFormatter = new GroovyFormat();
			groovyFormatter.format(args[0], code);
			if (groovyFormatter.isFormatted()) {
				createBackupFile(args[0], code);
			}

		} else {
			System.out.println("Sorry, no formatting could be applied to " + args[0]);
		}

	}

	/**
	 * A one-argument method that will take a filename and return a string containing 
	 * the contents of that file.
	 * 
	 * @param filename
	 *            The name of that file
	 * @return String containing the contents of that file
	 */
	@SuppressWarnings("resource")
	private static String readInFile(String fileName) {
		BufferedReader inStream = null;
		StringBuilder code = new StringBuilder("");
		String line;
		try {
			FileReader file = new FileReader(fileName);
			inStream = new BufferedReader(file);
			while ((line = inStream.readLine()) != null) {
				code.append(line + "\n");
			}
		} catch (IOException e) {
			System.out.println("Error occured when opening " + fileName);
			return null;
		}
		return code.toString();
	}


	/**
	 * A two-argument method that will take a filename string and the contents of 
	 * that file (before formatted), and return a backup file.
	 *
	 * @param filename, a string representing the name of the file
	 * @param before, a string containing the contents of that file
	 *
	 * @return a backup file
	 */
	private static void createBackupFile(String fileName, String before) {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss");
			String formattedDate = sdf.format(date);
			String nameWithDate = fileName + "_BACKUP_" + formattedDate;
			FileWriter file = new FileWriter(nameWithDate);
			PrintWriter safety = new PrintWriter(file);
			safety.println(before);
			safety.close();
			// System.out.println("*** A backup file was placed in " +
			// nameWithDate + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
