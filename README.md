format
======

Our general purpose formatter that was pulled together from different projects. The 
main file for this software is located in format-v[number]/format. This is a command line
executable that is capable of integrating many other formatters into one application.
So far, this formatter can automatically format java and groovy files, provided by many
of the classes from the Eclipse API, as well as many of the classes from the Groovy-eclipse
open source project.

### Setup

1. So far, only a bash script has been made for this program, so if you work on a windows
	OS, you will need to install a bash terminal (git, cygwin, etc.). 

1. You will need a recent version of the Java runtime environment (as well as a complete JDK if you plan to modify the source code).

1. Download this repository (or clone it if you plan to modify the source code).

1. Add the absolute path of the format-v[number] directory to your path.

	To make sure it was correctly installed, enter in the following command:

```
	formatBashTest
```


If all tests passed, you correctly installed the software.

### Instructions

Enter the command:

```
format -h
```

This help screen provides you with how to use the formatter in a variety of 
  different ways. I.e.

```
	format <path/to/file>
```
Will format that single file

```
	format -b <path/to/file>
```
Will create a backup for that file

```
	format <path/to/directory>
```

Will format the contents of that directory, etc. 

### Adding additional formatters

The goal of this project is to combine the formatting capabilities of various projects in the open source community 
into one simple command-line feature. We found that the eclipse java source code formatter worked well, so we decided
to extract those classes and add it to this project. We also found that the groovy-eclipse groovy source code formatter
worked well, so we also extracted those classes and added them to this project. 

We tried to make it as easy as possible for you to have more languages to be formatted. To
do this, navigate to format-v[number]/conf directory, and you will see two files:
extension.cfg and hashbang.cfg. The extension.cfg file is used to format files that have a 
particular extension. The hashbang.cfg file is used to format files that have a particular string in the first
line of the source code. If you would like to format files with a particular file extension,
open the extension.cfg file. In there you will see comments as well as two lines, one starting 
with "java" and the other starting with "groovy". These are the two formatters that have been 
added to the program when you installed it. observe the line: 
```
java = java -jar $([[ "$( dirname "${BASH_SOURCE[0]}" )" = "." ]] &&  echo "format-v1.0" || echo "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )")/formatters/EclipseFormatter.jar -java
```
This line is telling the program to format all files with the extension, "java." This line is also
telling the program to use the following command line (after the equals sign) to format the file.
The verbose line of code within the "$(" and the ")" was used to get the path of the "format" bash script file when
it was ran. Feel free to use this if you decide to place a new formatter in a location that is relative
to the "format" bash script.

If you would like to add a new formatter to the program, simply add the extension, an "=", followed by
the command.

Now to format files based on the first line of code in the source rather than using the file extension,
navigate yourself to the hashBang.cfg file. In there you will see the same structure; however, instead of
a file extension being used to indicate which files to format, enter in the contents that the first line
of code the program must have. For instance, there is one formatter located in the file already. this 
formatter formats all files that have the first line containing "#!/usr/bin/env groovy".

A few key requirements of the additional formatters you add:

   * The formatters located in the hashBang.cfg file will only be used on files that have no file extension
   * When adding to the extension.cfg file, make sure you do not include the "." with the extension
   * Do not add any comments to the hashBang.cfg file
   * Make sure the formatting command that you add will replace the input file with the new formatted file. That is, it will format the file that you entered in as an argument
   * Make sure the command does not create a backup file. The program already has an option to do that
   * A newline must be the last character of both config files

### Modifying the existing code

If you would like to make changes to the eclipse formatter preferences, you will need to install the following on your computer:
   * A recent version of the Java runtime environment and the JDK
   * Maven 2.2.1 to build the project. 
   * You will also need to clone the repo

## Java formatting preferences

  In the EclipseFormatter/src/main/java/com/spidasoftware/EclipseFormatter/JavaFormat.java file, there is the initializeFormatter method 
  where you can add or remove preferences of the formatter.

  The api used to add or remove your own preferences are located at:

  [Java Formatting Preferencs](http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjdt%2Fcore%2Fformatter%2FDefaultCodeFormatterConstants.html "Eclipse Api")

  I give a couple examples of how to add preferences in the method's source code.

## Groovy formatting preferences

  In the EclipseFormatter/src/main/java/com/spidasoftware/EclipseFormatter/GroovyFormat.java file, there is the initializeFormatter method 
  where you can add or remove preferences of the formatter.

  The api used to add or remove your own preferences are located at:
  [Groovy Formatting Preferences](http://spidasoftware.github.io/format/com/spidasoftware/EclipseFormatter/SpidaFormatterPreferences "Groovy Formatting Preferences")

  I give a couple examples of how to add preferences in the method's source code.


Once you have made changes to the code, navigate yourself to the format/EclipseFormatter directory
and run:

```
mvn clean install
```
If all tests pass, this will construct you the jar, "EclipseFormatter/target/EclipseFormatter.jar." 
Replace the jar located in format-v[number]/formatters/ directory with the one created. This will 
add your changes to the program.
