/**
 * 
 * Extracted various classes from eclipse to create a command line automatic java 
 * and groovy formatter
 * 
 * July of 2013
 * SpidaSoftware
 * @author Nick Joodi
 * 
 */

package com.spidasoftware.EclipseFormatter;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Appender;
import org.apache.log4j.PatternLayout;

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
import org.apache.commons.cli.Options;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Formatter {
	private static Logger log = Logger.getRootLogger();
	private static Options options = new Options();


	public static void main(String[] args) {
		instantiateLogger();
		CommandLine cmd = getOptions(args, options);
		File pathToFile = new File(System.getProperty("user.dir") + File.separator + args[args.length - 1]);
		if (cmd.hasOption("help")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("format [option] [directory or file]", options);
		} else {
			if (pathToFile.isDirectory()) {
				ArrayList<File> files = new ArrayList<File>(Arrays.asList(pathToFile.listFiles()));
				for (File f : files) {
					String code = readInFile(f.toString());
					formatOne(f.toString(), code, cmd);
				}
			} else if (pathToFile.exists()) {
				String code = readInFile(args[args.length - 1]);
				formatOne(args[args.length - 1], code, cmd);
			} else {
				log.info("!!!invalid input!!!");
			}
		}

	}

	/**
	 * A three-argument method that will take a filename string, the contents of 
	 * that file as a string (before formatted), and the CommandLine arguments and
	 * format the respective file.
	 *
	 * @param filename, a string representing the name of the file
	 * @param code, a string containing the contents of that file
	 * @param cmd, the list of command line arguments
	 */
	public static String formatOne(String fileName, String code, CommandLine cmd) {
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		String nameWithDate = null;
		if (code != null && extension.equals("java")) {
			JavaFormat javaFormatter = new JavaFormat();
			javaFormatter.format(fileName, code);
			if (javaFormatter.isFormatted() && cmd.hasOption("b"))
				nameWithDate = createBackupFile(fileName, code);

		} else if (code != null && extension.equals("groovy")) {
			GroovyFormat groovyFormatter = new GroovyFormat();
			groovyFormatter.format(fileName, code);
			if (groovyFormatter.isFormatted() && cmd.hasOption("b"))
				nameWithDate = createBackupFile(fileName, code);

		} else {
			log.info("Sorry, no formatting could be applied to " + fileName);
		}
		return nameWithDate;
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
	public static String readInFile(String fileName) {
		BufferedReader inStream = null;
		StringBuilder code = new StringBuilder("");
		String line;
		try {
			FileReader file = new FileReader(fileName);
			inStream = new BufferedReader(file);
			while ((line = inStream.readLine()) != null) {
				code.append(line + "\n");
			}
			code.delete(code.length()-1, code.length());

		} catch (IOException e) {
			log.error("Error occured when opening " + fileName);
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
	 */
	public static String createBackupFile(String fileName, String before) {
		String nameWithDate = null;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss");
			String formattedDate = sdf.format(date);
			nameWithDate = fileName + "_BACKUP_" + formattedDate;
			FileWriter file = new FileWriter(nameWithDate);
			PrintWriter safety = new PrintWriter(file);
			safety.println(before);
			safety.close();
			return nameWithDate;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nameWithDate;
	}

	/**
	 * A no-argument method that will return the command line arguments as a 
	 * CommandLine object
	 *
	 * @return cmd, the CommandLine object holding the command line arguments
	 */
	public static CommandLine getOptions(String[] args, Options options) {
		options.addOption("b", false, "create a backup file");
		options.addOption("help", false, "print this message");
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			log.error(e, e);
		}
		return cmd;
	}

	/**
	* A no-argument method used to intantiate the logger
	*/
	public static void instantiateLogger() {
		ConsoleAppender console = new ConsoleAppender();
		String PATTERN = "%m%n";
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(Level.DEBUG);
		console.activateOptions();
		log.addAppender(console);
	}
}

