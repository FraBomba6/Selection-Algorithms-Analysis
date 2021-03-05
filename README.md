# Selection Algorithms Analysis
## Algorithms and data structures course project A.Y. 2019-2020
This repo collects the work done by Francesco Bombassei De Bona and Andrea Cantarutti for the realization of the first laboratory assignment during Algorithms and data structures course at Università degli Studi di Udine.
The project aims to compare execution times of some of the most famous selection algorithms, directly "on the field". During the project a robust timing mechanism was implemented and a pseudo-random generator based on Mersenne Twister was used for generating "good" random numbers.

### Technical prerequisites
Following software is mandatory for the correct visualization of the work:
* R
* RStudio
* IDE Java
* SDK Java
* Excel, or equivalent

### Repository structure
Relevant folders and files are:
* src - contains all .java source script regarding selection algorithms, collected data management (using a .xlsx file), input generation and other utilities
* R - contains data elaboration in R language and some plotted graphs
* Time.xlsx - file used to collect data and generate preliminary statistics
* Time_Template.xlsx - template to generate all the worksheets in the workbook Time.xlsx
All the other directories and files are irrelevant and just structural

### Precautions in using the class Time.java
The source code in Time.java, particularly the lines for warming up the JVM, were adapted for the available hardware and software settings. This may lead to different performance in other systems.

### Final report notes
* Final report is written in Italian;
* Excel.java and Count.java are not analyzed in the report. Their implementation is marginal with respect to the objectives of the project, their scope is to provide a more user friendly experience in data evaluation. In particular:
  * Excel.java - utilities to automatically build Time.xlsx;
  * Count.java - simple utility to count the number of integers in a provided sequence of numbers.
