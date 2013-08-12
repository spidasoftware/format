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

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Appender;
import org.apache.log4j.PatternLayout;

/**
 * An abstract class that will format source code.
 *
 * @author Nicholas Joodi
 */
abstract public class Format {
    protected boolean correctlyFormatted;

    /**
     * A no argument constructor that will create a Format object.
     */
    public Format() {
        ConsoleAppender console = new ConsoleAppender();
        String PATTERN = "%m%n";
        console.setLayout(new PatternLayout(PATTERN));
        console.setThreshold(Level.DEBUG);
        console.activateOptions();
        this.correctlyFormatted = false;
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
     * A two-argument method that will take two strings, a fileName and its respective code
     * and format the code.
     * 
     * @param fileName The name of that file.
     * @param code The string of code that will be formatted.
     */
    abstract public void format(String fileName, String code);
}