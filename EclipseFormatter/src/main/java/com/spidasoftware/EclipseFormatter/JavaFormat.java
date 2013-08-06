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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Appender;
import org.apache.log4j.PatternLayout;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

/**
 * The JavaFormat class that will format java source code using the formatter classes from the 
 * Eclipse api.
 *
 * @author Nicholas Joodi
 */
public class JavaFormat {
	private boolean correctlyFormatted;
	private static Logger log = Logger.getRootLogger();

	/**
	 * A no argument constructor that will create a JavaFormat object.
	 */
	public JavaFormat() {
		ConsoleAppender console = new ConsoleAppender();
		String PATTERN = "%m%n";
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(Level.DEBUG);
		console.activateOptions();
		correctlyFormatted = false;
	}

	/**
	 * A two-argument method that will take two strings, a fileName and its respective code
	 * and format the code.
	 * 
	 * @param fileName The name of that file.
	 * @param code The string of code that will be formatted.
	 */
	public void format(String fileName, String code) {
		CodeFormatter cf = initializeFormatter();
		TextEdit te = cf.format(CodeFormatter.K_UNKNOWN, code, 0, code.length(), 0,
				System.getProperty("line.separator"));
		IDocument dc = new Document(code.toString());
		if (te == null || code.length() == 0) {
			log.info("!!! Could not format " + fileName + " !!!");
		} else {
			try {
				te.apply(dc);

				PrintWriter out = new PrintWriter(new FileWriter(fileName));
				out.println(dc.get());
				out.close();

				log.info("*** Java standard formatting conventions have been applied to " + fileName + " ***");
				correctlyFormatted = true;
			} catch (MalformedTreeException e) {
				log.error("!!!Could not format " + fileName + "!!!");
			} catch (BadLocationException e) {
				log.error("!!!Could not format " + fileName + "!!!");
			} catch (IOException e) {
				log.error("!!!Could not format " + fileName + "!!!");
			} catch (Exception e) {
				log.error("!!!Could not format " + fileName + "!!!");
			}
		}
	}

	/**
	 * A no-argument method that will return a boolean indicating if the code has been formatted.
	 * 
	 * @return a boolean indicating if the file was formatted.
	 */
	public boolean isFormatted() {
		return correctlyFormatted;
	}

	/**
	 * A static method that will prepare the JavaFormat object for formatting
	 * the respective code that was passed into the format method. 
	 *
	 * @param code the String representing the source code of the file. 
	 * @return a CodeFormatter class that will format the source code.	 
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	private static CodeFormatter initializeFormatter() {
		@SuppressWarnings("rawtypes")
		Map options = DefaultCodeFormatterConstants.getEclipseDefaultSettings();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);

		// This is where you will add your java formatting preferences. I have an example below that 
		// is commented out that would not allow for a line to be split at the selectors in a method invocation.	

		// options.put(DefaultCodeFormatterConstants.FORMATTER_ALIGNMENT_FOR_SELECTOR_IN_METHOD_INVOCATION,
		//		DefaultCodeFormatterConstants.createAlignmentValue(false, DefaultCodeFormatterConstants.WRAP_NO_SPLIT, DefaultCodeFormatterConstants.INDENT_DEFAULT));
		options.put(DefaultCodeFormatterConstants.FORMATTER_LINE_SPLIT, "120");

		final CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(options);
		return codeFormatter;

	}
}

