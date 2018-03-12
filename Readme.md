# CS211
Bellevue College, Winter 2018
Author: Zach Wilson

# Overview
This repository contains all the code that I wrote as part of Bellevue College's CS211 class. Each assignment is located in its own folder, and a description of each assignment can be found below. The code I've written is heavily commented and should be fairly easy to read. The commenting style I use is based on [Doxygen](https://www.stack.nl/~dimitri/doxygen/index.html) rather than Javadocs, as I use Doxygen for generating documentation for other projects I work on.

> All assignment implementatons provided here received perfect scores and/or extra credit. These are provided *as is*, and are intended to help students learn how to code. **Do not use the provided code as drop-in replacements for your own code.** Doing so won't result in you learning how to code or to become a better programmer (not to mention it's also plagarism).

# Assignments
## Assignment #1
The purpose of this assignment was to become familiar with the use of interfaces. This program reads in item data for a restaurant menu from the `menu.txt` file, then reads in information from three text files (`seattle.txt`, `bellevue.txt`, and `everett.txt`) which contain location-specific changes to apply to the menu.

> A Readme file is provided in this assignment's folder that contains a breakdown of how the program is structured.

## Assignment #2
This assignment was intended to introduce students to working with large data sets. The idea was to acquire a CSV file from a source, such as a government agency, which contained a large amount of geographic data. The data was then processed by the program and rendered using the Google Maps API to create an image with markers placed on the map. Each marker corresponded to one data point in the data set.

My implementation of the assignment reads in data from the data set and is configured to allow the program to selectively pick elements from the data set according to specific criteria. The data is then passed to my Map class, which generates a URL from the data. This URL contains all the data required by the Google Maps API to create the image that is retrieved and displayed by the program.

> The CSV file used by this program is too large to be hosted on Git normally, so I've left it out. The CSV file can be obtained from this [URL](https://data.seattle.gov/api/views/3k2p-39jp/rows.csv?accessType=DOWNLOAD) (direct download link) and was obtained from this [site](https://catalog.data.gov/harvest/seattle-json) (Seattle Police Department 911 Incident Response file). The file should be named `dataset.csv` and placed in the `Assignment 2` folder directly.

## Assignment #3 - 5
The purpose of this set of assignments was familiarize students with [Postfix](https://en.wikipedia.org/wiki/Reverse_Polish_notation) notation.. The three assignments were to (a) evaluate an expression and display error messages for any formatting mistakes, (b) convert the provided infix expression to postfix, and (c) evaluate the postfix expression and return the result. The program I've provided implements all three parts of the assignment.

The program that I've provided has a C++ version and a Java version. The C++ version was written using regexes and template metaprogramming, but uses a slightly different parsing technique that I've since iterated on in a different project. This program is capable of reading in an expression from the command line and evaluating it, including evaluating constants such as `pi` and `e`, as well as special mathematical functions such as `log`, `ln`, and `sin`/`cos`/`tan`.

The Java version of the program is much more simplistic in that it only supports basic arithmetic operators (`+`, `-`, `*`, and `/`). I wrote this version after adding on support for error messages in the C++ version, and realizing that the design of the parser I used for the C++ version could have been better designed in order to have better support for error messages. The parser I wrote for the Java version is a slimmed down implementation of the parser I'm currently using in a different (C++) project.

> A Readme file is provided in this assignment's folder that contains more information on how I process an expression and evaluate it.

## Assignment #6
The purpose of this assignment was to compare the performance of Java's `ArrayList` and `LinkedList`. This program runs several benchmarks on the data structures, then displays the results graphically.

## Assignment #7
This assignment introduces students to the Heap data structure. The implementation I went with uses a tree internally, but can also be done by using an array internally as well.

## Calculator
This was a program I wrote as part of a bonus assignment. This calculator reads in an infix expression and evaluates it by generating a tree internally. This calculator could have been implemented by evaluating the expression using recursive function calls, but was instead implemented by generating a tree of objects because of the assignment requirements.

This program gives some insight into how expressions can be evaluated without first transforming the expression into postfix notation.

## Menu
This program was written prior to assignment #1, and was purely a basic demonstration of how interfaces work. Assignment #1 expanded the scope of the requirements that were present for this program.