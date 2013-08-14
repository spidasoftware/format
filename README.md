`format`
======

The goal of this project is to combine the formatting capabilities of various projects in the open source community 
into one easy-to-use command-line feature. The Eclipse IDE and The Groovy-Eclipse plugin provide effective java and groovy source code formatters respectively,
so it was decided to extract those classes and add them to this project. However, this project has the potential to format an unlimited number of languages. Below are directions on how to setup and use this formatter, but also included is information on how to add more formatters to this project. Feel free to download this repository and add formatters of your own. In addition, there are instructions on how to add your own preferences to 
the java and groovy formatters incase you prefer different standards.

## Disclaimer

This formatter has been tested on over 1000 groovy and java files. In addition, the groovy formatter has been modified so that it would 
cancel any formatting of source code that contained unrecognizable syntax. That being said, all software associated with this program is
covered under the [Apache](http://www.apache.org/licenses/LICENSE-2.0 "Apache License, Version 2.0") and [Eclipse Public](http://www.eclipse.org/legal/epl-v10.html "Eclipse Public License - v 1.0") licenses.

## Installation of Software

Perform/Ensure the Following: 

1. Only a bash script has been made for this program, so if you work on a windows
	OS, you will need to install a bash terminal (git, cygwin, etc.). 

1. You will need a recent version of the Java runtime environment (as well as a complete JDK if you plan to modify the source code).

1. Download this repository (you can download or clone the repo by clicking the button to the right of this page).

1. Add the absolute path of the format-v[number] directory to your path.

1.	To make sure it was correctly installed, enter the following command in your bash shell:

```
	format -h
```

If the output is the usage of the formatter, then the software was correctly installed.

## How to Use

This program works like most standard command-line formatters. To display the options, enter the same command entered in step
5 of "Installation of Software":

```
   format -h
```

As you can see, there are four options provided with the formatter: "-h" (the last command you ran), "-b", "-r", and
"-g." 

   * "-b" will create a backup file for the file that was ran on. However, if no changes were made to the source code 
   after formatting took place, then a backup will not be created. 
   * "-r" will format all files in the directory and subdirectories of the directory that was ran on. 
   * "-g" will format all files modified in your current working directory on github. 

For example, running the following command in your bash shell will format all files and provide backup files in the directory
and subdirectories of the directory that was specified.

```
	format -r -b <path/to/directory>
```

## Other `format` information

  * [Add Additional Formatters](https://github.com/spidasoftware/format/wiki/Adding-Additional-Formatters)
  * [Technical Notes on Groovy and Java Formatter](https://github.com/spidasoftware/format/wiki/Included-Groovy-and-Java-Formatters)
  * [Hooking into External Tools](https://github.com/spidasoftware/format/wiki/Hooking-into-External-Tools)
  * [The Javadocs for this project](http://spidasoftware.github.io/format/index "The project's Javadoc")


## External Links

   * [The Eclipse Api for formatting Java code](http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.jdt.doc.isv%2Freference%2Fapi%2Forg%2Feclipse%2Fjdt%2Fcore%2Fformatter%2FDefaultCodeFormatterConstants.html "DefaultCodeFormatterConstants API")
   * [The Apache license](http://www.apache.org/licenses/LICENSE-2.0 "Apache License, Version 2.0")
   * [The Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html "Eclipse Public License - v 1.0")
   * [Groovy-Eclipse Formatter Changes](https://github.com/nickjoodi/groovy-eclipse/commit/789988fae5dee4e4dbde72e924d6bb1dd7679d87 "Groovy-Eclipse Formatter Changes")
