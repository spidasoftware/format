format
======

Our general purpose formatter that was pulled together from different projects. The 
main file for this software is located in format-v-/format. This is a command line
executable that is capable of integrating many other formatters into one application.
So far, this formatter can automattically format java and groovy files, provided by many
of the classes from the Eclipse API, as well as many of the classes from the Groovy-eclipse
open source project.

### Setup

1. So far, only a bash script has been made for this program, so if you work on a windows
	OS, you will need to install a bash terminal (git, cygwin, etc.)

1. Clone the repo

1. Make a global variable called FORMAT that has the absolute path of format-v[number]
 	as it's value.

	To make sure it was correctly installed, enter in the following command:

```
	format -h
```
	Which should diplay the following:

```
Usage: format [options] <file or directory with relative path>
    h:       print this message
    b:       create a backup file
    r:       format all files recursively starting from the directory specified
    g:       format all modified files in the git working directory
    - r and g can not be set together
    - g does not need a filename as an argument
```

If this was shown, you correctly installed the software

### Instructions

The help screen that was displayed earlier alows you to use the formatter in variety of 
  different ways. I.e.

```
	format <path/to/file>
```
Will format that single file

```
	format -b <path/to/file>
```
Wil create a backup for that file

```
	format <path/to/directory>
```

Will format the contents of that directory. Use "format -h" to find out how else you 
can format your files.

### Adding additional formatters

We tried to make it as easy as possible for you to have more languages to be formatted. To
do this, navigate to format-v[number]/conf directory, and you will see two files:
extension.cfg and hashbang.cfg. If you would to format files with a particular file extension,
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
navigate yourself to the hashBang.cfg file. In here you will see the same structure; however, instead of
a file extension being used to indicate which files to format, enter in the contents that the first line
of code the program must have. For instance, there is one formatter located in the file already. this 
formatter formats all files that have the first line containing "#!/usr/bin/env groovy".

### Modifying the existing code

If you would like to make changes to the eclipse formatter preferences, you will need to install
maven 2.2.1 to build the project. Once you have this installed, navigate yourself to
EclipseFormatter/src/main/java/com/spidasoftware/EclipseFormatter/Formatter.java file. Above the 
class declaration, read the comment to determine what you need to do to modify the preferences.

Once you have made changes to the code, navigate yourself to the in format/EclipseFormatter directory
and run:

```
mvn install
```
If all tests pass, this will construct you the jar,EclipseFormatter/target/EclipseFormatter.jar. 
Replace the jar located in format-v-/formatters/directory with the one created. This will 
add your changes to the program.
