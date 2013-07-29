format
======

Our general purpose formatter that was pulled together from different projects. The 
main file for this software is located in format-v[number]/format. This is a command line
executable that is capable of integrating many other formatters into one application.
So far, this formatter can automattically format java and groovy files, provided by many
of the classes from the Eclipse API, as well as many of the classes from the Groovy-eclipse
open source project.

### Setup

1. So far, only a bash script has been made for this program, so if you work on a windows
	OS, you will need to install a bash terminal (git, cygwin, etc.)

1. Clone the repo

1. Add the absolute path of the format-v[number] directory to your path

	To make sure it was correctly installed, enter in the following command:

```
	format -h
```

Which should display the following:


```
Usage: format [options] <file or directory with relative path>
    -h:       print this message
    -b:       create a backup file
    -r:       format all files recursively starting from the directory specified
    -g:       format all modified files in the git working directory
              (r and g can not be set together)
              (g does not need a filename as an argument)
```

If this was shown, you correctly installed the software.

### Instructions

The help screen that was displayed earlier shows you how to use the formatter in a variety of 
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

Will format the contents of that directory. Use "format -h" to find out how else you 
can format your files.

### Adding additional formatters

We tried to make it as easy as possible for you to have more languages to be formatted. To
do this, navigate to format-v[number]/conf directory, and you will see two files:
extension.cfg and hashbang.cfg. The extension.cfg file is used to format files that have a 
particular extension. The hashbang.cfg file is used to format files that have a particular string in the first
line of the source code. If you would like to format files with a particular file extension,
open the extension.cfg file. In there you will see comments as well as two lines, one starting 
with "java" and the other starting with "groovy". These are the two formatters that have been 
added to the program when you installed it. observe the line: 
```
java = java -jar ${FORMAT}/formatters/EclipseFormatter.jar -java
```
This line is telling the program to format all files with the extension, "java." This line is also
telling the program to use the following command line (after the equals sign) to format the file.
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

If you would like to make changes to the eclipse formatter preferences, you will need to install
maven 2.2.1 to build the project. Once you have this installed, navigate yourself to
EclipseFormatter/src/main/java/com/spidasoftware/EclipseFormatter/Formatter.java file. Above the 
class declaration, read the comment to determine what you need to do to modify the preferences.

Once you have made changes to the code, navigate yourself to the format/EclipseFormatter directory
and run:

```
mvn install
```
If all tests pass, this will construct you the jar, "EclipseFormatter/target/EclipseFormatter.jar." 
Replace the jar located in format-v[number]/formatters/ directory with the one created. This will 
add your changes to the program.
