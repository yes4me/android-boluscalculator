# Bolus Calculator

## Preface
---

* Purpose: This is a Bolus calculator
* Author: Thomas Nguyen
* Date started: 2017/06/10
* Last update: 2017/06/11

## Table of contents
---

This document will show you how to:

0. [Prerequisites](#Prerequisites)
1. [Setup](#Setup)
2. [Running the program](#Running-the-program)
3. [Known bugs](#Known-bugs)
4. [Other notes](#Other-notes)
5. [Acronyms](#Acronyms)
6. [Sources](#Sources)

## Prerequisites
---

* Windows 10
* Cygwin Bash shell
* Java Development Kit (JDK)
* Android Studio

## Setup
---

1. install and setup cygwin
    * install [Cygwin](https://cygwin.com/install.html)
    * get the version: `uname -a`
2. get the source code
    * install the package: git
    * `git clone <url>`

## Running the program
---

* Run Android Studio
* Run the application (SHIFT-F10)

## Known bugs
---

* This was tested on Moto X, Android version 5.1
* The UI was tested using the Android automation, Expresso, with the Test Recoder.

## Other notes
---

* To calculated the bolus, the recorded Blood Glucose (BG) must be between the "BG range for calculation". If not, you need to go to the hospital.
* correction bolus equation varies based on the current/recorded BG
* meal bolus = carb * IC ratio + adjustmentCorrection_IOB
* total bolus = correction bolus + meal bolus

## Acronyms
---

* Unit Test = white box testing
* white box test = software testing method in which the internal structure/ design/ implementation of the item being tested is known to the tester
* black box test = software testing method in which the internal structure/ design/ implementation of the item being tested is NOT known to the tester

## Sources
---

* Download:

    * [git](https://git-scm.com/)
    * [Java Development Kit (JDK)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

* Tutorials:

    * [README.md: preview of the readme.md](http://dillinger.io/)
    * [JsonReader](https://developer.android.com/reference/android/util/JsonReader.html)
    * [Toolbar](http://abhiandroid.com/materialdesign/toolbar)