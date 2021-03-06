# SEIP-Lab-Assignments
[![Build Status](https://travis-ci.com/NikolasMoatsos/SEIP-Lab-Assignments.svg?token=hsTvfFtqwpyWiMP9NiyE&branch=main)](https://travis-ci.com/NikolasMoatsos/SEIP-Lab-Assignments)
[![codecov](https://codecov.io/gh/NikolasMoatsos/SEIP-Lab-Assignments/branch/main/graph/badge.svg?token=7CQHIBNVSK)](https://codecov.io/gh/NikolasMoatsos/SEIP-Lab-Assignments)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This repository contains the Lab Assignments for the needs of the Software Engineering in Practice (SEiP) course offered by the [Department of Management Science & Technology](https://www.dept.aueb.gr/en/dmst) of the Athens University of Economics and Business.

## Assignments:
1. [Grades Histogram - Build Automation Tools](#build) 
2. [Unit Testing](#unittesting)
3. [Source Code Analyzer - Design Patterns](#designpatterns)

---

## <a name="build"></a>Grades Histogram - Build Automation Tools

**Description**

A Java application (using the Maven build-automation tool)
that reads a file with students’ grades, and generates a histogram according to the grades' frequencies.

**Tools & Technologies Used**
- Git, Github
- Apache Maven 3.8.1
- Java 1.8
- Eclipse IDE
- [JFreeChart](https://www.jfree.org/jfreechart/) charting Java library

**How to Build and Run**

Make sure you have installed properly Git, Maven and Java.

1. Clone this repository, with the command: <code>git clone https://github.com/NikolasMoatsos/SEIP-Lab-Assignments.git</code>.
2. Enter to your local copy of this repository, with the command: <code>cd SEIP-Lab-Assignments</code>.
3. Build the Maven project, with the command: <code>mvn install</code>.
4. Enter the target directory to find the jar file, with the command: <code>cd gradeshistogram</code> , <code>cd target</code>.
5. Create a grades.txt file inside the target directory and enter some grades from the scale 0-10. Each grade should be in a separate line!  
6. Execute the jar file, with the command: <code>java -jar gradeshistogram-0.0.1-SNAPSHOT-jar-with-dependencies.jar grades.txt</code>.

*Note that the gradeshistogram-0.0.1-SNAPSHOT.jar is not executable.*

## <a name="unittesting"></a>Unit Testing

See more details about the Unit Testing assignment in the according [README.md](unittesting/README.md).

## <a name="designpatterns"></a>Source Code Analyzer - Design Patterns

See more details about the Design Patterns assignment in the according [README.md](sourcecodeanalyzer/README.md).

## Licence 
Licensed under the [MIT Licence](LICENSE). 
