Title:          Week 4 - Program Improvement III
Author:         Team B : ( Elijah Cornell / Eric Landeis / Gordon Doskas / James Rippon /
                           Joseph Hart / Keith Green / Lance Branford )
Creation Date:  2016-02-11
Class:          PRG/421 - Roland Morales

Program Requirements:
 - Allow the reading of a collection of animal objects from an external file.
 - Output on screen the content of a collection of animal objects.
 - Use Iterator to achieve these goals.

Team Program Requirements:
 - Must implement at least three improvements
 - Must demonstrate the use JDBC

Team Improvements:
 - Animal data is persisted to a Derby database
 - Added ability to clone animals

The program is based on Eric Landeis's week 3 program.

--

Input: Derby database, if exists
Output: Console

Dependent libraries: lib/derby.jar

--
To Compile outside IDE: javac -d ./out/production/Team-Project-Wk4 -cp ./lib/derby.jar -sourcepath ./src ./src/Main.java

To Run - Windows : java -cp ./lib/derby.jar;./out/production/Team-Project-Wk4 Main
To Run - OSX     : java -cp ./lib/derby.jar:./out/production/Team-Project-Wk4 Main
