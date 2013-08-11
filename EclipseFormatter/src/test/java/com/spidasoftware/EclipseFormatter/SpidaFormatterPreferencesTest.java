/*
 * Copyright (C) 2013 the original author or authors.
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

import org.eclipse.jface.preference.IPreferenceStore;
import com.spidasoftware.EclipseFormatter.SpidaFormatterPreferences;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * tests for the SpidaFormatterPreferences class
 *
 * @author Nicholas Joodi
 */
public class SpidaFormatterPreferencesTest extends TestCase {
	private SpidaFormatterPreferences customizedPrefs;
	private final static Logger log = Logger.getLogger(GroovyFormat.class);

	public void setUp() throws Exception {
		IPreferenceStore pref = null;
		customizedPrefs = new SpidaFormatterPreferences(pref);
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, bracesStart was set
	 */
	public void testBracesStartConstruct() {
		log.info("SpidaFormatterPreferences Constructor, BracesStart preference was set");
		assertTrue("Constructor: BracesStart preferences was set", customizedPrefs.getBracesStart() == 0);
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, useTabs was set
	 */
	public void testUsetabsConstruct() {
		log.info("SpidaFormatterPreferences Constructor, UseTabs preference was set");
		assertTrue("Constructor: UseTabs preferences was set", customizedPrefs.useTabs());
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, bracesEnd was set
	 */
	public void testBracesEndConstruct() {
		log.info("SpidaFormatterPreferences Constructor, BracesEnd preference was set");
		assertTrue("Constructor: BracesEnd preferences was set", customizedPrefs.getBracesEnd() == 1);
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, maxlinelength was set
	 */
	public void testMaxLineLengthConstruct() {
		log.info("SpidaFormatterPreferences Constructor, MaxLineLength preference was set");
		assertTrue("Constructor: maxLineLength preferences was set", customizedPrefs.getMaxLineLength() == 80);
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, IndentEmptyLines was set
	 */
	public void testIndentEmptyLinesConstruct() {
		log.info("SpidaFormatterPreferences Constructor, IndentEmptyLines preference was set");
		assertTrue("Constructor: IndentEmptyLines preferences was set", !customizedPrefs.isIndentEmptyLines());
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, IndentationSize was set
	 */
	public void testIndentationSizeConstruct() {
		log.info("SpidaFormatterPreferences Constructor, IndentationSize preference was set");
		assertTrue("Constructor: IndentationSize preferences was set", customizedPrefs.getIndentationSize() == 4);
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, TabSize was set
	 */
	public void testTabSizeConstruct() {
		log.info("SpidaFormatterPreferences Constructor, TabSize preference was set");
		assertTrue("Constructor: TabSize preferences was set", customizedPrefs.getTabSize() == 4);
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, RemoveUnnecessarySemicolons was set
	 */
	public void testRemoveUnnecessarySemicolonsConstruct() {
		log.info("SpidaFormatterPreferences Constructor, RemoveUnnecessarySemicolons preference was set");
		assertTrue("Constructor: RemoveUnnecessarySemicolons preferences was set",
				!customizedPrefs.isRemoveUnnecessarySemicolons());
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, IndentationMultiline was set
	 */
	public void testIndentationMultilineConstruct() {
		log.info("SpidaFormatterPreferences Constructor, IndentationMultiline preference was set");
		assertTrue("Constructor: IndentationMultiline preferences was set",
				customizedPrefs.getIndentationMultiline() == 2);
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, SmartPaste was set
	 */
	public void testSmartPasteConstruct() {
		log.info("SpidaFormatterPreferences Constructor, SmartPaste preference was set");
		assertTrue("Constructor: SmartPaste preferences was set", customizedPrefs.isSmartPaste());
	}

	/**
	 * Test the SpidaFormatterPreferences constructor, LongListLength was set
	 */
	public void testLongListLengthConstruct() {
		log.info("SpidaFormatterPreferences Constructor, LongListLength preference was set");
		assertTrue("Constructor: LongListLength preferences was set", customizedPrefs.getLongListLength() == 30);
	}

	/**
	 * Test the SpidaFormatterPreferences set method, bracesStart was set
	 */
	public void testBracesStartSet() {
		log.info("SpidaFormatterPreferences set method, BracesStart preference was set");
		customizedPrefs.setBracesStart(1);
		assertTrue("Set method: BracesStart preferences was set", customizedPrefs.getBracesStart() == 1);
	}

	/**
	 * Test the SpidaFormatterPreferences set method, useTabs was set
	 */
	public void testUsetabsSet() {
		log.info("SpidaFormatterPreferences set method, UseTabs preference was set");
		customizedPrefs.setUseTabs(false);
		assertTrue("Set method: UseTabs preferences was set", !customizedPrefs.useTabs());
	}

	/**
	 * Test the SpidaFormatterPreferences set method, bracesEnd was set
	 */
	public void testBracesEndSet() {
		log.info("SpidaFormatterPreferences set method, BracesEnd preference was set");
		customizedPrefs.setBracesEnd(0);
		assertTrue("Set method: BracesEnd preferences was set", customizedPrefs.getBracesEnd() == 0);
	}

	/**
	 * Test the SpidaFormatterPreferences set method, maxlinelength was set
	 */
	public void testMaxLineLengthSet() {
		log.info("SpidaFormatterPreferences set method, MaxLineLength preference was set");
		customizedPrefs.setMaxLineLength(90);
		assertTrue("Set method: maxLineLength preferences was set", customizedPrefs.getMaxLineLength() == 90);
	}

	/**
	 * Test the SpidaFormatterPreferences set method, IndentEmptyLines was set
	 */
	public void testIndentEmptyLinesSet() {
		log.info("SpidaFormatterPreferences set method, IndentEmptyLines preference was set");
		customizedPrefs.setIndentEmptyLines(true);
		assertTrue("Set method: IndentEmptyLines preferences was set", customizedPrefs.isIndentEmptyLines());
	}

	/**
	 * Test the SpidaFormatterPreferences set method, IndentationSize was set
	 */
	public void testIndentationSizeSet() {
		log.info("SpidaFormatterPreferences set method, IndentationSize preference was set");
		customizedPrefs.setIndentationSize(5);
		assertTrue("Set method: IndentationSize preferences was set", customizedPrefs.getIndentationSize() == 5);
	}

	/**
	 * Test the SpidaFormatterPreferences set method, TabSize was set
	 */
	public void testTabSizeSet() {
		log.info("SpidaFormatterPreferences set method, TabSize preference was set");
		customizedPrefs.setTabSize(6);
		assertTrue("Set method: TabSize preferences was set", customizedPrefs.getTabSize() == 6);
	}

	/**
	 * Test the SpidaFormatterPreferences set method, RemoveUnnecessarySemicolons was set
	 */
	public void testRemoveUnnecessarySemicolonsSet() {
		log.info("SpidaFormatterPreferences set method, RemoveUnnecessarySemicolons preference was set");
		customizedPrefs.setRemoveUnnecessarySemicolons(true);
		assertTrue("Set method: RemoveUnnecessarySemicolons preferences was set",
				customizedPrefs.isRemoveUnnecessarySemicolons());
	}

	/**
	 * Test the SpidaFormatterPreferences set method, LongListLength was set
	 */
	public void testLongListLengthSet() {
		log.info("SpidaFormatterPreferences set method, LongListLength preference was set");
		customizedPrefs.setLongListLength(100);
		assertTrue("Set method: LongListLength preferences was set", customizedPrefs.getLongListLength() == 100);
	}

	/**
	 * Test the SpidaFormatterPreferences set method, IndentationMultiline was set
	 */
	public void testIndentationMultilineSet() {
		log.info("SpidaFormatterPreferences set method, IndentationMultiline preference was set");
		customizedPrefs.setIndentationMultiline(1);
		assertTrue("Set method: IndentationMultiline preferences was set",
				customizedPrefs.getIndentationMultiline() == 1);
	}
}

