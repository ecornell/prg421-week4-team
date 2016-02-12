#!/bin/bash
javac -d ./out/production/Team-Project-Wk4 -cp ./lib/derby.jar -sourcepath ./src ./src/Main.java
java -cp ./lib/derby.jar:./out/production/Team-Project-Wk4 Main
