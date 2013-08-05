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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.codehaus.groovy.eclipse.refactoring.formatter.FormatterPreferencesOnStore;

/** 
 * This class provides the client with various methods to set his or her desired 
 * groovy formatting preferences. SpidaFormatterPreferences extends
 * FormatterPreferencesOnStore which adds setter methods to allow the client to
 * change the default groovy formatting preferences.
 * 
 * @author Nicholas Joodi
 *
 */


public class SpidaFormatterPreferences extends FormatterPreferencesOnStore {
	

	private boolean useTabs;
    private int tabSize;
    private int indentSize;
    private int bracesStart;
    private int bracesEnd;
    private int maxLineLength;
    private boolean indentEmptyLines;
    private boolean removeUnnecessarySemicolons;
    private int longListLength;

    /**
     * A one argument constructor that takes a IPreferenceStore object. If 
     * this object equals null, then the new SpidaFormatterPreferences object
     * the default preferences from FormatterPreferencesOnStore.
     * 
     * @param preferences
     */
    public SpidaFormatterPreferences(IPreferenceStore preferences) {
		super(preferences);    
        useTabs = super.useTabs();
        tabSize = super.getTabSize();
        indentSize = super.getIndentationSize();
        bracesStart = super.getBracesStart();
        bracesEnd = super.getBracesEnd();
        maxLineLength = super.getMaxLineLength();
        indentEmptyLines = super.isIndentEmptyLines();
        removeUnnecessarySemicolons = super.isRemoveUnnecessarySemicolons();
        longListLength = super.getLongListLength();
	}
    
    /**
     * A one-argument method that will set a new value for where the braces will start relative to the 
     * method, field, conditional declaration, etc. Default value is 0.
     * 
     * @param newValue
     * @return int containg the value of where the braces will start
     */
    public int setBracesStart(int newValue) {
    	bracesStart = newValue;
        return bracesStart;
    }

    /**
     *  A one-argument method that will take a boolean indicating whether to use tabs or not. Default value is
     *  true
     *  
     * @param newValue
     * @return a boolean indicating whether to use tabs or not
     */
    public boolean setUseTabs(boolean newValue) {
    	useTabs = newValue;
        return useTabs;
    }
    
    /**
     * A one-argument method that will set a new value for where the braces end relative to the 
     * last line of code of the method, field, conditional, etc. Default value is 1.
     * 
     * @param newValue
     * @return int containg the value of where the braces will end
     */
    public int setBracesEnd(int newValue) {
    	bracesEnd = newValue;
        return bracesEnd;
    }

    /**
     * A one-argument method that will set a new value for maximum length of a line of code. The
     * default value is 80.
     * 
     * @param newValue
     * @return int representing the maximum line length
     */
    public int setMaxLineLength(int newValue) {
    	maxLineLength = newValue;
        return maxLineLength;
    }

    /**
     * A one-argument method that takes a boolean indicating whether or not to indent empty lines
     * The default value is false.
     * 
     * @param newValue
     * @return a boolean indicating whether or not to indent empty lines
     */
    public boolean setIndentEmptyLines(boolean newValue) {
    	indentEmptyLines = newValue;
        return indentEmptyLines;
    }
    
    /**
     * A one-argument method that sets a new value for the indentation size. The default value
     * is 4.
     * 
     * @param newValue
     * @return int representing the indentation size.
     */
    public int setIndentationSize(int newValue) {
    	indentSize = newValue;
        return indentSize;
    }
    
    /**
     * If the tabs are being used for formatting, then this one-argument method will set
     * a new value for the size of a tab. the default value is 4.
     * 
     * @param newValue
     * @return int representing the tab size.
     */
    public int setTabSize(int newValue) {
    	tabSize = newValue;
        return tabSize;
    }

    /**
     * A one-argument method that takes a boolean indicating whether or not to remove 
     * unnecessary semicolons. The default value is false
     * 
     * @param newValue
     * @return boolean indicating whether or not to remove unnecessary semicolons
     */
    public boolean setRemoveUnnecessarySemicolons(boolean newValue) {
    	removeUnnecessarySemicolons = newValue;
        return removeUnnecessarySemicolons;
    }

    /**
     * A one-argument method that sets a new value for the length of a square
     * bracket list. The default value is 30.
     * 
     * @param newValue
     * @return int representing the length of a square bracket list.
     */
    public int setLongListLength(int newValue) {
    	longListLength = newValue;
        return longListLength;
    }
    
    /**
     * A no-argument method that will return value for where the braces will start relative to the 
     * method, field, conditional declaration, etc. Default value is 0.
     * 
     * @return int containg the value of where the braces will start
     */
    public int getBracesStart() {
        return bracesStart;
    }

    /**
     *  A no-argument method that will return a boolean indicating whether to use tabs or not. Default value is
     *  true
     *  
     * @return a boolean indicating whether to use tabs or not
     */
    public boolean useTabs() {
        return useTabs;
    }

    /**
     * A no-argument method that will return the value for where the braces end relative to the 
     * last line of code of the method, field, conditional, etc. Default value is 1.
     * 
     * @return int containg the value of where the braces will end
     */
    public int getBracesEnd() {
        return bracesEnd;
    }

    /**
     * A no-argument method that will return the value for maximum length of a line of code. The
     * default value is 80.
     * 
     * @return int representing the maximum line length
     */
    public int getMaxLineLength() {
        return maxLineLength;
    }

    /**
     * A no-argument method that returns a boolean indicating whether or not to indent empty lines
     * The default value is false.
     * 
     * @return a boolean indicating whether or not to indent empty lines
     */
    public boolean isIndentEmptyLines() {
        return indentEmptyLines;
    }

    /**
     * A no-argument method that returns the value for the indentation size. The default value
     * is 4.
     * 
     * @return int representing the indentation size.
     */
    public int getIndentationSize() {
        return indentSize;
    }
    
    /**
     * A no-argument method will set returns the value for the size of a tab. the default value is 4.
     * 
     * @return int representing the tab size.
     */
    public int getTabSize() {
        return tabSize;
    }

    /**
     * A no-argument method that returns the boolean indicating whether or not to remove 
     * unnecessary semicolons. The default value is false
     * 
     * @return boolean indicating whether or not to remove unnecessary semicolons
     */
    public boolean isRemoveUnnecessarySemicolons() {
        return removeUnnecessarySemicolons;
    }

    /**
     * A no-argument method that returns the value for the length of a square
     * bracket list. The default value is 30.
     * 
     * @return int representing the length of a square bracket list.
     */
    public int getLongListLength() {
        return longListLength;
    }
}