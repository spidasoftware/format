format
======

The goal of this project is to combine the formatting capabilities of various projects in the open source community 
into one simple command-line feature. Eclipse and Groovy-Eclipse provide effective java and groovy formatters respectively,
so it was decided to extract those classes and add it to this project. However, that was only the beginning.
this program has the potential to format a variety of languages, including javascript, html, gsp, and many others. Below are directions on
how to setup and use this formatter, but also included is information on how to add more formatters to this project. Feel free 
to download this repository and add formatters of your own. In addition, there are instructions on how to add your own preferences to 
the java and groovy formatters incase you prefer different standards.

## Disclaimer

This formatter has been tested on over a 1000 groovy and java files. In addition, the groovy formatter has been modified so that it would 
cancle any formatting of source code that contained unrecognizable syntax. That being said, All software associated with this program is
covered under the [Apache](http://www.apache.org/licenses/LICENSE-2.0 "Apache License, Version 2.0") and [Eclipse Public](http://www.eclipse.org/legal/epl-v10.html "Eclipse Public License - v 1.0") licenses.

## Installation of Software

Perform/ensure the following: 

1. Only a bash script has been made for this program, so if you work on a windows
	OS, you will need to install a bash terminal (git, cygwin, etc.). 

1. You will need a recent version of the Java runtime environment (as well as a complete JDK if you plan to modify the source code).

1. Download this repository (you can download or clone the repo by clicking the button to right of this page).

1. Add the absolute path of the format-v[number] directory to your path.

1.	To make sure it was correctly installed, enter the following command in your bash shell:

```
	formatBashTest
```

If all tests passed, the software has been correctly installed.

## How to Use

This program works like most standard command-line formatters. To display the options, enter the following 
command into your bash shell:

```
format -h
```

As you can see, there are four options provided with the formatter: "-h" (the last command you ran), "-b", "-r", and
"-g." The -b will create a backup file for the file that the formatter will be ran on. "-r" will format all files
in the directory and subdirectories of the directory that was ran on. "-g" will format all files modified in your current
working directory on github. 

For example, running the following command in your bash shell will format all files and provide backup files in the directory
and subdirectories of the directory that was specified.

```
	format -r -b <path/to/directory>
```


## Adding additional formatters

Adding additional languages to this command-line formatter is relatively easy. To
do this, navigate to format-v[number]/conf directory, and there will be two files:
extension.cfg and hashbang.cfg. The extension.cfg file is used to format files that have a 
particular extension. The hashbang.cfg file is used to format files that have a particular string in the first
line of the source code. To add a formatter that will identify files with a particular file extension,
open the extension.cfg file. Inside, there will be comments as well as two lines, one starting 
with "java" and the other starting with "groovy." These are the two formatters that have been 
added to the program when installed. observe the line: 
```
java = java -jar ${SCRIPT_LOCATION}/formatters/EclipseFormatter.jar -java
```
This line is telling the program to format all files with the extension, "java." This line is also
telling the program to use the following command line (after the equals sign) to format the file. 
The bash variable, "SCRIPT_LOCATION," was used to get the path of the "format" bash script file when
it was ran. Use this variable if you decide to place a new formatter in a location that is relative
to the "format" bash script. To add a new formatter to this program, follow this outline. I.e. simply
add the extension, an "=", followed by the command.

Now to format files based on the first line of code in the source rather than using the file extension,
navigate yourself to the hashBang.cfg file. Inside you will see the same structure; however, instead of
a file extension being used to indicate which files to format, enter in the contents that the first line
of code the program must have. For instance, there is one formatter located in the file already. this 
formatter formats all files that have the first line containing "#!/usr/bin/env groovy".

A few requirements of the additional formatters you add:

   * That formatter that you add must take a file to format as the last argument on the command-line
   * The formatters located in the hashBang.cfg file will only be used on files that have no file extension
   * When adding to the extension.cfg file, make sure you do not include the "." with the extension
   * Do not add any comments to the hashBang.cfg file
   * Make sure the formatting command that you add will replace the input file with the new formatted file. That is, it will format the file that you entered in as an argument
   * Make sure the command does not create a backup file. The program already has an option to do that
   * A newline must be the last character of both config files

## Modifying the java or groovy formatters

If you would like to make changes to the Eclipse formatter preferences, you will need to install the following on your computer:
   * A recent version of the Java runtime environment and the JDK
   * Maven 2.2.1 to build the project. 

For your reference, here's the [Javadoc](http://spidasoftware.github.io/format/index "The project's Javadoc") for this project:

### Java formatting preferences

   * In the EclipseFormatter/src/main/java/com/spidasoftware/EclipseFormatter/JavaFormat.java file, there is the initializeFormatter method where you can add or remove preferences of the formatter.

   * The api used to add or remove your own preferences are located [here](http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjdt%2Fcore%2Fformatter%2FDefaultCodeFormatterConstants.html "Eclipse Api")

   * I give a couple examples of how to add preferences in the method's source code.

### Groovy formatting preferences

   * In the EclipseFormatter/src/main/java/com/spidasoftware/EclipseFormatter/GroovyFormat.java file, there is the initializeFormatter method where you can add or remove preferences of the formatter.

   * The api used to add or remove your own preferences are located [here](http://spidasoftware.github.io/format/com/spidasoftware/EclipseFormatter/SpidaFormatterPreferences "Groovy Formatting Preferences")

   * I give a couple examples of how to add preferences in the method's source code.

======

Once you have made changes to the code, navigate yourself to the format/EclipseFormatter directory
and run:

```
mvn clean install
```
If all tests pass, this will construct you the jar, "EclipseFormatter/target/EclipseFormatter.jar." 
Replace the jar located in format-v[number]/formatters/ directory with the one created. This will 
add your changes to the program. Note: do not replace the jar with the EclipseFormatter-1.0-SNAPSHOT. This
jar does not have all of the dependencies.

### Useful Links

   * [The Javadocs for this project](http://spidasoftware.github.io/format/index "The project's Javadoc")
   * [The Eclipse Api for formatting Java code](http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjdt%2Fcore%2Fformatter%2FDefaultCodeFormatterConstants.html "DefaultCodeFormatterConstants API")
   * [The Apache license](http://www.apache.org/licenses/LICENSE-2.0 "Apache License, Version 2.0")
   * [The Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html "Eclipse Public License - v 1.0").
