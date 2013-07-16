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

	public GroovyFormat() {
		correctlyFormatted = false;
	}

	/**
	 * A two-argument method that will take to strings, a filename and its respective code
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
			System.out.println("!!! Could not format " + fileName + " !!!");
		} else {
			try {
				te.apply(dc);

				PrintWriter out = new PrintWriter(new FileWriter(fileName));
				out.println(dc.get());
				out.close();

				System.out
						.println("*** Groovy standard formatting conventions have been applied to "
								+ fileName + " ***");
			} catch (MalformedTreeException e) {
				e.printStackTrace();
			} catch (BadLocationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			correctlyFormatted = true;
		}
	}

	/**
	 * A method that will return whether or not code has been formatted
	 * 
	 * @return isFormatted
	 *			 
	 */
	public boolean isFormatted() {
		return correctlyFormatted;
	}

	/**
	 * A static method that will prepare the JavaFormat object for formatting
	 * the respective code that was passed in to the format method 
	 *			 
	 */
	public static DefaultGroovyFormatter initializeFormatter(String code) {
		IPreferenceStore pref = null;
		FormatterPreferencesOnStore defaultPrefs = new FormatterPreferencesOnStore(
				pref);
		IDocument doc = new Document(code.toString());
		TextSelection sel = new TextSelection(0, code.length());
		DefaultGroovyFormatter formatter = new DefaultGroovyFormatter(sel, doc,
				defaultPrefs, false);
		return formatter;

	}
}