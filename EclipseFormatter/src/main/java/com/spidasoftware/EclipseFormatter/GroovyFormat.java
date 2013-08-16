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
 * The GroovyFormat class that will format groovy source code using the formatter classes 
 * from the Groovy-Eclipse open source project.
 *
 * @author Nicholas Joodi
 */
public class GroovyFormat extends Format {
	private final static Logger log = Logger.getRootLogger();

	/**
	 * A no argument constructor that will create a GroovyFormat object.
	 */
	public GroovyFormat() {
		super();
	}

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
			log.error("!!!Could not format " + fileName + "!!!", e);
		} catch (BadLocationException e) {
			log.error("!!!Could not format " + fileName + "!!!", e);
		} catch (IOException e) {
			log.error("!!!Could not format " + fileName + "!!!", e);
		} catch (NullPointerException e) {

			// This is Probably due to the formatter having trouble parsing through 
			// the source code. Instead of printing the stack trace,
			// the lines containg the unrecognizable syntax will be printed and the message 
			// below will be printed as well.
			log.info("!!!Could not format " + fileName + "!!!");
		} catch (Exception e) {
			log.error("!!!Could not format " + fileName + "!!!", e);
		}
	}

	private static DefaultGroovyFormatter initializeFormatter(String code) {
		IPreferenceStore pref = null;
		SpidaFormatterPreferences customizedPrefs = new SpidaFormatterPreferences(pref);

		// This is where you will add your own preferences. For example, below there are three modifications made
		// to the groovy formatter: a bracket list can have a length of 120 characters, the maximum line length is
		// 120 characters, and the indention level for a wrapped line is set to 1 rather than 2.
		customizedPrefs.setLongListLength(120);
		customizedPrefs.setMaxLineLength(120);
		customizedPrefs.setIndentationMultiline(1);

		IDocument doc = new Document(code.toString());
		TextSelection sel = new TextSelection(0, code.length());
		DefaultGroovyFormatter formatter = new DefaultGroovyFormatter(sel, doc, customizedPrefs, false);
		return formatter;
	}
}

