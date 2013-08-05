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

import org.codehaus.groovy.eclipse.refactoring.formatter.DefaultGroovyFormatter;
import com.spidasoftware.EclipseFormatter.SpidaFormatterPreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The GroovyFormat class that will format groovy source code
 *
 * This class formats groovy source code. See the initializeFormatter method to 
 * modify the formatting preferences
 *
 * @author Nicholas Joodi
 */
public class GroovyFormat {

	private boolean correctlyFormatted;
	private final static Logger log = Logger.getRootLogger();

	/**
	 * A no argument constructor that will set the correctlyFormatted field to false
	 */
	public GroovyFormat() {
		correctlyFormatted = false;
	}

	/**
	 * A two-argument method that will take two strings, a filename and its respective code
	 * and format the code
	 * 
	 * @param filename, The name of that file
	 * @param code, The string of code that will be formatted
	 */
	public void format(String fileName, String code) {
			try {
				DefaultGroovyFormatter cf = initializeFormatter(code);
				IDocument dc = new Document(code.toString());
				TextEdit te = cf.format();
				if (te == null || code.length() == 0) {
					log.info("!!! Could not format " + fileName + " !!!");
				} else {
					te.apply(dc);

					PrintWriter out = new PrintWriter(new FileWriter(fileName));
					out.println(dc.get());
					out.close();

					log.info("*** Groovy standard formatting conventions have been applied to " + fileName + " ***");
					correctlyFormatted = true;
				}
			} catch (MalformedTreeException e) {
				log.error(e, e);
				log.error("!!!Could Not format " + fileName + "!!!");
			} catch (BadLocationException e) {
				log.error(e, e);
				log.error("!!!Could Not format " + fileName + "!!!");
			} catch (IOException e) {
				log.error(e, e);
				log.error("!!!Could Not format " + fileName + "!!!");
			} catch (Exception e) {
				log.error("Cannot format " + fileName + ", probably due to compilation errors.  Please fix and try again.");
			}
	}

	/**
	 * A no-argument method that will return whether or not code has been formatted
	 * 
	 * @return a boolean indicating whether the file was formatted or not 
	 */
	public boolean isFormatted() {
		return correctlyFormatted;
	}

	/**
	 * A static method that will prepare the GroovyFormat object for formatting
	 * the respective code that was passed into the format method. If you would like to make changes
	 * to the preferences, see the FormatterPreferencesOnStore class.
	 *
	 * @param code, the string representing the source code of the file	 
	 * @return a DefaultGroovyFormatter class that will format the source code
	 */
	public static DefaultGroovyFormatter initializeFormatter(String code) {
		IPreferenceStore pref = null;
		SpidaFormatterPreferences customizedPrefs = new SpidaFormatterPreferences(pref);
		customizedPrefs.setUseTabs(false);
		customizedPrefs.setLongListLength(120);
		customizedPrefs.setMaxLineLength(120);
		IDocument doc = new Document(code.toString());
		TextSelection sel = new TextSelection(0, code.length());
		DefaultGroovyFormatter formatter = new DefaultGroovyFormatter(sel, doc, customizedPrefs, false);
		return formatter;
	}
}

