/**
 * The GroovyFormat class that will format groovy source code
 * 
 * July of 2013
 * spidasoftware
 * @author Nick Joodi
 */

package com.spidasoftware.EclipseFormatter;

import org.codehaus.groovy.eclipse.refactoring.formatter.DefaultGroovyFormatter;
import com.spidasoftware.EclipseFormatter.FormatterPreferencesOnStore;
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
 * spidasoftware
 * @author Nick Joodi
 */
public class GroovyFormat {

	private boolean correctlyFormatted;
	private final static Logger log = Logger.getRootLogger();

	/*
	 * A no argument constructor that will set the correctlyFormatted field to false
	 */
	public GroovyFormat() {
		correctlyFormatted = false;
	}

	/**
	 * A two-argument method that will take two strings, a filename and its respective code
	 * and format the code
	 * 
	 * @param filename
	 *            The name of that file
	 * @param code
	 *			  The string of code that will be formatted
	 */
	public void format(String fileName, String code) {
		DefaultGroovyFormatter cf = initializeFormatter(code);
		TextEdit te = cf.format();
		IDocument dc = new Document(code.toString());
		if (te == null || code.length() == 0) {
			log.info("!!! Could not format " + fileName + " !!!");
		} else {
			try {
				te.apply(dc);

				PrintWriter out = new PrintWriter(new FileWriter(fileName));
				out.println(dc.get());
				out.close();

				log.info("*** Groovy standard formatting conventions have been applied to " + fileName + " ***");
			} catch (MalformedTreeException e) {
				log.error(e, e);
			} catch (BadLocationException e) {
				log.error(e, e);
			} catch (IOException e) {
				log.error(e, e);
			}
			correctlyFormatted = true;
		}
	}

	/**
	 * A method that will return whether or not code has been formatted
	 * 
	 * @return isFormatted	 
	 */
	public boolean isFormatted() {
		return correctlyFormatted;
	}

	/**
	 * A static method that will prepare the JavaFormat object for formatting
	 * the respective code that was passed in to the format method 	 
	 */
	public static DefaultGroovyFormatter initializeFormatter(String code) {
		IPreferenceStore pref = null;
		FormatterPreferencesOnStore defaultPrefs = new FormatterPreferencesOnStore(pref);
		IDocument doc = new Document(code.toString());
		TextSelection sel = new TextSelection(0, code.length());
		DefaultGroovyFormatter formatter = new DefaultGroovyFormatter(sel, doc, defaultPrefs, false);
		return formatter;
	}
}

